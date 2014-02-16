package org.isk.jvmhardcore.pjba.structure.attribute;

import org.isk.jvmhardcore.pjba.structure.Exception;
import org.isk.jvmhardcore.pjba.structure.Instruction;
import org.isk.jvmhardcore.pjba.structure.attribute.constraint.CodeAttribute;
import org.isk.jvmhardcore.pjba.structure.attribute.constraint.MethodAttribute;
import org.isk.jvmhardcore.pjba.util.PjbaLinkedList;
import org.isk.jvmhardcore.pjba.visitor.Visitor;

public class Code extends Attribute implements MethodAttribute {
  public final static String ATTRIBUTE_NAME = "Code";

  private int maxStack;
  private int maxLocals;
  private int codeLength;

  private PjbaLinkedList<Instruction> instructions;
  private PjbaLinkedList<Exception> exceptions;
  private PjbaLinkedList<CodeAttribute> attributes;

  private int currentStack;

  public Code(final int attributeNameIndex) {
    super(attributeNameIndex);

    this.instructions = new PjbaLinkedList<>();
    this.exceptions = new PjbaLinkedList<>();
    this.attributes = new PjbaLinkedList<>();
  }

  public void setMaxStack(int maxStack) {
    this.maxStack = maxStack;
  }

  public void setMaxLocals(int maxLocals) {
    this.maxLocals = maxLocals;
  }

  public void setCodeLength(int codeLength) {
    this.codeLength = codeLength;
  }

  public int getCodeLength() {
    return this.codeLength;
  }

  public void setInstructions(PjbaLinkedList<Instruction> instructions) {
    this.instructions = instructions;
  }

  public void setParameterCount(int parameterCount) {
    this.addLocals(parameterCount);
  }

  private void addStack(int stack) {
    this.currentStack += stack;

    if (this.maxStack < this.currentStack) {
      this.maxStack = this.currentStack;
    }
  }

  private void addLocals(int locals) {
    if (this.maxLocals < locals) {
      this.maxLocals = locals;
    }
  }

  private void addLength(int length) {
    this.codeLength += length;
  }

  public void addInstruction(final Instruction instruction) {
    this.addStack(instruction.getStack());
    this.addLocals(instruction.getLocals());
    this.addLength(instruction.getLength());
    this.instructions.add(instruction);
  }

  @Override
  public void accept(Visitor visitor) {
    super.accept(visitor);

    // 2 + 2 + 4 + code.length + 2 + 8 * exceptionTable.length + 2
    visitor.visitAttributeLength(12 + this.codeLength + 8 * this.exceptions.size()); 
    visitor.visitCodeMaxStack(this.maxStack);
    visitor.visitCodeMaxLocals(this.maxLocals);
    visitor.visitCodeLength(this.codeLength);
    this.instructions.accept(visitor);
    visitor.visitCodeExceptionsSize(this.exceptions.size());
    this.exceptions.accept(visitor);
    visitor.visitCodeAttributesSize(this.attributes.size());
    this.attributes.accept(visitor);
  }
}
