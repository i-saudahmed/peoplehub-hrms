package org.saud.peoplehub.dto.request.user;

import org.saud.peoplehub.entity.Role;

import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public class CreateUserRequest {
    
    @NotBlank
    private String username;
    
    @NotBlank
    @Email 
    private String email;
    
    @NotNull 
    private Role role;

    

    public String getUsername() {
        return username;
    }



    public void setUsername(String username) {
        this.username = username;
    }



    public String getEmail() {
        return email;
    }



    public void setEmail(String email) {
        this.email = email;
    }



    public Role getRole() {
        return role;
    }



    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CreateUserRequest{");
        sb.append("username=").append(username);
        sb.append(", email=").append(email);
        sb.append(", role=").append(role);
        sb.append('}');
        return sb.toString();
    }


}
