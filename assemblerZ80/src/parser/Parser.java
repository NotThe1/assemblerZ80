package parser;

import java.util.LinkedList;

public class Parser {
	LinkedList<Token> tokens;
	Token lookAhead;

	public void parse(LinkedList<Token> tokens) {
		this.tokens = (LinkedList<Token>) tokens.clone();
		lookAhead = this.tokens.getFirst(); // throws no such element if list is empty

		expression();

		if (lookAhead.tokenType != TokenType.EPSILON) {
			throw new ParserException(String.format("Unexpected symbol %s found", lookAhead));
		}
	}// parse

	private void nextToken() {
		tokens.pop();
		if (tokens.isEmpty()) {
			lookAhead = new Token(TokenType.EPSILON, "");
		} else {
			lookAhead = tokens.getFirst();
		} // if
	}// nextToken

	private void expression() {
		// expression -> signed_term sum_op
		signedTerm();
		sumOp();
	}// expression

	private void signedTerm() {
		if (lookAhead.tokenType == TokenType.PLUS_MINUS) {
			// signed_term -> PLUS_MINUS term
			nextToken();
			term();
		} else {
			// signed_term -> term
			term();
		} // if
	}// signedTerm

	private void sumOp() {
		if (lookAhead.tokenType == TokenType.PLUS_MINUS) {
			// sum_op -> PLUS_MINUS term sum_op
			nextToken();
			term();
			sumOp();
		} else {
			// sum_op -> EPSILON
		} // if

	}// sumOp

	private void term() {
		// term -> factor term_op
		factor();
		termOp();
	}// term

	private void termOp() {
		if (lookAhead.tokenType == TokenType.MULT_DIV) {
			// term_op -> MULT_DIV factor term_op
			nextToken();
			signedFactor();
			termOp();
		} else {
			// term_op -> EPSILON
		} // if
	}// termOp

	private void factor() {
		// factor -> argument factor_op
		argument();
		factorOp();
	}// factor

	private void signedFactor() {
		if (lookAhead.tokenType == TokenType.PLUS_MINUS) {
			// signedFactor - >PLUS_MINUS factor
			nextToken();
			factor();
		} else {
			// signedFactor -> factor
			factor();
		} // if
	}// signedFactor

	private void factorOp() {
		if (lookAhead.tokenType == TokenType.RAISED) {
			// factorOp -> RAISED expression
			nextToken();
			signedFactor();
		} else {
			// factorOp -> EPSILON
		} // if
	}// factorOp

	private void argument() {
		if (lookAhead.tokenType == TokenType.FUNCTION) {
			// argument -> FUNCTION argument
			nextToken();
			argument();
		} else if (lookAhead.tokenType == TokenType.OPEN_PARENTHESES) {
			// argument -> OPEN_PARENTHESES sum CLOSE_PARENTHESES
			nextToken();
			expression();
			if (lookAhead.tokenType != TokenType.CLOSE_PARENTHESES) {
				throw new ParserException("Closing parentheses expected and " + lookAhead.sequence + " found instead");
			} // inner if

			nextToken();
		} else {
			// argument -> value
			value();
		} // if
	}// argument

	private void value() {
		switch (lookAhead.tokenType) {
		case BINARY:
		case OCTAL:
		case DECIMAL:
		case HEX:
			// argument -> number
			nextToken();
			break;
		case VARIABLE:
			// argument -> VARIABLE
			nextToken();
		default:
			throw new ParserException("Unexpected symbol " + lookAhead.sequence + " found");
		}// switch
	}// value

}// class Parser
