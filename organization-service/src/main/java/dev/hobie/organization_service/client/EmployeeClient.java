package dev.hobie.organization_service.client;

import dev.hobie.organization_service.model.Employee;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "employee-service")
public interface EmployeeClient {

  @GetMapping("/organization/{organizationId}")
  List<Employee> findByOrganization(@PathVariable("organizationId") Long organizationId);

  @GetMapping("/call-me/ping")
  String callMePing();
}
