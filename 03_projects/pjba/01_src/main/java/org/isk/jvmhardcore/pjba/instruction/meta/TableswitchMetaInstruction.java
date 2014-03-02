package org.isk.jvmhardcore.pjba.instruction.meta;

import org.isk.jvmhardcore.pjba.instruction.factory.TableswitchInstructionFactory;
import org.isk.jvmhardcore.pjba.structure.Instruction;

public class TableswitchMetaInstruction extends MetaInstruction {

  final private TableswitchInstructionFactory instructionBuilder;

  public TableswitchMetaInstruction(final String mnemonic,
                                     final ArgsType argsType,
                                     final TableswitchInstructionFactory instructionBuilder) {
    super(mnemonic, argsType);

    this.instructionBuilder = instructionBuilder;
    this.opcode = this.instructionBuilder.buildInstruction(0, 0, 0, 0, new int[0]).getOpcode();
  }

  public Instruction buildInstruction(int padding, int defaultOffset, int lowValue, int highValue, int[] jumpOffsets) {
    return this.instructionBuilder.buildInstruction(padding, defaultOffset, lowValue, highValue, jumpOffsets);
  }
}
