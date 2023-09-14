package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Producto;

public class Demo06 {
	//Listado de Productos
	public static void main(String[] args) {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager manager = fabrica.createEntityManager();
		
		//Listado  -->  select * from tb_xxx
		String sql = "select p from Producto p";
		List<Producto> lstProductos = manager.createQuery(sql, Producto.class).getResultList();
		
		for (Producto p : lstProductos) {
			System.out.println("Código.........: " + p.getId_prod());
			System.out.println("Descripcion....: " + p.getDes_prod());
			System.out.println("Stock..........: " + p.getStk_prod());
			System.out.println("Precio.........: " + p.getPre_prod());
			System.out.println("Categoría......: " + p.getIdcategoria() + " " + p.getObjCategoria().getDescripcion());
			System.out.println("Estado.........: " + p.getEst_prod());
			System.out.println("Proveedor......: " + p.getIdproveedor() + " " + p.getObjProveedor().getNombre_rs());
			System.out.println("-------------------------------");
		}
				
		manager.close();
	}
}
