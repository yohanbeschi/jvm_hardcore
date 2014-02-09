package org.isk.jvmhardcore.pjba.partfourteen;

import org.isk.jvmhardcore.pjba.builder.ClassFileBuilder;
import org.isk.jvmhardcore.pjba.builder.MethodBuilder;
import org.isk.jvmhardcore.pjba.structure.ClassFile;
import org.junit.Test;

public class Classes {

  @Test
  public void assemble0() throws Exception {
    final ClassFile classFile = this.buildClass("org/isk/jvmhardcore/pjba/AllInstructionsWithoutDummiesInCP", false);
    org.isk.jvmhardcore.pjba.Assembling.createFile(classFile);
  }

  @Test
  public void assemble1() throws Exception {
    final ClassFile classFile = this.buildClass("org/isk/jvmhardcore/pjba/AllInstructionsWithDummiesInCP", true);
    org.isk.jvmhardcore.pjba.Assembling.createFile(classFile);
  }

  private ClassFile buildClass(final String fullyQualifiedName, final boolean withDummies) {
    final ClassFileBuilder builder = new ClassFileBuilder(fullyQualifiedName);

    if (withDummies) {
      this.addDummyConstants(builder);
    }

    this.buildNop(builder);
    this.buildAConstNull(builder);
    this.buildIConstM1(builder);
    this.buildIConst0(builder);
    this.buildIConst1(builder);
    this.buildIConst2(builder);
    this.buildIConst3(builder);
    this.buildIConst4(builder);
    this.buildIConst5(builder);
    this.buildLConst0(builder);
    this.buildLConst1(builder);
    this.buildFConst0(builder);
    this.buildFConst1(builder);
    this.buildFConst2(builder);
    this.buildDConst0(builder);
    this.buildDConst1(builder);
    this.buildBipush(builder);
    this.buildSipush(builder);
    this.buildLdc(builder);
    if (withDummies) {
      this.buildLdcW(builder);
    }
    this.buildLdc2W(builder);
    this.buildILoad(builder);
    this.buildLLoad(builder);
    this.buildFLoad(builder);
    this.buildDLoad(builder);
    this.buildALoad(builder);
    this.buildILoad0(builder);
    this.buildILoad1(builder);
    this.buildILoad2(builder);
    this.buildILoad3(builder);
    this.buildLLoad0(builder);
    this.buildLLoad1(builder);
    this.buildLLoad2(builder);
    this.buildLLoad3(builder);
    this.buildFLoad0(builder);
    this.buildFLoad1(builder);
    this.buildFLoad2(builder);
    this.buildFLoad3(builder);
    this.buildDLoad0(builder);
    this.buildDLoad1(builder);
    this.buildDLoad2(builder);
    this.buildDLoad3(builder);
    this.buildALoad0(builder);
    this.buildALoad1(builder);
    this.buildALoad2(builder);
    this.buildALoad3(builder);
    this.buildIStore(builder);
    this.buildLStore(builder);
    this.buildFStore(builder);
    this.buildDStore(builder);
    this.buildAStore(builder);
    this.buildIStore0(builder);
    this.buildIStore1(builder);
    this.buildIStore2(builder);
    this.buildIStore3(builder);
    this.buildLStore0(builder);
    this.buildLStore1(builder);
    this.buildLStore2(builder);
    this.buildLStore3(builder);
    this.buildFStore0(builder);
    this.buildFStore1(builder);
    this.buildFStore2(builder);
    this.buildFStore3(builder);
    this.buildDStore0(builder);
    this.buildDStore1(builder);
    this.buildDStore2(builder);
    this.buildDStore3(builder);
    this.buildAStore0(builder);
    this.buildAStore1(builder);
    this.buildAStore2(builder);
    this.buildAStore3(builder);
    this.buildPop(builder);
    this.buildPop2(builder);
    this.buildDup(builder);
    this.buildDupX1(builder);
    this.buildDupX2(builder);
    this.buildDup2(builder);
    this.buildDup2X1(builder);
    this.buildDup2X2(builder);
    this.buildSwap(builder);
    this.buildIAdd(builder);
    this.buildLAdd(builder);
    this.buildFAdd(builder);
    this.buildDAdd(builder);
    this.buildISub(builder);
    this.buildLSub(builder);
    this.buildFSub(builder);
    this.buildDSub(builder);
    this.buildIMul(builder);
    this.buildLMul(builder);
    this.buildFMul(builder);
    this.buildDMul(builder);
    this.buildIDiv(builder);
    this.buildLDiv(builder);
    this.buildFDiv(builder);
    this.buildDDiv(builder);
    this.buildIRem(builder);
    this.buildLRem(builder);
    this.buildFRem(builder);
    this.buildDRem(builder);
    this.buildINeg(builder);
    this.buildLNeg(builder);
    this.buildFNeg(builder);
    this.buildDNeg(builder);
    this.buildIShl(builder);
    this.buildLShl(builder);
    this.buildIShr(builder);
    this.buildLShr(builder);
    this.buildIUshr(builder);
    this.buildLUshr(builder);
    this.buildIAnd(builder);
    this.buildLAnd(builder);
    this.buildIOr(builder);
    this.buildLOr(builder);
    this.buildIXor(builder);
    this.buildLXor(builder);
    this.buildI2L(builder);
    this.buildI2F(builder);
    this.buildI2D(builder);
    this.buildL2I(builder);
    this.buildL2F(builder);
    this.buildL2D(builder);
    this.buildF2I(builder);
    this.buildF2L(builder);
    this.buildF2D(builder);
    this.buildD2I(builder);
    this.buildD2L(builder);
    this.buildD2F(builder);
    this.buildI2B(builder);
    this.buildI2C(builder);
    this.buildI2S(builder);

    // Used everywhere => No test needed
    // this.buildIReturn(builder);
    // this.buildLReturn(builder);
    // this.buildFReturn(builder);
    // this.buildDReturn(builder);
    // this.buildAReturn(builder);
    // this.buildReturn(builder);

    this.buildIStoreLoadUnsigned(builder);
    this.buildLStoreLoadUnsigned(builder);
    this.buildFStoreLoadUnsigned(builder);
    this.buildDStoreLoadUnsigned(builder);
    this.buildAStoreLoadUnsigned(builder);

    return builder.build();
  }

  // To test ldc_w and with short indexes instead of byte
  private void addDummyConstants(ClassFileBuilder builder) {
    final String s = "abcdefghijklmnopqrstuvwxyz";

    final MethodBuilder mBuilder = builder.newMethod("dummy", "()V");

    for (int i = 0; i < s.length(); i++) {
      final StringBuilder sb = new StringBuilder();
      for (int j = 0; j < 10; j++) {
        sb.append(s.charAt(i));
        mBuilder.ldc(sb.toString());
      }
    }

    mBuilder.return_();
  }

  private void buildNop(ClassFileBuilder builder) {
    builder.newMethod("nop", "()V")
      .nop()
      .return_();
  }

  private void buildAConstNull(ClassFileBuilder builder) {
    builder.newMethod("aconst_null", "()Ljava/lang/Object;")
      .aconst_null()
      .areturn();
  }

  private void buildIConstM1(ClassFileBuilder builder) {
    builder.newMethod("iconst_m1", "()I")
      .iconst_m1()
      .ireturn();
  }

  private void buildIConst0(ClassFileBuilder builder) {
    builder.newMethod("iconst_0", "()I")
      .iconst_0()
      .ireturn();
  }

  private void buildIConst1(ClassFileBuilder builder) {
    builder.newMethod("iconst_1", "()I")
      .iconst_1()
      .ireturn();
  }

  private void buildIConst2(ClassFileBuilder builder) {
    builder.newMethod("iconst_2", "()I")
      .iconst_2()
      .ireturn();
  }

  private void buildIConst3(ClassFileBuilder builder) {
    builder.newMethod("iconst_3", "()I")
      .iconst_3()
      .ireturn();
  }

  private void buildIConst4(ClassFileBuilder builder) {
    builder.newMethod("iconst_4", "()I")
      .iconst_4()
      .ireturn();
  }

  private void buildIConst5(ClassFileBuilder builder) {
    builder.newMethod("iconst_5", "()I")
      .iconst_5()
      .ireturn();
  }

  private void buildLConst0(ClassFileBuilder builder) {
    builder.newMethod("lconst_0", "()J")
      .lconst_0()
      .lreturn();
  }

  private void buildLConst1(ClassFileBuilder builder) {
    builder.newMethod("lconst_1", "()J")
      .lconst_1()
      .lreturn();
  }

  private void buildFConst0(ClassFileBuilder builder) {
    builder.newMethod("fconst_0", "()F")
      .fconst_0()
      .freturn();
  }

  private void buildFConst1(ClassFileBuilder builder) {
    builder.newMethod("fconst_1", "()F")
      .fconst_1()
      .freturn();
  }

  private void buildFConst2(ClassFileBuilder builder) {
    builder.newMethod("fconst_2", "()F")
      .fconst_2()
      .freturn();
  }

  private void buildDConst0(ClassFileBuilder builder) {
    builder.newMethod("dconst_0", "()D")
      .dconst_0()
      .dreturn();
  }

  private void buildDConst1(ClassFileBuilder builder) {
    builder.newMethod("dconst_1", "()D")
      .dconst_1()
      .dreturn();
  }

  private void buildBipush(ClassFileBuilder builder) {
    builder.newMethod("bipush", "()I")
      .bipush((byte)125)
      .ireturn();
  }

  private void buildSipush(ClassFileBuilder builder) {
    builder.newMethod("sipush", "()I")
      .sipush((short)5_396)
      .ireturn();
  }

  private void buildLdc(ClassFileBuilder builder) {
    builder.newMethod("ldc_String", "()Ljava/lang/String;")
      .ldc("Hello World")
      .areturn();

    builder.newMethod("ldc_int", "()I")
      .ldc(167_980_564)
      .ireturn();

    builder.newMethod("ldc_float", "()F")
      .ldc(3.5f)
      .freturn();
  }

  private void buildLdcW(ClassFileBuilder builder) {
    builder.newMethod("ldc_w_String", "()Ljava/lang/String;")
    .ldc("Hello World Wide...")
    .areturn();

    builder.newMethod("ldc_w_int", "()I")
      .ldc(999_999_999)
      .ireturn();

    builder.newMethod("ldc_w_float", "()F")
      .ldc(999.9999f)
      .freturn();
  }

  private void buildLdc2W(ClassFileBuilder builder) {
    builder.newMethod("ldc_long", "()J")
      .ldc(167_980_564_900l)
      .lreturn();

    builder.newMethod("ldc_double", "()D")
      .ldc(3.578_978_979)
      .dreturn();
  }

  private void buildILoad(ClassFileBuilder builder) {
    builder.newMethod("iload", "(ZZZZZZZZZZI)I")
      .iload((byte)10)
      .ireturn();
  }

  private void buildLLoad(ClassFileBuilder builder) {
    builder.newMethod("lload", "(ZZZZZZZZZZJ)J")
      .lload((byte)10)
      .lreturn();
  }

  private void buildFLoad(ClassFileBuilder builder) {
    builder.newMethod("fload", "(ZZZZZZZZZZF)F")
      .fload((byte)10)
      .freturn();
  }

  private void buildDLoad(ClassFileBuilder builder) {
    builder.newMethod("dload", "(ZZZZZZZZZZD)D")
      .dload((byte)10)
      .dreturn();
  }

  private void buildALoad(ClassFileBuilder builder) {
    builder.newMethod("aload", "(ZZZZZZZZZZLjava/lang/Integer;)Ljava/lang/Integer;")
      .aload((byte)10)
      .areturn();
  }

  private void buildILoad0(ClassFileBuilder builder) {
    builder.newMethod("iload_0", "(I)I")
      .iload_0()
      .ireturn();
  }

  private void buildILoad1(ClassFileBuilder builder) {
    builder.newMethod("iload_1", "(ZI)I")
      .iload_1()
      .ireturn();
  }

  private void buildILoad2(ClassFileBuilder builder) {
    builder.newMethod("iload_2", "(ZZI)I")
      .iload_2()
      .ireturn();
  }

  private void buildILoad3(ClassFileBuilder builder) {
    builder.newMethod("iload_3", "(ZZZI)I")
      .iload_3()
      .ireturn();
  }

  private void buildLLoad0(ClassFileBuilder builder) {
    builder.newMethod("lload_0", "(J)J")
      .lload_0()
      .lreturn();
  }

  private void buildLLoad1(ClassFileBuilder builder) {
    builder.newMethod("lload_1", "(ZJ)J")
      .lload_1()
      .lreturn();
  }

  private void buildLLoad2(ClassFileBuilder builder) {
    builder.newMethod("lload_2", "(ZZJ)J")
      .lload_2()
      .lreturn();
  }

  private void buildLLoad3(ClassFileBuilder builder) {
    builder.newMethod("lload_3", "(ZZZJ)J")
      .lload_3()
      .lreturn();
  }

  private void buildFLoad0(ClassFileBuilder builder) {
    builder.newMethod("fload_0", "(F)F")
      .fload_0()
      .freturn();
  }

  private void buildFLoad1(ClassFileBuilder builder) {
    builder.newMethod("fload_1", "(ZF)F")
      .fload_1()
      .freturn();
  }

  private void buildFLoad2(ClassFileBuilder builder) {
    builder.newMethod("fload_2", "(ZZF)F")
      .fload_2()
      .freturn();
  }

  private void buildFLoad3(ClassFileBuilder builder) {
    builder.newMethod("fload_3", "(ZZZF)F")
      .fload_3()
      .freturn();
  }

  private void buildDLoad0(ClassFileBuilder builder) {
    builder.newMethod("dload_0", "(D)D")
      .dload_0()
      .dreturn();
  }

  private void buildDLoad1(ClassFileBuilder builder) {
    builder.newMethod("dload_1", "(ZD)D")
      .dload_1()
      .dreturn();
  }

  private void buildDLoad2(ClassFileBuilder builder) {
    builder.newMethod("dload_2", "(ZZD)D")
      .dload_2()
      .dreturn();
  }

  private void buildDLoad3(ClassFileBuilder builder) {
    builder.newMethod("dload_3", "(ZZZD)D")
      .dload_3()
      .dreturn();
  }

  private void buildALoad0(ClassFileBuilder builder) {
    builder.newMethod("aload_0", "(Ljava/lang/Integer;)Ljava/lang/Integer;")
      .aload_0()
      .areturn();
  }

  private void buildALoad1(ClassFileBuilder builder) {
    builder.newMethod("aload_1", "(ZLjava/lang/Integer;)Ljava/lang/Integer;")
      .aload_1()
      .areturn();
  }

  private void buildALoad2(ClassFileBuilder builder) {
    builder.newMethod("aload_2", "(ZZLjava/lang/Integer;)Ljava/lang/Integer;")
      .aload_2()
      .areturn();
  }

  private void buildALoad3(ClassFileBuilder builder) {
    builder.newMethod("aload_3", "(ZZZLjava/lang/Integer;)Ljava/lang/Integer;")
      .aload_3()
      .areturn();
  }

  private void buildIStore(ClassFileBuilder builder) {
    builder.newMethod("istore", "(IZZZZZZZZZI)I")
    .iload_0()
    .istore((byte) 10)
    .iload((byte) 10)
    .ireturn();
  }

  private void buildLStore(ClassFileBuilder builder) {
    builder.newMethod("lstore", "(JZZZZZZZZJ)J")
      .lload_0()
      .lstore((byte) 10)
      .lload((byte) 10)
      .lreturn();
  }

  private void buildFStore(ClassFileBuilder builder) {
    builder.newMethod("fstore", "(FZZZZZZZZZF)F")
      .fload_0()
      .fstore((byte) 10)
      .fload((byte) 10)
      .freturn();
  }

  private void buildDStore(ClassFileBuilder builder) {
    builder.newMethod("dstore", "(DZZZZZZZZD)D")
    .dload_0()
    .dstore((byte) 10)
    .dload((byte) 10)
    .dreturn();
  }

  private void buildAStore(ClassFileBuilder builder) {
    builder.newMethod("astore", "(Ljava/lang/Integer;ZZZZZZZZZLjava/lang/Integer;)Ljava/lang/Integer;")
    .aload_0()
    .astore((byte) 10)
    .aload((byte) 10)
    .areturn();
  }

  private void buildIStore0(ClassFileBuilder builder) {
    // v0 + 5 + v1
    builder.newMethod("istore_0", "(II)I")
      .iload_0()
      .iconst_5()
      .iadd()
      .istore_0()
      .iload_1()
      .iload_0()
      .iadd()
      .ireturn();
  }

  private void buildIStore1(ClassFileBuilder builder) {
    // v0 + 5 + v0
    builder.newMethod("istore_1", "(II)I")
      .iload_0()
      .iconst_5()
      .iadd()
      .istore_1()
      .iload_0()
      .iload_1()
      .iadd()
      .ireturn();
  }

  private void buildIStore2(ClassFileBuilder builder) {
    // v0 + v1
    builder.newMethod("istore_2", "(III)I")
      .iload_0()
      .istore_2()
      .iload_1()
      .iload_2()
      .iadd()
      .ireturn();
  }

  private void buildIStore3(ClassFileBuilder builder) {
    // v0 + v2
    builder.newMethod("istore_3", "(IIII)I")
      .iload_0()
      .istore_3()
      .iload_2()
      .iload_3()
      .iadd()
      .ireturn();
  }

  private void buildLStore0(ClassFileBuilder builder) {
    // v0 + 5 + v1
    builder.newMethod("lstore_0", "(JJ)J")
      .lload_0()
      .iconst_5()
      .i2l()
      .ladd()
      .lstore_0()
      .lload_2()
      .lload_0()
      .ladd()
      .lreturn();
  }

  private void buildLStore1(ClassFileBuilder builder) {
    // v0 + 5 + v0
    builder.newMethod("lstore_1", "(IJ)J")
      .iload_0()
      .iconst_5()
      .iadd()
      .i2l()
      .lstore_1()
      .iload_0()
      .i2l()
      .lload_1()
      .ladd()
      .lreturn();
  }

  private void buildLStore2(ClassFileBuilder builder) {
    // v0 + 1
    builder.newMethod("lstore_2", "(JJ)J")
      .lload_0()
      .lstore_2()
      .lconst_1()
      .lload_2()
      .ladd()
      .lreturn();
  }

  private void buildLStore3(ClassFileBuilder builder) {
    // v0 + v1
    builder.newMethod("lstore_3", "(JI)J")
      .lload_0()
      .lstore_3()
      .iload_2()
      .i2l()
      .lload_3()
      .ladd()
      .lreturn();
  }

  private void buildFStore0(ClassFileBuilder builder) {
    // v0 + 5 + v1
    builder.newMethod("fstore_0", "(FF)F")
      .fload_0()
      .iconst_5()
      .i2f()
      .fadd()
      .fstore_0()
      .fload_1()
      .fload_0()
      .fadd()
      .freturn();
  }

  private void buildFStore1(ClassFileBuilder builder) {
    // v0 + 5 + v0
    builder.newMethod("fstore_1", "(FF)F")
      .fload_0()
      .iconst_5()
      .i2f()
      .fadd()
      .fstore_1()
      .fload_0()
      .fload_1()
      .fadd()
      .freturn();
  }

  private void buildFStore2(ClassFileBuilder builder) {
    // v0 + v1
    builder.newMethod("fstore_2", "(FFF)F")
      .fload_0()
      .fstore_2()
      .fload_1()
      .fload_2()
      .fadd()
      .freturn();
  }

  private void buildFStore3(ClassFileBuilder builder) {
    // v0 + v2
    builder.newMethod("fstore_3", "(FFFF)F")
      .fload_0()
      .fstore_3()
      .fload_2()
      .fload_3()
      .fadd()
      .freturn();
  }

  private void buildDStore0(ClassFileBuilder builder) {
    // v0 + 5 + v1
    builder.newMethod("dstore_0", "(DD)D")
      .dload_0()
      .iconst_5()
      .i2d()
      .dadd()
      .dstore_0()
      .dload_2()
      .dload_0()
      .dadd()
      .dreturn();
  }

  private void buildDStore1(ClassFileBuilder builder) {
    // v0 + 5 + v0
    builder.newMethod("dstore_1", "(FD)D")
      .fload_0()
      .f2d()
      .iconst_5()
      .i2d()
      .dadd()
      .dstore_1()
      .fload_0()
      .f2d()
      .dload_1()
      .dadd()
      .dreturn();
  }

  private void buildDStore2(ClassFileBuilder builder) {
    // v0 + 1
    builder.newMethod("dstore_2", "(DD)D")
      .dload_0()
      .dstore_2()
      .dconst_1()
      .dload_2()
      .dadd()
      .dreturn();
  }

  private void buildDStore3(ClassFileBuilder builder) {
    // v0 + v2
    builder.newMethod("dstore_3", "(DF)D")
      .dload_0()
      .dstore_3()
      .fload_2()
      .f2d()
      .dload_3()
      .dadd()
      .dreturn();
  }

  private void buildAStore0(ClassFileBuilder builder) {
    builder.newMethod("astore_0", "(Ljava/lang/Integer;)Ljava/lang/Integer;")
      .aload_0()
      .aconst_null()
      .astore_0()
      .astore_0()
      .aload_0()
      .areturn();
  }

  private void buildAStore1(ClassFileBuilder builder) {
    builder.newMethod("astore_1", "(Ljava/lang/Integer;Ljava/lang/Integer;)Ljava/lang/Integer;")
      .aload_0()
      .astore_1()
      .aload_1()
      .areturn();
  }

  private void buildAStore2(ClassFileBuilder builder) {
    builder.newMethod("astore_2", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Ljava/lang/Integer;")
      .aload_0()
      .astore_2()
      .aload_2()
      .areturn();
  }

  private void buildAStore3(ClassFileBuilder builder) {
    builder.newMethod("astore_3", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Ljava/lang/Integer;")
      .aload_0()
      .astore_3()
      .aload_3()
      .areturn();
  }

  private void buildPop(ClassFileBuilder builder) {
    builder.newMethod("pop", "()D")
      .dconst_0()
      .iconst_1()
      .pop()
      .dreturn();
  }

  private void buildPop2(ClassFileBuilder builder) {
    builder.newMethod("pop2", "()I")
      .iconst_3()
      .iconst_1()
      .iconst_1()
      .pop2()
      .ireturn();
  }

  private void buildDup(ClassFileBuilder builder) {
    builder.newMethod("dup", "()I")
      .iconst_2()
      .iconst_1()
      .dup()
      .pop2()
      .ireturn();
  }

  private void buildDupX1(ClassFileBuilder builder) {
    builder.newMethod("dup_x1", "()I")
      .iconst_2()
      .iconst_1()
      .dup_x1()
      .pop2()
      .ireturn();
  }

  private void buildDupX2(ClassFileBuilder builder) {
    builder.newMethod("dup_x2", "()I")
      .iconst_3()
      .iconst_2()
      .iconst_1()
      .dup_x2()
      .pop2()
      .pop()
      .ireturn();
  }

  private void buildDup2(ClassFileBuilder builder) {
    builder.newMethod("dup2", "()D")
      .dconst_1()
      .dup2()
      .dadd()
      .dreturn();
  }

  private void buildDup2X1(ClassFileBuilder builder) {
    builder.newMethod("dup2_x1", "()D")
      .iconst_3()
      .dconst_1()
      .dup2_x1()
      .pop2()
      .pop()
      .dreturn();
  }

  private void buildDup2X2(ClassFileBuilder builder) {
    builder.newMethod("dup2_x2", "()D")
      .dconst_0()
      .dconst_1()
      .dup2_x2()
      .pop2()
      .pop2()
      .dreturn();
  }

  private void buildSwap(ClassFileBuilder builder) {
    builder.newMethod("swap", "()I")
    .iconst_1()
    .iconst_2()
    .swap()
    .ireturn();
  }

  private void buildIAdd(ClassFileBuilder builder) {
    builder.newMethod("iadd", "(II)I")
      .iload_0()
      .iload_1()
      .iadd()
      .ireturn();
  }

  private void buildLAdd(ClassFileBuilder builder) {
    builder.newMethod("ladd", "(JJ)J")
      .lload_0()
      .lload_2()
      .ladd()
      .lreturn();
  }

  private void buildFAdd(ClassFileBuilder builder) {
    builder.newMethod("fadd", "(FF)F")
      .fload_0()
      .fload_1()
      .fadd()
      .freturn();
  }

  private void buildDAdd(ClassFileBuilder builder) {
    builder.newMethod("dadd", "(DD)D")
      .dload_0()
      .dload_2()
      .dadd()
      .dreturn();
  }

  private void buildISub(ClassFileBuilder builder) {
    builder.newMethod("isub", "(II)I")
      .iload_0()
      .iload_1()
      .isub()
      .ireturn();
  }

  private void buildLSub(ClassFileBuilder builder) {
    builder.newMethod("lsub", "(JJ)J")
      .lload_0()
      .lload_2()
      .lsub()
      .lreturn();
  }

  private void buildFSub(ClassFileBuilder builder) {
    builder.newMethod("fsub", "(FF)F")
      .fload_0()
      .fload_1()
      .fsub()
      .freturn();
  }

  private void buildDSub(ClassFileBuilder builder) {
    builder.newMethod("dsub", "(DD)D")
      .dload_0()
      .dload_2()
      .dsub()
      .dreturn();
  }

  private void buildIMul(ClassFileBuilder builder) {
    builder.newMethod("imul", "(II)I")
      .iload_0()
      .iload_1()
      .imul()
      .ireturn();
  }

  private void buildLMul(ClassFileBuilder builder) {
    builder.newMethod("lmul", "(JJ)J")
      .lload_0()
      .lload_2()
      .lmul()
      .lreturn();
  }

  private void buildFMul(ClassFileBuilder builder) {
    builder.newMethod("fmul", "(FF)F")
      .fload_0()
      .fload_1()
      .fmul()
      .freturn();
  }

  private void buildDMul(ClassFileBuilder builder) {
    builder.newMethod("dmul", "(DD)D")
      .dload_0()
      .dload_2()
      .dmul()
      .dreturn();
  }

  private void buildIDiv(ClassFileBuilder builder) {
    builder.newMethod("idiv", "(II)I")
      .iload_0()
      .iload_1()
      .idiv()
      .ireturn();
  }

  private void buildLDiv(ClassFileBuilder builder) {
    builder.newMethod("ldiv", "(JJ)J")
      .lload_0()
      .lload_2()
      .ldiv()
      .lreturn();
  }

  private void buildFDiv(ClassFileBuilder builder) {
    builder.newMethod("fdiv", "(FF)F")
      .fload_0()
      .fload_1()
      .fdiv()
      .freturn();
  }

  private void buildDDiv(ClassFileBuilder builder) {
    builder.newMethod("ddiv", "(DD)D")
      .dload_0()
      .dload_2()
      .ddiv()
      .dreturn();
  }

  private void buildIRem(ClassFileBuilder builder) {
    builder.newMethod("irem", "(II)I")
      .iload_0()
      .iload_1()
      .irem()
      .ireturn();
  }

  private void buildLRem(ClassFileBuilder builder) {
    builder.newMethod("lrem", "(JJ)J")
      .lload_0()
      .lload_2()
      .lrem()
      .lreturn();
  }

  private void buildFRem(ClassFileBuilder builder) {
    builder.newMethod("frem", "(FF)F")
      .fload_0()
      .fload_1()
      .frem()
      .freturn();
  }

  private void buildDRem(ClassFileBuilder builder) {
    builder.newMethod("drem", "(DD)D")
      .dload_0()
      .dload_2()
      .drem()
      .dreturn();
  }

  private void buildINeg(ClassFileBuilder builder) {
    builder.newMethod("ineg", "(I)I")
      .iload_0()
      .ineg()
      .ireturn();
  }

  private void buildLNeg(ClassFileBuilder builder) {
    builder.newMethod("lneg", "(J)J")
      .lload_0()
      .lneg()
      .lreturn();
  }

  private void buildFNeg(ClassFileBuilder builder) {
    builder.newMethod("fneg", "(F)F")
      .fload_0()
      .fneg()
      .freturn();
  }

  private void buildDNeg(ClassFileBuilder builder) {
    builder.newMethod("dneg", "(D)D")
      .dload_0()
      .dneg()
      .dreturn();
  }

  private void buildIShl(ClassFileBuilder builder) {
    builder.newMethod("ishl", "(II)I")
      .iload_0()
      .iload_1()
      .ishl()
      .ireturn();
  }

  private void buildLShl(ClassFileBuilder builder) {
    builder.newMethod("lshl", "(JI)J")
      .lload_0()
      .iload_2()
      .lshl()
      .lreturn();
  }

  private void buildIShr(ClassFileBuilder builder) {
    builder.newMethod("ishr", "(II)I")
      .iload_0()
      .iload_1()
      .ishr()
      .ireturn();
  }

  private void buildLShr(ClassFileBuilder builder) {
    builder.newMethod("lshr", "(JI)J")
      .lload_0()
      .iload_2()
      .lshr()
      .lreturn();
  }

  private void buildIUshr(ClassFileBuilder builder) {
    builder.newMethod("iushr", "(II)I")
      .iload_0()
      .iload_1()
      .iushr()
      .ireturn();
  }

  private void buildLUshr(ClassFileBuilder builder) {
    builder.newMethod("lushr", "(JI)J")
      .lload_0()
      .iload_2()
      .lushr()
      .lreturn();
  }

  private void buildIAnd(ClassFileBuilder builder) {
    builder.newMethod("iand", "(II)I")
      .iload_0()
      .iload_1()
      .iand()
      .ireturn();
  }

  private void buildLAnd(ClassFileBuilder builder) {
    builder.newMethod("land", "(JJ)J")
      .lload_0()
      .lload_2()
      .land()
      .lreturn();
  }

  private void buildIOr(ClassFileBuilder builder) {
    builder.newMethod("ior", "(II)I")
      .iload_0()
      .iload_1()
      .ior()
      .ireturn();
  }

  private void buildLOr(ClassFileBuilder builder) {
    builder.newMethod("lor", "(JJ)J")
      .lload_0()
      .lload_2()
      .lor()
      .lreturn();
  }

  private void buildIXor(ClassFileBuilder builder) {
    builder.newMethod("ixor", "(II)I")
      .iload_0()
      .iload_1()
      .ixor()
      .ireturn();
  }

  private void buildLXor(ClassFileBuilder builder) {
    builder.newMethod("lxor", "(JJ)J")
      .lload_0()
      .lload_2()
      .lxor()
      .lreturn();
  }

  private void buildI2L(ClassFileBuilder builder) {
    builder.newMethod("i2l", "(IJ)J")
      .iload_0()
      .i2l()
      .lload_1()
      .ladd()
      .lreturn();
  }

  private void buildI2F(ClassFileBuilder builder) {
    builder.newMethod("i2f", "(II)F")
      .iload_0()
      .i2f()
      .iload_1()
      .i2f()
      .fdiv()
      .freturn();
  }

  private void buildI2D(ClassFileBuilder builder) {
    builder.newMethod("i2d", "(II)D")
      .iload_0()
      .i2d()
      .iload_1()
      .i2d()
      .ddiv()
      .dreturn();
  }

  private void buildL2I(ClassFileBuilder builder) {
    builder.newMethod("l2i", "(J)I")
      .lload_0()
      .l2i()
      .ireturn();
  }

  private void buildL2F(ClassFileBuilder builder) {
    builder.newMethod("l2f", "(JJ)F")
      .lload_0()
      .l2f()
      .lload_2()
      .l2f()
      .fdiv()
      .freturn();
  }

  private void buildL2D(ClassFileBuilder builder) {
    builder.newMethod("l2d", "(JJ)D")
      .lload_0()
      .l2d()
      .lload_2()
      .l2d()
      .ddiv()
      .dreturn();
  }

  private void buildF2I(ClassFileBuilder builder) {
    builder.newMethod("f2i", "(F)I")
      .fload_0()
      .f2i()
      .ireturn();
  }

  private void buildF2L(ClassFileBuilder builder) {
    builder.newMethod("f2l", "(F)J")
      .fload_0()
      .f2l()
      .lreturn();
  }

  private void buildF2D(ClassFileBuilder builder) {
    builder.newMethod("f2d", "(FD)D")
      .fload_0()
      .f2d()
      .dload_1()
      .dadd()
      .dreturn();
  }

  private void buildD2I(ClassFileBuilder builder) {
    builder.newMethod("d2i", "(D)I")
      .dload_0()
      .d2i()
      .ireturn();
  }

  private void buildD2L(ClassFileBuilder builder) {
    builder.newMethod("d2l", "(D)J")
      .dload_0()
      .d2l()
      .lreturn();
  }

  private void buildD2F(ClassFileBuilder builder) {
    builder.newMethod("d2f", "(DF)F")
      .dload_0()
      .d2f()
      .fload_2()
      .fadd()
      .freturn();
  }

  private void buildI2B(ClassFileBuilder builder) {
    builder.newMethod("i2b", "(I)I")
      .iload_0()
      .i2b()
      .ireturn();
  }

  private void buildI2C(ClassFileBuilder builder) {
    builder.newMethod("i2c", "(I)I")
      .iload_0()
      .i2c()
      .ireturn();
  }

  private void buildI2S(ClassFileBuilder builder) {
    builder.newMethod("i2s", "(I)I")
      .iload_0()
      .i2s()
      .ireturn();
  }

  private void buildIStoreLoadUnsigned(ClassFileBuilder builder) {
    builder.newMethod("istore_load_unsigned", "()I")
      .sipush((short)7_687)
      .istore((byte)230)
      .iload((byte)0xe6)
      .ireturn();
  }

  private void buildLStoreLoadUnsigned(ClassFileBuilder builder) {
    builder.newMethod("lstore_load_unsigned", "()J")
      .ldc((long)7_687_000)
      .lstore((byte)199)
      .lload((byte)199)
      .lreturn();
  }

  private void buildFStoreLoadUnsigned(ClassFileBuilder builder) {
    builder.newMethod("fstore_load_unsigned", "()F")
      .ldc(134.89f)
      .fstore((byte)142)
      .fload((byte)142)
      .freturn();
  }

  private void buildDStoreLoadUnsigned(ClassFileBuilder builder) {
    builder.newMethod("dstore_load_unsigned", "()D")
      .ldc(33.33)
      .dstore((byte)210)
      .dload((byte)210)
      .dreturn();
  }

  private void buildAStoreLoadUnsigned(ClassFileBuilder builder) {
    builder.newMethod("astore_load_unsigned", "()Ljava/lang/Object;")
      .aconst_null()
      .astore((byte)175)
      .aload((byte)175)
      .areturn();
  }
}
