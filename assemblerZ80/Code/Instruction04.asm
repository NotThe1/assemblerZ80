;Instruction04.asm
; LD only
V55			EQU		055H
Value55AA	EQU	055AAH

		ORG      0100H
Start:

;RRC_1
		RRC		(IX + 70H)	; DD CB db 0E
		RRC		(IY + 80H)	; FD CB db 0E
;RRC_2
		RRC		B			; CB 08
		RRC		C			; CB 09
		RRC		D			; CB 0A
		RRC		E			; CB 0B
		RRC		H			; CB 0C
		RRC		L			; CB 0D
		RRC		M			; CB 0E
		RRC		A			; CB 0F
;.....................

;RR_1
		RR		(IX + 50H)	; DD CB db 1E
		RR		(IY + 60H)	; FD CB db 1E
;RR_2
		RR		B			; CB 18
		RR		C			; CB 19
		RR		D			; CB 1A
		RR		E			; CB 1B
		RR		H			; CB 1C
		RR		L			; CB 1D
		RR		M			; CB 1E
		RR		A			; CB 1F
;.....................
;RLC_1
		RLC		(IX + 30H)	; DD CB db 06
		RLC		(IY + 40H)	; DD CB db 06

;RLC_2
		RLC		B			; CB 00
		RLC		C			; CB 01
		RLC		D			; CB 02
		RLC		E			; CB 03
		RLC		H			; CB 04
		RLC		L			; CB 05
		RLC		M			; CB 06
		RLC		A			; CB 07
;.....................
;RL_1
		RL		(IX + 10H)	; DD CB db 16
		RL		(IY + 20H)	; FD CB db 16
;RL_2
		RL		B			; CB 10
		RL		C			; CB 11
		RL		D			; CB 12
		RL		E			; CB 13
		RL		H			; CB 14
		RL		L			; CB 15
		RL		M			; CB 16
		RL		A			; CB 17
;.....................
;RET_1
		RET		NZ		; C0
		RET		Z		; C8
		RET		NC		; D0
		RET		C		; D8
		RET		PO		; E0
		RET		PE		; E8
		RET		P		; F0
		RET		M		; F8
;RET_2
		RET				; C9
;.....................
;RES_1
		RES		0, (IX+12H)			; DD CB db 86
		RES		1, (IX+0FFH)		; DD CB db 8E
		RES		2, (IX+34H)			; DD CB db 96
		RES		3, (IX+56H)			; DD CB db 9E
		RES		4, (IX+78H)			; DD CB db A6
		RES		5, (IX+9AH)			; DD CB db AE
		RES		6, (IX+ 0BCH)		; DD CB db B6
		RES		7, (IX+V55)		; DD CB db BE

		RES		0, (IY+12H)			; FD CB db 86
		RES		1, (IY+0FFH)		; FD CB db 8E
		RES		2, (IY+34H)			; FD CB db 96
		RES		3, (IY+56H)			; FD CB db 9E
		RES		4, (IY+78H)			; FD CB db A6
		RES		5, (IY+9AH)			; FD CB db AE
		RES		6, (IY+ 0BCH)		; FD CB db B6
		RES		7, (IY+V55)		; FD CB db BE
;RES_2
		RES		0,B				; CB 80
		RES		0,C				; CB 81
		RES		0,D				; CB 82
		RES		0,E				; CB 83
		RES		0,H				; CB 84
		RES		0,L				; CB 85
		RES		0,M				; CB 86
		RES		0,A				; CB 87
		                             
		RES		1,B				; CB 88
		RES		1,C				; CB 89
		RES		1,D				; CB 8A
		RES		1,E				; CB 8B
		RES		1,H				; CB 8C
		RES		1,L				; CB 8D
		RES		1,M				; CB 8E
		RES		1,A				; CB 8F

		RES		2,B				; CB 90
		RES		2,C				; CB 91
		RES		2,D				; CB 92
		RES		2,E				; CB 93
		RES		2,H				; CB 94
		RES		2,L				; CB 95
		RES		2,M				; CB 96
		RES		2,A				; CB 97
		                             
		RES		3,B				; CB 98
		RES		3,C				; CB 99
		RES		3,D				; CB 9A
		RES		3,E				; CB 9B
		RES		3,H				; CB 9C
		RES		3,L				; CB 9D
		RES		3,M				; CB 9E
		RES		3,A				; CB 9F

		RES		4,B				; CB A0
		RES		4,C				; CB A1
		RES		4,D				; CB A2
		RES		4,E				; CB A3
		RES		4,H				; CB A4
		RES		4,L				; CB A5
		RES		4,M				; CB A6
		RES		4,A				; CB A7
		                             
		RES		5,B				; CB A8
		RES		5,C				; CB A9
		RES		5,D				; CB AA
		RES		5,E				; CB AB
		RES		5,H				; CB AC
		RES		5,L				; CB AD
		RES		5,M				; CB AE
		RES		5,A				; CB AF

		RES		6,B				; CB B0
		RES		6,C				; CB B1
		RES		6,D				; CB B2
		RES		6,E				; CB B3
		RES		6,H				; CB B4
		RES		6,L				; CB B5
		RES		6,M				; CB B6
		RES		6,A				; CB B7
		                             
		RES		7,B				; CB B8
		RES		7,C				; CB B9
		RES		7,D				; CB BA
		RES		7,E				; CB BB
		RES		7,H				; CB BC
		RES		7,L				; CB BD
		RES		7,M				; CB BE
		RES		7,A				; CB BF
;.....................
;PUSH_1
		PUSH	BC			; C5
		PUSH	DE			; D5
		PUSH	HL			; E5
		PUSH	AF			; F5
;PUSH_2
		PUSH	IX			; DD E5
		PUSH	IY			; FD E5
;.....................
;POP_1
		POP		BC			; C1
		POP		DE			; D1
		POP		HL			; E1
		POP		AF			; F1
;POP_2
		POP		IX			; DD E1
		POP		IY			; FD E1
;.....................
;OUT_1
		OUT		(10H),A		; D3 db
;OUT_2
		OUT		(C),B		; ED 41
		OUT		(C),C		; ED 49
		OUT		(C),D		; ED 51
		OUT		(C),E		; ED 59
		OUT		(C),H		; ED 61
		OUT		(C),L		; ED 69
		OUT		(C),0		; ED 71
		OUT		(C),A		; ED 79
;.....................
;OR_1
		OR		(IX + 10H)	; DD B6 db		
		OR		(IY + 10H)	; FD B6 db		
;OR_2
		OR		01			; F6 db
		OR		Value55AA	; F6 db
;OR_3
		OR		B			; B0
		OR		C			; B1
		OR		D			; B2
		OR		E			; B3
		OR		H			; B4
		OR		L			; B5
		OR		(HL)		; B6
		OR		A			; B7




finish: