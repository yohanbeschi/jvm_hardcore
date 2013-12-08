package org.isk.jvmhardcore.mathparser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.isk.jvmhardcore.mathparser.core.Tokenizer.ParserException;
import org.junit.Assert;
import org.junit.Test;

public class MathParserTest {

  @Test
  public void addition() {
    final String string = "2+5";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathParser parser = new MathParser(inputStream);
    final String[] tokens = parser.parse();
    Assert.assertArrayEquals(new String[] { "2", "+", "5" }, tokens);
  }

  @Test
  public void subtraction() {
    final String string = "2-5";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathParser parser = new MathParser(inputStream);
    final String[] tokens = parser.parse();
    Assert.assertArrayEquals(new String[] { "2", "-", "5" }, tokens);
  }

  @Test
  public void multiplication() {
    final String string = "2*5";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathParser parser = new MathParser(inputStream);
    final String[] tokens = parser.parse();
    Assert.assertArrayEquals(new String[] { "2", "*", "5" }, tokens);
  }
  
  @Test
  public void division() {
    final String string = "2/5";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathParser parser = new MathParser(inputStream);
    final String[] tokens = parser.parse();
    Assert.assertArrayEquals(new String[] { "2", "/", "5" }, tokens);
  }
  @Test
  public void operatorExpected() {
    final String string = "3a+5";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathParser parser = new MathParser(inputStream);

    try {
      parser.parse();
      Assert.fail();
    } catch (ParserException e) {
      Assert.assertEquals("Line 1, column 2 - Expected: '+' or '-' or '*' or '/'. Got: a", e.getMessage());
    }
  }

  @Test
  public void digitExpected() {
    final String string = "2+,5";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathParser parser = new MathParser(inputStream);

    try {
      parser.parse();
      Assert.fail();
    } catch (ParserException e) {
      Assert.assertEquals("Line 1, column 3 - Expected: At least one Digit [0-9]. Got: ,", e.getMessage());
    }
  }
  
  @Test
  public void getNumber0() {
    final String string = "23+5";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathParser parser = new MathParser(inputStream);
    final String[] tokens = parser.parse();
    Assert.assertArrayEquals(new String[] { "23", "+", "5" }, tokens);
  }
  
  @Test
  public void getNumber1() {
    final String string = "2+54";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathParser parser = new MathParser(inputStream);
    final String[] tokens = parser.parse();
    Assert.assertArrayEquals(new String[] { "2", "+", "54" }, tokens);
  }
  
  @Test
  public void getNumber2() {
    final String string = "23+54";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathParser parser = new MathParser(inputStream);
    final String[] tokens = parser.parse();
    Assert.assertArrayEquals(new String[] { "23", "+", "54" }, tokens);
  }
  
  @Test
  public void getFloat0() {
    final String string = "1.2+5";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathParser parser = new MathParser(inputStream);
    final String[] tokens = parser.parse();
    Assert.assertArrayEquals(new String[] { "1.2", "+", "5" }, tokens);
  }
  
  @Test
  public void getFloat1() {
    final String string = "1+56.32";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathParser parser = new MathParser(inputStream);
    final String[] tokens = parser.parse();
    Assert.assertArrayEquals(new String[] { "1", "+", "56.32" }, tokens);
  }
  
  @Test
  public void getFloat2() {
    final String string = "1.2+56.32";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathParser parser = new MathParser(inputStream);
    final String[] tokens = parser.parse();
    Assert.assertArrayEquals(new String[] { "1.2", "+", "56.32" }, tokens);
  }
  
  @Test
  public void getNegativeInteger() {
    final String string = "-23+-54";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathParser parser = new MathParser(inputStream);
    final String[] tokens = parser.parse();
    Assert.assertArrayEquals(new String[] { "-23", "+", "-54" }, tokens);
  }
  
  @Test
  public void getNegativeFloat() {
    final String string = "-1.2+-56.32";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathParser parser = new MathParser(inputStream);
    final String[] tokens = parser.parse();
    Assert.assertArrayEquals(new String[] { "-1.2", "+", "-56.32" }, tokens);
  }
  
  @Test
  public void expressionWithUnprintables() {
    final String string = " \t1.2\u0003 \u0020+\n5   ";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathParser parser = new MathParser(inputStream);
    final String[] tokens = parser.parse();
    Assert.assertArrayEquals(new String[] { "1.2", "+", "5" }, tokens);
  }
}
