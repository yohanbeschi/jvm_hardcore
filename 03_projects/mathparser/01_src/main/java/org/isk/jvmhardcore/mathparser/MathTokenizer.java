package org.isk.jvmhardcore.mathparser;

import org.isk.jvmhardcore.mathparser.core.Reader;
import org.isk.jvmhardcore.mathparser.core.Tokenizer;
import org.isk.jvmhardcore.mathparser.core.util.Ascii;

public class MathTokenizer extends Tokenizer {

  public MathTokenizer(String filename, Reader reader) {
    super(filename, reader);
  }

  public int getDigit() {
    int character = this.next();

    if (isDigit(character)) {
      return character;
    } else {
      throw new ParserException("Expected: Digit [0-9].");
    }
  }

  public int getOperator() {
    int character = this.next();

    if (character == Ascii.PLUS_SIGN) {
      return character;
    } else {
      throw new ParserException("Expected: '+'.");
    }
  }
}
