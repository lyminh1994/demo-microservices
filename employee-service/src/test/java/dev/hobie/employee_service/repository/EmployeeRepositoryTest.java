package dev.hobie.employee_service.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dev.hobie.employee_service.model.Employee;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeRepositoryTest {

  private static final EmployeeRepository repository = new EmployeeRepository();

  @Test
  @Order(1)
  void testAddEmployee() {
    var employee = new Employee(1L, 1L, "Test Test", 100, "Test");
    employee = repository.add(employee);
    Assertions.assertNotNull(employee);
    Assertions.assertEquals(1L, employee.getId());
  }

  @Test
  @Order(2)
  void testFindAll() {
    var employees = repository.findAll();
    Assertions.assertEquals(1, employees.size());
    Assertions.assertEquals(1L, employees.get(0).getId());
  }

  @Test
  @Order(3)
  void testFindByDepartment() {
    var employees = repository.findByDepartment(1L);
    Assertions.assertEquals(1, employees.size());
    Assertions.assertEquals(1L, employees.get(0).getId());
  }

  @Test
  @Order(4)
  void testFindByOrganization() {
    var employees = repository.findByOrganization(1L);
    Assertions.assertEquals(1, employees.size());
    Assertions.assertEquals(1L, employees.get(0).getId());
  }

  @Test
  @Order(5)
  void testFindById() {
    var employee = repository.findById(1L);
    Assertions.assertNotNull(employee);
    Assertions.assertEquals(1L, employee.getId());
  }
}
