package dev.hobie.organization_service.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import dev.hobie.organization_service.model.Organization;
import java.util.List;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrganizationRepositoryTest {

  private static final OrganizationRepository repository = new OrganizationRepository();

  @Test
  @Order(1)
  void testAddOrganization() {
    Organization organization = new Organization("Test", "Test Street");
    organization = repository.add(organization);
    assertNotNull(organization, "Organization is null.");
    assertEquals(1L, organization.id(), "Organization bad id.");
  }

  @Test
  @Order(2)
  void testFindAll() {
    List<Organization> organizations = repository.findAll();
    assertEquals(1, organizations.size(), "Organizations size is wrong.");
    assertEquals(1L, organizations.getFirst().id(), "Organization bad id.");
  }

  @Test
  @Order(3)
  void testFindById() {
    Organization organization = repository.findById(1L);
    assertNotNull(organization, "Organization not found.");
    assertEquals(1L, organization.id(), "Organization bad id.");
  }
}
