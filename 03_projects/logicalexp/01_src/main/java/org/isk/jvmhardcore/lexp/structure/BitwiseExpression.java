package org.isk.jvmhardcore.lexp.structure;

import org.isk.jvmhardcore.lexp.core.Expression;
import org.isk.jvmhardcore.lexp.core.Node;
import org.isk.jvmhardcore.lexp.operators.BitwiseOperator;
import org.isk.jvmhardcore.lexp.visitor.Visitor;

public class BitwiseExpression extends Node<BitwiseOperator> {
  public BitwiseExpression(Expression left, BitwiseOperator data, Expression right) {
    super(left, data, right);
  }

  @Override
  public void accept(Visitor visitor) {
    super.left.accept(visitor);
    super.right.accept(visitor);
    visitor.visit(this.data);
  }
}
