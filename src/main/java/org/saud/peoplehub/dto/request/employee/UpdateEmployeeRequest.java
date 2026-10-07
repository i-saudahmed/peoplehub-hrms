package org.saud.peoplehub.dto.request.employee;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateEmployeeRequest {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    private String phone;

    @NotNull(message = "Join date is required")
    private LocalDate joinDate;

    private Long departmentId;

    private Long managerId;

    private String designation;

    private String employmentType;

    public UpdateEmployeeRequest() {
    }

    public UpdateEmployeeRequest(Long departmentId, String designation, String email, String employmentType, String firstName, LocalDate joinDate, String lastName, Long managerId, String phone) {
        this.departmentId = departmentId;
        this.designation = designation;
        this.email = email;
        this.employmentType = employmentType;
        this.firstName = firstName;
        this.joinDate = joinDate;
        this.lastName = lastName;
        this.managerId = managerId;
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UpdateEmployeeRequest{");
        sb.append("firstName=").append(firstName);
        sb.append(", lastName=").append(lastName);
        sb.append(", email=").append(email);
        sb.append(", phone=").append(phone);
        sb.append(", joinDate=").append(joinDate);
        sb.append(", departmentId=").append(departmentId);
        sb.append(", managerId=").append(managerId);
        sb.append(", designation=").append(designation);
        sb.append(", employmentType=").append(employmentType);
        sb.append('}');
        return sb.toString();
    }



}