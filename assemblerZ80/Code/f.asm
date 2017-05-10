;e.asm
Value55	EQU		055H
Value55AA	EQU	055AAH

		ORG      0100H
Start:

		ADC		A,0100H		; CD db
		ADD		A,Value55	; C6 db
		SBC		A, -1		; DE db
		LD		A, 255		; 3E DB

		LD		(IX+10H),B	; DD 70 db
		LD		(IX+11H),C	; DD 71 db
		LD		(IX+12H),D	; DD 72 db
		LD		(IX+13H),E	; DD 73 db
		LD		(IX+14H),H	; DD 74 db
		LD		(IX+15H),L	; DD 75 db
		LD		(IX+16H),A	; DD 77 db

		LD		(IY+20H),B	; FD 70 db
		LD		(IY+21H),C	; FD 71 db
		LD		(IY+22H),D	; FD 72 db
		LD		(IY+23H),E	; FD 73 db
		LD		(IY+24H),H	; FD 74 db
		LD		(IY+25H),L	; FD 75 db
		LD		(IY+26H),A	; FD 77 db
		
		LD		(IX+26H),Value55	; DD 76 db
		LD		(IY+26H),8+8	; FD 76 db

		
		

		EX		(SP),HL		; E3
		EX		(SP),IX		; DD E3
		EX		(SP),IY		; FD E3

		LD		(HL),B		; 70
		LD		(HL),C		; 71
		LD		(HL),D		; 72
		LD		(HL),E		; 73
		LD		(HL),H		; 74
		LD		(HL),L		; 75
		LD		(HL),A		; 77
		
		LD		(HL),Value55	; 36 db

		LD		(BC),A		; 02
		LD		(DE),A		; 12
                        
		OUT		(C),B		; ED 41			
		OUT		(C),C		; ED 49			
		OUT		(C),D		; ED 51			
		OUT		(C),E		; ED 59			
		OUT		(C),H		; ED 61			
		OUT		(C),L		; ED 69			
		OUT		(C),0		; ED 71			
		OUT		(C),A		; ED 79			

finish: