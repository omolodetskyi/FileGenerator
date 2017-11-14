package filegenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class FileGenerator {

	StringBuffer fileString = new StringBuffer(); // file content will be stored
													// here
	// String enteredString; // string entered by user
	// String generatedString; // string generated
	// String filePath; // path to file
	// String fileName; // name of file

	public void createFile(String filePath, String fileName, String generatedString) {
		File file = new File(filePath + "/" + fileName);
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
		String resultString;
		StringBuffer buffer = new StringBuffer();
		stringToMultiply = stringToMultiply + separator;
		for (int i = 0; i < numberOfStrings; i++) {
			buffer.append(stringToMultiply);
			if (newLine) {
				if (i < numberOfStrings - 1) {
					buffer.append(System.getProperty("line.separator"));
				}
			}

		}
		resultString = buffer.toString();
		return resultString;

	}

	public String generateRandomString(int charNumber, boolean includeSpecChar, boolean includeNumbers,
			boolean includeUpperCase) {
		String lettersLowCase = "abcdefghijklmnopqrstuvwxyz";
		String lettersUpperCase = lettersLowCase.toUpperCase();
		String numbers = "1234567890";
		String specialChars = "<,>.?/\\~!@#$%^&*()_-+={[}]|\'\"";
		String randomString;
		String resultString;
		Random rnd = new Random();
		StringBuffer sbRandomChars = new StringBuffer();
		randomString = lettersLowCase;
		if (includeUpperCase) {
			randomString = randomString + lettersUpperCase;
		}
		if (includeNumbers) {
			randomString = randomString + numbers;
		}
		if (includeSpecChar) {
			randomString = randomString + specialChars;
		}
		int i = 0;
		while (sbRandomChars.length() < charNumber) {
			i = (rnd.nextInt(randomString.length()));
			sbRandomChars.append(randomString.charAt(i));
		}
		resultString = sbRandomChars.toString();
		return resultString;

	}

	public FileGenerator() {

	}

}
