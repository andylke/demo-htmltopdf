package com.samsc.htmltodocument;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

/**
 * Tests for {@link Application}.
 *
 * @author Andy Lian
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ApplicationStartTests {

  @SpringBootApplication
  static class ApplicationStartTestConfiguration {}

  @Test
  void start() {}
}
