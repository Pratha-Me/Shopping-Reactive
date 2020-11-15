package com.online.shopping.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.connection.netty.NettyStreamFactoryFactory;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import java.util.Collection;
import java.util.Collections;

@Configuration
@EnableReactiveMongoRepositories(basePackages = {"com.online.shopping.repo"})
public class ReactiveMongoDBConfig extends AbstractReactiveMongoConfiguration {

// Note : The MongoTemplate bean is defined in the Parent Class.

    @Override
    protected String getDatabaseName() {
        return "shopping";
    }

    @Override
    public MongoClient reactiveMongoClient() {
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");
        MongoClientSettings clientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .streamFactoryFactory(NettyStreamFactoryFactory.builder().build())
                .build();
        return MongoClients.create(clientSettings);
    }

    @Override
    protected Collection<String> getMappingBasePackages() {
        return Collections.singleton("com.online.shopping");
    }
}
