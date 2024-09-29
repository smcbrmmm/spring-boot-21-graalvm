package com.smcbrmm.microservice.payment.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

import java.util.Objects;

@Configuration
public class ApplicationConfig {

    @Autowired
    Environment env;

    @Bean
    public MongoTemplate mongoTemplate(){
        SimpleMongoClientDatabaseFactory factory =
                new SimpleMongoClientDatabaseFactory(Objects.requireNonNull(env.getProperty("spring.data.mongodb.uri")));
        return new MongoTemplate(factory);
    }

}
