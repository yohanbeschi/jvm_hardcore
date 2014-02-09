package org.isk.jvmhardcore.pjba.parser.core;

import java.util.Stack;

public interface Production<E, T extends Tokenizer> {
  E produce(T tokenizer, Production<E, T>[] table, Stack<Production<E, T>> productionStack);
}