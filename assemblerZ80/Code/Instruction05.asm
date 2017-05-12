;Instruction05.asm
; RST - XOR
V55			EQU		055H
Value55AA	EQU	055AAH

		ORG      0100H
Start:


;.....................
;.....................
;XOR_1
		XOR		(IX + 90H)	; DD AE db		
		XOR		(IY + 0A0H)	; FD AE db		
;XOR_2  XOR
		XOR		B			;A8
		XOR		C			;A9
		XOR		D			;AA
		XOR		E			;AB
		XOR		H			;AC
		XOR		L			;AD
		XOR		(HL)		;AE
		XOR		A			;AF
;XOR_3  XOR
		XOR		01			; EE db
		XOR		Value55AA	; EE db
;.....................
;SUB_1
		SUB		(IX + 70H)	; DD 96 db		
		SUB		(IY + 80H)	; FD 96 db		
;SUB_2 
		SUB		B			;90
		SUB		C			;91
		SUB		D			;92
		SUB		E			;93
		SUB		H			;94
		SUB		L			;95
		SUB		(HL)		;96
		SUB		A			;97
;SUB_3  
		SUB		01			; D6 db
		SUB		Value55AA	; D6 db
;.....................
;SRL_1
		SRL		(IX + 50H)	; DD CB db 3E
		SRL		(IY + 60H)	; FD CB db 3E
;SRL_2
		SRL		B			; CB 38
		SRL		C			; CB 39
		SRL		D			; CB 3A
		SRL		E			; CB 3B
		SRL		H			; CB 3C
		SRL		L			; CB 3D
		SRL		M			; CB 3E
		SRL		A			; CB 3F
;.....................
;SRA_1
		SRA		(IX + 50H)	; DD CB db 2E
		SRA		(IY + 60H)	; FD CB db 2E
;SRA_2  
		SRA		B			; CB 28
		SRA		C			; CB 29
		SRA		D			; CB 2A
		SRA		E			; CB 2B
		SRA		H			; CB 2C
		SRA		L			; CB 2D
		SRA		M			; CB 2E
		SRA		A			; CB 2F
;.....................
;SLA_1
		SLA		(IX + 10H)	; DD CB db 26
		SLA		(IY + 20H)	; FD CB db 26
;SLA_2  
		SLA		B			; CB 20
		SLA		C			; CB 21
		SLA		D			; CB 22
		SLA		E			; CB 23
		SLA		H			; CB 24
		SLA		L			; CB 25
		SLA		M			; CB 26
		SLA		A			; CB 27
;.....................
;SET_1
		SET		0, (IX+12H)			; DD CB db C6
		SET		1, (IX+0FFH)		; DD CB db CE
		SET		2, (IX+34H)			; DD CB db D6
		SET		3, (IX+56H)			; DD CB db DE
		SET		4, (IX+78H)			; DD CB db E6
		SET		5, (IX+9AH)			; DD CB db EE
		SET		6, (IX+ 0BCH)		; DD CB db F6
		SET		7, (IX+V55)			; DD CB db FE

		SET		0, (IY+12H)			; FD CB db C6
		SET		1, (IY+0FFH)		; FD CB db CE
		SET		2, (IY+34H)			; FD CB db D6
		SET		3, (IY+56H)			; FD CB db DE
		SET		4, (IY+78H)			; FD CB db E6
		SET		5, (IY+9AH)			; FD CB db EE
		SET		6, (IY+ 0BCH)		; FD CB db F6
		SET		7, (IY+V55)			; FD CB db FE
;SET_2
		SET		0,B				; CB C0
		SET		0,C				; CB C1
		SET		0,D				; CB C2
		SET		0,E				; CB C3
		SET		0,H				; CB C4
		SET		0,L				; CB C5
		SET		0,(HL)				; CB C6
		SET		0,A				; CB C7
		                             
		SET		1,B				; CB C8
		SET		1,C				; CB C9
		SET		1,D				; CB CA
		SET		1,E				; CB CB
		SET		1,H				; CB CC
		SET		1,L				; CB CD
		SET		1,M				; CB CE
		SET		1,A				; CB CF
        
		SET		2,B				; CB D0
		SET		2,C				; CB D1
		SET		2,D				; CB D2
		SET		2,E				; CB D3
		SET		2,H				; CB D4
		SET		2,L				; CB D5
		SET		2,M				; CB D6
		SET		2,A				; CB D7
		                             
		SET		3,B				; CB D8
		SET		3,C				; CB D9
		SET		3,D				; CB DA
		SET		3,E				; CB DB
		SET		3,H				; CB DC
		SET		3,L				; CB DD
		SET		3,M				; CB DE
		SET		3,A				; CB DF
        
		SET		4,B				; CB E0
		SET		4,C				; CB E1
		SET		4,D				; CB E2
		SET		4,E				; CB E3
		SET		4,H				; CB E4
		SET		4,L				; CB E5
		SET		4,M				; CB E6
		SET		4,A				; CB E7
		                             
		SET		5,B				; CB E8
		SET		5,C				; CB E9
		SET		5,D				; CB EA
		SET		5,E				; CB EB
		SET		5,H				; CB EC
		SET		5,L				; CB ED
		SET		5,M				; CB EE
		SET		5,A				; CB EF
        
		SET		6,B				; CB F0
		SET		6,C				; CB F1
		SET		6,D				; CB F2
		SET		6,E				; CB F3
		SET		6,H				; CB F4
		SET		6,L				; CB F5
		SET		6,M				; CB F6
		SET		6,A				; CB F7
		                             
		SET		7,B				; CB F8
		SET		7,C				; CB F9
		SET		7,D				; CB FA
		SET		7,E				; CB FB
		SET		7,H				; CB FC
		SET		7,L				; CB FD
		SET		7,M				; CB FE
		SET		7,A				; CB FF
;.....................
;SBC_1
		SBC		A,( IX+10H)	; DD 9E db
		SBC		A,(IY+ V55)	; FD 9E db
;SBC_2		
		SBC		A,B			; 98
		SBC		A,C			; 99
		SBC		A,D			; 9A
		SBC		A,E			; 9B
		SBC		A,H			; 9C
		SBC		A,L			; 9D
		SBC		A,M			; 9E
		SBC		A,A			; 9F
;SBC_3		
		SBC		A,010H		; DE db
		SBC		A,V55		; DE db
;SBC_4		
		SBC		HL,BC		; ED 42
		SBC		HL,DE		; ED 52
		SBC		HL,HL		; ED 62
		SBC		HL,SP		; ED 72
;.....................
;RST_1
		RST		00H		; C7
		RST		08H		; CF
		RST		10H		; D7
		RST		18H		; DF
		RST		20H		; E7
		RST		28H		; EF
		RST		30H		; F7
		RST		38H		; FF		



finish: