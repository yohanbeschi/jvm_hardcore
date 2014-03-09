package org.isk.jvmhardcore.lexp.structure;

import org.isk.jvmhardcore.lexp.core.Leaf;
import org.isk.jvmhardcore.lexp.visitor.Visitor;

public class IntValue extends Leaf<Integer> {

  public IntValue(Integer data) {
    super(data);
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visitIntValue(super.data);
  }

}
