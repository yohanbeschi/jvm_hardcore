package org.isk.jvmhardcore.pjba.instruction;

import org.isk.jvmhardcore.pjba.instruction.interfaces.LabeledInstruction;
import org.isk.jvmhardcore.pjba.structure.Instruction;
import org.isk.jvmhardcore.pjba.visitor.Visitor;

public class IntArgInstruction extends Instruction implements LabeledInstruction {

  private int arg;

  public IntArgInstruction(int opcode, int stack, int locals, int arg) {
    super(opcode, stack, locals, 5);
    this.arg = arg;
  }

  @Override
  public void setOffset(String label, int offset) {
    this.arg = offset;
  }

  @Override
  public void accept(Visitor visitor) {
    super.accept(visitor);
    visitor.visitInstructionInt(this.arg);
  }
}
