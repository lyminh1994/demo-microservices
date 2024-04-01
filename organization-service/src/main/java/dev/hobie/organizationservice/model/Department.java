package dev.hobie.organizationservice.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Department {
  private Long id;
  private String name;
  private List<Employee> employees = new ArrayList<>();
}
