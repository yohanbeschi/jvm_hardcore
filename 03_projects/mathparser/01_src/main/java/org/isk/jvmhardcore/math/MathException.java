package org.isk.jvmhardcore.math;

public class MathException extends RuntimeException {

  private static final long serialVersionUID = 2413076920261396393L;

  public MathException() {
    super();
  }

  public MathException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public MathException(String message, Throwable cause) {
    super(message, cause);
  }

  public MathException(String message) {
    super(message);
  }

  public MathException(Throwable cause) {
    super(cause);
  }
}
