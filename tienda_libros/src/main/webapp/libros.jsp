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
<title>Libros</title>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<style>
.fondo:hover {
	background-color: #4bf5d9;
	color: #000;
}

.trans {
	opacity: 0.5;
}

.trans:hover {
	opacity: 0.9;
	font-size: larger;
	font-weight: 200;
} 

body {
	background-image:
		url("https://album.mediaset.es/eimg/2020/04/01/Fagw0vREqPnTbfcRoKSHm6.jpg?w=480");
	background-repeat: 100% no-repeat;
	background-size: 100% auto;
	height: 100%;
	padding-top: 5%;
	padding-right: 20%;
	padding-left: 20%;
}

h1,h3, p {
	font-family: 'Pacifico', cursive;
	font-family: 'Shantell Sans', cursive;
	color: #000;
	text-shadow: #fff 3px 6px 4px;
	text-align: center;
}
</style>

<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary fixed-top">
  <div class="container-fluid ">
    <a class="navbar-brand letra text-center" href="principal.jsp">Inicio</a>
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
<br>

	<h1>Libros</h1>
	<p><%String mensaje = (String) request.getAttribute("response");
			if(mensaje != null){
				out.println(mensaje);
				request.removeAttribute("response");
			} %></p>
	<h3>Listado libros disponibles</h3>
	<!-- Example Code -->
	<section>
		<div class="list-group text-center">
			<form name="AgregarForm" action="shopping" method="POST">
				<input type="hidden" name="todo" value="add">
				<table class="table table-dark table-hover">
					<tr>

						<th>ID</th>
						<th>Título Libro</th>
						<th>Autor</th>
						<!-- 	<th>Editorial</th>  -->
						<th>Stock</th>
						<th>Opción</th>
					</tr>
					<%
					Libreria_pra libreria = new Libreria_pra(); //OBJETO LIBRERIA
					libreria.cargarDatos();//cargamos los datos de libreria 
					/* ControladorLibros.cargarLibros(); */
					LibroPra libro = new LibroPra();//OBJETO LIBROS
					/* 		ControladorLibros mostrarLibros = new ControladorLibros(); 
							mostrarLibros.cargarLibros(); */
					for (int i = 0; i < libreria.tamano(); i++)
					{
					%>
					<td><%=libreria.getId(i)%></td>
					<td><%=libreria.getTitulo(i)%></td>
					<td><%=libreria.getAutor(i)%></td>
					<%-- 	<td><%=libreria.getEditorial(i) %></td>  --%>
					<td><%=libreria.getStock(i)%></td>
					<!-- Eliminar libro  -->
					<%
					libreria.borrar(i);
					int  idLibro = -1;
					String respuesta = "";			
					%>
					<td><input class="btn btn-outline-danger" type="submit"
						value="borrar"></td>
					</tr>
					<%
					respuesta = "<h2>Eliminado correctamente</h2>";
					}
					%>
					<%-- 		<%
				out.println("<tr>");
				
				out.println("<td>'"+libro.getIdLibro()+"'</td>");
				out.println("<td>'"+libro.getTituloLi()+"'</td>");
				out.println("<td>'"+libro.getAutor()+"'</td>");
				out.println("<td>'"+libro.getIdEditorialFK()+"'</td>");
				out.println("<td>'"+libro.getCantidadLi()+"'</td>");
					
				out.println("</tr>");
				 %> --%>
				
				</table>
			</form>
			<!-- 	<td><input class="btn btn-outline-light" type="submit"
							value="Cambiar estado pedido"></td> -->
			<a href="AltaLibro.jsp"
				class="list-group-item list-group-item-action trans fondo">Alta libros</a>
			<a href="modificacionLibro.jsp"
				class="list-group-item list-group-item-action trans fondo">Modificar
				libro</a>
			<!--    <a href="#" class="list-group-item list-group-item-action fondo">MOSTRAR EDITORIAL</a>
            <a href="#" class="list-group-item list-group-item-action fondo">MOSTRAR EDITORIAL</a>
            <a href="#" class="list-group-item list-group-item-action fondo">MOSTRAR EDITORIAL</a> -->
			<br>
			<form name="checkout" action="shopping" method="POST">
				<button name="todo" value="volver" class="btn btn-dark boton">Volver</button>
			</form>
		</div>

	</section>
	<!-- End Example Code -->
</body>

</html>