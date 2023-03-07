package es.studium.MVC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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


	public void cargarDatos()
	{
		// Creamos objetos para la conexión
//		Connection conn = null;
//		Statement stmt = null;
		try
		{

			//Paso 3: Crear las sentencias SQL utilizando objetos de la clase Statement
			Modelo.Conectar();

			//Paso 4: Ejecutar las sentencias
			String sql = "SELECT libros.*, autores.nombreAutor FROM libros INNER JOIN autores ON libros.idAutorFK = idAutor;";
			ResultSet rs = Modelo.statement.executeQuery(sql);

			LibroPra libro;
			while(rs.next())
			{
				libro = new LibroPra(rs.getInt("idLibro"),rs.getString("tituloLibro"), rs.getString("nombreAutor"), rs.getInt("cantidadLibro"),rs.getDouble("precioLibro"));
				tabla.add(libro);
//				libroSeleccionado += "<option value='idLibro'>" + rs.getInt("idLibro") +"-"+ rs.getString("tituloLibro") + " " +
//						rs.getInt("cantidadLibro") + " " + rs.getDouble("precioLibro") + "</option>";
				//				System.out.println(libroSeleccionado);

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
//				if(conn != null)
//				{
//					conn.close();
//				}
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
	public static int getId(int i)
	{
		return tabla.get(i).getIdLibro();
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
