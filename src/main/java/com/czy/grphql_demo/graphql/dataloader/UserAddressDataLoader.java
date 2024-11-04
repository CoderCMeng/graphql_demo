package com.czy.grphql_demo.graphql.dataloader;

import com.czy.grphql_demo.mapper.AddressMapper;
import com.czy.grphql_demo.models.Address;
import org.dataloader.MappedBatchLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class UserAddressDataLoader {
    @Autowired
    private AddressMapper addressMapper;

    public MappedBatchLoader<String, Address> getAddressByUserId() {
        return userIds -> CompletableFuture.supplyAsync(
                () -> addressMapper.queryAddressListByUserIds(userIds).stream().collect(Collectors.toMap(Address::getUserId, Function.identity()))
        );
    }
}
