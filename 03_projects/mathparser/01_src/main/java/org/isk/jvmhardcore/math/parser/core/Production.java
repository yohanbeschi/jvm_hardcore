package org.isk.jvmhardcore.math.parser.core;

import java.util.Stack;

public interface Production<E, T extends Tokenizer> {
  /**
   * Adds Productions to the productions table (non-terminal symbol) or returns an event (terminal symbol).
   * 
   * @param tokenizer
   * @param table
   * @param productionStack
   * @return
   */
  E produce(T tokenizer, Production<E, T>[] table, Stack<Production<E, T>> productionStack);
}