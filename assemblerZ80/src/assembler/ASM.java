package assembler;

/*
 * 	 2020-03-09  Removed local AppLogger and replace with reference to it in a Jar
 * 	 2019-05-06  Refactored Collection.sort(list,String.CASE_INSENSITIVE_ORDER) in SymbolTable
 *   2019-03-29	 Removed MyFileChoose, kept dialog on frame 
 * 	 2018-12-04  Trapped Index Out Of Range Exception.
 *				 Set Parent for JOption Pane error report at end of assembly
 *   2018-11-23  Added saving Terse setting between sessions
 *   2018-11-22  Fixed problem with non Terse MemFile 
 *   2018-11-20  Fixed problem with resolving Relative Jumps - FM
 *   2018-11-19  set rev at 2.0
 */
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.prefs.Preferences;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import appLogger.AppLogger;
//import appLogger.AppLogger;
import parser.EvaluationException;
import parser.ExpressionNode;
import parser.Parser;
import parser.ParserException;
import parser.SetVariable;
import parser.Token;
import parser.TokenType;
import parser.Tokenizer;

public class ASM {// implements Observer

	private AdapterForASM adapterForASM = new AdapterForASM();
	private InstructionCounter instructionCounter = InstructionCounter.getInstance();
	private SymbolTable symbolTable = SymbolTable.getInstance();
	private Parser parser = new Parser();
	private Tokenizer tokenizer = new Tokenizer();
//	private AppLogger appLogger = AppLogger.getInstance();
	private AppLogger log = AppLogger.getInstance();

	private Queue<SourceLineParts> allLineParts;
	// private HashMap<String, Byte> conditionTable;

	private String defaultDirectory;
	private String outputPathAndBase;
	private File asmSourceFile = null;
	private String sourceFileBase;
	private String sourceFileFullName;

	private StyledDocument docSource;
	private StyledDocument docListing;
	private JScrollBar sbarSource;
	private JScrollBar sbarListing;

	private void doStart() {
		lblStatus.setText(EMPTY_STRING);
		instructionCounter.reset();
		symbolTable.reset();
		if (asmSourceFile == null) {
			return; // do nothing
		}
		clearDoc(docSource);
		clearDoc(docListing);

		log.resetCounts();
		loadSourceFile(asmSourceFile, 1, null);
		allLineParts = new LinkedList<SourceLineParts>();

		passOne(); // make symbol table & fix labels
		ByteBuffer memoryImage = passTwo();

		if (rbListing.isSelected()) {
			saveListing();
		} // if listing
		if (rbMemFile.isSelected() || rbHexFile.isSelected()) {
			saveMemoryFile(memoryImage);
		} // if memory Image
		mnuFilePrintListing.setEnabled(true);
		
		

		if (log.getErrorCount() + log.getWarningCount() != 0) {
			String msg = String.format("There are %d Errors%n and %d Warnings%n", log.getErrorCount(),
					log.getWarningCount());
			JOptionPane.showMessageDialog(frmAsmAssembler, msg);

		}//if
		
		}// start
	

	private void saveListing() {
		try {
			FileWriter fw = new FileWriter(new File(outputPathAndBase + DOT + SUFFIX_LISTING));
			PrintWriter pw = new PrintWriter(fw);
			Scanner scanner = new Scanner(tpListing.getText());
			String listingLine;
			while (scanner.hasNextLine()) {
				listingLine = scanner.nextLine();
				listingLine = listingLine.replaceAll("\\s++$", EMPTY_STRING);
				if (listingLine.equals(EMPTY_STRING)) {
					continue; // skip
				} // if empty line
				pw.println(listingLine);
			} // while
			scanner.close();
			pw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} // try
	}// saveListing

	private void saveMemoryFile(ByteBuffer memoryImage) {
		int startAddress = instructionCounter.getLowestLocationSet() & 0XFFF0;
		byte[] memImage = new byte[memoryImage.capacity() - startAddress];
		memoryImage.position(startAddress);
		memoryImage.get(memImage);

		Pattern patternEmpty;
		Matcher matcherEmpty;
		String line = null;
		FileWriter fwMemFile = null;
		PrintWriter pwMemFile = null;
		FileWriter fwHexFile = null;
		PrintWriter pwHexFile = null;
		String memFile = outputPathAndBase + DOT + SUFFIX_MEM;
		String hexFile = outputPathAndBase + DOT + SUFFIX_HEX;
		try {

			Files.deleteIfExists(Paths.get(memFile));
			Files.deleteIfExists(Paths.get(hexFile));

			if (rbMemFile.isSelected()) {
				patternEmpty = Pattern.compile(": 00 00 00 00 00 00 00 00  00 00 00 00 00 00 00 00");
				fwMemFile = new FileWriter(new File(memFile));
				pwMemFile = new PrintWriter(fwMemFile);
				MemFormatter memFormatter = MemFormatter.memFormatterFactory(startAddress, memImage);
				while (memFormatter.hasNext()) {
					line = memFormatter.getNext();

					matcherEmpty = patternEmpty.matcher(line);
					if (matcherEmpty.find() && rbTerse.isSelected()) {
						continue;
					} // if
					pwMemFile.print(line);
				} // while Mem File
				pwMemFile.close();
				fwMemFile.close();
			} // if Mem

			if (rbHexFile.isSelected()) {
				fwHexFile = new FileWriter(new File(hexFile));
				pwHexFile = new PrintWriter(fwHexFile);
				String hexNumber;
				Integer dataCount;
				HexFormatter hexFormatter = new HexFormatter(startAddress, memImage);
				while (hexFormatter.hasNext()) {
					line = hexFormatter.getNext();
					if (!rbTerse.isSelected()) {
						pwHexFile.println(line);
					} else {
						hexNumber = line.substring(1, 3);
						dataCount = Integer.valueOf(hexNumber, 16);
						dataCount = (dataCount * 2) + 2; // add in data type
						// patternEmpty = Pattern.compile("{i}:" + hexNumber + "[0-9A-F]{4}[0]{" + dataCount +"}");
						patternEmpty = Pattern.compile(":" + hexNumber + "[0-9A-F]{4}[0]{" + dataCount + "}");
						matcherEmpty = patternEmpty.matcher(line);
						if (!matcherEmpty.find()) {
							pwHexFile.println(line);
						} // inner if
					} // outer if
				} // while Hex File
				pwHexFile.println(hexFormatter.getEOF());
				pwHexFile.close();
				fwHexFile.close();
			} // if Hex

		} catch (IOException e) {
			e.printStackTrace();
		} // try

	}// saveMemoryFile

	/**
	 * passTwo makes final pass at source, using symbol table to generate the object code
	 */
	private ByteBuffer passTwo() {
		// int hiAddress = ((((instructionCounter.getCurrentLocation() - 1) / SIXTEEN) + 2) * SIXTEEN) - 1;
		int hiAddress = (instructionCounter.getCurrentLocation() - 1) | 0X0F;
		ByteBuffer memoryImage = ByteBuffer.allocate(hiAddress + 1);

		int lowestLocationSet = instructionCounter.getLowestLocationSet();
		instructionCounter.reset(lowestLocationSet);
		instructionCounter.setCurrentLocation(lowestLocationSet);
		int currentLocation;
		String instructionImage;
		SourceLineParts sourceLineParts;

		while (!allLineParts.isEmpty()) {
			instructionImage = EMPTY_STRING;

			currentLocation = instructionCounter.getCurrentLocation();
			sourceLineParts = allLineParts.poll();

			if (sourceLineParts.hasInstruction()) {
				instructionImage = setMemoryBytesForInstruction(sourceLineParts);
			} else if (sourceLineParts.hasDirective()) {
				instructionImage = setMemoryBytesForDirective(sourceLineParts);
			} // if
			makeListing(currentLocation, instructionImage, sourceLineParts);
			if (!instructionImage.equals(EMPTY_STRING)) {
				buildMemoryImage(currentLocation, instructionImage, memoryImage);
			} // if
		} // while

		tpListing.setCaretPosition(0);
		makeXrefListing();
		// makeMemoryFile(memoryImage);
		return memoryImage;
	}// passTwo

	private String setMemoryBytesForInstruction(SourceLineParts sourceLineParts) {
		// String instructionStr = EMPTY_STRING;
		instructionCounter.incrementCurrentLocation(sourceLineParts.getOpCodeSize());

		byte[] netCodes = null;
		if (sourceLineParts.getArgumentCount() == 0) {
			String subOpCode = sourceLineParts.getSubOpCode();
			netCodes = SubInstructionSet.getBaseCodes(subOpCode);
		} else {
			netCodes = getActualCodes(sourceLineParts);
		}
		return getInstructionCode(netCodes);
	}// setMemoryBytesForInstruction

	private String getInstructionCode(byte[] baseCodes) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < baseCodes.length; i++) {
			sb.append(String.format("%02X ", baseCodes[i]));
		} // for
		return sb.toString().trim();
	}// getInstructionCode

	// <><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>
	private byte[] getActualCodes(SourceLineParts sourceLineParts) {
		String subOpCode = sourceLineParts.getSubOpCode();
		SubInstruction si = SubInstructionSet.getSubInstruction(subOpCode);
		byte[] ans = null;
		try {
			ans = si.baseCodes.clone();
		} catch (NullPointerException npe) {
			String msg = String.format("Line %04d has an invalid Operation, No SubInstruction",
					sourceLineParts.getLineNumber());
			reportError(msg);
			ans = new byte[] { (byte) 00 };
			return ans;
		}

		String argument1 = sourceLineParts.getArgument1();
		String argument2 = sourceLineParts.getArgument2();
		byte regByte;
		int value;

		switch (si.getInstruction()) {
		case "ADC":
		case "SBC":
			if (si.argument1Type.equals(Z80.LIT_HL)) { // ADC_4
				regByte = Z80.getRegisterByte(argument2);
				ans[1] = (byte) (ans[1] | regByte);
			} else {// arg1 is LIT_A
				if (si.argument2Type.equals(Z80.R_MAIN)) { // ADC_2
					regByte = Z80.getRegisterByte(argument2);
					ans[0] = (byte) (ans[0] | regByte);
				} else if (si.argument2Type.equals(Z80.IND_XYd)) { // ADC_1
					if (argument2.startsWith("(IY")) {
						ans[0] = (byte) 0XFD;
					} // else use the stored value for IX
					String exp = argument2.substring(4, argument2.length() - 1);
					value = resolveExpression(exp, sourceLineParts.getLineNumber());
					ans[2] = (byte) (ans[2] | value);
				} else { // ADC_3
					value = resolveExpression(argument2, sourceLineParts.getLineNumber());
					ans[1] = (byte) (ans[1] | value);
				} // inner else
			} // outer if
			break;

		case "ADD":
			if (si.argument1Type.equals(Z80.LIT_HL)) { // ADD_4
				regByte = Z80.getRegisterByte(argument2);
				ans[0] = (byte) (ans[0] | regByte);
			} else if (si.argument2Type.equals(Z80.LIT_IXY)) {// ADD_5
				if (argument1.equals("IY")) {
					ans[0] = (byte) 0XFD;
				} // else use the stored value for IX
				regByte = Z80.getRegisterByte(argument2);
				ans[1] = (byte) (ans[1] | regByte);
			} else {// arg1 is LIT_A
				if (si.argument2Type.equals(Z80.R_MAIN)) { // ADD_2
					regByte = Z80.getRegisterByte(argument2);
					ans[0] = (byte) (ans[0] | regByte);
				} else if (si.argument2Type.equals(Z80.IND_XYd)) { // ADD_1
					if (argument2.startsWith("(IY")) {
						ans[0] = (byte) 0XFD;
					} // else use the stored value for IX
					String exp = argument2.substring(4, argument2.length() - 1);
					value = resolveExpression(exp, sourceLineParts.getLineNumber());
					ans[2] = (byte) (ans[2] | value);
				} else { // ADC_3
					value = resolveExpression(argument2, sourceLineParts.getLineNumber());
					ans[1] = (byte) (ans[1] | value);
				} // inner else
			} // outer if
			break;
		case "OR":
		case "AND":
		case "CP":
		case "SUB":
		case "XOR":
			if (si.argument1Type.equals(Z80.R_MAIN)) { // AND_2
				regByte = Z80.getRegisterByte(argument1);
				ans[0] = (byte) (ans[0] | regByte);
			} else if (si.argument1Type.equals(Z80.IND_XYd)) { // AND_1
				if (argument1.startsWith("(IY")) {
					ans[0] = (byte) 0XFD;
				} // else use the stored value for IX
				String exp = argument1.substring(4, argument1.length() - 1);
				value = resolveExpression(exp, sourceLineParts.getLineNumber());
				ans[2] = (byte) (ans[2] | value);
			} else { // AND_3
				value = resolveExpression(argument1, sourceLineParts.getLineNumber());
				ans[1] = (byte) (ans[1] | value);
			} // if
			break;

		case "BIT":
			value = resolveExpression(argument1, sourceLineParts.getLineNumber());
			Byte bitValue = Z80.bitTable.get(value);
			if (bitValue == null) {
				String msg = String.format("%s on Line %04d is an invalid argument, must resolve to 0 thru 7",
						argument1, sourceLineParts.getLineNumber());
				reportError(msg);
				break;
			} // if - value

			if (si.argument2Type.equals(Z80.R_MAIN)) {// BIT_2
				ans[1] = (byte) (ans[1] | (byte) bitValue);
				value = resolveExpression(argument1, sourceLineParts.getLineNumber());
				value = Z80.getRegisterByte(argument2);
				ans[1] = (byte) (ans[1] | value);
			} else {// //BIT_1 IND_XYd
				ans[3] = (byte) (ans[3] | (byte) bitValue);
				if (argument2.startsWith("(IY")) {
					ans[0] = (byte) 0XFD;
				} // else use the stored value for IX
				String exp = argument2.substring(4, argument2.length() - 1);
				value = resolveExpression(exp, sourceLineParts.getLineNumber());
				ans[2] = (byte) (ans[2] | value);
			} // if
			break;

		case "CALL":
			String target = argument1;
			if (si.argument1Type.equals(Z80.COND)) {// CALL_1
				byte c = Z80.getConditionByte(argument1);
				ans[0] = (byte) (ans[0] | c);
				target = argument2;
			} // if conditions
				// Both CALL_1 and CALL_2
			ans = resolveDW(ans, target, sourceLineParts.getLineNumber());
			break;

		case "DEC":
		case "INC":
			if (si.argument1Type.equals(Z80.IND_XYd)) {// DEC_1
				if (argument1.startsWith("(IY")) {
					ans[0] = (byte) 0XFD;
				} // else use the stored value for IX
				String exp = argument1.substring(4, argument1.length() - 1);
				value = resolveExpression(exp, sourceLineParts.getLineNumber());
				ans[2] = (byte) (ans[2] | (byte) value);
			} else if (si.argument1Type.equals(Z80.LIT_IXY)) {// DEC_3
				if (argument1.equals("IY")) {
					ans[0] = (byte) 0XFD;
				} // else use the stored value for IX
			} else if (si.argument1Type.equals(Z80.R_MAIN)) {// DEC_2
				regByte = Z80.getRegisterByte(argument1);
				ans[0] = (byte) (ans[0] | regByte);
			} else {// DEC_4
				ans[0] = (byte) (ans[0] | getRegLeft3(argument1));
			} // if
			break;

		case "DJNZ":
			// value = resolveExpression(argument1, sourceLineParts.getLineNumber());
			value = resolveRelativeValue(argument1, sourceLineParts.getLineNumber());
			ans[1] = (byte) (ans[1] | value);
			break;

		case "EX":
			if (si.argument1Type.equals(Z80.IND_SP)) {
				if (argument2.equals("IY")) {
					ans[0] = (byte) 0XFD;
				} // if use the stored value for IX
					// both defaults are accurate otherwise
			} // if not EX_1 or EX_2, Code is fixed for EX_3 & EX_4
			break;

		case "IM":
			value = resolveExpression(argument1, sourceLineParts.getLineNumber());
			if (value == 0) {
				ans[1] = (byte) 0X46;
			} else if (value == 1) {
				ans[1] = (byte) 0X56;
			} else if (value == 2) {
				ans[1] = (byte) 0X5E;
			} else {
				String msg = String.format("%s on Line %04d is an invalid argument, must resolve to 0,1 or 2",
						argument1, sourceLineParts.getLineNumber());
				reportError(msg);
			} // if
			break;

		case "IN":
			if (si.argument2Type.equals(Z80.EXP_ADDR)) {// IN_2
				value = resolveExpression(argument2, sourceLineParts.getLineNumber());
				ans[1] = (byte) (ans[1] | (byte) value);
			} else {// IN_1 and IN_3
				ans[1] = (byte) (ans[1] | getRegLeft3(argument1));
			} // if
			break;

		case "JP":
			if (si.argument1Type.equals(Z80.IND_HL)) {// JP_3
				// no work here
			} else if (si.argument1Type.equals(Z80.IND_XY)) {// JP_2
				if (argument1.startsWith("(IY")) {
					ans[0] = (byte) 0XFD;
				} // else use the stored value for IX
			} else if (si.argument1Type.equals(Z80.COND)) {// JP_1
				regByte = Z80.getConditionByte(argument1);
				ans[0] = (byte) (ans[0] | regByte);
				ans = resolveDW(ans, argument2, sourceLineParts.getLineNumber());
			} else {// JP_4
				ans = resolveDW(ans, argument1, sourceLineParts.getLineNumber());
			} // if
			break;

		case "JR":
			String relExpression = argument1;
			if (si.argument1Type.equals(Z80.COND_LIMITED)) {// JR_1
				regByte = Z80.getConditionByte(argument1);
				ans[0] = (byte) (ans[0] | regByte);
				relExpression = argument2;
			} // if
				// Both JR_2 and JR_1
				// value = resolveExpression(relExpression, sourceLineParts.getLineNumber());
			value = resolveRelativeValue(relExpression, sourceLineParts.getLineNumber());
			ans[1] = (byte) (ans[1] | (byte) value);
			break;

		case "LD":
			switch (si.argument1Type) {

			case Z80.LIT_A:// LD_1,LD_2,LD_3,LD_4,LD_5,LD_6
				if (si.argument2Type.equals(Z80.IND_XYd)) { // LD_1
					if (argument2.startsWith("(IY")) {
						ans[0] = (byte) 0XFD;
					} // else use the stored value for IX
					String exp = argument2.substring(4, argument2.length() - 1);
					value = resolveExpression(exp, sourceLineParts.getLineNumber());
					ans[2] = (byte) (ans[2] | (byte) value);
				} else if (si.argument2Type.equals(Z80.IND_BCDE)) { // LD_2
					if (argument2.equals("(DE)")) {
						ans[0] = (byte) 0X1A;
					} // if
				} else if (si.argument2Type.equals(Z80.LIT_RI)) { // LD_3
					if (argument2.equals("R")) {
						ans[1] = (byte) 0X5F;
					} // if
				} else if (si.argument2Type.equals(Z80.R_MAIN)) { // LD_4
					regByte = Z80.getRegisterByte(argument2);
					ans[0] = (byte) (ans[0] | regByte);
				} else if (si.argument2Type.equals(Z80.EXP_ADDR)) { // LD_5
					ans = resolveDW(ans, argument2, sourceLineParts.getLineNumber());
				} else { // LD_6
					value = resolveExpression(argument2, sourceLineParts.getLineNumber());
					ans[1] = (byte) (ans[1] | (byte) value);
				} // if
				break;

			case Z80.LIT_HL: // LD_7
				ans = resolveDW(ans, argument2, sourceLineParts.getLineNumber());
				break;

			case Z80.LIT_SP: // LD_8,LIT_9,LIT_10,LIT_11
				if (argument2.equals("HL")) { // LD_8
					// do nothing
				} else if (si.argument2Type.equals(Z80.LIT_IXY)) { // LD_9
					if (argument2.equals("IY")) {
						ans[0] = (byte) 0XFD;
					} // else use the stored value for IX
				} else { // LD_10,LD_11
					ans = resolveDW(ans, argument2, sourceLineParts.getLineNumber());
				} // if
				break;

			case Z80.IND_BCDE: // LD_12
				if (argument1.equals("(DE)")) {
					ans[0] = (byte) 0X12;
				} // else is BC , use the default
				break;

			case Z80.IND_XYd: // LD_13, LD_14
				if (argument1.startsWith("(IY")) {
					ans[0] = (byte) 0XFD;
				} // if use the stored value for IX

				String exp = argument1.substring(4, argument1.length() - 1);
				value = resolveExpression(exp, sourceLineParts.getLineNumber());
				ans[2] = (byte) (ans[2] | (byte) value);
				if (si.argument2Type.equals(Z80.R_MAIN)) {// LD_13
					regByte = Z80.getRegisterByte(argument2);
					ans[1] = (byte) (ans[1] | regByte);
				} else { // LD_14 expression
					value = resolveExpression(argument2, sourceLineParts.getLineNumber());
					ans[3] = (byte) (ans[3] | (byte) value);
				} // if
				break;

			case Z80.LIT_RI: // LD_15
				if (argument1.equals("R")) {
					ans[1] = (byte) 0X4F;
				} // I is the default
				break;

			case Z80.R16_BDH: // LD_16, LD_17
				ans = resolveDW(ans, argument2, sourceLineParts.getLineNumber());
				regByte = Z80.getRegisterByte(argument1);

				if (si.argument2Type.equals(Z80.EXP_ADDR)) {// LD_16
					ans[1] = (byte) (ans[1] | (byte) regByte);
				} else { // LD_17
					ans[0] = (byte) (ans[0] | (byte) regByte);
				} // if
				break;

			case Z80.LIT_IXY: // LD_18, LD_19
				if (argument1.equals("IY")) {
					ans[0] = (byte) 0XFD;
				} // else use the stored value for IX
				ans = resolveDW(ans, argument2, sourceLineParts.getLineNumber());
				regByte = Z80.getRegisterByte(argument1);
				break;

			case Z80.R81: // LD_20, LD_21,LD_22
				if (si.argument2Type.equals(Z80.R_MAIN)) {// LD_20
					ans[0] = (byte) (ans[0] | getRegLeft3(argument1));
					regByte = Z80.getRegisterByte(argument2);
					ans[0] = (byte) (ans[0] | regByte);
				} else if (si.argument2Type.equals(Z80.IND_XYd)) { // LD_21
					ans[1] = (byte) (ans[1] | getRegLeft3(argument1));
					if (argument2.startsWith("(IY")) {
						ans[0] = (byte) 0XFD;
					} // else use the stored value for IX
					exp = argument2.substring(4, argument2.length() - 1);
					value = resolveExpression(exp, sourceLineParts.getLineNumber());
					ans[2] = (byte) (ans[2] | (byte) value);
				} else { // LD_22
					ans[0] = (byte) (ans[0] | getRegLeft3(argument1));
					value = resolveExpression(argument2, sourceLineParts.getLineNumber());
					ans[1] = (byte) (ans[1] | (byte) value);
				} // if
				break;

			case Z80.IND_HL: // LD_23, LD_24
				if (si.argument2Type.equals(Z80.R_MAIN)) {
					regByte = Z80.getRegisterByte(argument2);
					ans[0] = (byte) (ans[0] | regByte);
				} else { // Expression
					value = resolveExpression(argument2, sourceLineParts.getLineNumber());
					ans[1] = (byte) (ans[1] | (byte) value);
				} // if
				break;

			case Z80.EXP_ADDR1: // LD_25,LD_26,LD_27,LD_28
				if (argument2.equals("IY")) {// LD_26
					ans[0] = (byte) 0XFD;
				} // else use the stored value for IX
				if (si.argument2Type.equals(Z80.R16_SP)) { // LD_28
					regByte = Z80.getRegisterByte(argument2);
					ans[1] = (byte) (ans[1] | regByte);
				} // if
					// rest need ony identify the address
				ans = resolveDW(ans, argument1, sourceLineParts.getLineNumber());
				break;

			default: // LD switch
				break;
			}// Inner switch
				// ...........<><><><><><><>............
			break;

		case "OUT":
			if (si.argument1Type.equals(Z80.IND_C)) {// OUT_1
				if (argument2.equals("0")) {
					argument2 = "M";
				} // force
				ans[1] = (byte) (ans[1] | getRegLeft3(argument2));
			} else {// OUT_2
				value = resolveExpression(argument1, sourceLineParts.getLineNumber());
				ans[1] = (byte) (ans[1] | value);
			} // if
			break;

		case "POP":
		case "PUSH":
			if (si.argument1Type.equals(Z80.LIT_IXY)) {// PUSH_1,POP_1
				if (argument1.equals("IY")) {
					ans[0] = (byte) 0XFD;
				} // else use the stored value for IX
			} else {// PUSH_2,POP_2
				regByte = Z80.getRegisterByte(argument1);
				ans[0] = (byte) (ans[0] | regByte);
			} // if
			break;

		case "RES":
		case "SET":
			value = resolveExpression(argument1, sourceLineParts.getLineNumber());
			bitValue = Z80.bitTable.get(value);
			if (bitValue == null) {
				String msg = String.format("%s on Line %04d is an invalid argument, must resolve to 0 thru 7",
						argument1, sourceLineParts.getLineNumber());
				reportError(msg);
				break;
			} // if - value

			if (si.argument2Type.equals(Z80.R_MAIN)) { // RES_1
				ans[1] = (byte) (ans[1] | (byte) bitValue);
				regByte = Z80.getRegisterByte(argument2);
				ans[1] = (byte) (ans[1] | regByte);

			} else { // RES_2
				if (argument2.startsWith("(IY")) {
					ans[0] = (byte) 0XFD;
				} // if use the stored value for IX

				String exp = argument2.substring(4, argument2.length() - 1);
				value = resolveExpression(exp, sourceLineParts.getLineNumber());
				ans[2] = (byte) (ans[2] | (byte) value);

				ans[3] = (byte) (ans[3] | (byte) bitValue);
			} // if argument 2 type
			break;

		case "RET":
			if (si.argument1Type != null) {// RET_1
				byte c = Z80.getConditionByte(argument1);
				ans[0] = (byte) (ans[0] | c);
			} // if - do nothing if RET_2
			break;

		case "RL":
		case "RLC":
		case "RR":
		case "RRC":
		case "SLA":
		case "SRA":
		case "SRL":
			if (si.argument1Type.equals(Z80.IND_XYd)) { // RL_1
				if (argument1.startsWith("(IY")) {
					ans[0] = (byte) 0XFD;
				} // else use the stored value for IX
				String exp = argument1.substring(4, argument1.length() - 1);
				value = resolveExpression(exp, sourceLineParts.getLineNumber());
				ans[2] = (byte) (ans[2] | (byte) value);
			} else { // RL_2
				regByte = Z80.getRegisterByte(argument1);
				ans[1] = (byte) (ans[1] | regByte);
			} // if
			break;

		case "RST":
			value = resolveExpression(argument1, sourceLineParts.getLineNumber());
			if ((value > 56) | (value % 8 != 0)) {
				String msg = String.format("%s on Line %04d is an invalid argument", argument1,
						sourceLineParts.getLineNumber());
				reportError(msg);
				break;
			} // if
			ans[0] = (byte) (ans[0] | (byte) value);
			break;

		default: // major switch
		}// switch Instruction

		return ans;
	}// getActualCodes

	private byte getRegLeft3(String arg) {
		byte regValue = Z80.getRegisterByte(arg);
		return (byte) (regValue << 3);
	}// getRegLeft3

	// <><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>

	private byte[] resolveDW(byte[] target, String argument, int lineNumber) {
		byte[] work = target.clone();
		int offset = work.length - 2;
		int value = resolveExpression(argument, lineNumber);
		value = value & 0XFFFF; // limit to 64K
		byte hiByte = (byte) ((value >> 8) & 0XFF);
		byte loByte = (byte) (value & 0XFF);
		work[offset] = loByte;
		work[offset + 1] = hiByte;
		return work;
	}// resolveDW

	// -----------------------------------------------

	private String setMemoryBytesForDirective(SourceLineParts sourceLineParts) {

		if (!sourceLineParts.hasArguments()) {
			String msg = String.format("%s on Line %04d is an invalid argument", sourceLineParts.getDirective(),
					sourceLineParts.getLineNumber());
			reportError(msg);
		} // if - we have arguments

		String args;
		int ansInt;
		StringBuilder sb = new StringBuilder();

		int locationCount = 0;

		Scanner scannerDirective = new Scanner(sourceLineParts.getArgument1());
		scannerDirective.useDelimiter(COMMA);

		switch (sourceLineParts.getDirective().toUpperCase()) {
		case "DB":
			byte aByte;
			while (scannerDirective.hasNext()) {
				args = scannerDirective.next();
				if (args.matches(stringValuePattern)) { // literal
					args = args.replace(QUOTE, EMPTY_STRING);
					char[] allCharacters = args.toCharArray();
					for (char aCharacter : allCharacters) {
						aByte = (byte) aCharacter;
						sb.append(String.format("%02X ", aByte));
						locationCount++;
					} // for each
				} else {
					ansInt = resolveSimpleArgument(args, sourceLineParts.getLineNumber()) & 0XFF;
					sb.append(String.format("%02X ", ansInt));
					locationCount++;
				} // if
			} // while
			break;
		case "DW":
			while (scannerDirective.hasNext()) {
				args = scannerDirective.next();
				ansInt = resolveSimpleArgument(args, sourceLineParts.getLineNumber()) & 0XFFFF;
				byte hiByte = (byte) (ansInt >> 8);
				byte loByte = (byte) (ansInt & 0X00FF);
				sb.append(String.format("%02X %02X", loByte, hiByte));
				locationCount = 2;
			} // while
			break;
		case "DS":
			locationCount = resolveSimpleArgument(sourceLineParts.getArgument1(), sourceLineParts.getLineNumber())
					& 0XFFFF;
			break;
		case "ORG":
			Integer loc = resolveSimpleArgument(sourceLineParts.getArgument1(), sourceLineParts.getLineNumber());
			if (loc != null) {
				instructionCounter.setCurrentLocation(loc);
				instructionCounter.setPriorLocation();
				locationCount = 0;
			} // if

			break;
		default:
		}// switch
		scannerDirective.close();
		instructionCounter.incrementCurrentLocation(locationCount);
		return sb.toString();
	}// setMemoryBytesForDirective

	private void buildMemoryImage(int pc, String lineImage, ByteBuffer memoryImage0) {

		int intValue;
		String[] parts = lineImage.trim().split("\\s");
		for (int i = 0; i < parts.length; i++) {
			intValue = Integer.valueOf(parts[i], 16);
			try {
				memoryImage0.put((Integer) pc + i, (byte) intValue);
			} catch (IndexOutOfBoundsException obe) {
				// System.err.printf("[ASM.buildMemoryImage] %s%n", "Index Out of Range");
				String message = String.format("Index Out Of range - pc: %X,  lineImage: %s", pc, lineImage);
				//appLogger.addError(message);
				reportError(message);

			}
		} // for

	}// saveMemoryImage

	private void makeXrefListing() {
		String stars = "************************";
		insertListing(System.lineSeparator() + System.lineSeparator() + System.lineSeparator() + System.lineSeparator(),
				null);
		insertListing(String.format("           %s   %s   %s%n%n", stars, "Xref", stars), attrMaroon);

		List<String> symbolList = symbolTable.getAllSymbols();

		List<Integer> referenceList;
		for (String symbol : symbolList) {
			insertListing(String.format("%04d: ", symbolTable.getDefinedLineNumber(symbol)), attrSilver);
			SimpleAttributeSet sab = symbolTable.getType(symbol) == SymbolTable.LABEL ? attrBlue : attrNavy;

			insertListing(String.format("%-15s ", symbol), sab);
			insertListing(String.format("%04X   ", symbolTable.getValue(symbol)), attrRed);

			referenceList = symbolTable.getReferencedLineNumbers(symbol);
			for (Integer reference : referenceList) {
				insertListing(String.format("%04d ", reference), attrBlack);
			} // for references

			insertListing(System.lineSeparator(), null);
		} // for

	}// makeXrefListing ,symbolTable.getDefinedLineNumber(symbol)

	private void makeListing(int location, String memoryImage, SourceLineParts sourceLineParts) {
		String cmd;
		SimpleAttributeSet attributeSet;
		if (sourceLineParts.hasInstruction()) {
			cmd = String.format("%-6s ", sourceLineParts.getInstruction());
			attributeSet = attrNavy;
		} else if (sourceLineParts.hasDirective()) {
			cmd = String.format("%-6s ", sourceLineParts.getDirective());
			attributeSet = attrBlue;
		} else {
			cmd = EMPTY_STRING;
			attributeSet = null;
		} // if

		String symbol;
		SimpleAttributeSet attributeSet1;
		if (sourceLineParts.hasLabel()) {
			symbol = String.format("%-10s ", sourceLineParts.getLabel());// + COLON
			attributeSet1 = attrNavy;
		} else if (sourceLineParts.hasName()) {
			symbol = String.format("%-10s ", sourceLineParts.getName());
			attributeSet1 = attrNavy;
		} else {
			symbol = String.format("%-10s ", EMPTY_STRING);
			attributeSet1 = null;
		} // if

		String lineNumberStr = String.format("%04d: ", sourceLineParts.getLineNumber());
		insertListing(lineNumberStr, attrSilver);
		String memLocation = String.format("%04X ", location);
		insertListing(memLocation, attrGray);
		String image = String.format("%-8s", memoryImage);
		insertListing(image, attrRed);

		if (sourceLineParts.isLineAllComment()) {
			insertListing(sourceLineParts.getComment(), attrGreen);
		} else {
			insertListing(String.format("%4s", EMPTY_STRING), null);
			insertListing(symbol, attributeSet1);
			insertListing(cmd, attributeSet);
			String argument = String.format("%-20s ", sourceLineParts.getArgumentField());
			insertListing(argument, attrBlack);
			insertListing(sourceLineParts.getComment(), attrGreen);
		} // if only comment

		insertListing(System.lineSeparator(), null);
	}// makeListing

	/**
	 * passOne sets up the symbol table with initial value for Labels & symbols
	 */
	private void passOne() {
		// clearDoc(docListing);

		int lineNumber;
		String sourceLine;
		// LineParser lineParser = new LineParser();
		SourceLineAnalyzer lineAnalyzer = new SourceLineAnalyzer();
		SourceLineParts sourceLineParts;
		Scanner scannerPassOne = new Scanner(tpSource.getText());
		while (scannerPassOne.hasNextLine()) {
			sourceLine = scannerPassOne.nextLine();
			if (sourceLine.equals(EMPTY_STRING)) {
				continue;
			} // if skip textbox's empty lines

			sourceLineParts = lineAnalyzer.analyze(sourceLine);

			if (!sourceLineParts.isLineActive()) {
				continue;
			} // if skip textbox's empty lines

			lineNumber = sourceLineParts.getLineNumber();
			allLineParts.add(sourceLineParts);

			if (sourceLineParts.hasLabel()) {
				processLabel(sourceLineParts, lineNumber);
			} // if - has label

			if (sourceLineParts.hasInstruction()) {
				instructionCounter.incrementCurrentLocation(sourceLineParts.getOpCodeSize());
			} // if instruction

			if (sourceLineParts.hasDirective()) {
				processDirectiveForLineCounter(sourceLineParts, lineNumber);
			} // if directives

			if (sourceLineParts.hasName()) {
				processSymbol(sourceLineParts, lineNumber);
			} // if has symbol

			// displayStuff(lineParser);
		} // while
		SymbolTable.passOneDone();
		scannerPassOne.close();
	}// passOne

	private void processSymbol(SourceLineParts slp, int lineNumber) {
		if (!slp.hasDirective()) {
			return; // symbol definition needs to be on a directive line
		} // if have a Directive?
		String symbol = slp.getName();

		switch (slp.getDirective().toUpperCase()) {
		case "EQU":
			int value = resolveSimpleArgument(slp.getArgument1(), lineNumber);
			symbolTable.defineSymbol(symbol, value, lineNumber, SymbolTable.NAME);
			break;
		case "SET":
		case "MACRO":
		case "$INCLUDE":
			// ok let it go
			break;
		default:
//			appLogger.addError(
			reportError(
					String.format("** Check line number %d directive is = %s%n", lineNumber, slp.getDirective()));
			// look out for Include
		}// switch

	}// processSymbol

	private void processLabel(SourceLineParts slp, int lineNumber) {
		String label = slp.getLabel().replace(":", EMPTY_STRING);
		symbolTable.defineSymbol(label, instructionCounter.getCurrentLocation(), lineNumber, SymbolTable.LABEL);
	}// processSymbol

	private void processDirectiveForLineCounter(SourceLineParts slp, int lineNumber) {
		String directive = slp.getDirective();
		String arguments = slp.getArgument1();
		String errorMsg = String.format("Directive %s on line: %04d not yet implemented", directive, lineNumber);
		Scanner scannerComma;
		switch (directive.toUpperCase()) {
		// case "EQU":
		// int value = resolveSimpleArgument(arguments,lineNumber);
		// symbolTable.defineSymbol(slp.getName(), value, lineNumber, SymbolTable.NAME);
		// break;
		case "DB":
			if (arguments != null) {
				String arg;
				scannerComma = new Scanner(arguments);
				scannerComma.useDelimiter(COMMA);
				while (scannerComma.hasNext()) {
					arg = scannerComma.next();
					if (arg.matches(stringValuePattern)) {
						arg = arg.replace("'", "");
						instructionCounter.incrementCurrentLocation(arg.length());
					} else {
						instructionCounter.incrementCurrentLocation();
					} // if
				} // while
			} else {
				String msg = String.format("Directive %s on line: %04d needs an argument", slp.getDirective(),
						lineNumber);
				reportError(msg);
				break;
			} // if
			break;
		case "DW":
			if (arguments != null) {
				scannerComma = new Scanner(arguments);
				scannerComma.useDelimiter(COMMA);
				while (scannerComma.hasNext()) {
					instructionCounter.incrementCurrentLocation(2);
					scannerComma.next();
				} // while
			} else {
				String msg = String.format("Directive %s on line: %04d needs an argument", slp.getDirective(),
						lineNumber);
				reportError(msg);
				break;
			} // if
			break;
		case "DS":
			int storage = resolveSimpleArgument(arguments, lineNumber);
			instructionCounter.incrementCurrentLocation(storage);
			break;
		case "ORG":
			Integer loc = resolveSimpleArgument(arguments, lineNumber);
			if (loc != null) {
				instructionCounter.setCurrentLocation(loc);
				instructionCounter.setPriorLocation();
			} // if
			break;
		case "ASEG":
			if (arguments == null) {
				instructionCounter.makeCurrent(InstructionCounter.ASEG);
			} else {
				instructionCounter.makeCurrent(InstructionCounter.ASEG, arguments);
			} // if
			break;
		case "DSEG":
			if (arguments == null) {
				instructionCounter.makeCurrent(InstructionCounter.DSEG);
			} else {
				instructionCounter.makeCurrent(InstructionCounter.DSEG, arguments);
			} // if
			break;
		case "CSEG":
			if (arguments == null) {
				instructionCounter.makeCurrent(InstructionCounter.CSEG);
			} else {
				instructionCounter.makeCurrent(InstructionCounter.CSEG, arguments);
			} // if
			break;
		case "END":
			// throw new AssemblerException(errorMsg);
			break;
		case "IF":
		case "ELSE":
		case "ENDIF":
		case "PUBLIC":
		case "EXTRN":
		case "NAME":
		case "STKLN":
		case "TITLE":
			reportError(errorMsg);
			break;
		default:
			// NOP
			break;
		}// switch directive

	}// processDirectiveForLineCounter

	private int loadSourceFile(File sourceFile, int lineNumber, SimpleAttributeSet attr) {
		try {
			FileReader source = new FileReader(sourceFile);
			BufferedReader reader = new BufferedReader(source);
			String line = null;
			String rawLine = null;
			String outputLine;
			Matcher matcherInclude;
			Pattern patternForInclude = Pattern.compile("\\$INCLUDE ", Pattern.CASE_INSENSITIVE);
			while ((rawLine = reader.readLine()) != null) {

				line = rawLine;
				// outputLine = String.format("%04d %s%n", lineNumber++, line);
				outputLine = String.format("%04d %s\n", lineNumber++, line);

				insertSource(outputLine, attr);
				// txtSource.append(outputLine);
				matcherInclude = patternForInclude.matcher(line);

				if (matcherInclude.find()) {
					String fileReference = line.substring(matcherInclude.end(), line.length()).trim();
					lineNumber = doInclude(fileReference, sourceFile.getParentFile().getAbsolutePath(), lineNumber);
				} // if
			} // while
			reader.close();
			mnuFilePrintSource.setEnabled(true);
			mnuFilePrintListing.setEnabled(false);
		} catch (IOException e) {
			// e.printStackTrace();
			String error = String.format("File Not Found!! - %s", sourceFile.getAbsolutePath());
			reportError(error);
		} // TRY
			// return lineNumber;
		return lineNumber;
	}// loadSourceFile

	public int doInclude(String fileReference, String parentDirectory, int lineNumber) {

		if (!fileReference.contains("\\")) {
			fileReference = parentDirectory + System.getProperty("file.separator") + fileReference;
		} //

		if (!(fileReference.toUpperCase().endsWith(SUFFIX_ASSEMBLER_Z80.toUpperCase()))) {
			fileReference += "." + SUFFIX_ASSEMBLER_Z80;
		} //

		String includeMarker = ";<<<<<<<<<<<<<<<<<<<<<<< Include >>>>>>>>>>>>>>>>";
		insertSource(String.format("%04d %s%n", lineNumber++, includeMarker), attrBlue);

		File includedFile = new File(fileReference);
		lineNumber = loadSourceFile(includedFile, lineNumber, attrBlue);

		insertSource(String.format("%04d %s%n", lineNumber++, includeMarker), attrBlue);
		//
		return lineNumber;
	}// doInclude

	private void insertSource(String str, SimpleAttributeSet attr) {
		try {
			docSource.insertString(docSource.getLength(), str, attr);
		} catch (BadLocationException e) {
			e.printStackTrace();
		} // try
	}// insertSource

	private void insertListing(String str, SimpleAttributeSet attr) {
		try {
			// docListing.
			docListing.insertString(docListing.getLength(), str, attr);
		} catch (BadLocationException e) {
			e.printStackTrace();
		} // try
	}// insertSource

	/* ---------------------------------------------------------------------------------- */

	private void openFile() {
		JFileChooser fileChooser = new JFileChooser(defaultDirectory);
		fileChooser.setMultiSelectionEnabled(false);
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Assembler Source Code",
				SUFFIX_ASSEMBLER_8080, SUFFIX_ASSEMBLER_Z80));
		fileChooser.setAcceptAllFileFilterUsed(false);
		
		if (fileChooser.showOpenDialog(frmAsmAssembler) != JFileChooser.APPROVE_OPTION) {
			System.out.printf("%s%n", "You cancelled the file open");
		} else {
			asmSourceFile = fileChooser.getSelectedFile();
			prepareSourceFile(asmSourceFile);
			outputPathAndBase = defaultDirectory + FILE_SEPARATOR + sourceFileBase;
		} // if
	}// openFile

	private void prepareSourceFile(File asmSourceFile) {
		String asmSourceFileName = asmSourceFile.getName();
		// String asmSourceDirectory = asmSourceFile.getPath();
		String[] nameParts = (asmSourceFileName.split("\\."));
		sourceFileBase = nameParts[0];
		lblSourceFilePath.setText(asmSourceFile.getAbsolutePath());
		sourceFileFullName = asmSourceFile.getAbsolutePath();
		lblSourceFileName.setText(asmSourceFile.getName());
		lblListingFileName.setText(sourceFileBase + "." + SUFFIX_LISTING);
		defaultDirectory = asmSourceFile.getParent();
		outputPathAndBase = defaultDirectory + FILE_SEPARATOR + sourceFileBase;

		clearDoc(docSource);
		clearDoc(docListing);
		loadSourceFile(asmSourceFile, 1, null);
		tpSource.setCaretPosition(0);
		btnStart.setEnabled(true);
	}// prepareSourceFile

	private void doLoadLastFile() {
		asmSourceFile = new File(sourceFileFullName);
		prepareSourceFile(asmSourceFile);
	}// doLoadLastFile

	private void clearDoc(StyledDocument doc) {
		try {
			doc.remove(0, doc.getLength());
		} catch (BadLocationException e) {
			// Auto-generated catch block
			e.printStackTrace();
		} // try
	}// clearDoc

	/* ---------------------------------------------------------------------------------- */

	private void printListing(JTextPane textPane, String name) {
		Font originalFont = textPane.getFont();
		try {
			// textPane.setFont(new Font("Courier New", Font.PLAIN, 8));
			textPane.setFont(originalFont.deriveFont(8.0f));
			MessageFormat header = new MessageFormat(name);
			MessageFormat footer = new MessageFormat(new Date().toString() + "           Page - {0}");
			textPane.print(header, footer);
			// textPane.setFont(new Font("Courier New", Font.PLAIN, 14));
			textPane.setFont(originalFont);
		} catch (PrinterException e) {
			e.printStackTrace();
		} // try
	}// printListing

	/* ---------------------------------------------------------------------------------- */
	/* ---------------------------------------------------------------------------------- */

	private Integer resolveSimpleArgument(String argument, Integer lineNumber) {
		// Integer ans = null;
		Integer ans = 0;
		if (argument.equals("$")) {
			ans = instructionCounter.getCurrentLocation();
		} else if (symbolTable.contains(argument)) {
			ans = symbolTable.getValue(argument);
			symbolTable.referenceSymbol(argument, lineNumber);
		} else if (argument.matches(stringValuePattern)) {
			// ans = 0;
			String s = argument.replace("'", ""); // remove the 's
			byte[] ba = s.getBytes();
			for (byte b : ba) {
				ans = (ans << 8) + (b & 0XFF);
			} //

		} else if (argument.matches(hexValuePattern)) {
			ans = Integer.valueOf(argument.replace("H", ""), 16);
		} else if (argument.matches(octalValuePattern)) {
			ans = Integer.valueOf(argument.substring(0, argument.length() - 1), 8);
		} else if (argument.matches(decimalValuePattern)) {
			ans = Integer.valueOf(argument.replace("D", ""), 10);
		} else if (argument.matches(binaryValuePattern)) {
			ans = Integer.valueOf(argument.replace("D", ""), 2);

		} else {// send to expression resolver
			ans = resolveExpression(argument, lineNumber);
		}
		if (ans == null) {
			System.out.printf("Null ans from argument: %s%n", argument);
		}

		return (ans != null) ? ans & 0XFFFF : 0; // max value is 64K
	}// resolveSimpleArgument

	private Integer resolveRelativeValue(String argument, Integer lineNumber) {
		Integer ans = null;
		int targetType = symbolTable.getType(argument);
		if (symbolTable.contains(argument) && (targetType == SymbolTable.LABEL || targetType == SymbolTable.NAME)) {
			// }//if argument in symbol table
			// if (symbolTable.contains(argument) && symbolTable.getType(argument) == SymbolTable.LABEL) {
			int nextInstructionLocation = symbolTable.getValue("$") + 2;
			int symbolValue = symbolTable.getValue(argument);
			ans = symbolValue - nextInstructionLocation;
			if ((ans < -124) || (ans > 131)) {
				String msg = String.format("[ASM.resolveRelativeValue] Argument out of range %s %x%n", argument,
						symbolValue);
				reportError(msg);
				ans = 0;
			} // if

			symbolTable.referenceSymbol(argument, lineNumber);

		} else {
			ans = resolveExpression(argument, lineNumber);
		}
		return ans;

	}// resolveRelativeValue

	private Integer resolveExpression(String arguments, Integer lineNumber) {
		Integer answer = null;
		try {
			tokenizer.tokenize(arguments);
			ExpressionNode expression = parser.parse(tokenizer.getTokens());
			LinkedList<Token> tokens = tokenizer.getTokens();

			for (Token t : tokens) {
				if (t.tokenType == TokenType.VARIABLE) {
					int value = symbolTable.getValue(t.sequence);
					symbolTable.referenceSymbol(t.sequence, lineNumber);

					expression.accept(new SetVariable(t.sequence, value));
				} // if - its a variable
			}
			answer = expression.getValue();
		} catch (ParserException pe) {
			String msg = String.format(" Line %04d has a  bad argument: %s", lineNumber, arguments);
			reportError(msg);
		} catch (EvaluationException ee) {
			String msg = String.format(" Line %04d has a  bad argument: %s", lineNumber, arguments);
			reportError(msg);
		} // try
		return answer;
	}// resolveExpression

	/* ---------------------------------------------------------------------------------- */
	/* ---------------------------------------------------------------------------------- */

	/* ---------------------------------------------------------------------------------- */

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ASM window = new ASM();
					window.frmAsmAssembler.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				} // try
			}// run
		});
	}// main

	private void reportError(String messsage) {
		log.errorCount("*************" +  messsage + "*************");
	}// reportError

	private void setAttributes() {
		StyleConstants.setForeground(attrNavy, new Color(0, 0, 128));
		StyleConstants.setForeground(attrBlack, new Color(0, 0, 0));
		StyleConstants.setForeground(attrBlue, new Color(0, 0, 255));
		StyleConstants.setForeground(attrGreen, new Color(0, 128, 0));
		StyleConstants.setForeground(attrTeal, new Color(0, 128, 128));
		StyleConstants.setForeground(attrGray, new Color(128, 128, 128));
		StyleConstants.setForeground(attrSilver, new Color(192, 192, 192));
		StyleConstants.setForeground(attrRed, new Color(255, 0, 0));
		StyleConstants.setForeground(attrMaroon, new Color(128, 0, 0));
	}// setAttributes

	private void appClose() {
		Preferences myPrefs = Preferences.userNodeForPackage(ASM.class).node(this.getClass().getSimpleName());
		Dimension dim = frmAsmAssembler.getSize();
		myPrefs.putInt("Height", dim.height);
		myPrefs.putInt("Width", dim.width);
		Point point = frmAsmAssembler.getLocation();
		myPrefs.putInt("LocX", point.x);
		myPrefs.putInt("LocY", point.y);
		myPrefs.putInt("Divider", splitPane.getDividerLocation());
		myPrefs.putBoolean("rbListing", rbListing.isSelected());
		myPrefs.putBoolean("rbMemFile", rbMemFile.isSelected());
		myPrefs.putBoolean("rbHexFile", rbHexFile.isSelected());
		myPrefs.putBoolean("rbTerse", rbTerse.isSelected());

		myPrefs.put("defaultDirectory", defaultDirectory);
		myPrefs.put("LastFile", sourceFileFullName);
		myPrefs = null;

		// cleanUp

		System.exit(0);
	}// appClose

	private void appInit() {
		Preferences myPrefs = Preferences.userNodeForPackage(ASM.class).node(this.getClass().getSimpleName());
		frmAsmAssembler.setLocation(myPrefs.getInt("LocX", 100), myPrefs.getInt("LocY", 100));
		frmAsmAssembler.setSize(myPrefs.getInt("Width", 700), myPrefs.getInt("Height", 600));
		splitPane.setDividerLocation(myPrefs.getInt("Divider", 200));
		defaultDirectory = myPrefs.get("defaultDirectory", DEFAULT_DIRECTORY);
		rbListing.setSelected(myPrefs.getBoolean("rbListing", true));
		rbMemFile.setSelected(myPrefs.getBoolean("rbMemFile", true));
		rbHexFile.setSelected(myPrefs.getBoolean("rbHexFile", true));
		rbTerse.setSelected(myPrefs.getBoolean("rbTerse", true));
		sourceFileFullName = myPrefs.get("LastFile", "");
		myPrefs = null;

		docSource = tpSource.getStyledDocument();
		sbarSource = spSource.getVerticalScrollBar();
		sbarSource.setName(SBAR_SOURCE);
		sbarSource.addAdjustmentListener(adapterForASM);

		docListing = tpListing.getStyledDocument();
		log.setDoc(tpListing.getStyledDocument());
		log.setDoc(tpListing.getStyledDocument());

		sbarListing = spListing.getVerticalScrollBar();
		sbarListing.setName(SBAR_LISTING);
		sbarListing.addAdjustmentListener(adapterForASM);

		mnuFilePrintSource.setEnabled(false);
		mnuFilePrintListing.setEnabled(false);

		// symbolTable.addObserver(this);
		//
		setAttributes();
	}// appInit

	public ASM() {
		initialize();
		appInit();
	}// constructor

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAsmAssembler = new JFrame();
		frmAsmAssembler.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				appClose();
			}
		});
		frmAsmAssembler.setTitle("ASM - assembler for Zilog Z80  2.1.0");
		frmAsmAssembler.setBounds(100, 100, 662, 541);
		frmAsmAssembler.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 30, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		frmAsmAssembler.getContentPane().setLayout(gridBagLayout);

		JPanel panelTop = new JPanel();
		GridBagConstraints gbc_panelTop = new GridBagConstraints();
		gbc_panelTop.insets = new Insets(0, 0, 5, 0);
		gbc_panelTop.fill = GridBagConstraints.BOTH;
		gbc_panelTop.gridx = 0;
		gbc_panelTop.gridy = 0;
		frmAsmAssembler.getContentPane().add(panelTop, gbc_panelTop);
		GridBagLayout gbl_panelTop = new GridBagLayout();
		gbl_panelTop.columnWidths = new int[] { 0, 0 };
		gbl_panelTop.rowHeights = new int[] { 0, 0, 0 };
		gbl_panelTop.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panelTop.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		panelTop.setLayout(gbl_panelTop);

		lblSourceFilePath = new JLabel(NO_FILE);
		GridBagConstraints gbc_lblSourceFilePath = new GridBagConstraints();
		gbc_lblSourceFilePath.insets = new Insets(0, 0, 5, 0);
		gbc_lblSourceFilePath.anchor = GridBagConstraints.NORTH;
		gbc_lblSourceFilePath.gridx = 0;
		gbc_lblSourceFilePath.gridy = 0;
		panelTop.add(lblSourceFilePath, gbc_lblSourceFilePath);

		JPanel panelMain = new JPanel();
		GridBagConstraints gbc_panelMain = new GridBagConstraints();
		gbc_panelMain.fill = GridBagConstraints.BOTH;
		gbc_panelMain.gridx = 0;
		gbc_panelMain.gridy = 1;
		panelTop.add(panelMain, gbc_panelMain);
		GridBagLayout gbl_panelMain = new GridBagLayout();
		gbl_panelMain.columnWidths = new int[] { 80, 0, 0 };
		gbl_panelMain.rowHeights = new int[] { 0, 0 };
		gbl_panelMain.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panelMain.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panelMain.setLayout(gbl_panelMain);

		JPanel panelLeft = new JPanel();
		GridBagConstraints gbc_panelLeft = new GridBagConstraints();
		gbc_panelLeft.insets = new Insets(0, 0, 0, 5);
		gbc_panelLeft.fill = GridBagConstraints.VERTICAL;
		gbc_panelLeft.gridx = 0;
		gbc_panelLeft.gridy = 0;
		panelMain.add(panelLeft, gbc_panelLeft);
		GridBagLayout gbl_panelLeft = new GridBagLayout();
		gbl_panelLeft.columnWidths = new int[] { 0, 0, 0 };
		gbl_panelLeft.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelLeft.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panelLeft.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		panelLeft.setLayout(gbl_panelLeft);

		btnStart = new JButton("Start");
		btnStart.setEnabled(false);
		btnStart.setName(START_BUTTON);
		btnStart.addActionListener(adapterForASM);
		GridBagConstraints gbc_btnStart = new GridBagConstraints();
		gbc_btnStart.insets = new Insets(0, 0, 5, 5);
		gbc_btnStart.anchor = GridBagConstraints.NORTH;
		gbc_btnStart.gridx = 0;
		gbc_btnStart.gridy = 0;
		panelLeft.add(btnStart, gbc_btnStart);

		rbListing = new JRadioButton("Listing");
		rbListing.setSelected(true);
		GridBagConstraints gbc_rbListing = new GridBagConstraints();
		gbc_rbListing.anchor = GridBagConstraints.WEST;
		gbc_rbListing.insets = new Insets(0, 0, 5, 5);
		gbc_rbListing.gridx = 0;
		gbc_rbListing.gridy = 2;
		panelLeft.add(rbListing, gbc_rbListing);

		rbMemFile = new JRadioButton("Mem File");
		rbMemFile.setSelected(true);
		GridBagConstraints gbc_rbMemFile = new GridBagConstraints();
		gbc_rbMemFile.anchor = GridBagConstraints.WEST;
		gbc_rbMemFile.insets = new Insets(0, 0, 5, 5);
		gbc_rbMemFile.gridx = 0;
		gbc_rbMemFile.gridy = 4;
		panelLeft.add(rbMemFile, gbc_rbMemFile);

		rbHexFile = new JRadioButton("Hex File");
		rbHexFile.setSelected(true);
		GridBagConstraints gbc_rbHexFile = new GridBagConstraints();
		gbc_rbHexFile.anchor = GridBagConstraints.WEST;
		gbc_rbHexFile.insets = new Insets(0, 0, 5, 5);
		gbc_rbHexFile.gridx = 0;
		gbc_rbHexFile.gridy = 6;
		panelLeft.add(rbHexFile, gbc_rbHexFile);

		JButton btnLoadLastFile = new JButton("Load Last File");
		btnLoadLastFile.setName(BTN_LOAD_LAST_FILE);
		btnLoadLastFile.addActionListener(adapterForASM);

		rbTerse = new JRadioButton("Terse");
		rbTerse.setSelected(true);
		GridBagConstraints gbc_rbTerse = new GridBagConstraints();
		gbc_rbTerse.anchor = GridBagConstraints.WEST;
		gbc_rbTerse.insets = new Insets(0, 0, 5, 5);
		gbc_rbTerse.gridx = 0;
		gbc_rbTerse.gridy = 8;
		panelLeft.add(rbTerse, gbc_rbTerse);

		GridBagConstraints gbc_btnLoadLastFile = new GridBagConstraints();
		gbc_btnLoadLastFile.anchor = GridBagConstraints.NORTH;
		gbc_btnLoadLastFile.insets = new Insets(0, 0, 0, 5);
		gbc_btnLoadLastFile.gridx = 0;
		gbc_btnLoadLastFile.gridy = 10;
		panelLeft.add(btnLoadLastFile, gbc_btnLoadLastFile);

		splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setDividerSize(8);
		splitPane.setOneTouchExpandable(true);
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.fill = GridBagConstraints.BOTH;
		gbc_splitPane.gridx = 1;
		gbc_splitPane.gridy = 0;
		panelMain.add(splitPane, gbc_splitPane);

		spSource = new JScrollPane();
		splitPane.setLeftComponent(spSource);

		lblSourceFileName = new JLabel(NO_FILE);
		lblSourceFileName.setForeground(Color.BLUE);
		lblSourceFileName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSourceFileName.setHorizontalAlignment(SwingConstants.CENTER);
		spSource.setColumnHeaderView(lblSourceFileName);

		tpSource = new JTextPane();
		tpSource.setFont(new Font("Courier New", Font.PLAIN, 14));
		spSource.setViewportView(tpSource);

		spListing = new JScrollPane();
		splitPane.setRightComponent(spListing);

		lblListingFileName = new JLabel(NO_FILE);
		lblListingFileName.setHorizontalAlignment(SwingConstants.CENTER);
		lblListingFileName.setForeground(Color.BLUE);
		lblListingFileName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spListing.setColumnHeaderView(lblListingFileName);

		tpListing = new JTextPane();
		tpListing.setFont(new Font("Courier New", Font.PLAIN, 14));
		spListing.setViewportView(tpListing);

		panelStatus = new JPanel();
		panelStatus.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_panelStatus = new GridBagConstraints();
		gbc_panelStatus.fill = GridBagConstraints.BOTH;
		gbc_panelStatus.gridx = 0;
		gbc_panelStatus.gridy = 1;
		frmAsmAssembler.getContentPane().add(panelStatus, gbc_panelStatus);
		GridBagLayout gbl_panelStatus = new GridBagLayout();
		gbl_panelStatus.columnWidths = new int[] { 0, 0 };
		gbl_panelStatus.rowHeights = new int[] { 0, 0 };
		gbl_panelStatus.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panelStatus.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panelStatus.setLayout(gbl_panelStatus);

		lblStatus = new JLabel("");
		lblStatus.setForeground(Color.RED);
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.gridx = 0;
		gbc_lblStatus.gridy = 0;
		panelStatus.add(lblStatus, gbc_lblStatus);

		menuBar = new JMenuBar();
		frmAsmAssembler.setJMenuBar(menuBar);

		JMenu mnuFile = new JMenu("File");
		menuBar.add(mnuFile);

		JMenuItem mnuFileOpen = new JMenuItem("Open File");
		mnuFileOpen.setName(MNU_FILE_OPEN);
		mnuFileOpen.addActionListener(adapterForASM);
		mnuFile.add(mnuFileOpen);

		separator = new JSeparator();
		mnuFile.add(separator);

		mnuFilePrintSource = new JMenuItem("Print Source");
		mnuFilePrintSource.setName(MNU_FILE_PRINT_SOURCE);
		mnuFilePrintSource.addActionListener(adapterForASM);
		mnuFile.add(mnuFilePrintSource);

		mnuFilePrintListing = new JMenuItem("Print Listing");
		mnuFilePrintListing.setName(MNU_FILE_PRINT_LISTING);
		mnuFilePrintListing.addActionListener(adapterForASM);
		mnuFile.add(mnuFilePrintListing);

		separator_1 = new JSeparator();
		mnuFile.add(separator_1);

		JMenuItem mnuFileExit = new JMenuItem("Exit");
		mnuFileExit.setName(MNU_FILE_EXIT);
		mnuFileExit.addActionListener(adapterForASM);
		mnuFile.add(mnuFileExit);
	}// initialize
		// ---------------------------------------------------------------------

	class AdapterForASM implements ActionListener, AdjustmentListener {

		/* ActionListener */
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			String name = ((Component) actionEvent.getSource()).getName();
			switch (name) {
			// menu
			case MNU_FILE_OPEN:
				openFile();
				break;
			case MNU_FILE_PRINT_SOURCE:
				printListing(tpSource, sourceFileBase + DOT + SUFFIX_ASSEMBLER_8080);
				break;
			case MNU_FILE_PRINT_LISTING:
				printListing(tpListing, sourceFileBase + DOT + SUFFIX_LISTING);
				break;
			case MNU_FILE_EXIT:
				appClose();
				break;

			// buttons
			case START_BUTTON:
				doStart();
				break;

			case BTN_LOAD_LAST_FILE:
				doLoadLastFile();
			default:
			}// switch

		}// actionPerformed

		/* AdjustmentListener */

		@Override
		public void adjustmentValueChanged(AdjustmentEvent adjustmentEvent) {
			if (adjustmentEvent.getSource() instanceof JScrollBar) {
				int value = ((JScrollBar) adjustmentEvent.getSource()).getValue();
				sbarSource.setValue(value);
				sbarListing.setValue(value);
			} // if scroll bar

		}// adjustmentValueChanged

	}// class AdapterForASM
		// ---------------------------------------------------------------------

	private JScrollPane spSource;
	private JScrollPane spListing;
	private JSplitPane splitPane;
	private JLabel lblSourceFileName;
	private JLabel lblListingFileName;
	private JPanel panelStatus;
	private JFrame frmAsmAssembler;
	private JRadioButton rbListing;

	private JMenuBar menuBar;
	private JSeparator separator;
	private JSeparator separator_1;

	// private static final String ASSEMBLE = "Assemble";
	// private static final String RE_ASSEMBLE = "Reassemble";

	private static final String START_BUTTON = "btnStart";
	private static final String BTN_LOAD_LAST_FILE = "btnLoadLastFile";
	private static final String NO_FILE = "<No File Selected>";
	private static final String MNU_FILE_OPEN = "mnuFileOpen";
	private static final String MNU_FILE_PRINT_SOURCE = "mnuFilePrintSource";
	private static final String MNU_FILE_PRINT_LISTING = "mnuFilePrintListing";
	private static final String MNU_FILE_EXIT = "mnuFileExit";
	private static final String SBAR_SOURCE = "sbarSource";
	private static final String SBAR_LISTING = "sbarListing";

	// private static final String LINE_SEPARATOR = System.lineSeparator();
	private static final String FILE_SEPARATOR = File.separator;
	private static final String DEFAULT_DIRECTORY = "." + FILE_SEPARATOR + "Code" + FILE_SEPARATOR + ".";

	private final static String SUFFIX_ASSEMBLER_8080 = "asm";
	private final static String SUFFIX_ASSEMBLER_Z80 = "z80";
	private final static String SUFFIX_LISTING = "list";
	// private final static String SUFFIX_RTF = "rtf";
	private final static String SUFFIX_MEM = "mem";
	private final static String SUFFIX_HEX = "hex";

	private static final String EMPTY_STRING = ""; // empty string
	// private static final String ERRORS = "There are assembly errors"; // error message
	// private static final String SPACE = " "; // Space 0X20
	private static final String COMMA = ","; // Comma ,
	// private static final String COLON = ":"; // Colon : ,
	private static final String QUOTE = "'"; // single quote '
	// private static final String PERIOD = "."; // period .
	private static final String DOT = "."; // period .

	private static final String hexValuePattern = "[0-9][0-9A-Fa-f]{0,4}H";
	private static final String octalValuePattern = "[0-7]+[O|Q]";
	private static final String binaryValuePattern = "[01]B";
	private static final String decimalValuePattern = "[0-9]{1,4}D?+";
	private static final String stringValuePattern = "\\A'.*'\\z"; // used for

	// private static final int SIXTEEN = 16; // 0X10

	private String sixteenAsterisks = "****************";

	private SimpleAttributeSet attrBlack = new SimpleAttributeSet();
	private SimpleAttributeSet attrBlue = new SimpleAttributeSet();
	private SimpleAttributeSet attrGray = new SimpleAttributeSet();
	private SimpleAttributeSet attrGreen = new SimpleAttributeSet();
	private SimpleAttributeSet attrRed = new SimpleAttributeSet();
	private SimpleAttributeSet attrSilver = new SimpleAttributeSet();
	private SimpleAttributeSet attrNavy = new SimpleAttributeSet();
	private SimpleAttributeSet attrMaroon = new SimpleAttributeSet();
	private SimpleAttributeSet attrTeal = new SimpleAttributeSet();

	private JLabel lblSourceFilePath;
	private JButton btnStart;
	private JTextPane tpSource;
	private JTextPane tpListing;
	private JRadioButton rbMemFile;
	private JRadioButton rbHexFile;
	private JMenuItem mnuFilePrintSource;
	private JMenuItem mnuFilePrintListing;
	private JLabel lblStatus;
	private JRadioButton rbTerse;

	// private static final String

}// class ASM
