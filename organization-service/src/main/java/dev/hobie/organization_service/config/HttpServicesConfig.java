package dev.hobie.organization_service.config;

import dev.hobie.organization_service.client.DepartmentClient;
import dev.hobie.organization_service.client.EmployeeClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.AbstractOAuth2TokenAuthenticationToken;
import org.springframework.web.client.support.RestClientHttpServiceGroupConfigurer;
import org.springframework.web.service.registry.ImportHttpServices;

@Configuration(proxyBeanMethods = false)
@ImportHttpServices(group = "department-service", types = DepartmentClient.class)
@ImportHttpServices(group = "employee-service", types = EmployeeClient.class)
public class HttpServicesConfig {

  @Bean
  RestClientHttpServiceGroupConfigurer groupConfigurer() {
    return groups -> {
      groups.forEachClient(
          (_, clientBuilder) ->
              clientBuilder.requestInterceptor(
                  (request, body, execution) -> {
                    var authentication = SecurityContextHolder.getContext().getAuthentication();
                    if (authentication
                        instanceof AbstractOAuth2TokenAuthenticationToken<?> tokenAuthentication) {
                      request
                          .getHeaders()
                          .setBearerAuth(tokenAuthentication.getToken().getTokenValue());
                    }
                    return execution.execute(request, body);
                  }));
    };
  }
}
