package dev.hobie.organization_service.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Department {
  private Long id;
  private String name;
  private List<Employee> employees = new ArrayList<>();
}
