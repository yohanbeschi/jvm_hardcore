package org.isk.jvmhardcore.pjba;

import java.io.DataOutput;
import java.io.IOException;

import org.isk.jvmhardcore.pjba.structure.ClassFile;
import org.isk.jvmhardcore.pjba.visitor.Visitor;

public class Assembler implements Visitor {

  final private ClassFile classFile;
  final private DataOutput dataOutput;

  public Assembler(final ClassFile classFile, final DataOutput dataOutput) {
    this.classFile = classFile;
    this.dataOutput = dataOutput;
  }

  public void assemble() {
    this.classFile.accept(this);
  }

  @Override
  public void visitMagicNumber(int magicNumber) {
    this.writeInt(magicNumber);
  }

  @Override
  public void visitVersion(int version) {
    this.writeInt(version);
  }

  @Override
  public void visitConstantPoolSize(int size) {
    this.writeShort(size);
  }

  @Override
  public void visitClassAccessFlags(int accessFlags) {
    this.writeShort(accessFlags);
  }

  @Override
  public void visitThisClass(int thisClass) {
    this.writeShort(thisClass);
  }

  @Override
  public void visitSuperClass(int superClass) {
    this.writeShort(superClass);
  }

  @Override
  public void visitInterfacesSize(int size) {
    this.writeShort(size);
  }

  @Override
  public void visitFieldsSize(int size) {
    this.writeShort(size);
  }

  @Override
  public void visitMethodsSize(int size) {
    this.writeShort(size);
  }

  @Override
  public void visitClassAttributeSize(int size) {
    this.writeShort(size);
  }

  @Override
  public void visitConstantTag(int tag) {
    this.writeByte(tag);
  }

  @Override
  public void visitConstantUTF8(String value) {
    this.writeUTF(value); // length included
  }

  @Override
  public void visitConstantInteger(int value) {
    this.writeInt(value);
  }

  @Override
  public void visitConstantFloat(float value) {
    this.writeFloat(value);
  }

  @Override
  public void visitConstantLong(long value) {
    this.writeLong(value);
  }

  @Override
  public void visitConstantDouble(double value) {
    this.writeDouble(value);
  }

  @Override
  public void visitConstantClass(int utf8Index) {
    this.writeShort(utf8Index);
  }

  @Override
  public void visitConstantString(int utf8Index) {
    this.writeShort(utf8Index);
  }

  @Override
  public void visitMethodAccessFlags(int accessFlags) {
    this.writeShort(accessFlags);
  }

  @Override
  public void visitMethodNameIndex(int nameIndex) {
    this.writeShort(nameIndex);
  }

  @Override
  public void visitMethodDescriptorIndex(int descriptorIndex) {
    this.writeShort(descriptorIndex);
  }

  @Override
  public void visitMethodAttributesSize(int size) {
    this.writeShort(size);
  }

  @Override
  public void visitAttributeNameIndex(int nameIndex) {
    this.writeShort(nameIndex);
  }

  @Override
  public void visitAttributeLength(int length) {
    this.writeInt(length);
  }

  @Override
  public void visitCodeMaxStack(int maxStack) {
    this.writeShort(maxStack);
  }

  @Override
  public void visitCodeMaxLocals(int maxLocals) {
    this.writeShort(maxLocals);
  }

  @Override
  public void visitCodeLength(int codeLength) {
    this.writeInt(codeLength);
  }

  @Override
  public void visitCodeExceptionsSize(int size) {
    this.writeShort(size);
  }

  @Override
  public void visitCodeAttributesSize(int size) {
    this.writeShort(size);
  }

  @Override
  public void visitOpcode(int opcode) {
    this.writeByte(opcode);
  }

  @Override
  public void visitInstructionByte(int arg) {
    this.writeByte(arg);
  }

  @Override
  public void visitInstructionShort(int arg) {
    this.writeShort(arg);
  }

  @Override
  public void visitInstructionIinc(int indexInLV, int constant) {
    this.writeByte(indexInLV);
    this.writeByte(constant);
  }

  @Override
  public void visitInstructionWideIinc(int widenedOpcode, int indexInLV, int constant) {
    this.writeByte(widenedOpcode);
    this.writeShort(indexInLV);
    this.writeShort(constant);
  }

  @Override
  public void visitInstructionWideLoadStore(int widenedOpcode, int indexInLV) {
    this.writeByte(widenedOpcode);
    this.writeShort(indexInLV);
  }

  // -------------------------------------------------------------------------------------------------------------------
  // Write to the DataOutputStream
  // -------------------------------------------------------------------------------------------------------------------

  private void writeByte(int b) {
    try {
      this.dataOutput.writeByte(b);
    } catch (IOException e) {
      throw new RuntimeException("Unable to write class", e);
    }
  }

  private void writeShort(int s) {
    try {
      this.dataOutput.writeShort(s);
    } catch (IOException e) {
      throw new RuntimeException("Unable to write class", e);
    }
  }

  private void writeInt(int i) {
    try {
      this.dataOutput.writeInt(i);
    } catch (IOException e) {
      throw new RuntimeException("Unable to write class", e);
    }
  }

  private void writeFloat(float f) {
    try {
      this.dataOutput.writeFloat(f);
    } catch (IOException e) {
      throw new RuntimeException("Unable to write class", e);
    }
  }

  private void writeLong(long l) {
    try {
      this.dataOutput.writeLong(l);
    } catch (IOException e) {
      throw new RuntimeException("Unable to write class", e);
    }
  }

  private void writeDouble(double d) {
    try {
      this.dataOutput.writeDouble(d);
    } catch (IOException e) {
      throw new RuntimeException("Unable to write class", e);
    }
  }

  private void writeUTF(String s) {
    try {
      this.dataOutput.writeUTF(s);
    } catch (IOException e) {
      throw new RuntimeException("Unable to write class", e);
    }
  }
}
