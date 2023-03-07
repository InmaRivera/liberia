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
<title>Pedidos</title>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	<%		String usuario = (String) session.getAttribute("usuario");
	int tipoUsuario = 1; 
	%>
</head>
<style>
.fondo:hover {
	background-color: #4bf5d9;
	color: #000;
}

a {
	opacity: 0.7;
	color:#fff;
}

a:hover {
	opacity: 0.9;
	font-size: larger;
	font-weight: 200;
}

body {
	background-image:
		url("https://album.mediaset.es/eimg/2020/04/01/Fagw0vREqPnTbfcRoKSHm6.jpg?w=480");
	background-repeat: no-repeat;
	background-size: 100% auto;
	height: 100%;
	padding-top: 5%;
	padding-right: 20%;
	padding-left: 20%;
}

h1, p {
	font-family: 'Pacifico', cursive;
	font-family: 'Shantell Sans', cursive;
	color: #000;
	text-shadow: #fff 3px 6px 4px;
	text-align: center;
}
</style>

<body>
	<h1>Pedidos</h1>
	<!-- Example Code -->
	<section>
		<div class="list-group text-center">
			<table class="table table-dark table-hover">
				<tr>
					<th>Lista de pedidos</th>
					<th>Estado del pedido</th>
				</tr>
				<% 
				ControladorLibros listalibro = new ControladorLibros();
				Pedidos pedido = new Pedidos();
				for (int i=0; i >= pedido.hashCode(); i++)
				{
					out.println("<td class='text-center'>" + pedido.getIdPedido() + " ");
					out.println(pedido.isEnviado()+ "'>");
					out.println("</td>");
					
				}
				%>
				<tr>
					<td><%pedido.getIdPedido(); %></td>
						<td><%pedido.isEnviado(); %><input class="btn btn-outline-light" type="submit"
							value="Cambiar estado pedido"></td>
				</tr>
			</table>
<!-- 			<a 	class="list-group-item list-group-item-action bg-dark">Estado del pedido</a> -->
			<br>
			<!-- <a href="#" class="list-group-item list-group-item-action fondo">MOSTRAR EDITORIAL</a>
            <a href="#" class="list-group-item list-group-item-action fondo">MOSTRAR EDITORIAL</a>
            <a href="#" class="list-group-item list-group-item-action fondo">MOSTRAR EDITORIAL</a> -->
			<form name="checkout" action="shopping" method="POST">
				<button name="todo" value="volver" class="btn btn-dark boton">Volver</button>
			</form>
		</div>
	</section>
	<!-- End Example Code -->
</body>

</html>