package vistas;

//import java.awt.EventQueue;
import javax.swing.*;
import accesoADatos.*;
import entidades.*;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

@SuppressWarnings("serial")
public class AlumnosXMateria extends JInternalFrame {
	private MateriaData matData;
	private InscripcionData inscData;
	private JLabel jlTitulo;
	private JLabel jlSeleccion;
	private JComboBox<Materia> jcbMaterias;
	private JScrollPane scrollPane;
	private JTable jtAlumnos;
	private DefaultTableModel modeloTabla = new DefaultTableModel() {

		public boolean isCellEditable(int fila, int columna) {
			return false;
		}
	};

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AlumnosXMateria frame = new AlumnosXMateria();
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
	public AlumnosXMateria() {

		this.matData = new MateriaData();
		this.inscData = new InscripcionData();

		setBackground(new Color(102, 204, 153));
		setClosable(true);
		setBounds(100, 100, 512, 318);
		getContentPane().setLayout(null);

		jlTitulo = new JLabel("Listado de Alumnos por Materia");
		jlTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlTitulo.setBounds(133, 0, 227, 19);
		getContentPane().add(jlTitulo);

		jlSeleccion = new JLabel("Seleccione una materia");
		jlSeleccion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jlSeleccion.setBounds(10, 34, 151, 20);
		getContentPane().add(jlSeleccion);

		jcbMaterias = new JComboBox<Materia>();
		jcbMaterias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_jcbMaterias_actionPerformed(e);
			}
		});
		cargarComboBox();
		jcbMaterias.setBounds(171, 35, 189, 22);
		getContentPane().add(jcbMaterias);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 89, 476, 170);
		getContentPane().add(scrollPane);

		jtAlumnos = new JTable();
		jtAlumnos.setRowSelectionAllowed(true);
		jtAlumnos.setColumnSelectionAllowed(false);
		jtAlumnos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jtAlumnos.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(jtAlumnos);
		armarCabeceraTabla();
//		scrollPane.setColumnHeaderView(jtAlumnos);
	}

	private void cargarComboBox() {
		ArrayList<Materia> materias = matData.listarMateria();

		if (!materias.isEmpty()) {
			for (int i = 0; i < materias.size(); i++) {
				jcbMaterias.addItem(materias.get(i));
			}
		} else {
			JOptionPane.showMessageDialog(this, "No hay ninguna materia en la Base de Datos");
		}
	}

	private void armarCabeceraTabla() {

		modeloTabla.addColumn("idAlumno");
		modeloTabla.addColumn("DNI");
		modeloTabla.addColumn("Apellido");
		modeloTabla.addColumn("Nombre");
		jtAlumnos.setModel(modeloTabla);
	}

	private void cargarAlumno(Alumno alumno) {

		modeloTabla.addRow(
				new Object[] { alumno.getIdAlumno(), alumno.getDni(), alumno.getApellido(), alumno.getNombre() });
	}

	private void limpiarTabla() {

		for (int i = modeloTabla.getRowCount() - 1; i >= 0; i--) {
			modeloTabla.removeRow(i);
		}
	}

	protected void do_jcbMaterias_actionPerformed(ActionEvent e) {

		limpiarTabla();

		Materia materia = (Materia) jcbMaterias.getSelectedItem();
		ArrayList<Alumno> alumnos = inscData.obtenerAlumnosXMateria(materia.getIdMateria());

		for (int i = 0; i < alumnos.size(); i++) {
			cargarAlumno(alumnos.get(i));
		}
	}
}