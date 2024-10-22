package apps.EncryptionDecryption;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import controller.Controller;


public class HashBruteForceWordlist
{
	static Scanner inputScanner = new Scanner(System.in);
	static MessageDigest md;
	
	char min_char_value;
	char max_char_value;
	
	char[] guess;
	
	int max_num_chars;

	
	public HashBruteForceWordlist(String hashType, String l, String u, String n, String s) throws Exception
	{
		
		
		md = MessageDigest.getInstance(hashType);
		//TimeUnit.SECONDS.sleep(30);
		
		guess = null;
	}
	
	public static String crackWithWordlist(String typeOfHash, String hash, String path)
	{
		boolean done = false;
		String guess_hash;
		String word = null;
		try {
			md = MessageDigest.getInstance(typeOfHash);
		} catch (NoSuchAlgorithmException e1) {
			System.out.println("That Algorythmn does nto exist or is not available :(");
			Controller.restart();
			//e1.printStackTrace();
		}
		try {
			File wordlistFile = new File(path);
			Scanner fileReader = new Scanner(wordlistFile);
			Scanner fileLineCounter = new Scanner(wordlistFile);
			int linesInWordlist = 0;
			while(fileLineCounter.hasNextLine())
			{
				fileLineCounter.nextLine();
				linesInWordlist++;
			}
			
			
			System.out.println(linesInWordlist + " words in wordlist");
			
			System.out.println("Please press enter to continue:");
			
			int LinesPassed = 0;
			while(fileReader.hasNextLine() && !done)
			{
				
				LinesPassed++;
				word = fileReader.nextLine();
				//System.out.println(word);
				
				md.reset();
				md.update(new String(word).getBytes());
				guess_hash = hashToString(md.digest());
				System.out.println(word);
				if(hash.equals(guess_hash))
				{
					System.out.println(linesInWordlist + " words in wordlist");
					System.out.println(LinesPassed + " words used");
					int linesremaining = linesInWordlist - LinesPassed;
					System.out.println(linesremaining + " words untested");
					done = true;
					break;
				}
			}
			fileReader.close();
			fileLineCounter.close();
		} catch(FileNotFoundException e) {
			System.out.println("An error occurred.");
		      e.printStackTrace();
		}
		
		return new String(word);
	}
	

	
	
	
	protected static String hashToString(byte[] hash)
	{
		StringBuffer sb = new StringBuffer();
		
		for(int i = 0; i < hash.length; i++)
		{
			sb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}
	
	
	
	public static String hashcracked(String answer, String time)
	{
		String returnMe = ("Answer: " + answer + "\n" + "Processing Time: " + time);
		
		return  returnMe;
	}
	
	public static void main(String args[])
	{
		
		int numOfHashes = Integer.parseInt(args[0]);
		String typeOfHash = args[1];
		if(numOfHashes > 0)
		{
			for(int hashedInput = 1; numOfHashes >= hashedInput; hashedInput++)
			{
				try
				{
					if(args[hashedInput].equals("")) {
						System.out.println("Argument "+ (args[hashedInput]) + " is blank, skipping");
						hashedInput++;
					}
					//int numberOfArgs = args.length;
					//int startingNumForQuery = numberOfArgs - 3;
					
		
		
					long start;
					long end;
					String answer;
	
					start = System.nanoTime();
		
		
		
					answer = crackWithWordlist(typeOfHash, args[hashedInput+1], args[args.length-1]);
		
		
					end = System.nanoTime();
					System.out.println("Hashing algorythmn: "+ typeOfHash);
					System.out.println("Answer: " + answer);
					System.out.println("Processing Time: " + ((end - start)/1000000000 + " Seconds"));

					
				}
				catch(Exception e)
				{
					System.out.println("Exception: " + e.toString());
				}
		
		
		
		
		
		
			
			}
		}
	}
}
