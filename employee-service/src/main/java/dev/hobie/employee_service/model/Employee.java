package dev.hobie.employee_service.model;

public record Employee(
    Long id, Long organizationId, Long departmentId, String name, int age, String position) {

  public Employee(Long organizationId, Long departmentId, String name, int age, String position) {
    this(null, organizationId, departmentId, name, age, position);
  }
}
