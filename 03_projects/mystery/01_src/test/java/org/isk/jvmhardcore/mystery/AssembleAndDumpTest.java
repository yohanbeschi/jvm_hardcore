package org.isk.jvmhardcore.mystery;

import org.junit.Assert;
import org.junit.Test;

public class AssembleAndDumpTest {
  @Test
  public void bytesToHex0() {
    // 94(10) = 5E(16) = 0101 1110(2)
    final byte number = 94;
    final byte[] hex = AssembleAndDump.bytesToHex(new byte[]{number});

    // 5E in ASCII
    Assert.assertArrayEquals(new byte[] { 53, 69 }, hex);
  }
  
  @Test
  public void bytesToHex1() {
    // -23(10) = E9(16) = 1110 1001 = 233(10) unsigned byte
    final byte number = -23;
    final byte[] hex = AssembleAndDump.bytesToHex(new byte[]{number});

    // E9 in ASCII
    Assert.assertArrayEquals(new byte[] { 69, 57 }, hex);
  }

  @Test
  public void hexToBytes0() {
    final String hex = "5E";
    final byte[] number = AssembleAndDump.hexToBytes(hex);
    
    Assert.assertArrayEquals(new byte[] { 94 }, number);
  }
  
  @Test
  public void hexToBytes1() {
    final String hex = "E9";
    final byte[] number = AssembleAndDump.hexToBytes(hex);
    
    Assert.assertArrayEquals(new byte[] { -23 }, number);
  }

  @Test
  public void getFileNameWithoutExtension() {
    final String filename = AssembleAndDump.getFileNameWithoutExtension("my/directory/file.class");
    
    Assert.assertEquals("my/directory/file", filename);
  }
}