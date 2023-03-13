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
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
<title>Pedido</title>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/AlertifyJS/1.13.1/css/alertify.min.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/AlertifyJS/1.13.1/css/themes/default.min.css" />
<script src="js/alertify.min.js"></script>

<%
String usuario = (String) session.getAttribute("usuario");
if (usuario == null)
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
	background-repeat: 100% no-repeat;
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

.p1 {
	text-align: right;
}

.letra {
	color: #fff;
}
</style>

<body>
	<nav class="navbar navbar-expand-lg bg-body-tertiary fixed-top">
		<div class="container-fluid ">
			<a class="navbar-brand letra text-center" href="orderpra.jsp">Inicio</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse d-flex justify-content-end"
				id="navbarNav">
				<ul class="navbar-nav">

					<li class="nav-item "><a class="nav-link letra"
						href="logout.jsp">
							<button name="todo" value="logout"
								class="btn btn-danger boton p1">
								Cerrar Sesión <i class="bi bi-power"></i>
							</button>
					</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<br>
	<br>
	<br>
	<form name="checkout" action="shopping" method="POST"></form>
	<h1>Realizar pedido</h1>
	<%="<h5> Hola " + usuario + "</h5>"%>

	<br>
	<br>
	<section class="pt-5">
		<p>
			<strong>Elegir un libro y la cantidad:</strong>
		</p>
		<%
		String mensaje = (String) request.getAttribute("response");
		if (mensaje != null)
		{
			out.println(mensaje);
			request.removeAttribute("response");
		}
		%>
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
					/* LibroPra libro = new LibroPra(); *///OBJETO LIBROS 
					System.out.println("tamaño libreria : " + Libreria_pra.tamano());
					for (int i = 0; i < Libreria_pra.tamano(); i++)
					{
						/* out.println("<option class='text-center' value='" + libreria.getId(i) + "'>"); */
						out.println("<option class='text-center' value='" + i + "'>");
						out.println(libreria.getId(i) + " " + libreria.getTitulo(i) + "  " + libreria.getAutor(i) + " "
						+ libreria.getPrecio(i) + " €");
						out.println("</option>");
					}
					%>

				</select>
				<div class="input-group">
					<span class="input-group-text">Indique la cantidad:</span> <input
						type="text" class="form-control" type="text" name="cantidad"
						size="10" value="1">
				</div>
				<br> <br> <input type="submit" value="Añadir a la cesta"
					class="btn btn-outline-dark boton">
				<!-- 	<div class="col-12 pt-5">
					 <button class="btn btn-outline-dark boton" type="submit"
							value="Añadir a la cesta">Añadir a la cesta</button> 
				</div> -->

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
						<!-- <td><input type="submit" value="Eliminar de la cesta" class="btn btn-outline-light"></td> -->
						<td><input class="btn btn-outline-light" type="submit"
							value="Eliminar de la cesta"></td>
					</form>
				</tr>
				<%
				}
				%>
			</table>
			<br>
			<form name="checkoutForm" action="shopping" method="POST">
				<input type="hidden" name="todo" value="checkout">
				<button class="btn btn-outline-dark boton" type="submit"
					value="Confirmar compra">Confirmar compra</button>
			</form>
			<%
			}
			%>
		</div>

	</section>
	<br>
	<br>
	<!-- End form -->
</body>

</html>