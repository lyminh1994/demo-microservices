package dev.hobie.organization_service.repository;

import dev.hobie.organization_service.model.Organization;
import java.util.ArrayList;
import java.util.List;

public class OrganizationRepository {

  private final List<Organization> organizations = new ArrayList<>();

  public Organization add(Organization organization) {
    var newOrganization =
        new Organization(
            organizations.size() + 1L,
            organization.name(),
            organization.address(),
            organization.departments(),
            organization.employees());
    organizations.add(newOrganization);
    return newOrganization;
  }

  public Organization findById(Long id) {
    return organizations.stream().filter(a -> a.id().equals(id)).findFirst().orElseThrow();
  }

  public List<Organization> findAll() {
    return organizations;
  }
}
