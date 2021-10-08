package testApplications;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import controller.Controller;
//import testApplications.ShowProperties;
import testApplications.ShowProperties;


public class TracerouteLinux {

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
				p = Runtime.getRuntime().exec("sudo apt-get install traceroute");
				BufferedReader br = new BufferedReader(
					new InputStreamReader(p.getInputStream()));
				while ((s = br.readLine()) != null)
					System.out.println("line: " + s);
				p.waitFor();
				System.out.println ("exit: " + p.exitValue());
				p.destroy();
			} catch (Exception e) {
                e.printStackTrace();
                System.out.println("Could not download/update tracerout for some reason :( RESTARTING");
                Controller.restart();
            }
			
            
            
            System.out.println("Running actual script");
            try {
                p = Runtime.getRuntime().exec("traceroute google.com");
                BufferedReader br = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
                while ((s = br.readLine()) != null)
                    System.out.println(s);
                p.waitFor();
                System.out.println ("exit: " + p.exitValue());
                p.destroy();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Could not run tracerout for some reason :( RESTARTING");
                Controller.restart();

            }
            Controller.restart();

		}
    }












	public static void tracerouteInput(String input) {
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
				p = Runtime.getRuntime().exec("apt-get install traceroute");
				BufferedReader br = new BufferedReader(
					new InputStreamReader(p.getInputStream()));
				while ((s = br.readLine()) != null)
					System.out.println("line: " + s);
				p.waitFor();
				System.out.println ("exit: " + p.exitValue());
				p.destroy();
			} catch (Exception e) {
                e.printStackTrace();
                System.out.println("Could not download/update tracerout for some reason :( RESTARTING");
                Controller.restart();
            }
        
			
            
            
            System.out.println("Running actual script");
          

            try {
                p = Runtime.getRuntime().exec("traceroute "+ input);
                BufferedReader br = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
                while ((s = br.readLine()) != null)
                    System.out.println(s);
                p.waitFor();
                System.out.println ("exit: " + p.exitValue());
                p.destroy();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Could not run tracerout for some reason :( RESTARTING");
                Controller.restart();

            }
            Controller.restart();

        }
    }
    
}
