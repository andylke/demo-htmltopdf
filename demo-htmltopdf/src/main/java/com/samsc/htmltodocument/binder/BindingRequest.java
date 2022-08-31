package com.samsc.htmltodocument.binder;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Represent a request to generate a text output from a given template content and {@link JsonNode
 * JSON data}.
 *
 * @author Andy Lian
 */
public class BindingRequest {

  private String templateContent;

  private JsonNode templateData;

  public String getTemplateContent() {
    return templateContent;
  }

  public void setTemplateContent(String templateContent) {
    this.templateContent = templateContent;
  }

  public JsonNode getTemplateData() {
    return templateData;
  }

  public void setTemplateData(JsonNode templateData) {
    this.templateData = templateData;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
