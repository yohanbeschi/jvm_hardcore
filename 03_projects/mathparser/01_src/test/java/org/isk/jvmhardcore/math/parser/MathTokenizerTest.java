package org.isk.jvmhardcore.math.parser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import junit.framework.Assert;

import org.isk.jvmhardcore.math.parser.core.InputStreamReader;
import org.isk.jvmhardcore.math.parser.core.Tokenizer.ParserException;
import org.junit.Test;

public class MathTokenizerTest {
  @Test
  public void getEachToken() {
    final String string = "24+53";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathTokenizer tokenizer = new MathTokenizer(null, new InputStreamReader(inputStream));

    final int digitOne = tokenizer.getInteger();
    final ParsingOperator operator = tokenizer.getOperator();
    final int digitTwo = tokenizer.getInteger();

    Assert.assertEquals(24, digitOne);
    Assert.assertEquals(ParsingOperator.PLUS, operator);
    Assert.assertEquals(53, digitTwo);
  }

  @Test
  public void operatorExpected() {
    final String string = "3a+5";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathTokenizer tokenizer = new MathTokenizer(null, new InputStreamReader(inputStream));

    tokenizer.getInteger();

    try {
      tokenizer.getOperator();
      Assert.fail();
    } catch (ParserException e) {
      Assert.assertEquals("Line 1, column 2 - Expected: '+' or '-' or '*' or '/'. Got: a", e.getMessage());
    }
  }

  @Test
  public void digitExpected() {
    final String string = "2+,5";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathTokenizer tokenizer = new MathTokenizer(null, new InputStreamReader(inputStream));

    tokenizer.getInteger();
    tokenizer.getOperator();

    try {
      tokenizer.getInteger();
      Assert.fail();
    } catch (ParserException e) {
      Assert.assertEquals("Line 1, column 3 - Expected: At least one Digit [0-9]. Got: ,", e.getMessage());
    }
  }

  @Test
  public void addition() {
    final String string = "2+5";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathTokenizer tokenizer = new MathTokenizer(null, new InputStreamReader(inputStream));

    tokenizer.getInteger();
    final ParsingOperator operator = tokenizer.getOperator();
    Assert.assertEquals(ParsingOperator.PLUS, operator);
  }

  @Test
  public void subtraction() {
    final String string = "2-5";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathTokenizer tokenizer = new MathTokenizer(null, new InputStreamReader(inputStream));

    tokenizer.getInteger();
    final ParsingOperator operator = tokenizer.getOperator();
    Assert.assertEquals(ParsingOperator.MINUS, operator);
  }

  @Test
  public void multiplication() {
    final String string = "2*5";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathTokenizer tokenizer = new MathTokenizer(null, new InputStreamReader(inputStream));

    tokenizer.getInteger();
    final ParsingOperator operator = tokenizer.getOperator();
    Assert.assertEquals(ParsingOperator.TIMES, operator);
  }

  @Test
  public void division() {
    final String string = "2/5";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathTokenizer tokenizer = new MathTokenizer(null, new InputStreamReader(inputStream));

    tokenizer.getInteger();
    final ParsingOperator operator = tokenizer.getOperator();
    Assert.assertEquals(ParsingOperator.DIVIDE, operator);
  }

  @Test
  public void getInteger0() {
    final String string = "124";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathTokenizer tokenizer = new MathTokenizer(null, new InputStreamReader(inputStream));

    final int num = tokenizer.getInteger();

    Assert.assertEquals(124, num);
  }
  
  @Test
  public void getInteger1() {
    final String string = "+124";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathTokenizer tokenizer = new MathTokenizer(null, new InputStreamReader(inputStream));

    final int num = tokenizer.getInteger();

    Assert.assertEquals(124, num);
  }
  
  @Test
  public void getInteger2() {
    final String string = "-124";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathTokenizer tokenizer = new MathTokenizer(null, new InputStreamReader(inputStream));

    final int num = tokenizer.getInteger();

    Assert.assertEquals(-124, num);
  }
  
  @Test
  public void isFloat0() {
    final String string = "1.1";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathTokenizer tokenizer = new MathTokenizer(null, new InputStreamReader(inputStream));

    final boolean isFloat = tokenizer.isFloat();
    
    Assert.assertTrue(isFloat);
  }
  
  @Test
  public void isFloat1() {
    final String string = ".567";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathTokenizer tokenizer = new MathTokenizer(null, new InputStreamReader(inputStream));

    final boolean isFloat = tokenizer.isFloat();
    
    Assert.assertTrue(isFloat);
  }
  
  @Test
  public void isFloat2() {
    final String string = "23.";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathTokenizer tokenizer = new MathTokenizer(null, new InputStreamReader(inputStream));

    final boolean isFloat = tokenizer.isFloat();
    
    Assert.assertTrue(isFloat);
  }
  
  @Test
  public void isFloat3() {
    final String string = "+1.1";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathTokenizer tokenizer = new MathTokenizer(null, new InputStreamReader(inputStream));

    final boolean isFloat = tokenizer.isFloat();
    
    Assert.assertTrue(isFloat);
  }
  
  @Test
  public void isFloat4() {
    final String string = "+.567";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathTokenizer tokenizer = new MathTokenizer(null, new InputStreamReader(inputStream));

    final boolean isFloat = tokenizer.isFloat();
    
    Assert.assertTrue(isFloat);
  }
  
  @Test
  public void isFloat5() {
    final String string = "+23.";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathTokenizer tokenizer = new MathTokenizer(null, new InputStreamReader(inputStream));

    final boolean isFloat = tokenizer.isFloat();
    
    Assert.assertTrue(isFloat);
  }
  
  @Test
  public void isFloat6() {
    final String string = "-1.1";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathTokenizer tokenizer = new MathTokenizer(null, new InputStreamReader(inputStream));

    final boolean isFloat = tokenizer.isFloat();
    
    Assert.assertTrue(isFloat);
  }
  
  @Test
  public void isFloat7() {
    final String string = "-.567";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathTokenizer tokenizer = new MathTokenizer(null, new InputStreamReader(inputStream));

    final boolean isFloat = tokenizer.isFloat();
    
    Assert.assertTrue(isFloat);
  }
  
  @Test
  public void isFloat8() {
    final String string = "-23.";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathTokenizer tokenizer = new MathTokenizer(null, new InputStreamReader(inputStream));

    final boolean isFloat = tokenizer.isFloat();
    
    Assert.assertTrue(isFloat);
  }
  
  @Test
  public void isNotFloat0() {
    final String string = ".";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathTokenizer tokenizer = new MathTokenizer(null, new InputStreamReader(inputStream));

    final boolean isFloat = tokenizer.isFloat();
    
    Assert.assertFalse(isFloat);
  }
  
  @Test
  public void isNotFloat1() {
    final String string = "10";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathTokenizer tokenizer = new MathTokenizer(null, new InputStreamReader(inputStream));

    final boolean isFloat = tokenizer.isFloat();
    
    Assert.assertFalse(isFloat);
  }
  
  @Test
  public void getFloat0() {
    final String string = "1.1";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathTokenizer tokenizer = new MathTokenizer(null, new InputStreamReader(inputStream));

    final double num = tokenizer.getFloat();

    Assert.assertEquals(1.1, num);
  }

  @Test
  public void getFloat1() {
    final String string = ".567";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathTokenizer tokenizer = new MathTokenizer(null, new InputStreamReader(inputStream));

    final double num = tokenizer.getFloat();

    Assert.assertEquals(.567, num);
  }

  @Test
  public void getFloat2() {
    final String string = "23.";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathTokenizer tokenizer = new MathTokenizer(null, new InputStreamReader(inputStream));

    final double num = tokenizer.getFloat();

    Assert.assertEquals(23., num);
  }
  
  @Test
  public void getFloat3() {
    final String string = "+1.1";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathTokenizer tokenizer = new MathTokenizer(null, new InputStreamReader(inputStream));

    final double num = tokenizer.getFloat();

    Assert.assertEquals(1.1, num);
  }
  
  @Test
  public void getFloat4() {
    final String string = "+.567";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathTokenizer tokenizer = new MathTokenizer(null, new InputStreamReader(inputStream));

    final double num = tokenizer.getFloat();

    Assert.assertEquals(.567, num);
  }
  
  @Test
  public void getFloat5() {
    final String string = "+23.";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathTokenizer tokenizer = new MathTokenizer(null, new InputStreamReader(inputStream));

    final double num = tokenizer.getFloat();

    Assert.assertEquals(23., num);
  }
  
  @Test
  public void getFloat6() {
    final String string = "-1.1";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathTokenizer tokenizer = new MathTokenizer(null, new InputStreamReader(inputStream));

    final double num = tokenizer.getFloat();

    Assert.assertEquals(-1.1, num);
  }
  
  @Test
  public void getFloat7() {
    final String string = "-.567";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathTokenizer tokenizer = new MathTokenizer(null, new InputStreamReader(inputStream));

    final double num = tokenizer.getFloat();

    Assert.assertEquals(-.567, num);
  }
  
  @Test
  public void getFloat8() {
    final String string = "-23.";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathTokenizer tokenizer = new MathTokenizer(null, new InputStreamReader(inputStream));

    final double num = tokenizer.getFloat();

    Assert.assertEquals(-23., num);
  }
}
