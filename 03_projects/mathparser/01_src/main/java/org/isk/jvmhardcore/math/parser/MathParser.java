package org.isk.jvmhardcore.math.parser;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.Stack;

import org.isk.jvmhardcore.math.MathException;
import org.isk.jvmhardcore.math.common.Operator;
import org.isk.jvmhardcore.math.parser.core.InputStreamReader;
import org.isk.jvmhardcore.math.parser.core.Parser;
import org.isk.jvmhardcore.math.parser.core.util.Ascii;

public class MathParser extends Parser<LinkedList<Object>, EventType, MathTokenizer> {

  final private LinkedList<Object> postfixExpression;
  final private Stack<ParsingOperator> operatorStack;

  public MathParser(final InputStream inputStream) {
    super(inputStream, Symbols.number());

    this.postfixExpression = new LinkedList<>();
    this.operatorStack = new Stack<>();
  }

  @Override
  public LinkedList<Object> parse() {
    EventType eventType = null;

    boolean done = false;
    while (!done) {
      eventType = this.getNextEvent();

      switch (eventType) {
        case FLOAT:
          this.postfixExpression.add(this.tokenizer.getFloat());
          break;
        case INTEGER:
          this.postfixExpression.add(this.tokenizer.getInteger());
          break;
        case OPERATOR:
          this.processOperator(this.tokenizer.getOperator());
          break;
        case LEFT_PARENTHESIS:
          this.operatorStack.push(this.tokenizer.getParenthesis());
          break;
        case RIGHT_PARENTHESIS:
          this.processRightParenthesis(this.tokenizer.getParenthesis());
          break;
        case EOF:
          this.tokenizer.checkEndOfFile();
          this.processEndOfFile();
          done = true;
          break;
        default:
          System.err.println("Unexpected event.");
          break;
      }
    }

    return this.postfixExpression;
  }

  @Override
  protected MathTokenizer newTokenizer(final String filename, final InputStream inputStream) {
    return new MathTokenizer(filename, new InputStreamReader(inputStream));
  }

  @Override
  protected void initProductionTable() {
    this.table[Symbols.STREAM] = new Productions.Stream();
    this.table[Symbols.EOF] = new Productions.EndOfFile();
    this.table[Symbols.EXPRESSION] = new Productions.Expression();
    this.table[Symbols.OR_RIGHT_EXPRESSION] = new Productions.OrRightExpression();
    this.table[Symbols.OR_LEFT_PARENTHESIS] = new Productions.OrLeftParenthesis();
    this.table[Symbols.OR_RIGHT_PARENTHESIS] = new Productions.OrRightParenthesis();
    this.table[Symbols.LEFT_PARENTHESIS] = new Productions.LeftParenthesis();
    this.table[Symbols.RIGHT_PARENTHESIS] = new Productions.RightParenthesis();
    this.table[Symbols.NUMBER] = new Productions.Number();
    this.table[Symbols.OPERATOR] = new Productions.Operator();
    this.table[Symbols.WS] = new Productions.Whitespaces();
  }

  private void processOperator(ParsingOperator parsingOperator) {
    // The current operator is of less priority than the previous one
    if (!this.operatorStack.isEmpty() && parsingOperator.le(this.operatorStack.peek())) {
      this.postfixExpression.add(ParsingOperator.getClean(this.operatorStack.pop()));
    }

    this.operatorStack.push(parsingOperator);
  }

  private void processRightParenthesis(ParsingOperator rightParenthsesis) {
    // A closing parenthesis has been reached
    // We add all operators until the opening parenthesis
    while (!this.operatorStack.isEmpty() && this.operatorStack.peek() != ParsingOperator.LEFT_PARENTHESIS) {
      this.postfixExpression.add(ParsingOperator.getClean(this.operatorStack.pop()));
    }

    if (this.operatorStack.isEmpty() || this.operatorStack.pop() != ParsingOperator.LEFT_PARENTHESIS) {
      throw new MathException("Missing left parenthesis.");
    }
  }

  private void processEndOfFile() {
    while (!this.operatorStack.isEmpty()) {
      this.postfixExpression.add(ParsingOperator.getClean(this.operatorStack.pop()));
    }
  }
}

enum ParsingOperator {
  PLUS(0), MINUS(0), TIMES(1), DIVIDE(1), LEFT_PARENTHESIS(100), RIGHT_PARENTHESIS(100);

  private int value;

  private ParsingOperator(int value) {
    this.value = value;
  }

  public boolean le(ParsingOperator po) {
    return po.value != 100 && this.value <= po.value;
  }

  public static ParsingOperator get(int operator) {
    switch (operator) {
      case Ascii.PLUS_SIGN:
        return PLUS;
      case Ascii.HYPHEN:
        return MINUS;
      case Ascii.ASTERISK:
        return TIMES;
      case Ascii.SLASH:
        return DIVIDE;
      case Ascii.LEFT_PARENTHESIS:
        return LEFT_PARENTHESIS;
      case Ascii.RIGHT_PARENTHESIS:
        return RIGHT_PARENTHESIS;
      default:
        throw new MathException("Unexpected operator: " + String.valueOf(Character.toChars(operator)));
    }
  }

  public static Operator getClean(ParsingOperator operator) {
    switch (operator) {
      case PLUS:
        return Operator.PLUS;
      case MINUS:
        return Operator.MINUS;
      case TIMES:
        return Operator.TIMES;
      case DIVIDE:
        return Operator.DIVIDE;
      default:
        throw new MathException("Unexpected operator: " + operator);
    }
  }
}
