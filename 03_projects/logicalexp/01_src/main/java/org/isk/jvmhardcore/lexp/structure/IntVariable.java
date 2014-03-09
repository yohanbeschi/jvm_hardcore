package org.isk.jvmhardcore.lexp.structure;

import org.isk.jvmhardcore.lexp.core.Leaf;
import org.isk.jvmhardcore.lexp.visitor.Visitor;

public class IntVariable extends Leaf<Integer> {

  public IntVariable(Integer data) {
    super(data);
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visitIntVariable(super.data);
  }
}
