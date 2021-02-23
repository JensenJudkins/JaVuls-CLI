package testFiles;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceApp {

	
	
	
	
	
	public static void main(String[] args)
	{
		ExecutorService ex = Executors.newFixedThreadPool(10);
		String ip;
		
		
		Runnable task1 = new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName());
					System.out.println("My task1 started...");
					try {
					TimeUnit.SECONDS.sleep(2);
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("My task1 end...");
				}
		};
	
	
		Runnable task2 = new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
				System.out.println("My task2 started...");
				try {
				TimeUnit.SECONDS.sleep(2);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("My task2 end...");
			}
	};


	Runnable task3 = new Runnable() {
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName());
			System.out.println("My task3 started...");
			try {
			TimeUnit.SECONDS.sleep(2);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("My task3 end...");
		}
};
	
	ex.submit(task1);
	ex.submit(task2);
	ex.submit(task3);
	ex.shutdown();
}
}
