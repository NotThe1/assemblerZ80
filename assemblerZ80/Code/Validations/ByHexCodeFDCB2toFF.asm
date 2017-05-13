;ByHexCodeFDCB2toFF.asm

nnWord	EQU		01234H	; nn 
aByte	EQU		5AH		; n
dByte	EQU		0DDH	; d
;    PC  ->	$

XFDCB: ; d C9	LD C, SET 1, (IY + d)*
XFDCB: ; d CA	LD D, SET 1, (IY + d)*
XFDCB: ; d CB	LD E, SET 1, (IY + d)*
XFDCB: ; d CC	LD H, SET 1, (IY + d)*
XFDCB: ; d CD	LD L, SET 1, (IY + d)*
LFDCBddCE:	SET 1, (IY + dByte)
XFDCB: ; d CF	LD A, SET 1, (IY + d)*
XFDCB: ; d D0	LD B, SET 2, (IY + d)*
XFDCB: ; d D1	LD C, SET 2, (IY + d)*
XFDCB: ; d D2	LD D, SET 2, (IY + d)*
XFDCB: ; d D3	LD E, SET 2, (IY + d)*
XFDCB: ; d D4	LD H, SET 2, (IY + d)*
XFDCB: ; d D5	LD L, SET 2, (IY + d)*
LFDCBddD6:	SET 2, (IY + dByte)
XFDCB: ; d D7	LD A, SET 2, (IY + d)*
XFDCB: ; d D8	LD B, SET 3, (IY + d)*
XFDCB: ; d D9	LD C, SET 3, (IY + d)*
XFDCB: ; d DA	LD D, SET 3, (IY + d)*
XFDCB: ; d DB	LD E, SET 3, (IY + d)*
XFDCB: ; d DC	LD H, SET 3, (IY + d)*
XFDCB: ; d DD	LD L, SET 3, (IY + d)*
LFDCBddDE:	SET 3, (IY + dByte)
XFDCB: ; d DF	LD A, SET 3, (IY + d)*
XFDCB: ; d E0	LD B, SET 4, (IY + d)*
XFDCB: ; d E1	LD C, SET 4, (IY + d)*
XFDCB: ; d E2	LD D, SET 4, (IY + d)*
XFDCB: ; d E3	LD E, SET 4, (IY + d)*
XFDCB: ; d E4	LD H, SET 4, (IY + d)*
XFDCB: ; d E5	LD L, SET 4, (IY + d)*
LFDCBddE6:	SET 4, (IY + dByte)
XFDCB: ; d E7	LD A, SET 4, (IY + d)*
XFDCB: ; d E8	LD B, SET 5, (IY + d)*
XFDCB: ; d E9	LD C, SET 5, (IY + d)*
XFDCB: ; d EA	LD D, SET 5, (IY + d)*
XFDCB: ; d EB	LD E, SET 5, (IY + d)*
XFDCB: ; d EC	LD H, SET 5, (IY + d)*
XFDCB: ; d ED	LD L, SET 5, (IY + d)*
LFDCBddEE:	SET 5, (IY + dByte)
XFDCB: ; d EF	LD A, SET 5, (IY + d)*
XFDCB: ; d F0	LD B, SET 6, (IY + d)*
XFDCB: ; d F1	LD C, SET 6, (IY + d)*
XFDCB: ; d F2	LD D, SET 6, (IY + d)*
XFDCB: ; d F3	LD E, SET 6, (IY + d)*
XFDCB: ; d F4	LD H, SET 6, (IY + d)*
XFDCB: ; d F5	LD L, SET 6, (IY + d)*
LFDCBddF6:	SET 6, (IY + dByte)
XFDCB: ; d F7	LD A, SET 6, (IY + d)*
XFDCB: ; d F8	LD B, SET 7, (IY + d)*
XFDCB: ; d F9	LD C, SET 7, (IY + d)*
XFDCB: ; d FA	LD D, SET 7, (IY + d)*
XFDCB: ; d FB	LD E, SET 7, (IY + d)*
XFDCB: ; d FC	LD H, SET 7, (IY + d)*
XFDCB: ; d FD	LD L, SET 7, (IY + d)*
LFDCBddFE:	SET 7, (IY + dByte)
XFDCB: ; d FF	LD A, SET 7, (IY + d)*
LFDE1:	POP IY
LFDE3:	EX (SP), IY
LFDE5:	PUSH IY
LFDE9:	JP (IY)
LFDF9:	LD SP, IY
LFE:	CP aByte
LFF:	RST 38h