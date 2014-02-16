package org.isk.jvmhardcore.pjba;

import java.io.InputStream;
import java.util.List;

import org.isk.jvmhardcore.pjba.parser.core.Tokenizer.ParserException;
import org.isk.jvmhardcore.pjba.structure.ClassFile;
import org.junit.Assert;
import org.junit.Test;
import org.isk.pjb.*;

public class PjbParserTest {

  private List<ClassFile> test(String file) {
    final InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(file);
    final PjbParser parser = new PjbParser(file, inputStream);
    return parser.parse();
  }

  @Test
  public void parseSuccessful00() {
    List<ClassFile> list = this.test("parser/multi.pjb");

    Assert.assertEquals("2 classes", 2, list.size());
    // Class1
    final ClassFile classFile1 = list.get(0);
    Assert.assertEquals("MultiOne", classFile1.getClassName());
    // Class 2
    final ClassFile classFile2 = list.get(1);
    Assert.assertEquals("MultiTwo", classFile2.getClassName());
  }

  @Test
  public void parseSuccessful01() {
    List<ClassFile> list = this.test("parser/ok01.pjb");
    Assert.assertEquals(1, list.size());
    Assert.assertEquals(1, list.get(0).getMethods().size());
  }

  @Test
  public void parseSuccessful02() {
    List<ClassFile> list = this.test("parser/ok02.pjb");
    Assert.assertEquals(1, list.size());
    Assert.assertEquals(1, list.get(0).getMethods().size());
  }

  @Test
  public void parseSuccessful03() {
    List<ClassFile> list = this.test("parser/ok03.pjb");
    Assert.assertEquals(1, list.size());
    Assert.assertEquals(1, list.get(0).getMethods().size());
  }

  @Test
  public void parseSuccessful04() {
    List<ClassFile> list = this.test("parser/ok04.pjb");
    Assert.assertEquals(1, list.size());
    Assert.assertEquals(1, list.get(0).getMethods().size());
  }

  @Test
  public void parseSuccessful05() {
    List<ClassFile> list = this.test("parser/ok05.pjb");
    Assert.assertEquals(1, list.size());
    Assert.assertEquals(1, list.get(0).getMethods().size());
  }

  @Test
  public void parseSuccessful06() {
    List<ClassFile> list = this.test("parser/ok06.pjb");
    Assert.assertEquals(1, list.size());
    Assert.assertEquals(1, list.get(0).getMethods().size());
  }

  @Test
  public void parseSuccessful07() {
    List<ClassFile> list = this.test("parser/ok07.pjb");
    Assert.assertEquals(1, list.size());
    Assert.assertEquals(1, list.get(0).getMethods().size());
  }

  @Test
  public void parseSuccessful08() {
    List<ClassFile> list = this.test("parser/ok08.pjb");
    Assert.assertEquals(1, list.size());
    Assert.assertEquals(1, list.get(0).getMethods().size());
  }

  @Test
  public void parseSuccessful09() {
    List<ClassFile> list = this.test("parser/ok09.pjb");
    Assert.assertEquals(1, list.size());
    Assert.assertEquals(1, list.get(0).getMethods().size());
  }

  @Test
  public void parseSuccessful10() {
    List<ClassFile> list = this.test("parser/ok10.pjb");
    Assert.assertEquals(1, list.size());
    Assert.assertEquals(1, list.get(0).getMethods().size());
  }

  @Test
  public void parseSuccessful11() {
    List<ClassFile> list = this.test("parser/ok11.pjb");
    Assert.assertEquals(1, list.size());
    Assert.assertEquals(1, list.get(0).getMethods().size());
  }

  @Test
  public void parseFailure1() {
    try {
      this.test("parser/ko01.pjb");
      Assert.fail();
    } catch (ParserException e) {
      Assert.assertEquals("parser/ko01.pjb\nLine 1, column 1 - Expected directive: '.class' Got: c", e.getMessage());
    }
  }

  @Test
  public void parseFailure2() {
    try {
      this.test("parser/ko02.pjb");
      Assert.fail();
    } catch (ParserException e) {
      Assert.assertEquals("parser/ko02.pjb\nLine 4, column 1 - Expected directive: '.classend' Got: c", e.getMessage());
    }
  }

  @Test
  public void parseFailure3() {
    try {
      this.test("parser/ko03.pjb");
      Assert.fail();
    } catch (ParserException e) {
      Assert.assertEquals(
          "parser/ko03.pjb\nLine 2, column 2 - An identifier must start with an ASCII letter or '_' or '$'. Got: .",
          e.getMessage());
    }
  }

  @Test
  public void parseFailure4() {
    try {
      this.test("parser/ko04.pjb");
      Assert.fail();
    } catch (ParserException e) {
      Assert.assertEquals(
          "parser/ko04.pjb\nLine 3, column 2 - An identifier must start with an ASCII letter or '_' or '$'. Got: .",
          e.getMessage());
    }
  }

  @Test
  public void parseFailure5() {
    try {
      this.test("parser/ko05.pjb");
      Assert.fail();
    } catch (ParserException e) {
      Assert.assertEquals(
          "parser/ko05.pjb\nLine 2, column 10 - An identifier must start with an ASCII letter or '_' or '$'. Got: (",
          e.getMessage());
    }
  }

  @Test
  public void parseFailure6() {
    try {
      this.test("parser/ko06.pjb");
      Assert.fail();
    } catch (ParserException e) {
      Assert.assertEquals(
          "parser/ko06.pjb\nLine 3, column 2 - An identifier must start with a left parenthesis '('. Got: .",
          e.getMessage());
    }
  }

  @Test
  public void parseFailure7() {
    try {
      this.test("parser/ko07.pjb");
      Assert.fail();
    } catch (ParserException e) {
      Assert.assertEquals("parser/ko07.pjb\nLine 3, column 0 - Missing return descriptor. Got: \n", e.getMessage());
    }
  }

  @Test
  public void parseFailure8() {
    try {
      this.test("parser/ko08.pjb");
      Assert.fail();
    } catch (ParserException e) {
      Assert.assertEquals("parser/ko08.pjb\nLine 2, column 2 - Expected directive: '.classend' Got: m", e.getMessage());
    }
  }

  @Test
  public void parseFailure9() {
    try {
      this.test("parser/ko09.pjb");
      Assert.fail();
    } catch (ParserException e) {
      Assert.assertEquals("parser/ko09.pjb\nLine 3, column 2 - Unknown instruction. Got: methodend", e.getMessage());
    }
  }

  @Test
  public void parseFailure10() {
    try {
      this.test("parser/ko10.pjb");
      Assert.fail();
    } catch (ParserException e) {
      Assert.assertEquals("parser/ko10.pjb\nLine 3, column 3 - Unknown instruction. Got: instruction", e.getMessage());
    }
  }

  @Test
  public void ilocal_0() {
    final int result = TestIlocal_0.getFirst(1, 2, 3);

    Assert.assertEquals(1, result);
  }

  @Test
  public void ilocal_0_byte() {
    final byte result = TestIlocal_0_Byte.getFirst((byte) 1, 2, 3);

    Assert.assertEquals(1, result);
  }

  @Test
  public void ilocal_0_char() {
    final char result = TestIlocal_0_Char.getFirst((char) 1, 2, 3);

    Assert.assertEquals(1, result);
  }

  @Test
  public void ilocal_0_short() {
    final short result = TestIlocal_0_Short.getFirst((byte) 1, 2, 3);

    Assert.assertEquals(1, result);
  }

  @Test
  public void ilocal_0_noargs() {
    try {
      TestIlocal_0_NoArgs.getFirst();
      Assert.fail();
    } catch (VerifyError e) {
      Assert
          .assertEquals(
              "(class: org/isk/pjb/TestIlocal_0_NoArgs, method: getFirst signature: ()I) Accessing value from uninitialized register 0",
              e.getMessage());
    }
  }

  @Test
  public void ilocal_1() {
    final int result = TestIlocal_1.getSecond(1, 2, 3);

    Assert.assertEquals(2, result);
  }

  @Test
  public void iadd() {
    final int result = TestIadd.add(2, 3);

    Assert.assertEquals(5, result);
  }

  @Test
  public void ldc_string() {
    final String result = TestLdc_String.getString();

    Assert.assertEquals("Привет \\\" мир по-русски", result);
  }

  @Test
  public void ldc_integer() {
    final int result = TestLdc_Integer.getInteger();

    Assert.assertEquals(1000, result);
  }

  @Test
  public void ldc_float() {
    final float result = TestLdc_Float.getFloat();

    Assert.assertEquals(-15.56e-12f, result, 0.0001);
  }

  @Test
  public void ldc2_w_long() {
    final long result = TestLdc_Long.getLong();

    Assert.assertEquals(1324l, result);
  }

  @Test
  public void ldc2_w_double() {
    final double result = TestLdc_Double.getDouble();

    Assert.assertEquals(-14.70e-43, result, 0.0001);
  }

  @Test
  public void multi() {
    final int resultAdd = MultiOne.add(10, 5);
    Assert.assertEquals(15, resultAdd);

    final int resultSub = MultiOne.sub(5, 10);
    Assert.assertEquals(-5, resultSub);

    final int resultShift = MultiTwo.ishl(5, 1);
    Assert.assertEquals(10, resultShift);

    final double resultD2I = MultiTwo.d2i(15.786);
    Assert.assertEquals(15, resultD2I, 0.0001);
  }

  @Test
  public void iinc() {
    final int i = TestIinc.iinc(10);
    
    Assert.assertEquals(9, i);
  }

  @Test
  public void wide_istore_iload() {
    final int i = TestWide.wide_istore_iload(99_999);

    Assert.assertEquals(99_999, i);
  }

  @Test
  public void wide_iinc() {
    final int i = TestWide.iinc(31000);

    Assert.assertEquals(1, i);
  }
}
