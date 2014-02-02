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
}
