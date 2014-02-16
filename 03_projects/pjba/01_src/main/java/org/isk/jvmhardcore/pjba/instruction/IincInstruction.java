package org.isk.jvmhardcore.pjba.instruction;

import org.isk.jvmhardcore.pjba.structure.Instruction;
import org.isk.jvmhardcore.pjba.visitor.Visitor;

public class IincInstruction extends Instruction {
  
  final private int indexInLV;
  final private int constant;
  
  public IincInstruction(int opcode, int stack, int locals, int indexInLV, int constant) {
    super(opcode, stack, locals, 3);
    this.indexInLV = indexInLV;
    this.constant = constant;
  }

  @Override
  public void accept(Visitor visitor) {
    super.accept(visitor);
    visitor.visitInstructionIinc(this.indexInLV, this.constant);
  }
}
