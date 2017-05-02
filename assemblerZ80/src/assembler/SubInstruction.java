package assembler;

import java.util.regex.Pattern;

public class SubInstruction {
	public String name;
	public String instruction;
	public int size;
	public Byte[] baseCodes;
	public String argument1Type;
	public Pattern pattern1;
	public String argument2Type;
	public Pattern pattern2;
	
	public SubInstruction(String name,int size,String argument1Type,Pattern pattern1,
			String argument2Type,Pattern pattern2,Byte[] baseCodes){
		this.name=name;
		this.size=size;
		this.baseCodes = baseCodes;
		this.argument1Type = argument1Type;
		this.pattern1= pattern1;
		this.argument2Type = argument2Type;
		this.pattern2= pattern2;
		
		String[] ins = name.split("_");
		this.instruction= ins[0];
	}//Constructor
	
	
	
	public SubInstruction(String name,int size,String argument1Type,Pattern pattern1,Byte[] baseCodes){
		this( name, size, argument1Type, pattern1,null, null, baseCodes);
	}//Constructor


	public SubInstruction(String name,int size,Byte[] baseCodes){
		this( name, size, null, null,null, null, baseCodes);
	}//Constructor
	


	
	
	

}//class SubInstruction
