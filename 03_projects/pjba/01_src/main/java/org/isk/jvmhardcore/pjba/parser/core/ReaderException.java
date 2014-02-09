package org.isk.jvmhardcore.pjba.parser.core;

public class ReaderException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public ReaderException() {
    super();
  }

  public ReaderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public ReaderException(String message, Throwable cause) {
    super(message, cause);
  }

  public ReaderException(String message) {
    super(message);
  }

  public ReaderException(Throwable cause) {
    super(cause);
  }
}