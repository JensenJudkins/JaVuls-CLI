package applications;
import java.io.IOException;
import java.net.InetSocketAddress;
    import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
    import java.util.List;
    import java.util.Scanner;
    import java.util.concurrent.Callable;
    import java.util.concurrent.ExecutionException;
    import java.util.concurrent.ExecutorService;
    import java.util.concurrent.Executors;
    import java.util.concurrent.Future;
    import java.util.concurrent.TimeUnit;

    public class PortScanner {

    public static void main(final String... args) throws InterruptedException, ExecutionException 
    {
        final ExecutorService es = Executors.newCachedThreadPool();
        System.out.print("Please input the ip address you would like to scan for open ports: ");
        Scanner inputScanner = new Scanner(System.in);
        final String ip = inputScanner.nextLine();
        final int timeout = 200;
        final List<Future<ScanResult>> futures = new ArrayList<>();
        for (int port = 1; port <= 65535; port++) {
            // for (int port = 1; port <= 80; port++) {
            futures.add(portIsOpen(es, ip, port, timeout));
        }
        es.awaitTermination(200L, TimeUnit.MILLISECONDS);
        int openPorts = 0;
        for (final Future<ScanResult> f : futures) {
            if (f.get().isOpen()) {
                openPorts++;
                System.out.println(f.get().getPort());
            }
        }
        System.out.println("There are " + openPorts + " open ports on host " + ip + " (probed with a timeout of "
        + timeout + "ms)");
    }
    
    public static String localPortScan(String threads) throws UnknownHostException, InterruptedException, ExecutionException
    {
    	int threadsAllowed = Integer.parseInt(threads);
    	
    	if(threadsAllowed==0)
    	{
    	final ExecutorService es = Executors.newCachedThreadPool();
    	String ip = YourIPLookup.viewMyIP();
        final int timeout = 200;
        final List<Future<ScanResult>> futures = new ArrayList<>();
        for (int port = 1; port <= 65535; port++) {
            // for (int port = 1; port <= 80; port++) {
            futures.add(portIsOpen(es, ip, port, timeout));
        }
        es.awaitTermination(200L, TimeUnit.MILLISECONDS);
        int openPorts = 0;
        ArrayList<String> openPortsToPrint = new ArrayList<String>();
        
        
        for (final Future<ScanResult> f : futures) {
            if (f.get().isOpen()) {
                openPorts++;
                System.out.println(f.get().getPort());
                int theePort = f.get().getPort();
                String print = String.valueOf(theePort);
                openPortsToPrint.add(print);
            }
        }
        
        String r = openPortsToPrint.toString();
        System.out.println("There are " + openPorts + " open ports on host " + ip + " (probed with a timeout of "
        + timeout + "ms)");
        
        
        String openPortsOnLocal = "There are " + openPorts + " open ports on host " + ip + " (probed with a timeout of "
                + timeout + "ms) OPEN PORTS: " + r;
        
    	
        es.shutdown();
        return openPortsOnLocal;
    	}
    	else
    	{
    		final ExecutorService es = Executors.newFixedThreadPool(threadsAllowed);
        	String ip = YourIPLookup.viewMyIP();
            final int timeout = 200;
            final List<Future<ScanResult>> futures = new ArrayList<>();
            for (int port = 1; port <= 65535; port++) {
                // for (int port = 1; port <= 80; port++) {
                futures.add(portIsOpen(es, ip, port, timeout));
            }
            es.awaitTermination(200L, TimeUnit.MILLISECONDS);
            int openPorts = 0;
            ArrayList<String> openPortsToPrint = new ArrayList<String>();
            
            
            for (final Future<ScanResult> f : futures) {
                if (f.get().isOpen()) {
                    openPorts++;
                    System.out.println(f.get().getPort());
                    int theePort = f.get().getPort();
                    String print = String.valueOf(theePort);
                    openPortsToPrint.add(print);
                }
            }
            
            String r = openPortsToPrint.toString();
            System.out.println("There are " + openPorts + " open ports on host " + ip + " (probed with a timeout of "
            + timeout + "ms)");
            
            
            String openPortsOnLocal = "There are " + openPorts + " open ports on host " + ip + " (probed with a timeout of "
                    + timeout + "ms) OPEN PORTS: " + r;
            
        	
            es.shutdown();
            return openPortsOnLocal;
            
    	}
    
        
    }
    
    public static String foreignPortScan(String ipOfSearch, String threads) throws UnknownHostException, InterruptedException, ExecutionException
    {
    	
    	
    	
    	
    	
    	int threadsAllowed = Integer.parseInt(threads);
    	
    	if(threadsAllowed == 0)
    	{
    	final ExecutorService es = Executors.newCachedThreadPool();
    	String ip = ipOfSearch;
        final int timeout = 200;
        final List<Future<ScanResult>> futures = new ArrayList<>();
        for (int port = 1; port <= 65535; port++) {
            // for (int port = 1; port <= 80; port++) {
            futures.add(portIsOpen(es, ip, port, timeout));
        }
        es.awaitTermination(200L, TimeUnit.MILLISECONDS);
        int openPorts = 0;
        ArrayList<String> openPortsToPrint = new ArrayList<String>();
        
        
        for (final Future<ScanResult> f : futures) {
            if (f.get().isOpen()) {
                openPorts++;
                System.out.println(f.get().getPort());
                int theePort = f.get().getPort();
                String print = String.valueOf(theePort);
                openPortsToPrint.add(print);
                
               
                
                
                
            }
        }
        
        String r = openPortsToPrint.toString();
        System.out.println("There are " + openPorts + " open ports on host " + ip + " (probed with a timeout of "
        + timeout + "ms)");
        
        
        String openPortsOnLocal = "There are " + openPorts + " open ports on host " + ip + " (probed with a timeout of "
                + timeout + "ms) OPEN PORTS: " + r;
        
       
        es.shutdown();
        return openPortsOnLocal;
    	}
    	else
    	{
    		final ExecutorService es = Executors.newFixedThreadPool(threadsAllowed);
        	String ip = ipOfSearch;
            final int timeout = 200;
            final List<Future<ScanResult>> futures = new ArrayList<>();
            for (int port = 1; port <= 65535; port++) {
                // for (int port = 1; port <= 80; port++) {
                futures.add(portIsOpen(es, ip, port, timeout));
            }
            es.awaitTermination(200L, TimeUnit.MILLISECONDS);
            int openPorts = 0;
            ArrayList<String> openPortsToPrint = new ArrayList<String>();
            
            
            for (final Future<ScanResult> f : futures) {
                if (f.get().isOpen()) {
                    openPorts++;
                    System.out.println(f.get().getPort());
                    int theePort = f.get().getPort();
                    String print = String.valueOf(theePort);
                    openPortsToPrint.add(print);
                    
                   
                    
                    
                    
                }
            }
            
            String r = openPortsToPrint.toString();
            System.out.println("There are " + openPorts + " open ports on host " + ip + " (probed with a timeout of "
            + timeout + "ms)");
            
            
            String openPortsOnLocal = "There are " + openPorts + " open ports on host " + ip + " (probed with a timeout of "
                    + timeout + "ms) OPEN PORTS: " + r;
            
           
            es.shutdown();
            return openPortsOnLocal;
    	}
        
        
    }



    public static Future<ScanResult> portIsOpen(final ExecutorService es, final String ip, final int port,
    final int timeout) 
    {
        return es.submit(new Callable<ScanResult>() {
            @Override
            public ScanResult call() {
                try {
                	Socket socket = new Socket();
                    socket.connect(new InetSocketAddress(ip, port), timeout);
                    socket.close();
                    return new ScanResult(port, true);
                } catch (Exception ex) {
                    return new ScanResult(port, false);
                }
            }
        });
    }

    public static class ScanResult {
    	private int port;

    	private boolean isOpen;

    	public ScanResult(int port, boolean isOpen) 
    	{
    		super();
    		this.port = port;
    		this.isOpen = isOpen;
    	}

    	public int getPort() 
    	{
    		return port;
    	}

    	public void setPort(int port) 
    	{
    		this.port = port;
    	}

    	public boolean isOpen() 
    	{
    		return isOpen;
    	}

    	public void setOpen(boolean isOpen) 
    	{
    		this.isOpen = isOpen;
    	}

	    }
	    
}
                