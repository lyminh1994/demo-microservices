package dev.hobie.organization_service;

import static org.assertj.core.api.Assertions.assertThat;

import dev.hobie.organization_service.repository.OrganizationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrganizationServiceApplicationTests {

  @Autowired OrganizationRepository organizationRepository;

  @Test
  void contextLoads() {
    assertThat(organizationRepository).isNotNull();
  }
}
