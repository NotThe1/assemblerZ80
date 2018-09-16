package intelToZilog;

import java.util.HashMap;
import java.util.Set;
import java.util.regex.Pattern;

public class InstructionSet8080 {

	public InstructionSet8080() {
		// TODO Auto-generated constructor stub
	}
	/* --------------------------------------------------*/
	public static boolean isInstruction(String token) {
		return (instructions.containsKey(token));
	}// isInstruction
	
	public static String getOpCode(String name) {
		return instructions.get(name).getOpCode();
	}// getOpCode
	
	public static int getOpCodeSize(String name) {
		return instructions.get(name).getOpCodeSize();
	}// getOpCode
	
	public static int getOperandType(String name) {
		return instructions.get(name).getOperandType();
	}// getOpCode
	
	public static byte getBaseCode(String name) {
		return instructions.get(name).getBaseCode();
	}// getOpCode
	
	public static int getOperand1Shift(String name) {
		return instructions.get(name).getOperand1Shift();
	}// getOpCode
	
	public static int getOperand2Shift(String name) {
		return instructions.get(name).getOperand2Shift();
	}// getOpCode
	
	/* ----------------------------------------*/
	private  String getRegex() {
		StringBuilder sb = new StringBuilder("\\b(?i)(");
		Set<String> operations = getInstructionSet();
		for (String operation : operations) {
			sb.append(operation);
			sb.append("|");
		} // for
		sb.deleteCharAt(sb.length() - 1);
		sb.append(")\\b");
//		System.out.printf("[InstructionSet.getRegex] sb: %s%n%n", sb.toString());
		;
		return sb.toString();
	}//getRegex
	
	public  Pattern getInstructionPattern() {
		return Pattern.compile(getRegex());
	}//
	
	
	public static Set<String> getInstructionSet(){
		 return instructions.keySet();
	}//getInstructionSet
	
	/* ----------------------------------------*/
	
	private static HashMap<String, Instruction8080> instructions;
	static {
		instructions = new HashMap<String, Instruction8080>();

		instructions.put("CMC", new Instruction8080("CMC", 1, (byte) 0X3F, Instruction8080.ARGUMENT_NONE));
		instructions.put("STC", new Instruction8080("STC", 1, (byte) 0X37, Instruction8080.ARGUMENT_NONE));
		instructions.put("INR", new Instruction8080("INR", 1, (byte) 0X04, Instruction8080.ARGUMENT_R8, 3)); // INC
		instructions.put("DCR", new Instruction8080("DCR", 1, (byte) 0X05, Instruction8080.ARGUMENT_R8, 3));
		instructions.put("CMA", new Instruction8080("CMA", 1, (byte) 0X2F, Instruction8080.ARGUMENT_NONE));
		instructions.put("DAA", new Instruction8080("DAA", 1, (byte) 0X27, Instruction8080.ARGUMENT_NONE));
		instructions.put("NOP", new Instruction8080("NOP", 1, (byte) 0X00, Instruction8080.ARGUMENT_NONE));
		instructions.put("HLT", new Instruction8080("HLT", 1, (byte) 0X76, Instruction8080.ARGUMENT_NONE));

		instructions.put("MOV", new Instruction8080("MOV", 1, (byte) 0X40, Instruction8080.ARGUMENT_R8_R8, 3, 0));
		instructions.put("STAX", new Instruction8080("STAX", 1, (byte) 0X02, Instruction8080.ARGUMENT_R16D, 4)); // TODO?
		instructions.put("LDAX", new Instruction8080("LDAX", 1, (byte) 0X0A, Instruction8080.ARGUMENT_R16D, 4)); // TODO?

		instructions.put("ADD", new Instruction8080("ADD", 1, (byte) 0X80, Instruction8080.ARGUMENT_R8, 0));
		instructions.put("ADC", new Instruction8080("ADC", 1, (byte) 0X88, Instruction8080.ARGUMENT_R8, 0));
		instructions.put("SUB", new Instruction8080("SUB", 1, (byte) 0X90, Instruction8080.ARGUMENT_R8, 0));
		instructions.put("SBB", new Instruction8080("SBB", 1, (byte) 0X98, Instruction8080.ARGUMENT_R8, 0));
		instructions.put("ANA", new Instruction8080("ANA", 1, (byte) 0XA0, Instruction8080.ARGUMENT_R8, 0));
		instructions.put("XRA", new Instruction8080("XRA", 1, (byte) 0XA8, Instruction8080.ARGUMENT_R8, 0));
		instructions.put("ORA", new Instruction8080("ORA", 1, (byte) 0XB0, Instruction8080.ARGUMENT_R8, 0));
		instructions.put("CMP", new Instruction8080("CMP", 1, (byte) 0XB8, Instruction8080.ARGUMENT_R8, 0));

		instructions.put("RRC", new Instruction8080("RRC", 1, (byte) 0X0F, Instruction8080.ARGUMENT_NONE));
		instructions.put("RLC", new Instruction8080("RLC", 1, (byte) 0X07, Instruction8080.ARGUMENT_NONE));
		instructions.put("RAL", new Instruction8080("RAL", 1, (byte) 0X17, Instruction8080.ARGUMENT_NONE));
		instructions.put("RAR", new Instruction8080("RAR", 1, (byte) 0X1F, Instruction8080.ARGUMENT_NONE));

		instructions.put("PUSH", new Instruction8080("PUSH", 1, (byte) 0XC5, Instruction8080.ARGUMENT_R16Q, 4));
		instructions.put("POP", new Instruction8080("POP", 1, (byte) 0XC1, Instruction8080.ARGUMENT_R16Q, 4));
		instructions.put("DAD", new Instruction8080("DAD", 1, (byte) 0X09, Instruction8080.ARGUMENT_R16D, 4));
		instructions.put("INX", new Instruction8080("INX", 1, (byte) 0X03, Instruction8080.ARGUMENT_R16D, 4));
		instructions.put("DCX", new Instruction8080("DCX", 1, (byte) 0X0B, Instruction8080.ARGUMENT_R16D, 4));

		instructions.put("XCHG", new Instruction8080("XCHG", 1, (byte) 0XEB, Instruction8080.ARGUMENT_NONE));
		instructions.put("XTHL", new Instruction8080("XTHL", 1, (byte) 0XE3, Instruction8080.ARGUMENT_NONE));
		instructions.put("SPHL", new Instruction8080("SPHL", 1, (byte) 0XF9, Instruction8080.ARGUMENT_NONE));
		instructions.put("PCHL", new Instruction8080("PCHL", 1, (byte) 0XE9, Instruction8080.ARGUMENT_NONE));

		instructions.put("LXI", new Instruction8080("LXI", 3, (byte) 0X01, Instruction8080.ARGUMENT_R16D_D16, 4));
		instructions.put("MVI", new Instruction8080("MVI", 2, (byte) 0X06, Instruction8080.ARGUMENT_R8_D8, 3));
		instructions.put("ADI", new Instruction8080("ADI", 2, (byte) 0XC6, Instruction8080.ARGUMENT_D8));
		instructions.put("ACI", new Instruction8080("ACI", 2, (byte) 0XCE, Instruction8080.ARGUMENT_D8));
		instructions.put("SUI", new Instruction8080("SUI", 2, (byte) 0XD6, Instruction8080.ARGUMENT_D8));
		instructions.put("SBI", new Instruction8080("SBI", 2, (byte) 0XDE, Instruction8080.ARGUMENT_D8));
		instructions.put("ANI", new Instruction8080("ANI", 2, (byte) 0XE6, Instruction8080.ARGUMENT_D8));
		instructions.put("XRI", new Instruction8080("XRI", 2, (byte) 0XEE, Instruction8080.ARGUMENT_D8));
		instructions.put("ORI", new Instruction8080("ORI", 2, (byte) 0XF6, Instruction8080.ARGUMENT_D8));
		instructions.put("CPI", new Instruction8080("CPI", 2, (byte) 0XFE, Instruction8080.ARGUMENT_D8));

		instructions.put("STA", new Instruction8080("STA", 3, (byte) 0X32, Instruction8080.ARGUMENT_D16));
		instructions.put("LDA", new Instruction8080("LDA", 3, (byte) 0X3A, Instruction8080.ARGUMENT_D16));
		instructions.put("SHLD", new Instruction8080("SHLD", 3, (byte) 0X22, Instruction8080.ARGUMENT_D16));
		instructions.put("LHLD", new Instruction8080("LHLD", 3, (byte) 0X2A, Instruction8080.ARGUMENT_D16));

		instructions.put("JMP", new Instruction8080("JMP", 3, (byte) 0XC3, Instruction8080.ARGUMENT_D16));
		instructions.put("JNZ", new Instruction8080("JNZ", 3, (byte) 0XC2, Instruction8080.ARGUMENT_D16));
		instructions.put("JZ", new Instruction8080("JZ", 3, (byte) 0XCA, Instruction8080.ARGUMENT_D16));
		instructions.put("JNC", new Instruction8080("JNC", 3, (byte) 0XD2, Instruction8080.ARGUMENT_D16));
		instructions.put("JC", new Instruction8080("JC", 3, (byte) 0XDA, Instruction8080.ARGUMENT_D16));
		instructions.put("JPO", new Instruction8080("JPO", 3, (byte) 0XE2, Instruction8080.ARGUMENT_D16));
		instructions.put("JPE", new Instruction8080("JPE", 3, (byte) 0XEA, Instruction8080.ARGUMENT_D16));
		instructions.put("JP", new Instruction8080("JP", 3, (byte) 0XF2, Instruction8080.ARGUMENT_D16));
		instructions.put("JM", new Instruction8080("JM", 3, (byte) 0XFA, Instruction8080.ARGUMENT_D16));

		instructions.put("CALL", new Instruction8080("CALL", 3, (byte) 0XCD, Instruction8080.ARGUMENT_D16));
		instructions.put("CNZ", new Instruction8080("CNZ", 3, (byte) 0XC4, Instruction8080.ARGUMENT_D16));
		instructions.put("CZ", new Instruction8080("CZ", 3, (byte) 0XCC, Instruction8080.ARGUMENT_D16));
		instructions.put("CNC", new Instruction8080("CNC", 3, (byte) 0XD4, Instruction8080.ARGUMENT_D16));
		instructions.put("CC", new Instruction8080("CC", 3, (byte) 0XDC, Instruction8080.ARGUMENT_D16));
		instructions.put("CPO", new Instruction8080("CPO", 3, (byte) 0XE4, Instruction8080.ARGUMENT_D16));
		instructions.put("CPE", new Instruction8080("CPE", 3, (byte) 0XEC, Instruction8080.ARGUMENT_D16));
		instructions.put("CP", new Instruction8080("CP", 3, (byte) 0XF4, Instruction8080.ARGUMENT_D16));
		instructions.put("CM", new Instruction8080("CM", 3, (byte) 0XFC, Instruction8080.ARGUMENT_D16));

		instructions.put("RET", new Instruction8080("RET", 1, (byte) 0XC9, Instruction8080.ARGUMENT_NONE));
		instructions.put("RNZ", new Instruction8080("RNZ", 1, (byte) 0XC0, Instruction8080.ARGUMENT_NONE));
		instructions.put("RZ", new Instruction8080("RZ", 1, (byte) 0XC8, Instruction8080.ARGUMENT_NONE));
		instructions.put("RNC", new Instruction8080("RNC", 1, (byte) 0XD0, Instruction8080.ARGUMENT_NONE));
		instructions.put("RC", new Instruction8080("RC", 1, (byte) 0XD8, Instruction8080.ARGUMENT_NONE));
		instructions.put("RPO", new Instruction8080("RPO", 1, (byte) 0XE0, Instruction8080.ARGUMENT_NONE));
		instructions.put("RPE", new Instruction8080("RPE", 1, (byte) 0XE8, Instruction8080.ARGUMENT_NONE));
		instructions.put("RP", new Instruction8080("RP", 1, (byte) 0XF0, Instruction8080.ARGUMENT_NONE));
		instructions.put("RM", new Instruction8080("RM", 1, (byte) 0XF8, Instruction8080.ARGUMENT_NONE));

		instructions.put("RST", new Instruction8080("RST", 1, (byte) 0XC7, Instruction8080.ARGUMENT_P8, 3));

		instructions.put("EI", new Instruction8080("EI", 1, (byte) 0XFB, Instruction8080.ARGUMENT_NONE));
		instructions.put("DI", new Instruction8080("DI", 1, (byte) 0XF3, Instruction8080.ARGUMENT_NONE));

		instructions.put("IN", new Instruction8080("IN", 2, (byte) 0XDB, Instruction8080.ARGUMENT_D8));
		instructions.put("OUT", new Instruction8080("OUT", 2, (byte) 0XD3, Instruction8080.ARGUMENT_D8));
	}// static


}//class InstructionSet8080
