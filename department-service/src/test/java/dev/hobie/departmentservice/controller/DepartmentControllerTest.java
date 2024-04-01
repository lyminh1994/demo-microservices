package dev.hobie.departmentservice.controller;

import dev.hobie.departmentservice.client.EmployeeClient;
import dev.hobie.departmentservice.model.Department;
import dev.hobie.departmentservice.model.Employee;
import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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
class DepartmentControllerTest {

  @Autowired TestRestTemplate restTemplate;
  @MockBean EmployeeClient employeeClient;

  @Test
  void findAll() {
    var departments = restTemplate.getForObject("/", Department[].class);
    Assertions.assertTrue(departments.length > 0);
  }

  @Test
  void findById() {
    var department = restTemplate.getForObject("/{id}", Department.class, 1L);
    Assertions.assertNotNull(department);
    Assertions.assertNotNull(department.getId());
    Assertions.assertNotNull(department.getName());
    Assertions.assertEquals(1L, department.getId());
  }

  @Test
  void findByOrganization() {
    var departments =
        restTemplate.getForObject("/organization/{organizationId}", Department[].class, 1L);
    Assertions.assertTrue(departments.length > 0);
  }

  @Test
  void findByOrganizationWithEmployees() {
    Mockito.when(employeeClient.findByDepartment(Mockito.anyLong()))
        .thenReturn(Instancio.ofList(Employee.class).create());
    var departments =
        restTemplate.getForObject(
            "/organization/{organizationId}/with-employees", Department[].class, 1L);
    Assertions.assertTrue(departments.length > 0);
  }

  @Test
  void add() {
    var department = Instancio.create(Department.class);
    department = restTemplate.postForObject("/", department, Department.class);
    Assertions.assertNotNull(department);
    Assertions.assertNotNull(department.getId());
    Assertions.assertNotNull(department.getName());
  }
}
