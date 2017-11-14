package filegenerator;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

public class FileGeneratorUI extends JFrame {
	FileGenerator fg;
	// panel for elements
	JPanel panelMain;
	JPanel panelButton;
	// Directory selector
	JFileChooser selectDirectory;
	// SelectDirectory button
	JButton btnSelectDirectory;
	// Label for file name
	JLabel lblFileName;

	// JTextField txtFileName; removed
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
		panelButton = new JPanel();
		selectDirectory = new JFileChooser();

		selectDirectory.setFileSelectionMode(selectDirectory.DIRECTORIES_ONLY);
		btnSelectDirectory = new JButton("Select directory and enter file name");

		lblFileName = new JLabel("");
		btnGenerate = new JButton("Generate");
		btnExit = new JButton("Exit");

		panelMain.add(lblFileName, BorderLayout.NORTH);
		panelButton.add(btnSelectDirectory);
		panelButton.add(btnGenerate);
		panelButton.add(btnExit);
		panelMain.add(panelButton, BorderLayout.SOUTH);
		this.add(panelMain);
		this.setSize(800, 300);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("File Generator");
		this.setVisible(true);

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

}
