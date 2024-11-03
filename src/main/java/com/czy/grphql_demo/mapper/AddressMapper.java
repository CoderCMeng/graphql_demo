package com.czy.grphql_demo.mapper;

import com.czy.grphql_demo.models.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
  public Address queryAddressByUserId(String userId) {
    return MockDB.queryAddressByUserId(userId);
  }
}
