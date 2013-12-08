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

  public String getInteger() {
    this.fillWithDigits();

    if (!generator.isEmpty()) {
      this.rewind();
      return generator.toString();
    } else {
      throw new ParserException("Expected: At least one Digit [0-9].");
    }
  }

  public String getFloat() {
    int character = this.fillWithDigits();

    // getFloat() is called after isFloat()
    // therefore we are not supposed to check if it's a dot or not
    generator.appendChar(character);

    while (isDigit(character = this.next())) {
      generator.appendChar(character);
    }

    this.rewind();
    return generator.toString();
  }

  private int fillWithDigits() {
    int character = this.next();

    if (character == Ascii.PLUS_SIGN) {
      // Ignore
    } else if (character == Ascii.HYPHEN) {
      generator.appendChar(character);
    } else {
      this.rewind();
    }
    
    while (isDigit(character = this.next())) {
      generator.appendChar(character);
    }
    
    return character;
  }

  public int getOperator() {
    int character = this.next();

    if (this.isOperator(character)) {
      return character;
    } else {
      throw new ParserException("Expected: '+' or '-' or '*' or '/'.");
    }
  }
  
  public int getNext() {
    return this.next();
  }
  
  public boolean isFloat() {
    this.mark();

    int character = this.next();

    // Ignore the sign
    if (character != Ascii.PLUS_SIGN && character != Ascii.HYPHEN) {
      this.rewind();
    }

    // number of digits
    int counter = 0;

    // Check the part before the period
    while (isDigit(character = this.next())) {
      counter++;
    }

    // Check is the next character is a period
    if (Ascii.PERIOD != character) {
      this.reset();
      return false;
    }

    // There are digits before the period we don't need to continue
    // it's a float
    if (counter > 0) {
      this.reset();
      return true;
    }

    // Check the part after the period
    while (isDigit(character = this.next())) {
      counter++;
    }

    // There are digits after the period
    // it's a float
    if (counter > 0) {
      this.reset();
      return true;
    }

    // There is no character before and after the period
    this.reset();
    return false;
  }

  public boolean isOperator(int character) {
    return character == Ascii.PLUS_SIGN
              || character == Ascii.HYPHEN
              || character == Ascii.ASTERIX
              || character == Ascii.SLASH;
  }
  
  public boolean isOperator() {
    final int character = this.next();
    this.rewind();
    return isOperator(character);
  }
  
  public boolean isLeftParenthesis() {
    final int character = this.next();
    this.rewind();
    return character == Ascii.LEFT_PARENTHESIS;
  }

  public boolean isRightParenthesis() {
    final int character = this.next();
    this.rewind();
    return character == Ascii.RIGHT_PARENTHESIS;
  }
}
