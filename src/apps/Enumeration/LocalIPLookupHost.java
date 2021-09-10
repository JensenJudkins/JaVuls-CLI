package apps.Enumeration;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class LocalIPLookupHost {
	
	  
	    public static String getLocalIP() throws UnknownHostException
	    {
	        InetAddress inetAddress = InetAddress.getLocalHost();
	    	System.out.println("IP Address:- " + inetAddress.getHostAddress());
	        System.out.println("Host Name:- " + inetAddress.getHostName());
	        return inetAddress.toString();
	    }
	    
	    public static String viewMyIP() throws UnknownHostException
	    {
	    	InetAddress inetAddress = InetAddress.getLocalHost();
	    	String ipAddress = inetAddress.getHostAddress();

	        //return inetAddress.toString();
	        return ipAddress;
	    }
	    
	    
	    
	    private static String getIpAddress(byte[] rawBytes) {
	        int i = 4;
	        StringBuilder ipAddress = new StringBuilder();
	        for (byte raw : rawBytes) {
	            ipAddress.append(raw & 0xFF);
	            if (--i > 0) {
	                ipAddress.append(".");
	            }
	        }
	        return ipAddress.toString();
	    }
	    

	    
	    public static void main (String[] args) throws UnknownHostException {
			getLocalIP();
			try {
				controller.Controller.restart();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	    
}
