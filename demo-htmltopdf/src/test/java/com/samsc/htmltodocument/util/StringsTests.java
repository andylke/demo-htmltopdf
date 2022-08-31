package com.samsc.htmltodocument.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link Strings}.
 *
 * @author Andy Lian
 */
class StringsTests {

  @Test
  void isText() {
    assertThat(Strings.isText(null)).isFalse();
    assertThat(Strings.isText("")).isFalse();
    assertThat(Strings.isText(" ")).isFalse();
    assertThat(Strings.isText("abc")).isTrue();
    assertThat(Strings.isText(" abc ")).isTrue();
  }

  @Test
  void requireText() {
    assertThrows(IllegalStateException.class, () -> Strings.requireText(null));
    assertThrows(IllegalStateException.class, () -> Strings.requireText(""));
    assertThrows(IllegalStateException.class, () -> Strings.requireText(" "));
    final String text = new String("abc");
    assertThat(Strings.requireText(text)).isSameAs(text);
  }
}
