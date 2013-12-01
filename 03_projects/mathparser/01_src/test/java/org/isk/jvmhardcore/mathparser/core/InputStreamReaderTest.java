package org.isk.jvmhardcore.mathparser.core;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.isk.jvmhardcore.mathparser.core.util.Ascii;
import org.isk.jvmhardcore.test.Reflection;
import org.junit.Assert;
import org.junit.Test;

public class InputStreamReaderTest {
  // +-----------------------------------------------------------------------------------------------------------------
  // init()
  // +-----------------------------------------------------------------------------------------------------------------
  @Test
  public void init0() throws IOException {
    final String string = "abc";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());
    final InputStreamReader reader = new InputStreamReader(inputStream);
    inputStream.close();

    final int[] file = Reflection.getField(reader, "STREAM");
    Assert.assertArrayEquals(new int[] { Ascii.LOWERCASE_A, Ascii.LOWERCASE_B, Ascii.LOWERCASE_C, Ascii.EOF }, file);
  }

  @Test
  public void init1() throws IOException {
    final String string = "";
    final InputStream inputStream = new ByteArrayInputStream(string.getBytes());

    try {
      new InputStreamReader(inputStream);
      Assert.fail();
    } catch (ReaderException e) {
      Assert.assertEquals("This stream is empty.", e.getMessage());
    } finally {
      inputStream.close();
    }
  }

  @Test
  public void init2() throws IOException {
    final byte[] original = new byte[] { Ascii.LOWERCASE_A, Ascii.CR, Ascii.LF, Ascii.LOWERCASE_B, Ascii.LOWERCASE_C };
    final InputStream inputStream = new ByteArrayInputStream(original);
    final InputStreamReader reader = new InputStreamReader(inputStream);
    inputStream.close();

    final int[] file = Reflection.getField(reader, "STREAM");
    Assert.assertArrayEquals(new int[] { Ascii.LOWERCASE_A, Ascii.LF, Ascii.LOWERCASE_B, Ascii.LOWERCASE_C, Ascii.EOF,
        Ascii.NULL }, file);

    final int[] lines = Reflection.getField(reader, "lines");
    Assert.assertArrayEquals(new int[] { 1, 2, 2, 2, 0, 0 }, lines);

    final int[] columns = Reflection.getField(reader, "columns");
    Assert.assertArrayEquals(new int[] { 1, 0, 1, 2, 0, 0 }, columns);
  }

  @Test
  public void init3() throws IOException {
    final byte[] original = new byte[] { Ascii.LOWERCASE_A, Ascii.LF, Ascii.LOWERCASE_B, Ascii.LOWERCASE_C };
    final InputStream inputStream = new ByteArrayInputStream(original);
    final InputStreamReader reader = new InputStreamReader(inputStream);
    inputStream.close();

    final int[] file = Reflection.getField(reader, "STREAM");
    Assert.assertArrayEquals(
        new int[] { Ascii.LOWERCASE_A, Ascii.LF, Ascii.LOWERCASE_B, Ascii.LOWERCASE_C, Ascii.EOF }, file);

    final int[] lines = Reflection.getField(reader, "lines");
    Assert.assertArrayEquals(new int[] { 1, 2, 2, 2, 0 }, lines);

    final int[] columns = Reflection.getField(reader, "columns");
    Assert.assertArrayEquals(new int[] { 1, 0, 1, 2, 0 }, columns);
  }

  @Test
  public void init4() throws IOException {
    final byte[] original = new byte[] { Ascii.LOWERCASE_A, Ascii.CR, Ascii.LOWERCASE_B, Ascii.LOWERCASE_C };
    final InputStream inputStream = new ByteArrayInputStream(original);
    final InputStreamReader reader = new InputStreamReader(inputStream);
    inputStream.close();

    final int[] file = Reflection.getField(reader, "STREAM");
    Assert.assertArrayEquals(
        new int[] { Ascii.LOWERCASE_A, Ascii.LF, Ascii.LOWERCASE_B, Ascii.LOWERCASE_C, Ascii.EOF }, file);

    final int[] lines = Reflection.getField(reader, "lines");
    Assert.assertArrayEquals(new int[] { 1, 2, 2, 2, 0 }, lines);

    final int[] columns = Reflection.getField(reader, "columns");
    Assert.assertArrayEquals(new int[] { 1, 0, 1, 2, 0 }, columns);
  }

  // +-----------------------------------------------------------------------------------------------------------------
  // read()
  // +-----------------------------------------------------------------------------------------------------------------
  @Test
  public void read0() throws IOException {
    final InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("reader/lflf");
    final InputStreamReader reader = new InputStreamReader(inputStream);
    inputStream.close();

    int character = reader.read();
    Assert.assertEquals("Character A", Ascii.LOWERCASE_A, character);
    Assert.assertEquals("Column A", 1, reader.getColumn());
    Assert.assertEquals("Line A", 1, reader.getLine());

    character = reader.read();
    Assert.assertEquals("Character B", Ascii.LOWERCASE_B, character);
    Assert.assertEquals("Column B", 2, reader.getColumn());
    Assert.assertEquals("Line B", 1, reader.getLine());

    character = reader.read();
    Assert.assertEquals("Character C", Ascii.LOWERCASE_C, character);
    Assert.assertEquals("Column C", 3, reader.getColumn());
    Assert.assertEquals("Line C", 1, reader.getLine());

    character = reader.read();
    Assert.assertEquals("Character LF", Ascii.LF, character);
    Assert.assertEquals("Column LF", 0, reader.getColumn());
    Assert.assertEquals("Line LF", 2, reader.getLine());

    character = reader.read();
    Assert.assertEquals("Character LF", Ascii.LF, character);
    Assert.assertEquals("Column LF", 0, reader.getColumn());
    Assert.assertEquals("Line LF", 3, reader.getLine());

    character = reader.read();
    Assert.assertEquals("Character X", Ascii.LOWERCASE_X, character);
    Assert.assertEquals("Column X", 1, reader.getColumn());
    Assert.assertEquals("Line X", 3, reader.getLine());

    character = reader.read();
    Assert.assertEquals("Character Y", Ascii.LOWERCASE_Y, character);
    Assert.assertEquals("Column Y", 2, reader.getColumn());
    Assert.assertEquals("Line Y", 3, reader.getLine());

    character = reader.read();
    Assert.assertEquals("Character Z", Ascii.LOWERCASE_Z, character);
    Assert.assertEquals("Column Z", 3, reader.getColumn());
    Assert.assertEquals("Line Z", 3, reader.getLine());

    character = reader.read();
    Assert.assertEquals("Character EOF", Ascii.EOF, character);

    try {
      reader.read();
      Assert.fail();
    } catch (ReaderException e) {
      Assert.assertEquals("End of file reached. No more character to read.", e.getMessage());
    } finally {
      inputStream.close();
    }
  }

  @Test
  public void unread0() throws IOException {
    final InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("reader/lf");
    final InputStreamReader reader = new InputStreamReader(inputStream);
    inputStream.close();

    // ============ UnRead
    // Throws an exception
    try {
      reader.unread();
      Assert.fail();
    } catch (ReaderException e) {
      Assert.assertEquals("Nothing to unread.", e.getMessage());
    }

    // ============ Read 1 character
    // ---- A
    int character = reader.read();
    Assert.assertEquals("Character A1", Ascii.LOWERCASE_A, character);
    Assert.assertEquals("Column A1", 1, reader.getColumn());
    Assert.assertEquals("Line A1", 1, reader.getLine());

    // ============ UnRead 1 character
    reader.unread();

    // ============ Read 3 characters
    // ---- A
    character = reader.read();
    Assert.assertEquals("Character A2", Ascii.LOWERCASE_A, character);
    Assert.assertEquals("Column A2", 1, reader.getColumn());
    Assert.assertEquals("Line A2", 1, reader.getLine());
    // ---- B
    character = reader.read();
    // ---- C
    character = reader.read();

    // ============ UnRead 2 characters
    // ---- C
    reader.unread();
    // ---- B
    reader.unread();

    // ============ Read 3 characters
    // ---- B
    character = reader.read();
    Assert.assertEquals("Character B1", Ascii.LOWERCASE_B, character);
    Assert.assertEquals("Column B1", 2, reader.getColumn());
    Assert.assertEquals("Line B1", 1, reader.getLine());
    // ---- C
    character = reader.read();
    // ---- LF
    character = reader.read();
    // ---- X
    character = reader.read();

    // ============ UnRead 4 characters
    // ---- X
    reader.unread();
    // ---- LF
    reader.unread();
    // ---- C
    reader.unread();
    // ---- B
    reader.unread();

    // ============ Read 3 characters
    // ---- B
    character = reader.read();
    Assert.assertEquals("Character B2", Ascii.LOWERCASE_B, character);
    Assert.assertEquals("Column B2", 2, reader.getColumn());
    Assert.assertEquals("Line B2", 1, reader.getLine());
    // ---- C
    character = reader.read();
    // ---- LF
    character = reader.read();
    Assert.assertEquals("Character LF1", Ascii.LF, character);
    Assert.assertEquals("Column LF1", 0, reader.getColumn());
    Assert.assertEquals("Line LF1", 2, reader.getLine());

    // ============ UnRead 2 characters
    // ---- LF
    reader.unread();
    // ---- C
    reader.unread();

    // ============ Read 2 characters
    // ---- C
    character = reader.read();
    Assert.assertEquals("Character C", Ascii.LOWERCASE_C, character);
    Assert.assertEquals("Column C", 3, reader.getColumn());
    Assert.assertEquals("Line C", 1, reader.getLine());
    // ---- LF
    character = reader.read();

    // ============ UnRead 1 character
    // ---- LF
    reader.unread();

    // ============ Read 1 character
    // ---- LF
    character = reader.read();
    Assert.assertEquals("Character LF2", Ascii.LF, character);
    Assert.assertEquals("Column LF2", 0, reader.getColumn());
    Assert.assertEquals("Line LF2", 2, reader.getLine());

    // ============ Read 4 characters till the end
    // ---- X
    character = reader.read();
    // ---- Y
    character = reader.read();
    Assert.assertEquals("Character Y1", Ascii.LOWERCASE_Y, character);
    Assert.assertEquals("Column Y1", 2, reader.getColumn());
    Assert.assertEquals("Line Y1", 2, reader.getLine());
    // ---- Z
    reader.read();
    // ---- EOF
    character = reader.read();
    Assert.assertEquals("EOF", Ascii.EOF, character);

    // ============ UnRead 3 characters
    // ---- EOF
    character = reader.unread();
    // ---- Z
    character = reader.unread();
    Assert.assertEquals("Character Z1", Ascii.LOWERCASE_Z, character);
    Assert.assertEquals("Column Z1", 3, reader.getColumn());
    Assert.assertEquals("Line Z1", 2, reader.getLine());
    // ---- Y
    character = reader.unread();
    Assert.assertEquals("Character Y2", Ascii.LOWERCASE_Y, character);
    Assert.assertEquals("Column Y2", 2, reader.getColumn());
    Assert.assertEquals("Line Y2", 2, reader.getLine());

    // ============ Read 3 characters till the end
    // ---- Y
    character = reader.read();
    Assert.assertEquals("Character Y3", Ascii.LOWERCASE_Y, character);
    Assert.assertEquals("Column Y3", 2, reader.getColumn());
    Assert.assertEquals("Line Y3", 2, reader.getLine());
    // ---- Z
    reader.read();
    // ---- EOF
    character = reader.read();
    Assert.assertEquals("EOF", Ascii.EOF, character);
  }

  @Test
  public void unread1() throws IOException {
    final InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("reader/lf");
    final InputStreamReader reader = new InputStreamReader(inputStream);
    inputStream.close();

    // ============ UnRead
    // Throws an exception
    try {
      reader.unread(5);
      Assert.fail();
    } catch (ReaderException e) {
      Assert.assertEquals("Out of range. Can't unread so much characters.", e.getMessage());
    }

    // ============ Read 1 character
    // ---- A
    int character = reader.read();
    Assert.assertEquals("Character A1", Ascii.LOWERCASE_A, character);
    Assert.assertEquals("Column A1", 1, reader.getColumn());
    Assert.assertEquals("Line A1", 1, reader.getLine());

    // ============ UnRead 1 character
    reader.unread(1);

    // ============ Read 3 characters
    // ---- A
    character = reader.read();
    Assert.assertEquals("Character A2", Ascii.LOWERCASE_A, character);
    Assert.assertEquals("Column A2", 1, reader.getColumn());
    Assert.assertEquals("Line A2", 1, reader.getLine());
    // ---- B
    character = reader.read();
    // ---- C
    character = reader.read();

    // ============ UnRead 2 characters
    // ---- C/B
    reader.unread(2);

    // ============ Read 3 characters
    // ---- B
    character = reader.read();
    Assert.assertEquals("Character B1", Ascii.LOWERCASE_B, character);
    Assert.assertEquals("Column B1", 2, reader.getColumn());
    Assert.assertEquals("Line B1", 1, reader.getLine());
    // ---- C
    character = reader.read();
    // ---- LF
    character = reader.read();
    // ---- X
    character = reader.read();

    // ============ UnRead 4 characters
    // ---- X/LF/C/B
    reader.unread(4);

    // ============ Read 3 characters
    // ---- B
    character = reader.read();
    Assert.assertEquals("Character B2", Ascii.LOWERCASE_B, character);
    Assert.assertEquals("Column B2", 2, reader.getColumn());
    Assert.assertEquals("Line B2", 1, reader.getLine());
    // ---- C
    character = reader.read();
    // ---- LF
    character = reader.read();
    Assert.assertEquals("Character LF1", Ascii.LF, character);
    Assert.assertEquals("Column LF1", 0, reader.getColumn());
    Assert.assertEquals("Line LF1", 2, reader.getLine());

    // ============ UnRead 2 characters
    // ---- LF/C
    reader.unread(2);

    // ============ Read 2 characters
    // ---- C
    character = reader.read();
    Assert.assertEquals("Character C", Ascii.LOWERCASE_C, character);
    Assert.assertEquals("Column C", 3, reader.getColumn());
    Assert.assertEquals("Line C", 1, reader.getLine());
    // ---- LF
    character = reader.read();

    // ============ UnRead 1 character
    // ---- LF
    reader.unread(1);

    // ============ Read 1 character
    // ---- LF
    character = reader.read();
    Assert.assertEquals("Character LF2", Ascii.LF, character);
    Assert.assertEquals("Column LF2", 0, reader.getColumn());
    Assert.assertEquals("Line LF2", 2, reader.getLine());

    // ============ Read 4 characters till the end
    // ---- X
    character = reader.read();
    // ---- Y
    character = reader.read();
    Assert.assertEquals("Character Y1", Ascii.LOWERCASE_Y, character);
    Assert.assertEquals("Column Y1", 2, reader.getColumn());
    Assert.assertEquals("Line Y1", 2, reader.getLine());
    // ---- Z
    reader.read();
    // ---- EOF
    character = reader.read();
    Assert.assertEquals("EOF", Ascii.EOF, character);

    // ============ UnRead 2 characters
    // ---- Z
    reader.unread(2);

    // ============ Read 3 characters till the end
    // ---- Y
    character = reader.read();
    Assert.assertEquals("Character Y3", Ascii.LOWERCASE_Y, character);
    Assert.assertEquals("Column Y3", 2, reader.getColumn());
    Assert.assertEquals("Line Y3", 2, reader.getLine());
    // ---- Z
    reader.read();
    // ---- EOF
    character = reader.read();
    Assert.assertEquals("EOF", Ascii.EOF, character);
  }

  @Test
  public void markAndReset0() throws IOException {
    final InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("reader/lf");
    final InputStreamReader reader = new InputStreamReader(inputStream);
    inputStream.close();

    // ============ Read 3 characters
    // ---- A
    int character = reader.read();
    // ---- B
    character = reader.read();
    // ---- C
    character = reader.read();

    Assert.assertEquals("Character C1", Ascii.LOWERCASE_C, character);
    Assert.assertEquals("Column C1", 3, reader.getColumn());
    Assert.assertEquals("Line C1", 1, reader.getLine());

    // ============ Mark 0>1 [After C]
    reader.mark();

    // ============ Read 1 character
    // ---- LF
    character = reader.read();
    Assert.assertEquals("Character LF1-1", Ascii.LF, character);
    Assert.assertEquals("Column LF1-1", 0, reader.getColumn());
    Assert.assertEquals("Line LF1-1", 2, reader.getLine());

    // ============ Reset 1 [After C] > 0
    reader.reset();

    // ============ Read 1 character
    // ---- LF
    character = reader.read();
    Assert.assertEquals("Character LF1-2", Ascii.LF, character);
    Assert.assertEquals("Column LF1-2", 0, reader.getColumn());
    Assert.assertEquals("Line LF1-2", 2, reader.getLine());

    // ============ UnRead 1 character
    // ---- C
    reader.unread();

    // ============ Mark 0>1 [After C]
    reader.mark();

    // ============ Read 1 character
    // ---- LF
    character = reader.read();

    // ============ Mark 1>2 [After C / After LF]
    reader.mark();
    // ---- X
    character = reader.read();
    Assert.assertEquals("Character X-1", Ascii.LOWERCASE_X, character);
    Assert.assertEquals("Column X-1", 1, reader.getColumn());
    Assert.assertEquals("Line X-1", 2, reader.getLine());

    // ============ Reset 2[After C / After LF] > 1 [After C]
    reader.reset();

    // ============ UnRead 1 character
    // ---- LF
    reader.unread();

    // ============ Read 1 character
    // ---- LF
    character = reader.read();
    Assert.assertEquals("Character LF1-3", Ascii.LF, character);
    Assert.assertEquals("Column LF1-3", 0, reader.getColumn());
    Assert.assertEquals("Line LF1-3", 2, reader.getLine());
  }
  
  @Test
  public void markAndReset1() throws IOException {
    final InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("reader/lf");
    final InputStreamReader reader = new InputStreamReader(inputStream);
    inputStream.close();
    
    // ---- A
    reader.read();
    // ---- B
    reader.read();
    // ---- C
    reader.read();
    // ---- LF
    reader.read();
    // ---- X
    reader.read();
    
    reader.mark();
    
    // ---- Y
    reader.read();
    // ---- Z
    reader.read();
    // ---- EOF
    reader.read();
    
    reader.reset();
    
    int character = reader.read();
    Assert.assertEquals(Ascii.LOWERCASE_Y, character);
    Assert.assertEquals(2, reader.getColumn());
    Assert.assertEquals(2, reader.getLine());
  }
}
