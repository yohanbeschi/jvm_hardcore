package org.isk.jvmhardcore.pjba.parser;

public enum EventType {
  EOF,
  CLASS_START,
  CLASS_MODIFIER,
  CLASS_NAME,
  CLASS_END,
  METHOD_START,
  METHOD_MODIFIER,
  METHOD_NAME,
  METHOD_SIGNATURE,
  METHOD_END,
  INSTRUCTION;
}
