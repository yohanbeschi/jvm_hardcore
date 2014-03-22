package org.isk.jvmhardcore.pjba.builder;

import org.isk.jvmhardcore.pjba.structure.ClassFile;
import org.isk.jvmhardcore.pjba.structure.Field;
import org.isk.jvmhardcore.pjba.structure.Method;
import org.isk.jvmhardcore.pjba.structure.attribute.ConstantValue;
import org.isk.jvmhardcore.pjba.util.DescriptorCounter;

public class ClassFileBuilder {

  private ClassFile classFile;
  private MethodBuilder methodBuilder;

  public ClassFileBuilder(final int classModifiers, final String fullyQualifiedName) {
    super();
    this.classFile = new ClassFile(fullyQualifiedName);
    this.classFile.addAccessFlags(classModifiers);
  }

  public ClassFileBuilder newField(final int fieldModifiers, final String fieldName, final String fieldDescriptor) {
    this.createNewField(fieldModifiers, fieldName, fieldDescriptor);

    return this;
  }

  public ClassFileBuilder newConstantField(final int fieldModifiers,
                                           final String fieldName,
                                           final String fieldDescriptor,
                                           final int value) {
    final int constantIntegerIndex = this.classFile.addConstantInteger(value);
    final ConstantValue constantValue = this.createConstantValue(constantIntegerIndex);

    final Field field = this.createNewField(fieldModifiers | Field.MODIFIER_FINAL, fieldName, fieldDescriptor);
    field.addAttibute(constantValue);

    return this;
  }

  public ClassFileBuilder newConstantField(final int fieldModifiers,
                                           final String fieldName,
                                           final String fieldDescriptor,
                                           final long value) {
    final int constantIntegerIndex = this.classFile.addConstantLong(value);
    final ConstantValue constantValue = this.createConstantValue(constantIntegerIndex);

    final Field field = this.createNewField(fieldModifiers | Field.MODIFIER_FINAL, fieldName, fieldDescriptor);
    field.addAttibute(constantValue);

    return this;
  }

  public ClassFileBuilder newConstantField(final int fieldModifiers,
                                           final String fieldName,
                                           final String fieldDescriptor,
                                           final float value) {
    final int constantIntegerIndex = this.classFile.addConstantFloat(value);
    final ConstantValue constantValue = this.createConstantValue(constantIntegerIndex);

    final Field field = this.createNewField(fieldModifiers | Field.MODIFIER_FINAL, fieldName, fieldDescriptor);
    field.addAttibute(constantValue);

    return this;
  }

  public ClassFileBuilder newConstantField(final int fieldModifiers,
                                           final String fieldName,
                                           final String fieldDescriptor,
                                           final double value) {
    final int constantIntegerIndex = this.classFile.addConstantDouble(value);
    final ConstantValue constantValue = this.createConstantValue(constantIntegerIndex);

    final Field field = this.createNewField(fieldModifiers | Field.MODIFIER_FINAL, fieldName, fieldDescriptor);
    field.addAttibute(constantValue);

    return this;
  }

  public ClassFileBuilder newConstantField(final int fieldModifiers,
                                           final String fieldName,
                                           final String fieldDescriptor,
                                           final String value) {
    final int valueUtf8Index = this.classFile.addConstantUTF8(value);
    final int constantIntegerIndex = this.classFile.addConstantString(valueUtf8Index);
    final ConstantValue constantValue = this.createConstantValue(constantIntegerIndex);

    final Field field = this.createNewField(fieldModifiers | Field.MODIFIER_FINAL, fieldName, fieldDescriptor);
    field.addAttibute(constantValue);

    return this;
  }

  private Field createNewField(final int fieldModifiers, final String fieldName, final String fieldDescriptor) {
    final int nameIndex = this.classFile.addConstantUTF8(fieldName);
    final int descriptorIndex = this.classFile.addConstantUTF8(fieldDescriptor);
    final Field field = new Field();
    field.setNameIndex(nameIndex);
    field.setDescriptorIndex(descriptorIndex);
    field.setAccessFlags(fieldModifiers);
    this.classFile.addField(field);

    return field;
  }

  private ConstantValue createConstantValue(final int constantIndex) {
    final int constantValueAttributeIndex = this.classFile.addConstantUTF8(ConstantValue.ATTRIBUTE_NAME);
    final ConstantValue constantValue = new ConstantValue(constantValueAttributeIndex);
    constantValue.setConstantValueIndex(constantIndex);
    return constantValue;
  }

  public MethodBuilder newMethod(final int methodModifiers,
                                 final String methodName,
                                 final String methodDescriptor,
                                 final boolean eagerConstruction) {
    if (this.methodBuilder != null) {
      this.methodBuilder.buildMethod();
    }

    final int methodIndex = this.classFile.addConstantUTF8(methodName);
    final int descriptorIndex = this.classFile.addConstantUTF8(methodDescriptor);
    final Method method = new Method();
    method.setNameIndex(methodIndex);
    method.setDescriptorIndex(descriptorIndex);
    method.setAccessFlags(methodModifiers);
    this.classFile.addMethod(method);
    int locals = DescriptorCounter.methodsDescriptorParamsUnits(methodDescriptor);

    // Count this
    if ((Method.MODIFIER_STATIC & methodModifiers) != Method.MODIFIER_STATIC) {
      locals++;
    }
    
    this.methodBuilder = new MethodBuilder(this, method, locals, eagerConstruction);

    return this.methodBuilder;
  }

  public MethodBuilder newMethod(final int methodModifiers, final String methodName, final String methodDescriptor) {
    return this.newMethod(methodModifiers, methodName, methodDescriptor, true);
  }

  public MethodBuilder staticBlock(boolean eagerConstruction) {
    return this.newMethod(Method.MODIFIER_STATIC, "<clinit>", "()V", eagerConstruction);
  }

  public MethodBuilder staticBlock() {
    return this.staticBlock(true);
  }

  public ClassFile build() {
    if (this.methodBuilder != null) {
      this.methodBuilder.buildMethod();
    }

    return this.classFile;
  }

  protected ClassFile getClassFile() {
    return this.classFile;
  }
}
