package applications;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
 
/**
 * @author Crunchify.com 
 * Implement Linux Ping Utility in Java
 */
 
public class LinuxPing {
 
	private class crunchifyResultFromCommand extends Thread {
		InputStream inputStream = null;
 
		// This abstract class is the superclass of all classes representing an input stream of bytes.
		crunchifyResultFromCommand(InputStream is, String type) {
			this.inputStream = is;
		}
 
		public void run() {
			String crunchifyString = null;
			try {
 
				// Reads text from a character-input stream, buffering characters so as to provide for the efficient reading of characters, arrays, and lines.
				BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
				while ((crunchifyString = br.readLine()) != null) {
					System.out.println(crunchifyString);
				}
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}
 
	public crunchifyResultFromCommand getStreamResult(InputStream inputStream, String type) {
		return new crunchifyResultFromCommand(inputStream, type);
	}
 
	/*
	 * public static void main(String[] args) 
	{
		LinuxPinger("8.8.8.8");
		controller.AddABreak.addBreak(5);
		LinuxCurl("https://google.com");
		
	}
	*/
	public static void LinuxPinger(String w)
	{
		// Returns the runtime object associated with the current Java application.
				Runtime crunchifyRuntime = Runtime.getRuntime();
				LinuxPing rte = new LinuxPing();
				crunchifyResultFromCommand crunchifyError, crunchifyResult;
		 
				try {
					
					Process proc2 = crunchifyRuntime.exec("ping " + w);
					
					crunchifyError = rte.getStreamResult(proc2.getErrorStream(), "ERROR");
					crunchifyResult = rte.getStreamResult(proc2.getInputStream(), "OUTPUT");
					crunchifyError.start();
					crunchifyResult.start();
		 
					// Signals that an I/O exception of some sort has occurred.
				} catch (IOException exception) {
					exception.printStackTrace();
				}
	}
	
	public static void LinuxCurl(String w)
	{
		// Returns the runtime object associated with the current Java application.
				Runtime crunchifyRuntime = Runtime.getRuntime();
				LinuxPing rte = new LinuxPing();
				crunchifyResultFromCommand crunchifyError, crunchifyResult;
		try {
			Process proc1 = crunchifyRuntime.exec("curl -v " + w);
			
			crunchifyError = rte.getStreamResult(proc1.getErrorStream(), "ERROR");
			crunchifyResult = rte.getStreamResult(proc1.getInputStream(), "OUTPUT");
			
			crunchifyError.start();
			crunchifyResult.start();
 
			// Signals that an I/O exception of some sort has occurred.
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
 
}
