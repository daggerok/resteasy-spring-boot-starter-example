# resteasy-spring-boot-starter example [![CI](https://github.com/daggerok/resteasy-spring-boot-starter-example/workflows/CI/badge.svg)](https://github.com/daggerok/resteasy-spring-boot-starter-example/actions?query=workflow%3ACI)
Using RESTEasy Spring Boot starter integration. This is not just builtin Jersey integration!

_pom.xml_

```xml
<dependency>
    <groupId>org.jboss.resteasy</groupId>
    <artifactId>resteasy-spring-boot-starter</artifactId>
    <version>4.5.1.Final</version>
</dependency>
```

_app_

```java
@SpringBootApplication
public class RESTEasySpringBootApplication {

  @Component
  @ApplicationPath("/api")
  public static class RESTEasyConfig extends Application {

    @Component
    @Path("/hello")
    @Produces(MediaType.APPLICATION_JSON)
    public static class RESTEasyResource {

      @GET
      public Map<String, String> hello() {
        return Map.of("message", "Hello, World!");
      }
    }
  }

  public static void main(String[] args) {
    SpringApplication.run(RESTEasySpringBootApplication.class, args);
  }
}
```

_tests_

```java
@SpringBootTest
class RESTEasySpringBootApplicationTests {

  @Autowired
  WebTestClient webTestClient;

  @Test
  void contextLoads() {
    webTestClient.get()
                 .uri("/api/hello")
                 .exchange()
                 .expectStatus().isOk()
                 .expectBody(new ParameterizedTypeReference<Map<String, String>>() {})
                 .consumeWith(mapEntityExchangeResult -> {
                   var map = mapEntityExchangeResult.getResponseBody();
                   assertThat(map.get("message")).isNotNull()
                                                 .isEqualToIgnoringCase("hello, world!");
                 });

  }
}
```

_or simply_

```bash
./mvnw spring-boot:run
http :8080/api/hello
```

## resources

* https://www.youtube.com/watch?v=khzC-VwpFVM
<!--
* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.3.0.M4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.3.0.M4/maven-plugin/reference/html/#build-image)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#configuration-metadata-annotation-processor)
-->
* https://github.com/resteasy/resteasy-spring-boot/blob/master/mds/USAGE.md
