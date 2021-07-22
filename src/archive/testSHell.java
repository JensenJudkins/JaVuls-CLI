package archive;

import java.util.ArrayList;
import java.util.List;

import apps.ReverseShell.ReverseShell;



public class testSHell {
	
	public static void main(String[] args) {
		String ip = "localhost";
		String port = "9000";
		List<String> listOfArgs = new ArrayList<String>();
		listOfArgs.add(ip);
		listOfArgs.add(port);
		ReverseShell.main(listOfArgs.toArray(new String[listOfArgs.size()]));
		controller.Controller.restart();
	}

}
