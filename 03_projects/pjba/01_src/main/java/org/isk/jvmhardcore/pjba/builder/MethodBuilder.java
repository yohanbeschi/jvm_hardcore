package org.isk.jvmhardcore.pjba.builder;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.isk.jvmhardcore.pjba.instruction.Instructions;
import org.isk.jvmhardcore.pjba.instruction.LookupswitchInstruction;
import org.isk.jvmhardcore.pjba.instruction.TableswitchInstruction;
import org.isk.jvmhardcore.pjba.instruction.interfaces.LabeledInstruction;
import org.isk.jvmhardcore.pjba.instruction.interfaces.SwitchInstruction;
import org.isk.jvmhardcore.pjba.structure.ClassFile;
import org.isk.jvmhardcore.pjba.structure.Instruction;
import org.isk.jvmhardcore.pjba.structure.Method;
import org.isk.jvmhardcore.pjba.structure.attribute.Code;
import org.isk.jvmhardcore.pjba.util.DescriptorCounter;

public class MethodBuilder {
  final private static short SHORT_ZERO = 0;

  final private ClassFileBuilder classFileBuilder;
  final private Code code;
  final private boolean eagerConstruction;

  private int currentMethodLength;

  final private List<InstructionWrapper> instructions;
  final private List<InstructionWrapper> gotos;
  final private List<InstructionWrapper> labels;
  final private Map<String, InstructionWrapper> labelsAsMap;
  final private Map<String, List<LabelWrapper>> labelAsArgs;

  public MethodBuilder(ClassFileBuilder classFileBuilder, Method method, int parametersCount, boolean eagerConstruction) {
    this.labelAsArgs = new HashMap<>();
    this.classFileBuilder = classFileBuilder;

    if (eagerConstruction) {
      this.instructions = null;
      this.gotos = null;
      this.labels = null;
      this.labelsAsMap = new HashMap<>();
    } else {
      this.instructions = new LinkedList<>();
      this.gotos = new LinkedList<>();
      this.labels = new LinkedList<>();
      this.labelsAsMap = null;
    }
    
    final int codeAttributeIndex = this.classFileBuilder.getClassFile().addConstantUTF8(Code.ATTRIBUTE_NAME);
    this.code = new Code(codeAttributeIndex);
    this.code.setParameterCount(parametersCount);
    method.addAttibute(this.code);
    this.eagerConstruction = eagerConstruction;
  }

  // -------------------------------------------------------------------------------------------------------------------
  // Instructions
  // -------------------------------------------------------------------------------------------------------------------

  public MethodBuilder nop() {
    this.instruction(Instructions.NOP);
    return this;
  }

  public MethodBuilder aconst_null() {
    this.instruction(Instructions.ACONST_NULL);
    return this;
  }

  public MethodBuilder iconst_m1() {
    this.instruction(Instructions.ICONST_M1);
    return this;
  }

  public MethodBuilder iconst_0() {
    this.instruction(Instructions.ICONST_0);
    return this;
  }

  public MethodBuilder iconst_1() {
    this.instruction(Instructions.ICONST_1);
    return this;
  }

  public MethodBuilder iconst_2() {
    this.instruction(Instructions.ICONST_2);
    return this;
  }

  public MethodBuilder iconst_3() {
    this.instruction(Instructions.ICONST_3);
    return this;
  }

  public MethodBuilder iconst_4() {
    this.instruction(Instructions.ICONST_4);
    return this;
  }

  public MethodBuilder iconst_5() {
    this.instruction(Instructions.ICONST_5);
    return this;
  }

  public MethodBuilder lconst_0() {
    this.instruction(Instructions.LCONST_0);
    return this;
  }

  public MethodBuilder lconst_1() {
    this.instruction(Instructions.LCONST_1);
    return this;
  }

  public MethodBuilder fconst_0() {
    this.instruction(Instructions.FCONST_0);
    return this;
  }

  public MethodBuilder fconst_1() {
    this.instruction(Instructions.FCONST_1);
    return this;
  }

  public MethodBuilder fconst_2() {
    this.instruction(Instructions.FCONST_2);
    return this;
  }

  public MethodBuilder dconst_0() {
    this.instruction(Instructions.DCONST_0);
    return this;
  }

  public MethodBuilder dconst_1() {
    this.instruction(Instructions.DCONST_1);
    return this;
  }

  public MethodBuilder bipush(byte value) {
    this.instruction(Instructions.bipush(value));
    return this;
  }

  public MethodBuilder sipush(short value) {
    this.instruction(Instructions.sipush(value));
    return this;
  }

  public MethodBuilder ldc(String value) {
    final int utf8Index = this.classFileBuilder.getClassFile().addConstantUTF8(value);
    final int indexInCP = this.classFileBuilder.getClassFile().addConstantString(utf8Index);

    this.internalLdc(indexInCP);

    return this;
  }

  public MethodBuilder ldc(int value) {
    final int indexInCP = this.classFileBuilder.getClassFile().addConstantInteger(value);

    this.internalLdc(indexInCP);

    return this;
  }

  public MethodBuilder ldc(float value) {
    final int indexInCP = this.classFileBuilder.getClassFile().addConstantFloat(value);

    this.internalLdc(indexInCP);

    return this;
  }

  public MethodBuilder ldc(long value) {
    final int indexInCP = this.classFileBuilder.getClassFile().addConstantLong(value);
    this.instruction(Instructions.ldc2_w((short) indexInCP));
    return this;
  }

  public MethodBuilder ldc(double value) {
    final int indexInCP = this.classFileBuilder.getClassFile().addConstantDouble(value);
    this.instruction(Instructions.ldc2_w((short) indexInCP));
    return this;
  }

  private void internalLdc(final int indexInCP) {
    if (indexInCP > 255) { // 255 = Byte.MAX_UNSIGNED_VALUE
      this.instruction(Instructions.ldc_w((short) indexInCP));
    } else {
      this.instruction(Instructions.ldc((byte) indexInCP));
    }
  }

  public MethodBuilder iload(short indexInLV) {
    if (indexInLV >= Byte.MIN_VALUE && indexInLV <= Byte.MAX_VALUE) {
      this.instruction(Instructions.iload((byte) indexInLV));
    } else {
      this.instruction(Instructions.wide_iload(indexInLV));
    }
    return this;
  }

  public MethodBuilder lload(short indexInLV) {
    if (indexInLV >= Byte.MIN_VALUE && indexInLV <= Byte.MAX_VALUE) {
      this.instruction(Instructions.lload((byte) indexInLV));
    } else {
      this.instruction(Instructions.wide_lload(indexInLV));
    }
    return this;
  }

  public MethodBuilder fload(short indexInLV) {
    if (indexInLV >= Byte.MIN_VALUE && indexInLV <= Byte.MAX_VALUE) {
      this.instruction(Instructions.fload((byte) indexInLV));
    } else {
      this.instruction(Instructions.wide_fload(indexInLV));
    }
    return this;
  }

  public MethodBuilder dload(short indexInLV) {
    if (indexInLV >= Byte.MIN_VALUE && indexInLV <= Byte.MAX_VALUE) {
      this.instruction(Instructions.dload((byte) indexInLV));
    } else {
      this.instruction(Instructions.wide_dload(indexInLV));
    }
    return this;
  }

  public MethodBuilder aload(short indexInLV) {
    if (indexInLV >= Byte.MIN_VALUE && indexInLV <= Byte.MAX_VALUE) {
      this.instruction(Instructions.aload((byte) indexInLV));
    } else {
      this.instruction(Instructions.wide_aload(indexInLV));
    }
    return this;
  }

  public MethodBuilder iload_0() {
    this.instruction(Instructions.ILOAD_0);
    return this;
  }

  public MethodBuilder iload_1() {
    this.instruction(Instructions.ILOAD_1);
    return this;
  }

  public MethodBuilder iload_2() {
    this.instruction(Instructions.ILOAD_2);
    return this;
  }

  public MethodBuilder iload_3() {
    this.instruction(Instructions.ILOAD_3);
    return this;
  }

  public MethodBuilder lload_0() {
    this.instruction(Instructions.LLOAD_0);
    return this;
  }

  public MethodBuilder lload_1() {
    this.instruction(Instructions.LLOAD_1);
    return this;
  }

  public MethodBuilder lload_2() {
    this.instruction(Instructions.LLOAD_2);
    return this;
  }

  public MethodBuilder lload_3() {
    this.instruction(Instructions.LLOAD_3);
    return this;
  }

  public MethodBuilder fload_0() {
    this.instruction(Instructions.FLOAD_0);
    return this;
  }

  public MethodBuilder fload_1() {
    this.instruction(Instructions.FLOAD_1);
    return this;
  }

  public MethodBuilder fload_2() {
    this.instruction(Instructions.FLOAD_2);
    return this;
  }

  public MethodBuilder fload_3() {
    this.instruction(Instructions.FLOAD_3);
    return this;
  }

  public MethodBuilder dload_0() {
    this.instruction(Instructions.DLOAD_0);
    return this;
  }

  public MethodBuilder dload_1() {
    this.instruction(Instructions.DLOAD_1);
    return this;
  }

  public MethodBuilder dload_2() {
    this.instruction(Instructions.DLOAD_2);
    return this;
  }

  public MethodBuilder dload_3() {
    this.instruction(Instructions.DLOAD_3);
    return this;
  }

  public MethodBuilder aload_0() {
    this.instruction(Instructions.ALOAD_0);
    return this;
  }

  public MethodBuilder aload_1() {
    this.instruction(Instructions.ALOAD_1);
    return this;
  }

  public MethodBuilder aload_2() {
    this.instruction(Instructions.ALOAD_2);
    return this;
  }

  public MethodBuilder aload_3() {
    this.instruction(Instructions.ALOAD_3);
    return this;
  }

  public MethodBuilder istore(short indexInLV) {
    if (indexInLV >= Byte.MIN_VALUE && indexInLV <= Byte.MAX_VALUE) {
      this.instruction(Instructions.istore((byte) indexInLV));
    } else {
      this.instruction(Instructions.wide_istore(indexInLV));
    }
    return this;
  }

  public MethodBuilder lstore(short indexInLV) {
    if (indexInLV >= Byte.MIN_VALUE && indexInLV <= Byte.MAX_VALUE) {
      this.instruction(Instructions.lstore((byte) indexInLV));
    } else {
      this.instruction(Instructions.wide_lstore(indexInLV));
    }
    return this;
  }

  public MethodBuilder fstore(short indexInLV) {
    if (indexInLV >= Byte.MIN_VALUE && indexInLV <= Byte.MAX_VALUE) {
      this.instruction(Instructions.fstore((byte) indexInLV));
    } else {
      this.instruction(Instructions.wide_fstore(indexInLV));
    }
    return this;
  }

  public MethodBuilder dstore(short indexInLV) {
    if (indexInLV >= Byte.MIN_VALUE && indexInLV <= Byte.MAX_VALUE) {
      this.instruction(Instructions.dstore((byte) indexInLV));
    } else {
      this.instruction(Instructions.wide_dstore(indexInLV));
    }
    return this;
  }

  public MethodBuilder astore(short indexInLV) {
    if (indexInLV >= Byte.MIN_VALUE && indexInLV <= Byte.MAX_VALUE) {
      this.instruction(Instructions.astore((byte) indexInLV));
    } else {
      this.instruction(Instructions.wide_astore(indexInLV));
    }
    return this;
  }

  public MethodBuilder istore_0() {
    this.instruction(Instructions.ISTORE_0);
    return this;
  }

  public MethodBuilder istore_1() {
    this.instruction(Instructions.ISTORE_1);
    return this;
  }

  public MethodBuilder istore_2() {
    this.instruction(Instructions.ISTORE_2);
    return this;
  }

  public MethodBuilder istore_3() {
    this.instruction(Instructions.ISTORE_3);
    return this;
  }

  public MethodBuilder lstore_0() {
    this.instruction(Instructions.LSTORE_0);
    return this;
  }

  public MethodBuilder lstore_1() {
    this.instruction(Instructions.LSTORE_1);
    return this;
  }

  public MethodBuilder lstore_2() {
    this.instruction(Instructions.LSTORE_2);
    return this;
  }

  public MethodBuilder lstore_3() {
    this.instruction(Instructions.LSTORE_3);
    return this;
  }

  public MethodBuilder fstore_0() {
    this.instruction(Instructions.FSTORE_0);
    return this;
  }

  public MethodBuilder fstore_1() {
    this.instruction(Instructions.FSTORE_1);
    return this;
  }

  public MethodBuilder fstore_2() {
    this.instruction(Instructions.FSTORE_2);
    return this;
  }

  public MethodBuilder fstore_3() {
    this.instruction(Instructions.FSTORE_3);
    return this;
  }

  public MethodBuilder dstore_0() {
    this.instruction(Instructions.DSTORE_0);
    return this;
  }

  public MethodBuilder dstore_1() {
    this.instruction(Instructions.DSTORE_1);
    return this;
  }

  public MethodBuilder dstore_2() {
    this.instruction(Instructions.DSTORE_2);
    return this;
  }

  public MethodBuilder dstore_3() {
    this.instruction(Instructions.DSTORE_3);
    return this;
  }

  public MethodBuilder astore_0() {
    this.instruction(Instructions.ASTORE_0);
    return this;
  }

  public MethodBuilder astore_1() {
    this.instruction(Instructions.ASTORE_1);
    return this;
  }

  public MethodBuilder astore_2() {
    this.instruction(Instructions.ASTORE_2);
    return this;
  }

  public MethodBuilder astore_3() {
    this.instruction(Instructions.ASTORE_3);
    return this;
  }

  public MethodBuilder pop() {
    this.instruction(Instructions.POP);
    return this;
  }

  public MethodBuilder pop2() {
    this.instruction(Instructions.POP2);
    return this;
  }

  public MethodBuilder dup() {
    this.instruction(Instructions.DUP);
    return this;
  }

  public MethodBuilder dup_x1() {
    this.instruction(Instructions.DUP_X1);
    return this;
  }

  public MethodBuilder dup_x2() {
    this.instruction(Instructions.DUP_X2);
    return this;
  }

  public MethodBuilder dup2() {
    this.instruction(Instructions.DUP2);
    return this;
  }

  public MethodBuilder dup2_x1() {
    this.instruction(Instructions.DUP2_X1);
    return this;
  }

  public MethodBuilder dup2_x2() {
    this.instruction(Instructions.DUP2_X2);
    return this;
  }

  public MethodBuilder swap() {
    this.instruction(Instructions.SWAP);
    return this;
  }

  public MethodBuilder iadd() {
    this.instruction(Instructions.IADD);
    return this;
  }

  public MethodBuilder ladd() {
    this.instruction(Instructions.LADD);
    return this;
  }

  public MethodBuilder fadd() {
    this.instruction(Instructions.FADD);
    return this;
  }

  public MethodBuilder dadd() {
    this.instruction(Instructions.DADD);
    return this;
  }

  public MethodBuilder isub() {
    this.instruction(Instructions.ISUB);
    return this;
  }

  public MethodBuilder lsub() {
    this.instruction(Instructions.LSUB);
    return this;
  }

  public MethodBuilder fsub() {
    this.instruction(Instructions.FSUB);
    return this;
  }

  public MethodBuilder dsub() {
    this.instruction(Instructions.DSUB);
    return this;
  }

  public MethodBuilder imul() {
    this.instruction(Instructions.IMUL);
    return this;
  }

  public MethodBuilder lmul() {
    this.instruction(Instructions.LMUL);
    return this;
  }

  public MethodBuilder fmul() {
    this.instruction(Instructions.FMUL);
    return this;
  }

  public MethodBuilder dmul() {
    this.instruction(Instructions.DMUL);
    return this;
  }

  public MethodBuilder idiv() {
    this.instruction(Instructions.IDIV);
    return this;
  }

  public MethodBuilder ldiv() {
    this.instruction(Instructions.LDIV);
    return this;
  }

  public MethodBuilder fdiv() {
    this.instruction(Instructions.FDIV);
    return this;
  }

  public MethodBuilder ddiv() {
    this.instruction(Instructions.DDIV);
    return this;
  }

  public MethodBuilder irem() {
    this.instruction(Instructions.IREM);
    return this;
  }

  public MethodBuilder lrem() {
    this.instruction(Instructions.LREM);
    return this;
  }

  public MethodBuilder frem() {
    this.instruction(Instructions.FREM);
    return this;
  }

  public MethodBuilder drem() {
    this.instruction(Instructions.DREM);
    return this;
  }

  public MethodBuilder ineg() {
    this.instruction(Instructions.INEG);
    return this;
  }

  public MethodBuilder lneg() {
    this.instruction(Instructions.LNEG);
    return this;
  }

  public MethodBuilder fneg() {
    this.instruction(Instructions.FNEG);
    return this;
  }

  public MethodBuilder dneg() {
    this.instruction(Instructions.DNEG);
    return this;
  }

  public MethodBuilder ishl() {
    this.instruction(Instructions.ISHL);
    return this;
  }

  public MethodBuilder lshl() {
    this.instruction(Instructions.LSHL);
    return this;
  }

  public MethodBuilder ishr() {
    this.instruction(Instructions.ISHR);
    return this;
  }

  public MethodBuilder lshr() {
    this.instruction(Instructions.LSHR);
    return this;
  }

  public MethodBuilder iushr() {
    this.instruction(Instructions.IUSHR);
    return this;
  }

  public MethodBuilder lushr() {
    this.instruction(Instructions.LUSHR);
    return this;
  }

  public MethodBuilder iand() {
    this.instruction(Instructions.IAND);
    return this;
  }

  public MethodBuilder land() {
    this.instruction(Instructions.LAND);
    return this;
  }

  public MethodBuilder ior() {
    this.instruction(Instructions.IOR);
    return this;
  }

  public MethodBuilder lor() {
    this.instruction(Instructions.LOR);
    return this;
  }

  public MethodBuilder ixor() {
    this.instruction(Instructions.IXOR);
    return this;
  }

  public MethodBuilder lxor() {
    this.instruction(Instructions.LXOR);
    return this;
  }

  public MethodBuilder iinc(short indexInLV, short constant) {
    if (indexInLV >= Byte.MIN_VALUE && indexInLV <= Byte.MAX_VALUE
        && constant >= Byte.MIN_VALUE && constant <= Byte.MAX_VALUE) {
      this.instruction(Instructions.iinc((byte) indexInLV, (byte) constant));
    } else {
      this.instruction(Instructions.wide_iinc(indexInLV, constant));
    }
    return this;
  }

  public MethodBuilder i2l() {
    this.instruction(Instructions.I2L);
    return this;
  }

  public MethodBuilder i2f() {
    this.instruction(Instructions.I2F);
    return this;
  }

  public MethodBuilder i2d() {
    this.instruction(Instructions.I2D);
    return this;
  }

  public MethodBuilder l2i() {
    this.instruction(Instructions.L2I);
    return this;
  }

  public MethodBuilder l2f() {
    this.instruction(Instructions.L2F);
    return this;
  }

  public MethodBuilder l2d() {
    this.instruction(Instructions.L2D);
    return this;
  }

  public MethodBuilder f2i() {
    this.instruction(Instructions.F2I);
    return this;
  }

  public MethodBuilder f2l() {
    this.instruction(Instructions.F2L);
    return this;
  }

  public MethodBuilder f2d() {
    this.instruction(Instructions.F2D);
    return this;
  }

  public MethodBuilder d2i() {
    this.instruction(Instructions.D2I);
    return this;
  }

  public MethodBuilder d2l() {
    this.instruction(Instructions.D2L);
    return this;
  }

  public MethodBuilder d2f() {
    this.instruction(Instructions.D2F);
    return this;
  }

  public MethodBuilder i2b() {
    this.instruction(Instructions.I2B);
    return this;
  }

  public MethodBuilder i2c() {
    this.instruction(Instructions.I2C);
    return this;
  }

  public MethodBuilder i2s() {
    this.instruction(Instructions.I2S);
    return this;
  }

  public MethodBuilder lcmp() {
    this.instruction(Instructions.LCMP);
    return this;
  }

  public MethodBuilder fcmpl() {
    this.instruction(Instructions.FCMPL);
    return this;
  }

  public MethodBuilder fcmpg() {
    this.instruction(Instructions.FCMPG);
    return this;
  }

  public MethodBuilder dcmpl() {
    this.instruction(Instructions.DCMPL);
    return this;
  }

  public MethodBuilder dcmpg() {
    this.instruction(Instructions.DCMPG);
    return this;
  }

  public MethodBuilder ifeq(String label) {
    final Instruction instruction = Instructions.ifeq(SHORT_ZERO);
    this.instruction(instruction, label);

    return this;
  }

  public MethodBuilder ifne(String label) {
    final Instruction instruction = Instructions.ifne(SHORT_ZERO);
    this.instruction(instruction, label);

    return this;
  }

  public MethodBuilder iflt(String label) {
    final Instruction instruction = Instructions.iflt(SHORT_ZERO);
    this.instruction(instruction, label);

    return this;
  }

  public MethodBuilder ifge(String label) {
    final Instruction instruction = Instructions.ifge(SHORT_ZERO);
    this.instruction(instruction, label);

    return this;
  }

  public MethodBuilder ifgt(String label) {
    final Instruction instruction = Instructions.ifgt(SHORT_ZERO);
    this.instruction(instruction, label);

    return this;
  }

  public MethodBuilder ifle(String label) {
    final Instruction instruction = Instructions.ifle(SHORT_ZERO);
    this.instruction(instruction, label);

    return this;
  }

  public MethodBuilder if_icmpeq(String label) {
    final Instruction instruction = Instructions.if_icmpeq(SHORT_ZERO);
    this.instruction(instruction, label);

    return this;
  }

  public MethodBuilder if_icmpne(String label) {
    final Instruction instruction = Instructions.if_icmpne(SHORT_ZERO);
    this.instruction(instruction, label);

    return this;
  }

  public MethodBuilder if_icmplt(String label) {
    final Instruction instruction = Instructions.if_icmplt(SHORT_ZERO);
    this.instruction(instruction, label);

    return this;
  }

  public MethodBuilder if_icmpge(String label) {
    final Instruction instruction = Instructions.if_icmpge(SHORT_ZERO);
    this.instruction(instruction, label);

    return this;
  }

  public MethodBuilder if_icmpgt(String label) {
    final Instruction instruction = Instructions.if_icmpgt(SHORT_ZERO);
    this.instruction(instruction, label);

    return this;
  }

  public MethodBuilder if_icmple(String label) {
    final Instruction instruction = Instructions.if_icmple(SHORT_ZERO);
    this.instruction(instruction, label);

    return this;
  }

  public MethodBuilder if_acmpeq(String label) {
    final Instruction instruction = Instructions.if_acmpeq(SHORT_ZERO);
    this.instruction(instruction, label);

    return this;
  }

  public MethodBuilder if_acmpne(String label) {
    final Instruction instruction = Instructions.if_acmpne(SHORT_ZERO);
    this.instruction(instruction, label);

    return this;
  }

  public MethodBuilder goto_(String label) {
    this.instructionGoto(label);

    return this;
  }

  public TableswitchBuilder tableswitch(String defaultLabel, int lowValue, int highValue) {
    final int positionBeforeInstruction = this.code.getCodeLength();
    final int padding = 3 - positionBeforeInstruction % 4;
    final int nbOffsets = highValue - lowValue + 1;

    final TableswitchInstruction instruction = (TableswitchInstruction)
        Instructions.tableswitch(padding, 0, lowValue, highValue, new int[nbOffsets]);

    instruction.setDefaultLabel(defaultLabel);
    final InstructionWrapper instructionWrapper = new InstructionWrapper(instruction, this.currentMethodLength);
    this.instruction(instructionWrapper, defaultLabel);

    return new TableswitchBuilder(instruction, instructionWrapper, this);
  }

  public LookupswitchBuilder lookupswitch(String defaultLabel, int nbPairs) {
    final int positionBeforeInstruction = this.code.getCodeLength();
    final int padding = 3 - positionBeforeInstruction % 4;

    final LookupswitchInstruction instruction = (LookupswitchInstruction)
        Instructions.lookupswitch(padding, 0, nbPairs, new int[nbPairs], new int[nbPairs]);

    instruction.setDefaultLabel(defaultLabel);
    final InstructionWrapper instructionWrapper = new InstructionWrapper(instruction, this.currentMethodLength);
    this.instruction(instructionWrapper, defaultLabel);

    return new LookupswitchBuilder(instruction, instructionWrapper, this);
  }

  public MethodBuilder ireturn() {
    this.instruction(Instructions.IRETURN);
    return this;
  }

  public MethodBuilder lreturn() {
    this.instruction(Instructions.LRETURN);
    return this;
  }

  public MethodBuilder freturn() {
    this.instruction(Instructions.FRETURN);
    return this;
  }

  public MethodBuilder dreturn() {
    this.instruction(Instructions.DRETURN);
    return this;
  }

  public MethodBuilder areturn() {
    this.instruction(Instructions.ARETURN);
    return this;
  }

  public MethodBuilder return_() {
    this.instruction(Instructions.RETURN);
    return this;
  }

  public MethodBuilder getstatic(String fullyQualifiedName, String fieldName, String fieldDescriptor) {
    final int fieldRefIndex = this.internalField(fullyQualifiedName, fieldName, fieldDescriptor);
    final int sizeInStack = DescriptorCounter.fieldDescriptorUnits(fieldDescriptor);

    this.instruction(Instructions.getstatic((short) fieldRefIndex, sizeInStack));

    return this;
  }

  public MethodBuilder putstatic(String fullyQualifiedName, String fieldName, String fieldDescriptor) {
    final int fieldRefIndex = this.internalField(fullyQualifiedName, fieldName, fieldDescriptor);
    final int sizeInStack = DescriptorCounter.fieldDescriptorUnits(fieldDescriptor);

    this.instruction(Instructions.putstatic((short) fieldRefIndex, sizeInStack));

    return this;
  }

  private int internalField(String fullyQualifiedName, String fieldName, String fieldDescriptor) {
    final int classIndex = this.addConstantClass(fullyQualifiedName);
    final int nameAndTypeIndex = this.addConstantNameAndType(fieldName, fieldDescriptor);
    return this.classFileBuilder.getClassFile().addConstantFieldRef(classIndex, nameAndTypeIndex);
  }

  public MethodBuilder invokestatic(String fullyQualifiedName, String methodName, String methodDescriptor) {
    final int methodRefIndex = this.internalMethod(fullyQualifiedName, methodName, methodDescriptor);
    final int stackDelta = DescriptorCounter.methodsDescriptorSignatureUnits(methodDescriptor);

    this.instruction(Instructions.invokestatic((short) methodRefIndex, stackDelta));

    return this;
  }

  private int internalMethod(String fullyQualifiedName, String methodName, String methodDescriptor) {
    final int classIndex = this.addConstantClass(fullyQualifiedName);
    final int nameAndTypeIndex = this.addConstantNameAndType(methodName, methodDescriptor);
    final int methodRefIndex = this.classFileBuilder.getClassFile().addConstantMethodRef(classIndex, nameAndTypeIndex);
    return methodRefIndex;
  }

  private int addConstantClass(String fullyQualifiedName) {
    final int utf8ClassIndex = this.classFileBuilder.getClassFile().addConstantUTF8(fullyQualifiedName);
    final int classIndex = this.classFileBuilder.getClassFile().addConstantClass(utf8ClassIndex);
    return classIndex;
  }

  private int addConstantNameAndType(String name, String type) {
    final int utf8NameIndex = this.classFileBuilder.getClassFile().addConstantUTF8(name);
    final int utf8DescriptorIndex = this.classFileBuilder.getClassFile().addConstantUTF8(type);
    return this.classFileBuilder.getClassFile().addConstantNameAndType(utf8NameIndex, utf8DescriptorIndex);
  }

  public MethodBuilder ifnull(String label) {
    final Instruction instruction = Instructions.ifnull(SHORT_ZERO);
    this.instruction(instruction, label);

    return this;
  }

  public MethodBuilder ifnonnull(String label) {
    final Instruction instruction = Instructions.ifnonnull(SHORT_ZERO);
    this.instruction(instruction, label);

    return this;
  }

  // -------------------------------------------------------------------------------------------------------------------
  // Helpers
  // -------------------------------------------------------------------------------------------------------------------

  private void instruction(InstructionWrapper instructionWrapper) {
    final Instruction instruction = instructionWrapper.instruction;

    if (this.eagerConstruction) {
      this.code.addInstruction(instruction);
    } else {
      this.instructions.add(instructionWrapper);
    }

    this.currentMethodLength += instruction.getLength();
  }

  private void instruction(InstructionWrapper instructionWrapper, String label) {
    this.addLabel(instructionWrapper, label);
    this.instruction(instructionWrapper);
  }

  public MethodBuilder instruction(Instruction instruction) {
    final InstructionWrapper instructionWrapper = new InstructionWrapper(instruction, this.currentMethodLength);
    this.instruction(instructionWrapper);

    return this;
  }

  public MethodBuilder instruction(Instruction instruction, String label) {
    final InstructionWrapper instructionWrapper = new InstructionWrapper(instruction, this.currentMethodLength);
    this.instruction(instructionWrapper, label);

    return this;
  }

  private void instructionGoto(String label) {
    if (this.eagerConstruction) {
      this.eagerGoto(label);
    } else {
      this.lazyGoto(label);
    }
  }

  private void eagerGoto(String label) {
    final InstructionWrapper labelInstructionWrapper = this.labelsAsMap.get(label);

    if (labelInstructionWrapper != null) {
      final int offset = labelInstructionWrapper.position - this.currentMethodLength;

      if (offset >= Short.MIN_VALUE && offset <= Short.MAX_VALUE) {
        this.instruction(Instructions.goto_((short) offset));
      } else {
        this.instruction(Instructions.goto_w(offset));
      }
      return;
    }

    // Label not declared
    final Instruction instruction = Instructions.goto_(SHORT_ZERO); // Eager construction never use goto_w
    this.instruction(instruction, label);
  }

  private void lazyGoto(String label) {
    final Instruction instruction = Instructions.goto_w(0);
    final InstructionWrapper instructionWrapper = new InstructionWrapper(label, instruction, this.currentMethodLength);
    this.instruction(instructionWrapper, label);
    this.gotos.add(instructionWrapper);
  }

  public MethodBuilder label(String label) {
    if (this.eagerConstruction) {
      this.eagerLabel(label);
    } else {
      this.lazyLabel(label);
    }

    return this;
  }

  private void eagerLabel(String label) {
    final List<LabelWrapper> labelWrappers = this.labelAsArgs.get(label);

    if (labelWrappers != null) {
      for (LabelWrapper labelWrapper : labelWrappers) {
        final InstructionWrapper labeledInstructionWrapper = labelWrapper.instruction;
        final int offset = this.currentMethodLength - labeledInstructionWrapper.position;
        final LabeledInstruction labeledInstruction = (LabeledInstruction) labeledInstructionWrapper.instruction;
        labeledInstruction.setOffset(label, offset);
      }
    }

    this.labelsAsMap.put(label, new InstructionWrapper(new LabelInstruction(label), this.currentMethodLength));
  }

  private void lazyLabel(String label) {
    final InstructionWrapper instructionWrapper = new InstructionWrapper(new LabelInstruction(label), this.currentMethodLength);
    this.instruction(instructionWrapper);
    this.labels.add(instructionWrapper);

    final List<LabelWrapper> labelWrappers = this.labelAsArgs.get(label);

    if (labelWrappers != null) {
      for (LabelWrapper labelWrapper : labelWrappers) {
        labelWrapper.setBestEffortPosition(this.currentMethodLength);
      }
    }
  }

  protected void addLabel(InstructionWrapper instructionWrapper, String label) {
    if (this.eagerConstruction) {
      // Was the method label() already called with the same label as the one in parameter ?
      final InstructionWrapper labelInstructionWrapper = this.labelsAsMap.get(label);

      if (labelInstructionWrapper != null) {
        final LabeledInstruction labeledInstruction = (LabeledInstruction) instructionWrapper.instruction;
        final int offset = labelInstructionWrapper.position - this.currentMethodLength;
        labeledInstruction.setOffset(label, offset);

        return;
      }
    }

    // The method label() has not been called with the same label as the one in parameter
    List<LabelWrapper> list = this.labelAsArgs.get(label);
    if (list == null) {
      list = new LinkedList<>();
      this.labelAsArgs.put(label, list);
    }

    final LabelWrapper labelWrapper = new LabelWrapper(instructionWrapper);
    list.add(labelWrapper);
  }

  public ClassFile build() {
    return this.classFileBuilder.build();
  }

  void buildMethod() {
    if (!this.eagerConstruction) {
      this.checkGotoInstructions();
      this.setPositions();
      this.setOffsets();
      this.addInstructions();
    }
  }

  // -------------------------------------------------------------------------------------------------------------------
  // Lazy building
  // -------------------------------------------------------------------------------------------------------------------

  private void checkGotoInstructions() {
    // For each goto check the label associated and change goto_w into goto if needed
    for (InstructionWrapper instructionWrapper : this.gotos) {
      final List<LabelWrapper> labelWrappers = this.labelAsArgs.get(instructionWrapper.label);

      final int bestEffortOffset = labelWrappers.get(0).bestEffortPosition - instructionWrapper.position;

      // If the best effort position doesn't require a 'goto_w' we use a simple 'goto'
      if (bestEffortOffset >= Short.MIN_VALUE && bestEffortOffset <= Short.MAX_VALUE) {
        instructionWrapper.reset(Instructions.goto_(SHORT_ZERO));
      }
    }
  }

  private void setPositions() {
    // Set all positions
    int currentPosition = 0;
    for (InstructionWrapper instructionWrapper : this.instructions) {
      instructionWrapper.setPosition(currentPosition);

      // Set [Table/Lookup]Switch padding
      final Instruction instruction = instructionWrapper.instruction;
      if (instruction instanceof SwitchInstruction) {
        final int positionBeforeInstruction = instructionWrapper.position;
        final int padding = 3 - positionBeforeInstruction % 4;
        ((SwitchInstruction) instruction).setPadding(padding);
      }

      currentPosition += instructionWrapper.instruction.getLength();
    }
  }

  private void setOffsets() {
    // Set all offsets
    for (InstructionWrapper instructionWrapper : this.labels) {
      final LabelInstruction labelInstruction = (LabelInstruction) instructionWrapper.instruction;
      // Get instructions associated with label
      // And set the offset for each instruction
      final List<LabelWrapper> labelWrappers = this.labelAsArgs.get(labelInstruction.label);

      if (labelWrappers != null) {
        for (LabelWrapper labelWrapper : labelWrappers) {
          final InstructionWrapper labeledInstructionWrapper = labelWrapper.instruction;
          final int offset = instructionWrapper.position - labeledInstructionWrapper.position;
          final LabeledInstruction labeledInstruction = (LabeledInstruction) labeledInstructionWrapper.instruction;
          labeledInstruction.setOffset(labelInstruction.label, offset);
        }
      } else {
        System.out.println("No instruction for label: " + labelInstruction.label);
      }
    }
  }

  private void addInstructions() {
    // Add instructions to code
    for (InstructionWrapper instructionWrapper : this.instructions) {
      if (!(instructionWrapper.instruction instanceof LabelInstruction)) {
        this.code.addInstruction(instructionWrapper.instruction);
      }
    }
  }

  // -------------------------------------------------------------------------------------------------------------------
  // Classes
  // -------------------------------------------------------------------------------------------------------------------

  private class LabelWrapper {
    final InstructionWrapper instruction;
    int bestEffortPosition;

    public LabelWrapper(InstructionWrapper instruction) {
      super();
      this.instruction = instruction;
    }

    private void setBestEffortPosition(int position) {
      this.bestEffortPosition = position;
    }
  }

  class InstructionWrapper {
    String label; // Only used for goto in lazy mode
    Instruction instruction;
    int position;

    public InstructionWrapper(Instruction instruction, int position) {
      super();
      this.instruction = instruction;
      this.position = position;
    }

    // Only used for goto in lazy mode
    public InstructionWrapper(String label, Instruction instruction, int bestEffortPosition) {
      this(instruction, bestEffortPosition);
      this.label = label;
    }

    public void reset(Instruction instruction) {
      this.instruction = instruction;
    }

    private void setPosition(int position) {
      this.position = position;
    }
  }

  private class LabelInstruction extends Instruction {
    final String label;

    public LabelInstruction(String label) {
      super(0, 0, 0, 0);
      this.label = label;
    }
  }
}
