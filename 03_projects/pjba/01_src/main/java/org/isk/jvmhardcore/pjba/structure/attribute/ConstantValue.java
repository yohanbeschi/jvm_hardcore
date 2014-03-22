package org.isk.jvmhardcore.pjba.structure.attribute;

import org.isk.jvmhardcore.pjba.structure.attribute.constraint.FieldAttribute;
import org.isk.jvmhardcore.pjba.visitor.Visitor;

public class ConstantValue extends Attribute implements FieldAttribute {
  public final static String ATTRIBUTE_NAME = "ConstantValue";
  
  private int constantValueIndex;

  public ConstantValue(final int attributeNameIndex) {
    super(attributeNameIndex);
  }

  public void setConstantValueIndex(int constantValueIndex) {
    this.constantValueIndex = constantValueIndex;
  }

  @Override
  public void accept(Visitor visitor) {
    super.accept(visitor);
    visitor.visitConstantValueAttributeLength(2);
    visitor.visitConstantValueIndex(this.constantValueIndex);
  }

}
