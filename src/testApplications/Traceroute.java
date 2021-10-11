package testApplications;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import controller.Controller;
//import testApplications.ShowProperties;



public class Traceroute {

    public static void main(String args[]) {
		if(ShowProperties.getOperatingSystem().equals("Windows 10"))
		{
			try {
                traceroutWindowsTest();
            } catch (IOException e) {
                Controller.restart();
                e.printStackTrace();
            }
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



    public static void traceroutWindowsTest() throws IOException{
            String IP = "8.8.8.8";
            Runtime rt = Runtime.getRuntime();
            String[] commands = {"system.exe", "tracert " + IP};
            Process proc;
            
            proc = rt.exec(commands);
            

            BufferedReader stdInput = new BufferedReader(new 
           InputStreamReader(proc.getInputStream()));

            BufferedReader stdError = new BufferedReader(new 
            InputStreamReader(proc.getErrorStream()));

        // Read the output from the command
            System.out.println("Here is the standard output of the command:\n");
            String s = null;
            try {
                while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
                }
            } catch (IOException e) {
                Controller.restart();
                e.printStackTrace();
            }  

        // Read any errors from the attempted command
            System.out.println("Here is the standard error of the command (if any):\n");
            try {
                while ((s = stdError.readLine()) != null) {
                System.out.println(s);
                }
            } catch (IOException e) {
                Controller.restart();
                e.printStackTrace();
            }
    }



    public static void traceroutWindows(String IP) throws IOException{
        //String IP = "8.8.8.8";
        Runtime rt = Runtime.getRuntime();
        String[] commands = {"system.exe", "tracert " + IP};
        Process proc;
        
        proc = rt.exec(commands);
        

        BufferedReader stdInput = new BufferedReader(new 
       InputStreamReader(proc.getInputStream()));

        BufferedReader stdError = new BufferedReader(new 
        InputStreamReader(proc.getErrorStream()));

    // Read the output from the command
        System.out.println("Here is the standard output of the command:\n");
        String s = null;
        try {
            while ((s = stdInput.readLine()) != null) {
            System.out.println(s);
            }
        } catch (IOException e) {
            Controller.restart();
            e.printStackTrace();
        }  

    // Read any errors from the attempted command
        System.out.println("Here is the standard error of the command (if any):\n");
        try {
            while ((s = stdError.readLine()) != null) {
            System.out.println(s);
            }
        } catch (IOException e) {
            Controller.restart();
            e.printStackTrace();
        }
}








	public static void tracerouteLinux(String input) {
		
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
    

