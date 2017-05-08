package assembler;

import java.util.regex.Pattern;

public class SubInstruction {
	public String name = null;
	public String instruction =null;
	public byte[] baseCodes = null;
	public String argument1Type= null;
	public Pattern pattern1=null;
	public String argument2Type=null;
	public Pattern pattern2= null;
	
	public SubInstruction(String name,int size,String argument1Type,Pattern pattern1,
			String argument2Type,Pattern pattern2,byte[] baseCodes){
		this(name,size,baseCodes);
		this.argument1Type = argument1Type;
		this.pattern1= pattern1;
		this.argument2Type = argument2Type;
		this.pattern2= pattern2;
	}//Constructor
	
	
	
	public SubInstruction(String name,int size,String argument1Type,Pattern pattern1,byte[] baseCodes){
		this(name,size,baseCodes);
		this.argument1Type = argument1Type;
		this.pattern1= pattern1;	
	}//Constructor


	public SubInstruction(String name,int size,byte[] baseCodes){
		this.name=name;
		this.baseCodes = baseCodes;

		String[] ins = name.split("_");
		this.instruction= ins[0];
	}//Constructor
	
//-----------------------------------------------------------------	
	
	public SubInstruction(String name,String argument1Type,String argument2Type,byte[] baseCodes){
		this(name,baseCodes);
		this.argument1Type = argument1Type;
	}//Constructor

	public SubInstruction(String name,String argument1Type,byte[] baseCodes){
		this(name,baseCodes);
		this.argument1Type = argument1Type;
	}//Constructor

	public SubInstruction(String name,byte[] baseCodes){
		this.name=name;
		this.baseCodes = baseCodes;

		String[] ins = name.split("_");
		this.instruction= ins[0];
	}//Constructor
	
	public int getSize(){
		return baseCodes.length;
	}//getSize
	


	
	
	

}//class SubInstruction
