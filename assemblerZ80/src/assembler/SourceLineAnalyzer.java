package assembler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SourceLineAnalyzer {

	private Pattern pattern;
	private Matcher matcher;

	private String line;
	private String lineNumberStr;
	private String comment;
	private String label;
	private String instruction;
	private String subOpCode;
	private boolean lineAllComment;
	private boolean activeLine;

	public SourceLineAnalyzer() {
		appInit();
	}// Constructor

	private void appInit() {
		this.comment = null;
		clearAllVariables();
	}// appInit

	public void clearAllVariables() {
		this.line = EMPTY_STRING;
		this.lineNumberStr = EMPTY_STRING;
		this.comment = EMPTY_STRING;
		this.label = EMPTY_STRING;
		;
		this.activeLine = false;
	}// clearAllVariables

	public boolean isLineActive() {
		return activeLine;
	}//

	public boolean analyze(String line) {
		clearAllVariables();
		String workingLine = new String(line);
		if (workingLine.trim().length() == 0) {
			activeLine = false;
			return activeLine;
		} else {
			activeLine = true;
			workingLine = findLineNumber(workingLine);
			workingLine = findComment(workingLine);
			workingLine = findLabel(workingLine);
			workingLine = findInstruction( workingLine);
		} // if
		return activeLine;
	}// analyze

	private String findComment(String workingLine) {
		this.comment = null;
		this.lineAllComment = false;
		String netLine = new String(workingLine);
		if (!netLine.contains(";")) {
			// return netLine; /* no comments */
		} // if
		else if (netLine.startsWith(";")) {
			this.lineAllComment = true;
			comment = netLine;
			netLine = "";
		} else {
			Pattern patternForComment = Pattern.compile("('[^']*')|(;)");
			Matcher matcherForComment = patternForComment.matcher(netLine);
			int startFindLoc = 0;
			while (!matcherForComment.hitEnd()) {
				if (matcherForComment.find(startFindLoc)) {
					if ((matcherForComment.group(1) == null) && (matcherForComment.group(2) != null)) {
						comment = netLine.substring(matcherForComment.end(2) - 1);
						netLine = netLine.substring(0, matcherForComment.end(2) - 1);
						break; // found the comment
					} // if this is it
					startFindLoc = Math.max(matcherForComment.end(1), matcherForComment.end(2));
				} // if any match
			} // while
		} // if

		return netLine.trim();

	}// findComment

	public boolean isLineAllComment() {
		return lineAllComment;
	}// isLineAllComment

	public boolean hasComment() {
		return (comment != null);
	}// hasComment

	public String getComment() {
		return comment;
	}// getComment

	public String findLineNumber(String workingLine) {
		String netLine = new String(workingLine); // .trim()
		Pattern patternForLineNumber = Pattern.compile("^\\d{4}\\s");
		Matcher matcherForLineNumber = patternForLineNumber.matcher(netLine);
		if (matcherForLineNumber.lookingAt()) {
			this.lineNumberStr = matcherForLineNumber.group().trim();
			netLine = matcherForLineNumber.replaceFirst(EMPTY_STRING);
		} else {
			this.lineNumberStr = EMPTY_STRING;
		} // if
		return netLine;
	}// findLineNumber

	public boolean hasLineNumber() {
		return lineNumberStr.length() > 0;
	}// hasLineNumber

	public String getLineNumber() {
		return lineNumberStr;
	}// getLineNumber

	private String findLabel(String workingLine) {
		String netLine = new String(workingLine); // .trim()
		Pattern patternForLabel = Pattern.compile("[\\@\\?A-Za-z]{1}\\w{1,25}:{1}");
		Matcher matcherForLabel = patternForLabel.matcher(netLine);
		if (matcherForLabel.lookingAt()) {
			this.label = matcherForLabel.group();
			netLine = matcherForLabel.replaceFirst(EMPTY_STRING);
		} else {
			this.label = EMPTY_STRING;
		} // if
		return netLine;
	}// findLabel

	public boolean hasLabel() {
		return label.length() > 0;
	}// hasLabel

	public String getLabel() {
		return label;
	}// getLabel

	public String findInstruction(String workingLine) {
		String netLine = new String(workingLine).trim();
		InstructionSet is = new InstructionSet();
		Pattern patternForInstruction = Pattern.compile(InstructionSet.getRegex());
		Matcher matcherForInstruction = patternForInstruction.matcher(netLine);
		if (matcherForInstruction.lookingAt()) {
			this.instruction = matcherForInstruction.group();
			netLine = matcherForInstruction.replaceFirst(EMPTY_STRING);
			this.subOpCode = is.getSubCode(this.instruction, netLine);
		} else {
			this.instruction = EMPTY_STRING;
		}//if
		return netLine;
	}// findInstruction
	
	public boolean hasInstruction() {
		return this.instruction.length() > 0;
	}// hasLabel

	public String getInstruction() {
		return this.instruction;
	}// getLabel
	
	public String getSubOpCode(){
		return this.subOpCode;
	}//getSubOpCode
	
	

	private static final String COMMENT_CHAR = ";"; // semicolon ;
	private static final String SINGLE_QUOTE = "'"; // single quote '
	private static final String SPACE = " "; // space
	private static final String EMPTY_STRING = ""; // empty string

}// class LineAttributes
