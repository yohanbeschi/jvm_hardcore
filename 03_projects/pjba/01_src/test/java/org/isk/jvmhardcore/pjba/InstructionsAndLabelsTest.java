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

  @Test
  public void tableswitch1() {
    final int i1 = InstructionsAndLabels.tableswitch1(5);
    Assert.assertEquals(10, i1);

    final int i2 = InstructionsAndLabels.tableswitch1(6);
    Assert.assertEquals(12, i2);

    final int i3 = InstructionsAndLabels.tableswitch1(7);
    Assert.assertEquals(12, i3);

    final int i4 = InstructionsAndLabels.tableswitch1(8);
    Assert.assertEquals(14, i4);

    final int i5 = InstructionsAndLabels.tableswitch1(1);
    Assert.assertEquals(100, i5);
  }

  @Test
  public void tableswitch2() {
    final int i1 = InstructionsAndLabels.tableswitch2(5);
    Assert.assertEquals(10, i1);

    final int i2 = InstructionsAndLabels.tableswitch2(6);
    Assert.assertEquals(12, i2);

    final int i3 = InstructionsAndLabels.tableswitch2(7);
    Assert.assertEquals(12, i3);

    final int i4 = InstructionsAndLabels.tableswitch2(8);
    Assert.assertEquals(14, i4);

    final int i5 = InstructionsAndLabels.tableswitch2(1);
    Assert.assertEquals(14, i5);
  }

  @Test
  public void tableswitch3() {
    final int i1 = InstructionsAndLabels.tableswitch3(-1);
    Assert.assertEquals(-1, i1);

    final int i2 = InstructionsAndLabels.tableswitch3(0);
    Assert.assertEquals(3, i2);

    final int i3 = InstructionsAndLabels.tableswitch3(1);
    Assert.assertEquals(1, i3);
  }
  
  @Test
  public void lookupswitch1() {
    final int i1 = InstructionsAndLabels.lookupswitch1(5);
    Assert.assertEquals(20, i1);

    final int i2 = InstructionsAndLabels.lookupswitch1(10);
    Assert.assertEquals(20, i2);

    final int i3 = InstructionsAndLabels.lookupswitch1(15);
    Assert.assertEquals(30, i3);

    final int i4 = InstructionsAndLabels.lookupswitch1(1);
    Assert.assertEquals(100, i4);
  }

  @Test
  public void lookupswitch2() {
    final int i1 = InstructionsAndLabels.lookupswitch2(5);
    Assert.assertEquals(20, i1);

    final int i2 = InstructionsAndLabels.lookupswitch2(10);
    Assert.assertEquals(20, i2);

    final int i3 = InstructionsAndLabels.lookupswitch2(15);
    Assert.assertEquals(30, i3);

    final int i4 = InstructionsAndLabels.lookupswitch2(1);
    Assert.assertEquals(30, i4);
  }
  
  @Test
  public void lookupswitch3() {
    final int i1 = InstructionsAndLabels.lookupswitch3(-1);
    Assert.assertEquals(-1, i1);

    final int i2 = InstructionsAndLabels.lookupswitch3(0);
    Assert.assertEquals(3, i2);

    final int i3 = InstructionsAndLabels.lookupswitch3(1);
    Assert.assertEquals(1, i3);
  }
}