package org.isk.jvmhardcore.test;

import java.lang.reflect.Field;

public class Reflection {

  public static <T> T getField(final Object instance, final String fieldName) {
    return getField(instance, instance.getClass(), fieldName);
  }

  @SuppressWarnings("unchecked")
  public static <T> T getField(final Object instance, final Class<?> type, final String fieldName) {
    try {
      final Field field = type.getDeclaredField(fieldName);
      field.setAccessible(true);
      return (T) field.get(instance);
    } catch (Exception e) {
      throw new ReflectionException(e);
    }
  }
}

class ReflectionException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public ReflectionException() {
    super();
  }

  public ReflectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public ReflectionException(String message, Throwable cause) {
    super(message, cause);
  }

  public ReflectionException(String message) {
    super(message);
  }

  public ReflectionException(Throwable cause) {
    super(cause);
  }
}