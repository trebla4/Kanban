package UMG;

import javax.swing.JComponent;

public class ComponenteVacio extends Exception {

	private JComponent component;

	public JComponent getComponent(){
		return component;
	}

	public ComponenteVacio(String message, JComponent component) {
		super(message);
		this.component = component;
	}
}
