package org.isk.jvmhardcore.pjba.util;

import java.util.LinkedList;

import org.isk.jvmhardcore.pjba.visitor.Visitable;
import org.isk.jvmhardcore.pjba.visitor.Visitor;

public class PjbaLinkedList<E extends Visitable> extends LinkedList<E> implements Visitable {
  private static final long serialVersionUID = 1L;

  @Override
  public void accept(Visitor visitor) {
    for (Visitable v : this) {
      if (v != null) {
        v.accept(visitor);
      }
    }
  }
}
