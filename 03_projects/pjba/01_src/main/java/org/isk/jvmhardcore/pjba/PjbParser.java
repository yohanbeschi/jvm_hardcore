package org.isk.jvmhardcore.pjba;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.isk.jvmhardcore.pjba.builder.ClassFileBuilder;
import org.isk.jvmhardcore.pjba.builder.MethodBuilder;
import org.isk.jvmhardcore.pjba.instruction.Instructions;
import org.isk.jvmhardcore.pjba.instruction.meta.ByteArgMetaInstruction;
import org.isk.jvmhardcore.pjba.instruction.meta.MetaInstruction;
import org.isk.jvmhardcore.pjba.instruction.meta.NoArgMetaInstruction;
import org.isk.jvmhardcore.pjba.instruction.meta.ShortArgMetaInstruction;
import org.isk.jvmhardcore.pjba.parser.EventType;
import org.isk.jvmhardcore.pjba.parser.PjbTokenizer;
import org.isk.jvmhardcore.pjba.parser.Productions;
import org.isk.jvmhardcore.pjba.parser.Symbols;
import org.isk.jvmhardcore.pjba.parser.core.InputStreamReader;
import org.isk.jvmhardcore.pjba.parser.core.Parser;
import org.isk.jvmhardcore.pjba.structure.ClassFile;
import org.isk.jvmhardcore.pjba.structure.Instruction;

public class PjbParser extends Parser<List<ClassFile>, EventType, PjbTokenizer> {

  private List<ClassFile> classFiles;
  private ClassFileBuilder classFileBuilder;
  private MethodBuilder methodBuilder;

  public PjbParser(final String filename, final InputStream inputStream) {
    super(filename, inputStream, Symbols.number());
    this.classFiles = new LinkedList<ClassFile>();
  }

  public PjbParser(final InputStream inputStream) {
    super(inputStream, Symbols.number());
    this.classFiles = new LinkedList<ClassFile>();
  }

  @Override
  public List<ClassFile> parse() {
    EventType eventType = null;
    int classModifiers = 0;
    int methodModifiers = 0;
    String methodName = null;

    boolean done = false;
    while (!done) {
      eventType = this.getNextEvent();

      switch (eventType) {
        case CLASS_START:
          this.tokenizer.checkClassStart();
          break;
        case CLASS_MODIFIER:
          classModifiers |= this.tokenizer.getClassModifier();
          break;
        case CLASS_NAME:
          final String className = this.tokenizer.getClassName();
          this.classFileBuilder = new ClassFileBuilder(classModifiers, className);
          break;
        case CLASS_END:
          this.tokenizer.checkClassEnd();
          this.classFiles.add(this.classFileBuilder.build());
          classModifiers = 0;
          break;
        case METHOD_START:
          this.tokenizer.checkMethodStart();
          break;
        case METHOD_MODIFIER:
          methodModifiers |= this.tokenizer.getMethodModifier();
          break;
        case METHOD_NAME:
          methodName = this.tokenizer.getMethodName();
          break;
        case METHOD_SIGNATURE:
          final String methodDescriptor = this.tokenizer.getMethodSignature();
          this.methodBuilder = this.classFileBuilder.newMethod(methodModifiers, methodName, methodDescriptor, false);
          break;
        case METHOD_END:
          this.tokenizer.checkMethodEnd();
          methodModifiers = 0;
          break;
        case INSTRUCTION:
          this.processInstruction();
          break;
        case LABEL:
          final String label = this.tokenizer.getLabel();
          this.methodBuilder.label(label);
          break;
        case EOF:
          this.tokenizer.checkEndOfFile();
          done = true;
          break;
        default:
          System.err.println("Unexpected event.");
      }
    }

    return this.classFiles;
  }

  private void processInstruction() {
    final MetaInstruction metaInstruction = this.tokenizer.getMetaInstruction();
    Instruction instruction = null;

    switch (metaInstruction.getArgsType()) {
      case NONE:
        instruction = ((NoArgMetaInstruction) metaInstruction).buildInstruction();
        this.methodBuilder.instruction(instruction);
        break;
      case BYTE_VALUE:
        this.tokenizer.consumeWhitespaces();
        final byte byteValue = this.tokenizer.getByteValue();
        instruction = ((ByteArgMetaInstruction) metaInstruction).buildInstruction(byteValue);
        this.methodBuilder.instruction(instruction);
        break;
      case SHORT_VALUE:
        this.tokenizer.consumeWhitespaces();
        final short shortValue = this.tokenizer.getShortValue();
        instruction = ((ShortArgMetaInstruction) metaInstruction).buildInstruction(shortValue);
        this.methodBuilder.instruction(instruction);
        break;
      case IFS_CONSTANT:
        this.tokenizer.consumeWhitespaces();
        final Object ifs = this.tokenizer.getIfsConstant();

        if (ifs instanceof Integer) {
          this.methodBuilder.ldc((int) ifs);
        } else if (ifs instanceof Float) {
          this.methodBuilder.ldc((float) ifs);
        } else if (ifs instanceof String) {
          this.methodBuilder.ldc((String) ifs);
        }

        break;
      case LD_CONSTANT:
        this.tokenizer.consumeWhitespaces();
        final Object ld = this.tokenizer.getLdConstant();

        if (ld instanceof Long) {
          this.methodBuilder.ldc((long) ld);
        } else if (ld instanceof Double) {
          this.methodBuilder.ldc((double) ld);
        }

        break;
      case IINC:
        this.tokenizer.consumeWhitespaces();
        final int iincIndexInLV = this.tokenizer.getIntValue();
        this.tokenizer.consumeWhitespaces();
        final short constant = this.tokenizer.getShortValue();
        this.methodBuilder.iinc((short) iincIndexInLV, constant);
        break;
      case LV_INDEX:
        this.tokenizer.consumeWhitespaces();
        final int lvIndexInLV = this.tokenizer.getIntValue();
        
        if (lvIndexInLV >= Byte.MIN_VALUE && lvIndexInLV <= Byte.MAX_VALUE) {
          instruction = ((ByteArgMetaInstruction) metaInstruction).buildInstruction((byte) lvIndexInLV);
        } else {
          instruction = Instructions.wide_load_store((byte) metaInstruction.getOpcode(), (short) lvIndexInLV);
        }
        
        this.methodBuilder.instruction(instruction);
        
        break;
      case LABEL:
        this.tokenizer.consumeWhitespaces();
        final String label = this.tokenizer.getLabelAsArg();
        final Instruction ifInstruction = ((ShortArgMetaInstruction) metaInstruction).buildInstruction((byte)0);
        this.methodBuilder.instruction(ifInstruction, label);
        break;
      case W_IFS_CONSTANT:
      default:
        break;
    }
  }

  @Override
  protected PjbTokenizer newTokenizer(final String filename, final InputStream inputStream) {
    return new PjbTokenizer(filename, new InputStreamReader(inputStream));
  }

  @Override
  protected void initProductionTable() {
    this.table[Symbols.STREAM] = new Productions.Stream();
    this.table[Symbols.CLASSES] = new Productions.Classes();
    this.table[Symbols.EOF] = new Productions.EndOfFile();
    this.table[Symbols.CLASS] = new Productions.Class();
    this.table[Symbols.O_CLASS] = new Productions.OClass();
    this.table[Symbols.CLASS_START_IDENTIFIER] = new Productions.ClassStart();
    this.table[Symbols.CLASS_END_IDENTIFIER] = new Productions.ClassEnd();
    this.table[Symbols.CLASS_MODIFIERS] = new Productions.ClassModifiers();
    this.table[Symbols.CLASS_MODIFIER] = new Productions.ClassModifier();
    this.table[Symbols.CLASS_NAME] = new Productions.ClassName();
    this.table[Symbols.METHODS] = new Productions.Methods();
    this.table[Symbols.METHOD] = new Productions.Method();
    this.table[Symbols.METHOD_START_IDENTIFIER] = new Productions.MethodStart();
    this.table[Symbols.METHOD_END_IDENTIFIER] = new Productions.MethodEnd();
    this.table[Symbols.METHOD_MODIFIERS] = new Productions.MethodModifiers();
    this.table[Symbols.METHOD_MODIFIER] = new Productions.MethodModifier();
    this.table[Symbols.METHOD_NAME] = new Productions.MethodName();
    this.table[Symbols.METHOD_SIGNATURE] = new Productions.MethodSignature();
    this.table[Symbols.METHOD_CONTENT] = new Productions.MethodContent();
    this.table[Symbols.INSTRUCTION] = new Productions.Instruction();
    this.table[Symbols.LABEL] = new Productions.Label();
    this.table[Symbols.WS] = new Productions.Whitespaces();
  }
}
