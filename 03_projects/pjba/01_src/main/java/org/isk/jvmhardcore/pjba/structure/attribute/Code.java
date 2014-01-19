package org.isk.jvmhardcore.pjba.structure.attribute;

import org.isk.jvmhardcore.pjba.structure.Exception;

public class Code extends Attribute {
  public final static String ATTRIBUTE_NAME = "Code";

  private int maxStack;
  private int maxLocals;
  private int codeLength;
  private byte[] code;
  private int exceptionsCount;
  private Exception[] exceptions;
  private int attributesCount;
  private Attribute[] attributes;

  public Code(final int attributeNameIndex) {
    super(attributeNameIndex);
  }
}
