package assembler;

import java.util.HashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InstructionSet {

	private static InstructionSet instance = new InstructionSet();

	private HashMap<String, OpCodeNode> instructions = new HashMap<String, OpCodeNode>();
	private OpCodeNode root;
	private OpCodeNode[] branch;
	private Matcher matcher;
	private Pattern patternInstructions;

	public static InstructionSet getInstance() {
		return instance;
	}// getInstance

	private InstructionSet() {
		appInit();
	}// Constructor

	public boolean isValid(String opCode) {
		return instructions.containsKey(opCode);
	}// isValid

	public Pattern getPatternInstructions() {
		return this.patternInstructions;
	}// getPatternInstructions

	public String getSubCode(String source) {
		String opCodeVar = null;
		matcher = this.patternInstructions.matcher(source);
		if (matcher.find()) {
			opCodeVar = getSubCode(matcher.group(), source);
		}
		return opCodeVar;
	}// getSubCode

	public String getSubCode(String key, String source) {
		String opCodeVar = null;
		OpCodeNode root = instructions.get(key);
		opCodeVar = getSubCode(root, source);

		return opCodeVar;
	}// getSubCode

	private String getSubCode(OpCodeNode node, String source) {
		String ans = null;

		matcher = node.getPattern().matcher(source);

		if (!matcher.find()) {
			return ans;
		} // if not interested in this node!

		if (node.hasChildren()) {
			node.resetIterator();
			while (node.hasNext()) {
				source = matcher.replaceFirst(EMPTY_STRING);
				ans = getSubCode(node.next(), source.trim());
				if (ans != null) {
					break;
				} // if - found it
			} // while
		} else { // no children - this is the one we want !!
			ans = node.getOpCodeVariation();
		} // inner if
		return ans;

	}// getSubCode

	private String getRegex() {
		StringBuilder sb = new StringBuilder("\\b(?i)(");
		Set<String> operations = instructions.keySet();
		for (String operation : operations) {
			sb.append(operation);
			sb.append("|");
		} // for
		sb.deleteCharAt(sb.length() - 1);
		sb.append(")\\b");
		// System.out.printf("[InstructionSet.getRegex] sb: %s%n%n", sb.toString());
		;
		return sb.toString();
	}// getRegex

	// -----------------------------------------------------------------
	private void appInit() {

		instructions.put("ADC", rootADC()); // A <- A + s + CY
		instructions.put("ADD", rootADD()); // A <- A + s
		instructions.put("AND", rootAND()); // A <- A & s

		instructions.put("BIT", rootBIT()); // Z <- (IX+d)b

		instructions.put("CALL", rootCALL()); // (SP-1) <- PCH, (SP-2) <- PCL, PC <- nn
		instructions.put("CCF", rootCCF()); // CY <- CY
		instructions.put("CP", rootCP()); // A - s

		instructions.put("DEC", rootDEC()); // s <- s- 1
		instructions.put("DJNZ", rootDJNZ()); // special jump

		instructions.put("EX", rootEX()); // RR<->RR'

		instructions.put("IM", rootIM());
		instructions.put("IN", rootIN());
		instructions.put("INC", rootINC());

		instructions.put("JP", rootJP());
		instructions.put("JR", rootJR());

		patternInstructions = Pattern.compile(getRegex());

	}// appInit

	private OpCodeNode rootADC() {
		root = new OpCodeNode(patWord, BAD_OPCODE);

		branch = new OpCodeNode[] { new OpCodeNode(patLIT_A, BAD_OPCODE), new OpCodeNode(patIND_XYd, "ADC_1"),
				new OpCodeNode(patR8M, "ADC_2"), new OpCodeNode(patEXP, "ADC_3") };
		root.addBranch(branch);

		branch = new OpCodeNode[] { new OpCodeNode(patLIT_HL, BAD_OPCODE), new OpCodeNode(patR16_SP, "ADC_4") };
		root.addBranch(branch);

		return root;
	}// rootADC

	private OpCodeNode rootADD() {
		root = new OpCodeNode(patWord, BAD_OPCODE);

		branch = new OpCodeNode[] { new OpCodeNode(patLIT_A, BAD_OPCODE), new OpCodeNode(patIND_XYd, "ADD_1"),
				new OpCodeNode(patR8M, "ADD_2"), new OpCodeNode(patEXP, "ADD_3") };
		root.addBranch(branch);

		branch = new OpCodeNode[] { new OpCodeNode(patLIT_HL, BAD_OPCODE), new OpCodeNode(patR16_SP, "ADD_4") };
		root.addBranch(branch);

		branch = new OpCodeNode[] { new OpCodeNode(patR16_XY, BAD_OPCODE), new OpCodeNode(patR16_IX, "ADD_5") };
		root.addBranch(branch);
		return root;
	}// rootADD

	private OpCodeNode rootAND() {
		root = new OpCodeNode(patWord, BAD_OPCODE);
		branch = new OpCodeNode[] { new OpCodeNode(patIND_XYd, "AND_1") };
		root.addBranch(branch);
		branch = new OpCodeNode[] { new OpCodeNode(patR8M, "AND_2") };
		root.addBranch(branch);
		branch = new OpCodeNode[] { new OpCodeNode(patEXP, "AND_3") };
		root.addBranch(branch);
		return root;
	}// rootAND

	private OpCodeNode rootBIT() {
		root = new OpCodeNode(patWord, BAD_OPCODE);
		branch = new OpCodeNode[] { new OpCodeNode(patEXP, BAD_OPCODE), new OpCodeNode(patIND_XYd, "BIT_1"),
				new OpCodeNode(patR8M, "BIT_2") };
		root.addBranch(branch);
		return root;
	}// rootBIT

	private OpCodeNode rootCALL() {
		root = new OpCodeNode(patWord, BAD_OPCODE);
		branch = new OpCodeNode[] { new OpCodeNode(patCOND, BAD_OPCODE), new OpCodeNode(patEXP, "CALL_1") };
		root.addBranch(branch);
		branch = new OpCodeNode[] { new OpCodeNode(patEXP, "CALL_2") };
		root.addBranch(branch);
		return root;
	}// rootCALL

	private OpCodeNode rootCCF() {
		root = new OpCodeNode(patWord, "CCF_0");
		return root;
	}// rootCCF

	private OpCodeNode rootCP() {
		root = new OpCodeNode(patWord, BAD_OPCODE);
		branch = new OpCodeNode[] { new OpCodeNode(patIND_XYd, "CP_1") };
		root.addBranch(branch);
		branch = new OpCodeNode[] { new OpCodeNode(patR8M, "CP_2") };
		root.addBranch(branch);
		branch = new OpCodeNode[] { new OpCodeNode(patEXP, "CP_3") };
		root.addBranch(branch);
		return root;
	}// rootCP

	private OpCodeNode rootDEC() {
		root = new OpCodeNode(patWord, BAD_OPCODE);
		branch = new OpCodeNode[] { new OpCodeNode(patIND_XYd, "DEC_1") };
		root.addBranch(branch);
		branch = new OpCodeNode[] { new OpCodeNode(patR8M, "DEC_2") };
		root.addBranch(branch);
		branch = new OpCodeNode[] { new OpCodeNode(patR16_SP, "DEC_3") };
		root.addBranch(branch);
		branch = new OpCodeNode[] { new OpCodeNode(patR16_XY, "DEC_4") };
		root.addBranch(branch);
		return root;
	}// rootDEC

	private OpCodeNode rootDJNZ() {
		root = new OpCodeNode(patWord, BAD_OPCODE);
		branch = new OpCodeNode[] { new OpCodeNode(patEXP, "DJNZ_1") };
		root.addBranch(branch);
		return root;
	}// rootDJNZ

	private OpCodeNode rootEX() {
		root = new OpCodeNode(patWord, BAD_OPCODE);
		branch = new OpCodeNode[] { new OpCodeNode(patIND_SP, BAD_OPCODE), new OpCodeNode(patLIT_HL, "EX_1"),
				new OpCodeNode(patR16_XY, "EX_2") };
		root.addBranch(branch);
		branch = new OpCodeNode[] { new OpCodeNode(patLIT_AF, BAD_OPCODE), new OpCodeNode(patLIT_AFP, "EX_3") };
		root.addBranch(branch);
		branch = new OpCodeNode[] { new OpCodeNode(patLIT_DE, BAD_OPCODE), new OpCodeNode(patLIT_HL, "EX_4") };
		root.addBranch(branch);

		return root;
	}// rootEX

	private OpCodeNode rootIM() {
		root = new OpCodeNode(patWord, BAD_OPCODE);
		branch = new OpCodeNode[] { new OpCodeNode(patEXP, "IM_1") };
		root.addBranch(branch);
		return root;
	}// rootIM

	private OpCodeNode rootIN() {
		root = new OpCodeNode(patWord, BAD_OPCODE);
		branch = new OpCodeNode[] { new OpCodeNode(patLIT_A, BAD_OPCODE), new OpCodeNode(patIND_C, "IN_1") };
		root.addBranch(branch);
		branch = new OpCodeNode[] { new OpCodeNode(patR8, BAD_OPCODE), new OpCodeNode(patEXP, "IN_2") };
		root.addBranch(branch);
		return root;
	}// rootIN

	private OpCodeNode rootINC() {
		root = new OpCodeNode(patWord, BAD_OPCODE);
		branch = new OpCodeNode[] { new OpCodeNode(patIND_XYd, "INC_1") };
		root.addBranch(branch);
		branch = new OpCodeNode[] { new OpCodeNode(patR8M, "INC_2") };
		root.addBranch(branch);
		branch = new OpCodeNode[] { new OpCodeNode(patR16_SP, "INC_3") };
		root.addBranch(branch);
		branch = new OpCodeNode[] { new OpCodeNode(patR16_XY, "INC_4") };
		root.addBranch(branch);
		return root;
	}// rootINC

	private OpCodeNode rootJP() {
		root = new OpCodeNode(patWord, BAD_OPCODE);
		branch = new OpCodeNode[] { new OpCodeNode(patCOND, BAD_OPCODE), new OpCodeNode(patEXP, "JP_1") };
		root.addBranch(branch);
		branch = new OpCodeNode[] { new OpCodeNode(patIND_XY, "JP_2") };
		root.addBranch(branch);
		branch = new OpCodeNode[] { new OpCodeNode(patIND_HL, "JP_3") };
		root.addBranch(branch);
		branch = new OpCodeNode[] { new OpCodeNode(patEXP, "JP_4") };
		root.addBranch(branch);

		return root;
	}// rootJP

	private OpCodeNode rootJR() {
		root = new OpCodeNode(patWord, BAD_OPCODE);
		branch = new OpCodeNode[] { new OpCodeNode(patCOND1, BAD_OPCODE), new OpCodeNode(patEXP, "JR_1") };
		root.addBranch(branch);

		branch = new OpCodeNode[] { new OpCodeNode(patEXP, "JR_2") };
		root.addBranch(branch);

		return root;
	}// rootJR

	// ------------------------------------------------------------------------------------------
	public static final String BAD_OPCODE = "Bad OpCode";
	private static final String EMPTY_STRING = "";

	public final Pattern patR8M = Pattern.compile("(\\(HL\\))|(\\b[A|B|C|D|E|H|L|M]\\b)");
	public final Pattern patR8 = Pattern.compile("\\b[A|B|C|D|E|H|L]\\b");
	public final Pattern patR16_BCDE = Pattern.compile("BC|DE");
	public final Pattern patR16_SP = Pattern.compile("BC|DE|HL|SP");
	public final Pattern patR16_XY = Pattern.compile("IX|IY");
	public final Pattern patR16_IX = Pattern.compile("BC|DE|SP|IX");
	
	public final Pattern patLIT_A = Pattern.compile("A");
	public final Pattern patLIT_AF = Pattern.compile("AF");
	public final Pattern patLIT_DE = Pattern.compile("DE");
	public final Pattern patLIT_AFP = Pattern.compile("AF\\'");
	public final Pattern patLIT_HL = Pattern.compile("HL");
	
	public final Pattern patIND_HL = Pattern.compile("\\(HL\\)");
	public final Pattern patIND_XY = Pattern.compile("\\(XY\\)");
	public final Pattern patIND_XYd = Pattern.compile("\\(I[X|Y]\\+");
	public final Pattern patIND_SP = Pattern.compile("\\(SP\\)");
	public final Pattern patIND_C = Pattern.compile("\\(C\\)");
	
	public final Pattern patCOND = Pattern.compile("C|M|NC|NZ|P|PE|PO|Z");
	public final Pattern patCOND1 = Pattern.compile("C|NC|NZ|Z");
	
	public final Pattern patWord = Pattern.compile("\\b\\w*\\b");
	public final Pattern patEXP = Pattern.compile(".");

}// class InstructionSet
