package persistenciaDByMapeo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientePersistencia {
	
	static final String CONNECTOR = "com.mysql.jdbc.Driver";
	static final String URL = "jdbc:mysql://localhost:3306/hotel?autoReconnect=true&useSSL=false";
	static final String LOGIN = "root";
	static final String PASS = "Studium2018;";
	
	public static void createTableCliente() {
		/* Creamos la tabla Cliente si no existe */
		
		String sentenciaCrearBD = "CREATE TABLE IF NOT EXISTS hotel.clientes (idCliente INT NOT NULL AUTO_INCREMENT, nombre VARCHAR(45) NOT NULL, apellidos VARCHAR(60) NOT NULL, email VARCHAR(60) NOT NULL, dni VARCHAR(45) NOT NULL, clave VARCHAR(45) NOT NULL, PRIMARY KEY (idCliente));";
		
		try {
			Class.forName(CONNECTOR);
			Connection connect = DriverManager.getConnection(URL, LOGIN, PASS);
			
			connect.createStatement().executeUpdate(sentenciaCrearBD);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static int createCliente (String nombre, String apellidos, String email, String dni, String clave) {
		/* Devuelve el id del nuevo cliente */
		
		String sentenciaCrearCliente = "INSERT INTO clientes (nombre, apellidos, email, dni, clave) VALUES('" + nombre + "', '" + apellidos + "', '" + email + "', '" + dni + "', '" + clave + "');";
		String sentenciaRecuperarId = "SELECT LAST_INSERT_ID();";
		Integer idCliente=-1;
		
		try {
			Class.forName(CONNECTOR);
			Connection connect = DriverManager.getConnection(URL, LOGIN, PASS);
			Statement st = connect.createStatement();
			st.executeUpdate(sentenciaCrearCliente);
			
			ResultSet rs = st.executeQuery(sentenciaRecuperarId);
			rs.next();
			idCliente = rs.getInt(1);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return idCliente;
	}
	public static String readCliente(int idCliente, String campo) {
		/* Devuelve el valor de la columna "campo" del cliente identificado por "idCliente" */
		
		String sentenciaRecuperarCampo = "SELECT "+ campo +" FROM clientes WHERE idCliente = '" + idCliente + "';";
		String valor="";
		
		try {
			Class.forName(CONNECTOR);
			Connection connect = DriverManager.getConnection(URL, LOGIN, PASS);
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery(sentenciaRecuperarCampo);
			rs.next();
			valor = rs.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Los datos introducidos no corresponden a ningún cliente");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return valor;
	}
 
	public static boolean updateCliente(int idCliente, String campo, String nuevoValor) {
		/* Actualiza el valor de la columna "campo" del cliente identificado por "idCliente". Devuelve true si se ha logrado actualizar. */
		
		String sentenciaModificarCampo = "UPDATE clientes SET "+ campo +"='"+nuevoValor+"' WHERE idCliente = '" + idCliente + "';";
		Boolean correcto=false;
		
		try {
			Class.forName(CONNECTOR);
			Connection connect = DriverManager.getConnection(URL, LOGIN, PASS);
			connect.createStatement().executeUpdate(sentenciaModificarCampo);
			correcto = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return correcto;
	}
 
	public static boolean deleteCliente(int idCliente) {
		/* Elimina el cliente identificado por "idCliente". Devuelve true si se ha logrado eliminar. */
		
		String sentenciaEliminarCliente = "DELETE FROM clientes WHERE (idCliente='"+idCliente+"');";
		Boolean correcto=false;
		
		try {
			Class.forName(CONNECTOR);
			Connection connect = DriverManager.getConnection(URL, LOGIN, PASS);
			connect.createStatement().executeUpdate(sentenciaEliminarCliente);
			correcto = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return correcto;
	}
}
