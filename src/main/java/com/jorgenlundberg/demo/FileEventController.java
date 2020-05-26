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
    DbEvent parseResult = fileParser.parse(event);
    return new Reply("AWESOME", "" );
  }

  @GetMapping(value = "/")
  @ResponseStatus(code = HttpStatus.OK)
  public @ResponseBody Reply hello() {
    return new Reply("AWESOME", "Hello World!" );
  }

  private static final class Reply {
    String status;
    String message;

    public Reply(String status, String message) {
      this.status = status;
      this.message = message;
    }

    public String getStatus() {
      return status;
    }

    public void setStatus(String status) {
      this.status = status;
    }

    public String getMessage() {
      return message;
    }

    public void setMessage(String message) {
      this.message = message;
    }
  }
}
