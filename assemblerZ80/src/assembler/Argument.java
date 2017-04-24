package assembler;

public enum Argument {
	R8, R8M, R16_AF, R16_SP, R16_IX, R16_BDH, R16_BCDE, R16_XY, R_RI, 
	IND_XYd, IND_XY, COND, COND1, EXP, EXP_ADD, LIT_A, LIT_I_C, LIT_AF, 
	LIT_AFp, LIT_DE, LIT_HL, LIT_I_HL, LIT_I_SP
}// enum Argument

// R8 Register 8 Bit - A|B|C|D|E|H|L
// R8M Register 8 Bit - A|B|C|D|E|H|L|(HL)|M
// R16_AF Register 16 Bit - BC|DE|HL|AF
// R16_SP Register 16 Bit - BC|DE|HL|SP
// R16_IX Register 16 Bit - BC|DE|SP|IX
// R16_BDH Registers BC,DE & HL - BC|DE|HL
// R16_BCDE Registers BC & DE - BC|DE
// R16_XY Registers IX or IY - IX|IY
// R_RI Registers I & R - I | R
//
//
//
// IND_XYd Indirect from IX+d or IY+d - IND_Xd|IND_Yd
// IND_XY Indirect from IX or IY - IND_Xd|IND_Yd
// COND All Condition Codes - C|M|NC|NZ|P|PE|PO|Z
// COND1 Some Condition Codes - C|NC|Z|NZ
//
// EXP An Expression
// EXP_ADD An Expression as address - (EXP)
//
// LIT_A Literal "A"
//
// LIT_I_C Literal "(C)"
//
// LIT_AF Literal "AF"
// LIT_AFp Literal "AF'"
// ?? LIT_BC Literal "BC"
// LIT_DE Literal "DE"
// LIT_HL Literal "HL"
//
// ?? LIT_I_AF Literal "(AF)"
// ?? LIT_I_BC Literal "(BC)"
// ?? LIT_I_DE Literal "(DE)"
// LIT_I_HL Literal "(HL)"
// LIT_I_SP Literal "(SP)"
