package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Usuario;

public class Demo03 {
	//Encuentra y muestra los datos de usuario, según su código
	public static void main(String[] args) {
		//Llamar a la conexión
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		
		//Crear un manejador de las entidades
		EntityManager manager = fabrica.createEntityManager();
		
		//Objeto a modificar
		Usuario u = new Usuario();
		
		//Si queremos registrar, actualizar o eliminar - > transa..
		try {
			u = manager.find(Usuario.class, 1);
				if(u == null) {
					System.out.println("Usuario no existe");
				} else {
					System.out.println(u);
				}
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getCause().getMessage());
		}
		
		manager.close();
	}
}
