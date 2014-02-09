package org.isk.jvmhardcore.pjba.instruction.factory;

import org.isk.jvmhardcore.pjba.structure.Instruction;

public interface ShortArgInstructionFactory {
  Instruction buildInstruction(short s);
}
