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

.trans {
	opacity: 0.7;
}

.trans:hover {
	opacity: 0.9;
} 

body {
	background-image:
		url("https://album.mediaset.es/eimg/2020/04/01/Fagw0vREqPnTbfcRoKSHm6.jpg?w=480");
	background-repeat: 100% no-repeat;
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
.letra{
color:#fff;
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
	<h1>Bienvenido a la tienda</h1>
	<small> <%="<h5> Bienvenido,  " + username + "</h5>"%></small>
	<!-- Example Code -->
	<div class="list-group text-center">
	<a href="libros.jsp" class="list-group-item list-group-item-action fondo trans" aria-current="true">LIBROS</a> 
		<a href="editoriales.jsp"class="list-group-item list-group-item-action fondo trans">EDITORIALES</a> 
		<a href="autores.jsp" class="list-group-item list-group-item-action fondo trans">AUTORES</a> 
		<a href="pedidos.jsp" class="list-group-item list-group-item-action fondo trans">PEDIDOS</a>
		<br>
		 <form name="checkout" action="shopping" method="POST">
		<button name="todo" value="logout" class="btn btn-dark boton">SALIR</button>
	</form>
	</div>
	<!-- End Example Code -->
</body>

</html>