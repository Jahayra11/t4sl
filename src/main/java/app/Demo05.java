package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Usuario;

public class Demo05 {
	//Listado de Usuarios
	public static void main(String[] args) {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager manager = fabrica.createEntityManager();
		
		//Listado  -->  select * from tb_xxx
		String sql = "select u from Usuario u";
		List<Usuario> lstUsuarios = manager.createQuery(sql, Usuario.class).getResultList();
		
		for (Usuario u : lstUsuarios) {
			System.out.println("Código....: " + u.getCod_usua());
			System.out.println("Nombre....: " + u.getNom_usua() + " " + u.getApe_usua());
			System.out.println("Tipo......: " + u.getIdtipo() + " " + u.getObjTipo().getDescripcion());
			System.out.println("-------------------------------");
		}
				
		manager.close();
	}
}
