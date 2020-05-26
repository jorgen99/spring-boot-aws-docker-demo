package com.jorgenlundberg.demo;

public class FileEvent {
  private String s3Key;

  public FileEvent() {
  }

  public FileEvent(String s3Key) {
    this.s3Key = s3Key;
  }

  public DbEvent asDbEvent() {
    return new DbEvent(s3Key);
  }

  public String getS3Key() {
    return s3Key;
  }

  public void setS3Key(String s3Key) {
    this.s3Key = s3Key;
  }

  @Override
  public String toString() {
    return "FileEvent{" +
        "s3Key='" + s3Key + '\'' +
        '}';
  }
}
