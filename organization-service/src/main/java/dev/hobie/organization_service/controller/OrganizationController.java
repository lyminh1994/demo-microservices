package dev.hobie.organization_service.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.hobie.organization_service.client.DepartmentClient;
import dev.hobie.organization_service.client.EmployeeClient;
import dev.hobie.organization_service.model.Organization;
import dev.hobie.organization_service.repository.OrganizationRepository;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrganizationController {

  private final OrganizationRepository repository;
  private final DepartmentClient departmentClient;
  private final EmployeeClient employeeClient;

  @PostMapping("/")
  public Organization add(@RequestBody Organization organization) {
    log.info("Organization add: {}", organization);
    return repository.add(organization);
  }

  @GetMapping("/")
  public List<Organization> findAll() {
    log.info("Organization find");
    return repository.findAll();
  }

  @GetMapping("/{id}")
  public Organization findById(@PathVariable("id") Long id) {
    log.info("Organization find: id={}", id);
    return repository.findById(id);
  }

  @GetMapping("/{id}/with-departments")
  public Organization findByIdWithDepartments(@PathVariable("id") Long id) {
    log.info("Organization find: id={}", id);
    var organization = repository.findById(id);
    organization.setDepartments(departmentClient.findByOrganization(organization.getId()));
    return organization;
  }

  @GetMapping("/{id}/with-departments-and-employees")
  public Organization findByIdWithDepartmentsAndEmployees(@PathVariable("id") Long id) {
    log.info("Organization find: id={}", id);
    var organization = repository.findById(id);
    organization.setDepartments(
        departmentClient.findByOrganizationWithEmployees(organization.getId()));
    return organization;
  }

  @GetMapping("/{id}/with-employees")
  public Organization findByIdWithEmployees(@PathVariable("id") Long id) {
    log.info("Organization find: id={}", id);
    var organization = repository.findById(id);
    organization.setEmployees(employeeClient.findByOrganization(organization.getId()));
    return organization;
  }
}
