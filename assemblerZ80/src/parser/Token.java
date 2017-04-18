package parser;

public class Token {
	public final TokenType tokenType;
	public final String sequence;

	public Token(TokenType tokenType, String sequence) {
		this.tokenType = tokenType;
		this.sequence = sequence;
	}// Constructor - Token(token, sequence)

}// class Token

