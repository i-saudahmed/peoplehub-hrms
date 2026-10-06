package org.saud.peoplehub.dto.request.user;


import jakarta.validation.constraints.NotNull;

public class UpdateUserStatusRequest {

    @NotNull(message = "Active status is required")
    private Boolean active;

    public UpdateUserStatusRequest() {
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "UpdateUserStatusRequest [active=" + active + "]";
    }

    
}
