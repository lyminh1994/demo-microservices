package dev.hobie.department_service.controller;

import dev.hobie.department_service.client.EmployeeClient;
import dev.hobie.department_service.model.Department;
import dev.hobie.department_service.repository.DepartmentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DepartmentController {

  private final DepartmentRepository repository;
  private final EmployeeClient employeeClient;

  @PostMapping("/")
  public Department add(@RequestBody Department department) {
    return repository.add(department);
  }

  @GetMapping("/{id}")
  public Department findById(@PathVariable("id") Long id) {
    return repository.findById(id);
  }

  @GetMapping("/")
  public List<Department> findAll() {
    return repository.findAll();
  }

  @GetMapping("/organization/{organizationId}")
  public List<Department> findByOrganization(@PathVariable("organizationId") Long organizationId) {
    return repository.findByOrganization(organizationId);
  }

  @GetMapping("/organization/{organizationId}/with-employees")
  public List<Department> findByOrganizationWithEmployees(
      @PathVariable("organizationId") Long organizationId) {
    var departments = repository.findByOrganization(organizationId);
    return departments.stream()
        .map(
            department ->
                new Department(
                    department.id(),
                    department.organizationId(),
                    department.name(),
                    employeeClient.findByDepartment(department.id())))
        .toList();
  }
}
