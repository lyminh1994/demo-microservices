# demo-microservices

## config-service

You can deploy the whole thing to GKE. Once it's deployed, you can test the refresh configuration by issuing the following incantation to either the client or the server.

This demonstrates how you can add `spring-cloud-starter-bus-amqp` and the `spring-boot-starter-actuator` to the config server and to the various client microservices then invoke the new `/actuator/busrefresh` event, thusly:

``` shell
 curl localhost:8080/actuator/busrefresh -d {} -H "Content-Type: application/json"
```

You can also have the config server respond to changes in the source repositories and trigger the bus event (`RefreshRemoteApplicationEvent`) for you automatically, on the change itself. There are two ways to do this, both involve using the `spring-cloud-config-monitor`. If you're using a local file-system based Git repository, then you only need to make sure that the URL you use for the Spring Cloud Config Server Git URI is a `file://` URL, like this:

```yaml
spring:
  cloud:
    config:
      server:
        git:
          uri: file:///${HOME}/Desktop/configuration-config
```

If you're using a source code provider like Github or Gitlab, then you need to configure that service to invoke this config-server with a webhook.  The webhook will hit the `/monitor` endpoint with a payload. For a working but fake example, check out `fake_github_webhook.sh` which will trigger the `/monitor` endpoint on the `config-server`, the results of which you can observe in the `config-client-bus` project.

### Resources
* https://cloud.spring.io/spring-cloud-config/multi/multi__push_notifications_and_spring_cloud_bus.html
 
### To Do:

* get it working with a webhook on Github
* fully parameterize the GCR prefix and project so that only the image names themselves are hardcoded anywhere.
## department-service

## discovery-service

## employee-service

## gateway-service

## organization-service
