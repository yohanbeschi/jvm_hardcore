package org.isk.jvmhardcore.pjba.parser.tokenizer;

import org.isk.jvmhardcore.pjba.parser.core.Reader;
import org.isk.jvmhardcore.pjba.parser.core.Tokenizer;
import org.isk.jvmhardcore.pjba.util.Ascii;

public class DirectiveTokenizer extends Tokenizer {

  public DirectiveTokenizer(String filename, Reader reader) {
    super(filename, reader);
  }

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

  public void checkSuperStart() {
    if (this.next() != Ascii.PERIOD)
      throw new ParserException("Expected directive: '.super'");
    if (this.next() != Ascii.LOWERCASE_S)
      throw new ParserException("Expected directive: '.super'");
    if (this.next() != Ascii.LOWERCASE_U)
      throw new ParserException("Expected directive: '.super'");
    if (this.next() != Ascii.LOWERCASE_P)
      throw new ParserException("Expected directive: '.super'");
    if (this.next() != Ascii.LOWERCASE_E)
      throw new ParserException("Expected directive: '.super'");
    if (this.next() != Ascii.LOWERCASE_R)
      throw new ParserException("Expected directive: '.super'");
  }

  public void checkInterfaceStart() {
    if (this.next() != Ascii.PERIOD)
      throw new ParserException("Expected directive: '.interface'");
    if (this.next() != Ascii.LOWERCASE_I)
      throw new ParserException("Expected directive: '.interface'");
    if (this.next() != Ascii.LOWERCASE_N)
      throw new ParserException("Expected directive: '.interface'");
    if (this.next() != Ascii.LOWERCASE_T)
      throw new ParserException("Expected directive: '.interface'");
    if (this.next() != Ascii.LOWERCASE_E)
      throw new ParserException("Expected directive: '.interface'");
    if (this.next() != Ascii.LOWERCASE_R)
      throw new ParserException("Expected directive: '.interface'");
    if (this.next() != Ascii.LOWERCASE_F)
      throw new ParserException("Expected directive: '.interface'");
    if (this.next() != Ascii.LOWERCASE_A)
      throw new ParserException("Expected directive: '.interface'");
    if (this.next() != Ascii.LOWERCASE_C)
      throw new ParserException("Expected directive: '.interface'");
    if (this.next() != Ascii.LOWERCASE_E)
      throw new ParserException("Expected directive: '.interface'");
  }

  public void checkField() {
    if (this.next() != Ascii.PERIOD)
      throw new ParserException("Expected directive: '.field'");
    if (this.next() != Ascii.LOWERCASE_F)
      throw new ParserException("Expected directive: '.field'");
    if (this.next() != Ascii.LOWERCASE_I)
      throw new ParserException("Expected directive: '.field'");
    if (this.next() != Ascii.LOWERCASE_E)
      throw new ParserException("Expected directive: '.field'");
    if (this.next() != Ascii.LOWERCASE_L)
      throw new ParserException("Expected directive: '.field'");
    if (this.next() != Ascii.LOWERCASE_D)
      throw new ParserException("Expected directive: '.field'");
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

  public boolean isInterface() {
    this.mark();

    boolean isInterface = this.next() == Ascii.PERIOD 
                       && this.next() == Ascii.LOWERCASE_I
                       && this.next() == Ascii.LOWERCASE_N
                       && this.next() == Ascii.LOWERCASE_T
                       && this.next() == Ascii.LOWERCASE_E
                       && this.next() == Ascii.LOWERCASE_R
                       && this.next() == Ascii.LOWERCASE_F
                       && this.next() == Ascii.LOWERCASE_A
                       && this.next() == Ascii.LOWERCASE_C
                       && this.next() == Ascii.LOWERCASE_E;

    this.reset();
    this.mark();

    return isInterface;
  }

  public boolean isField() {
    this.mark();

    boolean isField = this.next() == Ascii.PERIOD 
                   && this.next() == Ascii.LOWERCASE_F
                   && this.next() == Ascii.LOWERCASE_I
                   && this.next() == Ascii.LOWERCASE_E
                   && this.next() == Ascii.LOWERCASE_L
                   && this.next() == Ascii.LOWERCASE_D;
    this.reset();
    this.mark();

    return isField;
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
}
