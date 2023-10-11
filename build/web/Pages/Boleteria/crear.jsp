<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Modelo.Aerolineas"%>
<%@page import="Modelo.Pasajes"%>
<%@page import="Modelo.Pais"%>
<%@page import="ModeloDAO.PasajesDAO"%>
<%@page import="ModeloDAO.AerolineasDAO"%>
<%@page import="ModeloDAO.PaisDAO"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        
        <link rel="apple-touch-icon" href="apple-touch-icon.png">
        <link rel="stylesheet" href="/ReservaDeVuelos/css/bootstrap.min.css">
        <link rel="stylesheet" href="/ReservaDeVuelos/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="/ReservaDeVuelos/css/fontAwesome.css">
        <link rel="stylesheet" href="/ReservaDeVuelos/css/hero-slider.css">
        <link rel="stylesheet" href="/ReservaDeVuelos/css/owl-carousel.css">
        <link rel="stylesheet" href="/ReservaDeVuelos/css/datepicker.css">
        <link rel="stylesheet" href="/ReservaDeVuelos/css/modificado/style.css"/>
        <link rel="stylesheet" href="/ReservaDeVuelos/css/tooplate-style.css">
        <script src="js/vendor/modernizr-2.8.3-respond-1.4.2.min.js"></script>
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">

        <link rel="stylesheet" href="/ReservaDeVuelos/Pages/Boleteria/estilos/style.css"/>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;500&display=swap" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">

        
        <title>Crear Reserva</title>
    </head>
    <body>
        <%
            AerolineasDAO ad = new AerolineasDAO();
            PasajesDAO pd = new PasajesDAO();
            PaisDAO psd = new PaisDAO();
            
            List<Aerolineas> listadoAerolineas = ad.listarAerolineas();
            //nos vamos a traer los pasajes...
            List<Pasajes> listadoPasajes = pd.listarPasajes();
            //nos vamos a traer los paises
            List<Pais> listadoPaises = psd.listarPaises();
            System.out.println(listadoPaises);
        %>
        
       
        
        <div class="encabezado">
            
            <div class="encabezado_titulo">
                <h1>Perfil<span>Ventas</span></h1>
                <p>Actualizar de Reserva</p>
            </div>
            
            <div class="navegacion">
                <nav>
                    <a href="/ReservaDeVuelos/BoleteriaControllers?accion=logout">Cerrar Sesión</a>
                </nav>
            </div>
        </div>
        
        <div class="perfil">
            <h2>Bienvenido Dennis Ponce</h2>
        </div>
        
        <div class="contenedor_creacion">
            <p>Rellena los campos a continuacion para generar la creacion de una nueva reserva</p>
            <div class="reserva_creacion">
                <div class="funcionalidad_extra">
                    <a href="/ReservaDeVuelos/BoleteriaControllers?accion=regresar" class="btn btn-warning btn-lg">Regresar</a>
                </div>
                <form class="formulario" action="/ReservaDeVuelos/BoleteriaControllers?accion=generarReserva" method="POST">
                    <div class="campos">
                        <div class="campo1 campo">

                            <div class="input-group mb-3  entrada">
                                <label class="input-group-text" for="inputGroupSelect01">Aerolinea</label>
                                <select class="form-select" id="inputGroupSelect01" name="Aerolinea">
                                    <option value="" selected disabled>Aerolineas disponibles</option>
                                      <% for(Aerolineas j : listadoAerolineas){ %>
                                    <option value="<%= j.getNombre() %>"><%= j.getNombre() %></option>
                                    <% } %>
                                </select>
                            </div>
                        </div>
                        <div class="campo2 campo">

                            <div class="input-group mb-3 entrada">
                                <label class="input-group-text" for="inputGroupSelect01">Pasaje  </label>
                                <select class="form-select" id="inputGroupSelect01" name="Pasaje">
                                    <option value="" disabled selected>Pasajes Disponibles</option>
                                    <% for(Pasajes j : listadoPasajes){ %>
                                    <option value="<%= j.getTarifa() %>"><%= j.getTarifa() %></option>
                                    <% } %>
                                </select>
                            </div>
                        </div>
                        <div class="campo3 campo">
                            <div class="input-group mb-3 entrada">
                                <span class="input-group-text" id="inputGroup-sizing-default">Fecha Disponible</span>
                                <input type="date" class="form-control" name="FechaDisponible" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                            </div>
                        </div>

                        <div class="campo4 campo">
                            <div class="input-group mb-3 entrada">
                                <span class="input-group-text" id="inputGroup-sizing-default">Hora Salida</span>
                                <input type="time" class="form-control" name="HoraSalida" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                            </div>
                        </div>

                        <div class="campo5 campo">
                            <div class="input-group mb-3 entrada">
                                <span class="input-group-text" id="inputGroup-sizing-default">Hora Llegada</span>
                                <input type="time" class="form-control" name="HoraLlegada" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                            </div>
                        </div>

                        <div class="campo6 campo">

                            <div class="input-group mb-3 entrada">
                                <label class="input-group-text" for="inputGroupSelect01">Pais Origen</label>
                                <select class="form-select" id="inputGroupSelect01" name="Pais_Origen">
                                   <option value="" selected> Escoge pais origen</option>
                                    <% for(Pais i : listadoPaises){ %>
                                        <option value="<%= i.getPaisNombre() %>"><%= i.getPaisNombre() %></option>
                                    <% } %>
                                </select>
                            </div>
                        </div>

                        <div class="campo7 campo">
                            <div class="input-group mb-3 entrada">
                                <label class="input-group-text" for="inputGroupSelect01">Pais Destino</label>
                                <select class="form-select" id="inputGroupSelect01" name="Pais_Destino">
                                   <option value="" selected> Escoge pais origen</option>
                                    <% for(Pais i : listadoPaises){ %>
                                        <option value="<%= i.getPaisNombre() %>"><%= i.getPaisNombre() %></option>
                                    <% } %>
                                </select>
                            </div>
                        </div>

                        <input type="submit" value="crear reserva" class="btn btn-outline-primary button"/>

                    </div>
                </form>
            </div>
        </div>
                            
        <footer>
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="primary-button">
                            <a href="#" class="scroll-top">Volver al Inicio</a>
                        </div>
                    </div>
                    <div class="col-md-12">
                        <ul class="social-icons">
                            <li><a href="https://www.facebook.com/tooplate"><i class="fa fa-facebook"></i></a></li>
                            <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                            <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                            <li><a href="#"><i class="fa fa-rss"></i></a></li>
                            <li><a href="#"><i class="fa fa-behance"></i></a></li>
                        </ul>
                    </div>

                    </div>
                </div>
            </div>
        </footer>
                
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script>window.jQuery || document.write('<script src="js/vendor/jquery-1.11.2.min.js"><\/script>')</script>

        <script src="js/vendor/bootstrap.min.js"></script>

        <script src="js/datepicker.js"></script>
        <script src="js/plugins.js"></script>
        <script src="js/main.js"></script>

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js" type="text/javascript"></script>
        <script type="text/javascript">
        $(document).ready(function() {



            // navigation click actions 
            $('.scroll-link').on('click', function(event){
                event.preventDefault();
                var sectionID = $(this).attr("data-id");
                scrollToID('#' + sectionID, 750);
            });
            // scroll to top action
            $('.scroll-top').on('click', function(event) {
                event.preventDefault();
                $('html, body').animate({scrollTop:0}, 'slow');         
            });
            // mobile nav toggle
            $('#nav-toggle').on('click', function (event) {
                event.preventDefault();
                $('#main-nav').toggleClass("open");
            });
        });
        // scroll function
        function scrollToID(id, speed){
            var offSet = 0;
            var targetOffset = $(id).offset().top - offSet;
            var mainNav = $('#main-nav');
            $('html,body').animate({scrollTop:targetOffset}, speed);
            if (mainNav.hasClass("open")) {
                mainNav.css("height", "1px").removeClass("in").addClass("collapse");
                mainNav.removeClass("open");
            }
        }
        if (typeof console === "undefined") {
            console = {
                log: function() { }
            };
        }
        </script>
    </body>
</html>
