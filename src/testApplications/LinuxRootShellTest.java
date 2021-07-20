package testApplications;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class LinuxRootShellTest {
	public static void main(String[] args){
		String command = "chmod +s /usr/bin/python3";
		
		try {
			Process process = Runtime.getRuntime().exec(command);
			BufferedReader reader = new BufferedReader(
				new InputStreamReader(process.getInputStream()));
			String line;
			while  ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
