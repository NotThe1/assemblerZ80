package intelToZilog;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
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
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import assembler.AppLogger;

public class MakeZ80Source {

	private AppLogger log = AppLogger.getInstance();
	private AdapterLog logAdaper = new AdapterLog();
	private StyledDocument docIntel;
	private StyledDocument docZilog;
	private Set<String> symbols = new HashSet<String>();

	private File tempFile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MakeZ80Source window = new MakeZ80Source();
					window.frmTemplate.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				} // try
			}// run
		});
	}// main

	/* Standard Stuff */

	////////////////////////////////////////////////////////////////
	private void loadIntelFile(File intelFile) {

		String intelFileName = intelFile.getName();
		// // String asmSourceDirectory = intelFile.getPath();
		// String[] nameParts = (intelFileName.split("\\."));
		// sourceFileBase = nameParts[0];

		lblIntelFile.setToolTipText(intelFile.getAbsolutePath());
		lblIntelFile.setText(intelFile.getName());
		// sourceFileFullName = intelFile.getAbsolutePath();
		// lblSourceFileName.setText(intelFile.getName());
		// lblListingFileName.setText(sourceFileBase + "." + SUFFIX_LISTING);
		// defaultDirectory = intelFile.getParent();
		// outputPathAndBase = defaultDirectory + FILE_SEPARATOR + sourceFileBase;
		//
		clearDoc(docIntel);
		clearDoc(docZilog);
		try {
			tempFile = File.createTempFile(Long.toString(System.currentTimeMillis()), null);
			tempFile.deleteOnExit();
			System.out.println(tempFile.getAbsolutePath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loadSourceFile(intelFile, 1, null);
		// tpSource.setCaretPosition(0);
		// btnStart.setEnabled(true);
		//

	}// loadIntelFile

	private int loadSourceFile(File sourceFile, int lineNumber, SimpleAttributeSet attr) {
		try {
			FileReader source = new FileReader(sourceFile);
			BufferedReader reader = new BufferedReader(source);
			String lineIntel = null;
			String rawLine = null;
			Matcher matcherList;
			Pattern patternForList = Pattern.compile("^[0-9]{4}: [0-9A-Fa-f]{4}", Pattern.CASE_INSENSITIVE);
			Matcher matcherInclude;
			Pattern patternForInclude = Pattern.compile("< Include >", Pattern.CASE_INSENSITIVE);
			Matcher matcherTarget;
			Pattern patternForTarget = Pattern.compile("^[0-9]{4}: [0-9A-Fa-f]{4} [0-9A-Fa-f]{2}",
					Pattern.CASE_INSENSITIVE);
			int inIncludeCount = 0;
			while ((rawLine = reader.readLine()) != null) {
				 lineIntel = rawLine;

				insertIntel(rawLine + System.lineSeparator(), attr);
				insertZilog(lineIntel + System.lineSeparator(), attr);

			} // while
			textIntel.setCaretPosition(0);
			textZilog.setCaretPosition(0);
			reader.close();
			// mnuFilePrintSource.setEnabled(true);
			// mnuFilePrintListing.setEnabled(false);
		} catch (IOException e) {
			String error = String.format("File Not Found!! - %s", sourceFile.getAbsolutePath());
			log.addError(error);
		} // TRY

		return lineNumber;
	}// loadSourceFile

	public int doInclude(String fileReference, String parentDirectory, int lineNumber) {

		if (!fileReference.contains("\\")) {
			fileReference = parentDirectory + System.getProperty("file.separator") + fileReference;
		} //

		// if (!(fileReference.toUpperCase().endsWith(SUFFIX_ASSEMBLER.toUpperCase()))) {
		// fileReference += "." + SUFFIX_ASSEMBLER;
		// } //

		// * String includeMarker = ";<<<<<<<<<<<<<<<<<<<<<<< Include >>>>>>>>>>>>>>>>";
		// insertSource(String.format("%04d %s%n", lineNumber++, includeMarker), attrBlue);
		//
		// File includedFile = new File(fileReference);
		// lineNumber = loadSourceFile(includedFile, lineNumber, attrBlue);
		//
		// * insertSource(String.format("%04d %s%n", lineNumber++, includeMarker), attrBlue);
		//
		return lineNumber;
	}// doInclude

	private void insertZilog(String str, SimpleAttributeSet attr) {
		try {
			docZilog.insertString(docZilog.getLength(), str, attr);
		} catch (BadLocationException e) {
			e.printStackTrace();
		} // try
	}// insertSource

	private void insertIntel(String str, SimpleAttributeSet attr) {
		try {
			docIntel.insertString(docIntel.getLength(), str, attr);
		} catch (BadLocationException e) {
			e.printStackTrace();
		} // try
	}// insertSource

	private void clearDoc(StyledDocument doc) {
		try {
			doc.remove(0, doc.getLength());
		} catch (BadLocationException e) {
			// Auto-generated catch block
			e.printStackTrace();
		} // try
	}// clearDoc

	////////////////////////////////////////////////////////////////

	private void doBtnOne() {

	}// doBtnOne

	private void doBtnTwo() {

	}// doBtnTwo

	private void doBtnThree() {

	}// doBtnThree

	private void doBtnFour() {

	}// doBtnFour

	// ---------------------------------------------------------

	private void doFileNew() {

	}// doFileNew

	private void doFileOpen() {
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Source Files", "asm", "z80");
		JFileChooser fileChooser = new JFileChooser(SOURCE_8080);
		fileChooser.setMultiSelectionEnabled(false);
		fileChooser.addChoosableFileFilter(filter);
		fileChooser.setAcceptAllFileFilterUsed(false);

		if (fileChooser.showOpenDialog(frmTemplate) == JFileChooser.CANCEL_OPTION) {
			System.out.println("Bailed out of the open");
			return;
		} // if - open
		loadIntelFile(fileChooser.getSelectedFile());
	}// doFileOpen

	private void doFileSave() {

	}// doFileSave

	private void doFileSaveAs() {

	}// doFileSaveAs

	private void doFilePrint() {

	}// doFilePrint

	private void doFileExit() {
		appClose();
		System.exit(0);
	}// doFileExit

	//////////////////////////////////////////////////

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

	/////////////////////////////////////
	private void appClose() {
		Preferences myPrefs = Preferences.userNodeForPackage(MakeZ80Source.class).node(this.getClass().getSimpleName());
		Dimension dim = frmTemplate.getSize();
		myPrefs.putInt("Height", dim.height);
		myPrefs.putInt("Width", dim.width);
		Point point = frmTemplate.getLocation();
		myPrefs.putInt("LocX", point.x);
		myPrefs.putInt("LocY", point.y);
		myPrefs.putInt("DividerMain", splitPane1Main.getDividerLocation());
		myPrefs.putInt("DividerCode", splitPaneCode.getDividerLocation());
		myPrefs = null;

	}// appClose

	private void appInit() {
		Preferences myPrefs = Preferences.userNodeForPackage(MakeZ80Source.class).node(this.getClass().getSimpleName());
		frmTemplate.setSize(myPrefs.getInt("Width", 761), myPrefs.getInt("Height", 693));
		frmTemplate.setLocation(myPrefs.getInt("LocX", 100), myPrefs.getInt("LocY", 100));
		splitPane1Main.setDividerLocation(myPrefs.getInt("DividerMain", 250));
		splitPaneCode.setDividerLocation(myPrefs.getInt("DividerCode", 250));
		myPrefs = null;
		setAttributes();
		symbols.add("$");

		// txtLog.setText(EMPTY_STRING);
		docIntel = textIntel.getStyledDocument();
		clearDoc(docIntel);
		docZilog = textZilog.getStyledDocument();
		clearDoc(docZilog);

		log.setDoc(txtLog.getStyledDocument());
		log.addInfo("Starting...");

	}// appInit

	public MakeZ80Source() {
		initialize();
		appInit();
	}// Constructor

	private void doLogClear() {
		log.clear();
	}// doLogClear

	private void doLogPrint() {

		Font originalFont = txtLog.getFont();
		try {
			// textPane.setFont(new Font("Courier New", Font.PLAIN, 8));
			txtLog.setFont(originalFont.deriveFont(8.0f));
			MessageFormat header = new MessageFormat("Identic Log");
			MessageFormat footer = new MessageFormat(new Date().toString() + "           Page - {0}");
			txtLog.print(header, footer);
			// textPane.setFont(new Font("Courier New", Font.PLAIN, 14));
			txtLog.setFont(originalFont);
		} catch (PrinterException e) {
			e.printStackTrace();
		} // try

	}// doLogPrint

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTemplate = new JFrame();
		frmTemplate.setTitle("Make Z80 Source from 8080 Source    0.0");
		frmTemplate.setBounds(100, 100, 721, 582);
		frmTemplate.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTemplate.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				appClose();
			}
		});
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 25, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		frmTemplate.getContentPane().setLayout(gridBagLayout);

		JPanel panelForButtons = new JPanel();
		GridBagConstraints gbc_panelForButtons = new GridBagConstraints();
		gbc_panelForButtons.anchor = GridBagConstraints.NORTH;
		gbc_panelForButtons.insets = new Insets(0, 0, 5, 0);
		gbc_panelForButtons.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelForButtons.gridx = 0;
		gbc_panelForButtons.gridy = 0;
		frmTemplate.getContentPane().add(panelForButtons, gbc_panelForButtons);
		GridBagLayout gbl_panelForButtons = new GridBagLayout();
		gbl_panelForButtons.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_panelForButtons.rowHeights = new int[] { 0, 0 };
		gbl_panelForButtons.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panelForButtons.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panelForButtons.setLayout(gbl_panelForButtons);

		btnOne = new JButton("Button 1");
		btnOne.setMinimumSize(new Dimension(100, 20));
		GridBagConstraints gbc_btnOne = new GridBagConstraints();
		gbc_btnOne.insets = new Insets(0, 0, 0, 5);
		gbc_btnOne.gridx = 0;
		gbc_btnOne.gridy = 0;
		panelForButtons.add(btnOne, gbc_btnOne);
		btnOne.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnOne.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doBtnOne();
			}
		});
		btnOne.setMaximumSize(new Dimension(0, 0));
		btnOne.setPreferredSize(new Dimension(100, 20));

		btnTwo = new JButton("Button 2");
		btnTwo.setMinimumSize(new Dimension(100, 20));
		GridBagConstraints gbc_btnTwo = new GridBagConstraints();
		gbc_btnTwo.insets = new Insets(0, 0, 0, 5);
		gbc_btnTwo.gridx = 1;
		gbc_btnTwo.gridy = 0;
		panelForButtons.add(btnTwo, gbc_btnTwo);
		btnTwo.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnTwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doBtnTwo();
			}
		});
		btnTwo.setPreferredSize(new Dimension(100, 20));
		btnTwo.setMaximumSize(new Dimension(0, 0));

		btnThree = new JButton("Button 3");
		btnThree.setMinimumSize(new Dimension(100, 20));
		GridBagConstraints gbc_btnThree = new GridBagConstraints();
		gbc_btnThree.insets = new Insets(0, 0, 0, 5);
		gbc_btnThree.gridx = 3;
		gbc_btnThree.gridy = 0;
		panelForButtons.add(btnThree, gbc_btnThree);
		btnThree.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnThree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doBtnThree();
			}
		});
		btnThree.setPreferredSize(new Dimension(100, 20));
		btnThree.setMaximumSize(new Dimension(0, 0));

		btnFour = new JButton("Button 4");
		btnFour.setMinimumSize(new Dimension(100, 20));
		GridBagConstraints gbc_btnFour = new GridBagConstraints();
		gbc_btnFour.gridx = 4;
		gbc_btnFour.gridy = 0;
		panelForButtons.add(btnFour, gbc_btnFour);
		btnFour.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnFour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doBtnFour();
			}
		});
		btnFour.setPreferredSize(new Dimension(100, 20));
		btnFour.setMaximumSize(new Dimension(0, 0));

		splitPane1Main = new JSplitPane();
		splitPane1Main.setOneTouchExpandable(true);
		GridBagConstraints gbc_splitPane1Main = new GridBagConstraints();
		gbc_splitPane1Main.insets = new Insets(0, 0, 5, 0);
		gbc_splitPane1Main.fill = GridBagConstraints.BOTH;
		gbc_splitPane1Main.gridx = 0;
		gbc_splitPane1Main.gridy = 1;
		frmTemplate.getContentPane().add(splitPane1Main, gbc_splitPane1Main);

		JPanel panelLeft = new JPanel();
		panelLeft.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Application Log",
				TitledBorder.CENTER, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		splitPane1Main.setLeftComponent(panelLeft);
		GridBagLayout gbl_panelLeft = new GridBagLayout();
		gbl_panelLeft.columnWidths = new int[] { 0, 0 };
		gbl_panelLeft.rowHeights = new int[] { 0, 0 };
		gbl_panelLeft.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panelLeft.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panelLeft.setLayout(gbl_panelLeft);

		JScrollPane scrollPaneForLog = new JScrollPane();
		GridBagConstraints gbc_scrollPaneForLog = new GridBagConstraints();
		gbc_scrollPaneForLog.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneForLog.gridx = 0;
		gbc_scrollPaneForLog.gridy = 0;
		panelLeft.add(scrollPaneForLog, gbc_scrollPaneForLog);

		JPanel panelMain = new JPanel();
		splitPane1Main.setRightComponent(panelMain);
		GridBagLayout gbl_panelMain = new GridBagLayout();
		gbl_panelMain.columnWidths = new int[] { 0, 0 };
		gbl_panelMain.rowHeights = new int[] { 0, 0 };
		gbl_panelMain.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panelMain.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panelMain.setLayout(gbl_panelMain);

		splitPaneCode = new JSplitPane();
		splitPaneCode.setOneTouchExpandable(true);
		GridBagConstraints gbc_splitPaneCode = new GridBagConstraints();
		gbc_splitPaneCode.fill = GridBagConstraints.BOTH;
		gbc_splitPaneCode.gridx = 0;
		gbc_splitPaneCode.gridy = 0;
		panelMain.add(splitPaneCode, gbc_splitPaneCode);

		JScrollPane scrollPane = new JScrollPane();
		splitPaneCode.setLeftComponent(scrollPane);

		textIntel = new JTextPane();
		textIntel.setEditable(false);
		textIntel.setFont(new Font("Courier New", Font.PLAIN, 11));
		scrollPane.setViewportView(textIntel);

		lblIntelFile = new JLabel(NO_FILE);
		lblIntelFile.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lblIntelFile.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(lblIntelFile);

		JScrollPane scrollPane_1 = new JScrollPane();
		splitPaneCode.setRightComponent(scrollPane_1);

		textZilog = new JTextPane();
		textZilog.setFont(new Font("Courier New", Font.PLAIN, 11));
		scrollPane_1.setViewportView(textZilog);

		lblZilogFile = new JLabel(NO_FILE);
		lblZilogFile.setHorizontalAlignment(SwingConstants.CENTER);
		lblZilogFile.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		scrollPane_1.setColumnHeaderView(lblZilogFile);

		txtLog = new JTextPane();
		scrollPaneForLog.setViewportView(txtLog);

		popupLog = new JPopupMenu();
		addPopup(txtLog, popupLog);

		JMenuItem popupLogClear = new JMenuItem("Clear Log");
		popupLogClear.setName(PUM_LOG_CLEAR);
		popupLogClear.addActionListener(logAdaper);
		popupLog.add(popupLogClear);

		JSeparator separator = new JSeparator();
		popupLog.add(separator);

		JMenuItem popupLogPrint = new JMenuItem("Print Log");
		popupLogPrint.setName(PUM_LOG_PRINT);
		popupLogPrint.addActionListener(logAdaper);
		popupLog.add(popupLogPrint);

		JLabel lblNewLabel = new JLabel("-");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(0, 128, 0));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		scrollPaneForLog.setColumnHeaderView(lblNewLabel);
		splitPane1Main.setDividerLocation(250);

		JPanel panelStatus = new JPanel();
		panelStatus.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_panelStatus = new GridBagConstraints();
		gbc_panelStatus.fill = GridBagConstraints.BOTH;
		gbc_panelStatus.gridx = 0;
		gbc_panelStatus.gridy = 2;
		frmTemplate.getContentPane().add(panelStatus, gbc_panelStatus);

		JMenuBar menuBar = new JMenuBar();
		frmTemplate.setJMenuBar(menuBar);

		JMenu mnuFile = new JMenu("File");
		menuBar.add(mnuFile);

		JMenuItem mnuFileNew = new JMenuItem("New");
		mnuFileNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doFileNew();
			}
		});
		mnuFile.add(mnuFileNew);

		JMenuItem mnuFileOpen = new JMenuItem("Open...");
		mnuFileOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doFileOpen();
			}
		});
		mnuFile.add(mnuFileOpen);

		JSeparator separator99 = new JSeparator();
		mnuFile.add(separator99);

		JMenuItem mnuFileSave = new JMenuItem("Save...");
		mnuFileSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doFileSave();
			}
		});
		mnuFile.add(mnuFileSave);

		JMenuItem mnuFileSaveAs = new JMenuItem("Save As...");
		mnuFileSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doFileSaveAs();
			}
		});
		mnuFile.add(mnuFileSaveAs);

		JSeparator separator_2 = new JSeparator();
		mnuFile.add(separator_2);

		JMenuItem mnuFilePrint = new JMenuItem("Print...");
		mnuFilePrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doFilePrint();
			}
		});
		mnuFile.add(mnuFilePrint);

		JSeparator separator_1 = new JSeparator();
		mnuFile.add(separator_1);

		JMenuItem mnuFileExit = new JMenuItem("Exit");
		mnuFileExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doFileExit();
			}
		});
		mnuFile.add(mnuFileExit);

	}// initialize

	private static final String PUM_LOG_PRINT = "popupLogPrint";
	private static final String PUM_LOG_CLEAR = "popupLogClear";

	private static final String SOURCE_BASE = "C:\\Users\\admin\\Dropbox\\Resources\\CPM\\CurrentOS\\";
	private static final String SOURCE_8080 = SOURCE_BASE + "8080";
	private static final String SOURCE_Z80 = SOURCE_BASE + "Z80";

	static final String EMPTY_STRING = "";
	static final String NO_FILE = "<<No File>>";

	//////////////////////////////////////////////////////////////////////////

	class AdapterLog implements ActionListener {// , ListSelectionListener
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			String name = ((Component) actionEvent.getSource()).getName();
			switch (name) {
			case PUM_LOG_PRINT:
				doLogPrint();
				break;
			case PUM_LOG_CLEAR:
				doLogClear();
				break;
			}// switch
		}// actionPerformed
	}// class AdapterAction

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				} // if popup Trigger
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}// addPopup

	private JFrame frmTemplate;
	private JButton btnOne;
	private JButton btnTwo;
	private JButton btnThree;
	private JButton btnFour;
	private JSplitPane splitPane1Main;
	private JSplitPane splitPaneCode;

	private JTextPane txtLog;
	private JPopupMenu popupLog;
	private JTextPane textIntel;
	private JTextPane textZilog;
	private JLabel lblIntelFile;
	private JLabel lblZilogFile;

	private SimpleAttributeSet attrBlack = new SimpleAttributeSet();
	private SimpleAttributeSet attrBlue = new SimpleAttributeSet();
	private SimpleAttributeSet attrGray = new SimpleAttributeSet();
	private SimpleAttributeSet attrGreen = new SimpleAttributeSet();
	private SimpleAttributeSet attrRed = new SimpleAttributeSet();
	private SimpleAttributeSet attrSilver = new SimpleAttributeSet();
	private SimpleAttributeSet attrNavy = new SimpleAttributeSet();
	private SimpleAttributeSet attrMaroon = new SimpleAttributeSet();
	private SimpleAttributeSet attrTeal = new SimpleAttributeSet();

}// class GUItemplate