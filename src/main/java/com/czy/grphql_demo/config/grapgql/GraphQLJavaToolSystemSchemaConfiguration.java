package com.czy.grphql_demo.config.grapgql;

import graphql.kickstart.tools.*;
import graphql.kickstart.tools.boot.SchemaDirective;
import graphql.kickstart.tools.boot.SchemaStringProvider;
import graphql.schema.GraphQLScalarType;
import graphql.schema.idl.SchemaDirectiveWiring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.io.IOException;
import java.util.List;

import static java.util.Objects.nonNull;

@Configuration
public class GraphQLJavaToolSystemSchemaConfiguration {
  @Autowired
  private ApplicationContext applicationContext;
  @Autowired(required = false)
  private SchemaParserDictionary dictionary;
  @Autowired(required = false)
  private List<SchemaDirective> directives;

  @Autowired(required = false)
  private List<SchemaDirectiveWiring> directiveWirings;

  @Bean
  @Order(0)
  public SchemaStringProvider systemSchemaStringProvider() {
    return new CustomClasspathResourceSchemaStringProvider("**/types/**/*.graphqls",
        applicationContext);
  }

  @Bean
  @Order(0)
  public SchemaParser systemSchemaParser(
      List<GraphQLResolver<?>> resolvers,
      GraphQLScalarType[] scalars,
      SchemaParserOptions.Builder optionsBuilder
  ) throws IOException {
    SchemaParserBuilder builder = new SchemaParserBuilder();
    if (nonNull(dictionary)) {
      builder.dictionary(dictionary.getDictionary());
    }
    List<String> schemaStrings = systemSchemaStringProvider().schemaStrings();
    schemaStrings.forEach(builder::schemaString);

    if (scalars != null) {
      builder.scalars(scalars);
    }

    builder.options(optionsBuilder.build());

    if (directives != null) {
      directives.forEach(it -> builder.directive(it.getName(), it.getDirective()));
    }

    if (directiveWirings != null) {
      directiveWirings.forEach(builder::directiveWiring);
    }

    return builder
        .resolvers(resolvers)
        .build();
  }

}
