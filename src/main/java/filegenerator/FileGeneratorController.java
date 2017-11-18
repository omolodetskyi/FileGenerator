package filegenerator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileGeneratorController {
	private FileGenerator fg;
	private FileGeneratorUI fgui;

	public FileGeneratorController(FileGenerator fg, FileGeneratorUI fgui) {
		this.fg = fg;
		this.fgui = fgui;
		fgui.addRandomOptionListener(new SelectRandom());
		fgui.addSpecificOptionListener(new SelectSpecific());
		fgui.addGenerateListener(new Generate());
		fgui.addSelectDirectoryListener(new SelectDirectory());
		fgui.addExitListener(new Exit());
	}

	class Exit implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}

	}

	class SelectRandom implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			fgui.setRandomEnabled();
			fg.setRandom(true);

		}
	}

	class SelectSpecific implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			fgui.setSpecificEnabled();
			fg.setRandom(false);

		}

	}

	class SelectDirectory implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String[] selectedFile;

			selectedFile = fgui.showSelectDirectoryDialog();

			fg.setFileName(selectedFile[1]);
			fg.setFilePath(selectedFile[0]);

		}

	}

	class Generate implements ActionListener {
		String generatedString = "";

		public void actionPerformed(ActionEvent e) {
			if (fg.getFilePath().isEmpty() || fg.getFileName().isEmpty()) {
				fgui.setFilePathLabel("You have NOT selected a directory or NOT entered file name! Please try again.");

			} else {
				if (fg.getRandom()) {
					generatedString = fg.generateRandomString(fgui.getNumberOfCharsVaue(),
							fgui.chkIncludeSpecChars.isSelected(), fgui.chkIncludeNumbers.isSelected(),
							fgui.chkIncludeUpperCase.isSelected());
				} else {
					generatedString = fg.multiplyString("Some Text", 50, true, "::");
				}

				fg.createFile(fg.getFilePath(), fg.getFileName(), generatedString);
			}

		}

	}
}
