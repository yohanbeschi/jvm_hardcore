package org.isk.jvmhardcore.lexp.structure;

import org.isk.jvmhardcore.lexp.core.Expression;
import org.isk.jvmhardcore.lexp.operators.RelationalOperator;
import org.isk.jvmhardcore.lexp.visitor.Visitor;

public class ZeroRelationalExpression extends RelationalExpression {

  public ZeroRelationalExpression(Expression left) {
    super(left, RelationalOperator.NE, null);
  }

  public ZeroRelationalExpression(Expression left, RelationalOperator data) {
    super(left, data, null);
  }

  @Override
  public void accept(Visitor visitor) {
    super.left.accept(visitor);
    visitor.visitZeroRelationExpression(this.data);
  }
}
