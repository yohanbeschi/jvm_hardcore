package org.isk.jvmhardcore.mathparser.core;

import java.io.InputStream;
import java.util.Stack;

/**
 * 
 * @author Yohan Beschi
 * 
 * @param <A>
 *          Object root type
 * @param <E>
 *          Event type
 * @param <T>
 *          Tokenizer type
 */
public abstract class Parser<A, E, T extends Tokenizer> {
  final public T tokenizer;
  final public Production<E, T>[] table;
  final public Stack<Production<E, T>> productionStack;

  public Parser(final InputStream inputStream, final int productionSize) {
    this(null, inputStream, productionSize);
  }

  @SuppressWarnings("unchecked")
  public Parser(final String filename, final InputStream inputStream, final int productionSize) {
    this.tokenizer = this.newTokenizer(filename, inputStream);
    this.table = new Production[productionSize];
    this.productionStack = new Stack<>();

    this.initProductionTable();
    this.productionStack.push(this.table[0]);
  }

  protected E getNextEvent() {
    while (!this.productionStack.isEmpty()) {
      final Production<E, T> production = this.productionStack.pop();
      final E event = production.produce(this.tokenizer, this.table, this.productionStack);

      if (event != null) {
        return event;
      }
    }

    return null;
  }

  protected abstract T newTokenizer(final String filename, final InputStream inputStream);

  protected abstract void initProductionTable();

  public abstract A parse();
}
