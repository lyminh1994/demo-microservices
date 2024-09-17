package dev.hobie.department_service.model;

import java.util.List;

public record Department(Long id, Long organizationId, String name, List<Employee> employees) {

  public Department(Long organizationId, String name) {
    this(null, organizationId, name, null);
  }
}
