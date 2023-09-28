package accesoADatos;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import entidades.Materia;

public class MateriaData {
	private Connection con = null;

	public MateriaData() {
		this.con = Conexion.getConexion();
	}

	public void guardarMateria(Materia materia) {
		String sql = "INSERT INTO materia (nombre, añoMateria, activo) VALUES( ?, ?, ?)";

		try {
			PreparedStatement ps = this.con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, materia.getNombre());
			ps.setInt(2, materia.getAnio());
			ps.setBoolean(3, materia.isActivo());
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();

			if (rs.next()) {
				materia.setIdMateria(rs.getInt(1));
				JOptionPane.showMessageDialog(null, "Materia Guardado Exitosamente");
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Materia");
		}
	}

	public Materia buscarMateria(int idMateria) {
		Materia materia = null;
		String sql = "SELECT  nombre, añoMateria FROM materia WHERE idMateria = ? AND activo = 1";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, idMateria);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				materia = new Materia();
				materia.setIdMateria(idMateria);
				materia.setNombre(rs.getString("nombre"));
				materia.setAnio(rs.getInt("añoMateria"));
				materia.setActivo(true);
			} else {
				JOptionPane.showMessageDialog(null, "No exite la materia con el ID = " + idMateria);
			}

			ps.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Materia");
		}

		return materia;
	}

	public Materia buscarMateriaPorNombre(String nombreMat) {
//		Materia materia = null;
//		String sql = "SELECT idMateria, nombre, añoMateria FROM materia WHERE nombre = ? AND activo = 1";
//		
//		try {
//			PreparedStatement ps = con.prepareStatement(sql);
//			ps.setString(1, nombreMat);
//			ResultSet rs = ps.executeQuery();
//			
//			if(rs.next()) {
//				materia = new Materia();
//				materia.setIdMateria(rs.getInt("idMateria"));
//				materia.setNombre(rs.getString("nombre"));
//				materia.setAnio(rs.getInt("añoMateria"));
//				materia.setActivo(true);
//			}else {
//				JOptionPane.showMessageDialog(null, "No exite la materia con el nombre = " + nombreMat);
//			}
//			
//			ps.close();
//			
//		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Materia");
//		}
//		      
//       return materia;

		Materia materia = null;
		String sql = "SELECT idMateria, nombre, añoMateria, activo FROM materia WHERE nombre = ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, nombreMat);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				materia = new Materia();
				materia.setIdMateria(rs.getInt("idMateria"));
				materia.setNombre(rs.getString("nombre"));
				materia.setAnio(rs.getInt("añoMateria"));
				materia.setActivo(rs.getBoolean("activo"));
			} /*else {
				JOptionPane.showMessageDialog(null, "No exite la materia con el nombre = " + nombreMat);
			}*/

			ps.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Materia");
		}

		return materia;
	}

	public void modificarMateria(Materia materia) {
//		String sql = "UPDATE materia SET nombre = ?, añoMateria = ? WHERE idMateria = ?";
//		
//		try {
//			PreparedStatement ps = con.prepareStatement(sql);
//			
//			ps.setString(1, materia.getNombre());
//			ps.setInt(2, materia.getAnio());
//			ps.setInt(3, materia.getIdMateria());
//			
//			int exito = ps.executeUpdate();
//			
//			if(exito == 1) {
//				JOptionPane.showMessageDialog(null, "Materia modificada exitosamente");
//			}
//		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Materia");
//		}

		String sql = "UPDATE materia SET nombre = ?, añoMateria = ?, activo = ? WHERE idMateria = ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, materia.getNombre());
			ps.setInt(2, materia.getAnio());
			ps.setBoolean(3, materia.isActivo());
			ps.setInt(4, materia.getIdMateria());

			int exito = ps.executeUpdate();

			if (exito == 1) {
				JOptionPane.showMessageDialog(null, "Materia modificada exitosamente");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Materia");
		}
	}

	public void eliminarMateria(int id) {
		String sql = "UPDATE materia SET activo = 0 WHERE idMateria = ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, id);

			int exito = ps.executeUpdate();

			if (exito == 1) {
				JOptionPane.showMessageDialog(null, "Materia eliminada exitosamente");
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Materia");
		}
	}

	public ArrayList<Materia> listarMateria() {
		ArrayList<Materia> materias = new ArrayList<Materia>();
		String sql = "SELECT idMateria, nombre, añoMateria FROM materia WHERE activo = 1";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Materia materia = new Materia();
				materia.setIdMateria(rs.getInt("idMateria"));
				materia.setNombre(rs.getString("nombre"));
				materia.setAnio(rs.getInt("añoMateria"));
				materia.setActivo(true);
				materias.add(materia);
			}

			ps.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Materia");
		}

		return materias;
	}
}