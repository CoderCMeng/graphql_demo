package com.czy.grphql_demo.mapper;

import com.czy.grphql_demo.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
  public User getUserById(String id) {
    return MockDB.queryUserById(id);
  }
}
