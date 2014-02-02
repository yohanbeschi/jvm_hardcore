package org.isk.jvmhardcore.pjba.structure.attribute;

import org.isk.jvmhardcore.pjba.visitor.Visitable;
import org.isk.jvmhardcore.pjba.visitor.Visitor;

public abstract class Attribute implements Visitable {
  final private int nameIndex;

  public Attribute(final int nameIndex) {
    this.nameIndex = nameIndex;
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visitAttributeNameIndex(this.nameIndex);
  }
}
