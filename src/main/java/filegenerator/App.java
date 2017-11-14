package filegenerator;

public class App {
	public static void main(String[] args) {
		FileGeneratorUI fgui = new FileGeneratorUI();
		FileGenerator fg = new FileGenerator();
		FileGeneratorController controller = new FileGeneratorController(fg, fgui);

	}
}
