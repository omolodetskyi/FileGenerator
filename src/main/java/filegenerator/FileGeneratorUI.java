package filegenerator;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class FileGeneratorUI extends JFrame {

	FileGenerator fg;

	// Button Group for string method selecton radio buttons
	ButtonGroup bgSelectStringMethod;

	// panels for elements
	JPanel panelMain;
	Box boxButtons;
	Box boxIncludeCheckBoxes;
	Box boxSpecifyElements;
	Box boxSpinner; // ?

	// **** Use random file content part ****

	// radio button to select Random string
	JRadioButton rbRandom;
	// Label "enter number of chars in file random content"
	JLabel lblNumberOfChars;
	// Text field to enter number of chars for random content
	JTextField txtNumberOfChars;
	// checkbox "include upper case"
	JCheckBox chkIncludeUpperCase;
	// checkbox "include numbers"
	JCheckBox chkIncludeNumbers;
	// checkbox "include special characters"
	JCheckBox chkIncludeSpecChars;

	// ***** Specify file content part ****

	// radio button to select Specific string
	JRadioButton rbSpecific;
	// text area to "Enter file content here
	JTextArea taSpecificString;
	// checkbox "Repeat content following number of times:"
	JCheckBox chkNumberofStings;
	// Spinner to specify number of times
	JSpinner spnNumberOfStrings;
	// checkbox "Use new line for each repeated content"
	JCheckBox chkNewLine;
	// checkbox "Use this separator for each repeated content:"
	JCheckBox chkSepartor;
	// Text field for separator
	JTextField txtSeparator;

	// **** Buttons part ****

	// Label for file name
	JLabel lblFileName;
	// Directory selector
	JFileChooser selectDirectory;
	// SelectDirectory button
	JButton btnSelectDirectory;
	// Generate button
	JButton btnGenerate;
	// Exit button
	JButton btnExit;

	// File file path and file name;
	String filePath = "";
	String fileName = "";

	public FileGeneratorUI() {

		// TODO create new file generator warum?
		fg = new FileGenerator();

		// create main panel
		panelMain = new JPanel();
		// set GridBagLayout for main panel
		panelMain.setLayout(new GridBagLayout());

		// ******************* Use random section ********************

		rbRandom = new JRadioButton("Use random file content");
		lblNumberOfChars = new JLabel("Enter number of chars for random content: ");
		txtNumberOfChars = new JTextField(10);
		boxIncludeCheckBoxes = Box.createVerticalBox();
		chkIncludeUpperCase = new JCheckBox("Include upper case");
		boxIncludeCheckBoxes.add(chkIncludeUpperCase);
		chkIncludeNumbers = new JCheckBox("Include numbers");
		boxIncludeCheckBoxes.add(chkIncludeNumbers);
		chkIncludeSpecChars = new JCheckBox("Include special characters");
		boxIncludeCheckBoxes.add(chkIncludeSpecChars);

		// ******************* Specify content section ****************

		boxSpecifyElements = Box.createVerticalBox();
		rbSpecific = new JRadioButton("Specify file content");
		boxSpecifyElements.add(rbSpecific);
		taSpecificString = new JTextArea("Enter file content here");
		boxSpecifyElements.add(taSpecificString);
		chkNumberofStings = new JCheckBox("Repeat content following number of times:");
		SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 1000000, 1);
		JSpinner spnNumberOfStrings = new JSpinner(spinnerModel);
		chkNewLine = new JCheckBox("Use new line for each repeated content");
		chkIncludeSpecChars = new JCheckBox("Use this separator for each repeated content:");
		txtSeparator = new JTextField(10);

		// ***************** Buttons section ***********************

		selectDirectory = new JFileChooser();
		selectDirectory.setFileSelectionMode(selectDirectory.DIRECTORIES_ONLY);
		lblFileName = new JLabel("");
		boxButtons = Box.createHorizontalBox();
		btnSelectDirectory = new JButton("Select directory and enter file name");
		boxButtons.add(btnSelectDirectory);
		boxButtons.add(btnGenerate);
		boxButtons.add(btnExit);
		btnGenerate = new JButton("Generate");
		btnExit = new JButton("Exit");

		// ***********************************************************

		// adding radiobutton to the group

		bgSelectStringMethod = new ButtonGroup();
		bgSelectStringMethod.add(rbRandom);
		bgSelectStringMethod.add(rbSpecific);
		rbRandom.setSelected(true); // by default Random is selected

		this.add(panelMain);
		this.setSize(800, 300);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("File Generator");
		this.setVisible(true);

	}

	int getSpinnerValue() {

		return (Integer) spnNumberOfStrings.getValue();

	}

	void addExitListener(ActionListener ExitButton) {
		btnExit.addActionListener(ExitButton);
	}

	void addGenerateListener(ActionListener GenerateButton) {
		btnGenerate.addActionListener(GenerateButton);
	}

	void addSelectDirectoryListener(ActionListener SelectDirectoryButton) {
		btnSelectDirectory.addActionListener(SelectDirectoryButton);
	}

	String[] showSelectDirectoryDialog() {
		int select = selectDirectory.showDialog(null, "Select Directory");
		String selectedfilePath;
		String selectedfileName;
		if (select == selectDirectory.APPROVE_OPTION) {
			selectedfilePath = (selectDirectory.getSelectedFile()).getParentFile().getPath();
			selectedfileName = (selectDirectory.getSelectedFile()).getName();
			setFilePathLabel("File " + selectedfilePath + "/" + selectedfileName
					+ " will be generated! Click \"Generate\" button.");
		} else {
			selectedfilePath = "";
			selectedfileName = "";
		}
		String[] result = { selectedfilePath, selectedfileName };
		return result;
	}

	void setFilePathLabel(String filepath) {
		lblFileName.setText(filepath);
	}

	private void componentAdd(JPanel thePanel, JComponent comp, int xPos, int yPos, int compWidth, int compHeight,
			int place, int stretch) {
		GridBagConstraints gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = xPos;

		gridConstraints.gridy = yPos;

		gridConstraints.ipadx = 5;
		gridConstraints.ipady = 5;

		gridConstraints.gridwidth = compWidth;

		gridConstraints.gridheight = compHeight;

		gridConstraints.weightx = 100;

		gridConstraints.weighty = 100;

		gridConstraints.insets = new Insets(5, 5, 5, 5);

		gridConstraints.anchor = place;

		gridConstraints.fill = stretch;

		thePanel.add(comp, gridConstraints);

	}

}
