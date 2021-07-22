package apps.ReverseShell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import controller.Controller;

/**
 * Listen on a port and print whatever it receives
 */
public class PortListenerTask implements Runnable {

	private final int port;
	private final ServerSocket server;
	private Socket client;

	public PortListenerTask(final int port) throws IOException {
		this.port = port;

		this.server = new ServerSocket(port);
		log("Listening on port: " + port);

	}
	@Override
	public void run() {

		try {
			this.client = this.server.accept(); // will block until receives connection
		} catch (final IOException e) {
			err("Error connecting to client: " + e);
			// should probably disconnect here
			return;
		}

		log("Client connected from " + this.client.getInetAddress());

		while (true) {
			try {
				// read lines from the client
				final BufferedReader reader = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
				String line;
				while ((line = reader.readLine()) != null) {
					log(line);
				}

				disconnect();
				Controller.restart();

			} catch (final IOException e) {
				err("Error reading from client: " + e);
			}
		}

	}



	/**
	 * Called when disconnecting
	 *
	 * @throws IOException
	 */
	private void disconnect() throws IOException {
		log("Client disconnected");
		this.client.close();
		Controller.restart();
	}

	/**
	 * Write out a message
	 *
	 * @param message
	 */
	private void log(final String message) {
		System.out.println("[" + this.port + "] " + message);
		Controller.restart();
	}

	/**
	 * Write out an error message
	 *
	 * @param message
	 */
	private void err(final String message) {
		System.err.println("[" + this.port + "] " + message);
		Controller.restart();
	}

}
