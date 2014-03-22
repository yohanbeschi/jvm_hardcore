package org.isk.jvmhardcore.pjba.parser.tokenizer;

import org.isk.jvmhardcore.pjba.parser.core.Reader;
import org.isk.jvmhardcore.pjba.parser.core.Tokenizer;
import org.isk.jvmhardcore.pjba.parser.core.util.StringGenerator;
import org.isk.jvmhardcore.pjba.util.Ascii;

public class LiteralTokenizer extends Tokenizer {

  final private StringGenerator generator;

  public LiteralTokenizer(String filename, Reader reader) {
    super(filename, reader);
    this.generator = new StringGenerator();
  }
  
  public byte getByteValue() {
    final InternalConstantValue constant = this.getConstantValue();

    try {
      return Byte.parseByte(constant.value);
    } catch (NumberFormatException e) {
      throw new ParserException("Expected: a number between -128 and 127");
    }
  }

  public short getShortValue() {
    final InternalConstantValue constant = this.getConstantValue();

    try {
      return Short.parseShort(constant.value);
    } catch (NumberFormatException e) {
      throw new ParserException("Expected: a number between -32768 and 32767");
    }
  }

  public int getIntValue() {
    final InternalConstantValue constant = this.getConstantValue();
    return constant.getInt();
  }
  
  public InternalConstantValue getConstantValue() {
    int character = this.peek();
    
    switch (character) {
      case Ascii.SINGLE_QUOTE:
        return this.readCharacter();
      case Ascii.QUOTATION_MARK:
        return this.readString();
      case Ascii.LOWERCASE_F:
        return this.readFalse();
      case Ascii.LOWERCASE_T:
        return this.readTrue();
      default:
        return this.readNumber();
    }
  }

  private InternalConstantValue readCharacter() {
    this.next();
    final String character = Integer.toString(this.next());
    
    if (this.next() != Ascii.SINGLE_QUOTE) {
      throw new ParserException("Expected: a single quotes (end of character)");
    }
    
    final InternalConstantValue value = new InternalConstantValue(character);
    value.isInteger = true;
    return value;
  }

  private InternalConstantValue readString() {
    int character = this.next(); // read: _"_
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

    final InternalConstantValue value = new InternalConstantValue(this.generator.toString());
    value.isString = true;
    return value;
  }
  
  private InternalConstantValue readFalse() {
    if (   this.next() != Ascii.LOWERCASE_F
        || this.next() != Ascii.LOWERCASE_A
        || this.next() != Ascii.LOWERCASE_L
        || this.next() != Ascii.LOWERCASE_S
        || this.next() != Ascii.LOWERCASE_E) {
      throw new ParserException("Expected: true");
    }

    final InternalConstantValue value = new InternalConstantValue("0");
    value.isInteger = true;
    return value;
  }

  private InternalConstantValue readTrue() {
    if (   this.next() != Ascii.LOWERCASE_T
        || this.next() != Ascii.LOWERCASE_R
        || this.next() != Ascii.LOWERCASE_U
        || this.next() != Ascii.LOWERCASE_E) {
      throw new ParserException("Expected: true");
    }

    final InternalConstantValue value = new InternalConstantValue("1");
    value.isInteger = true;
    return value;
  }

  //Float
  // 1.
  // .1
  // .1e-1
  // -1.1e-1
  // 1.e-1
  // 1e-1
  // +1e+1
  private InternalConstantValue readNumber() {
    InternalConstantValue value = null;
    int character = this.next();

    boolean isPeriodConsumed = false;

    switch(character) {
      case Ascii.PERIOD:
        isPeriodConsumed = true;
      case Ascii.PLUS_SIGN:
      case Ascii.HYPHEN:
        // Next must be a digit
        this.generator.appendChar(character);
        character = this.next();
      default: // character must be a digit
        if (this.isDigit(character)) {
          this.generator.appendChar(character);
        } else {
          throw new ParserException("Expected: a number");
        }
    }

    if (isPeriodConsumed) {
      value = this.fillFloat(true);
    }

    character = this.fillWithDigits();
    
    switch(character) {
      case Ascii.PERIOD:
      case Ascii.LOWERCASE_E:
      case Ascii.UPPERCASE_E:
        value = this.fillFloat(false);
        break;
      case Ascii.LOWERCASE_L:
      case Ascii.UPPERCASE_L:
        this.next(); // consume _l_ or _L_
        value = new InternalConstantValue(this.generator.toString());
        value.isLong = true;
        break;
      default: // must be a digit
        value = new InternalConstantValue(this.generator.toString());
        value.isInteger = true;
    }

    return value;
  }

  private int fillWithDigits() {
    int character = 0;

    while (this.isDigit(character = this.next())) {
      this.generator.appendChar(character);
    }

    this.rewind();

    return character;
  }

  private InternalConstantValue fillFloat(boolean isPeriodConsumed) {
    boolean isExponentConsumed = false;
    int character = 0;
    
    for (;;) {
      character = this.next();
      
      if (!isPeriodConsumed && character == Ascii.PERIOD) {
        isPeriodConsumed = true;
        this.generator.appendChar(character);
      } else if (!isExponentConsumed && (character == Ascii.LOWERCASE_E || character == Ascii.UPPERCASE_E)) {
        isExponentConsumed = true;
        this.generator.appendChar(character);

        character = this.next();
        if (this.isDigit(character) || character == Ascii.PLUS_SIGN || character == Ascii.HYPHEN) {
          this.generator.appendChar(character);
        } 
      } else if (this.isDigit(character)) {
        this.generator.appendChar(character);
      } else {
        break;
      }
    }

    final InternalConstantValue value = new InternalConstantValue(this.generator.toString());
    value.isDouble = true;
    if (character == Ascii.LOWERCASE_F || character == Ascii.UPPERCASE_F) {
      value.isFloat = true;
      value.isDouble = true;
    } else if (character != Ascii.LOWERCASE_D && character != Ascii.UPPERCASE_D) {
      this.rewind();
    }

    return value;
  }

  public class InternalConstantValue {
    final String value;
    private boolean isString;
    private boolean isInteger;
    private boolean isLong;
    private boolean isFloat;
    private boolean isDouble;

    public InternalConstantValue(String value) {
      super();
      this.value = value;
      this.isString = false;
      this.isInteger = false;
      this.isLong = false;
      this.isFloat = false;
      this.isDouble = false;
    }

    public boolean isString() {
      return isString;
    }

    public boolean isInteger() {
      return isInteger;
    }

    public boolean isLong() {
      return isLong;
    }

    public boolean isFloat() {
      return isFloat;
    }

    public boolean isDouble() {
      return isDouble;
    }

    public int getInt() {
      try {
        return Integer.parseInt(this.value);
      } catch (NumberFormatException e) {
        throw new ParserException("Expected: an int and got: " + this.value, false);
      }
    }

    public float getFloat() {
      try {
        return Float.parseFloat(this.value);
      } catch (NumberFormatException e) {
        throw new ParserException("Expected: a float and got: " + this.value, false);
      }
    }

    public String getString() {
      return this.value;
    }

    public long getLong() {
      try {
        return Long.parseLong(this.value);
      } catch (NumberFormatException e) {
        throw new ParserException("Expected: a long and got: " + this.value, false);
      }
    }

    public double getDouble() {
      try {
        return Double.parseDouble(this.value);
      } catch (NumberFormatException e) {
        throw new ParserException("Expected: a double and got: " + this.value, false);
      }
    }
  }
}
