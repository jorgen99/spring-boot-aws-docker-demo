package com.jorgenlundberg.demo;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
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

  private final FileParser fileParser;

  @Autowired
  public FileEventController(FileParser fileParser) {
    this.fileParser = fileParser;
  }

  @PostMapping(value = "/event/v1",
               consumes = APPLICATION_JSON_VALUE,
               produces = APPLICATION_JSON_VALUE
               )
  @ResponseStatus(code = CREATED)
  public @ResponseBody Reply event(@RequestBody FileEvent event) {
    var parseResult = fileParser.parse(event);
    var msg = String.format("File uploaded to s3: '%s'", parseResult.getS3Key());
    System.out.println(msg);
    return new Reply("AWESOME", msg);
  }

  @GetMapping(value = "/")
  @ResponseStatus(code = HttpStatus.OK)
  public @ResponseBody Reply hello() {
    return new Reply("AWESOME", "Hello World!" );
  }

  @Data
  @AllArgsConstructor
  private static final class Reply {
    private String status;
    private String message;
  }
}
