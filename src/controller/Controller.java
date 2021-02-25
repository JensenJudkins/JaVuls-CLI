package controller;

import navigation.*;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import apps.*;

import java.lang.Integer;
import java.net.UnknownHostException;

public class Controller {
	
	public void start(){
		createCLI();
		runApplication();
		
	}
	public void restart() {
		MainMenu.mainMenuList();
		runApplication();
	}
	public void createCLI()
	{
		
		MainMenu.freshStartMenu();
	}
	public void runApplication(){
		Scanner inputScanner = new Scanner(System.in);
		String input = inputScanner.nextLine();
		int x = Integer.parseInt(input);
		if( x == 1)
		{
			try {
				PublicIPLookupHost.main(null);
				restart();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				restart();
			}
		}
	}
	





}
