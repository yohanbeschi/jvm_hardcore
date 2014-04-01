package org.isk.jvmhardcore.bytecode.partthree;

import junit.framework.Assert;

import org.junit.Test;

public class BytecodeTest {
  @Test
  public void aconst_null() {
    final Object obj = Aconst_null.get();

    Assert.assertNull(obj);
  }

  @Test
  public void iconst_m1() {
    final int num = Iconst_m1.get();

    Assert.assertEquals(-1, num);
  }

  @Test
  public void dconst_1() {
    final double num = Dconst_1.get();

    Assert.assertEquals(1.0, num);
  }

  @Test
  public void wrongReturnType() {
    try {
      WrongReturnType.get();
      Assert.fail();
    } catch (VerifyError e) {
      Assert.assertEquals("(class: org/isk/jvmhardcore/bytecode/partthree/WrongReturnType, "
          + "method: get signature: ()I) Wrong return type in function", e.getMessage());
    }
  }

  @Test
  public void wrongTypeReturned() {
    try {
      WrongTypeReturned.get();
      Assert.fail();
    } catch (VerifyError e) {
      Assert.assertEquals("(class: org/isk/jvmhardcore/bytecode/partthree/WrongTypeReturned, "
          + "method: get signature: ()D) Expecting to find integer on stack", e.getMessage());
    }
  }

  @Test
  public void bipush() {
    final byte num = Bipush.get();

    Assert.assertEquals(117, num);
  }

  @Test
  public void sipush() {
    final short num = Sipush.get();

    Assert.assertEquals(14909, num);
  }

  @Test
  public void bipush_int() {
    final int num = Bipush_int.get();

    Assert.assertEquals(-117, num);
  }

  @Test
  public void ldc_string() {
    final String result = Ldc_String.getString();

    Assert.assertEquals("Привет \\\" мир по-русски", result);
  }

  @Test
  public void ldc_integer() {
    final int result = Ldc_Integer.getInteger();

    Assert.assertEquals(1000, result);
  }

  @Test
  public void ldc_float() {
    final float result = Ldc_Float.getFloat();

    Assert.assertEquals(-15.56e-12f, result);
  }

  @Test
  public void ldc2_w_long() {
    final long result = Ldc_Long.getLong();

    Assert.assertEquals(1324l, result);
  }

  @Test
  public void ldc2_w_double() {
    final double result = Ldc_Double.getDouble();

    Assert.assertEquals(-14.70e-43, result);
  }
}