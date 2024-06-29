package dev.hobie.department_service.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dev.hobie.department_service.model.Department;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DepartmentRepositoryTest {

  private static final DepartmentRepository repository = new DepartmentRepository();

  @Test
  @Order(1)
  void testAddDepartment() {
    Department department = new Department(1L, "Test");
    department = repository.add(department);
    Assertions.assertNotNull(department);
    Assertions.assertEquals(1L, department.getId());
  }

  @Test
  @Order(2)
  void testFindAll() {
    List<Department> departments = repository.findAll();
    Assertions.assertEquals(1, departments.size());
    Assertions.assertEquals(1L, departments.get(0).getId());
  }

  @Test
  @Order(3)
  void testFindByOrganization() {
    List<Department> departments = repository.findByOrganization(1L);
    Assertions.assertEquals(1, departments.size());
    Assertions.assertEquals(1L, departments.get(0).getId());
  }

  @Test
  @Order(4)
  void testFindById() {
    Department department = repository.findById(1L);
    Assertions.assertNotNull(department);
    Assertions.assertEquals(1L, department.getId());
  }
}
