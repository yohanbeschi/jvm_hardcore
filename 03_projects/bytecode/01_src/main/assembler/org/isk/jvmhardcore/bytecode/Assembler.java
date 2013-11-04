package org.isk.jvmhardcore.bytecode;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.isk.jvmhardcore.pjba.parser.PjbParser;
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
      if (directory.getCanonicalPath().contains("bytecode")) {
        GENERATED_DIRECTORY = "02_build/pjb-classes/";
      } else {
        GENERATED_DIRECTORY = "03_projects/bytecode/02_build/pjb-classes/";
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

    createFile(classFile);
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