package filegenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileGenerator {
	private static Logger log;
	private String fileName;
	private String filePath;
	private boolean isRandom;

	public boolean getRandom() {
		return isRandom;
	}

	public void setRandom(boolean isRandom) {
		log.debug("Use random file content is set to " + isRandom);
		this.isRandom = isRandom;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		log.debug("File name is set to " + fileName);
		this.fileName = fileName;
	}

	public String getFilePath() {

		return filePath;
	}

	public void setFilePath(String filePath) {
		log.debug("File path is set to " + filePath);
		this.filePath = filePath;
	}

	public StringBuffer getFileString() {
		return fileString;
	}

	public void setFileString(StringBuffer fileString) {
		if (fileString == null) {
			log.debug("File content is set to null");
		} else {
			log.debug("File content is set to " + fileString.toString());
		}
		this.fileString = fileString;
	}

	StringBuffer fileString = new StringBuffer(); // file content will be stored
													// here

	public void createFile(String filePath, String fileName, String generatedString) {
		// this method create file based on filePath, fileName and content with
		// generatedString
		File file = new File(filePath + "/" + fileName); // create object file
		try {// catch exceptions
			file.createNewFile(); // create file
			log.info("File " + filePath + "/" + fileName + " is created");
			FileWriter fileWriter = new FileWriter(file); // create FileWriter
															// to write file
															// content
			fileWriter.write(generatedString); // write file content
			log.debug("Following file content is written " + generatedString);
			fileWriter.close(); // close FileWriter
		} catch (IOException e) {
			// TODO think what to do in this case, probably return some value in
			// this method 1 if file is created, 0 if file is not created and
			// show error in UI based on this
			log.error("File can not be created or content can not be written");
			log.error(e.getStackTrace().toString());
		}
	}

	public String multiplyString(String stringToMultiply, int numberOfStrings, boolean newLine, String separator) {
		// method return string which is repeated numberOfStrings times, can be
		// formatted as each string on new line (newLine=true) and each string
		// separated by separator

		String resultString; // result will be returned
		StringBuffer buffer = new StringBuffer(); // buffer to create string
													// repeated multiple times
		log.debug("Starting generating file content as multiple user defined strings");
		log.debug("Settings are:");
		log.debug("* string to multiply: " + stringToMultiply);
		log.debug("* number of strings: " + numberOfStrings);
		log.debug("* should be each string on new line: " + newLine);
		log.debug("* string separator is " + separator);
		stringToMultiply = stringToMultiply + separator; // first string with
															// separtor (if
															// separator in not
															// defined by user
															// it's empty
															// string=""
		for (int i = 0; i < numberOfStrings; i++) { // loop to make string
													// numberOfStrings times
			buffer.append(stringToMultiply); // add string to buffer
			if (newLine) { // if newLine=true
				if (i < numberOfStrings - 1) { // and line is not the last
					buffer.append(System.getProperty("line.separator")); // add
																			// line.separator
																			// -
																			// could
																			// be
																			// different
																			// on
																			// different
																			// OS,
																			// makes
																			// new
																			// line
				}
			}

		}
		resultString = buffer.toString(); // make string from stringbuffer
		log.debug("File content is generated as " + resultString);
		return resultString; // return string

	}

	public String generateRandomString(int charNumber, boolean includeSpecChar, boolean includeNumbers,
			boolean includeUpperCase) {
		log.debug("Starting generating file content as random stringв");
		log.debug("Settings are:");
		log.debug("* number of charachters :" + charNumber);
		log.debug("* should include sepcial chars :" + includeSpecChar);
		log.debug("* should include numbers :" + includeNumbers);
		log.debug("* should include Upper Case :" + includeUpperCase);
		// method generates random string which contain charNumber chars, could
		// contain special chars (includeSpecChar=true), could contain
		// numbers(includerNumbers=true), could contain upper case
		// (includeUpperCase=true)

		String lettersLowCase = "abcdefghijklmnopqrstuvwxyz"; // all available
																// lower case
																// chars
		String lettersUpperCase = lettersLowCase.toUpperCase(); // all available
																// upper case
																// chars
		String numbers = "1234567890"; // all numbers
		String specialChars = "<,>.?/\\~!@#$%^&*()_-+={[}]|\'\""; // all special
																	// chars
		String randomString; // here we store all available chars for random
								// string, depends on selected chars
		String resultString; // result random sting will be returned
		Random rnd = new Random(); // object to make random
		StringBuffer sbRandomChars = new StringBuffer(); // buffer to store
															// random string
															// based on random
															// placed chars
		randomString = lettersLowCase; // initially we have only lower case
										// chars
		if (includeUpperCase) { // if include upper case chars
			randomString = randomString + lettersUpperCase; // add uppercase
															// chars to
															// randomstring
		}
		if (includeNumbers) { // if include numbers
			randomString = randomString + numbers; // add numbers to random
													// string
		}
		if (includeSpecChar) { // if include special chars
			randomString = randomString + specialChars; // include special chars
		}
		int i = 0; // initiate index which we make randomly selected
		while (sbRandomChars.length() < charNumber) { // loop until length of
														// random chars we put
														// for string is less
														// than number of chars
														// supposed for it
			i = (rnd.nextInt(randomString.length())); // i is randomly selected
			sbRandomChars.append(randomString.charAt(i)); // put char with
															// random index from
															// random string to
															// string buffer
		}
		resultString = sbRandomChars.toString();// make string from random
												// string buffer
		log.debug("File content is generated as " + resultString);
		return resultString; // return random string

	}

	// constructor of FileGenerator Class

	public FileGenerator() {
		log = LogManager.getLogger(this.getClass().getName());
		log.debug("FileGenrator object is created");
		// initiate all initial settings
		this.setFileName("");
		this.setFilePath("");
		this.setFileString(null);
		this.setRandom(true);
	}

}
