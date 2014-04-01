package org.isk.jvmhardcore.bytecode.partfour;

import org.junit.Assert;
import org.junit.Test;

public class BytecodeTest {
  @Test
  public void aload() {
    final String result = Aload.getThird((byte) 1, 2, "Hello", 4l);

    Assert.assertEquals("Hello", result);
  }

  @Test
  public void lload() {
    final long result = Lload.getFourth((byte) 1, 2, "Hello", 4l);

    Assert.assertEquals(4l, result);
  }

  @Test
  public void iload_0() {
    final int result = Iload_0.getFirst(1, 2, 3);

    Assert.assertEquals(1, result);
  }

  @Test
  public void iload_0_byte() {
    final byte result = Iload_0_Byte.getFirst((byte) 1, 2, 3);

    Assert.assertEquals(1, result);
  }

  @Test
  public void iload_0_char() {
    final char result = Iload_0_Char.getFirst((char) 1, 2, 3);

    Assert.assertEquals(1, result);
  }

  @Test
  public void iload_0_short() {
    final short result = Iload_0_Short.getFirst((byte) 1, 2, 3);

    Assert.assertEquals(1, result);
  }

  @Test
  public void iload_0_noargs() {
    try {
      Iload_0_NoArgs.getFirst();
      Assert.fail();
    } catch (VerifyError e) {
      Assert.assertEquals("(class: org/isk/jvmhardcore/bytecode/partfour/Iload_0_NoArgs, "
          + "method: getFirst signature: ()I) Accessing value from uninitialized register 0", e.getMessage());
    }
  }

  @Test
  public void iload_1() {
    final int result = Iload_1.getSecond(1, 2, 3);

    Assert.assertEquals(2, result);
  }

  @Test
  public void reassign() {
    final double result = Reassign.reassign();

    Assert.assertEquals(1.0, result, 0.00001);
  }
}