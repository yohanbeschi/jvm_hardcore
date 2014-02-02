package org.isk.jvmhardcore.visitor.four.pattern;

public interface Visitor {
  void visitClass();
  void visitClassName(String name);
  void visitMethodListSize(int size);
  void visitMethodName(String name);
  void visitFieldListSize(int size);
  void visitFieldName(String name);
}
