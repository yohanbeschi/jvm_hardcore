package org.isk.jvmhardcore.pjba.util;

public class BytecodeUtils {
  public static int unsign(final byte b) {
    return b & 0xFF;
  }

  public static int unsign(final short s) {
    return s & 0xFFFF;
  }

  public static long unsign(final int i) {
    return i & 0xFFFFFFFF;
  }
}
