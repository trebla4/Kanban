package UMG;

import java.util.Date;
import java.util.Random;


public class Programa {
	public static IDashboard dashboard = new DashboardList();

	public static void main(String[] args) throws Exception {
		System.out.println("Start " + new Date());

		Thread thread;
		User user;
		for (int i = 0; i < 15; i++) {
			user = new User();
			thread = new Thread(user);

			thread.setName("Title " + i);
			int priority = 1 + (int) (Math.random() * ((10 - 1) + 1));
			thread.setPriority(priority);

			thread.start();
		}

		System.out.println("End " + new Date());
	}

}
