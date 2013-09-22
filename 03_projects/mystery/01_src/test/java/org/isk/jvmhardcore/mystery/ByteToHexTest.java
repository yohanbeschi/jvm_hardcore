package org.isk.jvmhardcore.mystery;

import org.junit.Test;
import org.junit.Assert;
import org.junit.internal.ArrayComparisonFailure;

public class ByteToHexTest {

  @Test
  public void byteToHex0Test() {
    // 94(10) = 5E(16) = 0101 1110(2)
    final byte number = 94;
    final byte[] hex = byteToHex0(number);

    Assert.assertArrayEquals(new byte[] { 5, 14 }, hex);
  }

  public byte[] byteToHex0(byte number) {
    final byte[] bytes = new byte[2];
    bytes[0] = (byte) (number >> 4);
    bytes[1] = (byte) (number & 0x0f);
    return bytes;
  }

  @Test
  public void byteToHex1Test() {
    // -23(10) = E9(16) = 1110 1001 = 233(10) unsigned byte
    final byte number = -23;
    final byte[] hex = byteToHex0(number);

    try {
      
      Assert.assertArrayEquals(new byte[] { 14, 9 }, hex);
      // The method byteToHex0() doesn't work with negative values
      Assert.fail();
    } catch (ArrayComparisonFailure e) {
      // Arrays are not equals
    }
  }

  @Test
  public void byteToHex2Test() {
    // -23(10) = E9(16) = 1110 1001 = 233(10)
    final byte number = -23;
    final byte[] hex = byteToHex1(number);

    Assert.assertArrayEquals(new byte[] { 14, 9 }, hex);
  }

  byte[] byteToHex1(byte number) {
    // In Java all number types been signed
    // We need to unsigned the value to have the same 
    // behavior for positive and negative values
    final int unsignedByte = number & 0xff;
    final byte[] bytes = new byte[2];
    bytes[0] = (byte) (unsignedByte >>> 4);
    bytes[1] = (byte) (unsignedByte & 0x0f);
    return bytes;
  }

  @Test
  public void bitShifting() {
    Assert.assertTrue(-23 >> 4 != -23 >>> 4);
  }
}