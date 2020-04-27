package com.github.daggerok.resteasyspringbootapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebTestClientAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
    classes = {
        RESTEasySpringBootApplication.class,
        WebTestClientAutoConfiguration.class,
    },
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
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
                   assertThat(map.getOrDefault("message", "")).isNotNull()
                                                              .isNotBlank()
                                                              .isEqualToIgnoringCase("hello, world!");
                 });

  }
}
