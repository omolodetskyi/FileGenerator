package filegenerator;

import java.io.File;

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
		File file = new File(expectedFilePath + expectedFileName); // file
																	// object
																	// with this
																	// name and
																	// path
		fileGenerator.createFile(expectedFilePath, expectedFileName, "some text here");
		Assert.assertTrue(file.exists(), "File does not exist!"); // verify if
																	// file
																	// exist or
																	// not

	}
}
