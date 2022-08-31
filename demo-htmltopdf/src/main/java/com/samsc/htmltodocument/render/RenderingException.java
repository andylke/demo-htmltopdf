package com.samsc.htmltodocument.render;

public class RenderingException extends RuntimeException {

  private static final long serialVersionUID = -4614568443582432862L;

  public RenderingException(String message, Throwable cause) {
    super(message, cause);
  }

  public RenderingException(String message) {
    super(message);
  }
}
