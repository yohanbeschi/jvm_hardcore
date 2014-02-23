package org.isk.jvmhardcore.pjba.structure;

import org.isk.jvmhardcore.pjba.visitor.Visitable;
import org.isk.jvmhardcore.pjba.visitor.Visitor;

public class Instruction implements Visitable {
  final private int opcode;
  final private int stack;
  final private int locals;
  private int length;

  public Instruction(int opcode, int stack, int locals, int length) {
    this.opcode = opcode;
    this.stack = stack;
    this.locals = locals;
    this.length = length;
  }

  public int getOpcode() {
    return opcode;
  }

  public int getStack() {
    return this.stack;
  }

  public int getLocals() {
    return this.locals;
  }

  public int getLength() {
    return this.length;
  }

  protected void setLength(int length) {
    this.length = length;
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visitOpcode(this.opcode);
  }
}
