package org.isk.jvmhardcore.pjba.structure;

import org.isk.jvmhardcore.pjba.structure.attribute.constraint.FieldAttribute;
import org.isk.jvmhardcore.pjba.visitor.Visitor;

public class Field extends FieldAndMethod<FieldAttribute> {

  final public static int MODIFIER_PUBLIC = 0x0001;
  final public static int MODIFIER_PRIVATE = 0x0002;
  final public static int MODIFIER_PROTECTED = 0x0004;
  final public static int MODIFIER_STATIC = 0x0008;
  final public static int MODIFIER_FINAL = 0x0010;
  final public static int MODIFIER_VOLATILE = 0x0040;
  final public static int MODIFIER_TRANSIENT = 0x080;

  @Override
  public void accept(Visitor visitor) {
    visitor.visitFieldAccessFlags(this.accessFlags);
    visitor.visitFieldNameIndex(this.nameIndex);
    visitor.visitFieldDescriptorIndex(this.descriptorIndex);
    visitor.visitFieldAttributesSize(this.attributes.size());
    this.attributes.accept(visitor);
  }
}
