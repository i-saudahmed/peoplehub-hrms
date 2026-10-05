package org.saud.peoplehub.dto.request.user;

import org.saud.peoplehub.entity.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {
    
    private String username;
    private String email;
    private Role role;
}
