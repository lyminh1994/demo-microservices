package dev.hobie.organization_service.model;

import java.util.List;

public record Organization(
    Long id, String name, String address, List<Department> departments, List<Employee> employees) {

  public Organization(String name, String address) {
    this(null, name, address, null, null);
  }
}
