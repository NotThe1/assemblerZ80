package assembler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SourceLineAnalyzer {

	private String originalLine;
	private String lineNumberStr;
	private String comment;
	private String label;
	private String instruction;
	private String directive;
	private String name;
	private String subOpCode;
	private String argument1;
	private String argument2;
	private boolean lineAllComment;
	private boolean activeLine;
	
	private int opCodeSize;

	public SourceLineAnalyzer() {
		appInit();
	}// Constructor

	private void appInit() {
		this.comment = null;
		clearAllVariables();
	}// appInit

	public void clearAllVariables() {
		this.originalLine = null;
		this.lineNumberStr = null;
		this.comment = null;
		this.label = null;
		this.instruction= null;
		this.directive = null;
		this.name=null;
		
		;
		this.activeLine = false;
		this.argument1 = null;
		this.argument2 = null;
		this.opCodeSize = -1;

	}// clearAllVariables

	public boolean isLineActive() {
		return activeLine;
	}//

	public boolean analyze(String line) {
		clearAllVariables();
		activeLine = true;
		String workingLine = new String(line);
		this.originalLine = new String(line);
		
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
		
		if (workingLine.length() > 0) {
			workingLine = findDirective(workingLine);
		} // if

//		if (workingLine.length() > 0 & this.hasDirective()) {
//			findArguments(workingLine);
//			// done
//		} // if get args


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
		return comment != null;
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

	public String getLineNumberStr() {
		return lineNumberStr;
	}// getLineNumber

	public int getLineNumber() {
		return hasLineNumber() ? Integer.valueOf(this.lineNumberStr, 10) : -1;
	}// getLineNumber

	private String findLabel(String workingLine) {
		String netLine = new String(workingLine); // .trim()
		Pattern patternForLabel = Pattern.compile("[\\@\\?A-Za-z]{1}\\w{1,25}:{1}");
		Matcher matcherForLabel = patternForLabel.matcher(netLine);
		if (matcherForLabel.lookingAt()) {
			this.label = matcherForLabel.group();
			netLine = matcherForLabel.replaceFirst(EMPTY_STRING);
		} else {
			this.label = null;
		} // if
		return netLine.trim();
	}// findLabel

	public boolean hasLabel() {
		return label != null;
	}// hasLabel

	public String getLabel() {
		return hasLabel()?this.label:EMPTY_STRING;
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
			this.opCodeSize = SubInstructionSet.getOpCodeSize(subOpCode);
		} else {
			this.instruction = null;
		} // if
		return netLine.trim();
	}// findInstruction

	public boolean hasInstruction() {
		return this.instruction != null?true:false;
	}// hasLabel

	public String getInstruction() {
		return hasInstruction()?this.instruction:EMPTY_STRING;
	}// getLabel

	public String getSubOpCode() {
		return this.subOpCode;
	}// getSubOpCode
	
	public int getOpCodeSize(){
		return this.opCodeSize;
	}//getOpCodeSize

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
	
	
	/**
	 * at this part of the line analysis there can be 4 possibilities:
	 * 1 only a directive, 2 - a directive and  operand(s),
	 *  3 - a name and directive or 4 - name, directive and operand(s)
	 * @param workingLine
	 * @return
	 */
	
	private String findDirective(String workingLine) {
		String netLine = new String(workingLine).trim();
		this.directive = null;
				
		Pattern patternForDirectives = Pattern.compile(DirectiveSet.getRegex());
		Matcher matcherForDirective = patternForDirectives.matcher(netLine);

		if (matcherForDirective.find()) {	// there is a directive
			if (matcherForDirective.start()!= 0){ // we have a name to process
				String possibleName = netLine.substring(0, matcherForDirective.start());
				Pattern patternForName = Pattern.compile("[\\@\\?A-Za-z]{1}\\w{1,25}\\s");
				Matcher matcherForName = patternForName.matcher(possibleName);
				this.name = matcherForName.lookingAt() ? possibleName.trim() : null;
			}// if there is a name
			this.directive = matcherForDirective.group();
			netLine = netLine.substring(matcherForDirective.end());
			
		} // outer if
		return netLine;
	}// findDirective
	
	public boolean hasDirective(){
		return this.directive !=null;
	}//hasDirective
	
	public String getDirective(){
		return hasDirective()?this.directive:EMPTY_STRING;
	}//getDirective
	
	public boolean hasName(){
		return this.name!= null;
	}//hasName
	
	public String getName(){
		return hasName()?this.name:EMPTY_STRING;
	}//getName

	private static final String COMMENT_CHAR = ";"; // semicolon ;
	private static final String SINGLE_QUOTE = "'"; // single quote '
	private static final String SPACE = " "; // space
	private static final String EMPTY_STRING = ""; // empty string

}// class LineAttributes
