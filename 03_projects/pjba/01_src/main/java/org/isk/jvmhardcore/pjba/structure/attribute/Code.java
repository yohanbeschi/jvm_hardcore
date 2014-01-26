package org.isk.jvmhardcore.pjba.structure.attribute;

import java.io.DataOutput;
import java.io.IOException;

import org.isk.jvmhardcore.pjba.structure.Exception;
import org.isk.jvmhardcore.pjba.structure.Instruction;
import org.isk.jvmhardcore.pjba.structure.attribute.constraint.CodeAttribute;
import org.isk.jvmhardcore.pjba.structure.attribute.constraint.MethodAttribute;
import org.isk.jvmhardcore.pjba.util.PjbaLinkedList;

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
  public void toBytecode(DataOutput dataOutput) throws IOException {
    super.toBytecode(dataOutput);
    dataOutput.writeInt(12 + this.codeLength + 8 * this.exceptions.size()); // 2 + 2 + 4 + code.length + 2 + 8 *
                                                                            // exceptionTable.length + 2
    dataOutput.writeShort(this.maxStack);
    dataOutput.writeShort(this.maxLocals);
    dataOutput.writeInt(this.codeLength);
    this.instructions.toBytecode(dataOutput);
    dataOutput.writeShort(this.exceptions.size());
    this.exceptions.toBytecode(dataOutput);
    dataOutput.writeShort(this.attributes.size());
    this.attributes.toBytecode(dataOutput);
  }
}
