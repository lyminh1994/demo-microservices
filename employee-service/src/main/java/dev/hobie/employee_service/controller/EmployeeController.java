package dev.hobie.employee_service.controller;

import dev.hobie.employee_service.model.Employee;
import dev.hobie.employee_service.repository.EmployeeRepository;
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
public class EmployeeController {

  private final EmployeeRepository repository;

  @PostMapping("/")
  public Employee add(@RequestBody Employee employee) {
    log.info("Employee add: {}", employee);
    return repository.add(employee);
  }

  @GetMapping("/{id}")
  public Employee findById(@PathVariable("id") Long id) {
    log.info("Employee find: id={}", id);
    return repository.findById(id);
  }

  @GetMapping("/")
  public List<Employee> findAll() {
    log.info("Employee find all");
    return repository.findAll();
  }

  @GetMapping("/department/{departmentId}")
  public List<Employee> findByDepartment(@PathVariable("departmentId") Long departmentId) {
    log.info("Employee find: departmentId={}", departmentId);
    return repository.findByDepartment(departmentId);
  }

  @GetMapping("/organization/{organizationId}")
  public List<Employee> findByOrganization(@PathVariable("organizationId") Long organizationId) {
    log.info("Employee find: organizationId={}", organizationId);
    return repository.findByOrganization(organizationId);
  }
}
