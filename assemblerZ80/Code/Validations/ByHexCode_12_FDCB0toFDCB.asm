;ByHexCode_12_FDCB0toFDCB.asm

nnWord	EQU		01234H	; nn 
aByte	EQU		5AH		; n
dByte	EQU		0DDH	; d
;    PC  ->	$
;ByHexCodeFDCB0toFDCB.asm

nnWord	EQU		01234H	; nn 
aByte	EQU		5AH		; n
dByte	EQU		0DDH	; d
;    PC  ->	$

XFDCBdd23:  ; d 23	LD E, SLA (IY + d)*
XFDCBdd24:  ; d 24	LD H, SLA (IY + d)*
XFDCBdd25:  ; d 25	LD L, SLA (IY + d)*
LFDCBdd26:	SLA (IY + dByte)
XFDCBdd27:  ; d 27	LD A, SLA (IY + d)*
XFDCBdd28:  ; d 28	LD B, SRA (IY + d)*
XFDCBdd29:  ; d 29	LD C, SRA (IY + d)*
XFDCBdd2A:  ; d 2A	LD D, SRA (IY + d)*
XFDCBdd2B:  ; d 2B	LD E, SRA (IY + d)*
XFDCBdd2C:  ; d 2C	LD H, SRA (IY + d)*
XFDCBdd2D:  ; d 2D	LD L, SRA (IY + d)*
LFDCBdd2E:	SRA (IY + dByte)
XFDCBdd2F:  ; d 2F	LD A, SRA (IY + d)*
XFDCBdd30:  ; d 30	LD B, SLL (IY + d)*
XFDCBdd31:  ; d 31	LD C, SLL (IY + d)*
XFDCBdd32:  ; d 32	LD D, SLL (IY + d)*
XFDCBdd33:  ; d 33	LD E, SLL (IY + d)*
XFDCBdd34:  ; d 34	LD H, SLL (IY + d)*
XFDCBdd35:  ; d 35	LD L, SLL (IY + d)*
XFDCBdd36:  ; d 36	SLL (IY + d)*
XFDCBdd37:  ; d 37	LD A, SLL (IY + d)*
XFDCBdd38:  ; d 38	LD B, SRL (IY + d)*
XFDCBdd39:  ; d 39	LD C, SRL (IY + d)*
XFDCBdd3A:  ; d 3A	LD D, SRL (IY + d)*
XFDCBdd3B:  ; d 3B	LD E, SRL (IY + d)*
XFDCBdd3C:  ; d 3C	LD H, SRL (IY + d)*
XFDCBdd3D:  ; d 3D	LD L, SRL (IY + d)*
LFDCBdd3E:	SRL (IY + dByte)
XFDCBdd3F:  ; d 3F	LD A, SRL (IY + d)*
XFDCBdd40:  ; d 40	BIT 0, (IY + d)*
XFDCBdd41:  ; d 41	BIT 0, (IY + d)*
XFDCBdd42:  ; d 42	BIT 0, (IY + d)*
XFDCBdd43:  ; d 43	BIT 0, (IY + d)*
XFDCBdd44:  ; d 44	BIT 0, (IY + d)*
XFDCBdd45:  ; d 45	BIT 0, (IY + d)*
LFDCBdd46:	BIT 0, (IY + dByte)
XFDCBdd47:  ; d 47	BIT 0, (IY + d)*
XFDCBdd48:  ; d 48	BIT 1, (IY + d)*
XFDCBdd49:  ; d 49	BIT 1, (IY + d)*
XFDCBdd4A:  ; d 4A	BIT 1, (IY + d)*
XFDCBdd4B:  ; d 4B	BIT 1, (IY + d)*
XFDCBdd4C:  ; d 4C	BIT 1, (IY + d)*
XFDCBdd4D:  ; d 4D	BIT 1, (IY + d)*
LFDCBdd4E:	BIT 1, (IY + dByte)
XFDCBdd4F:  ; d 4F	BIT 1, (IY + d)*
XFDCBdd50:  ; d 50	BIT 2, (IY + d)*
XFDCBdd51:  ; d 51	BIT 2, (IY + d)*
XFDCBdd52:  ; d 52	BIT 2, (IY + d)*
XFDCBdd53:  ; d 53	BIT 2, (IY + d)*
XFDCBdd54:  ; d 54	BIT 2, (IY + d)*
XFDCBdd55:  ; d 55	BIT 2, (IY + d)*
LFDCBdd56:	BIT 2, (IY + dByte)
XFDCBdd57:  ; d 57	BIT 2, (IY + d)*
XFDCBdd58:  ; d 58	BIT 3, (IY + d)*
XFDCBdd59:  ; d 59	BIT 3, (IY + d)*
XFDCBdd5A:  ; d 5A	BIT 3, (IY + d)*
XFDCBdd5B:  ; d 5B	BIT 3, (IY + d)*
XFDCBdd5C:  ; d 5C	BIT 3, (IY + d)*
XFDCBdd5D:  ; d 5D	BIT 3, (IY + d)*
LFDCBdd5E:	BIT 3, (IY + dByte)
XFDCBdd5F:  ; d 5F	BIT 3, (IY + d)*
XFDCBdd60:  ; d 60	BIT 4, (IY + d)*
XFDCBdd61:  ; d 61	BIT 4, (IY + d)*
XFDCBdd62:  ; d 62	BIT 4, (IY + d)*
XFDCBdd63:  ; d 63	BIT 4, (IY + d)*
XFDCBdd64:  ; d 64	BIT 4, (IY + d)*
XFDCBdd65:  ; d 65	BIT 4, (IY + d)*
LFDCBdd66:	BIT 4, (IY + dByte)
XFDCBdd67:  ; d 67	BIT 4, (IY + d)*
XFDCBdd68:  ; d 68	BIT 5, (IY + d)*
XFDCBdd69:  ; d 69	BIT 5, (IY + d)*
XFDCBdd6A:  ; d 6A	BIT 5, (IY + d)*
XFDCBdd6B:  ; d 6B	BIT 5, (IY + d)*
XFDCBdd6C:  ; d 6C	BIT 5, (IY + d)*
XFDCBdd6D:  ; d 6D	BIT 5, (IY + d)*
LFDCBdd6E:	BIT 5, (IY + dByte)
XFDCBdd6F:  ; d 6F	BIT 5, (IY + d)*
XFDCBdd70:  ; d 70	BIT 6, (IY + d)*
XFDCBdd71:  ; d 71	BIT 6, (IY + d)*
XFDCBdd72:  ; d 72	BIT 6, (IY + d)*
XFDCBdd73:  ; d 73	BIT 6, (IY + d)*
XFDCBdd74:  ; d 74	BIT 6, (IY + d)*
XFDCBdd75:  ; d 75	BIT 6, (IY + d)*
