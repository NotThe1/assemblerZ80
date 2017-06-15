PrintString.asm:

	ORG		1800H

Start:

	LD		IX,message
	CALL	PSTRING
	HALT
	

message:
	DB	'1234567890'
	DB	00




; PRINT STRING TO TERMINAL
; ENTRY: IX STRING POINTER

PSTRING:
		LD		A,(IX + 0)
		CP		0
		JR		NZ,NEXT_LETTER
		RET

NEXT_LETTER:
		CALL	SEND_BYTE
	    INC		IX
	    JR		PSTRING
		
; Print the character in A to the termial
COUT:
        CALL	SEND_BYTE
	    RET
		
;----------------------
; SEND ASCII LETTER TO TERMINAL
; ENTRY: A

SEND_BYTE:
		LD		E,A          
		LD		A,7FH
		OUT		(1),A
		CALL	BIT_DELAY   ; SEND START BIT
		LD		D,8
            
CHK_BIT:
		BIT		0,E
		JP		Z,SEND_ZERO
		LD		A,0FFH
		OUT		(1),A
		JR		NEXT_BIT
		
SEND_ZERO:
		LD		A,7FH
		OUT		(1),A
		JR		NEXT_BIT
            
NEXT_BIT:
		CALL	BIT_DELAY    
		RRC		E
		DEC		D
		JP		NZ,CHK_BIT
		LD		A,0FFH
		OUT		(1),A
		CALL	BIT_DELAY  ; SEND STOP BIT
		RET
		
; BIT PERIOD FOR 2400 BIT/S
            
BIT_DELAY:
		LD		B,31H      ; old value 12H for 330pF
		NOP           ; new value 31H for 100pF 1198Hz (1200Hz)
		NOP
		NOP
		NOP
		DJNZ	$
		RET
		
		
		