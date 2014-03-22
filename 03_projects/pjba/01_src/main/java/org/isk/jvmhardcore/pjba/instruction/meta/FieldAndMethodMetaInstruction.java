package org.isk.jvmhardcore.pjba.instruction.meta;

import org.isk.jvmhardcore.pjba.instruction.factory.FieldAndMethodFactory;
import org.isk.jvmhardcore.pjba.structure.Instruction;

public class FieldAndMethodMetaInstruction extends MetaInstruction {
  final private static short SHORT_ZERO = 0;

  private FieldAndMethodFactory instructionBuilder;

  public FieldAndMethodMetaInstruction(final String mnemonic,
                             final ArgsType argsType,
                             final FieldAndMethodFactory instructionBuilder) {
    super(mnemonic, mnemonic, argsType);

    this.instructionBuilder = instructionBuilder;
    this.opcode = instructionBuilder.buildInstruction(SHORT_ZERO, SHORT_ZERO).getOpcode();
  }

  public Instruction buildInstruction(short indexInCP, int sizeInStack) {
    return this.instructionBuilder.buildInstruction(indexInCP, sizeInStack);
  }
}
