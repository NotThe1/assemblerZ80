;B.asm
TRUE			EQU		-1			; not false
$INCLUDE A.ASM

FALSE			EQU		0000H
ON				EQU		-1
OFF				EQU		0000H
LO_NIBBLE_MASK	EQU		0FH			; mask used to obtain the bytes low four bits
BYTE			EQU		1			; number of bytes for "byte" type
WORD			EQU		2			; number of bytes for "word" type

				DS		0100H
label1:
				db 'X'

