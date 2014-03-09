package org.isk.jvmhardcore.lexp.structure;

import org.isk.jvmhardcore.lexp.core.Expression;
import org.isk.jvmhardcore.lexp.core.Node;
import org.isk.jvmhardcore.lexp.operators.ArithmeticOperator;
import org.isk.jvmhardcore.lexp.visitor.Visitor;

public class ArithmeticExpression extends Node<ArithmeticOperator> {
  public ArithmeticExpression(Expression left, ArithmeticOperator data, Expression right) {
    super(left, data, right);
  }

  @Override
  public void accept(Visitor visitor) {
    super.left.accept(visitor);
    super.right.accept(visitor);
    visitor.visit(this.data);
  }
}
