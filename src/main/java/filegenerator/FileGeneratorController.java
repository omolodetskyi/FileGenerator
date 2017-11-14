package filegenerator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileGeneratorController {
	private FileGenerator fg;
	private FileGeneratorUI fgui;
	public String filePath, fileName;

	public FileGeneratorController(FileGenerator fg, FileGeneratorUI fgui) {
		this.fg = fg;
		this.fgui = fgui;
		fgui.addGenerateListener(new Generate());
		fgui.addSelectDirectoryListener(new SelectDirectory());
		fgui.addExitListener(new Exit());
	}

	class Exit implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}

	}

	class SelectDirectory implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String[] selectedFile;

			selectedFile = fgui.showSelectDirectoryDialog();
			System.out.println(selectedFile[0] + " and " + selectedFile[1]);
			filePath = selectedFile[0];
			fileName = selectedFile[1];

		}

	}

	class Generate implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (filePath.isEmpty() || fileName.isEmpty()) {
				fgui.setFilePathLabel("You have NOT selected a directory or NOT entered file name! Please try again.");

			} else {

				String generatedString = fg.generateRandomString(50, true, true, true);
				fg.createFile(filePath, fileName, generatedString);
			}

		}

	}
}
