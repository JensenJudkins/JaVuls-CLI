package applications;

import java.io.BufferedReader;
import java.io.InputStreamReader;
 
import java.net.*; 
//import java.*; 

public class PublicIPLookupHost {


	  
	 
	    public static void main(String args[]) 
	        throws UnknownHostException 
	    { 
	      yourPublicIP();
	        
	    } 
	    
	    public String websiteIP(String s) throws UnknownHostException
	    {
	    	try { 
	            // Fetch IP address by getByName() 
	            InetAddress ip = InetAddress.getByName(new URL(s) 
	                                                       .getHost()); 
	  
	            // Print the IP address 
	            System.out.println("Public IP Address of: " + ip); 
	            String returnMe = "Public IP Address of: " + ip;
	            return returnMe;
	        } 
	        catch (MalformedURLException e) { 
	            // It means the URL is invalid 
	            System.out.println("Invalid URL"); 
	            String returnMe = "Invalid input";
	            return returnMe;
	        } 
	    }
	    
	    public static String yourPublicIP() throws UnknownHostException
	    {
	    	String systemipaddress = ""; 
	        try { 
	            URL url_name = new URL("http://bot.whatismyipaddress.com"); 
	  
	            BufferedReader sc = new BufferedReader( 
	                new InputStreamReader(url_name.openStream())); 
	  
	            // reads system IPAddress 
	            systemipaddress = sc.readLine().trim(); 
	        } 
	        catch (Exception e) { 
	            systemipaddress = "Cannot Execute Properly"; 
	        } 
	        // Print IP address 
	        System.out.println("Public IP Address: "
	                           + systemipaddress + "\n");
	        String returnMe = "Public IP Address: "
                    + systemipaddress + "\n";
	        return returnMe;
	    } 
	    }
	



