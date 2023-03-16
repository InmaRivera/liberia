package es.studium.MVC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.ServletException;

public class Libreria_pra 
{
	// Pool de conexiones a la base de datos

	String libroSeleccionado ="";
	Connection conn = null;
	Statement statement = null;
	ResultSet rs = null;

	static ArrayList<LibroPra> listadoLibros = new ArrayList<LibroPra>();
	static ArrayList<Pedidos> listadoPedidos = new ArrayList<Pedidos>();
	static ArrayList<Editoriales> listadoEditorial = new ArrayList<Editoriales>();
	static ArrayList<Autores> listadoAutor = new ArrayList<Autores>();
	public Libreria_pra() {
		super();

	}
	//Alertas
	//	public class AlertifyExample {
	//	    public static void main(String[] args) {
	//	        Alertify.alert("Este es un mensaje de alerta");
	//	        Alertify.success("Este es un mensaje de éxito");
	//	        Alertify.error("Este es un mensaje de error");
	//	    }
	//	}

	//consulta de libros
	public void cargarDatos()
	{
		listadoLibros.clear();
		listadoPedidos.clear();
		listadoEditorial.clear();
		listadoAutor.clear();
		try
		{
			
				//Paso 3: Crear las sentencias SQL utilizando objetos de la clase Statement
				Modelo.Conectar();
				//Paso 4: Ejecutar las sentencias
				//			String sql = "SELECT libros.*, autores.nombreAutor FROM libros INNER JOIN autores ON libros.idAutorFK = idAutor;";
				String sql = "SELECT idLibro, tituloLibro, cantidadLibro, precioLibro, idEditorialFK, idAutorFK, autor.nombreAutor, editorial.nombreEditorial FROM libros JOIN autores AS autor ON idAutorFK = autor.idAutor JOIN editoriales AS editorial ON idEditorialFK = editorial.idEditorial ORDER BY idLibro;";
				ResultSet rs = Modelo.statement.executeQuery(sql);
				LibroPra libro;
				while(rs.next())
				{
					libro = new LibroPra(rs.getInt("idLibro"),rs.getString("tituloLibro"), rs.getString("nombreAutor"), rs.getInt("cantidadLibro"),rs.getDouble("precioLibro"));
					listadoLibros.add(libro);

				} 
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				//Cerramos el resto de recursos
				if(Modelo.statement != null)
				{
					Modelo.statement.close();
				}
				if(conn != null)
				{
					conn.close();
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}
	//Alta pedidos 

	public void insertarPedidos(String nombreUsuario, LocalDate fechaPedido) throws ServletException, SQLException 
	{	
		//Conectamos al pool
		Modelo.Conectar();

		//Creamos las sentencias
		String sentencia = "SELECT idUsuario FROM usuarios WHERE nombreUsuario = '"+nombreUsuario+"'"; 
		int idUsuarioFK = 0;
		//		int idLibroFK = 0;
		int cantidad = 0;
		//int idPedidoFK = 0;

		ResultSet rs = Modelo.statement.executeQuery(sentencia);
		while(rs.next())
		{
			idUsuarioFK = rs.getInt("idUsuario"); 
		}

		if(idUsuarioFK != 0)
		{
			int respuesta = 0;
			String insertPedido= "INSERT INTO pedidos (fechaPedido, idClienteFK) VALUES ('"+fechaPedido+"', " + idUsuarioFK + "); ";
			Modelo.statement.executeUpdate(insertPedido);
			//insertar historiales
			String sentenciaObtenerPedido = "SELECT * FROM pedidos WHERE fechaPedido = '"+fechaPedido+"' AND idClienteFK = "+idUsuarioFK+" ORDER BY 1 DESC";
			System.out.println(sentenciaObtenerPedido);
			ResultSet rsObtenerPedido =  Modelo.statement.executeQuery(sentenciaObtenerPedido);
			rsObtenerPedido.next();
			int idPedidoFK = rsObtenerPedido.getInt("idPedido");

		}
		else
		{

			throw new SQLException("Este usuario no está disponible");
		}
	}


	//Alta libro
	public void insertarLibro(String tituloLibro, double precio, int cantidad) throws SQLException{
		try
		{
			Modelo.Conectar();
			Modelo.conn.createStatement();
			String insertLibro = "INSERT INTO libro VALUES('null','"+tituloLibro +"','"+ precio + "','"+ cantidad+"');";
			Modelo.statement.executeUpdate(insertLibro);
			System.out.println(insertLibro);



		} catch (ServletException e)
		{

			e.printStackTrace();
		}
	}

	public static void borrar(int idLibro) {
		try
		{
			Modelo.Conectar();
			Modelo.conn.createStatement();
			String borrar = "DELETE FROM libros WHERE ('idLibro= '"+idLibro +"');";
			Modelo.statement.executeUpdate(borrar);
			System.out.println(borrar);


		} catch (ServletException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void modificarLibro(int idLibro, String tituloLibro, int cantidad, double precio, int editorialFK, int AutorFK) {
		try
		{
			Modelo.Conectar();
			Modelo.conn.createStatement();
			String editar = "UPDATE tienda_libros.libros SET tituloLibro = '"+tituloLibro+"', cantidadLibro = '"+cantidad+"', precioLibro = '"+precio+"', idEditorialFK = '"+editorialFK+"', idAutorFK = '"+AutorFK+"' WHERE (idLibro = '"+idLibro+"');";
			Modelo.statement.executeUpdate(editar);
			System.out.println(editar);


		} catch (ServletException e)
		{

			e.printStackTrace();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//consulta de pedidos
	public  ArrayList<Pedidos> getPedidos() throws ServletException {
		listadoLibros.clear();
		listadoPedidos.clear();
		listadoEditorial.clear();
		listadoAutor.clear();

		try
		{
			//Paso 3: Crear las sentencias SQL utilizando objetos de la clase Statement
			Modelo.Conectar();
			//Paso 4: Ejecutar las sentencias
			String pedidos = "select * from pedidos;";
			//			String sql = "SELECT idLibro, tituloLibro, cantidadLibro, precioLibro, idEditorialFK, idAutorFK, autor.nombreAutor, editorial.nombreEditorial FROM libros JOIN autores AS autor ON idAutorFK = autor.idAutor JOIN editoriales AS editorial ON idEditorialFK = editorial.idEditorial ORDER BY idLibro;";
			Modelo.statement.executeQuery(pedidos);
			Pedidos pedido;
			System.out.println(pedidos);
			ResultSet rs = Modelo.statement.executeQuery(pedidos);

			while(rs.next())
			{
				pedido = new Pedidos(rs.getInt("idPedido"),rs.getDate("fechaPedido"), rs.getDate("fechaEnviado"), rs.getInt("idClienteFK"),rs.getInt("enviado"));
				//				tabla.add(pedido);
				System.out.println("elementos del pedido: "+pedido);

			} 

		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				//Cerramos el resto de recursos
				if(Modelo.statement != null)
				{
					Modelo.statement.close();
				}

			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		return listadoPedidos;
	}


	//consultar editorial
	public ArrayList<Editoriales> getEditoriales() throws ServletException {
		//		ArrayList<Editoriales> listadoEditorial = new ArrayList<Editoriales>();
		listadoLibros.clear();
		listadoPedidos.clear();
		listadoEditorial.clear();
		listadoAutor.clear();


		// Creamos objetos para la conexión
		Modelo.Conectar();

		try {

			// Paso 4: Ejecutar las sentencias
			String sql = "SELECT * FROM editoriales;";
			ResultSet rs = Modelo.statement.executeQuery(sql);
			Editoriales editorial;
			while(rs.next())
			{
				editorial = new Editoriales(rs.getInt("idEditorial"), 
						rs.getString("nombreEditorial"));
				listadoEditorial.add(editorial);
				System.out.println(editorial);
			}


		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			Modelo.cerrarConexion();
		}


		return listadoEditorial;
	}

	//listado de autores
	public static ArrayList<Autores> getAutor() throws ServletException {

		ArrayList<Autores> listadoAutor = new ArrayList<Autores>();

		// Creamos objetos para la conexión
		Modelo.Conectar();

		try {

			// Paso 4: Ejecutar las sentencias
			String sql = "SELECT * FROM autores;";
			ResultSet rs = Modelo.statement.executeQuery(sql);
			Autores autor;
			while(rs.next())
			{
				autor = new Autores(	rs.getInt("idAutor"), 
						rs.getString("nombreAutor"));
				listadoAutor.add(autor);
				System.out.println(autor);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			Modelo.cerrarConexion();
		}


		return listadoAutor;
	}


	//Sacamos el numero de pedidos
	//	public static int tamanoPedidos() 
	//	{
	//		return listadoPedidos.size();
	//
	//	}
	//
	//	/**
	//	 * Devuelve el número de editoriales obtenidos
	//	 */
	//	public static int tamanoEditorial()
	//	{
	//		return listadoEditorial.size();
	//	}
	//	public static String getNombreEditorial(int getIdEditorial)
	//	{
	//		return listadoEditorial.get(getIdEditorial).getNombreEditorial();
	//	}
	//	/**
	//	 * Devuelve el número de autores obtenidos
	//	 */
	//	public static int tamanoAutor()
	//	{
	//		return listadoAutor.size();
	//	}
	//	public static String getNombreAutor(int getIdAutor)
	//	{
	//		return listadoAutor.get(getIdAutor).getAutor();
	//	}

	/**
	 * Devuelve el número de libros obtenidos
	 */
	public static int tamano()
	{
		return listadoLibros.size();
	}
	/**
	 * Devuelve el título del libro identificado con idLibro
	 */
	public static String getTitulo(int idLibro)
	{
		return listadoLibros.get(idLibro).getTituloLi();

	}
	//Devuelve el id libro
	public static int getIdLibro(int idLibro)
	{
	// 	return listadoLibros.get(idLibro).getIdLibro();
		for(LibroPra libro: listadoLibros)
		{
			if(libro.getIdLibro()==idLibro)
			{
				return libro.getIdLibro();
			}
		}
		return 0;
//
	}
	/**
	 * Devuelve la editorial del libro identificado con idLibro
	 */
	public static String getEditorial(int idLibro)
	{

		return listadoLibros.get(idLibro).getEditorial();
	}	/**
	 * Devuelve la editorial del libro identificado con idLibro
	 */
	public static String getNombreEditorial(int idLibro)
	{

		return listadoLibros.get(idLibro).getNombreEditorial();
	}
	/**
	 * Devuelve el autor del libro identificado con idLibro
	 */
	public static String getAutor(int idLibro)
	{
		return listadoLibros.get(idLibro).getAutor();

	}
	/**
	 * Devuelve el precio del libro identificado con idLibro
	 */
	public static double getPrecio(int idLibro)
	{
		return listadoLibros.get(idLibro).getPrecioLi();
	}
	/**
	 * Devuelve el stock del libro identificado con idLibro
	 */
	public static int getStock(int idLibro)
	{
		return listadoLibros.get(idLibro).getCantidadLi();
		//		for(LibroPra libro: tabla)
		//		{
		//			if(libro.getIdLibro()==idLibro)
		//			{
		//				return libro.getCantidadLi();
		//			}
		//		}
		//		return 0;
	}

}
