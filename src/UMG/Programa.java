package UMG;


public class Programa {
	public static void main(String[] args) throws Exception {
		Task task;
		IDashboard dashboard = new DashboardList();
		for (int i = 0; i < 15; i++) {
			task = new Task("Task " + i, State.DO_TO);
			if (dashboard.add(task)) {
				System.out.println("Added " + task.getTitle());
			}
		}
	}

}
