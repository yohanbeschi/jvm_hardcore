package org.isk.jvmhardcore.lexp;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import org.isk.jvmhardcore.lexp.core.Expression;
import org.isk.jvmhardcore.lexp.operators.ArithmeticOperator;
import org.isk.jvmhardcore.lexp.operators.BitwiseOperator;
import org.isk.jvmhardcore.lexp.operators.LogicalOperator;
import org.isk.jvmhardcore.lexp.operators.RelationalOperator;
import org.isk.jvmhardcore.lexp.visitor.Visitor;

public class Compiler implements Visitor {

  final private StringBuilder bytecode;
  final private Expression expression;

  final private Set<String> usedLabels;
  final private Stack<String> andLabels;
  final private Stack<String> orLabels;
  final private Stack<LogicalOperator> logicalOperators;

  public Compiler(Expression expression) {
    this.bytecode = new StringBuilder();
    this.expression = expression;

    this.usedLabels = new HashSet<>();
    this.andLabels = new Stack<>();
    this.orLabels = new Stack<>();
    this.logicalOperators = new Stack<>();
  }

  public String compile() {
    this.expression.accept(this);
    return this.bytecode.toString();
  }

  @Override
  public void visitBeforeLeft(String name, LogicalOperator operator) {
    this.logicalOperators.push(operator);

    if (operator == LogicalOperator.AND) {
      this.andLabels.push(name);
    } else {
      this.orLabels.push(name);
    }

    // this.traceLogicalExpressionTraversal("beforeLeft", name);
  }

  @Override
  public void visitAfterLeft(String name, LogicalOperator operator) {
    this.logicalOperators.pop();

    if (operator == LogicalOperator.AND) {
      this.andLabels.pop();
    } else if (operator == LogicalOperator.OR) {
      this.orLabels.pop();
    }

    if (this.usedLabels.remove(name)) {
      this.writeLabel(name);
    }

    // this.traceLogicalExpressionTraversal("afterLeft", name);
  }

  @Override
  public void visit(ArithmeticOperator operator) {
    String instruction = null;

    switch (operator) {
      case PLUS:
        instruction = "iadd";
        break;
      case MINUS:
        instruction = "isub";
        break;
      case TIMES:
        instruction = "imul";
        break;
      case DIVIDE:
        instruction = "idiv";
        break;
    }

    this.bytecode.append(instruction).append("\n");
  }

  @Override
  public void visit(BitwiseOperator operator) {
    String instruction = null;

    switch (operator) {
      case SHL:
        instruction = "ishl";
        break;
      case SHR:
        instruction = "ishr";
        break;
      case USHR:
        instruction = "iushr";
        break;
      case AND:
        instruction = "iand";
        break;
      case OR:
        instruction = "ior";
        break;
      case XOR:
        instruction = "ixor";
        break;
    }

    this.bytecode.append(instruction).append("\n");
  }

  @Override
  public void visitRelationExpression(RelationalOperator operator) {
    // this.traceLogicalExpressionTraversal("re", operator);
    final RelationalParams params = this.getRelationalParams(operator);

    String instruction = null;

    switch (params.operator) {
      case EQ:
        instruction = "if_icmpeq";
        break;
      case GE:
        instruction = "if_icmpge";
        break;
      case GT:
        instruction = "if_icmpgt";
        break;
      case LE:
        instruction = "if_icmple";
        break;
      case LT:
        instruction = "if_icmplt";
        break;
      case NE:
        instruction = "if_icmpne";
        break;
    }

    this.bytecode.append(instruction).append(" ").append(params.label).append("\n");
  }

  @Override
  public void visitZeroRelationExpression(RelationalOperator operator) {
    // this.traceLogicalExpressionTraversal("zre", operator);
    final RelationalParams params = this.getRelationalParams(operator);

    String instruction = null;

    switch (params.operator) {
      case EQ:
        instruction = "ifeq";
        break;
      case GE:
        instruction = "ifge";
        break;
      case GT:
        instruction = "ifgt";
        break;
      case LE:
        instruction = "ifle";
        break;
      case LT:
        instruction = "iflt";
        break;
      case NE:
        instruction = "ifne";
        break;
    }

    this.bytecode.append(instruction).append(" ").append(params.label).append("\n");
  }

  @Override
  public void visitIntValue(Integer value) {
    this.bytecode.append("ldc ").append(value).append("\n");
  }

  @Override
  public void visitIntVariable(Integer indexInLV) {
    this.bytecode.append("iload ").append(indexInLV).append("\n");
  }

  private void writeLabel(String label) {
    this.bytecode.append(label).append(":\n");
  }

  private RelationalParams getRelationalParams(RelationalOperator operator) {
    RelationalOperator relationalOperator = null;
    String label = null;

    if (this.logicalOperators.isEmpty()) {
      relationalOperator = operator.inverse();
      label = "ko";
    } else if (this.logicalOperators.peek() == LogicalOperator.AND) {
      label = !this.orLabels.isEmpty() ? this.orLabels.peek() : "ko";
      relationalOperator = operator.inverse();
    } else if (this.logicalOperators.peek() == LogicalOperator.OR) {
      label = !this.andLabels.isEmpty() ? this.andLabels.peek() : "ok";
      relationalOperator = operator;
    } else {
      throw new RuntimeException("Don't know what to do!");
    }

    this.usedLabels.add(label);

    return new RelationalParams(relationalOperator, label);
  }

  private class RelationalParams {
    final RelationalOperator operator;
    final String label;

    public RelationalParams(RelationalOperator operator, String label) {
      super();
      this.operator = operator;
      this.label = label;
    }
  }

  // private void traceLogicalExpressionTraversal(String from, String nodeName) {
  // System.out.print(from + " " + nodeName + ": ");
  //
  // this.trace();
  // }
  //
  // private void traceLogicalExpressionTraversal(String from, RelationalOperator operator) {
  // System.out.print(from + " " + operator + ": ");
  //
  // this.trace();
  // }
  //
  // private void trace() {
  // for (LogicalOperator lo : this.logicalOperators) {
  // System.out.print(lo + ", ");
  // }
  //
  // System.out.print("\n");
  // }
}
