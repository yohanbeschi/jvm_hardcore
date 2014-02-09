package org.isk.jvmhardcore.pjba.instruction;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.isk.jvmhardcore.pjba.structure.Instruction;

public class Instructions {

  final public static Instruction NOP = new NoArgInstruction(0x00, 0, 0);
  final public static Instruction ACONST_NULL = new NoArgInstruction(0x01, 1, 0);
  final public static Instruction ICONST_M1 = new NoArgInstruction(0x02, 1, 0);
  final public static Instruction ICONST_0 = new NoArgInstruction(0x03, 1, 0);
  final public static Instruction ICONST_1 = new NoArgInstruction(0x04, 1, 0);
  final public static Instruction ICONST_2 = new NoArgInstruction(0x05, 1, 0);
  final public static Instruction ICONST_3 = new NoArgInstruction(0x06, 1, 0);
  final public static Instruction ICONST_4 = new NoArgInstruction(0x07, 1, 0);
  final public static Instruction ICONST_5 = new NoArgInstruction(0x08, 1, 0);
  final public static Instruction LCONST_0 = new NoArgInstruction(0x09, 2, 0);
  final public static Instruction LCONST_1 = new NoArgInstruction(0x0a, 2, 0);
  final public static Instruction FCONST_0 = new NoArgInstruction(0x0b, 1, 0);
  final public static Instruction FCONST_1 = new NoArgInstruction(0x0c, 1, 0);
  final public static Instruction FCONST_2 = new NoArgInstruction(0x0d, 1, 0);
  final public static Instruction DCONST_0 = new NoArgInstruction(0x0e, 2, 0);
  final public static Instruction DCONST_1 = new NoArgInstruction(0x0f, 2, 0);
  final public static Instruction ILOAD_0 = new NoArgInstruction(0x1a, 1, 1);
  final public static Instruction ILOAD_1 = new NoArgInstruction(0x1b, 1, 2);
  final public static Instruction ILOAD_2 = new NoArgInstruction(0x1c, 1, 3);
  final public static Instruction ILOAD_3 = new NoArgInstruction(0x1d, 1, 4);
  final public static Instruction LLOAD_0 = new NoArgInstruction(0x1e, 2, 2);
  final public static Instruction LLOAD_1 = new NoArgInstruction(0x1f, 2, 3);
  final public static Instruction LLOAD_2 = new NoArgInstruction(0x20, 2, 4);
  final public static Instruction LLOAD_3 = new NoArgInstruction(0x21, 2, 5);
  final public static Instruction FLOAD_0 = new NoArgInstruction(0x22, 1, 1);
  final public static Instruction FLOAD_1 = new NoArgInstruction(0x23, 1, 2);
  final public static Instruction FLOAD_2 = new NoArgInstruction(0x24, 1, 3);
  final public static Instruction FLOAD_3 = new NoArgInstruction(0x25, 1, 4);
  final public static Instruction DLOAD_0 = new NoArgInstruction(0x26, 2, 2);
  final public static Instruction DLOAD_1 = new NoArgInstruction(0x27, 2, 3);
  final public static Instruction DLOAD_2 = new NoArgInstruction(0x28, 2, 4);
  final public static Instruction DLOAD_3 = new NoArgInstruction(0x29, 2, 5);
  final public static Instruction ALOAD_0 = new NoArgInstruction(0x2a, 1, 1);
  final public static Instruction ALOAD_1 = new NoArgInstruction(0x2b, 1, 2);
  final public static Instruction ALOAD_2 = new NoArgInstruction(0x2c, 1, 3);
  final public static Instruction ALOAD_3 = new NoArgInstruction(0x2d, 1, 4);
  final public static Instruction ISTORE_0 = new NoArgInstruction(0x3b, -1, 1);
  final public static Instruction ISTORE_1 = new NoArgInstruction(0x3c, -1, 2);
  final public static Instruction ISTORE_2 = new NoArgInstruction(0x3d, -1, 3);
  final public static Instruction ISTORE_3 = new NoArgInstruction(0x3e, -1, 4);
  final public static Instruction LSTORE_0 = new NoArgInstruction(0x3f, -2, 2);
  final public static Instruction LSTORE_1 = new NoArgInstruction(0x40, -2, 3);
  final public static Instruction LSTORE_2 = new NoArgInstruction(0x41, -2, 4);
  final public static Instruction LSTORE_3 = new NoArgInstruction(0x42, -2, 5);
  final public static Instruction FSTORE_0 = new NoArgInstruction(0x43, -1, 1);
  final public static Instruction FSTORE_1 = new NoArgInstruction(0x44, -1, 2);
  final public static Instruction FSTORE_2 = new NoArgInstruction(0x45, -1, 3);
  final public static Instruction FSTORE_3 = new NoArgInstruction(0x46, -1, 4);
  final public static Instruction DSTORE_0 = new NoArgInstruction(0x47, -2, 2);
  final public static Instruction DSTORE_1 = new NoArgInstruction(0x48, -2, 3);
  final public static Instruction DSTORE_2 = new NoArgInstruction(0x49, -2, 4);
  final public static Instruction DSTORE_3 = new NoArgInstruction(0x4a, -2, 5);
  final public static Instruction ASTORE_0 = new NoArgInstruction(0x4b, -1, 1);
  final public static Instruction ASTORE_1 = new NoArgInstruction(0x4c, -1, 2);
  final public static Instruction ASTORE_2 = new NoArgInstruction(0x4d, -1, 3);
  final public static Instruction ASTORE_3 = new NoArgInstruction(0x4e, -1, 4);
  final public static Instruction POP = new NoArgInstruction(0x57, -1, 0);
  final public static Instruction POP2 = new NoArgInstruction(0x58, -2, 0);
  final public static Instruction DUP = new NoArgInstruction(0x59, 1, 0);
  final public static Instruction DUP_X1 = new NoArgInstruction(0x5a, 1, 0);
  final public static Instruction DUP_X2 = new NoArgInstruction(0x5b, 1, 0);
  final public static Instruction DUP2 = new NoArgInstruction(0x5c, 2, 0);
  final public static Instruction DUP2_X1 = new NoArgInstruction(0x5d, 2, 0);
  final public static Instruction DUP2_X2 = new NoArgInstruction(0x5e, 2, 0);
  final public static Instruction SWAP = new NoArgInstruction(0x5f, 0, 0);
  final public static Instruction IADD = new NoArgInstruction(0x60, -1, 0);
  final public static Instruction LADD = new NoArgInstruction(0x61, -2, 0);
  final public static Instruction FADD = new NoArgInstruction(0x62, -1, 0);
  final public static Instruction DADD = new NoArgInstruction(0x63, -2, 0);
  final public static Instruction ISUB = new NoArgInstruction(0x64, -1, 0);
  final public static Instruction LSUB = new NoArgInstruction(0x65, -2, 0);
  final public static Instruction FSUB = new NoArgInstruction(0x66, -1, 0);
  final public static Instruction DSUB = new NoArgInstruction(0x67, -2, 0);
  final public static Instruction IMUL = new NoArgInstruction(0x68, -1, 0);
  final public static Instruction LMUL = new NoArgInstruction(0x69, -2, 0);
  final public static Instruction FMUL = new NoArgInstruction(0x6a, -1, 0);
  final public static Instruction DMUL = new NoArgInstruction(0x6b, -2, 0);
  final public static Instruction IDIV = new NoArgInstruction(0x6c, -1, 0);
  final public static Instruction LDIV = new NoArgInstruction(0x6d, -2, 0);
  final public static Instruction FDIV = new NoArgInstruction(0x6e, -1, 0);
  final public static Instruction DDIV = new NoArgInstruction(0x6f, -2, 0);
  final public static Instruction IREM = new NoArgInstruction(0x70, -1, 0);
  final public static Instruction LREM = new NoArgInstruction(0x71, -2, 0);
  final public static Instruction FREM = new NoArgInstruction(0x72, -1, 0);
  final public static Instruction DREM = new NoArgInstruction(0x73, -2, 0);
  final public static Instruction INEG = new NoArgInstruction(0x74, 0, 0);
  final public static Instruction LNEG = new NoArgInstruction(0x75, 0, 0);
  final public static Instruction FNEG = new NoArgInstruction(0x76, 0, 0);
  final public static Instruction DNEG = new NoArgInstruction(0x77, 0, 0);
  final public static Instruction ISHL = new NoArgInstruction(0x78, -1, 0);
  final public static Instruction LSHL = new NoArgInstruction(0x79, -2, 0);
  final public static Instruction ISHR = new NoArgInstruction(0x7a, -1, 0);
  final public static Instruction LSHR = new NoArgInstruction(0x7b, -2, 0);
  final public static Instruction IUSHR = new NoArgInstruction(0x7c, -1, 0);
  final public static Instruction LUSHR = new NoArgInstruction(0x7d, -2, 0);
  final public static Instruction IAND = new NoArgInstruction(0x7e, -1, 0);
  final public static Instruction LAND = new NoArgInstruction(0x7f, -2, 0);
  final public static Instruction IOR = new NoArgInstruction(0x80, -1, 0);
  final public static Instruction LOR = new NoArgInstruction(0x81, -2, 0);
  final public static Instruction IXOR = new NoArgInstruction(0x82, -1, 0);
  final public static Instruction LXOR = new NoArgInstruction(0x83, -2, 0);
  final public static Instruction I2L = new NoArgInstruction(0x85, 1, 0);
  final public static Instruction I2F = new NoArgInstruction(0x86, 0, 0);
  final public static Instruction I2D = new NoArgInstruction(0x87, 1, 0);
  final public static Instruction L2I = new NoArgInstruction(0x88, 0, 0);
  final public static Instruction L2F = new NoArgInstruction(0x89, -1, 0);
  final public static Instruction L2D = new NoArgInstruction(0x8a, 0, 0);
  final public static Instruction F2I = new NoArgInstruction(0x8b, 0, 0);
  final public static Instruction F2L = new NoArgInstruction(0x8c, 1, 0);
  final public static Instruction F2D = new NoArgInstruction(0x8d, 1, 0);
  final public static Instruction D2I = new NoArgInstruction(0x8e, -1, 0);
  final public static Instruction D2L = new NoArgInstruction(0x8f, 0, 0);
  final public static Instruction D2F = new NoArgInstruction(0x90, -1, 0);
  final public static Instruction I2B = new NoArgInstruction(0x91, 0, 0);
  final public static Instruction I2C = new NoArgInstruction(0x92, 0, 0);
  final public static Instruction I2S = new NoArgInstruction(0x93, 0, 0);
  final public static Instruction IRETURN = new NoArgInstruction(0xac, -1, 0);
  final public static Instruction LRETURN = new NoArgInstruction(0xad, -2, 0);
  final public static Instruction FRETURN = new NoArgInstruction(0xae, -1, 0);
  final public static Instruction DRETURN = new NoArgInstruction(0xaf, -2, 0);
  final public static Instruction ARETURN = new NoArgInstruction(0xb0, -1, 0);
  final public static Instruction RETURN = new NoArgInstruction(0xb1, 0, 0);

  final static private Map<Integer, MetaInstruction> OPCODE_TO_METAINSTRUCTION;
  final static private Map<String, MetaInstruction> MNEMONIC_TO_METAINSTRUCTION;

  static {
    OPCODE_TO_METAINSTRUCTION = new HashMap<>();
    MNEMONIC_TO_METAINSTRUCTION = new HashMap<>();
    final List<MetaInstruction> metaInstructions = init();
    initList(metaInstructions);
  }

  private Instructions() {
  }

  public static MetaInstruction getMetaInstruction(final String mnemonic) {
    return MNEMONIC_TO_METAINSTRUCTION.get(mnemonic);
  }

  public static MetaInstruction getMetaInstruction(final int opcode) {
    return OPCODE_TO_METAINSTRUCTION.get(opcode);
  }

  public static Instruction getInstruction(final String mnemonic) {
    return getMetaInstruction(mnemonic).getInstruction();
  }

  public static Instruction getInstruction(final int opcode) {
    return getMetaInstruction(opcode).getInstruction();
  }

  public static String getMnemonic(final int opcode) {
    return getMetaInstruction(opcode).getMnemonic();
  }

  private static void initList(List<MetaInstruction> metaInstructions) {
    for (MetaInstruction m : metaInstructions) {
      OPCODE_TO_METAINSTRUCTION.put(m.getOpcode(), m);
      MNEMONIC_TO_METAINSTRUCTION.put(m.getMnemonic(), m);
    }
  }

  private static List<MetaInstruction> init() {
    final List<MetaInstruction> list = new LinkedList<>();

    list.add(new MetaInstruction("nop", NOP));
    list.add(new MetaInstruction("aconst_null", ACONST_NULL));
    list.add(new MetaInstruction("iconst_m1", ICONST_M1));
    list.add(new MetaInstruction("iconst_0", ICONST_0));
    list.add(new MetaInstruction("iconst_1", ICONST_1));
    list.add(new MetaInstruction("iconst_2", ICONST_2));
    list.add(new MetaInstruction("iconst_3", ICONST_3));
    list.add(new MetaInstruction("iconst_4", ICONST_4));
    list.add(new MetaInstruction("iconst_5", ICONST_5));
    list.add(new MetaInstruction("lconst_0", LCONST_0));
    list.add(new MetaInstruction("lconst_1", LCONST_1));
    list.add(new MetaInstruction("fconst_0", FCONST_0));
    list.add(new MetaInstruction("fconst_1", FCONST_1));
    list.add(new MetaInstruction("fconst_2", FCONST_2));
    list.add(new MetaInstruction("dconst_0", DCONST_0));
    list.add(new MetaInstruction("dconst_1", DCONST_1));
    // TODO: 0x10 to 0x19
    list.add(new MetaInstruction("iload_0", ILOAD_0));
    list.add(new MetaInstruction("iload_1", ILOAD_1));
    list.add(new MetaInstruction("iload_2", ILOAD_2));
    list.add(new MetaInstruction("iload_3", ILOAD_3));
    list.add(new MetaInstruction("lload_0", LLOAD_0));
    list.add(new MetaInstruction("lload_1", LLOAD_1));
    list.add(new MetaInstruction("lload_2", LLOAD_2));
    list.add(new MetaInstruction("lload_3", LLOAD_3));
    list.add(new MetaInstruction("fload_0", FLOAD_0));
    list.add(new MetaInstruction("fload_1", FLOAD_1));
    list.add(new MetaInstruction("fload_2", FLOAD_2));
    list.add(new MetaInstruction("fload_3", FLOAD_3));
    list.add(new MetaInstruction("dload_0", DLOAD_0));
    list.add(new MetaInstruction("dload_1", DLOAD_1));
    list.add(new MetaInstruction("dload_2", DLOAD_2));
    list.add(new MetaInstruction("dload_3", DLOAD_3));
    list.add(new MetaInstruction("aload_0", ALOAD_0));
    list.add(new MetaInstruction("aload_1", ALOAD_1));
    list.add(new MetaInstruction("aload_2", ALOAD_2));
    list.add(new MetaInstruction("aload_3", ALOAD_3));
    // TODO: 0x2e to 0x3a
    list.add(new MetaInstruction("istore_0", ISTORE_0));
    list.add(new MetaInstruction("istore_1", ISTORE_1));
    list.add(new MetaInstruction("istore_2", ISTORE_2));
    list.add(new MetaInstruction("istore_3", ISTORE_3));
    list.add(new MetaInstruction("lstore_0", LSTORE_0));
    list.add(new MetaInstruction("lstore_1", LSTORE_1));
    list.add(new MetaInstruction("lstore_2", LSTORE_2));
    list.add(new MetaInstruction("lstore_3", LSTORE_3));
    list.add(new MetaInstruction("fstore_0", FSTORE_0));
    list.add(new MetaInstruction("fstore_1", FSTORE_1));
    list.add(new MetaInstruction("fstore_2", FSTORE_2));
    list.add(new MetaInstruction("fstore_3", FSTORE_3));
    list.add(new MetaInstruction("dstore_0", DSTORE_0));
    list.add(new MetaInstruction("dstore_1", DSTORE_1));
    list.add(new MetaInstruction("dstore_2", DSTORE_2));
    list.add(new MetaInstruction("dstore_3", DSTORE_3));
    list.add(new MetaInstruction("astore_0", ASTORE_0));
    list.add(new MetaInstruction("astore_1", ASTORE_1));
    list.add(new MetaInstruction("astore_2", ASTORE_2));
    list.add(new MetaInstruction("astore_3", ASTORE_3));
    // TODO: 0x4f to 0x56
    list.add(new MetaInstruction("pop", POP));
    list.add(new MetaInstruction("pop2", POP2));
    list.add(new MetaInstruction("dup", DUP));
    list.add(new MetaInstruction("dup_x1", DUP_X1));
    list.add(new MetaInstruction("dup_x2", DUP_X2));
    list.add(new MetaInstruction("dup2", DUP2));
    list.add(new MetaInstruction("dup2_x1", DUP2_X1));
    list.add(new MetaInstruction("dup2_x2", DUP2_X2));
    list.add(new MetaInstruction("swap", SWAP));
    list.add(new MetaInstruction("iadd", IADD));
    list.add(new MetaInstruction("ladd", LADD));
    list.add(new MetaInstruction("fadd", FADD));
    list.add(new MetaInstruction("dadd", DADD));
    list.add(new MetaInstruction("isub", ISUB));
    list.add(new MetaInstruction("lsub", LSUB));
    list.add(new MetaInstruction("fsub", FSUB));
    list.add(new MetaInstruction("dsub", DSUB));
    list.add(new MetaInstruction("imul", IMUL));
    list.add(new MetaInstruction("lmul", LMUL));
    list.add(new MetaInstruction("fmul", FMUL));
    list.add(new MetaInstruction("dmul", DMUL));
    list.add(new MetaInstruction("idiv", IDIV));
    list.add(new MetaInstruction("ldiv", LDIV));
    list.add(new MetaInstruction("fdiv", FDIV));
    list.add(new MetaInstruction("ddiv", DDIV));
    list.add(new MetaInstruction("irem", IREM));
    list.add(new MetaInstruction("lrem", LREM));
    list.add(new MetaInstruction("frem", FREM));
    list.add(new MetaInstruction("drem", DREM));
    list.add(new MetaInstruction("ineg", INEG));
    list.add(new MetaInstruction("lneg", LNEG));
    list.add(new MetaInstruction("fneg", FNEG));
    list.add(new MetaInstruction("dneg", DNEG));
    list.add(new MetaInstruction("ishl", ISHL));
    list.add(new MetaInstruction("lshl", LSHL));
    list.add(new MetaInstruction("ishr", ISHR));
    list.add(new MetaInstruction("lshr", LSHR));
    list.add(new MetaInstruction("iushr", IUSHR));
    list.add(new MetaInstruction("lushr", LUSHR));
    list.add(new MetaInstruction("iand", IAND));
    list.add(new MetaInstruction("land", LAND));
    list.add(new MetaInstruction("ior", IOR));
    list.add(new MetaInstruction("lor", LOR));
    list.add(new MetaInstruction("ixor", IXOR));
    list.add(new MetaInstruction("lxor", LXOR));
    // TODO: 0x84
    list.add(new MetaInstruction("i2l", I2L));
    list.add(new MetaInstruction("i2f", I2F));
    list.add(new MetaInstruction("i2d", I2D));
    list.add(new MetaInstruction("l2i", L2I));
    list.add(new MetaInstruction("l2f", L2F));
    list.add(new MetaInstruction("l2d", L2D));
    list.add(new MetaInstruction("f2i", F2I));
    list.add(new MetaInstruction("f2l", F2L));
    list.add(new MetaInstruction("f2d", F2D));
    list.add(new MetaInstruction("d2i", D2I));
    list.add(new MetaInstruction("d2l", D2L));
    list.add(new MetaInstruction("d2f", D2F));
    list.add(new MetaInstruction("i2b", I2B));
    list.add(new MetaInstruction("i2c", I2C));
    list.add(new MetaInstruction("i2s", I2S));
    // TODO: 0x94 to 0xab
    list.add(new MetaInstruction("ireturn", IRETURN));
    list.add(new MetaInstruction("lreturn", LRETURN));
    list.add(new MetaInstruction("freturn", FRETURN));
    list.add(new MetaInstruction("dreturn", DRETURN));
    list.add(new MetaInstruction("areturn", ARETURN));
    list.add(new MetaInstruction("return", RETURN));
    // TODO: 0xc4 to 0xc9

    return list;
  }

  public static Instruction nop() {
    return NOP;
  }

  public static Instruction aconst_null() {
    return ACONST_NULL;
  }

  public static Instruction iconst_m1() {
    return ICONST_M1;
  }

  public static Instruction iconst_0() {
    return ICONST_0;
  }

  public static Instruction iconst_1() {
    return ICONST_1;
  }

  public static Instruction iconst_2() {
    return ICONST_2;
  }

  public static Instruction iconst_3() {
    return ICONST_3;
  }

  public static Instruction iconst_4() {
    return ICONST_4;
  }

  public static Instruction iconst_5() {
    return ICONST_5;
  }

  public static Instruction lconst_0() {
    return LCONST_0;
  }

  public static Instruction lconst_1() {
    return LCONST_1;
  }

  public static Instruction fconst_0() {
    return FCONST_0;
  }

  public static Instruction fconst_1() {
    return FCONST_1;
  }

  public static Instruction fconst_2() {
    return FCONST_2;
  }

  public static Instruction dconst_0() {
    return DCONST_0;
  }

  public static Instruction dconst_1() {
    return DCONST_1;
  }

  public static Instruction iload_0() {
    return ILOAD_0;
  }

  public static Instruction iload_1() {
    return ILOAD_1;
  }

  public static Instruction iload_2() {
    return ILOAD_2;
  }

  public static Instruction iload_3() {
    return ILOAD_3;
  }

  public static Instruction lload_0() {
    return LLOAD_0;
  }

  public static Instruction lload_1() {
    return LLOAD_1;
  }

  public static Instruction lload_2() {
    return LLOAD_2;
  }

  public static Instruction lload_3() {
    return LLOAD_3;
  }

  public static Instruction fload_0() {
    return FLOAD_0;
  }

  public static Instruction fload_1() {
    return FLOAD_1;
  }

  public static Instruction fload_2() {
    return FLOAD_2;
  }

  public static Instruction fload_3() {
    return FLOAD_3;
  }

  public static Instruction dload_0() {
    return DLOAD_0;
  }

  public static Instruction dload_1() {
    return DLOAD_1;
  }

  public static Instruction dload_2() {
    return DLOAD_2;
  }

  public static Instruction dload_3() {
    return DLOAD_3;
  }

  public static Instruction aload_0() {
    return ALOAD_0;
  }

  public static Instruction aload_1() {
    return ALOAD_1;
  }

  public static Instruction aload_2() {
    return ALOAD_2;
  }

  public static Instruction aload_3() {
    return ALOAD_3;
  }

  public static Instruction istore_0() {
    return ISTORE_0;
  }

  public static Instruction istore_1() {
    return ISTORE_1;
  }

  public static Instruction istore_2() {
    return ISTORE_2;
  }

  public static Instruction istore_3() {
    return ISTORE_3;
  }

  public static Instruction lstore_0() {
    return LSTORE_0;
  }

  public static Instruction lstore_1() {
    return LSTORE_1;
  }

  public static Instruction lstore_2() {
    return LSTORE_2;
  }

  public static Instruction lstore_3() {
    return LSTORE_3;
  }

  public static Instruction fstore_0() {
    return FSTORE_0;
  }

  public static Instruction fstore_1() {
    return FSTORE_1;
  }

  public static Instruction fstore_2() {
    return FSTORE_2;
  }

  public static Instruction fstore_3() {
    return FSTORE_3;
  }

  public static Instruction dstore_0() {
    return DSTORE_0;
  }

  public static Instruction dstore_1() {
    return DSTORE_1;
  }

  public static Instruction dstore_2() {
    return DSTORE_2;
  }

  public static Instruction dstore_3() {
    return DSTORE_3;
  }

  public static Instruction astore_0() {
    return ASTORE_0;
  }

  public static Instruction astore_1() {
    return ASTORE_1;
  }

  public static Instruction astore_2() {
    return ASTORE_2;
  }

  public static Instruction astore_3() {
    return ASTORE_3;
  }

  public static Instruction pop() {
    return POP;
  }

  public static Instruction pop2() {
    return POP2;
  }

  public static Instruction dup() {
    return DUP;
  }

  public static Instruction dup_x1() {
    return DUP_X1;
  }

  public static Instruction dup_x2() {
    return DUP_X2;
  }

  public static Instruction dup2() {
    return DUP2;
  }

  public static Instruction dup2_x1() {
    return DUP2_X1;
  }

  public static Instruction dup2_x2() {
    return DUP2_X2;
  }

  public static Instruction swap() {
    return SWAP;
  }

  public static Instruction iadd() {
    return IADD;
  }

  public static Instruction ladd() {
    return LADD;
  }

  public static Instruction fadd() {
    return FADD;
  }

  public static Instruction dadd() {
    return DADD;
  }

  public static Instruction isub() {
    return ISUB;
  }

  public static Instruction lsub() {
    return LSUB;
  }

  public static Instruction fsub() {
    return FSUB;
  }

  public static Instruction dsub() {
    return DSUB;
  }

  public static Instruction imul() {
    return IMUL;
  }

  public static Instruction lmul() {
    return LMUL;
  }

  public static Instruction fmul() {
    return FMUL;
  }

  public static Instruction dmul() {
    return DMUL;
  }

  public static Instruction idiv() {
    return IDIV;
  }

  public static Instruction ldiv() {
    return LDIV;
  }

  public static Instruction fdiv() {
    return FDIV;
  }

  public static Instruction ddiv() {
    return DDIV;
  }

  public static Instruction irem() {
    return IREM;
  }

  public static Instruction lrem() {
    return LREM;
  }

  public static Instruction frem() {
    return FREM;
  }

  public static Instruction drem() {
    return DREM;
  }

  public static Instruction ineg() {
    return INEG;
  }

  public static Instruction lneg() {
    return LNEG;
  }

  public static Instruction fneg() {
    return FNEG;
  }

  public static Instruction dneg() {
    return DNEG;
  }

  public static Instruction ishl() {
    return ISHL;
  }

  public static Instruction lshl() {
    return LSHL;
  }

  public static Instruction ishr() {
    return ISHR;
  }

  public static Instruction lshr() {
    return LSHR;
  }

  public static Instruction iushr() {
    return IUSHR;
  }

  public static Instruction lushr() {
    return LUSHR;
  }

  public static Instruction iand() {
    return IAND;
  }

  public static Instruction land() {
    return LAND;
  }

  public static Instruction ior() {
    return IOR;
  }

  public static Instruction lor() {
    return LOR;
  }

  public static Instruction ixor() {
    return IXOR;
  }

  public static Instruction lxor() {
    return LXOR;
  }

  public static Instruction i2l() {
    return I2L;
  }

  public static Instruction i2f() {
    return I2F;
  }

  public static Instruction i2d() {
    return I2D;
  }

  public static Instruction l2i() {
    return L2I;
  }

  public static Instruction l2f() {
    return L2F;
  }

  public static Instruction l2d() {
    return L2D;
  }

  public static Instruction f2i() {
    return F2I;
  }

  public static Instruction f2l() {
    return F2L;
  }

  public static Instruction f2d() {
    return F2D;
  }

  public static Instruction d2i() {
    return D2I;
  }

  public static Instruction d2l() {
    return D2L;
  }

  public static Instruction d2f() {
    return D2F;
  }

  public static Instruction i2b() {
    return I2B;
  }

  public static Instruction i2c() {
    return I2C;
  }

  public static Instruction i2s() {
    return I2S;
  }

  public static Instruction ireturn() {
    return IRETURN;
  }

  public static Instruction lreturn() {
    return LRETURN;
  }

  public static Instruction freturn() {
    return FRETURN;
  }

  public static Instruction dreturn() {
    return DRETURN;
  }

  public static Instruction areturn() {
    return ARETURN;
  }

  public static Instruction return_() {
    return RETURN;
  }
}
