package org.isk.jvmhardcore.lexp.operators;

public enum RelationalOperator {
  EQ, // ==
  NE, // !=
  LT, // <
  LE, // <=
  GT, // >
  GE; // >=

  public RelationalOperator inverse() {
    switch (this) {
      case EQ:
        return NE;
      case NE:
        return EQ;
      case LT:
        return GE;
      case LE:
        return GT;
      case GT:
        return LE;
      case GE:
        return LT;
    }

    return null;
  }
}
