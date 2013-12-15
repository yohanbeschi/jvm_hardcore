package org.isk.jvmhardcore.math.parser;

import java.util.Stack;

import org.isk.jvmhardcore.math.parser.core.Production;

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

  // expression = orLeftParenthesis ws number orRightExpression ws
  public static class Expression implements Production<EventType, MathTokenizer> {
    public EventType produce(MathTokenizer tokenizer,
                             Production<EventType, MathTokenizer>[] table,
                             Stack<Production<EventType, MathTokenizer>> productionStack) {
      productionStack.push(table[Symbols.WS]);
      productionStack.push(table[Symbols.OR_RIGHT_EXPRESSION]);
      productionStack.push(table[Symbols.NUMBER]);
      productionStack.push(table[Symbols.WS]);
      productionStack.push(table[Symbols.OR_LEFT_PARENTHESIS]);

      return null;
    }
  }

  // orRightExpression = {ws operator orLeftParenthesis number orRightParenthesis ws}
  public static class OrRightExpression implements Production<EventType, MathTokenizer> {
    public EventType produce(MathTokenizer tokenizer,
                             Production<EventType, MathTokenizer>[] table,
                             Stack<Production<EventType, MathTokenizer>> productionStack) {
      tokenizer.consumeUnprintables();

      if (tokenizer.isOperator()) {
        productionStack.push(table[Symbols.OR_RIGHT_EXPRESSION]);
        productionStack.push(table[Symbols.WS]);
        productionStack.push(table[Symbols.OR_RIGHT_PARENTHESIS]);
        productionStack.push(table[Symbols.NUMBER]);
        productionStack.push(table[Symbols.OR_LEFT_PARENTHESIS]);
        productionStack.push(table[Symbols.OPERATOR]);
      }

      return null;
    }
  }

  // number = integer | float
  // integer = repeatingDigit
  // float = oRepeatingDigit [dot] oRepeatingDigit
  // repeatingDigit = digit oRepeatingDigit
  // oRepeatingDigit = {digit}
  // sign = '+' | '-' | ''
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

  // orLeftParenthesis = {ws leftParenthesis}
  public static class OrLeftParenthesis implements Production<EventType, MathTokenizer> {
    public EventType produce(MathTokenizer tokenizer,
                             Production<EventType, MathTokenizer>[] table,
                             Stack<Production<EventType, MathTokenizer>> productionStack) {
      tokenizer.consumeUnprintables();

      if (tokenizer.isLeftParenthesis()) {
        productionStack.push(table[Symbols.OR_LEFT_PARENTHESIS]);
        productionStack.push(table[Symbols.LEFT_PARENTHESIS]);
      }

      return null;
    }
  }

  // orRightParenthesis = {ws rightParenthesis}
  public static class OrRightParenthesis implements Production<EventType, MathTokenizer> {
    public EventType produce(MathTokenizer tokenizer,
                             Production<EventType, MathTokenizer>[] table,
                             Stack<Production<EventType, MathTokenizer>> productionStack) {
      tokenizer.consumeUnprintables();

      if (tokenizer.isRightParenthesis()) {
        productionStack.push(table[Symbols.OR_RIGHT_PARENTHESIS]);
        productionStack.push(table[Symbols.RIGHT_PARENTHESIS]);
      }

      return null;
    }
  }

  // leftParenthesis = '('
  public static class LeftParenthesis implements Production<EventType, MathTokenizer> {
    public EventType produce(MathTokenizer tokenizer,
                             Production<EventType, MathTokenizer>[] table,
                             Stack<Production<EventType, MathTokenizer>> productionStack) {
      return EventType.LEFT_PARENTHESIS;
    }
  }

  // rightParenthesis = ')'
  public static class RightParenthesis implements Production<EventType, MathTokenizer> {
    public EventType produce(MathTokenizer tokenizer,
                             Production<EventType, MathTokenizer>[] table,
                             Stack<Production<EventType, MathTokenizer>> productionStack) {
      return EventType.RIGHT_PARENTHESIS;
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

  // ws = ? caractères d'espacement ?
  public static class Whitespaces implements Production<EventType, MathTokenizer> {
    public EventType produce(MathTokenizer tokenizer,
                             Production<EventType, MathTokenizer>[] table,
                             Stack<Production<EventType, MathTokenizer>> productionStack) {
      tokenizer.consumeUnprintables();
      return null;
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
