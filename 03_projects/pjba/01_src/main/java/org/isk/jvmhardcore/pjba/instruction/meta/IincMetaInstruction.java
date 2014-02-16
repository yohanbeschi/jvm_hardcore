package org.isk.jvmhardcore.pjba.instruction.meta;

import org.isk.jvmhardcore.pjba.instruction.factory.IincInstructionFactory;
import org.isk.jvmhardcore.pjba.structure.Instruction;

public class IincMetaInstruction extends MetaInstruction {
  final private static byte BYTE_ZERO = 0;

  private IincInstructionFactory instructionBuilder;

  public IincMetaInstruction(final String mnemonic,
                                final ArgsType argsType,
                                final IincInstructionFactory instructionBuilder) {
    super(mnemonic, mnemonic, argsType);
    
    this.instructionBuilder = instructionBuilder;
    this.opcode = instructionBuilder.buildInstruction(BYTE_ZERO, BYTE_ZERO).getOpcode();
  }

  public Instruction buildInstruction(byte indexInLV, byte constant) {
    return this.instructionBuilder.buildInstruction(indexInLV, constant);
  }
}
