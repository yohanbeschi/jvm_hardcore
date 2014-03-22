package org.isk.jvmhardcore.pjba.structure;

import org.isk.jvmhardcore.pjba.structure.attribute.constraint.MethodAttribute;
import org.isk.jvmhardcore.pjba.visitor.Visitor;

public class Method extends FieldAndMethod<MethodAttribute> {
  final public static int MODIFIER_PUBLIC = 0x0001;
  final public static int MODIFIER_PRIVATE = 0x0002;
  final public static int MODIFIER_PROTECTED = 0x0004;
  final public static int MODIFIER_STATIC = 0x0008;
  final public static int MODIFIER_FINAL = 0x0010;
  final public static int MODIFIER_SYNCHRONIZED = 0x0020;
  final public static int MODIFIER_NATIVE = 0x0100;
  final public static int MODIFIER_ABSTRACT = 0x0400;
  final public static int MODIFIER_STRICTFP = 0x0800;

  @Override
  public void accept(Visitor visitor) {
    visitor.visitMethodAccessFlags(this.accessFlags);
    visitor.visitMethodNameIndex(this.nameIndex);
    visitor.visitMethodDescriptorIndex(this.descriptorIndex);
    visitor.visitMethodAttributesSize(this.attributes.size());
    this.attributes.accept(visitor);
  }
}
