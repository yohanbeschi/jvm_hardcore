package org.isk.jvmhardcore.pjba.dumper;

import org.isk.jvmhardcore.pjba.builder.ClassFileBuilder;
import org.isk.jvmhardcore.pjba.structure.ClassFile;
import org.isk.jvmhardcore.pjba.structure.Method;
import org.junit.Assert;
import org.junit.Test;

public class PjbDumperTest {
  @Test
  public void dump0() {
    final ClassFile classFile = new ClassFileBuilder(ClassFile.MODIFIER_PUBLIC, "org/isk/jvmhardcore/pjba/MyFirstClass")
      .newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "add", "(II)I")
        .ldc("Привет \\\" \\n мир по-русски")
        .iload_0()
        .iload_1()
        .iadd()
        .ireturn()
    .build();

    final PjbDumper dumper = new PjbDumper(classFile);
    final String dump = dumper.dump();
    Assert.assertEquals(EXPECTED, dump);
  }

  final private static String EXPECTED = ".class public super org/isk/jvmhardcore/pjba/MyFirstClass\n"
                                       + "  .super java/lang/Object\n\n"
                                       + "  .method public static add(II)I\n"
                                         // actual string: Привет \" \n мир по-русски
                                       + "    ldc \"Привет \\\\\\\" \\\\n мир по-русски\"\n"
                                       + "    iload_0\n"
                                       + "    iload_1\n"
                                       + "    iadd\n"
                                       + "    ireturn\n"
                                       + "  .methodend\n"
                                       + ".classend";
}
