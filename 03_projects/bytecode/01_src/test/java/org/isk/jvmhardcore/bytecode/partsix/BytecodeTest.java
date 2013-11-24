package org.isk.jvmhardcore.bytecode.partsix;

import org.junit.Assert;
import org.junit.Test;

public class BytecodeTest {
  @Test
  public void swap() {
    final int result = Swap.swap();

    Assert.assertEquals(1, result);
  }

  @Test
  public void pop() {
    final double result = Pop.pop();

    Assert.assertEquals(0.0, result, 0.0001);
  }

  @Test
  public void pop2_form1() {
    final int result = Pop2_Form1.pop2();

    Assert.assertEquals(0, result);
  }

  @Test
  public void pop2_form2() {
    final int result = Pop2_Form2.pop2();

    Assert.assertEquals(2, result);
  }

  @Test
  public void dup() {
    final int result = Dup.dup();

    Assert.assertEquals(2, result);
  }

  @Test
  public void dup_x1() {
    final int result = Dup_X1.dup_x1();

    Assert.assertEquals(1, result);
  }

  @Test
  public void dup_x2_form1() {
    final int result = Dup_X2_Form1.dup_x2();

    Assert.assertEquals(1, result);
  }

  @Test
  public void dup_x2_form2() {
    final int result = Dup_X2_Form2.dup_x2();

    Assert.assertEquals(2, result);
  }

  @Test
  public void dup2_form1() {
    final int result = Dup2_Form1.dup2();

    Assert.assertEquals(0x1020102, result);
  }

  @Test
  public void dup2_form2() {
    final double result = Dup2_Form2.dup2();

    Assert.assertEquals(2.0, result, 0.0001);
  }

  @Test
  public void dup2_x1_form1() {
    final int result = Dup2_X1_Form1.dup2_x1();

    Assert.assertEquals(0x12312, result);
  }

  @Test
  public void dup2_x1_form2() {
    final double result = Dup2_X1_Form2.dup2_x1();

    Assert.assertEquals(1.0, result, 0.0001);
  }

  @Test
  public void dup2_x2_form1() {
    final int result = Dup2_X2_Form1.dup2_x2();

    Assert.assertEquals(0x123412, result);
  }

  @Test
  public void dup2_x2_form2() {
    final double result = Dup2_X2_Form2.dup2_x2();

    Assert.assertEquals(1.0, result, 0.0001);
  }

  @Test
  public void dup2_x2_form3() {
    final int result = Dup2_X2_Form3.dup2_x2();

    Assert.assertEquals(0x21, result);
  }

  @Test
  public void dup2_x2_form4() {
    final double result = Dup2_X2_Form4.dup2_x2();

    Assert.assertEquals(1.0, result, 0.0001);
  }
}