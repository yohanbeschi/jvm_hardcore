package org.isk.jvmhardcore.pjba.instruction.meta;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.isk.jvmhardcore.pjba.instruction.Instructions;
import org.isk.jvmhardcore.pjba.instruction.factory.ByteArgInstructionFactory;
import org.isk.jvmhardcore.pjba.instruction.factory.FieldAndMethodFactory;
import org.isk.jvmhardcore.pjba.instruction.factory.IincInstructionFactory;
import org.isk.jvmhardcore.pjba.instruction.factory.IntArgInstructionFactory;
import org.isk.jvmhardcore.pjba.instruction.factory.InvokeinterfaceFactory;
import org.isk.jvmhardcore.pjba.instruction.factory.LookupswitchInstructionFactory;
import org.isk.jvmhardcore.pjba.instruction.factory.MultianewarrayInstructionFactory;
import org.isk.jvmhardcore.pjba.instruction.factory.ShortArgInstructionFactory;
import org.isk.jvmhardcore.pjba.instruction.factory.TableswitchInstructionFactory;
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
    list.add(new NoArgMetaInstruction("iaload", ArgsType.NONE, Instructions.IALOAD));
    list.add(new NoArgMetaInstruction("laload", ArgsType.NONE, Instructions.LALOAD));
    list.add(new NoArgMetaInstruction("faload", ArgsType.NONE, Instructions.FALOAD));
    list.add(new NoArgMetaInstruction("daload", ArgsType.NONE, Instructions.DALOAD));
    list.add(new NoArgMetaInstruction("aaload", ArgsType.NONE, Instructions.AALOAD));
    list.add(new NoArgMetaInstruction("baload", ArgsType.NONE, Instructions.BALOAD));
    list.add(new NoArgMetaInstruction("caload", ArgsType.NONE, Instructions.CALOAD));
    list.add(new NoArgMetaInstruction("saload", ArgsType.NONE, Instructions.SALOAD));
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
    list.add(new NoArgMetaInstruction("iastore", ArgsType.NONE, Instructions.IASTORE));
    list.add(new NoArgMetaInstruction("lastore", ArgsType.NONE, Instructions.LASTORE));
    list.add(new NoArgMetaInstruction("fastore", ArgsType.NONE, Instructions.FASTORE));
    list.add(new NoArgMetaInstruction("dastore", ArgsType.NONE, Instructions.DASTORE));
    list.add(new NoArgMetaInstruction("aastore", ArgsType.NONE, Instructions.AASTORE));
    list.add(new NoArgMetaInstruction("bastore", ArgsType.NONE, Instructions.BASTORE));
    list.add(new NoArgMetaInstruction("castore", ArgsType.NONE, Instructions.CASTORE));
    list.add(new NoArgMetaInstruction("sastore", ArgsType.NONE, Instructions.SASTORE));
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
    list.add(new NoArgMetaInstruction("lcmp", ArgsType.NONE, Instructions.LCMP));
    list.add(new NoArgMetaInstruction("fcmpl", ArgsType.NONE, Instructions.FCMPL));
    list.add(new NoArgMetaInstruction("fcmpg", ArgsType.NONE, Instructions.FCMPG));
    list.add(new NoArgMetaInstruction("dcmpl", ArgsType.NONE, Instructions.DCMPL));
    list.add(new NoArgMetaInstruction("dcmpg", ArgsType.NONE, Instructions.DCMPG));
    list.add(new ShortArgMetaInstruction("ifeq", ArgsType.LABEL, new ShortArgInstructionFactory() {

      @Override
      public Instruction buildInstruction(short branch) {
        return Instructions.ifeq(branch);
      }
    }));
    list.add(new ShortArgMetaInstruction("ifne", ArgsType.LABEL, new ShortArgInstructionFactory() {

      @Override
      public Instruction buildInstruction(short branch) {
        return Instructions.ifne(branch);
      }
    }));
    list.add(new ShortArgMetaInstruction("iflt", ArgsType.LABEL, new ShortArgInstructionFactory() {

      @Override
      public Instruction buildInstruction(short branch) {
        return Instructions.iflt(branch);
      }
    }));
    list.add(new ShortArgMetaInstruction("ifge", ArgsType.LABEL, new ShortArgInstructionFactory() {

      @Override
      public Instruction buildInstruction(short branch) {
        return Instructions.ifge(branch);
      }
    }));
    list.add(new ShortArgMetaInstruction("ifgt", ArgsType.LABEL, new ShortArgInstructionFactory() {

      @Override
      public Instruction buildInstruction(short branch) {
        return Instructions.ifgt(branch);
      }
    }));
    list.add(new ShortArgMetaInstruction("ifle", ArgsType.LABEL, new ShortArgInstructionFactory() {

      @Override
      public Instruction buildInstruction(short branch) {
        return Instructions.ifle(branch);
      }
    }));
    list.add(new ShortArgMetaInstruction("if_icmpeq", ArgsType.LABEL, new ShortArgInstructionFactory() {

      @Override
      public Instruction buildInstruction(short branch) {
        return Instructions.if_icmpeq(branch);
      }
    }));
    list.add(new ShortArgMetaInstruction("if_icmpne", ArgsType.LABEL, new ShortArgInstructionFactory() {

      @Override
      public Instruction buildInstruction(short branch) {
        return Instructions.if_icmpne(branch);
      }
    }));
    list.add(new ShortArgMetaInstruction("if_icmplt", ArgsType.LABEL, new ShortArgInstructionFactory() {

      @Override
      public Instruction buildInstruction(short branch) {
        return Instructions.if_icmplt(branch);
      }
    }));
    list.add(new ShortArgMetaInstruction("if_icmpge", ArgsType.LABEL, new ShortArgInstructionFactory() {

      @Override
      public Instruction buildInstruction(short branch) {
        return Instructions.if_icmpge(branch);
      }
    }));
    list.add(new ShortArgMetaInstruction("if_icmpgt", ArgsType.LABEL, new ShortArgInstructionFactory() {

      @Override
      public Instruction buildInstruction(short branch) {
        return Instructions.if_icmpgt(branch);
      }
    }));
    list.add(new ShortArgMetaInstruction("if_icmple", ArgsType.LABEL, new ShortArgInstructionFactory() {

      @Override
      public Instruction buildInstruction(short branch) {
        return Instructions.if_icmple(branch);
      }
    }));
    list.add(new ShortArgMetaInstruction("if_acmpeq", ArgsType.LABEL, new ShortArgInstructionFactory() {

      @Override
      public Instruction buildInstruction(short branch) {
        return Instructions.if_acmpeq(branch);
      }
    }));
    list.add(new ShortArgMetaInstruction("if_acmpne", ArgsType.LABEL, new ShortArgInstructionFactory() {

      @Override
      public Instruction buildInstruction(short branch) {
        return Instructions.if_acmpne(branch);
      }
    }));
    list.add(new ShortArgMetaInstruction("goto", ArgsType.GOTO, new ShortArgInstructionFactory() {
      @Override
      public Instruction buildInstruction(short branch) {
        return Instructions.goto_(branch);
      }
    }));
    // TODO: 0xa8 to 0xa9
    list.add(new TableswitchMetaInstruction("tableswitch", ArgsType.TABLE_SWITCH, new TableswitchInstructionFactory() {
      @Override
      public Instruction buildInstruction(int padding, int defaultOffset, int lowValue, int highValue, int[] jumpOffsets) {
        return Instructions.tableswitch(padding, defaultOffset, lowValue, highValue, jumpOffsets);
      }
    }));
    list.add(new LookupswitchMetaInstruction("lookupswitch", ArgsType.LOOKUP_SWITCH, new LookupswitchInstructionFactory() {
      @Override
      public Instruction buildInstruction(int padding,  int defaultOffset, int nbPairs, int[] keys, int[] offsets) {
        return Instructions.lookupswitch(padding, defaultOffset, nbPairs, keys, offsets);
      }
    }));
    list.add(new NoArgMetaInstruction("ireturn", ArgsType.NONE, Instructions.IRETURN));
    list.add(new NoArgMetaInstruction("lreturn", ArgsType.NONE, Instructions.LRETURN));
    list.add(new NoArgMetaInstruction("freturn", ArgsType.NONE, Instructions.FRETURN));
    list.add(new NoArgMetaInstruction("dreturn", ArgsType.NONE, Instructions.DRETURN));
    list.add(new NoArgMetaInstruction("areturn", ArgsType.NONE, Instructions.ARETURN));
    list.add(new NoArgMetaInstruction("return", ArgsType.NONE, Instructions.RETURN));
    list.add(new FieldAndMethodMetaInstruction("getstatic", ArgsType.FIELD, new FieldAndMethodFactory() {
      
      @Override
      public Instruction buildInstruction(short indexInCP, int sizeInStack) {
        return Instructions.getstatic(indexInCP, sizeInStack);
      }
    }));
    list.add(new FieldAndMethodMetaInstruction("putstatic", ArgsType.FIELD, new FieldAndMethodFactory() {

      @Override
      public Instruction buildInstruction(short indexInCP, int sizeInStack) {
        return Instructions.putstatic(indexInCP, sizeInStack);
      }
    }));
    list.add(new FieldAndMethodMetaInstruction("getfield", ArgsType.FIELD, new FieldAndMethodFactory() {
      
      @Override
      public Instruction buildInstruction(short indexInCP, int sizeInStack) {
        return Instructions.getfield(indexInCP, sizeInStack);
      }
    }));
    list.add(new FieldAndMethodMetaInstruction("putfield", ArgsType.FIELD, new FieldAndMethodFactory() {

      @Override
      public Instruction buildInstruction(short indexInCP, int sizeInStack) {
        return Instructions.putfield(indexInCP, sizeInStack);
      }
    }));
    list.add(new FieldAndMethodMetaInstruction("invokevirtual", ArgsType.METHOD, new FieldAndMethodFactory() {

      @Override
      public Instruction buildInstruction(short indexInCP, int sizeInStack) {
        return Instructions.invokevirtual(indexInCP, sizeInStack);
      }
    }));
    list.add(new FieldAndMethodMetaInstruction("invokespecial", ArgsType.METHOD, new FieldAndMethodFactory() {

      @Override
      public Instruction buildInstruction(short indexInCP, int sizeInStack) {
        return Instructions.invokespecial(indexInCP, sizeInStack);
      }
    }));
    list.add(new FieldAndMethodMetaInstruction("invokestatic", ArgsType.METHOD, new FieldAndMethodFactory() {

      @Override
      public Instruction buildInstruction(short indexInCP, int sizeInStack) {
        return Instructions.invokestatic(indexInCP, sizeInStack);
      }
    }));
    list.add(new InvokeinterfaceMetaInstruction("invokeinterface", ArgsType.METHOD, new InvokeinterfaceFactory() {

      @Override
      public Instruction buildInstruction(short indexInCP, int sizeInStack, int paramsCount) {
        return Instructions.invokeinterface(indexInCP, sizeInStack, paramsCount);
      }
    }));
    // TODO: 0xba
    list.add(new ShortArgMetaInstruction("new", ArgsType.CLASS, new ShortArgInstructionFactory() {

      @Override
      public Instruction buildInstruction(short indexInCP) {
        return Instructions.new_(indexInCP);
      }
    }));
    list.add(new ByteArgMetaInstruction("newarray", ArgsType.ARRAY_TYPE, new ByteArgInstructionFactory() {

      @Override
      public Instruction buildInstruction(byte arrayType) {
        return Instructions.newarray(arrayType);
      }
    }));
    list.add(new ShortArgMetaInstruction("anewarray", ArgsType.CLASS, new ShortArgInstructionFactory() {

      @Override
      public Instruction buildInstruction(short indexInCP) {
        return Instructions.anewarray(indexInCP);
      }
    }));
    list.add(new NoArgMetaInstruction("arraylength", ArgsType.NONE, Instructions.ARRAYLENGTH));
    // TODO: 0xbf
    list.add(new ShortArgMetaInstruction("checkcast", ArgsType.CLASS, new ShortArgInstructionFactory() {

      @Override
      public Instruction buildInstruction(short indexInCP) {
        return Instructions.checkcast(indexInCP);
      }
    }));
    list.add(new ShortArgMetaInstruction("instanceof", ArgsType.CLASS, new ShortArgInstructionFactory() {

      @Override
      public Instruction buildInstruction(short indexInCP) {
        return Instructions.instanceof_(indexInCP);
      }
    }));
    // TODO: 0xc2 and 0xc3
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
    list.add(new MultianewarrayMetaInstruction("multianewarray", ArgsType.ARRAY_MULTIDIM, new MultianewarrayInstructionFactory() {

      @Override
      public Instruction buildInstruction(short indexInCP, byte dimensions) {
        return Instructions.multianewarray(indexInCP, dimensions);
      }
    }));
    list.add(new ShortArgMetaInstruction("ifnull", ArgsType.LABEL, new ShortArgInstructionFactory() {

      @Override
      public Instruction buildInstruction(short branch) {
        return Instructions.ifnull(branch);
      }
    }));
    list.add(new ShortArgMetaInstruction("ifnonnull", ArgsType.LABEL, new ShortArgInstructionFactory() {

      @Override
      public Instruction buildInstruction(short branch) {
        return Instructions.ifnonnull(branch);
      }
    }));
    list.add(new IntArgMetaInstruction("goto_w", "goto", ArgsType.GOTO_W, new IntArgInstructionFactory() {

      @Override
      public Instruction buildInstruction(int branch) {
        return Instructions.goto_w(branch);
      }
    }));
    // TODO: 0xc8 and 0xc9

    return list;
  }

  private MetaInstructions() {
  }
}
