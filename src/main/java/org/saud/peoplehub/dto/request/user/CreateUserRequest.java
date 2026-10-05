package org.saud.peoplehub.dto.request.user;

import org.saud.peoplehub.entity.Role;

import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {
    
    @NotBlank
    private String username;
    
    @NotBlank
    @Email 
    private String email;
    
    @NotNull 
    private Role role;
}
