package org.isk.jvmhardcore.pjba;

import junit.framework.Assert;

import org.junit.Test;

public class AssemblerTest {
  @Test
  public void assemble0() {
    final int sum = MyFirstClass.add(3, 5);
    Assert.assertEquals(8, sum);
  }
}
