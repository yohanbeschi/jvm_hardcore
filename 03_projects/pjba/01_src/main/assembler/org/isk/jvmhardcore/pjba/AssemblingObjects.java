package org.isk.jvmhardcore.pjba;

import org.isk.jvmhardcore.pjba.builder.ClassFileBuilder;
import org.isk.jvmhardcore.pjba.structure.ClassFile;
import org.isk.jvmhardcore.pjba.structure.Field;
import org.isk.jvmhardcore.pjba.structure.Method;
import org.junit.Test;

public class AssemblingObjects {
  final public static String OBJECT = "java/lang/Object";
  final public static String INTEGER = "java/lang/Integer";
  final public static String USER = "org/isk/jvmhardcore/pjba/object/first/User";
  final public static String POINT = "org/isk/jvmhardcore/pjba/object/second/abs/Point";
  final public static String IPOINT = "org/isk/jvmhardcore/pjba/object/second/inter/IPoint";
  final public static String POINT3D = "org/isk/jvmhardcore/pjba/object/second/conc/Point3D";
  final public static String COMPARABLE = "java/lang/Comparable";

  @Test
  public void assemble0() throws Exception {
    final ClassFileBuilder builder = new ClassFileBuilder(ClassFile.MODIFIER_PUBLIC, USER);

    // ---- Fields
    builder.newField(Field.MODIFIER_PUBLIC, "username", "Ljava/lang/String;");
    builder.newField(Field.MODIFIER_PUBLIC, "age", "I");

    builder.newField(Field.MODIFIER_PROTECTED, "fieldInt", "I");
    builder.newField(Field.MODIFIER_PRIVATE, "fieldLong", "J");
    builder.newField(Field.MODIFIER_PROTECTED, "fieldFloat", "F");
    builder.newField(Field.MODIFIER_PRIVATE, "fieldDouble", "D");
    builder.newField(Field.MODIFIER_PRIVATE, "fieldString", "Ljava/lang/String;");

    // ---- Constructors
    builder.newConstructor(Method.MODIFIER_PUBLIC, "()V")
      .aload_0()
      .invokespecial(OBJECT, "<init>", "()V")
      .return_();

    builder.newConstructor(Method.MODIFIER_PUBLIC, "(Ljava/lang/String;I)V")
      .aload_0()
      .invokespecial(OBJECT, "<init>", "()V")
      .aload_0()
      .aload_1()
      .putfield(USER, "username", "Ljava/lang/String;")
      .aload_0()
      .iload_2()
      .putfield(USER, "age", "I")
      .return_();

    // ---- Getters/Setters
    builder.newMethod(Method.MODIFIER_PUBLIC, "getFieldInt", "()I")
      .aload_0()
      .getfield(USER, "fieldInt", "I")
      .ireturn();

    builder.newMethod(Method.MODIFIER_PUBLIC, "setFieldInt", "(I)V")
      .aload_0()
      .iload_1()
      .putfield(USER, "fieldInt", "I")
      .return_();

    builder.newMethod(Method.MODIFIER_PUBLIC, "getFieldLong", "()J")
      .aload_0()
      .getfield(USER, "fieldLong", "J")
      .lreturn();

    builder.newMethod(Method.MODIFIER_PUBLIC, "setFieldLong", "(J)V")
      .aload_0()
      .lload_1()
      .putfield(USER, "fieldLong", "J")
      .return_();

    builder.newMethod(Method.MODIFIER_PUBLIC, "getFieldFloat", "()F")
      .aload_0()
      .getfield(USER, "fieldFloat", "F")
      .freturn();

    builder.newMethod(Method.MODIFIER_PUBLIC, "setFieldFloat", "(F)V")
      .aload_0()
      .fload_1()
      .putfield(USER, "fieldFloat", "F")
      .return_();

    builder.newMethod(Method.MODIFIER_PUBLIC, "getFieldDouble", "()D")
      .aload_0()
      .getfield(USER, "fieldDouble", "D")
      .dreturn();

    builder.newMethod(Method.MODIFIER_PUBLIC, "setFieldDouble", "(D)V")
      .aload_0()
      .dload_1()
      .putfield(USER, "fieldDouble", "D")
      .return_();

    builder.newMethod(Method.MODIFIER_PUBLIC, "getFieldString", "()Ljava/lang/String;")
      .aload_0()
      .getfield(USER, "fieldString", "Ljava/lang/String;")
      .areturn();

    builder.newMethod(Method.MODIFIER_PUBLIC, "setFieldString", "(Ljava/lang/String;)V")
      .aload_0()
      .aload_1()
      .putfield(USER, "fieldString", "Ljava/lang/String;")
      .return_();

    // Factory
    builder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "factory", "()L" + USER + ";")
      .new_(USER)
      .dup()
      .invokespecial(USER, "<init>", "()V")
      .areturn();

    final ClassFile build = builder.build();
    org.isk.jvmhardcore.pjba.Assembling.createFile(build);
  }

  @Test
  public void assemble1() throws Exception {
    this.buildPoint();
    this.buildIPoint();
    this.build3DPoint();
  }

  private void buildPoint() throws Exception {
    final ClassFileBuilder pointBuilder = new ClassFileBuilder(ClassFile.MODIFIER_PUBLIC | ClassFile.MODIFIER_ABSTRACT, POINT, OBJECT);
    pointBuilder.newField(Field.MODIFIER_PUBLIC, "x", "I");
    pointBuilder.newField(Field.MODIFIER_PUBLIC, "y", "I");

    pointBuilder.newConstructor(Method.MODIFIER_PUBLIC, "(II)V")
      .aload_0()
      .invokespecial(OBJECT, "<init>", "()V")
      .aload_0()
      .iload_1()
      .putfield(POINT, "x", "I")
      .aload_0()
      .iload_2()
      .putfield(POINT, "y", "I")
      .return_();

    pointBuilder.newMethod(Method.MODIFIER_PUBLIC, "speed", "(II)V")
      .aload_0()
      .iload_1()
      .iload_2()
      .invokevirtual(POINT, "move", "(II)V")
      .return_();

    pointBuilder.newMethod(Method.MODIFIER_PUBLIC, "goLeft", "()V")
      .aload_0()
      .iconst_m1()
      .iconst_0()
      .invokevirtual(POINT, "move", "(II)V")
      .return_();

    pointBuilder.newMethod(Method.MODIFIER_PROTECTED, "goRight", "()V")
      .aload_0()
      .iconst_1()
      .iconst_0()
      .invokevirtual(POINT, "move", "(II)V")
      .return_();

    pointBuilder.newMethod(Method.MODIFIER_PROTECTED, "delegateGoUp", "()V")
      .aload_0()
      .invokespecial(POINT, "goUp", "()V")
      .return_();

    pointBuilder.newMethod(Method.MODIFIER_PRIVATE, "goUp", "()V")
      .aload_0()
      .iconst_0()
      .iconst_1()
      .invokevirtual(POINT, "move", "(II)V")
      .return_();

    pointBuilder.newAbstractMethod(Method.MODIFIER_PUBLIC, "move", "(II)V");

    final ClassFile point = pointBuilder.build();
    org.isk.jvmhardcore.pjba.Assembling.createFile(point);
  }

  private void buildIPoint() throws Exception {
    final ClassFileBuilder ipointBuilder = new ClassFileBuilder(ClassFile.MODIFIER_PUBLIC | ClassFile.MODIFIER_INTERFACE, IPOINT, OBJECT);

    ipointBuilder.newAbstractMethod(Method.MODIFIER_PUBLIC, "goDown", "()V");

    final ClassFile ipoint = ipointBuilder.build();
    org.isk.jvmhardcore.pjba.Assembling.createFile(ipoint);
  }

  private void build3DPoint() throws Exception {
    final ClassFileBuilder pointBuilder = new ClassFileBuilder(ClassFile.MODIFIER_PUBLIC | ClassFile.MODIFIER_FINAL, POINT3D, POINT);
    pointBuilder.newInterface(IPOINT);
    pointBuilder.newInterface(COMPARABLE);
    pointBuilder.newField(Field.MODIFIER_PRIVATE, "z", "I");

    pointBuilder.newConstructor(Method.MODIFIER_PUBLIC, "(III)V")
      .aload_0()
      .iload_1()
      .iload_2()
      .invokespecial(POINT, "<init>", "(II)V")
      .aload_0()
      .iload_2()
      .putfield(POINT3D, "z", "I")
      .return_();

    // Implements
    pointBuilder.newMethod(ClassFile.MODIFIER_PUBLIC, "move", "(II)V")
      .aload_0()
      .dup()
      .getfield(POINT, "x", "I")
      .iload_1()
      .iadd()
      .putfield(POINT, "x", "I")
      .aload_0()
      .dup()
      .getfield(POINT, "y", "I")
      .iload_2()
      .iadd()
      .putfield(POINT, "y", "I")
      .return_();

    // Implements
    pointBuilder.newMethod(ClassFile.MODIFIER_PUBLIC, "goDown", "()V")
      .aload_0()
      .iconst_0()
      .iconst_m1()
      .invokevirtual(POINT3D, "move", "(II)V")
      .return_();

    // Overloads
    pointBuilder.newMethod(ClassFile.MODIFIER_PUBLIC, "goDown", "(II)V")
      .aload_0()
      .iload_1()
      .iload_2()
      .invokevirtual(POINT3D, "move", "(II)V")
      .return_();

    // Overrides
    pointBuilder.newMethod(Method.MODIFIER_PROTECTED, "goRight", "()V")
      .aload_0()
      .iconst_1()
      .iconst_0()
      .invokevirtual(POINT3D, "move", "(II)V")
      .return_();

    // Delegates Protected From Class
    pointBuilder.newMethod(Method.MODIFIER_PUBLIC, "delegateGoRight", "()V")
      .aload_0()
      .invokevirtual(POINT3D, "goRight", "()V")
      .return_();

    // Delegates Protected From Parent
    pointBuilder.newMethod(Method.MODIFIER_PUBLIC, "goUp", "()V")
      .aload_0()
      .invokevirtual(POINT, "delegateGoUp", "()V")
      .return_();

    // Explicitly calling parent
    pointBuilder.newMethod(Method.MODIFIER_PUBLIC, "speed", "(II)V")
      .aload_0()
      .iload_1()
      .iload_2()
      .invokespecial(POINT, "speed", "(II)V")
      .return_();

    pointBuilder.newMethod(Method.MODIFIER_PUBLIC | Method.MODIFIER_STATIC, "goDown", "(L" + IPOINT + ";)V")
      .aload_0()
      .invokeinterface(IPOINT, "goDown", "()V")
      .return_();

    pointBuilder.newMethod(Method.MODIFIER_PUBLIC, "compareTo", "(L" + OBJECT + ";)I")
      .aload_1()
      .aload_0()
      .if_acmpne("notSameRef")  // if (o == this)
      .iconst_0()
      .ireturn()                // Same referencce 
      .label("notSameRef")
      .aload_1()
      .instanceof_(POINT)
      .ifeq("notPoint")         // if (o instanceof Point)
      .aload_1()
      .checkcast(POINT)
      .astore_2()
      .aload_0()
      .getfield(POINT, "x", "I")
      .aload_2()
      .getfield(POINT, "x", "I")
      .invokestatic(INTEGER, "compare", "(II)I")
      .istore_3()
      .iload_3()
      .ifne("notSameX")         // if (compareX == 0)
      .aload_0()
      .getfield(POINT, "y", "I")
      .aload_2()
      .getfield(POINT, "y", "I")
      .invokestatic(INTEGER, "compare", "(II)I")
      .ireturn()                // Return the result of the comparison between Ys
      .label("notSameX")
      .iload_3()
      .ireturn()                // Return the result of the comparison between Xs
      .label("notPoint")
      .iconst_m1()              // Not the same object or other is null
      .ireturn();
    
    final ClassFile ipoint = pointBuilder.build();
    org.isk.jvmhardcore.pjba.Assembling.createFile(ipoint);
  }
}
