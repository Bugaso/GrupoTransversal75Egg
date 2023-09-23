package vistas;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class MenuPrincipal extends JFrame {
	private JPanel contentPane;
	private JMenuBar jmbMenuBar;
	private JMenu jmAlumno;
	private JMenu jmMateria;
	private JMenu jmAdministracion;
	private JMenu jmConsultas;
	private JMenu jmSalir;
	private JMenuItem jmiFormAlumno;
	private JMenuItem jmiFormMateria;
	private JMenuItem jmiManejoIncrips;
	private JMenuItem jmiManiDeNotas;
	private JMenuItem jmiAlumnoXMateria;
	private final JDesktopPane jdpEscritorio = new JDesktopPane();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 600);
		
		jmbMenuBar = new JMenuBar();
		setJMenuBar(jmbMenuBar);
		
		jmAlumno = new JMenu("Alumno");
		jmbMenuBar.add(jmAlumno);
		
		jmiFormAlumno = new JMenuItem("Formulario de Alumno");
		jmiFormAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_jmiFormAlumno_actionPerformed(e);
			}
		});
		jmAlumno.add(jmiFormAlumno);
		
		jmMateria = new JMenu("Materia");
		jmbMenuBar.add(jmMateria);
		
		jmiFormMateria = new JMenuItem("Formulario de Materia");
		jmiFormMateria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_jmiFormMateria_actionPerformed(e);
			}
		});
		jmMateria.add(jmiFormMateria);
		
		jmAdministracion = new JMenu("Administración");
		jmbMenuBar.add(jmAdministracion);
		
		jmiManejoIncrips = new JMenuItem("Manejo de Inscripciones");
		jmiManejoIncrips.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_jmiManejoIncrips_actionPerformed(e);
			}
		});
		jmAdministracion.add(jmiManejoIncrips);
		
		jmiManiDeNotas = new JMenuItem("Manipulación de Notas");
		jmiManiDeNotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_jmiManiDeNotas_actionPerformed(e);
			}
		});
		jmAdministracion.add(jmiManiDeNotas);
		
		jmConsultas = new JMenu("Consultas");
		jmbMenuBar.add(jmConsultas);
		
		jmiAlumnoXMateria = new JMenuItem("Alumnos por Materia");
		jmiAlumnoXMateria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_jmiAlumnoXMateria_actionPerformed(e);
			}
		});
		jmConsultas.add(jmiAlumnoXMateria);
		
		jmSalir = new JMenu("Salir");
		jmbMenuBar.add(jmSalir);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		jdpEscritorio.setBounds(0, 0, 684, 539);
		contentPane.add(jdpEscritorio);
		jdpEscritorio.setBackground(new Color(0, 102, 153));	
	}
	
	protected void do_jmiFormAlumno_actionPerformed(ActionEvent e) {
		
		jdpEscritorio.removeAll();
		jdpEscritorio.repaint();
		FormularioAlumno fa = new FormularioAlumno();
		fa.setVisible(true);
		jdpEscritorio.add(fa);
		jdpEscritorio.moveToFront(fa);
	}
	
	protected void do_jmiFormMateria_actionPerformed(ActionEvent e) {
		
		jdpEscritorio.removeAll();
		jdpEscritorio.repaint();
		FormularioMateria fm = new FormularioMateria();
		fm.setVisible(true);
		jdpEscritorio.add(fm);
		jdpEscritorio.moveToFront(fm);
	}
	
	protected void do_jmiManejoIncrips_actionPerformed(ActionEvent e) {
		
		jdpEscritorio.removeAll();
		jdpEscritorio.repaint();
		FormularioInscripcion fi = new FormularioInscripcion();
		fi.setVisible(true);
		jdpEscritorio.add(fi);
		jdpEscritorio.moveToFront(fi);
	}
	
	protected void do_jmiManiDeNotas_actionPerformed(ActionEvent e) {
		
		jdpEscritorio.removeAll();
		jdpEscritorio.repaint();
		ManipulacionNotas mn = new ManipulacionNotas();
		mn.setVisible(true);
		jdpEscritorio.add(mn);
		jdpEscritorio.moveToFront(mn);
	}
	
	
	protected void do_jmiAlumnoXMateria_actionPerformed(ActionEvent e) {
		
		jdpEscritorio.removeAll();
		jdpEscritorio.repaint();
		AlumnosXMateria aXm = new AlumnosXMateria();
		aXm.setVisible(true);
		jdpEscritorio.add(aXm);
		jdpEscritorio.moveToFront(aXm);
	}
}