package org.isk.jvmhardcore.pjba.instruction.meta;

import org.isk.jvmhardcore.pjba.instruction.factory.MultianewarrayInstructionFactory;
import org.isk.jvmhardcore.pjba.structure.Instruction;

public class MultianewarrayMetaInstruction  extends MetaInstruction {
  final private static byte BYTE_ZERO = 0;
  final private static short SHORT_ZERO = 0;

  private MultianewarrayInstructionFactory instructionBuilder;

  public MultianewarrayMetaInstruction(final String mnemonic,
                                       final ArgsType argsType,
                                       final MultianewarrayInstructionFactory instructionBuilder) {
    super(mnemonic, argsType);
    this.instructionBuilder = instructionBuilder;
    this.opcode = instructionBuilder.buildInstruction(SHORT_ZERO, BYTE_ZERO).getOpcode();
  }

  public Instruction buildInstruction(short indexInCP, byte dimensions) {
    return this.instructionBuilder.buildInstruction(indexInCP, dimensions);
  }
}
