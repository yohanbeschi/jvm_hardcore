package org.isk.jvmhardcore.mathparser.core;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.isk.jvmhardcore.mathparser.core.Tokenizer.ParserException;
import org.isk.jvmhardcore.mathparser.core.util.Ascii;
import org.junit.Assert;
import org.junit.Test;

public class TokenizerTest {
  @Test
  public void endOfFileExpected0() {
    final String string = "a";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final TokenizerTester tokenizer = new TokenizerTester(null, new InputStreamReader(inputStream));
    
    tokenizer.next();
    tokenizer.checkEndOfFile();
  }
  
  @Test
  public void endOfFileExpected1() {
    final String string = "ab";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final TokenizerTester tokenizer = new TokenizerTester(null, new InputStreamReader(inputStream));
    
    tokenizer.next();
    
    try {
      tokenizer.checkEndOfFile();
      Assert.fail();
    } catch (ParserException e) {
      Assert.assertEquals("Line 1, column 2 - Expected: End of file. Got: b", e.getMessage());
    }
  }
  
  @Test
  public void endOfFileExpected2() {
    final String string = "ab";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final TokenizerTester tokenizer = new TokenizerTester("myfile.txt", new InputStreamReader(inputStream));
    
    tokenizer.next();
    
    try {
      tokenizer.checkEndOfFile();
      Assert.fail();
    } catch (ParserException e) {
      Assert.assertEquals("myfile.txt\nLine 1, column 2 - Expected: End of file. Got: b", e.getMessage());
    }
  }
  
  @Test
  public void isDigit0() {
    final boolean isDigit = Tokenizer.isDigit(Ascii.DIGIT_2);
    
    Assert.assertTrue(isDigit);
  }
  
  @Test
  public void isDigit1() {
    final boolean isDigit = Tokenizer.isDigit(Ascii.SPACE);
    
    Assert.assertFalse(isDigit);
  }
  
  @Test
  public void isAsciiLetter0() {
    final boolean isAsciiLetter = Tokenizer.isAsciiLetter(Ascii.LOWERCASE_P);
    
    Assert.assertTrue(isAsciiLetter);
  }
  
  @Test
  public void isAsciiLetter1() {
    final boolean isAsciiLetter = Tokenizer.isAsciiLetter(Ascii.DIGIT_2);
    
    Assert.assertFalse(isAsciiLetter);
  }
}

class TokenizerTester extends Tokenizer {
  public TokenizerTester(String filename, Reader reader) {
    super(filename, reader);
  }
}