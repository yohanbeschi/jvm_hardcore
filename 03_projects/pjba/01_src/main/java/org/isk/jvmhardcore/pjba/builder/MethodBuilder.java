package org.isk.jvmhardcore.pjba.builder;

import org.isk.jvmhardcore.pjba.instruction.Instructions;
import org.isk.jvmhardcore.pjba.structure.ClassFile;
import org.isk.jvmhardcore.pjba.structure.Instruction;
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

  public MethodBuilder instruction(Instruction instruction) {
    this.code.addInstruction(instruction);
    return this;
  }

  public MethodBuilder nop() {
    this.code.addInstruction(Instructions.NOP);
    return this;
  }

  public MethodBuilder aconst_null() {
    this.code.addInstruction(Instructions.ACONST_NULL);
    return this;
  }

  public MethodBuilder iconst_m1() {
    this.code.addInstruction(Instructions.ICONST_M1);
    return this;
  }

  public MethodBuilder iconst_0() {
    this.code.addInstruction(Instructions.ICONST_0);
    return this;
  }

  public MethodBuilder iconst_1() {
    this.code.addInstruction(Instructions.ICONST_1);
    return this;
  }

  public MethodBuilder iconst_2() {
    this.code.addInstruction(Instructions.ICONST_2);
    return this;
  }

  public MethodBuilder iconst_3() {
    this.code.addInstruction(Instructions.ICONST_3);
    return this;
  }

  public MethodBuilder iconst_4() {
    this.code.addInstruction(Instructions.ICONST_4);
    return this;
  }

  public MethodBuilder iconst_5() {
    this.code.addInstruction(Instructions.ICONST_5);
    return this;
  }

  public MethodBuilder lconst_0() {
    this.code.addInstruction(Instructions.LCONST_0);
    return this;
  }

  public MethodBuilder lconst_1() {
    this.code.addInstruction(Instructions.LCONST_1);
    return this;
  }

  public MethodBuilder fconst_0() {
    this.code.addInstruction(Instructions.FCONST_0);
    return this;
  }

  public MethodBuilder fconst_1() {
    this.code.addInstruction(Instructions.FCONST_1);
    return this;
  }

  public MethodBuilder fconst_2() {
    this.code.addInstruction(Instructions.FCONST_2);
    return this;
  }

  public MethodBuilder dconst_0() {
    this.code.addInstruction(Instructions.DCONST_0);
    return this;
  }

  public MethodBuilder dconst_1() {
    this.code.addInstruction(Instructions.DCONST_1);
    return this;
  }

  public MethodBuilder bipush(byte value) {
    this.code.addInstruction(Instructions.bipush(value));
    return this;
  }

  public MethodBuilder sipush(short value) {
    this.code.addInstruction(Instructions.sipush(value));
    return this;
  }

  public MethodBuilder ldc(String value) {
    final int utf8Index = this.classFileBuilder.getClassFile().addConstantUTF8(value);
    final int indexInCP = this.classFileBuilder.getClassFile().addConstantString(utf8Index);

    this.internalLdc(indexInCP);

    return this;
  }

  public MethodBuilder ldc(int value) {
    final int indexInCP = this.classFileBuilder.getClassFile().addConstantInteger(value);

    this.internalLdc(indexInCP);

    return this;
  }

  public MethodBuilder ldc(float value) {
    final int indexInCP = this.classFileBuilder.getClassFile().addConstantFloat(value);

    this.internalLdc(indexInCP);

    return this;
  }

  public MethodBuilder ldc(long value) {
    final int indexInCP = this.classFileBuilder.getClassFile().addConstantLong(value);
    this.code.addInstruction(Instructions.ldc2_w((short) indexInCP));
    return this;
  }

  public MethodBuilder ldc(double value) {
    final int indexInCP = this.classFileBuilder.getClassFile().addConstantDouble(value);
    this.code.addInstruction(Instructions.ldc2_w((short) indexInCP));
    return this;
  }

  private void internalLdc(final int indexInCP) {
    if (indexInCP > 255) { // 255 = Byte.MAX_UNSIGNED_VALUE
      this.code.addInstruction(Instructions.ldc_w((short) indexInCP));
    } else {
      this.code.addInstruction(Instructions.ldc((byte) indexInCP));
    }
  }

  public MethodBuilder iload(short indexInLV) {
    if (indexInLV >= Byte.MIN_VALUE && indexInLV <= Byte.MAX_VALUE) {
      this.code.addInstruction(Instructions.iload((byte) indexInLV));
    } else {
      this.code.addInstruction(Instructions.wide_iload(indexInLV));
    }
    return this;
  }

  public MethodBuilder lload(short indexInLV) {
    if (indexInLV >= Byte.MIN_VALUE && indexInLV <= Byte.MAX_VALUE) {
      this.code.addInstruction(Instructions.lload((byte) indexInLV));
    } else {
      this.code.addInstruction(Instructions.wide_lload(indexInLV));
    }
    return this;
  }

  public MethodBuilder fload(short indexInLV) {
    if (indexInLV >= Byte.MIN_VALUE && indexInLV <= Byte.MAX_VALUE) {
      this.code.addInstruction(Instructions.fload((byte) indexInLV));
    } else {
      this.code.addInstruction(Instructions.wide_fload(indexInLV));
    }
    return this;
  }

  public MethodBuilder dload(short indexInLV) {
    if (indexInLV >= Byte.MIN_VALUE && indexInLV <= Byte.MAX_VALUE) {
      this.code.addInstruction(Instructions.dload((byte) indexInLV));
    } else {
      this.code.addInstruction(Instructions.wide_dload(indexInLV));
    }
    return this;
  }

  public MethodBuilder aload(short indexInLV) {
    if (indexInLV >= Byte.MIN_VALUE && indexInLV <= Byte.MAX_VALUE) {
      this.code.addInstruction(Instructions.aload((byte) indexInLV));
    } else {
      this.code.addInstruction(Instructions.wide_aload(indexInLV));
    }
    return this;
  }

  public MethodBuilder iload_0() {
    this.code.addInstruction(Instructions.ILOAD_0);
    return this;
  }

  public MethodBuilder iload_1() {
    this.code.addInstruction(Instructions.ILOAD_1);
    return this;
  }

  public MethodBuilder iload_2() {
    this.code.addInstruction(Instructions.ILOAD_2);
    return this;
  }

  public MethodBuilder iload_3() {
    this.code.addInstruction(Instructions.ILOAD_3);
    return this;
  }

  public MethodBuilder lload_0() {
    this.code.addInstruction(Instructions.LLOAD_0);
    return this;
  }

  public MethodBuilder lload_1() {
    this.code.addInstruction(Instructions.LLOAD_1);
    return this;
  }

  public MethodBuilder lload_2() {
    this.code.addInstruction(Instructions.LLOAD_2);
    return this;
  }

  public MethodBuilder lload_3() {
    this.code.addInstruction(Instructions.LLOAD_3);
    return this;
  }

  public MethodBuilder fload_0() {
    this.code.addInstruction(Instructions.FLOAD_0);
    return this;
  }

  public MethodBuilder fload_1() {
    this.code.addInstruction(Instructions.FLOAD_1);
    return this;
  }

  public MethodBuilder fload_2() {
    this.code.addInstruction(Instructions.FLOAD_2);
    return this;
  }

  public MethodBuilder fload_3() {
    this.code.addInstruction(Instructions.FLOAD_3);
    return this;
  }

  public MethodBuilder dload_0() {
    this.code.addInstruction(Instructions.DLOAD_0);
    return this;
  }

  public MethodBuilder dload_1() {
    this.code.addInstruction(Instructions.DLOAD_1);
    return this;
  }

  public MethodBuilder dload_2() {
    this.code.addInstruction(Instructions.DLOAD_2);
    return this;
  }

  public MethodBuilder dload_3() {
    this.code.addInstruction(Instructions.DLOAD_3);
    return this;
  }

  public MethodBuilder aload_0() {
    this.code.addInstruction(Instructions.ALOAD_0);
    return this;
  }

  public MethodBuilder aload_1() {
    this.code.addInstruction(Instructions.ALOAD_1);
    return this;
  }

  public MethodBuilder aload_2() {
    this.code.addInstruction(Instructions.ALOAD_2);
    return this;
  }

  public MethodBuilder aload_3() {
    this.code.addInstruction(Instructions.ALOAD_3);
    return this;
  }

  public MethodBuilder istore(short indexInLV) {
    if (indexInLV >= Byte.MIN_VALUE && indexInLV <= Byte.MAX_VALUE) {
      this.code.addInstruction(Instructions.istore((byte) indexInLV));
    } else {
      this.code.addInstruction(Instructions.wide_istore(indexInLV));
    }
    return this;
  }

  public MethodBuilder lstore(short indexInLV) {
    if (indexInLV >= Byte.MIN_VALUE && indexInLV <= Byte.MAX_VALUE) {
      this.code.addInstruction(Instructions.lstore((byte) indexInLV));
    } else {
      this.code.addInstruction(Instructions.wide_lstore(indexInLV));
    }
    return this;
  }

  public MethodBuilder fstore(short indexInLV) {
    if (indexInLV >= Byte.MIN_VALUE && indexInLV <= Byte.MAX_VALUE) {
      this.code.addInstruction(Instructions.fstore((byte) indexInLV));
    } else {
      this.code.addInstruction(Instructions.wide_fstore(indexInLV));
    }
    return this;
  }

  public MethodBuilder dstore(short indexInLV) {
    if (indexInLV >= Byte.MIN_VALUE && indexInLV <= Byte.MAX_VALUE) {
      this.code.addInstruction(Instructions.dstore((byte) indexInLV));
    } else {
      this.code.addInstruction(Instructions.wide_dstore(indexInLV));
    }
    return this;
  }

  public MethodBuilder astore(short indexInLV) {
    if (indexInLV >= Byte.MIN_VALUE && indexInLV <= Byte.MAX_VALUE) {
      this.code.addInstruction(Instructions.astore((byte) indexInLV));
    } else {
      this.code.addInstruction(Instructions.wide_astore(indexInLV));
    }
    return this;
  }

  public MethodBuilder istore_0() {
    this.code.addInstruction(Instructions.ISTORE_0);
    return this;
  }

  public MethodBuilder istore_1() {
    this.code.addInstruction(Instructions.ISTORE_1);
    return this;
  }

  public MethodBuilder istore_2() {
    this.code.addInstruction(Instructions.ISTORE_2);
    return this;
  }

  public MethodBuilder istore_3() {
    this.code.addInstruction(Instructions.ISTORE_3);
    return this;
  }

  public MethodBuilder lstore_0() {
    this.code.addInstruction(Instructions.LSTORE_0);
    return this;
  }

  public MethodBuilder lstore_1() {
    this.code.addInstruction(Instructions.LSTORE_1);
    return this;
  }

  public MethodBuilder lstore_2() {
    this.code.addInstruction(Instructions.LSTORE_2);
    return this;
  }

  public MethodBuilder lstore_3() {
    this.code.addInstruction(Instructions.LSTORE_3);
    return this;
  }

  public MethodBuilder fstore_0() {
    this.code.addInstruction(Instructions.FSTORE_0);
    return this;
  }

  public MethodBuilder fstore_1() {
    this.code.addInstruction(Instructions.FSTORE_1);
    return this;
  }

  public MethodBuilder fstore_2() {
    this.code.addInstruction(Instructions.FSTORE_2);
    return this;
  }

  public MethodBuilder fstore_3() {
    this.code.addInstruction(Instructions.FSTORE_3);
    return this;
  }

  public MethodBuilder dstore_0() {
    this.code.addInstruction(Instructions.DSTORE_0);
    return this;
  }

  public MethodBuilder dstore_1() {
    this.code.addInstruction(Instructions.DSTORE_1);
    return this;
  }

  public MethodBuilder dstore_2() {
    this.code.addInstruction(Instructions.DSTORE_2);
    return this;
  }

  public MethodBuilder dstore_3() {
    this.code.addInstruction(Instructions.DSTORE_3);
    return this;
  }

  public MethodBuilder astore_0() {
    this.code.addInstruction(Instructions.ASTORE_0);
    return this;
  }

  public MethodBuilder astore_1() {
    this.code.addInstruction(Instructions.ASTORE_1);
    return this;
  }

  public MethodBuilder astore_2() {
    this.code.addInstruction(Instructions.ASTORE_2);
    return this;
  }

  public MethodBuilder astore_3() {
    this.code.addInstruction(Instructions.ASTORE_3);
    return this;
  }

  public MethodBuilder pop() {
    this.code.addInstruction(Instructions.POP);
    return this;
  }

  public MethodBuilder pop2() {
    this.code.addInstruction(Instructions.POP2);
    return this;
  }

  public MethodBuilder dup() {
    this.code.addInstruction(Instructions.DUP);
    return this;
  }

  public MethodBuilder dup_x1() {
    this.code.addInstruction(Instructions.DUP_X1);
    return this;
  }

  public MethodBuilder dup_x2() {
    this.code.addInstruction(Instructions.DUP_X2);
    return this;
  }

  public MethodBuilder dup2() {
    this.code.addInstruction(Instructions.DUP2);
    return this;
  }

  public MethodBuilder dup2_x1() {
    this.code.addInstruction(Instructions.DUP2_X1);
    return this;
  }

  public MethodBuilder dup2_x2() {
    this.code.addInstruction(Instructions.DUP2_X2);
    return this;
  }

  public MethodBuilder swap() {
    this.code.addInstruction(Instructions.SWAP);
    return this;
  }

  public MethodBuilder iadd() {
    this.code.addInstruction(Instructions.IADD);
    return this;
  }

  public MethodBuilder ladd() {
    this.code.addInstruction(Instructions.LADD);
    return this;
  }

  public MethodBuilder fadd() {
    this.code.addInstruction(Instructions.FADD);
    return this;
  }

  public MethodBuilder dadd() {
    this.code.addInstruction(Instructions.DADD);
    return this;
  }

  public MethodBuilder isub() {
    this.code.addInstruction(Instructions.ISUB);
    return this;
  }

  public MethodBuilder lsub() {
    this.code.addInstruction(Instructions.LSUB);
    return this;
  }

  public MethodBuilder fsub() {
    this.code.addInstruction(Instructions.FSUB);
    return this;
  }

  public MethodBuilder dsub() {
    this.code.addInstruction(Instructions.DSUB);
    return this;
  }

  public MethodBuilder imul() {
    this.code.addInstruction(Instructions.IMUL);
    return this;
  }

  public MethodBuilder lmul() {
    this.code.addInstruction(Instructions.LMUL);
    return this;
  }

  public MethodBuilder fmul() {
    this.code.addInstruction(Instructions.FMUL);
    return this;
  }

  public MethodBuilder dmul() {
    this.code.addInstruction(Instructions.DMUL);
    return this;
  }

  public MethodBuilder idiv() {
    this.code.addInstruction(Instructions.IDIV);
    return this;
  }

  public MethodBuilder ldiv() {
    this.code.addInstruction(Instructions.LDIV);
    return this;
  }

  public MethodBuilder fdiv() {
    this.code.addInstruction(Instructions.FDIV);
    return this;
  }

  public MethodBuilder ddiv() {
    this.code.addInstruction(Instructions.DDIV);
    return this;
  }

  public MethodBuilder irem() {
    this.code.addInstruction(Instructions.IREM);
    return this;
  }

  public MethodBuilder lrem() {
    this.code.addInstruction(Instructions.LREM);
    return this;
  }

  public MethodBuilder frem() {
    this.code.addInstruction(Instructions.FREM);
    return this;
  }

  public MethodBuilder drem() {
    this.code.addInstruction(Instructions.DREM);
    return this;
  }

  public MethodBuilder ineg() {
    this.code.addInstruction(Instructions.INEG);
    return this;
  }

  public MethodBuilder lneg() {
    this.code.addInstruction(Instructions.LNEG);
    return this;
  }

  public MethodBuilder fneg() {
    this.code.addInstruction(Instructions.FNEG);
    return this;
  }

  public MethodBuilder dneg() {
    this.code.addInstruction(Instructions.DNEG);
    return this;
  }

  public MethodBuilder ishl() {
    this.code.addInstruction(Instructions.ISHL);
    return this;
  }

  public MethodBuilder lshl() {
    this.code.addInstruction(Instructions.LSHL);
    return this;
  }

  public MethodBuilder ishr() {
    this.code.addInstruction(Instructions.ISHR);
    return this;
  }

  public MethodBuilder lshr() {
    this.code.addInstruction(Instructions.LSHR);
    return this;
  }

  public MethodBuilder iushr() {
    this.code.addInstruction(Instructions.IUSHR);
    return this;
  }

  public MethodBuilder lushr() {
    this.code.addInstruction(Instructions.LUSHR);
    return this;
  }

  public MethodBuilder iand() {
    this.code.addInstruction(Instructions.IAND);
    return this;
  }

  public MethodBuilder land() {
    this.code.addInstruction(Instructions.LAND);
    return this;
  }

  public MethodBuilder ior() {
    this.code.addInstruction(Instructions.IOR);
    return this;
  }

  public MethodBuilder lor() {
    this.code.addInstruction(Instructions.LOR);
    return this;
  }

  public MethodBuilder ixor() {
    this.code.addInstruction(Instructions.IXOR);
    return this;
  }

  public MethodBuilder lxor() {
    this.code.addInstruction(Instructions.LXOR);
    return this;
  }

  public MethodBuilder iinc(short indexInLV, short constant) {
    if (   indexInLV >= Byte.MIN_VALUE && indexInLV <= Byte.MAX_VALUE
        && constant >= Byte.MIN_VALUE && constant <= Byte.MAX_VALUE) {
      this.code.addInstruction(Instructions.iinc((byte) indexInLV, (byte) constant));
    } else {
      this.code.addInstruction(Instructions.wide_iinc(indexInLV, constant));
    }
    return this;
  }

  public MethodBuilder i2l() {
    this.code.addInstruction(Instructions.I2L);
    return this;
  }

  public MethodBuilder i2f() {
    this.code.addInstruction(Instructions.I2F);
    return this;
  }

  public MethodBuilder i2d() {
    this.code.addInstruction(Instructions.I2D);
    return this;
  }

  public MethodBuilder l2i() {
    this.code.addInstruction(Instructions.L2I);
    return this;
  }

  public MethodBuilder l2f() {
    this.code.addInstruction(Instructions.L2F);
    return this;
  }

  public MethodBuilder l2d() {
    this.code.addInstruction(Instructions.L2D);
    return this;
  }

  public MethodBuilder f2i() {
    this.code.addInstruction(Instructions.F2I);
    return this;
  }

  public MethodBuilder f2l() {
    this.code.addInstruction(Instructions.F2L);
    return this;
  }

  public MethodBuilder f2d() {
    this.code.addInstruction(Instructions.F2D);
    return this;
  }

  public MethodBuilder d2i() {
    this.code.addInstruction(Instructions.D2I);
    return this;
  }

  public MethodBuilder d2l() {
    this.code.addInstruction(Instructions.D2L);
    return this;
  }

  public MethodBuilder d2f() {
    this.code.addInstruction(Instructions.D2F);
    return this;
  }

  public MethodBuilder i2b() {
    this.code.addInstruction(Instructions.I2B);
    return this;
  }

  public MethodBuilder i2c() {
    this.code.addInstruction(Instructions.I2C);
    return this;
  }

  public MethodBuilder i2s() {
    this.code.addInstruction(Instructions.I2S);
    return this;
  }

  public MethodBuilder ireturn() {
    this.code.addInstruction(Instructions.IRETURN);
    return this;
  }

  public MethodBuilder lreturn() {
    this.code.addInstruction(Instructions.LRETURN);
    return this;
  }

  public MethodBuilder freturn() {
    this.code.addInstruction(Instructions.FRETURN);
    return this;
  }

  public MethodBuilder dreturn() {
    this.code.addInstruction(Instructions.DRETURN);
    return this;
  }

  public MethodBuilder areturn() {
    this.code.addInstruction(Instructions.ARETURN);
    return this;
  }

  public MethodBuilder return_() {
    this.code.addInstruction(Instructions.RETURN);
    return this;
  }

  public ClassFile build() {
    return this.classFileBuilder.build();
  }
}
