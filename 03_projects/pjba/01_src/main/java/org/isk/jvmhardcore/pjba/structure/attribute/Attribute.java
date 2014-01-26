package org.isk.jvmhardcore.pjba.structure.attribute;

import java.io.DataOutput;
import java.io.IOException;

import org.isk.jvmhardcore.pjba.util.BytecodeEnabled;

public abstract class Attribute implements BytecodeEnabled {
  final private int nameIndex;

  public Attribute(final int nameIndex) {
    this.nameIndex = nameIndex;
  }

  @Override
  public void toBytecode(DataOutput dataOutput) throws IOException {
    dataOutput.writeShort(this.nameIndex);
  }
}
