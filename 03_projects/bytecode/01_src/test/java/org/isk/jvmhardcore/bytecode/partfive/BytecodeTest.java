package org.isk.jvmhardcore.bytecode.partfive;

import org.junit.Assert;
import org.junit.Test;

public class BytecodeTest {
  @Test
  public void add() {
    final int result = Addition.add(1, 2, 3);

    Assert.assertEquals(6, result);
  }

  @Test
  public void subtract() {
    final double result = Subtraction.subtract(1, 2, 3);

    Assert.assertEquals(-4d, result, 0.0001);
  }

  @Test
  public void multiply() {
    final float result = Multiplication.multiply(3, 2);

    Assert.assertEquals(6, result, 0.0001);
  }

  @Test
  public void divide() {
    final long result = Division.divide(3, 2);

    Assert.assertEquals(1, result);
  }

  @Test
  public void getRemainder() {
    final int result = Remainder.getRemainder(40, 7);

    Assert.assertEquals(5, result);
  }

  @Test
  public void negate() {
    final int result = Negation.negate(-10);

    Assert.assertEquals(10, result);
  }

  @Test
  public void shiftLeft() {
    final int result = BitShifting_Left.shift();

    Assert.assertEquals(18, result);
  }

  @Test
  public void shiftRight() {
    final int result = BitShifting_Right.shift();

    Assert.assertEquals(4, result);
  }

  @Test
  public void iand() {
    final int result = Iand.iand();

    Assert.assertEquals(2, result);
  }

  @Test
  public void ior() {
    final int result = Ior.ior();

    Assert.assertEquals(15, result);
  }

  @Test
  public void ixor() {
    final int result = Ixor.ixor();

    Assert.assertEquals(13, result);
  }

  @Test
  public void d2i() {
    final int result = D2i.d2i(14.98);

    Assert.assertEquals(14, result);
  }
}
