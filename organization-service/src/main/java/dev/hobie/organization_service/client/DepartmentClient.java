package dev.hobie.organization_service.client;

import dev.hobie.organization_service.model.Department;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface DepartmentClient {

  @GetExchange("/organization/{organizationId}")
  List<Department> findByOrganization(@PathVariable Long organizationId);

  @GetExchange("/organization/{organizationId}/with-employees")
  List<Department> findByOrganizationWithEmployees(@PathVariable Long organizationId);
}
