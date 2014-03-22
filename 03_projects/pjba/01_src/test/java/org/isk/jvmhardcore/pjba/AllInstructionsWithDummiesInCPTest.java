package org.isk.jvmhardcore.pjba;

import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

public class AllInstructionsWithDummiesInCPTest {
  @Test
  public void nop() {
    AllInstructionsWithDummiesInCP.nop();
  }

  @Test
  public void aconst_null() {
    final Object o = AllInstructionsWithDummiesInCP.aconst_null();
    Assert.assertNull(o);
  }

  @Test
  public void iconst_m1() {
    final int i = AllInstructionsWithDummiesInCP.iconst_m1();
    Assert.assertEquals(-1, i);
  }

  @Test
  public void iconst_0() {
    final int i = AllInstructionsWithDummiesInCP.iconst_0();
    Assert.assertEquals(0, i);
  }

  @Test
  public void iconst_1() {
    final int i = AllInstructionsWithDummiesInCP.iconst_1();
    Assert.assertEquals(1, i);
  }

  @Test
  public void iconst_2() {
    final int i = AllInstructionsWithDummiesInCP.iconst_2();
    Assert.assertEquals(2, i);
  }

  @Test
  public void iconst_3() {
    final int i = AllInstructionsWithDummiesInCP.iconst_3();
    Assert.assertEquals(3, i);
  }

  @Test
  public void iconst_4() {
    final int i = AllInstructionsWithDummiesInCP.iconst_4();
    Assert.assertEquals(4, i);
  }

  @Test
  public void iconst_5() {
    final int i = AllInstructionsWithDummiesInCP.iconst_5();
    Assert.assertEquals(5, i);
  }

  @Test
  public void lconst_0() {
    final long l = AllInstructionsWithDummiesInCP.lconst_0();
    Assert.assertEquals(0l, l);
  }

  @Test
  public void lconst_1() {
    final long l = AllInstructionsWithDummiesInCP.lconst_1();
    Assert.assertEquals(1l, l);
  }

  @Test
  public void fconst_0() {
    final float f = AllInstructionsWithDummiesInCP.fconst_0();
    Assert.assertEquals(0f, f, 0.0001);
  }

  @Test
  public void fconst_1() {
    final float f = AllInstructionsWithDummiesInCP.fconst_1();
    Assert.assertEquals(1f, f, 0.0001);
  }

  @Test
  public void fconst_2() {
    final float f = AllInstructionsWithDummiesInCP.fconst_2();
    Assert.assertEquals(2f, f, 0.0001);
  }

  @Test
  public void dconst_0() {
    final double d = AllInstructionsWithDummiesInCP.dconst_0();
    Assert.assertEquals(0.0, d, 0.0001);
  }

  @Test
  public void dconst_1() {
    final double d = AllInstructionsWithDummiesInCP.dconst_1();
    Assert.assertEquals(1.0, d, 0.0001);
  }

  @Test
  public void bipush() {
    final int i = AllInstructionsWithDummiesInCP.bipush();
    Assert.assertEquals(125, i);
  }

  @Test
  public void sipush() {
    final int i = AllInstructionsWithDummiesInCP.sipush();
    Assert.assertEquals(5_396, i);
  }

  @Test
  public void ldc_String() {
    final String s = AllInstructionsWithDummiesInCP.ldc_String();
    Assert.assertEquals("Hello World", s);
  }

  @Test
  public void ldc_int() {
    final int i = AllInstructionsWithDummiesInCP.ldc_int();
    Assert.assertEquals(167_980_564, i);
  }

  @Test
  public void ldc_float() {
    final float f = AllInstructionsWithDummiesInCP.ldc_float();
    Assert.assertEquals(3.5f, f, 0.0001);
  }

  @Test
  public void ldc_w_String() {
    final String s = AllInstructionsWithDummiesInCP.ldc_w_String();
    Assert.assertEquals("Hello World Wide...", s);
  }

  @Test
  public void ldc_w_int() {
    final int i = AllInstructionsWithDummiesInCP.ldc_w_int();
    Assert.assertEquals(999_999_999, i);
  }

  @Test
  public void ldc_w_float() {
    final float f = AllInstructionsWithDummiesInCP.ldc_w_float();
    Assert.assertEquals(999.9999f, f, 0.0001);
  }

  @Test
  public void ldc_long() {
    final long l = AllInstructionsWithDummiesInCP.ldc_long();
    Assert.assertEquals(167_980_564_900l, l);
  }

  @Test
  public void ldc_double() {
    final double d = AllInstructionsWithDummiesInCP.ldc_double();
    Assert.assertEquals(3.578_978_979, d, 0.0001);
  }

  @Test
  public void iload() {
    final int i = AllInstructionsWithDummiesInCP.iload(true, true, true, true, true, true, true, true, true, true, 5);
    Assert.assertEquals(5, i);
  }

  @Test
  public void lload() {
    final long l = AllInstructionsWithDummiesInCP
        .lload(true, true, true, true, true, true, true, true, true, true, 15l);
    Assert.assertEquals(15l, l);
  }

  @Test
  public void fload() {
    final float f = AllInstructionsWithDummiesInCP.fload(true, true, true, true, true, true, true, true, true, true,
        5.5f);
    Assert.assertEquals(5.5f, f, 0.0001);
  }

  @Test
  public void dload() {
    final double d = AllInstructionsWithDummiesInCP.dload(true, true, true, true, true, true, true, true, true, true,
        5.9);
    Assert.assertEquals(5.9, d, 0.0001);
  }

  @Test
  public void aload() {
    final Integer i = AllInstructionsWithDummiesInCP.aload(true, true, true, true, true, true, true, true, true, true,
        89);
    Assert.assertEquals(89, i.intValue());
  }

  @Test
  public void iload_0() {
    final int i = AllInstructionsWithDummiesInCP.iload_0(5);
    Assert.assertEquals(5, i);
  }

  @Test
  public void iload_1() {
    final int i = AllInstructionsWithDummiesInCP.iload_1(true, 5);
    Assert.assertEquals(5, i);
  }

  @Test
  public void iload_2() {
    final int i = AllInstructionsWithDummiesInCP.iload_2(true, true, 5);
    Assert.assertEquals(5, i);
  }

  @Test
  public void iload_3() {
    final int i = AllInstructionsWithDummiesInCP.iload_3(true, true, true, 5);
    Assert.assertEquals(5, i);
  }

  @Test
  public void lload_0() {
    final long l = AllInstructionsWithDummiesInCP.lload_0(15l);
    Assert.assertEquals(15l, l);
  }

  @Test
  public void lload_1() {
    final long l = AllInstructionsWithDummiesInCP.lload_1(true, 15l);
    Assert.assertEquals(15l, l);
  }

  @Test
  public void lload_2() {
    final long l = AllInstructionsWithDummiesInCP.lload_2(true, true, 15l);
    Assert.assertEquals(15l, l);
  }

  @Test
  public void lload_3() {
    final long l = AllInstructionsWithDummiesInCP.lload_3(true, true, true, 15l);
    Assert.assertEquals(15l, l);
  }

  @Test
  public void fload_0() {
    final float f = AllInstructionsWithDummiesInCP.fload_0(5.5f);
    Assert.assertEquals(5.5f, f, 0.0001);
  }

  @Test
  public void fload_1() {
    final float f = AllInstructionsWithDummiesInCP.fload_1(true, 5.5f);
    Assert.assertEquals(5.5f, f, 0.0001);
  }

  @Test
  public void fload_2() {
    final float f = AllInstructionsWithDummiesInCP.fload_2(true, true, 5.5f);
    Assert.assertEquals(5.5f, f, 0.0001);
  }

  @Test
  public void fload_3() {
    final float f = AllInstructionsWithDummiesInCP.fload_3(true, true, true, 5.5f);
    Assert.assertEquals(5.5f, f, 0.0001);
  }

  @Test
  public void dload_0() {
    final double d = AllInstructionsWithDummiesInCP.dload_0(5.9);
    Assert.assertEquals(5.9, d, 0.0001);
  }

  @Test
  public void dload_1() {
    final double d = AllInstructionsWithDummiesInCP.dload_1(true, 5.9);
    Assert.assertEquals(5.9, d, 0.0001);
  }

  @Test
  public void dload_2() {
    final double d = AllInstructionsWithDummiesInCP.dload_2(true, true, 5.9);
    Assert.assertEquals(5.9, d, 0.0001);
  }

  @Test
  public void dload_3() {
    final double d = AllInstructionsWithDummiesInCP.dload_3(true, true, true, 5.9);
    Assert.assertEquals(5.9, d, 0.0001);
  }

  @Test
  public void aload_0() {
    final Integer expected = new Integer(5000);
    final Integer i = AllInstructionsWithDummiesInCP.aload_0(expected);
    Assert.assertTrue(i == expected);
  }

  @Test
  public void aload_1() {
    final Integer expected = new Integer(5000);
    final Integer i = AllInstructionsWithDummiesInCP.aload_1(true, expected);
    Assert.assertTrue(i == expected);
  }

  @Test
  public void aload_2() {
    final Integer expected = new Integer(5000);
    final Integer i = AllInstructionsWithDummiesInCP.aload_2(true, true, expected);
    Assert.assertTrue(i == expected);
  }

  @Test
  public void aload_3() {
    final Integer expected = new Integer(5000);
    final Integer i = AllInstructionsWithDummiesInCP.aload_3(true, true, true, expected);
    Assert.assertTrue(i == expected);
  }

  @Test
  public void istore() {
    final int i = AllInstructionsWithDummiesInCP.istore(2, true, true, true, true, true, true, true, true, true, 4);
    Assert.assertEquals(2, i);
  }

  @Test
  public void lstore() {
    final long l = AllInstructionsWithDummiesInCP.lstore(2l, true, true, true, true, true, true, true, true, 4l);
    Assert.assertEquals(2l, l);
  }

  @Test
  public void fstore() {
    final float f = AllInstructionsWithDummiesInCP.fstore(2.5f, true, true, true, true, true, true, true, true, true,
        4.5f);
    Assert.assertEquals(2.5f, f, 0.0001);
  }

  @Test
  public void dstore() {
    final double d = AllInstructionsWithDummiesInCP.dstore(2.98, true, true, true, true, true, true, true, true, 40.56);
    Assert.assertEquals(2.98, d, 0.0001);
  }

  @Test
  public void astore() {
    final Integer i = AllInstructionsWithDummiesInCP.astore(2, true, true, true, true, true, true, true, true, true, 4);
    Assert.assertEquals(2, i.intValue());
  }

  @Test
  public void istore_0() {
    final int i = AllInstructionsWithDummiesInCP.istore_0(2, 4);
    Assert.assertEquals(11, i);
  }

  @Test
  public void istore_1() {
    final int i = AllInstructionsWithDummiesInCP.istore_1(2, 4);
    Assert.assertEquals(9, i);
  }

  @Test
  public void istore_2() {
    final int i = AllInstructionsWithDummiesInCP.istore_2(2, 5, 6);
    Assert.assertEquals(7, i);
  }

  @Test
  public void istore_3() {
    final int i = AllInstructionsWithDummiesInCP.istore_3(2, 5, 6, 7);
    Assert.assertEquals(8, i);
  }

  @Test
  public void lstore_0() {
    final long l = AllInstructionsWithDummiesInCP.lstore_0(2l, 4l);
    Assert.assertEquals(11l, l);
  }

  @Test
  public void lstore_1() {
    final long l = AllInstructionsWithDummiesInCP.lstore_1(2, 4l);
    Assert.assertEquals(9l, l);
  }

  //
  @Test
  public void lstore_2() {
    final long l = AllInstructionsWithDummiesInCP.lstore_2(2l, 5l);
    Assert.assertEquals(3l, l);
  }

  @Test
  public void lstore_3() {
    final long l = AllInstructionsWithDummiesInCP.lstore_3(2l, 5);
    Assert.assertEquals(7l, l);
  }

  @Test
  public void fstore_0() {
    final float f = AllInstructionsWithDummiesInCP.fstore_0(2f, 4f);
    Assert.assertEquals(11f, f, 0.0001);
  }

  @Test
  public void fstore_1() {
    final float f = AllInstructionsWithDummiesInCP.fstore_1(2f, 4f);
    Assert.assertEquals(9f, f, 0.0001);
  }

  @Test
  public void fstore_2() {
    final float f = AllInstructionsWithDummiesInCP.fstore_2(2f, 5f, 6f);
    Assert.assertEquals(7f, f, 0.0001);
  }

  @Test
  public void fstore_3() {
    final float f = AllInstructionsWithDummiesInCP.fstore_3(2f, 5f, 6f, 7f);
    Assert.assertEquals(8f, f, 0.0001);
  }

  @Test
  public void dstore_0() {
    final double d = AllInstructionsWithDummiesInCP.dstore_0(2.0, 4.0);
    Assert.assertEquals(11.0, d, 0.0001);
  }

  @Test
  public void dstore_1() {
    final double d = AllInstructionsWithDummiesInCP.dstore_1(2.0f, 4.0);
    Assert.assertEquals(9.0, d, 0.0001);
  }

  @Test
  public void dstore_2() {
    final double d = AllInstructionsWithDummiesInCP.dstore_2(2.0, 5.0);
    Assert.assertEquals(3.0, d, 0.0001);
  }

  @Test
  public void dstore_3() {
    final double d = AllInstructionsWithDummiesInCP.dstore_3(2.0, 5.0f);
    Assert.assertEquals(7.0, d, 0.0001);
  }

  @Test
  public void astore_0() {
    final int i = AllInstructionsWithDummiesInCP.astore_0(2);
    Assert.assertEquals(2, i, 0.0001);
  }

  @Test
  public void astore_1() {
    final int i = AllInstructionsWithDummiesInCP.astore_1(2, 5);
    Assert.assertEquals(2, i, 0.0001);
  }

  @Test
  public void astore_2() {
    final int i = AllInstructionsWithDummiesInCP.astore_2(2, 5, 4);
    Assert.assertEquals(2, i, 0.0001);
  }

  @Test
  public void astore_3() {
    final int i = AllInstructionsWithDummiesInCP.astore_3(2, 5, 4, 7);
    Assert.assertEquals(2, i, 0.0001);
  }

  @Test
  public void pop() {
    final double d = AllInstructionsWithDummiesInCP.pop();
    Assert.assertEquals(0.0, d, 0.0001);
  }

  @Test
  public void pop2() {
    final int i = AllInstructionsWithDummiesInCP.pop2();
    Assert.assertEquals(3, i);
  }

  @Test
  public void dup() {
    final int i = AllInstructionsWithDummiesInCP.dup();
    Assert.assertEquals(2, i);
  }

  @Test
  public void dup_x1() {
    final int i = AllInstructionsWithDummiesInCP.dup_x1();
    Assert.assertEquals(1, i);
  }

  @Test
  public void dup_x2() {
    final int i = AllInstructionsWithDummiesInCP.dup_x2();
    Assert.assertEquals(1, i);
  }

  @Test
  public void dup2() {
    final double d = AllInstructionsWithDummiesInCP.dup2();
    Assert.assertEquals(2.0, d, 0.0001);
  }

  @Test
  public void dup2_x1() {
    final double d = AllInstructionsWithDummiesInCP.dup2_x1();
    Assert.assertEquals(1.0, d, 0.0001);
  }

  @Test
  public void dup2_x2() {
    final double d = AllInstructionsWithDummiesInCP.dup2_x2();
    Assert.assertEquals(1.0, d, 0.0001);
  }

  @Test
  public void swap() {
    final int i = AllInstructionsWithDummiesInCP.swap();
    Assert.assertEquals(1, i);
  }

  @Test
  public void iadd() {
    final int i = AllInstructionsWithDummiesInCP.iadd(5, 10);
    Assert.assertEquals(15, i);
  }

  @Test
  public void ladd() {
    final long l = AllInstructionsWithDummiesInCP.ladd(15l, 100l);
    Assert.assertEquals(115l, l);
  }

  @Test
  public void fadd() {
    final float f = AllInstructionsWithDummiesInCP.fadd(5.5f, 10.5f);
    Assert.assertEquals(16.0f, f, 0.0001);
  }

  @Test
  public void dadd() {
    final double d = AllInstructionsWithDummiesInCP.dadd(5.9, 10.9);
    Assert.assertEquals(16.8, d, 0.0001);
  }

  @Test
  public void isub() {
    final int i = AllInstructionsWithDummiesInCP.isub(5, 10);
    Assert.assertEquals(-5, i);
  }

  @Test
  public void lsub() {
    final long l = AllInstructionsWithDummiesInCP.lsub(15l, 100l);
    Assert.assertEquals(-85l, l);
  }

  @Test
  public void fsub() {
    final float f = AllInstructionsWithDummiesInCP.fsub(5.5f, 10.5f);
    Assert.assertEquals(-5.0f, f, 0.0001);
  }

  @Test
  public void dsub() {
    final double d = AllInstructionsWithDummiesInCP.dsub(5.9, 10.9);
    Assert.assertEquals(-5.0, d, 0.0001);
  }

  @Test
  public void imul() {
    final int i = AllInstructionsWithDummiesInCP.imul(5, 10);
    Assert.assertEquals(50, i);
  }

  @Test
  public void lmul() {
    final long l = AllInstructionsWithDummiesInCP.lmul(15l, 100l);
    Assert.assertEquals(1500l, l);
  }

  @Test
  public void fmul() {
    final float f = AllInstructionsWithDummiesInCP.fmul(5.2f, 10.5f);
    Assert.assertEquals(54.6f, f, 0.0001);
  }

  @Test
  public void dmul() {
    final double d = AllInstructionsWithDummiesInCP.dmul(5.9, 10.9);
    Assert.assertEquals(64.31, d, 0.0001);
  }

  @Test
  public void idiv() {
    final int i = AllInstructionsWithDummiesInCP.idiv(10, 5);
    Assert.assertEquals(2, i);
  }

  @Test
  public void ldiv() {
    final long l = AllInstructionsWithDummiesInCP.ldiv(150l, 10l);
    Assert.assertEquals(15l, l);
  }

  @Test
  public void fdiv() {
    final float f = AllInstructionsWithDummiesInCP.fdiv(10.5f, 5.5f);
    Assert.assertEquals(1.90f, f, 0.01);
  }

  @Test
  public void ddiv() {
    final double d = AllInstructionsWithDummiesInCP.ddiv(10.0, 2.0);
    Assert.assertEquals(5.0, d, 0.0001);
  }

  @Test
  public void irem() {
    final int i = AllInstructionsWithDummiesInCP.irem(7, 2);
    Assert.assertEquals(1, i);
  }

  @Test
  public void lrem() {
    final long l = AllInstructionsWithDummiesInCP.lrem(155l, 10l);
    Assert.assertEquals(5l, l);
  }

  @Test
  public void frem() {
    final float f = AllInstructionsWithDummiesInCP.frem(0.5f, 0.3f);
    Assert.assertEquals(0.2f, f, 0.01);
  }

  @Test
  public void drem() {
    final double d = AllInstructionsWithDummiesInCP.drem(17.5, 15.3);
    Assert.assertEquals(2.2, d, 0.01);
  }

  @Test
  public void ineg() {
    final int i = AllInstructionsWithDummiesInCP.ineg(7);
    Assert.assertEquals(-7, i);
  }

  @Test
  public void lneg() {
    final long l = AllInstructionsWithDummiesInCP.lneg(-155l);
    Assert.assertEquals(155l, l);
  }

  @Test
  public void fneg() {
    final float f = AllInstructionsWithDummiesInCP.fneg(0.5f);
    Assert.assertEquals(-0.5f, f, 0.0001);
  }

  @Test
  public void dneg() {
    final double d = AllInstructionsWithDummiesInCP.dneg(-17.5);
    Assert.assertEquals(17.5, d, 0.0001);
  }

  // ..., value1, value2 → ..., result
  // An int result is calculated by shifting value1 left by s bit positions,
  // where s is the value of the low 5 bits of value2. (5 bits => 0 to 31)
  @Test
  public void ishl0() {
    final int i = AllInstructionsWithDummiesInCP.ishl(10, 32);
    Assert.assertEquals(10, i);
  }

  @Test
  public void ishl1() {
    final int i = AllInstructionsWithDummiesInCP.ishl(1, 31);
    Assert.assertEquals(Integer.MIN_VALUE, i);
  }

  @Test
  public void lshl0() {
    final int i = AllInstructionsWithDummiesInCP.ishl(10, 64);
    Assert.assertEquals(10, i);
  }

  @Test
  public void lshl1() {
    final long l = AllInstructionsWithDummiesInCP.lshl(1, 63);
    Assert.assertEquals(Long.MIN_VALUE, l);
  }

  @Test
  public void ishr0() {
    final int i = AllInstructionsWithDummiesInCP.ishr(10, 32);
    Assert.assertEquals(10, i);
  }

  @Test
  public void ishr1() {
    final int i = AllInstructionsWithDummiesInCP.ishr(Integer.MIN_VALUE, 31);
    Assert.assertEquals(-1, i);
  }

  @Test
  public void lshr0() {
    final long l = AllInstructionsWithDummiesInCP.lshr(10, 64);
    Assert.assertEquals(10, l);
  }

  @Test
  public void lshr1() {
    final long l = AllInstructionsWithDummiesInCP.lshr(Long.MIN_VALUE, 63);
    Assert.assertEquals(-1, l);
  }

  @Test
  public void iushr() {
    final int i = AllInstructionsWithDummiesInCP.iushr(Integer.MIN_VALUE, 31);
    Assert.assertEquals(1, i);
  }

  @Test
  public void lushr() {
    final long l = AllInstructionsWithDummiesInCP.lushr(Long.MIN_VALUE, 63);
    Assert.assertEquals(1, l);
  }

  @Test
  public void iand() {
    final int i = AllInstructionsWithDummiesInCP.iand(5, 4);
    Assert.assertEquals(4, i);
  }

  @Test
  public void land() {
    final long l = AllInstructionsWithDummiesInCP.land(Long.MAX_VALUE, Long.MIN_VALUE);
    Assert.assertEquals(0, l);
  }

  @Test
  public void ior() {
    final int i = AllInstructionsWithDummiesInCP.ior(5, 4);
    Assert.assertEquals(5, i);
  }

  @Test
  public void lor() {
    final long l = AllInstructionsWithDummiesInCP.lor(Long.MAX_VALUE, Long.MIN_VALUE);
    Assert.assertEquals(-1, l);
  }

  @Test
  public void ixor() {
    final int i = AllInstructionsWithDummiesInCP.ixor(5, 4);
    Assert.assertEquals(1, i);
  }

  @Test
  public void lxor() {
    final long l = AllInstructionsWithDummiesInCP.lxor(Long.MAX_VALUE, Long.MIN_VALUE);
    Assert.assertEquals(-1, l);
  }

  @Test
  public void iinc() {
    final int i = AllInstructionsWithDummiesInCP.iinc(10);
    Assert.assertEquals(5, i);
  }

  @Test
  public void i2l() {
    final long l = AllInstructionsWithDummiesInCP.i2l(5, 4l);
    Assert.assertEquals(9, l);
  }

  @Test
  public void i2f() {
    final float f = AllInstructionsWithDummiesInCP.i2f(5, 2);
    Assert.assertEquals(2.5f, f, 0.0001);
  }

  @Test
  public void i2d() {
    final double d = AllInstructionsWithDummiesInCP.i2d(10, 3);
    Assert.assertEquals(3.33, d, 0.01);
  }

  @Test
  public void l2i() {
    final int i = AllInstructionsWithDummiesInCP.l2i(Long.MAX_VALUE);
    Assert.assertEquals(-1, i);
  }

  @Test
  public void l2f() {
    final float f = AllInstructionsWithDummiesInCP.l2f(5l, 2l);
    Assert.assertEquals(2.5f, f, 0.0001);
  }

  @Test
  public void l2d() {
    final double d = AllInstructionsWithDummiesInCP.l2d(10l, 3l);
    Assert.assertEquals(3.33, d, 0.01);
  }

  @Test
  public void f2i() {
    final int i = AllInstructionsWithDummiesInCP.f2i(4.56f);
    Assert.assertEquals(4, i);
  }

  @Test
  public void f2l() {
    final long l = AllInstructionsWithDummiesInCP.f2l(45.908f);
    Assert.assertEquals(45, l);
  }

  @Test
  public void f2d() {
    final double d = AllInstructionsWithDummiesInCP.f2d(10.5f, 5.6f);
    Assert.assertEquals(16.1, d, 0.01);
  }

  @Test
  public void d2i() {
    final int i = AllInstructionsWithDummiesInCP.d2i(4.56f);
    Assert.assertEquals(4, i);
  }

  @Test
  public void d2l() {
    final long l = AllInstructionsWithDummiesInCP.d2l(45.908f);
    Assert.assertEquals(45, l);
  }

  @Test
  public void d2f() {
    final float f = AllInstructionsWithDummiesInCP.d2f(10.5f, 5.6f);
    Assert.assertEquals(16.1f, f, 0.01);
  }

  @Test
  public void i2b() {
    final int i = AllInstructionsWithDummiesInCP.i2b(Integer.MAX_VALUE);
    Assert.assertEquals(-1, i);
  }

  @Test
  public void i2c() {
    final int i = AllInstructionsWithDummiesInCP.i2c(Integer.MAX_VALUE);
    Assert.assertEquals(65_535, i);
  }

  @Test
  public void i2s() {
    final int i = AllInstructionsWithDummiesInCP.i2s(Integer.MAX_VALUE);
    Assert.assertEquals(-1, i);
  }

  @Test
  public void lcmp() {
    final int i1 = AllInstructionsWithDummiesInCP.lcmp(5, 10);
    Assert.assertEquals(-1, i1);

    final int i2 = AllInstructionsWithDummiesInCP.lcmp(5, 5);
    Assert.assertEquals(0, i2);

    final int i3 = AllInstructionsWithDummiesInCP.lcmp(10, 5);
    Assert.assertEquals(1, i3);
  }

  @Test
  public void fcmpl() {
    final int i1 = AllInstructionsWithDummiesInCP.fcmpl(5.5f, 10.1f);
    Assert.assertEquals(-1, i1);

    final int i2 = AllInstructionsWithDummiesInCP.fcmpl(5.5f, 5.5f);
    Assert.assertEquals(0, i2);

    final int i3 = AllInstructionsWithDummiesInCP.fcmpl(10.1f, 5.5f);
    Assert.assertEquals(1, i3);

    // returns -1 if one of the value is NaN
    final int i4 = AllInstructionsWithDummiesInCP.fcmpl(Float.NaN, 5.5f);
    Assert.assertEquals(-1, i4);

    final int i5 = AllInstructionsWithDummiesInCP.fcmpl(5.5f, Float.NaN);
    Assert.assertEquals(-1, i5);
  }

  @Test
  public void fcmpg() {
    final int i1 = AllInstructionsWithDummiesInCP.fcmpg(5.5f, 10.1f);
    Assert.assertEquals(-1, i1);

    final int i2 = AllInstructionsWithDummiesInCP.fcmpg(5.5f, 5.5f);
    Assert.assertEquals(0, i2);

    final int i3 = AllInstructionsWithDummiesInCP.fcmpg(10.1f, 5.5f);
    Assert.assertEquals(1, i3);

    // returns 1 if one of the value is NaN
    final int i4 = AllInstructionsWithDummiesInCP.fcmpg(Float.NaN, 5.5f);
    Assert.assertEquals(1, i4);

    final int i5 = AllInstructionsWithDummiesInCP.fcmpg(5.5f, Float.NaN);
    Assert.assertEquals(1, i5);
  }

  @Test
  public void dcmpl() {
    final int i1 = AllInstructionsWithDummiesInCP.dcmpl(5.5, 10.1);
    Assert.assertEquals(-1, i1);

    final int i2 = AllInstructionsWithDummiesInCP.dcmpl(5.5, 5.5);
    Assert.assertEquals(0, i2);

    final int i3 = AllInstructionsWithDummiesInCP.dcmpl(10.1, 5.5);
    Assert.assertEquals(1, i3);

    // returns -1 if one of the value is NaN
    final int i4 = AllInstructionsWithDummiesInCP.dcmpl(Double.NaN, 5.5);
    Assert.assertEquals(-1, i4);

    final int i5 = AllInstructionsWithDummiesInCP.dcmpl(5.5, Double.NaN);
    Assert.assertEquals(-1, i5);
  }

  @Test
  public void dcmpg() {
    final int i1 = AllInstructionsWithDummiesInCP.dcmpg(5.5, 10.1);
    Assert.assertEquals(-1, i1);

    final int i2 = AllInstructionsWithDummiesInCP.dcmpg(5.5, 5.5);
    Assert.assertEquals(0, i2);

    final int i3 = AllInstructionsWithDummiesInCP.dcmpg(10.1, 5.5);
    Assert.assertEquals(1, i3);

    // returns 1 if one of the value is NaN
    final int i4 = AllInstructionsWithDummiesInCP.dcmpg(Double.NaN, 5.5);
    Assert.assertEquals(1, i4);

    final int i5 = AllInstructionsWithDummiesInCP.dcmpg(5.5, Double.NaN);
    Assert.assertEquals(1, i5);
  }

  @Test
  public void ifeq() {
    final boolean b1 = AllInstructionsWithDummiesInCP.ifeq(0);
    Assert.assertTrue(b1);

    final boolean b2 = AllInstructionsWithDummiesInCP.ifeq(-5);
    Assert.assertFalse(b2);

    final boolean b3 = AllInstructionsWithDummiesInCP.ifeq(5);
    Assert.assertFalse(b3);
  }

  @Test
  public void ifne() {
    final boolean b1 = AllInstructionsWithDummiesInCP.ifne(5);
    Assert.assertTrue(b1);

    final boolean b2 = AllInstructionsWithDummiesInCP.ifne(-5);
    Assert.assertTrue(b2);

    final boolean b3 = AllInstructionsWithDummiesInCP.ifne(0);
    Assert.assertFalse(b3);
  }

  @Test
  public void iflt() {
    final boolean b1 = AllInstructionsWithDummiesInCP.iflt(5);
    Assert.assertFalse(b1);

    final boolean b2 = AllInstructionsWithDummiesInCP.iflt(-5);
    Assert.assertTrue(b2);

    final boolean b3 = AllInstructionsWithDummiesInCP.iflt(0);
    Assert.assertFalse(b3);
  }

  @Test
  public void ifge() {
    final boolean b1 = AllInstructionsWithDummiesInCP.ifge(5);
    Assert.assertTrue(b1);

    final boolean b2 = AllInstructionsWithDummiesInCP.ifge(-5);
    Assert.assertFalse(b2);

    final boolean b3 = AllInstructionsWithDummiesInCP.ifge(0);
    Assert.assertTrue(b3);
  }

  @Test
  public void ifgt() {
    final boolean b1 = AllInstructionsWithDummiesInCP.ifgt(5);
    Assert.assertTrue(b1);

    final boolean b2 = AllInstructionsWithDummiesInCP.ifgt(-5);
    Assert.assertFalse(b2);

    final boolean b3 = AllInstructionsWithDummiesInCP.ifgt(0);
    Assert.assertFalse(b3);
  }

  @Test
  public void ifle() {
    final boolean b1 = AllInstructionsWithDummiesInCP.ifle(5);
    Assert.assertFalse(b1);

    final boolean b2 = AllInstructionsWithDummiesInCP.ifle(-5);
    Assert.assertTrue(b2);

    final boolean b3 = AllInstructionsWithDummiesInCP.ifle(0);
    Assert.assertTrue(b3);
  }

  @Test
  public void if_icmpeq() {
    final boolean b1 = AllInstructionsWithDummiesInCP.if_icmpeq(5, 5);
    Assert.assertTrue(b1);

    final boolean b2 = AllInstructionsWithDummiesInCP.if_icmpeq(-5, 5);
    Assert.assertFalse(b2);

    final boolean b3 = AllInstructionsWithDummiesInCP.if_icmpeq(5, -5);
    Assert.assertFalse(b3);
  }

  @Test
  public void if_icmpne() {
    final boolean b1 = AllInstructionsWithDummiesInCP.if_icmpne(5, 5);
    Assert.assertFalse(b1);

    final boolean b2 = AllInstructionsWithDummiesInCP.if_icmpne(-5, 5);
    Assert.assertTrue(b2);

    final boolean b3 = AllInstructionsWithDummiesInCP.if_icmpne(5, -5);
    Assert.assertTrue(b3);
  }

  @Test
  public void if_icmplt() {
    final boolean b1 = AllInstructionsWithDummiesInCP.if_icmplt(5, 5);
    Assert.assertFalse(b1);

    final boolean b2 = AllInstructionsWithDummiesInCP.if_icmplt(-5, 5);
    Assert.assertTrue(b2);

    final boolean b3 = AllInstructionsWithDummiesInCP.if_icmplt(5, -5);
    Assert.assertFalse(b3);
  }

  @Test
  public void if_icmpge() {
    final boolean b1 = AllInstructionsWithDummiesInCP.if_icmpge(5, 5);
    Assert.assertTrue(b1);

    final boolean b2 = AllInstructionsWithDummiesInCP.if_icmpge(-5, 5);
    Assert.assertFalse(b2);

    final boolean b3 = AllInstructionsWithDummiesInCP.if_icmpge(5, -5);
    Assert.assertTrue(b3);
  }

  @Test
  public void if_icmpgt() {
    final boolean b1 = AllInstructionsWithDummiesInCP.if_icmpgt(5, 5);
    Assert.assertFalse(b1);

    final boolean b2 = AllInstructionsWithDummiesInCP.if_icmpgt(-5, 5);
    Assert.assertFalse(b2);

    final boolean b3 = AllInstructionsWithDummiesInCP.if_icmpgt(5, -5);
    Assert.assertTrue(b3);
  }

  @Test
  public void if_icmple() {
    final boolean b1 = AllInstructionsWithDummiesInCP.if_icmple(5, 5);
    Assert.assertTrue(b1);

    final boolean b2 = AllInstructionsWithDummiesInCP.if_icmple(-5, 5);
    Assert.assertTrue(b2);

    final boolean b3 = AllInstructionsWithDummiesInCP.if_icmple(5, -5);
    Assert.assertFalse(b3);
  }

  @Test
  public void if_acmpeq() {
    final Object o1 = new Object();
    final Object o2 = new Object();

    final boolean b1 = AllInstructionsWithDummiesInCP.if_acmpeq(o1, o1);
    Assert.assertTrue(b1);

    final boolean b2 = AllInstructionsWithDummiesInCP.if_acmpeq(o2, o1);
    Assert.assertFalse(b2);
  }

  @Test
  public void if_acmpne() {
    final Object o1 = new Object();
    final Object o2 = new Object();

    final boolean b1 = AllInstructionsWithDummiesInCP.if_acmpne(o1, o1);
    Assert.assertFalse(b1);

    final boolean b2 = AllInstructionsWithDummiesInCP.if_acmpne(o1, o2);
    Assert.assertTrue(b2);
  }

  @Test
  public void goto_before() {
    final int i = AllInstructionsWithDummiesInCP.goto_before();
    Assert.assertEquals(45, i);
  }

  @Test
  public void goto_after() {
    final boolean b1 = AllInstructionsWithDummiesInCP.goto_after(10, 100);
    Assert.assertTrue(b1);

    final boolean b2 = AllInstructionsWithDummiesInCP.goto_after(100, 10);
    Assert.assertFalse(b2);
  }

  @Test
  public void tableswitch() {
    final int i1 = AllInstructionsWithDummiesInCP.tableswitch(5);
    Assert.assertEquals(10, i1);

    final int i2 = AllInstructionsWithDummiesInCP.tableswitch(6);
    Assert.assertEquals(12, i2);

    final int i3 = AllInstructionsWithDummiesInCP.tableswitch(7);
    Assert.assertEquals(14, i3);

    final int i4 = AllInstructionsWithDummiesInCP.tableswitch(1);
    Assert.assertEquals(100, i4);
  }

  @Test
  public void lookupswitch() {
    final int i1 = AllInstructionsWithDummiesInCP.lookupswitch(5);
    Assert.assertEquals(10, i1);

    final int i2 = AllInstructionsWithDummiesInCP.lookupswitch(10);
    Assert.assertEquals(20, i2);

    final int i3 = AllInstructionsWithDummiesInCP.lookupswitch(15);
    Assert.assertEquals(30, i3);

    final int i4 = AllInstructionsWithDummiesInCP.tableswitch(1);
    Assert.assertEquals(100, i4);
  }

  @Test
  public void getstatic() {
    final int i = AllInstructionsWithDummiesInCP.getstatic();
    Assert.assertEquals(Integer.MAX_VALUE, i);
  }

  @Test
  public void putstatic() {
    final int i = AllInstructionsWithDummiesInCP.putstatic(10);
    Assert.assertEquals(10, i);
  }

  @Test
  public void invokestatic() {
    final int i1 = AllInstructionsWithDummiesInCP.invokestatic(10, 5, 9);
    Assert.assertEquals(19, i1);

    final int i2 = AllInstructionsWithDummiesInCP.invokestatic(5, 10, 9);
    Assert.assertEquals(19, i2);
  }

  @Test
  public void wide_iinc() {
    final long l = AllInstructionsWithDummiesInCP.wide_iinc(10_000);
    Assert.assertEquals(((long) -20_000 << 32) | 6, l);
  }

  @Test
  public void wide_istore_iload() {
    final int i = AllInstructionsWithDummiesInCP.wide_istore_iload();
    Assert.assertEquals(3, i);
  }

  @Test
  public void wide_lstore_lload() {
    final long l = AllInstructionsWithDummiesInCP.wide_lstore_lload();
    Assert.assertEquals(1, l);
  }

  @Test
  public void wide_fstore_fload() {
    final float f = AllInstructionsWithDummiesInCP.wide_fstore_fload();
    Assert.assertEquals(1f, f, 0.0001);
  }

  @Test
  public void wide_dstore_dload() {
    final double d = AllInstructionsWithDummiesInCP.wide_dstore_dload();
    Assert.assertEquals(1, d, 0.0001);
  }

  @Test
  public void wide_astore_aload() {
    final Object o = AllInstructionsWithDummiesInCP.wide_astore_aload();
    Assert.assertNull(o);
  }

  @Test
  public void ifnull() {
    final boolean b1 = AllInstructionsWithDummiesInCP.ifnull(null);
    Assert.assertTrue(b1);

    final boolean b2 = AllInstructionsWithDummiesInCP.ifnull(new Object());
    Assert.assertFalse(b2);

    final boolean b3 = AllInstructionsWithDummiesInCP.ifnull(new LinkedList<>());
    Assert.assertFalse(b3);
  }

  @Test
  public void ifnonnull() {
    final boolean b1 = AllInstructionsWithDummiesInCP.ifnonnull(null);
    Assert.assertFalse(b1);

    final boolean b2 = AllInstructionsWithDummiesInCP.ifnonnull(new Object());
    Assert.assertTrue(b2);

    final boolean b3 = AllInstructionsWithDummiesInCP.ifnonnull(new LinkedList<>());
    Assert.assertTrue(b3);
  }

  @Test
  public void goto_w_before() {
    final int i = AllInstructionsWithDummiesInCP.goto_w_before();
    Assert.assertEquals(45, i);
  }

  @Test
  public void goto_w_after() {
    final boolean b1 = AllInstructionsWithDummiesInCP.goto_w_after(10, 100);
    Assert.assertTrue(b1);

    final boolean b2 = AllInstructionsWithDummiesInCP.goto_w_after(100, 10);
    Assert.assertFalse(b2);
  }

  @Test
  public void istore_load_unsigned() {
    final int i = AllInstructionsWithDummiesInCP.istore_load_unsigned();
    Assert.assertEquals(7_687, i);
  }

  @Test
  public void lstore_load_unsigned() {
    final long l = AllInstructionsWithDummiesInCP.lstore_load_unsigned();
    Assert.assertEquals(7_687_000, l);
  }

  @Test
  public void fstore_load_unsigned() {
    final float f = AllInstructionsWithDummiesInCP.fstore_load_unsigned();
    Assert.assertEquals(134.89f, f, 0.0001);
  }

  @Test
  public void dstore_load_unsigned() {
    final double d = AllInstructionsWithDummiesInCP.dstore_load_unsigned();
    Assert.assertEquals(33.33, d, 0.0001);
  }

  @Test
  public void astore_load_unsigned() {
    final Object o = AllInstructionsWithDummiesInCP.astore_load_unsigned();
    Assert.assertNull(o);
  }

  @Test
  public void constantsTester() {
    Assert.assertEquals(Integer.MAX_VALUE, AllInstructionsWithDummiesInCP.TEST_INT);
    Assert.assertEquals(Long.MAX_VALUE, AllInstructionsWithDummiesInCP.TEST_LONG);
    Assert.assertEquals(Float.MAX_VALUE, AllInstructionsWithDummiesInCP.TEST_FLOAT, 0.0001);
    Assert.assertEquals(Double.MAX_VALUE, AllInstructionsWithDummiesInCP.TEST_DOUBLE, 0.0001);
    Assert.assertEquals("Hello world", AllInstructionsWithDummiesInCP.TEST_STRING);

    final int i = AllInstructionsWithDummiesInCP.getIntConstantValue();
    Assert.assertEquals(Integer.MAX_VALUE, i);

    final long l = AllInstructionsWithDummiesInCP.getLongConstantValue();
    Assert.assertEquals(Long.MAX_VALUE, l);

    final float f = AllInstructionsWithDummiesInCP.getFloatConstantValue();
    Assert.assertEquals(Float.MAX_VALUE, f, 0.0001);

    final double d = AllInstructionsWithDummiesInCP.getDoubleConstantValue();
    Assert.assertEquals(Double.MAX_VALUE, d, 0.0001);

    final String s = AllInstructionsWithDummiesInCP.getStringConstantValue();
    Assert.assertEquals("Hello world", s);
  }

  @Test
  public void staticBlock() {
    Assert.assertEquals(98_765, AllInstructionsWithDummiesInCP.STATIC_BLOCK);
  }
}
