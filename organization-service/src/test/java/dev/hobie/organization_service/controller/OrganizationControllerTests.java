package dev.hobie.organization_service.controller;

import static org.junit.jupiter.api.Assertions.*;

import dev.hobie.organization_service.model.Organization;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = {
      "spring.cloud.discovery.enabled=false",
      "spring.cloud.config.discovery.enabled=false"
    })
class OrganizationControllerTests {

  @Autowired TestRestTemplate restTemplate;

  @Test
  void findAll() {
    var o = restTemplate.getForObject("/", Organization[].class);
    assertTrue(o.length > 0);
  }

  @Test
  void findById() {
    var o = restTemplate.getForObject("/{id}", Organization.class, 1);
    assertNotNull(o);
    assertNotNull(o.id());
    assertEquals(1, o.id());
  }

  @Test
  void add() {
    var o = new Organization("Test", "Test1");
    o = restTemplate.postForObject("/", o, Organization.class);
    assertNotNull(o);
    assertNotNull(o.id());
    assertEquals(3, o.id());
  }
}
