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
    this.buildTableswitch1(builder);
    this.buildTableswitch2(builder);
    this.buildTableswitch3(builder);
    this.buildLookupswitch1(builder);
    this.buildLookupswitch2(builder);
    this.buildLookupswitch3(builder);

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

  private void buildTableswitch1(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "tableswitch1", "(I)I")
      .iload_0()
      .tableswitch("default", 5, 8)
        .offset("label_5")
        .offset("label_6and7")
        .offset("label_6and7")
        .offset("label_8")
      .end()
      .label("label_5")
      .bipush((byte) 10)
      .ireturn()
      .label("label_6and7")
      .bipush((byte) 12)
      .ireturn()
      .label("label_8")
      .bipush((byte) 14)
      .ireturn()
      .label("default")
      .bipush((byte) 100)
      .ireturn();
  }
  
  private void buildTableswitch2(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "tableswitch2", "(I)I")
      .iload_0()
      .tableswitch("label_8", 5, 8)
        .offset("label_5")
        .offset("label_6and7")
        .offset("label_6and7")
        .offset("label_8")
      .end()
      .label("label_5")
      .bipush((byte) 10)
      .ireturn()
      .label("label_6and7")
      .bipush((byte) 12)
      .ireturn()
      .label("label_8")
      .bipush((byte) 14)
      .ireturn();
  }

  private void buildTableswitch3(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "tableswitch3", "(I)I")
      .label("loop")
      .iload_0()
      .ifgt("ok")
      .iload_0()
      .tableswitch("ok", 0, 1)
        .offset("label_0")
        .offset("ok")
      .end()
      .label("label_0")
      .iconst_3()
      .istore_0()
      .goto_("loop")
      .label("ok")
      .iload_0()
      .ireturn();
  }

  private void buildLookupswitch1(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "lookupswitch1", "(I)I")
      .iload_0()
      .lookupswitch("default", 3)
        .matchOffset(5, "label_10")
        .matchOffset(10, "label_10")
        .matchOffset(15, "label_15")
      .end()
      .label("label_10")
      .bipush((byte) 20)
      .ireturn()
      .label("label_15")
      .bipush((byte) 30)
      .ireturn()
      .label("default")
      .bipush((byte) 100)
      .ireturn();
  }

  private void buildLookupswitch2(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "lookupswitch2", "(I)I")
      .iload_0()
      .lookupswitch("label_15", 3)
        .matchOffset(5, "label_10")
        .matchOffset(10, "label_10")
        .matchOffset(15, "label_15")
      .end()
      .label("label_10")
      .bipush((byte) 20)
      .ireturn()
      .label("label_15")
      .bipush((byte) 30)
      .ireturn();
  }

  private void buildLookupswitch3(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "lookupswitch3", "(I)I")
      .label("loop")
      .iload_0()
      .ifgt("ok")
      .iload_0()
      .lookupswitch("ok", 2)
        .matchOffset(0, "label_0")
        .matchOffset(5000, "ok")
      .end()
      .label("label_0")
      .iconst_3()
      .istore_0()
      .goto_("loop")
      .label("ok")
      .iload_0()
      .ireturn();
  }
}
