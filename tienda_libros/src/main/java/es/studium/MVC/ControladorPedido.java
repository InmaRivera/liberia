package es.studium.MVC;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
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

	public ControladorPedido() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Modelo.Conectar();
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true);
		String todo = request.getParameter("checkout");
		Pedidos pedido = new Pedidos();
		int idClienteFK = pedido.idClienteFK;
		String fechaPedido = pedido.fechaPedido;
		String fechaEnviado = pedido.fechaEnviado;
//		boolean enviado = pedido.isEnviado();
		String nextPage = "";

		//insertar nuevo pedido
		if(todo.equals("checkout")) {
			String sql ="INSERT INTO pedidos VALUES('null', '"+fechaPedido+"', '"+fechaEnviado+"', idClienteFK = '"+ idClienteFK +"');";
			nextPage = "/checkoutpra.jsp";
			System.out.println(sql);
			try
			{
				Modelo.statement.executeUpdate(sql);
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Modelo.cerrarConexion();
		}
		
		//String sql ="INSERT INTO pedidos VALUES(null, "+fechaPedido+", "+fechaEnviado+", idClienteFk ="+idCliente");";
		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher =
				servletContext.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}

}
