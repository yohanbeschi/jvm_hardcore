package org.isk.jvmhardcore.math.parser.core;

public interface Reader {
  /**
   * Read the next character from the stream.
   */
  int read();

  /**
   * Unread the last character read and make it as not consumed.
   */
  int unread();

  /**
   * Unread characters and make them as not consumed.
   * 
   * @param chars
   *          number of characters to unread.
   */
  void unread(int chars);

  /**
   * Mark a position to go back
   */
  void mark();

  /**
   * Go back to the position saved by mark()
   */
  void reset();

  /**
   * Get current line
   */
  int getLine();

  /**
   * Get current column
   */
  int getColumn();
}
