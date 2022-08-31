package com.samsc.htmltodocument.util;

import org.apache.commons.lang3.StringUtils;

/**
 * {@link String} utility methods, mainly for internal use.
 *
 * @author Andy Lian
 */
public class Strings {

  /**
   * Return true if the given {@code string} is non-null, not empty and has at least one
   * non-whitespace character.
   *
   * <pre>
   * Strings.isText(null) = false
   * Strings.isText("") = false
   * Strings.isText(" ") = false
   * Strings.isText("abc") = true
   * Strings.isText(" abc ") = true
   * </pre>
   *
   * @param string
   * @return
   */
  public static boolean isText(final CharSequence string) {
    return StringUtils.isBlank(string) == false;
  }

  /**
   * Return the given {@code string} if the {@code string} is non-null, not empty and has at least
   * one non-whitespace character, otherwise throw a {@link IllegalArgumentException}.
   *
   * @param <T>
   * @param string
   * @return
   */
  public static <T extends CharSequence> T requireText(final T string) {
    if (isText(string) == false) {
      throw new IllegalStateException(String.format("'%s' must be a text string", string));
    }

    return string;
  }
}
