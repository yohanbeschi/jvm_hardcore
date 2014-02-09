package org.isk.jvmhardcore.pjba.instruction;

import org.isk.jvmhardcore.pjba.structure.Instruction;
import org.isk.jvmhardcore.pjba.visitor.Visitor;

public class ByteArgInstruction extends Instruction {

  final private int arg;

  public ByteArgInstruction(int opcode, int stack, int locals, int arg) {
    super(opcode, stack, locals, 2);
    this.arg = arg;
  }

  @Override
  public void accept(Visitor visitor) {
    super.accept(visitor);
    visitor.visitInstructionByte(this.arg);
  }
}
