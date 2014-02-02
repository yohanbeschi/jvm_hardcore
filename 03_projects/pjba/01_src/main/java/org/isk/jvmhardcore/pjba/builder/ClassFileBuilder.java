package org.isk.jvmhardcore.pjba.builder;

import org.isk.jvmhardcore.pjba.structure.ClassFile;
import org.isk.jvmhardcore.pjba.structure.Method;

public class ClassFileBuilder {

  private ClassFile classFile;

  public ClassFileBuilder(final String fullyQualifiedName) {
    super();
    this.classFile = new ClassFile(fullyQualifiedName);
  }

  public MethodBuilder newMethod(String methodName, String methodDescriptor) {
    final int methodIndex = this.classFile.addConstantUTF8(methodName);
    final int descriptorIndex = this.classFile.addConstantUTF8(methodDescriptor);
    final Method method = new Method();
    method.setNameIndex(methodIndex);
    method.setDescriptorIndex(descriptorIndex);
    this.classFile.addMethod(method);
    final int parametersCount = method.countParameters(methodDescriptor);

    return new MethodBuilder(this, method, parametersCount);
  }

  public ClassFile build() {
    return this.classFile;
  }

  protected ClassFile getClassFile() {
    return this.classFile;
  }
}
