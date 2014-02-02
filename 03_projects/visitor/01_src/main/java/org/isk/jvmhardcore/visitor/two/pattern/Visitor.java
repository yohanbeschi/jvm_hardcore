package org.isk.jvmhardcore.visitor.two.pattern;

import org.isk.jvmhardcore.visitor.two.structure.ClassFile;
import org.isk.jvmhardcore.visitor.two.structure.Field;
import org.isk.jvmhardcore.visitor.two.structure.Method;

public interface Visitor {
  void visit(ClassFile classFile);
  void visit(Method method);
  void visit(Field field);
}
