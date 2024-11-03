package com.czy.grphql_demo.mapper;

import com.czy.grphql_demo.models.Address;
import com.czy.grphql_demo.models.User;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MockDB {
  private static final List<User> USERS = new ArrayList<>();

  static {
    USERS.add(new User("1", "张三", 18));
    USERS.add(new User("2", "李四", 19));
    USERS.add(new User("3", "王二", 20));
    USERS.add(new User("4", "小明", 16));
    USERS.add(new User("5", "小红", 18));
  }

  private static final List<Address> ADDRESSES = new ArrayList<>();

  static {
    ADDRESSES.add(new Address("1", "1", "广东", "广州市"));
    ADDRESSES.add(new Address("2", "2", "北京市", "北京市"));
    ADDRESSES.add(new Address("3", "3", "上海市", "上海市"));
    ADDRESSES.add(new Address("4", "4", "四川省", "成都市"));
    ADDRESSES.add(new Address("5", "5", "重庆市", "重庆市"));
  }

  public static User queryUserById(String userId) {
    log.info("执行queryUserById：userId = {}", userId);
    return USERS.stream().filter(u -> u.getId().equals(userId)).findFirst().orElse(null);
  }

  public static Address queryAddressByUserId(String userId) {
    log.info("执行queryAddressByUserId：userId = {}", userId);
    return ADDRESSES.stream().filter(a -> a.getUserId().equals(userId)).findFirst().orElse(null);
  }
}
