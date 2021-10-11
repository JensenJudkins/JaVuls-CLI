package backend;

import java.util.regex.*;
import java.util.Scanner;
import java.io.*;


public class Grep 
{
	 public static void main (String [] args) throws IOException
	  {
		 //make sure there is a space in front and then at least several spaces afterwards 
		 String testme = " "+ "22" +"   ";
		 findMe(testme);
	  }
	 
	 public static String sendmethestuff(String thestuff) throws IOException
	 {
			 //make sure there is a space in front and then at least several spaces afterwards 
			 String testme = " "+ thestuff +"   ";
			 //findMe(testme);
			 String returnme = findMe(testme);
			 return returnme;
	 }
	 
	
	
 public static String findMe(String find) throws FileNotFoundException {
    String line;
    int lines = 0;
    //int matches = 0;

    // Check the command line, prompt user if needed   
    
    String filename = "portservices.txt";
   // System.out.print  ("Find " + find);
   // System.out.println(" in "  + filename);
    String matchString = "No matches for port " + find;

    // Create a Scanner for the file
    File in = new File(filename);
    Scanner scan = new Scanner( in );
    
    // Get a regular expression from the command line  
    Pattern pat = Pattern.compile( find );

    // Match lines against the Regular Expression
    while ( scan.hasNextLine() ) 
    {
      line = scan.nextLine();
      lines++;
      
      // Check if the current line contains the pattern
      Matcher match = pat.matcher( line );
      if (match.find()) 
      {
        System.out.println ("" + lines + ": " + line);
        matchString = "" + lines + ": " + line;
        //matches++;
      }
    }
    scan.close();
    return matchString;
    
    //System.out.println( lines + " lines, " + matches + " matches" );
  }
}