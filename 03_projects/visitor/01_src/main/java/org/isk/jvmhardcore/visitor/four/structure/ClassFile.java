package org.isk.jvmhardcore.visitor.four.structure;

import org.isk.jvmhardcore.visitor.four.pattern.Visitable;
import org.isk.jvmhardcore.visitor.four.pattern.Visitor;

public class ClassFile implements Visitable {
  final private String className;
  final private CustomList<Field> fields;
  final private CustomList<Method> methods;

  public ClassFile(String className) {
    this.className = className;
    this.fields = new CustomList<Field>();
    this.methods = new CustomList<Method>();
  }

  public void addField(Field field) {
    this.fields.add(field);
  }

  public void addMethod(Method method) {
    this.methods.add(method);
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visitClass();
    visitor.visitClassName(this.className);

    visitor.visitFieldListSize(this.fields.size());
    this.fields.accept(visitor);

    visitor.visitMethodListSize(this.methods.size());
    this.methods.accept(visitor);
  }
}
