package dev.hobie.gateway;

import static org.springdoc.core.utils.Constants.DEFAULT_API_DOCS_URL;

import java.util.HashSet;
import java.util.Set;
import org.springdoc.core.properties.AbstractSwaggerUiConfigProperties;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
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
  public Set<AbstractSwaggerUiConfigProperties.SwaggerUrl> apis(
      RouteDefinitionLocator locator, SwaggerUiConfigProperties swaggerUiConfigProperties) {
    Set<AbstractSwaggerUiConfigProperties.SwaggerUrl> urls = new HashSet<>();
    var definitions = locator.getRouteDefinitions().collectList().block();
    assert definitions != null;
    definitions.stream()
        .filter(routeDefinition -> routeDefinition.getId().matches(".*-service"))
        .forEach(
            routeDefinition -> {
              var name = routeDefinition.getId().replace("-service", "");
              var swaggerUrl =
                  new AbstractSwaggerUiConfigProperties.SwaggerUrl(
                      name, DEFAULT_API_DOCS_URL + "/" + name, null);
              urls.add(swaggerUrl);
            });
    swaggerUiConfigProperties.setUrls(urls);
    return urls;
  }
}
