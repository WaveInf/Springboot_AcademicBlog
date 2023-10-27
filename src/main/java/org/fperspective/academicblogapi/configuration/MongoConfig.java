package org.fperspective.academicblogapi.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
public class MongoConfig {

    // @Value("${MONGODB_URI}")
    // private String mongoUri;

    // public String getMongoUri() {
    //     return mongoUri;
    // }

    // public String getMongoDatabase(){
    //     return "Main";
    // }
    
}
