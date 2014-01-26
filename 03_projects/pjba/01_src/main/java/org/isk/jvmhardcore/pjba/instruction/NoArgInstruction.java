package org.isk.jvmhardcore.pjba.instruction;

import org.isk.jvmhardcore.pjba.structure.Instruction;

public class NoArgInstruction extends Instruction {

  public NoArgInstruction(int opcode, int stack, int locals) {
    super(opcode, stack, locals, 1);
  }
}
