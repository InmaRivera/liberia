package es.studium.MVC;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;

public class ControladorEditorial
{
	public static ArrayList<Editoriales> getEditoriales() throws ServletException {

		ArrayList<Editoriales> listadoEditorial = new ArrayList<Editoriales>();

		// Creamos objetos para la conexión
		Modelo.Conectar();

		try {

			// Paso 4: Ejecutar las sentencias
			String sql = "SELECT * FROM editoriales ORDER BY nombreEditorial;";
			ResultSet rs = Modelo.statement.executeQuery(sql);
			Editoriales editorial;
			while(rs.next())
			{
				editorial = new Editoriales(	rs.getInt("idEditorial"), 
						rs.getString("nombreEditorial"));
				listadoEditorial.add(editorial);
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
}
