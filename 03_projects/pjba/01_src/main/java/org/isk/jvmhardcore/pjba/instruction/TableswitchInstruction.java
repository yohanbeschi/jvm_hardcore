package org.isk.jvmhardcore.pjba.instruction;

import java.util.LinkedList;
import java.util.List;

import org.isk.jvmhardcore.pjba.instruction.interfaces.LabeledInstruction;
import org.isk.jvmhardcore.pjba.instruction.interfaces.SwitchInstruction;
import org.isk.jvmhardcore.pjba.structure.Instruction;
import org.isk.jvmhardcore.pjba.visitor.Visitor;

public class TableswitchInstruction extends Instruction implements LabeledInstruction, SwitchInstruction {
  final private List<Label> labels;
  private Label defaultLabel;
  
  private int padding;
  private int defaultOffset;
  final private int lowValue;
  final private int highValue;
  final private int[] jumpOffsets;

  public TableswitchInstruction(int opcode, int stack, int locals,
                                int padding, int defaultOffset,
                                int lowValue, int highValue, int[] jumpOffsets) {
    super(opcode, stack, locals, getLength(padding, jumpOffsets.length));
    this.padding = padding;
    this.defaultOffset = defaultOffset;
    this.lowValue = lowValue;
    this.highValue = highValue;
    this.jumpOffsets = jumpOffsets;

    this.labels = new LinkedList<>();
  }

  public static int getLength(int padding, int offsets) {
    return 13 + padding + 4 * offsets; // 1 + padding + 4 + 4 + 4 + 4 * jumpOffsets.length
  }

  @Override
  public void setPadding(int padding) {
    this.padding = padding;
    super.setLength(getLength(padding, jumpOffsets.length));
  }

  public void setDefaultLabel(String defaultLabel) {
    this.defaultLabel = new Label(defaultLabel);
  }

  public void addOffsetLabel(String label) {
    this.labels.add(new Label(label));
  }

  @Override
  public void setOffset(String label, int offset) {
    if (this.defaultLabel != null && this.defaultLabel.equals(new Label(label))) {
      this.defaultOffset = offset;
      this.defaultLabel.complete = true;
      return;
    }

    final int index = this.labels.indexOf(new Label(label));

    if (index == -1) {
      throw new RuntimeException("Unknow label: " + label);
    } else {
      final Label labelObj = this.labels.get(index);
      labelObj.complete = true;
      this.jumpOffsets[index] = offset;
    }
  }

  @Override
  public void accept(Visitor visitor) {
    super.accept(visitor);
    visitor.visitInstructionTableSwitch(this.padding, this.defaultOffset, this.lowValue, this.highValue, this.jumpOffsets);
  }
  
  private class Label {
    final public String label;
    private boolean complete;

    public Label(String label) {
      super();
      this.label = label;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + getOuterType().hashCode();
      result = prime * result + ((label == null) ? 0 : label.hashCode());
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      Label other = (Label) obj;
      if (!getOuterType().equals(other.getOuterType()))
        return false;
      if (label == null) {
        if (other.label != null)
          return false;
      } else if (!label.equals(other.label))
        return false;
      if (this.complete || other.complete)
        return false;
      return true;
    }

    private TableswitchInstruction getOuterType() {
      return TableswitchInstruction.this;
    }
  }
}
