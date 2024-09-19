package dev.hobie.organization_service.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.AbstractOAuth2TokenAuthenticationToken;

@Configuration(proxyBeanMethods = false)
public class FeignClientConfig {

  @Bean
  RequestInterceptor requestInterceptor() {
    return requestTemplate -> {
      var authentication =
          (AbstractOAuth2TokenAuthenticationToken<?>)
              SecurityContextHolder.getContext().getAuthentication();
      if (authentication != null) {
        var token = authentication.getToken().getTokenValue();
        requestTemplate.header(HttpHeaders.AUTHORIZATION, "Bearer " + token);
      }
    };
  }
}
