package org.isk.jvmhardcore.pjba.instruction;

import java.util.LinkedList;
import java.util.List;

import org.isk.jvmhardcore.pjba.instruction.interfaces.LabeledInstruction;
import org.isk.jvmhardcore.pjba.instruction.interfaces.SwitchInstruction;
import org.isk.jvmhardcore.pjba.structure.Instruction;
import org.isk.jvmhardcore.pjba.visitor.Visitor;

public class LookupswitchInstruction extends Instruction implements LabeledInstruction, SwitchInstruction {

  final private List<Pair> keyLabels;
  private Pair defaultLabel;
  
  private int padding;
  private int defaultOffset;
  final private int nbPairs;
  final private int[] keys;
  final private int[] jumpOffsets;
  
  public LookupswitchInstruction(int opcode, int stack, int locals, int padding,
                                 int defaultOffset, int nbPairs,
                                 int[] keys, int[] jumpOffsets) {
    super(opcode, stack, locals, getLength(padding, keys.length));
    this.padding = padding;
    this.defaultOffset = defaultOffset;
    this.nbPairs = nbPairs;
    this.keys = keys;
    this.jumpOffsets = jumpOffsets;

    this.keyLabels = new LinkedList<>();
  }

  public static int getLength(int padding, int keys) {
    // 1 + padding + 4 + 4 + 4 * keys.length + 4 * offsets.length) where keys.length == offsets.length
    return 9 + padding + 8 * keys;
  }

  @Override
  public void setPadding(int padding) {
    this.padding = padding;
    super.setLength(getLength(padding, keys.length));
  }

  public void setDefaultLabel(String defaultLabel) {
    this.defaultLabel = new Pair(0, defaultLabel);
  }

  public void addMatchOffsetLabel(int key, String label) {
    this.keyLabels.add(new Pair(key, label));
  }

  @Override
  public void setOffset(String label, int offset) {
    if (this.defaultLabel != null && this.defaultLabel.equals(new Pair(0, label))) {
      this.defaultOffset = offset;
      this.defaultLabel.complete = true;
      return;
    }

    final int index = this.keyLabels.indexOf(new Pair(0, label));

    if (index == -1) {
      throw new RuntimeException("Unknow label: " + label);
    } else {
      final Pair pair = this.keyLabels.get(index);
      pair.complete = true;
      this.keys[index] = pair.key;
      this.jumpOffsets[index] = offset;
    }
  }
  
  private class Pair {
    final public int key;
    final public String label;
    private boolean complete;

    public Pair(int key, String label) {
      super();
      this.key = key;
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
      Pair other = (Pair) obj;
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

    private LookupswitchInstruction getOuterType() {
      return LookupswitchInstruction.this;
    }
  }
  
  @Override
  public void accept(Visitor visitor) {
    super.accept(visitor);
    visitor.visitInstructionLookupSwitch(this.padding, this.defaultOffset, this.nbPairs, this.keys, this.jumpOffsets);
  }
}
