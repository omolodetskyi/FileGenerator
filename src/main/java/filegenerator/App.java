package filegenerator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {
	private static Logger log;

	public static void main(String[] args) {
		log = LogManager.getLogger(App.class.getName());
		log.info("FileGenerator is started");
		FileGeneratorUI fgui = new FileGeneratorUI();
		log.debug("FileGeneratorUI object is initiated");
		FileGenerator fg = new FileGenerator();
		log.debug("FileGenerator object is initiated");
		FileGeneratorController controller = new FileGeneratorController(fg, fgui);
		log.debug("FileGeneratorController object is initiated");

	}
}
