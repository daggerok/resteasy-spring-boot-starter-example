package com.github.daggerok.resteasyspringbootapp;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Map;

@Component
@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
public class RESTEasyResource {

  @GET
  public Map<String, String> hello() {
    return Map.of("message", "Hello, World!");
  }
}
