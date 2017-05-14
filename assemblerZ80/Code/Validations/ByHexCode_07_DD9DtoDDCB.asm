;ByHexCode_07_DD9DtoDDCB.asm

nnWord	EQU		01234H	; nnWord
aByte	EQU		5AH		; n
dByte	EQU		0DDH	; d
;    PC  ->	$

XDD9D:	;SBC A, IXL*
LDD9E:	SBC A, (IX + dByte)
XDDA4:	;AND IXH*
XDDA5:	;AND IXL*
LDDA6:	AND (IX + dByte)
XDDAC:	;XOR IXH*
XDDAD:	;XOR IXL*
LDDAE:	XOR (IX + dByte)
XDDB4:	;OR IXH*
XDDB5:	;OR IXL*
LDDB6:	OR (IX + dByte)
XDDBC:	;CP IXH*
XDDBD:	;CP IXL*
LDDBE:	CP (IX + dByte)
XDDCBdd00:  ; d 00	LD B, RLC (IX + d)*
XDDCBdd01:  ; d 01	LD C, RLC (IX + d)*
XDDCBdd02:  ; d 02	LD D, RLC (IX + d)*
XDDCBdd03:  ; d 03	LD E, RLC (IX + d)*
XDDCBdd04:  ; d 04	LD H, RLC (IX + d)*
XDDCBdd05:  ; d 05	LD L, RLC (IX + d)*
LDDCBdd06:	RLC (IX + dByte)
XDDCBdd07:  ; d 07	LD A, RLC (IX + d)*
XDDCBdd08:  ; d 08	LD B, RRC (IX + d)*
XDDCBdd09:  ; d 09	LD C, RRC (IX + d)*
XDDCBdd0A:  ; d 0A	LD D, RRC (IX + d)*
XDDCBdd0B:  ; d 0B	LD E, RRC (IX + d)*
XDDCBdd0C:  ; d 0C	LD H, RRC (IX + d)*
XDDCBdd0D:  ; d 0D	LD L, RRC (IX + d)*
LDDCBdd0E:	RRC (IX + dByte)
XDDCBdd0F:  ; d 0F	LD A, RRC (IX + d)*
XDDCBdd10:  ; d 10	LD B, RL (IX + d)*
XDDCBdd11:  ; d 11	LD C, RL (IX + d)*
XDDCBdd12:  ; d 12	LD D, RL (IX + d)*
XDDCBdd13:  ; d 13	LD E, RL (IX + d)*
XDDCBdd14:  ; d 14	LD H, RL (IX + d)*
XDDCBdd15:  ; d 15	LD L, RL (IX + d)*
LDDCBdd16:	RL (IX + dByte)
XDDCBdd17:  ; d 17	LD A, RL (IX + d)*
XDDCBdd18:  ; d 18	LD B, RR (IX + d)*
XDDCBdd19:  ; d 19	LD C, RR (IX + d)*
XDDCBdd1A:  ; d 1A	LD D, RR (IX + d)*
XDDCBdd1B:  ; d 1B	LD E, RR (IX + d)*
XDDCBdd1C:  ; d 1C	LD H, RR (IX + d)*
XDDCBdd1D:  ; d 1D	LD L, RR (IX + d)*
LDDCBdd1E:	RR (IX + dByte)
XDDCBdd1F:  ; d 1F	LD A, RR (IX + d)*
XDDCBdd20:  ; d 20	LD B, SLA (IX + d)*
XDDCBdd21:  ; d 21	LD C, SLA (IX + d)*
XDDCBdd22:  ; d 22	LD D, SLA (IX + d)*
XDDCBdd23:  ; d 23	LD E, SLA (IX + d)*
XDDCBdd24:  ; d 24	LD H, SLA (IX + d)*
XDDCBdd25:  ; d 25	LD L, SLA (IX + d)*
LDDCBdd26:	SLA (IX + dByte)
XDDCBdd27:  ; d 27	LD A, SLA (IX + d)*
XDDCBdd28:  ; d 28	LD B, SRA (IX + d)*
XDDCBdd29:  ; d 29	LD C, SRA (IX + d)*
XDDCBdd2A:  ; d 2A	LD D, SRA (IX + d)*
XDDCBdd2B:  ; d 2B	LD E, SRA (IX + d)*
XDDCBdd2C:  ; d 2C	LD H, SRA (IX + d)*
XDDCBdd2D:  ; d 2D	LD L, SRA (IX + d)*
LDDCBdd2E:	SRA (IX + dByte)
XDDCBdd2F:  ; d 2F	LD A, SRA (IX + d)*
XDDCBdd30:  ; d 30	LD B, SLL (IX + d)*
XDDCBdd31:  ; d 31	LD C, SLL (IX + d)*
XDDCBdd32:  ; d 32	LD D, SLL (IX + d)*
XDDCBdd33:  ; d 33	LD E, SLL (IX + d)*
XDDCBdd34:  ; d 34	LD H, SLL (IX + d)*
XDDCBdd35:  ; d 35	LD L, SLL (IX + d)*
XDDCBdd36:  ; d 36	SLL (IX + d)*
XDDCBdd37:  ; d 37	LD A, SLL (IX + d)*
XDDCBdd38:  ; d 38	LD B, SRL (IX + d)*
XDDCBdd39:  ; d 39	LD C, SRL (IX + d)*
XDDCBdd3A:  ; d 3A	LD D, SRL (IX + d)*
XDDCBdd3B:  ; d 3B	LD E, SRL (IX + d)*
XDDCBdd3C:  ; d 3C	LD H, SRL (IX + d)*
XDDCBdd3D:  ; d 3D	LD L, SRL (IX + d)*
LDDCBdd3E:	SRL (IX + dByte)
XDDCBdd3F:  ; d 3F	LD A, SRL (IX + d)*
XDDCBdd40:  ; d 40	BIT 0, (IX + d)*
XDDCBdd41:  ; d 41	BIT 0, (IX + d)*
XDDCBdd42:  ; d 42	BIT 0, (IX + d)*
XDDCBdd43:  ; d 43	BIT 0, (IX + d)*
XDDCBdd44:  ; d 44	BIT 0, (IX + d)*
