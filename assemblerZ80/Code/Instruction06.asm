;Instruction06.asm
; No Operand Instructions
V55			EQU		055H
Value55AA	EQU	055AAH

		ORG      0100H
Start:


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