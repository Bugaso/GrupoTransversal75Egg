package accesoADatos;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import entidades.Alumno;

public class AlumnoData {
	private Connection con = null;

	public AlumnoData() {
		con = Conexion.getConexion();
	}

	public void guardarAlumno(Alumno alumno) {
		String sql = "INSERT INTO alumno (dni, apellido, nombre, fechaNac, activo)" + "VALUES(?, ?, ?, ?, ?)";

		try {
			PreparedStatement ps = this.con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, alumno.getDni());
			ps.setString(2, alumno.getApellido());
			ps.setString(3, alumno.getNombre());
			ps.setDate(4, Date.valueOf(alumno.getFchNacimiento()));
			ps.setBoolean(5, alumno.isActivo());
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();

			if (rs.next()) {
				alumno.setIdAlumno(rs.getInt(1));
				JOptionPane.showMessageDialog(null, "Alumno Guardado Exitosamente");
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Alumno");
		}
	}

	public Alumno buscarAlumno(int idAlumno) {
		Alumno alumno = null;
		String sql = "SELECT dni, apellido, nombre, fechaNac FROM alumno " + "WHERE idAlumno = ? AND activo = 1";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, idAlumno);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				alumno = new Alumno();
				alumno.setIdAlumno(idAlumno);
				alumno.setDni(rs.getInt("dni"));
				alumno.setApellido(rs.getString("apellido"));
				alumno.setNombre(rs.getString("nombre"));
				alumno.setFchNacimiento(rs.getDate("fechaNac").toLocalDate());
				alumno.setActivo(true);
			} /*else {
				JOptionPane.showMessageDialog(null, "No exite el alumno con el ID = " + idAlumno);
			}*/

			ps.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Alumno");
		}

		return alumno;
	}

	public Alumno buscarAlumnoPorDni(int dni) {
//		Alumno alumno = null;
//		String sql = "SELECT idAlumno, dni, apellido, nombre, fechaNac FROM alumno "
//				+ "WHERE dni = ? AND activo = 1";
//		
//		try {
//			PreparedStatement ps = con.prepareStatement(sql);
//			ps.setInt(1, dni);
//			ResultSet rs = ps.executeQuery();
//			
//			if(rs.next()) {
//				alumno = new Alumno();
//				alumno.setIdAlumno(rs.getInt("idAlumno"));
//				alumno.setDni(dni);
//				alumno.setApellido(rs.getString("apellido"));
//				alumno.setNombre(rs.getString("nombre"));
//				alumno.setFchNacimiento(rs.getDate("fechaNac").toLocalDate());
//				alumno.setActivo(true);
//			}else {
//				JOptionPane.showMessageDialog(null, "No existe el alumno con el DNI = " + dni);
//			}
//			
//			ps.close();
//			
//		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Alumno");
//		}
//		
//		return alumno;

		Alumno alumno = null;
		String sql = "SELECT idAlumno, dni, apellido, nombre, fechaNac, activo FROM alumno " + "WHERE dni = ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, dni);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				alumno = new Alumno();
				alumno.setIdAlumno(rs.getInt("idAlumno"));
				alumno.setDni(dni);
				alumno.setApellido(rs.getString("apellido"));
				alumno.setNombre(rs.getString("nombre"));
				alumno.setFchNacimiento(rs.getDate("fechaNac").toLocalDate());
				alumno.setActivo(rs.getBoolean("activo"));
			} /*else {
				JOptionPane.showMessageDialog(null, "No existe el alumno con el DNI = " + dni);
			}*/

			ps.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Alumno");
		}

		return alumno;
	}

	public ArrayList<Alumno> listarAlumnos() {
		ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
		String sql = "SELECT idAlumno, dni, apellido, nombre, fechaNac FROM alumno WHERE activo = 1";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Alumno alumno = new Alumno();
				alumno.setIdAlumno(rs.getInt("idAlumno"));
				alumno.setDni(rs.getInt("dni"));
				alumno.setApellido(rs.getString("apellido"));
				alumno.setNombre(rs.getString("nombre"));
				alumno.setFchNacimiento(rs.getDate("fechaNac").toLocalDate());
				alumno.setActivo(true);
				alumnos.add(alumno);
			}

			ps.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Alumno");
		}

		return alumnos;
	}

	public void modificarAlumno(Alumno alumno) {
//		String sql = "UPDATE alumno SET dni = ?, apellido = ?, nombre = ?, fechaNac = ? " 
//				+ "WHERE idAlumno = ?";
//		
//		try {
//			PreparedStatement ps = con.prepareStatement(sql);
//			
//			ps.setInt(1, alumno.getDni());
//			ps.setString(2, alumno.getApellido());
//			ps.setString(3, alumno.getNombre());
//			ps.setDate(4, Date.valueOf(alumno.getFchNacimiento()));
//			ps.setInt(5, alumno.getIdAlumno());
//			
//			int exito = ps.executeUpdate();
//			
//			if(exito == 1) {
//				JOptionPane.showMessageDialog(null, "Alumno modificado exitosamente");
//			}
//		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Alumno");
//		}

		String sql = "UPDATE alumno SET dni = ?, apellido = ?, nombre = ?, fechaNac = ?, activo = ? "
				+ "WHERE idAlumno = ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, alumno.getDni());
			ps.setString(2, alumno.getApellido());
			ps.setString(3, alumno.getNombre());
			ps.setDate(4, Date.valueOf(alumno.getFchNacimiento()));
			ps.setBoolean(5, alumno.isActivo());
			ps.setInt(6, alumno.getIdAlumno());

			int exito = ps.executeUpdate();

			if (exito == 1) {
				JOptionPane.showMessageDialog(null, "Alumno Modificado Exitosamente");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Alumno");
		}
	}

	public void eliminarAlumno(int id) {
		String sql = "UPDATE alumno SET activo = 0 WHERE idAlumno = ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, id);

			int exito = ps.executeUpdate();

			if (exito == 1) {
				JOptionPane.showMessageDialog(null, "Alumno eliminado exitosamente");
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Alumno");
		}
	}
}