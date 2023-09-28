package vistas;

//import java.awt.EventQueue;
import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import accesoADatos.*;
import entidades.*;

@SuppressWarnings("serial")
public class FormularioMateria extends JInternalFrame {
	private MateriaData matData = new MateriaData();
	private JLabel jlMateria;
	private JLabel jlNombre;
	private JLabel jlAnio;
	private JLabel jlActivo;
	private JButton jbAnadir;
	private JButton jbRefrescar;
	private JButton jbGuardarCambios;
	private JButton jbBuscar;
	private JTextField jtfNombre;
	private JTextField jtfAnio;
	private JRadioButton jrbActivo;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FormularioMateria frame = new FormularioMateria();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public FormularioMateria() {
		setClosable(true);
		getContentPane().setBackground(new Color(102, 204, 153));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		jlMateria = new JLabel("Materia");
		jlMateria.setFont(new Font("Tahoma", Font.BOLD, 18));
		jlMateria.setBounds(182, 0, 75, 14);
		getContentPane().add(jlMateria);

		jlNombre = new JLabel("Nombre");
		jlNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jlNombre.setBounds(10, 53, 60, 14);
		getContentPane().add(jlNombre);

		jlAnio = new JLabel("Año");
		jlAnio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jlAnio.setBounds(10, 94, 33, 14);
		getContentPane().add(jlAnio);

		jlActivo = new JLabel("Activo");
		jlActivo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jlActivo.setBounds(10, 138, 46, 14);
		getContentPane().add(jlActivo);

		jbAnadir = new JButton("Añadir");
		jbAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_jbAnadir_actionPerformed(e);
			}
		});
		jbAnadir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jbAnadir.setBounds(38, 220, 89, 23);
		getContentPane().add(jbAnadir);

		jbRefrescar = new JButton("Refrescar");
		jbRefrescar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_jbRefrescar_actionPerformed(e);
			}
		});
		jbRefrescar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jbRefrescar.setBounds(161, 220, 104, 23);
		getContentPane().add(jbRefrescar);

		jbGuardarCambios = new JButton("Guardar\nCambios");
		jbGuardarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_jbGuardarCambios_actionPerformed(e);
			}
		});
		jbGuardarCambios.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jbGuardarCambios.setBounds(296, 215, 104, 34);
		getContentPane().add(jbGuardarCambios);
		jbGuardarCambios.setText("<html>" + jbGuardarCambios.getText().replaceAll("\\n", "<br>") + "</html>");

		jbBuscar = new JButton("Buscar");
		jbBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_jbBuscar_actionPerformed(e);
			}
		});
		jbBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jbBuscar.setBounds(335, 49, 89, 23);
		getContentPane().add(jbBuscar);

		jtfNombre = new JTextField();
		jtfNombre.setBounds(101, 50, 179, 20);
		getContentPane().add(jtfNombre);
		jtfNombre.setColumns(10);

		jtfAnio = new JTextField();
		jtfAnio.setBounds(101, 91, 86, 20);
		getContentPane().add(jtfAnio);
		jtfAnio.setColumns(10);

		jrbActivo = new JRadioButton("SI/NO");
		jrbActivo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jrbActivo.setBackground(new Color(102, 204, 153));
		jrbActivo.setBounds(101, 134, 66, 23);
		getContentPane().add(jrbActivo);

	}

	protected void do_jbAnadir_actionPerformed(ActionEvent e) {

		if (!jtfNombre.getText().equals("") && !jtfAnio.getText().equals("")) {

			try {
				int anioMateria = Integer.parseInt(jtfAnio.getText());
				
				if(anioMateria >= 1) {
					if (matData.buscarMateriaPorNombre(jtfNombre.getText()) == null) {
						String nombre = jtfNombre.getText();
						boolean activo = jrbActivo.isSelected();

						Materia materia = new Materia(nombre, anioMateria, activo);
						matData.guardarMateria(materia);
					} else {
						JOptionPane.showMessageDialog(this, "La materia que quiere guardar, ya esta en la Base de Datos");
					}
				}else {
					JOptionPane.showMessageDialog(this, "Año Negativo o Cero. El Año de la materia debe ser un número positivo");
				}

			} catch (NumberFormatException exc) {
				JOptionPane.showMessageDialog(this, "En el campo Año ingrese solo valores númericos sin punto ni coma");
			}
		} else {
			JOptionPane.showMessageDialog(this, "Algun/os de los campo/s esta vacio");
		}
	}

	protected void do_jbBuscar_actionPerformed(ActionEvent e) {

		if (!jtfNombre.getText().equals("")) {
			Materia materiaBusc = this.matData.buscarMateriaPorNombre(jtfNombre.getText());

			if (materiaBusc != null) {
				jtfNombre.setText(materiaBusc.getNombre());
				jtfAnio.setText(materiaBusc.getAnio() + "");
				jrbActivo.setSelected(materiaBusc.isActivo());
			} else {
				JOptionPane.showMessageDialog(this, "La materia buscada no existe en la Base de Datos");
			}
		} else {
			JOptionPane.showMessageDialog(this, "El campo Nombre esta vacío");
		}
	}

	protected void do_jbRefrescar_actionPerformed(ActionEvent e) {

		jtfNombre.setText("");
		jtfAnio.setText("");
		jrbActivo.setSelected(false);
	}

	protected void do_jbGuardarCambios_actionPerformed(ActionEvent e) {

		if (!jtfNombre.getText().equals("") && !jtfAnio.getText().equals("")) {

			try {
				int anioMateria = Integer.parseInt(jtfAnio.getText());
				Materia materiaAMod = matData.buscarMateriaPorNombre(jtfNombre.getText());

				if (materiaAMod != null) {
					materiaAMod.setNombre(jtfNombre.getText());
					materiaAMod.setAnio(anioMateria);
					materiaAMod.setActivo(jrbActivo.isSelected());
					matData.modificarMateria(materiaAMod);

//					JOptionPane.showMessageDialog(this, "Materia Modificada");
				} else {
					JOptionPane.showMessageDialog(this, "La materia que quiere modificar no esta en la Base de Datos");
				}

			} catch (NumberFormatException exc) {
				JOptionPane.showMessageDialog(this, "En el campo Año ingrese solo valores númericos sin punto ni coma");
			}
		} else {
			JOptionPane.showMessageDialog(this, "Algun/os de los campo/s esta vacio");
		}
	}
}