package org.isk.jvmhardcore.pjba.instruction.factory;

import org.isk.jvmhardcore.pjba.structure.Instruction;

public interface InvokeinterfaceFactory {
  Instruction buildInstruction(short s, int sizeInStack, int paramsCount);
}
