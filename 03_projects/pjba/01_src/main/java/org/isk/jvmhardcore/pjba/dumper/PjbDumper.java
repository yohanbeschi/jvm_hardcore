package org.isk.jvmhardcore.pjba.dumper;

import java.util.LinkedList;
import java.util.List;

import org.isk.jvmhardcore.pjba.instruction.LookupswitchInstruction;
import org.isk.jvmhardcore.pjba.instruction.TableswitchInstruction;
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
  private int currentMethodLength;
  private StringBuilder currentInstruction;
  private List<InstructionWrapper> instructions;
  private int labelCount;
  private List<LabelWrapper> labels;

  public PjbDumper(ClassFile classFile) {
    super();
    this.classFile = classFile;
    this.pjb = new StringBuilder();
  }

  public String dump() {
    this.classFile.accept(this);

    if (this.methodCount > 0) {
      this.finalizeMethod();
      this.pjb.append("  .methodend\n");
    }

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
  public void visitSuperClass(int superClassIndex) {
    final String fullyQualifiedSuperClassName = StringValues.getPrintableConstant(superClassIndex, this.classFile);
    this.pjb.append("  .super ").append(fullyQualifiedSuperClassName).append("\n\n");
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
  public void visitConstantFieldRef(int classIndex, int nameAndTypeIndex) {
    // Do nothing
  }

  @Override
  public void visitConstantMethodRef(int classIndex, int nameAndTypeIndex) {
    // Do nothing
  }

  @Override
  public void visitConstantInterfaceMethodRef(int classIndex, int nameAndTypeIndex) {
    // Do nothing
  }

  @Override
  public void visitConstantNameAndType(int nameIndex, int descriptorIndex) {
    // Do nothing
  }

  @Override
  public void visitInterfaceConstantClassIndex(int constantClassIndex) {
    final String fullyQualifiedInterfaceName = StringValues.getPrintableConstant(constantClassIndex, this.classFile);
    this.pjb.append("  .interface ").append(fullyQualifiedInterfaceName);
  }

  @Override
  public void visitFieldAccessFlags(int accessFlags) {
    this.pjb.append("  .field ").append(StringValues.getFieldModifiers(accessFlags));
  }

  @Override
  public void visitFieldNameIndex(int nameIndex) {
    final Constant.UTF8 constantUtf8 = (Constant.UTF8) this.classFile.getConstant(nameIndex);
    this.pjb.append(constantUtf8.value).append(" ");
  }

  @Override
  public void visitFieldDescriptorIndex(int descriptorIndex) {
    final Constant.UTF8 constantUtf8 = (Constant.UTF8) this.classFile.getConstant(descriptorIndex);
    this.pjb.append(constantUtf8.value).append("\n");
  }

  @Override
  public void visitFieldAttributesSize(int size) {
    // Do nothing
  }

  @Override
  public void visitConstantValueAttributeLength(int length) {
    // Do nothing
  }

  @Override
  public void visitConstantValueIndex(int constantValueIndex) {
    this.pjb.setLength(this.pjb.length() - 1);
    
    final String value = StringValues.getPrintableConstant(BytecodeUtils.unsign((short) constantValueIndex), this.classFile);
    this.pjb.append(" = ").append(value).append("\n");
  }

  @Override
  public void visitMethodAccessFlags(int accessFlags) {
    if (this.methodCount > 0) {
      this.finalizeMethod();
      this.pjb.append("  .methodend\n\n");
    }

    this.currentMethodLength = 0;
    this.labelCount = 1;
    this.labels = new LinkedList<>();
    this.instructions = new LinkedList<>();
    this.pjb.append("  .method ").append(StringValues.getMethodModifiers(accessFlags));

    this.methodCount++;
  }

  private void finalizeMethod() {
    for (LabelWrapper label : this.labels) {
      final int index = this.getInstructionIndex(label.expectedPosition);
      this.instructions.add(index, new InstructionWrapper("    " + label.label + ":\n", 0));
    }

    for (InstructionWrapper instruction : this.instructions) {
      this.pjb.append(instruction.instruction);
    }
  }

  private int getInstructionIndex(int expectedPosition) {
    for (int i = 0; i < this.instructions.size(); i++) {
      final InstructionWrapper instruction = this.instructions.get(i);
      if (instruction.position == expectedPosition) {
        return i;
      }
    }

    throw new RuntimeException("Instruction not found starting at: " + expectedPosition);
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
  public void visitCodeAttributeLength(int length) {
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
    if (this.currentInstruction == null) {
      this.currentInstruction = new StringBuilder();
    }

    // Opcode/Mnemonic
    this.metaInstruction = MetaInstructions.getMetaInstruction(opcode);

    if (this.metaInstruction.getArgsType() != ArgsType.WIDE) {
      this.currentInstruction.append("    ").append(this.metaInstruction.getPjbMnemonic());

      if (this.metaInstruction.getArgsType() == ArgsType.NONE) {
        this.currentInstruction.append("\n");
        this.writeInstruction();
        this.currentMethodLength += 1;
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
      case ARRAY_TYPE:
        printableValue = StringValues.getArrayType(value);
        break;
      default:
        throw new RuntimeException("Incorrect type: " + type + " for the value: " + value);
    }

    this.currentInstruction.append(" ").append(printableValue).append("\n");
    this.writeInstruction();
    this.currentMethodLength += 2;
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
      case FIELD:
      case METHOD:
      case CLASS:
        printableValue = StringValues.getPrintableConstant(BytecodeUtils.unsign((short) value), this.classFile);
        break;
      case LABEL:
      case GOTO:
        printableValue = this.getLabel(value);
        break;
      default:
        throw new RuntimeException("Incorrect type: " + type + " for the value: " + value);
    }

    this.currentInstruction.append(" ").append(printableValue).append("\n");
    this.writeInstruction();
    this.currentMethodLength += 3;
  }

  @Override
  public void visitInstructionInt(int value) {
    final String printableValue = this.getLabel(value);
    this.currentInstruction.append(" ").append(printableValue).append("\n");
    this.writeInstruction();
    this.currentMethodLength += 5;
  }

  @Override
  public void visitInstructionIinc(int indexInLV, int constant) {
    this.currentInstruction.append(" ").append(indexInLV).append(" ").append(constant).append("\n");
    this.writeInstruction();
    this.currentMethodLength += 3;
  }

  @Override
  public void visitInstructionWideIinc(int widenedOpcode, int indexInLV, int constant) {
    this.currentInstruction.append("    ").append(MetaInstructions.getMetaInstruction(widenedOpcode).getPjbMnemonic())
        .append(" ").append(BytecodeUtils.unsign((short) indexInLV)).append(" ").append(constant).append("\n");

    this.writeInstruction();
    this.currentMethodLength += 6;
  }

  @Override
  public void visitInstructionWideLoadStore(int widenedOpcode, int indexInLV) {
    this.currentInstruction.append("    ").append(MetaInstructions.getMetaInstruction(widenedOpcode).getPjbMnemonic())
        .append(" ").append(BytecodeUtils.unsign((short) indexInLV)).append("\n");

    this.writeInstruction();
    this.currentMethodLength += 4;
  }

  @Override
  public void visitInstructionTableSwitch(int padding, int defaultOffset, int lowValue, int highValue, int[] jumpOffsets) {
    final String defaultLabel = this.getLabel(defaultOffset);

    this.currentInstruction.append(" ").append(defaultLabel).append(" ").append(lowValue).append(" ").append(highValue)
        .append("\n");

    for (int jumpOffset : jumpOffsets) {
      final String label = this.getLabel(jumpOffset);
      this.currentInstruction.append("      ").append(label).append("\n");
    }

    this.writeInstruction();
    this.currentMethodLength += TableswitchInstruction.getLength(padding, jumpOffsets.length);
  }

  @Override
  public void visitInstructionLookupSwitch(int padding, int defaultOffset, int nbPairs, int[] keys, int[] jumpOffsets) {
    final String defaultLabel = this.getLabel(defaultOffset);

    this.currentInstruction.append(" ").append(defaultLabel).append(" ").append(nbPairs).append("\n");

    for (int i = 0; i < nbPairs; i++) {
      final String label = this.getLabel(jumpOffsets[i]);
      this.currentInstruction.append("      ").append(keys[i]).append(" ").append(label).append("\n");
    }

    this.writeInstruction();
    this.currentMethodLength += LookupswitchInstruction.getLength(padding, keys.length);
  }

  @Override
  public void visitInvokeinterface(int indexInCP, int paramsCount, int zero) {
    final String printableValue = StringValues.getPrintableConstant(BytecodeUtils.unsign((short) indexInCP), this.classFile);
    
    this.currentInstruction.append(" ").append(printableValue).append("\n");
    this.writeInstruction();
    this.currentMethodLength += 4;
  }

  @Override
  public void visitMultinewarrayInstruction(int indexInCP, int dimensions) {
    final String printableValue = StringValues.getPrintableConstant(BytecodeUtils.unsign((short) indexInCP), this.classFile);

    this.currentInstruction.append(" ").append(printableValue).append(" ").append(dimensions).append("\n");
    this.writeInstruction();
    this.currentMethodLength += 4;
  }

  private void writeInstruction() {
    this.instructions.add(new InstructionWrapper(this.currentInstruction.toString(), this.currentMethodLength));
    this.currentInstruction = new StringBuilder();
  }

  private String getLabel(int offset) {
    String printableValue;

    final int expectedPosition = this.currentMethodLength + offset;
    final String label = this.getLabelAtExpectedPosition(expectedPosition);

    if (label != null) {
      printableValue = label;
    } else {
      printableValue = "label" + this.labelCount++;
      this.labels.add(new LabelWrapper(printableValue.toString(), expectedPosition));
    }

    return printableValue;
  }

  private String getLabelAtExpectedPosition(int expectedPosition) {
    for (LabelWrapper lw : this.labels) {
      if (lw.expectedPosition == expectedPosition) {
        return lw.label;
      }
    }
    return null;
  }

  private class LabelWrapper {
    final String label;
    final int expectedPosition;

    public LabelWrapper(String label, int expectedPosition) {
      super();
      this.label = label;
      this.expectedPosition = expectedPosition;
    }
  }

  private class InstructionWrapper {
    final String instruction;
    final int position;

    public InstructionWrapper(String instruction, int position) {
      super();
      this.instruction = instruction;
      this.position = position;
    }
  }
}
