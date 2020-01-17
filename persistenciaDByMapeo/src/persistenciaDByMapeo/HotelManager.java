package persistenciaDByMapeo;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HotelManager {
	
	static SessionFactory sesFact;
	
	private static SessionFactory getSessionFactory() {
		sesFact = new Configuration().addAnnotatedClass(Clientes.class).configure().buildSessionFactory();

		return sesFact;
	}

	public static void createCliente(Clientes cli) {
		Session sessionObj = getSessionFactory().openSession();
		Transaction transObj = sessionObj.beginTransaction();
		sessionObj.save(cli);
		transObj.commit();
		sessionObj.close();
		System.out.println("Cliente " + cli.getNombre()+" "+cli.getApellidos() + " insertado correctamente");
	}
	
	 public static List readCliente() {
		 Session sessionObj = getSessionFactory().openSession();
		 String query = "FROM Clientes";
		 List resultado =  sessionObj.createQuery(query).list();
		 sessionObj.close();
		 return resultado;
	}
	
	 public static void updateCliente(Clientes cli) {
		 Session sessionObj = getSessionFactory().openSession();
		 Transaction transObj = sessionObj.beginTransaction();
		 Clientes clienteBD = (Clientes)sessionObj.load(Clientes.class, cli.getIdCliente());
		 clienteBD.setNombre(cli.getNombre());
		 clienteBD.setApellidos(cli.getApellidos());
		 clienteBD.setEmail(cli.getEmail());
		 clienteBD.setDni(cli.getDni());
		 clienteBD.setClave(cli.getClave());
		 transObj.commit();
		 sessionObj.close();
		 System.out.println("Actualizado correctamente");
	}
	 
	 public static void deleteCliente(Clientes cli) {
		 Session sessionObj = getSessionFactory().openSession();
		 Transaction transObj = sessionObj.beginTransaction();
		 Clientes peliculaBD = (Clientes)
		 sessionObj.load(Clientes.class, cli.getIdCliente());
		 sessionObj.delete(peliculaBD);
		 transObj.commit();
		 sessionObj.close();
		 System.out.println("Eliminado correctamente");
	}
	 
	 public static void main(String[] args) {
		//Creamos los clientes
		Clientes cli1 = new Clientes("Laura", "Muñoz", "laura@grupostudium.com","30257541L","clavedeclienteLaura");
		Clientes cli2 = new Clientes("José", "Platero", "jose@grupostudium.com","30297613J","clavedeclienteJose");
		
		//Los añadimos a la base de datos
		createCliente(cli1);
		createCliente(cli2);
		
		//Mostramos los clientes creados
		Iterator clientes = readCliente().iterator();
		while(clientes.hasNext()) {
			Clientes cliente = (Clientes)clientes.next();
			System.out.println(cliente.getNombre()+ " "+cliente.getApellidos()+" - "+cliente.getDni());
		}
		
		//Cambiamos el apellido del cliente Laura
		cli1.setApellidos("Palomo");
		updateCliente(cli1);
		
		//Eliminamos el cliente José
		deleteCliente(cli2);
		
		//Mostramos los clientes para ver los cambios
		clientes = readCliente().iterator();
		while(clientes.hasNext()) {
			Clientes cliente = (Clientes)clientes.next();
			System.out.println(cliente.getNombre()+ " "+cliente.getApellidos()+" - "+cliente.getDni());
		}
	}
}
