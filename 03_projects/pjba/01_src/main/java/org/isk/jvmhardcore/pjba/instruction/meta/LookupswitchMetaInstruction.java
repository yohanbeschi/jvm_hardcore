package org.isk.jvmhardcore.pjba.instruction.meta;

import org.isk.jvmhardcore.pjba.instruction.factory.LookupswitchInstructionFactory;
import org.isk.jvmhardcore.pjba.structure.Instruction;

public class LookupswitchMetaInstruction extends MetaInstruction {

  final private LookupswitchInstructionFactory instructionBuilder;

  public LookupswitchMetaInstruction(final String mnemonic,
                                     final ArgsType argsType,
                                     final LookupswitchInstructionFactory instructionBuilder) {
    super(mnemonic, argsType);

    this.instructionBuilder = instructionBuilder;
    this.opcode = this.instructionBuilder.buildInstruction(0, 0, 0, new int[0], new int[0]).getOpcode();
  }

  public Instruction buildInstruction(int padding,  int defaultOffset, int nbPairs, int[] keys, int[] offsets) {
    return this.instructionBuilder.buildInstruction(padding, defaultOffset, nbPairs, keys, offsets);
  }
}
