package dev.hobie.employeeservice.repository;

import dev.hobie.employeeservice.model.Employee;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.util.Assert;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeRepositoryTest {

  private static final EmployeeRepository repository = new EmployeeRepository();

  @Test
  @Order(1)
  void testAddEmployee() {
    var employee = new Employee(1L, 1L, "Test Test", 100, "Test");
    employee = repository.add(employee);
    Assert.notNull(employee, "Employee is null.");
    Assert.isTrue(employee.getId() == 1L, "Employee bad id.");
  }

  @Test
  @Order(2)
  void testFindAll() {
    var employees = repository.findAll();
    Assert.isTrue(employees.size() == 1, "Employees size is wrong.");
    Assert.isTrue(employees.get(0).getId() == 1L, "Employee bad id.");
  }

  @Test
  @Order(3)
  void testFindByDepartment() {
    var employees = repository.findByDepartment(1L);
    Assert.isTrue(employees.size() == 1, "Employees size is wrong.");
    Assert.isTrue(employees.get(0).getId() == 1L, "Employee bad id.");
  }

  @Test
  @Order(4)
  void testFindByOrganization() {
    var employees = repository.findByOrganization(1L);
    Assert.isTrue(employees.size() == 1, "Employees size is wrong.");
    Assert.isTrue(employees.get(0).getId() == 1L, "Employee bad id.");
  }

  @Test
  @Order(5)
  void testFindById() {
    var employee = repository.findById(1L);
    Assert.notNull(employee, "Employee not found.");
    Assert.isTrue(employee.getId() == 1L, "Employee bad id.");
  }
}
