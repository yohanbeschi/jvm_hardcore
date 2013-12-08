package org.isk.jvmhardcore.mathparser;

import org.isk.jvmhardcore.mathparser.core.Reader;
import org.isk.jvmhardcore.mathparser.core.Tokenizer;
import org.isk.jvmhardcore.mathparser.core.util.Ascii;
import org.isk.jvmhardcore.mathparser.core.util.StringGenerator;

public class MathTokenizer extends Tokenizer {

  final private StringGenerator generator;

  public MathTokenizer(String filename, Reader reader) {
    super(filename, reader);

    this.generator = new StringGenerator();
  }

  public String getNumber() {
    int character = Ascii.NULL;

    while (isDigit(character = this.next())) {
      generator.appendChar(character);
    }

    if (!generator.isEmpty()) {
      this.rewind();
      return generator.toString();
    } else {
      throw new ParserException("Expected: At least one Digit [0-9].");
    }
  }

  public int getOperator() {
    int character = this.next();

    if ( character == Ascii.PLUS_SIGN
      || character == Ascii.HYPHEN
      || character == Ascii.ASTERIX
      || character == Ascii.SLASH
        ) {
      return character;
    } else {
      throw new ParserException("Expected: '+' or '-' or '*' or '/'.");
    }
  }
}
