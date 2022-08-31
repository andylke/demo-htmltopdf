package com.samsc.htmltodocument.binder.freemarker;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.context.runner.WebApplicationContextRunner;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samsc.htmltodocument.binder.BindingResult;

/**
 * Tests for {@link FreeMarkerBinder}.
 *
 * @author Andy Lian
 */
class FreeMarkerTemplateBinderTests {

  @TestConfiguration
  static class FreeMarkerTemplateBinderTestConfiguration {

    @Bean
    public FreeMarkerBinder freeMarkerTemplateBinder() {
      return new FreeMarkerBinder();
    }
  }

  final WebApplicationContextRunner runner =
      new WebApplicationContextRunner()
          .withConfiguration(
              AutoConfigurations.of(
                  JacksonAutoConfiguration.class, FreeMarkerAutoConfiguration.class))
          .withUserConfiguration(FreeMarkerTemplateBinderTestConfiguration.class);

  @Test
  void bind() {
    runner.run(
        (context) -> {
          final ObjectMapper objectMapper = context.getBean(ObjectMapper.class);

          final FreeMarkerBindingRequest request = new FreeMarkerBindingRequest();
          request.setTemplateData(
              objectMapper.readTree("{ \"a\": { \"b\": { \"c\":\"123\"} } }"));
          request.setTemplateContent("abc ${a.b.c}");

          final FreeMarkerBinder binder = context.getBean(FreeMarkerBinder.class);
          final BindingResult result = binder.bind(request);

          assertThat(result).isNotNull();
          assertThat(result.getContent()).isEqualTo("abc 123");
        });
  }
}
