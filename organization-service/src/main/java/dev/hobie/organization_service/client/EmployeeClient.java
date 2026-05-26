package dev.hobie.organization_service.client;

import dev.hobie.organization_service.model.Employee;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface EmployeeClient {

  @GetExchange("/organization/{organizationId}")
  List<Employee> findByOrganization(@PathVariable Long organizationId);

  @GetExchange("/call-me/ping")
  String callMePing();
}
