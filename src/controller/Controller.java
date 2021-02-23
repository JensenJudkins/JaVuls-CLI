package controller;

import navigation.*;
import testApplications.*;
import java.util.Scanner;

public class Controller {
	
	public void start(){
		createCLI();
		
		
	}
	public void createCLI()
	{
		Banner.createBanner();
		MainMenu.mainMenuList();
	}

}
