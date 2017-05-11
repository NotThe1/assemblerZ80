package assembler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SourceLineAnalyzer {

	private SourceLineParts sourceLineParts;
	private String originalLine;
	InstructionSet is = InstructionSet.getInstance();
	Pattern patternForInstruction = is.getPatternInstructions();

	public SourceLineAnalyzer() {

	}// Constructor

	public SourceLineParts analyze(String line) {
		sourceLineParts = new SourceLineParts();
		String workingLine = new String(line);
		this.originalLine = new String(line);

		if (workingLine.trim().length() == 0) {
			return sourceLineParts;
		} // exit if the line is empty
		sourceLineParts.setActiveLine(true);

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
		if (workingLine.length() > 0 & sourceLineParts.hasInstruction()) {
			findArguments(workingLine);
			return sourceLineParts; // done
		} // if find args

		if (workingLine.length() > 0) {
			workingLine = findDirective(workingLine);
		} // if

		return sourceLineParts;
	}// analyze

	private String findComment(String workingLine) {
		String netLine = new String(workingLine);
		if (!netLine.contains(";")) {
			// return netLine; /* no comments */
		} // if
		else if (netLine.startsWith(";")) {
			sourceLineParts.setLineAllComment(true);
			sourceLineParts.setComment(netLine);
			netLine = "";
		} else {
			Pattern patternForComment = Pattern.compile("('[^']*')|(;)");
			Matcher matcherForComment = patternForComment.matcher(netLine);
			int startFindLoc = 0;
			while (!matcherForComment.hitEnd()) {
				if (matcherForComment.find(startFindLoc)) {
					if ((matcherForComment.group(1) == null) && (matcherForComment.group(2) != null)) {
						sourceLineParts.setComment(netLine.substring(matcherForComment.end(2) - 1));
						netLine = netLine.substring(0, matcherForComment.end(2) - 1);
						break; // found the comment
					} // if this is it
					startFindLoc = Math.max(matcherForComment.end(1), matcherForComment.end(2));
				} // if any match
			} // while
		} // if

		return netLine.trim();

	}// findComment

	public String findLineNumber(String workingLine) {
		String netLine = new String(workingLine); // .trim()
		Pattern patternForLineNumber = Pattern.compile("^\\d{4}\\s");
		Matcher matcherForLineNumber = patternForLineNumber.matcher(netLine);
		if (matcherForLineNumber.lookingAt()) {
			// this.lineNumberStr = matcherForLineNumber.group().trim();
			sourceLineParts.setLineNumber(Integer.valueOf(matcherForLineNumber.group().trim()));
			netLine = matcherForLineNumber.replaceFirst(EMPTY_STRING);
		} else {
			sourceLineParts.setLineNumber(0);
		} // if
		return netLine.trim();
	}// findLineNumber

	private String findLabel(String workingLine) {
		String netLine = new String(workingLine); // .trim()
		Pattern patternForLabel = Pattern.compile("[\\@\\?A-Za-z]{1}\\w{1,25}:{1}");
		Matcher matcherForLabel = patternForLabel.matcher(netLine);
		if (matcherForLabel.lookingAt()) {
			sourceLineParts.setLabel(matcherForLabel.group());
			netLine = matcherForLabel.replaceFirst(EMPTY_STRING);
		} else {
			sourceLineParts.setLabel(null);
		} // if
		return netLine.trim();
	}// findLabel

	public String findInstruction(String workingLine) {
		String netLine = new String(workingLine);
//		InstructionSet is = InstructionSet.getInstance();
//		Pattern patternForInstruction = is.getPatternInstructions();
		Matcher matcherForInstruction = patternForInstruction.matcher(netLine);
		if (matcherForInstruction.lookingAt()) {
			String instruction = matcherForInstruction.group();
			sourceLineParts.setInstruction(instruction);
			netLine = matcherForInstruction.replaceFirst(EMPTY_STRING);
			String subOpCode = is.findSubCode(instruction, workingLine);
			sourceLineParts.setSubOpCode(subOpCode);
			sourceLineParts.setOpCodeSize(SubInstructionSet.getOpCodeSize(subOpCode));
		} else {
			sourceLineParts.setInstruction(null);
		} // if
		return netLine.trim();
	}// findInstruction

	private String findArguments(String workingLine) {
		String source = workingLine.replaceAll("\\s", EMPTY_STRING);// remove all spaces

		String arguments[] = source.split(",");
		if (source.length() > 0) {
			sourceLineParts.setArgument1(arguments[0]);
			if (arguments.length > 1) {
				sourceLineParts.setArgument2(arguments[1]);
			} // inner if
		} // outer if

		return EMPTY_STRING;
	}// findArguments

	/**
	 * at this part of the line analysis there can be 4 possibilities: 1 only a directive, 2 - a directive and
	 * operand(s), 3 - a name and directive or 4 - name, directive and operand(s)
	 * 
	 * @param workingLine
	 * @return
	 */

	private String findDirective(String workingLine) {
		String netLine = new String(workingLine).trim();

		Pattern patternForDirectives = Pattern.compile(DirectiveSet.getRegex());
		Matcher matcherForDirective = patternForDirectives.matcher(netLine);

		if (matcherForDirective.find()) { // there is a directive
			if (matcherForDirective.start() != 0) { // we have a name to process
				String possibleName = netLine.substring(0, matcherForDirective.start());
				Pattern patternForName = Pattern.compile("[\\@\\?A-Za-z]{1}\\w{1,25}\\s");
				Matcher matcherForName = patternForName.matcher(possibleName);
				String name = matcherForName.lookingAt() ? possibleName.trim() : null;
				sourceLineParts.setName(name);
			} // if there is a name
			sourceLineParts.setDirective(matcherForDirective.group());

			// netLine = netLine.substring(matcherForDirective.end());
			sourceLineParts.setArgument1(netLine.substring(matcherForDirective.end()).trim());
		} // outer if
		return netLine;
	}// findDirective

//	private static final String COMMENT_CHAR = ";"; // semicolon ;
//	private static final String SINGLE_QUOTE = "'"; // single quote '
//	private static final String SPACE = " "; // space
	private static final String EMPTY_STRING = ""; // empty string

}// class LineAttributes
