package org.isk.jvmhardcore.pjba.instruction;

import org.isk.jvmhardcore.pjba.structure.Instruction;
import org.isk.jvmhardcore.pjba.visitor.Visitor;

public class MultianewarrayInstruction extends Instruction {

  private final int indexInCP;
  private final int dimensions;
  
  public MultianewarrayInstruction(int opcode, int stack, int locals, int indexInCP, int dimensions) {
    super(opcode, stack, locals, 4);
    this.indexInCP = indexInCP;
    this.dimensions = dimensions;
  }

  @Override
  public void accept(Visitor visitor) {
    super.accept(visitor);
    visitor.visitMultinewarrayInstruction(this.indexInCP, this.dimensions);
  }
}
