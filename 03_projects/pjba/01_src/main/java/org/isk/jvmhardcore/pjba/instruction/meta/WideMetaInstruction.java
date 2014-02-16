package org.isk.jvmhardcore.pjba.instruction.meta;

import org.isk.jvmhardcore.pjba.instruction.factory.WideInstructionFactory;
import org.isk.jvmhardcore.pjba.structure.Instruction;

public class WideMetaInstruction extends MetaInstruction {
  final private static short SHORT_ZERO = 0;

  private WideInstructionFactory instructionBuilder;

  public WideMetaInstruction(final String mnemonic,
                                final ArgsType argsType,
                                final WideInstructionFactory instructionBuilder) {
    super(mnemonic, mnemonic, argsType);
    
    this.instructionBuilder = instructionBuilder;
    this.opcode = instructionBuilder.buildInstruction(SHORT_ZERO, SHORT_ZERO).getOpcode();
  }

  public Instruction buildInstruction(byte widenedOpcode, short indexInLV) {
    return this.instructionBuilder.buildInstruction(widenedOpcode, indexInLV);
  }
  
  public Instruction buildInstruction(short indexInLV, short constant) {
    return this.instructionBuilder.buildInstruction(indexInLV, constant);
  }
}
