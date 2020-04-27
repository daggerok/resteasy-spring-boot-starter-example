package com.github.daggerok.resteasyspringbootapp;

import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@Component
@ApplicationPath("/api")
public class RESTEasyConfig extends Application { }
