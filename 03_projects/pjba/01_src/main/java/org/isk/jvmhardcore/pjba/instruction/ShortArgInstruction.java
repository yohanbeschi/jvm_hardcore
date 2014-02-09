package org.isk.jvmhardcore.pjba.instruction;

import org.isk.jvmhardcore.pjba.structure.Instruction;
import org.isk.jvmhardcore.pjba.visitor.Visitor;

public class ShortArgInstruction extends Instruction {

  final private int arg;

  public ShortArgInstruction(int opcode, int stack, int locals, int arg) {
    super(opcode, stack, locals, 3);
    this.arg = arg;
  }

  @Override
  public void accept(Visitor visitor) {
    super.accept(visitor);
    visitor.visitInstructionShort(this.arg);
  }
}
