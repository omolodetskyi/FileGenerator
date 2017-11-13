package filegenerator;

import java.io.File;
import java.io.FileWriter;
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
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(generatedString);
			fileWriter.close();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public String multiplyString(String stringToMultiply, int numberOfStrings, boolean newLine, String separator) {
		String resultString = "";
		StringBuffer buffer = new StringBuffer();
		stringToMultiply = stringToMultiply + separator;
		if (newLine) {
			stringToMultiply = stringToMultiply + System.getProperty("line.separator");
		}
		for (int i = 0; i < numberOfStrings; i++) {
			buffer.append(stringToMultiply);
		}
		resultString = buffer.toString();
		return resultString;

	}

	public FileGenerator() {

	}

}
