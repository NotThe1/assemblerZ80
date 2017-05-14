;ByHexCode_09_DDCB1toDDCB.asm

nnWord	EQU		01234H	; nnWord
aByte	EQU		5AH		; n
dByte	EQU		0DDH	; d
;    PC  ->	$

XDDCBdd98:  ; d 98	LD B, RES 3, (IX + d)*
XDDCBdd99:  ; d 99	LD C, RES 3, (IX + d)*
XDDCBdd9A:  ; d 9A	LD D, RES 3, (IX + d)*
XDDCBdd9B:  ; d 9B	LD E, RES 3, (IX + d)*
XDDCBdd9C:  ; d 9C	LD H, RES 3, (IX + d)*
XDDCBdd9D:  ; d 9D	LD L, RES 3, (IX + d)*
LLDDCBdd9E:	RES 3, (IX + dByte)
XDDCBdd9F:  ; d 9F	LD A, RES 3, (IX + d)*
XDDCBddA0:  ; d A0	LD B, RES 4, (IX + d)*
XDDCBddA1:  ; d A1	LD C, RES 4, (IX + d)*
XDDCBddA2:  ; d A2	LD D, RES 4, (IX + d)*
XDDCBddA3:  ; d A3	LD E, RES 4, (IX + d)*
XDDCBddA4:  ; d A4	LD H, RES 4, (IX + d)*
XDDCBddA5:  ; d A5	LD L, RES 4, (IX + d)*
LDDCBddA6:	RES 4, (IX + dByte)
XDDCBddA7:  ; d A7	LD A, RES 4, (IX + d)*
XDDCBddA8:  ; d A8	LD B, RES 5, (IX + d)*
XDDCBddA9:  ; d A9	LD C, RES 5, (IX + d)*
XDDCBddAA:  ; d AA	LD D, RES 5, (IX + d)*
XDDCBddAB:  ; d AB	LD E, RES 5, (IX + d)*
XDDCBddAC:  ; d AC	LD H, RES 5, (IX + d)*
XDDCBddAD:  ; d AD	LD L, RES 5, (IX + d)*
LDDCBddAE:	RES 5, (IX + dByte)
XDDCBddAF:  ; d AF	LD A, RES 5, (IX + d)*
XDDCBddB0:  ; d B0	LD B, RES 6, (IX + d)*
XDDCBddB1:  ; d B1	LD C, RES 6, (IX + d)*
XDDCBddB2:  ; d B2	LD D, RES 6, (IX + d)*
XDDCBddB3:  ; d B3	LD E, RES 6, (IX + d)*
XDDCBddB4:  ; d B4	LD H, RES 6, (IX + d)*
XDDCBddB5:  ; d B5	LD L, RES 6, (IX + d)*
LDDCBddB6:	RES 6, (IX + dByte)
XDDCBddB7:  ; d B7	LD A, RES 6, (IX + d)*
XDDCBddB8:  ; d B8	LD B, RES 7, (IX + d)*
XDDCBddB9:  ; d B9	LD C, RES 7, (IX + d)*
XDDCBddBA:  ; d BA	LD D, RES 7, (IX + d)*
XDDCBddBB:  ; d BB	LD E, RES 7, (IX + d)*
XDDCBddBC:  ; d BC	LD H, RES 7, (IX + d)*
XDDCBddBD:  ; d BD	LD L, RES 7, (IX + d)*
LDDCBddBE:	RES 7, (IX + dByte)
XDDCBddBF:  ; d BF	LD A, RES 7, (IX + d)*
XDDCBddC0:  ; d C0	LD B, SET 0, (IX + d)*
XDDCBddC1:  ; d C1	LD C, SET 0, (IX + d)*
XDDCBddC2:  ; d C2	LD D, SET 0, (IX + d)*
XDDCBddC3:  ; d C3	LD E, SET 0, (IX + d)*
XDDCBddC4:  ; d C4	LD H, SET 0, (IX + d)*
XDDCBddC5:  ; d C5	LD L, SET 0, (IX + d)*
LDDCBddC6:	SET 0, (IX + dByte)
XDDCBddC7:  ; d C7	LD A, SET 0, (IX + d)*
XDDCBddC8:  ; d C8	LD B, SET 1, (IX + d)*
XDDCBddC9:  ; d C9	LD C, SET 1, (IX + d)*
XDDCBddCA:  ; d CA	LD D, SET 1, (IX + d)*
XDDCBddCB:  ; d CB	LD E, SET 1, (IX + d)*
XDDCBddCC:  ; d CC	LD H, SET 1, (IX + d)*
XDDCBddCD:  ; d CD	LD L, SET 1, (IX + d)*
LDDCBddCE:	SET 1, (IX + dByte)
XDDCBddCF:  ; d CF	LD A, SET 1, (IX + d)*
XDDCBddD0:  ; d D0	LD B, SET 2, (IX + d)*
XDDCBddD1:  ; d D1	LD C, SET 2, (IX + d)*
XDDCBddD2:  ; d D2	LD D, SET 2, (IX + d)*
XDDCBddD3:  ; d D3	LD E, SET 2, (IX + d)*
XDDCBddD4:  ; d D4	LD H, SET 2, (IX + d)*
XDDCBddD5:  ; d D5	LD L, SET 2, (IX + d)*
LDDCBddD6:	SET 2, (IX + dByte)
XDDCBddD7:  ; d D7	LD A, SET 2, (IX + d)*
XDDCBddD8:  ; d D8	LD B, SET 3, (IX + d)*
XDDCBddD9:  ; d D9	LD C, SET 3, (IX + d)*
XDDCBddDA:  ; d DA	LD D, SET 3, (IX + d)*
XDDCBddDB:  ; d DB	LD E, SET 3, (IX + d)*
XDDCBddDC:  ; d DC	LD H, SET 3, (IX + d)*
XDDCBddDD:  ; d DD	LD L, SET 3, (IX + d)*
LDDCBddDE:	SET 3, (IX + dByte)
XDDCBddDF:  ; d DF	LD A, SET 3, (IX + d)*
XDDCBddE0:  ; d E0	LD B, SET 4, (IX + d)*
XDDCBddE1:  ; d E1	LD C, SET 4, (IX + d)*
XDDCBddE2:  ; d E2	LD D, SET 4, (IX + d)*
XDDCBddE3:  ; d E3	LD E, SET 4, (IX + d)*
XDDCBddE4:  ; d E4	LD H, SET 4, (IX + d)*
XDDCBddE5:  ; d E5	LD L, SET 4, (IX + d)*
LDDCBddE6:	SET 4, (IX + dByte)
XDDCBddE7:  ; d E7	LD A, SET 4, (IX + d)*
XDDCBddE8:  ; d E8	LD B, SET 5, (IX + d)*
XDDCBddE9:  ; d E9	LD C, SET 5, (IX + d)*
XDDCBddEA:  ; d EA	LD D, SET 5, (IX + d)*
