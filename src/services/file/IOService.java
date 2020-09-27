package services.file;

import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;


public class IOService {
	/** Reads a local file at the given path, which must be located within the 'src' directory of this project. 
	 *  Given path must be a relative address from the 'src' location.
	 * @param path
	 * @return String representation of the given file, or empty string if file not found.
	 */
	public static String readSourceFile(String path) {
		try {
			InputStream stream = IOService.class.getResourceAsStream(path);
			
			ByteArrayOutputStream result = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int length;
			while ((length = stream.read(buffer)) != -1) 
			    result.write(buffer, 0, length);
			
			return result.toString();
		}
		catch (Exception e) {
			System.out.println("Couldn't read file on path: " + path);
			return "";
		}
	}
	
	/** Write the string text into a file specified by the given path */
	public static void writeToFile(String text, String fileLocation) {
		try {
			PrintWriter out = new PrintWriter(new FileWriter(fileLocation));
			System.out.println("Printing to location: " + fileLocation);
			out.print(text);
			out.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("There was an error while trying to write to file: " + fileLocation);
		}
	}
	
	/** Return all the lines from the specified file as a single String */
	public static String readFile(String fileLocation) {
		try {
			return new String(Files.readAllBytes(Paths.get(fileLocation)));
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(fileLocation + " doesn't exist");
			
			return "";
		}
	}
}
