package testApplications;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import controller.Controller;

public class PingURL {

	public static String PingURL(String url) throws UnknownHostException, MalformedURLException
	{
	//String url = "http://judkinscustomtaxidermy.com";
    
    
		HttpURLConnection connection = null;

    try {

    	InetAddress address = InetAddress.getByName(new URL(url).getHost());
        String ip = address.getHostAddress();
        System.out.println(ip);
    	
    	
        URL u = new URL(url);
        connection = (HttpURLConnection) u.openConnection();
        connection.setRequestMethod("HEAD");
        int code = connection.getResponseCode();
        System.out.println(connection.getURL());
        System.out.println(connection.getRequestProperties());
        String connectionProperties = connection.getRequestProperties().toString();
        System.out.println("" + code);
        
        
        String sendMe = url + " has an IP address of " + ip + "\n" +"Connection properties: " + "\n" + connectionProperties + "\n" + "Code Returned:"+ code;
        return sendMe;
        // You can determine on HTTP return code received. 200 is success.

    } catch (MalformedURLException e) {

        System.out.println("Malformed URL, dont work cuh :(");
    	
       e.printStackTrace();
       Controller.restart();
        return "Connection has failed to " + url + "\n" + "MalformedURLException Error";
        
    } catch (IOException e) {

        System.out.println("IOException broski :(");

       e.printStackTrace();
       Controller.restart();
        return "Connection has failed to " + url + "\n" + "IOException Error";

    } finally {

        if (connection != null) {

            connection.disconnect();

        }

    }
    
    
	}

	
}
