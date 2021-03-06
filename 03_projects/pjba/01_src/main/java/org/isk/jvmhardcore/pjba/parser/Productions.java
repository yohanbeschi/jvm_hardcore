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
      productionStack.push(table[Symbols.CLASS]);

      return null;
    }
  }

  public static class Classes implements Production<EventType, PjbTokenizer> {
    public EventType produce(PjbTokenizer tokenizer,
                             Production<EventType, PjbTokenizer>[] table,
                             Stack<Production<EventType, PjbTokenizer>> productionStack) {
      tokenizer.consumeWhitespaces();

      if (tokenizer.isClassStart()) {
        productionStack.push(table[Symbols.CLASSES]);
        productionStack.push(table[Symbols.CLASS]);
      }
      return null;
    }
  }

  public static class Class implements Production<EventType, PjbTokenizer> {
    public EventType produce(PjbTokenizer tokenizer,
                             Production<EventType, PjbTokenizer>[] table,
                             Stack<Production<EventType, PjbTokenizer>> productionStack) {
      productionStack.push(table[Symbols.WS]);
      productionStack.push(table[Symbols.CLASS_END_IDENTIFIER]);
      productionStack.push(table[Symbols.CLASS_CONTENT]);
      productionStack.push(table[Symbols.CLASS_NAME]);
      productionStack.push(table[Symbols.CLASS_MODIFIERS]);
      productionStack.push(table[Symbols.WS]);
      productionStack.push(table[Symbols.CLASS_START_IDENTIFIER]);
      productionStack.push(table[Symbols.WS]);

      return null;
    }
  }

  public static class ClassContent implements Production<EventType, PjbTokenizer> {
    public EventType produce(PjbTokenizer tokenizer,
                             Production<EventType, PjbTokenizer>[] table,
                             Stack<Production<EventType, PjbTokenizer>> productionStack) {
      productionStack.push(table[Symbols.METHODS]);
      productionStack.push(table[Symbols.FIELDS]);
      productionStack.push(table[Symbols.INTERFACES]);
      productionStack.push(table[Symbols.SUPER]);

      return null;
    }
  }

  public static class Super implements Production<EventType, PjbTokenizer> {
    public EventType produce(PjbTokenizer tokenizer,
                             Production<EventType, PjbTokenizer>[] table,
                             Stack<Production<EventType, PjbTokenizer>> productionStack) {
      tokenizer.consumeWhitespaces();

      productionStack.push(table[Symbols.SUPER_END]);
      productionStack.push(table[Symbols.WS]);
      productionStack.push(table[Symbols.CLASS_NAME]);
      productionStack.push(table[Symbols.WS]);
      productionStack.push(table[Symbols.SUPER_IDENTIFIER_START]);

      return null;
    }
  }

  public static class Interfaces implements Production<EventType, PjbTokenizer> {
    public EventType produce(PjbTokenizer tokenizer,
                             Production<EventType, PjbTokenizer>[] table,
                             Stack<Production<EventType, PjbTokenizer>> productionStack) {
      tokenizer.consumeWhitespaces();

      if (tokenizer.isInterfaceStart()) {
        productionStack.push(table[Symbols.INTERFACES]);
        productionStack.push(table[Symbols.INTERFACE]);
      }
      return null;
    }
  }

  public static class Interface implements Production<EventType, PjbTokenizer> {
    public EventType produce(PjbTokenizer tokenizer,
                             Production<EventType, PjbTokenizer>[] table,
                             Stack<Production<EventType, PjbTokenizer>> productionStack) {
      productionStack.push(table[Symbols.INTERFACE_END]);
      productionStack.push(table[Symbols.WS]);
      productionStack.push(table[Symbols.CLASS_NAME]);
      productionStack.push(table[Symbols.WS]);
      productionStack.push(table[Symbols.INTERFACE_START_IDENTIFIER]);
      productionStack.push(table[Symbols.WS]);

      return null;
    }
  }

  public static class Fields implements Production<EventType, PjbTokenizer> {
    public EventType produce(PjbTokenizer tokenizer,
                             Production<EventType, PjbTokenizer>[] table,
                             Stack<Production<EventType, PjbTokenizer>> productionStack) {
      tokenizer.consumeWhitespaces();

      if (tokenizer.isFieldStart()) {
        productionStack.push(table[Symbols.FIELDS]);
        productionStack.push(table[Symbols.FIELD]);
      }
      return null;
    }
  }

  public static class Field implements Production<EventType, PjbTokenizer> {
    public EventType produce(PjbTokenizer tokenizer,
                             Production<EventType, PjbTokenizer>[] table,
                             Stack<Production<EventType, PjbTokenizer>> productionStack) {
      productionStack.push(table[Symbols.FIELD_END]);
      productionStack.push(table[Symbols.O_FIELD_ASSIGNEMENT]);
      productionStack.push(table[Symbols.WS]);
      productionStack.push(table[Symbols.FIELD_DESCRIPTOR]);
      productionStack.push(table[Symbols.WS]);
      productionStack.push(table[Symbols.FIELD_NAME]);
      productionStack.push(table[Symbols.WS]);
      productionStack.push(table[Symbols.FIELD_MODIFIERS]);
      productionStack.push(table[Symbols.WS]);
      productionStack.push(table[Symbols.FIELD_START_IDENTIFIER]);
      productionStack.push(table[Symbols.WS]);

      return null;
    }
  }

  public static class OFieldAssignement implements Production<EventType, PjbTokenizer> {
    public EventType produce(PjbTokenizer tokenizer,
                             Production<EventType, PjbTokenizer>[] table,
                             Stack<Production<EventType, PjbTokenizer>> productionStack) {
      tokenizer.consumeWhitespaces();

      if (tokenizer.isAssignementOperator()) {
        tokenizer.discardAssignementOperator();
        tokenizer.consumeWhitespaces();

        productionStack.push(table[Symbols.WS]);
        productionStack.push(table[Symbols.CONSTANT_VALUE]);
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

  public static class FieldModifiers implements Production<EventType, PjbTokenizer> {
    public EventType produce(PjbTokenizer tokenizer,
                             Production<EventType, PjbTokenizer>[] table,
                             Stack<Production<EventType, PjbTokenizer>> productionStack) {
      if (tokenizer.isFieldModifier()) {
        productionStack.push(table[Symbols.FIELD_MODIFIERS]);
        productionStack.push(table[Symbols.WS]);
        productionStack.push(table[Symbols.FIELD_MODIFIER]);
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

  public static class SuperStart implements Production<EventType, PjbTokenizer> {
    public EventType produce(PjbTokenizer tokenizer,
                             Production<EventType, PjbTokenizer>[] table,
                             Stack<Production<EventType, PjbTokenizer>> productionStack) {
      return EventType.SUPER_START;
    }
  }

  public static class InterfaceStart implements Production<EventType, PjbTokenizer> {
    public EventType produce(PjbTokenizer tokenizer,
                             Production<EventType, PjbTokenizer>[] table,
                             Stack<Production<EventType, PjbTokenizer>> productionStack) {
      return EventType.INTERFACE_START;
    }
  }

  public static class FieldStart implements Production<EventType, PjbTokenizer> {
    public EventType produce(PjbTokenizer tokenizer,
                             Production<EventType, PjbTokenizer>[] table,
                             Stack<Production<EventType, PjbTokenizer>> productionStack) {
      return EventType.FIELD_START;
    }
  }

  public static class FieldModifier implements Production<EventType, PjbTokenizer> {
    public EventType produce(PjbTokenizer tokenizer,
                             Production<EventType, PjbTokenizer>[] table,
                             Stack<Production<EventType, PjbTokenizer>> productionStack) {
      return EventType.FIELD_MODIFIER;
    }
  }

  public static class FieldName implements Production<EventType, PjbTokenizer> {
    public EventType produce(PjbTokenizer tokenizer,
                             Production<EventType, PjbTokenizer>[] table,
                             Stack<Production<EventType, PjbTokenizer>> productionStack) {
      return EventType.FIELD_NAME;
    }
  }

  public static class FieldDescriptor implements Production<EventType, PjbTokenizer> {
    public EventType produce(PjbTokenizer tokenizer,
                             Production<EventType, PjbTokenizer>[] table,
                             Stack<Production<EventType, PjbTokenizer>> productionStack) {
      return EventType.FIELD_TYPE;
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

  public static class ConstantValue implements Production<EventType, PjbTokenizer> {
    public EventType produce(PjbTokenizer tokenizer,
                             Production<EventType, PjbTokenizer>[] table,
                             Stack<Production<EventType, PjbTokenizer>> productionStack) {
      return EventType.CONSTANT_VALUE;
    }
  }

  public static class SuperEnd implements Production<EventType, PjbTokenizer> {
    public EventType produce(PjbTokenizer tokenizer,
                             Production<EventType, PjbTokenizer>[] table,
                             Stack<Production<EventType, PjbTokenizer>> productionStack) {
      return EventType.SUPER_END;
    }
  }

  public static class InterfaceEnd implements Production<EventType, PjbTokenizer> {
    public EventType produce(PjbTokenizer tokenizer,
                             Production<EventType, PjbTokenizer>[] table,
                             Stack<Production<EventType, PjbTokenizer>> productionStack) {
      return EventType.INTERFACE_END;
    }
  }

  public static class FieldEnd implements Production<EventType, PjbTokenizer> {
    public EventType produce(PjbTokenizer tokenizer,
                             Production<EventType, PjbTokenizer>[] table,
                             Stack<Production<EventType, PjbTokenizer>> productionStack) {
      return EventType.FIELD_END;
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
