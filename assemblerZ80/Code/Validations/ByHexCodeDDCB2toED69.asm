;ByHexCodeDDCB2toED69.asm

nnWord	EQU		01234H	; nn 
aByte	EQU		5AH		; n
dByte	EQU		0DDH	; d
;    PC  ->	$

XDDCB: ;d EB	LD E, SET 5, (IX + d)*
XDDCB: ;d EC	LD H, SET 5, (IX + d)*
XDDCB: ;d ED	LD L, SET 5, (IX + d)*
LDDCBddEE:	SET 5, (IX + dByte)
XDDCB: ;d EF	LD A, SET 5, (IX + d)*
XDDCB: ;d F0	LD B, SET 6, (IX + d)*
XDDCB: ;d F1	LD C, SET 6, (IX + d)*
XDDCB: ;d F2	LD D, SET 6, (IX + d)*
XDDCB: ;d F3	LD E, SET 6, (IX + d)*
XDDCB: ;d F4	LD H, SET 6, (IX + d)*
XDDCB: ;d F5	LD L, SET 6, (IX + d)*
LDDCBddF6:	SET 6, (IX + dByte)
XDDCB: ;d F7	LD A, SET 6, (IX + d)*
XDDCB: ;d F8	LD B, SET 7, (IX + d)*
XDDCB: ;d F9	LD C, SET 7, (IX + d)*
XDDCB: ;d FA	LD D, SET 7, (IX + d)*
XDDCB: ;d FB	LD E, SET 7, (IX + d)*
XDDCB: ;d FC	LD H, SET 7, (IX + d)*
XDDCB: ;d FD	LD L, SET 7, (IX + d)*
LDDCBddFE:	SET 7, (IX + dByte)
XDDCB: ;d FF	LD A, SET 7, (IX + d)*

LDDE1:	POP IX
LDDE3:	EX (SP), IX
LDDE5:	PUSH IX
LDDE9:	JP (IX)
LDDF9:	LD SP, IX
LDE:	SBC A, aByte
LDF:	RST 18h
LE0:	RET PO
LE1:	POP HL
LE2:	JP PO, nnWord
LE3:	EX (SP), HL
LE4:	CALL PO, nnWord
LE5:	PUSH HL
LE6:	AND aByte
LE7:	RST 20h
LE8:	RET PE
LE9:	JP (HL)
LEA:	JP PE, (nnWord)
LEB:	EX DE, HL
LEC:	CALL PE, nnWord
LED40:	IN B, (C)
LED41:	OUT (C), B
LED42:	SBC HL, BC
LED43:	LD (nnWord), BC
LED44:	NEG
LED45:	RETN
LED46:	IM 0
LED47:	LD I, A
LED48:	IN C, (C)
LED49:	OUT (C), C
LED4A:	ADC HL, BC
LED4B:	LD BC, (nnWord)
XED4C:	; NEG*
LED4D:	RETI
XED4E:	; IM 0/1*
LED4F:	LD R, A
LED50:	IN D, (C)
LED51:	OUT (C), D
LED52:	SBC HL, DE
LED53:	LD (nnWord), DE
XED54:	; NEG*
XED55:	; RETN*
LED56:	IM 1
LED57:	LD A, I
LED58:	IN E, (C)
LED59:	OUT (C), E
LED5A:	ADC HL, DE
LED5B:	LD DE, (nnWord)
XED5C:	; NEG*
XED5D:	; RETN*
LED5E:	IM 2
LED5F:	LD A, R
LED60:	IN H, (C)
LED61:	OUT (C), H
LED62:	SBC HL, HL
LED22:	LD (nnWord), HL	; ED 63 lo hi
XED64:	; NEG*
XED65:	; RETN*
XED66:	; IM 0*
LED67:	RRD
LED68:	IN L, (C)
LED69:	OUT (C), L