package testApplications;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class StartMonitorMode {
    public static String pathToScript = new File("MonitorModeScript.sh").getAbsolutePath();
 public static void main(String[] args) {
    Scanner inputScanner = new Scanner(System.in);
    System.out.println("Please input the interface to set to monitor mode");
    //String input = inputScanner.nextLine();
    String interfaceToChange = inputScanner.nextLine();
    System.out.println(new File("MonitorModeScript.sh").getAbsolutePath());
    System.out.println(pathToScript);
    if(interfaceToChange.equals(""))
      {
          interfaceToChange = "wlan0";
      }
     StartMonMode(interfaceToChange);
 }
 public static void StartMonMode(String interfaceToChange)
 {
 Process p;
  try {
      if(interfaceToChange.equals(""))
      {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Please input the interface to set to monitor mode");
        //String input = inputScanner.nextLine();
        interfaceToChange = inputScanner.nextLine();
      }
   String[] cmd = { "sh", pathToScript, interfaceToChange};
   p = Runtime.getRuntime().exec(cmd); 
   p.waitFor(); 
   BufferedReader reader=new BufferedReader(new InputStreamReader(
    p.getInputStream())); 
   String line; 
   while((line = reader.readLine()) != null) { 
    System.out.println(line);
   } 
  } catch (IOException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  } catch (InterruptedException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
 }
}
