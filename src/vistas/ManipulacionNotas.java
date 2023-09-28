package vistas;

//import java.awt.EventQueue;
import javax.swing.*;
import entidades.*;
import accesoADatos.*;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
//import java.awt.event.KeyAdapter;
//import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class ManipulacionNotas extends JInternalFrame {
	private AlumnoData aluData;
	private InscripcionData inscData;
	private JLabel jlTitulo;
	private JLabel jlSeleccion;
	private JComboBox<Alumno> jcbAlumnos;
	private JScrollPane scrollPane;
	private JButton jbActualizarN;
	private JTable jtMaterias;
	private DefaultTableModel modeloTabla = new DefaultTableModel() {

		public boolean isCellEditable(int fila, int columna) {

			if (columna == 3) {
				return true;
			} else {
				return false;
			}
		}
	};

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ManipulacionNotas frame = new ManipulacionNotas();
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
	public ManipulacionNotas() {
		this.aluData = new AlumnoData();
		this.inscData = new InscripcionData();

		setBackground(new Color(102, 204, 153));
		setClosable(true);
		setBounds(100, 100, 493, 348);
		getContentPane().setLayout(null);

		jlTitulo = new JLabel("Carga de Notas");
		jlTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		jlTitulo.setBounds(149, 0, 151, 22);
		getContentPane().add(jlTitulo);

		jlSeleccion = new JLabel("Seleccione un alumno");
		jlSeleccion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jlSeleccion.setBounds(10, 42, 144, 14);
		getContentPane().add(jlSeleccion);

		jcbAlumnos = new JComboBox<Alumno>();
		jcbAlumnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_jcbAlumnos_actionPerformed(e);
			}
		});
		cargarComboBox();
		jcbAlumnos.setBounds(164, 40, 222, 22);
		getContentPane().add(jcbAlumnos);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 94, 431, 136);
		getContentPane().add(scrollPane);

		jtMaterias = new JTable();
//		jtMaterias.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent e) {
//				do_jtMaterias_keyPressed(e);
//			}
//		});
//		scrollPane.setColumnHeaderView(jtMaterias);
		scrollPane.setViewportView(jtMaterias);
		armarCabeceraTabla();

		jbActualizarN = new JButton("Actualizar Nota");
		jbActualizarN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_jbActualizarN_actionPerformed(e);
			}
		});
		jbActualizarN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jbActualizarN.setBounds(164, 264, 136, 23);
		getContentPane().add(jbActualizarN);

	}

	private void cargarComboBox() {
		ArrayList<Alumno> alumnos = aluData.listarAlumnos();

		if (!alumnos.isEmpty()) {
			for (int i = 0; i < alumnos.size(); i++) {
				jcbAlumnos.addItem(alumnos.get(i));
			}
		} else {
			JOptionPane.showMessageDialog(this, "No hay ningun alumno en la Base de Datos");
		}
	}

	private void armarCabeceraTabla() {

		modeloTabla.addColumn("IdMateria");
		modeloTabla.addColumn("Nombre");
		modeloTabla.addColumn("Año de la Materia");
		modeloTabla.addColumn("Nota");
		jtMaterias.setModel(modeloTabla);
	}

	private void cargarMateria(Materia materia) {
		int idMateria = materia.getIdMateria();
		Alumno alumno = (Alumno) jcbAlumnos.getSelectedItem();

		modeloTabla.addRow(new Object[] { materia.getIdMateria(), materia.getNombre(), materia.getAnio(),
				inscData.buscarNotaInscripcion(alumno.getIdAlumno(), idMateria) });
	}

	private void limpiarTabla() {

		for (int i = modeloTabla.getRowCount() - 1; i >= 0; i--) {
			modeloTabla.removeRow(i);
		}
	}

	protected void do_jcbAlumnos_actionPerformed(ActionEvent e) {

		limpiarTabla();

		Alumno alumno = (Alumno) jcbAlumnos.getSelectedItem();
		ArrayList<Materia> materiasInscriptas = inscData.obtenerMateriasCursadas(alumno.getIdAlumno());

		for (int i = 0; i < materiasInscriptas.size(); i++) {
			cargarMateria(materiasInscriptas.get(i));
		}
	}

	protected void do_jbActualizarN_actionPerformed(ActionEvent e) {

		try {
			int filaSelec = jtMaterias.getSelectedRow();

			if (filaSelec != -1) {
				double notaActualizada = Double.parseDouble(jtMaterias.getValueAt(filaSelec, 3).toString());

				if (notaActualizada >= 1 && notaActualizada <= 10) {
					Alumno alumno = (Alumno) jcbAlumnos.getSelectedItem();
					int idMateria = (int) jtMaterias.getValueAt(filaSelec, 0);
					inscData.actualizarNota(alumno.getIdAlumno(), idMateria, notaActualizada);
					do_jcbAlumnos_actionPerformed(e);
				} else {
					JOptionPane.showMessageDialog(this, "NOTA INVÁLIDA!!!. Ingrese una nota entre 1 y 10");
				}

			} else {
				JOptionPane.showMessageDialog(this, "Seleccione una materia para actualiar la nota");
			}

		} catch (NumberFormatException exc) {
			JOptionPane.showMessageDialog(this, "En la columna Nota se aceptan solo valores númericos sin coma");
		}
	}

//	protected void do_jtMaterias_keyPressed(KeyEvent e) {
//		
//		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
//			
//			try {
//				int filaSelec = jtMaterias.getSelectedRow();
//				
//				if(filaSelec != -1) {
//					double notaActualizada = Double.parseDouble(jtMaterias.getValueAt(filaSelec, 3).toString());
//					
//					if(notaActualizada >= 1 && notaActualizada <= 10) {
//						Alumno alumno = (Alumno) jcbAlumnos.getSelectedItem();
//						int idMateria = (int) jtMaterias.getValueAt(filaSelec, 0);
//						inscData.actualizarNota(alumno.getIdAlumno(), idMateria, notaActualizada);
//					}else {
//						JOptionPane.showMessageDialog(this, "NOTA INVÁLIDA!!!. Ingrese una nota entre 1 y 10");
//					}
//					
//				}else {
//					JOptionPane.showMessageDialog(this, "Seleccione una materia para actualiar la nota");
//				}
//				
//			} catch (NumberFormatException exc) {
//				JOptionPane.showMessageDialog(this, "En la columna Nota se aceptan solo valores númericos sin coma");
//			}
//		}else {
//			JOptionPane.showMessageDialog(this, "Seleccione una materia pata actualizar la nota y luego presione ENTER");
//		}
//	}
}