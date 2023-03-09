<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page session="true" import="java.util.*, es.studium.MVC.*" %>
        <!DOCTYPE html>
        <html lang="es">

        <head>
            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
            <link href="https://getbootstrap.com/docs/5.2/assets/css/docs.css" rel="stylesheet">
            <title>Editar Libros</title>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
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

            a {
                opacity: 0.7;
                color: #fff;
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

            h1,
            h3,
            p {
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
            <h1>Modificar libro</h1>
            <!-- Example Code -->
            <section>
                <h3>Seleccione el libro a modificar</h3>
                <br>
                <div class="list-group bg-dark fondo">
                    <div class="centrar">
                        <form name="AgregarForm" action="shopping" method="POST">
                            <input type="hidden" name="todo" value="add">


                            <div class="form-floating">
                                <select class="form-select" id="floatingSelect"
                                    aria-label="Floating label select example">
                                    <option selected>idLibro</option>
                                    <option value="1">Libro</option>
                                    <option value="2">Libro</option>
                                    <option value="3">Libro</option>
                                </select>
                                <label class="color:#000;" for="floatingSelect">Seleccione un libro:</label>
                            </div>
                            <br>
                            <br>

                            <div class="mb-3">
                                <label for="formGroupExampleInput" class="form-label label1">Nombre Libro</label>
                                <input type="text" class="form-control" id="formGroupExampleInput"
                                    placeholder="Nombre Libro">
                            </div>
                            <div class="mb-3">
                                <label for="formGroupExampleInput2" class="form-label label1">Nombre Autor</label>
                                <input type="text" class="form-control" id="formGroupExampleInput2"
                                    placeholder="Nombre Autor">
                            </div>
                            <div class="mb-3">
                                <label for="formGroupExampleInput2" class="form-label label1">Nombre Editorial</label>
                                <input type="text" class="form-control" id="formGroupExampleInput2"
                                    placeholder="Nombre Editorial">
                            </div>
                            <div class="mb-3">
                                <label for="formGroupExampleInput2" class="form-label label1">Cantidad Libro</label>
                                <input type="text" class="form-control" id="formGroupExampleInput2"
                                    placeholder="Cantidad Stock">
                            </div>
                            <div class="mb-3">
                                <label for="formGroupExampleInput2" class="form-label label1">Precio</label>
                                <input type="text" class="form-control" id="formGroupExampleInput2"
                                    placeholder="Precio">
                            </div>
                        </form>
                    </div>
                    </form>
                    <div class="text-center">
                        <form class="" name="checkout" action="shopping" method="POST">
                            <button name="todo" value="Editar" class="btn btn-success boton">Editrar</button>

                            <form name="checkout" action="shopping" method="POST">
                                <button name="todo" value="volver" class="btn btn-danger boton">Volver</button>
                            </form>
                        </form>
                    </div>
            </section>
            <!-- End Example Code -->
        </body>

        </html>