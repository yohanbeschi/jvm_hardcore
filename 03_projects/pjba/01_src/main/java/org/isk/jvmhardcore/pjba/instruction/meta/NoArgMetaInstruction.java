package org.isk.jvmhardcore.pjba.instruction.meta;

import org.isk.jvmhardcore.pjba.structure.Instruction;

public class NoArgMetaInstruction extends MetaInstruction {

  private Instruction instruction;

  public NoArgMetaInstruction(final String mnemonic,
                              final ArgsType argsType,
                              final Instruction instruction) {
    this(mnemonic, mnemonic, argsType, instruction);
  }

  public NoArgMetaInstruction(final String mnemonic,
                              final String pjbMnemonic,
                              final ArgsType argsType,
                              final Instruction instruction) {
    super(mnemonic, pjbMnemonic, argsType);

    this.instruction = instruction;
    this.opcode = instruction.getOpcode();
  }

  public Instruction buildInstruction() {
    return instruction;
  }
}
