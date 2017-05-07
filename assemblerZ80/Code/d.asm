;d.asm
Value55	EQU		055H
Value55AA	EQU	055AAH

		ORG      0100H
Start:

		RL		(IX + 10H)	; DD CB db 16
		RLC		(IX + 10H)	; DD CB db 06
		RR		(IX + 10H)	; DD CB db 1E
		RRC		(IX + 10H)	; DD CB db 0E
		SLA		(IX + 10H)	; DD CB db 26
		SRA		(IX + 10H)	; DD CB db 2E
		SRL		(IX + 10H)	; DD CB db 3E

		RL		(IY + 10H)	; FD CB db 16
		RLC		(IY + 10H)	; FD CB db 06
		RR		(IY + 10H)	; FD CB db 1E
		RRC		(IY + 10H)	; FD CB db 0E
		SLA		(IY + 10H)	; FD CB db 26
		SRA		(IY + 10H)	; FD CB db 2E
		SRL		(IY + 10H)	; FD CB db 3E


		AND		(IX + 10H)	; DD A6 db		
		CP		(IX + 10H)	; DD BE db		
		DEC		(IX + 10H)	; DD 35 db		
		INC		(IX + 10H)	; DD 34 db		
		OR		(IX + 10H)	; DD B6 db		
		SUB		(IX + 10H)	; DD 96 db		
		XOR		(IX + 10H)	; DD AE db		

		AND		(IY + 10H)	; FD A6 db		
		CP		(IY + 10H)	; FD BE db		
		DEC		(IY + 10H)	; FD 35 db		
		INC		(IY + 10H)	; FD 34 db		
		OR		(IY + 10H)	; FD B6 db		
		SUB		(IY + 10H)	; FD 96 db		
		XOR		(IY + 10H)	; FD AE db		


		CP		(IX + 10H)	; DD BE db
		CP		(IY + 255)	; DD BE db

		JP		(HL )		; E9
		JP		( IX) 		; DD E9
		JP		( IY )		; FD E9
		


finish: