;Instruction05.asm
; LD only
V55			EQU		055H
Value55AA	EQU	055AAH

		ORG      0100H
Start:


;.....................
;.....................
;.....................
;.....................
;.....................
;ADC_1
		SBC		A,( IX+10H)	; DD 9E db
		SBC		A,(IY+ V55)	; FD 9E db
;ADC_2		
		SBC		A,B			; 98
		SBC		A,C			; 99
		SBC		A,D			; 9A
		SBC		A,E			; 9B
		SBC		A,H			; 9C
		SBC		A,L			; 9D
		SBC		A,M			; 9E
		SBC		A,A			; 9F
;ADC_3		
		SBC		A,010H		; DE db
		SBC		A,V55		; DE db
;ADC_4		
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