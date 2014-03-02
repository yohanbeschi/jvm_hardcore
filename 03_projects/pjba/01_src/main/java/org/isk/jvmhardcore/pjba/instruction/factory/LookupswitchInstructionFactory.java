package org.isk.jvmhardcore.pjba.instruction.factory;

import org.isk.jvmhardcore.pjba.structure.Instruction;

public interface LookupswitchInstructionFactory {
  Instruction buildInstruction(int padding,  int defaultOffset, int nbPairs, int[] keys, int[] offsets);
}
