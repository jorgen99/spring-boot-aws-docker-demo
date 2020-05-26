package com.jorgenlundberg.demo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTest {

  @Autowired
  private FileEventController controller;

  @Test
  void it_should_have_loaded_the_context_and_wired_the_FileEventController() {
    assertThat(controller, is(notNullValue()));
  }
}
