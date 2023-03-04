package es.studium.MVC;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ControladorLibros
 */
@WebServlet("/ControladorLibros")
public class ControladorLibros extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorLibros() {
        super();
  
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true);
		String todo = request.getParameter("todo");
		
		if(todo.equals("alta")) {
			// datos del formulario
			String tituloLibro = request.getParameter("tituloLibro");
			String cantidadLibro = request.getParameter("cantidadLibro");
			String precioLibro = request.getParameter("precioLibro");
			String idAutorFK = request.getParameter("idAutorFK");
			String idEditorialFK = request.getParameter("idEditorialFK");
			
			String sql = "INSERT INTO libros VALUES(NULL,'"+tituloLibro+"', '"+cantidadLibro+"', '"+precioLibro+"', '"+idAutorFK+"', '"+idEditorialFK+"')";
			
//			int error = insertLibro(query);
//			if(error==1) {
//				session.setAttribute("response", "Libro '" + tituloLibro + "' creado correctamente.");
//			} else {
//				session.setAttribute("response", "Error al crear el libro.");
//			}

			// redirección
			try {
				response.sendRedirect(request.getContextPath() + "/libros.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
				
		} 
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
