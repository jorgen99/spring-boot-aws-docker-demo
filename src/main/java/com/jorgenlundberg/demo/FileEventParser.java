package com.jorgenlundberg.demo;

import java.time.Instant;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
class FileEventParser {

  DbEvent parse(String json) {
    try {
      JsonNode jsonNode = new ObjectMapper().readTree(json);
      long eventTime = jsonNode.at("/eventTime/iMillis").asLong(-1L);
      String utcDate = Instant.ofEpochMilli(eventTime).atZone(ZoneOffset.UTC).toString();
      String bucketName = jsonNode.at("/s3/bucket/name").asText("no_bucket_name_found");
      String key = jsonNode.at("/s3/object/key").asText("no_key_found");
      return DbEvent.builder()
          .s3Key(key)
          .url(String.format("s3://%s/%s", bucketName, key))
          .timestamp(eventTime)
          .eventTime(utcDate)
          .raw(json)
          .build();
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

}
