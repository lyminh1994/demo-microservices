package dev.hobie.organizationservice.repository;

import dev.hobie.organizationservice.model.Organization;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.util.Assert;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrganizationRepositoryTest {

  private static final OrganizationRepository repository = new OrganizationRepository();

  @Test
  @Order(1)
  void testAddOrganization() {
    var organization = new Organization("Test", "Test Street");
    organization = repository.add(organization);
    Assert.notNull(organization, "Organization is null.");
    Assert.isTrue(organization.getId() == 1L, "Organization bad id.");
  }

  @Test
  @Order(2)
  void testFindAll() {
    var organizations = repository.findAll();
    Assert.isTrue(organizations.size() == 1, "Organizations size is wrong.");
    Assert.isTrue(organizations.get(0).getId() == 1L, "Organization bad id.");
  }

  @Test
  @Order(3)
  void testFindById() {
    var organization = repository.findById(1L);
    Assert.notNull(organization, "Organization not found.");
    Assert.isTrue(organization.getId() == 1L, "Organization bad id.");
  }
}
