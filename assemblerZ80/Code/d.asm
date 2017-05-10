;d.asm
Value55	EQU		055H
Value55AA	EQU	055AAH

		ORG      0100H
Start:

		RL		B			; CB 10
		RL		C			; CB 11
		RL		D			; CB 12
		RL		E			; CB 13
		RL		H			; CB 14
		RL		L			; CB 15
		RL		M			; CB 16
		RL		A			; CB 17

		RLC		B			; CB 00
		RLC		C			; CB 01
		RLC		D			; CB 02
		RLC		E			; CB 03
		RLC		H			; CB 04
		RLC		L			; CB 05
		RLC		M			; CB 06
		RLC		A			; CB 07

		RR		B			; CB 18
		RR		C			; CB 19
		RR		D			; CB 1A
		RR		E			; CB 1B
		RR		H			; CB 1C
		RR		L			; CB 1D
		RR		M			; CB 1E
		RR		A			; CB 1F

		RRC		B			; CB 08
		RRC		C			; CB 09
		RRC		D			; CB 0A
		RRC		E			; CB 0B
		RRC		H			; CB 0C
		RRC		L			; CB 0D
		RRC		M			; CB 0E
		RRC		A			; CB 0F

		SLA		B			; CB 20
		SLA		C			; CB 21
		SLA		D			; CB 22
		SLA		E			; CB 23
		SLA		H			; CB 24
		SLA		L			; CB 25
		SLA		M			; CB 26
		SLA		A			; CB 27

		SRA		B			; CB 28
		SRA		C			; CB 29
		SRA		D			; CB 2A
		SRA		E			; CB 2B
		SRA		H			; CB 2C
		SRA		L			; CB 2D
		SRA		M			; CB 2E
		SRA		A			; CB 2F

		SRL		B			; CB 38
		SRL		C			; CB 39
		SRL		D			; CB 3A
		SRL		E			; CB 3B
		SRL		H			; CB 3C
		SRL		L			; CB 3D
		SRL		M			; CB 3E
		SRL		A			; CB 3F




;
		AND		B			; A0
		AND		C			; A1
		AND		D			; A2
		AND		E			; A3
		AND		H			; A4
		AND		L			; A5
		AND		(HL)			; A6
		AND		A			; A7

		CP		B			; B8
		CP		C			; B9
		CP		D			; BA
		CP		E			; BB
		CP		H			; BC
		CP		L			; BD
		CP		M			; BE
		CP		A			; BF


		OR		B			; B0
		OR		C			; B1
		OR		D			; B2
		OR		E			; B3
		OR		H			; B4
		OR		L			; B5
		OR		M			; B6
		OR		A			; B7

		SUB		B			; 90
		SUB		C			; 91
		SUB		D			; 92
		SUB		E			; 93
		SUB		H			; 94
		SUB		L			; 95
		SUB		M			; 96
		SUB		A			; 97

		XOR		B			; A8
		XOR		C			; A9
		XOR		D			; AA
		XOR		E			; AB
		XOR		H			; AC
		XOR		L			; AD
		XOR		M			; AE
		XOR		A			; AF
	
		
		DEC		B			; 05
		DEC		C			; 0D
		DEC		D			; 15
		DEC		E			; 1D
		DEC		H			; 25
		DEC		L			; 2D
		DEC		M			; 35
		DEC		A			; 3D

		INC		B			; 04
		INC		C			; 0C
		INC		D			; 14
		INC		E			; 1C
		INC		H			; 24
		INC		L			; 2C
		INC		M			; 34
		INC		A			; 3C

		
		POP		IX			; DD E1
		POP		IY			; FD E1

		PUSH	IX			; DD E5
		PUSH	IY			; FD E5

		DEC		IX			; DD 2B
		DEC		IY			; FD 2B

		INC		IX			; DD 23
		INC		IY			; FD 23

		POP		BC			; C1
		POP		DE			; D1
		POP		HL			; E1
		POP		AF			; F1

		PUSH	BC			; C5
		PUSH	DE			; D5
		PUSH	HL			; E5
		PUSH	AF			; F5

		DEC		BC			; 0B
		DEC		DE			; 1B
		DEC		HL			; 2B
		DEC		SP			; 3B

		INC		BC			; 03
		INC		DE			; 13
		INC		HL			; 23
		INC		SP			; 33

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
		CP		(IY + 254)	; DD BE db

		JP		(HL )		; E9
		JP		( IX) 		; DD E9
		JP		( IY )		; FD E9
		


finish: