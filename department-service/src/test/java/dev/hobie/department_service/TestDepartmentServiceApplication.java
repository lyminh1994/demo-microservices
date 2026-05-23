package dev.hobie.department_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
class TestDepartmentServiceApplication {

  @Bean
  @ServiceConnection
  PostgreSQLContainer<?> postgresContainer() {
    return new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"));
  }

  @Bean
  @ServiceConnection(name = "openzipkin/zipkin")
  GenericContainer<?> zipkinContainer() {
    try (var container =
        new GenericContainer<>(DockerImageName.parse("openzipkin/zipkin:latest"))) {
      return container.withExposedPorts(9411);
    }
  }

  static void main(String[] args) {
    SpringApplication.from(DepartmentServiceApplication::main)
        .with(TestDepartmentServiceApplication.class)
        .run(args);
  }
}
