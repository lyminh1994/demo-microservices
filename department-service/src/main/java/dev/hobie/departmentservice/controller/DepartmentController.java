package dev.hobie.departmentservice.controller;

import dev.hobie.departmentservice.client.EmployeeClient;
import dev.hobie.departmentservice.model.Department;
import dev.hobie.departmentservice.repository.DepartmentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DepartmentController {

  private final DepartmentRepository repository;
  private final EmployeeClient employeeClient;

  @PostMapping("/")
  public Department add(@RequestBody Department department) {
    log.info("Department add: {}", department);
    return repository.add(department);
  }

  @GetMapping("/{id}")
  public Department findById(@PathVariable("id") Long id) {
    log.info("Department find: id={}", id);
    return repository.findById(id);
  }

  @GetMapping("/")
  public List<Department> findAll() {
    log.info("Department find");
    return repository.findAll();
  }

  @GetMapping("/organization/{organizationId}")
  public List<Department> findByOrganization(@PathVariable("organizationId") Long organizationId) {
    log.info("Department find: organizationId={}", organizationId);
    return repository.findByOrganization(organizationId);
  }

  @GetMapping("/organization/{organizationId}/with-employees")
  public List<Department> findByOrganizationWithEmployees(
      @PathVariable("organizationId") Long organizationId) {
    log.info("Department find: organizationId={}", organizationId);
    var departments = repository.findByOrganization(organizationId);
    departments.forEach(d -> d.setEmployees(employeeClient.findByDepartment(d.getId())));
    return departments;
  }
}
