package org.isk.jvmhardcore.pjba.instruction;

import org.isk.jvmhardcore.pjba.structure.Instruction;
import org.isk.jvmhardcore.pjba.visitor.Visitor;

public class InvokeinterfaceInstruction extends Instruction {

  final private int indexInCP;
  final private int paramsCount;
  
  public InvokeinterfaceInstruction(int opcode, int stack, int locals, int indexInCP, int paramsCount) {
    super(opcode, stack, locals, 5);
    this.indexInCP = indexInCP;
    this.paramsCount = paramsCount ;
  }
  
  @Override
  public void accept(Visitor visitor) {
    super.accept(visitor);
    visitor.visitInvokeinterface(this.indexInCP, this.paramsCount, 0);
  }
}
