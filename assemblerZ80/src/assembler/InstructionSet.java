package assembler;

import java.util.HashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InstructionSet {
	private static HashMap<String, OpCodeNode> instructions = new HashMap<String, OpCodeNode>();
	private OpCodeNode root;
	private OpCodeNode[] branch;
	private static Matcher matcher;

	public InstructionSet() {
		appInit();
	}// Constructor

	public static boolean isValid(String opCode) {
		return instructions.containsKey(opCode);
	}// isValid

	public static String getSubCode(String key, String source) {
		OpCodeNode root = instructions.get(key);
		String opCodeVar = BAD_OPCODE;
		String s = new String(source).trim();
		OpCodeNode ocnArg1, ocnArg2;
		while (root.hasNext()) {
			ocnArg1 = root.next();
			matcher = ocnArg1.getPattern().matcher(s);
			if (matcher.lookingAt()) {
				s = matcher.replaceFirst("").trim();
				while (ocnArg1.hasNext()) {
					ocnArg2 = ocnArg1.next();
					matcher = ocnArg2.getPattern().matcher(s);
					if (matcher.find()) {
						opCodeVar = ocnArg2.getOpCodeVariation();
						break;
					} // if match Arg2
				} // while arg1 has args
			} // If matching arg 1
		} // while root has arg
		return opCodeVar;
	}// getSubCode

	public static String getRegex() {
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
	}// appInit

	private OpCodeNode rootADC() {
		root = new OpCodeNode(null, BAD_OPCODE);

		branch = new OpCodeNode[] { new OpCodeNode(patLIT_A, BAD_OPCODE), new OpCodeNode(patIND_XYd, "ADC_1"),
				new OpCodeNode(patR8M, "ADC_2"), new OpCodeNode(patEXP, "ADC_3") };
		root.addBranch(branch);

		branch = new OpCodeNode[] { new OpCodeNode(patLIT_HL, BAD_OPCODE), new OpCodeNode(patR16_SP, "ADC_4") };
		root.addBranch(branch);

		return root;
	}// rootADC

	private OpCodeNode rootADD() {
		root = new OpCodeNode(null, BAD_OPCODE);

		branch = new OpCodeNode[] { new OpCodeNode(patLIT_A, BAD_OPCODE), new OpCodeNode(patIND_XYd, "ADD_1"),
				new OpCodeNode(patR8M, "ADD_2"), new OpCodeNode(patEXP, "ADD_3") };
		root.addBranch(branch);

		branch = new OpCodeNode[] { new OpCodeNode(patLIT_HL, BAD_OPCODE), new OpCodeNode(patR16_SP, "ADD_4") };
		root.addBranch(branch);

		branch = new OpCodeNode[] { new OpCodeNode(patR16_XY, BAD_OPCODE), new OpCodeNode(patR16_IX, "ADD_5") };
		root.addBranch(branch);
		return root;
	}

	private OpCodeNode rootMeta1() {
		root = new OpCodeNode(null, BAD_OPCODE);

		branch = new OpCodeNode[] { new OpCodeNode(patLIT_A, BAD_OPCODE), new OpCodeNode(patIND_XYd, "ADC_1"),
				new OpCodeNode(patR8M, "ADC_2"), new OpCodeNode(patEXP, "ADC_3") };
		root.addBranch(branch);

		branch = new OpCodeNode[] { new OpCodeNode(patLIT_HL, BAD_OPCODE), new OpCodeNode(patR16_SP, "ADC_4") };
		root.addBranch(branch);

		return root;
	}

	// ------------------------------------------------------------------------------------------
	public static final String BAD_OPCODE = "Bad OpCode";

	public static final Pattern patLIT_A = Pattern.compile("A");
	public static final Pattern patLIT_HL = Pattern.compile("HL");
	public static final Pattern patIND_XYd = Pattern.compile("\\(I[X|Y]\\+");
	public static final Pattern patR8M = Pattern.compile("(\\(HL\\))|(\\b[A|B|C|D|E|H|L|M]\\b)");
	public static final Pattern patEXP = Pattern.compile(".");
	public static final Pattern patR16_SP = Pattern.compile("BC|DE|HL|SP");
	public static final Pattern patR16_XY = Pattern.compile("IX|IY");
	public static final Pattern patR16_IX = Pattern.compile("BC|DE|SP|IX");
}// class InstructionSet
