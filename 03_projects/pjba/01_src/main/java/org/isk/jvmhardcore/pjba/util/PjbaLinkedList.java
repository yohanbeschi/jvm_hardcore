package org.isk.jvmhardcore.pjba.util;

import java.io.DataOutput;
import java.io.IOException;
import java.util.LinkedList;

public class PjbaLinkedList<E extends BytecodeEnabled> extends LinkedList<E> implements BytecodeEnabled {
  private static final long serialVersionUID = 1L;

  @Override
  public void toBytecode(DataOutput dataOutput) throws IOException {
    for (BytecodeEnabled b : this) {
      if (b != null) {
        b.toBytecode(dataOutput);
      }
    }
  }
}
