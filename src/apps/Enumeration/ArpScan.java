package apps.Enumeration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import backend.ShowProperties;
//import testApplications.ShowProperties;
import controller.Controller;

public class ArpScan {
	
	public static void main(String args[]) {
		if(ShowProperties.getOperatingSystem().equals("Windows 10"))
		{
			System.out.println("This is a windows machine");
		}
		else
		{
			String s;
			Process p;
			try {
				p = Runtime.getRuntime().exec("apt install arp-scan");
				BufferedReader br = new BufferedReader(
					new InputStreamReader(p.getInputStream()));
				while ((s = br.readLine()) != null)
					System.out.println(s);
				p.waitFor();
				System.out.println ("exit: " + p.exitValue());
				p.destroy();
			} catch (Exception e) {}
			try {
				System.out.println("Please input the interface you wish to arp scan: ");
				Scanner inputScanner = new Scanner(System.in);
				String Interface = inputScanner.nextLine();
				inputScanner.close();
				p = Runtime.getRuntime().exec("sudo arp-scan -v --localnet --interface="+ Interface );
				BufferedReader br = new BufferedReader(
					new InputStreamReader(p.getInputStream()));
				while ((s = br.readLine()) != null)
					System.out.println(s);
				p.waitFor();
				System.out.println ("exit: " + p.exitValue());
				p.destroy();
			} catch (Exception e) {}
		}
		
    }
	
	public static void arpScanLinux()
	{
		if(ShowProperties.getOperatingSystem().equals("Windows 10"))
		{
			System.out.println("This is a windows machine");
			Controller.restart();
		}
		else
		{
			String s;
			Process p;
			try {
				p = Runtime.getRuntime().exec("sudo apt install arp-scan");
				BufferedReader br = new BufferedReader(
					new InputStreamReader(p.getInputStream()));
				while ((s = br.readLine()) != null)
					System.out.println(s);
				p.waitFor();
				System.out.println ("exit: " + p.exitValue());
				p.destroy();
			} catch (Exception e) {}
			try {
				System.out.println("Please input the interface you wish to arp scan: ");
				Scanner inputScanner = new Scanner(System.in);
				String Interface = inputScanner.nextLine();
				inputScanner.close();
				p = Runtime.getRuntime().exec("sudo arp-scan -v --localnet --interface="+ Interface );
				BufferedReader br = new BufferedReader(
					new InputStreamReader(p.getInputStream()));
				while ((s = br.readLine()) != null)
					System.out.println(s);
				p.waitFor();
				System.out.println ("exit: " + p.exitValue());
				p.destroy();
			} catch (Exception e) {}
			Controller.restart();
		}
	}
}
