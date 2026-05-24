package dev.hobie.discovery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.env.EnvironmentEndpointProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import tools.jackson.databind.json.JsonMapper;

@EnableCaching
@EnableEurekaServer
@SpringBootApplication
@EnableConfigurationProperties(DiscoveryApplication.AppProperties.class)
public class DiscoveryApplication {

  private static final Logger log = LoggerFactory.getLogger(DiscoveryApplication.class);

  static void main(String[] args) {
    SpringApplication.run(DiscoveryApplication.class, args);
  }

  @ConfigurationProperties("application")
  public record AppProperties(String name, String version, String description) {}

  @Bean
  ApplicationRunner applicationRunner(
      JsonMapper mapper,
      EnvironmentEndpointProperties endpointProperties,
      AppProperties appProperties) {
    return _ ->
        log.info(
            "Starting application with properties {} \n {}",
            mapper.writeValueAsString(endpointProperties),
            mapper.writeValueAsString(appProperties));
  }
}
