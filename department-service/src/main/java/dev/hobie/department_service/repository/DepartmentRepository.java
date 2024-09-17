package dev.hobie.department_service.repository;

import java.util.ArrayList;
import java.util.List;

import dev.hobie.department_service.model.Department;

public class DepartmentRepository {

  private final List<Department> departments = new ArrayList<>();

  public Department add(Department department) {
    var newDepartment =
        new Department(
            departments.size() + 1L,
            department.organizationId(),
            department.name(),
            department.employees());
    departments.add(newDepartment);
    return newDepartment;
  }

  public Department findById(Long id) {
    return departments.stream().filter(a -> a.id().equals(id)).findFirst().orElseThrow();
  }

  public List<Department> findAll() {
    return departments;
  }

  public List<Department> findByOrganization(Long organizationId) {
    return departments.stream().filter(a -> a.organizationId().equals(organizationId)).toList();
  }
}
