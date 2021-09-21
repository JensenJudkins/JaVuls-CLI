package navigation;

import controller.Controller;

public class MainMenu {
    
    public static void freshStartMenu() {
    	Banner.createBanner();
    	mainMenuListFirstTime();
    }
    
    
    public static void mainMenuList(){
    	System.out.println("");
    	System.out.println("");
    	System.out.println("");
    	System.out.println("Services Running:");
    	System.out.println("");
    	System.out.println("	HTTP Server:" + httpIsRunning());
    	System.out.println("");
    	System.out.println("1) List (your) Public IP");
    	System.out.println("2) List (your) Local IP");
    	System.out.println("3) Port Scanner");
		System.out.println("4) NMAP Vulners Scan (requires root)");
    	System.out.println("5) Encrypt file with AES");
    	System.out.println("6) Decrypt AES file");
    	System.out.println("7) Hash Brute Force Cracker");
    	System.out.println("8) Hash Brute Force Benchmark");
    	System.out.println("9) Slow Loris Attack");
    	System.out.println("10) HTTP Server");
    	System.out.println("11) Reverse TCP Handler (User)");
    	System.out.println("12) Reverse TCP Connector (Victim)");
    	System.out.println("13) Put Interface Into Monitor Mode (SUDO REQUIREMENT, Linux Only)");
    	System.out.println("14) DDoS Attack");
		System.out.println("14) Network Host Discovery (SUDO REQUIREMENT, Linux Only)");
    	
    	
    	
    	
    	System.out.println("99) Quit");
    }
    
    public static void mainMenuListFirstTime(){
    	System.out.println("Services Running:");
    	System.out.println("");
    	System.out.println("	HTTP Server:" + " Off");
    	System.out.println("");
    	System.out.println("1) List (your) Public IP");
    	System.out.println("2) List (your) Local IP");
    	System.out.println("3) Port Scanner");
		System.out.println("4) NMAP Vulners Scan (SUDO REQUIREMENT, Linux Only)");
    	System.out.println("5) Encrypt file with AES");
    	System.out.println("6) Decrypt AES file");
    	System.out.println("7) Hash Brute Force Cracker");
    	System.out.println("8) Hash Brute Force Benchmark");
    	System.out.println("9) Slow Loris Attack");
    	System.out.println("10) HTTP Server");
    	System.out.println("11) Reverse TCP Handler (User)");
    	System.out.println("12) Reverse TCP Connector (Victim)");
    	System.out.println("13) Put Interface Into Monitor Mode (SUDO REQUIREMENT, Linux Only)");
    	System.out.println("14) DDoS Attack");
		System.out.println("15) Network Host Discovery (SUDO REQUIREMENT, Linux Only)");
    	
    	
    	
    	
    	System.out.println("99) Quit");
    }
    
    public static String httpIsRunning() {
    	String on = " Off";
    	if(Controller.httpServerRunning())
    	{
    		on = " Running";
    	}
    	
    	return on;
    }
    
}
