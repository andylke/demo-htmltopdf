package com.samsc.htmltodocument.render;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Represent a result for returning rendered output bytes.
 *
 * @author Andy Lian
 */
public class RenderingResult {

  private byte[] content;

  public byte[] getContent() {
    return content;
  }

  public void setContent(byte[] content) {
    this.content = content;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
