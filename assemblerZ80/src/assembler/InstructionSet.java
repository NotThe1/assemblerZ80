package assembler;

import java.util.HashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InstructionSet {

	private static InstructionSet instance = new InstructionSet();

	private static HashMap<String, OpCodeNode> instructions ;
	private static OpCodeNode root;
	private static OpCodeNode[] branch;
	private Matcher matcher;
	private static Pattern patternInstructions;

	public static InstructionSet getInstance() {
		return instance;
	}// getInstance

	private InstructionSet() {
		appInit();
	}// Constructor

	public boolean isValid(String opCode) {
		return instructions.containsKey(opCode);
	}// isValid

	public static Pattern getPatternInstructions() {
		return patternInstructions;
	}// getPatternInstructions

//	public String getSubCode(String source) {
//		String opCodeVar = null;
//		matcher = this.patternInstructions.matcher(source);
//		if (matcher.find()) {
//			opCodeVar = getSubCode(matcher.group(), source);
//		}
//		return opCodeVar;
//	}// getSubCode

	public String findSubCode(String key, String source) {
		String opCodeVar = null;
		OpCodeNode root = instructions.get(key);
		opCodeVar = getSubCode(root, new String(source));

		return opCodeVar;
	}// getSubCode

	private String getSubCode(OpCodeNode node, String source) {
		String ans = null;
		try{
		matcher = node.getPattern().matcher(source);
		}catch (NullPointerException npe){
			AppLogger.getInstance().addError(source);
		}
		if (!matcher.lookingAt()) {
			return ans;
		} // if not interested in this node!

		if (node.hasChildren()) {
			source = matcher.replaceFirst(Z80.EMPTY_STRING); // remove matched substring
			source = source.replaceAll("\\s", Z80.EMPTY_STRING);// remove all spaces
			node.resetIterator();

			while (node.hasNext()) {
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

	public static  String getRegex() {
		StringBuilder sb = new StringBuilder("\\b(?i)(");
		Set<String> operations = getInstructionSet();
		for (String operation : operations) {
			sb.append(operation);
			sb.append("|");
		} // for
		sb.deleteCharAt(sb.length() - 1);
		sb.append(")\\b");
		return sb.toString();
	}// getRegex
	
	public static Set<String> getInstructionSet(){
		 return instructions.keySet();
	}//getInstructionSet

	// -----------------------------------------------------------------
	private static void appInit() {
		instructions = new HashMap<String, OpCodeNode>();
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

	private static void addNoArgInstructions() {
		String ins;
		String[] insSet = new String[] { "CCF", "CPD", "CPDR", "CPI", "CPIR", "CPL", "DAA", "DI", "EI", "EXX", "HLT","HALT",
				"IND", "INDR", "INI", "INIR", "LDD", "LDDR", "LDI", "LDIR", "NEG", "NOP", "OTDR", "OTIR", "OUTD",
				"OUTI", "RETI", "RETN", "RLA", "RLCA", "RLD", "RRA", "RRCA", "RRD", "SCF" };
		for (int i = 0; i < insSet.length; i++) {
			ins = insSet[i];
			instructions.put(ins, rootNoArgs(ins));
		} // for

	}// addNoArgInstructions
	


	private static OpCodeNode rootNoArgs(String instruction) {
		root = new OpCodeNode(Z80.patWord, instruction + "_0");
		return root;
	}// rootCCF

	private static OpCodeNode rootADC() {
		root = new OpCodeNode(Z80.patWord, Z80.BAD_OPCODE);

		branch = new OpCodeNode[] { new OpCodeNode(Z80.patLIT_A, Z80.BAD_OPCODE),
				 new OpCodeNode(Z80.patIND_XYd, "ADC_1"),
				 new OpCodeNode(Z80.patR8M, "ADC_2"),
				 new OpCodeNode(Z80.patEXP, "ADC_3") };
		root.addBranch(branch);

		branch = new OpCodeNode[] { new OpCodeNode(Z80.patLIT_HL, Z80.BAD_OPCODE),
				new OpCodeNode(Z80.patR16_SP, "ADC_4") };
		root.addBranch(branch);
		return root;
	}// rootADC

	private static OpCodeNode rootADD() {
		root = new OpCodeNode(Z80.patWord, Z80.BAD_OPCODE);

		branch = new OpCodeNode[] { new OpCodeNode(Z80.patLIT_A, Z80.BAD_OPCODE),
				 new OpCodeNode(Z80.patIND_XYd, "ADD_1"),
				 new OpCodeNode(Z80.patIND_XY, "ADD_1A"),
				 new OpCodeNode(Z80.patR8M, "ADD_2"),
				 new OpCodeNode(Z80.patEXP, "ADD_3") };
		root.addBranch(branch);

		branch = new OpCodeNode[] { new OpCodeNode(Z80.patLIT_HL, Z80.BAD_OPCODE),
				 new OpCodeNode(Z80.patR16_SP, "ADD_4") };
		root.addBranch(branch);

		branch = new OpCodeNode[] { new OpCodeNode(Z80.patLIT_IXY, Z80.BAD_OPCODE),
				 new OpCodeNode(Z80.patR16_IX, "ADD_5") };
		root.addBranch(branch);
		return root;
	}// rootADD

	private static OpCodeNode rootAND() {
		root = new OpCodeNode(Z80.patWord, Z80.BAD_OPCODE);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patIND_XYd, "AND_1") };
		root.addBranch(branch);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patR8M, "AND_2") };
		root.addBranch(branch);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patEXP, "AND_3") };
		root.addBranch(branch);
		return root;
	}// rootAND

	private static OpCodeNode rootBIT() {
		root = new OpCodeNode(Z80.patWord, Z80.BAD_OPCODE);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patEXP1, Z80.BAD_OPCODE),
				 new OpCodeNode(Z80.patIND_XYd, "BIT_1"),
				 new OpCodeNode(Z80.patR8M, "BIT_2") };
		root.addBranch(branch);
		return root;
	}// rootBIT

	private static OpCodeNode rootCALL() {
		root = new OpCodeNode(Z80.patWord, Z80.BAD_OPCODE);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patCOND, Z80.BAD_OPCODE),
				 new OpCodeNode(Z80.patEXP, "CALL_1") };
		root.addBranch(branch);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patEXP, "CALL_2") };
		root.addBranch(branch);
		return root;
	}// rootCALL

	private static OpCodeNode rootCP() {
		root = new OpCodeNode(Z80.patWord, Z80.BAD_OPCODE);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patIND_XYd, "CP_1") };
		root.addBranch(branch);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patR8M, "CP_2") };
		root.addBranch(branch);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patEXP, "CP_3") };
		root.addBranch(branch);
		return root;
	}// rootCP

	private static OpCodeNode rootDEC() {
		root = new OpCodeNode(Z80.patWord, Z80.BAD_OPCODE);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patIND_XYd, "DEC_1") };
		root.addBranch(branch);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patR16_SP, "DEC_2") };
		root.addBranch(branch);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patLIT_IXY, "DEC_3") };
		root.addBranch(branch);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patR8M, "DEC_4") };
		root.addBranch(branch);
		return root;
	}// rootDEC

	private static OpCodeNode rootDJNZ() {
		root = new OpCodeNode(Z80.patWord, Z80.BAD_OPCODE);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patEXP, "DJNZ_1") };
		root.addBranch(branch);
		return root;
	}// rootDJNZ

	private static OpCodeNode rootEX() {
		root = new OpCodeNode(Z80.patWord, Z80.BAD_OPCODE);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patIND_SP, Z80.BAD_OPCODE),
				 new OpCodeNode(Z80.patLIT_HL, "EX_1"),
				 new OpCodeNode(Z80.patLIT_IXY, "EX_2") };
		root.addBranch(branch);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patLIT_AF, "EX_3") };
		root.addBranch(branch);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patLIT_DE, "EX_4") };
		root.addBranch(branch);

		return root;
	}// rootEX
	
	private static OpCodeNode rootLD(){
		root = new OpCodeNode(Z80.patWord, Z80.BAD_OPCODE);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patLIT_A, Z80.BAD_OPCODE),
				 new OpCodeNode(Z80.patIND_XYd, "LD_1"),
				 new OpCodeNode(Z80.patIND_BCDE, "LD_2"),
				 new OpCodeNode(Z80.patLIT_RI, "LD_3"),
				 new OpCodeNode(Z80.patR8M, "LD_4"),
				 new OpCodeNode(Z80.patEXP_ADD, "LD_5"),
				 new OpCodeNode(Z80.patEXP, "LD_6")};
		root.addBranch(branch);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patLIT_HL, Z80.BAD_OPCODE),
				 new OpCodeNode(Z80.patEXP_ADD, "LD_7")};
		root.addBranch(branch);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patLIT_SP, Z80.BAD_OPCODE),
				 new OpCodeNode(Z80.patLIT_HL, "LD_8"),
				 new OpCodeNode(Z80.patLIT_IXY, "LD_9"),
				 new OpCodeNode(Z80.patEXP_ADD, "LD_10"),
				 new OpCodeNode(Z80.patEXP, "LD_11")};
		root.addBranch(branch);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patIND_BCDE, "LD_12")};
		root.addBranch(branch);	
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patIND_XYd1, Z80.BAD_OPCODE),
				 new OpCodeNode(Z80.patR8, "LD_13"),
				 new OpCodeNode(Z80.patEXP, "LD_14")};
		root.addBranch(branch);

		branch = new OpCodeNode[] { new OpCodeNode(Z80.patLIT_RI, "LD_15")};
		root.addBranch(branch);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patR16_BDH, Z80.BAD_OPCODE),
				 new OpCodeNode(Z80.patEXP_ADD, "LD_16"),
				 new OpCodeNode(Z80.patEXP, "LD_17")};
		root.addBranch(branch);

		branch = new OpCodeNode[] { new OpCodeNode(Z80.patLIT_IXY, Z80.BAD_OPCODE),
				 new OpCodeNode(Z80.patEXP_ADD, "LD_18"),
				 new OpCodeNode(Z80.patEXP, "LD_19")};
		root.addBranch(branch);

		branch = new OpCodeNode[] { new OpCodeNode(Z80.patR81, Z80.BAD_OPCODE),
				 new OpCodeNode(Z80.patR8M, "LD_20"),
				 new OpCodeNode(Z80.patIND_XYd, "LD_21"),
				 new OpCodeNode(Z80.patEXP, "LD_22")};
		root.addBranch(branch);

		branch = new OpCodeNode[] { new OpCodeNode(Z80.patIND_HL, Z80.BAD_OPCODE),
				 new OpCodeNode(Z80.patR8M, "LD_23"),
				 new OpCodeNode(Z80.patEXP, "LD_24")};
		root.addBranch(branch);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patEXP_ADD1, Z80.BAD_OPCODE),
				 new OpCodeNode(Z80.patLIT_A, "LD_25"),
				 new OpCodeNode(Z80.patLIT_IXY, "LD_26"),
				 new OpCodeNode(Z80.patLIT_HL, "LD_27"),
				 new OpCodeNode(Z80.patR16_SP, "LD_28")};
		root.addBranch(branch);



		return root;
	}//

	private static OpCodeNode rootIM() {
		root = new OpCodeNode(Z80.patWord, Z80.BAD_OPCODE);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patEXP, "IM_1") };
		root.addBranch(branch);
		return root;
	}// rootIM

	private static OpCodeNode rootIN() {
		root = new OpCodeNode(Z80.patWord, Z80.BAD_OPCODE);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patLIT_A, Z80.BAD_OPCODE),
				 new OpCodeNode(Z80.patIND_C, "IN_1") };
		root.addBranch(branch);


		branch = new OpCodeNode[] { new OpCodeNode(Z80.patLIT_A, Z80.BAD_OPCODE),
		new OpCodeNode(Z80.patEXP_ADD, "IN_2") };

		root.addBranch(branch);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patR8, Z80.BAD_OPCODE),
				 new OpCodeNode(Z80.patIND_C, "IN_3") };
		root.addBranch(branch);
		return root;
	}// rootIN

	private static OpCodeNode rootINC() {
		root = new OpCodeNode(Z80.patWord, Z80.BAD_OPCODE);
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patIND_XYd, "INC_1") };
		root.addBranch(branch);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patR16_SP, "INC_2") };
		root.addBranch(branch);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patLIT_IXY, "INC_3") };
		root.addBranch(branch);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patR8M, "INC_4") };
		root.addBranch(branch);
		return root;
	}// rootINC

	private static OpCodeNode rootJP() {
		root = new OpCodeNode(Z80.patWord, Z80.BAD_OPCODE);
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patCOND, Z80.BAD_OPCODE),
				 new OpCodeNode(Z80.patEXP, "JP_1") };
		root.addBranch(branch);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patIND_XY, "JP_2") };
		root.addBranch(branch);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patIND_HL, "JP_3") };
		root.addBranch(branch);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patEXP, "JP_4") };
		root.addBranch(branch);
		return root;
	}// rootJP

	private static OpCodeNode rootJR() {
		root = new OpCodeNode(Z80.patWord, Z80.BAD_OPCODE);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patCONDs, Z80.BAD_OPCODE),
				 new OpCodeNode(Z80.patEXP, "JR_1") };
		root.addBranch(branch);

		branch = new OpCodeNode[] { new OpCodeNode(Z80.patEXP, "JR_2") };
		root.addBranch(branch);
		return root;
	}// rootJR

	// ------------------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------

	private static OpCodeNode rootOR() {
		root = new OpCodeNode(Z80.patWord, Z80.BAD_OPCODE);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patIND_XYd, "OR_1") };
		root.addBranch(branch);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patR8M, "OR_2") };
		root.addBranch(branch);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patEXP, "OR_3") };
		root.addBranch(branch);
		return root;
	}// rootOR

	private static OpCodeNode rootOUT() {
		root = new OpCodeNode(Z80.patWord, Z80.BAD_OPCODE);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patIND_C, "OUT_1") };
		root.addBranch(branch);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patEXP_ADD, "OUT_2") };
		root.addBranch(branch);
		return root;
	}// rootOUT

	private static OpCodeNode rootPOP() {
		root = new OpCodeNode(Z80.patWord, Z80.BAD_OPCODE);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patR16_AF, "POP_1") };
		root.addBranch(branch);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patLIT_IXY, "POP_2") };
		root.addBranch(branch);
		return root;
	}// rootPOP

	private static OpCodeNode rootPUSH() {
		root = new OpCodeNode(Z80.patWord, Z80.BAD_OPCODE);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patR16_AF, "PUSH_1") };
		root.addBranch(branch);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patLIT_IXY, "PUSH_2") };
		root.addBranch(branch);
		return root;
	}// rootPUSH

	private static OpCodeNode rootRES() {
		root = new OpCodeNode(Z80.patWord, Z80.BAD_OPCODE);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patEXP1, Z80.BAD_OPCODE),
				 new OpCodeNode(Z80.patIND_XYd, "RES_1"),
				 new OpCodeNode(Z80.patR8M, "RES_2") };
		root.addBranch(branch);
		return root;
	}// rootRES

	private static OpCodeNode rootRET() {
		root = new OpCodeNode(Z80.patWord, Z80.BAD_OPCODE);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patCOND, "RET_1") };
		root.addBranch(branch);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patNothing, "RET_2") };
		root.addBranch(branch);

		return root;
	}// rootRET

	private static OpCodeNode rootRL() {
		root = new OpCodeNode(Z80.patWord, Z80.BAD_OPCODE);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patIND_XYd, "RL_1") };
		root.addBranch(branch);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patR8M, "RL_2") };
		root.addBranch(branch);
		return root;
	}// rootRL

	private static OpCodeNode rootRLC() {
		root = new OpCodeNode(Z80.patWord, Z80.BAD_OPCODE);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patIND_XYd, "RLC_1") };
		root.addBranch(branch);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patR8M, "RLC_2") };
		root.addBranch(branch);
		return root;
	}// rootRLC

	private static OpCodeNode rootRR() {
		root = new OpCodeNode(Z80.patWord, Z80.BAD_OPCODE);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patIND_XYd, "RR_1") };
		root.addBranch(branch);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patR8M, "RR_2") };
		root.addBranch(branch);
		return root;
	}// rootRR

	private static OpCodeNode rootRRC() {
		root = new OpCodeNode(Z80.patWord, Z80.BAD_OPCODE);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patIND_XYd, "RRC_1") };		
		root.addBranch(branch);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patR8M, "RRC_2") };
		root.addBranch(branch);
		return root;
	}// rootRRC

	private static OpCodeNode rootRST() {
		root = new OpCodeNode(Z80.patWord, Z80.BAD_OPCODE);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patEXP, "RST_1") };
		root.addBranch(branch);
		return root;
	}// rootRST

	private static OpCodeNode rootSBC() {
		root = new OpCodeNode(Z80.patWord, Z80.BAD_OPCODE);

		branch = new OpCodeNode[] { new OpCodeNode(Z80.patLIT_A, Z80.BAD_OPCODE),
				 new OpCodeNode(Z80.patIND_XYd, "SBC_1"),
				 new OpCodeNode(Z80.patR8M, "SBC_2"),
				 new OpCodeNode(Z80.patEXP, "SBC_3") };
		root.addBranch(branch);

		branch = new OpCodeNode[] { new OpCodeNode(Z80.patLIT_HL, Z80.BAD_OPCODE),
				 new OpCodeNode(Z80.patR16_SP, "SBC_4") };
		root.addBranch(branch);

		return root;
	}// rootSBC

	private static OpCodeNode rootSET() {
		root = new OpCodeNode(Z80.patWord, Z80.BAD_OPCODE);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patEXP1, Z80.BAD_OPCODE),
				 new OpCodeNode(Z80.patIND_XYd, "SET_1"),
				 new OpCodeNode(Z80.patR8M, "SET_2") };
		root.addBranch(branch);

		return root;
	}// rootSET

	private static OpCodeNode rootSLA() {
		root = new OpCodeNode(Z80.patWord, Z80.BAD_OPCODE);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patIND_XYd, "SLA_1") };
		root.addBranch(branch);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patR8M, "SLA_2") };
		root.addBranch(branch);
		return root;
	}// rootSLA

	private static OpCodeNode rootSRA() {
		root = new OpCodeNode(Z80.patWord, Z80.BAD_OPCODE);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patIND_XYd, "SRA_1") };
		root.addBranch(branch);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patR8M, "SRA_2") };
		root.addBranch(branch);
		return root;
	}// rootSRA

	private static OpCodeNode rootSRL() {
		root = new OpCodeNode(Z80.patWord, Z80.BAD_OPCODE);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patIND_XYd, "SRL_1") };
		root.addBranch(branch);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patR8M, "SRL_2") };
		root.addBranch(branch);
		return root;
	}// rootSRL

	private static OpCodeNode rootSUB() {
		root = new OpCodeNode(Z80.patWord, Z80.BAD_OPCODE);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patIND_XYd, "SUB_1") };
		root.addBranch(branch);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patR8M, "SUB_2") };
		root.addBranch(branch);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patEXP, "SUB_3") };
		root.addBranch(branch);
		return root;
	}// rootSUB

	private static OpCodeNode rootXOR() {
		root = new OpCodeNode(Z80.patWord, Z80.BAD_OPCODE);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patIND_XYd, "XOR_1") };
		root.addBranch(branch);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patR8M, "XOR_2") };
		root.addBranch(branch);
		
		branch = new OpCodeNode[] { new OpCodeNode(Z80.patEXP, "XOR_3") };
		root.addBranch(branch);
		return root;
	}// rootXOR


}// class InstructionSet
