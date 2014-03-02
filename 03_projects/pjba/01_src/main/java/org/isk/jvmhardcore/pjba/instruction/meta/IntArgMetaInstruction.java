package org.isk.jvmhardcore.pjba.instruction.meta;


import org.isk.jvmhardcore.pjba.instruction.factory.IntArgInstructionFactory;
import org.isk.jvmhardcore.pjba.structure.Instruction;

public class IntArgMetaInstruction extends MetaInstruction {
  private IntArgInstructionFactory instructionBuilder;

  public IntArgMetaInstruction(final String mnemonic,
                               final ArgsType argsType,
                               final IntArgInstructionFactory instructionBuilder) {
    this(mnemonic, mnemonic, argsType, instructionBuilder);
  }

  public IntArgMetaInstruction(final String mnemonic,
                               final String pjbMnemonic,
                               final ArgsType argsType,
                               final IntArgInstructionFactory instructionBuilder) {
    super(mnemonic, pjbMnemonic, argsType);

    this.instructionBuilder = instructionBuilder;
    this.opcode = instructionBuilder.buildInstruction(0).getOpcode();
  }

  public Instruction buildInstruction(int i) {
    return this.instructionBuilder.buildInstruction(i);
  }
}
