;ByHexCode_13_FDCB1toFDCB.asm

nnWord	EQU		01234H	; nn 
aByte	EQU		5AH		; n
dByte	EQU		0DDH	; d
;    PC  ->	$

LFDCBdd76:	BIT 6, (IY + dByte)
XFDCBdd77:  ; d 77	BIT 6, (IY + d)*
XFDCBdd78:  ; d 78	BIT 7, (IY + d)*
XFDCBdd79:  ; d 79	BIT 7, (IY + d)*
XFDCBdd7A:  ; d 7A	BIT 7, (IY + d)*
XFDCBdd7B:  ; d 7B	BIT 7, (IY + d)*
XFDCBdd7C:  ; d 7C	BIT 7, (IY + d)*
XFDCBdd7D:  ; d 7D	BIT 7, (IY + d)*
LFDCBdd7E:	BIT 7, (IY + dByte)
XFDCBdd7F:  ; d 7F	BIT 7, (IY + d)*
XFDCBdd80:  ; d 80	LD B, RES 0, (IY + d)*
XFDCBdd81:  ; d 81	LD C, RES 0, (IY + d)*
XFDCBdd82:  ; d 82	LD D, RES 0, (IY + d)*
XFDCBdd83:  ; d 83	LD E, RES 0, (IY + d)*
XFDCBdd84:  ; d 84	LD H, RES 0, (IY + d)*
XFDCBdd85:  ; d 85	LD L, RES 0, (IY + d)*
LFDCBdd86:	RES 0, (IY + dByte)
XFDCBdd87:  ; d 87	LD A, RES 0, (IY + d)*
XFDCBdd88:  ; d 88	LD B, RES 1, (IY + d)*
XFDCBdd89:  ; d 89	LD C, RES 1, (IY + d)*
XFDCBdd8A:  ; d 8A	LD D, RES 1, (IY + d)*
XFDCBdd8B:  ; d 8B	LD E, RES 1, (IY + d)*
XFDCBdd8C:  ; d 8C	LD H, RES 1, (IY + d)*
XFDCBdd8D:  ; d 8D	LD L, RES 1, (IY + d)*
LFDCBdd8E:	RES 1, (IY + dByte)
XFDCBdd8F:  ; d 8F	LD A, RES 1, (IY + d)*
XFDCBdd90:  ; d 90	LD B, RES 2, (IY + d)*
XFDCBdd91:  ; d 91	LD C, RES 2, (IY + d)*
XFDCBdd92:  ; d 92	LD D, RES 2, (IY + d)*
XFDCBdd93:  ; d 93	LD E, RES 2, (IY + d)*
XFDCBdd94:  ; d 94	LD H, RES 2, (IY + d)*
XFDCBdd95:  ; d 95	LD L, RES 2, (IY + d)*
LFDCBdd96:	RES 2, (IY + dByte)
XFDCBdd97:  ; d 97	LD A, RES 2, (IY + d)*
XFDCBdd98:  ; d 98	LD B, RES 3, (IY + d)*
XFDCBdd99:  ; d 99	LD C, RES 3, (IY + d)*
XFDCBdd9A:  ; d 9A	LD D, RES 3, (IY + d)*
XFDCBdd9B:  ; d 9B	LD E, RES 3, (IY + d)*
XFDCBdd9C:  ; d 9C	LD H, RES 3, (IY + d)*
XFDCBdd9D:  ; d 9D	LD L, RES 3, (IY + d)*
LFDCBdd9E:	RES 3, (IY + dByte)
XFDCBdd9F:  ; d 9F	LD A, RES 3, (IY + d)*
XFDCBddA0:  ; d A0	LD B, RES 4, (IY + d)*
XFDCBddA1:  ; d A1	LD C, RES 4, (IY + d)*
XFDCBddA2:  ; d A2	LD D, RES 4, (IY + d)*
XFDCBddA3:  ; d A3	LD E, RES 4, (IY + d)*
XFDCBddA4:  ; d A4	LD H, RES 4, (IY + d)*
XFDCBddA5:  ; d A5	LD L, RES 4, (IY + d)*
LFDCBddA6:	RES 4, (IY + dByte)
XFDCBddA7:  ; d A7	LD A, RES 4, (IY + d)*
XFDCBddA8:  ; d A8	LD B, RES 5, (IY + d)*
XFDCBddA9:  ; d A9	LD C, RES 5, (IY + d)*
XFDCBddAA:  ; d AA	LD D, RES 5, (IY + d)*
XFDCBddAB:  ; d AB	LD E, RES 5, (IY + d)*
XFDCBddAC:  ; d AC	LD H, RES 5, (IY + d)*
XFDCBddAD:  ; d AD	LD L, RES 5, (IY + d)*
LFDCBddAE:	RES 5, (IY + dByte)
XFDCBddAF:  ; d AF	LD A, RES 5, (IY + d)*
XFDCBddB0:  ; d B0	LD B, RES 6, (IY + d)*
XFDCBddB1:  ; d B1	LD C, RES 6, (IY + d)*
XFDCBddB2:  ; d B2	LD D, RES 6, (IY + d)*
XFDCBddB3:  ; d B3	LD E, RES 6, (IY + d)*
XFDCBddB4:  ; d B4	LD H, RES 6, (IY + d)*
XFDCBddB5:  ; d B5	LD L, RES 6, (IY + d)*
LFDCBddB6:	RES 6, (IY + dByte)
XFDCBddB7:  ; d B7	LD A, RES 6, (IY + d)*
XFDCBddB8:  ; d B8	LD B, RES 7, (IY + d)*
XFDCBddB9:  ; d B9	LD C, RES 7, (IY + d)*
XFDCBddBA:  ; d BA	LD D, RES 7, (IY + d)*
XFDCBddBB:  ; d BB	LD E, RES 7, (IY + d)*
XFDCBddBC:  ; d BC	LD H, RES 7, (IY + d)*
XFDCBddBD:  ; d BD	LD L, RES 7, (IY + d)*
LFDCBddBE:	RES 7, (IY + dByte)
XFDCBddBF:  ; d BF	LD A, RES 7, (IY + d)*
XFDCBddC0:  ; d C0	LD B, SET 0, (IY + d)*
XFDCBddC1:  ; d C1	LD C, SET 0, (IY + d)*
XFDCBddC2:  ; d C2	LD D, SET 0, (IY + d)*
XFDCBddC3:  ; d C3	LD E, SET 0, (IY + d)*
XFDCBddC4:  ; d C4	LD H, SET 0, (IY + d)*
XFDCBddC5:  ; d C5	LD L, SET 0, (IY + d)*
LFDCBddC6:	SET 0, (IY + dByte)
XFDCBddC7:  ; d C7	LD A, SET 0, (IY + d)*
XFDCBddC8:  ; d C8	LD B, SET 1, (IY + d)*
