package org.isk.jvmhardcore.pjba.dumper;

import org.isk.jvmhardcore.pjba.builder.ClassFileBuilder;
import org.isk.jvmhardcore.pjba.structure.ClassFile;
import org.isk.jvmhardcore.pjba.structure.Method;
import org.junit.Test;

public class HexDumperTest {
  @Test
  public void dump0() {
    final ClassFile classFile = new ClassFileBuilder(ClassFile.MODIFIER_PUBLIC, "org/isk/jvmhardcore/pjba/MyFirstClass")
      .newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "add", "(II)I")
        .ldc("Привет \\\" мир по-русски")
        .iload_0()
        .iload_1()
        .iadd()
        .ireturn()
    .build();

    final HexDumper dumper = new HexDumper(classFile);
    System.out.println(dumper.dump());
  }
}
