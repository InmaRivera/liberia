package es.studium.MVC;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

/**
 * Servlet implementation class Controlador
 */
//@SuppressWarnings("unused")
@WebServlet("/shopping")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServletContext servletContext = null;
	RequestDispatcher requestDispatcher = null;
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
									request.setAttribute("response", "<h3 class='text-danger text-center'>ERROR: no hay suficientes libros en nuestros stock.</h3>");
									throw new ServletException("response");

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
					
					request.setAttribute("response", "<h3 class='text-danger'>'ERROR: no hay suficientes libros en nuestros stock.'</h3>");
					//					mensaje = "	throw new ServletException(\"alertify.success('ERROR: no hay suficientes libros en nuestros stock.');\");";
					//					throw new ServletException("alertify.success('ERROR: no hay suficientes libros en nuestros stock.');");
				}
				nextPage="/orderpra.jsp";
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
 //Obtenemos el usuario de la sesión
					 usuario = (String) session.getAttribute("usuario");
					System.out.println(usuario);
					
					//obtenemos la fecha 
					LocalDate fecha = LocalDate.now();
					DateTimeFormatter formato =  DateTimeFormatter.ofPattern("dd MM yy");
					String texto = fecha.format(formato);
					LocalDate parseDate = LocalDate.parse(texto,formato);
					
					//llamamos a la clase 
					
					Libreria_pra insertarPedido = new Libreria_pra();
					try
					{
						insertarPedido.insertarPedidos(usuario, fecha);
					} catch (ServletException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//Respuesta
					request.setAttribute("response","Este usuario no está disponible");
					
					// Redirige a checkout.jsp
					nextPage = "/checkoutpra.jsp";
				}
			}
			//Alta libro 
			else if (todo.equals("Nuevo")) {
				Libreria_pra libros = new Libreria_pra();
				String tituloLibro = request.getParameter("Nombre");
				double precioLibro = Double.parseDouble(request.getParameter("Precio"));
				String autor = request.getParameter("Autores");
				String editorial = request.getParameter("Editoriales");
				int cantidad = Integer.parseInt(request.getParameter("Cantidad"));
				if (tituloLibro != "" && precioLibro != 0.0) {
					try
					{
						//insertar libro nuevo
						libros.insertarLibro(tituloLibro, precioLibro, cantidad);

					} catch (SQLException e)
					{

						e.printStackTrace();
					}
					try
					{//insertar autor
						libros.insertarAutor(autor);
					} catch (SQLException e)
					{

						e.printStackTrace();
					}
					try
					{//insertar editorial
						libros.insertarEditorial(editorial);
					} catch (SQLException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					nextPage = "/AltaLibro.jsp";
				}

				else if (todo.equals("Confirmar"))
				{
					
					int error = -1;
					if(error==1) {
						request.setAttribute("response", "<h3 class='text-success'>Libro '" + tituloLibro + "' creado correctamente.</h3>");
					} else {
						request.setAttribute("response", "<h3 class='text-danger'>Error al crear el libro.</h3>");
					}
					nextPage = "libros.jsp";
				}
			}
			//modificar
			else if (todo.equals("Editar"))
			{
				Libreria_pra libros = new Libreria_pra();
				String tituloLibro = request.getParameter("titulo");
				double precioLibro = Double.parseDouble(request.getParameter("precio"));
				String autor = request.getParameter("autores");
				String editorial = request.getParameter("editoriales");
				int cantidad = Integer.parseInt(request.getParameter("cantidad"));
				int id = Integer.parseInt(request.getParameter("idLibro"));
				if (tituloLibro != "" && precioLibro != 0.0) {
					//insertar libro nuevo
					libros.modificarLibro(id, tituloLibro, cantidad, precioLibro, cantidad, id);
					nextPage = "/libros.jsp";
				}
				int error = -1;
				if(error==1) {
					request.setAttribute("response", "El Libro: '" + tituloLibro + "' se ha modificado correctamente.");
				} else {
					request.setAttribute("response", "Error al modificar el libro.");
				}
				nextPage = "libros.jsp";
			}
			//eliminar un libro
			else if (todo.equals("borrar"))
			{
				Libreria_pra libros = new Libreria_pra();
				int borrarLibro = Integer.parseInt(request.getParameter("borrar"));
				//				Libreria_pra libros = new Libreria_pra();
				libros.borrar(-1);
				//				{
				//				int listadoLibro = Integer.parseInt(request.getParameter("borrar"));
				////				remove(listadoLibro);
				//				
				//				}
				int borrar = -1;
				String respuesta ="";
				if(borrar == 0)
				{
					respuesta = "No se ha podido elimnar el libro";

				}
				else if (borrar == 1) 
				{
					respuesta = "Se ha eliminado correctamente";
				}
				nextPage = "/libros.jsp";
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
				nextPage = "/logout.jsp";
			}


			ServletContext servletContext = getServletContext();
			RequestDispatcher requestDispatcher =
					servletContext.getRequestDispatcher(nextPage);
			requestDispatcher.forward(request, response);
		}
	}
}

