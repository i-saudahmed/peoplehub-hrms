package org.saud.peoplehub.mapper;

import org.saud.peoplehub.dto.response.EmployeeResponse;
import org.saud.peoplehub.entity.Employee;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmployeeMapper {

    public EmployeeResponse toEmpResponse(Employee employee) {

        EmployeeResponse response = new EmployeeResponse();

        response.setId(employee.getId());
        response.setFirstName(employee.getFirstName());
        response.setLastName(employee.getLastName());
        response.setEmail(employee.getEmail());
        response.setPhone(employee.getPhone());
        response.setDesignation(employee.getDesignation());
        response.setEmploymentType(employee.getEmploymentType());
        response.setJoinDate(employee.getJoinDate());
        response.setStatus(employee.getStatus());

        // Department details
        // if (employee.getDepartment() != null) {
        // response.setDepartmentId(employee.getDepartment().getId());
        // response.setDepartmentName(employee.getDepartment().getName());
        // }

        // Manager details
        if (employee.getManager() != null) {
            response.setManagerId(employee.getManager().getId());

            response.setManagerName(
                    employee.getManager().getFirstName() + " "
                            + employee.getManager().getLastName());
        }

        response.setCreatedAt(employee.getCreatedAt());
        response.setUpdatedAt(employee.getUpdatedAt());

        return response;
    }
}
