package com.czy.grphql_demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
  private String id;
  private String name;
  private int age;

  public User(String id, String name, int age) {
    this.id = id;
    this.name = name;
    this.age = age;
  }
}
