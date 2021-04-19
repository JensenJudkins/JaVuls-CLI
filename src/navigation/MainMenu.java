package navigation;

import navigation.*;

public class MainMenu {
    
    public static void freshStartMenu() {
    	Banner.createBanner();
    	mainMenuList();
    }
    
    
    public static void mainMenuList(){
    	System.out.println("");
    	System.out.println("1) List (your) Public IP");
    	System.out.println("2) Port Scanner");
    	System.out.println("3) Encrypt file with AES");
    	System.out.println("4) Decrypt AES file");
    	System.out.println("5) MD5 Brute Force Crack");
    	System.out.println("6) Slow Loris Attack");
    	System.out.println("7) HTTP Server");
    	
    	
    	
    	
    	
    	System.out.println("99) Quit");
    }
    
}
