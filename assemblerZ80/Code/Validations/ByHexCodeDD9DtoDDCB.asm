;ByHexCodeDD9DtoDDCB.asm

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
XDDCB:	; d 00	LD B, RLC (IX + d)*
XDDCB:	; d 01	LD C, RLC (IX + d)*
XDDCB:	; d 02	LD D, RLC (IX + d)*
XDDCB:	; d 03	LD E, RLC (IX + d)*
XDDCB:	; d 04	LD H, RLC (IX + d)*
XDDCB:	; d 05	LD L, RLC (IX + d)*
LDDCBdd06:	RLC (IX + dByte)
XDDCB:	; d 07	LD A, RLC (IX + d)*
XDDCB:	; d 08	LD B, RRC (IX + d)*
XDDCB:	; d 09	LD C, RRC (IX + d)*
XDDCB:	; d 0A	LD D, RRC (IX + d)*
XDDCB:	; d 0B	LD E, RRC (IX + d)*
XDDCB:	; d 0C	LD H, RRC (IX + d)*
XDDCB:	; d 0D	LD L, RRC (IX + d)*
LDDCBdd0E:	RRC (IX + dByte)
XDDCB:	; d 0F	LD A, RRC (IX + d)*
XDDCB:	; d 10	LD B, RL (IX + d)*
XDDCB:	; d 11	LD C, RL (IX + d)*
XDDCB:	; d 12	LD D, RL (IX + d)*
XDDCB:	; d 13	LD E, RL (IX + d)*
XDDCB:	; d 14	LD H, RL (IX + d)*
XDDCB:	; d 15	LD L, RL (IX + d)*
LDDCBdd16:	RL (IX + dByte)
XDDCB:	; d 17	LD A, RL (IX + d)*
XDDCB:	; d 18	LD B, RR (IX + d)*
XDDCB:	; d 19	LD C, RR (IX + d)*
XDDCB:	; d 1A	LD D, RR (IX + d)*
XDDCB:	; d 1B	LD E, RR (IX + d)*
XDDCB:	; d 1C	LD H, RR (IX + d)*
XDDCB:	; d 1D	LD L, RR (IX + d)*
LDDCBdd1E:	RR (IX + dByte)
XDDCB:	; d 1F	LD A, RR (IX + d)*
XDDCB:	; d 20	LD B, SLA (IX + d)*
XDDCB:	; d 21	LD C, SLA (IX + d)*
XDDCB:	; d 22	LD D, SLA (IX + d)*
XDDCB:	; d 23	LD E, SLA (IX + d)*
XDDCB:	; d 24	LD H, SLA (IX + d)*
XDDCB:	; d 25	LD L, SLA (IX + d)*
LDDCBdd26:	SLA (IX + dByte)
XDDCB:	; d 27	LD A, SLA (IX + d)*
XDDCB:	; d 28	LD B, SRA (IX + d)*
XDDCB:	; d 29	LD C, SRA (IX + d)*
XDDCB:	; d 2A	LD D, SRA (IX + d)*
XDDCB:	; d 2B	LD E, SRA (IX + d)*
XDDCB:	; d 2C	LD H, SRA (IX + d)*
XDDCB:	; d 2D	LD L, SRA (IX + d)*
LDDCBdd2E:	SRA (IX + dByte)
XDDCB:	; d 2F	LD A, SRA (IX + d)*
XDDCB:	; d 30	LD B, SLL (IX + d)*
XDDCB:	; d 31	LD C, SLL (IX + d)*
XDDCB:	; d 32	LD D, SLL (IX + d)*
XDDCB:	; d 33	LD E, SLL (IX + d)*
XDDCB:	; d 34	LD H, SLL (IX + d)*
XDDCB:	; d 35	LD L, SLL (IX + d)*
XDDCB:	; d 36	SLL (IX + d)*
XDDCB:	; d 37	LD A, SLL (IX + d)*
XDDCB:	; d 38	LD B, SRL (IX + d)*
XDDCB:	; d 39	LD C, SRL (IX + d)*
XDDCB:	; d 3A	LD D, SRL (IX + d)*
XDDCB:	; d 3B	LD E, SRL (IX + d)*
XDDCB:	; d 3C	LD H, SRL (IX + d)*
XDDCB:	; d 3D	LD L, SRL (IX + d)*
LDDCBdd3E:	SRL (IX + dByte)
XDDCB:	; d 3F	LD A, SRL (IX + d)*
XDDCB:	; d 40	BIT 0, (IX + d)*
XDDCB:	; d 41	BIT 0, (IX + d)*
XDDCB:	; d 42	BIT 0, (IX + d)*
XDDCB:	; d 43	BIT 0, (IX + d)*
XDDCB:	; d 44	BIT 0, (IX + d)*