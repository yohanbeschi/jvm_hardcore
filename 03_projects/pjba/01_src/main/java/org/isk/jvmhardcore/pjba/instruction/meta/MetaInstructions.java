package org.isk.jvmhardcore.pjba.instruction.meta;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.isk.jvmhardcore.pjba.instruction.Instructions;
import org.isk.jvmhardcore.pjba.instruction.factory.ByteArgInstructionFactory;
import org.isk.jvmhardcore.pjba.instruction.factory.IincInstructionFactory;
import org.isk.jvmhardcore.pjba.instruction.factory.ShortArgInstructionFactory;
import org.isk.jvmhardcore.pjba.instruction.factory.WideInstructionFactory;
import org.isk.jvmhardcore.pjba.instruction.meta.MetaInstruction.ArgsType;
import org.isk.jvmhardcore.pjba.structure.Instruction;

public class MetaInstructions {
  final private static Map<Integer, MetaInstruction> OPCODE_TO_METAINSTRUCTION;
  final private static Map<String, MetaInstruction> MNEMONIC_TO_METAINSTRUCTION;

  static {
    OPCODE_TO_METAINSTRUCTION = new HashMap<>();
    MNEMONIC_TO_METAINSTRUCTION = new HashMap<>();
    final List<MetaInstruction> metaInstructions = init();
    initList(metaInstructions);
  }

  public static MetaInstruction getMetaInstruction(final String mnemonic) {
    return MNEMONIC_TO_METAINSTRUCTION.get(mnemonic);
  }

  public static MetaInstruction getMetaInstruction(final int opcode) {
    return OPCODE_TO_METAINSTRUCTION.get(opcode);
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

    list.add(new NoArgMetaInstruction("nop", ArgsType.NONE, Instructions.NOP));
    list.add(new NoArgMetaInstruction("aconst_null", ArgsType.NONE, Instructions.ACONST_NULL));
    list.add(new NoArgMetaInstruction("iconst_m1", ArgsType.NONE, Instructions.ICONST_M1));
    list.add(new NoArgMetaInstruction("iconst_0", ArgsType.NONE, Instructions.ICONST_0));
    list.add(new NoArgMetaInstruction("iconst_1", ArgsType.NONE, Instructions.ICONST_1));
    list.add(new NoArgMetaInstruction("iconst_2", ArgsType.NONE, Instructions.ICONST_2));
    list.add(new NoArgMetaInstruction("iconst_3", ArgsType.NONE, Instructions.ICONST_3));
    list.add(new NoArgMetaInstruction("iconst_4", ArgsType.NONE, Instructions.ICONST_4));
    list.add(new NoArgMetaInstruction("iconst_5", ArgsType.NONE, Instructions.ICONST_5));
    list.add(new NoArgMetaInstruction("lconst_0", ArgsType.NONE, Instructions.LCONST_0));
    list.add(new NoArgMetaInstruction("lconst_1", ArgsType.NONE, Instructions.LCONST_1));
    list.add(new NoArgMetaInstruction("fconst_0", ArgsType.NONE, Instructions.FCONST_0));
    list.add(new NoArgMetaInstruction("fconst_1", ArgsType.NONE, Instructions.FCONST_1));
    list.add(new NoArgMetaInstruction("fconst_2", ArgsType.NONE, Instructions.FCONST_2));
    list.add(new NoArgMetaInstruction("dconst_0", ArgsType.NONE, Instructions.DCONST_0));
    list.add(new NoArgMetaInstruction("dconst_1", ArgsType.NONE, Instructions.DCONST_1));
    list.add(new ByteArgMetaInstruction("bipush", ArgsType.BYTE_VALUE, new ByteArgInstructionFactory() {

      @Override
      public Instruction buildInstruction(byte b) {
        return Instructions.bipush(b);
      }
    }));
    list.add(new ShortArgMetaInstruction("sipush", ArgsType.SHORT_VALUE, new ShortArgInstructionFactory() {

      @Override
      public Instruction buildInstruction(short s) {
        return Instructions.sipush(s);
      }
    }));
    list.add(new ByteArgMetaInstruction("ldc", ArgsType.IFS_CONSTANT, new ByteArgInstructionFactory() {

      @Override
      public Instruction buildInstruction(byte b) {
        return Instructions.ldc(b);
      }
    }));
    list.add(new ShortArgMetaInstruction("ldc_w", "ldc", ArgsType.W_IFS_CONSTANT, new ShortArgInstructionFactory() {

      @Override
      public Instruction buildInstruction(short s) {
        return Instructions.ldc_w(s);
      }
    }));
    list.add(new ShortArgMetaInstruction("ldc2_w", ArgsType.LD_CONSTANT, new ShortArgInstructionFactory() {

      @Override
      public Instruction buildInstruction(short s) {
        return Instructions.ldc2_w(s);
      }
    }));
    list.add(new ByteArgMetaInstruction("iload", ArgsType.LV_INDEX, new ByteArgInstructionFactory() {

      @Override
      public Instruction buildInstruction(byte b) {
        return Instructions.iload(b);
      }
    }));
    list.add(new ByteArgMetaInstruction("lload", ArgsType.LV_INDEX, new ByteArgInstructionFactory() {

      @Override
      public Instruction buildInstruction(byte b) {
        return Instructions.lload(b);
      }
    }));
    list.add(new ByteArgMetaInstruction("fload", ArgsType.LV_INDEX, new ByteArgInstructionFactory() {

      @Override
      public Instruction buildInstruction(byte b) {
        return Instructions.fload(b);
      }
    }));
    list.add(new ByteArgMetaInstruction("dload", ArgsType.LV_INDEX, new ByteArgInstructionFactory() {

      @Override
      public Instruction buildInstruction(byte b) {
        return Instructions.dload(b);
      }
    }));
    list.add(new ByteArgMetaInstruction("aload", ArgsType.LV_INDEX, new ByteArgInstructionFactory() {

      @Override
      public Instruction buildInstruction(byte b) {
        return Instructions.aload(b);
      }
    }));
    list.add(new NoArgMetaInstruction("iload_0", ArgsType.NONE, Instructions.ILOAD_0));
    list.add(new NoArgMetaInstruction("iload_1", ArgsType.NONE, Instructions.ILOAD_1));
    list.add(new NoArgMetaInstruction("iload_2", ArgsType.NONE, Instructions.ILOAD_2));
    list.add(new NoArgMetaInstruction("iload_3", ArgsType.NONE, Instructions.ILOAD_3));
    list.add(new NoArgMetaInstruction("lload_0", ArgsType.NONE, Instructions.LLOAD_0));
    list.add(new NoArgMetaInstruction("lload_1", ArgsType.NONE, Instructions.LLOAD_1));
    list.add(new NoArgMetaInstruction("lload_2", ArgsType.NONE, Instructions.LLOAD_2));
    list.add(new NoArgMetaInstruction("lload_3", ArgsType.NONE, Instructions.LLOAD_3));
    list.add(new NoArgMetaInstruction("fload_0", ArgsType.NONE, Instructions.FLOAD_0));
    list.add(new NoArgMetaInstruction("fload_1", ArgsType.NONE, Instructions.FLOAD_1));
    list.add(new NoArgMetaInstruction("fload_2", ArgsType.NONE, Instructions.FLOAD_2));
    list.add(new NoArgMetaInstruction("fload_3", ArgsType.NONE, Instructions.FLOAD_3));
    list.add(new NoArgMetaInstruction("dload_0", ArgsType.NONE, Instructions.DLOAD_0));
    list.add(new NoArgMetaInstruction("dload_1", ArgsType.NONE, Instructions.DLOAD_1));
    list.add(new NoArgMetaInstruction("dload_2", ArgsType.NONE, Instructions.DLOAD_2));
    list.add(new NoArgMetaInstruction("dload_3", ArgsType.NONE, Instructions.DLOAD_3));
    list.add(new NoArgMetaInstruction("aload_0", ArgsType.NONE, Instructions.ALOAD_0));
    list.add(new NoArgMetaInstruction("aload_1", ArgsType.NONE, Instructions.ALOAD_1));
    list.add(new NoArgMetaInstruction("aload_2", ArgsType.NONE, Instructions.ALOAD_2));
    list.add(new NoArgMetaInstruction("aload_3", ArgsType.NONE, Instructions.ALOAD_3));
    // TODO: 0x2e to 0x35
    list.add(new ByteArgMetaInstruction("istore", ArgsType.LV_INDEX, new ByteArgInstructionFactory() {

      @Override
      public Instruction buildInstruction(byte b) {
        return Instructions.istore(b);
      }
    }));
    list.add(new ByteArgMetaInstruction("lstore", ArgsType.LV_INDEX, new ByteArgInstructionFactory() {

      @Override
      public Instruction buildInstruction(byte b) {
        return Instructions.lstore(b);
      }
    }));
    list.add(new ByteArgMetaInstruction("fstore", ArgsType.LV_INDEX, new ByteArgInstructionFactory() {

      @Override
      public Instruction buildInstruction(byte b) {
        return Instructions.fstore(b);
      }
    }));
    list.add(new ByteArgMetaInstruction("dstore", ArgsType.LV_INDEX, new ByteArgInstructionFactory() {

      @Override
      public Instruction buildInstruction(byte b) {
        return Instructions.dstore(b);
      }
    }));
    list.add(new ByteArgMetaInstruction("astore", ArgsType.LV_INDEX, new ByteArgInstructionFactory() {

      @Override
      public Instruction buildInstruction(byte b) {
        return Instructions.astore(b);
      }
    }));
    list.add(new NoArgMetaInstruction("istore_0", ArgsType.NONE, Instructions.ISTORE_0));
    list.add(new NoArgMetaInstruction("istore_1", ArgsType.NONE, Instructions.ISTORE_1));
    list.add(new NoArgMetaInstruction("istore_2", ArgsType.NONE, Instructions.ISTORE_2));
    list.add(new NoArgMetaInstruction("istore_3", ArgsType.NONE, Instructions.ISTORE_3));
    list.add(new NoArgMetaInstruction("lstore_0", ArgsType.NONE, Instructions.LSTORE_0));
    list.add(new NoArgMetaInstruction("lstore_1", ArgsType.NONE, Instructions.LSTORE_1));
    list.add(new NoArgMetaInstruction("lstore_2", ArgsType.NONE, Instructions.LSTORE_2));
    list.add(new NoArgMetaInstruction("lstore_3", ArgsType.NONE, Instructions.LSTORE_3));
    list.add(new NoArgMetaInstruction("fstore_0", ArgsType.NONE, Instructions.FSTORE_0));
    list.add(new NoArgMetaInstruction("fstore_1", ArgsType.NONE, Instructions.FSTORE_1));
    list.add(new NoArgMetaInstruction("fstore_2", ArgsType.NONE, Instructions.FSTORE_2));
    list.add(new NoArgMetaInstruction("fstore_3", ArgsType.NONE, Instructions.FSTORE_3));
    list.add(new NoArgMetaInstruction("dstore_0", ArgsType.NONE, Instructions.DSTORE_0));
    list.add(new NoArgMetaInstruction("dstore_1", ArgsType.NONE, Instructions.DSTORE_1));
    list.add(new NoArgMetaInstruction("dstore_2", ArgsType.NONE, Instructions.DSTORE_2));
    list.add(new NoArgMetaInstruction("dstore_3", ArgsType.NONE, Instructions.DSTORE_3));
    list.add(new NoArgMetaInstruction("astore_0", ArgsType.NONE, Instructions.ASTORE_0));
    list.add(new NoArgMetaInstruction("astore_1", ArgsType.NONE, Instructions.ASTORE_1));
    list.add(new NoArgMetaInstruction("astore_2", ArgsType.NONE, Instructions.ASTORE_2));
    list.add(new NoArgMetaInstruction("astore_3", ArgsType.NONE, Instructions.ASTORE_3));
    // TODO: 0x4f to 0x56
    list.add(new NoArgMetaInstruction("pop", ArgsType.NONE, Instructions.POP));
    list.add(new NoArgMetaInstruction("pop2", ArgsType.NONE, Instructions.POP2));
    list.add(new NoArgMetaInstruction("dup", ArgsType.NONE, Instructions.DUP));
    list.add(new NoArgMetaInstruction("dup_x1", ArgsType.NONE, Instructions.DUP_X1));
    list.add(new NoArgMetaInstruction("dup_x2", ArgsType.NONE, Instructions.DUP_X2));
    list.add(new NoArgMetaInstruction("dup2", ArgsType.NONE, Instructions.DUP2));
    list.add(new NoArgMetaInstruction("dup2_x1", ArgsType.NONE, Instructions.DUP2_X1));
    list.add(new NoArgMetaInstruction("dup2_x2", ArgsType.NONE, Instructions.DUP2_X2));
    list.add(new NoArgMetaInstruction("swap", ArgsType.NONE, Instructions.SWAP));
    list.add(new NoArgMetaInstruction("iadd", ArgsType.NONE, Instructions.IADD));
    list.add(new NoArgMetaInstruction("ladd", ArgsType.NONE, Instructions.LADD));
    list.add(new NoArgMetaInstruction("fadd", ArgsType.NONE, Instructions.FADD));
    list.add(new NoArgMetaInstruction("dadd", ArgsType.NONE, Instructions.DADD));
    list.add(new NoArgMetaInstruction("isub", ArgsType.NONE, Instructions.ISUB));
    list.add(new NoArgMetaInstruction("lsub", ArgsType.NONE, Instructions.LSUB));
    list.add(new NoArgMetaInstruction("fsub", ArgsType.NONE, Instructions.FSUB));
    list.add(new NoArgMetaInstruction("dsub", ArgsType.NONE, Instructions.DSUB));
    list.add(new NoArgMetaInstruction("imul", ArgsType.NONE, Instructions.IMUL));
    list.add(new NoArgMetaInstruction("lmul", ArgsType.NONE, Instructions.LMUL));
    list.add(new NoArgMetaInstruction("fmul", ArgsType.NONE, Instructions.FMUL));
    list.add(new NoArgMetaInstruction("dmul", ArgsType.NONE, Instructions.DMUL));
    list.add(new NoArgMetaInstruction("idiv", ArgsType.NONE, Instructions.IDIV));
    list.add(new NoArgMetaInstruction("ldiv", ArgsType.NONE, Instructions.LDIV));
    list.add(new NoArgMetaInstruction("fdiv", ArgsType.NONE, Instructions.FDIV));
    list.add(new NoArgMetaInstruction("ddiv", ArgsType.NONE, Instructions.DDIV));
    list.add(new NoArgMetaInstruction("irem", ArgsType.NONE, Instructions.IREM));
    list.add(new NoArgMetaInstruction("lrem", ArgsType.NONE, Instructions.LREM));
    list.add(new NoArgMetaInstruction("frem", ArgsType.NONE, Instructions.FREM));
    list.add(new NoArgMetaInstruction("drem", ArgsType.NONE, Instructions.DREM));
    list.add(new NoArgMetaInstruction("ineg", ArgsType.NONE, Instructions.INEG));
    list.add(new NoArgMetaInstruction("lneg", ArgsType.NONE, Instructions.LNEG));
    list.add(new NoArgMetaInstruction("fneg", ArgsType.NONE, Instructions.FNEG));
    list.add(new NoArgMetaInstruction("dneg", ArgsType.NONE, Instructions.DNEG));
    list.add(new NoArgMetaInstruction("ishl", ArgsType.NONE, Instructions.ISHL));
    list.add(new NoArgMetaInstruction("lshl", ArgsType.NONE, Instructions.LSHL));
    list.add(new NoArgMetaInstruction("ishr", ArgsType.NONE, Instructions.ISHR));
    list.add(new NoArgMetaInstruction("lshr", ArgsType.NONE, Instructions.LSHR));
    list.add(new NoArgMetaInstruction("iushr", ArgsType.NONE, Instructions.IUSHR));
    list.add(new NoArgMetaInstruction("lushr", ArgsType.NONE, Instructions.LUSHR));
    list.add(new NoArgMetaInstruction("iand", ArgsType.NONE, Instructions.IAND));
    list.add(new NoArgMetaInstruction("land", ArgsType.NONE, Instructions.LAND));
    list.add(new NoArgMetaInstruction("ior", ArgsType.NONE, Instructions.IOR));
    list.add(new NoArgMetaInstruction("lor", ArgsType.NONE, Instructions.LOR));
    list.add(new NoArgMetaInstruction("ixor", ArgsType.NONE, Instructions.IXOR));
    list.add(new NoArgMetaInstruction("lxor", ArgsType.NONE, Instructions.LXOR));
    list.add(new IincMetaInstruction("iinc", ArgsType.IINC, new IincInstructionFactory() {

      @Override
      public Instruction buildInstruction(byte indexInLV, byte constant) {
        return Instructions.iinc(indexInLV, constant);
      }
    }));
    list.add(new NoArgMetaInstruction("i2l", ArgsType.NONE, Instructions.I2L));
    list.add(new NoArgMetaInstruction("i2f", ArgsType.NONE, Instructions.I2F));
    list.add(new NoArgMetaInstruction("i2d", ArgsType.NONE, Instructions.I2D));
    list.add(new NoArgMetaInstruction("l2i", ArgsType.NONE, Instructions.L2I));
    list.add(new NoArgMetaInstruction("l2f", ArgsType.NONE, Instructions.L2F));
    list.add(new NoArgMetaInstruction("l2d", ArgsType.NONE, Instructions.L2D));
    list.add(new NoArgMetaInstruction("f2i", ArgsType.NONE, Instructions.F2I));
    list.add(new NoArgMetaInstruction("f2l", ArgsType.NONE, Instructions.F2L));
    list.add(new NoArgMetaInstruction("f2d", ArgsType.NONE, Instructions.F2D));
    list.add(new NoArgMetaInstruction("d2i", ArgsType.NONE, Instructions.D2I));
    list.add(new NoArgMetaInstruction("d2l", ArgsType.NONE, Instructions.D2L));
    list.add(new NoArgMetaInstruction("d2f", ArgsType.NONE, Instructions.D2F));
    list.add(new NoArgMetaInstruction("i2b", ArgsType.NONE, Instructions.I2B));
    list.add(new NoArgMetaInstruction("i2c", ArgsType.NONE, Instructions.I2C));
    list.add(new NoArgMetaInstruction("i2s", ArgsType.NONE, Instructions.I2S));
    // TODO: 0x94 to 0xab
    list.add(new NoArgMetaInstruction("ireturn", ArgsType.NONE, Instructions.IRETURN));
    list.add(new NoArgMetaInstruction("lreturn", ArgsType.NONE, Instructions.LRETURN));
    list.add(new NoArgMetaInstruction("freturn", ArgsType.NONE, Instructions.FRETURN));
    list.add(new NoArgMetaInstruction("dreturn", ArgsType.NONE, Instructions.DRETURN));
    list.add(new NoArgMetaInstruction("areturn", ArgsType.NONE, Instructions.ARETURN));
    list.add(new NoArgMetaInstruction("return", ArgsType.NONE, Instructions.RETURN));
    list.add(new WideMetaInstruction("wide", ArgsType.WIDE, new WideInstructionFactory() {

      @Override
      public Instruction buildInstruction(short indexInLV, short constant) {
        return Instructions.wide_iinc(indexInLV, constant);
      }

      @Override
      public Instruction buildInstruction(byte widenedOpcode, short indexInLV) {
        return Instructions.wide_load_store(widenedOpcode, indexInLV);
      }
    }));
    // TODO: 0xc5 to 0xc9

    return list;
  }

  private MetaInstructions() {
  }
}
