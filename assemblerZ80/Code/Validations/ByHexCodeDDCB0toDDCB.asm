;ByHexCodeDDCB0toDDCB.asm

nnWord	EQU		01234H	; nnWord
aByte	EQU		5AH		; n
dByte	EQU		0DDH	; d
;    PC  ->	$

XDDCB: ; d 45	BIT 0, (IX + d)*
LDDCBdd46:	BIT 0, (IX + dByte)
XDDCB: ; d 47	BIT 0, (IX + d)*
XDDCB: ; d 48	BIT 1, (IX + d)*
XDDCB: ; d 49	BIT 1, (IX + d)*
XDDCB: ; d 4A	BIT 1, (IX + d)*
XDDCB: ; d 4B	BIT 1, (IX + d)*
XDDCB: ; d 4C	BIT 1, (IX + d)*
XDDCB: ; d 4D	BIT 1, (IX + d)*
LDDCBdd4E:	BIT 1, (IX + dByte)
XDDCB: ; d 4F	BIT 1, (IX + d)*
XDDCB: ; d 50	BIT 2, (IX + d)*
XDDCB: ; d 51	BIT 2, (IX + d)*
XDDCB: ; d 52	BIT 2, (IX + d)*
XDDCB: ; d 53	BIT 2, (IX + d)*
XDDCB: ; d 54	BIT 2, (IX + d)*
XDDCB: ; d 55	BIT 2, (IX + d)*
LDDCBdd56:	BIT 2, (IX + dByte)
XDDCB: ; d 57	BIT 2, (IX + d)*
XDDCB: ; d 58	BIT 3, (IX + d)*
XDDCB: ; d 59	BIT 3, (IX + d)*
XDDCB: ; d 5A	BIT 3, (IX + d)*
XDDCB: ; d 5B	BIT 3, (IX + d)*
XDDCB: ; d 5C	BIT 3, (IX + d)*
XDDCB: ; d 5D	BIT 3, (IX + d)*
LDDCBdd5E:	BIT 3, (IX + dByte)
XDDCB: ; d 5F	BIT 3, (IX + d)*
XDDCB: ; d 60	BIT 4, (IX + d)*
XDDCB: ; d 61	BIT 4, (IX + d)*
XDDCB: ; d 62	BIT 4, (IX + d)*
XDDCB: ; d 63	BIT 4, (IX + d)*
XDDCB: ; d 64	BIT 4, (IX + d)*
XDDCB: ; d 65	BIT 4, (IX + d)*
LDDCBdd66:	BIT 4, (IX + dByte)
XDDCB: ; d 67	BIT 4, (IX + d)*
XDDCB: ; d 68	BIT 5, (IX + d)*
XDDCB: ; d 69	BIT 5, (IX + d)*
XDDCB: ; d 6A	BIT 5, (IX + d)*
XDDCB: ; d 6B	BIT 5, (IX + d)*
XDDCB: ; d 6C	BIT 5, (IX + d)*
XDDCB: ; d 6D	BIT 5, (IX + d)*
LDDCBdd6E:	BIT 5, (IX + dByte)
XDDCB: ; d 6F	BIT 5, (IX + d)*
XDDCB: ; d 70	BIT 6, (IX + d)*
XDDCB: ; d 71	BIT 6, (IX + d)*
XDDCB: ; d 72	BIT 6, (IX + d)*
XDDCB: ; d 73	BIT 6, (IX + d)*
XDDCB: ; d 74	BIT 6, (IX + d)*
XDDCB: ; d 75	BIT 6, (IX + d)*
LDDCBdd76:	BIT 6, (IX + dByte)
XDDCB: ; d 77	BIT 6, (IX + d)*
XDDCB: ; d 78	BIT 7, (IX + d)*
XDDCB: ; d 79	BIT 7, (IX + d)*
XDDCB: ; d 7A	BIT 7, (IX + d)*
XDDCB: ; d 7B	BIT 7, (IX + d)*
XDDCB: ; d 7C	BIT 7, (IX + d)*
XDDCB: ; d 7D	BIT 7, (IX + d)*
LDDCBdd7E:	BIT 7, (IX + dByte)
XDDCB: ; d 7F	BIT 7, (IX + d)*
XDDCB: ; d 80	LD B, RES 0, (IX + d)*
XDDCB: ; d 81	LD C, RES 0, (IX + d)*
XDDCB: ; d 82	LD D, RES 0, (IX + d)*
XDDCB: ; d 83	LD E, RES 0, (IX + d)*
XDDCB: ; d 84	LD H, RES 0, (IX + d)*
XDDCB: ; d 85	LD L, RES 0, (IX + d)*
LDDCBdd86:	RES 0, (IX + dByte)
XDDCB: ; d 87	LD A, RES 0, (IX + d)*
XDDCB: ; d 88	LD B, RES 1, (IX + d)*
XDDCB: ; d 89	LD C, RES 1, (IX + d)*
XDDCB: ; d 8A	LD D, RES 1, (IX + d)*
XDDCB: ; d 8B	LD E, RES 1, (IX + d)*
XDDCB: ; d 8C	LD H, RES 1, (IX + d)*
XDDCB: ; d 8D	LD L, RES 1, (IX + d)*
LDDCBdd8E:	RES 1, (IX + dByte)
XDDCB: ; d 8F	LD A, RES 1, (IX + d)*
XDDCB: ; d 90	LD B, RES 2, (IX + d)*
XDDCB: ; d 91	LD C, RES 2, (IX + d)*
XDDCB: ; d 92	LD D, RES 2, (IX + d)*
XDDCB: ; d 93	LD E, RES 2, (IX + d)*
XDDCB: ; d 94	LD H, RES 2, (IX + d)*
XDDCB: ; d 95	LD L, RES 2, (IX + d)*
LDDCBdd96:	RES 2, (IX + dByte)
XDDCB: ; d 97	LD A, RES 2, (IX + d)*