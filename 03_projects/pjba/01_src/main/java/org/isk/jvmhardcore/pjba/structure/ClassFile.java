package org.isk.jvmhardcore.pjba.structure;

import org.isk.jvmhardcore.pjba.structure.Constant.ConstantPoolEntry;
import org.isk.jvmhardcore.pjba.structure.attribute.constraint.ClassAttribute;
import org.isk.jvmhardcore.pjba.util.Ascii;
import org.isk.jvmhardcore.pjba.util.PjbaLinkedList;
import org.isk.jvmhardcore.pjba.visitor.Visitable;
import org.isk.jvmhardcore.pjba.visitor.Visitor;

public class ClassFile implements Visitable {
  final public static int MODIFIER_PUBLIC = 0x0001;
  final public static int MODIFIER_FINAL = 0x0010;
  final public static int MODIFIER_SUPER = 0x0020;
  final public static int MODIFIER_INTERFACE = 0x0200;
  final public static int MODIFIER_ABSTRACT = 0x0400;

  final private int magicNumber = 0xcafebabe;
  private int version = 0x30; // 48.0 = 0x00 (minor version) | 0x30 (major version)

  private PjbaLinkedList<Constant.ConstantPoolEntry> constantPool;

  private int accessFlags;

  private int thisClass;
  private int superClass;

  private PjbaLinkedList<Interface> interfaces;

  private PjbaLinkedList<Field> fields;
  private PjbaLinkedList<Method> methods;
  private PjbaLinkedList<ClassAttribute> attributes;

  private String className;
  private String directories;

  public ClassFile() {
    this.constantPool = new PjbaLinkedList<>();
    this.interfaces = new PjbaLinkedList<>();
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
  }

  // -------------------------------------------------------------------------------------------------------------------
  // Support methods
  // -------------------------------------------------------------------------------------------------------------------

  private void parseName(final String fullyQualifiedName) {
    int index = fullyQualifiedName.lastIndexOf(Ascii.SLASH);

    if (index >= 0) {
      this.directories = fullyQualifiedName.substring(0, ++index);
      this.className = fullyQualifiedName.substring(index);
    } else {
      this.className = fullyQualifiedName;
    }
  }

  // -------------------------------------------------------------------------------------------------------------------
  // External getters
  // -------------------------------------------------------------------------------------------------------------------

  public String getClassName() {
    return this.className;
  }

  public String getDirectories() {
    return this.directories;
  }

  // -------------------------------------------------------------------------------------------------------------------
  // ConstantPool methods
  // -------------------------------------------------------------------------------------------------------------------
  public void setVersion(int version) {
    this.version = version;
  }

  public void setConstantPool(PjbaLinkedList<ConstantPoolEntry> constantPool) {
    this.constantPool = constantPool;
  }

  public void setAccessFlags(int accessFlags) {
    this.accessFlags = accessFlags;
  }

  public void addAccessFlags(int accessFlags) {
    this.accessFlags |= accessFlags;
  }

  public boolean accessFlagSet(int accessFlags) {
    return (this.accessFlags & accessFlags) == accessFlags;
  }

  public void setThisClass(int thisClass) {
    this.thisClass = thisClass;
  }

  public void setSuperClass(int superClass) {
    this.superClass = superClass;
  }

  public void setInterfaces(PjbaLinkedList<Interface> interfaces) {
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

  // -------------------------------------------------------------------------------------------------------------------
  // ConstantPool methods
  // -------------------------------------------------------------------------------------------------------------------
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

  public int addConstantFieldRef(final int classIndex, final int nameAndTypeIndex) {
    final Constant.FieldRef constant = new Constant.FieldRef(classIndex, nameAndTypeIndex);
    return this.addConstant(constant);
  }

  public int addConstantMethodRef(final int classIndex, final int nameAndTypeIndex) {
    final Constant.MethodRef constant = new Constant.MethodRef(classIndex, nameAndTypeIndex);
    return this.addConstant(constant);
  }

  public int addConstantInterfaceMethodRef(final int classIndex, final int nameAndTypeIndex) {
    final Constant.InterfaceMethodRef constant = new Constant.InterfaceMethodRef(classIndex, nameAndTypeIndex);
    return this.addConstant(constant);
  }

  public int addConstantNameAndType(final int nameIndex, final int descriptorIndex) {
    final Constant.NameAndType constant = new Constant.NameAndType(nameIndex, descriptorIndex);
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

  // -------------------------------------------------------------------------------------------------------------------
  // Interface methods
  // -------------------------------------------------------------------------------------------------------------------

  public void addInterface(final Interface interface_) {
    this.interfaces.add(interface_);
  }

  public PjbaLinkedList<Interface> getInterfaces() {
    return this.interfaces;
  }

  // -------------------------------------------------------------------------------------------------------------------
  // Field methods
  // -------------------------------------------------------------------------------------------------------------------

  public void addField(final Field field) {
    this.fields.add(field);
  }

  public PjbaLinkedList<Field> getFields() {
    return this.fields;
  }

  // -------------------------------------------------------------------------------------------------------------------
  // Method methods
  // -------------------------------------------------------------------------------------------------------------------

  public void addMethod(final Method method) {
    this.methods.add(method);
  }

  public PjbaLinkedList<Method> getMethods() {
    return this.methods;
  }

  // -------------------------------------------------------------------------------------------------------------------
  // Overrides
  // -------------------------------------------------------------------------------------------------------------------

  @Override
  public void accept(Visitor visitor) {
    visitor.visitMagicNumber(this.magicNumber);
    visitor.visitVersion(this.version);
    visitor.visitConstantPoolSize(this.constantPool.size());
    this.constantPool.accept(visitor);
    visitor.visitClassAccessFlags(this.accessFlags);
    visitor.visitThisClass(this.thisClass);
    visitor.visitSuperClass(this.superClass);
    visitor.visitInterfacesSize(this.interfaces.size());
    this.interfaces.accept(visitor);
    visitor.visitFieldsSize(this.fields.size());
    this.fields.accept(visitor);
    visitor.visitMethodsSize(this.methods.size());
    this.methods.accept(visitor);
    visitor.visitClassAttributeSize(this.attributes.size());
    this.attributes.accept(visitor);
  }
}
