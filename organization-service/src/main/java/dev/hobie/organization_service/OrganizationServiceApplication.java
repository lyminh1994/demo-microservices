package dev.hobie.organization_service;

import dev.hobie.organization_service.model.Organization;
import dev.hobie.organization_service.repository.OrganizationRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
@SpringBootApplication
@OpenAPIDefinition(
    info =
        @Info(
            title = "Organization API",
            version = "1.0",
            description = "Documentation Organization API v1.0"))
public class OrganizationServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(OrganizationServiceApplication.class, args);
  }

  @Bean
  OrganizationRepository repository() {
    OrganizationRepository repository = new OrganizationRepository();
    repository.add(new Organization("Microsoft", "Redmond, Washington, USA"));
    repository.add(new Organization("Oracle", "Redwood City, California, USA"));
    return repository;
  }
}
