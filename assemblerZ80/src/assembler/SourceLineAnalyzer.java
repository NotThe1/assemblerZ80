package assembler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SourceLineAnalyzer {

	// private Pattern pattern;
	// private Matcher matcher;

	private String line;
	private String lineNumberStr;
	private String comment;
	private String label;
	private String instruction;
	private String subOpCode;
	private String argument1;
	private String argument2;
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
		this.argument1 = null;
		this.argument2 = null;

	}// clearAllVariables

	public boolean isLineActive() {
		return activeLine;
	}//

	public boolean analyze(String line) {
		clearAllVariables();
		activeLine = true;

		this.line = line;
		String workingLine = new String(line);
		if (workingLine.trim().length() == 0) {
			activeLine = false;
			return activeLine;
		} // exit if the line is empty

		workingLine = findLineNumber(workingLine);
		if (workingLine.length() > 0) {
			workingLine = findComment(workingLine);
		} // if
		if (workingLine.length() > 0) {
			workingLine = findLabel(workingLine);
		} // if
		if (workingLine.length() > 0) {
			workingLine = findInstruction(workingLine);
		} // if
		if (workingLine.length() > 0 & this.hasInstruction()) {
			findArguments(workingLine);
			return activeLine; // done
		} // if find args
		
//		if (workingLine.length() > 0) {
//			workingLine = findDirective(workingLine);
//		} // if
//
//		if (workingLine.length() > 0 & this.hasDirecive()) {
//			findArguments(workingLine);
//			// done
//		} // if get args


		return activeLine;
	}// analyze

	private String findComment(String workingLine) {
		this.comment = EMPTY_STRING;
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
		return (comment != EMPTY_STRING);
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
		return netLine.trim();
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
		return netLine.trim();
	}// findLabel

	public boolean hasLabel() {
		return label.length() > 0;
	}// hasLabel

	public String getLabel() {
		return label;
	}// getLabel

	public String findInstruction(String workingLine) {
		String netLine = new String(workingLine);
		InstructionSet is = InstructionSet.getInstance();
		Pattern patternForInstruction = is.getPatternInstructions();
		Matcher matcherForInstruction = patternForInstruction.matcher(netLine);
		if (matcherForInstruction.lookingAt()) {
			this.instruction = matcherForInstruction.group();
			netLine = matcherForInstruction.replaceFirst(EMPTY_STRING);
			this.subOpCode = is.findSubCode(this.instruction, workingLine);
		} else {
			this.instruction = EMPTY_STRING;
		} // if
		return netLine.trim();
	}// findInstruction

	public boolean hasInstruction() {
		return this.instruction != null?true:false;
	}// hasLabel

	public String getInstruction() {
		return this.instruction;
	}// getLabel

	public String getSubOpCode() {
		return this.subOpCode;
	}// getSubOpCode

	private String findArguments(String workingLine) {
		String source = workingLine.replaceAll("\\s", EMPTY_STRING);// remove all spaces
		argument1 = null;
		argument2 = null;

		String arguments[] = source.split(",");
		if (source.length() > 0) {
			argument1 = arguments[0];
			if (arguments.length > 1) {
				argument2 = arguments[1];
			} // inner if
		} // outer if

		return EMPTY_STRING;
	}// findArguments

	public boolean hasArguments() {
		return (argument1 != null) | (argument2 != null);
	}// has Arguments
	
	public int getArgumentCount(){
		int count = 0;
		count = argument1!=null? count+1:count;
		count = argument2!=null? count+1:count;
		return count;
	}//getArgumentCount
	
	public String getArgument1(){
		return argument1;
	}//getArgument1
	
	public String getArgument2(){
		return argument2;
	}//getArgument2

	private static final String COMMENT_CHAR = ";"; // semicolon ;
	private static final String SINGLE_QUOTE = "'"; // single quote '
	private static final String SPACE = " "; // space
	private static final String EMPTY_STRING = ""; // empty string

}// class LineAttributes
