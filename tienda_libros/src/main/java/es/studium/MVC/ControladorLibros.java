//package es.studium.MVC;
//
//import java.io.IOException;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//
///**
// * Servlet implementation class ControladorLibros
// */
//@WebServlet("/ControladorLibros")
//public class ControladorLibros extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//    public ControladorLibros() {
//        super();
//  
//    }
//public void mostrarLibros() {
//	
//}
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////		// TODO Auto-generated method stub
////		request.setCharacterEncoding("UTF-8");
////		HttpSession session = request.getSession(true);
////		String todo = request.getParameter("todo");
////		if(todo.equals("alta")) {
////			// datos del formulario
////			String tituloLibro = request.getParameter("tituloLibro");
////			String cantidadLibro = request.getParameter("cantidadLibro");
////			String precioLibro = request.getParameter("precioLibro");
////			String idAutorFK = request.getParameter("idAutorFK");
////			String idEditorialFK = request.getParameter("idEditorialFK");
////			
////			String sql = "INSERT INTO libros VALUES(NULL,'"+tituloLibro+"', '"+cantidadLibro+"', '"+precioLibro+"', '"+idAutorFK+"', '"+idEditorialFK+"')";
////			
////
////			// redirección
////			try {
////				response.sendRedirect(request.getContextPath() + "/libros.jsp");
////			} catch (IOException e) {
////				e.printStackTrace();
////			}
////				
////		} 
////		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}
//	static ArrayList<LibroPra> listadotabla = new ArrayList<LibroPra>();
//	public static ArrayList<LibroPra> cargarLibros() throws ServletException
//	{
//
////		listadolibros.clear();
//		
//		try {
//			
//			Modelo.Conectar();
//			
//			// Paso 4: Ejecutar las sentencias
//			String sql = "SELECT idLibro, tituloLibro, cantidadLibro, precioLibro, idEditorialFK, idAutorFK, autor.nombreAutor, editorial.nombreEditorial FROM libros JOIN autores AS autor ON idAutorFK = autor.idAutor JOIN editoriales AS editorial ON idEditorialFK = editorial.idEditorial ORDER BY idLibro;";
//			System.out.println(sql);
//			LibroPra libro;
//			ResultSet rs = Modelo.statement.executeQuery(sql);
//			while(rs.next())
//			{
////				libro = new LibroPra(	rs.getInt("idLibro"), 
////									rs.getString("tituloLibro"), 
////									rs.getInt("cantidadLibro"), 
////									rs.getDouble("precioLibro"), 
////									rs.getInt("idAutorFK"), 
////									rs.getString("autor.nombreAutor"), 
////									rs.getInt("idEditorialFK"), 
////									rs.getString("editorial.nombreEditorial")	);
////				listadotabla.add(libro);
//			}
//			return listadotabla;
//		}
//		catch(Exception ex)
//		{
//			ex.printStackTrace();
//		}
//		finally
//		{
//			Modelo.cerrarConexion();
//		}
//		return null;
//	}
//}
