package com.jorgenlundberg.demo;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
public class FileEventController {

  private final FileEventParser fileEventParser;
  private final DynamoDb dynamoDb;

  @Value("${buildtime}")
  private String BUILD_TIME;

  @Autowired
  public FileEventController(FileEventParser fileEventParser, DynamoDb dynamoDb) {
    this.fileEventParser = fileEventParser;
    this.dynamoDb = dynamoDb;
  }

  @PostMapping(value = "/event/v1",
               consumes = APPLICATION_JSON_VALUE,
               produces = APPLICATION_JSON_VALUE
               )
  @ResponseStatus(code = CREATED)
  public @ResponseBody Reply event(@RequestBody String eventJson) {
    var eventRecord = fileEventParser.parse(eventJson);

    var msg = String.format("File uploaded to s3: '%s'", eventRecord.getUrl());
    System.out.println(msg);

    dynamoDb.save(eventRecord);
    return new Reply("AWESOME", msg, BUILD_TIME);
  }

  @GetMapping(value = "/")
  @ResponseStatus(code = HttpStatus.OK)
  public @ResponseBody Reply hello() {
    return new Reply("AWESOME", "Hello World!", BUILD_TIME);
  }

  @Data
  @AllArgsConstructor
  private static final class Reply {
    private String status;
    private String message;
    private String buildTime;
  }
}
