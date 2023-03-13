package es.studium.MVC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

public class Libreria_pra 
{
	// Pool de conexiones a la base de datos

	String libroSeleccionado ="";
	Connection conn = null;
	Statement statement = null;
	ResultSet rs = null;

	static ArrayList<LibroPra> tabla = new ArrayList<LibroPra>();

	public Libreria_pra() {
		super();

	}

	//consulta de libros
	public void cargarDatos()
	{
		tabla.clear();
		try
		{
			//Paso 3: Crear las sentencias SQL utilizando objetos de la clase Statement
			Modelo.Conectar();
			//Paso 4: Ejecutar las sentencias
			String sql = "SELECT libros.*, autores.nombreAutor FROM libros INNER JOIN autores ON libros.idAutorFK = idAutor;";
			//			String sql = "SELECT idLibro, tituloLibro, cantidadLibro, precioLibro, idEditorialFK, idAutorFK, autor.nombreAutor, editorial.nombreEditorial FROM libros JOIN autores AS autor ON idAutorFK = autor.idAutor JOIN editoriales AS editorial ON idEditorialFK = editorial.idEditorial ORDER BY idLibro;";
			ResultSet rs = Modelo.statement.executeQuery(sql);
			LibroPra libro;
			while(rs.next())
			{
				libro = new LibroPra(rs.getInt("idLibro"),rs.getString("tituloLibro"), rs.getString("nombreAutor"), rs.getInt("cantidadLibro"),rs.getDouble("precioLibro"));
				tabla.add(libro);

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
			
			Modelo.conn.createStatement();
			
			//Creamos las sentencias
			String sentencia = "SELECT idUsuario FROM usuarios WHERE nombreUsuario = '"+nombreUsuario+"'"; 
		    int    idUsuarioFK = 0;
			ResultSet rs = Modelo.statement.executeQuery(sentencia);
			while(rs.next())
			{
				idUsuarioFK = rs.getInt("idUsuario"); 
			}

            if(idUsuarioFK != 0)
            {
                String insertPedido= "INSERT INTO pedidos VALUES ('null','" + fechaPedido + "', 'NOW()', '" + idUsuarioFK + "'); ";
                Modelo.statement.executeUpdate(insertPedido);
            }
            else
            {
            	
                throw new SQLException("Este usuario no está disponible");
            }
		}
//			String sql = "INSERT INTO pedidos VALUES('"+ pedido.getIdPedido()+ "', 'NOW()','"+fechaEnviado+"', 'NULL',0)";
	
		

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

	//Autor nuevo
	public void insertarAutor(String Autor) throws SQLException{
		try
		{
			Modelo.Conectar();
			Modelo.conn.createStatement();
			String insertAutor = "INSERT INTO autores VALUES('null','"+ Autor +"');";
			Modelo.statement.executeUpdate(insertAutor);
			System.out.println(insertAutor);


		} catch (ServletException e)
		{

			e.printStackTrace();
		}
	}
	//Autor nuevo
	public void insertarEditorial(String Editorial) throws SQLException{
		try
		{
			Modelo.Conectar();
			Modelo.conn.createStatement();
			String insertEditorial = "INSERT INTO editoriales VALUES('null','"+ Editorial +"');";
			//Actualizamos
			Modelo.statement.executeUpdate(insertEditorial);
			System.out.println(insertEditorial);


		} catch (ServletException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void borrar(int idLibro) {
		try
		{
			Modelo.Conectar();
			Modelo.conn.createStatement();
			String borrar = "DELETE FROM tienda_libros.libros WHERE ('idLibro= ','"+idLibro +"');";
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
		ArrayList<Pedidos> listadoPedidos = new ArrayList<Pedidos>();

		tabla.clear();
		try
		{
			//Paso 3: Crear las sentencias SQL utilizando objetos de la clase Statement
			Modelo.Conectar();
			//Paso 4: Ejecutar las sentencias
			String sql = "select * from pedidos;";
			//			String sql = "SELECT idLibro, tituloLibro, cantidadLibro, precioLibro, idEditorialFK, idAutorFK, autor.nombreAutor, editorial.nombreEditorial FROM libros JOIN autores AS autor ON idAutorFK = autor.idAutor JOIN editoriales AS editorial ON idEditorialFK = editorial.idEditorial ORDER BY idLibro;";
			Modelo.statement.executeQuery(sql);
			Pedidos pedido;
			System.out.println(sql);

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



	/**
	 * Devuelve el número de libros obtenidos
	 */
	public static int tamano()
	{
		return tabla.size();
	}
	/**
	 * Devuelve el título del libro identificado con idLibro
	 */
	public static String getTitulo(int idLibro)
	{
		return tabla.get(idLibro).getTituloLi();
	}
	public static int getId(int i)
	{
		return tabla.get(i).getIdLibro();
	}
	/**
	 * Devuelve la editorial del libro identificado con idLibro
	 */
	public static String getEditorial(int idLibro)
	{
		return tabla.get(idLibro).getEditorial();
	}
	/**
	 * Devuelve el autor del libro identificado con idLibro
	 */
	public static String getAutor(int idLibro)
	{
		return tabla.get(idLibro).getAutor();
	}
	/**
	 * Devuelve el precio del libro identificado con idLibro
	 */
	public static double getPrecio(int idLibro)
	{
		return tabla.get(idLibro).getPrecioLi();

	}
	/**
	 * Devuelve el stock del libro identificado con idLibro
	 */
	public static int getStock(int idLibro)
	{
		return tabla.get(idLibro).getCantidadLi();

	}

}
