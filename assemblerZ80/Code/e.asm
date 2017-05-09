;d.asm
Value55	EQU		055H
Value55AA	EQU	055AAH

		ORG      0100H
Start:


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
;;;

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
;;;;;
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

		
		
		RES		0,B				; CB 80
		SET		0,B				; CB C0



		BIT		0, (IX+12H)			; DD CB db 46
		BIT		1, (IX+0FFH)		; DD CB db 4E
		BIT		2, (IX+34H)			; DD CB db 56
		BIT		3, (IX+56H)			; DD CB db 5E
		BIT		4, (IX+78H)			; DD CB db 66
		BIT		5, (IX+9AH)			; DD CB db 6E
		BIT		6, (IX+ 0BCH)			; DD CB db 76
		BIT		7, (IX+Value55)		; DD CB db 7E

		RES		0, (IX+12H)			; DD CB db 86
		RES		1, (IX+0FFH)		; DD CB db 8E
		RES		2, (IX+34H)			; DD CB db 96
		RES		3, (IX+56H)			; DD CB db 9E
		RES		4, (IX+78H)			; DD CB db A6
		RES		5, (IX+9AH)			; DD CB db AE
		RES		6, (IX+ 0BCH)			; DD CB db B6
		RES		7, (IX+Value55)		; DD CB db BE

		SET		0, (IX+12H)			; DD CB db C6
		SET		1, (IX+0FFH)		; DD CB db CE
		SET		2, (IX+34H)			; DD CB db C6
		SET		3, (IX+56H)			; DD CB db CE
		SET		4, (IX+78H)			; DD CB db C6
		SET		5, (IX+9AH)			; DD CB db CE
		SET		6, (IX+ 0BCH)			; DD CB db C6
		SET		7, (IX+Value55)		; DD CB db CE

		
		OUT		(10H),A			; D3 db

		JR		NZ, 10H			; 20 db
		JR		Z, 20H			; 28 db
		JR		NC, -1			; 30 db
		JR		C, -2			; 28 db


		CALL	NZ,1234H		; C4 dw
		CALL	Z,$				; CC dw
		CALL	NC,Start		; D4 dw
		CALL	C,$				; DC dw
		CALL	PO,1234H		; E4 dw
		CALL	PE,$			; EC dw
		CALL	P,1234H			; F4 dw
		CALL	M,$	- (7*3)		; FC dw
                                
		JP		NZ,1234H		; C2 dw
		JP		Z,$				; CA dw
		JP		NC,finish		; D2 dw
		JP		C,$				; DA dw
		JP		PO,1234H		; E2 dw
		JP		PE,$			; EA dw
		JP		P,1234H			; F2 dw
		JP		M,$				; FA dw

		


finish: