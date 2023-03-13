package es.studium.MVC;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;

public class ControladorAutor
{
	public static ArrayList<Autores> getAutor() throws ServletException {

		ArrayList<Autores> listadoAutor = new ArrayList<Autores>();

		// Creamos objetos para la conexión
		Modelo.Conectar();

		try {

			// Paso 4: Ejecutar las sentencias
			String sql = "SELECT * FROM autores  ORDER BY nombreAutor;;";
			ResultSet rs = Modelo.statement.executeQuery(sql);
			Autores autor;
			while(rs.next())
			{
				autor = new Autores(	rs.getInt("idAutor"), 
						rs.getString("nombreAutor"));
				listadoAutor.add(autor);
				
				System.out.println(rs.getInt("idAutor")+ " " +
						rs.getString("nombreAutor"));
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

}
