package dev.hobie.department_service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import dev.hobie.department_service.client.EmployeeClient;
import dev.hobie.department_service.model.Department;
import dev.hobie.department_service.model.Employee;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = {
      "spring.cloud.discovery.enabled=false",
      "spring.cloud.config.discovery.enabled=false"
    })
class DepartmentServiceApplicationTests {

  @Autowired TestRestTemplate restTemplate;
  @MockBean EmployeeClient employeeClient;

  @Test
  void findAll() {
    var departments = restTemplate.getForObject("/", Department[].class);
    assertTrue(departments.length > 0);
  }

  @Test
  void findById() {
    var department = restTemplate.getForObject("/{id}", Department.class, 1L);
    assertNotNull(department);
    assertNotNull(department.id());
    assertNotNull(department.name());
    assertEquals(1L, department.id());
  }

  @Test
  void findByOrganization() {
    var departments =
        restTemplate.getForObject("/organization/{organizationId}", Department[].class, 1L);
    assertTrue(departments.length > 0);
  }

  @Test
  void findByOrganizationWithEmployees() {
    when(employeeClient.findByDepartment(anyLong()))
        .thenReturn(Instancio.ofList(Employee.class).create());
    var departments =
        restTemplate.getForObject(
            "/organization/{organizationId}/with-employees", Department[].class, 1L);
    assertTrue(departments.length > 0);
  }

  @Test
  void add() {
    var department = Instancio.create(Department.class);
    department = restTemplate.postForObject("/", department, Department.class);
    assertNotNull(department);
    assertNotNull(department.id());
    assertNotNull(department.name());
  }
}
