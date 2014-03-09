package org.isk.jvmhardcore.lexp.visitor;

import org.isk.jvmhardcore.lexp.operators.ArithmeticOperator;
import org.isk.jvmhardcore.lexp.operators.BitwiseOperator;
import org.isk.jvmhardcore.lexp.operators.LogicalOperator;
import org.isk.jvmhardcore.lexp.operators.RelationalOperator;

public interface Visitor {
  void visitBeforeLeft(String name, LogicalOperator operator);
  void visitAfterLeft(String name, LogicalOperator operator);
  void visitRelationExpression(RelationalOperator expression);
  void visitZeroRelationExpression(RelationalOperator expression);
  void visit(ArithmeticOperator data);
  void visit(BitwiseOperator data);

  void visitIntValue(Integer integer);
  void visitIntVariable(Integer integer);
}
