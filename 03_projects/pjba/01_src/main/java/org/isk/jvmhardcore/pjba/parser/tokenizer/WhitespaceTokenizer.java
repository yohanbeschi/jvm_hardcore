package org.isk.jvmhardcore.pjba.parser.tokenizer;

import org.isk.jvmhardcore.pjba.parser.core.Reader;
import org.isk.jvmhardcore.pjba.parser.core.Tokenizer;
import org.isk.jvmhardcore.pjba.util.Ascii;

public class WhitespaceTokenizer extends Tokenizer {

  public WhitespaceTokenizer(String filename, Reader reader) {
    super(filename, reader);
  }

  public void consumeWhitespaces() {
    int character = Ascii.NULL;

    for (;;) {
      // Whitespaces
      while ((character = this.next()) >= Ascii.NULL && character <= Ascii.SPACE) {
      }
      this.rewind();

      // Nothing to read
      if (character == Ascii.EOF) {
        break;
      }

      // Comments
      if (!this.consumeComments()) {
        break;
      }
    }
  }

  private boolean consumeComments() {
    this.mark();

    int character = this.next();

    if (character == Ascii.NUMBER_SIGN || character == Ascii.SEMICOLON || character == Ascii.COMMERCIAL_AT) {
      while ((character = this.next()) != Ascii.LF && character != Ascii.EOF) {
      }

      if (character == Ascii.EOF) {
        this.rewind();
      }

      return true;
    } else if (character == Ascii.SLASH && this.next() == Ascii.ASTERISK) {
      int previous = Ascii.NULL;
      while ((character = this.next()) != Ascii.EOF) {
        if (previous == Ascii.ASTERISK && character == Ascii.SLASH) {
          break;
        }
        previous = character;
      }

      if (character == Ascii.EOF) {
        this.rewind();
      }

      return true;
    }

    this.reset();
    return false;
  }

  public boolean isAssignementOperator() {
    return this.peek() == Ascii.EQUALS;
  }

  public void discardAssignementOperator() {
    // Called just after isAssignementOperator()
    // therefore no checking
    this.next();
  }
}
