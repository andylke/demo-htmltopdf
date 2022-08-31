package com.samsc.htmltodocument.render;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Represent a request to render a given text content.
 *
 * @author Andy Lian
 */
public class RenderingRequest {

  private String content;

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
