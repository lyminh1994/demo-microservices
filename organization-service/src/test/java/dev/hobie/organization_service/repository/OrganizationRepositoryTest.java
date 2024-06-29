package dev.hobie.organization_service.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dev.hobie.organization_service.model.Organization;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrganizationRepositoryTest {

  private static final OrganizationRepository repository = new OrganizationRepository();

  @Test
  @Order(1)
  void testAddOrganization() {
    var organization = new Organization("Test", "Test Street");
    organization = repository.add(organization);
    Assertions.assertNotNull(organization);
    Assertions.assertEquals(1L, organization.getId());
  }

  @Test
  @Order(2)
  void testFindAll() {
    var organizations = repository.findAll();
    Assertions.assertEquals(1, organizations.size());
    Assertions.assertEquals(1L, organizations.get(0).getId());
  }

  @Test
  @Order(3)
  void testFindById() {
    var organization = repository.findById(1L);
    Assertions.assertNotNull(organization);
    Assertions.assertEquals(1L, organization.getId());
  }
}
