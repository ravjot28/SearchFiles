import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {

	private static List<String> searchFiles(File file, String pattern,
			List<String> result) throws FileNotFoundException {

		if (!file.isDirectory()) {
			throw new IllegalArgumentException("file has to be a directory");
		}

		if (result == null) {
			result = new ArrayList<String>();
		}

		File[] files = file.listFiles();

		if (files != null) {
			for (File currentFile : files) {
				if (currentFile.isDirectory()) {
					searchFiles(currentFile, pattern, result);
				} else {
					Scanner scanner = new Scanner(currentFile);
					if (scanner.findWithinHorizon(pattern, 0) != null) {
						result.add(currentFile.getAbsolutePath());
					}
					scanner.close();
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		File folder = new File("/Users/ravjotsingh/Desktop/r");
		ArrayList<String> files = new ArrayList<String>();
		try {
			files = (ArrayList<String>) searchFiles(folder, "rav", files);
			for(String file:files)
				System.out.println(file);
		} catch (FileNotFoundException e1) {
			// you should tell the user here that something went wrong
		}
	}

}
