package org.isk.jvmhardcore.pjba.util;

import java.io.DataOutput;
import java.io.IOException;

public interface BytecodeEnabled {
  void toBytecode(final DataOutput dataOutput) throws IOException;
}
