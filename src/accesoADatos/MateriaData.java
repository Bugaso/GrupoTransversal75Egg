package accesoADatos;

import entidades.Alumno;
import entidades.Materia;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class MateriaData {
    private Connection con = null;

    
    public MateriaData() {
		con = Conexion.getConexion();
	}
	
    public void guardarMateria(Materia materia) {
		String sql = "INSERT INTO materia ( nombre,añoMateria, activo)" 
				+ "VALUES( ?, ?, ?)";
                
        try {
			PreparedStatement ps = this.con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
		
			ps.setString(1, materia.getNombre());
			ps.setInt(2, materia.getAnio());
			ps.setBoolean(3, materia.isActivo());
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			
			if(rs.next()) {
				materia.setIdMateria(rs.getInt(1));
				JOptionPane.showMessageDialog(null, "Materia Guardado exitosamente");
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al acceder a la materia");
		}        
  
    
}
   public Materia buscarMateria(int id) {
		Materia materia = null;
		String sql = "SELECT  nombre, añoMateria FROM materia "
				+ "WHERE idMateria = ? AND activo = 1"; 
                
                
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				materia = new Materia();
				materia.setIdMateria(id);
				materia.setNombre(rs.getString("nombre"));
				materia.setAnio(rs.getInt("añoMateria"));
				materia.setActivo(true);
			}else {
				JOptionPane.showMessageDialog(null, "No exite la materia con el ID = " + id);
			}
			
			ps.close();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al acceder a la materia");
		}
		      
        return materia;
}
   
   public void modificarMateria(Materia materia) {
		String sql = "UPDATE materia SET nombre = ?, añoMateria=?" 
				+ "WHERE idMateria = ?";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			
			
			ps.setString(1, materia.getNombre());
			ps.setInt(2, materia.getAnio());
			ps.setInt(3, materia.getIdMateria());
			
			int exito = ps.executeUpdate();
			
			if(exito == 1) {
				JOptionPane.showMessageDialog(null, "La materia se ha Modificado exitosamente");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al acceder a la materia");
		}
	}
   public void eliminarMateria(int id) {
		String sql = "UPDATE materia SET activo = 0 WHERE idMateria = ?";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			int exito = ps.executeUpdate();
			
			if(exito == 1) {
				JOptionPane.showMessageDialog(null, "La materiase ha Eliminado exitosamente");
			}
			
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al acceder a la materia");
		}
	}
   	public ArrayList<Materia> listarMateria(){
		ArrayList<Materia> materias = new ArrayList<Materia>();
		String sql = "SELECT idMateria, nombre,añoMateria  FROM materia WHERE activo = 1";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Materia materia = new Materia();
				materia.setIdMateria(rs.getInt("idMateria"));
				materia.setNombre(rs.getString("nombre"));
				materia.setAnio(rs.getInt("añoMateria"));
				materia.setActivo(true);
				materias.add(materia);
			}
			
			ps.close();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Alumno");
		}
		
		return materias;
	}
   
   
   
   }