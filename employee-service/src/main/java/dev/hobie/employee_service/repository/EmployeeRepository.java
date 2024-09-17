package dev.hobie.employee_service.repository;

import dev.hobie.employee_service.model.Employee;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {

  private final List<Employee> employees = new ArrayList<>();

  public Employee add(Employee employee) {
    var newEmployee =
        new Employee(
            employees.size() + 1L,
            employee.organizationId(),
            employee.departmentId(),
            employee.name(),
            employee.age(),
            employee.position());
    employees.add(newEmployee);
    return newEmployee;
  }

  public Employee findById(Long id) {
    return employees.stream().filter(a -> a.id().equals(id)).findFirst().orElseThrow();
  }

  public List<Employee> findAll() {
    return employees;
  }

  public List<Employee> findByDepartment(Long departmentId) {
    return employees.stream().filter(a -> a.departmentId().equals(departmentId)).toList();
  }

  public List<Employee> findByOrganization(Long organizationId) {
    return employees.stream().filter(a -> a.organizationId().equals(organizationId)).toList();
  }
}
