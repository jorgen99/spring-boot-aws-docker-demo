package com.jorgenlundberg.demo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamoDbBean
public class DbEvent {

  private String s3Key;
  private Long timestamp;
  private String url;
  private String eventTime;

  private String raw;

  @DynamoDbPartitionKey
  public String getS3Key() {
    return s3Key;
  }

  @DynamoDbSortKey
  public Long getTimestamp() {
    return timestamp;
  }
}
