package org.isk.jvmhardcore.pjba.structure;

import java.io.DataOutput;
import java.io.IOException;

import org.isk.jvmhardcore.pjba.util.BytecodeEnabled;

public class Exception implements BytecodeEnabled {

  @Override
  public void toBytecode(DataOutput dataOutput) throws IOException {
    // Do nothing
  }
}
