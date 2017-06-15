;Z.asm
	DB 'A visible character:'
	ADD	A,(IX+0)
;	LD	A,(IX+0)


	.org		0100h
;Start:
	JMP		Start
	DB	-1
	DW	-1
	
	.DB	-1
	.DW	-1
	
	.BYTE	'A'
	
	JP	NZ, Main;
Start:
	LD A,02

Main:
	JP	Main
	LD	A,01
	JP		0100H
	JP	(HL)
	JP	(IX)
	JP  (IY)
	
	JP	NZ, Main;
