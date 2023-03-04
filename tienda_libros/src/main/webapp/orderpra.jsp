<%-- Página de órdenes de pedido --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true" import="java.util.*, es.studium.MVC.*"%>
<!doctype html>
<html lang="es">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="https://getbootstrap.com/docs/5.2/assets/css/docs.css"
	rel="stylesheet">

<title>Pedido</title>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<%
String username = (String) session.getAttribute("usuario");
if (username == null)
{
	response.sendRedirect("./index.jsp");
}
%>
</head>
<style>
@import
	url('https://fonts.googleapis.com/css2?family=Pacifico&family=Shantell+Sans:ital,wght@0,300;1,300&display=swap')
	;

body {
	background-image:
		url("https://album.mediaset.es/eimg/2020/04/01/Fagw0vREqPnTbfcRoKSHm6.jpg?w=480");
	background-repeat: no-repeat;
	background-size: 100% auto;
	height: 100%;
	padding-top: 3%;
	padding-right: 20%;
	padding-left: 20%;
}

.btn:hover {
	color: #fafafa;
	background-color: #0452f9;
}

.boton {
	align-items: center;
}

h1, p {
	font-family: 'Pacifico', cursive;
	font-family: 'Shantell Sans', cursive;
	color: #000;
	text-shadow: #fff 3px 6px 4px;
	text-align: center;
}

h5 {
	color: #fff;
	text-shadow: #000 3px 6px 4px;
	text-align: center;
}

section {
	background-color: #f7efd6;
	opacity: 0.9;
	border-radius: 10px 10px 10px 10px;
}
</style>

<body class="bg-dark">
	<h1>Realizar pedido</h1>
	<%="<h5> Hola " + username + "</h5>"%>
	<br>
	<br>
	<section class="pt-5">
		<p>
			<strong>Elegir un libro y la cantidad:</strong>
		</p>
		<!-- formulario-->
		<div class=" justify-content-center text-center">
			<form name="AgregarForm" action="shopping" method="POST">
				<input type="hidden" name="todo" value="add"> <select
					name="idLibro" class="form-select"
					aria-label="Default select example">
					<%
					Libreria_pra libreria = new Libreria_pra(); //OBJETO LIBRERIA
					libreria.cargarDatos();//cargamos los datos de libreria
					LibroPra libro = new LibroPra();//OBJETO LIBROS
					
					for (int i = 0; i < libreria.tamano(); i++)
					{
						out.println("<option class='text-center' value='" + libreria.getId(i) + "'>");
						out.println(libreria.getTitulo(i) + " - " + libreria.getAutor(i) + " -> " + libreria.getPrecio(i) + " €");
					}
					%>

				</select>
				<div class="input-group">
					<span class="input-group-text">Indique la cantidad:</span> <input
						type="text" aria-label="First name" class="form-control"
						type="text" name="cantidad" size="10" value="1"> <br>
					<div class="col-12 pt-5">
						<button class="btn btn-outline-dark boton" type="submit"
							value="Añadir a la cesta">Añadir a la cesta</button>
					</div>
				</div>
			</form>
			<hr />
			<br />
			<%
			// Scriplet 2: Chequea el contenido de la cesta 
			ArrayList<LibroPedido> cesta = (ArrayList<LibroPedido>) session.getAttribute("carrito");
			if (cesta != null && cesta.size() > 0)
			{
			%>
			<p>
				<strong>Tu cesta contiene:</strong>
			</p>
			<table class="table table-dark table-hover">
				<tr>
					<th>Título</th>
					<th>Autor</th>
					<th>Cantidad</th>
					<th>Precio</th>

					<th>&nbsp;</th>
				</tr>
				<%
				// Scriplet 3: Muestra los libros del carrito 
				for (int i = 0; i < cesta.size(); i++)
				{
					LibroPedido elementoPedido = cesta.get(i);
				%>
				<tr>
					<form name="borrarForm" action="shopping" method="POST">
						<input type="hidden" name="todo" value="remove"> <input
							type="hidden" name="indiceElemento" value="<%=i%>">
						<td align="center"><%=elementoPedido.getTitulo()%></td>
						<td lign="center"><%=elementoPedido.getAutor()%></td>
						<td align="center"><%=elementoPedido.getCantidad()%></td>
						<td align="center"><%=elementoPedido.getPrecio()%> €</td>
						<td><input class="btn btn-outline-light" type="submit"
							value="Eliminar de la cesta"></td>
					</form>
				</tr>
				<%
				}
				%>
			</table>
			<br />
			<form name="checkoutForm" action="shopping" method="POST">
				<input type="hidden" name="todo" value="checkout">
				<button class="btn btn-outline-dark boton" type="submit"
					value="Confirmar compra">Confirmar compra</button>
					</form>
			<%
			}
			%>
		</div>
		<br> 
		<br>
	</section>
	<!-- End form -->
</body>

</html>