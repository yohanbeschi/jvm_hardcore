package org.isk.jvmhardcore.visitor.four.structure;

import org.isk.jvmhardcore.visitor.four.pattern.Visitable;
import org.isk.jvmhardcore.visitor.four.pattern.Visitor;

public class Field implements Visitable {
  final private String name;

  public Field(String name) {
    this.name = name;
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visitFieldName(this.name);
  }
}
