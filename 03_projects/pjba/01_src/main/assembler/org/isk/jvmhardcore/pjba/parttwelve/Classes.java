package org.isk.jvmhardcore.pjba.parttwelve;

import org.isk.jvmhardcore.pjba.instruction.Instructions;
import org.isk.jvmhardcore.pjba.structure.ClassFile;
import org.isk.jvmhardcore.pjba.structure.Method;
import org.isk.jvmhardcore.pjba.structure.attribute.Code;
import org.junit.Test;

public class Classes {

  @Test
  public void assemble0() throws Exception {
    // ConstantPoolEntries
    final String className = "org/isk/jvmhardcore/pjba/MyFirstClass";
    final String methodName = "add";
    final String methodDescriptor = "(II)I";

    // ClassFile
    final ClassFile classFile = new ClassFile(className);

    // Method
    final int methodIndex = classFile.addConstantUTF8(methodName);
    final int descriptorIndex = classFile.addConstantUTF8(methodDescriptor);
    final Method method = new Method();
    method.setNameIndex(methodIndex);
    method.setDescriptorIndex(descriptorIndex);
    classFile.addMethod(method);

    // Code
    final int codeAttributeIndex = classFile.addConstantUTF8(Code.ATTRIBUTE_NAME);
    final Code code = new Code(codeAttributeIndex);
    final int parameterCount = method.countParameters(methodDescriptor);
    code.setParameterCount(parameterCount);

    // Instructions
    code.addInstruction(Instructions.iload_0());
    code.addInstruction(Instructions.iload_1());
    code.addInstruction(Instructions.iadd());
    code.addInstruction(Instructions.ireturn());
    method.addAttibute(code);

    org.isk.jvmhardcore.pjba.Assembling.createFile(classFile);
  }
}
