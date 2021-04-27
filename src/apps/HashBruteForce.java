package apps;

import java.security.MessageDigest;


public class HashBruteForce
{
	MessageDigest md;
	
	char min_char_value;
	char max_char_value;
	
	char[] guess;
	
	int max_num_chars;

	
	public HashBruteForce(String hashType, String l, String u, String n, String s) throws Exception
	{
		if(!l.contentEquals("none"))
		{
			min_char_value = 97;
		}
		if(!u.contentEquals("none"))
		{
			min_char_value = 65;
		}
		if(!n.contentEquals("none"))
		{
			min_char_value = 48;
		}
		if(!s.contentEquals("none"))
		{
			min_char_value = 32;
		}
		
		
		
		
		
		if(!s.contentEquals("none"))
		{
			max_char_value = 47;
		}
		if(!n.contentEquals("none"))
		{
			max_char_value = 57;
		}
		if(!u.contentEquals("none"))
		{
			max_char_value = 90;
		}
		if(!l.contentEquals("none"))
		{
			max_char_value = 126;
		}
		
		
		
		
		
		
		max_num_chars = 10;
		
		md = MessageDigest.getInstance(hashType);
		//TimeUnit.SECONDS.sleep(30);
		
		guess = null;
	}
	
	public String crack(String hash)
	{
		boolean done = false;
		String guess_hash;
			
		for(int num_chars = 0; num_chars < max_num_chars && !done; num_chars++)
		{
			// Initialize guess at the start of each interation
			guess = new char[num_chars];
			for(int x = 0; x < num_chars; x++)
			{
				guess[x] = min_char_value;
			}
			
			while(canIncrementGuess() && !done)
			{
				incrementGuess();
				md.reset();
				md.update(new String(guess).getBytes());
				guess_hash = hashToString(md.digest());
				System.out.println(guess);
				if(hash.equals(guess_hash))
				{
					done = true;
				}
			}
		}
		return new String(guess);
	}
	
	public String crackBG(String hash, String type)
	{
		System.out.println("Hashing Algorythmn: "+ type);
		System.out.println("Cracking: " + hash);
		boolean done = false;
		String guess_hash;
			
		for(int num_chars = 0; num_chars < max_num_chars && !done; num_chars++)
		{
			// Initialize guess at the start of each iteration
			guess = new char[num_chars];
			for(int x = 0; x < num_chars; x++)
			{
				guess[x] = min_char_value;
			}
			
			while(canIncrementGuess() && !done)
			{
				incrementGuess();
				md.reset();
				md.update(new String(guess).getBytes());
				guess_hash = hashToString(md.digest());
				//System.out.println(guess);
				if(hash.equals(guess_hash))
				{
					done = true;
				}
			}
		}
		return new String(guess);
	}
	
	protected boolean canIncrementGuess()
	{
		boolean canIncrement = false;
		
		for(int x=0; x < guess.length; x++)
		{
			if(guess[x] < max_char_value) canIncrement = true;
		}
		return canIncrement;
	}
	
	protected void incrementGuess()
	{
		boolean incremented = false;

		for(int x = (guess.length - 1);x >= 0 && !incremented; x--)
		{	
			if(guess[x] < max_char_value)
			{
				guess[x]++;
				if(x < (guess.length-1))
				{
					guess[x+1] = min_char_value;
				}
				incremented = true;
			}
		} 
	}
	
	protected String hashToString(byte[] hash)
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
		//System.out.println(args.length);
		//for (int i = 0; args.length > i; i++)
		//{
		//	System.out.println(args[i]);
		//}
		
		int numOfHashes = Integer.parseInt(args[0]);
		String typeOfHash = args[1];
		if(numOfHashes > 0)
		{
			for(int h = 1; numOfHashes >= h; h++)
			{
				try
				{
					if(args[h].equals("")) {
						//System.out.println("Argument "+ (h) + " is blank, skipping");
						h++;
					}
					int numberOfArgs = args.length;
					int startingNumForQuery = numberOfArgs - 7;
					//System.out.print(startingNumForQuery +" number of args " +numberOfArgs);
					for(int k=startingNumForQuery-1; k < startingNumForQuery; k++)
					{
						if (args[k].equals(""))
						{
							args[startingNumForQuery] = "n";
						}
					}
					//System.out.println(args[1]+args[startingNumForQuery+1]+ args[startingNumForQuery+2]+ args[startingNumForQuery+3]+ args[startingNumForQuery+4]);
					HashBruteForce bc = new HashBruteForce(args[1],args[startingNumForQuery+1], args[startingNumForQuery+2], args[startingNumForQuery+3], args[startingNumForQuery+4]);
					long start;
					long end;
					String answer;
				
					start = System.nanoTime();
					//System.out.println(args[startingNumForQuery+6]);
					if(args[startingNumForQuery+6].equals("y"))
					{
						answer = bc.crackBG(args[h+1], args[1]);
					}
					else
					{
						answer = bc.crack(args[h+1]);
					}
					
					end = System.nanoTime();
				
					
					if(args[startingNumForQuery+6].equals("y"))
						{
						//System.out.println("Hashing algorythmn: "+ args[1]);
						System.out.println("Processing Time: " + ((end - start)/1000000000)+" Seconds, " + ((end - start)/1000000)+" Milliseconds (10^-3 thousandth), " + ((end - start)/1000)+" Microseconds (10^-6 millienth), " + ((end - start)/1)+" Nanoseconds (10^-9 billienth)");
						}
					else {
						System.out.println("Hashing algorythmn: "+ args[startingNumForQuery+4]);
						System.out.println("Answer: " + answer);
						System.out.println("Processing Time: " + ((end - start)/1000000000 + " Seconds"));	
					}
					
				}
				catch(Exception e)
				{
					System.out.println("Exception: " + e.toString());
				}
			}
		}
	}
}
