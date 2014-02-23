package org.isk.jvmhardcore.pjba.instruction.interfaces;

public interface LabeledInstruction {
  void setOffset(String label, int offset);
}
