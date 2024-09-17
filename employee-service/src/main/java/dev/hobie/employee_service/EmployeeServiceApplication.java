package dev.hobie.employee_service;

import dev.hobie.employee_service.model.Employee;
import dev.hobie.employee_service.repository.EmployeeRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
    info =
        @Info(
            title = "Employee API",
            version = "1.0",
            description = "Documentation Employee API v1.0"))
public class EmployeeServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(EmployeeServiceApplication.class, args);
  }

  @Bean
  EmployeeRepository repository() {
    var repository = new EmployeeRepository();
    repository.add(new Employee(1L, 1L, "John Smith", 34, "Analyst"));
    repository.add(new Employee(1L, 1L, "Darren Hamilton", 37, "Manager"));
    repository.add(new Employee(1L, 1L, "Tom Scott", 26, "Tester"));
    repository.add(new Employee(1L, 2L, "Anna London", 39, "Analyst"));
    repository.add(new Employee(1L, 2L, "Patrick Dempsey", 27, "Tester"));
    repository.add(new Employee(2L, 3L, "Kevin Price", 38, "Developer"));
    repository.add(new Employee(2L, 3L, "Ian Scott", 34, "Designer"));
    repository.add(new Employee(2L, 3L, "Andrew Campton", 30, "Manager"));
    repository.add(new Employee(2L, 4L, "Steve Franklin", 25, "Developer"));
    repository.add(new Employee(2L, 4L, "Elisabeth Smith", 30, "QA"));
    return repository;
  }
}
