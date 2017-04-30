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

//		if (!matcher.find()) {
			if (!matcher.lookingAt()) {
			return ans;
		} // if not interested in this node!

		if (node.hasChildren()) {
			
			source = matcher.replaceFirst(EMPTY_STRING);	// remove matched substring
			source = source.replaceAll("\\s", EMPTY_STRING);//  remove all spaces
		
			
			node.resetIterator();
			
			while (node.hasNext()) {
				
//				source = matcher.replaceFirst(EMPTY_STRING);	// remove matched substring
//				source = source.replaceAll("\\s", EMPTY_STRING);//  remove all spaces

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
		// instructions.put("CCF", rootCCF()); // CY <- CY
		instructions.put("CP", rootCP()); // A - s

		instructions.put("DEC", rootDEC()); // s <- s- 1
		instructions.put("DJNZ", rootDJNZ()); // special jump

		instructions.put("EX", rootEX()); // RR<->RR'

		instructions.put("IM", rootIM());
		instructions.put("IN", rootIN());
		instructions.put("INC", rootINC());

		instructions.put("JP", rootJP());
		instructions.put("JR", rootJR());

		instructions.put("LD", rootLD());

		instructions.put("OR", rootOR());
		instructions.put("OUT", rootOUT());

		instructions.put("POP", rootPOP());
		instructions.put("PUSH", rootPUSH());

		instructions.put("RES", rootRES());
		instructions.put("RET", rootRET());
		instructions.put("RL", rootRL());
		instructions.put("RLC", rootRLC());
		instructions.put("RR", rootRR());
		instructions.put("RRC", rootRRC());
		instructions.put("RST", rootRST());

		instructions.put("SBC", rootSBC());
		instructions.put("SET", rootSET());
		instructions.put("SLA", rootSLA());
		instructions.put("SRA", rootSRA());
		instructions.put("SRL", rootSRL());
		instructions.put("SUB", rootSUB());

		instructions.put("XOR", rootXOR());

		addNoArgInstructions();

		patternInstructions = Pattern.compile(getRegex());

	}// appInit

	private void addNoArgInstructions() {
		String ins;
		String[] insSet = new String[] { "CCF", "CPD", "CPDR", "CPI", "CPIR", "CPL", "DAA", "DI", "EI", "EXX", "HLT",
				"IND", "INDR", "INI", "INIR", "LDD", "LDDR", "LDI", "LDIR", "NEG", "NOP", "OTDR", "OTIR", "OUTD",
				"OUTI", "RETI", "RETN", "RLA", "RLCA", "RLD", "RRA", "RRCA", "RRD", "SCF" };
		for (int i = 0; i < insSet.length; i++) {
			ins = insSet[i];
			instructions.put(ins, rootNoArgs(ins));
		} // for

	}// addNoArgInstructions

	private OpCodeNode rootNoArgs(String instruction) {
		root = new OpCodeNode(patWord, instruction + "_0");
		return root;
	}// rootCCF

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
		branch = new OpCodeNode[] { new OpCodeNode(patEXP1, BAD_OPCODE), new OpCodeNode(patIND_XYd, "BIT_1"),
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
		branch = new OpCodeNode[] { new OpCodeNode(patR16_SP, "DEC_2") };
		root.addBranch(branch);
		branch = new OpCodeNode[] { new OpCodeNode(patR16_XY, "DEC_3") };
		root.addBranch(branch);
		branch = new OpCodeNode[] { new OpCodeNode(patR8M, "DEC_4") };
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
		branch = new OpCodeNode[] { new OpCodeNode(patLIT_AF, "EX_3") };
		root.addBranch(branch);
		branch = new OpCodeNode[] { new OpCodeNode(patLIT_DE, "EX_4") };
		root.addBranch(branch);

		return root;
	}// rootEX
	
	private OpCodeNode rootLD(){
		root = new OpCodeNode(patWord, BAD_OPCODE);
		
		branch = new OpCodeNode[] { new OpCodeNode(patLIT_A, BAD_OPCODE),
				new OpCodeNode(patIND_XYd, "LD_1"),
				new OpCodeNode(patIND_BCDE, "LD_2"),
				new OpCodeNode(patR8_RI, "LD_3"),
				new OpCodeNode(patR8M, "LD_4"),
				new OpCodeNode(patEXP_ADD, "LD_5"),
				new OpCodeNode(patEXP, "LD_6")};
		root.addBranch(branch);
		
		branch = new OpCodeNode[] { new OpCodeNode(patLIT_HL, BAD_OPCODE),
				new OpCodeNode(patEXP_ADD, "LD_7")};
		root.addBranch(branch);
		
		branch = new OpCodeNode[] { new OpCodeNode(patLIT_SP, BAD_OPCODE),
				new OpCodeNode(patLIT_HL, "LD_8"),
				new OpCodeNode(patR16_XY, "LD_9"),
				new OpCodeNode(patEXP_ADD, "LD_10"),
				new OpCodeNode(patEXP, "LD_11")};
		root.addBranch(branch);
		
		branch = new OpCodeNode[] { new OpCodeNode(patIND_BCDE, "LD_12")};
		root.addBranch(branch);
		
		branch = new OpCodeNode[] { new OpCodeNode(patIND_XYd1, BAD_OPCODE),
				new OpCodeNode(patR8, "LD_13"),
				new OpCodeNode(patEXP, "LD_14")};
		root.addBranch(branch);

		branch = new OpCodeNode[] { new OpCodeNode(patR8_RI, "LD_15")};
		root.addBranch(branch);
		
		branch = new OpCodeNode[] { new OpCodeNode(patR16_SP, BAD_OPCODE),
				new OpCodeNode(patEXP_ADD, "LD_16"),
				new OpCodeNode(patEXP, "LD_17")};
		root.addBranch(branch);

		branch = new OpCodeNode[] { new OpCodeNode(patR16_XY, BAD_OPCODE),
				new OpCodeNode(patEXP_ADD, "LD_18"),
				new OpCodeNode(patEXP, "LD_19")};
		root.addBranch(branch);

		branch = new OpCodeNode[] { new OpCodeNode(patR81, BAD_OPCODE),
				new OpCodeNode(patR8M, "LD_20"),
				new OpCodeNode(patIND_XYd, "LD_21"),
				new OpCodeNode(patEXP, "LD_22")};
		root.addBranch(branch);

	
//		branch = new OpCodeNode[] { new OpCodeNode(patR8M, BAD_OPCODE), new OpCodeNode(patR8M, "LD_4")
//				, new OpCodeNode(patEXP, "LD_5")};
//		root.addBranch(branch);
//	
//		

		branch = new OpCodeNode[] { new OpCodeNode(patIND_HL, BAD_OPCODE), new OpCodeNode(patR8M, "LD_23"),
				new OpCodeNode(patEXP, "LD_24")};
		root.addBranch(branch);



		return root;
	}//

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
		branch = new OpCodeNode[] { new OpCodeNode(patR16_SP, "INC_2") };
		root.addBranch(branch);
		branch = new OpCodeNode[] { new OpCodeNode(patR16_XY, "INC_3") };
		root.addBranch(branch);
		branch = new OpCodeNode[] { new OpCodeNode(patR8M, "INC_4") };
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
		branch = new OpCodeNode[] { new OpCodeNode(patCONDs, BAD_OPCODE), new OpCodeNode(patEXP, "JR_1") };
		root.addBranch(branch);

		branch = new OpCodeNode[] { new OpCodeNode(patEXP, "JR_2") };
		root.addBranch(branch);

		return root;
	}// rootJR

	// ------------------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------

	private OpCodeNode rootOR() {
		root = new OpCodeNode(patWord, BAD_OPCODE);
		branch = new OpCodeNode[] { new OpCodeNode(patIND_XYd, "OR_1") };
		root.addBranch(branch);
		branch = new OpCodeNode[] { new OpCodeNode(patR8M, "OR_2") };
		root.addBranch(branch);
		branch = new OpCodeNode[] { new OpCodeNode(patEXP, "OR_3") };
		root.addBranch(branch);
		return root;
	}// rootOR

	private OpCodeNode rootOUT() {
		root = new OpCodeNode(patWord, BAD_OPCODE);
		branch = new OpCodeNode[] { new OpCodeNode(patIND_C, "OUT_1") };
		root.addBranch(branch);
		branch = new OpCodeNode[] { new OpCodeNode(patEXP_ADD, "OUT_2") };
		root.addBranch(branch);
		return root;
	}// rootOUT

	private OpCodeNode rootPOP() {
		root = new OpCodeNode(patWord, BAD_OPCODE);
		branch = new OpCodeNode[] { new OpCodeNode(patR16_AF, "POP_1") };
		root.addBranch(branch);
		branch = new OpCodeNode[] { new OpCodeNode(patR16_XY, "POP_2") };
		root.addBranch(branch);
		return root;
	}// rootPOP

	private OpCodeNode rootPUSH() {
		root = new OpCodeNode(patWord, BAD_OPCODE);
		branch = new OpCodeNode[] { new OpCodeNode(patR16_AF, "PUSH_1") };
		root.addBranch(branch);
		branch = new OpCodeNode[] { new OpCodeNode(patR16_XY, "PUSH_2") };
		root.addBranch(branch);
		return root;
	}// rootPUSH

	private OpCodeNode rootRES() {
		root = new OpCodeNode(patWord, BAD_OPCODE);
		branch = new OpCodeNode[] { new OpCodeNode(patEXP1, BAD_OPCODE), new OpCodeNode(patIND_XYd, "RES_1"),
				new OpCodeNode(patR8M, "RES_2") };
		root.addBranch(branch);

		return root;
	}// rootRES

	private OpCodeNode rootRET() {
		root = new OpCodeNode(patWord, BAD_OPCODE);
		branch = new OpCodeNode[] { new OpCodeNode(patCOND, "RET_1") };
		root.addBranch(branch);
		branch = new OpCodeNode[] { new OpCodeNode(patNothing, "RET_2") };
		root.addBranch(branch);

		return root;
	}// rootRET

	private OpCodeNode rootRL() {
		root = new OpCodeNode(patWord, BAD_OPCODE);
		branch = new OpCodeNode[] { new OpCodeNode(patIND_XYd, "RL_1") };
		root.addBranch(branch);
		branch = new OpCodeNode[] { new OpCodeNode(patR8M, "RL_2") };
		root.addBranch(branch);

		return root;
	}// rootRL

	private OpCodeNode rootRLC() {
		root = new OpCodeNode(patWord, BAD_OPCODE);
		branch = new OpCodeNode[] { new OpCodeNode(patIND_XYd, "RLC_1") };
		root.addBranch(branch);
		branch = new OpCodeNode[] { new OpCodeNode(patR8M, "RLC_2") };
		root.addBranch(branch);

		return root;
	}// rootRLC

	private OpCodeNode rootRR() {
		root = new OpCodeNode(patWord, BAD_OPCODE);
		branch = new OpCodeNode[] { new OpCodeNode(patIND_XYd, "RR_1") };
		root.addBranch(branch);
		branch = new OpCodeNode[] { new OpCodeNode(patR8M, "RR_2") };
		root.addBranch(branch);

		return root;
	}// rootRR

	private OpCodeNode rootRRC() {
		root = new OpCodeNode(patWord, BAD_OPCODE);
		branch = new OpCodeNode[] { new OpCodeNode(patIND_XYd, "RRC_1") };
		root.addBranch(branch);
		branch = new OpCodeNode[] { new OpCodeNode(patR8M, "RRC_2") };
		root.addBranch(branch);

		return root;
	}// rootRRC

	private OpCodeNode rootRST() {
		root = new OpCodeNode(patWord, BAD_OPCODE);
		branch = new OpCodeNode[] { new OpCodeNode(patEXP, "RST_1") };
		root.addBranch(branch);

		return root;
	}// rootRST

	private OpCodeNode rootSBC() {
		root = new OpCodeNode(patWord, BAD_OPCODE);

		branch = new OpCodeNode[] { new OpCodeNode(patLIT_A, BAD_OPCODE), new OpCodeNode(patIND_XYd, "SBC_1"),
				new OpCodeNode(patR8M, "SBC_2"), new OpCodeNode(patEXP, "SBC_3") };
		root.addBranch(branch);

		branch = new OpCodeNode[] { new OpCodeNode(patLIT_HL, BAD_OPCODE), new OpCodeNode(patR16_SP, "SBC_4") };
		root.addBranch(branch);

		return root;
	}// rootSBC

	private OpCodeNode rootSET() {
		root = new OpCodeNode(patWord, BAD_OPCODE);
		branch = new OpCodeNode[] { new OpCodeNode(patEXP1, BAD_OPCODE), new OpCodeNode(patIND_XYd, "SET_1"),
				new OpCodeNode(patR8M, "SET_2") };
		root.addBranch(branch);

		return root;
	}// rootSET

	private OpCodeNode rootSLA() {
		root = new OpCodeNode(patWord, BAD_OPCODE);
		branch = new OpCodeNode[] { new OpCodeNode(patIND_XYd, "SLA_1") };
		root.addBranch(branch);
		branch = new OpCodeNode[] { new OpCodeNode(patR8M, "SLA_2") };
		root.addBranch(branch);

		return root;
	}// rootSLA

	private OpCodeNode rootSRA() {
		root = new OpCodeNode(patWord, BAD_OPCODE);
		branch = new OpCodeNode[] { new OpCodeNode(patIND_XYd, "SRA_1") };
		root.addBranch(branch);
		branch = new OpCodeNode[] { new OpCodeNode(patR8M, "SRA_2") };
		root.addBranch(branch);

		return root;
	}// rootSRA

	private OpCodeNode rootSRL() {
		root = new OpCodeNode(patWord, BAD_OPCODE);
		branch = new OpCodeNode[] { new OpCodeNode(patIND_XYd, "SRL_1") };
		root.addBranch(branch);
		branch = new OpCodeNode[] { new OpCodeNode(patR8M, "SRL_2") };
		root.addBranch(branch);

		return root;
	}// rootSRL

	private OpCodeNode rootSUB() {
		root = new OpCodeNode(patWord, BAD_OPCODE);
		branch = new OpCodeNode[] { new OpCodeNode(patIND_XYd, "SUB_1") };
		root.addBranch(branch);
		branch = new OpCodeNode[] { new OpCodeNode(patR8M, "SUB_2") };
		root.addBranch(branch);
		branch = new OpCodeNode[] { new OpCodeNode(patEXP, "SUB_3") };
		root.addBranch(branch);
		return root;
	}// rootSUB

	private OpCodeNode rootXOR() {
		root = new OpCodeNode(patWord, BAD_OPCODE);
		branch = new OpCodeNode[] { new OpCodeNode(patIND_XYd, "XOR_1") };
		root.addBranch(branch);
		branch = new OpCodeNode[] { new OpCodeNode(patR8M, "XOR_2") };
		root.addBranch(branch);
		branch = new OpCodeNode[] { new OpCodeNode(patEXP, "XOR_3") };
		root.addBranch(branch);
		return root;
	}// rootXOR

	// ------------------------------------------------------------------------------------------
	public static final String BAD_OPCODE = "Bad OpCode";
	private static final String EMPTY_STRING = "";

	public final Pattern patR8 = Pattern.compile("[A|B|C|D|E|H|L],|[A|B|C|D|E|H|L]\\b");
	public final Pattern patR81 = Pattern.compile("[A|B|C|D|E|H|L],");
//	public final Pattern patR8M = Pattern.compile("(\\(HL\\))|(\\b[A|B|C|D|E|H|L|M]\\b)");
	public final Pattern patR8M = Pattern.compile("\\(HL\\),|\\(HL\\)|[A|B|C|D|E|H|L|M],\\b|[A|B|C|D|E|H|L|M]\\b");
	public final Pattern patR8_RI = Pattern.compile("R\\b|I\\b");

	public final Pattern patR16_BCDE = Pattern.compile("BC|DE");
	public final Pattern patR16_AF = Pattern.compile("BC|DE|HL|AF");
	public final Pattern patR16_SP = Pattern.compile("BC,|DE,|HL,|SP,|BC|DE|HL|SP");
	public final Pattern patR16_XY = Pattern.compile("IX,|IY,|IX|IY");
	public final Pattern patR16_IX = Pattern.compile("BC|DE|SP|IX");

	public final Pattern patLIT_A = Pattern.compile("A,|A");
	public final Pattern patLIT_AF = Pattern.compile("AF,");
	public final Pattern patLIT_AFP = Pattern.compile("AF\\'");
	public final Pattern patLIT_DE = Pattern.compile("DE");
	public final Pattern patLIT_HL = Pattern.compile("HL,|HL");
	public final Pattern patLIT_SP = Pattern.compile("SP,|SP");

	public final Pattern patIND_BCDE = Pattern.compile("\\(BC\\)|\\(DE\\)");
	public final Pattern patIND_C = Pattern.compile("\\(C\\)");
	public final Pattern patIND_HL = Pattern.compile("\\(HL\\),|\\(HL\\)");
	public final Pattern patIND_SP = Pattern.compile("\\(SP\\),");
	public final Pattern patIND_XY = Pattern.compile("\\(X|Y\\)");
	public final Pattern patIND_XYd = Pattern.compile("\\(I[X|Y]\\+.*\\)"); // last argument
	public final Pattern patIND_XYd1 = Pattern.compile("\\(I[X|Y]\\+.*\\),"); // first of 2 arguments

	public final Pattern patCOND = Pattern.compile("C,|M,|NC,|NZ,|P,|PE,|PO,|Z,|C|M|NC|NZ|P|PE|PO|Z");
	public final Pattern patCONDs = Pattern.compile("C,|NC,|NZ,|Z,");	// some conditions

	public final Pattern patEXP = Pattern.compile(".*");	// last argument
	public final Pattern patEXP1 = Pattern.compile("[^,.]*,"); // first of 2 arguments
	public final Pattern patEXP_ADD = Pattern.compile("\\(.*\\)");
	public final Pattern patWord = Pattern.compile("\\b\\w*\\b");
	public final Pattern patNothing = Pattern.compile("^");

}// class InstructionSet
