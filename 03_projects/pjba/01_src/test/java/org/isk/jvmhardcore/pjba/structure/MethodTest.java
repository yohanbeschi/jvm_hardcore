package org.isk.jvmhardcore.pjba.structure;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

public class MethodTest {

  private static Method METHOD;

  @BeforeClass
  public static void init() {
    METHOD = new Method();
  }

  // --------------------------------------------------------------------------------------------------------------------
  // checkAndCountParams()
  // --------------------------------------------------------------------------------------------------------------------

  private int count(final String methodDescriptor) {
    return METHOD.countParameters(methodDescriptor);
  }

  // boolean
  @Test
  public void countParams0() {
    final int parameterCount = this.count("(Z)V");

    Assert.assertEquals(1, parameterCount);
  }

  // byte
  @Test
  public void countParams1() {
    final int parameterCount = this.count("(B)V");

    Assert.assertEquals(1, parameterCount);
  }

  // short
  @Test
  public void countParams2() {
    final int parameterCount = this.count("(S)V");

    Assert.assertEquals(1, parameterCount);
  }

  // char
  @Test
  public void countParams3() {
    final int parameterCount = this.count("(C)V");

    Assert.assertEquals(1, parameterCount);
  }

  // int
  @Test
  public void countParams4() {
    final int parameterCount = this.count("(I)V");

    Assert.assertEquals(1, parameterCount);
  }

  // long
  @Test
  public void countParams5() {
    final int parameterCount = this.count("(J)V");

    Assert.assertEquals(2, parameterCount);
  }

  // float
  @Test
  public void countParams6() {
    final int parameterCount = this.count("(F)V");

    Assert.assertEquals(1, parameterCount);
  }

  // double
  @Test
  public void countParams7() {
    final int parameterCount = this.count("(D)[I");

    Assert.assertEquals(2, parameterCount);
  }

  // no parameter
  @Test
  public void countParams8() {
    final int parameterCount = this.count("()Ljava.lang.Object;");

    Assert.assertEquals(0, parameterCount);
  }

  // reference
  @Test
  public void countParams9() {
    final int parameterCount = this.count("(Ljava.lang.Object;)Ljava.lang.Object;");

    Assert.assertEquals(1, parameterCount);
  }

  // Array 1D
  @Test
  public void countParams10() {
    final int parameterCount = this.count("([I)V");

    Assert.assertEquals(1, parameterCount);
  }

  // Array 2D
  @Test
  public void countParams11() {
    final int parameterCount = this.count("([[Z)V");

    Assert.assertEquals(1, parameterCount);
  }

  // Array 2D/3D
  @Test
  public void countParams12() {
    final int parameterCount = this.count("([[[Z[[B)V");

    Assert.assertEquals(2, parameterCount);
  }

  // reference array
  @Test
  public void countParams13() {
    final int parameterCount = this.count("([[[Ljava.lang.Object;[[B)V");

    Assert.assertEquals(2, parameterCount);
  }

  // reference arrays (second ref without package)
  @Test
  public void countParams14() {
    final int parameterCount = this.count("([[[Ljava.lang.Object;[[LObject;)V");

    Assert.assertEquals(2, parameterCount);
  }

}
