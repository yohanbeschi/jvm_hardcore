package org.isk.jvmhardcore.pjba.dumper;

import org.isk.jvmhardcore.pjba.structure.ClassFile;
import org.isk.jvmhardcore.pjba.structure.Constant;
import org.isk.jvmhardcore.pjba.structure.Constant.ConstantPoolEntry;
import org.isk.jvmhardcore.pjba.structure.Field;
import org.isk.jvmhardcore.pjba.structure.Method;

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

    if ((accessFlags & ClassFile.MODIFIER_PUBLIC) == ClassFile.MODIFIER_PUBLIC) {
      sb.append("public ");
    }

    if ((accessFlags & ClassFile.MODIFIER_FINAL) == ClassFile.MODIFIER_FINAL) {
      sb.append("final ");
    }

    if ((accessFlags & ClassFile.MODIFIER_SUPER) == ClassFile.MODIFIER_SUPER) {
      sb.append("super ");
    }

    if ((accessFlags & ClassFile.MODIFIER_INTERFACE) == ClassFile.MODIFIER_INTERFACE) {
      sb.append("interface ");
    }

    if ((accessFlags & ClassFile.MODIFIER_ABSTRACT) == ClassFile.MODIFIER_ABSTRACT) {
      sb.append("abstract ");
    }

    return sb.toString();
  }

  public static Object getFieldModifiers(int accessFlags) {
    final StringBuilder sb = new StringBuilder();

    if ((accessFlags & Field.MODIFIER_PUBLIC) == Field.MODIFIER_PUBLIC) {
      sb.append("public ");
    }

    if ((accessFlags & Field.MODIFIER_PRIVATE) == Field.MODIFIER_PRIVATE) {
      sb.append("private ");
    }

    if ((accessFlags & Field.MODIFIER_PROTECTED) == Field.MODIFIER_PROTECTED) {
      sb.append("protected ");
    }

    if ((accessFlags & Field.MODIFIER_STATIC) == Field.MODIFIER_STATIC) {
      sb.append("static ");
    }

    if ((accessFlags & Field.MODIFIER_FINAL) == Field.MODIFIER_FINAL) {
      sb.append("final ");
    }

    if ((accessFlags & Field.MODIFIER_VOLATILE) == Field.MODIFIER_VOLATILE) {
      sb.append("volatile ");
    }

    if ((accessFlags & Field.MODIFIER_TRANSIENT) == Field.MODIFIER_TRANSIENT) {
      sb.append("transient ");
    }

    return sb.toString();
  }

  public static String getMethodModifiers(int accessFlags) {
    final StringBuilder sb = new StringBuilder();

    if ((accessFlags & Method.MODIFIER_PUBLIC) == Method.MODIFIER_PUBLIC) {
      sb.append("public ");
    }

    if ((accessFlags & Method.MODIFIER_PRIVATE) == Method.MODIFIER_PRIVATE) {
      sb.append("private ");
    }

    if ((accessFlags & Method.MODIFIER_PROTECTED) == Method.MODIFIER_PROTECTED) {
      sb.append("protected ");
    }

    if ((accessFlags & Method.MODIFIER_STATIC) == Method.MODIFIER_STATIC) {
      sb.append("static ");
    }

    if ((accessFlags & Method.MODIFIER_FINAL) == Method.MODIFIER_FINAL) {
      sb.append("final ");
    }

    if ((accessFlags & Method.MODIFIER_SYNCHRONIZED) == Method.MODIFIER_SYNCHRONIZED) {
      sb.append("synchronized ");
    }

    if ((accessFlags & Method.MODIFIER_NATIVE) == Method.MODIFIER_NATIVE) {
      sb.append("native ");
    }

    if ((accessFlags & Method.MODIFIER_ABSTRACT) == Method.MODIFIER_ABSTRACT) {
      sb.append("abstract ");
    }

    if ((accessFlags & Method.MODIFIER_STRICTFP) == Method.MODIFIER_STRICTFP) {
      sb.append("strictfp ");
    }

    return sb.toString();
  }

  public static String getPrintableConstant(int index, ClassFile classFile) {
    final ConstantPoolEntry constant = classFile.getConstant(index);

    switch (constant.tag) {
      case 3: // Integer
        final Constant.Integer cInteger = (Constant.Integer) constant;
        return String.valueOf(cInteger.integer);
      case 4: // Float
        final Constant.Float cFloat = (Constant.Float) constant;
        return String.valueOf(cFloat.floatValue) + "f";
      case 5: // Long
        final Constant.Long cLong = (Constant.Long) constant;
        return String.valueOf(cLong.longValue) + "l";
      case 6: // Double
        final Constant.Double cDouble = (Constant.Double) constant;
        return String.valueOf(cDouble.doubleValue) + "d";
      case 7: // Class
        final Constant.Class cClass = (Constant.Class) constant;
        final Constant.UTF8 cClassName = (Constant.UTF8) classFile.getConstant(cClass.nameIndex);
        return cClassName.value;
      case 8: // String
        final Constant.String cString = (Constant.String) constant;
        final String value = ((Constant.UTF8) classFile.getConstant(cString.utf8Index)).value;
        return "\"" + value.replace("\\", "\\\\").replace("\"", "\\\"") + "\"";
      case 9: // Field
      case 10: // Method
      case 11: // InterfaceMethod
        final Constant.Ref cRef = (Constant.Ref) constant;
        final Constant.Class cmClass = (Constant.Class) classFile.getConstant(cRef.classIndex);
        final Constant.UTF8 cmClassName = (Constant.UTF8) classFile.getConstant(cmClass.nameIndex);
        final Constant.NameAndType nameAndType = (Constant.NameAndType) classFile.getConstant(cRef.nameAndTypeIndex);
        final Constant.UTF8 cName = (Constant.UTF8) classFile.getConstant(nameAndType.nameIndex);
        final Constant.UTF8 cDescriptor = (Constant.UTF8) classFile.getConstant(nameAndType.descriptorIndex);

        return cmClassName.value + " " + cName.value + " " + cDescriptor.value;
      default:
        return null;
    }
  }

  public static String getRefDescriptor(int index, ClassFile classFile) {
    final Constant.Ref ref = (Constant.Ref) classFile.getConstant(index);
    final Constant.NameAndType nameAndType = (Constant.NameAndType) classFile.getConstant(ref.nameAndTypeIndex);
    final Constant.UTF8 descriptor = (Constant.UTF8) classFile.getConstant(nameAndType.descriptorIndex);
    return descriptor.value;
  }
}
