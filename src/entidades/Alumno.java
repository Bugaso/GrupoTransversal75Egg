package entidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Alumno {
	private int idAlumno;
	private String apellido;
	private String nombre;
	private int dni;
	private LocalDate fchNacimiento;
	private boolean activo;
	
	
	public Alumno() {
		
	}
	
	public Alumno(int idAlumno, String apellido, String nombre, int dni, LocalDate fchNacimiento, boolean activo) {
		this.idAlumno = idAlumno;
		this.apellido = apellido;
		this.nombre = nombre;
		this.dni = dni;
		this.fchNacimiento = fchNacimiento;
		this.activo = activo;
	}
	
	public Alumno(String apellido, String nombre, int dni, LocalDate fchNacimiento, boolean activo) {
		this.apellido = apellido;
		this.nombre = nombre;
		this.dni = dni;
		this.fchNacimiento = fchNacimiento;
		this.activo = activo;
	}

	public int getIdAlumno() {return idAlumno;}

	public void setIdAlumno(int idAlumno) {this.idAlumno = idAlumno;}

	public String getApellido() {return apellido;}

	public void setApellido(String apellido) {this.apellido = apellido;}

	public String getNombre() {return nombre;}

	public void setNombre(String nombre) {this.nombre = nombre;}

	public int getDni() {return dni;}

	public void setDni(int dni) {this.dni = dni;}

	public LocalDate getFchNacimiento() {return fchNacimiento;}

	public void setFchNacimiento(LocalDate fchNacimiento) {this.fchNacimiento = fchNacimiento;}
	
	public boolean isActivo() {return activo;}

	public void setActivo(boolean activo) {this.activo = activo;}

	public String toString() {
		String cad = "";
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
		
		cad = cad + "ID del Alumno: " + this.idAlumno + "\n";
		cad = cad + "Apellido del Alumno: " + this.apellido + "\n";
		cad = cad + "Nombre del Alumno: " + this.nombre + "\n";
		cad = cad + "DNI del Alumno: " + this.dni + "\n";
		cad = cad + "Fecha de Nacimiento del Alumno: " + this.fchNacimiento.format(formato) + "\n";
		cad = cad + "Activo del Alumno: " + this.activo;
		
		return cad;
	}
}