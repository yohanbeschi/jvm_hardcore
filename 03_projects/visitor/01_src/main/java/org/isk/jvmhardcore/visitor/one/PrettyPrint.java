package org.isk.jvmhardcore.visitor.one;

import org.isk.jvmhardcore.visitor.one.structure.ClassFile;
import org.isk.jvmhardcore.visitor.one.structure.Field;
import org.isk.jvmhardcore.visitor.one.structure.Method;

public class PrettyPrint {
  final private ClassFile classFile;

  public PrettyPrint(ClassFile classFile) {
    this.classFile = classFile;
  }

  public String build() {
    final StringBuilder sb = new StringBuilder();
    sb.append("Class Name: ").append(classFile.getClassName()).append("\n");

    // Add fields
    sb.append("  Fields:").append("\n");
    for (Field field : classFile.getFields()) {
      sb.append("    ").append(field.getName()).append("\n");
    }

    // Add methods
    sb.append("  Methods:").append("\n");
    for (Method method : classFile.getMethods()) {
      sb.append("    ").append(method.getName()).append("\n");
    }

    return sb.toString();
  }
}
