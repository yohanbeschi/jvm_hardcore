package org.isk.jvmhardcore.pjba;

import java.io.DataInput;
import java.io.EOFException;
import java.io.IOException;

import org.isk.jvmhardcore.pjba.instruction.Instructions;
import org.isk.jvmhardcore.pjba.structure.ClassFile;
import org.isk.jvmhardcore.pjba.structure.Constant;
import org.isk.jvmhardcore.pjba.structure.Constant.ConstantPoolEntry;
import org.isk.jvmhardcore.pjba.structure.Instruction;
import org.isk.jvmhardcore.pjba.structure.Method;
import org.isk.jvmhardcore.pjba.structure.attribute.Code;
import org.isk.jvmhardcore.pjba.structure.attribute.constraint.MethodAttribute;
import org.isk.jvmhardcore.pjba.util.PjbaLinkedList;

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
    if (interfacesCount != 0) {
      throw new RuntimeException("Unable to read interfaces yet in class " + this.classFile.getClassName());
    }

    final int fieldsCount = this.readUnsignedShort();
    if (fieldsCount != 0) {
      throw new RuntimeException("Unable to read fields yet");
    }

    final int methodsCount = this.readUnsignedShort();
    final PjbaLinkedList<Method> methods = this.readMethods(methodsCount);
    this.classFile.setMethods(methods);

    final int attributesCount = this.readUnsignedShort();
    if (attributesCount != 0) {
      throw new RuntimeException("Unable to read class attributes yet in class " + this.classFile.getClassName());
    }
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

    final int accessFlags = this.readShort();
    method.setAccessFlags(accessFlags);

    final int nameIndex = this.readUnsignedShort();
    method.setNameIndex(nameIndex);

    final int descriptorIndex = this.readUnsignedShort();
    method.setDescriptorIndex(descriptorIndex);

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

      final Instruction instruction = Instructions.getInstruction(opcode);
      instructions.add(instruction);
    }

    return instructions;
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
    return i & 0xFFFFFFFF;
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
