package org.isk.jvmhardcore.mystery;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.FileOutputStream;

/**
 * Useless application
 * <ul>
 * <li>dumping a class file to a hex file containing the representation of
 * each byte as two ASCII characters</li>
 * <li>assembling a hex file to a class</li>
 * </ul>
 * 
 * @author Yohan Beschi
 */
public class AssembleAndDump {
  final static byte[] HEX_ARRAY = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

  /**
   * Entry point<br>
   * 3 possible values for arguments:
   * <ul>
   * <li>assemble=<filename>.hex</li>
   * <li>dump=<filename>.class</li>
   * <li>help</li>
   * </ul>
   */
  public static void main(String[] args) throws Exception {
    if (args != null && args.length == 1) {
      final String arg = args[0];
      final int equalsIdx = arg.indexOf("=");

      if (equalsIdx != -1) {
        final String pathStr = arg.substring(equalsIdx + 1);

        if (arg.startsWith("assemble")) {
          assemble(pathStr);
        } else if (arg.startsWith("dump")) {
          dump(pathStr);
        } else {
          displayHelp();
        }
      } else {
        displayHelp();
      }
    }
  }

  /**
   * Help message
   */
  public static void displayHelp() {
    final StringBuilder sb = new StringBuilder();
    sb.append("Syntax: <call java with optionally options> AssembleAndDump [arg]\n");
    sb.append("Arguments (only one at a time:)\n");
    sb.append("\tdump=<filename>.class\tTakes a class file and dumps it as ASCII Hexadecimal characters\n");
    sb.append("\t\t\t\tin a file named <filename>.hex\n");
    sb.append("\tassemble=<filename>.hex\tTakes a hex file and creates a class file named <filename>.class\n");
    sb.append("\thelp\t\t\tDisplays this message");

    System.out.println(sb.toString());
  }

  /**
   * <ol>
   * <li>Opens the file from the path in parameter</li>
   * <li>Converts each element of a byte array (the file) to two ASCII
   * hexadecimal characters</li>
   * <li>Writes the new array of byte to a .hex file</li>
   * </ol>
   */
  public static void dump(final String pathStr) throws Exception {
    if (!pathStr.endsWith(".class")) {
      System.out.println("The file extension should be .class");
    }

    final Path path = Paths.get(pathStr);
    final byte[] buffer = Files.readAllBytes(path);
    final byte[] bytes = bytesToHex(buffer);

    writeFile(bytes, path, ".hex");
  }

  /**
   * <ol>
   * <li>Opens the file from the path in parameter</li>
   * <li>Converts a byte array - the file - (where each element is and ASCII
   * hexadecimal character, and 2 contiguous elements represent a byte) to the
   * represented byte</li>
   * <li>Writes the new array of byte to a .class file</li>
   * </ol>
   */
  public static void assemble(final String pathStr) throws Exception {
    final Path path = Paths.get(pathStr);
    final byte[] buffer = Files.readAllBytes(path);
    final String string = new String(buffer);
    final byte[] bytes = hexToBytes(string);

    writeFile(bytes, path, ".class");
  }

  /**
   * Converts each element of a byte array to two ASCII hexadecimal characters
   * and set them in a new array.
   */
  public static byte[] bytesToHex(byte[] bytes) {
    final byte[] hexChars = new byte[bytes.length * 2];
    int v;
    for (int i = 0; i < bytes.length; i++) {
      v = bytes[i] & 0xFF;
      hexChars[i * 2] = HEX_ARRAY[v >>> 4];
      hexChars[i * 2 + 1] = HEX_ARRAY[v & 0x0F];
    }
    return hexChars;
  }

  /**
   * Converts a byte array (where each element is and ASCII hexadecimal
   * character, and 2 contiguous elements represent a byte) to the represented
   * byte
   */
  public static byte[] hexToBytes(String string) {
    final int length = string.length();
    final byte[] hex = new byte[length / 2];
    for (int i = 0; i < length; i += 2) {
      hex[i / 2] = (byte) ((Character.digit(string.charAt(i), 16) << 4) + Character.digit(string.charAt(i + 1), 16));
    }

    return hex;
  }

  /**
   * Write a file from an array of bytes, where the name is the same as the
   * original file.
   */
  public static void writeFile(final byte[] bytes, final Path path, final String extension) throws Exception {
    final String fileName = getFileNameWithoutExtension(path.toFile().getName());

    final FileOutputStream fileOutputStream = new FileOutputStream(fileName + extension);
    fileOutputStream.write(bytes);
    fileOutputStream.close();
  }

  /**
   * Get the name of a file without its extensions.
   */
  public static String getFileNameWithoutExtension(final String fileName) {
    int idx = fileName.lastIndexOf(".");

    if (idx > 0) {
      return fileName.substring(0, idx);
    }

    return fileName;
  }
}