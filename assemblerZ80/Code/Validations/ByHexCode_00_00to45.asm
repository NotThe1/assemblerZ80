;ByHexCode_00_00to45.asm

nnWord	EQU		01234H	; nn
aByte	EQU		5AH		; n
;    PC  ->	$


L00:	NOP
L01:	LD BC, nnWord
L02:	LD (BC), A
L03:	INC BC
L04:	INC B
L05:	DEC B
L06:	LD B, aByte
L07:	RLCA
L08:	EX AF, AF’
L09:	ADD HL, BC
L0A:	LD A, (BC)
L0B:	DEC BC
L0C:	INC C
L0D:	DEC C
L0E:	LD C, aByte
L0F:	RRCA
L10:	DJNZ $ + aByte
L11:	LD DE, nnWord
L12:	LD (DE), A
L13:	INC DE
L14:	INC D
L15:	DEC D
L16:	LD D, aByte
L17:	RLA
L18:	JR $ + aByte
L19:	ADD HL, DE
L1A:	LD A, (DE)
L1B:	DEC DE
L1C:	INC E
L1D:	DEC E
L1E: LD E, aByte
L1F:	RRA
L20: JR NZ, $ + aByte
L21: LD HL, nnWord
L22: LD (nnWord), HL
L23:	INC HL
L24:	INC H
L25:	DEC H
L26: LD H, aByte
L27:	DAA
L28: JR Z, $ + aByte
L29:	ADD HL, HL
L2A: LD HL, (nnWord)
L2B:	DEC HL
L2C:	INC L
L2D:	DEC L
L2E: LD L, aByte
L2F:	CPL
L30: JR NC, $ + aByte
L31: LD SP, nnWord
L32: LD (nnWord), A
L33:	INC SP
L34:	INC (HL)
L35:	DEC (HL)
L36: LD (HL), aByte
L37:	SCF
L38: JR C, $ + aByte
L39:	ADD HL, SP
L3A: LD A, (nnWord)
L3B:	DEC SP
L3C:	INC A
L3D:	DEC A
L3E: LD A, aByte
L3F:	CCF
L40:	LD B, B
L41:	LD B, C
L42:	LD B, D
L43:	LD B, E
L44:	LD B, H
L45:	LD B, L
