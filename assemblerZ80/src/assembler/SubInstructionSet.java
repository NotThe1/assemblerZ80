package assembler;

import java.util.HashMap;

public class SubInstructionSet {
	private HashMap<String, SubInstruction> subInstructions = new HashMap<>();

	private static SubInstructionSet instance = new SubInstructionSet();

	private SubInstructionSet() {
		appInit();
	}// constructor

	public static SubInstructionSet getInstance() {
		return instance;
	}// getInstance

	private void appInit() {

		Byte[] baseCodes = new Byte[] { (byte) 0XDD, (byte) 0X86, (byte) 0X00 };
		subInstructions.put("ADC_1",
				new SubInstruction("ADC_1", 3, "A", Z80.patLIT_A, "IX", Z80.patIND_XYd, baseCodes));
		
		baseCodes = new Byte[] { (byte) 0X80};
		subInstructions.put("ADC_2",
				new SubInstruction("ADC_1", 3, "A", Z80.patLIT_A, "IX", Z80.patR8M, baseCodes));

		 baseCodes = new Byte[] { (byte) 0XC6, (byte) 0X00};
		subInstructions.put("ADC_3",
				new SubInstruction("ADC_1", 3, "A", Z80.patLIT_A, "IX", Z80.patEXP, baseCodes));
		
		 baseCodes = new Byte[] { (byte) 0XC6, (byte) 0X00};
		subInstructions.put("ADC_4",
				new SubInstruction("ADC_1", 3, "A", Z80.patLIT_HL, "SP", Z80.patR16_SP, baseCodes));
		

	}// appInit

}//
