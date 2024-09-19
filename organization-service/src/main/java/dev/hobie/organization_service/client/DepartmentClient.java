package dev.hobie.organization_service.client;

import dev.hobie.organization_service.model.Department;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "department-service")
public interface DepartmentClient {

  @GetMapping("/organization/{organizationId}")
  List<Department> findByOrganization(@PathVariable("organizationId") Long organizationId);

  @GetMapping("/organization/{organizationId}/with-employees")
  List<Department> findByOrganizationWithEmployees(
      @PathVariable("organizationId") Long organizationId);
}
