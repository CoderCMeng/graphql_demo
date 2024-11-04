package com.czy.grphql_demo.mapper;

import com.czy.grphql_demo.models.Address;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class AddressMapper {
    public Address queryAddressByUserId(String userId) {
        return MockDB.queryAddressByUserId(userId);
    }

    public List<Address> queryAddressListByUserIds(Collection<String> userIdList) {
        return MockDB.queryAddressListByUserIds(userIdList);
    }
}
