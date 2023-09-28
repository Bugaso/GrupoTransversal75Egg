package vistas;

//import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.util.ArrayList;
import accesoADatos.*;
import entidades.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

@SuppressWarnings("serial")
public class FormularioInscripcion extends JInternalFrame {
	private AlumnoData aluData;
	private MateriaData matData;
	private InscripcionData insData;
	private JLabel jlInscripcion;
	private JLabel jlSeleccion;
	private JComboBox<Alumno> jcbAlumno;
	private JLabel jlListado;
	private JRadioButton jrbMatInscriptas;
	private JRadioButton jrbMatNoInscriptas;
	private JScrollPane scrollPane;
	private JTable jtMaterias;
	private DefaultTableModel modeloTabla = new DefaultTableModel() {

		public boolean isCellEditable(int fila, int columna) {
			return false;
		}
	};
	private JButton jbInscribirse;
	private JButton jbAnularInscripcion;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FormularioInscripcion frame = new FormularioInscripcion();
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
	public FormularioInscripcion() {
		this.aluData = new AlumnoData();
		this.matData = new MateriaData();
		this.insData = new InscripcionData();

		getContentPane().setBackground(new Color(102, 204, 153));
		setBackground(new Color(102, 204, 153));
		setClosable(true);
		setBounds(100, 100, 450, 394);
		getContentPane().setLayout(null);

		jlInscripcion = new JLabel("Formulario de Inscripción");
		jlInscripcion.setFont(new Font("Tahoma", Font.BOLD, 18));
		jlInscripcion.setBounds(106, 0, 242, 22);
		getContentPane().add(jlInscripcion);

		jlSeleccion = new JLabel("Seleccione un Alumno");
		jlSeleccion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jlSeleccion.setBounds(10, 48, 140, 14);
		getContentPane().add(jlSeleccion);

		jcbAlumno = new JComboBox<Alumno>();
		cargarJComboBox();
		jcbAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_jcbAlumno_actionPerformed(e);
			}
		});
		jcbAlumno.setBounds(173, 46, 234, 22);
		getContentPane().add(jcbAlumno);

		jlListado = new JLabel("Listado de Materias");
		jlListado.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlListado.setBounds(147, 96, 140, 14);
		getContentPane().add(jlListado);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 164, 385, 135);
		getContentPane().add(scrollPane);

		jtMaterias = new JTable();
		jtMaterias.setRowSelectionAllowed(false);
//		scrollPane.setColumnHeaderView(jtMaterias);
		scrollPane.setViewportView(jtMaterias);
		armarCabeceraTabla();

		jrbMatInscriptas = new JRadioButton("Materias Inscriptas");
		jrbMatInscriptas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_jrbMatInscriptas_actionPerformed(e);
			}
		});
		jrbMatInscriptas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jrbMatInscriptas.setBounds(20, 134, 140, 23);
		getContentPane().add(jrbMatInscriptas);

		jrbMatNoInscriptas = new JRadioButton("Materias no Inscriptas");
		jrbMatNoInscriptas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_jrbMatNoInscriptas_actionPerformed(e);
			}
		});
		jrbMatNoInscriptas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jrbMatNoInscriptas.setBounds(250, 134, 157, 23);
		getContentPane().add(jrbMatNoInscriptas);

		jbInscribirse = new JButton("Inscribirse");
		jbInscribirse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_jbInscribirse_actionPerformed(e);
			}
		});
		jbInscribirse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jbInscribirse.setBounds(50, 322, 99, 23);
		getContentPane().add(jbInscribirse);

		jbAnularInscripcion = new JButton("Anular\nInscripción");
		jbAnularInscripcion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_jbAnularInscripcion_actionPerformed(e);
			}
		});
		jbAnularInscripcion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jbAnularInscripcion.setBounds(291, 310, 91, 35);
		getContentPane().add(jbAnularInscripcion);
		jbAnularInscripcion.setText("<html>Anular<br>Inscripción</html>");

	}

	private void cargarJComboBox() {
		ArrayList<Alumno> alumnos = aluData.listarAlumnos();

		if (!alumnos.isEmpty()) {
			for (int i = 0; i < alumnos.size(); i++) {
				jcbAlumno.addItem(alumnos.get(i));
			}
		} else {
			JOptionPane.showMessageDialog(this, "No hay ningun alumno en la Base de Datos");
		}
	}

	private void armarCabeceraTabla() {

		modeloTabla.addColumn("IdMateria");
		modeloTabla.addColumn("Nombre");
		modeloTabla.addColumn("Año de la Materia");
		jtMaterias.setModel(modeloTabla);
	}

	private void cargarMateria(Materia materia) {

		modeloTabla.addRow(new Object[] { materia.getIdMateria(), materia.getNombre(), materia.getAnio() });
	}

	private void limpiarTabla() {

		for (int i = modeloTabla.getRowCount() - 1; i >= 0; i--) {
			modeloTabla.removeRow(i);
		}
	}

	protected void do_jcbAlumno_actionPerformed(ActionEvent e) {

		jrbMatInscriptas.setSelected(false);
		jrbMatNoInscriptas.setSelected(false);
		limpiarTabla();
		jbInscribirse.setEnabled(true);
		jbAnularInscripcion.setEnabled(true);
	}

	protected void do_jrbMatInscriptas_actionPerformed(ActionEvent e) {

		limpiarTabla();

		if (jrbMatInscriptas.isSelected()) {
			Alumno alumno = (Alumno) jcbAlumno.getSelectedItem();
			ArrayList<Materia> materiasInscriptas = insData.obtenerMateriasCursadas(alumno.getIdAlumno());

			jrbMatNoInscriptas.setSelected(false);
			jbInscribirse.setEnabled(false);
			jbAnularInscripcion.setEnabled(true);

			for (int i = 0; i < materiasInscriptas.size(); i++) {
				cargarMateria(materiasInscriptas.get(i));
			}
		} else {
			jbInscribirse.setEnabled(true);
		}

//		Alumno alumno = (Alumno) jcbAlumno.getSelectedItem();
//		ArrayList<Materia> materiasInscriptas = insData.obtenerMateriasCursadas(alumno.getIdAlumno());
//		
//		jrbMatNoInscriptas.setSelected(false);
//		jbInscribirse.setEnabled(false);
//		jbAnularInscripcion.setEnabled(true);
//			
//		for(int i=0; i<materiasInscriptas.size(); i++) {
//			cargarMateria(materiasInscriptas.get(i));
//		}
	}

	protected void do_jrbMatNoInscriptas_actionPerformed(ActionEvent e) {

		limpiarTabla();

		if (jrbMatNoInscriptas.isSelected()) {
			Alumno alumno = (Alumno) jcbAlumno.getSelectedItem();
			ArrayList<Materia> materiasNoInscriptas = insData.obtenerMateriasNOCursadas(alumno.getIdAlumno());

			jrbMatInscriptas.setSelected(false);
			jbAnularInscripcion.setEnabled(false);
			jbInscribirse.setEnabled(true);

			for (int i = 0; i < materiasNoInscriptas.size(); i++) {
				cargarMateria(materiasNoInscriptas.get(i));
			}
		} else {
			jbAnularInscripcion.setEnabled(true);
		}

//		Alumno alumno = (Alumno) jcbAlumno.getSelectedItem();
//		ArrayList<Materia> materiasInscriptas = insData.obtenerMateriasNOCursadas(alumno.getIdAlumno());
//		
//		jrbMatInscriptas.setSelected(false);
//		jbAnularInscripcion.setEnabled(false);
//		jbInscribirse.setEnabled(true);
//		
//		for(int i=0; i<materiasInscriptas.size(); i++) {
//			cargarMateria(materiasInscriptas.get(i));
//		}	
	}

	protected void do_jbInscribirse_actionPerformed(ActionEvent e) {
		int filaSelec = jtMaterias.getSelectedRow();

		if (filaSelec != -1) {
			Alumno alumno = (Alumno) jcbAlumno.getSelectedItem();
			Materia materia = matData.buscarMateriaPorNombre((String) jtMaterias.getValueAt(filaSelec, 1));
			Inscripcion inscripcion = new Inscripcion(alumno, materia, 0);
			insData.guardarInscripcion(inscripcion);
		} else {
			JOptionPane.showMessageDialog(this, "Seleccione una materia para inscribirse");
		}
	}

	protected void do_jbAnularInscripcion_actionPerformed(ActionEvent e) {
		int filaSelec = jtMaterias.getSelectedRow();

		if (filaSelec != -1) {
			Alumno alumno = (Alumno) jcbAlumno.getSelectedItem();
			Materia materia = matData.buscarMateriaPorNombre((String) jtMaterias.getValueAt(filaSelec, 1));

			insData.borrarInscripcionMateriaAlumno(alumno.getIdAlumno(), materia.getIdMateria());
		} else {
			JOptionPane.showMessageDialog(this, "Seleccione una materia para desinscribirse");
		}
	}
}