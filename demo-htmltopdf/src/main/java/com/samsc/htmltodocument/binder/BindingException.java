package com.samsc.htmltodocument.binder;

public class BindingException extends RuntimeException {

  private static final long serialVersionUID = -6583486230799948238L;

  public BindingException(String message, Throwable cause) {
    super(message, cause);
  }

  public BindingException(String message) {
    super(message);
  }
}
