package org.isk.jvmhardcore.pjba.util;

import java.io.UnsupportedEncodingException;

public class DescriptorCounter {
  final private static String ENCODING = "UTF-8";

  private DescriptorCounter() {
  }

  public static int fieldDescriptorUnits(final String fieldDescriptor) {
    final byte[] descriptor = getDescriptorAsByteArray(fieldDescriptor);
    return getType(descriptor, 0).size;
  }

  public static int methodsDescriptorParamsUnits(final String methodDescriptor) {
    return getMethodData(methodDescriptor).paramsSize;
  }

  public static int methodsDescriptorSignatureUnits(final String methodDescriptor) {
    final MethodData methodData = getMethodData(methodDescriptor);
    return methodData.returnSize - methodData.paramsSize;
  }

  private static MethodData getMethodData(final String methodDescriptor) {
    final byte[] descriptor = getDescriptorAsByteArray(methodDescriptor);

    int paramsSize = 0;

    Type type = new Type(0, 1); // 1: Step over the first element: Left parenthesis

    while (!type.isLastParam) {
      type = getType(descriptor, type.nextIndexToRead);
      paramsSize += type.size;
    }

    final Type returnType = getType(descriptor, type.nextIndexToRead);

    return new MethodData(paramsSize, returnType.size);
  }

  public static Type getType(final byte[] descriptor, int nextIndexToRead) {
    int size = 0;
    boolean isLastParam = false;
    byte character = descriptor[nextIndexToRead++];

    switch (character) {
      case Ascii.RIGHT_PARENTHESIS:
        isLastParam = true;
        break;
      case Ascii.UPPERCASE_B: // byte
      case Ascii.UPPERCASE_C: // char
      case Ascii.UPPERCASE_F: // float
      case Ascii.UPPERCASE_I: // int
      case Ascii.UPPERCASE_S: // short
      case Ascii.UPPERCASE_Z: // boolean
        size = 1;
        break;
      case Ascii.UPPERCASE_J: // long
      case Ascii.UPPERCASE_D: // double
        size = 2;
        break;
      case Ascii.LEFT_BRACKET: // array
        nextIndexToRead = getType(descriptor, nextIndexToRead).nextIndexToRead;
        size = 1;
        break;
      case Ascii.UPPERCASE_L: // reference
        while (nextIndexToRead < descriptor.length && (character = descriptor[nextIndexToRead++]) != Ascii.SEMICOLON)
          ;
        size = 1;
        break;
    }

    return new Type(size, nextIndexToRead, isLastParam);
  }

  public static byte[] getDescriptorAsByteArray(final String descriptor) {
    try {
      return descriptor.getBytes(ENCODING);
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException("Should never happen", e);
    }
  }

  public static class Type {
    final int size;
    final int nextIndexToRead;
    final boolean isLastParam;

    public Type(int size, int nextIndexToRead, boolean isLastParam) {
      super();
      this.size = size;
      this.isLastParam = isLastParam;
      this.nextIndexToRead = nextIndexToRead;
    }

    public Type(int size, int nexteIndexToRead) {
      this(size, nexteIndexToRead, false);
    }
  }

  public static class MethodData {
    final int paramsSize;
    final int returnSize;

    public MethodData(int paramsSize, int returnSize) {
      super();
      this.paramsSize = paramsSize;
      this.returnSize = returnSize;
    }
  }
}
