package org.isk.jvmhardcore.pjba.structure;

import org.isk.jvmhardcore.pjba.util.PjbaLinkedList;
import org.isk.jvmhardcore.pjba.visitor.Visitable;

public abstract class FieldAndMethod<A extends Visitable> implements Visitable {
  protected int accessFlags;
  protected int nameIndex;
  protected int descriptorIndex;

  protected PjbaLinkedList<A> attributes;

  public FieldAndMethod() {
    super();

    this.attributes = new PjbaLinkedList<A>();
  }

  public void setAccessFlags(int accessFlags) {
    this.accessFlags = accessFlags;
  }

  public void addAccessFlags(int accessFlags) {
    this.accessFlags |= accessFlags;
  }

  public int getAccessFlags() {
    return this.accessFlags;
  }

  public boolean accessFlagSet(int accessFlags) {
    return (this.accessFlags & accessFlags) == accessFlags;
  }

  public void setNameIndex(int utf8NameIndex) {
    this.nameIndex = utf8NameIndex;
  }

  public void setDescriptorIndex(int utf8DescriptorIndex) {
    this.descriptorIndex = utf8DescriptorIndex;
  }

  public void setAttributes(PjbaLinkedList<A> attributes) {
    this.attributes = attributes;
  }

  public void addAttibute(final A attribute) {
    this.attributes.add(attribute);
  }
}
