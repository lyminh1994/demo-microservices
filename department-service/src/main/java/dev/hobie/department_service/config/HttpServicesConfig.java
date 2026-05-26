package dev.hobie.department_service.config;

import dev.hobie.department_service.client.EmployeeClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.client.support.RestClientHttpServiceGroupConfigurer;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.service.registry.ImportHttpServices;

@Configuration(proxyBeanMethods = false)
@ImportHttpServices(group = "employee-service", types = EmployeeClient.class)
public class HttpServicesConfig {

  @Bean
  RestClientHttpServiceGroupConfigurer groupConfigurer() {
    return groups ->
        groups
            .filterByName("employee-service")
            .forEachClient(
                (_, clientBuilder) ->
                    clientBuilder.requestInterceptor(
                        (request, body, execution) -> {
                          var requestAttributes = RequestContextHolder.getRequestAttributes();
                          if (requestAttributes
                              instanceof ServletRequestAttributes servletAttributes) {
                            var authorization =
                                servletAttributes.getRequest().getHeader(HttpHeaders.AUTHORIZATION);
                            if (StringUtils.hasText(authorization)) {
                              request.getHeaders().set(HttpHeaders.AUTHORIZATION, authorization);
                            }
                          }
                          return execution.execute(request, body);
                        }));
  }
}
