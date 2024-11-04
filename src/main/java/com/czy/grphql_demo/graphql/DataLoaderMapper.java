package com.czy.grphql_demo.graphql;

import com.czy.grphql_demo.graphql.dataloader.UserAddressDataLoader;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DataLoaderMapper {
    private static final int MAX_BATCH_SIZE = 1000;

    @Autowired
    private UserAddressDataLoader userAddressDataLoader;

    public Map<String, DataLoader<?, ?>> buildMap() {
        DataLoaderOptions batchOptions = DataLoaderOptions.newOptions().setMaxBatchSize(MAX_BATCH_SIZE);
        HashMap<String, DataLoader<?, ?>> dataLoaderMap = new HashMap<>();
        dataLoaderMap.put(DataLoaderIdentity.USER_ADDRESS, DataLoader.newMappedDataLoader(userAddressDataLoader.getAddressByUserId(), batchOptions));
        return dataLoaderMap;
    }
}
