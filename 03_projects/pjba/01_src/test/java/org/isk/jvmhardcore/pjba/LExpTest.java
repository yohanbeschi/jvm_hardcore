package org.isk.jvmhardcore.pjba;

import org.isk.pjb.LExp;
import org.junit.Assert;
import org.junit.Test;

public class LExpTest {
  @Test
  public void arithmetic() {
    // (a | 2) + 567 * b
    final int i = LExp.arithmetic(1, 2);
    Assert.assertEquals(1137, i);
  }

  @Test
  public void relational0() {
    // a < b
    final boolean b1 = LExp.relational0(1, 2);
    Assert.assertTrue(b1);

    final boolean b2 = LExp.relational0(1, 1);
    Assert.assertFalse(b2);

    final boolean b3 = LExp.relational0(2, 1);
    Assert.assertFalse(b3);
  }

  @Test
  public void relational1() {
    // a && b >= 0
    final boolean b1 = LExp.relational1(true, 2);
    Assert.assertTrue(b1);

    final boolean b2 = LExp.relational1(true, 0);
    Assert.assertTrue(b2);

    final boolean b3 = LExp.relational1(true, -1);
    Assert.assertFalse(b3);

    final boolean b4 = LExp.relational1(false, 2);
    Assert.assertFalse(b4);

    final boolean b5 = LExp.relational1(false, -1);
    Assert.assertFalse(b5);
  }

  @Test
  public void relational2() {
    // a && 5 >= 10
    final boolean b1 = LExp.relational2(true);
    Assert.assertFalse(b1);

    final boolean b2 = LExp.relational2(false);
    Assert.assertFalse(b2);
  }

  @Test
  public void returnTrue() {
    // a (or a == true)
    final boolean b1 = LExp.returnTrue(true);
    Assert.assertTrue(b1);

    final boolean b2 = LExp.returnTrue(false);
    Assert.assertFalse(b2);
  }

  @Test
  public void and() {
    // a && b
    final boolean b1 = LExp.and(true, true);
    Assert.assertTrue(b1);

    final boolean b2 = LExp.and(false, true);
    Assert.assertFalse(b2);

    final boolean b3 = LExp.and(true, false);
    Assert.assertFalse(b3);

    final boolean b4 = LExp.and(false, false);
    Assert.assertFalse(b4);
  }

  @Test
  public void or() {
    // a || b
    final boolean b1 = LExp.or(true, true);
    Assert.assertTrue(b1);

    final boolean b2 = LExp.or(false, true);
    Assert.assertTrue(b2);

    final boolean b3 = LExp.or(true, false);
    Assert.assertTrue(b3);

    final boolean b4 = LExp.or(false, false);
    Assert.assertFalse(b4);
  }

  @Test
  public void or_and() {
    // (a && b) || c
    final boolean b1 = LExp.or_and(true, true, true);
    Assert.assertTrue(b1);

    final boolean b2 = LExp.or_and(false, true, true);
    Assert.assertTrue(b2);

    final boolean b3 = LExp.or_and(true, false, true);
    Assert.assertTrue(b3);

    final boolean b4 = LExp.or_and(false, false, true);
    Assert.assertTrue(b4);

    final boolean b5 = LExp.or_and(true, true, false);
    Assert.assertTrue(b5);

    final boolean b6 = LExp.or_and(false, true, false);
    Assert.assertFalse(b6);

    final boolean b7 = LExp.or_and(true, false, false);
    Assert.assertFalse(b7);

    final boolean b8 = LExp.or_and(false, false, false);
    Assert.assertFalse(b8);
  }

  @Test
  public void and_or() {
    // (a || b) && c
    final boolean b1 = LExp.and_or(true, true, true);
    Assert.assertTrue(b1);

    final boolean b2 = LExp.and_or(false, true, true);
    Assert.assertTrue(b2);

    final boolean b3 = LExp.and_or(true, false, true);
    Assert.assertTrue(b3);

    final boolean b4 = LExp.and_or(false, false, true);
    Assert.assertFalse(b4);

    final boolean b5 = LExp.and_or(true, true, false);
    Assert.assertFalse(b5);

    final boolean b6 = LExp.and_or(false, true, false);
    Assert.assertFalse(b6);

    final boolean b7 = LExp.and_or(true, false, false);
    Assert.assertFalse(b7);

    final boolean b8 = LExp.and_or(false, false, false);
    Assert.assertFalse(b8);
  }

  @Test
  public void and_or_or() {
    // ((a || b) || (c || d)) && e
    final boolean b1 = LExp.and_or_or(true, true, true, true, true);
    Assert.assertTrue(b1);

    final boolean b2 = LExp.and_or_or(true, false, false, false, true);
    Assert.assertTrue(b2);

    final boolean b3 = LExp.and_or_or(false, true, false, false, true);
    Assert.assertTrue(b3);

    final boolean b4 = LExp.and_or_or(false, false, true, false, true);
    Assert.assertTrue(b4);

    final boolean b5 = LExp.and_or_or(false, false, false, true, true);
    Assert.assertTrue(b5);

    final boolean b6 = LExp.and_or_or(true, true, true, true, false);
    Assert.assertFalse(b6);
  }

  @Test
  public void or_and_and() {
    // ((a && b) && (c && d)) || e
    final boolean b1 = LExp.or_and_and(true, true, true, true, true);
    Assert.assertTrue(b1);

    final boolean b2 = LExp.or_and_and(true, true, true, true, false);
    Assert.assertTrue(b2);

    final boolean b3 = LExp.or_and_and(false, false, false, false, true);
    Assert.assertTrue(b3);

    final boolean b4 = LExp.or_and_and(true, false, false, false, false);
    Assert.assertFalse(b4);

    final boolean b5 = LExp.or_and_and(false, true, false, false, false);
    Assert.assertFalse(b5);

    final boolean b6 = LExp.or_and_and(false, false, true, false, false);
    Assert.assertFalse(b6);

    final boolean b7 = LExp.or_and_and(false, false, false, true, false);
    Assert.assertFalse(b7);
  }

  @Test
  public void mixed1() {
    // (a && (b || c)) && (d || e)
    final boolean b1 = LExp.mixed1(true, true, true, true, true);
    Assert.assertTrue(b1);

    final boolean b2 = LExp.mixed1(true, true, false, true, false);
    Assert.assertTrue(b2);

    final boolean b3 = LExp.mixed1(true, false, true, false, true);
    Assert.assertTrue(b3);

    final boolean b4 = LExp.mixed1(true, false, false, false, false);
    Assert.assertFalse(b4);

    final boolean b5 = LExp.mixed1(true, true, false, false, false);
    Assert.assertFalse(b5);

    final boolean b6 = LExp.mixed1(false, false, true, false, false);
    Assert.assertFalse(b6);

    final boolean b7 = LExp.mixed1(false, false, false, true, false);
    Assert.assertFalse(b7);
  }

  @Test
  public void mixed2() {
    // (a || (b && c)) || (d && e)
    final boolean b1 = LExp.mixed2(true, true, true, true, true);
    Assert.assertTrue(b1);

    final boolean b2 = LExp.mixed2(true, false, false, false, false);
    Assert.assertTrue(b2);

    final boolean b3 = LExp.mixed2(false, true, true, false, false);
    Assert.assertTrue(b3);

    final boolean b4 = LExp.mixed2(false, false, false, true, true);
    Assert.assertTrue(b4);

    final boolean b5 = LExp.mixed2(false, true, false, false, false);
    Assert.assertFalse(b5);

    final boolean b6 = LExp.mixed2(false, false, true, false, false);
    Assert.assertFalse(b6);

    final boolean b7 = LExp.mixed2(false, false, false, true, false);
    Assert.assertFalse(b7);

    final boolean b8 = LExp.mixed2(false, false, false, false, true);
    Assert.assertFalse(b8);

    final boolean b9 = LExp.mixed2(false, false, false, false, false);
    Assert.assertFalse(b9);
  }

  @Test
  public void mixed3() {
    // ((a || b) && (c && (d || e))) || (f || (g && h))
    final boolean b1 = LExp.mixed3(true, true, true, true, true, true, true, true);
    Assert.assertTrue(b1);

    final boolean b2 = LExp.mixed3(true, false, true, true, false, false, false, false);
    Assert.assertTrue(b2);

    final boolean b3 = LExp.mixed3(false, true, true, false, true, false, false, false);
    Assert.assertTrue(b3);

    final boolean b4 = LExp.mixed3(false, false, false, false, false, true, false, false);
    Assert.assertTrue(b4);

    final boolean b5 = LExp.mixed3(false, false, false, false, false, false, true, true);
    Assert.assertTrue(b5);

    final boolean b6 = LExp.mixed3(false, false, false, false, false, false, false, true);
    Assert.assertFalse(b6);

    final boolean b7 = LExp.mixed3(true, false, false, false, false, false, false, true);
    Assert.assertFalse(b7);

    final boolean b8 = LExp.mixed3(true, false, true, false, false, false, false, false);
    Assert.assertFalse(b8);

    final boolean b9 = LExp.mixed3(false, false, false, false, false, false, false, false);
    Assert.assertFalse(b9);
  }

  @Test
  public void mixed4() {
    // ((a && b) || (c || (d && e))) && (f && (g || h))
    final boolean b1 = LExp.mixed4(true, true, true, true, true, true, true, true);
    Assert.assertTrue(b1);

    final boolean b2 = LExp.mixed4(true, true, false, false, false, true, true, false);
    Assert.assertTrue(b2);

    final boolean b3 = LExp.mixed4(false, false, true, false, false, true, false, true);
    Assert.assertTrue(b3);

    final boolean b4 = LExp.mixed4(false, false, false, true, true, true, false, true);
    Assert.assertTrue(b4);

    final boolean b5 = LExp.mixed4(false, true, false, false, false, true, true, false);
    Assert.assertFalse(b5);

    final boolean b6 = LExp.mixed4(false, false, false, false, false, true, false, true);
    Assert.assertFalse(b6);

    final boolean b7 = LExp.mixed4(false, false, false, true, true, false, false, true);
    Assert.assertFalse(b7);

    final boolean b8 = LExp.mixed4(false, true, false, false, false, false, true, false);
    Assert.assertFalse(b8);

    final boolean b9 = LExp.mixed4(false, false, false, false, false, false, false, false);
    Assert.assertFalse(b9);
  }
}
