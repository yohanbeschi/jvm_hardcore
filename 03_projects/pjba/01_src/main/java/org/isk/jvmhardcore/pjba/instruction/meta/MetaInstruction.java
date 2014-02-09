package org.isk.jvmhardcore.pjba.instruction.meta;


public abstract class MetaInstruction {

  final private String mnemonic;
  final private String pjbMnemonic;
  final private ArgsType argsType;
  protected int opcode;

  public MetaInstruction(final String mnemonic, final ArgsType argsType) {
    this(mnemonic, mnemonic, argsType);
  }

  public MetaInstruction(final String mnemonic, final String pjbMnemonic, final ArgsType argsType) {
    super();

    this.mnemonic = mnemonic;
    this.pjbMnemonic = pjbMnemonic;
    this.argsType = argsType;
  }

  public int getOpcode() {
    return this.opcode;
  }

  public String getMnemonic() {
    return this.mnemonic;
  }

  public ArgsType getArgsType() {
    return this.argsType;
  }

  public String getPjbMnemonic() {
    return this.pjbMnemonic;
  }

  public static enum ArgsType {
    NONE, 
    BYTE_VALUE, // => 1 byte
    SHORT_VALUE, // => 1 short
    IFS_CONSTANT, // int, float and String => 1 byte
    W_IFS_CONSTANT, // int, float and String => 1 short
    LD_CONSTANT // long and double => 1 short
  }
}
