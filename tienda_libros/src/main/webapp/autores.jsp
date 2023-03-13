<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" import="java.util.*, es.studium.MVC.*"%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/5.2/assets/css/docs.css" rel="stylesheet">
    <title>Autores</title>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <%		String usuario = (String) session.getAttribute("usuario");
	if(usuario==null){  
		response.sendRedirect("./index.jsp");
	}
	%>
</head>
<style>
    .fondo:hover {
        background-color: #4bf5d9;
        color: #000;
    }

 /*    a {
        opacity: 0.5;
    }

    a:hover {
        opacity: 0.9;
        font-size: larger;
        font-weight: 200;
    } */

    body {
        background-image: url("https://album.mediaset.es/eimg/2020/04/01/Fagw0vREqPnTbfcRoKSHm6.jpg?w=480");
        background-repeat: 100% no-repeat;
        background-size: 100% auto;
        height: 100%;
        padding-top: 5%;
        padding-right: 20%;
        padding-left: 20%;
    }

    h1,
    p {
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
<br>
<br>
    <h1>Autores</h1>
    <div class="list-group text-center">
        <table class="table table-dark table-hover">
            <tr>
            	<th>ID</th>
                <th>Nombre Autor</th>
            </tr>
            
			<%
			ArrayList<Autores> listadoAutor = ControladorAutor.getAutor();
			for (int i = 0; i < listadoAutor.size(); i++)
			{
				Autores autor = listadoAutor.get(i);
		/* 	out.println(autor);  */
				
			%>
            <tr>
            <td><%=autor.getIdAutor() %></td>
             <td><%=autor.getAutor() %></td>
            </tr>
            <%
				}
				%>
        </table>
        	<form name="checkout" action="shopping" method="POST">
				<button name="todo" value="volver" class="btn btn-dark boton">Volver</button>
			</form>
    </div>
</body>

</html>