package org.isk.jvmhardcore.lexp.core;

public abstract class Leaf<T> implements Expression {
  final public T data;

  public Leaf(T data) {
    super();
    this.data = data;
  }
}
