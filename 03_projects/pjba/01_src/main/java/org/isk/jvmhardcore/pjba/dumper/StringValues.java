package org.isk.jvmhardcore.pjba.dumper;

public class StringValues {
  private StringValues() {
  }

  public static String constantTagName(int tag) {
    switch (tag) {
      case 1:
        return "UTF8";
      case 3:
        return "Integer";
      case 4:
        return "Float";
      case 5:
        return "Long";
      case 6:
        return "Double";
      case 7:
        return "Class";
      case 8:
        return "FieldRef";
      case 9:
        return "MethodRef";
      case 10:
        return "MethodRef";
      case 11:
        return "InterfaceMethodRef";
      case 12:
        return "NameAndType";
    }
    return null;
  }

  public static String getClassModifiers(int accessFlags) {
    final StringBuilder sb = new StringBuilder();

    if ((accessFlags & 0x0001) == 0x0001) {
      sb.append("public ");
    }

    if ((accessFlags & 0x0010) == 0x0010) {
      sb.append("final ");
    }

    if ((accessFlags & 0x0020) == 0x0020) {
      sb.append("super ");
    }

    if ((accessFlags & 0x0200) == 0x0200) {
      sb.append("interface ");
    }

    if ((accessFlags & 0x0400) == 0x0400) {
      sb.append("abstract ");
    }

    return sb.toString();
  }

  public static String getMethodModifiers(int accessFlags) {
    final StringBuilder sb = new StringBuilder();

    if ((accessFlags & 0x0001) == 0x0001) {
      sb.append("public ");
    }

    if ((accessFlags & 0x0002) == 0x0002) {
      sb.append("private ");
    }

    if ((accessFlags & 0x0004) == 0x0004) {
      sb.append("protected ");
    }

    if ((accessFlags & 0x0008) == 0x0008) {
      sb.append("static ");
    }

    if ((accessFlags & 0x0010) == 0x0010) {
      sb.append("final ");
    }

    if ((accessFlags & 0x0020) == 0x0020) {
      sb.append("synchronized ");
    }

    if ((accessFlags & 0x0100) == 0x0100) {
      sb.append("native ");
    }

    if ((accessFlags & 0x0400) == 0x0400) {
      sb.append("abstract ");
    }

    if ((accessFlags & 0x0800) == 0x0800) {
      sb.append("strictfp ");
    }

    return sb.toString();
  }
}
