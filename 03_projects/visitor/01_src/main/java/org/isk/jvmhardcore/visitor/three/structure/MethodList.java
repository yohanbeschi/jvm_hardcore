package org.isk.jvmhardcore.visitor.three.structure;

import java.util.ArrayList;
import java.util.List;

import org.isk.jvmhardcore.visitor.three.pattern.Visitable;
import org.isk.jvmhardcore.visitor.three.pattern.Visitor;

public class MethodList implements Visitable {
  private List<Method> methods;

  public MethodList() {
    this.methods = new ArrayList<Method>();
  }

  public void add(Method method) {
    this.methods.add(method);
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);

    for (Method method : this.methods) {
      method.accept(visitor);
    }
  }
}
