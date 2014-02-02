package org.isk.jvmhardcore.pjba.instruction;

import java.util.HashMap;
import java.util.Map;

import org.isk.jvmhardcore.pjba.structure.Instruction;

public class Instructions {
  final public static String ILOAD_0_MNEMONIC = "iload_0";
  final public static String ILOAD_1_MNEMONIC = "iload_1";
  final public static String IADD_MNEMONIC = "iadd";
  final public static String IRETURN_MNEMONIC = "ireturn";

  final public static int ILOAD_0_OPCODE = 0x1a;
  final public static int ILOAD_1_OPCODE = 0x1b;
  final public static int IADD_OPCODE = 0x60;
  final public static int IRETURN_OPCODE = 0xac;

  final public static Instruction ILOAD_0 = new NoArgInstruction(ILOAD_0_OPCODE, 1, 1);
  final public static Instruction ILOAD_1 = new NoArgInstruction(ILOAD_1_OPCODE, 1, 2);
  final public static Instruction IADD = new NoArgInstruction(IADD_OPCODE, -1, 0);
  final public static Instruction IRETURN = new NoArgInstruction(IRETURN_OPCODE, -1, 0);

  final private static Map<Integer, String> OPCODE_TO_MNEMONIC;
  final private static Map<Integer, Instruction> OPCODE_TO_INSTRUCTION;

  static {
    OPCODE_TO_MNEMONIC = new HashMap<>();
    OPCODE_TO_MNEMONIC.put(ILOAD_0_OPCODE, ILOAD_0_MNEMONIC);
    OPCODE_TO_MNEMONIC.put(ILOAD_1_OPCODE, ILOAD_1_MNEMONIC);
    OPCODE_TO_MNEMONIC.put(IADD_OPCODE, IADD_MNEMONIC);
    OPCODE_TO_MNEMONIC.put(IRETURN_OPCODE, IRETURN_MNEMONIC);

    OPCODE_TO_INSTRUCTION = new HashMap<>();
    OPCODE_TO_INSTRUCTION.put(ILOAD_0_OPCODE, ILOAD_0);
    OPCODE_TO_INSTRUCTION.put(ILOAD_1_OPCODE, ILOAD_1);
    OPCODE_TO_INSTRUCTION.put(IADD_OPCODE, IADD);
    OPCODE_TO_INSTRUCTION.put(IRETURN_OPCODE, IRETURN);
  }

  public static String getMnemonic(final int opcode) {
    return OPCODE_TO_MNEMONIC.get(opcode);
  }

  public static Instruction getInstruction(final int opcode) {
    return OPCODE_TO_INSTRUCTION.get(opcode);
  }

  public static Instruction iload_0() {
    return ILOAD_0;
  }

  public static Instruction iload_1() {
    return ILOAD_1;
  }

  public static Instruction iadd() {
    return IADD;
  }

  public static Instruction ireturn() {
    return IRETURN;
  }
}
