; A.asm

				DW	1234
TRL_C			EQU		03H			; ETX
CTRL_E			EQU		05H			; physical eol

				ORG    0100H
				
				DW		5678
start:				DB		3

				DW		9012
				DB	'hello'
				DB	1,2,3,4
				DW	3456
				
				ORG	$ + 10
				
				DW	start
				
				
fini: