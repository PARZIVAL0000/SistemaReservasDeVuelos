<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Modelo.Vuelos" %>
<%@page import="Modelo.CarteleraViajes"%>
<%@page import="ModeloDAO.VuelosDAO"%>
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
        
        
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;500&display=swap" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        
        <link rel="stylesheet" href="/ReservaDeVuelos/Pages/Boleteria/estilos/style.css"/>
        
        <title>JSP Page</title>
    </head>
    <body class="body">
        <%
            VuelosDAO vd = new VuelosDAO();
            List<CarteleraViajes> listadoVuelos = vd.listadoCarteleraViajes();
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
        
        
        <div class="perfil d-inline-block m-auto w-100">
            <h2 class="text-capitalize fs-2 p-4">Bienvenido Dennis Ponce</h2>
        </div>

        <div class="tabla_info bg-color">
            <div class="funcionalidades">
                <ul>
                    <li><a href="/ReservaDeVuelos/BoleteriaControllers?accion=crear" class="btn btn-primary">Crear nueva reserva</a></li>
                    <li class="buscador">
                        <p class="text-light text-uppercase">Buscar por: </p>
                        
                        <div class="form-search">
                            <input type="search" placeholder="Filtrar Rápido" class="form-control w-30"/>
                            <input type="submit" class="btn btn-secondary w-30 fs-15 text-uppercase" value="buscar"/>
                        </div>
                        
                    </li>
                </ul>
            </div>
            
            <table id="tlb" class="table table-dark table-striped table-borderless table-sm">
                <thead>
                  <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Estado</th>
                    <th scope="col">Aerolinea</th>
                    <th scope="col">Pasaje</th>
                    <th scope="col">Fecha Disponible</th>
                    <th scope="col">Hora_llegada</th>
                    <th scope="col">Hora_salida</th>
                    <th scope="col">Pais Origen</th>
                    <th scope="col">Pais Destino</th>
                    <th scope="col">Realizar</th>
                    <th scope="col">Realizar</th>
                  </tr>
                </thead>
                <tbody>
                  <% for(CarteleraViajes j : listadoVuelos){  %>
                    <tr>
                      <th scope="row"><%= j.getId() %></th>
                      
                      <% if(j.getEstado() == 1){ %>
                        
                        <td>Disponible</td>
                      <% }else{ %>
                        <td>No Disponible</td>
                      <% } %>
                        
                      <td><%=  j.getAerolinea()   %></td>
                      <td>$<%= j.getTarifa()      %></td>
                      <td><%=  j.getFecha()       %></td>
                      <td><%=  j.getHoraLlegada() %></td>
                      <td><%=  j.getHoraSalida()  %></td>
                      <td><%=  j.getPaisOrigen()  %></td>
                      <td><%=  j.getPaisDestino() %></td>
                      <!-- .eliminar .actualizar -->
                      <td class="m-auto">
                          <div class="eliminar_contenedor btn btn-danger m-auto">  
                            <a href="/ReservaDeVuelos/BoleteriaControllers?accion=eliminar&id=<%= j.getId() %>" id="eliminar">Eliminar</a>
                          </div>
                      </td>
                      <td class="">
                          <div class="actualizar_contenedor btn btn-success">  
                            <a href="/ReservaDeVuelos/BoleteriaControllers?accion=actualizar&id=<%= j.getId() %>" id="actualizar">Actualizar</a>
                          </div>
                      </td>
                    </tr>
                  <% } %>
                </tbody>
            </table>
            
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
