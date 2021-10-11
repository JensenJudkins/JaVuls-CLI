package archive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class testMatcher {
	
	
	
	public static void testMe(String findMe) throws FileNotFoundException
	{
	    String line;
	    int lines = 0;
	    //int matches = 0;
		String filename = "ServicesAndPorts.txt";
		System.out.print  ("Find " + findMe);
	    System.out.println(" in "  + filename);

		File in = new File(filename);
	    Scanner scan = new Scanner( in );
	    while ( scan.hasNextLine() ) 
	    {
	      line = scan.nextLine();
	      lines++;
	      
	      // Check if the current line contains the pattern
	      //Matcher match = pat.matcher( line );
	      
	      if (isContain(line, findMe)) 
	      {
	        System.out.println ("" + lines + ": " + line);
	        //matches++;
	      }
	    }
	    scan.close();
	   // System.out.println( lines + " lines, " + matches + " matches" );
	  }
	
	
public static void main(String[] args) throws FileNotFoundException{

    String source1="search engines";
    String source2="search engine";
    String subterm_1 = "engines";
    String subterm_2 = "engine";

    System.out.println(isContain(source1,subterm_1));
    System.out.println(isContain(source2,subterm_1));
    System.out.println(isContain(source1,subterm_2));
    System.out.println(isContain(source2,subterm_2));
    //String testWord = "22         ";
    //Basically if the ports are less than 3 characters long they will need 9 spaces after it
    String testWord = " 21    ";
    //String testWord = "21         ";
    testMe(testWord);

}

    private static boolean isContain(String source, String subItem){
         String pattern = "\\b"+subItem+"\\b";
         Pattern p=Pattern.compile(pattern);
         Matcher m=p.matcher(source);
         return m.find();
    }

}