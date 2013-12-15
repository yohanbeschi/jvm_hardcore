package org.isk.jvmhardcore.math.solver;

import java.util.LinkedList;
import java.util.Stack;

import org.isk.jvmhardcore.math.MathException;
import org.isk.jvmhardcore.math.common.Operator;

public class MathSolver {
  private final Stack<Number> processingStack;

  public MathSolver() {
    this.processingStack = new Stack<>();
  }

  @SuppressWarnings("unchecked")
  public <T> T resolve(LinkedList<Object> postfixExpression) {
    this.processingStack.clear();

    for (Object object : postfixExpression) {
      if (object instanceof Operator) {
        this.compute((Operator) object);
      } else {
        this.processingStack.push((Number) object);
      }
    }

    return (T) this.processingStack.pop();
  }

  private void compute(Operator operator) {
    final Number right = this.processingStack.pop();
    final Number left = this.processingStack.pop();

    switch (operator) {
      case PLUS:
        this.add(left, right);
        break;
      case MINUS:
        this.subtract(left, right);
        break;
      case TIMES:
        this.multiply(left, right);
        break;
      case DIVIDE:
        this.divide(left, right);
        break;
      default:
        throw new MathException("Unknown operator: " + operator);
    }
  }

  private void add(Number left, Number right) {
    Number n = null;

    if (left instanceof Integer && right instanceof Integer) {
      n = left.intValue() + right.intValue();
    } else {
      n = left.doubleValue() + right.doubleValue();
    }

    this.processingStack.push(n);
  }

  private void subtract(Number left, Number right) {
    Number n = null;

    if (left instanceof Integer && right instanceof Integer) {
      n = left.intValue() - right.intValue();
    } else {
      n = left.doubleValue() - right.doubleValue();
    }

    this.processingStack.push(n);
  }

  private void multiply(Number left, Number right) {
    Number n = null;

    if (left instanceof Integer && right instanceof Integer) {
      n = left.intValue() * right.intValue();
    } else {
      n = left.doubleValue() * right.doubleValue();
    }

    this.processingStack.push(n);
  }

  private void divide(Number left, Number right) {
    Number n = null;
    
    if (left instanceof Integer && right instanceof Integer && (left.intValue() % right.intValue()) == 0) {
      n = left.intValue() / right.intValue();
    } else {
      n = left.doubleValue() / right.doubleValue();
    }

    this.processingStack.push(n);
  }
}
