package com.jorgenlundberg.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileEvent {

  private String s3Key;

  public DbEvent asDbEvent() {
    return new DbEvent(s3Key);
  }
}
