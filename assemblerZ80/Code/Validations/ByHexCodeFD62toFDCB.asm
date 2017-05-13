;ByHexCodeFD62toFDCB.asm

nnWord	EQU		01234H	; nn 
aByte	EQU		5AH		; n
dByte	EQU		0DDH	; d
;    PC  ->	$

XFD62:	; LD IYH, D*
XFD63:	; LD IYH, E*
XFD64:	; LD IYH, IYH*
XFD65:	; LD IYH, IYL*
LFD66:	LD H, (IY + dByte)
XFD67:	; LD IYH, A*
XFD68:	; LD IYL, B*
XFD69:	; LD IYL, C*
XFD6A:	; LD IYL, D*
XFD6B:	; LD IYL, E*
XFD6C:	; LD IYL, IYH*
XFD6D:	; LD IYL, IYL*
LFD6E:	LD L, (IY + dByte)
XFD6F:	; LD IYL, A*
LFD70:	LD (IY + dByte), B
LFD71:	LD (IY + dByte), C
LFD72:	LD (IY + dByte), D
LFD73:	LD (IY + dByte), E
LFD74:	LD (IY + dByte), H
LFD75:	LD (IY + dByte), L
LFD77:	LD (IY + dByte), A
XFD7C:	; LD A, IYH*
XFD7D:	; LD A, IYL*
LFD7E:	LD A, (IY + dByte)
XFD84:	; ADD A, IYH*
XFD85:	; ADD A, IYL*
LFD86:	ADD A, (IY + dByte)
XFD8C:	; ADC A, IYH*
XFD8D:	; ADC A, IYL*
LFD8E:	ADC A, (IY + dByte)
XFD94:	; SUB IYH*
XFD95:	; SUB IYL*
LFD96:	SUB (IY + dByte)
XFD9C:	; SBC A, IYH*
XFD9D:	; SBC A, IYL*
LFD9E:	SBC A, (IY + dByte)
XFDA4:	; AND IYH*
XFDA5:	; AND IYL*
LFDA6:	AND (IY + dByte)
XFDAC:	; XOR IYH*
XFDAD:	; XOR IYL*
LFDAE:	XOR (IY + dByte)
XFDB4:	; OR IYH*
XFDB5:	; OR IYL*
LFDB6:	OR (IY + dByte)
XFDBC:	; CP IYH*
XFDBD:	; CP IYL*
LFDBE:	CP (IY + dByte)
XFDCB: ; 00	LD B, RLC (IY + d)*
XFDCB: ; 01	LD C, RLC (IY + d)*
XFDCB: ; 02	LD D, RLC (IY + d)*
XFDCB: ; 03	LD E, RLC (IY + d)*
XFDCB: ; 04	LD H, RLC (IY + d)*
XFDCB: ; 05	LD L, RLC (IY + d)*
LFDCBdd06:	RLC (IY + dByte)
XFDCB: ; d 07	LD A, RLC (IY + d)*
XFDCB: ; d 08	LD B, RRC (IY + d)*
XFDCB: ; d 09	LD C, RRC (IY + d)*
XFDCB: ; d 0A	LD D, RRC (IY + d)*
XFDCB: ; d 0B	LD E, RRC (IY + d)*
XFDCB: ; d 0C	LD H, RRC (IY + d)*
XFDCB: ; d 0D	LD L, RRC (IY + d)*
LFDCBdd0E:	RRC (IY + dByte)
XFDCB: ; d 0F	LD A, RRC (IY + d)*
XFDCB: ; d 10	LD B, RL (IY + d)*
XFDCB: ; d 11	LD C, RL (IY + d)*
XFDCB: ; d 12	LD D, RL (IY + d)*
XFDCB: ; d 13	LD E, RL (IY + d)*
XFDCB: ; d 14	LD H, RL (IY + d)*
XFDCB: ; d 15	LD L, RL (IY + d)*
LFDCBdd16:	RL (IY + dByte)
XFDCB: ; d 17	LD A, RL (IY + d)*
XFDCB: ; d 18	LD B, RR (IY + d)*
XFDCB: ; d 19	LD C, RR (IY + d)*
XFDCB: ; d 1A	LD D, RR (IY + d)*
XFDCB: ; d 1B	LD E, RR (IY + d)*
XFDCB: ; d 1C	LD H, RR (IY + d)*
XFDCB: ; d 1D	LD L, RR (IY + d)*
LFDCBdd1E:	RR (IY + dByte)
XFDCB: ; d 1F	LD A, RR (IY + d)*
XFDCB: ; d 20	LD B, SLA (IY + d)*
XFDCB: ; d 21	LD C, SLA (IY + d)*
XFDCB: ; d 22	LD D, SLA (IY + d)*