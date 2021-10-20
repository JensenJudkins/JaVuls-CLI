package testApplications;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

import apps.Enumeration.LocalIPLookupHost;
import backend.ShowProperties;
import controller.Controller;

public class NetcatListener {
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
				p = Runtime.getRuntime().exec("sudo apt install ncat -yy");
				BufferedReader br = new BufferedReader(
					new InputStreamReader(p.getInputStream()));
				while ((s = br.readLine()) != null)
					System.out.println("line: " + s);
				p.waitFor();
				System.out.println ("exit: " + p.exitValue());
				p.destroy();
			} catch (Exception e) {}

            
			
            
            
            
                

		}


        System.out.println("INSTALLING ALL DEPENDANCIES");
        String s;
        Process p;
        try {
            p = Runtime.getRuntime().exec("ncat -nvlp 9000");
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

