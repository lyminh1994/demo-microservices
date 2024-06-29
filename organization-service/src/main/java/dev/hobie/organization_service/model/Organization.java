package dev.hobie.organization_service.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Organization {
  private Long id;
  private String name;
  private String address;
  private List<Department> departments = new ArrayList<>();
  private List<Employee> employees = new ArrayList<>();

  public Organization(String name, String address) {
    this.name = name;
    this.address = address;
  }
}
