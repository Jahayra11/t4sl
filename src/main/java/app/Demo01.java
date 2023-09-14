package app;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Usuario;

public class Demo01 {
	//Realizar el registro de un nuevo usuario usando valores fijos
	public static void main(String[] args) {
		//Llamar a la conexión
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		
		//Crear un manejador de las entidades
		EntityManager manager = fabrica.createEntityManager();
		
		//Objeto a grabar
		Usuario u = new Usuario();
		u.setCod_usua(162);
		u.setNom_usua("Maria");
		u.setApe_usua("Lopez");
		u.setUsr_usua("mlopez");
		u.setCla_usua("5656565656");
		u.setFna_usua("2005/05/07");
		u.setIdtipo(1);
		u.setEst_usua(1);
		
		//Si queremos registrar, actualizar o eliminar - > transa..
		manager.getTransaction().begin();
		manager.persist(u);
		manager.getTransaction().commit();		
		System.out.println("Registro, OK");
		manager.close();
	}
}
