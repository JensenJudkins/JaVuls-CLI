package testApplications;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.lang.Integer;
/**
 * @author Crunchify.com Problem: Sometimes InetAddress.isReachable() gives
 *         false result. We have implemented the same Reachable check using
 *         Socket. It works almost 100% of the time. Comparison added. Version:
 *         1.0
 * 
 */

public class CrunchifyInetAddressIsReachable {
	
/*FOR THIS PROGRAM TO BE CUSTOMIZABLE YOU NEED ARGS IN THIS ORDER
TIMEOUT LENGTH, HOST OR WEBSITE, PORT NUMBER, NUMBER OF PINGS
DEFAULTS ARE AS LISTED
2000 (200MS), WWW.GOOGLE.COM, 80, 5
*/
	public static void main(String[] args) {
		//This will make default timeout 2000 if no args[0] is given
		int timeout= (args.length > 0) ? Integer.parseInt(args[0]) : 2000;
		int port= (args.length > 2) ? Integer.parseInt(args[0]) : 80;
		int pings= (args.length > 3) ? Integer.parseInt(args[0]) : 5;
		String host;
		if(args.length > 1)
		{host = args[1];}
		else{
			host = "www.google.com";
		}
		
		// check ping using default Java Utility
		pingCheckbyInetAddressisReachable(timeout, host, port, pings);
		
		// check ping using modified Crunchify Utility
		pingCheckbyCrunchifyisReachable(timeout, host, port, pings);
	}

	private static void pingCheckbyInetAddressisReachable(int timeout, String host, int port, int pings) {
		InetAddress crunchifyAddr;
		try {
			
			crunchifyAddr = InetAddress.getByName(host);
			
			
			System.out.println("Checking if " + host + " is reachable via InetAddress on port " + port);
			System.out.println("Sever IP Address: " + InetAddress.getByName(host));
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		for(int x = 1; x<pings+1; x++)
		{
			try {
				crunchifyAddr = InetAddress.getByName(host);
				boolean reachable = crunchifyAddr.isReachable(timeout);
				if (reachable) {
					System.out.println("Ping "+x+" successful");
				} else {
					System.out.println("Ping "+x+" failed");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
 
	}
 
	private static void pingCheckbyCrunchifyisReachable(int timeout, String host, int port, int pings) {
		System.out.println("Checking if " + host + " is reachable via Crunchify on port " + port);
		for(int x =1; x<pings+1; x++)
		{
			try {
				crunchifyAddressReachable(host, port, timeout);
	
				System.out.println("Ping "+x+" successful ");
			} catch (Exception e) {
				System.out.println("Ping "+x+" failed ");
			}
		}
 
	}
 
	/*
	 * Overriding default InetAddress.isReachable() method to add 2 more arguments port and timeout value
	 * 
	 * Address: www.google.com 
	 * port: 80 or 443 
	 * timeout: 2000 (in milliseconds)
	 */
	private static boolean crunchifyAddressReachable(String address, int port, int timeout) {
		try {
 
			try (Socket crunchifySocket = new Socket()) {
				// Connects this socket to the server with a specified timeout value.
				crunchifySocket.connect(new InetSocketAddress(address, port), timeout);
			}
			// Return true if connection successful
			return true;
		} catch (IOException exception) {
			exception.printStackTrace();
 
			// Return false if connection fails
			return false;
		}
	}
}