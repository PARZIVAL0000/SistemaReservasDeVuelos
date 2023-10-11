<%@page import="Modelo.Usuarios"%>
<%@page import="Modelo.TipoPasajeros"%>
<%@page import="Modelo.Genero"%>
<%@page import="Modelo.Provincia"%>
<%@page import="Modelo.Usuario_Origen"%>
<%@page import="Modelo.Usuario_Destino"%>
<%@page import="Modelo.Origen"%>
<%@page import="Modelo.Destino"%>
<%@page import="ModeloDAO.UsuariosDAO"%>
<%@page import="ModeloDAO.TipoPasajerosDAO"%>
<%@page import="ModeloDAO.GeneroDAO"%>
<%@page import="ModeloDAO.ProvinciaDAO"%>
<%@page import="ModeloDAO.Usuario_OrigenDAO"%>
<%@page import="ModeloDAO.Usuario_DestinoDAO"%>
<%@page import="ModeloDAO.OrigenDAO"%>
<%@page import="ModeloDAO.DestinoDAO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en"> <!--<![endif]-->
    <head>
        
        <meta charset='utf-8'>
        <meta content='IE=edge,chrome=1' http-equiv='X-UA-Compatible'>
        <title>Dashboard Personal</title>
        <meta content='lab2023' name='author'>
        <meta content='' name='description'>
        <meta content='' name='keywords'>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <link href="Pages/Cliente/assets/stylesheets/application.css" rel="stylesheet" type="text/css" /><link href="//netdna.bootstrapcdn.com/font-awesome/3.2.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <link href="Pages/Cliente/assets/images/vuelos-de-google.png" rel="icon" type="image/ico" />
        <link rel="stylesheet" href="css/modificado/cliente.css"/>
        
    </head>
    <body id="perfil">
        
        <% 
            List<Usuarios> informacion = (ArrayList) request.getAttribute("info");
            
            //vamos a declarar nuestro dao
            Usuario_OrigenDAO uod = new Usuario_OrigenDAO();
            Usuario_DestinoDAO udd = new Usuario_DestinoDAO();
            OrigenDAO od = new OrigenDAO();
            DestinoDAO dd = new DestinoDAO();
            //dentro de este punto lo que haremos es extraer la informacion de vuelos del usuario....
            // pasajeros / origen y destino / tipo clase vuelo / aerolinea / 
            int id = informacion.get(0).getId();
            List<Usuario_Origen> usuarioOrigen = uod.listarRegistros();
            List<Usuario_Destino> usuarioDestino = udd.listarRegistros();
            //vamos a sacar solo los registros que concuerden con el identificador que tenemos de nuestro perfil...
            List<Usuario_Origen> usuarioOrigenPerfil = new ArrayList<>();
            List<Usuario_Destino> usuarioDestinoPerfil = new ArrayList<>();
            
            for(Usuario_Origen j : usuarioOrigen){
                
                if(j.getUsuario_id() == id){
                    usuarioOrigenPerfil.add(j);
                }
            }
            
            for(Usuario_Destino j : usuarioDestino){
                if(j.getUsuario_id() == id){
                    usuarioDestinoPerfil.add(j);
                }
            }
            
            
            //con ayuda de las tablas intermedias vamos a obtener los demas vuelos
            List<Origen> vuelos_origen = new ArrayList<>();
            for(Usuario_Origen uo : usuarioOrigenPerfil){
                int id_vuelo = uo.getOrigen_id();
                Origen org = new Origen();
                org.setId(id_vuelo);
                
                List<Origen> registro = od.filtroRegistro2(org);
                vuelos_origen.add(registro.get(0));
            }
            
            List<Destino> vuelos_destino = new ArrayList<>();
            for(Usuario_Destino ud : usuarioDestinoPerfil){
                int id_vuelo = ud.getDestino_id();
                Destino dst = new Destino();
                dst.setId(id_vuelo);
                
                List<Destino> registro = dd.filtroRegistro2(dst);
                vuelos_destino.add(registro.get(0));
            }
            
            //crearemos un codigo el cual me permita verificar por el numero de usos de una aerolinea...
            
        %>
        
        <!-- Navbar -->
        <div class='navbar navbar-default' id='navbar'>
            <nav class="navbar navbar-expand-lg bg-body-tertiary">
              <div class="container-fluid">
                <a class='navbar-brand' href='#'>
                    <i class='bx bxs-plane'><img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAAAXNSR0IArs4c6QAAAelJREFUSEvt1r+rz1EYwPHXjTuISS5K3Qklg+EWZcE/YJDh+jGixGY0cAeDxSIpTPJruInFykZKMVhksviRTCYUPTqf23F8Pt/v+Xy69S156vTtc77Ped7P85zzPOdMmZBMTYhrKHgtDiSn7+NL3wCGgDfiJTYk2EfsQPxWyxDwaVwuCDF3pZrKoFSfx7kCsoCYr5YhEf8HR3r/3VRH/T7CruIUfUh1/bT2dPU5XDsRzWJTh/FvOIUbNfBacNTpJUxXGA1wOBCOdMo48CrcxMEKYK7yLKX+fde6UeAteIhtPaGNekCjn4cTf0kXeD/uYPVAaLOsc99L8ApcxJkRwM9YV/zfNperXEeck6V9z8EzeIDdLdAXuJvG8Za+HG00jB9KY67Fxh/7noPLHvwmgW7hbWYoLojyQojv6F6NbMbR5MTWbH6pw5XgY7iXgBFlm/S9JCL6yMR8qvHfTufguNg/4eeYA9UX3JgL1vrmwTCujpcj4tY4+oKjc0WZlQ1lMaXzR2359QFvx+30vmqz/wpH8LoGXgNeibNpjOvV33EhjZHR14D34EkWxdeU7qtp7iQOY02ms69YU90yc8W9eIwor2sJGvBcAhrwE4jyWRbwLOIt/bxm7xD3djwM3o3Sr0l1Ja+f2sTAvwD+fF4fochyAQAAAABJRU5ErkJggg=="/></i>
                    AEROILUMINATY
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                  <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNavDropdown">
                  <ul class="navbar-nav">
                    <li class="nav-item dropdown">
                      <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <i class='icon-user'></i>
                        <strong><%= informacion.get(0).getNombre().split(" ")[0] %> <%= informacion.get(0).getApellido().split(" ")[0] %></strong>
                      </a>
                      <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="/ReservaDeVuelos/ClienteControllers?accion=logout">Cerrar Sesión</a></li>
                      </ul>
                    </li>
                  </ul>
                </div>
              </div>
            </nav>
        </div>  
                
        <div id='wrapper'>
          <!-- Sidebar -->
            <section id='sidebar'>
                <i class='icon-align-justify icon-large' id='toggle'></i>
                <ul id='dock'>
                    <li class='launcher'>
                        <!--<i class='icon-dashboard'></i>-->
                        <i class='icon-file-text-alt'></i>
                        <a href="/ReservaDeVuelos/ClienteControllers?accion=informacion&id=<%= informacion.get(0).getId() %>">Información</a>
                    </li>
                    <li class='active launcher'>
                        <i class='icon-table'></i>
                        <a href="/ReservaDeVuelos/ClienteControllers?accion=vuelos&id=<%= informacion.get(0).getId() %>">Vuelos</a>
                    </li>
                </ul>
                <div data-toggle='tooltip' id='beaker' title='Made by lab2023'></div>
            </section>
            <!-- Tools -->
            <section id='tools'>
                <ul class='breadcrumb' id='breadcrumb'>
                    <li class='title'>Vuelos</li>

                </ul>
                <div id='toolbar'>
                </div>
            </section>
            <!-- Content -->
          <div id='content'>
            <div class='panel panel-default'>
              <div class='panel-heading'>
                <i class='icon-beer icon-large'></i>
                Vuelos Cliente
                <div class='panel-tools'>
                  <div class='btn-group'>
                    <a class='btn' data-toggle='toolbar-tooltip' href='#' title='Toggle'>
                      <i class='icon-chevron-down'></i>
                    </a>
                  </div>
                </div>
              </div>
              <div class='panel-body'>
                  
                    <div class='page-header'>
                      <h4>Listado de Opciones</h4>
                    </div>
                  
                    <div class="listado-opciones">
                        <div class="opcion opcion-1" id="aerolinea">
                            <div class="img">
                                <div class="img-hangar"></div>
                            </div>
                            <div class="enlace">
                                <a href="#">Aerolíneas Comunes</a>
                            </div>
                        </div>
                        <div class="opcion opcion-2" id="vuelo_origen">
                            <div class="img">
                                <div class="img-vueloOrigen"></div>                                
                            </div>
                            <div class="enlace">
                                <a href="#">Vuelos Origen</a>
                            </div>
                        </div>
                        <div class="opcion opcion-3" id="vuelo_destino">
                            <div class="img">
                                <div class="img-vueloDestino"></div>
                            </div>
                            <div class="enlace">
                                <a href="#">Vuelos Destino</a>
                            </div>
                        </div>
                    </div>
                
                    <div class="contenedor-informacion-opciones">
                        <div class="info show" id="aerolinea">
                            
                            <h3 class="aerolinea-heading">Bienvenido a la seccion de Aerolineas</h3>   
                            
                            <table class="table table-warning">
                                <thead>
                                    <th>Id</th>
                                    <th>Nombre Aerolinea</th>
                                    <th>Estado</th>
                                </thead>
                                <tbody>
                                    <%  String valor = "";
                                        for(Origen j : vuelos_origen){ 
                                            if(!valor.equals(j.getAero_nombre())){
                                    %>
                                                <tr>
                                                    <td><%= j.getId() %></td>
                                                    <td><%= j.getAero_nombre() %></td>
                                                    <td>Disponible</td>
                                                </tr>
                                    <% 
                                                valor = j.getAero_nombre();
                                            }
                                        } 
                                    %>
                                </tbody>
                            </table>
                        </div>
                        <div class="info" id="vuelo_origen">
                            <h3 class="vuelo_origen-heading">Bienvenido a la seccion de vuelos origen</h3>
                            
                            <table class="table table-danger">
                                <thead>
                                    <th>Id</th>
                                    <th>Fecha</th>
                                    <th>Hora salida</th>
                                    <th>Hora llegada</th>
                                    <th>Pais Desde</th>
                                </thead>
                                <tbody>
                                    <% for(Origen j : vuelos_origen){ %>
                                    <tr>
                                        <td><%= j.getId() %></td>
                                        <td><%= j.getFecha() %></td>
                                        <td><%= j.getHora_salida() %></td>
                                        <td><%= j.getHora_llegada() %></td>
                                        <td><%= j.getPaisnombre() %></td>
                                    </tr>
                                    <% } %>
                                </tbody>
                            </table>
                        </div>
                        <div class="info" id="vuelo_destino">
                            <h3 class="vuelo_destino-heading">Bienvenido a la seccion de vuelos destino</h3>
                            
                            <table class="table table-success">
                                <thead>
                                    <th>Id</th>
                                    <th>Fecha</th>
                                    <th>Hora salida</th>
                                    <th>Hora llegada</th>
                                    <th>Pais Destino</th>
                                </thead>
                                <tbody>
                                    <% for(Destino j : vuelos_destino){ %>
                                    <tr>
                                        <td><%= j.getId() %></td>
                                        <td><%= j.getFecha() %></td>
                                        <td><%= j.getHora_salida() %></td>
                                        <td><%= j.getHora_llegada() %></td>
                                        <td><%= j.getPaisnombre() %></td>
                                    </tr>
                                    <% } %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                  
              </div>
            </div>
          </div>
        </div>
              
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
        <!-- Footer -->
        <!-- Javascripts -->
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js" type="text/javascript"></script><script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js" type="text/javascript"></script><script src="//cdnjs.cloudflare.com/ajax/libs/modernizr/2.6.2/modernizr.min.js" type="text/javascript"></script><script src="assets/javascripts/application-985b892b.js" type="text/javascript"></script>
        <!-- Google Analytics -->
        <script>
          var _gaq=[['_setAccount','UA-XXXXX-X'],['_trackPageview']];
          (function(d,t){var g=d.createElement(t),s=d.getElementsByTagName(t)[0];
          g.src=('https:'==location.protocol?'//ssl':'//www')+'.google-analytics.com/ga.js';
          s.parentNode.insertBefore(g,s)}(document,'script'));
        </script>
        
        
        <!--<script src="Pages/Cliente/assets/javascripts/funcionalidad/app.js"></script>-->
        <script src="js/scripts/cliente.js"></script>
    </body>
</html>