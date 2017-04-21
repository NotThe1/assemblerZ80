package assembler;

public enum Argument {
	R8,R16_AF,R16_SP,IMD8,IMD16,RI8,RIDX,
	R8_R8,R8_IMD8,R8_RI8,R8_RIDX,RI8_R8,RIDX_R8,RI8_IMD8,
	RIDX_IMD8,IMDIND8_R8,R8_IMDIND8
}//enum Argument
/**
	R8		Register 8  Bit
	R16_AF	Register 16 Bit  excludes the SP Register
	R16_SP	Register 16 Bit  excludes the AF Register
	IMD8	Immediate Data     8   Bit
	IMD16	Immediate Data     16  Bit

	RI8		Register Indirect   8  Bit
	RI16	Register Indirect  16  Bit
	RIDX	Register Index		8	Bit (IX & IY)
	IMDIND8	Indirect from Immediate data 8 Bit
	
	
	
	
	
	
	
	ID8		Indirect  Data     8   Bit	Thru 16 bit registers
	ID16	Indirect  Data     16  Bit	Thru 16 bit registers
**/