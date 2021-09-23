package controller;

public class Runner {
	public static void main(String[] args) 
	{
	Controller app = new Controller();
	try {
		app.start();
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("Bro the program just don't wanna start. leave me alone");
	}
	}

}
