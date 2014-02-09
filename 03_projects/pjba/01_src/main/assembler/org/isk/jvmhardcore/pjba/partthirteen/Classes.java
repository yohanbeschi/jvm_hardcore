package org.isk.jvmhardcore.pjba.partthirteen;

import org.isk.jvmhardcore.pjba.builder.ClassFileBuilder;
import org.isk.jvmhardcore.pjba.structure.ClassFile;
import org.isk.jvmhardcore.pjba.structure.Method;
import org.junit.Test;

public class Classes {
  @Test
  public void assemble0() throws Exception {
    final String fullyQualifiedName = "org/isk/jvmhardcore/pjba/MySecondClass";
    final ClassFile classFile = new ClassFileBuilder(ClassFile.MODIFIER_PUBLIC, fullyQualifiedName)
      .newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "add", "(II)I")
        .iload_0()
        .iload_1()
        .iadd()
        .ireturn()
      .newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "add2", "(II)I")
        .iload_1()
        .iload_0()
        .iadd()
        .ireturn()
    .build();

    org.isk.jvmhardcore.pjba.Assembling.createFile(classFile);
  }
}
