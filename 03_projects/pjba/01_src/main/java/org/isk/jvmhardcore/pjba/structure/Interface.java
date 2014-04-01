package org.isk.jvmhardcore.pjba.structure;

import org.isk.jvmhardcore.pjba.visitor.Visitable;
import org.isk.jvmhardcore.pjba.visitor.Visitor;

public class Interface implements Visitable {

  final private int constantClassIndex;

  public Interface(int constantClassIndex) {
    super();
    this.constantClassIndex = constantClassIndex;
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visitInterfaceConstantClassIndex(this.constantClassIndex);
  }

}
