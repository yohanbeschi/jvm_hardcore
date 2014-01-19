package org.isk.jvmhardcore.pjba.structure.attribute;

public abstract class Attribute {
  final private int nameIndex;
  private int attributeLength;

  public Attribute(final int nameIndex) {
    this.nameIndex = nameIndex;
  }
}
