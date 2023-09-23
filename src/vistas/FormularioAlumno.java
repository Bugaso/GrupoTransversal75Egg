package vistas;

//import java.awt.EventQueue;
import java.awt.Color;
import javax.swing.*;
import java.awt.Font;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.awt.event.ActionEvent;
import accesoADatos.*;
import entidades.Alumno;

@SuppressWarnings("serial")
public class FormularioAlumno extends JInternalFrame {
	private AlumnoData aluData = new AlumnoData();
	private JLabel jlAlumno;
	private JLabel jlDocumento;
	private JLabel jlNombre;
	private JLabel jlActivo;
	private JLabel jlApellido;
	private JLabel jlFechaNac;
	private JTextField jtfDocumento;
	private JTextField jtfApellido;
	private JTextField jtfNombre;
	private JRadioButton jrbActivo;
	private JDateChooser jdcFechaNac;
	private JButton jbBuscar;
	private JButton jbAnadir;
	private JButton jbRefrescar;
	private JButton jbGuardarCambios;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FormularioAlumno frame = new FormularioAlumno();
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
	public FormularioAlumno() {
		setClosable(true);
		getContentPane().setBackground(new Color(102, 204, 153));
		setBounds(100, 100, 470, 338);//458 338
		getContentPane().setLayout(null);
		
		jlDocumento = new JLabel("Documento");
		jlDocumento.setBounds(10, 28, 79, 14);
		getContentPane().add(jlDocumento);
		jlDocumento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		jlApellido = new JLabel("Apellido");
		jlApellido.setBounds(10, 53, 51, 22);
		getContentPane().add(jlApellido);
		jlApellido.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		jlNombre = new JLabel("Nombre");
		jlNombre.setBounds(10, 86, 51, 14);
		getContentPane().add(jlNombre);
		jlNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		jlActivo = new JLabel("Activo");
		jlActivo.setBounds(10, 127, 51, 14);
		getContentPane().add(jlActivo);
		jlActivo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		jlFechaNac = new JLabel("Fecha de Nacimiento");
		jlFechaNac.setBounds(10, 173, 134, 14);
		getContentPane().add(jlFechaNac);
		jlFechaNac.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		jtfDocumento = new JTextField();
		jtfDocumento.setBounds(145, 28, 158, 20);
		getContentPane().add(jtfDocumento);
		jtfDocumento.setColumns(10);
		
		jtfApellido = new JTextField();
		jtfApellido.setBounds(145, 57, 158, 20);
		getContentPane().add(jtfApellido);
		jtfApellido.setColumns(10);
		
		jtfNombre = new JTextField();
		jtfNombre.setBounds(145, 86, 158, 20);
		getContentPane().add(jtfNombre);
		jtfNombre.setColumns(10);
		
		jrbActivo = new JRadioButton("SI/NO");
		jrbActivo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jrbActivo.setBackground(new Color(102, 204, 153));
		jrbActivo.setBounds(145, 126, 63, 23);
		getContentPane().add(jrbActivo);
		
		jdcFechaNac = new JDateChooser();
		jdcFechaNac.setBounds(154, 173, 118, 20);
		getContentPane().add(jdcFechaNac);
		
		jbBuscar = new JButton("Buscar");
		jbBuscar.setToolTipText("Es un boton para buscar un Alumno");
		jbBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_jbBuscar_actionPerformed(e);
			}
		});
		jbBuscar.setBounds(341, 28, 79, 23);
		getContentPane().add(jbBuscar);
		jbBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
				
		jbAnadir = new JButton("Añadir");
		jbAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_jbAnadir_actionPerformed(e);
			}
		});
		jbAnadir.setToolTipText("Es un boton para añadir un nuevo Alumno");
		jbAnadir.setBounds(65, 255, 79, 23);
		getContentPane().add(jbAnadir);
		jbAnadir.setFont(new Font("Tahoma", Font.PLAIN, 14));
				
		jbRefrescar = new JButton("Refrescar");
		jbRefrescar.setToolTipText("Es un boton para vaciar los campos (DNI, Apellido, Nombre, etc ...)");
		jbRefrescar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_jbRefrescar_actionPerformed(e);
			}
		});
		jbRefrescar.setBounds(180, 255, 94, 23);
		getContentPane().add(jbRefrescar);
		jbRefrescar.setFont(new Font("Tahoma", Font.PLAIN, 14));
				
		jbGuardarCambios = new JButton("Guardar\nCambios");
		jbGuardarCambios.setToolTipText("Es un boton para modificar un Alumno");
		jbGuardarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_jbGuardarCambios_actionPerformed(e);
			}
		});
		jbGuardarCambios.setBounds(309, 250, 79, 35);
		getContentPane().add(jbGuardarCambios);
		jbGuardarCambios.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jbGuardarCambios.setText("<html>" + jbGuardarCambios.getText().replaceAll("\\n", "<br>") + "</html>");
				
		jlAlumno = new JLabel("ALUMNO");
		jlAlumno.setBounds(180, 0, 79, 22);
		getContentPane().add(jlAlumno);
		jlAlumno.setFont(new Font("Tahoma", Font.BOLD, 18));

	}
	
	protected void do_jbAnadir_actionPerformed(ActionEvent e) {
		
		if(!jtfDocumento.getText().equals("") && !jtfApellido.getText().equals("") && !jtfNombre.getText().equals("") && jdcFechaNac.getDate() != null) {
			
			try {
				int documento = Integer.parseInt(jtfDocumento.getText());
				
				if(aluData.buscarAlumnoPorDni(documento) == null) {
					String apellido = jtfApellido.getText();
					String nombre = jtfNombre.getText();
					boolean activo = jrbActivo.isSelected();
					LocalDate fechaNac = jdcFechaNac.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//					DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					Alumno alumno = new Alumno(apellido, nombre, documento, fechaNac, activo);
					aluData.guardarAlumno(alumno);
				}else {
					JOptionPane.showMessageDialog(this, "El alumno que quiere guardar, ya esta en la Base de Datos");
				}
				
				
			}catch(NumberFormatException exc){
				JOptionPane.showMessageDialog(this, "En el campo Documento ingrese solo valores númericos sin punto ni coma");
			}
		}else {
			JOptionPane.showMessageDialog(this, "Algun/os de los campo/s esta vacio");
		}
	}
	
	protected void do_jbBuscar_actionPerformed(ActionEvent e) {
		
		if(!jtfDocumento.getText().equals("")) {
			
			try {
				int documento = Integer.parseInt(jtfDocumento.getText());
				Alumno alumnoBusc = this.aluData.buscarAlumnoPorDni(documento);
				
				if(alumnoBusc != null) {
					jtfDocumento.setText(alumnoBusc.getDni() + "");
					jtfApellido.setText(alumnoBusc.getApellido());
					jtfNombre.setText(alumnoBusc.getNombre());
					jrbActivo.setSelected(alumnoBusc.isActivo());
					jdcFechaNac.setDate(Date.from(alumnoBusc.getFchNacimiento().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
				}else {
					JOptionPane.showMessageDialog(this, "El alumno buscado no existe en la Base de Datos");
				}
				
			}catch(NumberFormatException exc){
				JOptionPane.showMessageDialog(this, "Ingrese solo valores númericos sin punto ni coma");
			}
		}else {
			JOptionPane.showMessageDialog(this, "El campo Documento esta vacío");
		}
	}
	
	protected void do_jbRefrescar_actionPerformed(ActionEvent e) {
		
//		if(!jtfDocumento.getText().equals("")) {
//			
//			try {
//				int documento = Integer.parseInt(jtfDocumento.getText());
//				Alumno alumnoBusc = this.aluData.buscarAlumnoPorDni(documento);
//				
//				if(alumnoBusc != null) {
//					this.aluData.eliminarAlumno(alumnoBusc.getIdAlumno());
//				}else {
//					JOptionPane.showMessageDialog(this, "El alumno que quiere eliminar no existe en la Base de Datos");
//				}
//				
//			}catch(NumberFormatException exc){
//				JOptionPane.showMessageDialog(this, "Ingrese solo valores númericos sin punto ni coma");
//			}
//		}else {
//			JOptionPane.showMessageDialog(this, "El campo Documento esta vacío");
//		}
		
		jtfDocumento.setText("");
		jtfApellido.setText("");
		jtfNombre.setText("");
		jrbActivo.setSelected(false);
		jdcFechaNac.setDate(null);
	}
	
	protected void do_jbGuardarCambios_actionPerformed(ActionEvent e) {
		
		if(!jtfDocumento.getText().equals("") && !jtfApellido.getText().equals("") && !jtfNombre.getText().equals("") && jdcFechaNac.getDate() != null) {
			
			try {
				int documento = Integer.parseInt(jtfDocumento.getText());
				Alumno alumnoAMod = aluData.buscarAlumnoPorDni(documento);
				
				if(alumnoAMod != null) {
					alumnoAMod.setDni(Integer.parseInt(jtfDocumento.getText()));
					alumnoAMod.setApellido(jtfApellido.getText());
					alumnoAMod.setNombre(jtfNombre.getText());
					alumnoAMod.setActivo(jrbActivo.isSelected());
					alumnoAMod.setFchNacimiento(jdcFechaNac.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
//					String apellido = jtfApellido.getText();
//					String nombre = jtfNombre.getText();
//					boolean activo = jrbPresente.isSelected();
//					LocalDate fechaNac = jdcFechaNac.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//					DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//					Alumno alumno = new Alumno(apellido, nombre, documento, fechaNac, activo);
					aluData.modificarAlumno(alumnoAMod);
					
					JOptionPane.showMessageDialog(this, "Alumno Modificado");
				}else {
					JOptionPane.showMessageDialog(this, "El alumno que quiere modificar no esta en la Base de Datos");
				}
				
				
			}catch(NumberFormatException exc){
				JOptionPane.showMessageDialog(this, "En el campo Documento ingrese solo valores númericos sin punto ni coma");
			}
		}else {
			JOptionPane.showMessageDialog(this, "Algun/os de los campo/s esta vacio");
		}
	}
}