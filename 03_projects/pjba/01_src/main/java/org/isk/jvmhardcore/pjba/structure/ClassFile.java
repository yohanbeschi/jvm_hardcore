package org.isk.jvmhardcore.pjba.structure;

import org.isk.jvmhardcore.pjba.structure.attribute.Attribute;

public class ClassFile {
  final private int magic = 0xcafebabe;
  final private int version = 0x30; // 48.0 = 0x00 (version mineure) | 0x30 (version majeur)
  private int constantPoolCount;
  private Constant.ConstantPoolEntry[] constantPool;
  final private int accessFlags = 0x0001 | 0x0020; // public super;
  private int thisClass;
  private int superClass;
  private int interfacesCount;
  private int[] interfaces;
  private int fieldsCount;
  private Field[] fields;
  private int methodsCount;
  private Method[] methods;
  private int attributesCount;
  private Attribute[] attributes;
}
