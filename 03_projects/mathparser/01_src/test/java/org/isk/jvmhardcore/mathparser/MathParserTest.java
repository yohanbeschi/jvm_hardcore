package org.isk.jvmhardcore.mathparser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.isk.jvmhardcore.mathparser.core.Tokenizer.ParserException;
import org.junit.Assert;
import org.junit.Test;

public class MathParserTest {
  @Test
  public void parse0() {
    final String string = "2+5";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathParser parser = new MathParser(inputStream);
    final String[] tokens = parser.parse();
    Assert.assertArrayEquals(new String[] { "2", "+", "5" }, tokens);
  }

  @Test
  public void parse1() {
    final String string = "32+5";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathParser parser = new MathParser(inputStream);

    try {
      parser.parse();
      Assert.fail();
    } catch (ParserException e) {
      Assert.assertEquals("Line 1, column 2 - Expected: '+'. Got: 2", e.getMessage());
    }
  }

  @Test
  public void parse2() {
    final String string = "2+,5";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathParser parser = new MathParser(inputStream);

    try {
      parser.parse();
      Assert.fail();
    } catch (ParserException e) {
      Assert.assertEquals("Line 1, column 3 - Expected: Digit [0-9]. Got: ,", e.getMessage());
    }
  }
}
