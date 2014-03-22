package org.isk.jvmhardcore.pjba;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.isk.jvmhardcore.pjba.builder.ClassFileBuilder;
import org.isk.jvmhardcore.pjba.builder.LookupswitchBuilder;
import org.isk.jvmhardcore.pjba.builder.MethodBuilder;
import org.isk.jvmhardcore.pjba.builder.TableswitchBuilder;
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
import org.isk.jvmhardcore.pjba.parser.tokenizer.LiteralTokenizer.InternalConstantValue;
import org.isk.jvmhardcore.pjba.structure.ClassFile;
import org.isk.jvmhardcore.pjba.structure.Field;
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
    int fieldModifiers = 0;
    String fieldName = null;
    String fieldDescriptor = null;
    InternalConstantValue constantValue = null;
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
        case FIELD_START:
          this.tokenizer.checkFieldStart();
          break;
        case FIELD_MODIFIER:
          fieldModifiers |= this.tokenizer.getFieldModifier();
          break;
        case FIELD_NAME:
          fieldName = this.tokenizer.getFieldName();
          break;
        case FIELD_TYPE:
          fieldDescriptor = this.tokenizer.getFieldType();
          break;
        case CONSTANT_VALUE:
          constantValue = this.tokenizer.getConstantValue();
          break;
        case FIELD_END:
          if (constantValue == null) {
            this.classFileBuilder.newField(fieldModifiers, fieldName, fieldDescriptor);
          } else if ((fieldModifiers & Field.MODIFIER_FINAL) == Field.MODIFIER_FINAL) {
            if (constantValue.isString()) {
              this.classFileBuilder.newConstantField(fieldModifiers, fieldName, fieldDescriptor, constantValue.getString());
            } else if (constantValue.isInteger()) {
              this.classFileBuilder.newConstantField(fieldModifiers, fieldName, fieldDescriptor, constantValue.getInt());
            } else if (constantValue.isLong()) {
              this.classFileBuilder.newConstantField(fieldModifiers, fieldName, fieldDescriptor, constantValue.getLong());
            } else if (constantValue.isFloat()) {
              this.classFileBuilder.newConstantField(fieldModifiers, fieldName, fieldDescriptor, constantValue.getFloat());
            } else if (constantValue.isDouble()) {
              this.classFileBuilder.newConstantField(fieldModifiers, fieldName, fieldDescriptor, constantValue.getDouble());
            }

            constantValue = null;
          } else {
            throw this.tokenizer.new ParserException(
                  "The field <"
                + fieldName
                + "> can't be initialized because it's not <final>. Tip: If the field is not intended to be <final> " +
                "you need to initialize it in a static block.", false);
          }

          fieldModifiers = 0;
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
        final InternalConstantValue ifs = this.tokenizer.getConstantValue();

        if (ifs.isLong() || ifs.isInteger()) {
          this.methodBuilder.ldc(ifs.getInt());
        } else if (ifs.isDouble() || ifs.isFloat()) {
          this.methodBuilder.ldc(ifs.getFloat());
        } else if (ifs.isString()) {
          this.methodBuilder.ldc(ifs.getString());
        } else {
          throw new RuntimeException("Expected: an int, a float or a string");
        }

        break;
      case LD_CONSTANT:
        this.tokenizer.consumeWhitespaces();
        final InternalConstantValue ld = this.tokenizer.getConstantValue();

        if (ld.isLong() || ld.isInteger()) {
          this.methodBuilder.ldc(ld.getLong());
        } else if (ld.isDouble() || ld.isFloat()) {
          this.methodBuilder.ldc(ld.getDouble());
        } else {
          throw new RuntimeException("Expected: a long or a double");
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
        final Instruction ifInstruction = ((ShortArgMetaInstruction) metaInstruction).buildInstruction((byte) 0);
        this.methodBuilder.instruction(ifInstruction, label);
        break;
      case GOTO:
        this.tokenizer.consumeWhitespaces();
        final String gLabel = this.tokenizer.getLabelAsArg();
        this.methodBuilder.goto_(gLabel);
        break;
      case TABLE_SWITCH:
        this.tokenizer.consumeWhitespaces();
        final String tDefaultLabel = this.tokenizer.getLabelAsArg();
        this.tokenizer.consumeWhitespaces();
        final int lowValue = this.tokenizer.getIntValue();
        this.tokenizer.consumeWhitespaces();
        final int highValue = this.tokenizer.getIntValue();
        this.tokenizer.consumeWhitespaces();
        final TableswitchBuilder tsb = this.methodBuilder.tableswitch(tDefaultLabel, lowValue, highValue);

        final int nbOffsets = highValue - lowValue + 1;

        for (int i = 0; i < nbOffsets; i++) {
          this.tokenizer.consumeWhitespaces();
          final String tLabel = this.tokenizer.getLabelAsArg();
          tsb.offset(tLabel);
        }

        tsb.end();

        break;
      case LOOKUP_SWITCH:
        this.tokenizer.consumeWhitespaces();
        final String defaultLabel = this.tokenizer.getLabelAsArg();
        this.tokenizer.consumeWhitespaces();
        final int nbPairs = this.tokenizer.getIntValue();
        this.tokenizer.consumeWhitespaces();
        final LookupswitchBuilder lsb = this.methodBuilder.lookupswitch(defaultLabel, nbPairs);

        for (int i = 0; i < nbPairs; i++) {
          this.tokenizer.consumeWhitespaces();
          final int key = this.tokenizer.getIntValue();
          this.tokenizer.consumeWhitespaces();
          final String lLabel = this.tokenizer.getLabelAsArg();
          lsb.matchOffset(key, lLabel);
        }

        lsb.end();

        break;
      case FIELD:
        this.tokenizer.consumeWhitespaces();
        final String fieldClasseName = this.tokenizer.getClassName();
        this.tokenizer.consumeWhitespaces();
        final String fieldName = this.tokenizer.getFieldName();
        this.tokenizer.consumeWhitespaces();
        final String fieldType = this.tokenizer.getFieldType();

        if ("getstatic".equals(metaInstruction.getMnemonic())) {
          this.methodBuilder.getstatic(fieldClasseName, fieldName, fieldType);
        } else if ("putstatic".equals(metaInstruction.getMnemonic())) {
          this.methodBuilder.putstatic(fieldClasseName, fieldName, fieldType);
        }

        break;
      case METHOD:
        this.tokenizer.consumeWhitespaces();
        final String methodClassName = this.tokenizer.getClassName();
        this.tokenizer.consumeWhitespaces();
        final String methodName = this.tokenizer.getMethodName();
        this.tokenizer.consumeWhitespaces();
        final String methodSignature = this.tokenizer.getMethodSignature();

        if ("invokestatic".equals(metaInstruction.getMnemonic())) {
          this.methodBuilder.invokestatic(methodClassName, methodName, methodSignature);
        }
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
    this.table[Symbols.CLASS_START_IDENTIFIER] = new Productions.ClassStart();
    this.table[Symbols.CLASS_END_IDENTIFIER] = new Productions.ClassEnd();
    this.table[Symbols.CLASS_MODIFIERS] = new Productions.ClassModifiers();
    this.table[Symbols.CLASS_MODIFIER] = new Productions.ClassModifier();
    this.table[Symbols.CLASS_NAME] = new Productions.ClassName();
    this.table[Symbols.CLASS_CONTENT] = new Productions.ClassContent();
    this.table[Symbols.FIELDS] = new Productions.Fields();
    this.table[Symbols.FIELD] = new Productions.Field();
    this.table[Symbols.FIELD_START_IDENTIFIER] = new Productions.FieldStart();
    this.table[Symbols.FIELD_END] = new Productions.FieldEnd();
    this.table[Symbols.FIELD_MODIFIERS] = new Productions.FieldModifiers();
    this.table[Symbols.FIELD_MODIFIER] = new Productions.FieldModifier();
    this.table[Symbols.FIELD_NAME] = new Productions.FieldName();
    this.table[Symbols.FIELD_DESCRIPTOR] = new Productions.FieldDescriptor();
    this.table[Symbols.O_FIELD_ASSIGNEMENT] = new Productions.OFieldAssignement();
    this.table[Symbols.METHOD_CONTENT] = new Productions.MethodContent();
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
    this.table[Symbols.CONSTANT_VALUE] = new Productions.ConstantValue();
    this.table[Symbols.WS] = new Productions.Whitespaces();
  }
}
