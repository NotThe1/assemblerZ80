;Instruction02.asm
; LD only
V55			EQU		055H
Value55AA	EQU	055AAH

		ORG      0100H
Start:

;LD_1
;LD_2
;LD_3
;LD_4
;LD_5
;LD_6
;LD_7
		LD	HL,(Value55AA)	;2A lo hi
;LD_8
;LD_9
;LD_10
;LD_11
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
;LD_16
;LD_17
;LD_18
;LD_19
;LD_20
;LD_21
;LD_22
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