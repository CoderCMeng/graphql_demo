package com.czy.grphql_demo.graphql.resolver;

import com.czy.grphql_demo.graphql.DataLoaderIdentity;
import com.czy.grphql_demo.mapper.AddressMapper;
import com.czy.grphql_demo.models.Address;
import com.czy.grphql_demo.models.User;
import graphql.kickstart.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.dataloader.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class UserResolver implements GraphQLResolver<User> {
    @Autowired
    private AddressMapper addressMapper;

    // 会产生n+1问题
    /*public Address getAddress(User user) {
        log.info("UserResolver 执行getAddress userId: {}", user.getId());
        return addressMapper.queryAddressByUserId(user.getId());
    }*/

    public CompletableFuture<Address> getAddress(User user, DataFetchingEnvironment environment) {
        log.info("UserResolver 执行getAddressByBatchLoader userId: {}", user.getId());
        DataLoader<String, Address> dataLoader = environment.getDataLoader(DataLoaderIdentity.USER_ADDRESS);
        return dataLoader.load(user.getId()).thenApply(addresses -> addresses);
    }
}
