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

	public static SubInstruction getSubInstruction(String subOpCode) {
		return subInstructions.get(subOpCode);
	}// getOpCodeSize

	public static int getOpCodeSize(String subOpCode) {
		SubInstruction si = subInstructions.get(subOpCode);
		return si == null ? -1 : si.getSize();
	}// getOpCodeSize

	public static byte[] getBaseCodes(String subOpCode) {
		SubInstruction si = subInstructions.get(subOpCode);
		return si == null ? null : si.baseCodes;
	}// getOpCodeSize

	public static Pattern getPattern1(String subOpCode) {
		SubInstruction si = subInstructions.get(subOpCode);
		return si == null ? null : si.pattern1;
	}// getPattern1

	public static String getArgType1(String subOpCode) {
		SubInstruction si = subInstructions.get(subOpCode);
		return si == null ? null : si.argument1Type;
	}// getArgType1

	public static Pattern getPattern2(String subOpCode) {
		SubInstruction si = subInstructions.get(subOpCode);
		return si == null ? null : si.pattern2;
	}// getPattern2

	public static String getArgType2(String subOpCode) {
		SubInstruction si = subInstructions.get(subOpCode);
		return si == null ? null : si.argument2Type;
	}// getArgType2

	private void appInit() {
		// Zero Arguments ...................................................

		// baseCodes = new Byte[] { (byte) 0XDD, (byte) 0X86};
		// subInstructions.put("",new SubInstruction("", 0,baseCodes));

		byte[] baseCodes = new byte[] { (byte) 0X3F };
		subInstructions.put("CCF_0", new SubInstruction("CCF_0", baseCodes));

		baseCodes = new byte[] { (byte) 0XED, (byte) 0XA9 };
		subInstructions.put("CPD_0", new SubInstruction("CPD_0", baseCodes));

		baseCodes = new byte[] { (byte) 0XED, (byte) 0XB9 };
		subInstructions.put("CPDR_0", new SubInstruction("CPDR_0", baseCodes));

		baseCodes = new byte[] { (byte) 0XED, (byte) 0XA1 };
		subInstructions.put("CPI_0", new SubInstruction("CPI_0", baseCodes));

		baseCodes = new byte[] { (byte) 0XED, (byte) 0XB1 };
		subInstructions.put("CPIR_0", new SubInstruction("CPIR_0", baseCodes));

		baseCodes = new byte[] { (byte) 0X2F };
		subInstructions.put("CPL_0", new SubInstruction("CPL_0", baseCodes));

		baseCodes = new byte[] { (byte) 0X27 };
		subInstructions.put("DAA_0", new SubInstruction("DAA_0", baseCodes));

		baseCodes = new byte[] { (byte) 0XF3 };
		subInstructions.put("DI_0", new SubInstruction("DI_0", baseCodes));

		baseCodes = new byte[] { (byte) 0XFB };
		subInstructions.put("EI_0", new SubInstruction("EI_0", baseCodes));

		baseCodes = new byte[] { (byte) 0XD9 };
		subInstructions.put("EXX_0", new SubInstruction("EXX_0", baseCodes));

		baseCodes = new byte[] { (byte) 0X76 };
		subInstructions.put("HLT_0", new SubInstruction("HLT_0", baseCodes));

		baseCodes = new byte[] { (byte) 0XED, (byte) 0XAA };
		subInstructions.put("IND_0", new SubInstruction("IND_0", baseCodes));

		baseCodes = new byte[] { (byte) 0XED, (byte) 0XBA };
		subInstructions.put("INDR_0", new SubInstruction("INDR_0", baseCodes));

		baseCodes = new byte[] { (byte) 0XED, (byte) 0XA2 };
		subInstructions.put("INI_0", new SubInstruction("INI_0", baseCodes));

		baseCodes = new byte[] { (byte) 0XED, (byte) 0XB2 };
		subInstructions.put("INIR_0", new SubInstruction("INIR_0", baseCodes));

		baseCodes = new byte[] { (byte) 0XED, (byte) 0XA8 };
		subInstructions.put("LDD_0", new SubInstruction("LDD_0", baseCodes));

		baseCodes = new byte[] { (byte) 0XED, (byte) 0XB8 };
		subInstructions.put("LDDR_0", new SubInstruction("LDDR_0", baseCodes));

		baseCodes = new byte[] { (byte) 0XED, (byte) 0XA0 };
		subInstructions.put("LDI_0", new SubInstruction("LDI_0", baseCodes));

		baseCodes = new byte[] { (byte) 0XED, (byte) 0XB0 };
		subInstructions.put("LDIR_0", new SubInstruction("LDIR_0", baseCodes));

		baseCodes = new byte[] { (byte) 0XED, (byte) 0X44 };
		subInstructions.put("NEG_0", new SubInstruction("NEG_0", baseCodes));

		baseCodes = new byte[] { (byte) 0X00 };
		subInstructions.put("NOP_0", new SubInstruction("NOP_0", baseCodes));

		baseCodes = new byte[] { (byte) 0XED, (byte) 0XBB };
		subInstructions.put("OTDR_0", new SubInstruction("OTDR_0", baseCodes));

		baseCodes = new byte[] { (byte) 0XED, (byte) 0XB3 };
		subInstructions.put("OTIR_0", new SubInstruction("OTIR_0", baseCodes));

		baseCodes = new byte[] { (byte) 0XED, (byte) 0XAB };
		subInstructions.put("OUTD_0", new SubInstruction("OUTD_0", baseCodes));

		baseCodes = new byte[] { (byte) 0XED, (byte) 0XA3 };
		subInstructions.put("OUTI_0", new SubInstruction("OUTI_0", baseCodes));

		baseCodes = new byte[] { (byte) 0XED, (byte) 0X4D };
		subInstructions.put("RETI_0", new SubInstruction("RETI_0", baseCodes));

		baseCodes = new byte[] { (byte) 0XED, (byte) 0X45 };
		subInstructions.put("RETN_0", new SubInstruction("RETN_0", baseCodes));
		//
		baseCodes = new byte[] { (byte) 0X17 };
		subInstructions.put("RLA_0", new SubInstruction("RLA_0", baseCodes));

		baseCodes = new byte[] { (byte) 0X07 };
		subInstructions.put("RLCA_0", new SubInstruction("RLCA_0", baseCodes));

		baseCodes = new byte[] { (byte) 0XED, (byte) 0X6F };
		subInstructions.put("RLD_0", new SubInstruction("RLD_0", baseCodes));
		//

		baseCodes = new byte[] { (byte) 0X1F };
		subInstructions.put("RRA_0", new SubInstruction("RRA_0", baseCodes));

		baseCodes = new byte[] { (byte) 0X0F };
		subInstructions.put("RRCA_0", new SubInstruction("RCA_0", baseCodes));

		baseCodes = new byte[] { (byte) 0XED, (byte) 0X67 };
		subInstructions.put("RRD_0", new SubInstruction("RRD_0", baseCodes));

		baseCodes = new byte[] { (byte) 0X37 };
		subInstructions.put("SCF_0", new SubInstruction("SCF_0", baseCodes));

		// Zero and One Argument

		baseCodes = new byte[] { (byte) 0XC9 };
		subInstructions.put("RET_2", new SubInstruction("RET_2", baseCodes));

		baseCodes = new byte[] { (byte) 0XC0 };
		subInstructions.put("RET_1", new SubInstruction("RET_1", Z80.COND, baseCodes));

		// One Argument ...................................................

		baseCodes = new byte[] { (byte) 0XC7 };
		subInstructions.put("RST_1", new SubInstruction("RST_1", Z80.EXP_RST, baseCodes));


		baseCodes = new byte[] { (byte) 0XF6, (byte) 0X00 };
		subInstructions.put("OR_3", new SubInstruction("OR_3", Z80.EXP_DB, baseCodes));

		baseCodes = new byte[] { (byte) 0XD6, (byte) 0X00 };
		subInstructions.put("SUB_3", new SubInstruction("SUB_3", Z80.EXP_DB, baseCodes));

		baseCodes = new byte[] { (byte) 0XEE, (byte) 0X00 };
		subInstructions.put("XOR_3", new SubInstruction("XOR_3", Z80.EXP_DB, baseCodes));



		baseCodes = new byte[] { (byte) 0XDD, (byte) 0XB6, (byte) 0X00 };
		subInstructions.put("OR_1", new SubInstruction("OR_1", Z80.IND_XYd, baseCodes));

		baseCodes = new byte[] { (byte) 0XDD, (byte) 0X96, (byte) 0X00 };
		subInstructions.put("SUB_1", new SubInstruction("SUB_1", Z80.IND_XYd, baseCodes));

		baseCodes = new byte[] { (byte) 0XDD, (byte) 0XAE, (byte) 0X00 };
		subInstructions.put("XOR_1", new SubInstruction("XOR_1", Z80.IND_XYd, baseCodes));

		baseCodes = new byte[] { (byte) 0XDD, (byte) 0XCB, (byte) 0X00, (byte) 0X16 };
		subInstructions.put("RL_1", new SubInstruction("RL_1", Z80.IND_XYd, baseCodes));

		baseCodes = new byte[] { (byte) 0XDD, (byte) 0XCB, (byte) 0X00, (byte) 0X06 };
		subInstructions.put("RLC_1", new SubInstruction("RLC_1", Z80.IND_XYd, baseCodes));

		baseCodes = new byte[] { (byte) 0XDD, (byte) 0XCB, (byte) 0X00, (byte) 0X1E };
		subInstructions.put("RR_1", new SubInstruction("RR_1", Z80.IND_XYd, baseCodes));

		baseCodes = new byte[] { (byte) 0XDD, (byte) 0XCB, (byte) 0X00, (byte) 0X0E };
		subInstructions.put("RRC_1", new SubInstruction("RRC_1", Z80.IND_XYd, baseCodes));

		baseCodes = new byte[] { (byte) 0XDD, (byte) 0XCB, (byte) 0X00, (byte) 0X26 };
		subInstructions.put("SLA_1", new SubInstruction("SLA_1", Z80.IND_XYd, baseCodes));

		baseCodes = new byte[] { (byte) 0XDD, (byte) 0XCB, (byte) 0X00, (byte) 0X2E };
		subInstructions.put("SRA_1", new SubInstruction("SRA_1", Z80.IND_XYd, baseCodes));

		baseCodes = new byte[] { (byte) 0XDD, (byte) 0XCB, (byte) 0X00, (byte) 0X3E };
		subInstructions.put("SRL_1", new SubInstruction("SRL_1", Z80.IND_XYd, baseCodes));

		baseCodes = new byte[] { (byte) 0XC1 };
		subInstructions.put("POP_1", new SubInstruction("POP_1", Z80.R_MAIN, baseCodes));

		baseCodes = new byte[] { (byte) 0XC5 };
		subInstructions.put("PUSH_1", new SubInstruction("PUSH_1", Z80.R_MAIN, baseCodes));

		baseCodes = new byte[] { (byte) 0XDD, (byte) 0XE1 };
		subInstructions.put("POP_2", new SubInstruction("POP_2", Z80.LIT_IXY, baseCodes));

		baseCodes = new byte[] { (byte) 0XDD, (byte) 0XE5 };
		subInstructions.put("PUSH_2", new SubInstruction("PUSH_2", Z80.LIT_IXY, baseCodes));

		baseCodes = new byte[] { (byte) 0XB0 };
		subInstructions.put("OR_2", new SubInstruction("OR_2", Z80.R_MAIN, baseCodes));

		baseCodes = new byte[] { (byte) 0X90 };
		subInstructions.put("SUB_2", new SubInstruction("SUB_2", Z80.R_MAIN, baseCodes));

		baseCodes = new byte[] { (byte) 0XA8 };
		subInstructions.put("XOR_2", new SubInstruction("XOR_2", Z80.R_MAIN, baseCodes));
		//
		baseCodes = new byte[] { (byte) 0XCB, (byte) 0X10 };
		subInstructions.put("RL_2", new SubInstruction("RL_2", Z80.R_MAIN, baseCodes));

		baseCodes = new byte[] { (byte) 0XCB, (byte) 0X00 };
		subInstructions.put("RLC_2", new SubInstruction("RLC_2", Z80.R_MAIN, baseCodes));

		baseCodes = new byte[] { (byte) 0XCB, (byte) 0X18 };
		subInstructions.put("RR_2", new SubInstruction("RR_2", Z80.R_MAIN, baseCodes));

		baseCodes = new byte[] { (byte) 0XCB, (byte) 0X08 };
		subInstructions.put("RRC_2", new SubInstruction("RRC_2", Z80.R_MAIN, baseCodes));

		baseCodes = new byte[] { (byte) 0XCB, (byte) 0X20 };
		subInstructions.put("SLA_2", new SubInstruction("SLA_2", Z80.R_MAIN, baseCodes));

		baseCodes = new byte[] { (byte) 0XCB, (byte) 0X28 };
		subInstructions.put("SRA_2", new SubInstruction("SRA_2", Z80.R_MAIN, baseCodes));

		baseCodes = new byte[] { (byte) 0XCB, (byte) 0X38 };
		subInstructions.put("SRL_2", new SubInstruction("SRL_2", Z80.R_MAIN, baseCodes));

		// Two Arguments ...................................................

		baseCodes = new byte[] { (byte) 0XD3, (byte) 0X00 };
		subInstructions.put("OUT_2", new SubInstruction("OUT_2", Z80.EXP_ADDR, "A", baseCodes));

		baseCodes = new byte[] { (byte) 0XDD, (byte) 0XCB, (byte) 0X00, (byte) 0X86 };
		subInstructions.put("RES_1", new SubInstruction("RES_1", Z80.EXP_BIT, Z80.IND_XYd, baseCodes));

		baseCodes = new byte[] { (byte) 0XDD, (byte) 0XCB, (byte) 0X00, (byte) 0XC6 };
		subInstructions.put("SET_1", new SubInstruction("SET_1", Z80.EXP_BIT, Z80.IND_XYd, baseCodes));

		baseCodes = new byte[] { (byte) 0XCB, (byte) 0X80 };
		subInstructions.put("RES_2", new SubInstruction("RES_2", Z80.EXP_BIT, Z80.R_MAIN, baseCodes));

		baseCodes = new byte[] { (byte) 0XCB, (byte) 0XC0 };
		subInstructions.put("SET_2", new SubInstruction("SET_2", Z80.EXP_BIT, Z80.R_MAIN, baseCodes));

		baseCodes = new byte[] { (byte) 0XED, (byte) 0X41 };
		subInstructions.put("OUT_1", new SubInstruction("OUT_1", Z80.IND_C, Z80.R_MAIN, baseCodes));

	// Two Arguments ...................................................
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// ADC
		baseCodes = new byte[] { (byte) 0XDD, (byte) 0X8E, (byte) 0X00 };
		subInstructions.put("ADC_1", new SubInstruction("ADC_1", Z80.LIT_A, Z80.IND_XYd, baseCodes));

		baseCodes = new byte[] { (byte) 0X88 };
		subInstructions.put("ADC_2", new SubInstruction("ADC_2", Z80.LIT_A, Z80.R_MAIN, baseCodes));

		baseCodes = new byte[] { (byte) 0XCE, (byte) 0X00 };
		subInstructions.put("ADC_3", new SubInstruction("ADC_3", Z80.LIT_A, Z80.EXP, baseCodes));

		baseCodes = new byte[] { (byte) 0XED, (byte) 0X4A };
		subInstructions.put("ADC_4", new SubInstruction("ADC_4", Z80.LIT_HL, Z80.R16_SP, baseCodes));
		// ADD
		baseCodes = new byte[] { (byte) 0XDD, (byte) 0X86, (byte) 0X00 };
		subInstructions.put("ADD_1", new SubInstruction("ADD_1", Z80.LIT_A, Z80.IND_XYd, baseCodes));

		baseCodes = new byte[] { (byte) 0X80 };
		subInstructions.put("ADD_2", new SubInstruction("ADD_2", Z80.LIT_A, Z80.R_MAIN, baseCodes));

		baseCodes = new byte[] { (byte) 0XC6, (byte) 0X00 };
		subInstructions.put("ADD_3", new SubInstruction("ADD_3", Z80.LIT_A, Z80.EXP, baseCodes));

		baseCodes = new byte[] { (byte) 0X09 };
		subInstructions.put("ADD_4", new SubInstruction("ADD_4", Z80.LIT_HL, Z80.R16_SP, baseCodes));

		baseCodes = new byte[] { (byte) 0XDD, (byte) 0X09 };
		subInstructions.put("ADD_5", new SubInstruction("ADD_5", Z80.LIT_IXY, Z80.LIT_IXY, baseCodes));
		//AND		
		baseCodes = new byte[] { (byte) 0XE6, (byte) 0X00 };
		subInstructions.put("AND_3", new SubInstruction("AND_3", Z80.EXP_DB, baseCodes));
		
		baseCodes = new byte[] { (byte) 0XA0 };
		subInstructions.put("AND_2", new SubInstruction("AND_2", Z80.R_MAIN, baseCodes));

		baseCodes = new byte[] { (byte) 0XDD, (byte) 0XA6, (byte) 0X00 };
		subInstructions.put("AND_1", new SubInstruction("AND_1", Z80.IND_XYd, baseCodes));
//BIT
		baseCodes = new byte[] { (byte) 0XDD, (byte) 0XCB, (byte) 0X00, (byte) 0X46 };
		subInstructions.put("BIT_1", new SubInstruction("BIT_1", Z80.EXP_BIT, Z80.IND_XYd, baseCodes));

		baseCodes = new byte[] { (byte) 0XCB, (byte) 0X40 };
		subInstructions.put("BIT_2", new SubInstruction("BIT_2", Z80.EXP_BIT, Z80.R_MAIN, baseCodes));
//CALL	
		baseCodes = new byte[] { (byte) 0XC4, (byte) 0X00, (byte) 0X00 };
		subInstructions.put("CALL_1", new SubInstruction("CALL_1", Z80.COND, Z80.EXP_DW, baseCodes));

		baseCodes = new byte[] { (byte) 0XCD, (byte) 0X00, (byte) 0X00 };
		subInstructions.put("CALL_2", new SubInstruction("CALL_2", Z80.EXP_DW, baseCodes));
//CP
		baseCodes = new byte[] { (byte) 0XDD, (byte) 0XBE, (byte) 0X00 };
		subInstructions.put("CP_1", new SubInstruction("CP_1", Z80.IND_XYd, baseCodes));

		baseCodes = new byte[] { (byte) 0XB8 };
		subInstructions.put("CP_2", new SubInstruction("CP_2", Z80.R_MAIN, baseCodes));

		baseCodes = new byte[] { (byte) 0XFE, (byte) 0X00 };
		subInstructions.put("CP_3", new SubInstruction("CP_3", Z80.EXP_DB, baseCodes));
//DEC
		baseCodes = new byte[] { (byte) 0XDD, (byte) 0X35, (byte) 0X00 };
		subInstructions.put("DEC_1", new SubInstruction("DEC_1", Z80.IND_XYd, baseCodes));

		baseCodes = new byte[] { (byte) 0X0B };
		subInstructions.put("DEC_2", new SubInstruction("DEC_2", Z80.R_MAIN, baseCodes));

		baseCodes = new byte[] { (byte) 0XDD, (byte) 0X2B };
		subInstructions.put("DEC_3", new SubInstruction("DEC_3", Z80.LIT_IXY, baseCodes));

		baseCodes = new byte[] { (byte) 0X05 };
		subInstructions.put("DEC_4", new SubInstruction("DEC_4", Z80.R8M_S3, baseCodes));
//DJNZ
		baseCodes = new byte[] { (byte) 0X10, (byte) 0X00 };
		subInstructions.put("DJNZ_1", new SubInstruction("DJNZ_1", Z80.EXP_DB, baseCodes));
//EX
		baseCodes = new byte[] { (byte) 0XE3 };
		subInstructions.put("EX_1", new SubInstruction("EX_1", Z80.IND_SP, Z80.LIT_HL, baseCodes));

		baseCodes = new byte[] { (byte) 0XDD, (byte) 0XE3 };
		subInstructions.put("EX_2", new SubInstruction("EX_2", Z80.IND_SP, Z80.LIT_IXY, baseCodes));

		baseCodes = new byte[] { (byte) 0X08 };
		subInstructions.put("EX_3", new SubInstruction("EX_2", Z80.LIT_AF, Z80.LIT_AFp, baseCodes));

		baseCodes = new byte[] { (byte) 0XEB, };
		subInstructions.put("EX_4", new SubInstruction("EX_4", Z80.LIT_DE, Z80.LIT_HL, baseCodes));
//IM
		baseCodes = new byte[] { (byte) 0XED, (byte) 0X46 };
		subInstructions.put("IM_1", new SubInstruction("IM_1", Z80.EXP_IM, baseCodes));
//IN
		baseCodes = new byte[] { (byte) 0XED, (byte) 0X78 };
		subInstructions.put("IN_1", new SubInstruction("IN_1", Z80.LIT_A,Z80.IND_C, baseCodes));
	
		baseCodes = new byte[] { (byte) 0XDB, (byte) 0X40 };
		subInstructions.put("IN_2", new SubInstruction("IN_2", Z80.LIT_A,Z80.EXP_ADDR, baseCodes));
	
		baseCodes = new byte[] { (byte) 0XED, (byte) 0X40 };
		subInstructions.put("IN_3", new SubInstruction("IN_3", Z80.R_MAIN,Z80.IND_C, baseCodes));
//INC
		baseCodes = new byte[] { (byte) 0XDD, (byte) 0X34, (byte) 0X00 };
		subInstructions.put("INC_1", new SubInstruction("INC_1", Z80.IND_XYd, baseCodes));

		baseCodes = new byte[] { (byte) 0X03 };
		subInstructions.put("INC_2", new SubInstruction("INC_2", Z80.R_MAIN, baseCodes));

		baseCodes = new byte[] { (byte) 0XDD, (byte) 0X23 };
		subInstructions.put("INC_3", new SubInstruction("INC_3", Z80.LIT_IXY, baseCodes));

		baseCodes = new byte[] { (byte) 0X04 };
		subInstructions.put("INC_4", new SubInstruction("INC_4", Z80.R8M_S3, baseCodes));
//JP
		baseCodes = new byte[] { (byte) 0XC2, (byte) 0X00, (byte) 0X00 };
		subInstructions.put("JP_1", new SubInstruction("JP_1", Z80.COND, Z80.EXP_DW, baseCodes));

		baseCodes = new byte[] { (byte) 0XDD, (byte) 0XE9 };
		subInstructions.put("JP_2", new SubInstruction("JP_2", Z80.IND_XY, baseCodes));

		baseCodes = new byte[] { (byte) 0XE9 };
		subInstructions.put("JP_3", new SubInstruction("JP_3", Z80.IND_HL, baseCodes));

		baseCodes = new byte[] { (byte) 0XC3, (byte) 0X00, (byte) 0X00 };
		subInstructions.put("JP_4", new SubInstruction("JP_4", Z80.EXP_DW, baseCodes));
//JR
		baseCodes = new byte[] { (byte) 0X20, (byte) 0X00 };
		subInstructions.put("JR_1", new SubInstruction("JR_1", Z80.COND_LIMITED, Z80.EXP_DB, baseCodes));

		baseCodes = new byte[] { (byte) 0X18, (byte) 0X00 };
		subInstructions.put("JR_2", new SubInstruction("JR_2", Z80.EXP_DB, baseCodes));
//LD
		baseCodes = new byte[] { (byte) 0XDD,  (byte) 0X7E, (byte) 0X00 };
		subInstructions.put("LD_1", new SubInstruction("LD_1", Z80.LIT_A, Z80.IND_XYd, baseCodes));
		
		baseCodes = new byte[] { (byte) 0X0A};
		subInstructions.put("LD_2", new SubInstruction("LD_2", Z80.LIT_A, Z80.IND_BCDE, baseCodes));
		
		baseCodes = new byte[] { (byte) 0XED,  (byte) 0X57 };
		subInstructions.put("LD_3", new SubInstruction("LD_3", Z80.LIT_A, Z80.LIT_RI, baseCodes));
		
		baseCodes = new byte[] { (byte) 0X78,   };
		subInstructions.put("LD_4", new SubInstruction("LD_4", Z80.LIT_A, Z80.R_MAIN, baseCodes));
		
		baseCodes = new byte[] { (byte) 0X3A,  (byte) 0X00, (byte) 0X00 };
		subInstructions.put("LD_5", new SubInstruction("LD_5", Z80.LIT_A, Z80.EXP_ADDR, baseCodes));
		
		baseCodes = new byte[] { (byte) 0X3E,  (byte) 0X00 };
		subInstructions.put("LD_6", new SubInstruction("LD_6", Z80.LIT_A, Z80.EXP, baseCodes));
	
		baseCodes = new byte[] { (byte) 0X2a,  (byte) 0X00, (byte) 0X00 };
		subInstructions.put("LD_7", new SubInstruction("LD_7", Z80.LIT_HL, Z80.EXP_ADDR, baseCodes));
		
		baseCodes = new byte[] { (byte) 0XF9 };
		subInstructions.put("LD_8", new SubInstruction("LD_8", Z80.LIT_SP, Z80.LIT_HL, baseCodes));

		baseCodes = new byte[] { (byte) 0XDD ,(byte) 0XF9 };
		subInstructions.put("LD_9", new SubInstruction("LD_9", Z80.LIT_SP, Z80.LIT_IXY, baseCodes));
		
		baseCodes = new byte[] { (byte) 0XED ,(byte) 0X7B, (byte) 0X00, (byte) 0X00 };
		subInstructions.put("LD_10", new SubInstruction("LD_10", Z80.LIT_SP, Z80.EXP, baseCodes));

		baseCodes = new byte[] { (byte) 0X31 ,(byte) 0X00, (byte) 0X00 };
		subInstructions.put("LD_11", new SubInstruction("LD_11", Z80.LIT_SP, Z80.EXP_ADDR, baseCodes));
		
		baseCodes = new byte[] { (byte) 0X02 };
		subInstructions.put("LD_12", new SubInstruction("LD_12", Z80.IND_BCDE, Z80.LIT_A, baseCodes));

		baseCodes = new byte[] { (byte) 0XDD, (byte) 0X70, (byte) 0X00 };
		subInstructions.put("LD_13", new SubInstruction("LD_13", Z80.IND_XYd, Z80.R_MAIN, baseCodes));

		baseCodes = new byte[] { (byte) 0XDD, (byte) 0X36, (byte) 0X00, (byte) 0X00 };
		subInstructions.put("LD_14", new SubInstruction("LD_14", Z80.IND_XYd, Z80.EXP, baseCodes));
		
		baseCodes = new byte[] { (byte) 0XED, (byte) 0X47 };
		subInstructions.put("LD_15", new SubInstruction("LD_15", Z80.LIT_RI, Z80.LIT_A, baseCodes));
		
		baseCodes = new byte[] { (byte) 0XED, (byte) 0X4B, (byte) 0X00 , (byte) 0X00 };
		subInstructions.put("LD_16", new SubInstruction("LD_16", Z80.R16_BDH, Z80.EXP_ADDR, baseCodes));
		
		baseCodes = new byte[] { (byte) 0X01,  (byte) 0X00, (byte) 0X00  };
		subInstructions.put("LD_17", new SubInstruction("LD_17", Z80.R16_BDH, Z80.EXP, baseCodes));
		
		baseCodes = new byte[] { (byte) 0XDD,  (byte) 0X2A,(byte) 0X00, (byte) 0X00  };
		subInstructions.put("LD_18", new SubInstruction("LD_18", Z80.LIT_IXY, Z80.EXP_ADDR, baseCodes));
		
		baseCodes = new byte[] { (byte) 0XDD,  (byte) 0X21,(byte) 0X00, (byte) 0X00  };
		subInstructions.put("LD_19", new SubInstruction("LD_19", Z80.LIT_IXY, Z80.EXP, baseCodes));
		
		baseCodes = new byte[] { (byte) 0X40 };
		subInstructions.put("LD_20", new SubInstruction("LD_20", Z80.R81, Z80.R_MAIN, baseCodes));

		baseCodes = new byte[] { (byte) 0XDD,  (byte) 0X46 ,(byte) 0X00};
		subInstructions.put("LD_21", new SubInstruction("LD_21", Z80.R81, Z80.IND_XYd, baseCodes));

		baseCodes = new byte[] { (byte) 0X06,  (byte) 0X00};
		subInstructions.put("LD_22", new SubInstruction("LD_22", Z80.R81, Z80.EXP, baseCodes));

		baseCodes = new byte[] { (byte) 0X70 };
		subInstructions.put("LD_23", new SubInstruction("LD_23", Z80.IND_HL, Z80.R_MAIN, baseCodes));

		baseCodes = new byte[] { (byte) 0X36, (byte) 0X00 };
		subInstructions.put("LD_24", new SubInstruction("LD_24", Z80.IND_HL, Z80.EXP, baseCodes));

		//OR		
		baseCodes = new byte[] { (byte) 0XDD, (byte) 0XB6, (byte) 0X00 };
		subInstructions.put("OR_1", new SubInstruction("OR_1", Z80.IND_XYd, baseCodes));

		baseCodes = new byte[] { (byte) 0XB0 };
		subInstructions.put("OR_2", new SubInstruction("OR_2", Z80.R_MAIN, baseCodes));
		
		baseCodes = new byte[] { (byte) 0XF6, (byte) 0X00 };
		subInstructions.put("OR_3", new SubInstruction("OR_3", Z80.EXP_DB, baseCodes));
		

	



		//////////////////////////////////////////////////////////////////////////////////////////////////////////

	}// appInit

}//
