package filegenerator;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FileGeneratorTest {

	String fileName, expectedFileName; // actual filename and expected
	String filePath, expectedFilePath; // actual filepath and expected
	StringBuffer fileContent = new StringBuffer(); // bufferstring to collect
													// file content
	String actualfileContent, expectedFileContent; // actual file content and
													// expected
	FileGenerator fileGenerator = new FileGenerator(); // object of
														// FileGenerator

	@Test
	public void createFileTest() {

		expectedFilePath = "/Users/alexander/Documents/FileGenerator/"; // expected
																		// path
		expectedFileName = "test.txt"; // expected name
		expectedFileContent = "some text here";

		// file object with this name and path

		File file = new File(expectedFilePath + expectedFileName);
		// use creatFile method to create file

		fileGenerator.createFile(expectedFilePath, expectedFileName, "some text here");
		// verify if file exist or not

		Assert.assertTrue(file.exists(), "File does not exist! ");

		// verify if file is readable

		Assert.assertTrue(file.canRead(), "File can not be read! ");
		try {
			FileReader fileReader = new FileReader(file);
			Scanner scan = new Scanner(fileReader);
			while (scan.hasNext()) {
				fileContent.append(scan.nextLine());
			}
			fileReader.close();
			Assert.assertEquals(fileContent.toString(), expectedFileContent, "File content is not the same!");
		} catch (IOException e) {
			Assert.assertTrue(false, "Error while reading file!");
		}
	}

	@Test
	public void multiplyStringTest() {

		// test string generated 5 times without new line, with ; as separator

		String expectedString = "Some text here;Some text here;Some text here;Some text here;Some text here;";
		String actualString = fileGenerator.multiplyString("Some text here", 5, false, ";");
		Assert.assertEquals(actualString, expectedString, "Wrong string generated! ");

		// test string generated 3 times with new line without separator

		expectedString = "Some text here" + System.getProperty("line.separator") + "Some text here"
				+ System.getProperty("line.separator") + "Some text here" + System.getProperty("line.separator")
				+ "Some text here" + System.getProperty("line.separator") + "Some text here";
		actualString = fileGenerator.multiplyString("Some text here", 5, true, "");
		Assert.assertEquals(actualString, expectedString, "Wrong string generated! ");
	}

	@Test
	public void generateRandomStringTest() {
		int numberOfChars = 100;
		String test = fileGenerator.generateRandomString(numberOfChars, true, true, true);
		Assert.assertEquals(test.length(), numberOfChars, "Wrong string generated! Number of chars is not correct!");
	}
}
