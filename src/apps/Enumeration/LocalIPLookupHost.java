package apps.Enumeration;

//import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

import controller.Controller;

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

		private static String getLocalSubnet() throws UnknownHostException
		{
			//InetAddress localsubnet = InetAddress.getLocalHost();

			String yourIp = LocalIPLookupHost.viewMyIP().toString();

                
                String[] seperated = yourIp.split("\\.");
                System.out.println(seperated[0]);
                System.out.println(seperated[1]);
                System.out.println(seperated[2]);
                System.out.println(seperated[3]);
				String localsubnet = seperated[0] + "." + seperated[1] + "." + seperated[2] + ".0/24";



			
			//Insert method to delete right to left until you hit the first . char then append a 1/24 then return it for the arp scanner/device discovery tools
			return localsubnet;
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
			getIpAddress(null);
			getLocalSubnet();

			Controller.restart();
			
		}
		
	    
}
