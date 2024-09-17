package dev.hobie.employee_service.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.hobie.employee_service.model.Employee;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = {
      "spring.cloud.discovery.enabled=false",
      "spring.cloud.config.discovery.enabled=false"
    })
class EmployeeControllerTest {

  @Autowired TestRestTemplate restTemplate;

  @Test
  void findAll() {
    var employees = restTemplate.getForObject("/", Employee[].class);
    assertTrue(employees.length > 0);
  }

  @Test
  void findById() {
    var employee = restTemplate.getForObject("/{id}", Employee.class, 1L);
    assertNotNull(employee);
    assertNotNull(employee.id());
    assertNotNull(employee.name());
    assertEquals(1L, employee.id());
  }

  @Test
  void findByOrganization() {
    var employees =
        restTemplate.getForObject("/organization/{organizationId}", Employee[].class, 1L);
    assertTrue(employees.length > 0);
  }

  @Test
  void findByDepartment() {
    var employees = restTemplate.getForObject("/department/{departmentId}", Employee[].class, 1L);
    assertTrue(employees.length > 0);
  }

  @Test
  void add() {
    var employee = Instancio.create(Employee.class);
    employee = restTemplate.postForObject("/", employee, Employee.class);
    assertNotNull(employee);
    assertNotNull(employee.id());
    assertNotNull(employee.name());
  }
}
