package org.saud.peoplehub.repository;

import java.util.Optional;

import org.saud.peoplehub.entity.User;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    public Optional<User> findByEmail(String email) {
        return find("email", email).firstResultOptional();
    }
    public Optional<User> findByUsername(String username) {
        return find("username", username).firstResultOptional();
    }

}
