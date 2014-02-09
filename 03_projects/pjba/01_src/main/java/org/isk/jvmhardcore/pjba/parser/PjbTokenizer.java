package org.isk.jvmhardcore.pjba.parser;

import org.isk.jvmhardcore.pjba.instruction.meta.MetaInstruction;
import org.isk.jvmhardcore.pjba.instruction.meta.MetaInstructions;
import org.isk.jvmhardcore.pjba.parser.core.Reader;
import org.isk.jvmhardcore.pjba.parser.core.Tokenizer;
import org.isk.jvmhardcore.pjba.parser.core.util.StringGenerator;
import org.isk.jvmhardcore.pjba.structure.ClassFile;
import org.isk.jvmhardcore.pjba.structure.Method;
import org.isk.jvmhardcore.pjba.util.Ascii;

public class PjbTokenizer extends Tokenizer {

  final private StringGenerator generator;

  public PjbTokenizer(final String filename, final Reader reader) {
    super(filename, reader);
    this.generator = new StringGenerator();
  }

  //--------------------------------------------------------------------------------------------------------------------
  // Get to build graph objects
  //--------------------------------------------------------------------------------------------------------------------
  public int getClassModifier() {
    // public final abstract interface super
    int character = 0;

    while (this.isAsciiLetter(character = this.next())) {
      this.generator.appendChar(character);
    }

    this.rewind();

    final String modifier = this.generator.toString();

    if ("public".equals(modifier)) {
      return ClassFile.MODIFIER_PUBLIC;
    } else if ("final".equals(modifier)) {
      return ClassFile.MODIFIER_FINAL;
    } else if ("abstract".equals(modifier)) {
      return ClassFile.MODIFIER_ABSTRACT;
    } else if ("interface".equals(modifier)) {
      return ClassFile.MODIFIER_INTERFACE;
    } else if ("super".equals(modifier)) {
      return ClassFile.MODIFIER_SUPER;
    } else {
      throw new ParserException("Unknown class modifier: " + modifier);
    }
  }

  public String getClassName() {
    fillWithClassName();
    return this.generator.toString();
  }

  public int getMethodModifier() {
    // public private protected static final synchronized native abstract strictfp
    int character = 0;

    while (this.isAsciiLetter(character = this.next())) {
      this.generator.appendChar(character);
    }

    this.rewind();

    final String modifier = this.generator.toString();

    if ("public".equals(modifier)) {
      return Method.MODIFIER_PUBLIC;
    } else if ("private".equals(modifier)) {
      return Method.MODIFIER_PRIVATE;
    } else if ("protected".equals(modifier)) {
      return Method.MODIFIER_PROTECTED;
    } else if ("static".equals(modifier)) {
      return Method.MODIFIER_STATIC;
    } else if ("final".equals(modifier)) {
      return Method.MODIFIER_FINAL;
    } else if ("synchronized".equals(modifier)) {
      return Method.MODIFIER_SYNCHRONIZED;
    } else if ("native".equals(modifier)) {
      return Method.MODIFIER_NATIVE;
    } else if ("abstract".equals(modifier)) {
      return Method.MODIFIER_ABSTRACT;
    } else if ("strictfp".equals(modifier)) {
      return Method.MODIFIER_STRICTFP;
    } else {
      throw new ParserException("Unknown method modifier: " + modifier);
    }
  }

  public String getMethodName() {
    fillWithIdentifier();
    return this.generator.toString();
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

  private void fillWithClassName() {
    int character = Ascii.NULL;

    for (;;) {
      fillWithIdentifier();
      character = this.next();

      if (character != Ascii.SLASH) {
        break;
      }

      this.generator.appendChar(character);
    }

    this.rewind();
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

    if (   character == Ascii.UPPERCASE_B || character == Ascii.UPPERCASE_C || character == Ascii.UPPERCASE_D
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

  //--------------------------------------------------------------------------------------------------------------------
  // Get instructions with parameters
  //--------------------------------------------------------------------------------------------------------------------
  
  public MetaInstruction getMetaInstruction() {
    this.mark();
    int character = Ascii.NULL;

    while ((character = this.next()) != Ascii.SPACE
                        && character != Ascii.TAB
                        && character != Ascii.LF
                        && this.isInstructionChar(character)) {
      this.generator.appendChar(character);
    }

    final String instructionStr = this.generator.toString();

    final MetaInstruction metaInstruction = MetaInstructions.getMetaInstruction(instructionStr);
    if (metaInstruction != null) {
      this.rewind();
      return metaInstruction;
    }

    this.reset();
    throw new ParserException("Unknown instruction. Got: " + instructionStr, false);
  }

  public byte getByteValue() {
    int character = this.next();

    if (this.isDigit(character) || character == Ascii.PLUS_SIGN || character == Ascii.HYPHEN) {
      this.generator.appendChar(character);
    } else {
      throw new ParserException("Expected: a number");
    }

    while (this.isDigit(character = this.next())) {
      this.generator.appendChar(character);
    }

    this.rewind();

    final String value = this.generator.toString();

    return Byte.valueOf(value);
  }

  public short getShortValue() {
    int character = this.next();

    if (this.isDigit(character) || character == Ascii.PLUS_SIGN || character == Ascii.HYPHEN) {
      this.generator.appendChar(character);
    } else {
      throw new ParserException("Expected: a number");
    }

    while (this.isDigit(character = this.next())) {
      this.generator.appendChar(character);
    }

    this.rewind();

    final String value = this.generator.toString();

    return Short.valueOf(value);
  }

  public Object getIfsConstant() {
    int character = this.next();

    // String
    if (character == Ascii.QUOTATION_MARK) {
      int previous = character;
      for (;;) {
        character = this.next();
        if (previous == Ascii.REVERSE_SLANT && character == Ascii.REVERSE_SLANT) {
          previous = Ascii.NULL;
          this.generator.appendChar(character);
        } else if (previous != Ascii.REVERSE_SLANT && character == Ascii.QUOTATION_MARK) {
          break;
        } else {
          previous = character;

          if (character != Ascii.REVERSE_SLANT) {
            this.generator.appendChar(character);
          }
        }
      }

      return this.generator.toString();
    }

    if (this.isDigit(character) || character == Ascii.PLUS_SIGN || character == Ascii.HYPHEN) {
      this.generator.appendChar(character);
    } else {
      throw new ParserException("The instruction 'ldc' must be followed by a String, and integer or a float.");
    }

    for (;;) {
      character = this.next();

      // Float
      if (character == Ascii.PERIOD || character == Ascii.LOWERCASE_E || character == Ascii.UPPERCASE_E) {
        this.generator.appendChar(character);

        int eCount = 0;
        int signCount = 0;

        for (;;) {
          character = this.next();

          if (character == Ascii.LOWERCASE_E || character == Ascii.UPPERCASE_E) {
            eCount++;
          } else if (character == Ascii.PLUS_SIGN || character == Ascii.HYPHEN) {
            signCount++;
          } else if (!this.isDigit(character)) {
            break;
          }

          if (eCount > 1 || signCount > 1) {
            break;
          }

          this.generator.appendChar(character);
        }

        final String value = this.generator.toString();
        return Float.valueOf(value);
      }
      // Integer
      else if (!this.isDigit(character)) {
        this.rewind();

        final String value = this.generator.toString();
        return Integer.valueOf(value);
      }

      this.generator.appendChar(character);
    }
  }

  public Object getLdConstant() {
    int character = this.next();

    if (this.isDigit(character) || character == Ascii.PLUS_SIGN || character == Ascii.HYPHEN) {
      this.generator.appendChar(character);
    } else {
      throw new ParserException("The instruction 'ldc2_w' must be followed by a String, and integer or a float.");
    }

    for (;;) {
      character = this.next();

      // Double
      if (character == Ascii.PERIOD || character == Ascii.LOWERCASE_E || character == Ascii.UPPERCASE_E) {
        this.generator.appendChar(character);

        int eCount = 0;
        int signCount = 0;

        for (;;) {
          character = this.next();

          if (character == Ascii.LOWERCASE_E || character == Ascii.UPPERCASE_E) {
            eCount++;
          } else if (character == Ascii.PLUS_SIGN || character == Ascii.HYPHEN) {
            signCount++;
          } else if (!this.isDigit(character)) {
            break;
          }

          if (eCount > 1 || signCount > 1) {
            break;
          }

          this.generator.appendChar(character);
        }

        // ?
        // this.rewind();

        final String value = this.generator.toString();
        return Double.valueOf(value);
      }
      // Long
      else if (!this.isDigit(character)) {
        this.rewind();

        final String value = this.generator.toString();
        return Long.valueOf(value);
      }

      this.generator.appendChar(character);
    }
  }

  //--------------------------------------------------------------------------------------------------------------------
  // Check while reading
  //--------------------------------------------------------------------------------------------------------------------
  
  public void checkClassStart() {
    if (this.next() != Ascii.PERIOD)
      throw new ParserException("Expected directive: '.class'");
    if (this.next() != Ascii.LOWERCASE_C)
      throw new ParserException("Expected directive: '.class'");
    if (this.next() != Ascii.LOWERCASE_L)
      throw new ParserException("Expected directive: '.class'");
    if (this.next() != Ascii.LOWERCASE_A)
      throw new ParserException("Expected directive: '.class'");
    if (this.next() != Ascii.LOWERCASE_S)
      throw new ParserException("Expected directive: '.class'");
    if (this.next() != Ascii.LOWERCASE_S)
      throw new ParserException("Expected directive: '.class'");
  }

  public void checkClassEnd() {
    if (this.next() != Ascii.PERIOD)
      throw new ParserException("Expected directive: '.classend'");
    if (this.next() != Ascii.LOWERCASE_C)
      throw new ParserException("Expected directive: '.classend'");
    if (this.next() != Ascii.LOWERCASE_L)
      throw new ParserException("Expected directive: '.classend'");
    if (this.next() != Ascii.LOWERCASE_A)
      throw new ParserException("Expected directive: '.classend'");
    if (this.next() != Ascii.LOWERCASE_S)
      throw new ParserException("Expected directive: '.classend'");
    if (this.next() != Ascii.LOWERCASE_S)
      throw new ParserException("Expected directive: '.classend'");
    if (this.next() != Ascii.LOWERCASE_E)
      throw new ParserException("Expected directive: '.classend'");
    if (this.next() != Ascii.LOWERCASE_N)
      throw new ParserException("Expected directive: '.classend'");
    if (this.next() != Ascii.LOWERCASE_D)
      throw new ParserException("Expected directive: '.classend'");
  }

  public void checkMethodStart() {
    if (this.next() != Ascii.PERIOD)
      throw new ParserException("Expected directive: '.method'");
    if (this.next() != Ascii.LOWERCASE_M)
      throw new ParserException("Expected directive: '.method'");
    if (this.next() != Ascii.LOWERCASE_E)
      throw new ParserException("Expected directive: '.method'");
    if (this.next() != Ascii.LOWERCASE_T)
      throw new ParserException("Expected directive: '.method'");
    if (this.next() != Ascii.LOWERCASE_H)
      throw new ParserException("Expected directive: '.method'");
    if (this.next() != Ascii.LOWERCASE_O)
      throw new ParserException("Expected directive: '.method'");
    if (this.next() != Ascii.LOWERCASE_D)
      throw new ParserException("Expected directive: '.method'");
  }

  public void checkMethodEnd() {
    if (this.next() != Ascii.PERIOD)
      throw new ParserException("Expected directive: '.methodend'");
    if (this.next() != Ascii.LOWERCASE_M)
      throw new ParserException("Expected directive: '.methodend'");
    if (this.next() != Ascii.LOWERCASE_E)
      throw new ParserException("Expected directive: '.methodend'");
    if (this.next() != Ascii.LOWERCASE_T)
      throw new ParserException("Expected directive: '.methodend'");
    if (this.next() != Ascii.LOWERCASE_H)
      throw new ParserException("Expected directive: '.methodend'");
    if (this.next() != Ascii.LOWERCASE_O)
      throw new ParserException("Expected directive: '.methodend'");
    if (this.next() != Ascii.LOWERCASE_D)
      throw new ParserException("Expected directive: '.methodend'");
    if (this.next() != Ascii.LOWERCASE_E)
      throw new ParserException("Expected directive: '.methodend'");
    if (this.next() != Ascii.LOWERCASE_N)
      throw new ParserException("Expected directive: '.methodend'");
    if (this.next() != Ascii.LOWERCASE_D)
      throw new ParserException("Expected directive: '.methodend'");
  }

  //--------------------------------------------------------------------------------------------------------------------
  // Check but rewind
  //--------------------------------------------------------------------------------------------------------------------
  
  public boolean isClassStart() {
    this.mark();

    boolean isClassStart = this.next() == Ascii.PERIOD 
                        && this.next() == Ascii.LOWERCASE_C
                        && this.next() == Ascii.LOWERCASE_L 
                        && this.next() == Ascii.LOWERCASE_A 
                        && this.next() == Ascii.LOWERCASE_S
                        && this.next() == Ascii.LOWERCASE_S;
    this.reset();
    this.mark();

    return isClassStart;
  }

  public boolean isMethodStart() {
    this.mark();

    boolean isMethodStart = this.next() == Ascii.PERIOD 
                         && this.next() == Ascii.LOWERCASE_M
                         && this.next() == Ascii.LOWERCASE_E
                         && this.next() == Ascii.LOWERCASE_T
                         && this.next() == Ascii.LOWERCASE_H
                         && this.next() == Ascii.LOWERCASE_O
                         && this.next() == Ascii.LOWERCASE_D;
    this.reset();
    this.mark();

    return isMethodStart;
  }

  public boolean isMethodEnd() {
    this.mark();

    boolean isMethodEnd = this.next() == Ascii.PERIOD
                       && this.next() == Ascii.LOWERCASE_M
                       && this.next() == Ascii.LOWERCASE_E
                       && this.next() == Ascii.LOWERCASE_T
                       && this.next() == Ascii.LOWERCASE_H
                       && this.next() == Ascii.LOWERCASE_O
                       && this.next() == Ascii.LOWERCASE_D
                       && this.next() == Ascii.LOWERCASE_E
                       && this.next() == Ascii.LOWERCASE_N
                       && this.next() == Ascii.LOWERCASE_D;
    this.reset();
    this.mark();

    return isMethodEnd;
  }

  private boolean isIdentifierChar(int character) {
    return this.isAsciiLetter(character) 
        || this.isDigit(character) 
        || character == Ascii.UNDERSCORE
        || character == Ascii.DOLLAR_SIGN;
  }

  private boolean isInstructionChar(int character) {
    return this.isAsciiLetter(character) || this.isDigit(character) || character == Ascii.UNDERSCORE;
  }

  public boolean isClassModifier() {
    this.mark();
    int character = 0;

    while (this.isAsciiLetter(character = this.next())) {
      this.generator.appendChar(character);
    }
    
    this.reset();
    
    final String modifier = this.generator.toString();
    
    return "public".equals(modifier)
        || "final".equals(modifier)
        || "abstract".equals(modifier)
        || "interface".equals(modifier)
        || "super".equals(modifier);
  }

  public boolean isMethodModifier() {
    this.mark();
    int character = 0;

    while (this.isAsciiLetter(character = this.next())) {
      this.generator.appendChar(character);
    }
    
    this.reset();
    
    final String modifier = this.generator.toString();
    
    return "public".equals(modifier)
        || "private".equals(modifier)
        || "protected".equals(modifier)
        || "static".equals(modifier)
        || "final".equals(modifier)
        || "synchronized".equals(modifier)
        || "native".equals(modifier)
        || "abstract".equals(modifier)
        || "strictfp".equals(modifier);
  }

  //--------------------------------------------------------------------------------------------------------------------
  // Consume useless characters
  //--------------------------------------------------------------------------------------------------------------------
  
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
}
