package org.isk.jvmhardcore.pjba.instruction;

import org.isk.jvmhardcore.pjba.structure.Instruction;

public class Instructions {
  final public static int ILOAD_0_OPCODE = 0x1a;
  final public static int ILOAD_1_OPCODE = 0x1b;
  final public static int IADD_OPCODE = 0x60;
  final public static int IRETURN_OPCODE = 0xac;

  final public static Instruction ILOAD_0 = new NoArgInstruction(ILOAD_0_OPCODE, 1, 1);
  final public static Instruction ILOAD_1 = new NoArgInstruction(ILOAD_1_OPCODE, 1, 2);
  final public static Instruction IADD = new NoArgInstruction(IADD_OPCODE, -1, 0);
  final public static Instruction IRETURN = new NoArgInstruction(IRETURN_OPCODE, -1, 0);

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
