package org.isk.jvmhardcore.mathparser.core.util;

public class StringGenerator {
	private StringBuilder builder;

	public StringGenerator() {
		super();
		this.builder = new StringBuilder();
	}

	/**
	 * Appends the specified string to this character sequence.<br>
	 * The characters of the String argument are appended, in order, increasing
	 * the length of this sequence by the length of the argument. If string is
	 * null, then the four characters "null" are appended.<br>
	 * Let n be the length of this character sequence just prior to execution of
	 * the append method. Then the character at index k in the new character
	 * sequence is equal to the character at index k in the old character
	 * sequence, if k is less than n; otherwise, it is equal to the character at
	 * index k-n in the argument string.
	 * 
	 * @param string is the string to append
	 */
	public void append(final String string) {
		this.builder.append(string);
	}

	/**
	 * Appends the string representation of the char argument to this sequence.<br>
	 * The argument is appended to the contents of this sequence. The length of
	 * this sequence increases by 1.<br>
	 * The overall effect is exactly as if the argument were converted to a
	 * string by the method String.valueOf(char), and the character in that
	 * string were then appended to this character sequence.
	 * 
	 * @param character is the character to append
	 */
	public void append(final char character) {
		this.builder.append(character);
	}

	/**
	 * Appends the string representation of the integer argument to this sequence.<br>
	 * The argument is appended to the contents of this sequence. The length of
	 * this sequence increases by 1.<br>
	 * The overall effect is exactly as if the argument were converted to a
	 * string by the method String.valueOf((char)integer), and the character in that
	 * string were then appended to this character sequence.
	 * 
	 * @param integer is the character to append
	 */
	public void appendChar(final int integer) {
		this.builder.append((char)integer);
	}

	/**
	 * Whether or not the sequence is null.
	 * @return true if the sequence is null, otherwise false.
	 */
	public boolean isEmpty() {
		return this.builder.length() == 0;
	}

	/**
	 * Returns a string representing the data in this sequence and reset the
	 * sequence. A new String object is allocated and initialized to contain the
	 * character sequence currently represented by this object. This String is
	 * then returned. Subsequent changes to this sequence do not affect the
	 * contents of the String.
	 * 
	 * @return the string built.
	 */
	public String toString() {
		String string = this.builder.toString();
		this.builder.setLength(0);
		return string;
	}
}
