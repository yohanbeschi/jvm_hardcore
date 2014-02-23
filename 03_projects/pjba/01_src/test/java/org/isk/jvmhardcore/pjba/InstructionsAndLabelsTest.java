package org.isk.jvmhardcore.pjba;

import junit.framework.Assert;

import org.junit.Test;

public class InstructionsAndLabelsTest {
  @Test
  public void case1() {
    final boolean b1 = InstructionsAndLabels.case1(0);
    Assert.assertTrue(b1);

    final boolean b2 = InstructionsAndLabels.case1(-1);
    Assert.assertFalse(b2);

    final boolean b3 = InstructionsAndLabels.case1(-1);
    Assert.assertFalse(b3);
  }

  @Test
  public void case2() {
    final boolean b1 = InstructionsAndLabels.case2(0, 0);
    Assert.assertTrue(b1);

    final boolean b2 = InstructionsAndLabels.case2(0, 1);
    Assert.assertTrue(b2);

    final boolean b3 = InstructionsAndLabels.case2(1, 0);
    Assert.assertTrue(b3);

    final boolean b4 = InstructionsAndLabels.case2(1, 1);
    Assert.assertFalse(b4);
  }

  @Test
  public void case3() {
    final int i = InstructionsAndLabels.case3();
    Assert.assertEquals(10, i);
  }

  @Test
  public void case4() {
    final int i = InstructionsAndLabels.case4();
    Assert.assertEquals(20, i);
  }

  @Test
  public void case5() {
    final int i1 = InstructionsAndLabels.case5(0);
    Assert.assertEquals(10, i1);

    final int i2 = InstructionsAndLabels.case5(1);
    Assert.assertEquals(110, i2);
  }
}