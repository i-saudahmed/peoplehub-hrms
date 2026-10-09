package org.saud.peoplehub.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.saud.peoplehub.entity.Employee;
import org.saud.peoplehub.entity.Employee.EmploymentType;

import jakarta.persistence.Column;

public class EmployeeResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String designation;

    private EmploymentType employmentType;

    private LocalDate joinDate;

    private Employee.Status status;

    private Long departmentId;

    private String departmentName;

    private Long managerId;

    private String managerName;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public EmployeeResponse() {
    }

    public EmployeeResponse(LocalDateTime createdAt, Long departmentId, String departmentName, String designation,
            String email, EmploymentType employmentType, String firstName, Long id, LocalDate joinDate, String lastName,
            Long managerId, String managerName, String phone, Employee.Status status, LocalDateTime updatedAt) {
        this.createdAt = createdAt;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.designation = designation;
        this.email = email;
        this.employmentType = employmentType;
        this.firstName = firstName;
        this.id = id;
        this.joinDate = joinDate;
        this.lastName = lastName;
        this.managerId = managerId;
        this.managerName = managerName;
        this.phone = phone;
        this.status = status;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EmployeeResponse{");
        sb.append("id=").append(id);
        sb.append(", firstName=").append(firstName);
        sb.append(", lastName=").append(lastName);
        sb.append(", email=").append(email);
        sb.append(", phone=").append(phone);
        sb.append(", designation=").append(designation);
        sb.append(", employmentType=").append(employmentType);
        sb.append(", joinDate=").append(joinDate);
        sb.append(", status=").append(status);
        sb.append(", departmentId=").append(departmentId);
        sb.append(", departmentName=").append(departmentName);
        sb.append(", managerId=").append(managerId);
        sb.append(", managerName=").append(managerName);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append('}');
        return sb.toString();
    }

    public Employee.Status getStatus() {
        return status;
    }

    public void setStatus(Employee.Status status) {
        this.status = status;
    }

}
