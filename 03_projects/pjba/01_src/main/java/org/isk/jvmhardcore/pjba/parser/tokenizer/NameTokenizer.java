package org.isk.jvmhardcore.pjba.parser.tokenizer;

import org.isk.jvmhardcore.pjba.parser.core.Reader;
import org.isk.jvmhardcore.pjba.parser.core.Tokenizer;
import org.isk.jvmhardcore.pjba.parser.core.util.StringGenerator;
import org.isk.jvmhardcore.pjba.util.Ascii;

public class NameTokenizer extends Tokenizer {

  final private StringGenerator generator;

  public NameTokenizer(String filename, Reader reader) {
    super(filename, reader);
    this.generator = new StringGenerator();
  }

  public String getClassName() {
    this.fillWithClassName();
    return this.generator.toString();
  }

  public String getFieldName() {
    this.fillWithIdentifier();
    return this.generator.toString();
  }

  public String getFieldDescriptor() {
    this.fillWithDescriptor();
    return this.generator.toString();
  }

  public String getMethodName() {
    if (!this.getInitOrClinit()) {
      this.fillWithIdentifier();
    }
    return this.generator.toString();
  }

  private boolean getInitOrClinit() {
    if (this.peek() == Ascii.LESS_THAN) {
      this.next(); // Consume <
      int character = this.peek();
      

      if (character == Ascii.LOWERCASE_C && this.isClinit()) {
        this.generator.append("<clinit>");
        return true;
      } else if (character == Ascii.LOWERCASE_I && this.isInit()) {
        this.generator.append("<init>");
        return true;
      } else {
        throw new ParserException("Invalid method name. Only <init> and <clinit> can start with '<'.");
      }
    }
    return false;
  }

  private boolean isInit() {
    return    this.next() == Ascii.LOWERCASE_I
           && this.next() == Ascii.LOWERCASE_N
           && this.next() == Ascii.LOWERCASE_I
           && this.next() == Ascii.LOWERCASE_T
           && this.next() == Ascii.GREATER_THAN;
  }

  private boolean isClinit() {
    return    this.next() == Ascii.LOWERCASE_C
           && this.next() == Ascii.LOWERCASE_L
           && this.next() == Ascii.LOWERCASE_I
           && this.next() == Ascii.LOWERCASE_N
           && this.next() == Ascii.LOWERCASE_I
           && this.next() == Ascii.LOWERCASE_T
           && this.next() == Ascii.GREATER_THAN;
  }

  private void fillWithClassName() {
    int character = Ascii.NULL;

    for (;;) {
      this.fillWithIdentifier();
      character = this.next();

      if (character != Ascii.SLASH) {
        break;
      }

      this.generator.appendChar(character);
    }

    this.rewind();
  }

  public String getMethodSignature() {
    int character = this.next();

    if (character != Ascii.LEFT_PARENTHESIS) {
      throw new ParserException("An identifier must start with a left parenthesis '('.");
    }

    this.generator.appendChar(character);

    while (this.fillWithDescriptor()) {
    }

    character = this.next();

    if (character != Ascii.RIGHT_PARENTHESIS) {
      throw new ParserException("An identifier must end with a right parenthesis ')'.");
    }

    this.generator.appendChar(character);

    // Check void or throw an exception
    if (!fillWithDescriptor()) {
      character = this.next();

      if (character != Ascii.UPPERCASE_V) {
        throw new ParserException("Missing return descriptor.");
      }

      this.generator.appendChar(character);
    }

    return this.generator.toString();
  }

  private void fillWithIdentifier() {
    int character = this.next();

    if (!this.isAsciiLetter(character) && character != Ascii.UNDERSCORE && character != Ascii.DOLLAR_SIGN) {
      throw new ParserException("An identifier must start with an ASCII letter or '_' or '$'.");
    }

    this.generator.appendChar(character);

    while (this.isIdentifierChar(character = this.next())) {
      this.generator.appendChar(character);
    }

    this.rewind();
  }

  private boolean fillWithDescriptor() {
    int character = this.next();

    if (character == Ascii.UPPERCASE_B || character == Ascii.UPPERCASE_C || character == Ascii.UPPERCASE_D
        || character == Ascii.UPPERCASE_F || character == Ascii.UPPERCASE_I || character == Ascii.UPPERCASE_J
        || character == Ascii.UPPERCASE_S || character == Ascii.UPPERCASE_Z) {
      this.generator.appendChar(character);
    } else if (character == Ascii.LEFT_BRACKET) {
      this.generator.appendChar(character);
      fillWithDescriptor();
    } else if (character == Ascii.UPPERCASE_L) {
      this.generator.appendChar(character);
      fillWithClassName();
      character = this.next();

      if (character != Ascii.SEMICOLON) {
        throw new ParserException("An object type must end with a semicolon ';'.");
      }
      this.generator.appendChar(character);
    } else {
      this.rewind();
      return false;
    }

    return true;
  }

  private boolean isIdentifierChar(int character) {
    return this.isAsciiLetter(character) || this.isDigit(character) || character == Ascii.UNDERSCORE
        || character == Ascii.DOLLAR_SIGN;
  }
}
