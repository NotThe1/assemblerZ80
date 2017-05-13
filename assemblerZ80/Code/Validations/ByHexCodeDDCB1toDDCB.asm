;ByHexCodeDDCB1toDDCB.asm

nnWord	EQU		01234H	; nnWord
aByte	EQU		5AH		; n
dByte	EQU		0DDH	; d
;    PC  ->	$

XDDCB: ; d 98	LD B, RES 3, (IX + d)*
XDDCB: ; d 99	LD C, RES 3, (IX + d)*
XDDCB: ; d 9A	LD D, RES 3, (IX + d)*
XDDCB: ; d 9B	LD E, RES 3, (IX + d)*
XDDCB: ; d 9C	LD H, RES 3, (IX + d)*
XDDCB: ; d 9D	LD L, RES 3, (IX + d)*
XLDDCBdd9E:	RES 3, (IX + dByte)
XDDCB: ; d 9F	LD A, RES 3, (IX + d)*
XDDCB: ; d A0	LD B, RES 4, (IX + d)*
XDDCB: ; d A1	LD C, RES 4, (IX + d)*
XDDCB: ; d A2	LD D, RES 4, (IX + d)*
XDDCB: ; d A3	LD E, RES 4, (IX + d)*
XDDCB: ; d A4	LD H, RES 4, (IX + d)*
XDDCB: ; d A5	LD L, RES 4, (IX + d)*
LDDCBddA6:	RES 4, (IX + dByte)
XDDCB: ; d A7	LD A, RES 4, (IX + d)*
XDDCB: ; d A8	LD B, RES 5, (IX + d)*
XDDCB: ; d A9	LD C, RES 5, (IX + d)*
XDDCB: ; d AA	LD D, RES 5, (IX + d)*
XDDCB: ; d AB	LD E, RES 5, (IX + d)*
XDDCB: ; d AC	LD H, RES 5, (IX + d)*
XDDCB: ; d AD	LD L, RES 5, (IX + d)*
LDDCBddAE:	RES 5, (IX + dByte)
XDDCB: ; d AF	LD A, RES 5, (IX + d)*
XDDCB: ; d B0	LD B, RES 6, (IX + d)*
XDDCB: ; d B1	LD C, RES 6, (IX + d)*
XDDCB: ; d B2	LD D, RES 6, (IX + d)*
XDDCB: ; d B3	LD E, RES 6, (IX + d)*
XDDCB: ; d B4	LD H, RES 6, (IX + d)*
XDDCB: ; d B5	LD L, RES 6, (IX + d)*
LDDCBddB6:	RES 6, (IX + dByte)
XDDCB: ; d B7	LD A, RES 6, (IX + d)*
XDDCB: ; d B8	LD B, RES 7, (IX + d)*
XDDCB: ; d B9	LD C, RES 7, (IX + d)*
XDDCB: ; d BA	LD D, RES 7, (IX + d)*
XDDCB: ; d BB	LD E, RES 7, (IX + d)*
XDDCB: ; d BC	LD H, RES 7, (IX + d)*
XDDCB: ; d BD	LD L, RES 7, (IX + d)*
LDDCBddBE:	RES 7, (IX + dByte)
XDDCB: ; d BF	LD A, RES 7, (IX + d)*
XDDCB: ; d C0	LD B, SET 0, (IX + d)*
XDDCB: ; d C1	LD C, SET 0, (IX + d)*
XDDCB: ; d C2	LD D, SET 0, (IX + d)*
XDDCB: ; d C3	LD E, SET 0, (IX + d)*
XDDCB: ; d C4	LD H, SET 0, (IX + d)*
XDDCB: ; d C5	LD L, SET 0, (IX + d)*
LDDCBddC6:	SET 0, (IX + dByte)
XDDCB: ; d C7	LD A, SET 0, (IX + d)*
XDDCB: ; d C8	LD B, SET 1, (IX + d)*
XDDCB: ; d C9	LD C, SET 1, (IX + d)*
XDDCB: ; d CA	LD D, SET 1, (IX + d)*
XDDCB: ; d CB	LD E, SET 1, (IX + d)*
XDDCB: ; d CC	LD H, SET 1, (IX + d)*
XDDCB: ; d CD	LD L, SET 1, (IX + d)*
LDDCBddCE:	SET 1, (IX + dByte)
XDDCB: ; d CF	LD A, SET 1, (IX + d)*
XDDCB: ; d D0	LD B, SET 2, (IX + d)*
XDDCB: ; d D1	LD C, SET 2, (IX + d)*
XDDCB: ; d D2	LD D, SET 2, (IX + d)*
XDDCB: ; d D3	LD E, SET 2, (IX + d)*
XDDCB: ; d D4	LD H, SET 2, (IX + d)*
XDDCB: ; d D5	LD L, SET 2, (IX + d)*
LDDCBddD6:	SET 2, (IX + dByte)
XDDCB: ; d D7	LD A, SET 2, (IX + d)*
XDDCB: ; d D8	LD B, SET 3, (IX + d)*
XDDCB: ; d D9	LD C, SET 3, (IX + d)*
XDDCB: ; d DA	LD D, SET 3, (IX + d)*
XDDCB: ; d DB	LD E, SET 3, (IX + d)*
XDDCB: ; d DC	LD H, SET 3, (IX + d)*
XDDCB: ; d DD	LD L, SET 3, (IX + d)*
LDDCBddDE:	SET 3, (IX + dByte)
XDDCB: ; d DF	LD A, SET 3, (IX + d)*
XDDCB: ; d E0	LD B, SET 4, (IX + d)*
XDDCB: ; d E1	LD C, SET 4, (IX + d)*
XDDCB: ; d E2	LD D, SET 4, (IX + d)*
XDDCB: ; d E3	LD E, SET 4, (IX + d)*
XDDCB: ; d E4	LD H, SET 4, (IX + d)*
XDDCB: ; d E5	LD L, SET 4, (IX + d)*
LDDCBddE6:	SET 4, (IX + dByte)
XDDCB: ; d E7	LD A, SET 4, (IX + d)*
XDDCB: ; d E8	LD B, SET 5, (IX + d)*
XDDCB: ; d E9	LD C, SET 5, (IX + d)*
XDDCB: ; d EA	LD D, SET 5, (IX + d)*