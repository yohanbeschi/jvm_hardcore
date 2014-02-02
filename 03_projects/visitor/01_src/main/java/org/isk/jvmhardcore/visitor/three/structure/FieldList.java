package org.isk.jvmhardcore.visitor.three.structure;

import java.util.ArrayList;
import java.util.List;

import org.isk.jvmhardcore.visitor.three.pattern.Visitor;
import org.isk.jvmhardcore.visitor.three.pattern.Visitable;

public class FieldList implements Visitable {
  final private List<Field> fields;

  public FieldList() {
    this.fields = new ArrayList<Field>();
  }

  public void add(Field field) {
    this.fields.add(field);
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);

    for (Field field : this.fields) {
      field.accept(visitor);
    }
  }
}
