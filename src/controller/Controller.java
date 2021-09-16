package controller;

import navigation.*;
import testApplications.StartMonitorMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import java.io.IOException;
import apps.*;
import apps.EncryptionDecryption.EncryptDecryptFilesAES;
import apps.EncryptionDecryption.HashBruteForce;
import apps.EncryptionDecryption.HashBruteForceWordlist;
import apps.Enumeration.LocalIPLookupHost;
import apps.Enumeration.PortScanner;
import apps.Enumeration.PublicIPLookupHost;
import apps.ReverseShell.BindShellModified;
import apps.ReverseShell.ReverseShellModified;
import apps.ReverseShell.originals.BindShellBroken;
import apps.ReverseShell.originals.ReverseShellBroken;
import archive.LinuxReverseShell;
import archive.WindowsReverseShell;

import java.lang.Integer;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Controller {
	static Scanner inputScanner = new Scanner(System.in);
	
	public void start(){
		createCLI();
		runApplication();
	}
	public static void restart() {
		MainMenu.mainMenuList();
		runApplication();
		System.gc();
	}
	public void createCLI()
	{
		MainMenu.freshStartMenu();
	}
	public static int httpCounter = 0;
	
	public static boolean httpServerRunning() {
		boolean isRunning = false;
		if (httpCounter == 0)
		{
			isRunning = false;
		}
		if(httpCounter%2 == 1) {
			isRunning = true;
		}
		
		
		return isRunning;
	}
	
	public static int catchIntException(String x) {
		try {
			int y = Integer.parseInt(x);
			return y;
		} catch (Exception NumberFormatException) {
			System.out.println("You must input a number");
			int y = 999;
			return y;
		}
	}
	
	public static void runApplication(){
		
		String input = inputScanner.nextLine();
		int x = catchIntException(input);
		
		
		if(x == 999) {
			restart();
		}
		
		if(x == 99) {
			System.exit(0);
		}
		
	//SELECT APP SECTION
		//public ip lookup
		if( x == 1)
		{
			PublicIPLookup();
		}
		if( x == 2)
		{
			LocalIPLookup();
		}
		//Port Scanner
		if(x ==3) {
			try {
				PortScanner();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//AES Encrypt
		if(x == 4){
			AESEncrypt();
		}
		//AES Decrypt
		if(x == 5){
			AESDecrypt();
		}
		//MD5 Brute Force
		if(x == 6)
		{
			HashBruteForce();
		}
		if(x == 7)
		{
			BenchmarkHashRate();
		}
		//DDoS Attacks
		if(x == 8)
		{
			SlowLoris();
		}
		//Server Mode
		if(x == 9)
		{
			HTTPServer();
		}
		//Reverse Shell
		if(x == 10)
		{
			try {
				CreateReverseTCPListener();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(x == 11)
		{
			try {
				CreateReverseTCPConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(x == 12)
		{
			StartMonitorMode();
		}
		
	}
	
	
	//APPLICATION STARTER SECTION
	
	
	public static void StartMonitorMode()
	{
		Scanner inputScanner = new Scanner(System.in);
        System.out.println("Please input the interface to set to monitor mode");
        //String input = inputScanner.nextLine();
        String interfaceToChange = inputScanner.nextLine();
		StartMonitorMode.StartMonMode(interfaceToChange);
	}
	
	public static void PublicIPLookup() {
		try {
			PublicIPLookupHost.main(null);
			restart();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			restart();
		}
	}
	
	public static void LocalIPLookup() {
		
			try {
				LocalIPLookupHost.main(null);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			restart();
		
	}
	
	public static void PortScanner() throws IOException {
		System.out.println("Input the IP you wish to scan (default is local host 127.0.0.1): ");
		String ip = inputScanner.nextLine();
		System.out.println("Input number of threads for port scan (default is 0 which is the fastest): ");
		String threads = inputScanner.nextLine();
		System.out.println("Verbose mode? (Shows most likely services for open ports, default is no) [y]: ");
		String setverbose = inputScanner.nextLine();
		boolean verbose = false;
		if(setverbose.equals("y") || setverbose.equals("Y"))
		{
			verbose = true;
		}
		else {
			verbose = false;
		}
		if(threads.equals(""))
		{
			threads = "0";
		}
		
		if(ip.equals("")) {
			try {
				System.out.println("Scanning local host 127.0.0.1 with " + threads+ " threads");
				PortScanner.localPortScan(threads, verbose);
				restart();
			} catch (UnknownHostException | InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				restart();
			}
		}
		
		try {
			System.out.println("Scanning "+ip+" with " + threads+ " threads");
			PortScanner.foreignPortScan(ip, threads, verbose);
			restart();
		} catch (UnknownHostException | InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			restart();
		}
	}
	public static void AESEncrypt() {
		//Grab all input from user
		System.out.println("Path to file you would like to encrypt");
		String fileIn = inputScanner.nextLine();
		while(fileIn.equals(""))
		{
			System.out.println("You must input a file");	
			fileIn = inputScanner.nextLine();
		}
		System.out.println("Input the passphrase for the encryption (MUST BE 24 CHARACTERS LONG, Default is javulsisthebestthingever)");
		String passphrase = inputScanner.nextLine();
		System.out.println("Add file out path for encrypted file (default is same location with .enc extension");
		String fileOut = FilenameUtils.removeExtension(inputScanner.nextLine());
		
		//run tests against inputs
		if(passphrase.equals(""))
		{
			System.out.println("Using default passphrase (javulsisthebestthingever)");
			passphrase = "javulsisthebestthingever";
		}
		if(fileOut.equals(""))
		{
			System.out.println("Using default path out with .enc extension");	
			fileOut = FilenameUtils.removeExtension(fileIn) + ".enc";
		}
		
		//execute
		try {
			EncryptDecryptFilesAES.encryptedFile(passphrase, fileIn, fileOut);
			System.out.println("Encryption executed successfully :)");
			restart();
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Encryption failed, this is so sad");
			restart();
		}
	}
	public static void AESDecrypt(){
		//Grab all input from user
		System.out.println("Drag and drop the file that you would like to decrypt (path to file)");
		String fileIn = inputScanner.nextLine();
		if(fileIn.equals(""))
		{
			System.out.println("You must input a file");
			fileIn = inputScanner.nextLine();
		}
		System.out.println("Input the passphrase for the encryption (MUST BE 24 CHARACTERS LONG, Default is javulsisthebestthingever)");
		String passphrase = inputScanner.nextLine();
		System.out.println("Add file out path for decrypted file (default is same location with .txt extension");
		String fileOut = FilenameUtils.removeExtension(inputScanner.nextLine());
		
		//run tests against inputs
		if(passphrase.equals(""))
		{
			System.out.println("Using default passphrase (javulsisthebestthingever)");
			passphrase = "javulsisthebestthingever";
		}
		if(fileOut.equals(""))
		{
			System.out.println("Using default path out with .txt extension");	
			fileOut = fileIn + ".txt";
		}
		
		//execute
		try {
			EncryptDecryptFilesAES.decryptedFile(passphrase, fileIn, fileOut);
			System.out.println("Decryption executed successfully :)");
			restart();
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Decryption failed, this is so sad");
			restart();
		}
		
	}
 
	public static void SlowLoris() {
		System.out.println("Please input the website/host");
		String host = inputScanner.nextLine();
		while(host.equals(""))
		{
			System.out.println("You must input a website, host, or IP");	
			host = inputScanner.nextLine();
		}
		System.out.println("Input the port Number (default is 80)");
		String port = inputScanner.nextLine();
		System.out.println("Please input the number of threads you wish to use (defaut is 20000)");
		String threads = inputScanner.nextLine();
		System.out.println("Amount of time (in minutes, default is maximum [2147483647 min])");
		String time = inputScanner.nextLine();
				
		apps.SlowLoris.slowLorisRun(host, port, threads, time);
		restart();
	}
	public static void HTTPServer() {
		
		Thread httpServerThread = new Thread(){
		    public void run(){
		    	Scanner inputScanner = new Scanner(System.in);
		    	System.out.println("To shut down the http server you must run this command again and fill in the inputs with viable options");
				System.out.println("Please input the root directory of the HTTP server");
				String dir = inputScanner.nextLine();
				System.out.println("Please input the port number to listen on");
				String port = inputScanner.nextLine();
				System.out.println("Starting server");
				apps.HTTPServer.startServerUpInGUI(dir, port);
				Controller.restart();
		    }
		};
		httpCounter++;
		if(httpServerThread.isAlive()) {
			httpServerThread.stop();
		}
		else {
			httpServerThread.start();
		}
		
		

			httpServerRunning();
			
		
		
		
		
		
		
	}
	public static void HashBruteForce() {
		System.out.println("Will you be using a wordlist? (y)");
		String wordlistornah = inputScanner.nextLine();
		if(wordlistornah.equals("y"))
		{
			List<String> listOfHashes = new ArrayList<String>();
			System.out.println("Please input the number of hashes that you wish to crack");
			String numOfHashes = inputScanner.nextLine();
			int numohash = Integer.parseInt(numOfHashes);
			listOfHashes.add(numOfHashes);
			System.out.println("Please input the type of hash EXACTLY AS SEEN");
			System.out.println("Supported hashes (Default is MD5)\n"
					+ "MD2\n"
					+ "MD5\n"
					+ "SHA-1\n"
					+ "SHA-224\n"
					+ "SHA-256\n"
					+ "SHA-384\n"
					+ "SHA-512/224\n"
					+ "SHA-512/256");
			
			String typeOfHash = inputScanner.nextLine();
			if(!typeOfHash.equals("MD2") && !typeOfHash.equals("MD5") && !typeOfHash.equals("SHA-1") && !typeOfHash.equals("SHA-224") &&
					 !typeOfHash.equals("SHA-256") && !typeOfHash.equals("SHA-384") && !typeOfHash.equals("SHA-512/224") && !typeOfHash.equals("SHA-512/256"))
			{
				typeOfHash = "MD5";
			}
			listOfHashes.add(typeOfHash);
			for(int h = 0; numohash > h; h++)
			{
				
				System.out.println("Input Hash (" + (h+1) + "): ");
				String hash = inputScanner.nextLine();
				listOfHashes.add(hash);
			}
			System.out.println("Please add the exact path to the wordlist file you wish to use (INCLUDING '/home/(user)/':");
			String path = inputScanner.nextLine();
			listOfHashes.add(path);
			
			
			HashBruteForceWordlist.main(listOfHashes.toArray(new String[listOfHashes.size()]));
			restart();
			
		}
		else {
			List<String> listOfHashes = new ArrayList<String>();
			System.out.println("Please input the number of hashes that you wish to crack");
			String numOfHashes = inputScanner.nextLine();
			int numohash = Integer.parseInt(numOfHashes);
			listOfHashes.add(numOfHashes);
			System.out.println("Please input the type of hash EXACTLY AS SEEN");
			System.out.println("Supported hashes (Default is MD5)\n"
					+ "MD2\n"
					+ "MD5\n"
					+ "SHA-1\n"
					+ "SHA-224\n"
					+ "SHA-256\n"
					+ "SHA-384\n"
					+ "SHA-512/224\n"
					+ "SHA-512/256");
			
			String typeOfHash = inputScanner.nextLine();
			if(!typeOfHash.equals("MD2") && !typeOfHash.equals("MD5") && !typeOfHash.equals("SHA-1") && !typeOfHash.equals("SHA-224") &&
					 !typeOfHash.equals("SHA-256") && !typeOfHash.equals("SHA-384") && !typeOfHash.equals("SHA-512/224") && !typeOfHash.equals("SHA-512/256"))
			{
				typeOfHash = "MD5";
			}
			listOfHashes.add(typeOfHash);
			for(int h = 0; numohash > h; h++)
			{
				
				System.out.println("Input Hash (" + (h+1) + "): ");
				String hash = inputScanner.nextLine();
				listOfHashes.add(hash);
			}
			System.out.println("Please enter the characters you would like to test. ");
			System.out.println("Simply type the letter option in lowercase, or type a lowercase y for 'yes'. Enter 1 option per line.");
			System.out.println("");
			System.out.println("AKA Type the letter in the parenthesesis if you want to add them to the char set");
			System.out.println("");
			System.out.println("Lower Case (l)");
			String l = inputScanner.nextLine();
			if(!l.equals("l"))
			{
				l = "none";
			}
			listOfHashes.add(l);
			System.out.println("Upper Case (u)");
			String u = inputScanner.nextLine();
			if(!u.equals("u"))
			{
				u = "none";
			}
			listOfHashes.add(u);
			System.out.println("Numbers (n)");
			String n = inputScanner.nextLine();
			if(!n.equals("n"))
			{
				n = "none";
			}
			listOfHashes.add(n);
			System.out.println("Special Characters (s) [i.e. !@#$% etc]");
			String s = inputScanner.nextLine();
			if(!s.equals("s"))
			{
				s = "none";
			}
			listOfHashes.add(s);
			

			System.out.println("Allow to run in background?(y?)");
			String background = inputScanner.nextLine();
			if(background.equals("y"))
			{
				System.out.println("This feature will be added in the future... sorry, non-verbose mode has started");
			}
			else
			{
				background = "no";
			}
			listOfHashes.add(background);

			//System.out.println("Cracking " + listOfHashes.size() + " Hashes");
			HashBruteForce.main(listOfHashes.toArray(new String[listOfHashes.size()]));
			restart();
		}
		
		
		
		
			

	}
	public static void BenchmarkHashRate()
	{
		System.out.println("Benchmark will test how long it takes for each algorythmn to get to 94^3 hashes or 830,584 guesses");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		List<String> listOfHashes = new ArrayList<String>();
		String numOfHashes = "1";
		int numohash = Integer.parseInt(numOfHashes);
		listOfHashes.add(numOfHashes);
		String typeOfHash = "MD2";
		listOfHashes.add(typeOfHash);
		String hash = "887938f312078f5287d3114d3bb9d2d2";
		listOfHashes.add(hash);
		String l ="l";
		String u ="u";
		String n ="n";
		String s ="s";
		listOfHashes.add(l);
		listOfHashes.add(u);
		listOfHashes.add(n);
		listOfHashes.add(s);
		String background = "y";
		listOfHashes.add(background);
		String benchmark ="y";
		listOfHashes.add(benchmark);
		HashBruteForce.main(listOfHashes.toArray(new String[listOfHashes.size()]));
		listOfHashes.clear();
		System.out.println("");
		System.out.println("");
		
		listOfHashes.add(numOfHashes);
		typeOfHash = "MD5";
		listOfHashes.add(typeOfHash);
		hash = "102c3439d577d1d0d552b03fcae27c5e";
		listOfHashes.add(hash);
		listOfHashes.add(l);
		listOfHashes.add(u);
		listOfHashes.add(n);
		listOfHashes.add(s);
		listOfHashes.add(background);
		listOfHashes.add(benchmark);
		HashBruteForce.main(listOfHashes.toArray(new String[listOfHashes.size()]));
		listOfHashes.clear();
		System.out.println("");
		System.out.println("");
		
		listOfHashes.add(numOfHashes);
		typeOfHash = "SHA-1";
		listOfHashes.add(typeOfHash);
		hash = "40ef8e2f391a7167d3643c402aff5290167914a6";
		listOfHashes.add(hash);
		listOfHashes.add(l);
		listOfHashes.add(u);
		listOfHashes.add(n);
		listOfHashes.add(s);
		listOfHashes.add(background);
		listOfHashes.add(benchmark);
		HashBruteForce.main(listOfHashes.toArray(new String[listOfHashes.size()]));
		listOfHashes.clear();
		System.out.println("");
		System.out.println("");
		
		listOfHashes.add(numOfHashes);
		typeOfHash = "SHA-224";
		listOfHashes.add(typeOfHash);
		hash = "5e0cb235996b2b1c75af0454df59f52dce03a792959ee50ea9927865";
		listOfHashes.add(hash);
		listOfHashes.add(l);
		listOfHashes.add(u);
		listOfHashes.add(n);
		listOfHashes.add(s);
		listOfHashes.add(background);
		listOfHashes.add(benchmark);
		HashBruteForce.main(listOfHashes.toArray(new String[listOfHashes.size()]));
		listOfHashes.clear();
		System.out.println("");
		System.out.println("");
		
		listOfHashes.add(numOfHashes);
		typeOfHash = "SHA-256";
		listOfHashes.add(typeOfHash);
		hash = "a03f2fd631370334952c5db487ce810e6af747de720ed7a05543a4c1204d3998";
		listOfHashes.add(hash);
		listOfHashes.add(l);
		listOfHashes.add(u);
		listOfHashes.add(n);
		listOfHashes.add(s);
		listOfHashes.add(background);
		listOfHashes.add(benchmark);
		HashBruteForce.main(listOfHashes.toArray(new String[listOfHashes.size()]));
		listOfHashes.clear();
		System.out.println("");
		System.out.println("");
		
		
		listOfHashes.add(numOfHashes);
		typeOfHash = "SHA-384";
		listOfHashes.add(typeOfHash);
		hash = "04cc6681b210fca435c5fae5610ae021d72382ac8a3b1d6a366aa8e7c897d2cc60b21f011eeeab8a9263570648e3b309";
		listOfHashes.add(hash);
		listOfHashes.add(l);
		listOfHashes.add(u);
		listOfHashes.add(n);
		listOfHashes.add(s);
		listOfHashes.add(background);
		listOfHashes.add(benchmark);
		HashBruteForce.main(listOfHashes.toArray(new String[listOfHashes.size()]));
		listOfHashes.clear();
		System.out.println("");
		System.out.println("");
		
		listOfHashes.add(numOfHashes);
		typeOfHash = "SHA-512/224";
		listOfHashes.add(typeOfHash);
		hash = "444e291be9148571c9b3f03c94b2482f24d3b15bc96cb3901616117b";
		listOfHashes.add(hash);
		listOfHashes.add(l);
		listOfHashes.add(u);
		listOfHashes.add(n);
		listOfHashes.add(s);
		listOfHashes.add(background);
		listOfHashes.add(benchmark);
		HashBruteForce.main(listOfHashes.toArray(new String[listOfHashes.size()]));
		listOfHashes.clear();
		System.out.println("");
		System.out.println("");
		
		listOfHashes.add(numOfHashes);
		hash = "f0dbd2a766db0c870542c2adadc28cf548d9f3b4be6e5e29fbf0ee03d3b163c5";
		typeOfHash = "SHA-512/256";
		listOfHashes.add(typeOfHash);
		listOfHashes.add(hash);
		listOfHashes.add(l);
		listOfHashes.add(u);
		listOfHashes.add(n);
		listOfHashes.add(s);
		listOfHashes.add(background);
		listOfHashes.add(benchmark);
		HashBruteForce.main(listOfHashes.toArray(new String[listOfHashes.size()]));
		listOfHashes.clear();
		System.out.println("");
		System.out.println("");
		
		
		
		
		restart();
		
		
	}
	public static void CreateReverseTCPListener() {
		
		
		List<String> listOfArgs = new ArrayList<String>();
		
		BindShellModified.main(listOfArgs.toArray(new String[listOfArgs.size()]));
		
	}
	public static void CreateReverseTCPConnection() {
		
		
		List<String> listOfArgs = new ArrayList<String>();
		
		ReverseShellModified.main(listOfArgs.toArray(new String[listOfArgs.size()]));
		
	}
	


}
