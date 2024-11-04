package com.czy.grphql_demo.config.grapgql;

import graphql.kickstart.execution.context.DefaultGraphQLContext;
import org.dataloader.DataLoaderRegistry;

import javax.security.auth.Subject;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static graphql.Assert.assertNotNull;

@SuppressWarnings("unchecked")
public class CustomGraphQLContext extends DefaultGraphQLContext {


    private final ConcurrentMap<String, Object> map = new ConcurrentHashMap<>();

    public CustomGraphQLContext(DataLoaderRegistry dataLoaderRegistry, Subject subject) {
        super(dataLoaderRegistry, subject);
    }

    public CustomGraphQLContext add(String key, Object value) {
        map.put(assertNotNull(key), assertNotNull(value));
        return this;
    }

    public <T> T get(String key) {
        return (T) map.get(assertNotNull(key));
    }
}
