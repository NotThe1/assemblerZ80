;ByHexCodeFDCB0toFDCB.asm

nnWord	EQU		01234H	; nn 
aByte	EQU		5AH		; n
dByte	EQU		0DDH	; d
;    PC  ->	$

XFDCB: ; d 23	LD E, SLA (IY + d)*
XFDCB: ; d 24	LD H, SLA (IY + d)*
XFDCB: ; d 25	LD L, SLA (IY + d)*
LFDCBdd26:	SLA (IY + dByte)
XFDCB: ; d 27	LD A, SLA (IY + d)*
XFDCB: ; d 28	LD B, SRA (IY + d)*
XFDCB: ; d 29	LD C, SRA (IY + d)*
XFDCB: ; d 2A	LD D, SRA (IY + d)*
XFDCB: ; d 2B	LD E, SRA (IY + d)*
XFDCB: ; d 2C	LD H, SRA (IY + d)*
XFDCB: ; d 2D	LD L, SRA (IY + d)*
LFDCBv2E:	SRA (IY + dByte)
XFDCB: ; d 2F	LD A, SRA (IY + d)*
XFDCB: ; d 30	LD B, SLL (IY + d)*
XFDCB: ; d 31	LD C, SLL (IY + d)*
XFDCB: ; d 32	LD D, SLL (IY + d)*
XFDCB: ; d 33	LD E, SLL (IY + d)*
XFDCB: ; d 34	LD H, SLL (IY + d)*
XFDCB: ; d 35	LD L, SLL (IY + d)*
XFDCB: ; d 36	SLL (IY + d)*
XFDCB: ; d 37	LD A, SLL (IY + d)*
XFDCB: ; d 38	LD B, SRL (IY + d)*
XFDCB: ; d 39	LD C, SRL (IY + d)*
XFDCB: ; d 3A	LD D, SRL (IY + d)*
XFDCB: ; d 3B	LD E, SRL (IY + d)*
XFDCB: ; d 3C	LD H, SRL (IY + d)*
XFDCB: ; d 3D	LD L, SRL (IY + d)*
LFDCBdd3E:	SRL (IY + dByte)
XFDCB: ; d 3F	LD A, SRL (IY + d)*
XFDCB: ; d 40	BIT 0, (IY + d)*
XFDCB: ; d 41	BIT 0, (IY + d)*
XFDCB: ; d 42	BIT 0, (IY + d)*
XFDCB: ; d 43	BIT 0, (IY + d)*
XFDCB: ; d 44	BIT 0, (IY + d)*
XFDCB: ; d 45	BIT 0, (IY + d)*
LFDCBdd46:	BIT 0, (IY + dByte)
XFDCB: ; d 47	BIT 0, (IY + d)*
XFDCB: ; d 48	BIT 1, (IY + d)*
XFDCB: ; d 49	BIT 1, (IY + d)*
XFDCB: ; d 4A	BIT 1, (IY + d)*
XFDCB: ; d 4B	BIT 1, (IY + d)*
XFDCB: ; d 4C	BIT 1, (IY + d)*
XFDCB: ; d 4D	BIT 1, (IY + d)*
LFDCBdd4E:	BIT 1, (IY + dByte)
XFDCB: ; d 4F	BIT 1, (IY + d)*
XFDCB: ; d 50	BIT 2, (IY + d)*
XFDCB: ; d 51	BIT 2, (IY + d)*
XFDCB: ; d 52	BIT 2, (IY + d)*
XFDCB: ; d 53	BIT 2, (IY + d)*
XFDCB: ; d 54	BIT 2, (IY + d)*
XFDCB: ; d 55	BIT 2, (IY + d)*
LFDCBdd56:	BIT 2, (IY + dByte)
XFDCB: ; d 57	BIT 2, (IY + d)*
XFDCB: ; d 58	BIT 3, (IY + d)*
XFDCB: ; d 59	BIT 3, (IY + d)*
XFDCB: ; d 5A	BIT 3, (IY + d)*
XFDCB: ; d 5B	BIT 3, (IY + d)*
XFDCB: ; d 5C	BIT 3, (IY + d)*
XFDCB: ; d 5D	BIT 3, (IY + d)*
LFDCBdd5E:	BIT 3, (IY + dByte)
XFDCB: ; d 5F	BIT 3, (IY + d)*
XFDCB: ; d 60	BIT 4, (IY + d)*
XFDCB: ; d 61	BIT 4, (IY + d)*
XFDCB: ; d 62	BIT 4, (IY + d)*
XFDCB: ; d 63	BIT 4, (IY + d)*
XFDCB: ; d 64	BIT 4, (IY + d)*
XFDCB: ; d 65	BIT 4, (IY + d)*
LFDCBdd66:	BIT 4, (IY + dByte)
XFDCB: ; d 67	BIT 4, (IY + d)*
XFDCB: ; d 68	BIT 5, (IY + d)*
XFDCB: ; d 69	BIT 5, (IY + d)*
XFDCB: ; d 6A	BIT 5, (IY + d)*
XFDCB: ; d 6B	BIT 5, (IY + d)*
XFDCB: ; d 6C	BIT 5, (IY + d)*
XFDCB: ; d 6D	BIT 5, (IY + d)*
LFDCBdd6E:	BIT 5, (IY + dByte)
XFDCB: ; d 6F	BIT 5, (IY + d)*
XFDCB: ; d 70	BIT 6, (IY + d)*
XFDCB: ; d 71	BIT 6, (IY + d)*
XFDCB: ; d 72	BIT 6, (IY + d)*
XFDCB: ; d 73	BIT 6, (IY + d)*
XFDCB: ; d 74	BIT 6, (IY + d)*
XFDCB: ; d 75	BIT 6, (IY + d)*