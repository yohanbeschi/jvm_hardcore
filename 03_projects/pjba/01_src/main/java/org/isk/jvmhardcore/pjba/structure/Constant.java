package org.isk.jvmhardcore.pjba.structure;

import java.io.DataOutput;
import java.io.IOException;

import org.isk.jvmhardcore.pjba.util.BytecodeEnabled;

public class Constant {

  // --------------------------------------------------------------------------------------------------------------------
  // ConstantPoolEntry
  // --------------------------------------------------------------------------------------------------------------------
  public static abstract class ConstantPoolEntry implements BytecodeEnabled {
    final int tag;

    public ConstantPoolEntry(final ConstantPoolTag tag) {
      this.tag = tag.getValue();
    }

    public void toBytecode(final DataOutput dataOutput) throws IOException {
      dataOutput.writeByte(this.tag);
      this.constantToBytecode(dataOutput);
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

    public abstract void constantToBytecode(final DataOutput dataOutput) throws IOException;
  }

  // --------------------------------------------------------------------------------------------------------------------
  // ConstantUTF8
  // --------------------------------------------------------------------------------------------------------------------

  public static class UTF8 extends ConstantPoolEntry {
    final java.lang.String value;

    public UTF8(final java.lang.String value) {
      super(ConstantPoolTag.UTF8);
      this.value = value;
    }

    @Override
    public void constantToBytecode(DataOutput dataOutput) throws IOException {
      dataOutput.writeUTF(this.value); // length included
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
    final int integer;

    public Integer(int integer) {
      super(ConstantPoolTag.INTEGER);
      this.integer = integer;
    }

    @Override
    public void constantToBytecode(DataOutput dataOutput) throws IOException {
      dataOutput.writeInt(this.integer);
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
    final float floatValue;

    public Float(float floatValue) {
      super(ConstantPoolTag.FLOAT);
      this.floatValue = floatValue;
    }

    @Override
    public void constantToBytecode(DataOutput dataOutput) throws IOException {
      dataOutput.writeFloat(this.floatValue);
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
    final long longValue;

    public Long(long longValue) {
      super(ConstantPoolTag.LONG);
      this.longValue = longValue;
    }

    @Override
    public void constantToBytecode(DataOutput dataOutput) throws IOException {
      dataOutput.writeLong(this.longValue);
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
    final double doubleValue;

    public Double(double doubleValue) {
      super(ConstantPoolTag.DOUBLE);
      this.doubleValue = doubleValue;
    }

    @Override
    public void constantToBytecode(DataOutput dataOutput) throws IOException {
      dataOutput.writeDouble(this.doubleValue);
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
    final int nameIndex;

    public Class(final int nameIndex) {
      super(ConstantPoolTag.CLASS);
      this.nameIndex = nameIndex;
    }

    @Override
    public void constantToBytecode(DataOutput dataOutput) throws IOException {
      dataOutput.writeShort(this.nameIndex);
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
    final private int utf8Index;

    public String(int stringIndex) {
      super(ConstantPoolTag.STRING);
      this.utf8Index = stringIndex;
    }

    @Override
    public void constantToBytecode(DataOutput dataOutput) throws IOException {
      dataOutput.writeShort(this.utf8Index);
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
    UTF8(1), INTEGER(3), FLOAT(4), LONG(5), DOUBLE(6), CLASS(7), STRING(8), FIELDREF(9), METHODREF(10), INTERFACE_METHODREF(
        11), NAME_AND_TYPE(12);

    private int value;

    private ConstantPoolTag(int value) {
      this.value = value;
    }

    public int getValue() {
      return this.value;
    }
  }
}
