package org.isk.jvmhardcore.lexp;

import org.isk.jvmhardcore.lexp.core.Expression;
import org.isk.jvmhardcore.lexp.operators.ArithmeticOperator;
import org.isk.jvmhardcore.lexp.operators.BitwiseOperator;
import org.isk.jvmhardcore.lexp.operators.LogicalOperator;
import org.isk.jvmhardcore.lexp.operators.RelationalOperator;
import org.isk.jvmhardcore.lexp.structure.ArithmeticExpression;
import org.isk.jvmhardcore.lexp.structure.BitwiseExpression;
import org.isk.jvmhardcore.lexp.structure.IntValue;
import org.isk.jvmhardcore.lexp.structure.IntVariable;
import org.isk.jvmhardcore.lexp.structure.LogicalExpression;
import org.isk.jvmhardcore.lexp.structure.RelationalExpression;
import org.isk.jvmhardcore.lexp.structure.ZeroRelationalExpression;
import org.junit.Assert;
import org.junit.Test;

public class CompilerTest {
  @Test
  public void arithmeticAndBitwise() {
    // (a | 2) + 567 * b
    final Expression e = new ArithmeticExpression(
                             new BitwiseExpression(
                                 new IntVariable(0),
                                 BitwiseOperator.OR,
                                 new IntValue(2)
                             ),
                             ArithmeticOperator.PLUS,
                             new ArithmeticExpression(
                                 new IntValue(567),
                                 ArithmeticOperator.TIMES,
                                 new IntVariable(1)
                             )
                         );

    final Compiler compiler = new Compiler(e);
    Assert.assertEquals("iload 0\n"
                      + "ldc 2\n"
                      + "ior\n"
                      + "ldc 567\n"
                      + "iload 1\n"
                      + "imul\n"
                      + "iadd\n",
                        compiler.compile());
  }

  @Test
  public void relational0() {
    // a < b
    final Expression e = new RelationalExpression(
                             new IntVariable(0),
                             RelationalOperator.LT,
                             new IntVariable(1)
                         );

    final Compiler compiler = new Compiler(e);
    Assert.assertEquals("iload 0\n"
                      + "iload 1\n"
                      + "if_icmpge ko\n",
                        compiler.compile());
  }

  @Test
  public void relational1() {
    // a && b >= 0
    final Expression e = new LogicalExpression("and1", 
                             new ZeroRelationalExpression(new IntVariable(0)),
                             LogicalOperator.AND,
                             new ZeroRelationalExpression(new IntVariable(1), RelationalOperator.GE)
                         ); 

    final Compiler compiler = new Compiler(e);
    Assert.assertEquals("iload 0\n"
                      + "ifeq ko\n"
                      + "iload 1\n"
                      + "iflt ko\n",
                      compiler.compile());
  }
  
  @Test
  public void relational2() {
    // a && 5 >= 10
    final Expression e = new LogicalExpression("and1", 
                             new ZeroRelationalExpression(new IntVariable(0)),
                             LogicalOperator.AND,
                             new RelationalExpression(
                                 new IntValue(5),
                                 RelationalOperator.GE,
                                 new IntValue(10)
                             )
                         ); 

    final Compiler compiler = new Compiler(e);
    Assert.assertEquals("iload 0\n"
                      + "ifeq ko\n"
                      + "ldc 5\n"
                      + "ldc 10\n"
                      + "if_icmplt ko\n",
                      compiler.compile());
  }

  @Test
  public void returnTrue() {
    // a (or a == true)
    final Expression e = new IntVariable(0);

    final Compiler compiler = new Compiler(e);
    Assert.assertEquals("iload 0\n", compiler.compile());
  }

  @Test
  public void and() {
    // a && b
    final Expression e = new LogicalExpression("and1", 
                             new ZeroRelationalExpression(new IntVariable(0)),
                             LogicalOperator.AND,
                             new ZeroRelationalExpression(new IntVariable(1))
                         );

    final Compiler compiler = new Compiler(e);
    Assert.assertEquals("iload 0\n"
                      + "ifeq ko\n"
                      + "iload 1\n"
                      + "ifeq ko\n",
                      compiler.compile());
  }

  @Test
  public void or() {
    // a || b
    final Expression e = new LogicalExpression("or1",
                                 new ZeroRelationalExpression(new IntVariable(0)),
                                 LogicalOperator.OR,
                                 new ZeroRelationalExpression(new IntVariable(1))
                             );

    final Compiler compiler = new Compiler(e);
    Assert.assertEquals("iload 0\n"
                      + "ifne ok\n"
                      + "iload 1\n"
                      + "ifeq ko\n",
                      compiler.compile());
  }

  @Test
  public void or_and() {
    // (a && b) || c
    final Expression e =
          new LogicalExpression("or1",
              new LogicalExpression("and1",
                  new ZeroRelationalExpression(new IntVariable(0)),
                  LogicalOperator.AND,
                  new ZeroRelationalExpression(new IntVariable(1))
              ),
              LogicalOperator.OR,
              new ZeroRelationalExpression(new IntVariable(2))
          );

    final Compiler compiler = new Compiler(e);
    Assert.assertEquals("iload 0\n"
                      + "ifeq or1\n"
                      + "iload 1\n"
                      + "ifne ok\n"
                      + "or1:\n"
                      + "iload 2\n"
                      + "ifeq ko\n",
                      compiler.compile());
  }

  @Test
  public void and_or() {
    // (a || b) && c
    final Expression e =
        new LogicalExpression("and1",
            new LogicalExpression("or1",
                new ZeroRelationalExpression(new IntVariable(0)),
                LogicalOperator.OR,
                new ZeroRelationalExpression(new IntVariable(1))
            ),
            LogicalOperator.AND,
            new ZeroRelationalExpression(new IntVariable(2))
        );

    final Compiler compiler = new Compiler(e);
    Assert.assertEquals("iload 0\n"
                      + "ifne and1\n"
                      + "iload 1\n"
                      + "ifeq ko\n"
                      + "and1:\n"
                      + "iload 2\n"
                      + "ifeq ko\n",
                      compiler.compile());
  }

  @Test
  public void and_or_or() {
    // ((a || b) || (c || d)) && e
    final Expression e =
          new LogicalExpression("and1",
              new LogicalExpression("or1", 
                  new LogicalExpression("or2",
                      new ZeroRelationalExpression(new IntVariable(0)),
                      LogicalOperator.OR,
                      new ZeroRelationalExpression(new IntVariable(1))
                  ),
                  LogicalOperator.OR,
                  new LogicalExpression("or3",
                      new ZeroRelationalExpression(new IntVariable(2)),
                      LogicalOperator.OR,
                      new ZeroRelationalExpression(new IntVariable(3))
                  )
              ),
              LogicalOperator.AND,
              new ZeroRelationalExpression(new IntVariable(4))
          );

    final Compiler compiler = new Compiler(e);
    Assert.assertEquals("iload 0\n"
                      + "ifne and1\n"
                      + "iload 1\n"
                      + "ifne and1\n"
                      + "iload 2\n"
                      + "ifne and1\n"
                      + "iload 3\n"
                      + "ifeq ko\n"
                      + "and1:\n"
                      + "iload 4\n"
                      + "ifeq ko\n",
                      compiler.compile());
  }

  @Test
  public void or_and_and() {
    // ((a && b) && (c && d)) || e
    final Expression e =
          new LogicalExpression("or1",
              new LogicalExpression("and1", 
                  new LogicalExpression("and2",
                      new ZeroRelationalExpression(new IntVariable(0)),
                      LogicalOperator.AND,
                      new ZeroRelationalExpression(new IntVariable(1))
                  ),
                  LogicalOperator.AND,
                  new LogicalExpression("and3",
                      new ZeroRelationalExpression(new IntVariable(2)),
                      LogicalOperator.AND,
                      new ZeroRelationalExpression(new IntVariable(3))
                  )
              ),
              LogicalOperator.OR,
              new ZeroRelationalExpression(new IntVariable(4))
          );

    final Compiler compiler = new Compiler(e);
    Assert.assertEquals("iload 0\n"
                      + "ifeq or1\n"
                      + "iload 1\n"
                      + "ifeq or1\n"
                      + "iload 2\n"
                      + "ifeq or1\n"
                      + "iload 3\n"
                      + "ifne ok\n"
                      + "or1:\n"
                      + "iload 4\n"
                      + "ifeq ko\n",
                      compiler.compile());
  }

  @Test
  public void mixed1() {
    // (a && (b || c)) && (d || e)
    final Expression e =
          new LogicalExpression("and1", 
              new LogicalExpression("and2",
                  new ZeroRelationalExpression(new IntVariable(0)),
                  LogicalOperator.AND,
                  new LogicalExpression("or1",
                      new ZeroRelationalExpression(new IntVariable(1)),
                      LogicalOperator.OR,
                      new ZeroRelationalExpression(new IntVariable(2))
                  )
              ),
              LogicalOperator.AND,
              new LogicalExpression("or2",
                  new ZeroRelationalExpression(new IntVariable(3)),
                  LogicalOperator.OR,
                  new ZeroRelationalExpression(new IntVariable(4))
              )
          );

    final Compiler compiler = new Compiler(e);
    Assert.assertEquals("iload 0\n"
                      + "ifeq ko\n"
                      + "iload 1\n"
                      + "ifne and1\n"
                      + "iload 2\n"
                      + "ifeq ko\n"
                      + "and1:\n"
                      + "iload 3\n"
                      + "ifne ok\n"
                      + "iload 4\n"
                      + "ifeq ko\n",
                      compiler.compile());
  }

  @Test
  public void mixed2() {
    // (a || (b && c)) || (d && e)
    final Expression e =
          new LogicalExpression("or1", 
              new LogicalExpression("or2",
                  new ZeroRelationalExpression(new IntVariable(0)),
                  LogicalOperator.OR,
                  new LogicalExpression("and1",
                      new ZeroRelationalExpression(new IntVariable(1)),
                      LogicalOperator.AND,
                      new ZeroRelationalExpression(new IntVariable(2))
                  )
              ),
              LogicalOperator.OR,
              new LogicalExpression("and2",
                  new ZeroRelationalExpression(new IntVariable(3)),
                  LogicalOperator.AND,
                  new ZeroRelationalExpression(new IntVariable(4))
              )
          );

    final Compiler compiler = new Compiler(e);
    Assert.assertEquals("iload 0\n"
                      + "ifne ok\n"
                      + "iload 1\n"
                      + "ifeq or1\n"
                      + "iload 2\n"
                      + "ifne ok\n"
                      + "or1:\n"
                      + "iload 3\n"
                      + "ifeq ko\n"
                      + "iload 4\n"
                      + "ifeq ko\n",
                      compiler.compile());
  }

  @Test
  public void mixed3() {
    // ((a || b) && (c && (d || e))) || (f || (g && h))
    final Expression e =
          new LogicalExpression("or1",
              new LogicalExpression("and1",
                  new LogicalExpression("or2",
                      new ZeroRelationalExpression(new IntVariable(0)),
                      LogicalOperator.OR,
                      new ZeroRelationalExpression(new IntVariable(1))
                  ),
                  LogicalOperator.AND,
                  new LogicalExpression("and2",
                      new ZeroRelationalExpression(new IntVariable(2)),
                      LogicalOperator.AND,
                      new LogicalExpression("or3",
                          new ZeroRelationalExpression(new IntVariable(3)),
                          LogicalOperator.OR,
                          new ZeroRelationalExpression(new IntVariable(4))
                      )
                  )
              ),
              LogicalOperator.OR,
              new LogicalExpression("or4", 
                  new ZeroRelationalExpression(new IntVariable(5)),
                  LogicalOperator.OR,
                  new LogicalExpression("and3",
                      new ZeroRelationalExpression(new IntVariable(6)),
                      LogicalOperator.AND,
                      new ZeroRelationalExpression(new IntVariable(7))
                  )
              )
          );

    final Compiler compiler = new Compiler(e);
    Assert.assertEquals("iload 0\n"
                      + "ifne and1\n"
                      + "iload 1\n"
                      + "ifeq or1\n"
                      + "and1:\n"
                      + "iload 2\n"
                      + "ifeq or1\n"
                      + "iload 3\n"
                      + "ifne ok\n"
                      + "iload 4\n"
                      + "ifne ok\n"
                      + "or1:\n"
                      + "iload 5\n"
                      + "ifne ok\n"
                      + "iload 6\n"
                      + "ifeq ko\n"
                      + "iload 7\n"
                      + "ifeq ko\n",
                      compiler.compile());
  }

  @Test
  public void mixed4() {
    // ((a && b) || (c || (d && e))) && (f && (g || h))
    final Expression e =
          new LogicalExpression("and1",
              new LogicalExpression("or1",
                  new LogicalExpression("and2",
                      new ZeroRelationalExpression(new IntVariable(0)),
                      LogicalOperator.AND,
                      new ZeroRelationalExpression(new IntVariable(1))
                  ),
                  LogicalOperator.OR,
                  new LogicalExpression("or2",
                      new ZeroRelationalExpression(new IntVariable(2)),
                      LogicalOperator.OR,
                      new LogicalExpression("and3",
                          new ZeroRelationalExpression(new IntVariable(3)),
                          LogicalOperator.AND,
                          new ZeroRelationalExpression(new IntVariable(4))
                      )
                  )
              ),
              LogicalOperator.AND,
              new LogicalExpression("and4", 
                  new ZeroRelationalExpression(new IntVariable(5)),
                  LogicalOperator.AND,
                  new LogicalExpression("or3",
                      new ZeroRelationalExpression(new IntVariable(6)),
                      LogicalOperator.OR,
                      new ZeroRelationalExpression(new IntVariable(7))
                  )
              )
          );

    final Compiler compiler = new Compiler(e);
    Assert.assertEquals("iload 0\n"
                      + "ifeq or1\n"
                      + "iload 1\n"
                      + "ifne and1\n"
                      + "or1:\n"
                      + "iload 2\n"
                      + "ifne and1\n"
                      + "iload 3\n"
                      + "ifeq ko\n"
                      + "iload 4\n"
                      + "ifeq ko\n"
                      + "and1:\n"
                      + "iload 5\n"
                      + "ifeq ko\n"
                      + "iload 6\n"
                      + "ifne ok\n"
                      + "iload 7\n"
                      + "ifeq ko\n",
                      compiler.compile());
  }
}
