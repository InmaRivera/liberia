package es.studium.MVC;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.studium.MVC.LibroPra;
/**
 * Servlet implementation class Controlador
 */
//@SuppressWarnings("unused")
@WebServlet("/shopping")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void init(ServletConfig conf) throws ServletException
	{
		super.init(conf);
		Modelo.Conectar();
		//		Libreria_pra.cargarDatos();
	}
	public Controlador() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// Recupera la sesión actual o crea una nueva si no existe
		HttpSession session = request.getSession(true);
		String usuario = (String)session.getAttribute("usuario");

		if(usuario==null) {
			try {
				response.sendRedirect(request.getContextPath() + "/index.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			// Recupera el carrito de la sesión actual
			@SuppressWarnings("unchecked")
			ArrayList<LibroPedido> elCarrito = (ArrayList<LibroPedido>)
			session.getAttribute("carrito");
			// Determina a qué página jsp se redirigirá
			String nextPage = "";
			String todo = request.getParameter("todo");
			if(todo==null)
			{
				// Primer acceso, redirigir a orderpra.jsp
				nextPage = "/orderpra.jsp";
			}
			else if(todo.equals("add"))
			{
				// Mandado por order.jsp con los parámetros idLibro y cantidad
				// Creamos un elementoPedido y lo añadimos al carrito
				int indice = Integer.parseInt(request.getParameter("idLibro"));
				int cantidad = Integer.parseInt(request.getParameter("cantidad"));
				int stock = Libreria_pra.getStock(indice);
				if (cantidad <= stock) {
					LibroPedido nuevoElementoPedido = new LibroPedido(indice, cantidad);
					if(elCarrito==null)
					{

						// El carrito está vacío
						elCarrito = new ArrayList<>();
						elCarrito.add(nuevoElementoPedido);
						// Enlazar el carrito con la sesión
						session.setAttribute("carrito", elCarrito);
					}
					else
					{
						// Comprueba si el libro está ya en el carrito
						// Si lo está, actualizamos la cantidad
						// Si no está, lo añadimos
						boolean encontrado = false;
						Iterator<LibroPedido> iter = elCarrito.iterator();
						while(!encontrado&&iter.hasNext())
						{
							LibroPedido unElementoPedido = (LibroPedido)iter.next();

							if(unElementoPedido.getIdLibro() ==	nuevoElementoPedido.getIdLibro())
							{
								int nuevaCantidad = unElementoPedido.getCantidad() +
										nuevoElementoPedido.getCantidad();
								//comprobamos cantidad en Stock
								if(nuevaCantidad <= stock)
								{
									unElementoPedido.setCantidad(nuevaCantidad);
									encontrado = true;
								}
								else
								{
															
									throw new ServletException("ERROR: no hay suficientes libros en nuestros stock.");

								}
							}
						}
						if(!encontrado)
						{
							// Lo añade al carrito
							elCarrito.add(nuevoElementoPedido);
						}
					}
					// Volvemos a order.jps para seguir con la compra
					nextPage = "/orderpra.jsp";
				}
				else 
				{
					throw new ServletException("ERROR: no hay suficientes libros en nuestros stock.");
				}
			}

			else if(todo.equals("remove"))
			{
				// Enviado por order.jsp con el parámetro indiceElementoDespliegue
				// Borra el elemento indiceElemento del carrito
				int indiceCarrito = Integer.parseInt(request.getParameter("indiceElemento"));
				elCarrito.remove(indiceCarrito);
				// Vuelve a order.jsp para seguir con la compra
				nextPage = "/orderpra.jsp";
			}
			else if (todo.equals("checkout"))
			{
				// Enviado por orderpra.jsp
				// Calcula el precio total de todos los elementos del carrito
				double precioTotal = 0;
				int cantidadTotalOrdenada = 0;
				Pedidos pedido = new Pedidos();

				for(LibroPedido item: elCarrito)
				{
					double precio = item.getPrecio();
					int cantidadOrdenada = item.getCantidad();
					precioTotal += precio * cantidadOrdenada;
					cantidadTotalOrdenada += cantidadOrdenada;							
					// Da formato al precio con dos decimales
					StringBuilder sb = new StringBuilder();
					Formatter formatter = new Formatter(sb);
					formatter.format("%.2f", precioTotal);
					formatter.close();
					// Coloca el precioTotal y la cantidad total en el request
					request.setAttribute("precioTotal", sb.toString());
					request.setAttribute("cantidadTotal", cantidadTotalOrdenada+"");

					// Redirige a checkout.jsp
					nextPage = "/checkoutpra.jsp";
				}
			}
			// seguir comprando
			else if (todo.equals("otro"))
			{
				nextPage = "/orderpra.jsp";
			}
			else if (todo.equals("volver"))
			{
				nextPage = "/principal.jsp";
			}
			else if (todo.equals("volverLibros"))
			{
				nextPage = "/libros.jsp";
			}
			//para salir de la app
			else if (todo.equals("logout"))
			{
				nextPage = "/index.jsp";
			}
			ServletContext servletContext = getServletContext();
			RequestDispatcher requestDispatcher =
					servletContext.getRequestDispatcher(nextPage);
			requestDispatcher.forward(request, response);
		}
	}
}


