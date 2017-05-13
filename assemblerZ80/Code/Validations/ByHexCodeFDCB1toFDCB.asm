;ByHexCodeFDCB1toFDCB.asm

nnWord	EQU		01234H	; nn 
aByte	EQU		5AH		; n
dByte	EQU		0DDH	; d
;    PC  ->	$

LFDCBdd76:	BIT 6, (IY + dByte)
XFDCB: ; d 77	BIT 6, (IY + d)*
XFDCB: ; d 78	BIT 7, (IY + d)*
XFDCB: ; d 79	BIT 7, (IY + d)*
XFDCB: ; d 7A	BIT 7, (IY + d)*
XFDCB: ; d 7B	BIT 7, (IY + d)*
XFDCB: ; d 7C	BIT 7, (IY + d)*
XFDCB: ; d 7D	BIT 7, (IY + d)*
LFDCBdd7E:	BIT 7, (IY + dByte)
XFDCB: ; d 7F	BIT 7, (IY + d)*
XFDCB: ; d 80	LD B, RES 0, (IY + d)*
XFDCB: ; d 81	LD C, RES 0, (IY + d)*
XFDCB: ; d 82	LD D, RES 0, (IY + d)*
XFDCB: ; d 83	LD E, RES 0, (IY + d)*
XFDCB: ; d 84	LD H, RES 0, (IY + d)*
XFDCB: ; d 85	LD L, RES 0, (IY + d)*
LFDCBdd86:	RES 0, (IY + dByte)
XFDCB: ; d 87	LD A, RES 0, (IY + d)*
XFDCB: ; d 88	LD B, RES 1, (IY + d)*
XFDCB: ; d 89	LD C, RES 1, (IY + d)*
XFDCB: ; d 8A	LD D, RES 1, (IY + d)*
XFDCB: ; d 8B	LD E, RES 1, (IY + d)*
XFDCB: ; d 8C	LD H, RES 1, (IY + d)*
XFDCB: ; d 8D	LD L, RES 1, (IY + d)*
LFDCBdd8E:	RES 1, (IY + dByte)
XFDCB: ; d 8F	LD A, RES 1, (IY + d)*
XFDCB: ; d 90	LD B, RES 2, (IY + d)*
XFDCB: ; d 91	LD C, RES 2, (IY + d)*
XFDCB: ; d 92	LD D, RES 2, (IY + d)*
XFDCB: ; d 93	LD E, RES 2, (IY + d)*
XFDCB: ; d 94	LD H, RES 2, (IY + d)*
XFDCB: ; d 95	LD L, RES 2, (IY + d)*
LFDCBdd96:	RES 2, (IY + dByte)
XFDCB: ; d 97	LD A, RES 2, (IY + d)*
XFDCB: ; d 98	LD B, RES 3, (IY + d)*
XFDCB: ; d 99	LD C, RES 3, (IY + d)*
XFDCB: ; d 9A	LD D, RES 3, (IY + d)*
XFDCB: ; d 9B	LD E, RES 3, (IY + d)*
XFDCB: ; d 9C	LD H, RES 3, (IY + d)*
XFDCB: ; d 9D	LD L, RES 3, (IY + d)*
LFDCBdd9E:	RES 3, (IY + dByte)
XFDCB: ; d 9F	LD A, RES 3, (IY + d)*
XFDCB: ; d A0	LD B, RES 4, (IY + d)*
XFDCB: ; d A1	LD C, RES 4, (IY + d)*
XFDCB: ; d A2	LD D, RES 4, (IY + d)*
XFDCB: ; d A3	LD E, RES 4, (IY + d)*
XFDCB: ; d A4	LD H, RES 4, (IY + d)*
XFDCB: ; d A5	LD L, RES 4, (IY + d)*
LFDCBddA6:	RES 4, (IY + dByte)
XFDCB: ; d A7	LD A, RES 4, (IY + d)*
XFDCB: ; d A8	LD B, RES 5, (IY + d)*
XFDCB: ; d A9	LD C, RES 5, (IY + d)*
XFDCB: ; d AA	LD D, RES 5, (IY + d)*
XFDCB: ; d AB	LD E, RES 5, (IY + d)*
XFDCB: ; d AC	LD H, RES 5, (IY + d)*
XFDCB: ; d AD	LD L, RES 5, (IY + d)*
LFDCBddAE:	RES 5, (IY + dByte)
XFDCB: ; d AF	LD A, RES 5, (IY + d)*
XFDCB: ; d B0	LD B, RES 6, (IY + d)*
XFDCB: ; d B1	LD C, RES 6, (IY + d)*
XFDCB: ; d B2	LD D, RES 6, (IY + d)*
XFDCB: ; d B3	LD E, RES 6, (IY + d)*
XFDCB: ; d B4	LD H, RES 6, (IY + d)*
XFDCB: ; d B5	LD L, RES 6, (IY + d)*
LFDCBddB6:	RES 6, (IY + dByte)
XFDCB: ; d B7	LD A, RES 6, (IY + d)*
XFDCB: ; d B8	LD B, RES 7, (IY + d)*
XFDCB: ; d B9	LD C, RES 7, (IY + d)*
XFDCB: ; d BA	LD D, RES 7, (IY + d)*
XFDCB: ; d BB	LD E, RES 7, (IY + d)*
XFDCB: ; d BC	LD H, RES 7, (IY + d)*
XFDCB: ; d BD	LD L, RES 7, (IY + d)*
LFDCBddBE:	RES 7, (IY + dByte)
XFDCB: ; d BF	LD A, RES 7, (IY + d)*
XFDCB: ; d C0	LD B, SET 0, (IY + d)*
XFDCB: ; d C1	LD C, SET 0, (IY + d)*
XFDCB: ; d C2	LD D, SET 0, (IY + d)*
XFDCB: ; d C3	LD E, SET 0, (IY + d)*
XFDCB: ; d C4	LD H, SET 0, (IY + d)*
XFDCB: ; d C5	LD L, SET 0, (IY + d)*
LFDCBddC6:	SET 0, (IY + dByte)
XFDCB: ; d C7	LD A, SET 0, (IY + d)*
XFDCB: ; d C8	LD B, SET 1, (IY + d)*