package org.isk.jvmhardcore.visitor.four.structure;

import java.util.ArrayList;

import org.isk.jvmhardcore.visitor.four.pattern.Visitable;
import org.isk.jvmhardcore.visitor.four.pattern.Visitor;

public class CustomList<E extends Visitable> extends ArrayList<E> implements Visitable {
  private static final long serialVersionUID = 1L;

  @Override
  public void accept(Visitor visitor) {
    for (E e : this) {
      e.accept(visitor);
    }
  }
}
