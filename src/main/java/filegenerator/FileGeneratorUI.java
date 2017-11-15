package filegenerator;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
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
	// panels for elements
	JPanel panelMain;
	Box boxFileGenerate;
	Box boxStringGenerate;
	Box boxSpinner;
	// Directory selector
	JFileChooser selectDirectory;
	// SelectDirectory button
	JButton btnSelectDirectory;
	// Label for file name
	JLabel lblFileName;
	//
	JCheckBox chkNumberofStings;
	JCheckBox chkNewLine;
	JCheckBox chkSepartor;
	JCheckBox chkIncludeNumbers;
	JCheckBox chkIncludeSpecChars;
	JCheckBox chkIncludeUpperCase;
	JTextField txtSeparator;
	JTextField txtNumberOfChars;
	JTextArea taSpecificString;
	JLabel lblNumberOfChars;
	// Button Group for string method selecton radio buttons
	ButtonGroup bgSelectStringMethod;
	// radio button to select Random string
	JRadioButton rbRandom;
	// radio button to select Specific string
	JRadioButton rbSpecific;
	// Generate button
	JButton btnGenerate;
	// Exit button
	JButton btnExit;
	// Number of strings spinner
	JSpinner spnNumberOfStrings;
	// File object
	// File file;
	String filePath = "";
	String fileName = "";

	public FileGeneratorUI() {
		fg = new FileGenerator();
		panelMain = new JPanel(new BorderLayout());
		boxFileGenerate = Box.createVerticalBox();
		SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 1000000, 1);
		JSpinner spnNumberOfStrings = new JSpinner(spinnerModel);
		chkNumberofStings = new JCheckBox("Repeat content following number of times: ");
		chkNewLine = new JCheckBox("Use new line for each repeated content");
		chkSepartor = new JCheckBox("Use following separator for each repeated content");
		boxStringGenerate = Box.createVerticalBox();
		boxSpinner = Box.createHorizontalBox();
		selectDirectory = new JFileChooser();

		selectDirectory.setFileSelectionMode(selectDirectory.DIRECTORIES_ONLY);
		btnSelectDirectory = new JButton("Select directory and enter file name");

		bgSelectStringMethod = new ButtonGroup();
		rbRandom = new JRadioButton("Use random file content");
		rbSpecific = new JRadioButton("Specify file content");

		bgSelectStringMethod.add(rbRandom);
		bgSelectStringMethod.add(rbSpecific);

		rbRandom.setSelected(true);

		boxStringGenerate.add(rbRandom);
		boxStringGenerate.add(rbSpecific);
		boxSpinner.add(chkNumberofStings);
		boxSpinner.add(spnNumberOfStrings);

		lblFileName = new JLabel("");
		btnGenerate = new JButton("Generate");
		btnExit = new JButton("Exit");
		panelMain.add(boxStringGenerate, BorderLayout.NORTH);
		panelMain.add(boxSpinner, BorderLayout.CENTER);
		boxFileGenerate.add(lblFileName);
		boxFileGenerate.add(btnSelectDirectory);
		boxFileGenerate.add(btnGenerate);
		boxFileGenerate.add(btnExit);
		panelMain.add(boxFileGenerate, BorderLayout.SOUTH);
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
