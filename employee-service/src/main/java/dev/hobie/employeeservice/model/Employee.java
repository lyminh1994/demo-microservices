package dev.hobie.employeeservice.model;

import lombok.Data;

@Data
public class Employee {
  private Long id;
  private Long organizationId;
  private Long departmentId;
  private String name;
  private int age;
  private String position;

  public Employee(Long organizationId, Long departmentId, String name, int age, String position) {
    this.organizationId = organizationId;
    this.departmentId = departmentId;
    this.name = name;
    this.age = age;
    this.position = position;
  }
}
