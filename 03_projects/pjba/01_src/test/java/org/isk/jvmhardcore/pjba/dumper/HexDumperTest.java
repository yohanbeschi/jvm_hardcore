package org.isk.jvmhardcore.pjba.dumper;

import org.isk.jvmhardcore.pjba.builder.ClassFileBuilder;
import org.isk.jvmhardcore.pjba.structure.ClassFile;
import org.isk.jvmhardcore.pjba.structure.Method;
import org.junit.Test;

public class HexDumperTest {
  @Test
  public void dump0() {
    final ClassFileBuilder cfb = new ClassFileBuilder(ClassFile.MODIFIER_PUBLIC, "org/isk/jvmhardcore/pjba/MyFirstClass");
    cfb.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "add", "(II)I")
        .iload_0()
        .iload_1()
        .iadd()
        .ireturn();
    final ClassFile classFile = cfb.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "buildILoadUnsigned", "()I")
      .ldc(7_687)
      .istore((byte) 0xe6)
      .iload((byte) 0xe6)
      .ireturn()
    .build();

    final HexDumper dumper = new HexDumper(classFile);
    System.out.println(dumper.dump());
  }
}
