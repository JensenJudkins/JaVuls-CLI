package portListener;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A simple listener for testing TCP connections
 *
 * Usage: java -jar TcpListener
 */
public class TcpListener {

	public static void main(final String args[]) throws IOException {
/**
		if (args.length == 0) {
			printUsage();
			return;
		}

		final List<String> ports = Arrays.asList(args);

		final ExecutorService executor = Executors.newCachedThreadPool();

		for (final String p : ports) {

			final int port = Integer.parseInt(p);

			executor.execute(new PortListenerTask(port));
	}
*/
		
		runMe(9999);
		
	}

	private static void printUsage() {
		System.out.println("Usage: java -jar TcpListener PORT");
	}
	
	public static void runMe(int port) throws IOException {


		final ExecutorService executor = Executors.newCachedThreadPool();


			executor.execute(new PortListenerTask(port));
	}

}
