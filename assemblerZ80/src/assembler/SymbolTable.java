package assembler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Set;

public class SymbolTable extends Observable{
	
	private static SymbolTable instance = new SymbolTable();
	
	private HashMap<String, SymbolTableEntry> symbols;
	// private ArrayList<SymbolTableEntry> symbols;
	private SymbolTableEntry entry;
	private static boolean pass1 = true; // used to control two pass assembler
	private InstructionCounter instructionCounter;
	
	public static SymbolTable getInstance(){
		return instance;
	}//getInstance

	private SymbolTable() {
		symbols = new HashMap<String, SymbolTableEntry>();
		symbols.put("$", new SymbolTableEntry("$", 0, 0, SymbolTable.ASSEMBLER));
		instructionCounter = InstructionCounter.getInstance();
		// pass1 = true;
	}// SymbolTable

	public SymbolTable(InstructionCounter ic) {
		symbols = new HashMap<String, SymbolTableEntry>();
		this.instructionCounter = ic;
		// pass1 = true;
	}// SymbolTable

	public void reset() {
		symbols.clear();
		symbols.put("$", new SymbolTableEntry("$", 0, 0, SymbolTable.ASSEMBLER));
	}// reset

	public static void passOneDone() {
		pass1 = false;
	}//passOneDone

	public void defineSymbol(String name, int value, int lineNumber, int symbolType) {
		if (symbols.containsKey(name)) {
			// TODO - account for SET
			if (!pass1) {
				return;
			} //
			String error = String.format("Duplicate definition of %s at line # %04d%n", name, lineNumber);
			reportError(error);
		} else { // new symbol
			symbols.put(name, new SymbolTableEntry(name, value, lineNumber, symbolType));
		} // if
	}// defineSymbol

	public void referenceSymbol(String name, int lineNumber, int symbolType) {
		if (symbols.containsKey(name)) {
			entry = symbols.get(name);
			entry.addReferenceLineNumber(lineNumber);
			symbols.put(name, entry);
		} else {
			symbols.put(name, new SymbolTableEntry(name, lineNumber, symbolType));
		} // if
	}// referenceSymbol

	public void referenceSymbol(String name, int lineNumber) {
		if (symbols.containsKey(name)) {
			entry = symbols.get(name);
			entry.addReferenceLineNumber(lineNumber);
			symbols.put(name, entry);
		} else {
			String error = String.format("Attempting to reference an undefined symbol: %s on line: %04d",
					name, lineNumber);
			reportError(error);
		} // if
	}// referenceSymbol

	public HashMap<String, SymbolTableEntry> getTableEntries() {
		return symbols;
	}// getTableEntries

	public List<String> getAllSymbols() {
		Set<String> allSymbols = symbols.keySet();
		List<String> symbolList = new ArrayList<String>(allSymbols);
		Collections.sort(symbolList, new CaseInsensitiveSort());
		return symbolList;
	}// getAllSymbols2
	
	class CaseInsensitiveSort<String> implements Comparator<String> {
		@Override
		public int compare(String arg0, String arg1) {
			return ((java.lang.String) arg0).compareToIgnoreCase((java.lang.String) arg1);
		}// compare
	}// CaseInsensitiveSort

	public SymbolTableEntry getEntry(String symbol) {
		return symbols.get(symbol);
	}// getEntry

	public boolean contains(String name) {
		return symbols.containsKey(name);
	}// contains

	public int getType(String symbol) {
		return symbols.get(symbol).getSymbolType();
	}// getType

	public int getDefinedLineNumber(String symbol) {
		return symbols.get(symbol).getDefinedLineNumber();
	}// getDefinedLineNumber

	public List<Integer> getReferencedLineNumbers(String symbol) {
		return symbols.get(symbol).getReferencedLineNumbers();
	}// getDefinedLineNumber

	public String getTypeName(String symbol) {
		String ans = null;
		switch (this.getType(symbol)) {
		case LABEL:
			ans = "Label";
			break;
		case NAME:
			ans = "Name";
			break;
		case ASSEMBLER:
			ans = "Assembler";
			break;
		default:
			ans = "Unknown";
		}
		return ans;
	}// getType

	public Integer getValue(String name) {
		if (name.equals("$")) {
			return instructionCounter.getPriorLocation();
		} else {
			try {
				return symbols.get(name).getValue();
			} catch (NullPointerException npe) {
				System.err.printf("Bad symbol: %s%n", name);
				return 0;
			} // try
		} // if

	}// getValue
	
	private void reportError(String error){
		this.setChanged();
		this.notifyObservers(error);	
	}//reportError
	
		// ----------------------------------------------------------------------

	public final static int LABEL = 0;
	public final static int NAME = 1;
	public final static int ASSEMBLER = 2;

	public final static int LOCAL = 3;
	public final static int GLOBAL = 4;

}// class SymbolTable
