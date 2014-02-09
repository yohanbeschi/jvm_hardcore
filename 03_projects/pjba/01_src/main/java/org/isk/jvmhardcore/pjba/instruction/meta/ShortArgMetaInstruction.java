package org.isk.jvmhardcore.pjba.instruction.meta;


import org.isk.jvmhardcore.pjba.instruction.factory.ShortArgInstructionFactory;
import org.isk.jvmhardcore.pjba.structure.Instruction;

public class ShortArgMetaInstruction extends MetaInstruction {
  final private static short SHORT_ZERO = 0;

  private ShortArgInstructionFactory instructionBuilder;

  public ShortArgMetaInstruction(final String mnemonic,
                                 final ArgsType argsType,
                                 final ShortArgInstructionFactory instructionBuilder) {
    this(mnemonic, mnemonic, argsType, instructionBuilder);
  }

  public ShortArgMetaInstruction(final String mnemonic,
                                 final String pjbMnemonic,
                                 final ArgsType argsType,
                                 final ShortArgInstructionFactory instructionBuilder) {
    super(mnemonic, pjbMnemonic, argsType);

    this.instructionBuilder = instructionBuilder;
    this.opcode = instructionBuilder.buildInstruction(SHORT_ZERO).getOpcode();
  }

  public Instruction buildInstruction(short s) {
    return this.instructionBuilder.buildInstruction(s);
  }
}
