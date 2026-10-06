package org.saud.peoplehub.service;

import java.util.List;

import org.jboss.logging.Logger;
import org.saud.peoplehub.dto.request.user.CreateUserRequest;
import org.saud.peoplehub.dto.request.user.UpdateUserRequest;
import org.saud.peoplehub.dto.response.PageResponse;
import org.saud.peoplehub.dto.response.UserResponse;
import org.saud.peoplehub.entity.User;
import org.saud.peoplehub.exception.UserAlreadyExistsException;
import org.saud.peoplehub.exception.UserNotFoundException;
import org.saud.peoplehub.mapper.UserMapper;
import org.saud.peoplehub.repository.UserRepository;
import org.saud.peoplehub.util.PasswordService;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserService {

        @Inject
        UserRepository userRepository;

        @Inject
        PasswordService passwordService;

        @Inject
        UserMapper mapper;

        private static final Logger LOG = Logger.getLogger(UserService.class);

        int passwordLength = 8;

        @Transactional
        public UserResponse createUser(CreateUserRequest request) {

                LOG.infof(
                                "Starting user creation. username=%s, email=%s, role=%s",
                                request.getUsername(),
                                request.getEmail(),
                                request.getRole());

                if (userRepository.findByEmail(request.getEmail()).isPresent()) {
                        LOG.warnf(
                                        "User creation failed. Email already exists. email=%s",
                                        request.getEmail());
                        throw new UserAlreadyExistsException("Email already exists");

                }

                if (userRepository.findByUsername(request.getUsername()).isPresent()) {
                        LOG.warnf(
                                        "User creation failed. Username already exists. username=%s",
                                        request.getUsername());
                        throw new UserAlreadyExistsException("UserName already exists");

                }

                LOG.debugf(
                                "Generating temporary password. username=%s",
                                request.getUsername());
                String temporaryPassword = passwordService.generatePassword(passwordLength);

                LOG.debugf(
                                "Temporary password generated successfully. username=%s",
                                request.getUsername());

                User user = new User();

                user.setEmail(request.getEmail());
                user.setRole(request.getRole());
                user.setUsername(request.getUsername());
                user.setPassword(temporaryPassword);

                userRepository.persist(user);

                LOG.infof(
                                "User created successfully. username=%s, email=%s, role=%s",
                                user.getUsername(),
                                user.getEmail(),
                                user.getRole());
                return mapper.toResponse(user);

        }

        public UserResponse getUserByUsernameOrEmailOrId(String query) {
                LOG.infof("Searching user by username, email, or id=%s", query);

                User user = userRepository.findByUsernameOrEmailOrId(query).orElseThrow(() -> {
                        LOG.warnf("User not found with username, email, or id=%s", query);

                        return new UserNotFoundException(
                                        "User not found with username or email or id : " + query);
                });

                LOG.infof("User found successfully with id=%s", user.getId());

                return mapper.toResponse(user);
        }

        public PageResponse<UserResponse> getAllUsers(int page, int size) {

                LOG.infof("Fetching users. page=%d, size=%d", page, size);

                PanacheQuery<User> query = userRepository.findAll();

                query.page(Page.of(page, size));

                List<User> users = query.list();

                long totalElements = query.count();

                int totalPages = (int) Math.ceil((double) totalElements / size);

                List<UserResponse> responses = users.stream()
                                .map(user -> mapper.toResponse(user))
                                .toList();

                LOG.infof("Users fetched successfully. page=%d, size=%d, returned=%d, total=%d", page, size,
                                responses.size(), totalElements);

                return new PageResponse<>(responses, page, size, totalElements, totalPages);
        }

        @Transactional
        public UserResponse updateUser(Long id, UpdateUserRequest request) {

                LOG.infof("Starting user update. id=%d", id);

                User user = userRepository.findByIdOptional(id).orElseThrow(() -> {
                        LOG.warnf("User update failed. User not found. id=%d", id);
                        return new UserNotFoundException("User not found with id: " + id);
                });

                userRepository.findByUsername(request.getUsername())
                                .filter(existingUser -> !existingUser.getId().equals(id))
                                .ifPresent(existingUser -> {
                                        LOG.warnf("User update failed. Username already exists. username=%s",
                                                        request.getUsername());

                                        throw new UserAlreadyExistsException("Username already exists");
                                });

                userRepository.findByEmail(request.getEmail())
                                .filter(existingUser -> !existingUser.getId().equals(id))
                                .ifPresent(existingUser -> {
                                        LOG.warnf("User update failed. Email already exists. email=%s",
                                                        request.getEmail());

                                        throw new UserAlreadyExistsException("Email already exists");
                                });

                if (request.getUsername() != null) {
                        user.setUsername(request.getUsername());
                }

                if (request.getEmail() != null) {
                        user.setEmail(request.getEmail());
                }

                if (request.getRole() != null) {
                        user.setRole(request.getRole());
                }

                LOG.infof("User updated successfully. id=%d", id);

                return mapper.toResponse(user);

        }

}
