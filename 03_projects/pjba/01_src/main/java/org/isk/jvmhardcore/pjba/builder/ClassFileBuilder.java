package org.isk.jvmhardcore.pjba.builder;

import org.isk.jvmhardcore.pjba.structure.ClassFile;
import org.isk.jvmhardcore.pjba.structure.Method;

public class ClassFileBuilder {

  private ClassFile classFile;
  private MethodBuilder methodBuilder;

  public ClassFileBuilder(final int classModifiers, final String fullyQualifiedName) {
    super();
    this.classFile = new ClassFile(fullyQualifiedName);
    this.classFile.addAccessFlags(classModifiers);
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
    final int parametersCount = method.countParameters(methodDescriptor);

    this.methodBuilder = new MethodBuilder(this, method, parametersCount, eagerConstruction);

    return this.methodBuilder;
  }

  public MethodBuilder newMethod(final int methodModifiers, final String methodName, final String methodDescriptor) {
    return this.newMethod(methodModifiers, methodName, methodDescriptor, true);
  }

  public ClassFile build() {
    this.methodBuilder.buildMethod();
    return this.classFile;
  }

  protected ClassFile getClassFile() {
    return this.classFile;
  }
}
