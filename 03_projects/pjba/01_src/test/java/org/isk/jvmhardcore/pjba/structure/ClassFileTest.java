package org.isk.jvmhardcore.pjba.structure;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClassFileTest {

  final private static int OFFSET = 4; // Constructor sets thisClass & superClass
  
  private ClassFile classFile;

  @Before
  public void init() {
    this.classFile = new ClassFile("");
  }
  
  @Test
  public void addConstantUTF80() {
    final int index = this.classFile.addConstantUTF8("value");
    
    Assert.assertEquals(index, 1 + OFFSET);
  }
  
  @Test
  public void addConstantUTF81() {
    int index = this.classFile.addConstantUTF8("value");
    index = this.classFile.addConstantUTF8("value2");
    
    Assert.assertEquals(index, 2 + OFFSET);
  }
  
  @Test
  public void addConstantUTF82() {
    int index = this.classFile.addConstantUTF8("value");
    int newIndex = this.classFile.addConstantUTF8("value");
    
    Assert.assertEquals(index, newIndex);
  }
  
  @Test
  public void addConstantClass0() {
    final int index = this.classFile.addConstantClass(25);
    
    Assert.assertEquals(index, 1 + OFFSET);
  }
  
  @Test
  public void addConstantClass1() {
    int index = this.classFile.addConstantClass(25);
    index = this.classFile.addConstantClass(2);
    
    Assert.assertEquals(index, 2 + OFFSET);
  }
  
  @Test
  public void addConstantClass2() {
    final int index = this.classFile.addConstantClass(25);
    final int newIndex = this.classFile.addConstantClass(25);
    
    Assert.assertEquals(index, newIndex);
  }
  
  @Test
  public void addConstantString0() {
    final int index = this.classFile.addConstantString(1);
    
    Assert.assertEquals(index, 1 + OFFSET);
  }
  
  @Test
  public void addConstantString1() {
    int index = this.classFile.addConstantString(1);
    index = this.classFile.addConstantString(2);
    
    Assert.assertEquals(index, 2 + OFFSET);
  }
  
  @Test
  public void addConstantString2() {
    final int index = this.classFile.addConstantString(24);
    final int newIndex = this.classFile.addConstantString(24);
    
    Assert.assertEquals(index, newIndex);
  }
  
  @Test
  public void addConstantInteger0() {
    final int index = this.classFile.addConstantInteger(10);
    
    Assert.assertEquals(index, 1 + OFFSET);
  }
  
  @Test
  public void addConstantInteger1() {
    int index = this.classFile.addConstantInteger(10);
    index = this.classFile.addConstantInteger(14);
    
    Assert.assertEquals(index, 2 + OFFSET);
  }
  
  @Test
  public void addConstantInteger2() {
    final int index = this.classFile.addConstantInteger(10);
    final int newIndex = this.classFile.addConstantInteger(10);
    
    Assert.assertEquals(index, newIndex);
  }
  
  @Test
  public void addConstantFloat0() {
    final int index = this.classFile.addConstantFloat(10.5f);
    
    Assert.assertEquals(index, 1 + OFFSET);
  }
  
  @Test
  public void addConstantFloat1() {
    int index = this.classFile.addConstantFloat(10.5f);
    index = this.classFile.addConstantFloat(14.9f);
    
    Assert.assertEquals(index, 2 + OFFSET);
  }
  
  @Test
  public void addConstantFloat2() {
    final int index = this.classFile.addConstantFloat(10.5f);
    final int newIndex = this.classFile.addConstantFloat(10.5f);
    
    Assert.assertEquals(index, newIndex);
  }
  
  @Test
  public void addConstantLong0() {
    final int index = this.classFile.addConstantLong(222_333_333_333_999l);
    
    Assert.assertEquals(index, 1 + OFFSET);
  }
  
  @Test
  public void addConstantLong1() {
    int index = this.classFile.addConstantLong(222_333_333_333_999l);
    index = this.classFile.addConstantLong(222_333_333_333_888l);
   
    Assert.assertEquals(index, 3 + OFFSET);
  }
  
  @Test
  public void addConstantLong2() {
    final int index = this.classFile.addConstantLong(222_333_333_333_999l);
    final int newIndex = this.classFile.addConstantLong(222_333_333_333_999l);
   
    Assert.assertEquals(index, newIndex);
  }
  
  @Test
  public void addConstantDouble0() {
    final int index = this.classFile.addConstantDouble(25.678);
    
    Assert.assertEquals(index, 1 + OFFSET);
  }
  
  @Test
  public void addConstantDouble1() {
    int index = this.classFile.addConstantDouble(25.678);
    index = this.classFile.addConstantDouble(104.7);
    
    Assert.assertEquals(index, 3 + OFFSET);
  }
  
  @Test
  public void addConstantDouble2() {
    final int index = this.classFile.addConstantDouble(25.678);
    final int newIndex = this.classFile.addConstantDouble(25.678);
    
    Assert.assertEquals(index, newIndex);
  }
}
