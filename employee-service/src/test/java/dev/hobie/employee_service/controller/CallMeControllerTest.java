package dev.hobie.employee_service.controller;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = {
      "spring.cloud.config.enabled=false",
      "spring.security.oauth2.resourceserver.jwt.jwk-set-uri=http://localhost:8080/realms/demo/protocol/openid-connect/certs",
      "eureka.client.enabled=false"
    })
@EnableAutoConfiguration(
    exclude = {
      DataSourceAutoConfiguration.class,
      DataSourceTransactionManagerAutoConfiguration.class,
      HibernateJpaAutoConfiguration.class
    })
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
