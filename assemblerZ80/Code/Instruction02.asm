;Instruction02.asm
; DJNZ - JR
V55			EQU		055H
Value55AA	EQU	055AAH

		ORG      0100H
Start:

;JR_1
		JR		NZ, 10H		; 20 db
		JR		Z, 20H		; 28 db
		JR		NC, -1		; 30 db
		JR		C, -2		; 28 db
;JR_2
		JR		10 + 10H	; 18 db
;.....................
;JP_1
		JP		NZ,1234H	; C2 dw
		JP		Z,$			; CA dw
		JP		NC,finish	; D2 dw
		JP		C,$			; DA dw
		JP		PO,1234H	; E2 dw
		JP		PE,$		; EA dw
		JP		P,1234H		; F2 dw
		JP		M,$			; FA dw
;JP_2
		JP		( IX) 		; DD E9
		JP		( IY )		; FD E9
;JP_3
		JP		(HL )		; E9
;JP_4
		JP		55AAH		; C3 dw
		JP		Start		; C3 dw
		JP		$			; C3 DW
;.....................
;INC_1
		INC		(IX + 10H)	; DD 34 db		
		INC		(IY + 10H)	; FD 34 db		


;INC_2
		INC		BC			; 03
		INC		DE			; 13
		INC		HL			; 23
		INC		SP			; 33
;INC_3
		INC		IX			; DD 23
		INC		IY			; FD 23
;INC_4
		INC		B			; 04
		INC		C			; 0C
		INC		D			; 14
		INC		E			; 1C
		INC		H			; 24
		INC		L			; 2C
		INC		M			; 34
		INC		A			; 3C


;.....................
;IN_1
		IN	A,(C)		; ED 78
;IN_2
		IN	A,(V55)		; DB db
;IN_3
		IN	B,(C)		; ED 40
		IN	C,(C)		; ED 48
		IN	D,(C)		; ED 50
		IN	E,(C)		; ED 58
		IN	H,(C)		; ED 60
		IN	L,(C)		; ED 68
		IN	A,(C)		; ED 78

;.....................
;IM
		IM	0			; ED 46
		IM	1			; ED 56
		IM	2			; ED 5E
;.....................

;EX_1
		EX	(SP),HL		; E3
;EX_2
		EX	(SP),IX		; DD E3
		EX	(SP),IY		; FD E3
;EX_3
		EX	AF,AF'		; 08
;EX_4
		EX	DE,HL		; EB
		
;.....................

;DJNZ_1
		DJNZ	10 + 10D	; 10 db
		DJNZ	V55			; 10 db

			

finish: