package com.jorgenlundberg.demo;

import static software.amazon.awssdk.enhanced.dynamodb.TableSchema.fromBean;

import org.springframework.stereotype.Service;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.mapper.BeanTableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Service
public class DynamoDb {

  private static final BeanTableSchema<DbEvent> FILE_EVENT_SCHEMA = fromBean(DbEvent.class);
  private final DynamoDbTable<DbEvent> fileEventTable;

  public DynamoDb() {
    fileEventTable = DynamoDbEnhancedClient.builder()
        .dynamoDbClient(DynamoDbClient.builder().build())
        .build()
        .table("fileEvent", FILE_EVENT_SCHEMA);
  }

  public void put(DbEvent event) {
    fileEventTable.putItem(event);
  }
}
