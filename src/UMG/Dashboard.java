package UMG;


import java.util.Arrays;


public class Dashboard implements IDashboard {
//Implementamos la  interfaz IDashboard

	private int size;
	private transient Task[] tasks;
	private transient int modCount = 0;
	
	//Constructor con capacidad inicial
		public Dashboard(int initialCapacity) {
			super();
			if (initialCapacity < 0)
				throw new IllegalArgumentException("Illegal Capacity: "
						+ initialCapacity);
			this.tasks = new Task[initialCapacity];
		}
	//Constructor con capacidad inicial 10
		public Dashboard() {
			this(10);
		}

	//Metodo add de la interfaz IDashbord
	public boolean add(Task task) {
		ensureCapacity(size + 1);
		tasks[size++] = task;
		return true;
	}
//Ajuste de capacidad
	public void ensureCapacity(int minCapacity) {
		modCount++;
		int oldCapacity = tasks.length;
		if (minCapacity > oldCapacity) {
			int newCapacity = (oldCapacity * 3) / 2 + 1;
			if (newCapacity < minCapacity)//Capacidad minima
				newCapacity = minCapacity;
			tasks = Arrays.copyOf(tasks, newCapacity);
		}
	}
	/*Eliminar terea esp*/
	public boolean remove(Task task) {
		if (task == null) {
			for (int index = 0; index < size; index++)
				if (tasks[index] == null) {
					fastRemove(index);
					return true;
				}
		} else {
			for (int index = 0; index < size; index++)
				if (task.equals(tasks[index])) {
					fastRemove(index);
					return true;
				}
		}
		return false;
	}

	//Remover con indice
	private void fastRemove(int index) {
		modCount++;
		int numMoved = size - index - 1;
		if (numMoved > 0)
			System.arraycopy(tasks, index + 1, tasks, index, numMoved);
		tasks[--size] = null;
	}
//Vaciar las tareas
	public void clear() {
		modCount++;
		for (int i = 0; i < size; i++)
			tasks[i] = null;

		size = 0;
	}

}
