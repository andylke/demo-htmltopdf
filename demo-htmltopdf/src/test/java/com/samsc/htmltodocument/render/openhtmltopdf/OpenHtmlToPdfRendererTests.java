package com.samsc.htmltodocument.render.openhtmltopdf;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;

import com.samsc.htmltodocument.render.RenderingResult;

/**
 * Tests for {@link OpenHtmlToPdfRenderer}.
 *
 * @author Andy Lian
 */
class OpenHtmlToPdfRendererTests {

  @TestConfiguration
  static class OpenHtmlToPdfRendererTestConfiguration {

    @Bean
    public OpenHtmlToPdfRenderer openHtmlToPdfRenderer() {
      return new OpenHtmlToPdfRenderer();
    }
  }

  final ApplicationContextRunner runner =
      new ApplicationContextRunner()
          .withUserConfiguration(OpenHtmlToPdfRendererTestConfiguration.class);

  @Test
  void bind() {
    runner.run(
        (context) -> {
          final OpenHtmlToPdfRenderingRequest request = new OpenHtmlToPdfRenderingRequest();
          request.setContent(
              "<html>\r\n"
                  + "<head></head>\r\n"
                  + "<body>\r\n"
                  + "<p>abc</p>\r\n"
                  + "</body>\r\n"
                  + "</html>");

          final OpenHtmlToPdfRenderer renderer = context.getBean(OpenHtmlToPdfRenderer.class);
          final RenderingResult result = renderer.render(request);

          assertThat(result).isNotNull();
          // Use PDFBox to load return PDF bytes
          PDDocument document = PDDocument.load(result.getContent());
          document.close();
        });
  }
}
