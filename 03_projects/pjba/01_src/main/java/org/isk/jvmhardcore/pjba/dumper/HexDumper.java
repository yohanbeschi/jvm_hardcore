package org.isk.jvmhardcore.pjba.dumper;

import org.isk.jvmhardcore.pjba.instruction.LookupswitchInstruction;
import org.isk.jvmhardcore.pjba.instruction.TableswitchInstruction;
import org.isk.jvmhardcore.pjba.instruction.meta.MetaInstruction;
import org.isk.jvmhardcore.pjba.instruction.meta.MetaInstruction.ArgsType;
import org.isk.jvmhardcore.pjba.instruction.meta.MetaInstructions;
import org.isk.jvmhardcore.pjba.structure.ClassFile;
import org.isk.jvmhardcore.pjba.util.BytecodeUtils;
import org.isk.jvmhardcore.pjba.visitor.Visitor;

public class HexDumper implements Visitor {

  final private ClassFile classFile;
  final private StringBuilder pjb;
  private int hexCounter;
  private int currentMethodLength;
  private int methodCounterMax;
  private int constantCount = 1;

  public HexDumper(ClassFile classFile) {
    super();
    this.classFile = classFile;
    this.pjb = new StringBuilder();
  }

  public String dump() {
    this.classFile.accept(this);

    return this.pjb.toString();
  }

  @Override
  public void visitMagicNumber(int magicNumber) {
    this.pjb.append(this.getHexAndAddInt());
    this.pjb.append("\t0x");
    this.pjb.append(Integer.toHexString(magicNumber)).append("\n");
  }

  @Override
  public void visitVersion(int version) {
    final int minorVersion = version >> 8;
    final int majorVersion = version & 0xffff;

    this.pjb.append(this.getHexAndAddShort()).append("\t");
    this.pjb.append("Minor version: ").append(minorVersion).append("\n");
    this.pjb.append(this.getHexAndAddShort()).append("\t");
    this.pjb.append("Major version: ").append(majorVersion).append("\n");
  }

  @Override
  public void visitConstantPoolSize(int size) {
    this.pjb.append(this.getHexAndAddShort()).append("\t");
    this.pjb.append("Constant Pool: ").append(size).append("\n");
  }

  @Override
  public void visitClassAccessFlags(int accessFlags) {
    this.pjb.append(this.getHexAndAddShort()).append("\t");
    this.pjb.append("Access Flags: ").append(StringValues.getClassModifiers(accessFlags)).append("\n");
  }

  @Override
  public void visitThisClass(int thisClass) {
    this.pjb.append(this.getHexAndAddShort()).append("\t");
    this.pjb.append("This: #").append(thisClass).append("\n");
  }

  @Override
  public void visitSuperClass(int superClass) {
    this.pjb.append(this.getHexAndAddShort()).append("\t");
    this.pjb.append("Super: #").append(superClass).append("\n");
  }

  @Override
  public void visitInterfacesSize(int length) {
    this.pjb.append(this.getHexAndAddShort()).append("\t");
    this.pjb.append("Interfaces: ").append(length).append("\n");
  }

  @Override
  public void visitFieldsSize(int size) {
    this.pjb.append(this.getHexAndAddShort()).append("\t");
    this.pjb.append("Fields: ").append(size).append("\n");
  }

  @Override
  public void visitMethodsSize(int size) {
    this.pjb.append(this.getHexAndAddShort()).append("\t");
    this.pjb.append("Methods: ").append(size).append("\n");
  }

  @Override
  public void visitClassAttributeSize(int size) {
    this.pjb.append(this.getHexAndAddShort()).append("\t");
    this.pjb.append("Class Attributes: ").append(size).append("\n");
  }

  @Override
  public void visitConstantTag(int tag) {
    this.pjb.append(this.getHexAndAddByte()).append("\t");
    final String tagName = StringValues.constantTagName(tag);
    this.pjb.append("  #").append(this.constantCount++).append(" ").append(tagName).append(" ");
  }

  @Override
  public void visitConstantUTF8(String value) {
    this.getHexAndAddString(value);
    this.pjb.append("  ").append(value).append("\n");
  }

  @Override
  public void visitConstantInteger(int integer) {
    this.getHexAndAddInt();
    this.pjb.append("  ").append(integer).append("\n");
  }

  @Override
  public void visitConstantFloat(float floatValue) {
    this.getHexAndAddInt();
    this.pjb.append("  ").append(floatValue).append("\n");
  }

  @Override
  public void visitConstantLong(long longValue) {
    this.getHexAndAddLong();
    this.constantCount++;
    this.pjb.append("  ").append(longValue).append("\n");
  }

  @Override
  public void visitConstantDouble(double doubleValue) {
    this.getHexAndAddLong();
    this.constantCount++;
    this.pjb.append("  ").append(doubleValue).append("\n");
  }

  @Override
  public void visitConstantClass(int nameIndex) {
    this.getHexAndAddShort();
    this.pjb.append("  #").append(nameIndex).append("\n");
  }

  @Override
  public void visitConstantString(int utf8Index) {
    this.getHexAndAddShort();
    this.pjb.append("  #").append(utf8Index).append("\n");
  }

  @Override
  public void visitConstantFieldRef(int classIndex, int nameAndTypeIndex) {
    this.getHexAndAddShort();
    this.pjb.append("  #").append(classIndex).append(" #").append(nameAndTypeIndex).append("\n");
  }

  @Override
  public void visitConstantMethodRef(int classIndex, int nameAndTypeIndex) {
    this.getHexAndAddShort();
    this.pjb.append("  #").append(classIndex).append(" #").append(nameAndTypeIndex).append("\n");
  }

  @Override
  public void visitConstantInterfaceMethodRef(int classIndex, int nameAndTypeIndex) {
    this.getHexAndAddShort();
    this.pjb.append("  #").append(classIndex).append(" #").append(nameAndTypeIndex).append("\n");
  }

  @Override
  public void visitConstantNameAndType(int nameIndex, int descriptorIndex) {
    this.getHexAndAddShort();
    this.pjb.append("  #").append(nameIndex).append(" #").append(descriptorIndex).append("\n");
  }

  @Override
  public void visitInterfaceConstantClassIndex(int constantClassIndex) {
    this.pjb.append(this.getHexAndAddShort()).append("\t");
    this.pjb.append("+ #").append(constantClassIndex).append("\n");
  }

  @Override
  public void visitFieldAccessFlags(int accessFlags) {
    this.pjb.append(this.getHexAndAddShort()).append("\t");
    this.pjb.append("+ Access Flags: ").append(StringValues.getFieldModifiers(accessFlags)).append("\n");
  }

  @Override
  public void visitFieldNameIndex(int nameIndex) {
    this.pjb.append(this.getHexAndAddShort()).append("\t");
    this.pjb.append("  Name index: #").append(nameIndex).append("\n");
  }

  @Override
  public void visitFieldDescriptorIndex(int descriptorIndex) {
    this.pjb.append(this.getHexAndAddShort()).append("\t");
    this.pjb.append("  Descriptor index: #").append(descriptorIndex).append("\n");
  }

  @Override
  public void visitFieldAttributesSize(int size) {
    this.pjb.append(this.getHexAndAddShort()).append("\t");
    this.pjb.append("  Field Attributes: ").append(size).append("\n");
  }

  @Override
  public void visitConstantValueAttributeLength(int length) {
    this.pjb.append(this.getHexAndAddShort()).append("\t");
    this.pjb.append("    Length: ").append(length).append("\n");
  }

  @Override
  public void visitConstantValueIndex(int constantValueIndex) {
    this.pjb.append(this.getHexAndAddShort()).append("\t");
    this.pjb.append("    Value index: #").append(constantValueIndex).append("\n");
  }

  @Override
  public void visitMethodAccessFlags(int accessFlags) {
    this.currentMethodLength = 0;
    this.pjb.append(this.getHexAndAddShort()).append("\t");
    this.pjb.append("+ Access Flags: ").append(StringValues.getMethodModifiers(accessFlags)).append("\n");
  }

  @Override
  public void visitMethodNameIndex(int nameIndex) {
    this.pjb.append(this.getHexAndAddShort()).append("\t");
    this.pjb.append("  Name index: #").append(nameIndex).append("\n");
  }

  @Override
  public void visitMethodDescriptorIndex(int descriptorIndex) {
    this.pjb.append(this.getHexAndAddShort()).append("\t");
    this.pjb.append("  Descriptor index: #").append(descriptorIndex).append("\n");
  }

  @Override
  public void visitMethodAttributesSize(int size) {
    this.pjb.append(this.getHexAndAddShort()).append("\t");
    this.pjb.append("  Method Attributes: ").append(size).append("\n");
  }

  @Override
  public void visitAttributeNameIndex(int nameIndex) {
    this.pjb.append(this.getHexAndAddShort()).append("\t");
    this.pjb.append("  + Attribute name index: #").append(nameIndex).append("\n");
  }

  @Override
  public void visitCodeAttributeLength(int length) {
    this.pjb.append(this.getHexAndAddShort()).append("\t");
    this.pjb.append("    Length: ").append(length).append("\n");
  }

  @Override
  public void visitCodeMaxStack(int maxStack) {
    this.pjb.append(this.getHexAndAddShort()).append("\t");
    this.pjb.append("    Max stack: ").append(maxStack).append("\n");
  }

  @Override
  public void visitCodeMaxLocals(int maxLocals) {
    this.pjb.append(this.getHexAndAddShort()).append("\t");
    this.pjb.append("    Max locals: ").append(maxLocals).append("\n");
  }

  @Override
  public void visitCodeLength(int codeLength) {
    this.methodCounterMax = String.valueOf(codeLength).length();
    this.getHexAndAddInt();
  }

  @Override
  public void visitCodeExceptionsSize(int size) {
    this.pjb.append(this.getHexAndAddShort()).append("\t");
    this.pjb.append("    Exceptions: ").append(size).append("\n");
  }

  @Override
  public void visitCodeAttributesSize(int size) {
    this.pjb.append(this.getHexAndAddShort()).append("\t");
    this.pjb.append("    Code Attributes: ").append(size).append("\n");
  }

  private MetaInstruction metaInstruction;

  @Override
  public void visitOpcode(int opcode) {
    this.metaInstruction = MetaInstructions.getMetaInstruction(opcode);
    this.pjb.append(this.getHexAndAddByte()).append("\t");
    this.pjb.append("      ").append(this.getDecPadded(this.currentMethodLength))
            .append("  ").append(this.metaInstruction.getMnemonic());

    if (this.metaInstruction.getArgsType() == ArgsType.NONE) {
      this.pjb.append("\n");
    }

    this.currentMethodLength += 1;
  }

  @Override
  public void visitInstructionByte(int value) {
    final ArgsType type = this.metaInstruction.getArgsType();
    switch (type) {
      case BYTE_VALUE:
        this.pjb.append(" ").append(value).append("\n");
        break;
      case LV_INDEX:
        this.pjb.append(" ").append(BytecodeUtils.unsign((byte) value)).append("\n");
        break;
      case IFS_CONSTANT:
        this.pjb.append(" #").append(BytecodeUtils.unsign((byte) value)).append("\n");
        break;
      default:
        throw new RuntimeException("Incorrect type: " + type + " for the value: " + value);
    }

    this.currentMethodLength += 1;
    this.getHexAndAdd(1);
  }

  @Override
  public void visitInstructionShort(int value) {
    final ArgsType type = this.metaInstruction.getArgsType();
    switch (type) {
      case SHORT_VALUE:
        this.pjb.append(" ").append(value).append("\n");
        break;
      case W_IFS_CONSTANT:
      case LD_CONSTANT:
      case FIELD:
      case METHOD:
      case CLASS:
        this.pjb.append(" #").append(BytecodeUtils.unsign((short) value)).append("\n");
        break;
      case LABEL:
      case GOTO:
        this.pjb.append(" <").append(value).append(">\n");
        break;
      default:
        throw new RuntimeException("Incorrect type: " + type + " for the value: " + value);
    }

    this.currentMethodLength += 2;
    this.getHexAndAdd(2);
  }

  @Override
  public void visitInstructionInt(int value) {
    this.pjb.append(" <").append(value).append(">\n");

    this.currentMethodLength += 4;
    this.getHexAndAdd(4);
  }

  @Override
  public void visitInstructionIinc(int indexInLV, int constant) {
    this.pjb.append(" ").append(BytecodeUtils.unsign((byte) indexInLV)).append(" ").append(constant).append("\n");

    this.currentMethodLength += 2;
    this.getHexAndAdd(2);
  }

  @Override
  public void visitInstructionWideIinc(int widenedOpcode, int indexInLV, int constant) {
    this.pjb.append(" ").append(MetaInstructions.getMnemonic(widenedOpcode))
            .append(" ").append(BytecodeUtils.unsign((short) indexInLV))
            .append(" ").append(constant).append("\n");

    this.currentMethodLength += 5;
    this.getHexAndAdd(5);
  }

  @Override
  public void visitInstructionWideLoadStore(int widenedOpcode, int indexInLV) {
    this.pjb.append(" ").append(MetaInstructions.getMnemonic(widenedOpcode))
            .append(" ").append(BytecodeUtils.unsign((short) indexInLV)).append("\n");

    this.currentMethodLength += 3;
    this.getHexAndAdd(3);
  }

  @Override
  public void visitInstructionTableSwitch(int padding, int defaultOffset, int lowValue, int highValue, int[] jumpOffsets) {
    this.pjb.append(" ").append(padding).append(" <").append(BytecodeUtils.unsign(defaultOffset)).append("> ")
            .append(lowValue).append(" ").append(highValue).append("\n");

    for (int jumpOffset : jumpOffsets) {
      this.pjb.append("                     <").append(BytecodeUtils.unsign(jumpOffset)).append(">\n");
    }

    final int length = TableswitchInstruction.getLength(padding, jumpOffsets.length) - 1;
    this.currentMethodLength += length;
    this.getHexAndAdd(length);
  }

  @Override
  public void visitInstructionLookupSwitch(int padding, int defaultOffset, int nbPairs, int[] keys, int[] jumpOffsets) {
    this.pjb.append(" ").append(padding).append(" <").append(BytecodeUtils.unsign(defaultOffset)).append("> ")
            .append(nbPairs).append("\n");

    for (int i = 0; i < nbPairs; i++) {
      this.pjb.append("                     ").append(keys[i])
              .append(": <").append(BytecodeUtils.unsign(jumpOffsets[i])).append(">\n");
    }

    final int length = LookupswitchInstruction.getLength(padding, keys.length) - 1;
    this.currentMethodLength += length;
    this.getHexAndAdd(length);
  }

  @Override
  public void visitInvokeinterface(int indexInCP, int paramsCount, int zero) {
    this.pjb.append(" #").append(BytecodeUtils.unsign((short) indexInCP))
            .append(" ").append(paramsCount)
            .append(" ").append(zero)
            .append("\n");
    
    this.currentMethodLength += 4;
    this.getHexAndAdd(4);
  }

  // -------------------------------------------------------------------------------------------------------------------
  // Utilities
  // -------------------------------------------------------------------------------------------------------------------

  private String getHexAndAdd(int nbOfBytes) {
    final String hex = this.getHexPadded(this.hexCounter);
    this.hexCounter += nbOfBytes;
    return hex;
  }

  private String getHexAndAddByte() {
    final String hex = this.getHexPadded(this.hexCounter);
    this.hexCounter += 1;
    return hex;
  }

  private String getHexAndAddShort() {
    final String hex = this.getHexPadded(this.hexCounter);
    this.hexCounter += 2;
    return hex;
  }

  private String getHexAndAddInt() {
    final String hex = this.getHexPadded(this.hexCounter);
    this.hexCounter += 4;
    return hex;
  }

  private String getHexAndAddLong() {
    final String hex = this.getHexPadded(this.hexCounter);
    this.hexCounter += 8;
    return hex;
  }

  private Object getHexAndAddString(final String str) {
    final String hex = this.getHexPadded(this.hexCounter);
    final int size = this.getStringLength(str) + 2; // size in short
    this.hexCounter += size;
    return hex;
  }

  private String getHexPadded(int value) {
    final String hexString = Integer.toHexString(value);
    return this.pad(hexString, 4, "0");
  }

  private String getDecPadded(int value) {
    final String s = String.valueOf(value);
    return this.pad(s, this.methodCounterMax, " ");
  }

  private String pad(String value, int pad, String placeholder) {
    final StringBuilder sb = new StringBuilder();

    for (int i = value.length(); i < pad; i++) {
      sb.append(placeholder);
    }

    sb.append(value);
    return sb.toString();
  }

  private int getStringLength(final String str) {
    final int strlen = str.length();
    int utflen = 0;
    int c = 0;

    for (int i = 0; i < strlen; i++) {
      c = str.charAt(i);
      if ((c >= 0x0001) && (c <= 0x007F)) {
        utflen++;
      } else if (c > 0x07FF) {
        utflen += 3;
      } else {
        utflen += 2;
      }
    }

    return utflen;
  }
}
