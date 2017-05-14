;ByHexCode_14_FDCB2toFF.asm

nnWord	EQU		01234H	; nn 
aByte	EQU		5AH		; n
dByte	EQU		0DDH	; d
;    PC  ->	$

XFDCBddC9: ; d C9	LD C, SET 1, (IY + d)*
XFDCBddCA:  ; d CA	LD D, SET 1, (IY + d)*
XFDCBddCB:  ; d CB	LD E, SET 1, (IY + d)*
XFDCBddCC:  ; d CC	LD H, SET 1, (IY + d)*
XFDCBddCD:  ; d CD	LD L, SET 1, (IY + d)*
LFDCBddCE:	SET 1, (IY + dByte)
XFDCBddCF:  ; d CF	LD A, SET 1, (IY + d)*
XFDCBddD0:  ; d D0	LD B, SET 2, (IY + d)*
XFDCBddD1:  ; d D1	LD C, SET 2, (IY + d)*
XFDCBddD2:  ; d D2	LD D, SET 2, (IY + d)*
XFDCBddD3:  ; d D3	LD E, SET 2, (IY + d)*
XFDCBddD4:  ; d D4	LD H, SET 2, (IY + d)*
XFDCBddD5:  ; d D5	LD L, SET 2, (IY + d)*
LFDCBddD6:	SET 2, (IY + dByte)
XFDCBddD7:  ; d D7	LD A, SET 2, (IY + d)*
XFDCBddD8:  ; d D8	LD B, SET 3, (IY + d)*
XFDCBddD9:  ; d D9	LD C, SET 3, (IY + d)*
XFDCBddDA:  ; d DA	LD D, SET 3, (IY + d)*
XFDCBddDB:  ; d DB	LD E, SET 3, (IY + d)*
XFDCBddDC:  ; d DC	LD H, SET 3, (IY + d)*
XFDCBddDD:  ; d DD	LD L, SET 3, (IY + d)*
LFDCBddDE:	SET 3, (IY + dByte)
XFDCBddDF:  ; d DF	LD A, SET 3, (IY + d)*
XFDCBddE0:  ; d E0	LD B, SET 4, (IY + d)*
XFDCBddE1:  ; d E1	LD C, SET 4, (IY + d)*
XFDCBddE2:  ; d E2	LD D, SET 4, (IY + d)*
XFDCBddE3:  ; d E3	LD E, SET 4, (IY + d)*
XFDCBddE4:  ; d E4	LD H, SET 4, (IY + d)*
XFDCBddE5:  ; d E5	LD L, SET 4, (IY + d)*
LFDCBddE6:	SET 4, (IY + dByte)
XFDCBddE7:  ; d E7	LD A, SET 4, (IY + d)*
XFDCBddE8:  ; d E8	LD B, SET 5, (IY + d)*
XFDCBddE9:  ; d E9	LD C, SET 5, (IY + d)*
XFDCBddEA:  ; d EA	LD D, SET 5, (IY + d)*
XFDCBddEB:  ; d EB	LD E, SET 5, (IY + d)*
XFDCBddEC:  ; d EC	LD H, SET 5, (IY + d)*
XFDCBddED:  ; d ED	LD L, SET 5, (IY + d)*
LFDCBddEE:	SET 5, (IY + dByte)
XFDCBddEF: ; d EF	LD A, SET 5, (IY + d)*
XFDCBddF0: ; d F0	LD B, SET 6, (IY + d)*
XFDCBddF1: ; d F1	LD C, SET 6, (IY + d)*
XFDCBddF2: ; d F2	LD D, SET 6, (IY + d)*
XFDCBddF3: ; d F3	LD E, SET 6, (IY + d)*
XFDCBddF4: ; d F4	LD H, SET 6, (IY + d)*
XFDCBddF5: ; d F5	LD L, SET 6, (IY + d)*
LFDCBddF6:	SET 6, (IY + dByte)
XFDCBddF7: ; d F7	LD A, SET 6, (IY + d)*
XFDCBddF8: ; d F8	LD B, SET 7, (IY + d)*
XFDCBddF9: ; d F9	LD C, SET 7, (IY + d)*
XFDCBddFA: ; d FA	LD D, SET 7, (IY + d)*
XFDCBddFB: ; d FB	LD E, SET 7, (IY + d)*
XFDCBddFC: ; d FC	LD H, SET 7, (IY + d)*
XFDCBddFD: ; d FD	LD L, SET 7, (IY + d)*
LFDCBddFE:	SET 7, (IY + dByte)
XFDCBddFF: ; d FF	LD A, SET 7, (IY + d)*
LFDE1:	POP IY
LFDE3:	EX (SP), IY
LFDE5:	PUSH IY
LFDE9:	JP (IY)
LFDF9:	LD SP, IY
LFE:	CP aByte
LFF:	RST 38h
