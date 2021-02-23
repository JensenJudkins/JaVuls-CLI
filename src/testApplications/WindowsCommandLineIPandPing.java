package applications;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class WindowsCommandLineIPandPing 
{
	public printOutput getStreamWrapper(InputStream is, String type) 
	{
		return new printOutput(is, type);
	}
	
	
	//Main method for tests
	public static void main(String[] args)
	{
		//windowsPing(null);
		//windowsNmap();
		//windowsARP();
	}
	
	public static void windowsPing(String pingy)
	{
		Runtime rt = Runtime.getRuntime();
		WindowsCommandLineIPandPing rte = new WindowsCommandLineIPandPing();
		printOutput errorReported, outputMessage;
		
		
		try{
		//Process p = Runtime.getRuntime().exec("ping google.com");
		//Logic that happens with the command line thing in windows
			
		Process proc = rt.exec("ping " + pingy);
		errorReported = rte.getStreamWrapper(proc.getErrorStream(), "ERROR");
		outputMessage = rte.getStreamWrapper(proc.getInputStream(), "OUTPUT");
		errorReported.start();
		outputMessage.start();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		
	}
	
	public static void windowsIPConfig()
	{
		Runtime rt = Runtime.getRuntime();
		WindowsCommandLineIPandPing rte = new WindowsCommandLineIPandPing();
		printOutput errorReported, outputMessage;
		try{
			//Process p = Runtime.getRuntime().exec("ping google.com");
			//Logic that happens with the command line thing in windows
				
			Process proc = rt.exec("IPConfig");
			errorReported = rte.getStreamWrapper(proc.getErrorStream(), "ERROR");
			outputMessage = rte.getStreamWrapper(proc.getInputStream(), "OUTPUT");
			errorReported.start();
			outputMessage.start();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
	}
	
	public static void windowsNmap()
	{
		Runtime rt = Runtime.getRuntime();
		WindowsCommandLineIPandPing rte = new WindowsCommandLineIPandPing();
		printOutput errorReported, outputMessage;
		try{
			//Process p = Runtime.getRuntime().exec("ping google.com");
			//Logic that happens with the command line thing in windows
				
			Process proc = rt.exec("IPConfig");
			errorReported = rte.getStreamWrapper(proc.getErrorStream(), "ERROR");
			outputMessage = rte.getStreamWrapper(proc.getInputStream(), "OUTPUT");
			errorReported.start();
			outputMessage.start();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
	}
	
	public static String windowsARP()
	{
		Runtime rt = Runtime.getRuntime();
		WindowsCommandLineIPandPing rte = new WindowsCommandLineIPandPing();
		printOutput errorReported, outputMessage = null;
		try{
			//Process p = Runtime.getRuntime().exec("ping google.com");
			//Logic that happens with the command line thing in windows
				
			Process proc = rt.exec("arp -a");
			errorReported = rte.getStreamWrapper(proc.getErrorStream(), "ERROR");
			outputMessage = rte.getStreamWrapper(proc.getInputStream(), "OUTPUT");
			errorReported.start();
			outputMessage.start();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		return printOutput.printToScreen(null);

	}
	
	
	public static class printOutput extends Thread {
		InputStream is = null;
 
		printOutput(InputStream is, String type) {
			this.is = is;
		}
 
		static String s = null;
		public void run() {
			
			try {
				BufferedReader br = new BufferedReader(
						new InputStreamReader(is));
				while ((s = br.readLine()) != null) {
					System.out.println(s);
					printToScreen(s);
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			
			
			
			
		}
		
		public static String printToScreen(String s)
		{
			return s;
		}
		
		
	}
}
