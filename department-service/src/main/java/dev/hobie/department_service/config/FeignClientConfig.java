package dev.hobie.department_service.config;

import feign.RequestInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration(proxyBeanMethods = false)
public class FeignClientConfig {

  @Bean
  RequestInterceptor requestInterceptor(HttpServletRequest httpServletRequest) {
    return requestTemplate -> {
      var authentication = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
      if (StringUtils.isNotBlank(authentication)) {
        requestTemplate.header(HttpHeaders.AUTHORIZATION, authentication);
      }
    };
  }
}
