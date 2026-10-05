package org.saud.peoplehub.dto.response;

import java.time.LocalDateTime;

import org.saud.peoplehub.entity.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    private Long id;
    private String username;
    private String email;
    private Role role;
    private boolean passwordChanged;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}