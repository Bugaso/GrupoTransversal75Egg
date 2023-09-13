import java.sql.*;
import java.time.LocalDate;
import accesoADatos.*;
import entidades.*;
import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con = Conexion.getConexion();
		Alumno a = new Alumno(10, "Rodriguez", "Angelo Uriel", 123123122, LocalDate.of(1980, 4, 25), true);
		Alumno a2 = new Alumno(11, "Ocampo", "Alex", 333, LocalDate.of(2000, 7, 31), true);
		Alumno a3 = new Alumno(12, "Muñoz", "Nicolas", 111, LocalDate.of(1998, 10, 10), true);
//		AlumnoData ad = new AlumnoData();
//		
//		ad.guardarAlumno(a);
//		ad.guardarAlumno(a2);
//		ad.guardarAlumno(a3);
//		ad.modificarAlumno(a);
//		ad.eliminarAlumno(1);
		
//		Alumno aEncont = ad.buscarAlumnoPorDni(111);
//		if (aEncont != null) {
//			System.out.println(aEncont);
//		}
		
//		ArrayList<Alumno> alumnosEnct = ad.listarAlumnos();
//		if(alumnosEnct.size() != 0) {
//			for(int i=0; i<alumnosEnct.size(); i++) {
//				System.out.println(alumnosEnct.get(i));
//			}
//		}
		
		Materia historia= new Materia(1, "Historia", 2, true);
		Materia matematica= new Materia(2, "Matemática", 2, true);
		Materia filosofia= new Materia(3, "Filosofía", 1, true);
		Materia geografia= new Materia(4,"Geografía", 2, true);
		Materia biologia= new Materia(5, "Biología", 1, true);
//		MateriaData mate = new MateriaData();
//		
//		mate.guardarMateria(historia);
//		mate.guardarMateria(matematica);
//		mate.guardarMateria(filosofia);
//		mate.guardarMateria(geografia);
//		mate.guardarMateria(biologia);
		
		Inscripcion i1 = new Inscripcion(a, biologia, 7);
		Inscripcion i2 = new Inscripcion(a, historia, 6);
		Inscripcion i3 = new Inscripcion(a2, filosofia, 10);
		Inscripcion i4 = new Inscripcion(a2, historia, 9);
		Inscripcion i5 = new Inscripcion(a3, matematica, 5);
		Inscripcion i6 = new Inscripcion(a3, geografia, 9);
		InscripcionData id = new InscripcionData();
		
		id.guardarInscripcion(i1);
		id.guardarInscripcion(i2);
		id.guardarInscripcion(i3);
		id.guardarInscripcion(i4);
		id.guardarInscripcion(i5);
		id.guardarInscripcion(i6);
	}
}