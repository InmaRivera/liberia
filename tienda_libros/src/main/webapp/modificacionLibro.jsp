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
<title>Editar Libros</title>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<% String usuario=(String) session.getAttribute("usuario"); int tipoUsuario=1; %>
</head>
<style>
.centrar {
	padding-left: 4%;
	padding-top: 3%;
	padding-right: 4%;
}

.label1 {
	color: #fff;
}

/* a {
	opacity: 0.7;
	color: #fff;
}

a:hover {
	opacity: 0.9;
	font-size: larger;
	font-weight: 200;
} */

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

h1, h3, p {
	font-family: 'Pacifico', cursive;
	font-family: 'Shantell Sans', cursive;
	color: #000;
	text-shadow: #fff 3px 6px 4px;
	text-align: center;
}

.fondo {
	opacity: .9;
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
<br>

	<h1>Modificar libro</h1>
	<%
			String mensaje = (String) request.getAttribute("response");
			if(mensaje != null){
				out.println(mensaje);
				request.removeAttribute("response");
			}
		%>
	<!-- Example Code -->
	<section>
		<h3>Seleccione el libro a modificar</h3>
		<br>
		<div class="list-group bg-dark fondo">
			<div class="centrar">
				<form name="AgregarForm" action="shopping" method="POST">
					<input type="hidden" name="todo" value="add">


					<div class="form-floating">
						<select class="form-select" id="idLibro"
							aria-label="Floating label select example">
							<%
					Libreria_pra libreria = new Libreria_pra(); //OBJETO LIBRERIA
					libreria.cargarDatos();//cargamos los datos de libreria
					ArrayList<Editoriales> listadoEditorial = ControladorEditorial.getEditoriales();
					int idLibro = 0;
					String tituloLibro = "";
					int cantidad = 0;
					double precio= 0;
					int editorialFK =0;
					int AutorFK=0;
					
					LibroPra libro = new LibroPra();//OBJETO LIBROS 
					Editoriales editorial = new Editoriales();

					for (int i = 0; i < Libreria_pra.tamano(); i++)
					{
						out.println("<option class='text-center' value='" + libreria.getId(i) + "'>");
						out.println(libreria.getId(i) + " " + libreria.getTitulo(i) + "  " + libreria.getAutor(i) + " "
						+ libreria.getPrecio(i) + " €");
						out.println("</option>");
					}
					libreria.modificarLibro(idLibro, tituloLibro, cantidad, precio, editorialFK, AutorFK);
					%>
						</select> <label class="color:#000;" for="floatingSelect">Seleccione
							un libro:</label>
					</div>
					<br> <br>
	
					<div class="mb-3">
						<label for="idLibro" class="form-label label1">ID libro</label> <input
							type="text" class="form-control" id="idLibro"
							value="<%=libreria.getId(idLibro) %>" placeholder="ID Libro">
					</div>
					<div class="mb-3">
						<label for="titulo" class="form-label label1">Titulo Libro</label>
						<input type="text" class="form-control" id="titulo"
							value="<%=libreria.getTitulo(idLibro) %>"
							placeholder="Titulo Libro">
					</div>
					<div class="mb-3">
						<label for="editorial" class="form-label label1">Nombre
							Editorial</label> <input type="text" class="form-control" id="editorial"
							value="<%=libreria.getEditorial(idLibro) %>"
							placeholder="Nombre Editorial">
					</div>
					<div class="mb-3">
						<label for="cantidad" class="form-label label1">Cantidad
							Libro</label> <input type="text" class="form-control" id="cantidad"
							value="<%=libreria.getStock(idLibro) %>"
							placeholder="Cantidad Stock">
					</div>
					<div class="mb-3">
						<label for="precio" class="form-label label1">Precio</label> <input
							type="text" class="form-control" id="precio"
							value="<%=libreria.getPrecio(idLibro) %>" placeholder="Precio">
					</div>
				</form>
			</div>
		
			</form>
			<div class="text-center">
				<%libreria.modificarLibro(idLibro, tituloLibro, cantidad, precio, editorialFK, AutorFK); %>
				<form name="modificar" action="shopping" method="POST">
					<button name="todo" value="Editar"
						class="btn btn-success boton">Editar</button>

					<form name="checkout" action="shopping" method="POST">
						<button name="todo" value="volver" class="btn btn-danger boton">Volver</button>
					</form>
				</form>
			</div>
	</section>
	<!-- End Example Code -->
</body>

</html>