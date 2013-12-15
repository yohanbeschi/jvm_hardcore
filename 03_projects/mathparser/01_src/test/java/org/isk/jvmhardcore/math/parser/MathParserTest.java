package org.isk.jvmhardcore.math.parser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.LinkedList;

import org.isk.jvmhardcore.math.MathException;
import org.isk.jvmhardcore.math.common.Operator;
import org.isk.jvmhardcore.math.parser.MathParser;
import org.isk.jvmhardcore.math.parser.core.Tokenizer.ParserException;
import org.junit.Assert;
import org.junit.Test;

public class MathParserTest {

  private LinkedList<Object> expected(final Object ... objects) {
    final LinkedList<Object> list = new LinkedList<>();
    
    for (Object o : objects) {
      list.add(o);
    }
    
    return list;
  }
  
  private void test(final String expression, final LinkedList<Object> expected) {
    final InputStream inputStream = new ByteArrayInputStream(expression.getBytes());
    final MathParser parser = new MathParser(inputStream);
    final LinkedList<Object> tokens = parser.parse();
    Assert.assertEquals(expected, tokens);
  }
  
  @Test
  public void addition() {
    this.test("2+5", this.expected(2, 5, Operator.PLUS));
  }

  @Test
  public void subtraction() {
    this.test("2-5", this.expected(2, 5, Operator.MINUS));
  }

  @Test
  public void multiplication() {
    this.test("2*5", this.expected(2, 5, Operator.TIMES));
  }
  
  @Test
  public void division() {
    this.test("2/5", this.expected(2, 5, Operator.DIVIDE));
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
      Assert.assertEquals("Line 1, column 2 - Expected: End of file. Got: a", e.getMessage());
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
    this.test("23+5", this.expected(23, 5, Operator.PLUS));
  }
  
  @Test
  public void getNumber1() {
    this.test("2+54", this.expected(2, 54, Operator.PLUS));
  }
  
  @Test
  public void getNumber2() {
    this.test("23+54", this.expected(23, 54, Operator.PLUS));
  }
  
  @Test
  public void getFloat0() {
    this.test("1.2+5", this.expected(1.2, 5, Operator.PLUS));
  }
  
  @Test
  public void getFloat1() {
    this.test("1+56.32", this.expected(1, 56.32, Operator.PLUS));
  }
  
  @Test
  public void getFloat2() {
    this.test("1.2+56.32", this.expected(1.2, 56.32, Operator.PLUS));
  }
  
  @Test
  public void getNegativeInteger() {
    this.test("-23+-54", this.expected(-23, -54, Operator.PLUS));
  }
  
  @Test
  public void getNegativeFloat() {
    this.test("-1.2+-56.32", this.expected(-1.2, -56.32, Operator.PLUS));
  }
  
  @Test
  public void expressionWithUnprintables() {
    this.test(" \t1.2\u0003 \u0020+\n5   ", this.expected(1.2, 5, Operator.PLUS));
  }
  
  @Test
  public void variableNumberOfOperands() {
    this.test("2 + 3 - 4 * 5 / -17",
        this.expected(2, 3, Operator.PLUS, 4, 5, Operator.TIMES, -17, Operator.DIVIDE, Operator.MINUS));
  }
  
  @Test
  public void expressionWithParenthesis0() {
    this.test("(2 + 5) - 2", this.expected(2, 5, Operator.PLUS, 2, Operator.MINUS));
  }
  
  @Test
  public void expressionWithParenthesis1() {
    this.test("((2 + 5) * 2) - 2", this.expected(2, 5, Operator.PLUS, 2, Operator.TIMES, 2, Operator.MINUS));
  }
  
  @Test
  public void expressionWithParenthesis2() {
    this.test("((2 + 5) * (10 - 8)) - 2", 
        this.expected(2, 5, Operator.PLUS, 10, 8, Operator.MINUS, Operator.TIMES, 2, Operator.MINUS));
  }
  
  @Test
  public void expressionWithParenthesis3() {
    this.test("((2 + 5) * (10 - 8) - 2) + 3", 
        this.expected(2, 5, Operator.PLUS, 10, 8, Operator.MINUS, Operator.TIMES, 2, Operator.MINUS, 3, Operator.PLUS));
  }
  
  @Test
  public void expressionWithParenthesis4() {
    this.test("((2 + 5) - 2 * (10 - 8)) + 3", 
        this.expected(2, 5, Operator.PLUS, 2, 10, 8, Operator.MINUS, Operator.TIMES, Operator.MINUS, 3, Operator.PLUS));
  }
  
  @Test
  public void expressionWithParenthesis5() {
    this.test("(2 * (2 + 5) - (10 - 8)) + 3", 
        this.expected(2, 2, 5, Operator.PLUS, Operator.TIMES, 10, 8, Operator.MINUS, Operator.MINUS, 3, Operator.PLUS));
  }
  
  @Test
  public void expressionWithParenthesis6() {
    this.test("4 - (2 + 5) - 2", this.expected(4, 2, 5, Operator.PLUS, Operator.MINUS, 2, Operator.MINUS));
  }
  
  @Test
  public void wrongParensCount0() {
      final String string = "2 * (3 + 4";
      
      try {
          this.test(string, null);
          Assert.fail();
      } catch (MathException e) {
          Assert.assertEquals("Unexpected operator: LEFT_PARENTHESIS", e.getMessage());
      }
  }
  
  @Test
  public void wrongParensCount1() {
      final String string = "2 * (3 + 4))";
      
      try {
          this.test(string, null);
          Assert.fail();
      } catch (MathException e) {
          Assert.assertEquals("Missing left parenthesis.", e.getMessage());
      }
  }
  
  @Test
  public void wrongParensCount2() {
      final String string = "2 * ((3 + 4)";
      
      try {
          this.test(string, null);
          Assert.fail();
      } catch (MathException e) {
          Assert.assertEquals("Unexpected operator: LEFT_PARENTHESIS", e.getMessage());
      }
  }
  
  @Test
  public void wrongParensCount3() {
      final String string = "2 * (3 + 4))";
      
      try {
          this.test(string, null);
          Assert.fail();
      } catch (MathException e) {
          Assert.assertEquals("Missing left parenthesis.", e.getMessage());
      }
  }
}
