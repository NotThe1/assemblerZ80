package assembler;

import java.util.HashMap;
import java.util.regex.Pattern;

public final class Z80 {
	public static final String BAD_OPCODE = "Bad OpCode";
	public static final String EMPTY_STRING = "";

	public static final String COND = "COND";
	public static final String COND_LIMITED = "COND_LIMITED";
	public static final String EXP_DB = "EXP_DB";
	public static final String EXP_DW = "EXP_DW";
	public static final String EXP_BIT = "EXP_BIT";
	public static final String EXP_IM = "EXP_IM"; // used for IM -> 0,1,2
	public static final String EXP_RST = "EXP_RST"; // used for RST -> 0H,8H,10H,18H,20H,28H,30H,38H
	public static final String EXP_ADDR = "EXP_ADDR";

	public static final String R_MAIN = "R_MAIN"; // used for all standard 8080 registers

	public static final String R16_XY = "R16_XY";
	public static final String R8M = "R8M"; // basic 8 bit regisers including M
	public static final String R8M_S3 = "R8M_S3"; // R8M to be shifted 3 bits left

	public static final String IND_HL = "IND_HL";
	public static final String IND_XY = "IND_XY";
	public static final String IND_XYd = "IND_XYd";

	public static final Pattern patR8 = Pattern.compile("[A|B|C|D|E|H|L],|[A|B|C|D|E|H|L]\\b");
	public static final Pattern patR81 = Pattern.compile("[A|B|C|D|E|H|L],");
	public static final Pattern patR8M = Pattern
			.compile("\\(HL\\),|\\(HL\\)|[A|B|C|D|E|H|L|M],\\b|[A|B|C|D|E|H|L|M]\\b");
	public static final Pattern patR8_RI = Pattern.compile("R\\b|I\\b");

	public static final Pattern patR16_BCDE = Pattern.compile("BC|DE");
	public static final Pattern patR16_AF = Pattern.compile("BC|DE|HL|AF");
	public static final Pattern patR16_SP = Pattern.compile("BC,|DE,|HL,|SP,|BC|DE|HL|SP");
	public static final Pattern patR16_XY = Pattern.compile("IX,|IY,|IX|IY");
	public static final Pattern patR16_IX = Pattern.compile("BC|DE|SP|IX");

	public static final Pattern patLIT_A = Pattern.compile("A,|A");
	public static final Pattern patLIT_AF = Pattern.compile("AF,");
	public static final Pattern patLIT_AFP = Pattern.compile("AF\\'");
	public static final Pattern patLIT_DE = Pattern.compile("DE");
	public static final Pattern patLIT_HL = Pattern.compile("HL,|HL");
	public static final Pattern patLIT_SP = Pattern.compile("SP,|SP");

	public static final Pattern patIND_BCDE = Pattern.compile("\\(BC\\)|\\(DE\\)");
	public static final Pattern patIND_C = Pattern.compile("\\(C\\)");
	public static final Pattern patIND_HL = Pattern.compile("\\(HL\\),|\\(HL\\)");
	public static final Pattern patIND_SP = Pattern.compile("\\(SP\\),");
	public static final Pattern patIND_XY = Pattern.compile("\\(I[X|Y]\\)");
	public static final Pattern patIND_XYd = Pattern.compile("\\(I[X|Y]\\+.*\\)"); // last argument
	public static final Pattern patIND_XYd1 = Pattern.compile("\\(I[X|Y]\\+.*\\),"); // first of 2 arguments

	public static final Pattern patCOND = Pattern.compile("C,|M,|NC,|NZ,|P,|PE,|PO,|Z,|C|M|NC|NZ|P|PE|PO|Z");
	public static final Pattern patCONDs = Pattern.compile("C,|NC,|NZ,|Z,"); // some conditions

	public static final Pattern patEXP = Pattern.compile(".*"); // last argument
	public static final Pattern patEXP1 = Pattern.compile("[^,.]*,"); // first of 2 arguments
	public static final Pattern patEXP_ADD = Pattern.compile("\\(.*\\)");
	public static final Pattern patWord = Pattern.compile("\\b\\w*\\b");
	public static final Pattern patNothing = Pattern.compile("^");

	public static final Pattern patEXP_IM = Pattern.compile("0|1|2");

	public static final HashMap<String, Byte> conditionTable = new HashMap<>();
	static {
		conditionTable.put("NZ", (byte) 0b00000000);
		conditionTable.put("Z", (byte) 0b00001000);
		conditionTable.put("NC", (byte) 0b00010000);
		conditionTable.put("C", (byte) 0b00011000);
		conditionTable.put("PO", (byte) 0b00100000);
		conditionTable.put("PE", (byte) 0b00101000);
		conditionTable.put("P", (byte) 0b00110000);
		conditionTable.put("M", (byte) 0b00111000);
	}// static conditionTable

	public static final HashMap<String, Byte> registerTable = new HashMap<>();
	static {
		registerTable.put("A", (byte) 0b00000111);
		registerTable.put("B", (byte) 0b00000000);
		registerTable.put("C", (byte) 0b00000001);
		registerTable.put("D", (byte) 0b00000010);
		registerTable.put("E", (byte) 0b00000011);
		registerTable.put("H", (byte) 0b00000100);
		registerTable.put("L", (byte) 0b00000101);
		registerTable.put("M", (byte) 0b00000110);
		registerTable.put("(HL)", (byte) 0b00000110);

		registerTable.put("BC", (byte) 0b00000000);
		registerTable.put("DE", (byte) 0b00010000);
		registerTable.put("HL", (byte) 0b00100000);
		registerTable.put("SP", (byte) 0b00110000);
		registerTable.put("AF", (byte) 0b00110000);
	}// static registerTable

	public static final HashMap<Integer, Byte> bitTable = new HashMap<>();
	static {
		bitTable.put(0, (byte) 0b00000000);
		bitTable.put(1, (byte) 0b00001000);
		bitTable.put(2, (byte) 0b00010000);
		bitTable.put(3, (byte) 0b00011000);
		bitTable.put(4, (byte) 0b00100000);
		bitTable.put(5, (byte) 0b00101000);
		bitTable.put(6, (byte) 0b00110000);
		bitTable.put(7, (byte) 0b00111000);
	}// static bitTable
	
	

}// public Z80
