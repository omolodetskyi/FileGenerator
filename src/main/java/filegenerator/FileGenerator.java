package filegenerator;

import java.io.File;
import java.io.IOException;

public class FileGenerator {

	StringBuffer fileString = new StringBuffer(); // file content will be stored
													// here
	// String enteredString; // string entered by user
	// String generatedString; // string generated
	// String filePath; // path to file
	// String fileName; // name of file

	public void createFile(String filePath, String fileName, String generatedString) {
		File file = new File(filePath + fileName);
		try {
			file.createNewFile();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public FileGenerator() {

	}

}
