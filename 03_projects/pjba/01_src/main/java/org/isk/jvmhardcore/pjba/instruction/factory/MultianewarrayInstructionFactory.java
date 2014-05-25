package org.isk.jvmhardcore.pjba.instruction.factory;

import org.isk.jvmhardcore.pjba.structure.Instruction;

public interface MultianewarrayInstructionFactory {
  Instruction buildInstruction(short indexInCP, byte dimensions);
}
