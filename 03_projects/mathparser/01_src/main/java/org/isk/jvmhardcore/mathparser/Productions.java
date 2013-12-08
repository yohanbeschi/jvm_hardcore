package org.isk.jvmhardcore.mathparser;

import java.util.Stack;

import org.isk.jvmhardcore.mathparser.core.Production;

public class Productions {
  
  // stream = expression eof
  public static class Stream implements Production<EventType, MathTokenizer> {
    public EventType produce(MathTokenizer tokenizer,
                             Production<EventType, MathTokenizer>[] table,
                             Stack<Production<EventType, MathTokenizer>> productionStack) {
      productionStack.push(table[Symbols.EOF]);
      productionStack.push(table[Symbols.EXPRESSION]);

      return null;
    }
  }

  // expression = number operator number
  public static class Expression implements Production<EventType, MathTokenizer> {
    public EventType produce(MathTokenizer tokenizer,
                             Production<EventType, MathTokenizer>[] table,
                             Stack<Production<EventType, MathTokenizer>> productionStack) {
      productionStack.push(table[Symbols.NUMBER]);
      productionStack.push(table[Symbols.OPERATOR]);
      productionStack.push(table[Symbols.NUMBER]);

      return null;
    }
  }

  // number = integer | float
  // integer = repeatingDigit
  // float = oRepeatingDigit [dot] oRepeatingDigit
  // repeatingDigit = digit oRepeatingDigit
  // oRepeatingDigit = {digit}
  // digit = '0' | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9'
  // dot = '.'
  public static class Number implements Production<EventType, MathTokenizer> {
    public EventType produce(MathTokenizer tokenizer,
                             Production<EventType, MathTokenizer>[] table,
                             Stack<Production<EventType, MathTokenizer>> productionStack) {
      if (tokenizer.isFloat()) {
        return EventType.FLOAT;
      } else {
        return EventType.INTEGER;
      }
    }
  }

  // operator = '+' | '-' | '/' | '*'
  public static class Operator implements Production<EventType, MathTokenizer> {
    public EventType produce(MathTokenizer tokenizer,
                             Production<EventType, MathTokenizer>[] table,
                             Stack<Production<EventType, MathTokenizer>> productionStack) {
      return EventType.OPERATOR;
    }
  }

  // eof = ? fin du flux ?
  public static class EndOfFile implements Production<EventType, MathTokenizer> {
    public EventType produce(MathTokenizer tokenizer, 
                             Production<EventType, MathTokenizer>[] table,
                             Stack<Production<EventType, MathTokenizer>> productionStack) {
      return EventType.EOF;
    }
  }
}
