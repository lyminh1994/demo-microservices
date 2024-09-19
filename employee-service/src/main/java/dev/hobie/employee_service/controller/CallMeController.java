package dev.hobie.employee_service.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/call-me")
public class CallMeController {

  @PreAuthorize("hasAuthority('SCOPE_TEST')")
  @GetMapping("/ping")
  public String ping() {
    var context = SecurityContextHolder.getContext();
    var authentication = context.getAuthentication();
    return "Scopes: " + authentication.getAuthorities();
  }
}
