package org.isk.jvmhardcore.pjba.parser.tokenizer;

import org.isk.jvmhardcore.pjba.instruction.meta.MetaInstruction;
import org.isk.jvmhardcore.pjba.instruction.meta.MetaInstructions;
import org.isk.jvmhardcore.pjba.parser.core.Reader;
import org.isk.jvmhardcore.pjba.parser.core.Tokenizer;
import org.isk.jvmhardcore.pjba.parser.core.util.StringGenerator;
import org.isk.jvmhardcore.pjba.util.Ascii;

public class InstructionTokenizer extends Tokenizer {

  final private StringGenerator generator;
  
  public InstructionTokenizer(String filename, Reader reader) {
    super(filename, reader);
    this.generator = new StringGenerator();
  }

  public MetaInstruction getMetaInstruction() {
    this.mark();
    int character = Ascii.NULL;

    while ((character = this.next()) != Ascii.SPACE
                        && character != Ascii.TAB
                        && character != Ascii.LF
                        && this.isInstructionOrLabelChar(character)) {
      this.generator.appendChar(character);
    }

    final String instructionStr = this.generator.toString();

    final MetaInstruction metaInstruction = MetaInstructions.getMetaInstruction(instructionStr);
    if (metaInstruction != null) {
      this.rewind();
      return metaInstruction;
    }

    this.reset();
    throw new ParserException("Unknown instruction.", instructionStr);
  }

  public String getLabelAsArg() {
    int character = 0;

    while (this.isInstructionOrLabelChar(character = this.next())) {
      this.generator.appendChar(character);
    }

    if (character == Ascii.COLON) {
      throw new ParserException("A label following an instruction should not end with a colon.");
    }

    this.rewind();
    
    return this.generator.toString();
  }

  public String getLabel() {
    int character = 0;

    while (this.isInstructionOrLabelChar(character = this.next())) {
      this.generator.appendChar(character);
    }

    if (character != Ascii.COLON) {
      throw new ParserException("Excepted colon <:>.");
    }

    final String label = this.generator.toString();
    
    if (label.isEmpty()) {
      throw new ParserException("Empty Label.");
    }

    return label;
  }
  private boolean isInstructionOrLabelChar(int character) {
    return this.isAsciiLetter(character) || this.isDigit(character) || character == Ascii.UNDERSCORE;
  }
  
  public boolean isLabel() {
    this.mark();
    int character = 0;

    while (this.isInstructionOrLabelChar(character = this.next())) {}
    
    this.reset();
    
    if (character == Ascii.COLON) {
      return true;
    } else {
      return false;
    }
  }
}
