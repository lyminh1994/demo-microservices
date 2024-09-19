package dev.hobie.employee_service.controller;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CallMeControllerTest {

  @Autowired private MockMvc mockMvc;

  @Test
  void whenLackingScopeThenForbidden() throws Exception {
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.get("/call-me/ping")
                .with(SecurityMockMvcRequestPostProcessors.jwt()))
        .andExpect(MockMvcResultMatchers.status().isForbidden());
  }

  @Test
  void whenHavingScopeThenOk() throws Exception {
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.get("/call-me/ping")
                .with(
                    SecurityMockMvcRequestPostProcessors.jwt()
                        .authorities(List.of(new SimpleGrantedAuthority("SCOPE_TEST")))))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }
}
