package com.samsc.htmltodocument.render;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Tests for {@link RenderingRequest}.
 *
 * @author Andy Lian
 */
class RenderingRequestTests {

  final ApplicationContextRunner runner =
      new ApplicationContextRunner()
          .withConfiguration(AutoConfigurations.of(JacksonAutoConfiguration.class));

  @Test
  void writeValue() {
    runner.run(
        (context) -> {
          final ObjectMapper objectMapper = context.getBean(ObjectMapper.class);

          final RenderingRequest object = new RenderingRequest();
          object.setContent("abc");

          final String json = objectMapper.writeValueAsString(object);
          JSONAssert.assertEquals("{ \"content\":\"abc\" }", json, false);
        });
  }

  @Test
  void readValue() {
    runner.run(
        (context) -> {
          final ObjectMapper objectMapper = context.getBean(ObjectMapper.class);

          final RenderingRequest object =
              objectMapper.readValue("{ \"content\":\"abc\" }", RenderingRequest.class);

          assertThat(object.getContent()).isEqualTo("abc");
        });
  }
}
