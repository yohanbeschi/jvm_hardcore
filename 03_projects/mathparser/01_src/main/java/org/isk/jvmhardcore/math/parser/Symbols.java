package org.isk.jvmhardcore.math.parser;

public class Symbols {
  private static int number = 0;

  final public static int STREAM = number++;
  final public static int EOF = number++;

  final public static int EXPRESSION = number++;
  final public static int OR_RIGHT_EXPRESSION = number++;

  final public static int OR_LEFT_PARENTHESIS = number++;
  final public static int OR_RIGHT_PARENTHESIS = number++;
  final public static int LEFT_PARENTHESIS = number++;
  final public static int RIGHT_PARENTHESIS = number++;

  final public static int NUMBER = number++;
  final public static int OPERATOR = number++;

  final public static int WS = number++;

  private Symbols() {
  }

  public static int number() {
    return number;
  }
}
