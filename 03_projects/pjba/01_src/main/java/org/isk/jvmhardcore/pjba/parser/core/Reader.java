package org.isk.jvmhardcore.pjba.parser.core;

public interface Reader {
  int read();

  int unread();

  void unread(int chars);

  void mark();

  void reset();

  int getLine();

  int getColumn();
}
