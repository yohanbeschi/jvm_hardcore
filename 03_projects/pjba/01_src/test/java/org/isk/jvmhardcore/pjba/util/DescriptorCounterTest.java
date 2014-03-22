package org.isk.jvmhardcore.pjba.util;

import org.junit.Assert;
import org.junit.Test;

public class DescriptorCounterTest {

  private int count(final String methodDescriptor) {
    return DescriptorCounter.methodsDescriptorParamsUnits(methodDescriptor);
  }

  private int stackImpact(final String methodDescriptor) {
    return DescriptorCounter.methodsDescriptorSignatureUnits(methodDescriptor);
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

  // void
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

  @Test
  public void countParams15() {
    final int parameterCount = this.count("(Ljava/lang/Integer;ZZZZZZZZZLjava/lang/Integer;)Ljava/lang/Integer;");

    Assert.assertEquals(11, parameterCount);
  }

  // boolean
  @Test
  public void countStack0() {
    final int stackCount = this.stackImpact("(Z)V");

    Assert.assertEquals(-1, stackCount);
  }

  // byte
  @Test
  public void countStack1() {
    final int stackCount = this.stackImpact("(B)V");

    Assert.assertEquals(-1, stackCount);
  }

  // short
  @Test
  public void countStack2() {
    final int stackCount = this.stackImpact("(S)V");

    Assert.assertEquals(-1, stackCount);
  }

  // char
  @Test
  public void countStack3() {
    final int stackCount = this.stackImpact("(C)V");

    Assert.assertEquals(-1, stackCount);
  }

  // int
  @Test
  public void countStack4() {
    final int stackCount = this.stackImpact("(I)V");

    Assert.assertEquals(-1, stackCount);
  }

  // long
  @Test
  public void countStack5() {
    final int stackCount = this.stackImpact("(J)V");

    Assert.assertEquals(-2, stackCount);
  }

  // float
  @Test
  public void countStack6() {
    final int stackCount = this.stackImpact("(F)V");

    Assert.assertEquals(-1, stackCount);
  }

  // double
  @Test
  public void countStack7() {
    final int stackCount = this.stackImpact("(D)[I");

    Assert.assertEquals(-1, stackCount);
  }

  // void
  @Test
  public void countStack8() {
    final int stackCount = this.stackImpact("()Ljava.lang.Object;");

    Assert.assertEquals(1, stackCount);
  }

  // reference
  @Test
  public void countStack9() {
    final int stackCount = this.stackImpact("(Ljava.lang.Object;)Ljava.lang.Object;");

    Assert.assertEquals(0, stackCount);
  }

  // Array 1D
  @Test
  public void countStack10() {
    final int stackCount = this.stackImpact("([I)D");

    Assert.assertEquals(1, stackCount);
  }

  // Array 2D
  @Test
  public void countStack11() {
    final int stackCount = this.stackImpact("([[Z)[D");

    Assert.assertEquals(0, stackCount);
  }

  // Array 2D/3D
  @Test
  public void countStack12() {
    final int stackCount = this.stackImpact("([[[Z[[B)V");

    Assert.assertEquals(-2, stackCount);
  }

  // reference array
  @Test
  public void countStack13() {
    final int stackCount = this.stackImpact("([[[Ljava.lang.Object;[[B)V");

    Assert.assertEquals(-2, stackCount);
  }

  // reference arrays (second ref without package)
  @Test
  public void countStack14() {
    final int stackCount = this.stackImpact("([[[Ljava.lang.Object;[[LObject;)J");

    Assert.assertEquals(0, stackCount);
  }

  @Test
  public void countStack15() {
    final int stackCount = this.stackImpact("(Ljava/lang/Integer;ZZZZZZZZZLjava/lang/Integer;)Ljava/lang/Integer;");

    Assert.assertEquals(-10, stackCount);
  }

}
