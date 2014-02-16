package org.isk.jvmhardcore.pjba.instruction.factory;

import org.isk.jvmhardcore.pjba.structure.Instruction;

public interface IincInstructionFactory {
  Instruction buildInstruction(byte indexInLV, byte constant);
}
