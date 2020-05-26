package com.jorgenlundberg.demo;

public class DbEvent {

  private final String s3Key;

  public DbEvent(String s3Key) {
    this.s3Key = s3Key;
  }
}
