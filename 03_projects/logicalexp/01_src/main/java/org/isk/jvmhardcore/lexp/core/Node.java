package org.isk.jvmhardcore.lexp.core;

public abstract class Node<T> implements Expression {
  final public Expression left;
  final public Expression right;
  final public T data;

  public Node(Expression left, T data, Expression right) {
    super();
    this.left = left;
    this.data = data;
    this.right = right;
  }
}
