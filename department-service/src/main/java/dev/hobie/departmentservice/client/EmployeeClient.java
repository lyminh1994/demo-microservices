package dev.hobie.departmentservice.client;

import dev.hobie.departmentservice.model.Employee;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "employee-service")
public interface EmployeeClient {

  @GetMapping("/department/{departmentId}")
  List<Employee> findByDepartment(@PathVariable("departmentId") Long departmentId);
}
