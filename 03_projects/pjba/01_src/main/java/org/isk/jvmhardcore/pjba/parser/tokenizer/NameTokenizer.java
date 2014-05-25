package org.isk.jvmhardcore.pjba.parser.tokenizer;

import org.isk.jvmhardcore.pjba.builder.MethodBuilder;
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

  public byte getArrayType() {
    byte arrayType;
    int character = this.peek();

    switch (character) {
      case Ascii.LOWERCASE_B: // boolean or byte
        arrayType = this.readBooleanOrByte();
        break;
      case Ascii.LOWERCASE_C: // char
        arrayType = this.readChar();
        break;
      case Ascii.LOWERCASE_D: // double
        arrayType = this.readDouble();
        break;
      case Ascii.LOWERCASE_F: // float
        arrayType = this.readFloat();
        break;
      case Ascii.LOWERCASE_I: // int
        arrayType = this.readInt();
        break;
      case Ascii.LOWERCASE_L: // long
        arrayType = this.readLong();
        break;
      case Ascii.LOWERCASE_S: // short
        arrayType = this.readShort();
        break;
      default:
        throw new ParserException("Invalid type. Expected <boolean, byte, char, double, float, int, long or short>.");
    }

    return arrayType;
  }

  private byte readBooleanOrByte() {
    this.mark();
    this.next(); // b
    final int character = this.next(); // o or y
    this.reset();

    byte arrayType;

    switch(character) {
      case Ascii.LOWERCASE_O:
        arrayType = this.readBoolean();
        break;
      case Ascii.LOWERCASE_Y:
        arrayType = this.readByte();
        break;
      default:
        throw new ParserException("Invalid type. Expected <boolean, byte, char, double, float, int, long or short>.");
    }

    return arrayType;
  }

  private byte readBoolean() {
    this.generator.appendChar(this.next()); // b
    this.generator.appendChar(this.next()); // o
    this.generator.appendChar(this.next()); // o
    this.generator.appendChar(this.next()); // l
    this.generator.appendChar(this.next()); // e
    this.generator.appendChar(this.next()); // a
    this.generator.appendChar(this.next()); // n

    if (!"boolean".equals(generator.toString())) {
      throw new ParserException("Invalid type. Expected <boolean>.");
    }

    return MethodBuilder.ArrayType.BOOLEAN.getValue();
  }

  private byte readByte() {
    this.generator.appendChar(this.next()); // b
    this.generator.appendChar(this.next()); // y
    this.generator.appendChar(this.next()); // t
    this.generator.appendChar(this.next()); // e

    if (!"byte".equals(generator.toString())) {
      throw new ParserException("Invalid type. Expected <byte>.");
    }

    return MethodBuilder.ArrayType.BYTE.getValue();
  }

  private byte readChar() {
    this.generator.appendChar(this.next()); // c
    this.generator.appendChar(this.next()); // h
    this.generator.appendChar(this.next()); // a
    this.generator.appendChar(this.next()); // r

    if (!"char".equals(generator.toString())) {
      throw new ParserException("Invalid type. Expected <char>.");
    }

    return MethodBuilder.ArrayType.CHAR.getValue();
  }

  private byte readDouble() {
    this.generator.appendChar(this.next()); // d
    this.generator.appendChar(this.next()); // o
    this.generator.appendChar(this.next()); // u
    this.generator.appendChar(this.next()); // b
    this.generator.appendChar(this.next()); // l
    this.generator.appendChar(this.next()); // e

    if (!"double".equals(generator.toString())) {
      throw new ParserException("Invalid type. Expected <double>.");
    }

    return MethodBuilder.ArrayType.DOUBLE.getValue();
  }

  private byte readFloat() {
    this.generator.appendChar(this.next()); // f
    this.generator.appendChar(this.next()); // l
    this.generator.appendChar(this.next()); // o
    this.generator.appendChar(this.next()); // a
    this.generator.appendChar(this.next()); // t

    if (!"float".equals(generator.toString())) {
      throw new ParserException("Invalid type. Expected <float>.");
    }

    return MethodBuilder.ArrayType.FLOAT.getValue();
  }

  private byte readInt() {
    this.generator.appendChar(this.next()); // i
    this.generator.appendChar(this.next()); // n
    this.generator.appendChar(this.next()); // t

    if (!"int".equals(generator.toString())) {
      throw new ParserException("Invalid type. Expected <int>.");
    }

    return MethodBuilder.ArrayType.INT.getValue();
  }

  private byte readLong() {
    this.generator.appendChar(this.next()); // l
    this.generator.appendChar(this.next()); // o
    this.generator.appendChar(this.next()); // n
    this.generator.appendChar(this.next()); // g

    if (!"long".equals(generator.toString())) {
      throw new ParserException("Invalid type. Expected <long>.");
    }

    return MethodBuilder.ArrayType.LONG.getValue();
  }

  private byte readShort() {
    this.generator.appendChar(this.next()); // s
    this.generator.appendChar(this.next()); // h
    this.generator.appendChar(this.next()); // o
    this.generator.appendChar(this.next()); // r
    this.generator.appendChar(this.next()); // t

    if (!"short".equals(generator.toString())) {
      throw new ParserException("Invalid type. Expected <short>.");
    }

    return MethodBuilder.ArrayType.SHORT.getValue();
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
