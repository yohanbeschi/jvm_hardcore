package org.isk.jvmhardcore.pjba.structure;

import org.isk.jvmhardcore.pjba.structure.Constant.ConstantPoolEntry;
import org.isk.jvmhardcore.pjba.structure.attribute.constraint.ClassAttribute;
import org.isk.jvmhardcore.pjba.util.Ascii;
import org.isk.jvmhardcore.pjba.util.PjbaLinkedList;
import org.isk.jvmhardcore.pjba.visitor.Visitable;
import org.isk.jvmhardcore.pjba.visitor.Visitor;

public class ClassFile implements Visitable {
  final private int magicNumber = 0xcafebabe;
  private int version = 0x30; // 48.0 = 0x00 (version mineure) | 0x30 (version majeur)

  private PjbaLinkedList<Constant.ConstantPoolEntry> constantPool;

  private int accessFlags = 0x0001 | 0x0020; // public super

  private int thisClass;
  private int superClass;

  private int[] interfaces;

  private PjbaLinkedList<Field> fields;
  private PjbaLinkedList<Method> methods;
  private PjbaLinkedList<ClassAttribute> attributes;

  private String className;
  private String directories;

  public ClassFile() {
    this.constantPool = new PjbaLinkedList<>();
    this.interfaces = new int[0];
    this.fields = new PjbaLinkedList<>();
    this.methods = new PjbaLinkedList<>();
    this.attributes = new PjbaLinkedList<>();
  }

  public ClassFile(final String fullyQualifiedName) {
    this();

    this.parseName(fullyQualifiedName);

    this.constantPool.add(null); // Index 0 reserved by the JVM

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
  public void setVersion(int version) {
    this.version = version;
  }

  public void setConstantPool(PjbaLinkedList<Constant.ConstantPoolEntry> constantPool) {
    this.constantPool = constantPool;
  }

  public void setAccessFlags(int accessFlags) {
    this.accessFlags = accessFlags;
  }

  public void setThisClass(int thisClass) {
    this.thisClass = thisClass;
  }

  public void setSuperClass(int superClass) {
    this.superClass = superClass;
  }

  public void setInterfaces(int[] interfaces) {
    this.interfaces = interfaces;
  }

  public void setFields(PjbaLinkedList<Field> fields) {
    this.fields = fields;
  }

  public void setMethods(PjbaLinkedList<Method> methods) {
    this.methods = methods;
  }

  public void setAttributes(PjbaLinkedList<ClassAttribute> attributes) {
    this.attributes = attributes;
  }

  public void setFullyQualifiedClassName(String fullyQualifiedClassName) {
    this.parseName(fullyQualifiedClassName);
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

  public ConstantPoolEntry getConstant(int index) {
    return this.constantPool.get(index);
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
  public void accept(Visitor visitor) {
    visitor.visitMagicNumber(this.magicNumber);
    visitor.visitVersion(this.version);
    visitor.visitConstantPoolSize(this.constantPool.size());
    this.constantPool.accept(visitor);
    visitor.visitClassAccessFlags(this.accessFlags);
    visitor.visitThisClass(this.thisClass);
    visitor.visitSuperClass(this.superClass);
    visitor.visitInterfacesSize(this.interfaces.length);
    visitor.visitFieldsSize(this.fields.size());
    this.fields.accept(visitor);
    visitor.visitMethodsSize(this.methods.size());
    this.methods.accept(visitor);
    visitor.visitClassAttributeSize(this.attributes.size());
    this.attributes.accept(visitor);
  }
}