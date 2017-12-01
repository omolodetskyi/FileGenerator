package filegenerator;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileGeneratorUI extends JFrame {
	private static Logger log;

	FileGenerator fg;

	// Button Group for string method selecton radio buttons
	ButtonGroup bgSelectStringMethod;

	// panels for elements
	JPanel panelMain;
	JPanel fileNamePanel;
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

	// constructor of FileGeneratorUI class
	public FileGeneratorUI() {

		// create main panel
		log = LogManager.getLogger(this.getClass().getName());
		panelMain = new JPanel();
		fileNamePanel = new JPanel();
		// set GridBagLayout for main panel
		panelMain.setLayout(new GridBagLayout());

		// ******************* Use random section ********************

		rbRandom = new JRadioButton("Use random file content");
		rbRandom.setSelected(true);
		rbRandom.setActionCommand("random");
		lblNumberOfChars = new JLabel("Enter number of chars for random content: ");
		txtNumberOfChars = new JTextField(4);
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
		taSpecificString = new JTextArea("Enter file content here");
		taSpecificString.setPreferredSize(new Dimension(414, 40));
		taSpecificString.addFocusListener(new typeInTextArea());
		boxSpecifyElements.add(taSpecificString);
		chkNumberofStings = new JCheckBox("Repeat content following number of times:");
		SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 1000000, 1);
		spnNumberOfStrings = new JSpinner(spinnerModel);
		spnNumberOfStrings.setPreferredSize(new Dimension(60, 25));
		chkNewLine = new JCheckBox("Use new line for each repeated content");
		chkSeparator = new JCheckBox("Use this separator for each repeated content:");
		txtSeparator = new JTextField("", 4);
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
		Insets insets = new Insets(5, 5, 5, 5);
		componentAdd(panelMain, rbRandom, 0, 0, 2, 1, 1.0, 1.0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH,
				insets);
		componentAdd(panelMain, lblNumberOfChars, 0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.NORTHWEST,
				GridBagConstraints.BOTH, insets);
		componentAdd(panelMain, txtNumberOfChars, 1, 1, 1, 1, 0.5, 1.0, GridBagConstraints.NORTHWEST,
				GridBagConstraints.NONE, insets);
		componentAdd(panelMain, chkNumberofStings, 1, 1, 1, 1, 1.0, 1.0, GridBagConstraints.NORTHWEST,
				GridBagConstraints.BOTH, insets);
		componentAdd(panelMain, boxIncludeCheckBoxes, 0, 3, 2, 1, 1.0, 1.0, GridBagConstraints.NORTHWEST,
				GridBagConstraints.BOTH, insets);
		componentAdd(panelMain, rbSpecific, 0, 4, 2, 1, 1.0, 1.0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH,
				insets);
		componentAdd(panelMain, rbSpecific, 0, 5, 2, 1, 1.0, 1.0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH,
				insets);

		componentAdd(panelMain, taSpecificString, 0, 6, 2, 2, 1.0, 1.0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, insets);
		componentAdd(panelMain, chkNumberofStings, 0, 8, 1, 1, 1.0, 1.0, GridBagConstraints.NORTHWEST,
				GridBagConstraints.BOTH, insets);
		componentAdd(panelMain, spnNumberOfStrings, 1, 8, 1, 1, 1.0, 1.0, GridBagConstraints.NORTHWEST,
				GridBagConstraints.VERTICAL, insets);
		componentAdd(panelMain, chkNewLine, 0, 9, 2, 1, 1.0, 1.0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH,
				insets);
		componentAdd(panelMain, chkSeparator, 0, 10, 1, 1, 1.0, 1.0, GridBagConstraints.NORTHWEST,
				GridBagConstraints.BOTH, insets);

		componentAdd(panelMain, txtSeparator, 1, 10, 1, 1, 1.0, 1.0, GridBagConstraints.NORTHWEST,
				GridBagConstraints.NONE, insets);
		componentAdd(panelMain, lblFileName, 0, 11, 2, 1, 1.0, 1.0, GridBagConstraints.NORTHWEST,
				GridBagConstraints.BOTH, insets);
		componentAdd(panelMain, boxButtons, 0, 12, 2, 1, 1.0, 1.0, GridBagConstraints.NORTHWEST,
				GridBagConstraints.BOTH, insets);
		this.add(panelMain);
		this.setSize(500, 500);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("File Generator");
		this.setVisible(true);

	}

	void setRandomEnabled() {
		log.debug("Set enabled Random content section");
		txtNumberOfChars.setEnabled(true);

		chkIncludeUpperCase.setEnabled(true);

		chkIncludeNumbers.setEnabled(true);

		chkIncludeSpecChars.setEnabled(true);
		log.debug("Set disabled specific content section");

		taSpecificString.setEnabled(false);

		chkNumberofStings.setEnabled(false);
		spnNumberOfStrings.setEnabled(false);
		chkNewLine.setEnabled(false);
		chkSeparator.setEnabled(false);
		txtSeparator.setEnabled(false);
	}

	void setSpecificEnabled() {
		log.debug("Set enabled specific content section");
		taSpecificString.setEnabled(true);

		chkNumberofStings.setEnabled(true);
		spnNumberOfStrings.setEnabled(true);
		chkNewLine.setEnabled(true);
		chkSeparator.setEnabled(true);
		txtSeparator.setEnabled(true);
		txtNumberOfChars.setEnabled(true);
		log.debug("Set disabled Random content section");
		txtNumberOfChars.setEnabled(false);
		chkIncludeUpperCase.setEnabled(false);

		chkIncludeNumbers.setEnabled(false);

		chkIncludeSpecChars.setEnabled(false);
	}

	int getSpinnerValue() {
		log.debug("Spiner value is " + (Integer) spnNumberOfStrings.getValue());

		return (Integer) spnNumberOfStrings.getValue();

	}

	boolean[] getRandomSettings() {
		boolean[] settings = new boolean[3];
		settings[0] = chkIncludeSpecChars.isSelected();
		settings[1] = chkIncludeNumbers.isSelected();
		settings[2] = chkIncludeUpperCase.isSelected();
		log.debug("Settings in UI for random content:");
		log.debug("* should include spec chars " + settings[0]);
		log.debug("* should include numbers " + settings[1]);
		log.debug("* should Upper case " + settings[2]);
		return settings;
	}

	String getSeparatorValue() {
		String separatorValue = "";
		if (chkSeparator.isSelected())
			separatorValue = txtSeparator.getText();
		else {
			separatorValue = "";
		}
		log.debug("Separator value is " + separatorValue);
		return separatorValue;
	}

	int getNumberOfCharsVaue() {
		log.debug("Number of chars value " + txtNumberOfChars.getText());
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
		log.debug("Select Directory dialog is opened");
		String selectedfilePath;
		String selectedfileName;
		String selPathShow, selFileNameShow;
		if (select == selectDirectory.APPROVE_OPTION) {
			selectedfilePath = (selectDirectory.getSelectedFile()).getParentFile().getPath();
			log.debug("Directory is selected " + selectedfilePath);
			selectedfileName = (selectDirectory.getSelectedFile()).getName();
			log.debug("Filename is entered " + selectedfileName);
			selPathShow = selectedfilePath;
			selFileNameShow = selectedfileName;

			if (selectedfilePath.length() > 50) {
				log.debug("Filepath is longer than 50 chars");

				selPathShow = selectedfilePath.substring(0, 20) + "...";
				log.debug("Filepath is cut to 20 chars " + selPathShow);
			}
			if (selectedfileName.length() > 50) {
				log.debug("Filename is longer than 50 chars");
				selFileNameShow = selectedfileName.substring(0, 20) + "...";
				log.debug("Filename is cut to 20 chars " + selPathShow);

			}
			String msg = "File " + selPathShow + "/" + selFileNameShow + " will be generated!";
			setFilePathLabel(msg);
		} else {
			selectedfilePath = "";
			selectedfileName = "";
		}
		String[] result = { selectedfilePath, selectedfileName };
		return result;
	}

	void setFilePathLabel(String filepath) {
		log.debug("Show filepath in UI: " + filepath);
		lblFileName.setText(filepath);
	}

	class typeInTextArea implements FocusListener {

		public void focusGained(FocusEvent e) {
			log.debug("User put focus in the text area");
			if (taSpecificString.getText().equals("Enter file content here")) {
				log.debug("There is default content in text area");
				taSpecificString.setText("");
				log.debug("removed default content of text area");
			}
		}

		public void focusLost(FocusEvent e) {
			log.debug("User put focus out of the text area");
			if (taSpecificString.getText().equals("")) {
				log.debug("There is empty content in text area");

				taSpecificString.setText("Enter file content here");
				log.debug("default content is added to the text area");
			}

		}
	}

	private void componentAdd(JPanel thePanel, JComponent comp, int xPos, int yPos, int compWidth, int compHeight,
			double compWeightx, double comWeighty, int place, int stretch, Insets insets) {
		GridBagConstraints gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = xPos;

		gridConstraints.gridy = yPos;

		gridConstraints.ipadx = 5;
		gridConstraints.ipady = 5;

		gridConstraints.gridwidth = compWidth;

		gridConstraints.gridheight = compHeight;

		gridConstraints.weightx = compWeightx;

		gridConstraints.weighty = comWeighty;

		gridConstraints.insets = insets;

		gridConstraints.anchor = place;

		gridConstraints.fill = stretch;

		thePanel.add(comp, gridConstraints);

	}
}
