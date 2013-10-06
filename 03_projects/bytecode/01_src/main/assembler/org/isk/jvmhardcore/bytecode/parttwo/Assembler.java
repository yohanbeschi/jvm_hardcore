package org.isk.jvmhardcore.bytecode.parttwo;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

import org.isk.jvmhardcore.pjba.instructions.Instructions;
import org.isk.jvmhardcore.pjba.parser.PjbParser;
import org.isk.jvmhardcore.pjba.structure.ClassFile;
import org.isk.jvmhardcore.pjba.structure.Code;
import org.isk.jvmhardcore.pjba.structure.Method;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Assembles PJB files and PJBA Objects.
 * 
 * @author Yohan Beschi
 */
public class Assembler {
  private static String GENERATED_DIRECTORY;

  /**
   * Find the current directory and set the one that will use used to generate the class files.
   * 
   * @throws Exception
   */
  @BeforeClass
  public static void init() throws Exception {
    final File directory = new File(".");
    if (directory.getCanonicalPath().contains("bytecode")) {
      GENERATED_DIRECTORY = "02_build/pjb-classes/";
    } else {
      GENERATED_DIRECTORY = "03_projects/bytecode/02_build/pjb-classes/";
    }
  }

  /**
   * Assembles all files in the directory <code>pjb/</code> of the current project.
   * 
   * @throws Exception
   */
  @Test
  public void assemblePjb() throws Exception {
    final URL resource = Thread.currentThread().getContextClassLoader().getResource("pjb/");
    final File folder = new File(resource.getPath());
    this.goThroughEachFolder(folder);
  }

  private void goThroughEachFolder(final File file) throws Exception {
      final File[] listFiles = file.listFiles();
      
      if (listFiles != null) {
        for (final File fileEntry : listFiles) {
          if (fileEntry.isFile()) {
            this.generate(fileEntry);
          } else {
            this.goThroughEachFolder(fileEntry);
          }
        }
      }
  }

  /**
   * Generate a PJBA ClassFile.
   * 
   * @param filepath
   * @throws Exception
   */
  public void generate(final File file) throws Exception {
    final InputStream inputStream = new FileInputStream(file);
    final PjbParser parser = new PjbParser(file.getAbsolutePath(), inputStream);
    final ClassFile classFile = parser.parse();

    this.createFile(classFile);
  }

  /**
   * Create a class file in the directory <code>02_build/pjb-classes</code>.
   * 
   * @param classFile
   * @throws Exception
   */
  private void createFile(final ClassFile classFile) throws Exception {
    final String directoryStr = GENERATED_DIRECTORY + classFile.getDirectories();

    final File directory = new File(directoryStr);
    if (!directory.exists()) {
      directory.mkdirs();
    }

    final FileOutputStream file = new FileOutputStream(directoryStr + classFile.getClassName() + ".class");
    final DataOutput bytecode = new DataOutputStream(file);
    classFile.toBytecode(bytecode);
    file.close();
  }

  /**
   * Creates a class file in Java, using the same objects as the pjb parser.
   * 
   * @throws Exception
   */
  @Test
  public void assembleJava() throws Exception {
    // ConstantPoolEntries
    final String className = "org/isk/jvmhardcore/bytecode/parttwo/AdderJava";
    final String methodName = "add";
    final String methodDescriptor = "(II)I";

    // ClassFile
    final ClassFile classFile = new ClassFile(className);
    final int classNameIndex = classFile.addConstantUTF8(className);
    final int thisClassIndex = classFile.addConstantClass(classNameIndex);
    classFile.setThisClassIndex(thisClassIndex);

    // Method
    final int codeAttributeIndex = classFile.addConstantUTF8(Code.ATTRIBUTE_NAME);
    final int methodIndex = classFile.addConstantUTF8(methodName);
    final int descriptorIndex = classFile.addConstantUTF8(methodDescriptor);
    final Method method = new Method(codeAttributeIndex);
    method.setNameIndex(methodIndex);
    final int parameterCount = Method.getParameterCount(methodDescriptor);
    method.setDescriptorIndex(descriptorIndex, parameterCount);
    classFile.addMethod(method);

    // Instructions
    method.addInstruction(Instructions.iload_0());
    method.addInstruction(Instructions.iload_1());
    method.addInstruction(Instructions.iadd());
    method.addInstruction(Instructions.ireturn());

    this.createFile(classFile);
  }
}
