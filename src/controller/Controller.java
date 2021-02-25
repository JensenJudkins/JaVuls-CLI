package controller;

import navigation.*;
import readyForImplementation.PortScanner;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;
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
		
	//Now it is selecting the apps	
		//public ip lookup
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
		//Port Scanner
		if(x ==2) {
			System.out.println("Input the IP you wish to scan (deafult is local host 127.0.0.1): ");
			String ip = inputScanner.nextLine();
			System.out.println("Input number of threads for port scan (default is 0 which is the fastest): ");
			String threads = inputScanner.nextLine();
			if(threads.equals(""))
			{
				threads = "0";
			}
			
			if(ip.equals("")) {
				try {
					System.out.println("Scanning local host 127.0.0.1 with " + threads+ " threads");
					PortScanner.localPortScan(threads);
					restart();
				} catch (UnknownHostException | InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					restart();
				}
			}
			
			try {
				System.out.println("Scanning "+ip+" with " + threads+ " threads");
				PortScanner.foreignPortScan(ip, threads);
				restart();
			} catch (UnknownHostException | InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				restart();
			}
		}
	}
	





}
