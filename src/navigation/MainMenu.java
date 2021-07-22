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
    	System.out.println("2) Port Scanner");
    	System.out.println("3) Encrypt file with AES");
    	System.out.println("4) Decrypt AES file");
    	System.out.println("5) Hash Brute Force Cracker");
    	System.out.println("6) Hash Brute Force Benchmark");
    	System.out.println("7) Slow Loris Attack");
    	System.out.println("8) HTTP Server");
    	System.out.println("9) Reverse TCP Handler (User)");
    	System.out.println("10) Reverse TCP Connector (Victim)");
    	
    	
    	
    	
    	
    	System.out.println("99) Quit");
    }
    
    public static void mainMenuListFirstTime(){
    	System.out.println("Services Running:");
    	System.out.println("");
    	System.out.println("	HTTP Server:" + " Off");
    	System.out.println("");
    	System.out.println("1) List (your) Public IP");
    	System.out.println("2) Port Scanner");
    	System.out.println("3) Encrypt file with AES");
    	System.out.println("4) Decrypt AES file");
    	System.out.println("5) Hash Brute Force Cracker");
    	System.out.println("6) Hash Brute Force Benchmark");
    	System.out.println("7) Slow Loris Attack");
    	System.out.println("8) HTTP Server");
    	System.out.println("9) Reverse TCP Handler (User)");
    	System.out.println("10) Reverse TCP Connector (Victim)");
    	
    	
    	
    	
    	
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
