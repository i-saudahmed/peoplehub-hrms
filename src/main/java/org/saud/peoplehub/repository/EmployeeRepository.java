package org.saud.peoplehub.repository;

import java.util.List;
import java.util.Optional;

import org.saud.peoplehub.entity.Employee;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmployeeRepository implements PanacheRepository<Employee> {

    public Optional<Employee> findByEmployeeCode(String employeeCode) {
        return find("employeeCode", employeeCode).firstResultOptional();
    }

    public boolean existsByEmployeeCode(String employeeCode) {
        return count("employeeCode", employeeCode) > 0;
    }

    public List<Employee> findByStatus(Employee.Status status) {
        return list("status", Sort.by("firstName"), status);
    }

    public List<Employee> findByDepartmentId(Long departmentId) {
        return list("department.id", Sort.by("firstName"), departmentId);
    }

    public List<Employee> findByManagerId(Long managerId) {
        return list("manager.id", Sort.by("firstName"), managerId);
    }

    public Optional<Employee> findByUserId(Long userId) {
        return find("user.id", userId).firstResultOptional();
    }
}