package com.czy.grphql_demo.resolver;

import com.czy.grphql_demo.mapper.MockDB;
import com.czy.grphql_demo.models.Address;
import com.czy.grphql_demo.models.User;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserResolver implements GraphQLResolver<User> {

  public Address getAddress(User user) {
    log.info("UserResolver 执行getAddress userId: {}", user.getId());
    return MockDB.queryAddressByUserId(user.getId());
  }
}
