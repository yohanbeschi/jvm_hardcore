package org.isk.jvmhardcore.pjba.builder;

import org.isk.jvmhardcore.pjba.builder.MethodBuilder.InstructionWrapper;
import org.isk.jvmhardcore.pjba.instruction.LookupswitchInstruction;

public class LookupwitchBuilder {

  final private LookupswitchInstruction instruction;
  final private InstructionWrapper instructionWrapper;
  final private MethodBuilder methodBuilder;

  public LookupwitchBuilder(LookupswitchInstruction instruction,
                            InstructionWrapper instructionWrapper,
                            MethodBuilder methodBuilder) {
    this.instruction = instruction;
    this.instructionWrapper = instructionWrapper;
    this.methodBuilder = methodBuilder;
  }

  public LookupwitchBuilder matchOffset(int value, String label) {
    this.instruction.addMatchOffsetLabel(value, label);
    this.methodBuilder.addLabel(this.instructionWrapper, label);
    return this;
  }

  public MethodBuilder end() {
    return this.methodBuilder;
  }
}
