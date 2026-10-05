package org.saud.peoplehub.service;

import org.jboss.logging.Logger;
import org.saud.mapper.UserMapper;
import org.saud.peoplehub.dto.request.user.CreateUserRequest;
import org.saud.peoplehub.dto.response.UserResponse;
import org.saud.peoplehub.entity.User;
import org.saud.peoplehub.repository.UserRepository;
import org.saud.peoplehub.util.PasswordService;

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
            throw new IllegalArgumentException("Email already exists");

        }

        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            LOG.warnf(
                    "User creation failed. Username already exists. username=%s",
                    request.getUsername());
            throw new IllegalArgumentException("UserName already exists");

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

}
