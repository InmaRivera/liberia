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
<title>Tienda Libros</title>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<%
String username = (String) session.getAttribute("usuario");
if (username == null)
{
	response.sendRedirect("./index.jsp");
}
%>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<style>
.fondo:hover {
	background-color: #4bf5d9;
	color: #000;
}

a {
	opacity: 0.5;
}

a:hover {
	opacity: 0.9;
}

body {
	background-image:
		url("https://album.mediaset.es/eimg/2020/04/01/Fagw0vREqPnTbfcRoKSHm6.jpg?w=480");
	background-repeat: no-repeat;
	background-size: 100% auto;
	height: 100%;
	padding-top: 10%;
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

h5 {
	color: #fff;
	text-shadow: #000 3px 6px 4px;
	text-align: center;
}
</style>

<body>

	<h1>Bienvenido a la tienda</h1>
	<small> <%="<h5> Bienvenido,  " + username + "</h5>"%></small>
	<!-- Example Code -->
	<div class="list-group text-center">
		<a href="#" class="list-group-item list-group-item-action fondo"
			aria-current="true">
			LIBROS</a> 
		<a href="#"	class="list-group-item list-group-item-action fondo"><%
			Editoriales editorial = new Editoriales();
			%>EDITORIALES</a> 
		<a href="#" class="list-group-item list-group-item-action fondo ">AUTORES</a> 
		<!-- <a href="#" class="list-group-item list-group-item-action fondo"></a>  -->
		<a href="#"	class="list-group-item list-group-item-action fondo">PEDIDOS</a>
		<br>
		 <form name="checkout" action="shopping" method="POST">
		<button href="index.jsp" name="todo" value="logout" class="btn btn-outline-light boton">SALIR</button>
	</form>
	</div>
	<!-- End Example Code -->
</body>

</html>