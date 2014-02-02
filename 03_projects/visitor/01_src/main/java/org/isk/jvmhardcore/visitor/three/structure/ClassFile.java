package org.isk.jvmhardcore.visitor.three.structure;

import org.isk.jvmhardcore.visitor.three.pattern.Visitable;
import org.isk.jvmhardcore.visitor.three.pattern.Visitor;

public class ClassFile implements Visitable {
  final private ClassName className;
  final private FieldList fields;
  final private MethodList methods;

  public ClassFile(String className) {
    this.className = new ClassName(className);
    this.fields = new FieldList();
    this.methods = new MethodList();
  }

  public void addField(Field field) {
    this.fields.add(field);
  }

  public void addMethod(Method method) {
    this.methods.add(method);
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
    visitor.visit(this.className);
    this.fields.accept(visitor);
    this.methods.accept(visitor);
  }
}
