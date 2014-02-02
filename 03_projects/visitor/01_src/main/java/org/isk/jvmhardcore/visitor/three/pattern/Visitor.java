package org.isk.jvmhardcore.visitor.three.pattern;

import org.isk.jvmhardcore.visitor.three.structure.ClassName;
import org.isk.jvmhardcore.visitor.three.structure.FieldList;
import org.isk.jvmhardcore.visitor.three.structure.MethodList;
import org.isk.jvmhardcore.visitor.three.structure.ClassFile;
import org.isk.jvmhardcore.visitor.three.structure.Field;
import org.isk.jvmhardcore.visitor.three.structure.Method;

public interface Visitor {
  void visit(ClassFile classFile);
  void visit(ClassName className);
  void visit(MethodList methods);
  void visit(Method method);
  void visit(FieldList fields);
  void visit(Field field);
}
