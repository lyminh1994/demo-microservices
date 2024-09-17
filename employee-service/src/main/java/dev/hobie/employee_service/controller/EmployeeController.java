package dev.hobie.employee_service.controller;

import dev.hobie.employee_service.model.Employee;
import dev.hobie.employee_service.repository.EmployeeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

  private final EmployeeRepository repository;

  @PostMapping("/")
  public Employee add(@RequestBody Employee employee) {
    return repository.add(employee);
  }

  @GetMapping("/{id}")
  public Employee findById(@PathVariable("id") Long id) {
    return repository.findById(id);
  }

  @GetMapping("/")
  public List<Employee> findAll() {
    return repository.findAll();
  }

  @GetMapping("/department/{departmentId}")
  public List<Employee> findByDepartment(@PathVariable("departmentId") Long departmentId) {
    return repository.findByDepartment(departmentId);
  }

  @GetMapping("/organization/{organizationId}")
  public List<Employee> findByOrganization(@PathVariable("organizationId") Long organizationId) {
    return repository.findByOrganization(organizationId);
  }
}
