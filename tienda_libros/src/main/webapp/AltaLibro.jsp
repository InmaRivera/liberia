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
<title>Alta Libros</title>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<%
String usuario = (String) session.getAttribute("usuario");
int tipoUsuario = 1;
%>
</head>
<style>
.trans {
	opacity: 0.7;
	color: #fff;
}

.centrar {
	padding-left: 4%;
	padding-top: 3%;
	padding-right: 4%;
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

label {
	color: #fff;
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

<body class="bg-dark">
	<nav class="navbar navbar-expand-lg bg-body-tertiary fixed-top">
		<div class="container-fluid ">
			<a class="navbar-brand letra text-center" href="principal.jsp">Inicio</a>
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
	<h1>Registrar libro</h1>
	<p>
		<%
		String msg = (String) session.getAttribute("response");
		if (msg != null)
		{
			out.println(msg);
			session.removeAttribute("response");
		}
		%>
	</p>
	<br>
	<!-- Example Code -->
	<section>
		<div class="list-group bg-dark fondo">
			<div class="mb-3 centrar">
				<form name="AgregarForm" action="shopping" method="POST">
					<input type="hidden" name="todo" value="Nuevo">
					<div class="mb-3">
						<%
						Libreria_pra libreria = new Libreria_pra(); //OBJETO LIBRERIA
						libreria.cargarDatos();//cargamos los datos de libreria
						ArrayList<Editoriales> listadoEditorial = new ArrayList<Editoriales>();
						ArrayList<Autores> listadoAutor = new ArrayList<Autores>();
						String tituloLibro = "";
						double precio = 0;
						int cantidad = 0;
						String Editorial = "";
						String Autor = "";
						/* libreria.insertarLibro(tituloLibro, precio, cantidad);
						libreria.insertarEditorial(Editorial);
						libreria.insertarAutor(Autor); */

						LibroPra libro = new LibroPra();//OBJETO LIBROS 
						ArrayList<LibroPra> listalibros = (ArrayList<LibroPra>) session.getAttribute("Nuevo");
						%>
						<label for="Libro" class="form-label">Nombre Libro</label> <input
							type="text" class="form-control" id="Libro"
							value="<%=libro.getTituloLi()%>" placeholder="Nombre Libro">
					</div>
					<div class="mb-3">
						<label for="Autor" class="form-label">Nombre Autor</label> <input
							type="text" class="form-control" id="Autor"
							value="<%=libro.getAutor()%>" placeholder="Nombre Autor">
					</div>
					<div class="mb-3">
						<label for="Editorial" class="form-label">Nombre Editorial</label>
						<input type="text" class="form-control" id="Editorial"
							value="<%=libro.getEditorial()%>" placeholder="Nombre Editorial">
					</div>
					<div class="mb-3">
						<label for="Cantidad" class="form-label">Cantidad Libro</label> <input
							type="text" class="form-control" id="Cantidad"
							value="<%=libro.getCantidadLi()%>" placeholder="Cantidad Stock">
					</div>
					<div class="mb-3">
						<label for="Precio" class="form-label">Precio</label> <input
							type="text" class="form-control" id="Precio"
							value="<%=libro.getCantidadLi()%>" placeholder="Precio">
					</div>
				</form>

			</div>
			<div class="text-center">
				<form name="AltaLibro" action="shopping" method="POST">
					<%
					ArrayList<LibroPra> confirmar = (ArrayList<LibroPra>) session.getAttribute("Confirmar");
					%>
					<a type="submit" name="todo" value="Confirmar"
						class="btn btn-success boton">Confirmar</a>


					<form name="checkout" action="shopping" method="POST">
						<button name="todo" value="volverLibros"
							class="btn btn-danger boton">Volver</button>
					</form>
				</form>
			</div>
			<br>
		</div>
	</section>
	<br>
	<!-- End Example Code -->
</body>

</html>