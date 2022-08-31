package com.samsc.htmltodocument.render.openhtmltopdf;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;

import com.openhtmltopdf.java2d.api.BufferedImagePageProcessor;
import com.openhtmltopdf.java2d.api.Java2DRendererBuilder;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.samsc.htmltodocument.render.AbstractRenderer;
import com.samsc.htmltodocument.render.Renderer;
import com.samsc.htmltodocument.render.RenderingException;
import com.samsc.htmltodocument.render.RenderingResult;

/**
 * Implementation of {@link Renderer} that uses <a
 * href="https://github.com/danfickle/openhtmltopdf">openhtmltopdf</a> to render a text content into
 * a specific output format.
 *
 * @author Andy Lian
 */
@Component
public class OpenHtmlToPdfRenderer extends AbstractRenderer<OpenHtmlToPdfRenderingRequest> {

  @Override
  protected void process(
      OpenHtmlToPdfRenderingRequest renderingRequest, RenderingResult renderingResult) {
    renderingResult.setContent(
        render(renderingRequest.getOutputFormat(), renderingRequest.getContent()));
  }

  private byte[] render(final RenderingOutputFormat outputFormat, final String htmlContent) {
    switch (outputFormat) {
      case PNG:
        return renderPng(htmlContent);
      case PDF:
      default:
        return renderPdf(htmlContent);
    }
  }

  private byte[] renderPng(final String htmlContent) {
    BufferedImagePageProcessor pageProcessor =
        new BufferedImagePageProcessor(BufferedImage.TYPE_INT_RGB, 1);
    try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {

      Java2DRendererBuilder builder = new Java2DRendererBuilder();
      builder.withHtmlContent(htmlContent, "/").toSinglePage(pageProcessor).runFirstPage();

      final BufferedImage image = pageProcessor.getPageImages().get(0);
      ImageIO.write(image, "PNG", output);
      return output.toByteArray();

    } catch (Exception e) {
      throw new RenderingException(
          String.format(
              "Failed to convert the given HTML content to PNG using OpenHtmlToPdf renderer"),
          e);
    }
  }

  private byte[] renderPdf(final String htmlContent) {
    try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {

      PdfRendererBuilder builder = new PdfRendererBuilder();
      builder.withHtmlContent(htmlContent, "/").toStream(output).run();
      return output.toByteArray();

    } catch (Exception e) {
      throw new RenderingException(
          String.format(
              "Failed to convert the given HTML content to PDF using OpenHtmlToPdf renderer"),
          e);
    }
  }
}
