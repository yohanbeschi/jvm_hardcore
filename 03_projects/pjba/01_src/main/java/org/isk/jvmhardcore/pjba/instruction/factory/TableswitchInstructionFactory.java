package org.isk.jvmhardcore.pjba.instruction.factory;

import org.isk.jvmhardcore.pjba.structure.Instruction;

public interface TableswitchInstructionFactory {
  Instruction buildInstruction(int padding, int defaultOffset, int lowValue, int highValue, int[] jumpOffsets);
}
