package com.jorgenlundberg.demo;

import org.springframework.stereotype.Service;

@Service
class FileParser {

  DbEvent parse(FileEvent event) {
    return event.asDbEvent();
  }

}
