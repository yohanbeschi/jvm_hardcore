package org.isk.jvmhardcore.pjba;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.isk.jvmhardcore.pjba.parser.core.Tokenizer.ParserException;
import org.isk.jvmhardcore.pjba.structure.ClassFile;
import org.isk.pjb.MultiOne;
import org.isk.pjb.MultiTwo;
import org.isk.pjb.TestGoto;
import org.isk.pjb.TestIadd;
import org.isk.pjb.TestIfICompCond;
import org.isk.pjb.TestIfcond;
import org.isk.pjb.TestIinc;
import org.isk.pjb.TestIlocal_0;
import org.isk.pjb.TestIlocal_0_Byte;
import org.isk.pjb.TestIlocal_0_Char;
import org.isk.pjb.TestIlocal_0_NoArgs;
import org.isk.pjb.TestIlocal_0_Short;
import org.isk.pjb.TestIlocal_1;
import org.isk.pjb.TestLdc_Double;
import org.isk.pjb.TestLdc_Float;
import org.isk.pjb.TestLdc_Integer;
import org.isk.pjb.TestLdc_Long;
import org.isk.pjb.TestLdc_String;
import org.isk.pjb.TestObject;
import org.isk.pjb.TestStatic;
import org.isk.pjb.TestStaticUpdater;
import org.isk.pjb.TestSwitch;
import org.isk.pjb.TestTypeCmp;
import org.isk.pjb.TestWide;
import org.junit.Assert;
import org.junit.Test;

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
  public void parseSuccessful12() {
    List<ClassFile> list = this.test("parser/ok12.pjb");
    Assert.assertEquals(1, list.size());
    Assert.assertEquals(1, list.get(0).getMethods().size());
  }

  @Test
  public void parseSuccessful13() {
    List<ClassFile> list = this.test("parser/ok13.pjb");
    Assert.assertEquals(1, list.size());
    Assert.assertEquals(9, list.get(0).getFields().size());

    // PjbDumper dumper = new PjbDumper(list.get(0));
    // System.out.println(dumper.dump());
  }

  @Test
  public void parseSuccessful14() {
    List<ClassFile> list = this.test("parser/ok14.pjb");
    Assert.assertEquals(1, list.size());
    Assert.assertEquals(1, list.get(0).getMethods().size());
  }

  @Test
  public void parseSuccessful15() {
    List<ClassFile> list = this.test("parser/ok15.pjb");
    Assert.assertEquals(1, list.size());
    Assert.assertEquals(1, list.get(0).getFields().size());
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
  public void parseFailure11() {
    try {
      this.test("parser/ko11.pjb");
      Assert.fail();
    } catch (ParserException e) {
      Assert.assertEquals(
          "parser/ko11.pjb\nLine 4, column 17 - A label following an instruction should not end with a colon. Got: :",
          e.getMessage());
    }
  }

  @Test
  public void parseFailure12() {
    try {
      this.test("parser/ko12.pjb");
      Assert.fail();
    } catch (ParserException e) {
      Assert.assertEquals("parser/ko12.pjb\nLine 7, column 5 - Unknown instruction. Got: label_1", e.getMessage());
    }
  }

  @Test
  public void parseFailure13() {
    try {
      this.test("parser/ko13.pjb");
      Assert.fail();
    } catch (ParserException e) {
      Assert.assertEquals("parser/ko13.pjb\nLine 3, column 1 - The field <MY_FIELD_2> can't be initialized because "
          + "it's not <final>. Tip: If the field is not intended to be <final> you need to initialize it "
          + "in a static block.", e.getMessage());
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

  @Test
  public void ifeq() {
    final boolean b1 = TestIfcond.ifeq(true);
    Assert.assertTrue(b1);

    final boolean b2 = TestIfcond.ifeq(false);
    Assert.assertFalse(b2);
  }

  @Test
  public void ifeq_and() {
    final boolean b1 = TestIfcond.and(true, true);
    Assert.assertTrue(b1);

    final boolean b2 = TestIfcond.and(false, false);
    Assert.assertFalse(b2);

    final boolean b3 = TestIfcond.and(true, false);
    Assert.assertFalse(b3);

    final boolean b4 = TestIfcond.and(false, true);
    Assert.assertFalse(b4);
  }

  @Test
  public void ifeq_or() {
    final boolean b1 = TestIfcond.or(true, true);
    Assert.assertTrue(b1);

    final boolean b2 = TestIfcond.or(false, false);
    Assert.assertFalse(b2);

    final boolean b3 = TestIfcond.or(true, false);
    Assert.assertTrue(b3);

    final boolean b4 = TestIfcond.or(false, true);
    Assert.assertTrue(b4);
  }

  @Test
  public void ifeq_and_or() {
    // a && (b || c) && (d || e)

    final boolean b1 = TestIfcond.and_or(true, false, true, false, true);
    Assert.assertTrue(b1);

    final boolean b2 = TestIfcond.and_or(true, true, false, true, false);
    Assert.assertTrue(b2);

    final boolean b3 = TestIfcond.and_or(false, true, false, true, false);
    Assert.assertFalse(b3);

    final boolean b4 = TestIfcond.and_or(true, false, false, true, false);
    Assert.assertFalse(b4);

    final boolean b5 = TestIfcond.and_or(true, true, false, false, false);
    Assert.assertFalse(b5);
  }

  @Test
  public void ifeq_neg() {
    final int i = TestIfcond.ifeq_neg();
    Assert.assertEquals(10, i);
  }

  @Test
  public void if_icmpge() {
    final boolean b1 = TestIfICompCond.if_icmpge(5, 5);
    Assert.assertTrue(b1);

    final boolean b2 = TestIfICompCond.if_icmpge(10, 5);
    Assert.assertTrue(b2);

    final boolean b3 = TestIfICompCond.if_icmpge(5, 10);
    Assert.assertFalse(b3);
  }

  @Test
  public void if_cmp_or_and() {
    // (a >= b || c == d) && a != d
    // a >= b && a != d
    final boolean b1 = TestIfICompCond.or_and(5, 4, 9, 10);
    Assert.assertTrue(b1);

    // c == d && a != d
    final boolean b2 = TestIfICompCond.or_and(1, 4, 10, 10);
    Assert.assertTrue(b2);

    // a >= b but a == d
    final boolean b3 = TestIfICompCond.or_and(10, 4, 10, 10);
    Assert.assertFalse(b3);

    // a < b && c != d
    final boolean b4 = TestIfICompCond.or_and(1, 4, 9, 10);
    Assert.assertFalse(b4);
  }

  @Test
  public void if_icmplt_neg() {
    final int i = TestIfICompCond.if_icmplt_neg();
    Assert.assertEquals(10, i);
  }

  @Test
  public void lcmp() {
    final int i1 = TestTypeCmp.lcmp(10, 1);
    Assert.assertEquals(1, i1);

    final int i2 = TestTypeCmp.lcmp(10, 10);
    Assert.assertEquals(0, i2);

    final int i3 = TestTypeCmp.lcmp(1, 10);
    Assert.assertEquals(-1, i3);
  }

  @Test
  public void lcmp_ifle() {
    final boolean b1 = TestTypeCmp.lcmp_ifle(10, 5);
    Assert.assertTrue(b1);

    final boolean b2 = TestTypeCmp.lcmp_ifle(5, 5);
    Assert.assertFalse(b2);

    final boolean b3 = TestTypeCmp.lcmp_ifle(5, 10);
    Assert.assertFalse(b3);
  }

  @Test
  public void dcmpl() {
    final int i1 = TestTypeCmp.dcmpl(5.5, 10.1);
    Assert.assertEquals(-1, i1);

    final int i2 = TestTypeCmp.dcmpl(5.5, 5.5);
    Assert.assertEquals(0, i2);

    final int i3 = TestTypeCmp.dcmpl(10.1, 5.5);
    Assert.assertEquals(1, i3);

    // returns -1 if one of the value is NaN
    final int i4 = TestTypeCmp.dcmpl(Double.NaN, 5.5);
    Assert.assertEquals(-1, i4);

    final int i5 = TestTypeCmp.dcmpl(5.5, Double.NaN);
    Assert.assertEquals(-1, i5);
  }

  @Test
  public void dcmpg() {
    final int i1 = TestTypeCmp.dcmpg(5.5, 10.1);
    Assert.assertEquals(-1, i1);

    final int i2 = TestTypeCmp.dcmpg(5.5, 5.5);
    Assert.assertEquals(0, i2);

    final int i3 = TestTypeCmp.dcmpg(10.1, 5.5);
    Assert.assertEquals(1, i3);

    // returns 1 if one of the value is NaN
    final int i4 = TestTypeCmp.dcmpg(Double.NaN, 5.5);
    Assert.assertEquals(1, i4);

    final int i5 = TestTypeCmp.dcmpg(5.5, Double.NaN);
    Assert.assertEquals(1, i5);
  }

  @Test
  public void if_acmpeq() {
    final Object o1 = new Object();
    final Object o2 = new Object();

    final boolean b1 = TestObject.if_acmpeq(o1, o1);
    Assert.assertTrue(b1);

    final boolean b2 = TestObject.if_acmpeq(o2, o1);
    Assert.assertFalse(b2);
  }

  @Test
  public void if_acmpne() {
    final Object o1 = new Object();
    final Object o2 = new Object();

    final boolean b1 = TestObject.if_acmpne(o1, o1);
    Assert.assertFalse(b1);

    final boolean b2 = TestObject.if_acmpne(o1, o2);
    Assert.assertTrue(b2);
  }

  @Test
  public void ifnull() {
    final boolean b1 = TestObject.ifnull(null);
    Assert.assertTrue(b1);

    final boolean b2 = TestObject.ifnull(new Object());
    Assert.assertFalse(b2);

    final boolean b3 = TestObject.ifnull(new LinkedList<>());
    Assert.assertFalse(b3);
  }

  @Test
  public void ifnonnull() {
    final boolean b1 = TestObject.ifnonnull(null);
    Assert.assertFalse(b1);

    final boolean b2 = TestObject.ifnonnull(new Object());
    Assert.assertTrue(b2);

    final boolean b3 = TestObject.ifnonnull(new LinkedList<>());
    Assert.assertTrue(b3);
  }

  @Test
  public void goto_after() {
    final boolean b1 = TestGoto.goto_after(10, 100);
    Assert.assertTrue(b1);

    final boolean b2 = TestGoto.goto_after(100, 10);
    Assert.assertFalse(b2);
  }

  @Test
  public void goto_before() {
    final int i = TestGoto.goto_before();
    Assert.assertEquals(-55, i);
  }

  @Test
  public void tableswitch() {
    final int i1 = TestSwitch.tableswitch(5);
    Assert.assertEquals(10, i1);

    final int i2 = TestSwitch.tableswitch(6);
    Assert.assertEquals(12, i2);

    final int i3 = TestSwitch.tableswitch(7);
    Assert.assertEquals(14, i3);

    final int i4 = TestSwitch.tableswitch(1);
    Assert.assertEquals(100, i4);
  }

  @Test
  public void tableswitch_signed() {
    final int i1 = TestSwitch.tableswitch_signed(-1);
    Assert.assertEquals(14, i1);

    final int i2 = TestSwitch.tableswitch_signed(0);
    Assert.assertEquals(10, i2);

    final int i3 = TestSwitch.tableswitch_signed(1);
    Assert.assertEquals(12, i3);

    final int i4 = TestSwitch.tableswitch_signed(10);
    Assert.assertEquals(100, i4);
  }

  @Test
  public void tableswitch_neg() {
    final int i1 = TestSwitch.tableswitch_neg(-1);
    Assert.assertEquals(-1, i1);

    final int i2 = TestSwitch.tableswitch_neg(0);
    Assert.assertEquals(3, i2);

    final int i3 = TestSwitch.tableswitch_neg(1);
    Assert.assertEquals(1, i3);
  }

  @Test
  public void lookupswitch() {
    final int i1 = TestSwitch.lookupswitch(5);
    Assert.assertEquals(10, i1);

    final int i2 = TestSwitch.lookupswitch(10);
    Assert.assertEquals(20, i2);

    final int i3 = TestSwitch.lookupswitch(15);
    Assert.assertEquals(30, i3);

    final int i4 = TestSwitch.tableswitch(1);
    Assert.assertEquals(100, i4);
  }

  @Test
  public void lookupswitch_signed() {
    final int i1 = TestSwitch.lookupswitch_signed(-5);
    Assert.assertEquals(10, i1);

    final int i2 = TestSwitch.lookupswitch_signed(-10);
    Assert.assertEquals(20, i2);

    final int i3 = TestSwitch.lookupswitch_signed(15);
    Assert.assertEquals(30, i3);

    final int i4 = TestSwitch.lookupswitch_signed(1);
    Assert.assertEquals(100, i4);
  }

  @Test
  public void lookupswitch_neg() {
    final int i1 = TestSwitch.lookupswitch_neg(-1);
    Assert.assertEquals(-1, i1);

    final int i2 = TestSwitch.lookupswitch_neg(0);
    Assert.assertEquals(3, i2);

    final int i3 = TestSwitch.lookupswitch_neg(1);
    Assert.assertEquals(1, i3);
  }

  @Test
  public void invokestatic() {
    final int i1 = TestStatic.invokestatic(10, 5, 9);
    Assert.assertEquals(19, i1);

    final int i2 = TestStatic.invokestatic(5, 10, 9);
    Assert.assertEquals(19, i2);
  }

  @Test
  public void getstatic() {
    final int i = TestStatic.getstatic();
    Assert.assertEquals(Integer.MAX_VALUE, i);
  }

  @Test
  public void putstatic() {
    Assert.assertEquals(0, TestStatic.TEST_PUTSTATIC);
    final int i = TestStatic.putstatic(10);
    Assert.assertEquals(10, i);
    Assert.assertEquals(10, TestStatic.TEST_PUTSTATIC);
  }

  @Test
  public void notInitialized() {
    Assert.assertEquals(0, TestStatic.TEST_NOT_INITIALIZED_INT);
    Assert.assertEquals(0, TestStatic.TEST_NOT_INITIALIZED_LONG);
    Assert.assertEquals(0.0f, TestStatic.TEST_NOT_INITIALIZED_FLOAT, 0.0001);
    Assert.assertEquals(0.0, TestStatic.TEST_NOT_INITIALIZED_DOUBLE, 0.0001);
    Assert.assertNull(TestStatic.TEST_NOT_INITIALIZED_OBJ);

    TestStatic.TEST_NOT_INITIALIZED_INT = 111;
    TestStatic.TEST_NOT_INITIALIZED_LONG = 222_222;
    TestStatic.TEST_NOT_INITIALIZED_FLOAT = 333.333f;
    TestStatic.TEST_NOT_INITIALIZED_DOUBLE = 444_444.444_444;
    TestStatic.TEST_NOT_INITIALIZED_OBJ = "Hello JVMHardcore";

    Assert.assertEquals(111, TestStatic.TEST_NOT_INITIALIZED_INT);
    Assert.assertEquals(222_222, TestStatic.TEST_NOT_INITIALIZED_LONG);
    Assert.assertEquals(333.333f, TestStatic.TEST_NOT_INITIALIZED_FLOAT, 0.0001);
    Assert.assertEquals(444_444.444_444, TestStatic.TEST_NOT_INITIALIZED_DOUBLE, 0.0001);
    Assert.assertTrue("Hello JVMHardcore" == TestStatic.TEST_NOT_INITIALIZED_OBJ);
  }

  @Test
  public void constantsTester() {
    Assert.assertEquals(Integer.MAX_VALUE, TestStatic.TEST_INT);
    Assert.assertEquals(Long.MAX_VALUE, TestStatic.TEST_LONG);
    Assert.assertEquals(Float.MAX_VALUE, TestStatic.TEST_FLOAT, 0.0001);
    Assert.assertEquals(Double.MAX_VALUE, TestStatic.TEST_DOUBLE, 0.0001);
    Assert.assertEquals("Hello world", TestStatic.TEST_STRING);
  }

  @Test
  public void resetFinal() {
    Assert.assertEquals(Integer.MAX_VALUE, TestStatic.TEST_RESET_FINAL);
    final int i = TestStatic.resetFinal(10);

    Assert.assertEquals(10, i);
    Assert.assertEquals(Integer.MAX_VALUE, TestStatic.TEST_RESET_FINAL);
    // TestStatic.TEST_PUTSTATIC = 125; Compile error but not a JVM constraint

    try {
      TestStaticUpdater.resetFinal(100);
      Assert.fail();
    } catch (IllegalAccessError e) {
      Assert.assertEquals("java.lang.IllegalAccessError", e.toString());
    }
  }
  
  @Test
  public void resetFina2() {
    try {
      TestStaticUpdater.resetFinal2(100);
      Assert.fail();
    } catch (IllegalAccessError e) {
      Assert.assertEquals("java.lang.IllegalAccessError", e.toString());
    }
  }

  @Test
  public void staticBlock() {
    Assert.assertEquals(98_765, TestStatic.TEST_STATIC_BLOCK);
  }
}
