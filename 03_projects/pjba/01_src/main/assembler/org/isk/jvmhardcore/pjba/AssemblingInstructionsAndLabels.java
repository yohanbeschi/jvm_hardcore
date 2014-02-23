package org.isk.jvmhardcore.pjba;
import org.isk.jvmhardcore.pjba.builder.ClassFileBuilder;
import org.isk.jvmhardcore.pjba.structure.ClassFile;
import org.isk.jvmhardcore.pjba.structure.Method;
import org.junit.Test;


public class AssemblingInstructionsAndLabels {
  @Test
  public void assemble() throws Exception {
    final ClassFileBuilder builder = new ClassFileBuilder(ClassFile.MODIFIER_PUBLIC,
        "org/isk/jvmhardcore/pjba/InstructionsAndLabels");

    this.buildCase1(builder);
    this.buildCase2(builder);
    this.buildCase3(builder);
    this.buildCase4(builder);
    this.buildCase5(builder);

    org.isk.jvmhardcore.pjba.Assembling.createFile(builder.build());

    // final PjbDumper dumper = new PjbDumper(builder.build());
    // System.out.println(dumper.dump());

    // final HexDumper dumper = new HexDumper(builder.build());
    // System.out.println(dumper.dump());
  }

  /*
   * Case 1:
   *      mnemonic label_1
   *      label_1
   */
  private void buildCase1(final ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "case1", "(I)Z")
      .iload_0()
      .ifeq("ok")
      .iconst_0()
      .ireturn()
      .label("ok")
      .iconst_1()
      .ireturn();
  }

  /*
   * Case 2:
   *      mnemonic label_1
   *      mnemonic label_1
   *      label_1
   */
  private void buildCase2(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "case2", "(II)Z")
      .iload_0()
      .ifeq("ok")
      .iload_1()
      .ifeq("ok")
      .iconst_0()
      .ireturn()
      .label("ok")
      .iconst_1()
      .ireturn();
  }

  /*
   * Case 3:
   *      label_1
   *      mnemonic label_1
   */
  private void buildCase3(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "case3", "()I")
      .bipush((byte) -10)
      .istore_0()
      .iconst_0()
      .istore_1()
      .label("before")              // label_1
      .iinc((short) 0, (short) 1)
      .iinc((short) 1, (short) 1)
      .iload_0()
      .iflt("before")               // mnemonic label_1
      .iload_1()
      .ireturn();
  }

  /*
   * Case 4:
   *      label_1
   *      mnemonic label_1
   *      mnemonic label_1
   */
  private void buildCase4(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "case4", "()I")
      .bipush((byte) -10)
      .istore_0()
      .iconst_0()
      .istore_1()
      .label("before")
      .iinc((short) 0, (short) 1)
      .iinc((short) 1, (short) 1)
      .iload_0()
      .iflt("before")
      .iinc((short) 0, (short) -10)
      .iload_1()
      .bipush((byte) 10)
      .if_icmpeq("before")
      .iload_1()
      .ireturn();
  }

  /*
   * Case 5:
   *      mnemonic label_1
   *      label_1
   *      mnemonic label_1
   */
  private void buildCase5(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "case5", "(I)I")
    .bipush((byte) -10)
    .istore_1()
    .iconst_0()
    .istore_2()
    .iload_0()
    .ifeq("before")
    .bipush((byte) 100)
    .istore_2()
    .label("before")              // label_1
    .iinc((short) 1, (short) 1)
    .iinc((short) 2, (short) 1)
    .iload_1()
    .iflt("before")               // mnemonic label_1
    .iload_2()
    .ireturn();
  }
}
