package assembler;

public class Directive {
	private String name;
	private boolean nameRequired;
	private int maxNumberOfArguments;
	private boolean passTwo;

	public Directive(String name,boolean nameRequired,int maxNumberOfArguments,boolean passTwo) {
		this.name = name;
		this.nameRequired = nameRequired;
		this.maxNumberOfArguments = maxNumberOfArguments;
		this.passTwo = passTwo;
	}//Constructor - Directive(nameRequired, maxNumberOfArguments, dataType)

	 boolean isNameRequired() {
		return nameRequired;
	}//isNameRequired

	int getMaxNumberOfArguments() {
		return maxNumberOfArguments;
	}//getMaxNumberOfArguments

	boolean doPassTwo() {
		return passTwo;
	}//doPassTwo

	public String getName() {
		return name;
	}//getName

}//class Directive
