;ByHexCode_08_DDCB0toDDCB.asm

nnWord	EQU		01234H	; nnWord
aByte	EQU		5AH		; n
dByte	EQU		0DDH	; d
;    PC  ->	$

XDDCBdd45:  ; d 45	BIT 0, (IX + d)*
LDDCBdd46:	BIT 0, (IX + dByte)
XDDCBdd47:  ; d 47	BIT 0, (IX + d)*
XDDCBdd48:  ; d 48	BIT 1, (IX + d)*
XDDCBdd49:  ; d 49	BIT 1, (IX + d)*
XDDCBdd4A:  ; d 4A	BIT 1, (IX + d)*
XDDCBdd4B:  ; d 4B	BIT 1, (IX + d)*
XDDCBdd4C:  ; d 4C	BIT 1, (IX + d)*
XDDCBdd4D:  ; d 4D	BIT 1, (IX + d)*
LDDCBdd4E:	BIT 1, (IX + dByte)
XDDCBdd4F:  ; d 4F	BIT 1, (IX + d)*
XDDCBdd50:  ; d 50	BIT 2, (IX + d)*
XDDCBdd51:  ; d 51	BIT 2, (IX + d)*
XDDCBdd52:  ; d 52	BIT 2, (IX + d)*
XDDCBdd53:  ; d 53	BIT 2, (IX + d)*
XDDCBdd54:  ; d 54	BIT 2, (IX + d)*
XDDCBdd55:  ; d 55	BIT 2, (IX + d)*
LDDCBdd56:	BIT 2, (IX + dByte)
XDDCBdd57:  ; d 57	BIT 2, (IX + d)*
XDDCBdd58:  ; d 58	BIT 3, (IX + d)*
XDDCBdd59:  ; d 59	BIT 3, (IX + d)*
XDDCBdd5A:  ; d 5A	BIT 3, (IX + d)*
XDDCBdd5B:  ; d 5B	BIT 3, (IX + d)*
XDDCBdd5C:  ; d 5C	BIT 3, (IX + d)*
XDDCBdd5D:  ; d 5D	BIT 3, (IX + d)*
LDDCBdd5E:	BIT 3, (IX + dByte)
XDDCBdd5F:  ; d 5F	BIT 3, (IX + d)*
XDDCBdd60:  ; d 60	BIT 4, (IX + d)*
XDDCBdd61:  ; d 61	BIT 4, (IX + d)*
XDDCBdd62:  ; d 62	BIT 4, (IX + d)*
XDDCBdd63:  ; d 63	BIT 4, (IX + d)*
XDDCBdd64:  ; d 64	BIT 4, (IX + d)*
XDDCBdd65:  ; d 65	BIT 4, (IX + d)*
LDDCBdd66:	BIT 4, (IX + dByte)
XDDCBdd67:  ; d 67	BIT 4, (IX + d)*
XDDCBdd68:  ; d 68	BIT 5, (IX + d)*
XDDCBdd69:  ; d 69	BIT 5, (IX + d)*
XDDCBdd6A:  ; d 6A	BIT 5, (IX + d)*
XDDCBdd6B:  ; d 6B	BIT 5, (IX + d)*
XDDCBdd6C:  ; d 6C	BIT 5, (IX + d)*
XDDCBdd6D:  ; d 6D	BIT 5, (IX + d)*
LDDCBdd6E:	BIT 5, (IX + dByte)
XDDCBdd6F:  ; d 6F	BIT 5, (IX + d)*
XDDCBdd70:  ; d 70	BIT 6, (IX + d)*
XDDCBdd71:  ; d 71	BIT 6, (IX + d)*
XDDCBdd72:  ; d 72	BIT 6, (IX + d)*
XDDCBdd73:  ; d 73	BIT 6, (IX + d)*
XDDCBdd74:  ; d 74	BIT 6, (IX + d)*
XDDCBdd75:  ; d 75	BIT 6, (IX + d)*
LDDCBdd76:	BIT 6, (IX + dByte)
XDDCBdd77:  ; d 77	BIT 6, (IX + d)*
XDDCBdd78:  ; d 78	BIT 7, (IX + d)*
XDDCBdd79:  ; d 79	BIT 7, (IX + d)*
XDDCBdd7A:  ; d 7A	BIT 7, (IX + d)*
XDDCBdd7B:  ; d 7B	BIT 7, (IX + d)*
XDDCBdd7C:  ; d 7C	BIT 7, (IX + d)*
XDDCBdd7D:  ; d 7D	BIT 7, (IX + d)*
LDDCBdd7E:	BIT 7, (IX + dByte)
XDDCBdd7F:  ; d 7F	BIT 7, (IX + d)*
XDDCBdd80:  ; d 80	LD B, RES 0, (IX + d)*
XDDCBdd81:  ; d 81	LD C, RES 0, (IX + d)*
XDDCBdd82:  ; d 82	LD D, RES 0, (IX + d)*
XDDCBdd83:  ; d 83	LD E, RES 0, (IX + d)*
XDDCBdd84:  ; d 84	LD H, RES 0, (IX + d)*
XDDCBdd85:  ; d 85	LD L, RES 0, (IX + d)*
LDDCBdd86:	RES 0, (IX + dByte)
XDDCBdd87:  ; d 87	LD A, RES 0, (IX + d)*
XDDCBdd88:  ; d 88	LD B, RES 1, (IX + d)*
XDDCBdd89:  ; d 89	LD C, RES 1, (IX + d)*
XDDCBdd8A:  ; d 8A	LD D, RES 1, (IX + d)*
XDDCBdd8B:  ; d 8B	LD E, RES 1, (IX + d)*
XDDCBdd8C:  ; d 8C	LD H, RES 1, (IX + d)*
XDDCBdd8D:  ; d 8D	LD L, RES 1, (IX + d)*
LDDCBdd8E:	RES 1, (IX + dByte)
XDDCBdd8F:  ; d 8F	LD A, RES 1, (IX + d)*
XDDCBdd90:  ; d 90	LD B, RES 2, (IX + d)*
XDDCBdd91:  ; d 91	LD C, RES 2, (IX + d)*
XDDCBdd92:  ; d 92	LD D, RES 2, (IX + d)*
XDDCBdd93:  ; d 93	LD E, RES 2, (IX + d)*
XDDCBdd94:  ; d 94	LD H, RES 2, (IX + d)*
XDDCBdd95:  ; d 95	LD L, RES 2, (IX + d)*
LDDCBdd96:	RES 2, (IX + dByte)
XDDCBdd97:  ; d 97	LD A, RES 2, (IX + d)*
