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
<% String usuario=(String) session.getAttribute("usuario"); int tipoUsuario=1; %>
</head>
<style>
a {
	opacity: 0.7;
	color: #fff;
}

.centrar {
	padding-left: 4%;
	padding-top: 3%;
	padding-right: 4%;
}

a:hover {
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

h1, p {
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
	<h1>Registrar libro</h1>
	<br>
	<!-- Example Code -->
	<section class="">
		<div class="list-group bg-dark fondo">
			<div class="mb-3 centrar">
				<form name="AgregarForm" action="shopping" method="POST">
					<input type="hidden" name="todo" value="add">
					<div class="mb-3">
						<label for="formGroupExampleInput" class="form-label">Nombre
							Libro</label> <input type="text" class="form-control"
							id="formGroupExampleInput" placeholder="Nombre Libro">
					</div>
					<div class="mb-3">
						<label for="formGroupExampleInput2" class="form-label">Nombre
							Autor</label> <input type="text" class="form-control"
							id="formGroupExampleInput2" placeholder="Nombre Autor">
					</div>
					<div class="mb-3">
						<label for="formGroupExampleInput2" class="form-label">Nombre
							Editorial</label> <input type="text" class="form-control"
							id="formGroupExampleInput2" placeholder="Nombre Editorial">
					</div>
					<div class="mb-3">
						<label for="formGroupExampleInput2" class="form-label">Cantidad
							Libro</label> <input type="text" class="form-control"
							id="formGroupExampleInput2" placeholder="Cantidad Stock">
					</div>
					<div class="mb-3">
						<label for="formGroupExampleInput2" class="form-label">Precio</label>
						<input type="text" class="form-control"
							id="formGroupExampleInput2" placeholder="Precio">
					</div>
				</form>
			</div>
			<div class="text-center">
				<form class="" name="checkout" action="shopping" method="POST">
					<button name="todo" value="Confirmar" class="btn btn-success boton">Confirmar</button>

					<form name="checkout" action="shopping" method="POST">
						<button name="todo" value="volverLibros" class="btn btn-danger boton">Volver</button>
					</form>
				</form>
			</div>
			<br>
	</section>
	<br>
	<!-- End Example Code -->
</body>

</html>