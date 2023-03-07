package es.studium.MVC;

import java.io.IOException;
import java.sql.ResultSet;
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
		doGet(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nextPage = "";
		request.setCharacterEncoding("UTF-8");
		// Recupera la sesión actual o crea una nueva si no existe
		HttpSession session = request.getSession(true);
		String usuario = (String)session.getAttribute("usuario");

		String todo = request.getParameter("todo");
		int idUsuario =	0;

		System.out.println(usuario);
		if(usuario==null) {
			idUsuario = (int) session.getAttribute("idUsuario");


			usuario = (String) session.getAttribute("tipoUsuario");
			//			String idCliente = request.getParameter("usuario");
			int idPedidoFK = 0;
			int cantidad = 0;
			int idLibro = 0;

			String resultado = "";
			//			boolean enviado = pedido.isEnviado();

			//insertar nuevo pedido
			if(todo.equals("add")) {
				@SuppressWarnings("unchecked")
				ArrayList<LibroPedido> cesta = (ArrayList<LibroPedido>)session.getAttribute("carrito");
				Double random = ((Math.random()*8999999)+1000000);
				int idPedido = random.intValue();

				//comprobar que la cesta no está vacía y añadir el pedido
				if (cesta != null && cesta.size()>0) {
					int restar = 0; // para comprobar stock
					for (int i = 0; i < cesta.size(); i++) {

						if(restar!=0) 
						{
							// CREAR PEDIDO


							int error = crearPedido(idPedido, idUsuario);
							if(error == 1) 
							{
								resultado += "<span class='text-success'>Pedido creado correctamente.</span></br>";
							}

						} 
						else 
						{
							resultado += "<span class='text-danger'>No se pudo realizar el pedido, los libros están agotados.</span></br>";
						}
						// BORRAR CARRITO
						session.removeAttribute("carrito");
						nextPage = "/checkoutpra.jsp";
					}

				}
			}
		}
		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher =
				servletContext.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}


	@SuppressWarnings("unused")
	public void selectPedidos() throws SQLException{
		String sql ="SELECT * FROM pedidos;";
		System.out.println(sql);
		try
		{
			Modelo.Conectar();
			ResultSet rs = Modelo.statement.executeQuery(sql);
			//			return Modelo.statement.executeQuery(sql);

		} catch (ServletException e)
		{

			e.printStackTrace();
		}


	}
	private int crearPedido(int idPedido, int usuario) {
		Pedidos pedido = new Pedidos();
		pedido.getIdPedido();
		//			int idClienteFK = pedido.getIdClienteFK();
		Date fechaPedido = pedido.getFechaPedido();
		Date fechaEnviado = pedido.getFechaEnviado();

		String sql = "INSERT INTO pedidos VALUES('"+ pedido.getIdPedido()+ "', 'NOW()','"+fechaEnviado+"', 'NULL',0)";
		System.out.println(sql);

		try {

			Modelo.Conectar();			
			return Modelo.statement.executeUpdate(sql);

		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}

