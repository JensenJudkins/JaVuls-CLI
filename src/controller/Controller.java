package controller;

import navigation.*;
import testApplications.*;
import java.util.Scanner;
import readyForImplementation.*;
import testApplications.*;
import java.lang.Integer;

public class Controller {
	
	public void start(){
		createCLI();
		runApplication();
		
	}
	public void createCLI()
	{
		Banner.createBanner();
		MainMenu.mainMenuList();
	}
	public void runApplication(){
		Scanner inputScanner = new Scanner(System.in);
		String input = inputScanner.nextLine();
		var x = Integer.parseInt(input);
		if( x == 1)
		{
			Banner.createBanner();
		}
	}

}
