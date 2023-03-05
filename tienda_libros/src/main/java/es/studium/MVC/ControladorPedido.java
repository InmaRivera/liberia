package es.studium.MVC;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ControladorPedido
 */
@WebServlet("/ControladorPedido")
public class ControladorPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void init(ServletConfig conf) throws ServletException
	{
		super.init(conf);
		Modelo.Conectar();
		Libreria_pra.tamano();

	}
	public ControladorPedido() {
		super();


	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub


		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true);
		String todo = request.getParameter("todo");
		int idCliente = 0;
		String username = (String) session.getAttribute("usuario");
		if(username!=null) {

			idCliente = (int) session.getAttribute("tipoUsuario");
			Pedidos pedido = new Pedidos();
			int idClienteFK = pedido.getIdClienteFK();
			Date fechaPedido = pedido.getFechaPedido();
			Date fechaEnviado = pedido.getFechaEnviado();
			String resultado = "";
			//		boolean enviado = pedido.isEnviado();
			String nextPage = "";

			//insertar nuevo pedido
			if(todo.equals("checkout")) {
				@SuppressWarnings("unchecked")
				ArrayList<LibroPedido> cesta = (ArrayList<LibroPedido>)
				session.getAttribute("carrito");
				//comprobar que la cesta no está vacía y añadir el pedido
				if (cesta != null && cesta.size()>0) {
					try
					{
						//Crear una sentencia
						Modelo.conn.prepareStatement(todo);
						//insertamos pedido
						String sql ="INSERT INTO pedidos VALUES('null', '"+fechaPedido+"', '"+fechaEnviado+"', idClienteFK = '"+idCliente+"');";
						System.out.println(sql);
						//y ejecutar la sentencia SQL
						if((Modelo.statement.executeUpdate(sql))==1)
						{
							resultado = "<p>Error al hacer pedido<p>";
						}
						else
						{
							resultado = "<p>Pedido realizado correctamente<p>";
						}

					} catch (SQLException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			ServletContext servletContext = getServletContext();
			RequestDispatcher requestDispatcher =
					servletContext.getRequestDispatcher(nextPage);
			requestDispatcher.forward(request, response);
		}
	}
}


