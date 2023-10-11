<%@page import="Modelo.Usuarios"%>
<%@page import="Modelo.TipoPasajeros"%>
<%@page import="Modelo.Genero"%>
<%@page import="Modelo.Provincia"%>
<%@page import="ModeloDAO.UsuariosDAO"%>
<%@page import="ModeloDAO.TipoPasajerosDAO"%>
<%@page import="ModeloDAO.GeneroDAO"%>
<%@page import="ModeloDAO.ProvinciaDAO"%>
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

    </head>
    <body id="perfil">
        
        <% 
            List<Usuarios> informacion = (ArrayList) request.getAttribute("info");
            
            //vamos a instancias nuestros daos
            TipoPasajerosDAO tpd = new TipoPasajerosDAO();
            GeneroDAO gd = new GeneroDAO();
            ProvinciaDAO pd = new ProvinciaDAO();

            //vamos a traernos la informacion tipo de pasajero
            List<TipoPasajeros> tipoPasajeros = tpd.listadoRegistros();
            
            //vamos a traernos la informacion del genero
            List<Genero> genero = gd.listadoRegistros();
            
            //vamos a traernos la informacion de provincia
            List<Provincia> provincia = pd.listarRegistro();
            
            System.out.println(informacion.get(0).getProvincia());
            //a continuacion lo que haremos es mostrar dicha informacion dentro de nuestro html.
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
              <li class='active launcher'>
                <!--<i class='icon-dashboard'></i>-->
                <i class='icon-file-text-alt'></i>
                <a href="/ReservaDeVuelos/ClienteControllers?accion=informacion&id=<%= informacion.get(0).getId() %>">Información</a>
              </li>
              <li class='launcher'>
                <i class='icon-table'></i>
                <a href="/ReservaDeVuelos/ClienteControllers?accion=vuelos&id=<%= informacion.get(0).getId() %>">Vuelos</a>
              </li>
              <li class='launcher dropdown hover'>
                
                <ul class='dropdown-menu'>
                  <li class='dropdown-header'>Launcher description</li>
                  <li>
                    <a href='#'>Action</a>
                  </li>
                  <li>
                    <a href='#'>Another action</a>
                  </li>
                  <li>
                    <a href='#'>Something else here</a>
                  </li>
                </ul>
              </li>
             
            </ul>
            <div data-toggle='tooltip' id='beaker' title='Made by lab2023'></div>
          </section>
          <!-- Tools -->
          <section id='tools'>
            <ul class='breadcrumb' id='breadcrumb'>
              <li class='title'>Información</li>

            </ul>
            <div id='toolbar'>

            </div>
          </section>
          <!-- Content -->
          <div id='content'>
            <div class='panel panel-default'>
              <div class='panel-heading'>
                <i class='icon-beer icon-large'></i>
                Información Cliente
                <div class='panel-tools'>
                  <div class='btn-group'>
<!--                    <a class='btn' href='#'>
                      <i class='icon-refresh'></i>
                      Refresh statics
                    </a>-->
                    <a class='btn' data-toggle='toolbar-tooltip' href='#' title='Toggle'>
                      <i class='icon-chevron-down'></i>
                    </a>
                  </div>
                </div>
              </div>
              <div class='panel-body'>
                <div class='page-header'>
                  <h4>Datos Generales Cliente</h4>
                </div>
                  
                  <div class="formulario_info">
                       <form method="POST" action="/ReservaDeVuelos/ClienteControllers?accion=actualizarDatos">
                          <input type="hidden" name="id" value="<%= informacion.get(0).getId()  %>"/>
                          <div class="input-group mb-3">
                            <span class="input-group-text" id="inputGroup-sizing-default">Nombres</span>
                            <input type="text" class="form-control" name="Nombre" aria-label="Sizing example input" value="<%= informacion.get(0).getNombre() %>" aria-describedby="inputGroup-sizing-default" disabled>
                          </div>
                          
                          <div class="input-group mb-3">
                            <span class="input-group-text" id="inputGroup-sizing-default">Apellidos</span>
                            <input type="text" class="form-control" name="Apellido" aria-label="Sizing example input" value="<%= informacion.get(0).getApellido() %>" aria-describedby="inputGroup-sizing-default" disabled>
                          </div>
                          
                          <div class="input-group mb-3">
                            <span class="input-group-text" id="inputGroup-sizing-default">Correo</span>
                            <input type="text" class="form-control" name="Correo" aria-label="Sizing example input" value="<%= informacion.get(0).getCorreo() %>" aria-describedby="inputGroup-sizing-default" disabled>
                          </div>
                          
                          <div class="input-group mb-3">
                            <span class="input-group-text" id="inputGroup-sizing-default">Celular</span>
                            <input type="text" class="form-control" name="Celular" aria-label="Sizing example input" value="<%= informacion.get(0).getCelular() %>" aria-describedby="inputGroup-sizing-default" disabled>
                          </div>
                          
                          <div class="input-group mb-3">
                            <span class="input-group-text" id="inputGroup-sizing-default">Cedula</span>
                            <input type="text" class="form-control" name="Cedula" aria-label="Sizing example input" value="<%= informacion.get(0).getCedula() %>" aria-describedby="inputGroup-sizing-default" disabled>
                          </div>
                          
                          <div class="input-group mb-3">
                            <span class="input-group-text" id="inputGroup-sizing-default">Tipo Pasajero</span>
                            <select style="width:100%;height:3.3rem" class="form-select seleccion" aria-label="Default select example" name="TipoPasajero" disabled>
                              <option disabled>Selecciona por el tipo de pasajero</option>
                              <% for(TipoPasajeros j : tipoPasajeros){ %>
                                <% if(j.getNombre().equals(informacion.get(0).getTipoPasajero())){ %>
                                    <option value="<%= j.getNombre() %>" selected><%= j.getNombre() %></option>
                                <% }else{ %>
                                    <option value="<%= j.getNombre() %>"><%= j.getNombre() %></option>
                                <% } %>
                              <% } %>
                            </select>
                          </div>
                          
                          <div class="input-group mb-3">
                            <span class="input-group-text" id="inputGroup-sizing-default">Genero</span>
                            <select style="width:100%;height:3.3rem" class="form-select seleccion" aria-label="Default select example" name="Genero" disabled>
                              <option disabled>Selecciona por el tipo de genero</option>
                              <% for(Genero j : genero){ %>
                                <% if(j.getTipo().equals(informacion.get(0).getGenero())){ %>
                                    <option value="<%= j.getTipo() %>" selected><%= j.getTipo() %></option>
                                <% }else{ %>
                                    <option value="<%= j.getTipo() %>"><%= j.getTipo() %></option>
                                <% } %>
                              <% } %>
                            </select>
                          </div>
                          
                          <div class="input-group mb-3">
                            <span class="input-group-text" id="inputGroup-sizing-default">Provincia</span>
                            <select style="width:100%;height:3.3rem" class="form-select seleccion" aria-label="Default select example" name="Provincia" disabled>
                              <option disabled>Selecciona por el tipo de provincia</option>
                              <% for(Provincia j : provincia){ %>
                               
                                <% if(j.getNombre().equals(informacion.get(0).getProvincia())){ %>
                                    <option value="<%= j.getNombre() %>" selected><%= j.getNombre() %></option>
                                <% }else{ %>
                                    <option value="<%= j.getNombre() %>"><%= j.getNombre() %></option>
                                <% } %> 
                              <% } %>
                            </select>
                          </div>
                          
                          <div class="campo_botones">  
                            <button type="button" id="actualizar" class="btn btn-success">Actualizar</button>
                          </div>
                          
                      </form>
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
        
        
        <script src="Pages/Cliente/assets/javascripts/funcionalidad/app.js"></script>
        
    </body>
</html>