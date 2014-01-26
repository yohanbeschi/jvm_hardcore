package org.isk.jvmhardcore.equality;

import org.junit.Assert;
import org.junit.Test;

public class EqualityTest {
  @Test
  public void stringEquality0() {
    final String s1 = "Bonjour";
    final String s2 = "Bonjour";

    Assert.assertEquals(s1, s2);
    Assert.assertTrue(s1 == s2);
  }

  @Test
  public void stringEquality1() {
    final String s1 = new String("Bonjour");
    final String s2 = new String("Bonjour");

    Assert.assertEquals(s1, s2);
    Assert.assertFalse(s1 == s2);
  }

  @Test
  public void stringEquality2() {
    final String i1 = StringOne.get();
    final String i2 = StringTwo.get();
    
    Assert.assertTrue(i1 == i2);
  }
  
//  @Test
//  public void integerEquality0() {
//    final int i1 = 5;
//    final int i2 = 5;
//    
//    Assert.assertTrue(i1 == i2);
//  }
  
  @Test
  public void integerEquality1() {
    final Integer i1 = -127;
    final Integer i2 = -127;
    
    Assert.assertTrue(i1 == i2);
  }
  
  @Test
  public void integerEquality2() {
    final Integer i1 = new Integer(5);
    final Integer i2 = new Integer(5);
    
    Assert.assertFalse(i1 == i2);
  }
  
  @Test
  public void integerEquality3() {
    final Integer i1 = 5_000;
    final Integer i2 = 5_000;
    
    Assert.assertFalse(i1 == i2);
  }
}
