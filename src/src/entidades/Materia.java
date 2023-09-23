package entidades;

public class Materia {
	private int idMateria;
	private String nombre;
	private int anio;
	private boolean activo;
	
	
	public Materia() {
		
	}
	
	public Materia(int idMateria, String nombre, int anio, boolean activo) {
		this.idMateria = idMateria;
		this.nombre = nombre;
		this.anio = anio;
		this.activo = activo;
	}
	
	public Materia(String nombre, int anio, boolean activo) {
		this.nombre = nombre;
		this.anio = anio;
		this.activo = activo;
	}

	public int getIdMateria() {return idMateria;}
	
	public void setIdMateria(int idMateria) {this.idMateria = idMateria;}

	public String getNombre() {return nombre;}

	public void setNombre(String nombre) {this.nombre = nombre;}

	public int getAnio() {return anio;}

	public void setAnio(int anio) {this.anio = anio;}

	public boolean isActivo() {return activo;}

	public void setActivo(boolean activo) {this.activo = activo;}
	
	public String toString() {
		String cad = "";
		
//		cad = cad + "ID de la Materia: " + this.idMateria + "\n";
//		cad = cad + "Nombre de la Materia: " + this.nombre + "\n";
//		cad = cad + "Año de la Materia: " + this.anio + "\n";
//		cad = cad + "Activo de la Materia: " + this.activo;
		
		cad = cad + this.nombre + ",   " + this.anio + " Año";
		
		return cad;
	}
}