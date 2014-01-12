package org.isk.jvmhardcore.encoding;

import java.io.UnsupportedEncodingException;

import junit.framework.Assert;

import org.junit.Test;

public class StringTest {

  // Declaring two Strings, s1 using the sugar syntax assignation
  // and s2 using the constructor taking one parameter, the String
  @Test
  public void stringEquality0() {
    final String s1 = "abcdefghij";
    final String s2 = new String("abcdefghij");

    Assert.assertEquals(s1, s2);
  }

  // Declaring two Strings, s1 using the sugar syntax assignation
  // and s2 using the constructor taking two parameters, the String and a
  // charset name (UTF-8)
  @Test
  public void stringEquality1() throws UnsupportedEncodingException {
    final String s1 = "abcdefghij";
    final String s2 = new String("abcdefghij".getBytes(), "UTF-8");

    Assert.assertEquals(s1, s2);
  }

  // Declaring two Strings, s1 using the sugar syntax assignation
  // and s2 using the constructor taking two parameters, the String and a charset name (UTF-16)
  @Test
  public void stringEquality2() throws UnsupportedEncodingException {
    final String s1 = "abcdefghij";
    final String s2 = new String("abcdefghij".getBytes(), "UTF-16");

    Assert.assertFalse(s1.equals(s2));
  }

  // Declaring two Strings (see. stringEquality2()) and displaying their characters.
  @Test
  public void stringEquality3() throws UnsupportedEncodingException {
    final String s1 = "abcdefghij";
    final String s2 = new String("abcdefghij".getBytes(), "UTF-16");

    System.out.println("---- s1 ----");

    for (char c : s1.toCharArray()) {
      System.out.print(c + " ");
    }

    System.out.println("\n---- s2 ----");

    for (char c : s2.toCharArray()) {
      System.out.print(c + " ");
    }

    System.out.println("");
  }

  // Declaring two Strings (see. stringEquality2()) and displaying their code points.
  @Test
  public void stringEquality4() throws UnsupportedEncodingException {
    final String s1 = "abcdefghij";
    final String s2 = new String("abcdefghij".getBytes(), "UTF-16");

    System.out.println("---- s1 ----");

    for (int i = 0; i < s1.length(); i++) {
      final int codePoint = s1.codePointAt(i);
      System.out.print(codePoint + " ");
    }

    System.out.println("\n---- s2 ----");

    for (int i = 0; i < s2.length(); i++) {
      final int codePoint = s2.codePointAt(i);
      System.out.print(codePoint + " ");
    }

    System.out.println("");
  }

  // Declaring two Strings (see. stringEquality2()) and displaying them as bytes in hexadecimal form.
  @Test
  public void stringEquality5() throws UnsupportedEncodingException {
    final String s1 = "abcdefghij";
    final String s2 = new String("abcdefghij".getBytes(), "UTF-16");

    System.out.println("---- s1 ----");

    for (byte b : s1.getBytes()) {
      System.out.print(this.byteToHex(b) + " ");
    }

    System.out.println("\n---- s2 ----");

    for (byte b : s2.getBytes()) {
      System.out.print(b + " ");
      System.out.print(this.byteToHex(b) + " ");
    }

    System.out.println("");
  }

  // Declaring two Strings, s1 using the sugar syntax assignation
  // and s2 using the constructor taking two parameters, the String and a charset name (UTF-16)
  @Test
  public void stringEquality6() throws UnsupportedEncodingException {
    final String s1 = "abcdefghij";
    final String s2 = new String("abcdefghij".getBytes("UTF-16"), "UTF-16");

    Assert.assertTrue(s1.equals(s2));
  }

  // Same as stringEquality5(), but using the method getBytes() with an encoding.
  @Test
  public void stringEquality7() throws UnsupportedEncodingException {
    final String s1 = "abcdefghij";
    final String s2 = new String("abcdefghij".getBytes("UTF-8"), "UTF-16");

    System.out.println("---- s1 ----");

    for (byte b : s1.getBytes("UTF-8")) {
      System.out.print(this.byteToHex(b) + " ");
    }

    System.out.println("\n---- s2 ----");

    for (byte b : s2.getBytes("UTF-16")) {
      System.out.print(this.byteToHex(b) + " ");
    }

    System.out.println("");
  }

  final static byte[] HEX_ARRAY = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

  String byteToHex(byte number) {
    final int unsignedByte = number & 0xff;
    final StringBuilder sb = new StringBuilder();
    sb.append((char) HEX_ARRAY[unsignedByte >>> 4]);
    sb.append((char) HEX_ARRAY[unsignedByte & 0x0F]);
    return sb.toString();
  }
}
