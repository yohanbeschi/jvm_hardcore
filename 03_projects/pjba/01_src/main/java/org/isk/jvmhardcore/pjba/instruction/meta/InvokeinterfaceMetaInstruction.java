package org.isk.jvmhardcore.pjba.instruction.meta;

import org.isk.jvmhardcore.pjba.instruction.factory.InvokeinterfaceFactory;
import org.isk.jvmhardcore.pjba.structure.Instruction;

public class InvokeinterfaceMetaInstruction extends MetaInstruction {
  final private static short BYTE_ZERO = 0;
  final private static short SHORT_ZERO = 0;

  private InvokeinterfaceFactory instructionBuilder;

  public InvokeinterfaceMetaInstruction(final String mnemonic,
                             final ArgsType argsType,
                             final InvokeinterfaceFactory instructionBuilder) {
    super(mnemonic, mnemonic, argsType);

    this.instructionBuilder = instructionBuilder;
    this.opcode = instructionBuilder.buildInstruction(SHORT_ZERO, SHORT_ZERO, BYTE_ZERO).getOpcode();
  }

  public Instruction buildInstruction(short indexInCP, int sizeInStack, int paramsCount) {
    return this.instructionBuilder.buildInstruction(indexInCP, sizeInStack, paramsCount);
  }
}
