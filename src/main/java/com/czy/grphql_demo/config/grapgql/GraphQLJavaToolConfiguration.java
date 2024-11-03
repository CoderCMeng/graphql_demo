package com.czy.grphql_demo.config.grapgql;

import com.czy.grphql_demo.models.Address;
import com.czy.grphql_demo.models.User;
import graphql.Scalars;
import graphql.kickstart.tools.SchemaParserDictionary;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphQLJavaToolConfiguration {
//  @Bean
//  public GraphQLScalarType[] scalars() {
//    return new GraphQLScalarType[] {
////        ExtendedScalars.Object,
////        ExtendedScalars.Json,
////        ExtendedScalars.DateTime,
////        ExtendedScalars.Date,
////        ExtendedScalars.Url,
////        Scalars.GraphQLInt,
//    };
//  }

  @Bean
  public SchemaParserDictionary dictionary() {
    SchemaParserDictionary dictionary = new SchemaParserDictionary();
    addFieldsDictionary(dictionary);
    return dictionary;
  }

  private void addFieldsDictionary(SchemaParserDictionary dictionary) {
    dictionary.add(
        User.class,
        Address.class
    );
  }
}
