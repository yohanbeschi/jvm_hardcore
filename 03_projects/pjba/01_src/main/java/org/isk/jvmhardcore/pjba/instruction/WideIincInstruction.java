package org.isk.jvmhardcore.pjba.instruction;

import org.isk.jvmhardcore.pjba.structure.Instruction;
import org.isk.jvmhardcore.pjba.visitor.Visitor;

public class WideIincInstruction extends Instruction {

  final private int widenedOpcode;
  final private int indexInLV;
  final private int constant;

  public WideIincInstruction(int opcode, int stack, int locals, int widenedOpcode, int indexInLV, int constant) {
    super(opcode, stack, locals, 6);
    this.widenedOpcode = widenedOpcode;
    this.indexInLV = indexInLV;
    this.constant = constant;
  }

  @Override
  public void accept(Visitor visitor) {
    super.accept(visitor);
    visitor.visitInstructionWideIinc(this.widenedOpcode, this.indexInLV, this.constant);
  }
}
