package org.isk.jvmhardcore.pjba.structure;

import java.io.DataOutput;
import java.io.IOException;

import org.isk.jvmhardcore.pjba.util.BytecodeEnabled;

public class Instruction implements BytecodeEnabled {
  final private int opcode;
  final private int stack;
  final private int locals;
  final private int length;

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

  @Override
  public void toBytecode(DataOutput dataOutput) throws IOException {
    dataOutput.writeByte(opcode);
  }
}
