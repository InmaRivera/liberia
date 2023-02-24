<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Tienda Libros</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
</head>
<style>
    .fondo {
        /* background-image: url("https://album.mediaset.es/eimg/2020/04/01/Fagw0vREqPnTbfcRoKSHm6.jpg?w=480"); */
        background-image: url("https://mobimg.b-cdn.net/v3/fetch/2b/2bcbfcc8b66946e37b52c8abf6d42965.jpeg");
    }

    .texto {
        color: #fff;
    }
</style>

<body>
   <h3 class="text-center mb-4 text-danger">Error! Debes introducir tu usuario</h3>
    <div class="vh-100 d-flex justify-content-center align-items-center">
        <div class="col-md-4 p-5 shadow border-1 rounded-3 fondo">
            <h2 class="text-center mb-4 text-info">Login</h2>
            <form method="post" action="login">
                <div class="mb-3">
                    <label for="exampleInputEmail1" class="form-label texto">Usuario</label>
                    <input type="text" class="form-control border border-primary" name="usuario" id="usuario"
                        aria-describedby="emailHelp" placeholder="Nombre de Usuario o email">
                </div>
                <div class="mb-3">
                    <label for="exampleInputPassword1" class="form-label texto">Contraseña</label>
                    <input type="password" class="form-control border border-primary" name="password" id="password"
                        placeholder="Introduce tu clave">
                </div>
                <p class="small"><a class="text-primary fw-bold" href="forget-password.html">¿Olvidaste la
                        contraseña?</a></p>
                <div class="d-grid">
                    <button class="btn btn-outline-info" type="submit">Login</button>
                </div>
            </form>
            <div class="mt-3">
                <p class="mb-0 text-center texto">¿No tienes contraseña? <a href="signup.html"
                        class="text-primary fw-bold">Entra</a></p>
            </div>
        </div>
    </div>
</body>

</html>