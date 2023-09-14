package app;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Usuario;

public class Demo02 {
	//Actualiza los datos de usuario
	public static void main(String[] args) {
		//Llamar a la conexión
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		
		//Crear un manejador de las entidades
		EntityManager manager = fabrica.createEntityManager();
		
		//Objeto a modificar
		Usuario u = new Usuario();
		u.setCod_usua(3);
		u.setNom_usua("Zoila");
		u.setApe_usua("Toro");
		u.setUsr_usua("ztoro@gmail.com");
		u.setCla_usua("5656565656");
		u.setFna_usua("2008/03/07");
		u.setIdtipo(1);
		u.setEst_usua(1);
		
		//Si queremos regisrtrar, actualizar o eliminar - > transa..
		try {
			manager.getTransaction().begin();
			manager.merge(u);
			manager.getTransaction().commit();		
			System.out.println("Actualización, OK");
		} catch (Exception e) {
			System.out.println("Error: " + e.getCause().getMessage());
		}
		
		manager.close();
	}
}
