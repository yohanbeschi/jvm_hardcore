package org.isk.jvmhardcore.lexp.visitor;

public interface Visitable {
  void accept(Visitor visitor);
}
