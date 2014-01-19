package org.isk.jvmhardcore.pjba.structure;

public class Constant {
  public static abstract class ConstantPoolEntry {
    final private int tag;

    public ConstantPoolEntry(ConstantPoolTag tag) {
      this.tag = tag.getValue();
    }
  }

  public static class UTF8 extends ConstantPoolEntry {
    final private java.lang.String value;

    public UTF8(final java.lang.String value) {
      super(ConstantPoolTag.UTF8);
      this.value = value;
    }
  }

  public static class Integer extends ConstantPoolEntry {
    final private int integer;

    public Integer(int integer) {
      super(ConstantPoolTag.INTEGER);
      this.integer = integer;
    }
  }

  public static class Float extends ConstantPoolEntry {
    final private float floatValue;

    public Float(float floatValue) {
      super(ConstantPoolTag.FLOAT);
      this.floatValue = floatValue;
    }
  }

  public static class Long extends ConstantPoolEntry {
    final private long longValue;

    public Long(long longValue) {
      super(ConstantPoolTag.LONG);
      this.longValue = longValue;
    }
  }

  public static class Double extends ConstantPoolEntry {
    final private double doubleValue;

    public Double(double doubleValue) {
      super(ConstantPoolTag.DOUBLE);
      this.doubleValue = doubleValue;
    }
  }

  public static class Class extends ConstantPoolEntry {
    final private int nameIndex;

    public Class(final int nameIndex) {
      super(ConstantPoolTag.CLASS);
      this.nameIndex = nameIndex;
    }
  }

  public static class String extends ConstantPoolEntry {
    final private int utf8Index;

    public String(int stringIndex) {
      super(ConstantPoolTag.STRING);
      this.utf8Index = stringIndex;
    }
  }

  public static enum ConstantPoolTag {
    UTF8(1),
    INTEGER(3),
    FLOAT(4),
    LONG(5),
    DOUBLE(6),
    CLASS(7),
    STRING(8),
    FIELDREF(9),
    METHODREF(10),
    INTERFACE_METHODREF(11),
    NAME_AND_TYPE(12);

    private int value;

    private ConstantPoolTag(int value) {
      this.value = value;
    }

    public int getValue() {
      return this.value;
    }
  }
}
