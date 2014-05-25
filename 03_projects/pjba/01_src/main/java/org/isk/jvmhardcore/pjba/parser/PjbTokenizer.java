package org.isk.jvmhardcore.pjba.parser;

import org.isk.jvmhardcore.pjba.instruction.meta.MetaInstruction;
import org.isk.jvmhardcore.pjba.parser.core.Reader;
import org.isk.jvmhardcore.pjba.parser.core.Tokenizer;
import org.isk.jvmhardcore.pjba.parser.tokenizer.DirectiveTokenizer;
import org.isk.jvmhardcore.pjba.parser.tokenizer.InstructionTokenizer;
import org.isk.jvmhardcore.pjba.parser.tokenizer.LiteralTokenizer;
import org.isk.jvmhardcore.pjba.parser.tokenizer.LiteralTokenizer.InternalConstantValue;
import org.isk.jvmhardcore.pjba.parser.tokenizer.ModifierTokenizer;
import org.isk.jvmhardcore.pjba.parser.tokenizer.NameTokenizer;
import org.isk.jvmhardcore.pjba.parser.tokenizer.WhitespaceTokenizer;

public class PjbTokenizer extends Tokenizer {

  final private DirectiveTokenizer directiveTokenizer;
  final private ModifierTokenizer modifierTokenizer;
  final private InstructionTokenizer instructionTokenizer;
  final private LiteralTokenizer literalTokenizer;
  final private NameTokenizer nameTokenizer;
  final private WhitespaceTokenizer whitespaceTokenizer;

  public PjbTokenizer(final String filename, final Reader reader) {
    super(filename, reader);
    this.directiveTokenizer = new DirectiveTokenizer(filename, reader);
    this.modifierTokenizer = new ModifierTokenizer(filename, reader);
    this.instructionTokenizer = new InstructionTokenizer(filename, reader);
    this.literalTokenizer = new LiteralTokenizer(filename, reader);
    this.nameTokenizer = new NameTokenizer(filename, reader);
    this.whitespaceTokenizer = new WhitespaceTokenizer(filename, reader);
  }

  // -------------------------------------------------------------------------------------------------------------------
  // Get to build graph objects
  // -------------------------------------------------------------------------------------------------------------------
  public int getClassModifier() {
    return this.modifierTokenizer.getClassModifier();
  }

  public String getClassName() {
    return this.nameTokenizer.getClassName();
  }

  public int getFieldModifier() {
    return this.modifierTokenizer.getFieldModifier();
  }

  public String getFieldName() {
    return this.nameTokenizer.getFieldName();
  }

  public String getFieldType() {
    return this.nameTokenizer.getFieldDescriptor();
  }

  public int getMethodModifier() {
    return this.modifierTokenizer.getMethodModifier();
  }

  public String getMethodName() {
    return this.nameTokenizer.getMethodName();
  }

  public String getMethodSignature() {
    return this.nameTokenizer.getMethodSignature();
  }

  public byte getArrayType() {
    return this.nameTokenizer.getArrayType();
  }

  // -------------------------------------------------------------------------------------------------------------------
  // Get instructions with parameters
  // -------------------------------------------------------------------------------------------------------------------

  public MetaInstruction getMetaInstruction() {
    return this.instructionTokenizer.getMetaInstruction();
  }

  public byte getByteValue() {
    return this.literalTokenizer.getByteValue();
  }

  public short getShortValue() {
    return this.literalTokenizer.getShortValue();
  }

  public int getIntValue() {
    return this.literalTokenizer.getIntValue();
  }

  public InternalConstantValue getConstantValue() {
    return this.literalTokenizer.getConstantValue();
  }

  public String getLabelAsArg() {
    return this.instructionTokenizer.getLabelAsArg();
  }

  public String getLabel() {
    return this.instructionTokenizer.getLabel();
  }

  // -------------------------------------------------------------------------------------------------------------------
  // Check while reading
  // -------------------------------------------------------------------------------------------------------------------

  public void checkClassStart() {
    this.directiveTokenizer.checkClassStart();
  }

  public void checkClassEnd() {
    this.directiveTokenizer.checkClassEnd();
  }

  public void checkSuperStart() {
    this.directiveTokenizer.checkSuperStart();
  }

  public void checkInterfaceStart() {
    this.directiveTokenizer.checkInterfaceStart();
  }

  public void checkFieldStart() {
    this.directiveTokenizer.checkField();
  }

  public void checkMethodStart() {
    this.directiveTokenizer.checkMethodStart();
  }

  public void checkMethodEnd() {
    this.directiveTokenizer.checkMethodEnd();
  }

  // -------------------------------------------------------------------------------------------------------------------
  // Check but rewind
  // -------------------------------------------------------------------------------------------------------------------

  public boolean isClassStart() {
    return this.directiveTokenizer.isClassStart();
  }

  public boolean isInterfaceStart() {
    return this.directiveTokenizer.isInterface();
  }

  public boolean isFieldStart() {
    return this.directiveTokenizer.isField();
  }

  public boolean isMethodStart() {
    return this.directiveTokenizer.isMethodStart();
  }

  public boolean isMethodEnd() {
    return this.directiveTokenizer.isMethodEnd();
  }

  public boolean isLabel() {
    return this.instructionTokenizer.isLabel();
  }

  public boolean isClassModifier() {
    return this.modifierTokenizer.isClassModifier();
  }

  public boolean isFieldModifier() {
    return this.modifierTokenizer.isFieldModifier();
  }

  public boolean isMethodModifier() {
    return this.modifierTokenizer.isMethodModifier();
  }

  public boolean isAssignementOperator() {
    return this.whitespaceTokenizer.isAssignementOperator();
  }

  // --------------------------------------------------------------------------------------------------------------------
  // Consume useless characters
  // --------------------------------------------------------------------------------------------------------------------

  public void consumeWhitespaces() {
    this.whitespaceTokenizer.consumeWhitespaces();
  }

  public void discardAssignementOperator() {
    this.whitespaceTokenizer.discardAssignementOperator();
  }
}
