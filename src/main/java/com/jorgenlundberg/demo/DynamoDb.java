package com.jorgenlundberg.demo;

import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

@Service
public class DynamoDb {

  private final DynamoDBMapper dbMapper;

  public DynamoDb() {
    AmazonDynamoDB dbClient = AmazonDynamoDBClientBuilder.defaultClient();
    dbMapper = new DynamoDBMapper(dbClient);
  }

  public void save(DbEvent event) {
    dbMapper.save(event);
  }
}
