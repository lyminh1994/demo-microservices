package dev.hobie.department_service.repository;

import java.util.ArrayList;
import java.util.List;

import dev.hobie.department_service.model.Department;

public class DepartmentRepository {

  private final List<Department> departments = new ArrayList<>();

  public Department add(Department department) {
    department.setId((long) (departments.size() + 1));
    departments.add(department);
    return department;
  }

  public Department findById(Long id) {
    return departments.stream().filter(a -> a.getId().equals(id)).findFirst().orElseThrow();
  }

  public List<Department> findAll() {
    return departments;
  }

  public List<Department> findByOrganization(Long organizationId) {
    return departments.stream().filter(a -> a.getOrganizationId().equals(organizationId)).toList();
  }
}
