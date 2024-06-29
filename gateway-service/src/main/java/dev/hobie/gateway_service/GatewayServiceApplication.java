package dev.hobie.gateway_service;

import java.util.ArrayList;
import java.util.List;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}

    @Bean
    List<GroupedOpenApi> apis(RouteDefinitionLocator locator) {
		var groups = new ArrayList<GroupedOpenApi>();
		var definitions = locator.getRouteDefinitions().collectList().block();
		assert definitions != null;
		definitions.stream()
				.filter(routeDefinition -> routeDefinition.getId().matches(".*-service"))
				.forEach(
						routeDefinition -> {
							var name = routeDefinition.getId().replace("-service", "");
							groups.add(
									GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").group(name).build());
						});
		return groups;
	}
}
