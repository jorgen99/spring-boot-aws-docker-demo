package com.jorgenlundberg.demo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamoDBTable(tableName = "fileEvent")
public class DbEvent {

  @DynamoDBHashKey(attributeName = "s3Key")
  private String s3Key;

  @DynamoDBRangeKey(attributeName = "timestamp")
  private Long timestamp;

  private String url;
  private String eventTime;

  private String raw;
}
