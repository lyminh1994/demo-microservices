package dev.hobie.employee_service.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import dev.hobie.employee_service.model.Employee;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeRepositoryTest {

  private static final EmployeeRepository repository = new EmployeeRepository();

  @Test
  @Order(1)
  void testAddEmployee() {
    var employee = new Employee(1L, 1L, "Test Test", 100, "Test");
    employee = repository.add(employee);
    assertNotNull(employee, "Employee is null.");
    assertEquals(1L, employee.id(), "Employee bad id.");
  }

  @Test
  @Order(2)
  void testFindAll() {
    var employees = repository.findAll();
    assertEquals(1, employees.size(), "Employees size is wrong.");
    assertEquals(1L, employees.getFirst().id(), "Employee bad id.");
  }

  @Test
  @Order(3)
  void testFindByDepartment() {
    var employees = repository.findByDepartment(1L);
    assertEquals(1, employees.size(), "Employees size is wrong.");
    assertEquals(1L, employees.getFirst().id(), "Employee bad id.");
  }

  @Test
  @Order(4)
  void testFindByOrganization() {
    var employees = repository.findByOrganization(1L);
    assertEquals(1, employees.size(), "Employees size is wrong.");
    assertEquals(1L, employees.getFirst().id(), "Employee bad id.");
  }

  @Test
  @Order(5)
  void testFindById() {
    var employee = repository.findById(1L);
    assertNotNull(employee, "Employee not found.");
    assertEquals(1L, employee.id(), "Employee bad id.");
  }
}
