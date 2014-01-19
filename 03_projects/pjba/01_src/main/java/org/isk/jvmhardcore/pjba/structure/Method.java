package org.isk.jvmhardcore.pjba.structure;

import org.isk.jvmhardcore.pjba.structure.attribute.Attribute;

public class Method {
  private int accessFlags = 0x0001 | 0x0008; // public static
  private int nameIndex;
  private int descriptorIndex;
  private int attributesCount;
  private Attribute[] attributes;
}
