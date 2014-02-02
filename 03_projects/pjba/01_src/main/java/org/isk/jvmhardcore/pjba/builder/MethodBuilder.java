package org.isk.jvmhardcore.pjba.builder;

import org.isk.jvmhardcore.pjba.instruction.Instructions;
import org.isk.jvmhardcore.pjba.structure.Method;
import org.isk.jvmhardcore.pjba.structure.attribute.Code;

public class MethodBuilder {

  final private ClassFileBuilder classFileBuilder;
  final private Code code;

  public MethodBuilder(ClassFileBuilder classFileBuilder, Method method, int parametersCount) {
    this.classFileBuilder = classFileBuilder;

    final int codeAttributeIndex = this.classFileBuilder.getClassFile().addConstantUTF8(Code.ATTRIBUTE_NAME);
    this.code = new Code(codeAttributeIndex);
    this.code.setParameterCount(parametersCount);
    method.addAttibute(this.code);
  }

  public MethodBuilder iload_0() {
    this.code.addInstruction(Instructions.ILOAD_0);
    return this;
  }

  public MethodBuilder iload_1() {
    this.code.addInstruction(Instructions.ILOAD_1);
    return this;
  }

  public MethodBuilder iadd() {
    this.code.addInstruction(Instructions.IADD);
    return this;
  }

  public ClassFileBuilder ireturn() {
    this.code.addInstruction(Instructions.IRETURN);
    return this.classFileBuilder;
  }
}
