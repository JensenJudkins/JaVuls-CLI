package testApplications;
/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import testApplications.*;
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

public class Tests 
{

	public static String returnMyPublicIp() throws UnknownHostException, IOException
	{
		String ip = java.net.InetAddress.getLocalHost().getHostAddress();
		return ip;
	}

	public String returnLocalIP() throws UnknownHostException, IOException
	{
		String ip = java.net.InetAddress.getLocalHost().getHostAddress();
		return ip;
	}


	//create a method print ping of a given ip in windows
	public static void printPingWindows(String ip) throws IOException, InterruptedException
	{
		String command = "ping -n 1 " + ip;
		Process p = Runtime.getRuntime().exec(command);
		BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null)
		{
			System.out.println(inputLine);
		}
		in.close();
	}



	public static void printPing(String ip) throws IOException
	{
		String ping = "ping -c 1 " + ip;
		Process p = Runtime.getRuntime().exec(ping);
		BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null)
		{
			System.out.println(inputLine);
		}
		in.close();
	}





	public static boolean ping(String ip) throws IOException, InterruptedException
	{
		String command = "ping -c 1 " + ip;
		Process process = Runtime.getRuntime().exec(command);
		int exitValue = process.waitFor();
		return (exitValue == 0);
	}

	public static void main(String[] args)
	{
		try {
			printPing("8.8.8.8");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			printPingWindows("8.8.8.8");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	

		try {
			System.out.println(returnMyPublicIp());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		


		//creates a string of the operating system for future usage
		//String localOS = ShowProperties.getOperatingSystem();
		
		//SlowLoris.slowLorisRun("http://judkinscustomtaxidermy.com", 80, 0, 0);
		/*
		try {
			
			//Grabs the operating system that the program is running on
			//ShowProperties.getOperatingSystem();
			//Gets your local machine IP
			//YourIPLookup.getLocalIP();
			//YourIPLookup.viewMyIP();
			
			//if machine is on windows it will run the windows version of command line tools
			
			if (localOS.equals("Windows 10"))
			{
				WindowsCommandLineIPandPing.windowsPing("8.8.8.8");
				controller.AddABreak.addBreak(5);
				
			}
			else
			{
				LinuxPing.LinuxPinger("google.com");
				controller.AddABreak.addBreak(5);
			}
			
			
			//Runs the port scanner
			PortScanner.main(YourIPLookup.getLocalIP().toString());
			
			
			// A bunch of catch clauses for all the possible errors
			} catch (UnknownHostException e) {
			// 
			e.printStackTrace();
			} catch (InterruptedException e) {
				// 
				e.printStackTrace();
			} catch (ExecutionException e) {
				// 
				e.printStackTrace();
			} catch (IOException e) {
				// 
				e.printStackTrace();
			}
		*/
		
		
		
	}
}
