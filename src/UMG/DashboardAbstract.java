package UMG;


import java.util.ArrayList;
import java.util.List;

//Clase abstracta
public abstract class DashboardAbstract {
	protected List<Task> container;

	public DashboardAbstract() {
		super();
		System.out.println("starting DashboardAbstract() ctor...");
		container = new ArrayList<Task>();
	}

	public boolean add(Task task) {
		return container.add(task);
	}

}
