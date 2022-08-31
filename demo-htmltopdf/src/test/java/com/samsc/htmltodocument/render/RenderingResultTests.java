package com.samsc.htmltodocument.render;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Tests for {@link RenderingResult}.
 *
 * @author Andy Lian
 */
class RenderingResultTests {

  final ApplicationContextRunner runner =
      new ApplicationContextRunner()
          .withConfiguration(AutoConfigurations.of(JacksonAutoConfiguration.class));

  @Test
  void writeValue() {
    runner.run(
        (context) -> {
          final ObjectMapper objectMapper = context.getBean(ObjectMapper.class);

          final RenderingResult object = new RenderingResult();
          object.setContent("abc".getBytes());

          final String json = objectMapper.writeValueAsString(object);
          JSONAssert.assertEquals("{ \"content\":\"YWJj\" }", json, false);
        });
  }

  @Test
  void readValue() {
    runner.run(
        (context) -> {
          final ObjectMapper objectMapper = context.getBean(ObjectMapper.class);

          final RenderingResult object =
              objectMapper.readValue("{ \"content\":\"YWJj\" }", RenderingResult.class);

          assertThat(object.getContent()).containsExactly("abc".getBytes());
        });
  }
}
