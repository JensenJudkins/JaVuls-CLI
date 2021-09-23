package testApplications;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import apps.Enumeration.LocalIPLookupHost;
//import testApplications.ShowProperties;

public class LinuxArpScan {
	
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
				p = Runtime.getRuntime().exec("sudo apt-get install arp-scan");
				BufferedReader br = new BufferedReader(
					new InputStreamReader(p.getInputStream()));
				while ((s = br.readLine()) != null)
					System.out.println("line: " + s);
				p.waitFor();
				System.out.println ("exit: " + p.exitValue());
				p.destroy();
			} catch (Exception e) {}
			try {
				p = Runtime.getRuntime().exec("sudo arp-scan --interface=eth0 " +  LocalIPLookupHost.getLocalIP() +"/24");
				BufferedReader br = new BufferedReader(
					new InputStreamReader(p.getInputStream()));
				while ((s = br.readLine()) != null)
					System.out.println("line: " + s);
				p.waitFor();
				System.out.println ("exit: " + p.exitValue());
				p.destroy();
			} catch (Exception e) {}
		}
    }
	
	public void arpScanLinux()
	{
		/*
		String s;
        Process p;
        try {
            p = Runtime.getRuntime().exec("ls -aF");
            BufferedReader br = new BufferedReader(
                new InputStreamReader(p.getInputStream()));
            while ((s = br.readLine()) != null)
                System.out.println("line: " + s);
            p.waitFor();
            System.out.println ("exit: " + p.exitValue());
            p.destroy();
			*/
			String s;
        Process p;
        try {
            p = Runtime.getRuntime().exec("sudo apt-get install arp-scan");
            BufferedReader br = new BufferedReader(
                new InputStreamReader(p.getInputStream()));
            while ((s = br.readLine()) != null)
                System.out.println("line: " + s);
            p.waitFor();
            System.out.println ("exit: " + p.exitValue());
            p.destroy();
        } catch (Exception e) {}
	}
}
