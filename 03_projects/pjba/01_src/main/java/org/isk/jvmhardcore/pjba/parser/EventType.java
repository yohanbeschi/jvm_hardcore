package org.isk.jvmhardcore.pjba.parser;

public enum EventType {
  EOF,
  CLASS_START,
  CLASS_MODIFIER,
  CLASS_NAME,
  CLASS_END,
  SUPER_START,
  SUPER_END,
  INTERFACE_START,
  INTERFACE_END, 
  FIELD_START,
  FIELD_END,
  FIELD_MODIFIER,
  FIELD_NAME,
  FIELD_TYPE,
  METHOD_START,
  METHOD_MODIFIER,
  METHOD_NAME,
  METHOD_SIGNATURE,
  METHOD_END,
  INSTRUCTION,
  LABEL,
  CONSTANT_VALUE;
}
