package com.samsc.htmltodocument.binder.freemarker;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samsc.htmltodocument.binder.AbstractBinder;
import com.samsc.htmltodocument.binder.Binder;
import com.samsc.htmltodocument.binder.BindingException;
import com.samsc.htmltodocument.binder.BindingResult;

import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Implementation of {@link Binder} that uses FreeMarker to generate a text output base on a
 * text template and {@link JsonNode JSON data}.
 *
 * @author Andy Lian
 */
@Component
public class FreeMarkerBinder extends AbstractBinder<FreeMarkerBindingRequest> {

  @Autowired private FreeMarkerConfigurer freeMarkerConfigurer;

  @Autowired private ObjectMapper objectMapper;

  @Override
  protected void process(FreeMarkerBindingRequest bindingRequest, BindingResult bindingResult) {
    final Template freeMarkerTemplate =
        createTemplate(bindingRequest.getTemplateContent());
    bindingResult.setContent(
        processTemplate(freeMarkerTemplate, bindingRequest.getTemplateData()));
  }

  private Template createTemplate(final String templateContent) {
    try {
      return new Template("template", templateContent, freeMarkerConfigurer.getConfiguration());
    } catch (IOException e) {
      throw new BindingException(
          String.format("Failed to instantiate new FreeMarker's Template object"), e);
    }
  }

  private String processTemplate(final Template template, final JsonNode templateData) {
    try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
      template.process(createTemplateModel(templateData), new OutputStreamWriter(output));
      return new String(output.toByteArray());
    } catch (TemplateException | IOException e) {
      throw new BindingException(
          String.format(
              "Failed to generate output from the given data using FreeMarker's template"),
          e);
    }
  }

  private Map<String, Object> createTemplateModel(final JsonNode templateData) {
    return objectMapper.convertValue(templateData, new TypeReference<Map<String, Object>>() {});
  }
}
