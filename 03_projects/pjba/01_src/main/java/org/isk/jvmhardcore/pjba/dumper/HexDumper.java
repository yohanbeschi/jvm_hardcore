package org.isk.jvmhardcore.pjba.dumper;

import org.isk.jvmhardcore.pjba.instruction.Instructions;
import org.isk.jvmhardcore.pjba.structure.ClassFile;
import org.isk.jvmhardcore.pjba.visitor.Visitor;

public class HexDumper implements Visitor {

  final private ClassFile classFile;
  final private StringBuilder pjb;
  private int hexCounter;
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
  public void visitMethodAccessFlags(int accessFlags) {
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
  public void visitAttributeLength(int length) {
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

  @Override
  public void visitOpcode(int opcode) {
    this.pjb.append(this.getHexAndAddByte()).append("\t");
    this.pjb.append("      ").append(Instructions.getMnemonic(opcode)).append("\n");
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
    final StringBuilder sb = new StringBuilder();

    for (int i = hexString.length(); i < 8; i++) {
      sb.append("0");
    }

    sb.append(hexString);
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
