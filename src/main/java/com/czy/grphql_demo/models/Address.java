package com.czy.grphql_demo.models;

import lombok.Data;

@Data
public class Address {
  private String id;
  private String userId;
  private String province;
  private String city;

  public Address(String id, String userId, String province, String city) {
    this.id = id;
    this.userId = userId;
    this.province = province;
    this.city = city;
  }
}
