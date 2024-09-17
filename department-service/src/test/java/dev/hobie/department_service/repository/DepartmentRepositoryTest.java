package dev.hobie.department_service.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import dev.hobie.department_service.model.Department;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DepartmentRepositoryTest {

  private static final DepartmentRepository repository = new DepartmentRepository();

  @Test
  @Order(1)
  void testAddDepartment() {
    var department = new Department(1L, "Test");
    department = repository.add(department);
    assertNotNull(department, "Department is null.");
    assertEquals(1L, department.id(), "Department bad id.");
  }

  @Test
  @Order(2)
  void testFindAll() {
    var departments = repository.findAll();
    assertEquals(1, departments.size(), "Departments size is wrong.");
    assertEquals(1L, departments.getFirst().id(), "Department bad id.");
  }

  @Test
  @Order(3)
  void testFindByOrganization() {
    var departments = repository.findByOrganization(1L);
    assertEquals(1, departments.size(), "Departments size is wrong.");
    assertEquals(1L, departments.getFirst().id(), "Department bad id.");
  }

  @Test
  @Order(4)
  void testFindById() {
    var department = repository.findById(1L);
    assertNotNull(department, "Department not found.");
    assertEquals(1L, department.id(), "Department bad id.");
  }
}
