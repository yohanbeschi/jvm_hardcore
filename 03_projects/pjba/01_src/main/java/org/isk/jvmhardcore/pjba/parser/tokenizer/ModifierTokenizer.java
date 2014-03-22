package org.isk.jvmhardcore.pjba.parser.tokenizer;

import org.isk.jvmhardcore.pjba.parser.core.Reader;
import org.isk.jvmhardcore.pjba.parser.core.Tokenizer;
import org.isk.jvmhardcore.pjba.parser.core.util.StringGenerator;
import org.isk.jvmhardcore.pjba.structure.ClassFile;
import org.isk.jvmhardcore.pjba.structure.Field;
import org.isk.jvmhardcore.pjba.structure.Method;

public class ModifierTokenizer extends Tokenizer {

  final private StringGenerator generator;

  public ModifierTokenizer(String filename, Reader reader) {
    super(filename, reader);
    this.generator = new StringGenerator();
  }

  public int getClassModifier() {
    // public final abstract interface super
    this.buildStringAndRewind();

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

  

  public int getFieldModifier() {
    this.buildStringAndRewind();

    final String modifier = this.generator.toString();

    if ("public".equals(modifier)) {
      return Field.MODIFIER_PUBLIC;
    } else if ("private".equals(modifier)) {
      return Field.MODIFIER_PRIVATE;
    } else if ("protected".equals(modifier)) {
      return Field.MODIFIER_PROTECTED;
    } else if ("static".equals(modifier)) {
      return Field.MODIFIER_STATIC;
    } else if ("final".equals(modifier)) {
      return Field.MODIFIER_FINAL;
    } else if ("volatile".equals(modifier)) {
      return Field.MODIFIER_VOLATILE;
    } else if ("transient".equals(modifier)) {
      return Field.MODIFIER_TRANSIENT;
    } else {
      throw new ParserException("Unknown method modifier: " + modifier);
    }
  }

  public int getMethodModifier() {
    this.buildStringAndRewind();

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
  
  public boolean isClassModifier() {
    this.buildStringAndReset();
    
    final String modifier = this.generator.toString();
    
    return "public".equals(modifier)
        || "final".equals(modifier)
        || "abstract".equals(modifier)
        || "interface".equals(modifier)
        || "super".equals(modifier);
  }

  public boolean isFieldModifier() {
    this.buildStringAndReset();
    
    final String modifier = this.generator.toString();
    
    return "public".equals(modifier)
        || "private".equals(modifier)
        || "protected".equals(modifier)
        || "static".equals(modifier)
        || "final".equals(modifier)
        || "volatile".equals(modifier)
        || "transient".equals(modifier);
  }

  public boolean isMethodModifier() {
    this.buildStringAndReset();
    
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

  private void buildStringAndRewind() {
    int character = 0;

    while (this.isAsciiLetter(character = this.next())) {
      this.generator.appendChar(character);
    }

    this.rewind();
  }

  private void buildStringAndReset() {
    this.mark();
    int character = 0;

    while (this.isAsciiLetter(character = this.next())) {
      this.generator.appendChar(character);
    }
    
    this.reset();
  }
}
