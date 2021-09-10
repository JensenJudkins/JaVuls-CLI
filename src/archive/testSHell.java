package archive;

import java.util.ArrayList;
import java.util.List;

import apps.ReverseShell.originals.ReverseShellBroken;



public class testSHell {
	
	public static void main(String[] args) {
		String ip = "localhost";
		String port = "9000";
		List<String> listOfArgs = new ArrayList<String>();
		listOfArgs.add(ip);
		listOfArgs.add(port);
		ReverseShellBroken.main(listOfArgs.toArray(new String[listOfArgs.size()]));
		try {
			controller.Controller.restart();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
