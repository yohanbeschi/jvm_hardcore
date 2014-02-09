package org.isk.jvmhardcore.pjba.instruction.factory;

import org.isk.jvmhardcore.pjba.structure.Instruction;

public interface ByteArgInstructionFactory {
  Instruction buildInstruction(byte b);
}
