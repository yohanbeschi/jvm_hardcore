package org.isk.jvmhardcore.math.solver;

import java.util.LinkedList;

import org.isk.jvmhardcore.math.common.Operator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class MathSolverTest {
  private static MathSolver SOLVER;

  @BeforeClass
  public static void init() {
    SOLVER = new MathSolver();
  }

  private LinkedList<Object> postfixExpression(final Object... objects) {
    final LinkedList<Object> list = new LinkedList<>();

    for (Object o : objects) {
      list.add(o);
    }

    return list;
  }

  private void test(final LinkedList<Object> postfixExpression, final int expectedResult) {
    final int result = SOLVER.resolve(postfixExpression);
    Assert.assertEquals(expectedResult, result);
  }

  private void test(final LinkedList<Object> postfixExpression, final double expectedResult) {
    final double result = SOLVER.resolve(postfixExpression);
    Assert.assertEquals(expectedResult, result, 0.001);
  }

  @Test
  public void resolve0() {
    // 2 + 5
    this.test(this.postfixExpression(2, 5, Operator.PLUS), 7);
  }

  @Test
  public void resolve1() {
    // 2.5 + 5
    this.test(this.postfixExpression(2.5, 5, Operator.PLUS), 7.5);
  }

  @Test
  public void resolve2() {
    // 2 + 5.67
    this.test(this.postfixExpression(2, 5.67, Operator.PLUS), 7.67);
  }

  @Test
  public void resolve3() {
    // 2.897 + 5.67
    this.test(this.postfixExpression(2.897, 5.67, Operator.PLUS), 8.567);
  }

  @Test
  public void resolve4() {
    // 2 - 5
    this.test(this.postfixExpression(2, 5, Operator.MINUS), -3);
  }

  @Test
  public void resolve5() {
    // 5 - 2
    this.test(this.postfixExpression(5, 2, Operator.MINUS), 3);
  }

  @Test
  public void resolve6() {
    // 5.1 - 2
    this.test(this.postfixExpression(5.1, 2, Operator.MINUS), 3.1);
  }

  @Test
  public void resolve7() {
    // 5 - 2.27
    this.test(this.postfixExpression(5, 2.27, Operator.MINUS), 2.73);
  }

  @Test
  public void resolve8() {
    // 5.698 - 2.277
    this.test(this.postfixExpression(5.698, 2.277, Operator.MINUS), 3.421);
  }

  @Test
  public void resolve9() {
    // 2 * 5
    this.test(this.postfixExpression(2, 5, Operator.TIMES), 10);
  }

  @Test
  public void resolve10() {
    // 2.56 * 5
    this.test(this.postfixExpression(2.56, 5, Operator.TIMES), 12.8);
  }

  @Test
  public void resolve11() {
    // 2 * 5.25
    this.test(this.postfixExpression(2, 5.25, Operator.TIMES), 10.50);
  }

  @Test
  public void resolve12() {
    // 2.75 * 5.25
    this.test(this.postfixExpression(2.75, 5.25, Operator.TIMES), 14.4375);
  }

  @Test
  public void resolve13() {
    // 4 / 2
    this.test(this.postfixExpression(4, 2, Operator.DIVIDE), 2);
  }

  @Test
  public void resolve14() {
    // 5.5 / 2
    this.test(this.postfixExpression(5.5, 2, Operator.DIVIDE), 2.75);
  }

  @Test
  public void resolve15() {
    // 5 / 2.5
    this.test(this.postfixExpression(5, 2.5, Operator.DIVIDE), 2.0);
  }

  @Test
  public void resolve16() {
    // 5.5 / 2.5
    this.test(this.postfixExpression(5.5, 2.5, Operator.DIVIDE), 2.2);
  }

  @Test
  public void resolve17() {
    // 0 / 2.5
    this.test(this.postfixExpression(0, 2.5, Operator.DIVIDE), 0.0);
  }

  @Test
  public void resolve18() {
    // 10 / 0
    try {
      SOLVER.resolve(this.postfixExpression(10, 0, Operator.DIVIDE));
      Assert.fail();
    } catch (ArithmeticException e) {
      Assert.assertEquals("class java.lang.ArithmeticException: / by zero", e.getClass() + ": " + e.getMessage());
    }
  }

  // ==================================================================================================================

  @Test
  public void resolve19() {
    // 0.5 + -50.0 * 32.756
    this.test(this.postfixExpression(.5, -50., 32.756, Operator.TIMES, Operator.PLUS), -1637.3);
  }

  @Test
  public void resolve20() {
    // 2 + 3 - 4 * 5 / -17
    this.test(this.postfixExpression(2, 3, Operator.PLUS, 4, 5, Operator.TIMES, -17, Operator.DIVIDE, Operator.MINUS),
        6.1764);
  }

  @Test
  public void resolve21() {
    // 2 + 5 - 2
    this.test(this.postfixExpression(2, 5, Operator.PLUS, 2, Operator.MINUS), 5);
  }

  @Test
  public void resolve22() {
    // ((2 + 5) * 2) - 2
    this.test(this.postfixExpression(2, 5, Operator.PLUS, 2, Operator.TIMES, 2, Operator.MINUS), 12);
  }

  @Test
  public void resolve23() {
    // ((2 + 5) * (10 - 8)) - 2
    this.test(this.postfixExpression(2, 5, Operator.PLUS, 10, 8, Operator.MINUS, Operator.TIMES, 2, Operator.MINUS),
        12);
  }

  @Test
  public void resolve24() {
    // ((2 + 5) * (10 - 8) - 2) + 3
    this.test(this.postfixExpression(2, 5, Operator.PLUS, 10, 8, Operator.MINUS, Operator.TIMES, 2, Operator.MINUS,
        3, Operator.PLUS), 15);
  }

  @Test
  public void resolve25() {
    // ((2 + 5) - 2 * (10 - 8)) + 3
    this.test(this.postfixExpression(2, 5, Operator.PLUS, 2, 10, 8, Operator.MINUS, Operator.TIMES, Operator.MINUS,
        3, Operator.PLUS), 6);
  }

  @Test
  public void resolve26() {
    // (2 * (2 + 5) - (10 - 8)) + 3
    this.test(this.postfixExpression(2, 2, 5, Operator.PLUS, Operator.TIMES, 10, 8, Operator.MINUS, Operator.MINUS,
        3, Operator.PLUS), 15);
  }

  @Test
  public void resolve27() {
    // 4 - (2 + 5) - 2
    this.test(this.postfixExpression(4, 2, 5, Operator.PLUS, Operator.MINUS, 2, Operator.MINUS), -5);
  }
}