package org.isk.jvmhardcore.pjba.structure;

import java.io.DataOutput;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.isk.jvmhardcore.pjba.structure.attribute.constraint.MethodAttribute;
import org.isk.jvmhardcore.pjba.util.Ascii;
import org.isk.jvmhardcore.pjba.util.BytecodeEnabled;
import org.isk.jvmhardcore.pjba.util.PjbaLinkedList;

public class Method implements BytecodeEnabled {
  private static final String ENCODING = "UTF-8";

  final private int accessFlags = 0x0001 | 0x0008; // public static

  private int nameIndex;
  private int descriptorIndex;

  private PjbaLinkedList<MethodAttribute> attributes;

  public Method() {
    super();

    this.attributes = new PjbaLinkedList<>();
  }

  public void setNameIndex(int utf8NameIndex) {
    this.nameIndex = utf8NameIndex;
  }

  public void setDescriptorIndex(int utf8DescriptorIndex) {
    this.descriptorIndex = utf8DescriptorIndex;
  }

  public void addAttibute(final MethodAttribute attribute) {
    this.attributes.add(attribute);
  }

  public int countParameters(final String methodDescriptor) {
    try {
      final byte[] descriptor = methodDescriptor.getBytes(ENCODING);

      int index = 1; // Step over the first element: Left parenthesis
      int locals = 0;
      int previousLocals = locals;

      for (;;) {
        final long pack = countOneParameter(descriptor, index, locals);
        locals = (int) (pack & 0xffff);
        index = (int) (pack >>> 32);

        // We encountered a right bracket or a void
        if (locals == previousLocals) {
          break;
        }

        previousLocals = locals;
      }

      return locals;
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException("Should never happen", e);
    }
  }

  private long countOneParameter(byte[] descriptor, int index, int locals) {
    byte character = descriptor[index++];

    switch (character) {
      case Ascii.UPPERCASE_B: // byte
      case Ascii.UPPERCASE_C: // char
      case Ascii.UPPERCASE_F: // float
      case Ascii.UPPERCASE_I: // int
      case Ascii.UPPERCASE_S: // short
      case Ascii.UPPERCASE_Z: // boolean
        locals++;
        break;
      case Ascii.UPPERCASE_J: // long
      case Ascii.UPPERCASE_D: // double
        locals += 2;
        break;
      case Ascii.LEFT_BRACKET: // array
        locals++;
        final long pack = this.countOneParameter(descriptor, index, 0); // ignore locals
        index = (int) (pack >>> 32);
        break;
      case Ascii.UPPERCASE_L: // reference
        locals++;
        while (index < descriptor.length && (character = descriptor[index++]) != Ascii.SEMICOLON)
          ;
        break;
    }

    // Locals maximum value = 65,535
    // Arrays maximum size in Java = 2,147,483,647
    return (((long) index) << 32) | locals;
  }

  @Override
  public void toBytecode(DataOutput dataOutput) throws IOException {
    dataOutput.writeShort(this.accessFlags);
    dataOutput.writeShort(this.nameIndex);
    dataOutput.writeShort(this.descriptorIndex);
    dataOutput.writeShort(this.attributes.size());
    this.attributes.toBytecode(dataOutput);
  }
}
