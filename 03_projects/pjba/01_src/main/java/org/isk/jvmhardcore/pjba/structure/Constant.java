package org.isk.jvmhardcore.pjba.structure;

import org.isk.jvmhardcore.pjba.visitor.Visitable;
import org.isk.jvmhardcore.pjba.visitor.Visitor;

public class Constant {

  // --------------------------------------------------------------------------------------------------------------------
  // ConstantPoolEntry
  // --------------------------------------------------------------------------------------------------------------------
  public static abstract class ConstantPoolEntry implements Visitable {
    final public int tag;

    public ConstantPoolEntry(final ConstantPoolTag tag) {
      this.tag = tag.getValue();
    }

    public void accept(final Visitor visitor) {
      visitor.visitConstantTag(this.tag);
      this.constantAccept(visitor);
    }

    @Override
    public int hashCode() {
      return 31 + tag;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }

      if (obj == null) {
        return false;
      }

      if (super.getClass() != obj.getClass()) {
        return false;
      }

      final ConstantPoolEntry other = (ConstantPoolEntry) obj;
      if (this.tag != other.tag) {
        return false;
      }

      return true;
    }

    public abstract void constantAccept(final Visitor visitor);
  }

  // --------------------------------------------------------------------------------------------------------------------
  // ConstantUTF8
  // --------------------------------------------------------------------------------------------------------------------

  public static class UTF8 extends ConstantPoolEntry {
    final public java.lang.String value;

    public UTF8(final java.lang.String value) {
      super(ConstantPoolTag.UTF8);
      this.value = value;
    }

    @Override
    public void constantAccept(final Visitor visitor) {
      visitor.visitConstantUTF8(this.value);
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = super.hashCode();
      result = prime * result + ((this.value == null) ? 0 : this.value.hashCode());
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }

      if (!super.equals(obj)) {
        return false;
      }

      if (super.getClass() != obj.getClass()) {
        return false;
      }

      final UTF8 other = (UTF8) obj;
      if (this.value == null) {
        if (other.value != null) {
          return false;
        }
      } else if (!this.value.equals(other.value)) {
        return false;
      }

      return true;
    }
  }

  // --------------------------------------------------------------------------------------------------------------------
  // ConstantInteger
  // --------------------------------------------------------------------------------------------------------------------

  public static class Integer extends ConstantPoolEntry {
    final public int integer;

    public Integer(int integer) {
      super(ConstantPoolTag.INTEGER);
      this.integer = integer;
    }

    @Override
    public void constantAccept(final Visitor visitor) {
      visitor.visitConstantInteger(this.integer);
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = super.hashCode();
      result = prime * result + this.integer;
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }

      if (!super.equals(obj)) {
        return false;
      }

      if (super.getClass() != obj.getClass()) {
        return false;
      }

      final Integer other = (Integer) obj;
      if (this.integer != other.integer) {
        return false;
      }

      return true;
    }
  }

  // --------------------------------------------------------------------------------------------------------------------
  // ConstantFloat
  // --------------------------------------------------------------------------------------------------------------------

  public static class Float extends ConstantPoolEntry {
    final public float floatValue;

    public Float(float floatValue) {
      super(ConstantPoolTag.FLOAT);
      this.floatValue = floatValue;
    }

    @Override
    public void constantAccept(final Visitor visitor) {
      visitor.visitConstantFloat(this.floatValue);
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = super.hashCode();
      result = prime * result + java.lang.Float.floatToIntBits(this.floatValue);
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }

      if (!super.equals(obj)) {
        return false;
      }

      if (super.getClass() != obj.getClass()) {
        return false;
      }

      final Float other = (Float) obj;
      if (java.lang.Float.floatToIntBits(this.floatValue) != java.lang.Float.floatToIntBits(other.floatValue)) {
        return false;
      }

      return true;
    }
  }

  // --------------------------------------------------------------------------------------------------------------------
  // ConstantLong
  // --------------------------------------------------------------------------------------------------------------------

  public static class Long extends ConstantPoolEntry {
    final public long longValue;

    public Long(long longValue) {
      super(ConstantPoolTag.LONG);
      this.longValue = longValue;
    }

    @Override
    public void constantAccept(final Visitor visitor) {
      visitor.visitConstantLong(this.longValue);
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = super.hashCode();
      result = prime * result + (int) (this.longValue ^ (this.longValue >>> 32));
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }

      if (!super.equals(obj)) {
        return false;
      }

      if (super.getClass() != obj.getClass()) {
        return false;
      }

      final Long other = (Long) obj;
      if (this.longValue != other.longValue) {
        return false;
      }

      return true;
    }
  }

  // --------------------------------------------------------------------------------------------------------------------
  // ConstantDouble
  // --------------------------------------------------------------------------------------------------------------------

  public static class Double extends ConstantPoolEntry {
    final public double doubleValue;

    public Double(double doubleValue) {
      super(ConstantPoolTag.DOUBLE);
      this.doubleValue = doubleValue;
    }

    @Override
    public void constantAccept(final Visitor visitor) {
      visitor.visitConstantDouble(this.doubleValue);
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = super.hashCode();
      long temp;
      temp = java.lang.Double.doubleToLongBits(this.doubleValue);
      result = prime * result + (int) (temp ^ (temp >>> 32));
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }

      if (!super.equals(obj)) {
        return false;
      }

      if (super.getClass() != obj.getClass()) {
        return false;
      }

      final Double other = (Double) obj;
      if (java.lang.Double.doubleToLongBits(this.doubleValue) != java.lang.Double.doubleToLongBits(other.doubleValue)) {
        return false;
      }

      return true;
    }
  }

  // --------------------------------------------------------------------------------------------------------------------
  // ConstantClass
  // --------------------------------------------------------------------------------------------------------------------

  public static class Class extends ConstantPoolEntry {
    final public int nameIndex;

    public Class(final int nameIndex) {
      super(ConstantPoolTag.CLASS);
      this.nameIndex = nameIndex;
    }

    @Override
    public void constantAccept(final Visitor visitor) {
      visitor.visitConstantClass(this.nameIndex);
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = super.hashCode();
      result = prime * result + this.nameIndex;
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }

      if (!super.equals(obj)) {
        return false;
      }

      if (super.getClass() != obj.getClass()) {
        return false;
      }

      final Class other = (Class) obj;
      if (this.nameIndex != other.nameIndex) {
        return false;
      }

      return true;
    }
  }

  // --------------------------------------------------------------------------------------------------------------------
  // ConstantString
  // --------------------------------------------------------------------------------------------------------------------

  public static class String extends ConstantPoolEntry {
    final public int utf8Index;

    public String(int stringIndex) {
      super(ConstantPoolTag.STRING);
      this.utf8Index = stringIndex;
    }

    @Override
    public void constantAccept(final Visitor visitor) {
      visitor.visitConstantString(this.utf8Index);
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = super.hashCode();
      result = prime * result + this.utf8Index;
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }

      if (!super.equals(obj)) {
        return false;
      }

      if (super.getClass() != obj.getClass()) {
        return false;
      }

      final String other = (String) obj;
      if (this.utf8Index != other.utf8Index) {
        return false;
      }

      return true;
    }
  }

  // --------------------------------------------------------------------------------------------------------------------
  // Tags
  // --------------------------------------------------------------------------------------------------------------------

  public static enum ConstantPoolTag {
    UNDEFINED(0),
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
