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
	public static final String EXP_IM = "EXP_IM";		// used for IM -> 0,1,2
	public static final String EXP_RST = "EXP_RST";		// used for RST -> 0H,8H,10H,18H,20H,28H,30H,38H
	public static final String EXP_ADDR = "EXP_ADDR";
	
	public static final String IND_HL = "IND_HL";
	public static final String IND_XY = "IND_XY";
	public static final String IND_XYd = "IND_XYd";


	public static final Pattern patR8 = Pattern.compile("[A|B|C|D|E|H|L],|[A|B|C|D|E|H|L]\\b");
	public static final Pattern patR81 = Pattern.compile("[A|B|C|D|E|H|L],");
	public static final Pattern patR8M = Pattern.compile("\\(HL\\),|\\(HL\\)|[A|B|C|D|E|H|L|M],\\b|[A|B|C|D|E|H|L|M]\\b");
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
	public static final Pattern patCONDs = Pattern.compile("C,|NC,|NZ,|Z,");	// some conditions

	public static final Pattern patEXP = Pattern.compile(".*");	// last argument
	public static final Pattern patEXP1 = Pattern.compile("[^,.]*,"); // first of 2 arguments
	public static final Pattern patEXP_ADD = Pattern.compile("\\(.*\\)");
	public static final Pattern patWord = Pattern.compile("\\b\\w*\\b");
	public static final Pattern patNothing = Pattern.compile("^");
	
	public static final Pattern patEXP_IM = Pattern.compile("0|1|2");
	
	
	public static final HashMap<String,Byte> conditionTable = new HashMap<>();
	static{
		conditionTable.put("NZ",(byte) 0b00000000);
		conditionTable.put("Z", (byte) 0b00001000);
		conditionTable.put("NC",(byte) 0b00010000);
		conditionTable.put("C", (byte) 0b00011000);
		conditionTable.put("PO",(byte) 0b00100000);
		conditionTable.put("PE",(byte) 0b00101000);
		conditionTable.put("P", (byte) 0b00110000);
		conditionTable.put("M", (byte) 0b00111000);
	}//static
	
	public Z80(){
		
	}
}//
