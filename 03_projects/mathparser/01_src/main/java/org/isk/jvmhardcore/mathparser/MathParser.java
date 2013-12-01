package org.isk.jvmhardcore.mathparser;

import java.io.InputStream;

import org.isk.jvmhardcore.mathparser.core.InputStreamReader;
import org.isk.jvmhardcore.mathparser.core.Parser;

public class MathParser extends Parser<String[], EventType, MathTokenizer> {

  private String[] tokens;
  private int counter = 0;

  public MathParser(final InputStream inputStream) {
    super(inputStream, Symbols.number());

    this.tokens = new String[3];
  }

  @Override
  public String[] parse() {
    EventType eventType = null;

    boolean done = false;
    while (!done) {
      eventType = this.getNextEvent();

      switch (eventType) {
        case DIGIT:
          this.tokens[counter++] = String.valueOf(Character.toChars(this.tokenizer.getDigit()));
          break;
        case OPERATOR:
          this.tokens[counter++] = String.valueOf(Character.toChars(this.tokenizer.getOperator()));
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
    this.table[Symbols.DIGIT] = new Productions.Digit();
    this.table[Symbols.OPERATOR] = new Productions.Operator();
  }
}
