package com.sunlife.vn.repository;

import com.sunlife.vn.common.constant.TableConstant;
import com.sunlife.vn.models.entity.dynamodb.EvtMstr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Repository
public class EvtMastrRepository {
    @Autowired
    private DynamoDbEnhancedClient dynamoDbenhancedClient;

    public void saveSms(EvtMstr evt_mastr) {
        DynamoDbTable<EvtMstr> actionTable = getTable();

        actionTable.putItem(evt_mastr);
    
    }

    private DynamoDbTable<EvtMstr> getTable() {
        // Create a tablescheme to scan our bean class order
        return dynamoDbenhancedClient.table(TableConstant.EVENT_MSTR,
                TableSchema.fromBean(EvtMstr.class));
    }
}
