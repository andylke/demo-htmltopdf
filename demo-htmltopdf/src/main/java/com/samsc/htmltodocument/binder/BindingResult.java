package com.samsc.htmltodocument.binder;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Represent a result for returning generated output content.
 *
 * @author Andy Lian
 */
public class BindingResult {

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
