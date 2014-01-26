package org.isk.jvmhardcore.pjba.structure;

import java.io.DataOutput;
import java.io.IOException;

import org.isk.jvmhardcore.pjba.structure.Constant.ConstantPoolEntry;
import org.isk.jvmhardcore.pjba.structure.attribute.constraint.ClassAttribute;
import org.isk.jvmhardcore.pjba.util.Ascii;
import org.isk.jvmhardcore.pjba.util.BytecodeEnabled;
import org.isk.jvmhardcore.pjba.util.PjbaLinkedList;

public class ClassFile implements BytecodeEnabled {
  final private int magicNumber = 0xcafebabe;
  final private int version = 0x30; // 48.0 = 0x00 (version mineure) | 0x30 (version majeur)

  final private PjbaLinkedList<ConstantPoolEntry> constantPool;

  final private int accessFlags = 0x0001 | 0x0020; // public super

  final private int thisClass;
  final private int superClass;

  final private int[] interfaces;

  final private PjbaLinkedList<Field> fields;
  final private PjbaLinkedList<Method> methods;
  final private PjbaLinkedList<ClassAttribute> attributes;

  private String className;
  private String directories;

  public ClassFile(final String fullyQualifiedName) {
    this.parseName(fullyQualifiedName);

    this.constantPool = new PjbaLinkedList<>();
    this.constantPool.add(null); // Index 0 reserved by the JVM

    this.interfaces = new int[0];
    this.fields = new PjbaLinkedList<>();
    this.methods = new PjbaLinkedList<>();
    this.attributes = new PjbaLinkedList<>();

    // This
    final int classNameIndex = this.addConstantUTF8(fullyQualifiedName);
    this.thisClass = this.addConstantClass(classNameIndex);

    // Parent - Hard coded for now
    final String superName = "java/lang/Object";
    final int superNameIndex = this.addConstantUTF8(superName);
    this.superClass = this.addConstantClass(superNameIndex);
  }

  // --------------------------------------------------------------------------------------------------------------------
  // Support methods
  // --------------------------------------------------------------------------------------------------------------------

  private void parseName(final String fullyQualifiedName) {
    int index = fullyQualifiedName.lastIndexOf(Ascii.SLASH);

    if (index >= 0) {
      this.directories = fullyQualifiedName.substring(0, ++index);
      this.className = fullyQualifiedName.substring(index);
    } else {
      this.className = fullyQualifiedName;
    }
  }

  // --------------------------------------------------------------------------------------------------------------------
  // External getters
  // --------------------------------------------------------------------------------------------------------------------

  public String getClassName() {
    return className;
  }

  public String getDirectories() {
    return directories;
  }

  // --------------------------------------------------------------------------------------------------------------------
  // ConstantPool methods
  // --------------------------------------------------------------------------------------------------------------------
  public int addConstantClass(final int utf8Index) {
    final Constant.Class constant = new Constant.Class(utf8Index);
    return this.addConstant(constant);
  }

  public int addConstantString(final int utf8Index) {
    final Constant.String constant = new Constant.String(utf8Index);
    return this.addConstant(constant);
  }

  public int addConstantInteger(final int value) {
    final Constant.Integer constant = new Constant.Integer(value);
    return this.addConstant(constant);
  }

  public int addConstantFloat(final float value) {
    final Constant.Float constant = new Constant.Float(value);
    return this.addConstant(constant);
  }

  public int addConstantLong(final long value) {
    final Constant.Long constant = new Constant.Long(value);
    final int index = this.addConstant(constant);
    this.constantPool.add(null);
    return index;
  }

  public int addConstantDouble(final double value) {
    final Constant.Double constant = new Constant.Double(value);
    final int index = this.addConstant(constant);
    this.constantPool.add(null);
    return index;
  }

  public int addConstantUTF8(final String value) {
    final Constant.UTF8 constant = new Constant.UTF8(value);
    return this.addConstant(constant);
  }

  private int addConstant(final ConstantPoolEntry constant) {
    final int index = this.constantPool.indexOf(constant);

    if (index == -1) {
      this.constantPool.add(constant);
      return this.getCurrentCPIndex();
    } else {
      return index;
    }
  }

  public int getCurrentCPIndex() {
    return this.constantPool.size() - 1;
  }

  // --------------------------------------------------------------------------------------------------------------------
  // Method methods
  // --------------------------------------------------------------------------------------------------------------------

  public void addMethod(final Method method) {
    this.methods.add(method);
  }

  // --------------------------------------------------------------------------------------------------------------------
  // Overrides
  // --------------------------------------------------------------------------------------------------------------------

  @Override
  public void toBytecode(DataOutput dataOutput) throws IOException {
    dataOutput.writeInt(this.magicNumber);
    dataOutput.writeInt(this.version);
    dataOutput.writeShort(this.constantPool.size());
    this.constantPool.toBytecode(dataOutput);
    dataOutput.writeShort(this.accessFlags);
    dataOutput.writeShort(this.thisClass);
    dataOutput.writeShort(this.superClass);
    dataOutput.writeShort(this.interfaces.length);
    dataOutput.writeShort(this.fields.size());
    this.fields.toBytecode(dataOutput);
    dataOutput.writeShort(this.methods.size());
    this.methods.toBytecode(dataOutput);
    dataOutput.writeShort(this.attributes.size());
    this.attributes.toBytecode(dataOutput);
  }
}
