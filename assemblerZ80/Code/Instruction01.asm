;Instruction01.asm
; ADC - DEC

V55			EQU		055H
Value55AA	EQU	055AAH

		ORG      0100H
Start:

;DEC_1
		DEC		(IX + 10H)	; DD 35 db		
		DEC		(IY + 10H)	; FD 35 db		

;DEC_2
		DEC		BC			; 0B
		DEC		DE			; 1B
		DEC		HL			; 2B
		DEC		SP			; 3B
;DEC_3
		DEC		IX			; DD 2B
		DEC		IY			; FD 2B
;DEC_4
		DEC		B			; 05
		DEC		C			; 0D
		DEC		D			; 15
		DEC		E			; 1D
		DEC		H			; 25
		DEC		L			; 2D
		DEC		M			; 35
		DEC		A			; 3D

;.....................
;CP_1
		CP		(IX + 11111111B)	; DD BE db		
		CP		(IY + 10H)	; FD BE db		
;CP_2
		CP		B			; B8
		CP		C			; B9
		CP		D			; BA
		CP		E			; BB
		CP		H			; BC
		CP		L			; BD
		CP		M			; BE
		CP		A			; BF
;CP_3
		CP		02+3		; FE db
;.....................

;CALL_1
		CALL	NZ,1234H		; C4 dw
		CALL	Z,$				; CC dw
		CALL	NC,Start		; D4 dw
		CALL	C,$				; DC dw
		CALL	PO,1234H		; E4 dw
		CALL	PE,$			; EC dw
		CALL	P,1234H			; F4 dw
		CALL	M,$	- (7*3)		; FC dw
;CALL_2
		CALL	1234H			; CD dw
		CALL	$				; CD dw
		CALL	$ + 1234H		; CD dw

;.....................

;BIT_1
		BIT		0, (IX+12H)		; DD CB db 46
		BIT		1, (IX+0FFH)	; DD CB db 4E
		BIT		2, (IX+34H)		; DD CB db 56
		BIT		3, (IX+56H)		; DD CB db 5E
		BIT		4, (IX+78H)		; DD CB db 66
		BIT		5, (IX+9AH)		; DD CB db 6E
		BIT		6, (IX+ 0BCH)	; DD CB db 76
		BIT		7, (IX+V55)		; DD CB db 7E

		BIT		0, (IY+12H)		; FD CB db 46
		BIT		1, (IY+0FFH)	; FD CB db 4E
		BIT		2, (IY+34H)		; FD CB db 56
		BIT		3, (IY+56H)		; FD CB db 5E
		BIT		4, (IY+78H)		; FD CB db 66
		BIT		5, (IY+9AH)		; FD CB db 6E
		BIT		6, (IY+ 0BCH)	; FD CB db 76
		BIT		7, (IY+ 55O)	; DD CB db 7E
;BIT_2
		BIT		0,B				; CB 40
		BIT		0,C				; CB 41
		BIT		0,D				; CB 42
		BIT		0,E				; CB 43
		BIT		0,H				; CB 44
		BIT		0,L				; CB 45
		BIT		0,M				; CB 46
		BIT		0,A				; CB 47
		
		BIT		1,B				; CB 48
		BIT		1,C				; CB 49
		BIT		1,D				; CB 4A
		BIT		1,E				; CB 4B
		BIT		1,H				; CB 4C
		BIT		1,L				; CB 4D
		BIT		1,M				; CB 4E
		BIT		1,A				; CB 4F

		BIT		2,B				; CB 50
		BIT		2,C				; CB 51
		BIT		2,D				; CB 52
		BIT		2,E				; CB 53
		BIT		2,H				; CB 54
		BIT		2,L				; CB 55
		BIT		2,M				; CB 56
		BIT		2,A				; CB 57
		
		BIT		3,B				; CB 58
		BIT		3,C				; CB 59
		BIT		3,D				; CB 5A
		BIT		3,E				; CB 5B
		BIT		3,H				; CB 5C
		BIT		3,L				; CB 5D
		BIT		3,M				; CB 5E
		BIT		3,A				; CB 5F

		BIT		4,B				; CB 60
		BIT		4,C				; CB 61
		BIT		4,D				; CB 62
		BIT		4,E				; CB 63
		BIT		4,H				; CB 64
		BIT		4,L				; CB 65
		BIT		4,M				; CB 66
		BIT		4,A				; CB 67
		                             6
		BIT		5,B				; CB 68
		BIT		5,C				; CB 69
		BIT		5,D				; CB 6A
		BIT		5,E				; CB 6B
		BIT		5,H				; CB 6C
		BIT		5,L				; CB 6D
		BIT		5,M				; CB 6E
		BIT		5,A				; CB 6F

		BIT		6,B				; CB 70
		BIT		6,C				; CB 71
		BIT		6,D				; CB 72
		BIT		6,E				; CB 73
		BIT		6,H				; CB 74
		BIT		6,L				; CB 75
		BIT		6,M				; CB 76
		BIT		6,A				; CB 77
		                             7
		BIT		7,B				; CB 78
		BIT		7,C				; CB 79
		BIT		7,D				; CB 7A
		BIT		7,E				; CB 7B
		BIT		7,H				; CB 7C
		BIT		7,L				; CB 7D
		BIT		7,M				; CB 7E
		BIT		7,A				; CB 7F
;.....................


;AND_1
		AND		(IX + 10H)	; DD A6 db		
		AND		(IY + 10H)	; FD A6 db		
;AND_2
		AND		01			; E6 db
		AND		Value55AA	; E6 db
;AND_3
		AND		B			; A0
		AND		C			; A1
		AND		D			; A2
		AND		E			; A3
		AND		H			; A4
		AND		L			; A5
		AND		(HL)		; A6
		AND		A			; A7


;.....................

;ADD_1
		ADD		A,( IX+10H)	; DD 86 db
		ADD		A,(IY+ V55)	; FD 86 db
;ADD_2		
		ADD		A,B			; 80
		ADD		A,C			; 81
		ADD		A,D			; 82
		ADD		A,E			; 83
		ADD		A,H			; 84
		ADD		A,L			; 85
		ADD		A,M			; 86
		ADD		A,A			; 87
;ADD_3		
		ADD		A,010H		; C6 db
		ADD		A,V55		; C6 db
;ADD_4		
		ADD		HL,BC		; 09
		ADD		HL,DE		; 19
		ADD		HL,HL		; 29
		ADD		HL,SP		; 39
;ADD_5
		ADD		IX,BC		; DD 09
		ADD		IX,DE		; DD 19
		ADD		IX,IX		; DD 29
		ADD		IX,SP		; DD 39
		ADD		IY,BC		; FD 09
		ADD		IY,DE		; FD 19
		ADD		IY,IY		; FD 29
		ADD		IY,SP		; FD 39
		
;.....................

;ADC_1
		ADC		A,( IX+10H)	; DD 8E db
		ADC		A,(IY+ V55)	; FD 8E db
;ADC_2		
		ADC		A,B			; 88
		ADC		A,C			; 89
		ADC		A,D			; 8A
		ADC		A,E			; 8B
		ADC		A,H			; 8C
		ADC		A,L			; 8D
		ADC		A,M			; 8E
		ADC		A,A			; 8F
;ADC_3		
		ADC		A,010H		; CE db
		ADC		A,V55			; CE db
;ADC_4		
		ADC		HL,BC		; ED 4A
		ADC		HL,DE		; ED 5A
		ADC		HL,HL		; ED 6A
		ADC		HL,SP		; ED 7A
		
		
			

finish: