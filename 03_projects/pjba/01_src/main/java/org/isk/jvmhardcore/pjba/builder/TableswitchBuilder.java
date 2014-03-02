package org.isk.jvmhardcore.pjba.builder;

import org.isk.jvmhardcore.pjba.builder.MethodBuilder.InstructionWrapper;
import org.isk.jvmhardcore.pjba.instruction.TableswitchInstruction;

public class TableswitchBuilder {

  final private TableswitchInstruction instruction;
  final private InstructionWrapper instructionWrapper;
  final private MethodBuilder methodBuilder;
  
  public TableswitchBuilder(TableswitchInstruction instruction,
                            InstructionWrapper instructionWrapper,
                            MethodBuilder methodBuilder) {
    this.instruction = instruction;
    this.instructionWrapper = instructionWrapper;
    this.methodBuilder = methodBuilder;
  }

  public TableswitchBuilder offset(String label) {
    this.instruction.addOffsetLabel(label);
    this.methodBuilder.addLabel(this.instructionWrapper, label);
    return this;
  }

  public MethodBuilder end() {
    return this.methodBuilder;
  }
}
