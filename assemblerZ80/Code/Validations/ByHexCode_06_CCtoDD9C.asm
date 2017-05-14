;ByHexCode_06_CCtoDD9C.asm

nnWord	EQU		01234H	; nnWord
aByte	EQU		5AH		; n
dByte	EQU		0DDH	; d
;    PC  ->	$

LCC:	CALL Z, nnWord
LCD:	CALL nnWord
LCE:	ADC A, aByte
LCF:	RST 8h
LD0:	RET NC
LD1:	POP DE
LD2:	JP NC, nnWord
LD3:	OUT (aByte), A
LD4:	CALL NC, nnWord
LD5:	PUSH DE
LD6:	SUB aByte
LD7:	RST 10h
LD8:	RET C
LD9:	EXX
LDA:	JP C, nnWord
LDB:	IN A, (aByte)
LDC:	CALL C, nnWord
LDD09:	ADD IX, BC
LDD19:	ADD IX, DE
LDD21:	LD IX, nnWord
LDD22:	LD (nnWord), IX
LDD23:	INC IX
XDD24:	;INC IXH*
XDD25:	;DEC IXH*
XDD26:	;LD IXH, aByte*
LDD29:	ADD IX, IX
LDD2A:	LD IX, (nnWord)
LDD2B:	DEC IX
XDD2C:	;INC IXL*
XDD2D:	;DEC IXL*
XDD2E:	;LD IXL, aByte*
LDD34:	INC (IX + dByte)
LDD35:	DEC (IX + dByte)
LDD36:	LD (IX + dByte), aByte
LDD39:	ADD IX, SP
XDD44:	;LD B, IXH*
XDD45:	;LD B, IXL*
LDD46:	LD B, (IX + dByte)
XDD4C:	;LD C, IXH*
XDD4D:	;LD C, IXL*
LDD4E:	LD C, (IX + dByte)
LDD54:	;LD D, IXH*
LDD55:	;LD D, IXL*
LDD56:	LD D, (IX + dByte)
XDD5C:	;LD E, IXH*
XDD5D:	;LD E, IXL*
LDD5E:	LD E, (IX + dByte)
XDD60:	;LD IXH, B*
XDD61:	;LD IXH, C*
XDD62:	;LD IXH, D*
XDD63:	;LD IXH, E*
XDD64:	;LD IXH, IXH*
XDD65:	;LD IXH, IXL*
LDD66:	LD H, (IX + dByte)
XDD67:	;LD IXH, A*
XDD68:	;LD IXL, B*
XDD69:	;LD IXL, C*
XDD6A:	;LD IXL, D*
XDD6B:	;LD IXL, E*
XDD6C:	;LD IXL, IXH*
XDD6D:	;LD IXL, IXL*
LDD6E:	LD L, (IX + dByte)
XDD6F:	;LD IXL, A*
LDD70:	LD (IX + dByte), B
LDD71:	LD (IX + dByte), C
LDD72:	LD (IX + dByte), D
LDD73:	LD (IX + dByte), E
LDD74:	LD (IX + dByte), H
LDD75:	LD (IX + dByte), L
LDD77:	LD (IX + dByte), A
XDD7C:	;LD A, IXH*
XDD7D:	;LD A, IXL*
LDD7E:	LD A, (IX + dByte)
XDD84:	;ADD A, IXH*
XDD85:	;ADD A, IXL*
LDD86:	ADD A, (IX + dByte)
XDD8C:	;ADC A, IXH*
XDD8D:	;ADC A, IXL*
LDD8E:	ADC A, (IX + dByte)
XDD94:	;SUB IXH*
XDD95:	;SUB IXL*
LDD96:	SUB (IX + dByte)
XDD9C:	;SBC A, IXH*