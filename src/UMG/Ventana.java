package UMG;

	import java.awt.BorderLayout;
	import java.awt.Dimension;
	import java.awt.FlowLayout;
	import java.awt.GridBagConstraints;
	import java.awt.GridBagLayout;
	import java.awt.Insets;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.text.ParseException;
	import java.text.SimpleDateFormat;
	import java.util.Date;

	import javax.swing.JButton;
	import javax.swing.JComboBox;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JPanel;
	import javax.swing.JScrollPane;
	import javax.swing.JTextArea;
	import javax.swing.JTextField;
	import javax.swing.SwingConstants;
	import javax.swing.text.JTextComponent;

	public class Ventana extends JFrame {

		private static final long serialVersionUID = 1L;

		private JTextField txtTitle;
		private JTextArea txtDescription;
		private JComboBox<State> cboState;
		private JTextField txtCategory;
		private JTextField txtPriority;
		private JTextField txtOwner;
		private JTextField txtDueDate;
		private JButton btnAdd;
		private JButton btnCancel;

		public Ventana(String title) {
			super(title);
			initialize();
			addListeners();
		}

		private void clean() {
			txtTitle.setText("");
			txtDescription.setText("");
			cboState.setSelectedIndex(0);
			txtCategory.setText("");
			txtPriority.setText("");
			txtOwner.setText("");
			txtDueDate.setText(new Date().toString());
		}

		private void isEmpty(String message, JTextComponent text)
				throws ComponenteVacio {
			if ("".equals(text.getText().trim())) {
				throw new ComponenteVacio(message, text);
			}
		}

		private void verify() throws Exception {
			isEmpty("CAMPO VACIO: TITULO", txtTitle);
			isEmpty("CAMPO VACIO: DESCRIPCION", txtDescription);
			isEmpty("CAMPO VACIO: CATEGORIA", txtCategory);
			isEmpty("CAMPO VACIO: PRIORIDAD", txtPriority);
			isEmpty("CAMPO VACIO: PROPIETARIO", txtOwner);
			isEmpty("CAMPO VACIO: FECHA VENCIMIENTO", txtDueDate);
		}

		private boolean save() {
			Task task = new Task();
			task.setTitle(txtTitle.getText());
			task.setDescription(txtDescription.getText());
			task.setState(cboState.getItemAt(cboState.getSelectedIndex()));
			task.setCategory(new Category(txtCategory.getText()));
			task.setPriority(Short.parseShort(txtPriority.getText()));
			task.setOwner(txtOwner.getText());
			SimpleDateFormat formatter = new SimpleDateFormat(
					"EEE MMM d HH:mm:ss zzz yyyy");
			try {
				task.setDueDate((java.sql.Date) formatter.parse(txtDueDate.getText()));
			} catch (ParseException e) {
				return false;
			}
			task.setCreateDate((java.sql.Date) new Date());

			return Programa.dashboard.add(task);
		}

		private class TaskAction implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object source = e.getSource();
				if (source instanceof JButton) {
					if (btnAdd == source) {
						try {
							verify();
							if (JOptionPane.YES_OPTION == JOptionPane
									.showConfirmDialog(null,
											"DESEA AGREGAR TAREA?",
											"OK",
											JOptionPane.YES_NO_OPTION,
											JOptionPane.INFORMATION_MESSAGE)) {
								if (save()) {
									JOptionPane.showMessageDialog(null,
											"TAREA AÑADIDA!!!");
									clean();
								}
							}
						} catch (ComponenteVacio ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(),
									"ERROR EN VALIDACION", JOptionPane.ERROR_MESSAGE);
							ex.getComponent().requestFocus();
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(),
									"ERROR EN VALIDACION", JOptionPane.ERROR_MESSAGE);

						}
					} else if (btnCancel == source) {
						dispose();
					}
				}
			}

		}

		public void addListeners() {
			TaskAction ta = new TaskAction();
			btnAdd.addActionListener(ta);
			btnCancel.addActionListener(ta);
		}

		public void initialize() {
			setLayout(new BorderLayout());

			JPanel pnlFields = new JPanel(new GridBagLayout());

			GridBagConstraints gc = new GridBagConstraints();
			gc.fill = GridBagConstraints.BOTH;
			gc.insets = new Insets(2, 2, 2, 2);

			// Title

			gc.gridx = 0;
			gc.gridy = 0;
			pnlFields.add(new JLabel("Titulo:"), gc);

			gc.gridx = 1;
			txtTitle = new JTextField();
			txtTitle.setPreferredSize(new Dimension(200, (int) txtTitle
					.getPreferredSize().getHeight()));
			pnlFields.add(txtTitle, gc);

			// Description

			gc.gridx = 0;
			gc.gridy = 1;
			JLabel lblDescription = new JLabel("Descripcion:", SwingConstants.LEFT);
			lblDescription.setVerticalAlignment(SwingConstants.TOP);
			pnlFields.add(lblDescription, gc);

			gc.gridx = 1;
			txtDescription = new JTextArea();
			txtDescription.setPreferredSize(new Dimension(200, 100));
			JScrollPane scroll = new JScrollPane(txtDescription);
			pnlFields.add(scroll, gc);

			// State

			gc.gridx = 0;
			gc.gridy = 2;
			pnlFields.add(new JLabel("Estaus:"), gc);

			gc.gridx = 1;
			cboState = new JComboBox<State>(State.values());
			cboState.setPreferredSize(new Dimension(200, (int) cboState
					.getPreferredSize().getHeight()));
			pnlFields.add(cboState, gc);

			// Category

			gc.gridx = 0;
			gc.gridy = 3;
			pnlFields.add(new JLabel("Categoria:"), gc);

			gc.gridx = 1;
			txtCategory = new JTextField();
			txtCategory.setPreferredSize(new Dimension(200, (int) txtCategory
					.getPreferredSize().getHeight()));
			pnlFields.add(txtCategory, gc);

			// Priority

			gc.gridx = 0;
			gc.gridy = 4;
			pnlFields.add(new JLabel("Prioridad:"), gc);

			gc.gridx = 1;
			txtPriority = new JTextField();
			txtPriority.setPreferredSize(new Dimension(200, (int) txtPriority
					.getPreferredSize().getHeight()));
			pnlFields.add(txtPriority, gc);

			// Owner

			gc.gridx = 0;
			gc.gridy = 5;
			pnlFields.add(new JLabel("Propietario:"), gc);

			gc.gridx = 1;
			txtOwner = new JTextField();
			txtOwner.setPreferredSize(new Dimension(200, (int) txtOwner
					.getPreferredSize().getHeight()));
			pnlFields.add(txtOwner, gc);

			// Due date

			gc.gridx = 0;
			gc.gridy = 6;
			pnlFields.add(new JLabel("Fecha de Vencimiento:"), gc);

			gc.gridx = 1;
			txtDueDate = new JTextField();
			txtDueDate.setText(new Date().toString());
			txtDueDate.setPreferredSize(new Dimension(200, (int) txtDueDate
					.getPreferredSize().getHeight()));
			pnlFields.add(txtDueDate, gc);

			JPanel pnlButtons = new JPanel(new FlowLayout());

			btnAdd = new JButton("AÑADIR");
			pnlButtons.add(btnAdd);
			btnCancel = new JButton("CANCELAR");
			pnlButtons.add(btnCancel);

			add(pnlFields, BorderLayout.PAGE_START);
			add(pnlButtons, BorderLayout.PAGE_END);
		}

		public static void main(String... args) {
			JFrame window = new Ventana("AÑADIR TAREA");
			window.setMinimumSize(new Dimension(380, 340));
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window.setVisible(true);

		}

}
