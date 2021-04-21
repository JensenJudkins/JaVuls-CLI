package apps;

import java.security.MessageDigest;

import javax.swing.JOptionPane;

public class MD5BruteCrackBG
{
	MessageDigest md;
	
	char min_char_value;
	char max_char_value;
	
	char[] guess;
	
	int max_num_chars;

	
	public MD5BruteCrackBG(String l, String u, String n, String s) throws Exception
	{
		if(!l.contentEquals("none"))
		{
			min_char_value = 97;
		}
		else if(!u.contentEquals("none"))
		{
			min_char_value = 65;
		}
		else if(!n.contentEquals("none"))
		{
			min_char_value = 48;
		}
		else if(!s.contentEquals("none"))
		{
			min_char_value = 32;
		}
		else {
			min_char_value = 48;
		}
		
		
		
		
		if(!s.contentEquals("none"))
		{
			max_char_value = 47;
		}
		else if(!n.contentEquals("none"))
		{
			max_char_value = 57;
		}
		else if(!u.contentEquals("none"))
		{
			max_char_value = 90;
		}
		else if(!l.contentEquals("none"))
		{
			max_char_value = 126;
		}
		else {
			max_char_value = 126;
		}
		
		
		
		
		
		max_num_chars = 10;
		
		md = MessageDigest.getInstance("MD5");
		
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
	
	public static String test(String testword)
	{
		try
		{
			MD5BruteCrackBG bc = new MD5BruteCrackBG(testword, testword, testword, testword);
			long start;
			long end;
			String answer;
		
			start = System.nanoTime();
			answer = bc.crack(testword);
			end = System.nanoTime();
			
			long timeTogether = (end - start)/1000000000;
		
			System.out.println("Answer: " + answer);
			System.out.println("Processing Time: " + ((end - start)/1000000000));
			String time = Long.toString(timeTogether);
			JOptionPane.showMessageDialog(null, hashcracked(answer, time));
			return answer;
		}
		catch(Exception e)
		{
			System.out.println("Exception: " + e.toString());
			return "somethingfailed";
		}
	}
	
	public static String hashcracked(String answer, String time)
	{
		String returnMe = ("Answer: " + answer + "\n" + "Processing Time: " + time);
		
		return  returnMe;
	}
	
	public static void main(String args[])
	{
		int numOfHashes = Integer.parseInt(args[0]);
		if(numOfHashes > 0)
		{
			for(int h = 1; numOfHashes >= h; h++)
			{
				try
				{
					if(args[h].equals("")) {
						System.out.println("Argument "+ h + " is blank, skipping");
						h++;
					}
					int numberOfArgs = args.length;
					int startingNumForQuery = numberOfArgs - 4;
					System.out.print(startingNumForQuery +" number of args " +numberOfArgs);
					for(int k=startingNumForQuery-1; k < startingNumForQuery; k++)
					{
						if (args[k].equals(""))
						{
							args[startingNumForQuery] = "n";
						}
					}
					MD5BruteCrackBG bc = new MD5BruteCrackBG(args[startingNumForQuery], args[startingNumForQuery+1], args[startingNumForQuery+2], args[startingNumForQuery+3]);
					long start;
					long end;
					String answer;
				
					start = System.nanoTime();
					answer = bc.crack(args[h]);
					end = System.nanoTime();
				
					System.out.println("Answer: " + answer);
					System.out.println("Processing Time: " + ((end - start)/1000000000));
				}
				catch(Exception e)
				{
					System.out.println("Exception: " + e.toString());
				}
			}
		}
	}
}
