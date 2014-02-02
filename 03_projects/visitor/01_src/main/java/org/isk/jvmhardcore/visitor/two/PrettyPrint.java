package org.isk.jvmhardcore.visitor.two;

import org.isk.jvmhardcore.visitor.two.pattern.Visitor;
import org.isk.jvmhardcore.visitor.two.structure.ClassFile;
import org.isk.jvmhardcore.visitor.two.structure.Field;
import org.isk.jvmhardcore.visitor.two.structure.Method;

public class PrettyPrint implements Visitor {
  final private StringBuilder sb = new StringBuilder();

  final private ClassFile classFile;

  public PrettyPrint(ClassFile classFile) {
    this.classFile = classFile;
  }

  @Override
  public void visit(ClassFile classFile) {
    this.sb.append("Class Name: ").append(classFile.getClassName()).append("\n");
  }

  @Override
  public void visit(Method method) {
    this.sb.append("  ").append(method.getName()).append("\n");
  }

  @Override
  public void visit(Field field) {
    this.sb.append("  ").append(field.getName()).append("\n");
  }

  public String build() {
    this.classFile.accept(this);
    return sb.toString();
  }
}
