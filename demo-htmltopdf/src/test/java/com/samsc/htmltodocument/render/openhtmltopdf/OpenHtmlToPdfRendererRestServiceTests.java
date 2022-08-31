package com.samsc.htmltodocument.render.openhtmltopdf;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samsc.htmltodocument.render.RenderingResult;

@WebMvcTest(controllers = {OpenHtmlToPdfRendererRestService.class})
class OpenHtmlToPdfRendererRestServiceTests {

  @TestConfiguration(proxyBeanMethods = false)
  static class OpenHtmlToPdfRendererRestServiceTestConfiguration {

    @Bean
    public OpenHtmlToPdfRenderer openHtmlToPdfRenderer() {
      return new OpenHtmlToPdfRenderer();
    }
  }

  @Autowired MockMvc mvc;

  @Autowired ObjectMapper objectMapper;

  @WithMockUser
  @Test
  void render() throws Exception {
    final OpenHtmlToPdfRenderingRequest request = new OpenHtmlToPdfRenderingRequest();
    request.setContent(
        "<html>\r\n"
            + "<head></head>\r\n"
            + "<body>\r\n"
            + "<p>abc</p>\r\n"
            + "</body>\r\n"
            + "</html>");

    final String responseContent =
        mvc.perform(
                post("/renderer/render")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().is2xxSuccessful())
            .andReturn()
            .getResponse()
            .getContentAsString();

    final RenderingResult result = objectMapper.readValue(responseContent, RenderingResult.class);
    // Use PDFBox to load return PDF bytes
    PDDocument document = PDDocument.load(result.getContent());
    document.close();
  }
}
