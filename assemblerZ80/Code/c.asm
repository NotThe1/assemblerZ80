;c.asm

Value55	EQU		055H
Value55AA	EQU	055AAH

		ORG      0100H
Start:

		
		CALL	1234H		; CD dw
		CALL	$			; CD dw
		JP		55AAH		; C3 dw
		JP		Start		; C3 dw
		CALL	$ + 1234H	; CD dw
		JP		$			; C3 DW
		
		AND		01			; E6 db
		AND		Value55AA	; E6 db
		CP		02+3		; FE db
		DJNZ	10 + 10D	; 10 db
		JR		10 + 10H	; 18 db
		OR		10 + 10O	; F6 db
		SUB		10 + 10B	; D6 db
		XOR		07			; EE db


		RST		00H		; C7
		RST		08H		; CF
		RST		10H		; D7
		RST		18H		; DF
		RST		20H		; E7
		RST		28H		; EF
		RST		30H		; F7
		RST		38H		; FF

		IM		0		; ED 46
		IM		1		; ED 56
		IM		2		; ED 5E
		IM		Value55


		RET				; C9
		RET		NZ		; C0
		RET		Z		; C8
		RET		NC		; D0
		RET		C		; D8
		RET		PO		; E0
		RET		PE		; E8
		RET		P		; F0
		RET		M		; F8

		CCF 			; 3F
		CPD 			; ED A9
		CPDR			; ED B9
		CPI				; ED A1 
		CPIR			; ED B1
		CPL				; 2F 
		DAA				; 27 
		DI  			; F3
		EI  			; FB
		EXX 			; D9
		HLT 			; 76
		IND 			; ED AA
		INDR			; ED BA
		INI 			; ED A2
		INIR			; ED B2
		LDD 			; ED A8
		LDDR			; ED B8
		LDI 			; ED A0
		LDIR			; ED B0
		NEG 			; ED 44
		NOP 			; 00
		OTDR			; ED BB
		OTIR			; ED B3
		OUTD			; ED AB
		OUTI			; ED A3
		RETI			; ED 4D
		RETN			; ED 45
		RLA 			; 17
		RLCA			; 07
		RLD 			; ED 6F
		RRA 			; 1F
		RRCA			; 0F
		RRD 			; ED 67
		SCF				; 37
finish:
		

