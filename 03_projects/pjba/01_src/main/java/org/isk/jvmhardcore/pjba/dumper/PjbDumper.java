package org.isk.jvmhardcore.pjba.dumper;

import org.isk.jvmhardcore.pjba.instruction.meta.MetaInstruction;
import org.isk.jvmhardcore.pjba.instruction.meta.MetaInstruction.ArgsType;
import org.isk.jvmhardcore.pjba.instruction.meta.MetaInstructions;
import org.isk.jvmhardcore.pjba.structure.ClassFile;
import org.isk.jvmhardcore.pjba.structure.Constant;
import org.isk.jvmhardcore.pjba.util.BytecodeUtils;
import org.isk.jvmhardcore.pjba.visitor.Visitor;

public class PjbDumper implements Visitor {

  final private ClassFile classFile;
  final private StringBuilder pjb;
  private int methodCount;

  public PjbDumper(ClassFile classFile) {
    super();
    this.classFile = classFile;
    this.pjb = new StringBuilder();
  }

  public String dump() {
    this.classFile.accept(this);

    this.pjb.append("  .methodend\n");
    this.pjb.append(".classend");
    return this.pjb.toString();
  }

  @Override
  public void visitMagicNumber(int magicNumber) {
    pjb.append(".class ");
  }

  @Override
  public void visitVersion(int version) {
    // Do nothing
  }

  @Override
  public void visitConstantPoolSize(int size) {
    // Do nothing
  }

  @Override
  public void visitClassAccessFlags(int accessFlags) {
    this.pjb.append(StringValues.getClassModifiers(accessFlags));
  }

  @Override
  public void visitThisClass(int thisClass) {
    final Constant.Class constantClass = (Constant.Class) this.classFile.getConstant(thisClass);
    final Constant.UTF8 constantUtf8 = (Constant.UTF8) this.classFile.getConstant(constantClass.nameIndex);
    this.pjb.append(constantUtf8.value).append("\n");
  }

  @Override
  public void visitSuperClass(int superClass) {
    // Do nothing
  }

  @Override
  public void visitInterfacesSize(int length) {
    // Do nothing
  }

  @Override
  public void visitFieldsSize(int size) {
    // Do nothing
  }

  @Override
  public void visitMethodsSize(int size) {
    // Do nothing
  }

  @Override
  public void visitClassAttributeSize(int size) {
    // Do nothing
  }

  @Override
  public void visitConstantTag(int tag) {
    // Do nothing
  }

  @Override
  public void visitConstantUTF8(String value) {
    // Do nothing
  }

  @Override
  public void visitConstantInteger(int integer) {
    // Do nothing
  }

  @Override
  public void visitConstantFloat(float floatValue) {
    // Do nothing
  }

  @Override
  public void visitConstantLong(long longValue) {
    // Do nothing
  }

  @Override
  public void visitConstantDouble(double doubleValue) {
    // Do nothing
  }

  @Override
  public void visitConstantClass(int utf8Index) {
    // Do nothing
  }

  @Override
  public void visitConstantString(int utf8Index) {
    // Do nothing
  }

  @Override
  public void visitMethodAccessFlags(int accessFlags) {
    if (this.methodCount > 0) {
      this.pjb.append("  .methodend\n\n");
    }

    this.pjb.append("  .method ").append(StringValues.getMethodModifiers(accessFlags));

    this.methodCount++;
  }

  @Override
  public void visitMethodNameIndex(int nameIndex) {
    final Constant.UTF8 constantUtf8 = (Constant.UTF8) this.classFile.getConstant(nameIndex);
    this.pjb.append(constantUtf8.value);
  }

  @Override
  public void visitMethodDescriptorIndex(int descriptorIndex) {
    final Constant.UTF8 constantUtf8 = (Constant.UTF8) this.classFile.getConstant(descriptorIndex);
    this.pjb.append(constantUtf8.value).append("\n");
  }

  @Override
  public void visitMethodAttributesSize(int size) {
    // Do nothing
  }

  @Override
  public void visitAttributeNameIndex(int nameIndex) {
    // Do nothing
  }

  @Override
  public void visitAttributeLength(int length) {
    // Do nothing
  }

  @Override
  public void visitCodeMaxStack(int maxStack) {
    // Do nothing
  }

  @Override
  public void visitCodeMaxLocals(int maxLocals) {
    // Do nothing
  }

  @Override
  public void visitCodeLength(int codeLength) {
    // Do nothing
  }

  @Override
  public void visitCodeExceptionsSize(int size) {
    // Do nothing
  }

  @Override
  public void visitCodeAttributesSize(int size) {
    // Do nothing
  }

  private MetaInstruction metaInstruction;

  @Override
  public void visitOpcode(int opcode) {
    // Opcode/Mnemonic
    this.metaInstruction = MetaInstructions.getMetaInstruction(opcode);

    if (this.metaInstruction.getArgsType() != ArgsType.WIDE) {
      this.pjb.append("    ").append(this.metaInstruction.getPjbMnemonic());

      if (this.metaInstruction.getArgsType() == ArgsType.NONE) {
        this.pjb.append("\n");
      }
    }
  }

  @Override
  public void visitInstructionByte(int value) {
    Object printableValue = null;

    final ArgsType type = this.metaInstruction.getArgsType();
    switch (type) {
      case BYTE_VALUE:
        printableValue = value;
        break;
      case LV_INDEX:
        printableValue = BytecodeUtils.unsign((byte) value);
        break;
      case IFS_CONSTANT:
        printableValue = StringValues.getPrintableConstant(BytecodeUtils.unsign((byte) value), this.classFile);
        break;
      default:
        throw new RuntimeException("Incorrect type: " + type + " for the value: " + value);
    }

    this.pjb.append(" ").append(printableValue).append("\n");
  }

  @Override
  public void visitInstructionShort(int value) {
    Object printableValue = null;

    final ArgsType type = this.metaInstruction.getArgsType();
    switch (type) {
      case SHORT_VALUE:
        printableValue = value;
        break;
      case W_IFS_CONSTANT:
      case LD_CONSTANT:
        printableValue = StringValues.getPrintableConstant(BytecodeUtils.unsign((short) value), this.classFile);
        break;
      default:
        throw new RuntimeException("Incorrect type: " + type + " for the value: " + value);
    }

    this.pjb.append(" ").append(printableValue).append("\n");
  }

  @Override
  public void visitInstructionIinc(int indexInLV, int constant) {
    this.pjb.append(" ").append(indexInLV).append(" ").append(constant).append("\n");
  }

  @Override
  public void visitInstructionWideIinc(int widenedOpcode, int indexInLV, int constant) {
    this.pjb.append("    ").append(MetaInstructions.getMetaInstruction(widenedOpcode).getPjbMnemonic())
            .append(" ").append(BytecodeUtils.unsign((short) indexInLV))
            .append(" ").append(constant).append("\n");
  }

  @Override
  public void visitInstructionWideLoadStore(int widenedOpcode, int indexInLV) {;
    this.pjb.append("    ").append(MetaInstructions.getMetaInstruction(widenedOpcode).getPjbMnemonic())
            .append(" ").append(BytecodeUtils.unsign((short) indexInLV)).append("\n");
  }
}
