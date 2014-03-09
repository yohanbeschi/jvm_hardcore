package org.isk.jvmhardcore.lexp.structure;

import org.isk.jvmhardcore.lexp.core.Expression;
import org.isk.jvmhardcore.lexp.core.Node;
import org.isk.jvmhardcore.lexp.operators.RelationalOperator;
import org.isk.jvmhardcore.lexp.visitor.Visitor;

public class RelationalExpression extends Node<RelationalOperator> {

  public RelationalExpression(Expression left, RelationalOperator data, Expression right) {
    super(left, data, right);
  }

  @Override
  public void accept(Visitor visitor) {
    super.left.accept(visitor);
    super.right.accept(visitor);
    visitor.visitRelationExpression(this.data);
  }
}
