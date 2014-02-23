package org.isk.jvmhardcore.pjba;

import org.junit.Assert;
import org.junit.Test;

public class AllInstructionsWithoutDummiesInCPTest {
  @Test
  public void nop() {
    AllInstructionsWithoutDummiesInCP.nop();
  }

  @Test
  public void aconst_null() {
    final Object o = AllInstructionsWithoutDummiesInCP.aconst_null();
    Assert.assertNull(o);
  }

  @Test
  public void iconst_m1() {
    final int i = AllInstructionsWithoutDummiesInCP.iconst_m1();
    Assert.assertEquals(-1, i);
  }

  @Test
  public void iconst_0() {
    final int i = AllInstructionsWithoutDummiesInCP.iconst_0();
    Assert.assertEquals(0, i);
  }

  @Test
  public void iconst_1() {
    final int i = AllInstructionsWithoutDummiesInCP.iconst_1();
    Assert.assertEquals(1, i);
  }

  @Test
  public void iconst_2() {
    final int i = AllInstructionsWithoutDummiesInCP.iconst_2();
    Assert.assertEquals(2, i);
  }

  @Test
  public void iconst_3() {
    final int i = AllInstructionsWithoutDummiesInCP.iconst_3();
    Assert.assertEquals(3, i);
  }

  @Test
  public void iconst_4() {
    final int i = AllInstructionsWithoutDummiesInCP.iconst_4();
    Assert.assertEquals(4, i);
  }

  @Test
  public void iconst_5() {
    final int i = AllInstructionsWithoutDummiesInCP.iconst_5();
    Assert.assertEquals(5, i);
  }

  @Test
  public void lconst_0() {
    final long l = AllInstructionsWithoutDummiesInCP.lconst_0();
    Assert.assertEquals(0l, l);
  }

  @Test
  public void lconst_1() {
    final long l = AllInstructionsWithoutDummiesInCP.lconst_1();
    Assert.assertEquals(1l, l);
  }

  @Test
  public void fconst_0() {
    final float f = AllInstructionsWithoutDummiesInCP.fconst_0();
    Assert.assertEquals(0f, f, 0.0001);
  }

  @Test
  public void fconst_1() {
    final float f = AllInstructionsWithoutDummiesInCP.fconst_1();
    Assert.assertEquals(1f, f, 0.0001);
  }

  @Test
  public void fconst_2() {
    final float f = AllInstructionsWithoutDummiesInCP.fconst_2();
    Assert.assertEquals(2f, f, 0.0001);
  }

  @Test
  public void dconst_0() {
    final double d = AllInstructionsWithoutDummiesInCP.dconst_0();
    Assert.assertEquals(0.0, d, 0.0001);
  }

  @Test
  public void dconst_1() {
    final double d = AllInstructionsWithoutDummiesInCP.dconst_1();
    Assert.assertEquals(1.0, d, 0.0001);
  }

  @Test
  public void bipush() {
    final int i = AllInstructionsWithoutDummiesInCP.bipush();
    Assert.assertEquals(125, i);
  }

  @Test
  public void sipush() {
    final int i = AllInstructionsWithoutDummiesInCP.sipush();
    Assert.assertEquals(5_396, i);
  }

  @Test
  public void ldc_String() {
    final String s = AllInstructionsWithoutDummiesInCP.ldc_String();
    Assert.assertEquals("Hello World", s);
  }

  @Test
  public void ldc_int() {
    final int i = AllInstructionsWithoutDummiesInCP.ldc_int();
    Assert.assertEquals(167_980_564, i);
  }

  @Test
  public void ldc_float() {
    final float f = AllInstructionsWithoutDummiesInCP.ldc_float();
    Assert.assertEquals(3.5f, f, 0.0001);
  }

  @Test
  public void ldc_long() {
    final long l = AllInstructionsWithoutDummiesInCP.ldc_long();
    Assert.assertEquals(167_980_564_900l, l);
  }

  @Test
  public void ldc_double() {
    final double d = AllInstructionsWithoutDummiesInCP.ldc_double();
    Assert.assertEquals(3.578_978_979, d, 0.0001);
  }

  @Test
  public void iload() {
    final int i = AllInstructionsWithoutDummiesInCP
        .iload(true, true, true, true, true, true, true, true, true, true, 5);
    Assert.assertEquals(5, i);
  }

  @Test
  public void lload() {
    final long l = AllInstructionsWithoutDummiesInCP.lload(true, true, true, true, true, true, true, true, true, true,
        15l);
    Assert.assertEquals(15l, l);
  }

  @Test
  public void fload() {
    final float f = AllInstructionsWithoutDummiesInCP.fload(true, true, true, true, true, true, true, true, true, true,
        5.5f);
    Assert.assertEquals(5.5f, f, 0.0001);
  }

  @Test
  public void dload() {
    final double d = AllInstructionsWithoutDummiesInCP.dload(true, true, true, true, true, true, true, true, true,
        true, 5.9);
    Assert.assertEquals(5.9, d, 0.0001);
  }

  @Test
  public void aload() {
    final Integer i = AllInstructionsWithoutDummiesInCP.aload(true, true, true, true, true, true, true, true, true,
        true, 89);
    Assert.assertEquals(89, i.intValue());
  }

  @Test
  public void iload_0() {
    final int i = AllInstructionsWithoutDummiesInCP.iload_0(5);
    Assert.assertEquals(5, i);
  }

  @Test
  public void iload_1() {
    final int i = AllInstructionsWithoutDummiesInCP.iload_1(true, 5);
    Assert.assertEquals(5, i);
  }

  @Test
  public void iload_2() {
    final int i = AllInstructionsWithoutDummiesInCP.iload_2(true, true, 5);
    Assert.assertEquals(5, i);
  }

  @Test
  public void iload_3() {
    final int i = AllInstructionsWithoutDummiesInCP.iload_3(true, true, true, 5);
    Assert.assertEquals(5, i);
  }

  @Test
  public void lload_0() {
    final long l = AllInstructionsWithoutDummiesInCP.lload_0(15l);
    Assert.assertEquals(15l, l);
  }

  @Test
  public void lload_1() {
    final long l = AllInstructionsWithoutDummiesInCP.lload_1(true, 15l);
    Assert.assertEquals(15l, l);
  }

  @Test
  public void lload_2() {
    final long l = AllInstructionsWithoutDummiesInCP.lload_2(true, true, 15l);
    Assert.assertEquals(15l, l);
  }

  @Test
  public void lload_3() {
    final long l = AllInstructionsWithoutDummiesInCP.lload_3(true, true, true, 15l);
    Assert.assertEquals(15l, l);
  }

  @Test
  public void fload_0() {
    final float f = AllInstructionsWithoutDummiesInCP.fload_0(5.5f);
    Assert.assertEquals(5.5f, f, 0.0001);
  }

  @Test
  public void fload_1() {
    final float f = AllInstructionsWithoutDummiesInCP.fload_1(true, 5.5f);
    Assert.assertEquals(5.5f, f, 0.0001);
  }

  @Test
  public void fload_2() {
    final float f = AllInstructionsWithoutDummiesInCP.fload_2(true, true, 5.5f);
    Assert.assertEquals(5.5f, f, 0.0001);
  }

  @Test
  public void fload_3() {
    final float f = AllInstructionsWithoutDummiesInCP.fload_3(true, true, true, 5.5f);
    Assert.assertEquals(5.5f, f, 0.0001);
  }

  @Test
  public void dload_0() {
    final double d = AllInstructionsWithoutDummiesInCP.dload_0(5.9);
    Assert.assertEquals(5.9, d, 0.0001);
  }

  @Test
  public void dload_1() {
    final double d = AllInstructionsWithoutDummiesInCP.dload_1(true, 5.9);
    Assert.assertEquals(5.9, d, 0.0001);
  }

  @Test
  public void dload_2() {
    final double d = AllInstructionsWithoutDummiesInCP.dload_2(true, true, 5.9);
    Assert.assertEquals(5.9, d, 0.0001);
  }

  @Test
  public void dload_3() {
    final double d = AllInstructionsWithoutDummiesInCP.dload_3(true, true, true, 5.9);
    Assert.assertEquals(5.9, d, 0.0001);
  }

  @Test
  public void aload_0() {
    final Integer expected = new Integer(5000);
    final Integer i = AllInstructionsWithoutDummiesInCP.aload_0(expected);
    Assert.assertTrue(i == expected);
  }

  @Test
  public void aload_1() {
    final Integer expected = new Integer(5000);
    final Integer i = AllInstructionsWithoutDummiesInCP.aload_1(true, expected);
    Assert.assertTrue(i == expected);
  }

  @Test
  public void aload_2() {
    final Integer expected = new Integer(5000);
    final Integer i = AllInstructionsWithoutDummiesInCP.aload_2(true, true, expected);
    Assert.assertTrue(i == expected);
  }

  @Test
  public void aload_3() {
    final Integer expected = new Integer(5000);
    final Integer i = AllInstructionsWithoutDummiesInCP.aload_3(true, true, true, expected);
    Assert.assertTrue(i == expected);
  }

  @Test
  public void istore() {
    final int i = AllInstructionsWithoutDummiesInCP.istore(2, true, true, true, true, true, true, true, true, true, 4);
    Assert.assertEquals(2, i);
  }

  @Test
  public void lstore() {
    final long l = AllInstructionsWithoutDummiesInCP.lstore(2l, true, true, true, true, true, true, true, true, 4l);
    Assert.assertEquals(2l, l);
  }

  @Test
  public void fstore() {
    final float f = AllInstructionsWithoutDummiesInCP.fstore(2.5f, true, true, true, true, true, true, true, true,
        true, 4.5f);
    Assert.assertEquals(2.5f, f, 0.0001);
  }

  @Test
  public void dstore() {
    final double d = AllInstructionsWithoutDummiesInCP.dstore(2.98, true, true, true, true, true, true, true, true,
        40.56);
    Assert.assertEquals(2.98, d, 0.0001);
  }

  @Test
  public void astore() {
    final Integer i = AllInstructionsWithoutDummiesInCP.astore(2, true, true, true, true, true, true, true, true, true,
        4);
    Assert.assertEquals(2, i.intValue());
  }

  @Test
  public void istore_0() {
    final int i = AllInstructionsWithoutDummiesInCP.istore_0(2, 4);
    Assert.assertEquals(11, i);
  }

  @Test
  public void istore_1() {
    final int i = AllInstructionsWithoutDummiesInCP.istore_1(2, 4);
    Assert.assertEquals(9, i);
  }

  @Test
  public void istore_2() {
    final int i = AllInstructionsWithoutDummiesInCP.istore_2(2, 5, 6);
    Assert.assertEquals(7, i);
  }

  @Test
  public void istore_3() {
    final int i = AllInstructionsWithoutDummiesInCP.istore_3(2, 5, 6, 7);
    Assert.assertEquals(8, i);
  }

  @Test
  public void lstore_0() {
    final long l = AllInstructionsWithoutDummiesInCP.lstore_0(2l, 4l);
    Assert.assertEquals(11l, l);
  }

  @Test
  public void lstore_1() {
    final long l = AllInstructionsWithoutDummiesInCP.lstore_1(2, 4l);
    Assert.assertEquals(9l, l);
  }

  @Test
  public void lstore_2() {
    final long l = AllInstructionsWithoutDummiesInCP.lstore_2(2l, 5l);
    Assert.assertEquals(3l, l);
  }

  @Test
  public void lstore_3() {
    final long l = AllInstructionsWithoutDummiesInCP.lstore_3(2l, 5);
    Assert.assertEquals(7l, l);
  }

  @Test
  public void fstore_0() {
    final float f = AllInstructionsWithoutDummiesInCP.fstore_0(2f, 4f);
    Assert.assertEquals(11f, f, 0.0001);
  }

  @Test
  public void fstore_1() {
    final float f = AllInstructionsWithoutDummiesInCP.fstore_1(2f, 4f);
    Assert.assertEquals(9f, f, 0.0001);
  }

  @Test
  public void fstore_2() {
    final float f = AllInstructionsWithoutDummiesInCP.fstore_2(2f, 5f, 6f);
    Assert.assertEquals(7f, f, 0.0001);
  }

  @Test
  public void fstore_3() {
    final float f = AllInstructionsWithoutDummiesInCP.fstore_3(2f, 5f, 6f, 7f);
    Assert.assertEquals(8f, f, 0.0001);
  }

  @Test
  public void dstore_0() {
    final double d = AllInstructionsWithoutDummiesInCP.dstore_0(2.0, 4.0);
    Assert.assertEquals(11.0, d, 0.0001);
  }

  @Test
  public void dstore_1() {
    final double d = AllInstructionsWithoutDummiesInCP.dstore_1(2.0f, 4.0);
    Assert.assertEquals(9.0, d, 0.0001);
  }

  @Test
  public void dstore_2() {
    final double d = AllInstructionsWithoutDummiesInCP.dstore_2(2.0, 5.0);
    Assert.assertEquals(3.0, d, 0.0001);
  }

  @Test
  public void dstore_3() {
    final double d = AllInstructionsWithoutDummiesInCP.dstore_3(2.0, 5.0f);
    Assert.assertEquals(7.0, d, 0.0001);
  }

  @Test
  public void astore_0() {
    final int i = AllInstructionsWithoutDummiesInCP.astore_0(2);
    Assert.assertEquals(2, i, 0.0001);
  }

  @Test
  public void astore_1() {
    final int i = AllInstructionsWithoutDummiesInCP.astore_1(2, 5);
    Assert.assertEquals(2, i, 0.0001);
  }

  @Test
  public void astore_2() {
    final int i = AllInstructionsWithoutDummiesInCP.astore_2(2, 5, 4);
    Assert.assertEquals(2, i, 0.0001);
  }

  @Test
  public void astore_3() {
    final int i = AllInstructionsWithoutDummiesInCP.astore_3(2, 5, 4, 7);
    Assert.assertEquals(2, i, 0.0001);
  }

  @Test
  public void pop() {
    final double d = AllInstructionsWithoutDummiesInCP.pop();
    Assert.assertEquals(0.0, d, 0.0001);
  }

  @Test
  public void pop2() {
    final int i = AllInstructionsWithoutDummiesInCP.pop2();
    Assert.assertEquals(3, i);
  }

  @Test
  public void dup() {
    final int i = AllInstructionsWithoutDummiesInCP.dup();
    Assert.assertEquals(2, i);
  }

  @Test
  public void dup_x1() {
    final int i = AllInstructionsWithoutDummiesInCP.dup_x1();
    Assert.assertEquals(1, i);
  }

  @Test
  public void dup_x2() {
    final int i = AllInstructionsWithoutDummiesInCP.dup_x2();
    Assert.assertEquals(1, i);
  }

  @Test
  public void dup2() {
    final double d = AllInstructionsWithoutDummiesInCP.dup2();
    Assert.assertEquals(2.0, d, 0.0001);
  }

  @Test
  public void dup2_x1() {
    final double d = AllInstructionsWithoutDummiesInCP.dup2_x1();
    Assert.assertEquals(1.0, d, 0.0001);
  }

  @Test
  public void dup2_x2() {
    final double d = AllInstructionsWithoutDummiesInCP.dup2_x2();
    Assert.assertEquals(1.0, d, 0.0001);
  }

  @Test
  public void swap() {
    final int i = AllInstructionsWithoutDummiesInCP.swap();
    Assert.assertEquals(1, i);
  }

  @Test
  public void iadd() {
    final int i = AllInstructionsWithoutDummiesInCP.iadd(5, 10);
    Assert.assertEquals(15, i);
  }

  @Test
  public void ladd() {
    final long l = AllInstructionsWithoutDummiesInCP.ladd(15l, 100l);
    Assert.assertEquals(115l, l);
  }

  @Test
  public void fadd() {
    final float f = AllInstructionsWithoutDummiesInCP.fadd(5.5f, 10.5f);
    Assert.assertEquals(16.0f, f, 0.0001);
  }

  @Test
  public void dadd() {
    final double d = AllInstructionsWithoutDummiesInCP.dadd(5.9, 10.9);
    Assert.assertEquals(16.8, d, 0.0001);
  }

  @Test
  public void isub() {
    final int i = AllInstructionsWithoutDummiesInCP.isub(5, 10);
    Assert.assertEquals(-5, i);
  }

  @Test
  public void lsub() {
    final long l = AllInstructionsWithoutDummiesInCP.lsub(15l, 100l);
    Assert.assertEquals(-85l, l);
  }

  @Test
  public void fsub() {
    final float f = AllInstructionsWithoutDummiesInCP.fsub(5.5f, 10.5f);
    Assert.assertEquals(-5.0f, f, 0.0001);
  }

  @Test
  public void dsub() {
    final double d = AllInstructionsWithoutDummiesInCP.dsub(5.9, 10.9);
    Assert.assertEquals(-5.0, d, 0.0001);
  }

  @Test
  public void imul() {
    final int i = AllInstructionsWithoutDummiesInCP.imul(5, 10);
    Assert.assertEquals(50, i);
  }

  @Test
  public void lmul() {
    final long l = AllInstructionsWithoutDummiesInCP.lmul(15l, 100l);
    Assert.assertEquals(1500l, l);
  }

  @Test
  public void fmul() {
    final float f = AllInstructionsWithoutDummiesInCP.fmul(5.2f, 10.5f);
    Assert.assertEquals(54.6f, f, 0.0001);
  }

  @Test
  public void dmul() {
    final double d = AllInstructionsWithoutDummiesInCP.dmul(5.9, 10.9);
    Assert.assertEquals(64.31, d, 0.0001);
  }

  @Test
  public void idiv() {
    final int i = AllInstructionsWithoutDummiesInCP.idiv(10, 5);
    Assert.assertEquals(2, i);
  }

  @Test
  public void ldiv() {
    final long l = AllInstructionsWithoutDummiesInCP.ldiv(150l, 10l);
    Assert.assertEquals(15l, l);
  }

  @Test
  public void fdiv() {
    final float f = AllInstructionsWithoutDummiesInCP.fdiv(10.5f, 5.5f);
    Assert.assertEquals(1.90f, f, 0.01);
  }

  @Test
  public void ddiv() {
    final double d = AllInstructionsWithoutDummiesInCP.ddiv(10.0, 2.0);
    Assert.assertEquals(5.0, d, 0.0001);
  }

  @Test
  public void irem() {
    final int i = AllInstructionsWithoutDummiesInCP.irem(7, 2);
    Assert.assertEquals(1, i);
  }

  @Test
  public void lrem() {
    final long l = AllInstructionsWithoutDummiesInCP.lrem(155l, 10l);
    Assert.assertEquals(5l, l);
  }

  @Test
  public void frem() {
    final float f = AllInstructionsWithoutDummiesInCP.frem(0.5f, 0.3f);
    Assert.assertEquals(0.2f, f, 0.01);
  }

  @Test
  public void drem() {
    final double d = AllInstructionsWithoutDummiesInCP.drem(17.5, 15.3);
    Assert.assertEquals(2.2, d, 0.01);
  }

  @Test
  public void ineg() {
    final int i = AllInstructionsWithoutDummiesInCP.ineg(7);
    Assert.assertEquals(-7, i);
  }

  @Test
  public void lneg() {
    final long l = AllInstructionsWithoutDummiesInCP.lneg(-155l);
    Assert.assertEquals(155l, l);
  }

  @Test
  public void fneg() {
    final float f = AllInstructionsWithoutDummiesInCP.fneg(0.5f);
    Assert.assertEquals(-0.5f, f, 0.0001);
  }

  @Test
  public void dneg() {
    final double d = AllInstructionsWithoutDummiesInCP.dneg(-17.5);
    Assert.assertEquals(17.5, d, 0.0001);
  }

  // ..., value1, value2 → ..., result
  // An int result is calculated by shifting value1 left by s bit positions,
  // where s is the value of the low 5 bits of value2. (5 bits => 0 to 31)
  @Test
  public void ishl0() {
    final int i = AllInstructionsWithoutDummiesInCP.ishl(10, 32);
    Assert.assertEquals(10, i);
  }

  @Test
  public void ishl1() {
    final int i = AllInstructionsWithoutDummiesInCP.ishl(1, 31);
    Assert.assertEquals(Integer.MIN_VALUE, i);
  }

  @Test
  public void lshl0() {
    final int i = AllInstructionsWithoutDummiesInCP.ishl(10, 64);
    Assert.assertEquals(10, i);
  }

  @Test
  public void lshl1() {
    final long l = AllInstructionsWithoutDummiesInCP.lshl(1, 63);
    Assert.assertEquals(Long.MIN_VALUE, l);
  }

  @Test
  public void ishr0() {
    final int i = AllInstructionsWithoutDummiesInCP.ishr(10, 32);
    Assert.assertEquals(10, i);
  }

  @Test
  public void ishr1() {
    final int i = AllInstructionsWithoutDummiesInCP.ishr(Integer.MIN_VALUE, 31);
    Assert.assertEquals(-1, i);
  }

  @Test
  public void lshr0() {
    final long l = AllInstructionsWithoutDummiesInCP.lshr(10, 64);
    Assert.assertEquals(10, l);
  }

  @Test
  public void lshr1() {
    final long l = AllInstructionsWithoutDummiesInCP.lshr(Long.MIN_VALUE, 63);
    Assert.assertEquals(-1, l);
  }

  @Test
  public void iushr() {
    final int i = AllInstructionsWithoutDummiesInCP.iushr(Integer.MIN_VALUE, 31);
    Assert.assertEquals(1, i);
  }

  @Test
  public void lushr() {
    final long l = AllInstructionsWithoutDummiesInCP.lushr(Long.MIN_VALUE, 63);
    Assert.assertEquals(1, l);
  }

  @Test
  public void iand() {
    final int i = AllInstructionsWithoutDummiesInCP.iand(5, 4);
    Assert.assertEquals(4, i);
  }

  @Test
  public void land() {
    final long l = AllInstructionsWithoutDummiesInCP.land(Long.MAX_VALUE, Long.MIN_VALUE);
    Assert.assertEquals(0, l);
  }

  @Test
  public void ior() {
    final int i = AllInstructionsWithoutDummiesInCP.ior(5, 4);
    Assert.assertEquals(5, i);
  }

  @Test
  public void lor() {
    final long l = AllInstructionsWithoutDummiesInCP.lor(Long.MAX_VALUE, Long.MIN_VALUE);
    Assert.assertEquals(-1, l);
  }

  @Test
  public void ixor() {
    final int i = AllInstructionsWithoutDummiesInCP.ixor(5, 4);
    Assert.assertEquals(1, i);
  }

  @Test
  public void lxor() {
    final long l = AllInstructionsWithoutDummiesInCP.lxor(Long.MAX_VALUE, Long.MIN_VALUE);
    Assert.assertEquals(-1, l);
  }

  @Test
  public void iinc() {
    final int i = AllInstructionsWithoutDummiesInCP.iinc(10);
    Assert.assertEquals(5, i);
  }

  @Test
  public void i2l() {
    final long l = AllInstructionsWithoutDummiesInCP.i2l(5, 4l);
    Assert.assertEquals(9, l);
  }

  @Test
  public void i2f() {
    final float f = AllInstructionsWithoutDummiesInCP.i2f(5, 2);
    Assert.assertEquals(2.5f, f, 0.0001);
  }

  @Test
  public void i2d() {
    final double d = AllInstructionsWithoutDummiesInCP.i2d(10, 3);
    Assert.assertEquals(3.33, d, 0.01);
  }

  @Test
  public void l2i() {
    final int i = AllInstructionsWithoutDummiesInCP.l2i(Long.MAX_VALUE);
    Assert.assertEquals(-1, i);
  }

  @Test
  public void l2f() {
    final float f = AllInstructionsWithoutDummiesInCP.l2f(5l, 2l);
    Assert.assertEquals(2.5f, f, 0.0001);
  }

  @Test
  public void l2d() {
    final double d = AllInstructionsWithoutDummiesInCP.l2d(10l, 3l);
    Assert.assertEquals(3.33, d, 0.01);
  }

  @Test
  public void f2i() {
    final int i = AllInstructionsWithoutDummiesInCP.f2i(4.56f);
    Assert.assertEquals(4, i);
  }

  @Test
  public void f2l() {
    final long l = AllInstructionsWithoutDummiesInCP.f2l(45.908f);
    Assert.assertEquals(45, l);
  }

  @Test
  public void f2d() {
    final double d = AllInstructionsWithoutDummiesInCP.f2d(10.5f, 5.6f);
    Assert.assertEquals(16.1, d, 0.01);
  }

  @Test
  public void d2i() {
    final int i = AllInstructionsWithoutDummiesInCP.d2i(4.56f);
    Assert.assertEquals(4, i);
  }

  @Test
  public void d2l() {
    final long l = AllInstructionsWithoutDummiesInCP.d2l(45.908f);
    Assert.assertEquals(45, l);
  }

  @Test
  public void d2f() {
    final float f = AllInstructionsWithoutDummiesInCP.d2f(10.5f, 5.6f);
    Assert.assertEquals(16.1f, f, 0.01);
  }

  @Test
  public void i2b() {
    final int i = AllInstructionsWithoutDummiesInCP.i2b(Integer.MAX_VALUE);
    Assert.assertEquals(-1, i);
  }

  @Test
  public void i2c() {
    final int i = AllInstructionsWithoutDummiesInCP.i2c(Integer.MAX_VALUE);
    Assert.assertEquals(65_535, i);
  }

  @Test
  public void i2s() {
    final int i = AllInstructionsWithoutDummiesInCP.i2s(Integer.MAX_VALUE);
    Assert.assertEquals(-1, i);
  }

  @Test
  public void lcmp() {
    final int i1 = AllInstructionsWithoutDummiesInCP.lcmp(5, 10);
    Assert.assertEquals(-1, i1);

    final int i2 = AllInstructionsWithoutDummiesInCP.lcmp(5, 5);
    Assert.assertEquals(0, i2);

    final int i3 = AllInstructionsWithoutDummiesInCP.lcmp(10, 5);
    Assert.assertEquals(1, i3);
  }

  @Test
  public void fcmpl() {
    final int i1 = AllInstructionsWithoutDummiesInCP.fcmpl(5.5f, 10.1f);
    Assert.assertEquals(-1, i1);

    final int i2 = AllInstructionsWithoutDummiesInCP.fcmpl(5.5f, 5.5f);
    Assert.assertEquals(0, i2);

    final int i3 = AllInstructionsWithoutDummiesInCP.fcmpl(10.1f, 5.5f);
    Assert.assertEquals(1, i3);

    // returns -1 if one of the value is NaN
    final int i4 = AllInstructionsWithoutDummiesInCP.fcmpl(Float.NaN, 5.5f);
    Assert.assertEquals(-1, i4);

    final int i5 = AllInstructionsWithoutDummiesInCP.fcmpl(5.5f, Float.NaN);
    Assert.assertEquals(-1, i5);
  }

  @Test
  public void fcmpg() {
    final int i1 = AllInstructionsWithoutDummiesInCP.fcmpg(5.5f, 10.1f);
    Assert.assertEquals(-1, i1);

    final int i2 = AllInstructionsWithoutDummiesInCP.fcmpg(5.5f, 5.5f);
    Assert.assertEquals(0, i2);

    final int i3 = AllInstructionsWithoutDummiesInCP.fcmpg(10.1f, 5.5f);
    Assert.assertEquals(1, i3);

    // returns 1 if one of the value is NaN
    final int i4 = AllInstructionsWithoutDummiesInCP.fcmpg(Float.NaN, 5.5f);
    Assert.assertEquals(1, i4);

    final int i5 = AllInstructionsWithoutDummiesInCP.fcmpg(5.5f, Float.NaN);
    Assert.assertEquals(1, i5);
  }

  @Test
  public void dcmpl() {
    final int i1 = AllInstructionsWithoutDummiesInCP.dcmpl(5.5, 10.1);
    Assert.assertEquals(-1, i1);

    final int i2 = AllInstructionsWithoutDummiesInCP.dcmpl(5.5, 5.5);
    Assert.assertEquals(0, i2);

    final int i3 = AllInstructionsWithoutDummiesInCP.dcmpl(10.1, 5.5);
    Assert.assertEquals(1, i3);

    // returns -1 if one of the value is NaN
    final int i4 = AllInstructionsWithoutDummiesInCP.dcmpl(Double.NaN, 5.5);
    Assert.assertEquals(-1, i4);

    final int i5 = AllInstructionsWithoutDummiesInCP.dcmpl(5.5, Double.NaN);
    Assert.assertEquals(-1, i5);
  }

  @Test
  public void dcmpg() {
    final int i1 = AllInstructionsWithoutDummiesInCP.dcmpg(5.5, 10.1);
    Assert.assertEquals(-1, i1);

    final int i2 = AllInstructionsWithoutDummiesInCP.dcmpg(5.5, 5.5);
    Assert.assertEquals(0, i2);

    final int i3 = AllInstructionsWithoutDummiesInCP.dcmpg(10.1, 5.5);
    Assert.assertEquals(1, i3);

    // returns 1 if one of the value is NaN
    final int i4 = AllInstructionsWithoutDummiesInCP.dcmpg(Double.NaN, 5.5);
    Assert.assertEquals(1, i4);

    final int i5 = AllInstructionsWithoutDummiesInCP.dcmpg(5.5, Double.NaN);
    Assert.assertEquals(1, i5);
  }

  @Test
  public void ifeq() {
    final boolean b1 = AllInstructionsWithoutDummiesInCP.ifeq(0);
    Assert.assertTrue(b1);

    final boolean b2 = AllInstructionsWithoutDummiesInCP.ifeq(-5);
    Assert.assertFalse(b2);

    final boolean b3 = AllInstructionsWithoutDummiesInCP.ifeq(5);
    Assert.assertFalse(b3);
  }

  @Test
  public void ifne() {
    final boolean b1 = AllInstructionsWithoutDummiesInCP.ifne(5);
    Assert.assertTrue(b1);

    final boolean b2 = AllInstructionsWithoutDummiesInCP.ifne(-5);
    Assert.assertTrue(b2);

    final boolean b3 = AllInstructionsWithoutDummiesInCP.ifne(0);
    Assert.assertFalse(b3);
  }

  @Test
  public void iflt() {
    final boolean b1 = AllInstructionsWithoutDummiesInCP.iflt(5);
    Assert.assertFalse(b1);

    final boolean b2 = AllInstructionsWithoutDummiesInCP.iflt(-5);
    Assert.assertTrue(b2);

    final boolean b3 = AllInstructionsWithoutDummiesInCP.iflt(0);
    Assert.assertFalse(b3);
  }

  @Test
  public void ifge() {
    final boolean b1 = AllInstructionsWithoutDummiesInCP.ifge(5);
    Assert.assertTrue(b1);

    final boolean b2 = AllInstructionsWithoutDummiesInCP.ifge(-5);
    Assert.assertFalse(b2);

    final boolean b3 = AllInstructionsWithoutDummiesInCP.ifge(0);
    Assert.assertTrue(b3);
  }

  @Test
  public void ifgt() {
    final boolean b1 = AllInstructionsWithoutDummiesInCP.ifgt(5);
    Assert.assertTrue(b1);

    final boolean b2 = AllInstructionsWithoutDummiesInCP.ifgt(-5);
    Assert.assertFalse(b2);

    final boolean b3 = AllInstructionsWithoutDummiesInCP.ifgt(0);
    Assert.assertFalse(b3);
  }

  @Test
  public void ifle() {
    final boolean b1 = AllInstructionsWithoutDummiesInCP.ifle(5);
    Assert.assertFalse(b1);

    final boolean b2 = AllInstructionsWithoutDummiesInCP.ifle(-5);
    Assert.assertTrue(b2);

    final boolean b3 = AllInstructionsWithoutDummiesInCP.ifle(0);
    Assert.assertTrue(b3);
  }

  @Test
  public void if_icmpeq() {
    final boolean b1 = AllInstructionsWithoutDummiesInCP.if_icmpeq(5, 5);
    Assert.assertTrue(b1);

    final boolean b2 = AllInstructionsWithoutDummiesInCP.if_icmpeq(-5, 5);
    Assert.assertFalse(b2);

    final boolean b3 = AllInstructionsWithoutDummiesInCP.if_icmpeq(5, -5);
    Assert.assertFalse(b3);
  }

  @Test
  public void if_icmpne() {
    final boolean b1 = AllInstructionsWithoutDummiesInCP.if_icmpne(5, 5);
    Assert.assertFalse(b1);

    final boolean b2 = AllInstructionsWithoutDummiesInCP.if_icmpne(-5, 5);
    Assert.assertTrue(b2);

    final boolean b3 = AllInstructionsWithoutDummiesInCP.if_icmpne(5, -5);
    Assert.assertTrue(b3);
  }

  @Test
  public void if_icmplt() {
    final boolean b1 = AllInstructionsWithoutDummiesInCP.if_icmplt(5, 5);
    Assert.assertFalse(b1);

    final boolean b2 = AllInstructionsWithoutDummiesInCP.if_icmplt(-5, 5);
    Assert.assertTrue(b2);

    final boolean b3 = AllInstructionsWithoutDummiesInCP.if_icmplt(5, -5);
    Assert.assertFalse(b3);
  }

  @Test
  public void if_icmpge() {
    final boolean b1 = AllInstructionsWithoutDummiesInCP.if_icmpge(5, 5);
    Assert.assertTrue(b1);

    final boolean b2 = AllInstructionsWithoutDummiesInCP.if_icmpge(-5, 5);
    Assert.assertFalse(b2);

    final boolean b3 = AllInstructionsWithoutDummiesInCP.if_icmpge(5, -5);
    Assert.assertTrue(b3);
  }

  @Test
  public void if_icmpgt() {
    final boolean b1 = AllInstructionsWithoutDummiesInCP.if_icmpgt(5, 5);
    Assert.assertFalse(b1);

    final boolean b2 = AllInstructionsWithoutDummiesInCP.if_icmpgt(-5, 5);
    Assert.assertFalse(b2);

    final boolean b3 = AllInstructionsWithoutDummiesInCP.if_icmpgt(5, -5);
    Assert.assertTrue(b3);
  }

  @Test
  public void if_icmple() {
    final boolean b1 = AllInstructionsWithoutDummiesInCP.if_icmple(5, 5);
    Assert.assertTrue(b1);

    final boolean b2 = AllInstructionsWithoutDummiesInCP.if_icmple(-5, 5);
    Assert.assertTrue(b2);

    final boolean b3 = AllInstructionsWithoutDummiesInCP.if_icmple(5, -5);
    Assert.assertFalse(b3);
  }

  @Test
  public void wide_iinc() {
    final long l = AllInstructionsWithoutDummiesInCP.wide_iinc(10_000);
    Assert.assertEquals(((long) -20_000 << 32) | 6, l);
  }

  @Test
  public void wide_istore_iload() {
    final int i = AllInstructionsWithoutDummiesInCP.wide_istore_iload();
    Assert.assertEquals(3, i);
  }

  @Test
  public void wide_lstore_lload() {
    final long l = AllInstructionsWithoutDummiesInCP.wide_lstore_lload();
    Assert.assertEquals(1, l);
  }

  @Test
  public void wide_fstore_fload() {
    final float f = AllInstructionsWithoutDummiesInCP.wide_fstore_fload();
    Assert.assertEquals(1f, f, 0.0001);
  }

  @Test
  public void wide_dstore_dload() {
    final double d = AllInstructionsWithoutDummiesInCP.wide_dstore_dload();
    Assert.assertEquals(1, d, 0.0001);
  }

  @Test
  public void wide_astore_aload() {
    final Object o = AllInstructionsWithoutDummiesInCP.wide_astore_aload();
    Assert.assertNull(o);
  }

  @Test
  public void istore_load_unsigned() {
    final int i = AllInstructionsWithoutDummiesInCP.istore_load_unsigned();
    Assert.assertEquals(7_687, i);
  }

  @Test
  public void lstore_load_unsigned() {
    final long l = AllInstructionsWithoutDummiesInCP.lstore_load_unsigned();
    Assert.assertEquals(7_687_000, l);
  }

  @Test
  public void fstore_load_unsigned() {
    final float f = AllInstructionsWithoutDummiesInCP.fstore_load_unsigned();
    Assert.assertEquals(134.89f, f, 0.0001);
  }

  @Test
  public void dstore_load_unsigned() {
    final double d = AllInstructionsWithoutDummiesInCP.dstore_load_unsigned();
    Assert.assertEquals(33.33, d, 0.0001);
  }

  @Test
  public void astore_load_unsigned() {
    final Object o = AllInstructionsWithoutDummiesInCP.astore_load_unsigned();
    Assert.assertNull(o);
  }
}