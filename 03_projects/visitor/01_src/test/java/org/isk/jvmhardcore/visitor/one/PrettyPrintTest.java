package org.isk.jvmhardcore.visitor.one;

import org.isk.jvmhardcore.visitor.one.structure.ClassFile;
import org.isk.jvmhardcore.visitor.one.structure.Field;
import org.isk.jvmhardcore.visitor.one.structure.Method;
import org.junit.Assert;
import org.junit.Test;

public class PrettyPrintTest {
  @Test
  public void prettyPrint() {
    final ClassFile classFile = new ClassFile("MyClass");
    classFile.addMethod(new Method("method1"));
    classFile.addMethod(new Method("method2"));
    classFile.addField(new Field("field1"));
    classFile.addMethod(new Method("method3"));
    classFile.addField(new Field("field2"));

    final PrettyPrint prettyPrint = new PrettyPrint(classFile);
    final String classFileAsString = prettyPrint.build();
    Assert.assertEquals(EXPECTED, classFileAsString);
  }

  final public static String EXPECTED = 
        "Class Name: MyClass\n"
      + "  Fields:\n"
      + "    field1\n"
      + "    field2\n"
      + "  Methods:\n"
      + "    method1\n"
      + "    method2\n"
      + "    method3\n";
}
