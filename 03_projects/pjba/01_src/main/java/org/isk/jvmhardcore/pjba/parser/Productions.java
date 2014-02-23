package org.isk.jvmhardcore.pjba.parser;

import java.util.Stack;

import org.isk.jvmhardcore.pjba.parser.core.Production;

public class Productions {
  public static class Stream implements Production<EventType, PjbTokenizer> {
    public EventType produce(PjbTokenizer tokenizer,
                             Production<EventType, PjbTokenizer>[] table,
                             Stack<Production<EventType, PjbTokenizer>> productionStack) {
      productionStack.push(table[Symbols.EOF]);
      productionStack.push(table[Symbols.CLASSES]);

      return null;
    }
  }

  public static class Classes implements Production<EventType, PjbTokenizer> {
    public EventType produce(PjbTokenizer tokenizer,
                             Production<EventType, PjbTokenizer>[] table,
                             Stack<Production<EventType, PjbTokenizer>> productionStack) {
      productionStack.push(table[Symbols.O_CLASS]);
      productionStack.push(table[Symbols.CLASS]);
      return null;
    }
  }

  public static class Class implements Production<EventType, PjbTokenizer> {
    public EventType produce(PjbTokenizer tokenizer,
                             Production<EventType, PjbTokenizer>[] table,
                             Stack<Production<EventType, PjbTokenizer>> productionStack) {
      productionStack.push(table[Symbols.WS]);
      productionStack.push(table[Symbols.CLASS_END_IDENTIFIER]);
      productionStack.push(table[Symbols.METHODS]);
      productionStack.push(table[Symbols.CLASS_NAME]);
      productionStack.push(table[Symbols.CLASS_MODIFIERS]);
      productionStack.push(table[Symbols.WS]);
      productionStack.push(table[Symbols.CLASS_START_IDENTIFIER]);
      productionStack.push(table[Symbols.WS]);

      return null;
    }
  }

  public static class OClass implements Production<EventType, PjbTokenizer> {
    public EventType produce(PjbTokenizer tokenizer,
                             Production<EventType, PjbTokenizer>[] table,
                             Stack<Production<EventType, PjbTokenizer>> productionStack) {
      if (tokenizer.isClassStart()) {
        productionStack.push(table[Symbols.O_CLASS]);
        productionStack.push(table[Symbols.CLASS]);
      }

      return null;
    }
  }

  public static class Methods implements Production<EventType, PjbTokenizer> {
    public EventType produce(PjbTokenizer tokenizer,
                             Production<EventType, PjbTokenizer>[] table,
                             Stack<Production<EventType, PjbTokenizer>> productionStack) {
      tokenizer.consumeWhitespaces();

      if (tokenizer.isMethodStart()) {
        productionStack.push(table[Symbols.METHODS]);
        productionStack.push(table[Symbols.METHOD]);
      }
      return null;
    }
  }

  public static class Method implements Production<EventType, PjbTokenizer> {
    public EventType produce(PjbTokenizer tokenizer,
                             Production<EventType, PjbTokenizer>[] table,
                             Stack<Production<EventType, PjbTokenizer>> productionStack) {
      productionStack.push(table[Symbols.WS]);
      productionStack.push(table[Symbols.METHOD_END_IDENTIFIER]);
      productionStack.push(table[Symbols.METHOD_CONTENT]);
      productionStack.push(table[Symbols.METHOD_SIGNATURE]);
      productionStack.push(table[Symbols.WS]);
      productionStack.push(table[Symbols.METHOD_NAME]);
      productionStack.push(table[Symbols.METHOD_MODIFIERS]);
      productionStack.push(table[Symbols.WS]);
      productionStack.push(table[Symbols.METHOD_START_IDENTIFIER]);
      productionStack.push(table[Symbols.WS]);

      return null;
    }
  }

  public static class ClassStart implements Production<EventType, PjbTokenizer> {
    public EventType produce(PjbTokenizer tokenizer,
                             Production<EventType, PjbTokenizer>[] table,
                             Stack<Production<EventType, PjbTokenizer>> productionStack) {
      return EventType.CLASS_START;
    }
  }

  public static class ClassModifiers implements Production<EventType, PjbTokenizer> {
    public EventType produce(PjbTokenizer tokenizer,
                             Production<EventType, PjbTokenizer>[] table,
                             Stack<Production<EventType, PjbTokenizer>> productionStack) {
      if (tokenizer.isClassModifier()) {
        productionStack.push(table[Symbols.CLASS_MODIFIERS]);
        productionStack.push(table[Symbols.WS]);
        productionStack.push(table[Symbols.CLASS_MODIFIER]);
      }
      return null;
    }
  }

  public static class MethodModifiers implements Production<EventType, PjbTokenizer> {
    public EventType produce(PjbTokenizer tokenizer,
                             Production<EventType, PjbTokenizer>[] table,
                             Stack<Production<EventType, PjbTokenizer>> productionStack) {
      if (tokenizer.isMethodModifier()) {
        productionStack.push(table[Symbols.METHOD_MODIFIERS]);
        productionStack.push(table[Symbols.WS]);
        productionStack.push(table[Symbols.METHOD_MODIFIER]);
      }
      return null;
    }
  }

  public static class ClassModifier implements Production<EventType, PjbTokenizer> {
    public EventType produce(PjbTokenizer tokenizer,
                             Production<EventType, PjbTokenizer>[] table,
                             Stack<Production<EventType, PjbTokenizer>> productionStack) {
      return EventType.CLASS_MODIFIER;
    }
  }

  public static class ClassName implements Production<EventType, PjbTokenizer> {
    public EventType produce(PjbTokenizer tokenizer,
                             Production<EventType, PjbTokenizer>[] table,
                             Stack<Production<EventType, PjbTokenizer>> productionStack) {
      return EventType.CLASS_NAME;
    }
  }

  public static class MethodStart implements Production<EventType, PjbTokenizer> {
    public EventType produce(PjbTokenizer tokenizer,
                             Production<EventType, PjbTokenizer>[] table,
                             Stack<Production<EventType, PjbTokenizer>> productionStack) {
      return EventType.METHOD_START;
    }
  }

  public static class MethodModifier implements Production<EventType, PjbTokenizer> {
    public EventType produce(PjbTokenizer tokenizer,
                             Production<EventType, PjbTokenizer>[] table,
                             Stack<Production<EventType, PjbTokenizer>> productionStack) {
      return EventType.METHOD_MODIFIER;
    }
  }

  public static class MethodName implements Production<EventType, PjbTokenizer> {
    public EventType produce(PjbTokenizer tokenizer,
                             Production<EventType, PjbTokenizer>[] table,
                             Stack<Production<EventType, PjbTokenizer>> productionStack) {
      return EventType.METHOD_NAME;
    }
  }

  public static class MethodSignature implements Production<EventType, PjbTokenizer> {
    public EventType produce(PjbTokenizer tokenizer,
                             Production<EventType, PjbTokenizer>[] table,
                             Stack<Production<EventType, PjbTokenizer>> productionStack) {
      return EventType.METHOD_SIGNATURE;
    }
  }

  public static class MethodContent implements Production<EventType, PjbTokenizer> {
    public EventType produce(PjbTokenizer tokenizer,
                             Production<EventType, PjbTokenizer>[] table,
                             Stack<Production<EventType, PjbTokenizer>> productionStack) {
      tokenizer.consumeWhitespaces();

      if (!tokenizer.isMethodEnd()) {
        productionStack.push(table[Symbols.METHOD_CONTENT]);
        productionStack.push(table[Symbols.WS]);

        if (tokenizer.isLabel()) {
          productionStack.push(table[Symbols.LABEL]);
        } else {
          productionStack.push(table[Symbols.INSTRUCTION]);
        }
      }

      return null;
    }
  }

  public static class Instruction implements Production<EventType, PjbTokenizer> {
    public EventType produce(PjbTokenizer tokenizer,
                             Production<EventType, PjbTokenizer>[] table,
                             Stack<Production<EventType, PjbTokenizer>> productionStack) {
      return EventType.INSTRUCTION;
    }
  }

  public static class Label implements Production<EventType, PjbTokenizer> {
    public EventType produce(PjbTokenizer tokenizer,
                             Production<EventType, PjbTokenizer>[] table,
                             Stack<Production<EventType, PjbTokenizer>> productionStack) {
      return EventType.LABEL;
    }
  }

  public static class MethodEnd implements Production<EventType, PjbTokenizer> {
    public EventType produce(PjbTokenizer tokenizer,
                             Production<EventType, PjbTokenizer>[] table,
                             Stack<Production<EventType, PjbTokenizer>> productionStack) {
      return EventType.METHOD_END;
    }
  }

  public static class ClassEnd implements Production<EventType, PjbTokenizer> {
    public EventType produce(PjbTokenizer tokenizer,
                             Production<EventType, PjbTokenizer>[] table,
                             Stack<Production<EventType, PjbTokenizer>> productionStack) {
      return EventType.CLASS_END;
    }
  }

  public static class Whitespaces implements Production<EventType, PjbTokenizer> {
    public EventType produce(PjbTokenizer tokenizer,
                             Production<EventType, PjbTokenizer>[] table,
                             Stack<Production<EventType, PjbTokenizer>> productionStack) {
      tokenizer.consumeWhitespaces();
      return null;
    }
  }

  public static class EndOfFile implements Production<EventType, PjbTokenizer> {
    public EventType produce(PjbTokenizer tokenizer,
                             Production<EventType, PjbTokenizer>[] table,
                             Stack<Production<EventType, PjbTokenizer>> productionStack) {
      return EventType.EOF;
    }
  }
}
