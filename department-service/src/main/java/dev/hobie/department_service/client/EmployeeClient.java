package dev.hobie.department_service.client;

import dev.hobie.department_service.model.Employee;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface EmployeeClient {

  @GetExchange("/department/{departmentId}")
  List<Employee> findByDepartment(@PathVariable Long departmentId);
}
