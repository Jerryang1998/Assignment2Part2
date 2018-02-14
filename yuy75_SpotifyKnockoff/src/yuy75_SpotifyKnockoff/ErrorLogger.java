package yuy75_SpotifyKnockoff;

import java.io.*;

public class ErrorLogger {
	public static void log(String errorMessage) throws IOException{
		//Save the following information to error.txt
		//Data, Time, errorMessage \n
		String fileName = "error.text";
		try {
			PrintWriter outputStream = new PrintWriter(fileName);
			outputStream.println("01/30/2018, 09:00 PM, Error");
			outputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
}
}
