package dev.hobie.departmentservice.model;

import lombok.Data;

@Data
public class Employee {
  private Long id;
  private String name;
  private int age;
  private String position;

  public Employee(String name, int age, String position) {
    this.name = name;
    this.age = age;
    this.position = position;
  }
}
