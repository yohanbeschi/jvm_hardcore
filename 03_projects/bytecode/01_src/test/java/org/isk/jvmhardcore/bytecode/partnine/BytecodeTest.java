package org.isk.jvmhardcore.bytecode.partnine;

import org.junit.Assert;
import org.junit.Test;

public class BytecodeTest {
  @Test
  public void compute() {
    final int result = Postfix.compute();
    Assert.assertEquals(12, result);
  }
}
