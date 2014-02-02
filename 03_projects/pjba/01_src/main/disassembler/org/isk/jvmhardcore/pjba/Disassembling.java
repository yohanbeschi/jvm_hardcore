package org.isk.jvmhardcore.pjba;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;

import org.isk.jvmhardcore.pjba.structure.ClassFile;
import org.junit.Test;

public class Disassembling {
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
        GENERATED_DIRECTORY = "02_build/class-classes/";
      } else {
        GENERATED_DIRECTORY = "03_projects/pjba/02_build/class-classes/";
      }
    } catch (IOException e) {
    }
  }

  /**
   * Disassembles all files in the directory <code>pjb-classes/</code> of the current project and reassembles them in
   * the directory <code>class-classes</code>.
   * 
   * @throws Exception
   */
  @Test
  public void disassembleClasses() throws Exception {
    final Enumeration<URL> enums = Thread.currentThread().getContextClassLoader().getResources(".");

    URL resource = null;
    while (enums.hasMoreElements()) {
      resource = enums.nextElement();
      if (resource.getPath().contains("pjb-classes")) {
        break;
      }

      // If not found => NullPointerException
      resource = null;
    }

    final File folder = new File(resource.getPath());
    this.goThroughEachFolder(folder);
  }

  private void goThroughEachFolder(final File file) throws Exception {
    final File[] listFiles = file.listFiles();

    if (listFiles != null) {
      for (final File fileEntry : listFiles) {
        if (fileEntry.isFile()) {
          this.disassemble(fileEntry);
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
  public void disassemble(final File file) throws Exception {
    final InputStream inputStream = new FileInputStream(file);
    final DataInput dataInput = new DataInputStream(inputStream);
    final Disassembler disassambler = new Disassembler(dataInput);
    final ClassFile classFile = disassambler.disassemble();

    this.createFile(classFile);
  }

  /**
   * Create a class file in the directory <code>02_build/pjb-classes</code>.
   * 
   * @param classFile
   * @throws Exception
   */
  public void createFile(final ClassFile classFile) throws Exception {
    final String directoryStr = GENERATED_DIRECTORY + classFile.getDirectories();

    final File directory = new File(directoryStr);
    if (!directory.exists()) {
      directory.mkdirs();
    }

    final FileOutputStream file = new FileOutputStream(directoryStr + classFile.getClassName() + ".class");
    final DataOutput bytecode = new DataOutputStream(file);
    final Assembler assembler = new Assembler(classFile, bytecode);
    assembler.assemble();
    file.close();
  }
}
