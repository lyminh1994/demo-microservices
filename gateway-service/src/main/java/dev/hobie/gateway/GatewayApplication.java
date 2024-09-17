package dev.hobie.gateway;

import java.util.Set;
import java.util.stream.Collectors;
import org.springdoc.core.properties.AbstractSwaggerUiConfigProperties.SwaggerUrl;
import org.springdoc.core.properties.SwaggerUiConfigParameters;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

@EnableCaching
@SpringBootApplication
public class GatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(GatewayApplication.class, args);
  }

  @Bean
  @Lazy(false)
  public Set<SwaggerUrl> apis(
      RouteDefinitionLocator locator, SwaggerUiConfigParameters swaggerUiConfigParameters) {
    var definitions = locator.getRouteDefinitions().collectList().block();
    assert definitions != null;
    var urls =
        definitions.stream()
            .filter(routeDefinition -> routeDefinition.getId().matches(".*-service"))
            .map(
                routeDefinition -> {
                  var name = routeDefinition.getId().replace("-service", "");
                  return new SwaggerUrl(name, "/" + name, null);
                })
            .collect(Collectors.toSet());
    swaggerUiConfigParameters.setUrls(urls);
    return urls;
  }
}
