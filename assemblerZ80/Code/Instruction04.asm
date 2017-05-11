;Instruction04.asm
; LD only
V55			EQU		055H
Value55AA	EQU	055AAH

		ORG      0100H
Start:
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


;.....................
;.....................
;.....................
;.....................
;.....................
;.....................


finish: