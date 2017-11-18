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
	JCheckBox chkSeparator;
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

	public FileGeneratorUI() {

		// create main panel
		panelMain = new JPanel();
		// set GridBagLayout for main panel
		panelMain.setLayout(new GridBagLayout());

		// ******************* Use random section ********************

		rbRandom = new JRadioButton("Use random file content");
		rbRandom.setSelected(true);
		rbRandom.setActionCommand("random");
		lblNumberOfChars = new JLabel("Enter number of chars for random content: ");
		txtNumberOfChars = new JTextField(10);
		txtNumberOfChars.setText("1");
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
		rbSpecific.setActionCommand("specific");
		boxSpecifyElements.add(rbSpecific);
		taSpecificString = new JTextArea("Enter file content here", 30, 50);
		boxSpecifyElements.add(taSpecificString);
		chkNumberofStings = new JCheckBox("Repeat content following number of times:");
		SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 1000000, 1);
		spnNumberOfStrings = new JSpinner(spinnerModel);
		chkNewLine = new JCheckBox("Use new line for each repeated content");
		chkSeparator = new JCheckBox("Use this separator for each repeated content:");
		txtSeparator = new JTextField(10);
		setRandomEnabled();

		// ***************** Buttons section ***********************

		selectDirectory = new JFileChooser();
		selectDirectory.setFileSelectionMode(selectDirectory.DIRECTORIES_ONLY);
		lblFileName = new JLabel("");
		boxButtons = Box.createHorizontalBox();
		btnSelectDirectory = new JButton("Select directory and enter file name");
		boxButtons.add(btnSelectDirectory);
		btnGenerate = new JButton("Generate");
		boxButtons.add(btnGenerate);
		btnExit = new JButton("Exit");
		boxButtons.add(btnExit);

		// ***********************************************************

		// adding radiobutton to the group

		bgSelectStringMethod = new ButtonGroup();
		bgSelectStringMethod.add(rbRandom);
		bgSelectStringMethod.add(rbSpecific);
		// by default Random is selected

		// ********* forming the gridBagLayout ********
		componentAdd(panelMain, rbRandom, 0, 0, 2, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH);
		componentAdd(panelMain, lblNumberOfChars, 0, 1, 1, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH);
		componentAdd(panelMain, txtNumberOfChars, 1, 1, 1, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH);
		componentAdd(panelMain, chkNumberofStings, 1, 1, 1, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH);
		componentAdd(panelMain, boxIncludeCheckBoxes, 0, 3, 2, 1, GridBagConstraints.NORTHWEST,
				GridBagConstraints.BOTH);
		componentAdd(panelMain, rbSpecific, 0, 4, 2, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH);
		componentAdd(panelMain, rbSpecific, 0, 5, 2, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH);
		componentAdd(panelMain, taSpecificString, 0, 6, 2, 2, GridBagConstraints.NORTHWEST,
				GridBagConstraints.HORIZONTAL);
		componentAdd(panelMain, chkNumberofStings, 0, 8, 1, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH);
		componentAdd(panelMain, spnNumberOfStrings, 1, 8, 1, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE);
		componentAdd(panelMain, chkNewLine, 0, 9, 2, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH);
		componentAdd(panelMain, chkSeparator, 0, 10, 1, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH);
		componentAdd(panelMain, txtSeparator, 1, 10, 1, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH);
		componentAdd(panelMain, lblFileName, 0, 11, 2, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH);
		componentAdd(panelMain, boxButtons, 0, 12, 2, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH);
		this.add(panelMain);
		this.setSize(800, 500);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// this.pack();
		this.setTitle("File Generator");
		this.setVisible(true);

	}

	void setRandomEnabled() {
		txtNumberOfChars.setEnabled(true);

		chkIncludeUpperCase.setEnabled(true);

		chkIncludeNumbers.setEnabled(true);

		chkIncludeSpecChars.setEnabled(true);

		taSpecificString.setEnabled(false);

		chkNumberofStings.setEnabled(false);
		spnNumberOfStrings.setEnabled(false);
		chkNewLine.setEnabled(false);
		chkSeparator.setEnabled(false);
		txtSeparator.setEnabled(false);
	}

	void setSpecificEnabled() {
		taSpecificString.setEnabled(true);

		chkNumberofStings.setEnabled(true);
		spnNumberOfStrings.setEnabled(true);
		chkNewLine.setEnabled(true);
		chkSeparator.setEnabled(true);
		txtSeparator.setEnabled(true);
		txtNumberOfChars.setEnabled(true);

		txtNumberOfChars.setEnabled(false);
		chkIncludeUpperCase.setEnabled(false);

		chkIncludeNumbers.setEnabled(false);

		chkIncludeSpecChars.setEnabled(false);
	}

	int getSpinnerValue() {

		return (Integer) spnNumberOfStrings.getValue();

	}

	int getNumberOfCharsVaue() {
		return Integer.parseInt(txtNumberOfChars.getText());
	}

	void addExitListener(ActionListener ExitButton) {
		btnExit.addActionListener(ExitButton);
	}

	void addGenerateListener(ActionListener GenerateButton) {
		btnGenerate.addActionListener(GenerateButton);
	}

	void addRandomOptionListener(ActionListener RandomOption) {
		rbRandom.addActionListener(RandomOption);
	}

	void addSpecificOptionListener(ActionListener SpecificOption) {
		rbSpecific.addActionListener(SpecificOption);
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
