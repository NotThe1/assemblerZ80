;d.asm
Value55	EQU		055H
Value55AA	EQU	055AAH

		ORG      0100H
Start:
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