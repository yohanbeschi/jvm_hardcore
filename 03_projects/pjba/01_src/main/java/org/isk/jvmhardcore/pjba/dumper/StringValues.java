package org.isk.jvmhardcore.pjba.dumper;

import org.isk.jvmhardcore.pjba.structure.ClassFile;
import org.isk.jvmhardcore.pjba.structure.Constant;
import org.isk.jvmhardcore.pjba.structure.Constant.ConstantPoolEntry;

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

  public static String getPrintableConstant(int index, ClassFile classFile) {
    final ConstantPoolEntry constant = classFile.getConstant(index);

    switch (constant.tag) {
      case 3:
        final Constant.Integer cInteger = (Constant.Integer) constant;
        return String.valueOf(cInteger.integer);
      case 4:
        final Constant.Float cFloat = (Constant.Float) constant;
        return String.valueOf(cFloat.floatValue);
      case 5:
        final Constant.Long cLong = (Constant.Long) constant;
        return String.valueOf(cLong.longValue);
      case 6:
        final Constant.Double cDouble = (Constant.Double) constant;
        return String.valueOf(cDouble.doubleValue);
      case 8:
        final Constant.String cString = (Constant.String) constant;
        final String value = ((Constant.UTF8) classFile.getConstant(cString.utf8Index)).value;
        return "\"" + value.replace("\\", "\\\\").replace("\"", "\\\"") + "\"";
      default:
        return null;
    }
  }
}
