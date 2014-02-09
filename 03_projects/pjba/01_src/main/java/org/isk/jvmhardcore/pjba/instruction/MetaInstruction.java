package org.isk.jvmhardcore.pjba.instruction;

import org.isk.jvmhardcore.pjba.structure.Instruction;

public class MetaInstruction {

  final private int opcode;
  final private String mnemonic;
  final private Instruction instruction;

  public MetaInstruction(final String mnemonic, final Instruction instruction) {
    this.mnemonic = mnemonic;
    this.instruction = instruction;
    this.opcode = instruction.getOpcode();
  }

  public int getOpcode() {
    return this.opcode;
  }

  public String getMnemonic() {
    return this.mnemonic;
  }

  public Instruction getInstruction() {
    return this.instruction;
  }
}
