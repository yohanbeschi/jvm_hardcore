package org.isk.jvmhardcore.pjba;

import java.io.DataInput;
import java.io.EOFException;
import java.io.IOException;

import org.isk.jvmhardcore.pjba.dumper.StringValues;
import org.isk.jvmhardcore.pjba.instruction.LookupswitchInstruction;
import org.isk.jvmhardcore.pjba.instruction.TableswitchInstruction;
import org.isk.jvmhardcore.pjba.instruction.meta.ByteArgMetaInstruction;
import org.isk.jvmhardcore.pjba.instruction.meta.FieldAndMethodMetaInstruction;
import org.isk.jvmhardcore.pjba.instruction.meta.IincMetaInstruction;
import org.isk.jvmhardcore.pjba.instruction.meta.IntArgMetaInstruction;
import org.isk.jvmhardcore.pjba.instruction.meta.InvokeinterfaceMetaInstruction;
import org.isk.jvmhardcore.pjba.instruction.meta.LookupswitchMetaInstruction;
import org.isk.jvmhardcore.pjba.instruction.meta.MetaInstruction;
import org.isk.jvmhardcore.pjba.instruction.meta.MetaInstructions;
import org.isk.jvmhardcore.pjba.instruction.meta.NoArgMetaInstruction;
import org.isk.jvmhardcore.pjba.instruction.meta.ShortArgMetaInstruction;
import org.isk.jvmhardcore.pjba.instruction.meta.TableswitchMetaInstruction;
import org.isk.jvmhardcore.pjba.instruction.meta.WideMetaInstruction;
import org.isk.jvmhardcore.pjba.structure.ClassFile;
import org.isk.jvmhardcore.pjba.structure.Constant;
import org.isk.jvmhardcore.pjba.structure.Constant.ConstantPoolEntry;
import org.isk.jvmhardcore.pjba.structure.Field;
import org.isk.jvmhardcore.pjba.structure.FieldAndMethod;
import org.isk.jvmhardcore.pjba.structure.Instruction;
import org.isk.jvmhardcore.pjba.structure.Interface;
import org.isk.jvmhardcore.pjba.structure.Method;
import org.isk.jvmhardcore.pjba.structure.attribute.Code;
import org.isk.jvmhardcore.pjba.structure.attribute.ConstantValue;
import org.isk.jvmhardcore.pjba.structure.attribute.constraint.FieldAttribute;
import org.isk.jvmhardcore.pjba.structure.attribute.constraint.MethodAttribute;
import org.isk.jvmhardcore.pjba.util.BytecodeUtils;
import org.isk.jvmhardcore.pjba.util.DescriptorCounter;
import org.isk.jvmhardcore.pjba.util.PjbaLinkedList;
import org.isk.jvmhardcore.pjba.visitor.Visitable;

public class Disassembler {
  final private DataInput dataInput;
  final private ClassFile classFile;

  public Disassembler(DataInput dataInput) {
    super();
    this.dataInput = dataInput;
    this.classFile = new ClassFile();
  }

  public ClassFile disassemble() {
    final long magicNumber = this.readInt();
    if (magicNumber != 0xCAFEBABE) {
      throw new RuntimeException("Invalid Class File Format");
    }

    this.readClass();

    this.checkEOF();

    return this.classFile;
  }

  private void checkEOF() {
    try {
      this.dataInput.readByte();
      throw new RuntimeException("Class " + this.classFile.getClassName() + " has not been completely read");
    } catch (EOFException e) {
      // Expected
    } catch (IOException e) {
      // Unexpected...
    }
  }

  // -------------------------------------------------------------------------------------------------------------------
  // Read Class
  // -------------------------------------------------------------------------------------------------------------------

  private void readClass() {
    final int version = this.readInt();
    this.classFile.setVersion(version);

    final int constantPoolCount = this.readUnsignedShort();
    final PjbaLinkedList<ConstantPoolEntry> constants = this.readConstants(constantPoolCount);
    this.classFile.setConstantPool(constants);

    final int accessFlags = this.readShort();
    this.classFile.setAccessFlags(accessFlags);

    final int thisClass = this.readUnsignedShort();
    this.classFile.setThisClass(thisClass);

    final Constant.Class constantClass = (Constant.Class) this.classFile.getConstant(thisClass);
    final Constant.UTF8 constantUTF8 = (Constant.UTF8) this.classFile.getConstant(constantClass.nameIndex);
    final String fullyQualifiedClassName = constantUTF8.value;
    this.classFile.setFullyQualifiedClassName(fullyQualifiedClassName);

    final int superClass = this.readUnsignedShort();
    this.classFile.setSuperClass(superClass);

    final int interfacesCount = this.readUnsignedShort();
    final PjbaLinkedList<Interface> interfaces = this.readInterfaces(interfacesCount);
    this.classFile.setInterfaces(interfaces);

    final int fieldsCount = this.readUnsignedShort();
    final PjbaLinkedList<Field> fields = this.readFields(fieldsCount);
    this.classFile.setFields(fields);

    final int methodsCount = this.readUnsignedShort();
    final PjbaLinkedList<Method> methods = this.readMethods(methodsCount);
    this.classFile.setMethods(methods);

    final int attributesCount = this.readUnsignedShort();
    if (attributesCount != 0) {
      throw new RuntimeException("Unable to read class attributes yet in class " + this.classFile.getClassName());
    }
  }

  // -------------------------------------------------------------------------------------------------------------------
  // Read Interfaces
  // -------------------------------------------------------------------------------------------------------------------

  private PjbaLinkedList<Interface> readInterfaces(int interfacesCount) {
    final PjbaLinkedList<Interface> list = new PjbaLinkedList<>();

    for (int i = 0; i < interfacesCount; i++) {
      final short constantClassIndex = this.readShort();
      list.add(new Interface(constantClassIndex));
    }

    return list;
  }

  // -------------------------------------------------------------------------------------------------------------------
  // Read Fields
  // -------------------------------------------------------------------------------------------------------------------

  private PjbaLinkedList<Field> readFields(int fieldsCount) {
    final PjbaLinkedList<Field> list = new PjbaLinkedList<>();

    for (int i = 0; i < fieldsCount; i++) {
      // System.out.println("Method no: " + i);
      list.add(this.readField());
    }

    return list;
  }

  private Field readField() {
    final Field field = new Field();

    this.readFieldAndMethod(field);

    final int attributesCount = this.readUnsignedShort();
    final PjbaLinkedList<FieldAttribute> attributes = this.readFieldAttributes(attributesCount);
    field.setAttributes(attributes);

    return field;
  }

  private <A extends Visitable> void readFieldAndMethod(FieldAndMethod<A> fieldAndMethod) {
    final int accessFlags = this.readShort();
    fieldAndMethod.setAccessFlags(accessFlags);

    final int nameIndex = this.readUnsignedShort();
    fieldAndMethod.setNameIndex(nameIndex);

    final int descriptorIndex = this.readUnsignedShort();
    fieldAndMethod.setDescriptorIndex(descriptorIndex);
  }

  private PjbaLinkedList<FieldAttribute> readFieldAttributes(int attributesCount) {
    final PjbaLinkedList<FieldAttribute> attributes = new PjbaLinkedList<>();

    for (int i = 0; i < attributesCount; i++) {
      final int attributeNameIndex = this.readUnsignedShort();
      final ConstantPoolEntry entry = this.classFile.getConstant(attributeNameIndex);
      final String attributeName = ((Constant.UTF8) entry).value;

      if (ConstantValue.ATTRIBUTE_NAME.equals(attributeName)) {
        final ConstantValue constantValue = this.readConstantValue(attributeNameIndex);
        attributes.add(constantValue);
      } else {
        throw new RuntimeException("Unknown attribute: " + attributeName + " in class " + this.classFile.getClassName());
      }
    }

    return attributes;
  }

  private ConstantValue readConstantValue(int attributeNameIndex) {
    final ConstantValue constantValue = new ConstantValue(attributeNameIndex);
    this.readInt(); // Read 2
    constantValue.setConstantValueIndex(this.readShort());
    return constantValue;
  }

  // -------------------------------------------------------------------------------------------------------------------
  // Read Methods
  // -------------------------------------------------------------------------------------------------------------------

  private PjbaLinkedList<Method> readMethods(int methodsCount) {
    final PjbaLinkedList<Method> list = new PjbaLinkedList<>();

    for (int i = 0; i < methodsCount; i++) {
      list.add(this.readMethod());
    }

    return list;
  }

  private Method readMethod() {
    final Method method = new Method();

    this.readFieldAndMethod(method);

    final int attributesCount = this.readUnsignedShort();
    final PjbaLinkedList<MethodAttribute> attributes = this.readMethodAttributes(attributesCount);
    method.setAttributes(attributes);

    return method;
  }

  private PjbaLinkedList<MethodAttribute> readMethodAttributes(int attributesCount) {
    final PjbaLinkedList<MethodAttribute> attributes = new PjbaLinkedList<>();

    for (int i = 0; i < attributesCount; i++) {
      final int attributeNameIndex = this.readUnsignedShort();
      final ConstantPoolEntry entry = this.classFile.getConstant(attributeNameIndex);
      final String attributeName = ((Constant.UTF8) entry).value;

      if (Code.ATTRIBUTE_NAME.equals(attributeName)) {
        final Code code = this.readCode(attributeNameIndex);
        attributes.add(code);
      } else {
        throw new RuntimeException("Unknown attribute: " + attributeName + " in class " + this.classFile.getClassName());
      }
    }

    return attributes;
  }

  // -------------------------------------------------------------------------------------------------------------------
  // Read Code
  // -------------------------------------------------------------------------------------------------------------------

  private Code readCode(final int attributeNameIndex) {
    final Code code = new Code(attributeNameIndex);

    // Attribute length
    this.readInt(); // Dynamically computed

    final int maxStack = this.readUnsignedShort();
    code.setMaxStack(maxStack);

    final int maxLocals = this.readUnsignedShort();
    code.setMaxLocals(maxLocals);

    final long codeLength = this.readUnsignedInt();
    code.setCodeLength((int) codeLength);

    final PjbaLinkedList<Instruction> instructions = this.readInstructions(codeLength);
    code.setInstructions(instructions);

    final int exceptionsCount = this.readUnsignedShort();
    if (exceptionsCount != 0) {
      throw new RuntimeException("Unable to read exceptions yet in class " + this.classFile.getClassName());
    }

    final int attributesCount = this.readUnsignedShort();
    if (attributesCount != 0) {
      throw new RuntimeException("Unable to read code attributes yet in class " + this.classFile.getClassName());
    }

    return code;
  }

  private PjbaLinkedList<Instruction> readInstructions(long codeSize) {
    final PjbaLinkedList<Instruction> instructions = new PjbaLinkedList<>();
    int bytesProceed = 0;

    while (bytesProceed < codeSize) {
      final int opcode = this.readUnsignedByte();
      bytesProceed++;

      final MetaInstruction metaInstruction = MetaInstructions.getMetaInstruction(opcode);
      Instruction instruction = null;

      if (metaInstruction instanceof NoArgMetaInstruction) {
        instruction = ((NoArgMetaInstruction) metaInstruction).buildInstruction();
      } else if (metaInstruction instanceof ByteArgMetaInstruction) {
        bytesProceed++;
        final byte b = this.readByte();
        instruction = ((ByteArgMetaInstruction) metaInstruction).buildInstruction(b);
      } else if (metaInstruction instanceof ShortArgMetaInstruction) {
        bytesProceed += 2;
        final short s = this.readShort();
        instruction = ((ShortArgMetaInstruction) metaInstruction).buildInstruction(s);
      } else if (metaInstruction instanceof IntArgMetaInstruction) {
        bytesProceed += 4;
        final int i = this.readInt();
        instruction = ((IntArgMetaInstruction) metaInstruction).buildInstruction(i);
      } else if (metaInstruction instanceof IincMetaInstruction) {
        bytesProceed += 2;
        final byte indexInLV = this.readByte();
        final byte constant = this.readByte();
        instruction = ((IincMetaInstruction) metaInstruction).buildInstruction(indexInLV, constant);
      } else if (metaInstruction instanceof FieldAndMethodMetaInstruction) {
        bytesProceed += 2;
        final short indexInCP = this.readShort();
        final String descriptor = StringValues.getRefDescriptor(indexInCP, this.classFile);
        int sizeInStack = 0;

        switch (metaInstruction.getArgsType()) {
          case FIELD:
            sizeInStack = DescriptorCounter.fieldDescriptorUnits(descriptor);
            break;
          case METHOD:
            sizeInStack = DescriptorCounter.methodsDescriptorSignatureUnits(descriptor);
            break;
          default:
            System.err.println("Impossible ArgsType in this context: " + metaInstruction.getArgsType());
        }

        instruction = ((FieldAndMethodMetaInstruction) metaInstruction).buildInstruction(indexInCP, sizeInStack);
      } else if (metaInstruction instanceof InvokeinterfaceMetaInstruction) {
        bytesProceed += 4;
        final short indexInCP = this.readShort();

        final String descriptor = StringValues.getRefDescriptor(indexInCP, this.classFile);
        final int sizeInStack = DescriptorCounter.methodsDescriptorSignatureUnits(descriptor);

        final int paramsCount = this.readByte();
        this.readByte(); // 0

        instruction = ((InvokeinterfaceMetaInstruction) metaInstruction).buildInstruction(indexInCP, sizeInStack, paramsCount);
      } else if (metaInstruction instanceof WideMetaInstruction) {
        final int widenedOpcode = this.readUnsignedByte();
        final MetaInstruction widenedMetaInstruction = MetaInstructions.getMetaInstruction(widenedOpcode);

        switch (widenedMetaInstruction.getArgsType()) {
          case IINC:
            bytesProceed += 5;
            final short iincIndexInLV = this.readShort();
            final short constant = this.readShort();
            instruction = ((WideMetaInstruction) metaInstruction).buildInstruction(iincIndexInLV, constant);
            break;
          default: // load and store
            bytesProceed += 3;
            final short otherIndexInLV = this.readShort();
            instruction = ((WideMetaInstruction) metaInstruction).buildInstruction((byte) widenedOpcode, otherIndexInLV);
        }
      } else if (metaInstruction instanceof TableswitchMetaInstruction) {
        final int padding = this.readSwitchPadding(bytesProceed - 1);
        final int defaultOffset = this.readInt();
        final int lowValue = this.readInt();
        final int highValue = this.readInt();
        final int[] jumpOffsets = new int[highValue - lowValue + 1];

        for (int i = 0; i < jumpOffsets.length; i++) {
          jumpOffsets[i] = this.readInt();
        }

        bytesProceed += TableswitchInstruction.getLength(padding, jumpOffsets.length) - 1;

        instruction = ((TableswitchMetaInstruction) metaInstruction).buildInstruction(padding, defaultOffset, lowValue,
            highValue, jumpOffsets);
      } else if (metaInstruction instanceof LookupswitchMetaInstruction) {
        final int padding = this.readSwitchPadding(bytesProceed - 1);
        final int defaultOffset = this.readInt();
        final int nbPairs = this.readInt();
        final int[] keys = new int[nbPairs];
        final int[] offsets = new int[nbPairs];

        for (int i = 0; i < keys.length; i++) {
          keys[i] = this.readInt();
          offsets[i] = this.readInt();
        }

        bytesProceed += LookupswitchInstruction.getLength(padding, keys.length) - 1;

        instruction = ((LookupswitchMetaInstruction) metaInstruction).buildInstruction(padding, defaultOffset, nbPairs, keys, offsets);
      }

      instructions.add(instruction);
    }

    return instructions;
  }

  private int readSwitchPadding(int bytesProceed) {
    final int padding = 3 - bytesProceed % 4;

    if (padding == 1) {
      this.readByte();
    } else if (padding == 2) {
      this.readShort();
    } else if (padding == 3) {
      this.readByte();
      this.readShort();
    }
    return padding;
  }

  // -------------------------------------------------------------------------------------------------------------------
  // Read Constants
  // -------------------------------------------------------------------------------------------------------------------

  private PjbaLinkedList<ConstantPoolEntry> readConstants(int constantPoolCount) {
    final PjbaLinkedList<ConstantPoolEntry> list = new PjbaLinkedList<>();
    list.add(null);

    int realCount = 2; // For easy comparison with constantPoolCount
    for (int i = 1; i < constantPoolCount; i++, realCount++) {
      final int tag = this.readByte();
      switch (tag) {
        case 1: // UTF8
          list.add(this.readConstantUTF8());
          break;
        case 3: // Integer
          list.add(this.readConstantInteger());
          break;
        case 4: // Float
          list.add(this.readConstantFloat());
          break;
        case 5: // Long
          realCount++;
          list.add(this.readConstantLong());
          list.add(null);
          break;
        case 6: // Double
          realCount++;
          list.add(this.readConstantDouble());
          list.add(null);
          break;
        case 7: // Class
          list.add(this.readConstantClass());
          break;
        case 8: // String
          list.add(this.readConstantString());
          break;
        case 9: // FieldRef
          list.add(this.readConstantFieldRef());
          break;
        case 10: // MethodRef
          list.add(this.readConstantMethodRef());
          break;
        case 11: // InterfaceMethodRef
          list.add(this.readConstantInterfaceMethodRef());
          break;
        case 12: // NameAndType
          list.add(this.readConstantNameAndType());
          break;
        default:
          throw new RuntimeException("Unknown tag: " + tag + " in class " + this.classFile.getClassName());
      }

      if (realCount == constantPoolCount) {
        break;
      }
    }

    return list;
  }

  private ConstantPoolEntry readConstantUTF8() {
    final String value = this.readUTF();
    return new Constant.UTF8(value);
  }

  private ConstantPoolEntry readConstantInteger() {
    final int value = this.readInt();
    return new Constant.Integer(value);
  }

  private ConstantPoolEntry readConstantFloat() {
    final float value = this.readFloat();
    return new Constant.Float(value);
  }

  private ConstantPoolEntry readConstantLong() {
    final long value = this.readLong();
    return new Constant.Long(value);
  }

  private ConstantPoolEntry readConstantDouble() {
    final double value = this.readDouble();
    return new Constant.Double(value);
  }

  private ConstantPoolEntry readConstantClass() {
    final int value = this.readShort();
    return new Constant.Class(value);
  }

  private ConstantPoolEntry readConstantString() {
    final int value = this.readShort();
    return new Constant.String(value);
  }

  private ConstantPoolEntry readConstantFieldRef() {
    final int classIndex = this.readShort();
    final int nameAndTypeIndex = this.readShort();
    return new Constant.FieldRef(classIndex, nameAndTypeIndex);
  }

  private ConstantPoolEntry readConstantMethodRef() {
    final int classIndex = this.readShort();
    final int nameAndTypeIndex = this.readShort();
    return new Constant.MethodRef(classIndex, nameAndTypeIndex);
  }

  private ConstantPoolEntry readConstantInterfaceMethodRef() {
    final int classIndex = this.readShort();
    final int nameAndTypeIndex = this.readShort();
    return new Constant.InterfaceMethodRef(classIndex, nameAndTypeIndex);
  }

  private ConstantPoolEntry readConstantNameAndType() {
    final int nameIndex = this.readShort();
    final int descriptorIndex = this.readShort();
    return new Constant.NameAndType(nameIndex, descriptorIndex);
  }

  // -------------------------------------------------------------------------------------------------------------------
  // Unsigning methods
  // -------------------------------------------------------------------------------------------------------------------
  public int readUnsignedByte() {
    try {
      return this.dataInput.readUnsignedByte();
    } catch (IOException e) {
      throw new RuntimeException("Unable to read data input in class " + this.classFile.getClassName(), e);
    }
  }

  public int readUnsignedShort() {
    try {
      return this.dataInput.readUnsignedShort();
    } catch (IOException e) {
      throw new RuntimeException("Unable to read data input in class " + this.classFile.getClassName(), e);
    }
  }

  public long readUnsignedInt() {
    final int i = this.readInt();
    return BytecodeUtils.unsign(i);
  }

  // --------------------------------------------------------------------------------------------------------------------
  // DataInput methods wrappers
  // --------------------------------------------------------------------------------------------------------------------
  private byte readByte() {
    try {
      return this.dataInput.readByte();
    } catch (IOException e) {
      throw new RuntimeException("Unable to read data input in class " + this.classFile.getClassName(), e);
    }
  }

  private short readShort() {
    try {
      return this.dataInput.readShort();
    } catch (IOException e) {
      throw new RuntimeException("Unable to read data input in class " + this.classFile.getClassName(), e);
    }
  }

  private int readInt() {
    try {
      return this.dataInput.readInt();
    } catch (IOException e) {
      throw new RuntimeException("Unable to read data input in class " + this.classFile.getClassName(), e);
    }
  }

  private long readLong() {
    try {
      return this.dataInput.readLong();
    } catch (IOException e) {
      throw new RuntimeException("Unable to read data input in class " + this.classFile.getClassName(), e);
    }
  }

  private float readFloat() {
    try {
      return this.dataInput.readFloat();
    } catch (IOException e) {
      throw new RuntimeException("Unable to read data input in class " + this.classFile.getClassName(), e);
    }
  }

  private double readDouble() {
    try {
      return this.dataInput.readDouble();
    } catch (IOException e) {
      throw new RuntimeException("Unable to read data input in class " + this.classFile.getClassName(), e);
    }
  }

  private String readUTF() {
    try {
      return this.dataInput.readUTF();
    } catch (IOException e) {
      throw new RuntimeException("Unable to read data input in class " + this.classFile.getClassName(), e);
    }
  }
}
