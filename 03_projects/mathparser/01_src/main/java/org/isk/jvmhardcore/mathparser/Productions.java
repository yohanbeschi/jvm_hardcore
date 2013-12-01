package org.isk.jvmhardcore.mathparser;

import java.util.Stack;

import org.isk.jvmhardcore.mathparser.core.Production;

public class Productions {
  
  public static class Stream implements Production<EventType, MathTokenizer> {
    public EventType produce(MathTokenizer tokenizer,
                             Production<EventType, MathTokenizer>[] table,
                             Stack<Production<EventType, MathTokenizer>> productionStack) {
      productionStack.push(table[Symbols.EOF]);
      productionStack.push(table[Symbols.EXPRESSION]);

      return null;
    }
  }

  public static class Expression implements Production<EventType, MathTokenizer> {
    public EventType produce(MathTokenizer tokenizer,
                             Production<EventType, MathTokenizer>[] table,
                             Stack<Production<EventType, MathTokenizer>> productionStack) {
      productionStack.push(table[Symbols.DIGIT]);
      productionStack.push(table[Symbols.OPERATOR]);
      productionStack.push(table[Symbols.DIGIT]);

      return null;
    }
  }

  public static class Digit implements Production<EventType, MathTokenizer> {
    public EventType produce(MathTokenizer tokenizer,
                             Production<EventType, MathTokenizer>[] table,
                             Stack<Production<EventType, MathTokenizer>> productionStack) {
      return EventType.DIGIT;
    }
  }

  public static class Operator implements Production<EventType, MathTokenizer> {
    public EventType produce(MathTokenizer tokenizer,
                             Production<EventType, MathTokenizer>[] table,
                             Stack<Production<EventType, MathTokenizer>> productionStack) {
      return EventType.OPERATOR;
    }
  }

  public static class EndOfFile implements Production<EventType, MathTokenizer> {
    public EventType produce(MathTokenizer tokenizer, 
                             Production<EventType, MathTokenizer>[] table,
                             Stack<Production<EventType, MathTokenizer>> productionStack) {
      return EventType.EOF;
    }
  }
}
