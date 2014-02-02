package org.isk.jvmhardcore.visitor.one.structure;

import java.util.ArrayList;
import java.util.List;

public class ClassFile {
  final private String className;
  final private List<Field> fields;
  final private List<Method> methods;

  public ClassFile(String className) {
    this.className = className;
    this.fields = new ArrayList<Field>();
    this.methods = new ArrayList<Method>();
  }

  public void addField(Field field) {
    this.fields.add(field);
  }

  public void addMethod(Method method) {
    this.methods.add(method);
  }

  public String getClassName() {
    return this.className;
  }

  public List<Field> getFields() {
    return this.fields;
  }

  public List<Method> getMethods() {
    return this.methods;
  }
}
