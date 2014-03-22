package org.isk.jvmhardcore.pjba.instruction.factory;

import org.isk.jvmhardcore.pjba.structure.Instruction;

public interface FieldAndMethodFactory {
  Instruction buildInstruction(short s, int sizeInStack);
}
