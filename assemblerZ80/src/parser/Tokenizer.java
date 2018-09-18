package parser;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {
	private LinkedList<TokenInfo> tokenInfos;
	private LinkedList<Token> tokens;

	public Tokenizer() {
		tokenInfos = new LinkedList<TokenInfo>();
		tokens = new LinkedList<Token>();
		appInit();	
	}// Constructor 
	
	private void appInit(){
		tokenInfos.add(new TokenInfo(PATTERN_FUNCTION,TokenType.FUNCTION));
		tokenInfos.add(new TokenInfo(PATTERN_OPEN_PARENTHESES, TokenType.OPEN_PARENTHESES));
		tokenInfos.add(new TokenInfo(PATTERN_CLOSE_PARENTHESES,TokenType.CLOSE_PARENTHESES));
		tokenInfos.add(new TokenInfo(PATTERN_PLUS_MINUS, TokenType.PLUS_MINUS));
		tokenInfos.add(new TokenInfo(PATTERN_MULT_DIV , TokenType.MULT_DIV));
		tokenInfos.add(new TokenInfo(PATTERN_RAISED, TokenType.RAISED));
		tokenInfos.add(new TokenInfo(PATTERN_HEX, TokenType.HEX));
		tokenInfos.add(new TokenInfo(PATTERN_OCTAL,TokenType.OCTAL));
		tokenInfos.add(new TokenInfo(PATTERN_BINARY, TokenType.BINARY));
		tokenInfos.add(new TokenInfo(PATTERN_DECIMAL, TokenType.DECIMAL));
		tokenInfos.add(new TokenInfo(PATTERN_STRING, TokenType.STRING));
		tokenInfos.add(new TokenInfo(PATTERN_LOGIC, TokenType.LOGIC));
		tokenInfos.add(new TokenInfo(PATTERN_VARIABLE, TokenType.VARIABLE));
		tokenInfos.add(new TokenInfo(PATTERN_VARIABLE_$, TokenType.VARIABLE));		//"\\$"
	}//appInit


	public void tokenize(String str){
		String s = new String(str).trim();
		tokens.clear();
		
		while(!s.equals("")){
			boolean match = false;
			for(TokenInfo tokenInfo : tokenInfos){
				Matcher m = tokenInfo.patternToken.matcher(s);
				if (m.lookingAt()){		//m.find()
					match = true;
					
					String tok = m.group().trim();
					tokens.add(new Token(tokenInfo.tokenType,tok));
					s = m.replaceFirst("").trim();
					break;
				}//if -find
			}// for-each info
			if(!match){
				throw new ParserException("Unexpected character in input: " + s);	
			}//if
		}//while
	}//tokenize
	
	public LinkedList<Token> getTokens(){
		return tokens;
	}//getTokens


	// inner classes
	private class TokenInfo {
		public final Pattern patternToken;
		public final TokenType tokenType;
	
		public TokenInfo(String patternToken, TokenType tokenType){
//			this.patternToken = Pattern.compile("^(" + patternToken + ")");
			this.patternToken = Pattern.compile(patternToken);
			this.tokenType = tokenType;
		}//Constructor

	}// class TokenInfo PARENTHeSIS
	
	private static final String PATTERN_FUNCTION = "(?i)(sin|cos|exp|ln|sqrt)(?=\\()";
//	private static final String PATTERN_FUNCTION = "(?i)sin|cos|exp|ln|sqrt";
	private static final String PATTERN_OPEN_PARENTHESES = "\\(";
	private static final String PATTERN_CLOSE_PARENTHESES = "\\)";
	private static final String PATTERN_PLUS_MINUS = "[\\+|-]";
	private static final String PATTERN_MULT_DIV = "[\\*|/]";
	private static final String PATTERN_RAISED = "[\\^]";
	private static final String PATTERN_HEX = "(?i)[0-9][0-9A-F]{0,4}[H]";
	private static final String PATTERN_OCTAL = "(?i)[0-7]+[Q|O]";
	private static final String PATTERN_BINARY = "[0-1]+[B|b]";
	private static final String PATTERN_DECIMAL = "[0-9]{1,4}[D|d]?+";
	private static final String PATTERN_STRING = "('[^']*')|(\"[^\"]*\")";  // paired single or double quotes
	private static final String PATTERN_LOGIC = "(?i)\\bAND\\b|\\bOR\\b";
	private static final String PATTERN_VARIABLE = "^[\\?\\@\\w\\$][\\w\\$]{0,24}";
	private static final String PATTERN_VARIABLE_$ = "\\s\\$\\s";


	

	

}// class Tokenizer