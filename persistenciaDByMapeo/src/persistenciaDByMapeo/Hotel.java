package persistenciaDByMapeo;

public class Hotel {

	public static void main(String[] args) {
		
		ClientePersistencia.createTableCliente();
		
		int id = ClientePersistencia.createCliente("Javier", "Escudero", "javier@grupostudium.com", "12345678Z",
				"Studium2018");
		System.out.println(ClientePersistencia.readCliente(id, "apellidos"));
 
		ClientePersistencia.updateCliente(id, "apellidos", "Escudero Fernández");
		System.out.println(ClientePersistencia.readCliente(id, "apellidos"));
 
		ClientePersistencia.deleteCliente(id);
		System.out.println(ClientePersistencia.readCliente(id, "apellidos"));
	}

}
