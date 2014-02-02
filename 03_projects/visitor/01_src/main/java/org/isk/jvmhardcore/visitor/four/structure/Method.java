package org.isk.jvmhardcore.visitor.four.structure;

import org.isk.jvmhardcore.visitor.four.pattern.Visitable;
import org.isk.jvmhardcore.visitor.four.pattern.Visitor;

public class Method implements Visitable {
  final private String name;

  public Method(String name) {
    this.name = name;
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visitMethodName(this.name);
  }
}
