package org.isk.jvmhardcore.mathparser;

import java.io.InputStream;
import java.util.LinkedList;

import org.isk.jvmhardcore.mathparser.core.InputStreamReader;
import org.isk.jvmhardcore.mathparser.core.Parser;

public class MathParser extends Parser<LinkedList<String>, EventType, MathTokenizer> {

  private LinkedList<String> tokens;

  public MathParser(final InputStream inputStream) {
    super(inputStream, Symbols.number());

    this.tokens = new LinkedList<>();
  }

  @Override
  public LinkedList<String> parse() {
    EventType eventType = null;

    boolean done = false;
    while (!done) {
      eventType = this.getNextEvent();

      switch (eventType) {
      case FLOAT:
        this.tokens.add(this.tokenizer.getFloat());
        break;
      case INTEGER:
        this.tokens.add(this.tokenizer.getInteger());
        break;
      case OPERATOR:
        this.tokens.add(String.valueOf(Character.toChars(this.tokenizer.getOperator())));
        break;
      case LEFT_PARENTHESIS:
        this.tokens.add(String.valueOf(Character.toChars(this.tokenizer.getNext())));
        break;
      case RIGHT_PARENTHESIS:
        this.tokens.add(String.valueOf(Character.toChars(this.tokenizer.getNext())));
        break;
      case EOF:
        this.tokenizer.checkEndOfFile();
        done = true;
        break;
      default:
        System.err.println("Unexpected event.");
        break;
      }
    }

    return this.tokens;
  }

  @Override
  protected MathTokenizer newTokenizer(final String filename, final InputStream inputStream) {
    return new MathTokenizer(filename, new InputStreamReader(inputStream));
  }

  @Override
  protected void initProductionTable() {
    this.table[Symbols.STREAM] = new Productions.Stream();
    this.table[Symbols.EOF] = new Productions.EndOfFile();
    this.table[Symbols.EXPRESSION] = new Productions.Expression();
    this.table[Symbols.OR_RIGHT_EXPRESSION] = new Productions.OrRightExpression();
    this.table[Symbols.OR_LEFT_PARENTHESIS] = new Productions.OrLeftParenthesis();
    this.table[Symbols.OR_RIGHT_PARENTHESIS] = new Productions.OrRightParenthesis();
    this.table[Symbols.LEFT_PARENTHESIS] = new Productions.LeftParenthesis();
    this.table[Symbols.RIGHT_PARENTHESIS] = new Productions.RightParenthesis();
    this.table[Symbols.NUMBER] = new Productions.Number();
    this.table[Symbols.OPERATOR] = new Productions.Operator();
    this.table[Symbols.WS] = new Productions.Whitespaces();
  }
}
