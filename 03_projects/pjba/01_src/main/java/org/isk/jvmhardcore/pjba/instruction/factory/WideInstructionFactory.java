package org.isk.jvmhardcore.pjba.instruction.factory;

import org.isk.jvmhardcore.pjba.structure.Instruction;

public interface WideInstructionFactory {
  Instruction buildInstruction(byte widenedOpcode, short indexInLV);
  Instruction buildInstruction(short indexInLV, short constant);
}
