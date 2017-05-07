package assembler;

import java.util.HashMap;
import java.util.regex.Pattern;

public class SubInstructionSet {
	private static HashMap<String, SubInstruction> subInstructions = new HashMap<>();

	private static SubInstructionSet instance = new SubInstructionSet();

	private SubInstructionSet() {
		appInit();
	}// constructor

	public static SubInstructionSet getInstance() {
		return instance;
	}// getInstance
	
	public static SubInstruction getSubInstruction(String subOpCode){
		return  subInstructions.get(subOpCode);
	}//getOpCodeSize
	
	public static int getOpCodeSize(String subOpCode){
		SubInstruction si = subInstructions.get(subOpCode);
		return  si==null?-1:si.size;
	}//getOpCodeSize

	public static byte[] getBaseCodes(String subOpCode){
		SubInstruction si = subInstructions.get(subOpCode);
		return  si==null?null:si.baseCodes;
	}//getOpCodeSize
	
	public static Pattern getPattern1(String subOpCode){
		SubInstruction si = subInstructions.get(subOpCode);
		return  si==null?null:si.pattern1;
	}//getPattern1
	
	public static String getArgType1(String subOpCode){
		SubInstruction si = subInstructions.get(subOpCode);
		return  si==null?null:si.argument1Type;
	}//getArgType1
	
	public static Pattern getPattern2(String subOpCode){
		SubInstruction si = subInstructions.get(subOpCode);
		return  si==null?null:si.pattern2;
	}//getPattern2
	
	public static String getArgType2(String subOpCode){
		SubInstruction si = subInstructions.get(subOpCode);
		return  si==null?null:si.argument2Type;
	}//getArgType2
	
	

	private void appInit() {
		// Zero Arguments ...................................................
		
//		baseCodes = new Byte[] { (byte) 0XDD, (byte) 0X86};
//		subInstructions.put("",new SubInstruction("", 0,baseCodes));
		
		byte[] baseCodes = new byte[] { (byte) 0X3F};
		subInstructions.put("CCF_0",new SubInstruction("CCF_0", 1,baseCodes));
		
		baseCodes = new byte[] { (byte) 0XED, (byte) 0XA9};
		subInstructions.put("CPD_0",new SubInstruction("CPD_0", 2,baseCodes));
		
		baseCodes = new byte[] { (byte) 0XED, (byte) 0XB9};
		subInstructions.put("CPDR_0",new SubInstruction("CPDR_0", 2,baseCodes));
		
		baseCodes = new byte[] { (byte) 0XED, (byte) 0XA1};
		subInstructions.put("CPI_0",new SubInstruction("CPI_0", 2,baseCodes));
		
		baseCodes = new byte[] { (byte) 0XED, (byte) 0XB1};
		subInstructions.put("CPIR_0",new SubInstruction("CPIR_0", 2,baseCodes));
		
		 baseCodes = new byte[] { (byte) 0X2F};
		subInstructions.put("CPL_0",new SubInstruction("CPL_0", 1,baseCodes));
		
		 baseCodes = new byte[] { (byte) 0X27};
		subInstructions.put("DAA_0",new SubInstruction("DAA_0", 1,baseCodes));
		
		baseCodes = new byte[] { (byte) 0XF3};
		subInstructions.put("DI_0",new SubInstruction("DI_0", 1,baseCodes));
		
		baseCodes = new byte[] { (byte) 0XFB};
		subInstructions.put("EI_0",new SubInstruction("EI_0", 1,baseCodes));
		
		baseCodes = new byte[] { (byte) 0XD9};
		subInstructions.put("EXX_0",new SubInstruction("EXX_0", 1,baseCodes));
		
		baseCodes = new byte[] { (byte) 0X76};
		subInstructions.put("HLT_0",new SubInstruction("HLT_0", 1,baseCodes));
		
		baseCodes = new byte[] { (byte) 0XED, (byte) 0XAA};
		subInstructions.put("IND_0",new SubInstruction("IND_0", 2,baseCodes));
		
		baseCodes = new byte[] { (byte) 0XED, (byte) 0XBA};
		subInstructions.put("INDR_0",new SubInstruction("INDR_0", 2,baseCodes));

		baseCodes = new byte[] { (byte) 0XED, (byte) 0XA2};
		subInstructions.put("INI_0",new SubInstruction("INI_0", 2,baseCodes));

		baseCodes = new byte[] { (byte) 0XED, (byte) 0XB2};
		subInstructions.put("INIR_0",new SubInstruction("INIR_0", 2,baseCodes));

		baseCodes = new byte[] { (byte) 0XED, (byte) 0XA8};
		subInstructions.put("LDD_0",new SubInstruction("LDD_0", 2,baseCodes));

		baseCodes = new byte[] { (byte) 0XED, (byte) 0XB8};
		subInstructions.put("LDDR_0",new SubInstruction("LDDR_0", 2,baseCodes));

		baseCodes = new byte[] { (byte) 0XED, (byte) 0XA0};
		subInstructions.put("LDI_0",new SubInstruction("LDI_0", 2,baseCodes));

		baseCodes = new byte[] { (byte) 0XED, (byte) 0XB0};
		subInstructions.put("LDIR_0",new SubInstruction("LDIR_0", 2,baseCodes));

		baseCodes = new byte[] { (byte) 0XED, (byte) 0X44};
		subInstructions.put("NEG_0",new SubInstruction("NEG_0", 2,baseCodes));

		baseCodes = new byte[] { (byte) 0X00};
		subInstructions.put("NOP_0",new SubInstruction("NOP_0", 1,baseCodes));

		baseCodes = new byte[] { (byte) 0XED, (byte) 0XBB};
		subInstructions.put("OTDR_0",new SubInstruction("OTDR_0", 2,baseCodes));

		baseCodes = new byte[] { (byte) 0XED, (byte) 0XB3};
		subInstructions.put("OTIR_0",new SubInstruction("OTIR_0", 2,baseCodes));

		baseCodes = new byte[] { (byte) 0XED, (byte) 0XAB};
		subInstructions.put("OUTD_0",new SubInstruction("OUTD_0", 2,baseCodes));

		baseCodes = new byte[] { (byte) 0XED, (byte) 0XA3};
		subInstructions.put("OUTI_0",new SubInstruction("OUTI_0", 2,baseCodes));

		baseCodes = new byte[] { (byte) 0XED, (byte) 0X4D};
		subInstructions.put("RETI_0",new SubInstruction("RETI_0", 2,baseCodes));

		baseCodes = new byte[] { (byte) 0XED, (byte) 0X45};
		subInstructions.put("RETN_0",new SubInstruction("RETN_0", 2,baseCodes));
		//
		baseCodes = new byte[] { (byte) 0X17};
		subInstructions.put("RLA_0",new SubInstruction("RLA_0", 1,baseCodes));
		
		baseCodes = new byte[] { (byte) 0X07};
		subInstructions.put("RLCA_0",new SubInstruction("RLCA_0", 1,baseCodes));
		
		baseCodes = new byte[] { (byte) 0XED, (byte) 0X6F};
		subInstructions.put("RLD_0",new SubInstruction("RLD_0", 2,baseCodes));
		//

		baseCodes = new byte[] { (byte) 0X1F};
		subInstructions.put("RRA_0",new SubInstruction("RRA_0", 1,baseCodes));

		baseCodes = new byte[] { (byte) 0X0F};
		subInstructions.put("RRCA_0",new SubInstruction("RCA_0", 1,baseCodes));

		baseCodes = new byte[] { (byte) 0XED, (byte) 0X67};
		subInstructions.put("RRD_0",new SubInstruction("RRD_0", 2,baseCodes));

		
		baseCodes = new byte[] {  (byte) 0X37};
		subInstructions.put("SCF_0",new SubInstruction("SCF_0", 1,baseCodes));

		// Zero and One Argument
		
		baseCodes = new byte[] {  (byte) 0XC9};
		subInstructions.put("RET_2",new SubInstruction("RET_2", 1,baseCodes));

		baseCodes = new byte[] { (byte) 0XC0};
		subInstructions.put("RET_1",new SubInstruction("RET_1", 1,Z80.COND,Z80.patCOND,baseCodes));
			
		// One Argument ...................................................
		
		baseCodes = new byte[] { (byte) 0XED,(byte) 0X46};
		subInstructions.put("IM_1",new SubInstruction("IM_1", 2,Z80.EXP_IM,Z80.patEXP_IM,baseCodes));

		baseCodes = new byte[] { (byte) 0XC7};
		subInstructions.put("RST_1",new SubInstruction("RST_1", 1,Z80.EXP_RST,Z80.patEXP,baseCodes));

		baseCodes = new byte[] { (byte) 0XE6,(byte) 0X00};
		subInstructions.put("AND_3",new SubInstruction("AND_3", 2,Z80.EXP_DB,Z80.patEXP,baseCodes));


		baseCodes = new byte[] { (byte) 0XFE,(byte) 0X00};
		subInstructions.put("CP_3",new SubInstruction("CP_3", 2,Z80.EXP_DB,Z80.patEXP,baseCodes));


		baseCodes = new byte[] { (byte) 0X10,(byte) 0X00};
		subInstructions.put("DJNZ_1",new SubInstruction("DJNZ_1", 2,Z80.EXP_DB,Z80.patEXP,baseCodes));


		baseCodes = new byte[] { (byte) 0X18,(byte) 0X00};
		subInstructions.put("JR_2",new SubInstruction("JR_2", 2,Z80.EXP_DB,Z80.patEXP,baseCodes));


		baseCodes = new byte[] { (byte) 0XF6,(byte) 0X00};
		subInstructions.put("OR_3",new SubInstruction("OR_3", 2,Z80.EXP_DB,Z80.patEXP,baseCodes));


		baseCodes = new byte[] { (byte) 0XD6,(byte) 0X00};
		subInstructions.put("SUB_3",new SubInstruction("SUB_3", 2,Z80.EXP_DB,Z80.patEXP,baseCodes));


		baseCodes = new byte[] { (byte) 0XEE,(byte) 0X00};
		subInstructions.put("XOR_3",new SubInstruction("XOR_3", 2,Z80.EXP_DB,Z80.patEXP,baseCodes));

		baseCodes = new byte[] { (byte) 0XCD,(byte) 0X00,(byte) 0X00};
		subInstructions.put("CALL_2",new SubInstruction("CALL_2", 3,Z80.EXP_DW,Z80.patEXP,baseCodes));

		baseCodes = new byte[] { (byte) 0Xc3,(byte) 0X00,(byte) 0X00};
		subInstructions.put("JP_4",new SubInstruction("JP_4", 3,Z80.EXP_DW,Z80.patEXP,baseCodes));


		
		// Two Arguments ...................................................

		baseCodes = new byte[] { (byte) 0XDD, (byte) 0X86, (byte) 0X00 };
		subInstructions.put("ADC_1",
				new SubInstruction("ADC_1", 3, "A", Z80.patLIT_A, "IX", Z80.patIND_XYd, baseCodes));
		
		baseCodes = new byte[] { (byte) 0X80};
		subInstructions.put("ADC_2",
				new SubInstruction("ADC_2", 1, "A", Z80.patLIT_A, "IX", Z80.patR8M, baseCodes));

		 baseCodes = new byte[] { (byte) 0XC6, (byte) 0X00};
		subInstructions.put("ADC_3",
				new SubInstruction("ADC_3", 2, "A", Z80.patLIT_A, "IX", Z80.patEXP, baseCodes));
		
		 baseCodes = new byte[] { (byte) 0XC6, (byte) 0X00};
		subInstructions.put("ADC_4",
				new SubInstruction("ADC_4", 2, "A", Z80.patLIT_HL, "SP", Z80.patR16_SP, baseCodes));
		
		baseCodes = new byte[] { (byte) 0XC4, (byte) 0X00, (byte) 0X00 };
		subInstructions.put("CALL_1",
				new SubInstruction("CALL_1", 3, Z80.COND, Z80.patCOND, Z80.EXP_DW, Z80.patEXP, baseCodes));
		
		baseCodes = new byte[] { (byte) 0XC2, (byte) 0X00, (byte) 0X00 };
		subInstructions.put("JP_1",
				new SubInstruction("JP_1", 3, Z80.COND, Z80.patCOND, Z80.EXP_DW, Z80.patEXP, baseCodes));
		
		baseCodes = new byte[] { (byte) 0X20, (byte) 0X00 };
		subInstructions.put("JR_1",
				new SubInstruction("JR_1", 2, Z80.COND_LIMITED, Z80.patCONDs, Z80.EXP_DB, Z80.patEXP, baseCodes));
		
		baseCodes = new byte[] { (byte) 0XD3, (byte) 0X00 };
		subInstructions.put("OUT_2",
				new SubInstruction("OUT_2", 2, Z80.EXP_ADDR, Z80.patEXP_ADD, "A", Z80.patLIT_A, baseCodes));
		

		

	}// appInit

}//
