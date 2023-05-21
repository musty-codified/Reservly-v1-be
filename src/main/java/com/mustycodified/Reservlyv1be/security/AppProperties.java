package com.mustycodified.Reservlyv1be.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class AppProperties {
    @Autowired
    private Environment env;
    public String getSecretKey(){
        return env.getProperty("secretKey");
    }
}
