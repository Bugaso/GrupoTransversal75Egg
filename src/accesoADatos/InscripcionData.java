package accesoADatos;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import entidades.*;

public class InscripcionData {
	private Connection con;
	private AlumnoData aluData;
	private MateriaData matData;
	
	public InscripcionData() {
		this.con = Conexion.getConexion();
		this.aluData = new AlumnoData();
		this.matData = new MateriaData();
	}
	
	public void guardarInscripcion(Inscripcion inscripcion) {
		String sql = "INSERT INTO inscripcion (idAlumno, idMateria, nota) VALUES(?, ?, ?)";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			ps.setInt(1, inscripcion.getAlumno().getIdAlumno());
			ps.setInt(2, inscripcion.getMateria().getIdMateria());
			ps.setDouble(3, inscripcion.getNota());
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			
			if(rs.next()) {
				inscripcion.setIdInscripcion(rs.getInt(1));
				JOptionPane.showMessageDialog(null, "Inscripción guardada exitosamnete!!!");
			}
			
			ps.close();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripción");
		}
	}
	
	public ArrayList<Inscripcion> obtenerInscripcione(){
		ArrayList<Inscripcion> inscripciones = new ArrayList<Inscripcion>();
		
		String sql = "SELECT idInscripcion, idAlumno, idMateria, nota FROM inscripcion";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Inscripcion inscripcion = new Inscripcion();
				inscripcion.setIdInscripcion(rs.getInt("idInscripcion"));
//				inscripcion.getAlumno().setIdAlumno(rs.getInt("idAlumno"));
				inscripcion.setAlumno(aluData.buscarAlumno(rs.getInt("idAlumno")));
//				inscripcion.getMateria().setIdMateria(rs.getInt("idMateria"));
				inscripcion.setMateria(matData.buscarMateria(rs.getInt("idMateria")));
				inscripcion.setNota(rs.getDouble("nota"));
				inscripciones.add(inscripcion);
			}
			
			ps.close();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripción");
		}
		
		return inscripciones;
	}
	
	public ArrayList<Inscripcion> obtenerInscripcionesPorAlumno(int id){
		ArrayList<Inscripcion> inscripciones = new ArrayList<Inscripcion>();
		String sql = "SELECT idInscripcion, idAlumno, idMateria, nota WHERE idAlumno = ?";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Inscripcion inscripcion = new Inscripcion();
				inscripcion.setIdInscripcion(rs.getInt("idInscripcion"));
//				inscripcion.getAlumno().setIdAlumno(rs.getInt("idAlumno"));
				inscripcion.setAlumno(aluData.buscarAlumno(rs.getInt("idAlumno")));
//				inscripcion.getMateria().setIdMateria(rs.getInt("idMateria"));
				inscripcion.setMateria(matData.buscarMateria(rs.getInt("idMateria")));
				inscripcion.setNota(rs.getDouble("nota"));
				inscripciones.add(inscripcion);
			}
			
			ps.close();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripción");
		}
		
		return inscripciones;
	}
	
	public ArrayList<Materia> obtenerMateriasCursadas(int id){
		ArrayList<Materia> materias = new ArrayList<Materia>();
		String sql = "SELECT inscripcion.idMateria, nombre, añoMateria FROM inscripcion "
				+ "JOIN materia (inscripcion.idMateria = materia.idMateria) WHERE idAlumno = ?";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Materia materia = new Materia();
				materia.setIdMateria(rs.getInt("inscripcion.idMateria"));
				materia.setNombre(rs.getString("nombre"));
				materia.setAnio(rs.getInt("añoMateria"));
				materia.setActivo(true);
				materias.add(materia);
			}
			
			ps.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripción y/o tabla Materia");
		}
		
		return materias;
	}
	
	public ArrayList<Materia> obtenerMateriasNOCursadas(int id){
		ArrayList<Materia> materias = new ArrayList<Materia>();
		String sql = "SELECT DISTINTC inscripcion.idMateria, nombre, añoMateria FROM inscripcion "
				+ "JOIN materia ON (inscripcion.idMateria = materia.idMateria) WHERE idAlumno != ?";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Materia materia = new Materia();
				materia.setIdMateria(rs.getInt("inscripcion.idMateria"));
				materia.setNombre(rs.getString("nombre"));
				materia.setAnio(rs.getInt("añoMateria"));
				materia.setActivo(true);
				materias.add(materia);
			}
			
			ps.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripción y/o tabla Materia");
		}
		
		return materias;
	}
	
	public void borrarInscripcionMateriaAlumno(int idAlumno, int idMateria) {
		String sql = "DELETE FROM inscripcion WHERE idAlumno = ? AND idMateria = ?";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, idAlumno);
			ps.setInt(2, idMateria);
			
			int exito = ps.executeUpdate();
			
			if(exito == 1) {
				JOptionPane.showMessageDialog(null, "Inscripción eliminada exitosamente!!!");
			}else {
				JOptionPane.showMessageDialog(null, "No se puede eliminar la inscripción porque el Alumno con el ID = " + idAlumno + " no esta inscripto a la materia con el ID = " + idMateria);
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripción");
		}
	}
	
	public void actualizarNota(int idAlumno, int idMateria, double nota) {
		String sql = "UPDATE incripcion SET nota = ? WHERE idAlumno = ? AND idMateria = ?";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setDouble(1, nota);
			ps.setInt(2, idAlumno);
			ps.setInt(3, idMateria);
			
			int exito = ps.executeUpdate();
			
			if(exito == 1) {
				JOptionPane.showMessageDialog(null, "Nota actualizada");
			}else {
				JOptionPane.showMessageDialog(null, "El alumno con el ID = " + idAlumno + " no esta inscripto a la materia con el ID = " + idMateria);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripción");
		}
	}
	
	public ArrayList<Alumno> obtenerAlumnosXMateria(int idMateria){
		ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
		String sql = "SELECT a.idAlumno, a.dni, a.apellido, a.nombre, a.fechaNac "
				+ "FROM alumno a JOIN inscripcion i ON (a.idAlumno = i.idAlumno) "
				+ "WHERE i.idMateria = ?";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, idMateria);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Alumno alumno = new Alumno();
				alumno.setIdAlumno(rs.getInt("a.idAlumno"));
				alumno.setDni(rs.getInt("a.dni"));
				alumno.setApellido(rs.getString("a.apellido"));
				alumno.setNombre(rs.getString("a.nombre"));
				alumno.setFchNacimiento(rs.getDate("a.fechaNac").toLocalDate());
				alumno.setActivo(true);
				alumnos.add(alumno);
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripción y/o Alumno");
		}
		
		return alumnos;
	}
}