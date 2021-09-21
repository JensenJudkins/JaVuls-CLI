package testApplications;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

import apps.Enumeration.LocalIPLookupHost;
import controller.Controller;
import testApplications.ShowProperties;

public class NMAPDeviceDiscovery {
    public static void main(String args[]) {
		if(ShowProperties.getOperatingSystem().equals("Windows 10"))
		{
			System.out.println("This is a windows machine");
		}
		else
		{
            //INSTALL DEPENDENCIES
            System.out.println("INSTALLING ALL DEPENDANCIES");
			String s;
			Process p;
			try {
				p = Runtime.getRuntime().exec("sudo apt-get install nmap");
				BufferedReader br = new BufferedReader(
					new InputStreamReader(p.getInputStream()));
				while ((s = br.readLine()) != null)
					System.out.println("line: " + s);
				p.waitFor();
				System.out.println ("exit: " + p.exitValue());
				p.destroy();
			} catch (Exception e) {}
			
            
            
            try {
                String yourIp = LocalIPLookupHost.viewMyIP();
                
                String[] seperated = yourIp.split("\\.");
                System.out.println(seperated[0]);
                System.out.println(seperated[1]);
                System.out.println(seperated[2]);
                System.out.println(seperated[3]);
                    //RUN SCRIPT
                System.out.println("Running actual script");
                try {
                    p = Runtime.getRuntime().exec("nmap -sn "  + seperated[0] + "." + seperated[1] + "." + seperated[2] + ".0/24");
                    BufferedReader br = new BufferedReader(
                        new InputStreamReader(p.getInputStream()));
                    while ((s = br.readLine()) != null)
                        System.out.println(s);
                    p.waitFor();
                    System.out.println ("exit: " + p.exitValue());
                    p.destroy();
                } catch (Exception e) {}
                } catch (UnknownHostException e1) {
                    System.out.println("Something broke dude :(");
                    e1.printStackTrace();
                    Controller.restart();
                }

		}
    }












	public static void NMAPNetworkHostDiscovery() {
		if(ShowProperties.getOperatingSystem().equals("Windows 10"))
		{
			System.out.println("This is a windows machine");
		}
		else
		{
            //INSTALL DEPENDENCIES
            System.out.println("INSTALLING ALL DEPENDANCIES");
			String s;
			Process p;
			try {
				p = Runtime.getRuntime().exec("sudo apt-get install nmap");
				BufferedReader br = new BufferedReader(
					new InputStreamReader(p.getInputStream()));
				while ((s = br.readLine()) != null)
					System.out.println("line: " + s);
				p.waitFor();
				System.out.println ("exit: " + p.exitValue());
				p.destroy();
			} catch (Exception e) {}
			
            
            
            try {
                String yourIp = LocalIPLookupHost.viewMyIP();
                
                String[] seperated = yourIp.split("\\.");
                System.out.println(seperated[0]);
                System.out.println(seperated[1]);
                System.out.println(seperated[2]);
                System.out.println(seperated[3]);
                    //RUN SCRIPT
                System.out.println("Running actual script");
                try {
                    p = Runtime.getRuntime().exec("nmap -sn "  + seperated[0] + "." + seperated[1] + "." + seperated[2] + ".0/24");
                    BufferedReader br = new BufferedReader(
                        new InputStreamReader(p.getInputStream()));
                    while ((s = br.readLine()) != null)
                        System.out.println(s);
                    p.waitFor();
                    System.out.println ("exit: " + p.exitValue());
                    p.destroy();
                } catch (Exception e) {}
                } catch (UnknownHostException e1) {
                    System.out.println("Something broke dude :(");
                    e1.printStackTrace();
                    Controller.restart();
                }

    
        }
    }
}
