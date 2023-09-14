package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Categoria;
import model.Producto;
import model.Proveedor;
import model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class FrmManteProd extends JFrame {

	private JPanel contentPane;

	private JTextArea txtSalida;
	private JTextField txtCodigo;
	private JComboBox cboCategorias;
	private JComboBox cboProveedores;
	private JTextField txtDescripcion;
	private JTextField txtStock;
	private JTextField txtPrecio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmManteProd frame = new FrmManteProd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmManteProd() {
		setTitle("Mantenimiento de Productos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}
		});
		btnRegistrar.setBounds(324, 29, 89, 23);
		contentPane.add(btnRegistrar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 171, 414, 143);
		contentPane.add(scrollPane);

		txtSalida = new JTextArea();
		scrollPane.setViewportView(txtSalida);

		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listado();
			}
		});
		btnListado.setBounds(177, 322, 89, 23);
		contentPane.add(btnListado);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(122, 11, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);

		JLabel lblCodigo = new JLabel("Id. Producto :");
		lblCodigo.setBounds(10, 14, 102, 14);
		contentPane.add(lblCodigo);

		cboCategorias = new JComboBox();
		cboCategorias.setBounds(122, 70, 106, 22);
		contentPane.add(cboCategorias);

		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setBounds(10, 74, 102, 14);
		contentPane.add(lblCategora);

		JLabel lblNomProducto = new JLabel("Nom. Producto :");
		lblNomProducto.setBounds(10, 45, 102, 14);
		contentPane.add(lblNomProducto);

		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(122, 42, 144, 20);
		contentPane.add(txtDescripcion);

		JLabel lblStock = new JLabel("Stock:");
		lblStock.setBounds(10, 106, 102, 14);
		contentPane.add(lblStock);

		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(122, 103, 77, 20);
		contentPane.add(txtStock);

		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(10, 134, 102, 14);
		contentPane.add(lblPrecio);

		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(122, 131, 77, 20);
		contentPane.add(txtPrecio);

		JLabel lblProveedores = new JLabel("Proveedor:");
		lblProveedores.setBounds(230, 106, 102, 14);
		contentPane.add(lblProveedores);

		cboProveedores = new JComboBox();
		cboProveedores.setBounds(300, 104, 120, 22);
		contentPane.add(cboProveedores);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar();
			}
		});
		btnBuscar.setBounds(324, 63, 89, 23);
		contentPane.add(btnBuscar);

		llenaCombo();
	}

	void llenaCombo() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager manager = fabrica.createEntityManager();
		
		String sql = "select c from Categoria c";
		List<Categoria> lstCategorias = manager.createQuery(sql, Categoria.class).getResultList();
		
		cboCategorias.addItem("--Selecciona--");
		for (Categoria c : lstCategorias) {
			cboCategorias.addItem(c.getDescripcion());	
		}
				
		String sql2 = "select pr from Proveedor pr";
		List<Proveedor> lstProveedores = manager.createQuery(sql2, Proveedor.class).getResultList();
		
		cboProveedores.addItem("--Selecciona--");
		for (Proveedor pr : lstProveedores) {
			cboProveedores.addItem(pr.getNombre_rs());	
		}
		
				
		manager.close();
	}

	void registrar() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager manager = fabrica.createEntityManager();
		
		String id_prod = txtCodigo.getText();
		String des_prod = txtDescripcion.getText();
		int stk_prod = Integer.parseInt(txtStock.getText());
		double pre_prod = Double.parseDouble(txtPrecio.getText());
		int idcategoria = cboCategorias.getSelectedIndex();
		//Si es tipo boolean (que contiene 2 valores verdadero, falso)
		int est_prod = 1; //1 -> true  0 -> false;
		int idproveedor = cboProveedores.getSelectedIndex();
		
			Producto p = new Producto();
			p.setId_prod(id_prod);
			p.setDes_prod(des_prod);
			p.setStk_prod(stk_prod);
			p.setPre_prod(pre_prod);
			p.setIdcategoria(idcategoria);
			p.setEst_prod(est_prod);
			p.setIdproveedor(idproveedor);
				
			try {
				manager.getTransaction().begin();
				manager.persist(p);
				manager.getTransaction().commit();		
				aviso("Registro, OK");
			} catch (Exception e) {
				aviso("Error: Al grabar, verificar datos");
			}
			
			manager.close();
	}
	
	void aviso(String Producto) {
		JOptionPane.showMessageDialog(this, Producto, "Aviso del sistema", JOptionPane.INFORMATION_MESSAGE);
	}

	void listado() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager manager = fabrica.createEntityManager();
		
		String sql = "select p from Producto p";
		List<Producto> lstProductos = manager.createQuery(sql, Producto.class).getResultList();
		
		for (Producto p : lstProductos) {
			imprimir("Código.........: " + p.getId_prod());
			imprimir("Descripcion....: " + p.getDes_prod());
			imprimir("Stock..........: " + p.getStk_prod());
			imprimir("Precio.........: " + p.getPre_prod());
			imprimir("Categoría......: " + p.getIdcategoria() + " " + p.getObjCategoria().getDescripcion());
			imprimir("Estado.........: " + p.getEst_prod());
			imprimir("Proveedor......: " + p.getIdproveedor() + " " + p.getObjProveedor().getNombre_rs());
			imprimir("-------------------------------");
		}
				
		manager.close();
	}
		
	void imprimir(String Producto) {
		txtSalida.append(Producto + "\n");
	}
	
	void buscar() {
		// TODO Auto-generated method stub
		
	}
}
