package testFiles;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import view.JFX;

public class PortScannerFour
{
	
	public static String WebIPPortScannerTest(String ipOfSearch)
	{
		StringBuilder str = new StringBuilder("Open Ports on " + ipOfSearch + ":");
		
	for( int current = 78; current <= 81; current++ ) {
		try {
			Socket s = new Socket();
			s.connect( new InetSocketAddress( ipOfSearch, current )); //attempt a connection
			s.close();
			
			//System.out.println(( "Open port: " + current + System.lineSeparator() ));
			str.append(current + System.lineSeparator());
		}
		catch( IOException ioe ) { //connection failed
			System.out.println("connection failed");
			}
		}
	
	String stringFinal = str.toString();
	return stringFinal;
	
	
	
	
	}
	
	
	}
	




	

