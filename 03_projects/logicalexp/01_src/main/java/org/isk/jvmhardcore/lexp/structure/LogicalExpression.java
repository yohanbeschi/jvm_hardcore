package org.isk.jvmhardcore.lexp.structure;

import org.isk.jvmhardcore.lexp.core.Expression;
import org.isk.jvmhardcore.lexp.core.Node;
import org.isk.jvmhardcore.lexp.operators.LogicalOperator;
import org.isk.jvmhardcore.lexp.visitor.Visitor;

public class LogicalExpression extends Node<LogicalOperator> {
  final public String name;

  public LogicalExpression(String name, Expression left, LogicalOperator data, Expression right) {
    super(left, data, right);
    this.name = name;
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visitBeforeLeft(this.name, super.data);
    this.left.accept(visitor);
    visitor.visitAfterLeft(this.name, super.data);
    this.right.accept(visitor);
  }
}
