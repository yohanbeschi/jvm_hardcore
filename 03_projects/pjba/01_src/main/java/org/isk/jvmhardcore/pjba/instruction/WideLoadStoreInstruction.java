package org.isk.jvmhardcore.pjba.instruction;

import org.isk.jvmhardcore.pjba.structure.Instruction;
import org.isk.jvmhardcore.pjba.visitor.Visitor;

public class WideLoadStoreInstruction extends Instruction {

  final private int widenedOpcode;
  final private int indexInLV;

  public WideLoadStoreInstruction(int opcode, int stack, int locals, int widenedOpcode, int indexInLV) {
    super(opcode, stack, locals, 4);
    this.widenedOpcode = widenedOpcode;
    this.indexInLV = indexInLV;
  }

  @Override
  public void accept(Visitor visitor) {
    super.accept(visitor);
    visitor.visitInstructionWideLoadStore(this.widenedOpcode, this.indexInLV);
  }
}
