package filegenerator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileGeneratorController {
	private FileGenerator fg;
	private FileGeneratorUI fgui;
	private static Logger log;

	public FileGeneratorController(FileGenerator fg, FileGeneratorUI fgui) {
		log = LogManager.getLogger(this.getClass().getName());
		log.debug("FileGenratorController object is created");
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
			log.info("User clicked 'Exit' button and application is closed");
			System.exit(0);
		}

	}

	class SelectRandom implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			log.debug("Use random file content radio button is selected");
			fgui.setRandomEnabled();
			fg.setRandom(true);

		}
	}

	class SelectSpecific implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			log.debug("Specify file content radio button is selected");
			fgui.setSpecificEnabled();
			fg.setRandom(false);

		}

	}

	class SelectDirectory implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String[] selectedFile;
			log.debug("Select directory button is clicked");
			selectedFile = fgui.showSelectDirectoryDialog();
			log.debug("User selected directory " + selectedFile[0]);
			log.debug("User entered file name " + selectedFile[1]);
			fg.setFileName(selectedFile[1]);
			fg.setFilePath(selectedFile[0]);

		}

	}

	class Generate implements ActionListener {
		String generatedString = "";
		int num = 0;
		boolean error, fileIsOK; // error is set to true if there is error in
									// number of chars value
		// fileIsOK is returned by createFile function and false if file is not
		// created

		public void actionPerformed(ActionEvent e) {
			error = false;
			fileIsOK = true;
			log.debug("Error value is " + error);
			log.debug("Generate button is clicked");
			if (fg.getFilePath().isEmpty() || fg.getFileName().isEmpty()) {
				log.debug("User did not select any directory or file");
				fgui.setFilePathLabel("Directory of file name have NOT been selected. Please try again!");

			} else {
				if (fg.getRandom()) {
					log.debug("User selected random content");
					boolean[] settings = fgui.getRandomSettings();
					num = fgui.getNumberOfCharsValue();
					if (num < 1) {
						fgui.showErrorMessage();
						error = true;
					} else {
						generatedString = fg.generateRandomString(num, settings[0], settings[1], settings[2]);
					}
				} else {
					log.debug("User specific content");
					generatedString = fg.multiplyString(fgui.taSpecificString.getText(), fgui.getSpinnerValue(),
							fgui.chkNewLine.isSelected(), fgui.getSeparatorValue());
				}
				if (!error) {
					log.debug("Creating file");
					fileIsOK = fg.createFile(fg.getFilePath(), fg.getFileName(), generatedString);
					if (!fileIsOK) {
						log.debug("Creating file fails");
						log.debug("Show error message in UI");
						fgui.showFileErrorMessage();
					}
				}
			}

		}

	}

}
