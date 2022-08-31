package com.samsc.htmltodocument.binder.freemarker;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samsc.htmltodocument.binder.BindingResult;

@WebMvcTest(controllers = {FreeMarkerBinderRestService.class})
class FreeMarkerTemplateBinderRestServiceTests {

  @TestConfiguration(proxyBeanMethods = false)
  static class FreeMarkerTemplateBinderRestServiceTestConfiguration {

    @Bean
    public FreeMarkerBinder freeMarkerTemplateBinder() {
      return new FreeMarkerBinder();
    }
  }

  @Autowired MockMvc mvc;

  @Autowired ObjectMapper objectMapper;

  @WithMockUser
  @Test
  void bind() throws Exception {
    final FreeMarkerBindingRequest request = new FreeMarkerBindingRequest();
    request.setTemplateData(objectMapper.readTree("{ \"a\": { \"b\": { \"c\":\"123\"} } }"));
    request.setTemplateContent("abc ${a.b.c}");

    final String responseContent =
        mvc.perform(
                post("/binder/bind")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().is2xxSuccessful())
            .andReturn()
            .getResponse()
            .getContentAsString();

    final BindingResult result = objectMapper.readValue(responseContent, BindingResult.class);
    assertThat(result.getContent()).isEqualTo("abc 123");
  }
}
