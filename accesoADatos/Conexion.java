package accesoADatos;

import java.sql.*;

import javax.swing.JOptionPane;

public class Conexion {
	private static final String URL = "jdbc:mariadb://localhost/";
	private static final String DB = "universidadulp";
	private static final String USUARIO = "root";
	private static final String PASSWORD = "";
	private static Connection connection;

	private Conexion() {

	}

	public static Connection getConexion() {

		if (connection == null) {
			try {
				Class.forName("org.mariadb.jdbc.Driver");

				connection = DriverManager.getConnection(URL + DB, USUARIO, PASSWORD);

//				JOptionPane.showMessageDialog(null, "Conexión Exitosa!!!");

			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Error al cargar los drivers");
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Error al conectarse a la Base de Datos");
			}
		}

		return connection;
	}
}