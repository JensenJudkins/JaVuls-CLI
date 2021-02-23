package applications;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
 
/**
 * @author Crunchify.com
 * Problem: Sometimes InetAddress.isReachable() gives false result. We have implemented the same Reachable check using Socket. It works almost 100% of the time. Comparison added.
 * Version: 1.0
 * 
 */
 
 
public class CrunchifyInetAddressIsReachable {
	static String host = "www.judkinscustomtaxidermy.com";
 
	public static void main(String[] args) {
 
		// check ping using default Java Utility
		pingCheckbyInetAddressisReachable();
		
		// check ping using modified Crunchify Utility
		pingCheckbyCrunchifyisReachable();
	}
 
	private static void pingCheckbyInetAddressisReachable() {
 
		try {
			InetAddress crunchifyAddr = InetAddress.getByName(host);
			boolean reachable = crunchifyAddr.isReachable(2000);
 
			if (reachable) {
				System.out.println("InetAddress.isReachable(timeout) Result ==> Ping successful for host: " + host);
			} else {
				System.out.println("InetAddress.isReachable(timeout) Result ==> Ping failed for host: " + host);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
 
	}
 
	private static void pingCheckbyCrunchifyisReachable() {
		try {
			crunchifyAddressReachable(host, 80, 2000);
 
			System.out.println("\nOverloaded isReachable(host, port, timeout) Result ==> Ping successful for host: " + host);
		} catch (Exception e) {
			System.out.println("\nOverloaded isReachable(host, port, timeout) Result ==> Ping failed for host: " + host);
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