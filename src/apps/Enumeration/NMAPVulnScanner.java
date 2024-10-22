package apps.Enumeration;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import backend.ShowProperties;
//import apps.Enumeration.LocalIPLookupHost;
import controller.Controller;

public class NMAPVulnScanner {
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
				p = Runtime.getRuntime().exec("apt install nmap -y");
				BufferedReader br = new BufferedReader(
					new InputStreamReader(p.getInputStream()));
				while ((s = br.readLine()) != null)
					System.out.println("line: " + s);
				p.waitFor();
				System.out.println ("exit: " + p.exitValue());
				p.destroy();
			} catch (Exception e) {}
			try {
				p = Runtime.getRuntime().exec("apt install git -y");
				BufferedReader br = new BufferedReader(
					new InputStreamReader(p.getInputStream()));
				while ((s = br.readLine()) != null)
					System.out.println("line: " + s);
				p.waitFor();
				System.out.println ("exit: " + p.exitValue());
				p.destroy();
			} catch (Exception e) {}
            try {
				p = Runtime.getRuntime().exec("git clone https://github.com/vulnersCom/nmap-vulners.git");
				BufferedReader br = new BufferedReader(
					new InputStreamReader(p.getInputStream()));
				while ((s = br.readLine()) != null)
					System.out.println("line: " + s);
				p.waitFor();
				System.out.println ("exit: " + p.exitValue());
				p.destroy();
			} catch (Exception e) {}

            //RUN SCRIPT
            System.out.println("Running actual script");
            try {
				p = Runtime.getRuntime().exec("nmap -Pn -T4 -sV --script vulners 127.0.0.1");
				BufferedReader br = new BufferedReader(
					new InputStreamReader(p.getInputStream()));
				while ((s = br.readLine()) != null)
					System.out.println("line: " + s);
				p.waitFor();
				System.out.println ("exit: " + p.exitValue());
				p.destroy();
			} catch (Exception e) {}




            try {
				p = Runtime.getRuntime().exec("rm -rf nmap-vulners/");
				BufferedReader br = new BufferedReader(
					new InputStreamReader(p.getInputStream()));
				while ((s = br.readLine()) != null)
					System.out.println("line: " + s);
				p.waitFor();
				System.out.println ("exit: " + p.exitValue());
				p.destroy();
			} catch (Exception e) {}
            //Remove vulners
		}
    }












	public static void NMAPVulnersScan(String IP) {
		if(ShowProperties.getOperatingSystem().equals("Windows 10"))
		{
			System.out.println("This is a windows machine, sorry this is only supported by Linux machines at the moment :(");
			Controller.restart();
		}
		else
		{
            //INSTALL DEPENDENCIES
            System.out.println("INSTALLING ALL DEPENDANCIES");
			String s;
			Process p;
			try {
				p = Runtime.getRuntime().exec("apt install nmap git");
				BufferedReader br = new BufferedReader(
					new InputStreamReader(p.getInputStream()));
				while ((s = br.readLine()) != null)
					System.out.println(s);
				p.waitFor();
				System.out.println ("exit: " + p.exitValue());
				p.destroy();
			} catch (Exception e) {}
			
            

            //RUN SCRIPT
            System.out.println("Running actual script");
            try {
				p = Runtime.getRuntime().exec("nmap -Pn -sV -T4 --script vulners " + IP);
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
