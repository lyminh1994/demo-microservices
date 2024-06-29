package dev.hobie.department_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import dev.hobie.department_service.model.Department;
import dev.hobie.department_service.repository.DepartmentRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@EnableFeignClients
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Department API", version = "1.0", description = "Documentation Department API v1.0"))
public class DepartmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepartmentServiceApplication.class, args);
	}

	@Bean
	public DepartmentRepository repository() {
		DepartmentRepository repository = new DepartmentRepository();
		repository.add(new Department(1L, "Development"));
		repository.add(new Department(1L, "Operations"));
		repository.add(new Department(2L, "Development"));
		repository.add(new Department(2L, "Operations"));
		return repository;
	}
}
