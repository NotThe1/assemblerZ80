;Instruction03.asm
; LD only
V55			EQU		055H
Value55AA	EQU	055AAH

		ORG      0100H
Start:

;LD_1
		LD	A,(IX+07)	;DD 7E db
		LD	A,(IY+17H)	;FD 7E db
;LD_2
		LD	A,(BC)		; 0A
		LD	A,(DE)		; 1A
;LD_3
		LD	A,I			; ED 57
		LD	A,R			; ED 5F
;LD_4
		LD	A,B			; 78
		LD	A,C			; 79
		LD	A,D			; 7A
		LD	A,E			; 7B
		LD	A,H			; 7C
		LD	A,L			; 7D
		LD	A,M			; 7E
		LD	A,(HL)		; 7E
		LD	A,A			; 7F
;LD_5
		LD	A,(Start)	; 3A lo hi
;LD_6
		LD	A,V55		; 3E db
;LD_7
		LD	HL,(Value55AA)	;2A lo hi
;LD_8
		LD	SP,HL			; F9
;LD_9
		LD	SP,IX			; DD F9
		LD	SP,IY			; FD F9
;LD_10
		LD	SP,(Value55AA)	; ED 7B lo hi
;LD_11
		LD	SP,Value55AA	; 31 lo hi
;LD_12
		LD	(BC),A		; 02
		LD	(DE),A		; 12
;LD_13
		LD	(IX + 12H),-1	; DD 36 db db - DD 36 12 FF
		LD	(IY + 0AAH),0	; DD 36 db db - DD 36 AA 00
;LD_14
		LD	(IX+0BH),B		; DD 70 0B
		LD	(IX+0CH),C		; DD 71 0C
		LD	(IX+0DH),D		; DD 72 0D
		LD	(IX+0EH),E		; DD 73 0E
		LD	(IX+0FH),H		; DD 74 0F
		LD	(IX+0),L		; DD 75 00
		LD	(IX+(-1)),A		; DD 77 FF
;LD_15
		LD	I,A				; ED 47
		LD	R,A				; ED 4F
;LD_16
		LD	BC,(Value55AA)	; ED 4B lo hi
		LD	DE,(Value55AA)	; ED 5B lo hi
		LD	HL,(Value55AA)	; ED 6B lo hi
;		LD	SP,(Value55AA)	; ED 7B lo hi
;LD_17
		LD	BC,Value55AA	; 01 lo hi
		LD	DE,Value55AA	; 11 lo hi
		LD	HL,Value55AA	; 21 lo hi
;		LD	SP,Value55AA	; 31 lo hi
;LD_18
		LD	IX,(1234H)		; DD 2A lo hi
		LD	IY,(5678H)		; FD 2A lo hi
;LD_19
		LD	IX,1234H		; DD 21 lo hi
		LD	IY,5678H		; FD 21 lo hi

;LD_20
		LD	B,B			; 40
		LD	B,C			; 41
		LD	B,D			; 42
		LD	B,E			; 43
		LD	B,H			; 44
		LD	B,L			; 45
		LD	B,M			; 46
		LD	B,(HL)		; 46
		LD	B,A			; 47
		
		LD	C,B			; 48
		LD	C,C			; 49
		LD	C,D			; 4A
		LD	C,E			; 4B
		LD	C,H			; 4C
		LD	C,L			; 4D
		LD	C,M			; 4E
		LD	C,(HL)		; 4E
		LD	C,A			; 4F
		
		
		LD	D,B			; 50
		LD	D,C			; 51
		LD	D,D			; 52
		LD	D,E			; 53
		LD	D,H			; 54
		LD	D,L			; 55
		LD	D,M			; 56
		LD	D,(HL)		; 56
		LD	D,A			; 57
		                 
		LD	E,B			; 58
		LD	E,C			; 59
		LD	E,D			; 5A
		LD	E,E			; 5B
		LD	E,H			; 5C
		LD	E,L			; 5D
		LD	E,M			; 5E
		LD	E,(HL)		; 5E
		LD	E,A			; 5F
		
		LD	H,B			; 60
		LD	H,C			; 61
		LD	H,D			; 62
		LD	H,E			; 63
		LD	H,H			; 64
		LD	H,L			; 65
		LD	H,M			; 66
		LD	H,(HL)		; 66
		LD	H,A			; 67
		                  
		LD	L,B			; 68
		LD	L,C			; 69
		LD	L,D			; 6A
		LD	L,E			; 6B
		LD	L,H			; 6C
		LD	L,L			; 6D
		LD	L,M			; 6E
		LD	L,(HL)		; 6E
		LD	L,A			; 6F
		
		                  		                  
		
		
;LD_21
		LD	B,(IX+1)	;DD 46 db
		LD	C,(IX+2)	;DD 4E db
		LD	D,(IX+3)	;DD 56 db
		LD	E,(IX+4)	;DD 5E db
		LD	H,(IX+5)	;DD 66 db
		LD	L,(IX+6)	;DD 6E db
		
		LD	B,(IY+11H)	;FD 46 db
		LD	C,(IY+12H)	;FD 4E db
		LD	D,(IY+13H)	;FD 56 db
		LD	E,(IY+14H)	;FD 5E db
		LD	H,(IY+15H)	;FD 66 db
		LD	L,(IY+16H)	;FD 6E db
;LD_22
		LD	B,V55		; 06
		LD	C,V55		; 0D
		LD	D,V55		; 16
		LD	E,V55		; 1D
		LD	H,V55		; 26
		LD	L,V55		; 2D
;		LD	M,V55		; 36
		LD	(HL),V55		; 36
;LD_23
		LD	(HL),B		; 70
		LD	(HL),C		; 71
		LD	(HL),D		; 72
		LD	(HL),E		; 73
		LD	(HL),H		; 74
		LD	(HL),L		; 75
		LD	(HL),A		; 77
;LD_24
		LD	(HL),V55	; 36 db

			

finish: