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
    }
    
}
