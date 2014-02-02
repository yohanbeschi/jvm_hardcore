package org.isk.jvmhardcore.visitor.four;

import org.isk.jvmhardcore.visitor.four.pattern.Visitor;
import org.isk.jvmhardcore.visitor.four.structure.ClassFile;

public class PrettyPrint implements Visitor {
  final private StringBuilder sb = new StringBuilder();

  final private ClassFile classFile;

  public PrettyPrint(ClassFile classFile) {
    this.classFile = classFile;
  }

  @Override
  public void visitClass() {
    this.sb.append("Class Name: ");
  }

  @Override
  public void visitClassName(String name) {
    this.sb.append(name).append("\n");
  }

  @Override
  public void visitMethodListSize(int size) {
    this.sb.append("  Methods:").append("\n");
  }

  @Override
  public void visitMethodName(String name) {
    this.sb.append("    ").append(name).append("\n");
  }

  @Override
  public void visitFieldListSize(int size) {
    this.sb.append("  Fields:").append("\n");
  }

  @Override
  public void visitFieldName(String name) {
    this.sb.append("    ").append(name).append("\n");
  }

  public String build() {
    this.classFile.accept(this);
    return this.sb.toString();
  }
}
