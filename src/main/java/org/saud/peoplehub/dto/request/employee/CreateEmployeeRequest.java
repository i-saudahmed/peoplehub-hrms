package org.saud.peoplehub.dto.request.employee;

import java.time.LocalDate;

import org.saud.peoplehub.entity.Employee.EmploymentType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateEmployeeRequest {

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

    private EmploymentType employmentType;

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

    public EmploymentType getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(EmploymentType employmentType) {
        this.employmentType = employmentType;
    }

    public CreateEmployeeRequest() {
    }

    public CreateEmployeeRequest(@NotBlank(message = "First name is required") String firstName,
            @NotBlank(message = "Last name is required") String lastName,
            @NotBlank(message = "Email is required") @Email(message = "Invalid email format") String email,
            String phone, @NotNull(message = "Join date is required") LocalDate joinDate, Long departmentId,
            Long managerId, String designation, EmploymentType employmentType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.joinDate = joinDate;
        this.departmentId = departmentId;
        this.managerId = managerId;
        this.designation = designation;
        this.employmentType = employmentType;
    }

    @Override
    public String toString() {
        return "CreateEmployeeRequest [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
                + ", phone=" + phone + ", joinDate=" + joinDate + ", departmentId=" + departmentId + ", managerId="
                + managerId + ", designation=" + designation + ", employmentType=" + employmentType + "]";
    }

}