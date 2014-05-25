package org.isk.jvmhardcore.pjba;

import org.isk.jvmhardcore.pjba.builder.ClassFileBuilder;
import org.isk.jvmhardcore.pjba.builder.MethodBuilder;
import org.isk.jvmhardcore.pjba.structure.ClassFile;
import org.isk.jvmhardcore.pjba.structure.Field;
import org.isk.jvmhardcore.pjba.structure.Method;
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
    final ClassFileBuilder builder = new ClassFileBuilder(ClassFile.MODIFIER_PUBLIC, fullyQualifiedName);

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
    this.buildIaload(builder);
    this.buildLaload(builder);
    this.buildFaload(builder);
    this.buildDaload(builder);
    this.buildAaload(builder);
    this.buildBaload(builder);
    this.buildCaload(builder);
    this.buildSaload(builder);
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
    this.buildIaStore(builder);
    this.buildLaStore(builder);
    this.buildFaStore(builder);
    this.buildDaStore(builder);
    this.buildAaStore(builder);
    this.buildBaStore(builder);
    this.buildCaStore(builder);
    this.buildSaStore(builder);
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
    this.buildIinc(builder);
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
    this.buildLCmp(builder);
    this.buildFCmpl(builder);
    this.buildFCmpg(builder);
    this.buildDCmpl(builder);
    this.buildDCmpg(builder);
    this.buildIfeq(builder);
    this.buildIfne(builder);
    this.buildIflt(builder);
    this.buildIfge(builder);
    this.buildIfgt(builder);
    this.buildIfle(builder);
    this.buildIfIcmpeq(builder);
    this.buildIfIcmpne(builder);
    this.buildIfIcmplt(builder);
    this.buildIfIcmpge(builder);
    this.buildIfIcmpgt(builder);
    this.buildIfIcmple(builder);
    this.buildIfAcmpeq(builder);
    this.buildIfAcmpne(builder);
    this.buildGotoBefore(builder);
    this.buildGotoAfter(builder);
    this.buildTableswitch(builder);
    this.buildLookupswitch(builder);
    this.buildGotoWBefore(builder);
    this.buildGotoWAfter(builder);
    this.buildGetstatic(builder);
    this.buildPutstatic(builder, fullyQualifiedName);
    this.buildInvokestatic(builder);
    this.buildNewarrayOfBooleans(builder);
    this.buildNewarrayOfChars(builder);
    this.buildNewarrayOfFloats(builder);
    this.buildNewarrayOfDoubles(builder);
    this.buildNewarrayOfBytes(builder);
    this.buildNewarrayOfShorts(builder);
    this.buildNewarrayOfInts(builder);
    this.buildNewarrayOfLongs(builder);
    this.buildAnewarray(builder);
    this.buildArraylength(builder);
    this.buildWideIinc(builder);
    this.buildWideIStoreLoad(builder);
    this.buildWideLStoreLoad(builder);
    this.buildWideFStoreLoad(builder);
    this.buildWideDStoreLoad(builder);
    this.buildWideAStoreLoad(builder);
    this.buildMultianewarray(builder);
    this.buildIfnull(builder);
    this.buildIfnonnull(builder);

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
    this.buildConstantsTester(builder, fullyQualifiedName);
    this.buildStaticBlockTester(builder, fullyQualifiedName);

    return builder.build();
  }

  // To test ldc_w and with short indexes instead of byte
  private void addDummyConstants(ClassFileBuilder builder) {
    final String s = "abcdefghijklmnopqrstuvwxyz";

    final MethodBuilder mBuilder = builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "dummy", "()V");

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
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "nop", "()V")
      .nop()
      .return_();
  }

  private void buildAConstNull(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "aconst_null", "()Ljava/lang/Object;")
      .aconst_null()
      .areturn();
  }

  private void buildIConstM1(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "iconst_m1", "()I")
      .iconst_m1()
      .ireturn();
  }

  private void buildIConst0(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "iconst_0", "()I")
      .iconst_0()
      .ireturn();
  }

  private void buildIConst1(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "iconst_1", "()I")
      .iconst_1()
      .ireturn();
  }

  private void buildIConst2(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "iconst_2", "()I")
      .iconst_2()
      .ireturn();
  }

  private void buildIConst3(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "iconst_3", "()I")
      .iconst_3()
      .ireturn();
  }

  private void buildIConst4(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "iconst_4", "()I")
      .iconst_4()
      .ireturn();
  }

  private void buildIConst5(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "iconst_5", "()I")
      .iconst_5()
      .ireturn();
  }

  private void buildLConst0(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "lconst_0", "()J")
      .lconst_0()
      .lreturn();
  }

  private void buildLConst1(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "lconst_1", "()J")
      .lconst_1()
      .lreturn();
  }

  private void buildFConst0(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "fconst_0", "()F")
      .fconst_0()
      .freturn();
  }

  private void buildFConst1(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "fconst_1", "()F")
      .fconst_1()
      .freturn();
  }

  private void buildFConst2(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "fconst_2", "()F")
      .fconst_2()
      .freturn();
  }

  private void buildDConst0(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "dconst_0", "()D")
      .dconst_0()
      .dreturn();
  }

  private void buildDConst1(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "dconst_1", "()D")
      .dconst_1()
      .dreturn();
  }

  private void buildBipush(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "bipush", "()I")
      .bipush((byte)125)
      .ireturn();
  }

  private void buildSipush(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "sipush", "()I")
      .sipush((short)5_396)
      .ireturn();
  }

  private void buildLdc(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "ldc_String", "()Ljava/lang/String;")
      .ldc("Hello World")
      .areturn();

    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "ldc_int", "()I")
      .ldc(167_980_564)
      .ireturn();

    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "ldc_float", "()F")
      .ldc(3.5f)
      .freturn();
  }

  private void buildLdcW(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "ldc_w_String", "()Ljava/lang/String;")
    .ldc("Hello World Wide...")
    .areturn();

    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "ldc_w_int", "()I")
      .ldc(999_999_999)
      .ireturn();

    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "ldc_w_float", "()F")
      .ldc(999.9999f)
      .freturn();
  }

  private void buildLdc2W(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "ldc_long", "()J")
      .ldc(167_980_564_900l)
      .lreturn();

    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "ldc_double", "()D")
      .ldc(3.578_978_979)
      .dreturn();
  }

  private void buildILoad(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "iload", "(ZZZZZZZZZZI)I")
      .iload((byte)10)
      .ireturn();
  }

  private void buildLLoad(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "lload", "(ZZZZZZZZZZJ)J")
      .lload((byte)10)
      .lreturn();
  }

  private void buildFLoad(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "fload", "(ZZZZZZZZZZF)F")
      .fload((byte)10)
      .freturn();
  }

  private void buildDLoad(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "dload", "(ZZZZZZZZZZD)D")
      .dload((byte)10)
      .dreturn();
  }

  private void buildALoad(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "aload", "(ZZZZZZZZZZLjava/lang/Integer;)Ljava/lang/Integer;")
      .aload((byte)10)
      .areturn();
  }

  private void buildILoad0(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "iload_0", "(I)I")
      .iload_0()
      .ireturn();
  }

  private void buildILoad1(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "iload_1", "(ZI)I")
      .iload_1()
      .ireturn();
  }

  private void buildILoad2(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "iload_2", "(ZZI)I")
      .iload_2()
      .ireturn();
  }

  private void buildILoad3(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "iload_3", "(ZZZI)I")
      .iload_3()
      .ireturn();
  }

  private void buildLLoad0(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "lload_0", "(J)J")
      .lload_0()
      .lreturn();
  }

  private void buildLLoad1(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "lload_1", "(ZJ)J")
      .lload_1()
      .lreturn();
  }

  private void buildLLoad2(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "lload_2", "(ZZJ)J")
      .lload_2()
      .lreturn();
  }

  private void buildLLoad3(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "lload_3", "(ZZZJ)J")
      .lload_3()
      .lreturn();
  }

  private void buildFLoad0(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "fload_0", "(F)F")
      .fload_0()
      .freturn();
  }

  private void buildFLoad1(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "fload_1", "(ZF)F")
      .fload_1()
      .freturn();
  }

  private void buildFLoad2(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "fload_2", "(ZZF)F")
      .fload_2()
      .freturn();
  }

  private void buildFLoad3(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "fload_3", "(ZZZF)F")
      .fload_3()
      .freturn();
  }

  private void buildDLoad0(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "dload_0", "(D)D")
      .dload_0()
      .dreturn();
  }

  private void buildDLoad1(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "dload_1", "(ZD)D")
      .dload_1()
      .dreturn();
  }

  private void buildDLoad2(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "dload_2", "(ZZD)D")
      .dload_2()
      .dreturn();
  }

  private void buildDLoad3(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "dload_3", "(ZZZD)D")
      .dload_3()
      .dreturn();
  }

  private void buildALoad0(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "aload_0", "(Ljava/lang/Integer;)Ljava/lang/Integer;")
      .aload_0()
      .areturn();
  }

  private void buildALoad1(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "aload_1", "(ZLjava/lang/Integer;)Ljava/lang/Integer;")
      .aload_1()
      .areturn();
  }

  private void buildALoad2(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "aload_2", "(ZZLjava/lang/Integer;)Ljava/lang/Integer;")
      .aload_2()
      .areturn();
  }

  private void buildALoad3(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "aload_3", "(ZZZLjava/lang/Integer;)Ljava/lang/Integer;")
      .aload_3()
      .areturn();
  }

  private void buildIaload(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "iaload", "([II)I")
      .aload_0()
      .iload_1()
      .iaload()
      .ireturn();
  }

  private void buildLaload(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "laload", "([JI)J")
      .aload_0()
      .iload_1()
      .laload()
      .lreturn();
  }

  private void buildFaload(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "faload", "([FI)F")
      .aload_0()
      .iload_1()
      .faload()
      .freturn();
  }

  private void buildDaload(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "daload", "([DI)D")
      .aload_0()
      .iload_1()
      .daload()
      .dreturn();
  }

  private void buildAaload(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "aaload", "([Ljava/lang/Object;I)Ljava/lang/Object;")
      .aload_0()
      .iload_1()
      .aaload()
      .areturn();
  }

  private void buildBaload(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "baload", "([BI)B")
      .aload_0()
      .iload_1()
      .baload()
      .ireturn();
  }

  private void buildCaload(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "caload", "([CI)C")
      .aload_0()
      .iload_1()
      .caload()
      .ireturn();
  }

  private void buildSaload(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "saload", "([SI)S")
      .aload_0()
      .iload_1()
      .saload()
      .ireturn();
  }

  private void buildIStore(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "istore", "(IZZZZZZZZZI)I")
      .iload_0()
      .istore((byte) 10)
      .iload((byte) 10)
      .ireturn();
  }

  private void buildLStore(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "lstore", "(JZZZZZZZZJ)J")
      .lload_0()
      .lstore((byte) 10)
      .lload((byte) 10)
      .lreturn();
  }

  private void buildFStore(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "fstore", "(FZZZZZZZZZF)F")
      .fload_0()
      .fstore((byte) 10)
      .fload((byte) 10)
      .freturn();
  }

  private void buildDStore(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "dstore", "(DZZZZZZZZD)D")
    .dload_0()
    .dstore((byte) 10)
    .dload((byte) 10)
    .dreturn();
  }

  private void buildAStore(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "astore", "(Ljava/lang/Integer;ZZZZZZZZZLjava/lang/Integer;)Ljava/lang/Integer;")
    .aload_0()
    .astore((byte) 10)
    .aload((byte) 10)
    .areturn();
  }

  private void buildIStore0(ClassFileBuilder builder) {
    // v0 + 5 + v1
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "istore_0", "(II)I")
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
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "istore_1", "(II)I")
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
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "istore_2", "(III)I")
      .iload_0()
      .istore_2()
      .iload_1()
      .iload_2()
      .iadd()
      .ireturn();
  }

  private void buildIStore3(ClassFileBuilder builder) {
    // v0 + v2
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "istore_3", "(IIII)I")
      .iload_0()
      .istore_3()
      .iload_2()
      .iload_3()
      .iadd()
      .ireturn();
  }

  private void buildLStore0(ClassFileBuilder builder) {
    // v0 + 5 + v1
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "lstore_0", "(JJ)J")
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
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "lstore_1", "(IJ)J")
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
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "lstore_2", "(JJ)J")
      .lload_0()
      .lstore_2()
      .lconst_1()
      .lload_2()
      .ladd()
      .lreturn();
  }

  private void buildLStore3(ClassFileBuilder builder) {
    // v0 + v1
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "lstore_3", "(JI)J")
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
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "fstore_0", "(FF)F")
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
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "fstore_1", "(FF)F")
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
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "fstore_2", "(FFF)F")
      .fload_0()
      .fstore_2()
      .fload_1()
      .fload_2()
      .fadd()
      .freturn();
  }

  private void buildFStore3(ClassFileBuilder builder) {
    // v0 + v2
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "fstore_3", "(FFFF)F")
      .fload_0()
      .fstore_3()
      .fload_2()
      .fload_3()
      .fadd()
      .freturn();
  }

  private void buildDStore0(ClassFileBuilder builder) {
    // v0 + 5 + v1
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "dstore_0", "(DD)D")
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
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "dstore_1", "(FD)D")
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
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "dstore_2", "(DD)D")
      .dload_0()
      .dstore_2()
      .dconst_1()
      .dload_2()
      .dadd()
      .dreturn();
  }

  private void buildDStore3(ClassFileBuilder builder) {
    // v0 + v2
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "dstore_3", "(DF)D")
      .dload_0()
      .dstore_3()
      .fload_2()
      .f2d()
      .dload_3()
      .dadd()
      .dreturn();
  }

  private void buildAStore0(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "astore_0", "(Ljava/lang/Integer;)Ljava/lang/Integer;")
      .aload_0()
      .aconst_null()
      .astore_0()
      .astore_0()
      .aload_0()
      .areturn();
  }

  private void buildAStore1(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "astore_1", "(Ljava/lang/Integer;Ljava/lang/Integer;)Ljava/lang/Integer;")
      .aload_0()
      .astore_1()
      .aload_1()
      .areturn();
  }

  private void buildAStore2(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "astore_2", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Ljava/lang/Integer;")
      .aload_0()
      .astore_2()
      .aload_2()
      .areturn();
  }

  private void buildAStore3(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "astore_3", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Ljava/lang/Integer;")
      .aload_0()
      .astore_3()
      .aload_3()
      .areturn();
  }

  private void buildIaStore(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "iastore", "([III)V")
      .aload_0()
      .iload_1()
      .iload_2()
      .iastore()
      .return_();
  }

  private void buildLaStore(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "lastore", "([JIJ)V")
      .aload_0()
      .iload_1()
      .lload_2()
      .lastore()
      .return_();
  }

  private void buildFaStore(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "fastore", "([FIF)V")
      .aload_0()
      .iload_1()
      .fload_2()
      .fastore()
      .return_();
  }

  private void buildDaStore(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "dastore", "([DID)V")
      .aload_0()
      .iload_1()
      .dload_2()
      .dastore()
      .return_();
  }

  private void buildAaStore(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "aastore", "([Ljava/lang/String;ILjava/lang/String;)V")
      .aload_0()
      .iload_1()
      .aload_2()
      .aastore()
      .return_();
  }

  private void buildBaStore(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "bastore", "([BIB)V")
      .aload_0()
      .iload_1()
      .iload_2()
      .bastore()
      .return_();
  }

  private void buildCaStore(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "castore", "([CIC)V")
      .aload_0()
      .iload_1()
      .iload_2()
      .castore()
      .return_();
  }

  private void buildSaStore(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "sastore", "([SIS)V")
      .aload_0()
      .iload_1()
      .iload_2()
      .sastore()
      .return_();
  }

  private void buildPop(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "pop", "()D")
      .dconst_0()
      .iconst_1()
      .pop()
      .dreturn();
  }

  private void buildPop2(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "pop2", "()I")
      .iconst_3()
      .iconst_1()
      .iconst_1()
      .pop2()
      .ireturn();
  }

  private void buildDup(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "dup", "()I")
      .iconst_2()
      .iconst_1()
      .dup()
      .pop2()
      .ireturn();
  }

  private void buildDupX1(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "dup_x1", "()I")
      .iconst_2()
      .iconst_1()
      .dup_x1()
      .pop2()
      .ireturn();
  }

  private void buildDupX2(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "dup_x2", "()I")
      .iconst_3()
      .iconst_2()
      .iconst_1()
      .dup_x2()
      .pop2()
      .pop()
      .ireturn();
  }

  private void buildDup2(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "dup2", "()D")
      .dconst_1()
      .dup2()
      .dadd()
      .dreturn();
  }

  private void buildDup2X1(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "dup2_x1", "()D")
      .iconst_3()
      .dconst_1()
      .dup2_x1()
      .pop2()
      .pop()
      .dreturn();
  }

  private void buildDup2X2(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "dup2_x2", "()D")
      .dconst_0()
      .dconst_1()
      .dup2_x2()
      .pop2()
      .pop2()
      .dreturn();
  }

  private void buildSwap(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "swap", "()I")
    .iconst_1()
    .iconst_2()
    .swap()
    .ireturn();
  }

  private void buildIAdd(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "iadd", "(II)I")
      .iload_0()
      .iload_1()
      .iadd()
      .ireturn();
  }

  private void buildLAdd(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "ladd", "(JJ)J")
      .lload_0()
      .lload_2()
      .ladd()
      .lreturn();
  }

  private void buildFAdd(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "fadd", "(FF)F")
      .fload_0()
      .fload_1()
      .fadd()
      .freturn();
  }

  private void buildDAdd(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "dadd", "(DD)D")
      .dload_0()
      .dload_2()
      .dadd()
      .dreturn();
  }

  private void buildISub(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "isub", "(II)I")
      .iload_0()
      .iload_1()
      .isub()
      .ireturn();
  }

  private void buildLSub(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "lsub", "(JJ)J")
      .lload_0()
      .lload_2()
      .lsub()
      .lreturn();
  }

  private void buildFSub(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "fsub", "(FF)F")
      .fload_0()
      .fload_1()
      .fsub()
      .freturn();
  }

  private void buildDSub(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "dsub", "(DD)D")
      .dload_0()
      .dload_2()
      .dsub()
      .dreturn();
  }

  private void buildIMul(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "imul", "(II)I")
      .iload_0()
      .iload_1()
      .imul()
      .ireturn();
  }

  private void buildLMul(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "lmul", "(JJ)J")
      .lload_0()
      .lload_2()
      .lmul()
      .lreturn();
  }

  private void buildFMul(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "fmul", "(FF)F")
      .fload_0()
      .fload_1()
      .fmul()
      .freturn();
  }

  private void buildDMul(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "dmul", "(DD)D")
      .dload_0()
      .dload_2()
      .dmul()
      .dreturn();
  }

  private void buildIDiv(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "idiv", "(II)I")
      .iload_0()
      .iload_1()
      .idiv()
      .ireturn();
  }

  private void buildLDiv(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "ldiv", "(JJ)J")
      .lload_0()
      .lload_2()
      .ldiv()
      .lreturn();
  }

  private void buildFDiv(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "fdiv", "(FF)F")
      .fload_0()
      .fload_1()
      .fdiv()
      .freturn();
  }

  private void buildDDiv(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "ddiv", "(DD)D")
      .dload_0()
      .dload_2()
      .ddiv()
      .dreturn();
  }

  private void buildIRem(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "irem", "(II)I")
      .iload_0()
      .iload_1()
      .irem()
      .ireturn();
  }

  private void buildLRem(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "lrem", "(JJ)J")
      .lload_0()
      .lload_2()
      .lrem()
      .lreturn();
  }

  private void buildFRem(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "frem", "(FF)F")
      .fload_0()
      .fload_1()
      .frem()
      .freturn();
  }

  private void buildDRem(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "drem", "(DD)D")
      .dload_0()
      .dload_2()
      .drem()
      .dreturn();
  }

  private void buildINeg(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "ineg", "(I)I")
      .iload_0()
      .ineg()
      .ireturn();
  }

  private void buildLNeg(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "lneg", "(J)J")
      .lload_0()
      .lneg()
      .lreturn();
  }

  private void buildFNeg(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "fneg", "(F)F")
      .fload_0()
      .fneg()
      .freturn();
  }

  private void buildDNeg(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "dneg", "(D)D")
      .dload_0()
      .dneg()
      .dreturn();
  }

  private void buildIShl(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "ishl", "(II)I")
      .iload_0()
      .iload_1()
      .ishl()
      .ireturn();
  }

  private void buildLShl(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "lshl", "(JI)J")
      .lload_0()
      .iload_2()
      .lshl()
      .lreturn();
  }

  private void buildIShr(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "ishr", "(II)I")
      .iload_0()
      .iload_1()
      .ishr()
      .ireturn();
  }

  private void buildLShr(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "lshr", "(JI)J")
      .lload_0()
      .iload_2()
      .lshr()
      .lreturn();
  }

  private void buildIUshr(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "iushr", "(II)I")
      .iload_0()
      .iload_1()
      .iushr()
      .ireturn();
  }

  private void buildLUshr(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "lushr", "(JI)J")
      .lload_0()
      .iload_2()
      .lushr()
      .lreturn();
  }

  private void buildIAnd(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "iand", "(II)I")
      .iload_0()
      .iload_1()
      .iand()
      .ireturn();
  }

  private void buildLAnd(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "land", "(JJ)J")
      .lload_0()
      .lload_2()
      .land()
      .lreturn();
  }

  private void buildIOr(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "ior", "(II)I")
      .iload_0()
      .iload_1()
      .ior()
      .ireturn();
  }

  private void buildLOr(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "lor", "(JJ)J")
      .lload_0()
      .lload_2()
      .lor()
      .lreturn();
  }

  private void buildIXor(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "ixor", "(II)I")
      .iload_0()
      .iload_1()
      .ixor()
      .ireturn();
  }

  private void buildLXor(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "lxor", "(JJ)J")
      .lload_0()
      .lload_2()
      .lxor()
      .lreturn();
  }

  private void buildIinc(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "iinc", "(I)I")
      .iinc((byte)0, (byte)-5)
      .iload_0()
      .ireturn();
  }

  private void buildI2L(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "i2l", "(IJ)J")
      .iload_0()
      .i2l()
      .lload_1()
      .ladd()
      .lreturn();
  }

  private void buildI2F(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "i2f", "(II)F")
      .iload_0()
      .i2f()
      .iload_1()
      .i2f()
      .fdiv()
      .freturn();
  }

  private void buildI2D(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "i2d", "(II)D")
      .iload_0()
      .i2d()
      .iload_1()
      .i2d()
      .ddiv()
      .dreturn();
  }

  private void buildL2I(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "l2i", "(J)I")
      .lload_0()
      .l2i()
      .ireturn();
  }

  private void buildL2F(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "l2f", "(JJ)F")
      .lload_0()
      .l2f()
      .lload_2()
      .l2f()
      .fdiv()
      .freturn();
  }

  private void buildL2D(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "l2d", "(JJ)D")
      .lload_0()
      .l2d()
      .lload_2()
      .l2d()
      .ddiv()
      .dreturn();
  }

  private void buildF2I(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "f2i", "(F)I")
      .fload_0()
      .f2i()
      .ireturn();
  }

  private void buildF2L(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "f2l", "(F)J")
      .fload_0()
      .f2l()
      .lreturn();
  }

  private void buildF2D(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "f2d", "(FD)D")
      .fload_0()
      .f2d()
      .dload_1()
      .dadd()
      .dreturn();
  }

  private void buildD2I(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "d2i", "(D)I")
      .dload_0()
      .d2i()
      .ireturn();
  }

  private void buildD2L(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "d2l", "(D)J")
      .dload_0()
      .d2l()
      .lreturn();
  }

  private void buildD2F(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "d2f", "(DF)F")
      .dload_0()
      .d2f()
      .fload_2()
      .fadd()
      .freturn();
  }

  private void buildI2B(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "i2b", "(I)I")
      .iload_0()
      .i2b()
      .ireturn();
  }

  private void buildI2C(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "i2c", "(I)I")
      .iload_0()
      .i2c()
      .ireturn();
  }

  private void buildI2S(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "i2s", "(I)I")
      .iload_0()
      .i2s()
      .ireturn();
  }

  private void buildLCmp(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "lcmp", "(JJ)I")
      .lload_0()
      .lload_2()
      .lcmp()
      .ireturn();
  }

  private void buildFCmpl(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "fcmpl", "(FF)I")
      .fload_0()
      .fload_1()
      .fcmpl()
      .ireturn();
  }

  private void buildFCmpg(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "fcmpg", "(FF)I")
      .fload_0()
      .fload_1()
      .fcmpg()
      .ireturn();
  }

  private void buildDCmpl(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "dcmpl", "(DD)I")
      .dload_0()
      .dload_2()
      .dcmpl()
      .ireturn();
  }

  private void buildDCmpg(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "dcmpg", "(DD)I")
      .dload_0()
      .dload_2()
      .dcmpg()
      .ireturn();
  }

  private void buildIfeq(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "ifeq", "(I)Z")
      .iload_0()
      .ifeq("ok")
      .iconst_0()
      .ireturn()
      .label("ok")
      .iconst_1()
      .ireturn();
  }

  private void buildIfne(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "ifne", "(I)Z")
      .iload_0()
      .ifne("ok")
      .iconst_0()
      .ireturn()
      .label("ok")
      .iconst_1()
      .ireturn();
  }

  private void buildIflt(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "iflt", "(I)Z")
      .iload_0()
      .iflt("ok")
      .iconst_0()
      .ireturn()
      .label("ok")
      .iconst_1()
      .ireturn();
  }

  private void buildIfge(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "ifge", "(I)Z")
      .iload_0()
      .ifge("ok")
      .iconst_0()
      .ireturn()
      .label("ok")
      .iconst_1()
      .ireturn();
  }

  private void buildIfgt(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "ifgt", "(I)Z")
      .iload_0()
      .ifgt("ok")
      .iconst_0()
      .ireturn()
      .label("ok")
      .iconst_1()
      .ireturn();
  }

  private void buildIfle(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "ifle", "(I)Z")
      .iload_0()
      .ifle("ok")
      .iconst_0()
      .ireturn()
      .label("ok")
      .iconst_1()
      .ireturn();
  }

  private void buildIfIcmpeq(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "if_icmpeq", "(II)Z")
      .iload_0()
      .iload_1()
      .if_icmpeq("ok")
      .iconst_0()
      .ireturn()
      .label("ok")
      .iconst_1()
      .ireturn();
  }

  private void buildIfIcmpne(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "if_icmpne", "(II)Z")
      .iload_0()
      .iload_1()
      .if_icmpne("ok")
      .iconst_0()
      .ireturn()
      .label("ok")
      .iconst_1()
      .ireturn();
  }

  private void buildIfIcmplt(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "if_icmplt", "(II)Z")
      .iload_0()
      .iload_1()
      .if_icmplt("ok")
      .iconst_0()
      .ireturn()
      .label("ok")
      .iconst_1()
      .ireturn();
  }

  private void buildIfIcmpge(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "if_icmpge", "(II)Z")
      .iload_0()
      .iload_1()
      .if_icmpge("ok")
      .iconst_0()
      .ireturn()
      .label("ok")
      .iconst_1()
      .ireturn();
  }

  private void buildIfIcmpgt(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "if_icmpgt", "(II)Z")
      .iload_0()
      .iload_1()
      .if_icmpgt("ok")
      .iconst_0()
      .ireturn()
      .label("ok")
      .iconst_1()
      .ireturn();
  }

  private void buildIfIcmple(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "if_icmple", "(II)Z")
      .iload_0()
      .iload_1()
      .if_icmple("ok")
      .iconst_0()
      .ireturn()
      .label("ok")
      .iconst_1()
      .ireturn();
  }

  private void buildIfAcmpeq(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "if_acmpeq", "(Ljava/lang/Object;Ljava/lang/Object;)Z")
      .aload_0()
      .aload_1()
      .if_acmpeq("ok")
      .iconst_0()
      .ireturn()
      .label("ok")
      .iconst_1()
      .ireturn();
  }

  private void buildIfAcmpne(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "if_acmpne", "(Ljava/lang/Object;Ljava/lang/Object;)Z")
      .aload_0()
      .aload_1()
      .if_acmpne("ok")
      .iconst_0()
      .ireturn()
      .label("ok")
      .iconst_1()
      .ireturn();
  }

  private void buildGotoBefore(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "goto_before", "()I")
      .iconst_0()
      .istore_0()
      .iconst_0()
      .istore_1()
      .label("loop")
      .iload_1()
      .bipush((byte) 10)
      .if_icmpge("outLoop")
      .iload_0()
      .iload_1()
      .iadd()
      .istore_0()
      .iinc((short) 1, (short) 1)
      .goto_("loop")
      .label("outLoop")
      .iload_0()
      .ireturn();
  }

  private void buildGotoAfter(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "goto_after", "(II)Z")
      .iload_0()
      .iload_1()
      .if_icmpge("ko")
      .iconst_1()
      .goto_("return")
      .label("ko")
      .iconst_0()
      .label("return")
      .ireturn();
  }

  private void buildTableswitch(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "tableswitch", "(I)I")
      .iload_0()
      .tableswitch("default", 5, 7)
        .offset("label_5")
        .offset("label_6")
        .offset("label_7")
      .end()
      .label("label_5")
      .bipush((byte) 10)
      .ireturn()
      .label("label_6")
      .bipush((byte) 12)
      .ireturn()
      .label("label_7")
      .bipush((byte) 14)
      .ireturn()
      .label("default")
      .bipush((byte) 100)
      .ireturn();
  }

  private void buildLookupswitch(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "lookupswitch", "(I)I")
      .iload_0()
      .lookupswitch("default", 3)
        .matchOffset(5, "label_5")
        .matchOffset(10, "label_10")
        .matchOffset(15, "label_15")
      .end()
      .label("label_5")
      .bipush((byte) 10)
      .ireturn()
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

  private void buildIStoreLoadUnsigned(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "istore_load_unsigned", "()I")
      .sipush((short)7_687)
      .istore((byte)230)
      .iload((byte)0xe6)
      .ireturn();
  }

  private void buildLStoreLoadUnsigned(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "lstore_load_unsigned", "()J")
      .ldc((long)7_687_000)
      .lstore((byte)199)
      .lload((byte)199)
      .lreturn();
  }

  private void buildFStoreLoadUnsigned(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "fstore_load_unsigned", "()F")
      .ldc(134.89f)
      .fstore((byte)142)
      .fload((byte)142)
      .freturn();
  }

  private void buildDStoreLoadUnsigned(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "dstore_load_unsigned", "()D")
      .ldc(33.33)
      .dstore((byte)210)
      .dload((byte)210)
      .dreturn();
  }

  private void buildAStoreLoadUnsigned(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "astore_load_unsigned", "()Ljava/lang/Object;")
      .aconst_null()
      .astore((byte)175)
      .aload((byte)175)
      .areturn();
  }

  private void buildWideIinc(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "wide_iinc", "(I)J")
      .iinc((short)0, (short)-30_000)
      .iconst_5()
      .istore((short)10_000)
      .iinc((short)10_000, (short)1)
      .iload((short)10_000)
      .i2l()
      .iload_0()
      .i2l()
      .bipush((byte)32)
      .lshl()
      .lor()
      .lreturn();
  }

  private void buildWideIStoreLoad(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "wide_istore_iload", "()I")
      .iconst_3()
      .istore((short)0xffff)
      .iload((short)0xffff)
      .ireturn();
  }

  private void buildWideLStoreLoad(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "wide_lstore_lload", "()J")
      .lconst_1()
      .lstore((short)354)
      .lload((short)354)
      .lreturn();
  }

  private void buildWideFStoreLoad(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "wide_fstore_fload", "()F")
      .fconst_1()
      .fstore((short)14_999)
      .fload((short)14_999)
      .freturn();
  }

  private void buildWideDStoreLoad(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "wide_dstore_dload", "()D")
      .dconst_1()
      .dstore((short)7_908)
      .dload((short)7_908)
      .dreturn();
  }

  private void buildWideAStoreLoad(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "wide_astore_aload", "()Ljava/lang/Object;")
      .aconst_null()
      .astore((short)-24_365)
      .aload((short)-24_365)
      .areturn();
  }

  private void buildGetstatic(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "getstatic", "()I")
      .getstatic("java/lang/Integer", "MAX_VALUE", "I")
      .ireturn();
  }

  private void buildPutstatic(ClassFileBuilder builder, String fullyQualifiedName) {
    builder.newField(Field.MODIFIER_STATIC, "PUTSTATIC_FIELD", "I");
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "putstatic", "(I)I")
      .iload_0()
      .putstatic(fullyQualifiedName, "PUTSTATIC_FIELD", "I")
      .getstatic(fullyQualifiedName, "PUTSTATIC_FIELD", "I")
      .ireturn();
  }

  private void buildInvokestatic(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "invokestatic", "(III)I")
      .iload_0()
      .iload_1()
      .invokestatic("java/lang/Math", "max", "(II)I")
      .iload_2()
      .iadd()
      .ireturn();
  }

  private void buildNewarrayOfBooleans(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "getNewarrayOfBooleans", "(I)[Z")
      .iload_0()
      .newarray(MethodBuilder.ArrayType.BOOLEAN)
      .areturn();
  }

  private void buildNewarrayOfChars(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "getNewarrayOfChars", "(I)[C")
      .iload_0()
      .newarray(MethodBuilder.ArrayType.CHAR)
      .areturn();
  }

  private void buildNewarrayOfFloats(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "getNewarrayOfFloats", "(I)[F")
      .iload_0()
      .newarray(MethodBuilder.ArrayType.FLOAT)
      .areturn();
  }

  private void buildNewarrayOfDoubles(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "getNewarrayOfDoubles", "(I)[D")
      .iload_0()
      .newarray(MethodBuilder.ArrayType.DOUBLE)
      .areturn();
  }

  private void buildNewarrayOfBytes(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "getNewarrayOfBytes", "(I)[B")
      .iload_0()
      .newarray(MethodBuilder.ArrayType.BYTE)
      .areturn();
  }

  private void buildNewarrayOfShorts(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "getNewarrayOfShorts", "(I)[S")
      .iload_0()
      .newarray(MethodBuilder.ArrayType.SHORT)
      .areturn();
  }

  private void buildNewarrayOfInts(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "getNewarrayOfInts", "(I)[I")
      .iload_0()
      .newarray(MethodBuilder.ArrayType.INT)
      .areturn();
  }

  private void buildNewarrayOfLongs(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "getNewarrayOfLongs", "(I)[J")
      .iload_0()
      .newarray(MethodBuilder.ArrayType.LONG)
      .areturn();
  }
  
  private void buildAnewarray(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "getNewarrayOfStrings", "(I)[Ljava/lang/String;")
      .iload_0()
      .anewarray("java/lang/String")
      .areturn();
  }

  private void buildArraylength(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "arraylength", "([I)I")
      .aload_0()
      .arraylength()
      .ireturn();
  }

  private void buildMultianewarray(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "multianewarray", "(II)[[Ljava/lang/Object;")
      .iload_0()
      .iload_1()
      .multianewarray("[[Ljava/lang/Object;", (byte) 2)
      .areturn();
  }

  private void buildIfnull(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "ifnull", "(Ljava/lang/Object;)Z")
      .aload_0()
      .ifnull("ok")
      .iconst_0()
      .ireturn()
      .label("ok")
      .iconst_1()
      .ireturn();
  }

  private void buildIfnonnull(ClassFileBuilder builder) {
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "ifnonnull", "(Ljava/lang/Object;)Z")
      .aload_0()
      .ifnonnull("ok")
      .iconst_0()
      .ireturn()
      .label("ok")
      .iconst_1()
      .ireturn();
  }
  
  private void buildGotoWBefore(ClassFileBuilder builder) {
    final MethodBuilder mb = builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "goto_w_before", "()I", false)
      .iconst_0()
      .istore_0()
      .iconst_0()
      .istore_1()
      .label("loop")
      .iload_1()
      .bipush((byte) 10)
      .if_icmplt("ok")
      .goto_("outLoop")
      .label("ok");

    // Start at 2 because we don't want to rewrite the counter at index 0
    // if so we would have an unlimited loop
    for (int i = 2; i < 10_002; i++) {
      mb.iconst_0();
      mb.istore((short) i);
    }

    mb.iload_0()
      .iload_1()
      .iadd()
      .istore_0()
      .iinc((short) 1, (short) 1)
      .goto_("loop")
      .label("outLoop")
      .iload_0()
      .ireturn();
  }

  private void buildGotoWAfter(ClassFileBuilder builder) {
    final MethodBuilder mb = builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "goto_w_after", "(II)Z", false)
      .iload_0()
      .iload_1()
      .if_icmpge("ko")
      .iconst_1()
      .goto_("return")
      .label("ko")
      .iconst_0()
      .goto_("return");

    for (int i = 0; i < 10_000; i++) {
      mb.iconst_0();
      mb.istore((short) i);
    }

    mb.label("return")
      .ireturn();
  }

  private void buildConstantsTester(ClassFileBuilder builder, String fullyQualifiedName) {
    builder.newConstantField(Field.MODIFIER_STATIC, "TEST_INT", "I", Integer.MAX_VALUE);
    builder.newConstantField(Field.MODIFIER_STATIC, "TEST_LONG", "J", Long.MAX_VALUE);
    builder.newConstantField(Field.MODIFIER_STATIC, "TEST_FLOAT", "F", Float.MAX_VALUE);
    builder.newConstantField(Field.MODIFIER_STATIC, "TEST_DOUBLE", "D", Double.MAX_VALUE);
    builder.newConstantField(Field.MODIFIER_STATIC, "TEST_STRING", "Ljava/lang/String;", "Hello world");

    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "getIntConstantValue", "()I")
      .getstatic(fullyQualifiedName, "TEST_INT", "I")
      .ireturn();

    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "getLongConstantValue", "()J")
      .getstatic(fullyQualifiedName, "TEST_LONG", "J")
      .lreturn();

    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "getFloatConstantValue", "()F")
      .getstatic(fullyQualifiedName, "TEST_FLOAT", "F")
      .freturn();

    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "getDoubleConstantValue", "()D")
      .getstatic(fullyQualifiedName, "TEST_DOUBLE", "D")
      .dreturn();

    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "getStringConstantValue", "()Ljava/lang/String;")
      .getstatic(fullyQualifiedName, "TEST_STRING", "Ljava/lang/String;")
      .areturn();
  }

  private void buildStaticBlockTester(ClassFileBuilder builder, String fullyQualifiedName) {
    builder.newField(Field.MODIFIER_STATIC, "STATIC_BLOCK", "I");
    builder.staticBlock()
      .ldc(98_765)
      .putstatic(fullyQualifiedName, "STATIC_BLOCK", "I")
      .return_();
  }
}
