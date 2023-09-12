import java.sql.*;
import java.time.LocalDate;
import accesoADatos.*;
import entidades.Alumno;
import entidades.Materia;
import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con = Conexion.getConexion();
	Alumno a = new Alumno(1, "Rodriguez", "Angelo Uriel", 123123122, LocalDate.of(1980, 4, 25), true);
		Alumno a2 = new Alumno("Ocampo", "Alex", 333, LocalDate.of(2000, 7, 31), true);
		Alumno a3 = new Alumno("Muñoz", "Nicolas", 111, LocalDate.of(1998, 10, 10), true);
		AlumnoData ad = new AlumnoData();
		
		ad.guardarAlumno(a2);
		ad.guardarAlumno(a3);
		ad.modificarAlumno(a);
		ad.eliminarAlumno(1);
		
		Alumno aEncont = ad.buscarAlumnoPorDni(111);
		if (aEncont != null) {
			System.out.println(aEncont);
		}
		
		ArrayList<Alumno> alumnosEnct = ad.listarAlumnos();
		if(alumnosEnct.size() != 0) {
			for(int i=0; i<alumnosEnct.size(); i++) {
			System.out.println(alumnosEnct.get(i));
			}
		}
	


Materia matematica= new Materia("matematica", 2, true);
Materia filosofia= new Materia("filosofia", 1, true);
Materia historia= new Materia("historia", 2, true);
Materia geografia= new Materia("geografia", 2, true);
Materia biologia= new Materia("biologia", 1, true);

    MateriaData mate= new MateriaData();
    mate.guardarMateria(historia);
    mate.guardarMateria(matematica);
    mate.guardarMateria(filosofia);
    mate.guardarMateria(geografia);
    mate.guardarMateria(biologia);
    }
}
        
