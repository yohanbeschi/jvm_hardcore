package org.isk.jvmhardcore.visitor.three;

import org.isk.jvmhardcore.visitor.three.structure.FieldList;
import org.isk.jvmhardcore.visitor.three.structure.MethodList;
import org.isk.jvmhardcore.visitor.three.pattern.Visitor;
import org.isk.jvmhardcore.visitor.three.structure.ClassFile;
import org.isk.jvmhardcore.visitor.three.structure.ClassName;
import org.isk.jvmhardcore.visitor.three.structure.Field;
import org.isk.jvmhardcore.visitor.three.structure.Method;

public class PrettyPrint implements Visitor {
  final private StringBuilder sb = new StringBuilder();

  final private ClassFile classFile;

  public PrettyPrint(ClassFile classFile) {
    this.classFile = classFile;
  }

  public void visit(ClassFile classFile) {
    this.sb.append("Class Name: ");
  }

  @Override
  public void visit(ClassName className) {
    this.sb.append(className.getName()).append("\n");
  }

  @Override
  public void visit(MethodList methods) {
    this.sb.append("  Methods:").append("\n");
  }

  @Override
  public void visit(Method method) {
    this.sb.append("    ").append(method.getName()).append("\n");
  }

  @Override
  public void visit(FieldList fields) {
    this.sb.append("  Fields:").append("\n");
  }

  @Override
  public void visit(Field field) {
    this.sb.append("    ").append(field.getName()).append("\n");
  }

  public String build() {
    this.classFile.accept(this);
    return this.sb.toString();
  }
}
