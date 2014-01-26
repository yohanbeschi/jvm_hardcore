package org.isk.jvmhardcore.pjba;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.isk.jvmhardcore.pjba.structure.ClassFile;
import org.junit.Test;

public class Assembler {
  private static String GENERATED_DIRECTORY;

  /**
   * Find the current directory and set the one that will use used to generate the class files.
   * 
   * @throws Exception
   */
  static {
    final File directory = new File(".");
    try {
      if (directory.getCanonicalPath().contains("pjba")) {
        GENERATED_DIRECTORY = "02_build/pjb-classes/";
      } else {
        GENERATED_DIRECTORY = "03_projects/pjba/02_build/pjb-classes/";
      }
    } catch (IOException e) {
    }
  }

  /**
   * Assembles all files in the directory <code>pjb/</code> of the current project.
   * 
   * @throws Exception
   */
  @Test
  public void assemblePjb() throws Exception {
    // TODO: Like in bytecode project
    // Without this method we get an error while running tests
  }

  /**
   * Create a class file in the directory <code>02_build/pjb-classes</code>.
   * 
   * @param classFile
   * @throws Exception
   */
  public static void createFile(final ClassFile classFile) throws Exception {
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
}
