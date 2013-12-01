package org.isk.jvmhardcore.mathparser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import junit.framework.Assert;

import org.isk.jvmhardcore.mathparser.core.InputStreamReader;
import org.isk.jvmhardcore.mathparser.core.Tokenizer.ParserException;
import org.isk.jvmhardcore.mathparser.core.util.Ascii;
import org.junit.Test;

public class MathTokenizerTest {
  @Test
  public void getEachToken() {
    final String string = "2+5";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathTokenizer tokenizer = new MathTokenizer(null, new InputStreamReader(inputStream));
    
    final int digitOne = tokenizer.getDigit();
    final int operator = tokenizer.getOperator();
    final int digitTwo = tokenizer.getDigit();
    
    Assert.assertEquals(Ascii.DIGIT_2, digitOne);
    Assert.assertEquals(Ascii.PLUS_SIGN, operator);
    Assert.assertEquals(Ascii.DIGIT_5, digitTwo);
  }
  
  @Test
  public void operatorExpected() {
    final String string = "32+5";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathTokenizer tokenizer = new MathTokenizer(null, new InputStreamReader(inputStream));
    
    tokenizer.getDigit();
    
    try {
      tokenizer.getOperator();
      Assert.fail();
    } catch (ParserException e) {
      Assert.assertEquals("Line 1, column 2 - Expected: '+'. Got: 2", e.getMessage());
    }
  }
  
  @Test
  public void digitExpected() {
    final String string = "2+,5";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final MathTokenizer tokenizer = new MathTokenizer(null, new InputStreamReader(inputStream));

    tokenizer.getDigit();
    tokenizer.getOperator();
    
    try {
      tokenizer.getDigit();
      Assert.fail();
    } catch (ParserException e) {
      Assert.assertEquals("Line 1, column 3 - Expected: Digit [0-9]. Got: ,", e.getMessage());
    }
  }
}
