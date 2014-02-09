package org.isk.jvmhardcore.pjba;

import org.junit.Assert;
import org.junit.Test;

public class AssemblerTest {
  @Test
  public void assemble0() {
    final int sum = MyFirstClass.add(3, 5);
    Assert.assertEquals(8, sum);
  }

  @Test
  public void assemble1() {
    final int sum1 = MySecondClass.add(3, 5);
    Assert.assertEquals(8, sum1);

    final int sum2 = MySecondClass.add2(2, sum1);
    Assert.assertEquals(10, sum2);
  }

  @Test
  public void nop() {
    NoArg.nop();
  }

  @Test
  public void aconst_null() {
    final Object o = NoArg.aconst_null();
    Assert.assertNull(o);
  }

  @Test
  public void iconst_m1() {
    final int i = NoArg.iconst_m1();
    Assert.assertEquals(-1, i);
  }

  @Test
  public void iconst_0() {
    final int i = NoArg.iconst_0();
    Assert.assertEquals(0, i);
  }

  @Test
  public void iconst_1() {
    final int i = NoArg.iconst_1();
    Assert.assertEquals(1, i);
  }

  @Test
  public void iconst_2() {
    final int i = NoArg.iconst_2();
    Assert.assertEquals(2, i);
  }

  @Test
  public void iconst_3() {
    final int i = NoArg.iconst_3();
    Assert.assertEquals(3, i);
  }

  @Test
  public void iconst_4() {
    final int i = NoArg.iconst_4();
    Assert.assertEquals(4, i);
  }

  @Test
  public void iconst_5() {
    final int i = NoArg.iconst_5();
    Assert.assertEquals(5, i);
  }

  @Test
  public void lconst_0() {
    final long l = NoArg.lconst_0();
    Assert.assertEquals(0l, l);
  }

  @Test
  public void lconst_1() {
    final long l = NoArg.lconst_1();
    Assert.assertEquals(1l, l);
  }

  @Test
  public void fconst_0() {
    final float f = NoArg.fconst_0();
    Assert.assertEquals(0f, f, 0.0001);
  }

  @Test
  public void fconst_1() {
    final float f = NoArg.fconst_1();
    Assert.assertEquals(1f, f, 0.0001);
  }

  @Test
  public void fconst_2() {
    final float f = NoArg.fconst_2();
    Assert.assertEquals(2f, f, 0.0001);
  }

  @Test
  public void dconst_0() {
    final double d = NoArg.dconst_0();
    Assert.assertEquals(0.0, d, 0.0001);
  }

  @Test
  public void dconst_1() {
    final double d = NoArg.dconst_1();
    Assert.assertEquals(1.0, d, 0.0001);
  }

  @Test
  public void iload_0() {
    final int i = NoArg.iload_0(5);
    Assert.assertEquals(5, i);
  }

  @Test
  public void iload_1() {
    final int i = NoArg.iload_1(true, 5);
    Assert.assertEquals(5, i);
  }

  @Test
  public void iload_2() {
    final int i = NoArg.iload_2(true, true, 5);
    Assert.assertEquals(5, i);
  }

  @Test
  public void iload_3() {
    final int i = NoArg.iload_3(true, true, true, 5);
    Assert.assertEquals(5, i);
  }

  @Test
  public void lload_0() {
    final long l = NoArg.lload_0(15l);
    Assert.assertEquals(15l, l);
  }

  @Test
  public void lload_1() {
    final long l = NoArg.lload_1(true, 15l);
    Assert.assertEquals(15l, l);
  }

  @Test
  public void lload_2() {
    final long l = NoArg.lload_2(true, true, 15l);
    Assert.assertEquals(15l, l);
  }

  @Test
  public void lload_3() {
    final long l = NoArg.lload_3(true, true, true, 15l);
    Assert.assertEquals(15l, l);
  }

  @Test
  public void fload_0() {
    final float f = NoArg.fload_0(5.5f);
    Assert.assertEquals(5.5f, f, 0.0001);
  }

  @Test
  public void fload_1() {
    final float f = NoArg.fload_1(true, 5.5f);
    Assert.assertEquals(5.5f, f, 0.0001);
  }

  @Test
  public void fload_2() {
    final float f = NoArg.fload_2(true, true, 5.5f);
    Assert.assertEquals(5.5f, f, 0.0001);
  }

  @Test
  public void fload_3() {
    final float f = NoArg.fload_3(true, true, true, 5.5f);
    Assert.assertEquals(5.5f, f, 0.0001);
  }

  @Test
  public void dload_0() {
    final double d = NoArg.dload_0(5.9);
    Assert.assertEquals(5.9, d, 0.0001);
  }

  @Test
  public void dload_1() {
    final double d = NoArg.dload_1(true, 5.9);
    Assert.assertEquals(5.9, d, 0.0001);
  }

  @Test
  public void dload_2() {
    final double d = NoArg.dload_2(true, true, 5.9);
    Assert.assertEquals(5.9, d, 0.0001);
  }

  @Test
  public void dload_3() {
    final double d = NoArg.dload_3(true, true, true, 5.9);
    Assert.assertEquals(5.9, d, 0.0001);
  }

  @Test
  public void aload_0() {
    final Integer expected = new Integer(5000);
    final Integer i = NoArg.aload_0(expected);
    Assert.assertTrue(i == expected);
  }

  @Test
  public void aload_1() {
    final Integer expected = new Integer(5000);
    final Integer i = NoArg.aload_1(true, expected);
    Assert.assertTrue(i == expected);
  }

  @Test
  public void aload_2() {
    final Integer expected = new Integer(5000);
    final Integer i = NoArg.aload_2(true, true, expected);
    Assert.assertTrue(i == expected);
  }

  @Test
  public void aload_3() {
    final Integer expected = new Integer(5000);
    final Integer i = NoArg.aload_3(true, true, true, expected);
    Assert.assertTrue(i == expected);
  }

  @Test
  public void istore_0() {
    final int i = NoArg.istore_0(2, 4);
    Assert.assertEquals(11, i);
  }

  @Test
  public void istore_1() {
    final int i = NoArg.istore_1(2, 4);
    Assert.assertEquals(9, i);
  }

  @Test
  public void istore_2() {
    final int i = NoArg.istore_2(2, 5, 6);
    Assert.assertEquals(7, i);
  }

  @Test
  public void istore_3() {
    final int i = NoArg.istore_3(2, 5, 6, 7);
    Assert.assertEquals(8, i);
  }

  @Test
  public void lstore_0() {
    final long l = NoArg.lstore_0(2l, 4l);
    Assert.assertEquals(11l, l);
  }

  @Test
  public void lstore_1() {
    final long l = NoArg.lstore_1(2, 4l);
    Assert.assertEquals(9l, l);
  }

  //
  @Test
  public void lstore_2() {
    final long l = NoArg.lstore_2(2l, 5l);
    Assert.assertEquals(3l, l);
  }

  @Test
  public void lstore_3() {
    final long l = NoArg.lstore_3(2l, 5);
    Assert.assertEquals(7l, l);
  }

  @Test
  public void fstore_0() {
    final float f = NoArg.fstore_0(2f, 4f);
    Assert.assertEquals(11f, f, 0.0001);
  }

  @Test
  public void fstore_1() {
    final float f = NoArg.fstore_1(2f, 4f);
    Assert.assertEquals(9f, f, 0.0001);
  }

  @Test
  public void fstore_2() {
    final float f = NoArg.fstore_2(2f, 5f, 6f);
    Assert.assertEquals(7f, f, 0.0001);
  }

  @Test
  public void fstore_3() {
    final float f = NoArg.fstore_3(2f, 5f, 6f, 7f);
    Assert.assertEquals(8f, f, 0.0001);
  }

  @Test
  public void dstore_0() {
    final double d = NoArg.dstore_0(2.0, 4.0);
    Assert.assertEquals(11.0, d, 0.0001);
  }

  @Test
  public void dstore_1() {
    final double d = NoArg.dstore_1(2.0f, 4.0);
    Assert.assertEquals(9.0, d, 0.0001);
  }

  @Test
  public void dstore_2() {
    final double d = NoArg.dstore_2(2.0, 5.0);
    Assert.assertEquals(3.0, d, 0.0001);
  }

  @Test
  public void dstore_3() {
    final double d = NoArg.dstore_3(2.0, 5.0f);
    Assert.assertEquals(7.0, d, 0.0001);
  }

  @Test
  public void astore_0() {
    final int i = NoArg.astore_0(2);
    Assert.assertEquals(2, i);
  }

  @Test
  public void astore_1() {
    final int i = NoArg.astore_1(2, 5);
    Assert.assertEquals(2, i);
  }

  @Test
  public void astore_2() {
    final int i = NoArg.astore_2(2, 5, 4);
    Assert.assertEquals(2, i);
  }

  @Test
  public void astore_3() {
    final int i = NoArg.astore_3(2, 5, 4, 7);
    Assert.assertEquals(2, i);
  }

  @Test
  public void pop() {
    final double d = NoArg.pop();
    Assert.assertEquals(0.0, d, 0.0001);
  }

  @Test
  public void pop2() {
    final int i = NoArg.pop2();
    Assert.assertEquals(3, i);
  }

  @Test
  public void dup() {
    final int i = NoArg.dup();
    Assert.assertEquals(2, i);
  }

  @Test
  public void dup_x1() {
    final int i = NoArg.dup_x1();
    Assert.assertEquals(1, i);
  }

  @Test
  public void dup_x2() {
    final int i = NoArg.dup_x2();
    Assert.assertEquals(1, i);
  }

  @Test
  public void dup2() {
    final double d = NoArg.dup2();
    Assert.assertEquals(2.0, d, 0.0001);
  }

  @Test
  public void dup2_x1() {
    final double d = NoArg.dup2_x1();
    Assert.assertEquals(1.0, d, 0.0001);
  }

  @Test
  public void dup2_x2() {
    final double d = NoArg.dup2_x2();
    Assert.assertEquals(1.0, d, 0.0001);
  }

  @Test
  public void swap() {
    final int i = NoArg.swap();
    Assert.assertEquals(1, i);
  }

  @Test
  public void iadd() {
    final int i = NoArg.iadd(5, 10);
    Assert.assertEquals(15, i);
  }

  @Test
  public void ladd() {
    final long l = NoArg.ladd(15l, 100l);
    Assert.assertEquals(115l, l);
  }

  @Test
  public void fadd() {
    final float f = NoArg.fadd(5.5f, 10.5f);
    Assert.assertEquals(16.0f, f, 0.0001);
  }

  @Test
  public void dadd() {
    final double d = NoArg.dadd(5.9, 10.9);
    Assert.assertEquals(16.8, d, 0.0001);
  }

  @Test
  public void isub() {
    final int i = NoArg.isub(5, 10);
    Assert.assertEquals(-5, i);
  }

  @Test
  public void lsub() {
    final long l = NoArg.lsub(15l, 100l);
    Assert.assertEquals(-85l, l);
  }

  @Test
  public void fsub() {
    final float f = NoArg.fsub(5.5f, 10.5f);
    Assert.assertEquals(-5.0f, f, 0.0001);
  }

  @Test
  public void dsub() {
    final double d = NoArg.dsub(5.9, 10.9);
    Assert.assertEquals(-5.0, d, 0.0001);
  }

  @Test
  public void imul() {
    final int i = NoArg.imul(5, 10);
    Assert.assertEquals(50, i);
  }

  @Test
  public void lmul() {
    final long l = NoArg.lmul(15l, 100l);
    Assert.assertEquals(1500l, l);
  }

  @Test
  public void fmul() {
    final float f = NoArg.fmul(5.2f, 10.5f);
    Assert.assertEquals(54.6f, f, 0.0001);
  }

  @Test
  public void dmul() {
    final double d = NoArg.dmul(5.9, 10.9);
    Assert.assertEquals(64.31, d, 0.0001);
  }

  @Test
  public void idiv() {
    final int i = NoArg.idiv(10, 5);
    Assert.assertEquals(2, i);
  }

  @Test
  public void ldiv() {
    final long l = NoArg.ldiv(150l, 10l);
    Assert.assertEquals(15l, l);
  }

  @Test
  public void fdiv() {
    final float f = NoArg.fdiv(10.5f, 5.5f);
    Assert.assertEquals(1.90f, f, 0.01);
  }

  @Test
  public void ddiv() {
    final double d = NoArg.ddiv(10.0, 2.0);
    Assert.assertEquals(5.0, d, 0.0001);
  }

  @Test
  public void irem() {
    final int i = NoArg.irem(7, 2);
    Assert.assertEquals(1, i);
  }

  @Test
  public void lrem() {
    final long l = NoArg.lrem(155l, 10l);
    Assert.assertEquals(5l, l);
  }

  @Test
  public void frem() {
    final float f = NoArg.frem(0.5f, 0.3f);
    Assert.assertEquals(0.2f, f, 0.01);
  }

  @Test
  public void drem() {
    final double d = NoArg.drem(17.5, 15.3);
    Assert.assertEquals(2.2, d, 0.01);
  }

  @Test
  public void ineg() {
    final int i = NoArg.ineg(7);
    Assert.assertEquals(-7, i);
  }

  @Test
  public void lneg() {
    final long l = NoArg.lneg(-155l);
    Assert.assertEquals(155l, l);
  }

  @Test
  public void fneg() {
    final float f = NoArg.fneg(0.5f);
    Assert.assertEquals(-0.5f, f, 0.0001);
  }

  @Test
  public void dneg() {
    final double d = NoArg.dneg(-17.5);
    Assert.assertEquals(17.5, d, 0.0001);
  }

  // ..., value1, value2 → ..., result
  // An int result is calculated by shifting value1 left by s bit positions,
  // where s is the value of the low 5 bits of value2. (5 bits => 0 to 31)
  @Test
  public void ishl0() {
    final int i = NoArg.ishl(10, 32);
    Assert.assertEquals(10, i);
  }

  @Test
  public void ishl1() {
    final int i = NoArg.ishl(1, 31);
    Assert.assertEquals(Integer.MIN_VALUE, i);
  }

  @Test
  public void lshl0() {
    final int i = NoArg.ishl(10, 64);
    Assert.assertEquals(10, i);
  }

  @Test
  public void lshl1() {
    final long l = NoArg.lshl(1, 63);
    Assert.assertEquals(Long.MIN_VALUE, l);
  }

  @Test
  public void ishr0() {
    final int i = NoArg.ishr(10, 32);
    Assert.assertEquals(10, i);
  }

  @Test
  public void ishr1() {
    final int i = NoArg.ishr(Integer.MIN_VALUE, 31);
    Assert.assertEquals(-1, i);
  }

  @Test
  public void lshr0() {
    final long l = NoArg.lshr(10, 64);
    Assert.assertEquals(10, l);
  }

  @Test
  public void lshr1() {
    final long l = NoArg.lshr(Long.MIN_VALUE, 63);
    Assert.assertEquals(-1, l);
  }

  @Test
  public void iushr() {
    final int i = NoArg.iushr(Integer.MIN_VALUE, 31);
    Assert.assertEquals(1, i);
  }

  @Test
  public void lushr() {
    final long l = NoArg.lushr(Long.MIN_VALUE, 63);
    Assert.assertEquals(1, l);
  }

  @Test
  public void iand() {
    final int i = NoArg.iand(5, 4);
    Assert.assertEquals(4, i);
  }

  @Test
  public void land() {
    final long l = NoArg.land(Long.MAX_VALUE, Long.MIN_VALUE);
    Assert.assertEquals(0, l);
  }

  @Test
  public void ior() {
    final int i = NoArg.ior(5, 4);
    Assert.assertEquals(5, i);
  }

  @Test
  public void lor() {
    final long l = NoArg.lor(Long.MAX_VALUE, Long.MIN_VALUE);
    Assert.assertEquals(-1, l);
  }

  @Test
  public void ixor() {
    final int i = NoArg.ixor(5, 4);
    Assert.assertEquals(1, i);
  }

  @Test
  public void lxor() {
    final long l = NoArg.lxor(Long.MAX_VALUE, Long.MIN_VALUE);
    Assert.assertEquals(-1, l);
  }

  @Test
  public void i2l() {
    final long l = NoArg.i2l(5, 4l);
    Assert.assertEquals(9, l);
  }

  @Test
  public void i2f() {
    final float f = NoArg.i2f(5, 2);
    Assert.assertEquals(2.5f, f, 0.0001);
  }

  @Test
  public void i2d() {
    final double d = NoArg.i2d(10, 3);
    Assert.assertEquals(3.33, d, 0.01);
  }

  @Test
  public void l2i() {
    final int i = NoArg.l2i(Long.MAX_VALUE);
    Assert.assertEquals(-1, i);
  }

  @Test
  public void l2f() {
    final float f = NoArg.l2f(5l, 2l);
    Assert.assertEquals(2.5f, f, 0.0001);
  }

  @Test
  public void l2d() {
    final double d = NoArg.l2d(10l, 3l);
    Assert.assertEquals(3.33, d, 0.01);
  }

  @Test
  public void f2i() {
    final int i = NoArg.f2i(4.56f);
    Assert.assertEquals(4, i);
  }

  @Test
  public void f2l() {
    final long l = NoArg.f2l(45.908f);
    Assert.assertEquals(45, l);
  }

  @Test
  public void f2d() {
    final double d = NoArg.f2d(10.5f, 5.6f);
    Assert.assertEquals(16.1, d, 0.01);
  }

  @Test
  public void d2i() {
    final int i = NoArg.d2i(4.56f);
    Assert.assertEquals(4, i);
  }

  @Test
  public void d2l() {
    final long l = NoArg.d2l(45.908f);
    Assert.assertEquals(45, l);
  }

  @Test
  public void d2f() {
    final float f = NoArg.d2f(10.5f, 5.6f);
    Assert.assertEquals(16.1f, f, 0.01);
  }

  @Test
  public void i2b() {
    final int i = NoArg.i2b(Integer.MAX_VALUE);
    Assert.assertEquals(-1, i);
  }

  @Test
  public void i2c() {
    final int i = NoArg.i2c(Integer.MAX_VALUE);
    Assert.assertEquals(65_535, i);
  }

  @Test
  public void i2s() {
    final int i = NoArg.i2s(Integer.MAX_VALUE);
    Assert.assertEquals(-1, i);
  }
}
