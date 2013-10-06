package org.isk.jvmhardcore.bytecode.parttwo;

import junit.framework.Assert;

import org.isk.jvmhardcore.bytecode.parttwo.Adder;
import org.isk.jvmhardcore.bytecode.parttwo.AdderJava;
import org.junit.Test;

public class AdderTest {
  /**
   * Test the method <code>add()</code> of the class Adder from the file Adder.pjb
   */
  @Test
  public void add0() {
    final int sum = Adder.add(2, 3);
    
    Assert.assertEquals(5, sum);
  }
  
  /**
   * Test the method <code>add()</code> of the class AdderJava generated from Java code.
   */
  @Test
  public void add1() {
    final int sum = AdderJava.add(5, 4);
    
    Assert.assertEquals(9, sum);
  }
}
