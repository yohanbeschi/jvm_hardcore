package org.isk.jvmhardcore.pjba.parser.core;

import org.isk.jvmhardcore.pjba.util.Ascii;

public abstract class Tokenizer {
  final private String filename;
  final private Reader reader;

  public Tokenizer(final String filename, final Reader reader) {
    this.reader = reader;
    this.filename = filename;
  }

  public void consumeWhitespaces() {
    int character = Ascii.NULL;
    while ((character = this.next()) >= Ascii.NULL && character <= Ascii.SPACE) {
    }

    this.rewind();
  }

  public boolean isDigit(int character) {
    return character >= Ascii.DIGIT_0 && character <= Ascii.DIGIT_9;
  }

  public boolean isAsciiLetter(int character) {
    return character >= Ascii.LOWERCASE_A && character <= Ascii.LOWERCASE_Z 
        || character >= Ascii.UPPERCASE_A && character <= Ascii.UPPERCASE_Z;
  }

  public void checkEndOfFile() {
    int character = this.next();

    if (character != Ascii.EOF) {
      throw new ParserException("Expected: End of file.");
    }
  }

  protected int next() {
    return this.reader.read();
  }

  protected int rewind() {
    return this.reader.unread();
  }

  protected void mark() {
    this.reader.mark();
  }

  protected void reset() {
    this.reader.reset();
  }

  protected int peek() {
    int character = this.next();
    this.rewind();
    return character;
  }

  public class ParserException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ParserException(String message) {
      super((filename != null ? filename + "\n" : "") + "Line " + reader.getLine() + ", column " + reader.getColumn()
          + " - " + message + " Got: <" + String.valueOf(Character.toChars(reader.unread())) + ">");
    }

    public ParserException(String message, boolean printCurrentCharacter) {
      super((filename != null ? filename + "\n" : "") + "Line " + reader.getLine() + ", column " + reader.getColumn()
          + " - " + message
          + (printCurrentCharacter ? " Got: <" + String.valueOf(Character.toChars(reader.unread())) + ">" : ""));
    }
    
    public ParserException(String message, String got) {
      super((filename != null ? filename + "\n" : "") + "Line " + reader.getLine() + ", column " + reader.getColumn()
          + " - " + message + " Got: <" + got + ">");
    }
  }
}