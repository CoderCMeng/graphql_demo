package com.czy.grphql_demo.config.grapgql;

import graphql.kickstart.tools.boot.SchemaStringProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class CustomClasspathResourceSchemaStringProvider implements SchemaStringProvider {

  private final ApplicationContext applicationContext;
  private final String schemaLocationPattern;

  public CustomClasspathResourceSchemaStringProvider(String schemaLocationPattern,
      ApplicationContext applicationContext) {
    this.schemaLocationPattern = schemaLocationPattern;
    this.applicationContext = applicationContext;
  }

  @Override
  public List<String> schemaStrings() throws IOException {
    Resource[] resources = applicationContext.getResources("classpath*:" + schemaLocationPattern);
    if (resources.length <= 0) {
      throw new IllegalStateException(
          "No graphql schema files found on classpath with location pattern '"
              + schemaLocationPattern
              + "'.  Please add a graphql schema to the classpath or add a SchemaParser bean to your application context.");
    }

    return Arrays.stream(resources)
        .map(this::readSchema)
        .collect(Collectors.toList());
  }

  private String readSchema(Resource resource) {
    try (
        InputStream inputStream = resource.getInputStream();
        InputStreamReader bufferedInputStream = new InputStreamReader(inputStream,
            StandardCharsets.UTF_8.name());
        BufferedReader reader = new BufferedReader(bufferedInputStream)
    ) {
      return reader.lines().collect(joining("\n"));
    } catch (IOException e) {
      throw new IllegalStateException("Cannot read graphql schema from resource " + resource, e);
    }
  }

}
