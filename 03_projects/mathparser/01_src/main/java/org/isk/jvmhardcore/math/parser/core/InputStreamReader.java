package org.isk.jvmhardcore.math.parser.core;

import java.io.IOException;
import java.io.InputStream;

import org.isk.jvmhardcore.math.parser.core.util.Ascii;

public class InputStreamReader implements Reader {
  /**
   * The stream as an array of bytes.
   */
  final private int[] STREAM;

  /**
   * The current position in the stream as an array of ints.
   */
  private int counter = 0;

  /**
   * The last character of the stream has been read.
   */
  private boolean eofReached = false;

  /**
   * The line of the character in the stream.
   */
  private int[] lines = null;

  /**
   * The column of the character in the stream.
   */
  private int[] columns = null;

  /**
   * The saved position.
   */
  private int savedPosition;

  /**
   * The last action was an unread (for getColumn and getLine)
   */
  private boolean unread = false;

  /**
   * 
   * @param inputStream
   */
  public InputStreamReader(final InputStream inputStream) {
    this(inputStream, "UTF-8");
  }

  /**
   * 
   * @param inputStream
   * @param charsetName
   */
  public InputStreamReader(final InputStream inputStream, final String charsetName) {
    try {
      this.STREAM = new int[inputStream.available() + 1]; // +1 for EOF
      this.lines = new int[STREAM.length];
      this.columns = new int[STREAM.length];
      final java.io.InputStreamReader inputStreamReader = new java.io.InputStreamReader(inputStream, charsetName);
      this.init(inputStreamReader);
    } catch (IOException e) {
      throw new ReaderException(e);
    }
  }

  /**
   * Replaces all CRLF by LF.
   * 
   * @param inputStream
   * @throws IOException
   */
  private void init(final java.io.InputStreamReader inputStreamReader) throws IOException {
    int previousChar = -2;

    // Reads first character
    int currentChar = inputStreamReader.read();
    if (currentChar == -1) {
      throw new ReaderException("This stream is empty.");
    }

    if (currentChar == Ascii.CR) {
      this.STREAM[0] = Ascii.LF;
      this.columns[0] = 0;
      this.lines[0] = 2;
    } else {
      this.STREAM[0] = currentChar;
      this.columns[0] = 1;
      this.lines[0] = 1;
    }

    previousChar = currentChar;

    // Reads next characters
    int index = 1;
    while ((currentChar = inputStreamReader.read()) != -1) {
      if (previousChar == Ascii.CR && currentChar == Ascii.LF) {
        continue;
      }

      // CR replaced by LF
      if (currentChar == Ascii.CR) {
        this.STREAM[index] = Ascii.LF;
      }
      // Other characters (Skips CRLF)
      else {
        this.STREAM[index] = currentChar;
      }

      // Compute lines and columns
      int previousIndex = index - 1;
      if (this.STREAM[index] == Ascii.LF) {
        this.columns[index] = 0;
        this.lines[index] = this.lines[previousIndex] + 1;
      } else {
        this.columns[index] = this.columns[previousIndex] + 1;
        this.lines[index] = this.lines[previousIndex];
      }

      previousChar = currentChar;
      index++;
    }

    this.STREAM[index] = Ascii.EOF;
  }

  /**
   * Read the next character from the stream.
   * 
   * @return
   */
  public int read() {
    this.unread = false;

    if (this.eofReached) {
      throw new ReaderException("End of file reached. No more character to read.");
    }

    final int character = STREAM[this.counter];
    if (character == Ascii.EOF) {
      this.eofReached = true;
    } else {
      this.counter++;
    }

    return character;
  }

  /**
   * Unread characters and make them as not consumed.
   */
  public int unread() {
    this.unread = true;

    if (this.eofReached) {
      this.eofReached = false;
      return STREAM[counter];
    } else if (this.counter > 0) {
      return STREAM[--counter];
    } else {
      throw new ReaderException("Nothing to unread.");
    }
  }

  /**
   * Unread a character and make it as not consumed.
   * 
   * @param chars
   *          number of characters to unread.
   */
  public void unread(int chars) {
    if (this.counter >= chars) {
      if (this.eofReached) {
        this.eofReached = false;
      }
      this.counter -= chars;
    } else {
      throw new ReaderException("Out of range. Can't unread so much characters.");
    }
  }

  /**
   * Mark a position to go back
   */
  public void mark() {
    this.savedPosition = this.counter;
  }

  /**
   * Go back to the position saved by mark()
   */
  public void reset() {
    this.counter = this.savedPosition;
    this.eofReached = false;
    this.unread = true;
  }

  /**
   * Get current line
   */
  public int getLine() {
    if (this.counter > 0) {
      if (this.unread) {
        return this.lines[this.counter];
      } else {
        return this.lines[this.counter - 1];
      }
    } else {
      return 1;
    }
  }

  /**
   * Get current column
   */
  public int getColumn() {
    if (this.counter > 0) {
      if (this.unread) {
        return this.columns[this.counter];
      } else {
        return this.columns[this.counter - 1];
      }
    } else {
      return 1;
    }
  }
}
