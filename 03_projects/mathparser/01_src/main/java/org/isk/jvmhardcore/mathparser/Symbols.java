package org.isk.jvmhardcore.mathparser;

public class Symbols {
  private static int number = 0;

  final public static int STREAM = number++;
  final public static int EOF = number++;

  final public static int EXPRESSION = number++;
  final public static int NUMBER = number++;
  final public static int OPERATOR = number++;

  private Symbols() {
  }

  public static int number() {
    return number;
  }
}
