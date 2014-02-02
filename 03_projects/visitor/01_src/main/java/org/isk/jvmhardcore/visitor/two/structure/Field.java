package org.isk.jvmhardcore.visitor.two.structure;

import org.isk.jvmhardcore.visitor.two.pattern.Visitable;
import org.isk.jvmhardcore.visitor.two.pattern.Visitor;

public class Field implements Visitable {
  final private String name;

  public Field(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
}
