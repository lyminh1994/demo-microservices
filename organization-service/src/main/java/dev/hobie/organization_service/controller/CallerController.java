package dev.hobie.organization_service.controller;

import dev.hobie.organization_service.client.EmployeeClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/caller")
public class CallerController {

  private final EmployeeClient employeeClient;

  @PreAuthorize("hasAuthority('SCOPE_TEST')")
  @GetMapping("/ping")
  public String ping() {
    return "Call Me " + employeeClient.callMePing();
  }
}
