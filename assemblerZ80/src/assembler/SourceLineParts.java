package assembler;

public class SourceLineParts {
	private Integer lineNumber = 0;
	private int opCodeSize = -1;
	private String comment = null;
	private String label = null;
	private String instruction = null;
	private String directive = null;
	private String name = null;
	private String subOpCode = null;
	private String argument1 = null;
	private String argument2 = null;
	private boolean lineAllComment = false;
	private boolean activeLine = false;

	public SourceLineParts() {
	}// Constructor

	public void setLineNumber(Integer lineNumber) {
		if (lineNumber > 0) {
			this.lineNumber = lineNumber;
		} // if
	}// setLineNumber

	public void setOpCodeSize(Integer opCodeSize) {
		if (opCodeSize > 0) {
			this.opCodeSize = opCodeSize;
		} // if
	}// setOpCodeSize

	public void setLineAllComment(boolean state) {
		this.lineAllComment = state;
	}// setLineAllComment

	public void setActiveLine(boolean state) {
		this.activeLine = state;
	}// setActiveLine

	public void setComment(String comment) {
		this.comment = comment;
	}// setComment

	public void setLabel(String label) {
		this.label = label;
	}// setLabel

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}// setInstruction

	public void setDirective(String directive) {
		this.directive = directive;
	}// setDirective

	public void setName(String name) {
		this.name = name;
	}// setName

	public void setSubOpCode(String subOpCode) {
		this.subOpCode = subOpCode;
	}// setSubOpCode

	public void setArgument1(String argument1) {
		this.argument1 = argument1;
	}// setArgument1

	public void setArgument2(String argument2) {
		this.argument2 = argument2;
	}// setArgument2
		// -------------------------

	public boolean hasComment() {
		return comment != null;
	}// hasComment

	public boolean hasLabel() {
		return label != null;
	}// hasLabel

	public boolean hasInstruction() {
		return this.instruction != null;
	}// hasLabel

	public boolean hasDirective() {
		return this.directive != null;
	}// hasDirective

	public boolean hasName() {
		return this.name != null;
	}// hasName

	public boolean hasArguments() {
		return (argument1 != null) | (argument2 != null);
	}// has Arguments

	// -------------------------

	public int getOpCodeSize() {
		return this.opCodeSize;
	}// getOpCodeSize

	public Integer getLineNumber() {
		return this.lineNumber;
	}// getLineNumber

	public String getLineNumberStr() {
		return String.format("%04d", this.lineNumber);
	}// getLineNumberStr

	public boolean isLineAllComment() {
		return this.lineAllComment;
	}// isLineAllComment

	public boolean isLineActive() {
		return activeLine;
	}// isActiveLine

	public String getComment() {
		return hasComment() ? this.comment : EMPTY_STRING;
	}// getComment

	public String getLabel() {
		return hasLabel() ? this.label : EMPTY_STRING;
	}// getLabel

	public String getInstruction() {
		return hasInstruction() ? this.instruction : EMPTY_STRING;
	}// getInstruction

	public String getDirective() {
		return hasDirective() ? this.directive : EMPTY_STRING;
	}// getDirective

	public String getName() {
		return hasName() ? this.name : EMPTY_STRING;
	}// getName

	public String getSubOpCode() {
		return this.subOpCode;
	}// getSubOpCode

	public int getArgumentCount() {
		int count = 0;
		count = argument1 != null ? count + 1 : count;
		count = argument2 != null ? count + 1 : count;
		return count;
	}// getArgumentCount

	public String getArgument1() {
		return argument1;
	}// getArgument1

	public String getArgument2() {
		return argument2;
	}// getArgument2

	public String getArgumentField() {
		String ans;
		switch (getArgumentCount()) {
		case 1:
			ans = getArgument1();
			break;
		case 2:
			ans = getArgument1() + "," + getArgument2();
			break;
		case 0:
		default:
			ans = EMPTY_STRING;
		}//switch
		return ans;
	}// getArgumentField

	// -------------------------

	private static final String EMPTY_STRING = ""; // empty string

}// SourceLineParts
