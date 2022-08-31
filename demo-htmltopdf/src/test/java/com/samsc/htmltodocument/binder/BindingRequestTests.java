package com.samsc.htmltodocument.binder;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Tests for {@link BindingRequest}.
 *
 * @author Andy Lian
 */
class BindingRequestTests {

  final ApplicationContextRunner runner =
      new ApplicationContextRunner()
          .withConfiguration(AutoConfigurations.of(JacksonAutoConfiguration.class));

  @Test
  void writeValue() {
    runner.run(
        (context) -> {
          final ObjectMapper objectMapper = context.getBean(ObjectMapper.class);

          final BindingRequest object = new BindingRequest();
          object.setTemplateContent("abc");
          object.setTemplateData(objectMapper.readTree("{ \"abc\":\"123\" }"));

          final String json = objectMapper.writeValueAsString(object);
          JSONAssert.assertEquals(
              "{ \"templateContent\":\"abc\", \"templateData\":{ \"abc\":\"123\" } }", json, false);
        });
  }

  @Test
  void readValue() {
    runner.run(
        (context) -> {
          final ObjectMapper objectMapper = context.getBean(ObjectMapper.class);

          final BindingRequest object =
              objectMapper.readValue(
                  "{ \"templateContent\":\"abc\", \"templateData\":{ \"abc\":\"123\" } }",
                  BindingRequest.class);

          assertThat(object.getTemplateContent()).isEqualTo("abc");
          assertThat(object.getTemplateData())
              .isEqualTo(objectMapper.readTree("{ \"abc\":\"123\" }"));
        });
  }
}
