package com.czy.grphql_demo.resolver.query;

import com.czy.grphql_demo.mapper.UserMapper;
import com.czy.grphql_demo.models.User;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class UserQueryResolver implements GraphQLQueryResolver {
  @Autowired
  private UserMapper userMapper;

  public User getUserById(String id) {
    log.info("UserQueryResolver执行 getUserById: id = {}", id);
    return userMapper.getUserById(id);
  }

  public List<User> getAllUsers() {
    log.info("UserQueryResolver执行getAllUsers");
    return userMapper.getAllUsers();
  }
}
