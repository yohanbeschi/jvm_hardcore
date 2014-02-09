package org.isk.jvmhardcore.pjba.visitor;

public interface Visitor {
  // -------------------------------------------------------------------------------------------------------------------
  // ClassFile
  // -------------------------------------------------------------------------------------------------------------------
  void visitMagicNumber(int magicNumber);
  void visitVersion(int version);
  void visitConstantPoolSize(int size);
  void visitClassAccessFlags(int accessFlags);
  void visitThisClass(int thisClass);
  void visitSuperClass(int superClass);
  void visitInterfacesSize(int size);
  void visitFieldsSize(int size);
  void visitMethodsSize(int size);
  void visitClassAttributeSize(int size);

  // -------------------------------------------------------------------------------------------------------------------
  // Constant
  // -------------------------------------------------------------------------------------------------------------------
  void visitConstantTag(int tag);
  void visitConstantUTF8(java.lang.String value);
  void visitConstantInteger(int value);
  void visitConstantFloat(float value);
  void visitConstantLong(long value);
  void visitConstantDouble(double value);
  void visitConstantClass(int utf8Index);
  void visitConstantString(int utf8Index);

  // -------------------------------------------------------------------------------------------------------------------
  // Method
  // -------------------------------------------------------------------------------------------------------------------
  void visitMethodAccessFlags(int accessFlags);
  void visitMethodNameIndex(int nameIndex);
  void visitMethodDescriptorIndex(int descriptorIndex);
  void visitMethodAttributesSize(int size);

  // -------------------------------------------------------------------------------------------------------------------
  // Attribute
  // -------------------------------------------------------------------------------------------------------------------
  void visitAttributeNameIndex(int nameIndex);
  void visitAttributeLength(int length);

  // -------------------------------------------------------------------------------------------------------------------
  // Code
  // -------------------------------------------------------------------------------------------------------------------
  void visitCodeMaxStack(int maxStack);
  void visitCodeMaxLocals(int maxLocals);
  void visitCodeLength(int codeLength);
  void visitCodeExceptionsSize(int size);
  void visitCodeAttributesSize(int size);

  //--------------------------------------------------------------------------------------------------------------------
  // Instruction
  // -------------------------------------------------------------------------------------------------------------------
  void visitOpcode(int opcode);
  void visitInstructionByte(int value);
  void visitInstructionShort(int value);
}
