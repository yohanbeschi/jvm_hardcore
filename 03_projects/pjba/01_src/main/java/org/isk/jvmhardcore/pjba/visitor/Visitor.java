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
  void visitConstantFieldRef(int classIndex, int nameAndTypeIndex);
  void visitConstantMethodRef(int classIndex, int nameAndTypeIndex);
  void visitConstantInterfaceMethodRef(int classIndex, int nameAndTypeIndex);
  void visitConstantNameAndType(int nameIndex, int descriptorIndex);

  // -------------------------------------------------------------------------------------------------------------------
  // Interface
  // -------------------------------------------------------------------------------------------------------------------
  void visitInterfaceConstantClassIndex(int constantClassIndex);

  // -------------------------------------------------------------------------------------------------------------------
  // Field
  // -------------------------------------------------------------------------------------------------------------------
  void visitFieldAccessFlags(int accessFlags);
  void visitFieldNameIndex(int nameIndex);
  void visitFieldDescriptorIndex(int descriptorIndex);
  void visitFieldAttributesSize(int size);

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
  void visitCodeAttributeLength(int i);

  // -------------------------------------------------------------------------------------------------------------------
  // ConstantValue
  // -------------------------------------------------------------------------------------------------------------------
  void visitConstantValueAttributeLength(int i);
  void visitConstantValueIndex(int constantValueIndex);

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
  void visitInstructionInt(int arg);
  void visitInstructionIinc(int indexInLV, int constant);
  void visitInstructionWideIinc(int widenedOpcode, int indexInLV, int constant);
  void visitInstructionWideLoadStore(int widenedOpcode, int indexInLV);
  void visitInstructionTableSwitch(int padding, int defaultOffset, int lowValue, int highValue, int[] jumpOffsets);
  void visitInstructionLookupSwitch(int padding, int defaultOffset, int nbPairs, int[] keys, int[] jumpOffsets);
  void visitInvokeinterface(int indexInCP, int paramsCount, int zero);
  void visitMultinewarrayInstruction(int indexInCP, int dimensions);
}
