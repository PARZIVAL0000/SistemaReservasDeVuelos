
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Modelo.CarteleraViajes"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.Aerolineas"%>
<%@page import="Modelo.Pasajes"%>
<%@page import="Modelo.Pais"%>
<%@page import="Modelo.Vuelos"%>
<%@page import="ModeloDAO.AerolineasDAO"%>
<%@page import="ModeloDAO.PasajesDAO"%>
<%@page import="ModeloDAO.PaisDAO"%>
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
        
        
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;500&display=swap" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        
        <link rel="stylesheet" href="/ReservaDeVuelos/Pages/Boleteria/estilos/style.css"/>
        
        <title>Actualizar Reserva</title>
    </head>
    <body class="body">
        <%
            //primero instancedgiaremos cada uno de nuestros DAO.
            AerolineasDAO ad = new AerolineasDAO();
            PasajesDAO pd = new PasajesDAO();
            PaisDAO paisd = new PaisDAO();
            
            //obtenemos la informacion pasada desde nuestro controlador para poder hacer uso de ella.
            List<Vuelos> registro = (ArrayList) request.getAttribute("registro");
            //vamos ir obteniendo listado de datos de nuestra base de datos; por ejemplo> aerolineas, pasajes, paises
            List<Aerolineas> listadoAerolineas = ad.listarAerolineas();
            List<Pasajes> listadoPasajes = pd.listarPasajes();
            List<Pais> listadoPaises = paisd.listarPaises();
            

            String fecha_llegada = registro.get(0).getHora_llegada().split(":")[0]+":"+registro.get(0).getHora_llegada().split(":")[1];
            String fecha_salida = registro.get(0).getHora_salida().split(":")[0]+":"+registro.get(0).getHora_salida().split(":")[1];
            
        %>
        
        <div class="encabezado bg-primary p-3">
            
            <div class="encabezado_titulo">
                <h1 class="text-light fs-40">Perfil <span>Ventas</span></h1>
                <p class="text-light">Actualizar de Reservas</p>
            </div>
            
            <div class="navegacion">
                <nav>
                    <a href="/ReservaDeVuelos/BoleteriaControllers?accion=logout" class="text-light text-uppercase fs-5">Cerrar Sesión</a>
                </nav>
            </div>
            
        </div>
        
        <div class="perfil">
            <h2>Bienvenido Dennis Ponce</h2>
        </div>
        
        <div class="contenedor_creacion card p-5 bg-color">
            <p class="text-light">Modifica los campos a continuacion para poder generar una actualización</p>
            <div class="reserva_actualizacion">
                <div class="funcionalidad_extra">
                    <a href="/ReservaDeVuelos/BoleteriaControllers?accion=regresar" class="btn btn-warning btn-lg">Regresar</a>
                </div>
                <form class="formulario" action="/ReservaDeVuelos/BoleteriaControllers?accion=modificar" method="POST">
                    <div class="campos grid-inputs">
                        <div class="campo1 campo">

                            <div class="input-group mb-3 entrada">
                                <label class="input-group-text" for="inputGroupSelect01">Aerolinea</label>
                                <select class="form-select" id="inputGroupSelect01" name="Aerolinea">
                                    <option value="" disabled selected>Aerolineas disponibles</option>
                                      <% for(Aerolineas j : listadoAerolineas){ %>
                                          <% if(j.getId() == registro.get(0).getAerolinea_id() ){ %>
                                            <option value="<%= j.getNombre() %>" selected><%= j.getNombre() %></option>
                                          <% }else if(j.getId() != registro.get(0).getAerolinea_id()){ %>
                                            <option value="<%= j.getNombre() %>"><%= j.getNombre() %></option>
                                          <%}%>
                                    <% } %>
                                </select>
                            </div>
                        </div>
                        <div class="campo2 campo">

                            <div class="input-group mb-3 entrada">
                                <label class="input-group-text" for="inputGroupSelect01">Pasaje  </label>
                                <select class="form-select" id="inputGroupSelect01" name="Pasaje">
                                    <option value="" disabled selected>Pasajes Disponibles</option>
                                    <!-- Lo que vamos a realizar a continuacion es una comparacion, para poner nuestro pasaje que se
                                    encuentra registrado.-->
                                    <% for(Pasajes j : listadoPasajes){ %>
                                        <% if(j.getId() == registro.get(0).getPasaje_id()){ %>
                                            <option value="<%= j.getTarifa() %>" selected><%= j.getTarifa() %></option>
                                        <% }else{ %>
                                            <option value="<%= j.getTarifa() %>"><%= j.getTarifa() %></option>
                                        <% } %>
                                    <% } %>
                                </select>
                            </div>
                        </div>

                        <div class="campo3 campo">
                            <div class="input-group mb-3 entrada">
                                <label class="input-group-text" for="inputGroupSelect01">Disponible  </label>
                                <select class="form-select" id="inputGroupSelect01" name="Disponible">
                                    <option value="" disabled selected>Seleccionar la disponibilidad de la reserva</option>
                                    <!-- Lo que vamos a realizar a continuacion es una comparacion, para poner nuestro pasaje que se
                                    encuentra registrado.-->
                                    <% if(registro.get(0).getEstado() == 1){ %>
                                        <option value="1" selected>disponible</option>
                                        <option value="0">no disponible</option>
                                    <% }else{ %>
                                        <option value="1">disponible</option>
                                        <option value="0" selected>no disponible</option>
                                    <% } %>
                                </select>
                            </div>
                        </div>

                        <div class="campo4 campo">
                            <div class="input-group mb-3 entrada">
                                <span class="input-group-text" id="inputGroup-sizing-default">Fecha Disponible</span>
                                <input type="date" name="FechaDisponible" value="<%= registro.get(0).getFecha() %>" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                            </div>
                        </div>

                        <div class="campo5 campo">
                            <div class="input-group mb-3 entrada">
                                <span class="input-group-text" id="inputGroup-sizing-default">Hora Salida</span>
                                <input type="time" name="HoraSalida" value="<%= fecha_salida %>" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                            </div>
                        </div>

                        <div class="campo6 campo">
                            <div class="input-group mb-3 entrada">
                                <span class="input-group-text" id="inputGroup-sizing-default">Hora Llegada</span>
                                <input type="time" name="HoraLlegada" value="<%= fecha_llegada %>" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                            </div>
                        </div>

                        <div class="campo7 campo">

                            <div class="input-group mb-3 entrada">
                                <label class="input-group-text" for="inputGroupSelect01">Pais Origen</label>
                                <select class="form-select" id="inputGroupSelect01" name="Pais_Origen">
                                   <option value="" selected> Escoge pais origen</option>
                                    <% for(Pais i : listadoPaises){ %>
                                        <% if(i.getId() == registro.get(0).getPais_origen()){ %>
                                            <option value="<%= i.getPaisNombre() %>" selected><%= i.getPaisNombre() %></option>
                                        <% }else{ %>
                                            <option value="<%= i.getPaisNombre() %>"><%= i.getPaisNombre() %></option>
                                        <% } %>
                                    <% } %>
                                </select>
                            </div>
                        </div>

                        <div class="campo8 campo">
                            <div class="input-group mb-3 entrada">
                                <label class="input-group-text" for="inputGroupSelect01">Pais Destino</label>
                                <select class="form-select" id="inputGroupSelect01" name="Pais_Destino">
                                   <option value="" selected> Escoge pais origen</option>
                                    <% for(Pais i : listadoPaises){ %>
                                        <% if(i.getId() == registro.get(0).getPais_destino()){ %>
                                            <option value="<%= i.getPaisNombre() %>" selected><%= i.getPaisNombre() %></option>
                                        <% }else{ %>
                                            <option value="<%= i.getPaisNombre() %>"><%= i.getPaisNombre() %></option>
                                        <% } %>
                                    <% } %>
                                </select>
                            </div>
                        </div>
                        <input type="hidden" name="id" value="<%= registro.get(0).getId() %>"/>

                        <input type="submit" value="actualizar reserva" class="btn btn-primary button"/>

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
