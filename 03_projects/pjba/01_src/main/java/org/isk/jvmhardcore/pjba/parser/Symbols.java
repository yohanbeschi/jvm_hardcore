package org.isk.jvmhardcore.pjba.parser;

public class Symbols {
  private static int number = 0;

  final public static int STREAM = number++;
  final public static int CLASSES = number++;
  final public static int EOF = number++;

  final public static int CLASS = number++;
  final public static int O_CLASS = number++;
  final public static int CLASS_START_IDENTIFIER = number++;
  final public static int CLASS_END_IDENTIFIER = number++;
  final public static int CLASS_MODIFIERS = number++;
  final public static int CLASS_MODIFIER = number++;
  final public static int CLASS_NAME = number++;

  final public static int METHODS = number++;
  final public static int METHOD = number++;
  final public static int METHOD_START_IDENTIFIER = number++;
  final public static int METHOD_END_IDENTIFIER = number++;
  final public static int METHOD_MODIFIERS = number++;
  final public static int METHOD_MODIFIER = number++;
  final public static int METHOD_NAME = number++;
  final public static int METHOD_SIGNATURE = number++;
  final public static int METHOD_CONTENT = number++;

  final public static int INSTRUCTION = number++;
  final public static int LABEL = number++;

  final public static int WS = number++;

  private Symbols() {
  }

  public static int number() {
    return number;
  }
}
