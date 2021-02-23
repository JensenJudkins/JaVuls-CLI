package testFiles;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import applications.ShowProperties;

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
		            p = Runtime.getRuntime().exec("ls -aF");
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
        } catch (Exception e) {}
	}
}
