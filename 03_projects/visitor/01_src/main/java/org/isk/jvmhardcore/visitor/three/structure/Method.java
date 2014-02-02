package org.isk.jvmhardcore.visitor.three.structure;

import org.isk.jvmhardcore.visitor.three.pattern.Visitable;
import org.isk.jvmhardcore.visitor.three.pattern.Visitor;

public class Method implements Visitable {
  final private String name;

  public Method(String name) {
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
