package dev.hobie.organization_service.model;

import java.util.List;

public record Department(Long id, String name, List<Employee> employees) {}
