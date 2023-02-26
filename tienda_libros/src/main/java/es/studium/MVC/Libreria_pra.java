package es.studium.MVC;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class Libreria_pra extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	// Pool de conexiones a la base de datos
	private static DataSource pool;

	public Libreria_pra() {
		super();

	}
	//Se ejecuta una sola vez para iniciar las tareas al principio
	public void init(ServletConfig conf) throws ServletException
	{
		super.init(conf);
		try
		{
			// Crea un contexto para poder luego buscar el recurso DataSource
			InitialContext ctx = new InitialContext();
			// Busca el recurso DataSource en el contexto
			pool = (DataSource)ctx.lookup("java:comp/env/jdbc/mysql_tienda_libros");
			if(pool == null)
			{
				throw new ServletException("DataSource desconocida 'mysql_tienda_libros'");
			}
		}
		catch(NamingException ex){}
	}
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doPost(request, response);
//	}
		static ArrayList<LibroPra> tabla = new ArrayList<LibroPra>();
		public static void cargarDatos()
		{
			// Creamos objetos para la conexión
			Connection conn = null;
			Statement stmt = null;
			try
			{

				//Paso 3: Crear las sentencias SQL utilizando objetos de la clase Statement
				//				System.out.println(conn);
				conn = pool.getConnection();

				stmt = conn.createStatement();
				//Paso 4: Ejecutar las sentencias
				String sqlStr = "SELECT * FROM libros";
				ResultSet rs = stmt.executeQuery(sqlStr);
				LibroPra libro;
				while(rs.next())
				{
					libro = new LibroPra(rs.getInt("idLibro"), rs.getString("tituloLibro"),
							rs.getInt("cantidadLibro"), rs.getDouble("precioLibro"));
					tabla.add(libro);

					String LibroPra = "<option value='idLibro'>" + (rs.getInt("idLibro") +"-"+ rs.getString("tituloLibro") + " " +
							rs.getInt("cantidadLibro") + " " + rs.getDouble("precioLibro") + "</option>");
					System.out.println(LibroPra);
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
					if(stmt != null)
					{
						stmt.close();
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
		/**
		 * Devuelve el autor del libro identificado con idLibro
		 */
		public static Autores getAutor(int idLibro)
		{
			return tabla.get(idLibro).getIdAutorFK();
		}
		/**
		 * Devuelve el precio del libro identificado con idLibro
		 */
		public static double getPrecio(int idLibro)
		{
			return tabla.get(idLibro).getPrecioLi();

	}
}
