package com.czy.grphql_demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
  private String id;
  private String name;
  private int age;
  private Address address;

  public User(String id, String name, int age) {
    this.id = id;
    this.name = name;
    this.age = age;
  }
}
