package es.studium.MVC;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class Login
 */
@WebServlet(name = "Login",
urlPatterns = {"/login"})
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Pool de conexiones a la base de datos
	private DataSource pool;

	public Login() {
		super();

	}
	//Se ejecuta una sola vez para iniciar las tareas al principio
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		try
		{
			// Crea un contexto para poder luego buscar el recurso DataSource
			InitialContext ctx = new InitialContext();
			// Busca el recurso DataSource en el contexto
			pool = (DataSource)ctx.lookup("java:comp/env/jdbc/mysql_tienda_libros");
			if(pool == null)
			{
				throw new ServletException("DataSource desconocida 'mysql_tienda_libros'");
			}
		}
		catch(NamingException ex){}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, 
	IOException {

		// responde al index
		request.setCharacterEncoding("UTF-8");
		//PrintWriter out = response.getWriter();
		Connection conn = null;
		Statement stmt = null;
		String nextPage = "";
		//variable para diferenciar usuarios
		//la iniciamos en -1 para no pisar 1 o 0 de nuestros usuarios
		int tipoUsuarioLogado = -1;

		HttpSession session = request.getSession(false);

		try
		{
			// Obtener una conexión del pool
			conn = pool.getConnection();
			//System.out.println(conn);
			stmt = conn.createStatement();
			// creo una variable para diferenciar entre admin y cliente al iniciar sesion

			// Recuperar los parámetros usuario y password de la petición request
			String usuario = request.getParameter("usuario");
			String password = request.getParameter("password");
			// Validar los parámetros de la petición request
			if(usuario.length()==0)
			{
				//si no introduce el nombre
//				out.println("<h3>Debes introducir tu usuario</h3>");
				session.setAttribute("usuario", usuario);
//				rd = request.getRequestDispatcher("index1.jsp");
				nextPage = "/index1.jsp";
			}
			else if(password.length()==0)
			{
				//si no introducen contraseña
//				out.println("<h3>Debes introducir tu contraseña</h3>");
				session.setAttribute("usuario", usuario);
//				rd = request.getRequestDispatcher("index2.jsp");
				nextPage = "/index2.jsp";
			}

			else 
			{
				// Verificar que existe el usuario y su correspondiente clave
				StringBuilder sqlStr = new StringBuilder();

				sqlStr.append("SELECT * FROM usuarios WHERE ");
				sqlStr.append("STRCMP(usuarios.nombreUsuario, '").append(usuario).append("') = 0 ");
				//sqlStr.append("AND STRCMP(usuarios.tipoUsuario, '").append(tipo).append("') = " +tipo);
				sqlStr.append(" AND STRCMP(usuarios.claveUsuario, SHA2('").append(password).append("',256)) = 0;");

				ResultSet rset = stmt.executeQuery(sqlStr.toString());

				if(!rset.next())
				{
					// Si el resultset no está vacío y la contraseña o usuario mal
					//	out.println("<h5> libros base: " + libros +"</h5>");
//					out.println("<h2>Nombre de usuario o contraseña incorrectos</h2>");
//					out.println("<p><a href='index.html'>Volver a Login</a></p>");
					session.setAttribute("usuario", usuario);
//					rd = request.getRequestDispatcher("index3.jsp");
					nextPage = "/index3.jsp";
				}
				else
				{
				
					//Recuperamos el tipo de usuario de la base de datos
					tipoUsuarioLogado = rset.getInt("tipoUsuario");
					// Sacar el tipo del usuario logado
					String sql = ("SELECT tipoUsuario = '"+ tipoUsuarioLogado +"' FROM usuarios WHERE " 
							+ "nombreUsuario = '"
							+ usuario 
							+ "' AND claveUsuario = SHA2('" + password +  "', 256);");
					//System.out.println(sql+ "\n ");

					// Si los datos son correctos comprobamos si es usuario admin o es usuario cliente

			



					//comprobamos con 0 si es trabajador
					if(tipoUsuarioLogado==0)
					{	
						// Creamos una variable de session, con el registro de usuario
						// Verificar en el administrador de aplicaciones de tomcat.
						if(session != null)
						{
							//si la session no es nula, la invalidamos
							session.invalidate();
						}

						session = request.getSession(true);
						synchronized(session)
						{
							//y sincronizamos la nueva sesion según el tipo de usuario
							session.setAttribute("usuario", usuario);
						}
						nextPage = "/admin.jsp";
					}
					//comprobamos con 1 si es cliente
					else if(tipoUsuarioLogado==1)
					{
						// Creamos una variable de session, con el registro de usuario (Bean)
						// Verificar en el administrador de aplicaciones de tomcat.
						if(session != null)
						{
							session.invalidate();
						}

						session = request.getSession(true);
						synchronized(session)
						{
							session.setAttribute("usuario", usuario);

						}
						nextPage = "/orderpra.jsp";
					}
					//					}
				}
			}
		}
		catch(SQLException ex)
		{

		}
		finally
		{
			// Cerramos objetos
			//out.close();
			try
			{if(stmt != null)
			{
				stmt.close();
			}
			if(conn != null)
			{
				// Esto devolvería la conexión al pool
				conn.close();
			}
			}
			catch(SQLException ex){}
		}

		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher =
				servletContext.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);

	}
}
