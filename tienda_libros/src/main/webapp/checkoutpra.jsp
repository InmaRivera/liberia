<%-- Página de confirmación del pedido --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true" import="java.util.*, es.studium.MVC.*"%>
<!DOCTYPE html>
<html lang="es">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="https://getbootstrap.com/docs/5.2/assets/css/docs.css"
	rel="stylesheet">

<title>Confirmación</title>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
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

section {
	background-color: #f7efd6;
	opacity: 0.9;
	border-radius: 10px 10px 10px 10px;
}
.p1{
text-align: right;
}
.letra{
color: #fff;
}
</style>

<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary fixed-top">
  <div class="container-fluid ">
    <a class="navbar-brand letra text-center" href="orderpra.jsp">Inicio</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse d-flex justify-content-end" id="navbarNav">
      <ul class="navbar-nav">
    
        <li class="nav-item ">
          <a class="nav-link letra" href="logout.jsp">
		<button name="todo" value="logout" class="btn btn-danger boton p1">Cerrar Sesión  <i class="bi bi-power"></i></button></a>
        </li>
      </ul>
    </div>
  </div>
</nav>
<form name="checkout" action="shopping" method="POST">
		</form>
	 <br>
	<h1>Confirmar pedido</h1>
	<br>
	<br>

	<section class="pt-5">
		<p>
			<strong>Has comprado los siguientes libros:</strong>
		</p>
		<!-- table -->
		<div class=" justify-content-center text-center">

			<table class="table table-dark table-hover">
				<tr>
					<th>Título</th>
					<th>Autor</th>
					<th>Cantidad</th>
					<th>Precio</th>

				</tr>
				<%
		// Muestra los elementos del carrito
		ArrayList<LibroPedido> cesta = (ArrayList<LibroPedido>) session.getAttribute("carrito");
		for (LibroPedido item : cesta)
		{
			%>
				<tr>
					<td><%=item.getTitulo()%></td>
					<td><%=item.getAutor()%></td>
					<td><%=item.getCantidad()%></td>
					<td><%=item.getPrecio()%> €</td>

				</tr>
				<% } session.invalidate(); %>
				<hr>
				<tr>
					<th align="right" colspan="2">Total</th>
					
					<th align="right"><%=request.getAttribute("cantidadTotal")%></th>
					<th align="right"><%=request.getAttribute("precioTotal")%>€</th>
				</tr>
			</table>
			<br /> <br />
			<p>Pulsa aquí para realizar otro pedido</p>
			<div class="col-12 pt-5">
			<a href="orderpra.jsp" class="btn btn-outline-dark boton">Hacer otro pedido</a>
		
			</div>
		</div>
		<br />

	</section>
	<!-- End form -->
</body>

</html>