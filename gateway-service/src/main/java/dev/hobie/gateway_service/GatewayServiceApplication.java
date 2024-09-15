package dev.hobie.gateway_service;

import java.util.HashSet;
import java.util.Set;
import org.springdoc.core.properties.AbstractSwaggerUiConfigProperties;
import org.springdoc.core.properties.AbstractSwaggerUiConfigProperties.SwaggerUrl;
import org.springdoc.core.properties.SwaggerUiConfigParameters;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

@EnableCaching
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(GatewayServiceApplication.class, args);
  }

  @Bean
  @Lazy(false)
  public Set<SwaggerUrl> apis(
      RouteDefinitionLocator locator, SwaggerUiConfigParameters swaggerUiConfigParameters) {
    Set<AbstractSwaggerUiConfigProperties.SwaggerUrl> urls = new HashSet<>();
    var definitions = locator.getRouteDefinitions().collectList().block();
    assert definitions != null;
    definitions.stream()
        .filter(routeDefinition -> routeDefinition.getId().matches(".*-service"))
        .forEach(
            routeDefinition -> {
              var name = routeDefinition.getId().replace("-service", "");
              var swaggerUrl =
                  new AbstractSwaggerUiConfigProperties.SwaggerUrl(name, "/" + name, null);
              urls.add(swaggerUrl);
            });
    swaggerUiConfigParameters.setUrls(urls);
    return urls;
  }
}
